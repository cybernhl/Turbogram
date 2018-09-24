package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class NotificationsSettingsActivity$$Lambda$2 implements RequestDelegate {
    static final RequestDelegate $instance = new NotificationsSettingsActivity$$Lambda$2();

    private NotificationsSettingsActivity$$Lambda$2() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        NotificationsSettingsActivity.m1235xafa3bc98(tLObject, tLRPC$TL_error);
    }
}
