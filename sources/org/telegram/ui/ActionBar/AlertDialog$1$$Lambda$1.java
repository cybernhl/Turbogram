package org.telegram.ui.ActionBar;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import org.telegram.ui.ActionBar.AlertDialog.C09901;

final /* synthetic */ class AlertDialog$1$$Lambda$1 implements OnScrollChangedListener {
    private final C09901 arg$1;

    AlertDialog$1$$Lambda$1(C09901 c09901) {
        this.arg$1 = c09901;
    }

    public void onScrollChanged() {
        this.arg$1.lambda$onLayout$1$AlertDialog$1();
    }
}
