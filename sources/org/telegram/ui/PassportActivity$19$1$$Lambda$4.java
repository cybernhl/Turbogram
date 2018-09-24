package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_secureRequiredType;
import org.telegram.ui.PassportActivity.19.C19271;

final /* synthetic */ class PassportActivity$19$1$$Lambda$4 implements Runnable {
    private final C19271 arg$1;
    private final TLObject arg$2;
    private final String arg$3;
    private final TLRPC$TL_secureRequiredType arg$4;
    private final PassportActivityDelegate arg$5;
    private final TLRPC$TL_error arg$6;
    private final ErrorRunnable arg$7;

    PassportActivity$19$1$$Lambda$4(C19271 c19271, TLObject tLObject, String str, TLRPC$TL_secureRequiredType tLRPC$TL_secureRequiredType, PassportActivityDelegate passportActivityDelegate, TLRPC$TL_error tLRPC$TL_error, ErrorRunnable errorRunnable) {
        this.arg$1 = c19271;
        this.arg$2 = tLObject;
        this.arg$3 = str;
        this.arg$4 = tLRPC$TL_secureRequiredType;
        this.arg$5 = passportActivityDelegate;
        this.arg$6 = tLRPC$TL_error;
        this.arg$7 = errorRunnable;
    }

    public void run() {
        this.arg$1.lambda$null$1$PassportActivity$19$1(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
