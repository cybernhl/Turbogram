package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.LoginActivity.LoginActivitySmsView.C18663;

final /* synthetic */ class LoginActivity$LoginActivitySmsView$3$$Lambda$2 implements Runnable {
    private final C18663 arg$1;
    private final TLRPC$TL_error arg$2;

    LoginActivity$LoginActivitySmsView$3$$Lambda$2(C18663 c18663, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = c18663;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$0$LoginActivity$LoginActivitySmsView$3(this.arg$2);
    }
}
