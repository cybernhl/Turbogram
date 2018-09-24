package com.google.android.gms.measurement.internal;

final class zzbx implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzl zzaqp;

    zzbx(zzbv zzbv, zzl zzl, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqp = zzl;
        this.zzaqn = zzh;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzc(this.zzaqp, this.zzaqn);
    }
}
