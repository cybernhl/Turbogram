package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.ui.ActionBar.BaseFragment;

final /* synthetic */ class SendMessagesHelper$$Lambda$55 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$TL_error arg$2;
    private final BaseFragment arg$3;
    private final TLRPC$TL_messages_editMessage arg$4;

    SendMessagesHelper$$Lambda$55(SendMessagesHelper sendMessagesHelper, TLRPC$TL_error tLRPC$TL_error, BaseFragment baseFragment, TLRPC$TL_messages_editMessage tLRPC$TL_messages_editMessage) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = baseFragment;
        this.arg$4 = tLRPC$TL_messages_editMessage;
    }

    public void run() {
        this.arg$1.lambda$null$10$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4);
    }
}
