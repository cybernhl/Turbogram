package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_photo;

final /* synthetic */ class SendMessagesHelper$$Lambda$26 implements Runnable {
    private final MessageObject arg$1;
    private final int arg$2;
    private final TLRPC$TL_photo arg$3;
    private final HashMap arg$4;
    private final long arg$5;
    private final MessageObject arg$6;
    private final SendMessagesHelper$SendingMediaInfo arg$7;

    SendMessagesHelper$$Lambda$26(MessageObject messageObject, int i, TLRPC$TL_photo tLRPC$TL_photo, HashMap hashMap, long j, MessageObject messageObject2, SendMessagesHelper$SendingMediaInfo sendMessagesHelper$SendingMediaInfo) {
        this.arg$1 = messageObject;
        this.arg$2 = i;
        this.arg$3 = tLRPC$TL_photo;
        this.arg$4 = hashMap;
        this.arg$5 = j;
        this.arg$6 = messageObject2;
        this.arg$7 = sendMessagesHelper$SendingMediaInfo;
    }

    public void run() {
        SendMessagesHelper.lambda$null$57$SendMessagesHelper(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
