package com.google.android.gms.measurement.internal;

final class zzdk implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ long zzark;

    zzdk(zzcs zzcs, long j) {
        this.zzarc = zzcs;
        this.zzark = j;
    }

    public final void run() {
        this.zzarc.zzgp().zzanp.set(this.zzark);
        this.zzarc.zzgo().zzjk().zzg("Minimum session duration set", Long.valueOf(this.zzark));
    }
}
