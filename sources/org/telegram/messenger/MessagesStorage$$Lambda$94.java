package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$messages_Dialogs;

final /* synthetic */ class MessagesStorage$$Lambda$94 implements Runnable {
    private final MessagesStorage arg$1;
    private final TLRPC$messages_Dialogs arg$2;
    private final int arg$3;

    MessagesStorage$$Lambda$94(MessagesStorage messagesStorage, TLRPC$messages_Dialogs tLRPC$messages_Dialogs, int i) {
        this.arg$1 = messagesStorage;
        this.arg$2 = tLRPC$messages_Dialogs;
        this.arg$3 = i;
    }

    public void run() {
        this.arg$1.lambda$putDialogs$122$MessagesStorage(this.arg$2, this.arg$3);
    }
}
