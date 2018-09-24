package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_setAccountTTL;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

final /* synthetic */ class PrivacySettingsActivity$$Lambda$17 implements RequestDelegate {
    private final PrivacySettingsActivity arg$1;
    private final AlertDialog arg$2;
    private final TLRPC$TL_account_setAccountTTL arg$3;

    PrivacySettingsActivity$$Lambda$17(PrivacySettingsActivity privacySettingsActivity, AlertDialog alertDialog, TLRPC$TL_account_setAccountTTL tLRPC$TL_account_setAccountTTL) {
        this.arg$1 = privacySettingsActivity;
        this.arg$2 = alertDialog;
        this.arg$3 = tLRPC$TL_account_setAccountTTL;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$5$PrivacySettingsActivity(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
