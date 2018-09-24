package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.C19378;

final /* synthetic */ class PassportActivity$8$$Lambda$4 implements Runnable {
    private final C19378 arg$1;
    private final boolean arg$2;
    private final TLRPC$TL_error arg$3;

    PassportActivity$8$$Lambda$4(C19378 c19378, boolean z, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = c19378;
        this.arg$2 = z;
        this.arg$3 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$run$16$PassportActivity$8(this.arg$2, this.arg$3);
    }
}
