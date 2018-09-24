package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Peer;

final /* synthetic */ class SendMessagesHelper$$Lambda$56 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$Message arg$2;
    private final int arg$3;
    private final TLRPC$Peer arg$4;
    private final ArrayList arg$5;
    private final long arg$6;
    private final TLRPC$Message arg$7;

    SendMessagesHelper$$Lambda$56(SendMessagesHelper sendMessagesHelper, TLRPC$Message tLRPC$Message, int i, TLRPC$Peer tLRPC$Peer, ArrayList arrayList, long j, TLRPC$Message tLRPC$Message2) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$Message;
        this.arg$3 = i;
        this.arg$4 = tLRPC$Peer;
        this.arg$5 = arrayList;
        this.arg$6 = j;
        this.arg$7 = tLRPC$Message2;
    }

    public void run() {
        this.arg$1.lambda$null$6$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
