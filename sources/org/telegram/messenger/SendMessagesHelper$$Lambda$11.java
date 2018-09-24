package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SendMessagesHelper$$Lambda$11 implements RequestDelegate {
    private final SendMessagesHelper arg$1;
    private final TLObject arg$2;
    private final TLRPC$Message arg$3;
    private final MessageObject arg$4;
    private final String arg$5;

    SendMessagesHelper$$Lambda$11(SendMessagesHelper sendMessagesHelper, TLObject tLObject, TLRPC$Message tLRPC$Message, MessageObject messageObject, String str) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLObject;
        this.arg$3 = tLRPC$Message;
        this.arg$4 = messageObject;
        this.arg$5 = str;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$performSendMessageRequest$39$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, tLObject, tLRPC$TL_error);
    }
}
