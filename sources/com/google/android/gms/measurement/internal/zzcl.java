package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcl implements Callable<List<zzfj>> {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    zzcl(zzbv zzbv, zzh zzh) {
        this.zzaqo = zzbv;
        this.zzaqn = zzh;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzaqo.zzamz.zzly();
        return this.zzaqo.zzamz.zzjq().zzbk(this.zzaqn.packageName);
    }
}
