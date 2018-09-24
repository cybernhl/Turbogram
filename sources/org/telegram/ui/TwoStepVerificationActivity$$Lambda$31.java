package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC$TL_auth_passwordRecovery;

final /* synthetic */ class TwoStepVerificationActivity$$Lambda$31 implements OnClickListener {
    private final TwoStepVerificationActivity arg$1;
    private final TLRPC$TL_auth_passwordRecovery arg$2;

    TwoStepVerificationActivity$$Lambda$31(TwoStepVerificationActivity twoStepVerificationActivity, TLRPC$TL_auth_passwordRecovery tLRPC$TL_auth_passwordRecovery) {
        this.arg$1 = twoStepVerificationActivity;
        this.arg$2 = tLRPC$TL_auth_passwordRecovery;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$null$1$TwoStepVerificationActivity(this.arg$2, dialogInterface, i);
    }
}
