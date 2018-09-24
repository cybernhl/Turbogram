package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_sendVerifyPhoneCode;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PassportActivity$$Lambda$49 implements Runnable {
    private final PassportActivity arg$1;
    private final TLRPC$TL_error arg$2;
    private final String arg$3;
    private final PassportActivityDelegate arg$4;
    private final TLObject arg$5;
    private final TLRPC$TL_account_sendVerifyPhoneCode arg$6;

    PassportActivity$$Lambda$49(PassportActivity passportActivity, TLRPC$TL_error tLRPC$TL_error, String str, PassportActivityDelegate passportActivityDelegate, TLObject tLObject, TLRPC$TL_account_sendVerifyPhoneCode tLRPC$TL_account_sendVerifyPhoneCode) {
        this.arg$1 = passportActivity;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = str;
        this.arg$4 = passportActivityDelegate;
        this.arg$5 = tLObject;
        this.arg$6 = tLRPC$TL_account_sendVerifyPhoneCode;
    }

    public void run() {
        this.arg$1.lambda$null$65$PassportActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
