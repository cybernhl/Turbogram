package com.google.android.gms.measurement.internal;

final class zzex extends zzv {
    private final /* synthetic */ zzfa zzasv;
    private final /* synthetic */ zzew zzatb;

    zzex(zzew zzew, zzcq zzcq, zzfa zzfa) {
        this.zzatb = zzew;
        this.zzasv = zzfa;
        super(zzcq);
    }

    public final void run() {
        this.zzatb.cancel();
        this.zzatb.zzgo().zzjl().zzbx("Starting upload from DelayedRunnable");
        this.zzasv.zzlt();
    }
}
