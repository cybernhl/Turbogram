package org.telegram.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import org.telegram.ui.LoginActivity.TokenView;

final /* synthetic */ class LoginActivity$TokenView$$Lambda$0 implements OnEditorActionListener {
    private final TokenView arg$1;

    LoginActivity$TokenView$$Lambda$0(TokenView tokenView) {
        this.arg$1 = tokenView;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.arg$1.lambda$new$0$LoginActivity$TokenView(textView, i, keyEvent);
    }
}
