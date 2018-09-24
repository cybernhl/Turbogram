package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$messages_Dialogs;

final /* synthetic */ class MessagesController$$Lambda$206 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$messages_Dialogs arg$2;
    private final boolean arg$3;
    private final int arg$4;

    MessagesController$$Lambda$206(MessagesController messagesController, TLRPC$messages_Dialogs tLRPC$messages_Dialogs, boolean z, int i) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$messages_Dialogs;
        this.arg$3 = z;
        this.arg$4 = i;
    }

    public void run() {
        this.arg$1.lambda$null$106$MessagesController(this.arg$2, this.arg$3, this.arg$4);
    }
}
