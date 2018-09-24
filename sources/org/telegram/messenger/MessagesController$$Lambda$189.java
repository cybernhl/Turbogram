package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$ChatFull;

final /* synthetic */ class MessagesController$$Lambda$189 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$ChatFull arg$2;
    private final String arg$3;

    MessagesController$$Lambda$189(MessagesController messagesController, TLRPC$ChatFull tLRPC$ChatFull, String str) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$ChatFull;
        this.arg$3 = str;
    }

    public void run() {
        this.arg$1.lambda$null$151$MessagesController(this.arg$2, this.arg$3);
    }
}
