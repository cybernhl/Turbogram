package org.telegram.ui;

import android.view.View;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended;

final /* synthetic */ class DialogsActivity$$Lambda$2 implements OnItemClickListenerExtended {
    private final DialogsActivity arg$1;

    DialogsActivity$$Lambda$2(DialogsActivity dialogsActivity) {
        this.arg$1 = dialogsActivity;
    }

    public void onItemClick(View view, int i, float f, float f2) {
        this.arg$1.lambda$createView$2$DialogsActivity(view, i, f, f2);
    }
}
