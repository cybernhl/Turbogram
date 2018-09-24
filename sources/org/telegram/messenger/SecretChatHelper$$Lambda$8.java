package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_encryptedChatDiscarded;

final /* synthetic */ class SecretChatHelper$$Lambda$8 implements Runnable {
    private final SecretChatHelper arg$1;
    private final TLRPC$TL_encryptedChatDiscarded arg$2;

    SecretChatHelper$$Lambda$8(SecretChatHelper secretChatHelper, TLRPC$TL_encryptedChatDiscarded tLRPC$TL_encryptedChatDiscarded) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$TL_encryptedChatDiscarded;
    }

    public void run() {
        this.arg$1.lambda$decryptMessage$16$SecretChatHelper(this.arg$2);
    }
}
