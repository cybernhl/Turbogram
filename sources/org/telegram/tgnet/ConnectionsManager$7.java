package org.telegram.tgnet;

import org.telegram.messenger.NotificationCenter;

class ConnectionsManager$7 implements Runnable {
    final /* synthetic */ int val$currentAccount;
    final /* synthetic */ int val$state;

    ConnectionsManager$7(int i, int i2) {
        this.val$currentAccount = i;
        this.val$state = i2;
    }

    public void run() {
        ConnectionsManager.access$202(ConnectionsManager.getInstance(this.val$currentAccount), this.val$state);
        NotificationCenter.getInstance(this.val$currentAccount).postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
    }
}
