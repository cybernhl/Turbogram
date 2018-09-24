package org.telegram.ui;

import org.telegram.ui.Components.PasscodeView.PasscodeViewDelegate;

final /* synthetic */ class LaunchActivity$$Lambda$5 implements PasscodeViewDelegate {
    private final LaunchActivity arg$1;

    LaunchActivity$$Lambda$5(LaunchActivity launchActivity) {
        this.arg$1 = launchActivity;
    }

    public void didAcceptedPassword() {
        this.arg$1.lambda$showPasscodeActivity$5$LaunchActivity();
    }
}
