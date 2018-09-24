package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_secureValue;
import org.telegram.ui.PassportActivity.19.C19271;

final /* synthetic */ class PassportActivity$19$1$$Lambda$3 implements RequestDelegate {
    private final C19271 arg$1;
    private final TLRPC$TL_secureValue arg$2;

    PassportActivity$19$1$$Lambda$3(C19271 c19271, TLRPC$TL_secureValue tLRPC$TL_secureValue) {
        this.arg$1 = c19271;
        this.arg$2 = tLRPC$TL_secureValue;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$run$4$PassportActivity$19$1(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
