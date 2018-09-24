package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_signIn;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.LoginActivity.LoginActivitySmsView;

final /* synthetic */ class LoginActivity$LoginActivitySmsView$$Lambda$6 implements Runnable {
    private final LoginActivitySmsView arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;
    private final TLRPC$TL_auth_signIn arg$4;
    private final String arg$5;

    LoginActivity$LoginActivitySmsView$$Lambda$6(LoginActivitySmsView loginActivitySmsView, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject, TLRPC$TL_auth_signIn tLRPC$TL_auth_signIn, String str) {
        this.arg$1 = loginActivitySmsView;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
        this.arg$4 = tLRPC$TL_auth_signIn;
        this.arg$5 = str;
    }

    public void run() {
        this.arg$1.lambda$null$8$LoginActivity$LoginActivitySmsView(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
