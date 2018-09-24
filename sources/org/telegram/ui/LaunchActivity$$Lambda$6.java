package org.telegram.ui;

import android.os.Bundle;

final /* synthetic */ class LaunchActivity$$Lambda$6 implements Runnable {
    private final LaunchActivity arg$1;
    private final Bundle arg$2;

    LaunchActivity$$Lambda$6(LaunchActivity launchActivity, Bundle bundle) {
        this.arg$1 = launchActivity;
        this.arg$2 = bundle;
    }

    public void run() {
        this.arg$1.lambda$handleIntent$6$LaunchActivity(this.arg$2);
    }
}
