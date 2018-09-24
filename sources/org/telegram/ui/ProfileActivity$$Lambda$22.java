package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$ChatParticipant;

final /* synthetic */ class ProfileActivity$$Lambda$22 implements OnClickListener {
    private final ProfileActivity arg$1;
    private final ArrayList arg$2;
    private final TLRPC$ChatParticipant arg$3;
    private final TLRPC$ChannelParticipant arg$4;

    ProfileActivity$$Lambda$22(ProfileActivity profileActivity, ArrayList arrayList, TLRPC$ChatParticipant tLRPC$ChatParticipant, TLRPC$ChannelParticipant tLRPC$ChannelParticipant) {
        this.arg$1 = profileActivity;
        this.arg$2 = arrayList;
        this.arg$3 = tLRPC$ChatParticipant;
        this.arg$4 = tLRPC$ChannelParticipant;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$null$8$ProfileActivity(this.arg$2, this.arg$3, this.arg$4, dialogInterface, i);
    }
}
