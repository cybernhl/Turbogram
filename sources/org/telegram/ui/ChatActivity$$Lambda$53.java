package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ChatActivity$$Lambda$53 implements RequestDelegate {
    private final ChatActivity arg$1;

    ChatActivity$$Lambda$53(ChatActivity chatActivity) {
        this.arg$1 = chatActivity;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$startEditingMessageObject$69$ChatActivity(tLObject, tLRPC$TL_error);
    }
}
