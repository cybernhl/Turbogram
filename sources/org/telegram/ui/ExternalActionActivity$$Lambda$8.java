package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ExternalActionActivity$$Lambda$8 implements OnDismissListener {
    private final ExternalActionActivity arg$1;
    private final TLRPC$TL_error arg$2;

    ExternalActionActivity$$Lambda$8(ExternalActionActivity externalActionActivity, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1 = externalActionActivity;
        this.arg$2 = tLRPC$TL_error;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.arg$1.lambda$null$7$ExternalActionActivity(this.arg$2, dialogInterface);
    }
}
