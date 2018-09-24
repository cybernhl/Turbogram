package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateNewChannelMessage;

final /* synthetic */ class SendMessagesHelper$$Lambda$38 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$TL_updateNewChannelMessage arg$2;

    SendMessagesHelper$$Lambda$38(SendMessagesHelper sendMessagesHelper, TLRPC$TL_updateNewChannelMessage tLRPC$TL_updateNewChannelMessage) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$TL_updateNewChannelMessage;
    }

    public void run() {
        this.arg$1.lambda$null$34$SendMessagesHelper(this.arg$2);
    }
}
