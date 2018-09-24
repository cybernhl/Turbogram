package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateServiceNotification;

final /* synthetic */ class MessagesController$$Lambda$136 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_updateServiceNotification arg$2;

    MessagesController$$Lambda$136(MessagesController messagesController, TLRPC$TL_updateServiceNotification tLRPC$TL_updateServiceNotification) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_updateServiceNotification;
    }

    public void run() {
        this.arg$1.lambda$processUpdateArray$223$MessagesController(this.arg$2);
    }
}
