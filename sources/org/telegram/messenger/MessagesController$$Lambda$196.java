package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Updates;

final /* synthetic */ class MessagesController$$Lambda$196 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$Updates arg$2;

    MessagesController$$Lambda$196(MessagesController messagesController, TLRPC$Updates tLRPC$Updates) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$Updates;
    }

    public void run() {
        this.arg$1.lambda$null$138$MessagesController(this.arg$2);
    }
}
