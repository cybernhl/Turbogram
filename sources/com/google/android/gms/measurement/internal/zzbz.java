package com.google.android.gms.measurement.internal;

final class zzbz implements Runnable {
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzl zzaqp;

    zzbz(zzbv zzbv, zzl zzl) {
        this.zzaqo = zzbv;
        this.zzaqp = zzl;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzf(this.zzaqp);
    }
}
