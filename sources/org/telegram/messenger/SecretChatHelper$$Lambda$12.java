package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SecretChatHelper$$Lambda$12 implements RequestDelegate {
    private final SecretChatHelper arg$1;
    private final TLRPC$EncryptedChat arg$2;

    SecretChatHelper$$Lambda$12(SecretChatHelper secretChatHelper, TLRPC$EncryptedChat tLRPC$EncryptedChat) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$EncryptedChat;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$acceptSecretChat$22$SecretChatHelper(this.arg$2, tLObject, tLRPC$TL_error);
    }
}
