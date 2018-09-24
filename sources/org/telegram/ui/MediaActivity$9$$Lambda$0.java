package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.ui.MediaActivity.C18839;

final /* synthetic */ class MediaActivity$9$$Lambda$0 implements OnClickListener {
    private final C18839 arg$1;
    private final String arg$2;

    MediaActivity$9$$Lambda$0(C18839 c18839, String str) {
        this.arg$1 = c18839;
        this.arg$2 = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onLinkLongPress$0$MediaActivity$9(this.arg$2, dialogInterface, i);
    }
}
