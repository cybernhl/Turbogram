package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$157 implements RequestDelegate {
    private final MessagesController arg$1;
    private final TLRPC$Chat arg$2;
    private final int arg$3;

    MessagesController$$Lambda$157(MessagesController messagesController, TLRPC$Chat tLRPC$Chat, int i) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$Chat;
        this.arg$3 = i;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$211$MessagesController(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
