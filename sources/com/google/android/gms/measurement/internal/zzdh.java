package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdh implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdh(zzcs zzcs, AtomicReference atomicReference) {
        this.zzarc = zzcs;
        this.zzarb = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(Double.valueOf(this.zzarc.zzgq().zzc(this.zzarc.zzgf().zzal(), zzaf.zzako)));
                this.zzarb.notify();
            } catch (Throwable th) {
                this.zzarb.notify();
            }
        }
    }
}
