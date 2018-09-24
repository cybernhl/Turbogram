package com.google.android.gms.measurement.internal;

final class zzch implements Runnable {
    private final /* synthetic */ zzbv zzaqo;
    private final /* synthetic */ String zzaqq;
    private final /* synthetic */ zzad zzaqr;

    zzch(zzbv zzbv, zzad zzad, String str) {
        this.zzaqo = zzbv;
        this.zzaqr = zzad;
        this.zzaqq = str;
    }

    public final void run() {
        this.zzaqo.zzamz.zzly();
        this.zzaqo.zzamz.zzc(this.zzaqr, this.zzaqq);
    }
}
