package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LaunchActivity$$Lambda$15 implements OnClickListener {
    private final int arg$1;
    private final int[] arg$2;

    LaunchActivity$$Lambda$15(int i, int[] iArr) {
        this.arg$1 = i;
        this.arg$2 = iArr;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LaunchActivity.lambda$runLinkRequest$26$LaunchActivity(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
