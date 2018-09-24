package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcx implements Runnable {
    private final /* synthetic */ boolean zzaev;
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzcx(zzcs zzcs, AtomicReference atomicReference, boolean z) {
        this.zzarc = zzcs;
        this.zzarb = atomicReference;
        this.zzaev = z;
    }

    public final void run() {
        this.zzarc.zzgg().zza(this.zzarb, this.zzaev);
    }
}
