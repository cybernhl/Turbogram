package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_help_appUpdate;

final /* synthetic */ class LaunchActivity$$Lambda$38 implements Runnable {
    private final LaunchActivity arg$1;
    private final TLRPC$TL_help_appUpdate arg$2;
    private final int arg$3;

    LaunchActivity$$Lambda$38(LaunchActivity launchActivity, TLRPC$TL_help_appUpdate tLRPC$TL_help_appUpdate, int i) {
        this.arg$1 = launchActivity;
        this.arg$2 = tLRPC$TL_help_appUpdate;
        this.arg$3 = i;
    }

    public void run() {
        this.arg$1.lambda$null$27$LaunchActivity(this.arg$2, this.arg$3);
    }
}
