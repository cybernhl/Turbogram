package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;

class ConnectionsManager$4 implements Runnable {
    final /* synthetic */ int val$currentAccount;
    final /* synthetic */ TLObject val$message;

    ConnectionsManager$4(int i, TLObject tLObject) {
        this.val$currentAccount = i;
        this.val$message = tLObject;
    }

    public void run() {
        MessagesController.getInstance(this.val$currentAccount).processUpdates((TLRPC$Updates) this.val$message, false);
    }
}
