package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

final /* synthetic */ class SecretChatHelper$$Lambda$29 implements Runnable {
    private final SecretChatHelper arg$1;
    private final TLRPC$Message arg$2;

    SecretChatHelper$$Lambda$29(SecretChatHelper secretChatHelper, TLRPC$Message tLRPC$Message) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$Message;
    }

    public void run() {
        this.arg$1.lambda$null$5$SecretChatHelper(this.arg$2);
    }
}
