package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LaunchActivity$$Lambda$18 implements OnClickListener {
    private final LaunchActivity arg$1;

    LaunchActivity$$Lambda$18(LaunchActivity launchActivity) {
        this.arg$1 = launchActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onRequestPermissionsResult$30$LaunchActivity(dialogInterface, i);
    }
}
