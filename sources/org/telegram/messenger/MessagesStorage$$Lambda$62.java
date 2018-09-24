package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

final /* synthetic */ class MessagesStorage$$Lambda$62 implements Runnable {
    private final MessagesStorage arg$1;
    private final TLRPC$EncryptedChat arg$2;
    private final boolean arg$3;

    MessagesStorage$$Lambda$62(MessagesStorage messagesStorage, TLRPC$EncryptedChat tLRPC$EncryptedChat, boolean z) {
        this.arg$1 = messagesStorage;
        this.arg$2 = tLRPC$EncryptedChat;
        this.arg$3 = z;
    }

    public void run() {
        this.arg$1.lambda$updateEncryptedChatSeq$86$MessagesStorage(this.arg$2, this.arg$3);
    }
}
