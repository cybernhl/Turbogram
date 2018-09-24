package org.telegram.messenger;

final /* synthetic */ class LocaleController$$Lambda$1 implements Runnable {
    private final LocaleController arg$1;
    private final LocaleController$LocaleInfo arg$2;
    private final int arg$3;

    LocaleController$$Lambda$1(LocaleController localeController, LocaleController$LocaleInfo localeController$LocaleInfo, int i) {
        this.arg$1 = localeController;
        this.arg$2 = localeController$LocaleInfo;
        this.arg$3 = i;
    }

    public void run() {
        this.arg$1.lambda$applyLanguage$1$LocaleController(this.arg$2, this.arg$3);
    }
}
