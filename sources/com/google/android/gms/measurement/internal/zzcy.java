package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcy implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzcy(zzcs zzcs, AtomicReference atomicReference) {
        this.zzarc = zzcs;
        this.zzarb = atomicReference;
    }

    public final void run() {
        this.zzarc.zzgg().zza(this.zzarb);
    }
}
