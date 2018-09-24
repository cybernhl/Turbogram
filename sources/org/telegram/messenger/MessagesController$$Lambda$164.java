package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$TL_messages_dialogs;
import org.telegram.tgnet.TLRPC$TL_messages_peerDialogs;

final /* synthetic */ class MessagesController$$Lambda$164 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$TL_messages_peerDialogs arg$2;
    private final ArrayList arg$3;
    private final ArrayList arg$4;
    private final long arg$5;
    private final LongSparseArray arg$6;
    private final TLRPC$TL_messages_dialogs arg$7;

    MessagesController$$Lambda$164(MessagesController messagesController, TLRPC$TL_messages_peerDialogs tLRPC$TL_messages_peerDialogs, ArrayList arrayList, ArrayList arrayList2, long j, LongSparseArray longSparseArray, TLRPC$TL_messages_dialogs tLRPC$TL_messages_dialogs) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$TL_messages_peerDialogs;
        this.arg$3 = arrayList;
        this.arg$4 = arrayList2;
        this.arg$5 = j;
        this.arg$6 = longSparseArray;
        this.arg$7 = tLRPC$TL_messages_dialogs;
    }

    public void run() {
        this.arg$1.lambda$null$201$MessagesController(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
