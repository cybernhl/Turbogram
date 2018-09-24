package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.ui.DialogsActivity.C17395;

final /* synthetic */ class DialogsActivity$5$$Lambda$1 implements OnClickListener {
    private final C17395 arg$1;
    private final boolean arg$2;
    private final boolean arg$3;
    private final boolean arg$4;
    private final TLRPC$TL_dialog arg$5;
    private final boolean arg$6;
    private final TLRPC$Chat arg$7;

    DialogsActivity$5$$Lambda$1(C17395 c17395, boolean z, boolean z2, boolean z3, TLRPC$TL_dialog tLRPC$TL_dialog, boolean z4, TLRPC$Chat tLRPC$Chat) {
        this.arg$1 = c17395;
        this.arg$2 = z;
        this.arg$3 = z2;
        this.arg$4 = z3;
        this.arg$5 = tLRPC$TL_dialog;
        this.arg$6 = z4;
        this.arg$7 = tLRPC$Chat;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onItemClick$1$DialogsActivity$5(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, dialogInterface, i);
    }
}
