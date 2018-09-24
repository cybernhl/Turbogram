package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PassportActivity$$Lambda$71 implements Runnable {
    private final PassportActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;

    PassportActivity$$Lambda$71(PassportActivity passportActivity, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject) {
        this.arg$1 = passportActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
    }

    public void run() {
        this.arg$1.lambda$null$9$PassportActivity(this.arg$2, this.arg$3);
    }
}
