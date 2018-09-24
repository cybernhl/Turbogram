package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdd implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdd(zzcs zzcs, AtomicReference atomicReference) {
        this.zzarc = zzcs;
        this.zzarb = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(this.zzarc.zzgq().zzbb(this.zzarc.zzgf().zzal()));
                this.zzarb.notify();
            } catch (Throwable th) {
                this.zzarb.notify();
            }
        }
    }
}
