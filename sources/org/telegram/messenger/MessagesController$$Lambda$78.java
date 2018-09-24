package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class MessagesController$$Lambda$78 implements RequestDelegate {
    static final RequestDelegate $instance = new MessagesController$$Lambda$78();

    private MessagesController$$Lambda$78() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        MessagesController.lambda$markMessageContentAsRead$119$MessagesController(tLObject, tLRPC$TL_error);
    }
}
