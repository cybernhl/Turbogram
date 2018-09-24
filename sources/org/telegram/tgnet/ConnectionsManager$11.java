package org.telegram.tgnet;

import org.telegram.messenger.MessagesController;

class ConnectionsManager$11 implements Runnable {
    final /* synthetic */ int val$currentAccount;
    final /* synthetic */ TLRPC$TL_config val$message;

    ConnectionsManager$11(int i, TLRPC$TL_config tLRPC$TL_config) {
        this.val$currentAccount = i;
        this.val$message = tLRPC$TL_config;
    }

    public void run() {
        MessagesController.getInstance(this.val$currentAccount).updateConfig(this.val$message);
    }
}
