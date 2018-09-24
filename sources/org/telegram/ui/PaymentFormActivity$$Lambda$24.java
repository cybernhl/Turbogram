package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getTmpPassword;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$24 implements RequestDelegate {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_account_getTmpPassword arg$2;

    PaymentFormActivity$$Lambda$24(PaymentFormActivity paymentFormActivity, TLRPC$TL_account_getTmpPassword tLRPC$TL_account_getTmpPassword) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_account_getTmpPassword;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$37$PaymentFormActivity(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
