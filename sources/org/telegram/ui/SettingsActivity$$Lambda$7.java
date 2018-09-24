package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SettingsActivity$$Lambda$7 implements RequestDelegate {
    private final SettingsActivity arg$1;

    SettingsActivity$$Lambda$7(SettingsActivity settingsActivity) {
        this.arg$1 = settingsActivity;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$1$SettingsActivity(tLObject, tLRPC$TL_error);
    }
}
