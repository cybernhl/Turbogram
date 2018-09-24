package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.LoginActivity.LoginActivityResetWaitView;

final /* synthetic */ class LoginActivity$LoginActivityResetWaitView$$Lambda$3 implements Runnable {
    private final LoginActivityResetWaitView arg$1;
    private final TLRPC$TL_error arg$2;

    LoginActivity$LoginActivityResetWaitView$$Lambda$3(LoginActivityResetWaitView loginActivityResetWaitView, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = loginActivityResetWaitView;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$0$LoginActivity$LoginActivityResetWaitView(this.arg$2);
    }
}
