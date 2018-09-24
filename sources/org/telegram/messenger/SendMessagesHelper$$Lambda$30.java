package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$BotInlineResult;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_game;
import org.telegram.tgnet.TLRPC$TL_photo;

final /* synthetic */ class SendMessagesHelper$$Lambda$30 implements Runnable {
    private final TLRPC$TL_document arg$1;
    private final int arg$2;
    private final String arg$3;
    private final long arg$4;
    private final MessageObject arg$5;
    private final TLRPC$BotInlineResult arg$6;
    private final HashMap arg$7;
    private final TLRPC$TL_photo arg$8;
    private final TLRPC$TL_game arg$9;

    SendMessagesHelper$$Lambda$30(TLRPC$TL_document tLRPC$TL_document, int i, String str, long j, MessageObject messageObject, TLRPC$BotInlineResult tLRPC$BotInlineResult, HashMap hashMap, TLRPC$TL_photo tLRPC$TL_photo, TLRPC$TL_game tLRPC$TL_game) {
        this.arg$1 = tLRPC$TL_document;
        this.arg$2 = i;
        this.arg$3 = str;
        this.arg$4 = j;
        this.arg$5 = messageObject;
        this.arg$6 = tLRPC$BotInlineResult;
        this.arg$7 = hashMap;
        this.arg$8 = tLRPC$TL_photo;
        this.arg$9 = tLRPC$TL_game;
    }

    public void run() {
        SendMessagesHelper.lambda$null$48$SendMessagesHelper(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8, this.arg$9);
    }
}
