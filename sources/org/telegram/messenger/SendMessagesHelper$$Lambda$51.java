package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputMedia;

final /* synthetic */ class SendMessagesHelper$$Lambda$51 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLObject arg$2;
    private final TLRPC$InputMedia arg$3;
    private final SendMessagesHelper$DelayedMessage arg$4;

    SendMessagesHelper$$Lambda$51(SendMessagesHelper sendMessagesHelper, TLObject tLObject, TLRPC$InputMedia tLRPC$InputMedia, SendMessagesHelper$DelayedMessage sendMessagesHelper$DelayedMessage) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLObject;
        this.arg$3 = tLRPC$InputMedia;
        this.arg$4 = sendMessagesHelper$DelayedMessage;
    }

    public void run() {
        this.arg$1.lambda$null$18$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4);
    }
}
