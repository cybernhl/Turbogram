package org.telegram.messenger;

import org.telegram.messenger.ImageLoader.C08052;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;

final /* synthetic */ class ImageLoader$2$$Lambda$1 implements Runnable {
    private final C08052 arg$1;
    private final int arg$2;
    private final String arg$3;
    private final TLRPC$InputFile arg$4;
    private final TLRPC$InputEncryptedFile arg$5;
    private final byte[] arg$6;
    private final byte[] arg$7;
    private final long arg$8;

    ImageLoader$2$$Lambda$1(C08052 c08052, int i, String str, TLRPC$InputFile tLRPC$InputFile, TLRPC$InputEncryptedFile tLRPC$InputEncryptedFile, byte[] bArr, byte[] bArr2, long j) {
        this.arg$1 = c08052;
        this.arg$2 = i;
        this.arg$3 = str;
        this.arg$4 = tLRPC$InputFile;
        this.arg$5 = tLRPC$InputEncryptedFile;
        this.arg$6 = bArr;
        this.arg$7 = bArr2;
        this.arg$8 = j;
    }

    public void run() {
        this.arg$1.lambda$fileDidUploaded$2$ImageLoader$2(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8);
    }
}
