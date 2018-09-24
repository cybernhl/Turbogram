package com.google.android.gms.measurement.internal;

final class zzdl implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ long zzark;

    zzdl(zzcs zzcs, long j) {
        this.zzarc = zzcs;
        this.zzark = j;
    }

    public final void run() {
        this.zzarc.zzgp().zzanq.set(this.zzark);
        this.zzarc.zzgo().zzjk().zzg("Session timeout duration set", Long.valueOf(this.zzark));
    }
}
