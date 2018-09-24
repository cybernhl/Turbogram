package com.google.android.gms.measurement.internal;

final class zzdi implements Runnable {
    private final /* synthetic */ boolean zzaes;
    private final /* synthetic */ zzcs zzarc;

    zzdi(zzcs zzcs, boolean z) {
        this.zzarc = zzcs;
        this.zzaes = z;
    }

    public final void run() {
        this.zzarc.zzk(this.zzaes);
    }
}
