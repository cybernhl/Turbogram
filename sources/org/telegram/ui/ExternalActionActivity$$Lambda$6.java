package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_authorizationForm;
import org.telegram.tgnet.TLRPC$TL_account_getAuthorizationForm;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

final /* synthetic */ class ExternalActionActivity$$Lambda$6 implements RequestDelegate {
    private final ExternalActionActivity arg$1;
    private final AlertDialog arg$2;
    private final int arg$3;
    private final TLRPC$TL_account_authorizationForm arg$4;
    private final TLRPC$TL_account_getAuthorizationForm arg$5;
    private final String arg$6;
    private final String arg$7;

    ExternalActionActivity$$Lambda$6(ExternalActionActivity externalActionActivity, AlertDialog alertDialog, int i, TLRPC$TL_account_authorizationForm tLRPC$TL_account_authorizationForm, TLRPC$TL_account_getAuthorizationForm tLRPC$TL_account_getAuthorizationForm, String str, String str2) {
        this.arg$1 = externalActionActivity;
        this.arg$2 = alertDialog;
        this.arg$3 = i;
        this.arg$4 = tLRPC$TL_account_authorizationForm;
        this.arg$5 = tLRPC$TL_account_getAuthorizationForm;
        this.arg$6 = str;
        this.arg$7 = str2;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$6$ExternalActionActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, tLObject, tLRPC$TL_error);
    }
}
