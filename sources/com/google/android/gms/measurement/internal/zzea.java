package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzea implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzdr zzasg;
    private final /* synthetic */ boolean zzasi;
    private final /* synthetic */ boolean zzasj;
    private final /* synthetic */ zzl zzask;
    private final /* synthetic */ zzl zzasl;

    zzea(zzdr zzdr, boolean z, boolean z2, zzl zzl, zzh zzh, zzl zzl2) {
        this.zzasg = zzdr;
        this.zzasi = z;
        this.zzasj = z2;
        this.zzask = zzl;
        this.zzaqn = zzh;
        this.zzasl = zzl2;
    }

    public final void run() {
        zzag zzd = this.zzasg.zzasa;
        if (zzd == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zzasi) {
            this.zzasg.zza(zzd, this.zzasj ? null : this.zzask, this.zzaqn);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzasl.packageName)) {
                    zzd.zza(this.zzask, this.zzaqn);
                } else {
                    zzd.zzb(this.zzask);
                }
            } catch (RemoteException e) {
                this.zzasg.zzgo().zzjd().zzg("Failed to send conditional user property to the service", e);
            }
        }
        this.zzasg.zzcy();
    }
}
