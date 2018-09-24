package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

final /* synthetic */ class LaunchActivity$$Lambda$43 implements Runnable {
    private final LaunchActivity arg$1;
    private final AlertDialog arg$2;
    private final TLRPC$TL_error arg$3;
    private final TLObject arg$4;
    private final int arg$5;

    LaunchActivity$$Lambda$43(LaunchActivity launchActivity, AlertDialog alertDialog, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject, int i) {
        this.arg$1 = launchActivity;
        this.arg$2 = alertDialog;
        this.arg$3 = tLRPC$TL_error;
        this.arg$4 = tLObject;
        this.arg$5 = i;
    }

    public void run() {
        this.arg$1.lambda$null$17$LaunchActivity(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
