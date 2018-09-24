package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView.C19423.C19411;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$3$1$$Lambda$1 implements Runnable {
    private final C19411 arg$1;
    private final TLRPC$TL_error arg$2;

    PassportActivity$PhoneConfirmationView$3$1$$Lambda$1(C19411 c19411, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = c19411;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$0$PassportActivity$PhoneConfirmationView$3$1(this.arg$2);
    }
}
