package org.telegram.ui;

import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$PhotoSize;

final /* synthetic */ class ChannelEditInfoActivity$$Lambda$12 implements Runnable {
    private final ChannelEditInfoActivity arg$1;
    private final TLRPC$InputFile arg$2;
    private final TLRPC$PhotoSize arg$3;

    ChannelEditInfoActivity$$Lambda$12(ChannelEditInfoActivity channelEditInfoActivity, TLRPC$InputFile tLRPC$InputFile, TLRPC$PhotoSize tLRPC$PhotoSize) {
        this.arg$1 = channelEditInfoActivity;
        this.arg$2 = tLRPC$InputFile;
        this.arg$3 = tLRPC$PhotoSize;
    }

    public void run() {
        this.arg$1.lambda$didUploadedPhoto$15$ChannelEditInfoActivity(this.arg$2, this.arg$3);
    }
}
