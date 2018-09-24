package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.C19378;

final /* synthetic */ class PassportActivity$8$$Lambda$11 implements Runnable {
    private final C19378 arg$1;
    private final TLRPC$TL_error arg$2;

    PassportActivity$8$$Lambda$11(C19378 c19378, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = c19378;
        this.arg$2 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$6$PassportActivity$8(this.arg$2);
    }
}
