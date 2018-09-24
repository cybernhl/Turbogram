package com.google.android.gms.measurement.internal;

final class zzcg implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzad zzaqr;

    zzcg(zzbv zzbv, zzad zzad, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqr = zzad;
        this.zzaqn = zzh;
    }

    public final void run() {
        zzad zzb = this.zzaqo.zzb(this.zzaqr, this.zzaqn);
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzc(zzb, this.zzaqn);
    }
}
