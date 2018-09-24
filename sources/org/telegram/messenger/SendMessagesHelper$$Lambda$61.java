package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC$TL_photo;

final /* synthetic */ class SendMessagesHelper$$Lambda$61 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$TL_photo arg$2;
    private final MessageObject arg$3;
    private final File arg$4;
    private final SendMessagesHelper$DelayedMessage arg$5;
    private final String arg$6;

    SendMessagesHelper$$Lambda$61(SendMessagesHelper sendMessagesHelper, TLRPC$TL_photo tLRPC$TL_photo, MessageObject messageObject, File file, SendMessagesHelper$DelayedMessage sendMessagesHelper$DelayedMessage, String str) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$TL_photo;
        this.arg$3 = messageObject;
        this.arg$4 = file;
        this.arg$5 = sendMessagesHelper$DelayedMessage;
        this.arg$6 = str;
    }

    public void run() {
        this.arg$1.lambda$null$1$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
