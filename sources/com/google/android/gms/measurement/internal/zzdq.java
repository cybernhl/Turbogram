package com.google.android.gms.measurement.internal;

final class zzdq implements Runnable {
    private final /* synthetic */ zzdo zzarx;
    private final /* synthetic */ zzdn zzary;

    zzdq(zzdo zzdo, zzdn zzdn) {
        this.zzarx = zzdo;
        this.zzary = zzdn;
    }

    public final void run() {
        this.zzarx.zza(this.zzary);
        this.zzarx.zzaro = null;
        this.zzarx.zzgg().zzb(null);
    }
}
