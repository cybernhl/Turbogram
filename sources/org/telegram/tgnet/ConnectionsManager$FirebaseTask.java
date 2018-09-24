package org.telegram.tgnet;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;

class ConnectionsManager$FirebaseTask extends AsyncTask<Void, Void, NativeByteBuffer> {
    private int currentAccount;
    private FirebaseRemoteConfig firebaseRemoteConfig;

    /* renamed from: org.telegram.tgnet.ConnectionsManager$FirebaseTask$1 */
    class C09691 implements OnCompleteListener<Void> {
        C09691() {
        }

        public void onComplete(Task<Void> finishedTask) {
            final boolean success = finishedTask.isSuccessful();
            Utilities.stageQueue.postRunnable(new Runnable() {
                public void run() {
                    ConnectionsManager.access$302(null);
                    String config = null;
                    if (success) {
                        ConnectionsManager$FirebaseTask.this.firebaseRemoteConfig.activateFetched();
                        config = ConnectionsManager$FirebaseTask.this.firebaseRemoteConfig.getString("ipconfigv2");
                    }
                    if (TextUtils.isEmpty(config)) {
                        if (BuildVars.LOGS_ENABLED) {
                            FileLog.d("failed to get firebase result");
                            FileLog.d("start dns txt task");
                        }
                        ConnectionsManager$DnsTxtLoadTask task = new ConnectionsManager$DnsTxtLoadTask(ConnectionsManager$FirebaseTask.this.currentAccount);
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                        ConnectionsManager.access$302(task);
                        return;
                    }
                    byte[] bytes = Base64.decode(config, 0);
                    try {
                        NativeByteBuffer buffer = new NativeByteBuffer(bytes.length);
                        buffer.writeBytes(bytes);
                        ConnectionsManager.native_applyDnsConfig(ConnectionsManager$FirebaseTask.this.currentAccount, buffer.address, UserConfig.getInstance(ConnectionsManager$FirebaseTask.this.currentAccount).getClientPhone());
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                }
            });
        }
    }

    /* renamed from: org.telegram.tgnet.ConnectionsManager$FirebaseTask$2 */
    class C09702 implements Runnable {
        C09702() {
        }

        public void run() {
            if (BuildVars.LOGS_ENABLED) {
                FileLog.d("failed to get firebase result");
                FileLog.d("start dns txt task");
            }
            ConnectionsManager$DnsTxtLoadTask task = new ConnectionsManager$DnsTxtLoadTask(ConnectionsManager$FirebaseTask.this.currentAccount);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
            ConnectionsManager.access$302(task);
        }
    }

    public ConnectionsManager$FirebaseTask(int instance) {
        this.currentAccount = instance;
    }

    protected NativeByteBuffer doInBackground(Void... voids) {
        try {
            if (ConnectionsManager.native_isTestBackend(this.currentAccount) != 0) {
                throw new Exception("test backend");
            }
            this.firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            this.firebaseRemoteConfig.setConfigSettings(new Builder().setDeveloperModeEnabled(false).build());
            String currentValue = this.firebaseRemoteConfig.getString("ipconfigv2");
            if (BuildVars.LOGS_ENABLED) {
                FileLog.d("current firebase value = " + currentValue);
            }
            this.firebaseRemoteConfig.fetch(0).addOnCompleteListener(new C09691());
            return null;
        } catch (Throwable e) {
            Utilities.stageQueue.postRunnable(new C09702());
            FileLog.e(e);
        }
    }

    protected void onPostExecute(NativeByteBuffer result) {
    }
}
