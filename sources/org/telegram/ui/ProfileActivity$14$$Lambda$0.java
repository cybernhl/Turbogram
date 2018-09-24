package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ProfileActivity.AnonymousClass14;

final /* synthetic */ class ProfileActivity$14$$Lambda$0 implements OnClickListener {
    private final AnonymousClass14 arg$1;
    private final long arg$2;
    private final User arg$3;

    ProfileActivity$14$$Lambda$0(AnonymousClass14 anonymousClass14, long j, User user) {
        this.arg$1 = anonymousClass14;
        this.arg$2 = j;
        this.arg$3 = user;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$didSelectDialogs$0$ProfileActivity$14(this.arg$2, this.arg$3, dialogInterface, i);
    }
}
