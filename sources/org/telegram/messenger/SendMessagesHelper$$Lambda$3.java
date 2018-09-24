package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_forwardMessages;

final /* synthetic */ class SendMessagesHelper$$Lambda$3 implements RequestDelegate {
    private final SendMessagesHelper arg$1;
    private final long arg$2;
    private final boolean arg$3;
    private final boolean arg$4;
    private final LongSparseArray arg$5;
    private final ArrayList arg$6;
    private final ArrayList arg$7;
    private final TLRPC$Peer arg$8;
    private final TLRPC$TL_messages_forwardMessages arg$9;

    SendMessagesHelper$$Lambda$3(SendMessagesHelper sendMessagesHelper, long j, boolean z, boolean z2, LongSparseArray longSparseArray, ArrayList arrayList, ArrayList arrayList2, TLRPC$Peer tLRPC$Peer, TLRPC$TL_messages_forwardMessages tLRPC$TL_messages_forwardMessages) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = j;
        this.arg$3 = z;
        this.arg$4 = z2;
        this.arg$5 = longSparseArray;
        this.arg$6 = arrayList;
        this.arg$7 = arrayList2;
        this.arg$8 = tLRPC$Peer;
        this.arg$9 = tLRPC$TL_messages_forwardMessages;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$sendMessage$9$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8, this.arg$9, tLObject, tLRPC$TL_error);
    }
}
