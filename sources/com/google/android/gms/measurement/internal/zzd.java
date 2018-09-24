package com.google.android.gms.measurement.internal;

final class zzd implements Runnable {
    private final /* synthetic */ zza zzafu;
    private final /* synthetic */ long zzafv;

    zzd(zza zza, long j) {
        this.zzafu = zza;
        this.zzafv = j;
    }

    public final void run() {
        this.zzafu.zzr(this.zzafv);
    }
}
