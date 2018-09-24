package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$TL_channelAdminRights;
import org.telegram.tgnet.TLRPC$TL_channelBannedRights;
import org.telegram.ui.ChannelRightsEditActivity.ChannelRightsEditActivityDelegate;

final /* synthetic */ class ChannelUsersActivity$$Lambda$12 implements ChannelRightsEditActivityDelegate {
    private final ChannelUsersActivity arg$1;
    private final ArrayList arg$2;
    private final int arg$3;
    private final TLRPC$ChannelParticipant arg$4;

    ChannelUsersActivity$$Lambda$12(ChannelUsersActivity channelUsersActivity, ArrayList arrayList, int i, TLRPC$ChannelParticipant tLRPC$ChannelParticipant) {
        this.arg$1 = channelUsersActivity;
        this.arg$2 = arrayList;
        this.arg$3 = i;
        this.arg$4 = tLRPC$ChannelParticipant;
    }

    public void didSetRights(int i, TLRPC$TL_channelAdminRights tLRPC$TL_channelAdminRights, TLRPC$TL_channelBannedRights tLRPC$TL_channelBannedRights) {
        this.arg$1.lambda$null$5$ChannelUsersActivity(this.arg$2, this.arg$3, this.arg$4, i, tLRPC$TL_channelAdminRights, tLRPC$TL_channelBannedRights);
    }
}
