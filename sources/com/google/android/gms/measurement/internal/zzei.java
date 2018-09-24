package com.google.android.gms.measurement.internal;

final class zzei implements Runnable {
    private final /* synthetic */ zzef zzasp;
    private final /* synthetic */ zzag zzasq;

    zzei(zzef zzef, zzag zzag) {
        this.zzasp = zzef;
        this.zzasq = zzag;
    }

    public final void run() {
        synchronized (this.zzasp) {
            this.zzasp.zzasm = false;
            if (!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjk().zzbx("Connected to remote service");
                this.zzasp.zzasg.zza(this.zzasq);
            }
        }
    }
}
