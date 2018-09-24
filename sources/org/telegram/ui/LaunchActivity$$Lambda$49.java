package org.telegram.ui;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.ui.LocationActivity.LocationActivityDelegate;

final /* synthetic */ class LaunchActivity$$Lambda$49 implements LocationActivityDelegate {
    private final int[] arg$1;
    private final long arg$2;

    LaunchActivity$$Lambda$49(int[] iArr, long j) {
        this.arg$1 = iArr;
        this.arg$2 = j;
    }

    public void didSelectLocation(TLRPC$MessageMedia tLRPC$MessageMedia, int i) {
        SendMessagesHelper.getInstance(this.arg$1[0]).sendMessage(tLRPC$MessageMedia, this.arg$2, null, null, null);
    }
}
