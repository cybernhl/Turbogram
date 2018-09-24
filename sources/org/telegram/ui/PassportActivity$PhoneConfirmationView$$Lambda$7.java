package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_resendCode;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$$Lambda$7 implements Runnable {
    private final PhoneConfirmationView arg$1;
    private final TLRPC$TL_error arg$2;
    private final Bundle arg$3;
    private final TLObject arg$4;
    private final TLRPC$TL_auth_resendCode arg$5;

    PassportActivity$PhoneConfirmationView$$Lambda$7(PhoneConfirmationView phoneConfirmationView, TLRPC$TL_error tLRPC$TL_error, Bundle bundle, TLObject tLObject, TLRPC$TL_auth_resendCode tLRPC$TL_auth_resendCode) {
        this.arg$1 = phoneConfirmationView;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = bundle;
        this.arg$4 = tLObject;
        this.arg$5 = tLRPC$TL_auth_resendCode;
    }

    public void run() {
        this.arg$1.lambda$null$3$PassportActivity$PhoneConfirmationView(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
