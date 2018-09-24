package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_payments_sendPaymentForm;

final /* synthetic */ class PaymentFormActivity$$Lambda$20 implements RequestDelegate {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_payments_sendPaymentForm arg$2;

    PaymentFormActivity$$Lambda$20(PaymentFormActivity paymentFormActivity, TLRPC$TL_payments_sendPaymentForm tLRPC$TL_payments_sendPaymentForm) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_payments_sendPaymentForm;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$sendData$35$PaymentFormActivity(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
