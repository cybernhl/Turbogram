package com.google.android.gms.measurement.internal;

final class zzby implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzl zzaqp;

    zzby(zzbv zzbv, zzl zzl, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqp = zzl;
        this.zzaqn = zzh;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzb(this.zzaqp, this.zzaqn);
    }
}
