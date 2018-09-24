package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$13 implements RequestDelegate {
    private final MessagesController arg$1;
    private final TLRPC$Chat arg$2;
    private final long arg$3;
    private final int arg$4;
    private final int arg$5;

    MessagesController$$Lambda$13(MessagesController messagesController, TLRPC$Chat tLRPC$Chat, long j, int i, int i2) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$Chat;
        this.arg$3 = j;
        this.arg$4 = i;
        this.arg$5 = i2;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$loadFullChat$15$MessagesController(this.arg$2, this.arg$3, this.arg$4, this.arg$5, tLObject, tLRPC$TL_error);
    }
}
