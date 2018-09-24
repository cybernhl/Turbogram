package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$241 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_error arg$2;
    private final int arg$3;

    MessagesController$$Lambda$241(MessagesController messagesController, TLRPC$TL_error tLRPC$TL_error, int i) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = i;
    }

    public void run() {
        this.arg$1.lambda$null$14$MessagesController(this.arg$2, this.arg$3);
    }
}
