package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ProfileActivity$$Lambda$4 implements RequestDelegate {
    private final ProfileActivity arg$1;

    ProfileActivity$$Lambda$4(ProfileActivity profileActivity) {
        this.arg$1 = profileActivity;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$createView$11$ProfileActivity(tLObject, tLRPC$TL_error);
    }
}
