package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

final /* synthetic */ class SendMessagesHelper$$Lambda$59 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$Message arg$2;
    private final long arg$3;
    private final int arg$4;
    private final TLRPC$Message arg$5;

    SendMessagesHelper$$Lambda$59(SendMessagesHelper sendMessagesHelper, TLRPC$Message tLRPC$Message, long j, int i, TLRPC$Message tLRPC$Message2) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$Message;
        this.arg$3 = j;
        this.arg$4 = i;
        this.arg$5 = tLRPC$Message2;
    }

    public void run() {
        this.arg$1.lambda$null$5$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
