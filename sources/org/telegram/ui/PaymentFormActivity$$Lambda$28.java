package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_payments_sendPaymentForm;

final /* synthetic */ class PaymentFormActivity$$Lambda$28 implements Runnable {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLRPC$TL_payments_sendPaymentForm arg$3;

    PaymentFormActivity$$Lambda$28(PaymentFormActivity paymentFormActivity, TLRPC$TL_error tLRPC$TL_error, TLRPC$TL_payments_sendPaymentForm tLRPC$TL_payments_sendPaymentForm) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLRPC$TL_payments_sendPaymentForm;
    }

    public void run() {
        this.arg$1.lambda$null$34$PaymentFormActivity(this.arg$2, this.arg$3);
    }
}
