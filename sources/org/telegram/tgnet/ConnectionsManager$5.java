package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;

class ConnectionsManager$5 implements Runnable {
    final /* synthetic */ int val$currentAccount;

    ConnectionsManager$5(int i) {
        this.val$currentAccount = i;
    }

    public void run() {
        MessagesController.getInstance(this.val$currentAccount).updateTimerProc();
    }
}
