package org.telegram.messenger;

import org.telegram.messenger.ImageLoader.C08052;

final /* synthetic */ class ImageLoader$2$$Lambda$2 implements Runnable {
    private final C08052 arg$1;
    private final int arg$2;
    private final String arg$3;
    private final boolean arg$4;

    ImageLoader$2$$Lambda$2(C08052 c08052, int i, String str, boolean z) {
        this.arg$1 = c08052;
        this.arg$2 = i;
        this.arg$3 = str;
        this.arg$4 = z;
    }

    public void run() {
        this.arg$1.lambda$fileDidFailedUpload$4$ImageLoader$2(this.arg$2, this.arg$3, this.arg$4);
    }
}
