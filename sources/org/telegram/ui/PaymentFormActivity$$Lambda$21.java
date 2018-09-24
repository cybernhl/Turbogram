package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getPassword;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$21 implements RequestDelegate {
    private final PaymentFormActivity arg$1;
    private final String arg$2;
    private final TLRPC$TL_account_getPassword arg$3;

    PaymentFormActivity$$Lambda$21(PaymentFormActivity paymentFormActivity, String str, TLRPC$TL_account_getPassword tLRPC$TL_account_getPassword) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = str;
        this.arg$3 = tLRPC$TL_account_getPassword;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$checkPassword$40$PaymentFormActivity(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
