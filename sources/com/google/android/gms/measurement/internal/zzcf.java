package com.google.android.gms.measurement.internal;

final class zzcf implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcf(zzbv zzbv, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqn = zzh;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzd(this.zzaqn);
    }
}
