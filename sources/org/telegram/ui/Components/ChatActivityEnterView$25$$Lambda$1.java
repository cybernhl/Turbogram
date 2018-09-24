package org.telegram.ui.Components;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.ui.Components.ChatActivityEnterView.AnonymousClass25;

final /* synthetic */ class ChatActivityEnterView$25$$Lambda$1 implements OnClickListener {
    private final AnonymousClass25 arg$1;
    private final TLRPC$Document arg$2;

    ChatActivityEnterView$25$$Lambda$1(AnonymousClass25 anonymousClass25, TLRPC$Document tLRPC$Document) {
        this.arg$1 = anonymousClass25;
        this.arg$2 = tLRPC$Document;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$onGifSelected$1$ChatActivityEnterView$25(this.arg$2, dialogInterface, i);
    }
}
