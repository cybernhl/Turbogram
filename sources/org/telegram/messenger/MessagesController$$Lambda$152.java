package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateChannel;

final /* synthetic */ class MessagesController$$Lambda$152 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_updateChannel arg$2;

    MessagesController$$Lambda$152(MessagesController messagesController, TLRPC$TL_updateChannel tLRPC$TL_updateChannel) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_updateChannel;
    }

    public void run() {
        this.arg$1.lambda$null$227$MessagesController(this.arg$2);
    }
}
