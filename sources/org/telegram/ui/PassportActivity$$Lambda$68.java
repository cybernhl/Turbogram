package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PassportActivity$$Lambda$68 implements Runnable {
    private final PassportActivity arg$1;
    private final TLRPC$TL_error arg$2;

    PassportActivity$$Lambda$68(PassportActivity passportActivity, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = passportActivity;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$14$PassportActivity(this.arg$2);
    }
}
