package org.telegram.messenger;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class MediaController$21 implements OnCancelListener {
    final /* synthetic */ boolean[] val$cancelled;

    MediaController$21(boolean[] zArr) {
        this.val$cancelled = zArr;
    }

    public void onCancel(DialogInterface dialog) {
        this.val$cancelled[0] = true;
    }
}
