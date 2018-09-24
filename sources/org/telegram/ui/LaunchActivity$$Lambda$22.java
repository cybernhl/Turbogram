package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LaunchActivity$$Lambda$22 implements OnClickListener {
    private final LaunchActivity arg$1;

    LaunchActivity$$Lambda$22(LaunchActivity launchActivity) {
        this.arg$1 = launchActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$didReceivedNotification$34$LaunchActivity(dialogInterface, i);
    }
}
