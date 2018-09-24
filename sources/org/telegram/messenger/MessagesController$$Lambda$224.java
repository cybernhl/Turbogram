package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_help_termsOfServiceUpdate;

final /* synthetic */ class MessagesController$$Lambda$224 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_help_termsOfServiceUpdate arg$2;

    MessagesController$$Lambda$224(MessagesController messagesController, TLRPC$TL_help_termsOfServiceUpdate tLRPC$TL_help_termsOfServiceUpdate) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_help_termsOfServiceUpdate;
    }

    public void run() {
        this.arg$1.lambda$null$71$MessagesController(this.arg$2);
    }
}
