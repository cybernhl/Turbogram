package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.messenger.LocaleController$LocaleInfo;

final /* synthetic */ class LaunchActivity$$Lambda$30 implements OnClickListener {
    private final LaunchActivity arg$1;
    private final LocaleController$LocaleInfo[] arg$2;

    LaunchActivity$$Lambda$30(LaunchActivity launchActivity, LocaleController$LocaleInfo[] localeController$LocaleInfoArr) {
        this.arg$1 = launchActivity;
        this.arg$2 = localeController$LocaleInfoArr;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$showLanguageAlertInternal$44$LaunchActivity(this.arg$2, dialogInterface, i);
    }
}
