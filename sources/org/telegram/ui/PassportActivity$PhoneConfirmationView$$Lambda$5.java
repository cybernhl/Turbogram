package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_verifyPhone;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$$Lambda$5 implements Runnable {
    private final PhoneConfirmationView arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLRPC$TL_account_verifyPhone arg$3;

    PassportActivity$PhoneConfirmationView$$Lambda$5(PhoneConfirmationView phoneConfirmationView, TLRPC$TL_error tLRPC$TL_error, TLRPC$TL_account_verifyPhone tLRPC$TL_account_verifyPhone) {
        this.arg$1 = phoneConfirmationView;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLRPC$TL_account_verifyPhone;
    }

    public void run() {
        this.arg$1.lambda$null$5$PassportActivity$PhoneConfirmationView(this.arg$2, this.arg$3);
    }
}
