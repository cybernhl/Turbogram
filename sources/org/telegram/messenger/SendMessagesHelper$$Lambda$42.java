package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Updates;

final /* synthetic */ class SendMessagesHelper$$Lambda$42 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$Updates arg$2;
    private final TLRPC$Message arg$3;

    SendMessagesHelper$$Lambda$42(SendMessagesHelper sendMessagesHelper, TLRPC$Updates tLRPC$Updates, TLRPC$Message tLRPC$Message) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$Updates;
        this.arg$3 = tLRPC$Message;
    }

    public void run() {
        this.arg$1.lambda$null$30$SendMessagesHelper(this.arg$2, this.arg$3);
    }
}
