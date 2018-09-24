package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_channels_channelParticipant;

final /* synthetic */ class MessagesController$$Lambda$158 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_channels_channelParticipant arg$2;

    MessagesController$$Lambda$158(MessagesController messagesController, TLRPC$TL_channels_channelParticipant tLRPC$TL_channels_channelParticipant) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_channels_channelParticipant;
    }

    public void run() {
        this.arg$1.lambda$null$207$MessagesController(this.arg$2);
    }
}
