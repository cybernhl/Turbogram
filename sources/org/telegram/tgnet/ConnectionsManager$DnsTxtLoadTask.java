package org.telegram.tgnet;

import android.os.AsyncTask;
import android.util.Base64;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;

class ConnectionsManager$DnsTxtLoadTask extends AsyncTask<Void, Void, NativeByteBuffer> {
    private int currentAccount;

    /* renamed from: org.telegram.tgnet.ConnectionsManager$DnsTxtLoadTask$1 */
    class C09661 implements Comparator<String> {
        C09661() {
        }

        public int compare(String o1, String o2) {
            int l1 = o1.length();
            int l2 = o2.length();
            if (l1 > l2) {
                return -1;
            }
            if (l1 < l2) {
                return 1;
            }
            return 0;
        }
    }

    public ConnectionsManager$DnsTxtLoadTask(int instance) {
        this.currentAccount = instance;
    }

    protected NativeByteBuffer doInBackground(Void... voids) {
        Throwable e;
        ByteArrayOutputStream outbuf;
        NativeByteBuffer buffer;
        InputStream httpConnectionStream = null;
        int i = 0;
        ByteArrayOutputStream outbuf2 = null;
        while (i < 3) {
            String googleDomain;
            String domain;
            if (i == 0) {
                try {
                    googleDomain = "www.google.com";
                } catch (Throwable th) {
                    th = th;
                    outbuf = outbuf2;
                }
            } else if (i == 1) {
                googleDomain = "www.google.ru";
            } else {
                googleDomain = "google.com";
            }
            if (ConnectionsManager.native_isTestBackend(this.currentAccount) != 0) {
                domain = "tapv2.stel.com";
            } else {
                domain = MessagesController.getInstance(this.currentAccount).dcDomainName;
            }
            URLConnection httpConnection = new URL("https://" + googleDomain + "/resolve?name=" + domain + "&type=16").openConnection();
            httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
            httpConnection.addRequestProperty("Host", "dns.google.com");
            httpConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
            httpConnection.setReadTimeout(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
            httpConnection.connect();
            httpConnectionStream = httpConnection.getInputStream();
            outbuf = new ByteArrayOutputStream();
            try {
                JSONArray array;
                int len;
                ArrayList<String> arrayList;
                int a;
                StringBuilder builder;
                byte[] bytes;
                byte[] data = new byte[32768];
                while (!isCancelled()) {
                    int read = httpConnectionStream.read(data);
                    if (read > 0) {
                        outbuf.write(data, 0, read);
                    } else {
                        if (read == -1) {
                        }
                        array = new JSONObject(new String(outbuf.toByteArray(), "UTF-8")).getJSONArray("Answer");
                        len = array.length();
                        arrayList = new ArrayList(len);
                        for (a = 0; a < len; a++) {
                            arrayList.add(array.getJSONObject(a).getString("data"));
                        }
                        Collections.sort(arrayList, new C09661());
                        builder = new StringBuilder();
                        for (a = 0; a < arrayList.size(); a++) {
                            builder.append(((String) arrayList.get(a)).replace("\"", ""));
                        }
                        bytes = Base64.decode(builder.toString(), 0);
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
                            return buffer;
                        }
                        try {
                            outbuf.close();
                            return buffer;
                        } catch (Exception e3) {
                            return buffer;
                        }
                    }
                }
                array = new JSONObject(new String(outbuf.toByteArray(), "UTF-8")).getJSONArray("Answer");
                len = array.length();
                arrayList = new ArrayList(len);
                for (a = 0; a < len; a++) {
                    arrayList.add(array.getJSONObject(a).getString("data"));
                }
                Collections.sort(arrayList, new C09661());
                builder = new StringBuilder();
                for (a = 0; a < arrayList.size(); a++) {
                    builder.append(((String) arrayList.get(a)).replace("\"", ""));
                }
                bytes = Base64.decode(builder.toString(), 0);
                buffer = new NativeByteBuffer(bytes.length);
                buffer.writeBytes(bytes);
                if (httpConnectionStream != null) {
                    httpConnectionStream.close();
                }
                if (outbuf != null) {
                    return buffer;
                }
                outbuf.close();
                return buffer;
            } catch (Throwable th2) {
                e2 = th2;
            }
        }
        outbuf = outbuf2;
        return null;
        if (httpConnectionStream != null) {
            try {
                httpConnectionStream.close();
            } catch (Throwable e22) {
                FileLog.e(e22);
            }
        }
        if (outbuf != null) {
            try {
                outbuf.close();
            } catch (Exception e4) {
            }
        }
        throw th;
        if (outbuf != null) {
            outbuf.close();
        }
        throw th;
        throw th;
    }

    protected void onPostExecute(final NativeByteBuffer result) {
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                if (result != null) {
                    ConnectionsManager.access$302(null);
                    ConnectionsManager.native_applyDnsConfig(ConnectionsManager$DnsTxtLoadTask.this.currentAccount, result.address, UserConfig.getInstance(ConnectionsManager$DnsTxtLoadTask.this.currentAccount).getClientPhone());
                    return;
                }
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("failed to get dns txt result");
                    FileLog.d("start azure task");
                }
                ConnectionsManager$AzureLoadTask task = new ConnectionsManager$AzureLoadTask(ConnectionsManager$DnsTxtLoadTask.this.currentAccount);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                ConnectionsManager.access$302(task);
            }
        });
    }
}
