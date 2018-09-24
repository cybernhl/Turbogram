package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_dialog;

final /* synthetic */ class MessagesController$$Lambda$202 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_dialog arg$2;

    MessagesController$$Lambda$202(MessagesController messagesController, TLRPC$TL_dialog tLRPC$TL_dialog) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_dialog;
    }

    public void run() {
        this.arg$1.lambda$null$113$MessagesController(this.arg$2);
    }
}
