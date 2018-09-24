package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

final /* synthetic */ class SendMessagesHelper$$Lambda$43 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$Message arg$2;

    SendMessagesHelper$$Lambda$43(SendMessagesHelper sendMessagesHelper, TLRPC$Message tLRPC$Message) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$Message;
    }

    public void run() {
        this.arg$1.lambda$null$29$SendMessagesHelper(this.arg$2);
    }
}
