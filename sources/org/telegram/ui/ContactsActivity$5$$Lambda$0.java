package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.ui.ContactsActivity.C17085;

final /* synthetic */ class ContactsActivity$5$$Lambda$0 implements OnClickListener {
    private final C17085 arg$1;
    private final String arg$2;

    ContactsActivity$5$$Lambda$0(C17085 c17085, String str) {
        this.arg$1 = c17085;
        this.arg$2 = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onItemClick$0$ContactsActivity$5(this.arg$2, dialogInterface, i);
    }
}
