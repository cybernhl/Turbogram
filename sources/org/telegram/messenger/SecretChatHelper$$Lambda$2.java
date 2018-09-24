package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

final /* synthetic */ class SecretChatHelper$$Lambda$2 implements Runnable {
    private final SecretChatHelper arg$1;
    private final TLRPC$EncryptedChat arg$2;
    private final TLRPC$EncryptedChat arg$3;

    SecretChatHelper$$Lambda$2(SecretChatHelper secretChatHelper, TLRPC$EncryptedChat tLRPC$EncryptedChat, TLRPC$EncryptedChat tLRPC$EncryptedChat2) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$EncryptedChat;
        this.arg$3 = tLRPC$EncryptedChat2;
    }

    public void run() {
        this.arg$1.lambda$processUpdateEncryption$2$SecretChatHelper(this.arg$2, this.arg$3);
    }
}
