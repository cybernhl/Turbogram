package org.telegram.ui;

import android.view.View;
import android.view.View.OnClickListener;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.ui.Cells.LanguageCell;

final /* synthetic */ class LaunchActivity$$Lambda$28 implements OnClickListener {
    private final LocaleController$LocaleInfo[] arg$1;
    private final LanguageCell[] arg$2;

    LaunchActivity$$Lambda$28(LocaleController$LocaleInfo[] localeController$LocaleInfoArr, LanguageCell[] languageCellArr) {
        this.arg$1 = localeController$LocaleInfoArr;
        this.arg$2 = languageCellArr;
    }

    public void onClick(View view) {
        LaunchActivity.lambda$showLanguageAlertInternal$42$LaunchActivity(this.arg$1, this.arg$2, view);
    }
}
