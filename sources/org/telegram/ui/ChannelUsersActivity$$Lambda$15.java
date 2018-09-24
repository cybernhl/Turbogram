package org.telegram.ui;

import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.ui.ChannelRightsEditActivity.ChannelRightsEditActivityDelegate;

final /* synthetic */ class ChannelUsersActivity$$Lambda$15 implements ChannelRightsEditActivityDelegate {
    private final ChannelUsersActivity arg$1;
    private final TLRPC$ChannelParticipant arg$2;

    ChannelUsersActivity$$Lambda$15(ChannelUsersActivity channelUsersActivity, TLRPC$ChannelParticipant tLRPC$ChannelParticipant) {
        this.arg$1 = channelUsersActivity;
        this.arg$2 = tLRPC$ChannelParticipant;
    }

    public void didSetRights(int i, TLRPC$TL_channelAdminRights tLRPC$TL_channelAdminRights, TLRPC$TL_channelBannedRights tLRPC$TL_channelBannedRights) {
        this.arg$1.lambda$null$2$ChannelUsersActivity(this.arg$2, i, tLRPC$TL_channelAdminRights, tLRPC$TL_channelBannedRights);
    }
}
