package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_passwordSettings;
import org.telegram.ui.PassportActivity.C19378;

final /* synthetic */ class PassportActivity$8$$Lambda$6 implements Runnable {
    private final C19378 arg$1;
    private final TLRPC$TL_account_passwordSettings arg$2;
    private final boolean arg$3;
    private final byte[] arg$4;

    PassportActivity$8$$Lambda$6(C19378 c19378, TLRPC$TL_account_passwordSettings tLRPC$TL_account_passwordSettings, boolean z, byte[] bArr) {
        this.arg$1 = c19378;
        this.arg$2 = tLRPC$TL_account_passwordSettings;
        this.arg$3 = z;
        this.arg$4 = bArr;
    }

    public void run() {
        this.arg$1.lambda$null$14$PassportActivity$8(this.arg$2, this.arg$3, this.arg$4);
    }
}
