package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;

class ConnectionsManager$6 implements Runnable {
    final /* synthetic */ int val$currentAccount;

    ConnectionsManager$6(int i) {
        this.val$currentAccount = i;
    }

    public void run() {
        MessagesController.getInstance(this.val$currentAccount).getDifference();
    }
}
