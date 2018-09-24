package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_verifyPhone;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$$Lambda$3 implements RequestDelegate {
    private final PhoneConfirmationView arg$1;
    private final TLRPC$TL_account_verifyPhone arg$2;

    PassportActivity$PhoneConfirmationView$$Lambda$3(PhoneConfirmationView phoneConfirmationView, TLRPC$TL_account_verifyPhone tLRPC$TL_account_verifyPhone) {
        this.arg$1 = phoneConfirmationView;
        this.arg$2 = tLRPC$TL_account_verifyPhone;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$onNextPressed$6$PassportActivity$PhoneConfirmationView(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
