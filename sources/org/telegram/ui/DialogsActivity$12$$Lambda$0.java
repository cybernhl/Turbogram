package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.ui.DialogsActivity.AnonymousClass12;

final /* synthetic */ class DialogsActivity$12$$Lambda$0 implements OnClickListener {
    private final AnonymousClass12 arg$1;
    private final int arg$2;

    DialogsActivity$12$$Lambda$0(AnonymousClass12 anonymousClass12, int i) {
        this.arg$1 = anonymousClass12;
        this.arg$2 = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$needRemoveHint$0$DialogsActivity$12(this.arg$2, dialogInterface, i);
    }
}
