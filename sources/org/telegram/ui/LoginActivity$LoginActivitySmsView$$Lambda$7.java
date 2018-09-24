package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_signIn;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.LoginActivity.LoginActivitySmsView;

final /* synthetic */ class LoginActivity$LoginActivitySmsView$$Lambda$7 implements RequestDelegate {
    private final LoginActivitySmsView arg$1;
    private final TLRPC$TL_auth_signIn arg$2;

    LoginActivity$LoginActivitySmsView$$Lambda$7(LoginActivitySmsView loginActivitySmsView, TLRPC$TL_auth_signIn tLRPC$TL_auth_signIn) {
        this.arg$1 = loginActivitySmsView;
        this.arg$2 = tLRPC$TL_auth_signIn;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$7$LoginActivity$LoginActivitySmsView(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
