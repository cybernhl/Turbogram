package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_password;

final /* synthetic */ class PaymentFormActivity$$Lambda$23 implements Runnable {
    private final PaymentFormActivity arg$1;
    private final TLRPC$TL_account_password arg$2;
    private final byte[] arg$3;

    PaymentFormActivity$$Lambda$23(PaymentFormActivity paymentFormActivity, TLRPC$TL_account_password tLRPC$TL_account_password, byte[] bArr) {
        this.arg$1 = paymentFormActivity;
        this.arg$2 = tLRPC$TL_account_password;
        this.arg$3 = bArr;
    }

    public void run() {
        this.arg$1.lambda$null$38$PaymentFormActivity(this.arg$2, this.arg$3);
    }
}
