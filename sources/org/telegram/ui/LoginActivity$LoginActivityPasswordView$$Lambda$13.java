package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.LoginActivity.LoginActivityPasswordView;

final /* synthetic */ class LoginActivity$LoginActivityPasswordView$$Lambda$13 implements Runnable {
    private final LoginActivityPasswordView arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;

    LoginActivity$LoginActivityPasswordView$$Lambda$13(LoginActivityPasswordView loginActivityPasswordView, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject) {
        this.arg$1 = loginActivityPasswordView;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
    }

    public void run() {
        this.arg$1.lambda$null$2$LoginActivity$LoginActivityPasswordView(this.arg$2, this.arg$3);
    }
}
