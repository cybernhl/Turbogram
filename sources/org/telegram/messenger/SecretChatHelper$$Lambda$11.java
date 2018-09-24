package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SecretChatHelper$$Lambda$11 implements RequestDelegate {
    static final RequestDelegate $instance = new SecretChatHelper$$Lambda$11();

    private SecretChatHelper$$Lambda$11() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        SecretChatHelper.lambda$declineSecretChat$19$SecretChatHelper(tLObject, tLRPC$TL_error);
    }
}
