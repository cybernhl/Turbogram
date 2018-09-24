package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ChannelEditInfoActivity$$Lambda$27 implements Runnable {
    private final ChannelEditInfoActivity arg$1;
    private final TLRPC$TL_error arg$2;

    ChannelEditInfoActivity$$Lambda$27(ChannelEditInfoActivity channelEditInfoActivity, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = channelEditInfoActivity;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$1$ChannelEditInfoActivity(this.arg$2);
    }
}
