package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class BaseLocationAdapter$$Lambda$1 implements RequestDelegate {
    private final BaseLocationAdapter arg$1;

    BaseLocationAdapter$$Lambda$1(BaseLocationAdapter baseLocationAdapter) {
        this.arg$1 = baseLocationAdapter;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$searchPlacesWithQuery$3$BaseLocationAdapter(tLObject, tLRPC$TL_error);
    }
}
