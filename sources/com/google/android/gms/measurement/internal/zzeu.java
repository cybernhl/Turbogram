package com.google.android.gms.measurement.internal;

final class zzeu implements Runnable {
    private final /* synthetic */ long zzafv;
    private final /* synthetic */ zzeq zzasz;

    zzeu(zzeq zzeq, long j) {
        this.zzasz = zzeq;
        this.zzafv = j;
    }

    public final void run() {
        this.zzasz.zzan(this.zzafv);
    }
}
