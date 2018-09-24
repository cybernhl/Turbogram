package com.google.android.gms.measurement.internal;

final class zzbu implements Runnable {
    private final /* synthetic */ zzcr zzaqj;
    private final /* synthetic */ zzbt zzaqk;

    zzbu(zzbt zzbt, zzcr zzcr) {
        this.zzaqk = zzbt;
        this.zzaqj = zzcr;
    }

    public final void run() {
        this.zzaqk.zza(this.zzaqj);
        this.zzaqk.start();
    }
}
