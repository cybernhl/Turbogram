package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$87 implements RequestDelegate {
    static final RequestDelegate $instance = new MessagesController$$Lambda$87();

    private MessagesController$$Lambda$87() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        MessagesController.lambda$markMentionsAsRead$128$MessagesController(tLObject, tLRPC$TL_error);
    }
}
