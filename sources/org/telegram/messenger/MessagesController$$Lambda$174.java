package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$updates_ChannelDifference;

final /* synthetic */ class MessagesController$$Lambda$174 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$updates_ChannelDifference arg$2;

    MessagesController$$Lambda$174(MessagesController messagesController, TLRPC$updates_ChannelDifference tLRPC$updates_ChannelDifference) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$updates_ChannelDifference;
    }

    public void run() {
        this.arg$1.lambda$null$179$MessagesController(this.arg$2);
    }
}
