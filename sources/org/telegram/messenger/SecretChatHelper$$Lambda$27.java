package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$DecryptedMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class SecretChatHelper$$Lambda$27 implements RequestDelegate {
    private final SecretChatHelper arg$1;
    private final TLRPC$DecryptedMessage arg$2;
    private final TLRPC$EncryptedChat arg$3;
    private final TLRPC$Message arg$4;
    private final MessageObject arg$5;
    private final String arg$6;

    SecretChatHelper$$Lambda$27(SecretChatHelper secretChatHelper, TLRPC$DecryptedMessage tLRPC$DecryptedMessage, TLRPC$EncryptedChat tLRPC$EncryptedChat, TLRPC$Message tLRPC$Message, MessageObject messageObject, String str) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$DecryptedMessage;
        this.arg$3 = tLRPC$EncryptedChat;
        this.arg$4 = tLRPC$Message;
        this.arg$5 = messageObject;
        this.arg$6 = str;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$null$6$SecretChatHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, tLObject, tLRPC$TL_error);
    }
}
