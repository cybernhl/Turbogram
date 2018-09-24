package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

final /* synthetic */ class LaunchActivity$$Lambda$41 implements Runnable {
    private final LaunchActivity arg$1;
    private final AlertDialog arg$2;
    private final TLRPC$TL_error arg$3;

    LaunchActivity$$Lambda$41(LaunchActivity launchActivity, AlertDialog alertDialog, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = launchActivity;
        this.arg$2 = alertDialog;
        this.arg$3 = tLRPC$TL_error;
    }

    public void run() {
        this.arg$1.lambda$null$22$LaunchActivity(this.arg$2, this.arg$3);
    }
}
