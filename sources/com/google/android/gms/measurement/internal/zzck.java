package com.google.android.gms.measurement.internal;

final class zzck implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ zzfh zzaqs;

    zzck(zzbv zzbv, zzfh zzfh, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqs = zzfh;
        this.zzaqn = zzh;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzb(this.zzaqs, this.zzaqn);
    }
}
