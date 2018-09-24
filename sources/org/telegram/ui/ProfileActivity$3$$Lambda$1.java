package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ProfileActivity.C20583;

final /* synthetic */ class ProfileActivity$3$$Lambda$1 implements OnClickListener {
    private final C20583 arg$1;
    private final User arg$2;

    ProfileActivity$3$$Lambda$1(C20583 c20583, User user) {
        this.arg$1 = c20583;
        this.arg$2 = user;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onItemClick$1$ProfileActivity$3(this.arg$2, dialogInterface, i);
    }
}
