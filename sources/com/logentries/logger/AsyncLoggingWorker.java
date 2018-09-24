package com.logentries.logger;

import android.content.Context;
import android.util.Log;
import com.logentries.misc.Utils;
import com.logentries.net.LogentriesClient;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AsyncLoggingWorker {
    private static final String INVALID_TOKEN = "Given Token does not look right!";
    public static final int LOG_LENGTH_LIMIT = 65536;
    private static final int MAX_NETWORK_FAILURES_ALLOWED = 3;
    private static final int MAX_QUEUE_POLL_TIME = 1000;
    private static final int MAX_RECONNECT_ATTEMPTS = 3;
    private static final String QUEUE_OVERFLOW = "Logentries Buffer Queue Overflow. Message Dropped!";
    private static final int QUEUE_SIZE = 32768;
    private static final int RECONNECT_WAIT = 100;
    private static final String TAG = "LogentriesAndroidLogger";
    private SocketAppender appender;
    private LogStorage localStorage;
    private ArrayBlockingQueue<String> queue;
    private boolean started;

    private class SocketAppender extends Thread {
        private static final String LINE_SEP_REPLACER = " ";
        private String dataHubAddr;
        private int dataHubPort;
        private boolean isUsingDataHub;
        private LogentriesClient leClient;
        private boolean logHostName = true;
        private String token;
        private boolean useHttpPost;
        private boolean useSsl;

        public SocketAppender(boolean z, boolean z2, boolean z3, String str, int i, String str2, boolean z4) {
            super("Logentries Socket appender");
            setDaemon(true);
            this.useHttpPost = z;
            this.useSsl = z2;
            this.isUsingDataHub = z3;
            this.dataHubAddr = str;
            this.dataHubPort = i;
            this.token = str2;
            this.logHostName = z4;
        }

        private void closeConnection() {
            if (this.leClient != null) {
                this.leClient.close();
            }
        }

        private void openConnection() {
            if (this.leClient == null) {
                this.leClient = new LogentriesClient(this.useHttpPost, this.useSsl, this.isUsingDataHub, this.dataHubAddr, this.dataHubPort, this.token);
            }
            this.leClient.connect();
        }

        private boolean reopenConnection(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("maxReConnectAttempts value must be greater or equal to zero");
            }
            closeConnection();
            int i2 = 0;
            while (i2 < i) {
                try {
                    openConnection();
                    return true;
                } catch (IOException e) {
                    Thread.sleep(100);
                    i2++;
                }
            }
            return false;
        }

        private boolean tryUploadSavedLogs() {
            String str;
            Queue arrayDeque = new ArrayDeque();
            try {
                arrayDeque = AsyncLoggingWorker.this.localStorage.getAllLogsFromStorage(false);
                for (str = (String) arrayDeque.peek(); str != null; str = (String) arrayDeque.peek()) {
                    this.leClient.write(Utils.formatMessage(str.replace("\n", LINE_SEP_REPLACER), this.logHostName, this.useHttpPost));
                    arrayDeque.poll();
                }
                try {
                    AsyncLoggingWorker.this.localStorage.reCreateStorageFile();
                } catch (IOException e) {
                    Log.e(AsyncLoggingWorker.TAG, e.getMessage());
                }
                return true;
            } catch (IOException e2) {
                Log.e(AsyncLoggingWorker.TAG, "Cannot upload logs to the server. Error: " + e2.getMessage());
                try {
                    AsyncLoggingWorker.this.localStorage.reCreateStorageFile();
                    for (String str2 : r2) {
                        AsyncLoggingWorker.this.localStorage.putLogToStorage(str2);
                    }
                } catch (IOException e22) {
                    Log.e(AsyncLoggingWorker.TAG, "Cannot save logs to the local storage - part of messages will be dropped! Error: " + e22.getMessage());
                }
                return false;
            }
        }

        public void run() {
            String str;
            try {
                reopenConnection(3);
                Queue allLogsFromStorage = AsyncLoggingWorker.this.localStorage.getAllLogsFromStorage(true);
                Object obj = null;
                int i = 0;
                while (true) {
                    str = allLogsFromStorage.isEmpty() ? (String) AsyncLoggingWorker.this.queue.poll(1000, TimeUnit.MILLISECONDS) : (String) allLogsFromStorage.poll();
                    while (obj != null) {
                        try {
                            if (reopenConnection(3) && tryUploadSavedLogs()) {
                                obj = null;
                                i = 0;
                            }
                            if (str != null) {
                                this.leClient.write(Utils.formatMessage(str.replace("\n", LINE_SEP_REPLACER), this.logHostName, this.useHttpPost));
                            }
                        } catch (IOException e) {
                            if (i >= 3) {
                                try {
                                    AsyncLoggingWorker.this.localStorage.putLogToStorage(str);
                                    str = null;
                                    obj = 1;
                                } catch (IOException e2) {
                                    Log.e(AsyncLoggingWorker.TAG, "Cannot save the log message to the local storage! Error: " + e2.getMessage());
                                    obj = 1;
                                }
                            } else {
                                i++;
                                reopenConnection(3);
                            }
                        }
                    }
                    if (str != null) {
                        this.leClient.write(Utils.formatMessage(str.replace("\n", LINE_SEP_REPLACER), this.logHostName, this.useHttpPost));
                    }
                }
            } catch (InterruptedException e3) {
            } catch (InstantiationException e4) {
                InstantiationException instantiationException = e4;
                Log.e(AsyncLoggingWorker.TAG, "Cannot instantiate LogentriesClient due to improper configuration. Error: " + instantiationException.getMessage());
                str = (String) AsyncLoggingWorker.this.queue.poll();
                while (str != null) {
                    try {
                        AsyncLoggingWorker.this.localStorage.putLogToStorage(str);
                        str = (String) AsyncLoggingWorker.this.queue.poll();
                    } catch (IOException e5) {
                        Log.e(AsyncLoggingWorker.TAG, "Cannot save logs queue to the local storage - all log messages will be dropped! Error: " + instantiationException.getMessage());
                    }
                }
            }
            closeConnection();
        }
    }

    public AsyncLoggingWorker(Context context, boolean z, String str) {
        this(context, z, false, false, str, null, 0, true);
    }

    public AsyncLoggingWorker(Context context, boolean z, String str, String str2, int i) {
        this(context, z, false, true, str, str2, i, true);
    }

    public AsyncLoggingWorker(Context context, boolean z, boolean z2, String str) {
        this(context, z, z2, false, str, null, 0, true);
    }

    public AsyncLoggingWorker(Context context, boolean z, boolean z2, boolean z3, String str, String str2, int i, boolean z4) {
        this.started = false;
        if (checkTokenFormat(str)) {
            this.queue = new ArrayBlockingQueue(32768);
            this.localStorage = new LogStorage(context);
            this.appender = new SocketAppender(z2, z, z3, str2, i, str, z4);
            this.appender.start();
            this.started = true;
            return;
        }
        throw new IllegalArgumentException(INVALID_TOKEN);
    }

    private static boolean checkTokenFormat(String str) {
        return Utils.checkValidUUID(str);
    }

    private void tryOfferToQueue(String str) {
        if (!this.queue.offer(str)) {
            Log.e(TAG, "The queue is full - will try to drop the oldest message in it.");
            this.queue.poll();
            if (!this.queue.offer(str)) {
                throw new RuntimeException(QUEUE_OVERFLOW);
            }
        }
    }

    public void addLineToQueue(String str) {
        if (!this.started) {
            this.appender.start();
            this.started = true;
        }
        if (str.length() > 65536) {
            for (String tryOfferToQueue : Utils.splitStringToChunks(str, 65536)) {
                tryOfferToQueue(tryOfferToQueue);
            }
            return;
        }
        tryOfferToQueue(str);
    }

    public void close() {
        close(0);
    }

    public void close(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("queueFlushTimeout must be greater or equal to zero");
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.queue.isEmpty() && (j == 0 || System.currentTimeMillis() - currentTimeMillis < j)) {
        }
        this.appender.interrupt();
        this.started = false;
    }
}
