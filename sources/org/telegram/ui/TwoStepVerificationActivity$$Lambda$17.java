package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class TwoStepVerificationActivity$$Lambda$17 implements Runnable {
    private final TwoStepVerificationActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;

    TwoStepVerificationActivity$$Lambda$17(TwoStepVerificationActivity twoStepVerificationActivity, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject) {
        this.arg$1 = twoStepVerificationActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
    }

    public void run() {
        this.arg$1.lambda$null$24$TwoStepVerificationActivity(this.arg$2, this.arg$3);
    }
}
