package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.ui.ChannelRightsEditActivity.ChannelRightsEditActivityDelegate;

final /* synthetic */ class ProfileActivity$$Lambda$20 implements ChannelRightsEditActivityDelegate {
    private final ProfileActivity arg$1;

    ProfileActivity$$Lambda$20(ProfileActivity profileActivity) {
        this.arg$1 = profileActivity;
    }

    public void didSetRights(int i, TLRPC$TL_channelAdminRights tLRPC$TL_channelAdminRights, TLRPC$TL_channelBannedRights tLRPC$TL_channelBannedRights) {
        this.arg$1.lambda$null$12$ProfileActivity(i, tLRPC$TL_channelAdminRights, tLRPC$TL_channelBannedRights);
    }
}
