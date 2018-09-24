package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ProfileActivity$$Lambda$24 implements OnClickListener {
    private final ProfileActivity arg$1;
    private final int arg$2;

    ProfileActivity$$Lambda$24(ProfileActivity profileActivity, int i) {
        this.arg$1 = profileActivity;
        this.arg$2 = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$null$2$ProfileActivity(this.arg$2, dialogInterface, i);
    }
}
