package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$updates_Difference;

final /* synthetic */ class MessagesController$$Lambda$167 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$updates_Difference arg$2;

    MessagesController$$Lambda$167(MessagesController messagesController, TLRPC$updates_Difference tLRPC$updates_Difference) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$updates_Difference;
    }

    public void run() {
        this.arg$1.lambda$null$189$MessagesController(this.arg$2);
    }
}
