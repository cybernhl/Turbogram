package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_verifyEmail;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.PassportActivity.C19323;

final /* synthetic */ class PassportActivity$3$$Lambda$6 implements Runnable {
    private final C19323 arg$1;
    private final TLRPC$TL_error arg$2;
    private final Runnable arg$3;
    private final ErrorRunnable arg$4;
    private final TLRPC$TL_account_verifyEmail arg$5;

    PassportActivity$3$$Lambda$6(C19323 c19323, TLRPC$TL_error tLRPC$TL_error, Runnable runnable, ErrorRunnable errorRunnable, TLRPC$TL_account_verifyEmail tLRPC$TL_account_verifyEmail) {
        this.arg$1 = c19323;
        this.arg$2 = tLRPC$TL_error;
        this.arg$3 = runnable;
        this.arg$4 = errorRunnable;
        this.arg$5 = tLRPC$TL_account_verifyEmail;
    }

    public void run() {
        this.arg$1.lambda$null$5$PassportActivity$3(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
