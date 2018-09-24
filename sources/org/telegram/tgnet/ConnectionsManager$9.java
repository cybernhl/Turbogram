package org.telegram.tgnet;

import android.os.AsyncTask;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

class ConnectionsManager$9 implements Runnable {
    final /* synthetic */ int val$currentAccount;
    final /* synthetic */ int val$second;

    ConnectionsManager$9(int i, int i2) {
        this.val$second = i;
        this.val$currentAccount = i2;
    }

    public void run() {
        if (ConnectionsManager.access$300() == null && ((this.val$second != 0 || Math.abs(ConnectionsManager.access$400() - System.currentTimeMillis()) >= 10000) && ConnectionsManager.isNetworkOnline())) {
            ConnectionsManager.access$402(System.currentTimeMillis());
            if (this.val$second == 2) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("start azure dns task");
                }
                ConnectionsManager$AzureLoadTask task = new ConnectionsManager$AzureLoadTask(this.val$currentAccount);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                ConnectionsManager.access$302(task);
            } else if (this.val$second == 1) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("start dns txt task");
                }
                ConnectionsManager$DnsTxtLoadTask task2 = new ConnectionsManager$DnsTxtLoadTask(this.val$currentAccount);
                task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                ConnectionsManager.access$302(task2);
            } else {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("start firebase task");
                }
                ConnectionsManager$FirebaseTask task3 = new ConnectionsManager$FirebaseTask(this.val$currentAccount);
                task3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                ConnectionsManager.access$302(task3);
            }
        } else if (BuildVars.LOGS_ENABLED) {
            FileLog.d("don't start task, current task = " + ConnectionsManager.access$300() + " next task = " + this.val$second + " time diff = " + Math.abs(ConnectionsManager.access$400() - System.currentTimeMillis()) + " network = " + ConnectionsManager.isNetworkOnline());
        }
    }
}
