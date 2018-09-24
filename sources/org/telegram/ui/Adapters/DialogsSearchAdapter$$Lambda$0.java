package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_searchGlobal;

final /* synthetic */ class DialogsSearchAdapter$$Lambda$0 implements RequestDelegate {
    private final DialogsSearchAdapter arg$1;
    private final int arg$2;
    private final TLRPC$TL_messages_searchGlobal arg$3;

    DialogsSearchAdapter$$Lambda$0(DialogsSearchAdapter dialogsSearchAdapter, int i, TLRPC$TL_messages_searchGlobal tLRPC$TL_messages_searchGlobal) {
        this.arg$1 = dialogsSearchAdapter;
        this.arg$2 = i;
        this.arg$3 = tLRPC$TL_messages_searchGlobal;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$searchMessagesInternal$1$DialogsSearchAdapter(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
