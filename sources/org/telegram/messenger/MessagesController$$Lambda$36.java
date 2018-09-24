package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$photos_Photos;

final /* synthetic */ class MessagesController$$Lambda$36 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$photos_Photos arg$2;
    private final boolean arg$3;
    private final int arg$4;
    private final int arg$5;
    private final int arg$6;

    MessagesController$$Lambda$36(MessagesController messagesController, TLRPC$photos_Photos tLRPC$photos_Photos, boolean z, int i, int i2, int i3) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$photos_Photos;
        this.arg$3 = z;
        this.arg$4 = i;
        this.arg$5 = i2;
        this.arg$6 = i3;
    }

    public void run() {
        this.arg$1.lambda$processLoadedUserPhotos$50$MessagesController(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
