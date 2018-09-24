package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdg implements Runnable {
    private final /* synthetic */ AtomicReference zzarb;
    private final /* synthetic */ zzcs zzarc;

    zzdg(zzcs zzcs, AtomicReference atomicReference) {
        this.zzarc = zzcs;
        this.zzarb = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarb) {
            try {
                this.zzarb.set(Integer.valueOf(this.zzarc.zzgq().zzb(this.zzarc.zzgf().zzal(), zzaf.zzakn)));
                this.zzarb.notify();
            } catch (Throwable th) {
                this.zzarb.notify();
            }
        }
    }
}
