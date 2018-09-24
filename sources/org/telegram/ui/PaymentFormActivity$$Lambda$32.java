package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$32 implements RequestDelegate {
    private final PaymentFormActivity arg$1;
    private final boolean arg$2;
    private final String arg$3;

    PaymentFormActivity$$Lambda$32(PaymentFormActivity paymentFormActivity, boolean z, String str) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = z;
        this.arg$3 = str;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$27$PaymentFormActivity(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
