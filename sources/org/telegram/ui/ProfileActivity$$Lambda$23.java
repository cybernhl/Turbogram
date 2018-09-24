package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChatParticipant;
import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.ui.ChannelRightsEditActivity.ChannelRightsEditActivityDelegate;

final /* synthetic */ class ProfileActivity$$Lambda$23 implements ChannelRightsEditActivityDelegate {
    private final ProfileActivity arg$1;
    private final ArrayList arg$2;
    private final int arg$3;
    private final TLRPC$ChatParticipant arg$4;

    ProfileActivity$$Lambda$23(ProfileActivity profileActivity, ArrayList arrayList, int i, TLRPC$ChatParticipant tLRPC$ChatParticipant) {
        this.arg$1 = profileActivity;
        this.arg$2 = arrayList;
        this.arg$3 = i;
        this.arg$4 = tLRPC$ChatParticipant;
    }

    public void didSetRights(int i, TLRPC$TL_channelAdminRights tLRPC$TL_channelAdminRights, TLRPC$TL_channelBannedRights tLRPC$TL_channelBannedRights) {
        this.arg$1.lambda$null$7$ProfileActivity(this.arg$2, this.arg$3, this.arg$4, i, tLRPC$TL_channelAdminRights, tLRPC$TL_channelBannedRights);
    }
}
