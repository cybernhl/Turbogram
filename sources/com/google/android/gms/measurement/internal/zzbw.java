package com.google.android.gms.measurement.internal;

final class zzbw implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzbw(zzbv zzbv, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqn = zzh;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zze(this.zzaqn);
    }
}
