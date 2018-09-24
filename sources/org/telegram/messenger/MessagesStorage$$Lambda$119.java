package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Chat;

final /* synthetic */ class MessagesStorage$$Lambda$119 implements Runnable {
    private final MessagesStorage arg$1;
    private final TLRPC$Chat arg$2;
    private final long arg$3;

    MessagesStorage$$Lambda$119(MessagesStorage messagesStorage, TLRPC$Chat tLRPC$Chat, long j) {
        this.arg$1 = messagesStorage;
        this.arg$2 = tLRPC$Chat;
        this.arg$3 = j;
    }

    public void run() {
        this.arg$1.lambda$null$8$MessagesStorage(this.arg$2, this.arg$3);
    }
}
