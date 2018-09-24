package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_document;

final /* synthetic */ class SendMessagesHelper$$Lambda$23 implements Runnable {
    private final MessageObject arg$1;
    private final int arg$2;
    private final TLRPC$TL_document arg$3;
    private final String arg$4;
    private final HashMap arg$5;
    private final long arg$6;
    private final MessageObject arg$7;
    private final SendMessagesHelper$SendingMediaInfo arg$8;

    SendMessagesHelper$$Lambda$23(MessageObject messageObject, int i, TLRPC$TL_document tLRPC$TL_document, String str, HashMap hashMap, long j, MessageObject messageObject2, SendMessagesHelper$SendingMediaInfo sendMessagesHelper$SendingMediaInfo) {
        this.arg$1 = messageObject;
        this.arg$2 = i;
        this.arg$3 = tLRPC$TL_document;
        this.arg$4 = str;
        this.arg$5 = hashMap;
        this.arg$6 = j;
        this.arg$7 = messageObject2;
        this.arg$8 = sendMessagesHelper$SendingMediaInfo;
    }

    public void run() {
        SendMessagesHelper.lambda$null$54$SendMessagesHelper(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8);
    }
}
