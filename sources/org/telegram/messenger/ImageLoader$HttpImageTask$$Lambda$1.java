package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class ImageLoader$HttpImageTask$$Lambda$1 implements RequestDelegate {
    static final RequestDelegate $instance = new ImageLoader$HttpImageTask$$Lambda$1();

    private ImageLoader$HttpImageTask$$Lambda$1() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        HttpImageTask.lambda$doInBackground$2$ImageLoader$HttpImageTask(tLObject, tLRPC$TL_error);
    }
}
