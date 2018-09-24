package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$31 implements RequestDelegate {
    static final RequestDelegate $instance = new PaymentFormActivity$$Lambda$31();

    private PaymentFormActivity$$Lambda$31() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        PaymentFormActivity.lambda$null$29$PaymentFormActivity(tLObject, tLRPC$TL_error);
    }
}
