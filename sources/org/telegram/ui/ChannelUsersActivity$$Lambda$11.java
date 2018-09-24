package org.telegram.ui;

import org.telegram.tgnet.TLRPC$Updates;

final /* synthetic */ class ChannelUsersActivity$$Lambda$11 implements Runnable {
    private final ChannelUsersActivity arg$1;
    private final TLRPC$Updates arg$2;

    ChannelUsersActivity$$Lambda$11(ChannelUsersActivity channelUsersActivity, TLRPC$Updates tLRPC$Updates) {
        this.arg$1 = channelUsersActivity;
        this.arg$2 = tLRPC$Updates;
    }

    public void run() {
        this.arg$1.lambda$null$7$ChannelUsersActivity(this.arg$2);
    }
}
