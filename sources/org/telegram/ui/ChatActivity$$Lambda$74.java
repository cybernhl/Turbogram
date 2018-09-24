package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_getWebPagePreview;

final /* synthetic */ class ChatActivity$$Lambda$74 implements RequestDelegate {
    private final ChatActivity arg$1;
    private final TLRPC$TL_messages_getWebPagePreview arg$2;

    ChatActivity$$Lambda$74(ChatActivity chatActivity, TLRPC$TL_messages_getWebPagePreview tLRPC$TL_messages_getWebPagePreview) {
        this.arg$1 = chatActivity;
        this.arg$2 = tLRPC$TL_messages_getWebPagePreview;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$45$ChatActivity(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
