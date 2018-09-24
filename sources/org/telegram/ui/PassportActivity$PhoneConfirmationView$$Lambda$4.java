package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.PhoneConfirmationView;

final /* synthetic */ class PassportActivity$PhoneConfirmationView$$Lambda$4 implements RequestDelegate {
    static final RequestDelegate $instance = new PassportActivity$PhoneConfirmationView$$Lambda$4();

    private PassportActivity$PhoneConfirmationView$$Lambda$4() {
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        PhoneConfirmationView.lambda$onBackPressed$7$PassportActivity$PhoneConfirmationView(tLObject, tLRPC$TL_error);
    }
}
