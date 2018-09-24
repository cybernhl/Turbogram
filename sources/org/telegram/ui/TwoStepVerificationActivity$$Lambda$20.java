package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class TwoStepVerificationActivity$$Lambda$20 implements Runnable {
    private final TwoStepVerificationActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final boolean arg$3;
    private final TLObject arg$4;
    private final byte[] arg$5;
    private final TLRPC$TL_account_updatePasswordSettings arg$6;

    TwoStepVerificationActivity$$Lambda$20(TwoStepVerificationActivity twoStepVerificationActivity, TLRPC$TL_error tLRPC$TL_error, boolean z, TLObject tLObject, byte[] bArr, TLRPC$TL_account_updatePasswordSettings tLRPC$TL_account_updatePasswordSettings) {
        this.arg$1 = twoStepVerificationActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = z;
        this.arg$4 = tLObject;
        this.arg$5 = bArr;
        this.arg$6 = tLRPC$TL_account_updatePasswordSettings;
    }

    public void run() {
        this.arg$1.lambda$null$18$TwoStepVerificationActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
