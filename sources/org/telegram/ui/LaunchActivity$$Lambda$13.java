package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getAuthorizationForm;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

final /* synthetic */ class LaunchActivity$$Lambda$13 implements RequestDelegate {
    private final LaunchActivity arg$1;
    private final int[] arg$2;
    private final int arg$3;
    private final AlertDialog arg$4;
    private final TLRPC$TL_account_getAuthorizationForm arg$5;
    private final String arg$6;
    private final String arg$7;
    private final String arg$8;

    LaunchActivity$$Lambda$13(LaunchActivity launchActivity, int[] iArr, int i, AlertDialog alertDialog, TLRPC$TL_account_getAuthorizationForm tLRPC$TL_account_getAuthorizationForm, String str, String str2, String str3) {
        this.arg$1 = launchActivity;
        this.arg$2 = iArr;
        this.arg$3 = i;
        this.arg$4 = alertDialog;
        this.arg$5 = tLRPC$TL_account_getAuthorizationForm;
        this.arg$6 = str;
        this.arg$7 = str2;
        this.arg$8 = str3;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$runLinkRequest$23$LaunchActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8, tLObject, tLRPC$TL_error);
    }
}
