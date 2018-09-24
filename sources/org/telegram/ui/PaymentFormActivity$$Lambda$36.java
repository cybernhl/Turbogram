package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$36 implements Runnable {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;
    private final boolean arg$4;

    PaymentFormActivity$$Lambda$36(PaymentFormActivity paymentFormActivity, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject, boolean z) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
        this.arg$4 = z;
    }

    public void run() {
        this.arg$1.lambda$null$23$PaymentFormActivity(this.arg$2, this.arg$3, this.arg$4);
    }
}
