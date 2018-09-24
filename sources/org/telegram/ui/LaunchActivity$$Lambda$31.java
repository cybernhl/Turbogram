package org.telegram.ui;

import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

final /* synthetic */ class LaunchActivity$$Lambda$31 implements RequestDelegate {
    private final LaunchActivity arg$1;
    private final LocaleController$LocaleInfo[] arg$2;
    private final String arg$3;

    LaunchActivity$$Lambda$31(LaunchActivity launchActivity, LocaleController$LocaleInfo[] localeController$LocaleInfoArr, String str) {
        this.arg$1 = launchActivity;
        this.arg$2 = localeController$LocaleInfoArr;
        this.arg$3 = str;
    }

    public void run(TLObject tLObject, TLRPC$TL_error tLRPC$TL_error) {
        this.arg$1.lambda$showLanguageAlert$46$LaunchActivity(this.arg$2, this.arg$3, tLObject, tLRPC$TL_error);
    }
}
