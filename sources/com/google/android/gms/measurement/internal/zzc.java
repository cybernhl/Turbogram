package com.google.android.gms.measurement.internal;

final class zzc implements Runnable {
    private final /* synthetic */ String zzaet;
    private final /* synthetic */ long zzaft;
    private final /* synthetic */ zza zzafu;

    zzc(zza zza, String str, long j) {
        this.zzafu = zza;
        this.zzaet = str;
        this.zzaft = j;
    }

    public final void run() {
        this.zzafu.zzb(this.zzaet, this.zzaft);
    }
}
