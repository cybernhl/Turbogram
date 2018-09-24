package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$111 implements RequestDelegate {
    private final MessagesController arg$1;
    private final String arg$2;

    MessagesController$$Lambda$111(MessagesController messagesController, String str) {
        this.arg$1 = messagesController;
        this.arg$2 = str;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$registerForPush$171$MessagesController(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
