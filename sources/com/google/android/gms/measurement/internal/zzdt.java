package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdt implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzdr zzasg;

    zzdt(zzdr zzdr, zzh zzh) {
        this.zzasg = zzdr;
        this.zzaqn = zzh;
    }

    public final void run() {
        zzag zzd = this.zzasg.zzasa;
        if (zzd == null) {
            this.zzasg.zzgo().zzjd().zzbx("Failed to reset data on the service; null service");
            return;
        }
        try {
            zzd.zzd(this.zzaqn);
        } catch (RemoteException e) {
            this.zzasg.zzgo().zzjd().zzg("Failed to reset data on the service", e);
        }
        this.zzasg.zzcy();
    }
}
