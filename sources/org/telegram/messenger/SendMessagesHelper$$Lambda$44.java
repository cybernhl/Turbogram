package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_sendMultiMedia;

final /* synthetic */ class SendMessagesHelper$$Lambda$44 implements Runnable {
    private final SendMessagesHelper arg$1;
    private final TLRPC$TL_error arg$2;
    private final TLObject arg$3;
    private final ArrayList arg$4;
    private final ArrayList arg$5;
    private final TLRPC$TL_messages_sendMultiMedia arg$6;

    SendMessagesHelper$$Lambda$44(SendMessagesHelper sendMessagesHelper, TLRPC$TL_error tLRPC$TL_error, TLObject tLObject, ArrayList arrayList, ArrayList arrayList2, TLRPC$TL_messages_sendMultiMedia tLRPC$TL_messages_sendMultiMedia) {
        this.arg$1 = sendMessagesHelper;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = tLObject;
        this.arg$4 = arrayList;
        this.arg$5 = arrayList2;
        this.arg$6 = tLRPC$TL_messages_sendMultiMedia;
    }

    public void run() {
        this.arg$1.lambda$null$27$SendMessagesHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
