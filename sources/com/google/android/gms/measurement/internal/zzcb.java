package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcb implements Callable<List<zzfj>> {
    private final /* synthetic */ String zzaeh;
    private final /* synthetic */ String zzaeo;
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcb(zzbv zzbv, zzh zzh, String str, String str2) {
        this.zzaqo = zzbv;
        this.zzaqn = zzh;
        this.zzaeh = str;
        this.zzaeo = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzb(this.zzaqn.packageName, this.zzaeh, this.zzaeo);
    }
}
