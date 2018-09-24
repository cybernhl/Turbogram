package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputMedia;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SendMessagesHelper$$Lambda$8 implements RequestDelegate {
    private final SendMessagesHelper arg$1;
    private final TLRPC$InputMedia arg$2;
    private final SendMessagesHelper$DelayedMessage arg$3;

    SendMessagesHelper$$Lambda$8(SendMessagesHelper sendMessagesHelper, TLRPC$InputMedia tLRPC$InputMedia, SendMessagesHelper$DelayedMessage sendMessagesHelper$DelayedMessage) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$InputMedia;
        this.arg$3 = sendMessagesHelper$DelayedMessage;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$uploadMultiMedia$19$SendMessagesHelper(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
