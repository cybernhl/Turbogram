package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$DecryptedMessage;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$Message;

final /* synthetic */ class SecretChatHelper$$Lambda$3 implements Runnable {
    private final SecretChatHelper arg$1;
    private final TLRPC$EncryptedChat arg$2;
    private final TLRPC$DecryptedMessage arg$3;
    private final TLRPC$Message arg$4;
    private final TLRPC$InputEncryptedFile arg$5;
    private final MessageObject arg$6;
    private final String arg$7;

    SecretChatHelper$$Lambda$3(SecretChatHelper secretChatHelper, TLRPC$EncryptedChat tLRPC$EncryptedChat, TLRPC$DecryptedMessage tLRPC$DecryptedMessage, TLRPC$Message tLRPC$Message, TLRPC$InputEncryptedFile tLRPC$InputEncryptedFile, MessageObject messageObject, String str) {
        this.arg$1 = secretChatHelper;
        this.arg$2 = tLRPC$EncryptedChat;
        this.arg$3 = tLRPC$DecryptedMessage;
        this.arg$4 = tLRPC$Message;
        this.arg$5 = tLRPC$InputEncryptedFile;
        this.arg$6 = messageObject;
        this.arg$7 = str;
    }

    public void run() {
        this.arg$1.lambda$performSendEncryptedRequest$7$SecretChatHelper(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
