package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import org.telegram.ui.PrivacyControlActivity.C20471;

final /* synthetic */ class PrivacyControlActivity$1$$Lambda$0 implements OnClickListener {
    private final C20471 arg$1;
    private final SharedPreferences arg$2;

    PrivacyControlActivity$1$$Lambda$0(C20471 c20471, SharedPreferences sharedPreferences) {
        this.arg$1 = c20471;
        this.arg$2 = sharedPreferences;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onItemClick$0$PrivacyControlActivity$1(this.arg$2, dialogInterface, i);
    }
}
