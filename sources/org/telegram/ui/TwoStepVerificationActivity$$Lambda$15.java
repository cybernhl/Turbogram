package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class TwoStepVerificationActivity$$Lambda$15 implements Runnable {
    private final TwoStepVerificationActivity arg$1;
    private final TLRPC$TL_error arg$2;

    TwoStepVerificationActivity$$Lambda$15(TwoStepVerificationActivity twoStepVerificationActivity, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = twoStepVerificationActivity;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$26$TwoStepVerificationActivity(this.arg$2);
    }
}
