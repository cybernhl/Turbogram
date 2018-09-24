package com.google.android.gms.measurement.internal;

final class zzeo implements Runnable {
    private final /* synthetic */ Runnable zzacf;
    private final /* synthetic */ zzfa zzasv;

    zzeo(zzel zzel, zzfa zzfa, Runnable runnable) {
        this.zzasv = zzfa;
        this.zzacf = runnable;
    }

    public final void run() {
        this.zzasv.zzly();
        this.zzasv.zzg(this.zzacf);
        this.zzasv.zzlt();
    }
}
