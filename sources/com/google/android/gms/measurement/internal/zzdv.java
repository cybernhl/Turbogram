package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdv implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzdr zzasg;

    zzdv(zzdr zzdr, zzh zzh) {
        this.zzasg = zzdr;
        this.zzaqn = zzh;
    }

    public final void run() {
        zzag zzd = this.zzasg.zzasa;
        if (zzd == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzd.zza(this.zzaqn);
            this.zzasg.zza(zzd, null, this.zzaqn);
            this.zzasg.zzcy();
        } catch (RemoteException e) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send app launch to the service", e);
        }
    }
}
