package com.google.android.gms.measurement.internal;

final class zzed implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzfh zzaqs;
    private final /* synthetic */ zzdr zzasg;
    private final /* synthetic */ boolean zzasj;

    zzed(zzdr zzdr, boolean z, zzfh zzfh, zzh zzh) {
        this.zzasg = zzdr;
        this.zzasj = z;
        this.zzaqs = zzfh;
        this.zzaqn = zzh;
    }

    public final void run() {
        zzag zzd = this.zzasg.zzasa;
        if (zzd == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to set user attribute");
            return;
        }
        this.zzasg.zza(zzd, this.zzasj ? null : this.zzaqs, this.zzaqn);
        this.zzasg.zzcy();
    }
}
