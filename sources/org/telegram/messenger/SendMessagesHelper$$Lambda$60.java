package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC$Document;

final /* synthetic */ class SendMessagesHelper$$Lambda$60 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final SendMessagesHelper$DelayedMessage arg$2;
    private final File arg$3;
    private final TLRPC$Document arg$4;
    private final MessageObject arg$5;

    SendMessagesHelper$$Lambda$60(SendMessagesHelper sendMessagesHelper, SendMessagesHelper$DelayedMessage sendMessagesHelper$DelayedMessage, File file, TLRPC$Document tLRPC$Document, MessageObject messageObject) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = sendMessagesHelper$DelayedMessage;
        this.arg$3 = file;
        this.arg$4 = tLRPC$Document;
        this.arg$5 = messageObject;
    }

    public void run() {
        this.arg$1.lambda$null$3$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
