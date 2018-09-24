package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_help_proxyDataPromo;

final /* synthetic */ class MessagesController$$Lambda$219 implements Runnable {
    private final MessagesController arg$1;
    private final long arg$2;
    private final TLRPC$TL_help_proxyDataPromo arg$3;

    MessagesController$$Lambda$219(MessagesController messagesController, long j, TLRPC$TL_help_proxyDataPromo tLRPC$TL_help_proxyDataPromo) {
        this.arg$1 = messagesController;
        this.arg$2 = j;
        this.arg$3 = tLRPC$TL_help_proxyDataPromo;
    }

    public void run() {
        this.arg$1.lambda$null$77$MessagesController(this.arg$2, this.arg$3);
    }
}
