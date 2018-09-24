package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getPassword;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PaymentFormActivity$$Lambda$22 implements Runnable {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;
    private final String arg$4;
    private final TLRPC$TL_account_getPassword arg$5;

    PaymentFormActivity$$Lambda$22(PaymentFormActivity paymentFormActivity, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject, String str, TLRPC$TL_account_getPassword tLRPC$TL_account_getPassword) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
        this.arg$4 = str;
        this.arg$5 = tLRPC$TL_account_getPassword;
    }

    public void run() {
        this.arg$1.lambda$null$39$PaymentFormActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
