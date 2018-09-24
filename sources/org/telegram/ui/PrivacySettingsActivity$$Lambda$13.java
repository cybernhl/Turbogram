package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class PrivacySettingsActivity$$Lambda$13 implements RequestDelegate {
    static final RequestDelegate $instance = new PrivacySettingsActivity$$Lambda$13();

    private PrivacySettingsActivity$$Lambda$13() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        PrivacySettingsActivity.lambda$null$15$PrivacySettingsActivity(tLObject, tLRPC$TL_error);
    }
}
