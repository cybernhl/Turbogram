package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipants;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ProfileActivity$$Lambda$12 implements RequestDelegate {
    private final ProfileActivity arg$1;
    private final TLRPC$TL_channels_getParticipants arg$2;
    private final int arg$3;

    ProfileActivity$$Lambda$12(ProfileActivity profileActivity, TLRPC$TL_channels_getParticipants tLRPC$TL_channels_getParticipants, int i) {
        this.arg$1 = profileActivity;
        this.arg$2 = tLRPC$TL_channels_getParticipants;
        this.arg$3 = i;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$getChannelParticipants$22$ProfileActivity(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
