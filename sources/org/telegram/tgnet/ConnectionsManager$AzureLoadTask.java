package org.telegram.tgnet;

import android.os.AsyncTask;
import android.util.Base64;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;

class ConnectionsManager$AzureLoadTask extends AsyncTask<Void, Void, NativeByteBuffer> {
    private int currentAccount;

    public ConnectionsManager$AzureLoadTask(int instance) {
        this.currentAccount = instance;
    }

    protected NativeByteBuffer doInBackground(Void... voids) {
        Throwable e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream httpConnectionStream = null;
        try {
            URL downloadUrl;
            if (ConnectionsManager.native_isTestBackend(this.currentAccount) != 0) {
                downloadUrl = new URL("https://software-download.microsoft.com/testv2/config.txt");
            } else {
                downloadUrl = new URL("https://software-download.microsoft.com/prodv2/config.txt");
            }
            URLConnection httpConnection = downloadUrl.openConnection();
            httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
            httpConnection.addRequestProperty("Host", "tcdnb.azureedge.net");
            httpConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
            httpConnection.setReadTimeout(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
            httpConnection.connect();
            httpConnectionStream = httpConnection.getInputStream();
            ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
            try {
                byte[] bytes;
                NativeByteBuffer buffer;
                byte[] data = new byte[32768];
                while (!isCancelled()) {
                    int read = httpConnectionStream.read(data);
                    if (read > 0) {
                        outbuf.write(data, 0, read);
                    } else {
                        if (read == -1) {
                        }
                        bytes = Base64.decode(outbuf.toByteArray(), 0);
                        buffer = new NativeByteBuffer(bytes.length);
                        buffer.writeBytes(bytes);
                        if (httpConnectionStream != null) {
                            try {
                                httpConnectionStream.close();
                            } catch (Throwable e2) {
                                FileLog.e(e2);
                            }
                        }
                        if (outbuf != null) {
                            try {
                                outbuf.close();
                            } catch (Exception e3) {
                            }
                        }
                        byteArrayOutputStream = outbuf;
                        return buffer;
                    }
                }
                bytes = Base64.decode(outbuf.toByteArray(), 0);
                buffer = new NativeByteBuffer(bytes.length);
                buffer.writeBytes(bytes);
                if (httpConnectionStream != null) {
                    httpConnectionStream.close();
                }
                if (outbuf != null) {
                    outbuf.close();
                }
                byteArrayOutputStream = outbuf;
                return buffer;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = outbuf;
            }
        } catch (Throwable th3) {
            e2 = th3;
            try {
                FileLog.e(e2);
                if (httpConnectionStream != null) {
                    try {
                        httpConnectionStream.close();
                    } catch (Throwable e22) {
                        FileLog.e(e22);
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (httpConnectionStream != null) {
                    try {
                        httpConnectionStream.close();
                    } catch (Throwable e222) {
                        FileLog.e(e222);
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e5) {
                    }
                }
                throw th;
            }
        }
    }

    protected void onPostExecute(final NativeByteBuffer result) {
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                if (result != null) {
                    ConnectionsManager.native_applyDnsConfig(ConnectionsManager$AzureLoadTask.this.currentAccount, result.address, UserConfig.getInstance(ConnectionsManager$AzureLoadTask.this.currentAccount).getClientPhone());
                } else if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("failed to get azure result");
                }
                ConnectionsManager.access$302(null);
            }
        });
    }
}
