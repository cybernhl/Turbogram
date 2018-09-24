package org.telegram.ui;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.ui.LocationActivity.LocationActivityDelegate;

final /* synthetic */ class LaunchActivity$$Lambda$37 implements LocationActivityDelegate {
    private final HashMap arg$1;
    private final int arg$2;

    LaunchActivity$$Lambda$37(HashMap hashMap, int i) {
        this.arg$1 = hashMap;
        this.arg$2 = i;
    }

    public void didSelectLocation(TLRPC$MessageMedia tLRPC$MessageMedia, int i) {
        LaunchActivity.lambda$null$35$LaunchActivity(this.arg$1, this.arg$2, tLRPC$MessageMedia, i);
    }
}
