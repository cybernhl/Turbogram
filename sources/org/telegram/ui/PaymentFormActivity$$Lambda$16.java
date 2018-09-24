package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$16 implements RequestDelegate {
    private final PaymentFormActivity arg$1;

    PaymentFormActivity$$Lambda$16(PaymentFormActivity paymentFormActivity) {
        this.arg$1 = paymentFormActivity;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$loadPasswordInfo$21$PaymentFormActivity(tLObject, tLRPC$TL_error);
    }
}
