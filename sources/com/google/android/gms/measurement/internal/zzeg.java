package com.google.android.gms.measurement.internal;

final class zzeg implements Runnable {
    private final /* synthetic */ zzag zzaso;
    private final /* synthetic */ zzef zzasp;

    zzeg(zzef zzef, zzag zzag) {
        this.zzasp = zzef;
        this.zzaso = zzag;
    }

    public final void run() {
        synchronized (this.zzasp) {
            this.zzasp.zzasm = false;
            if (!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjl().zzbx("Connected to service");
                this.zzasp.zzasg.zza(this.zzaso);
            }
        }
    }
}
