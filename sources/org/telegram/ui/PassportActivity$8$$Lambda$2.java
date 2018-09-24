package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.C19378;

final /* synthetic */ class PassportActivity$8$$Lambda$2 implements RequestDelegate {
    private final C19378 arg$1;
    private final boolean arg$2;

    PassportActivity$8$$Lambda$2(C19378 c19378, boolean z) {
        this.arg$1 = c19378;
        this.arg$2 = z;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$run$10$PassportActivity$8(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
