package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_resendCode;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$$Lambda$2 implements RequestDelegate {
    private final PhoneConfirmationView arg$1;
    private final Bundle arg$2;
    private final TLRPC$TL_auth_resendCode arg$3;

    PassportActivity$PhoneConfirmationView$$Lambda$2(PhoneConfirmationView phoneConfirmationView, Bundle bundle, TLRPC$TL_auth_resendCode tLRPC$TL_auth_resendCode) {
        this.arg$1 = phoneConfirmationView;
        this.arg$2 = bundle;
        this.arg$3 = tLRPC$TL_auth_resendCode;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$resendCode$4$PassportActivity$PhoneConfirmationView(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
