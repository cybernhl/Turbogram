package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$updates_ChannelDifference;

final /* synthetic */ class MessagesController$$Lambda$180 implements Runnable {
    private final MessagesController arg$1;
    private final ArrayList arg$2;
    private final TLRPC$updates_ChannelDifference arg$3;

    MessagesController$$Lambda$180(MessagesController messagesController, ArrayList arrayList, TLRPC$updates_ChannelDifference tLRPC$updates_ChannelDifference) {
        this.arg$1 = messagesController;
        this.arg$2 = arrayList;
        this.arg$3 = tLRPC$updates_ChannelDifference;
    }

    public void run() {
        this.arg$1.lambda$null$183$MessagesController(this.arg$2, this.arg$3);
    }
}
