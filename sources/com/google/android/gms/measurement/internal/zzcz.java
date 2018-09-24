package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcz implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ long zzari;

    zzcz(zzcs zzcs, long j) {
        this.zzarc = zzcs;
        this.zzari = j;
    }

    public final void run() {
        boolean z = true;
        zzco zzco = this.zzarc;
        long j = this.zzari;
        zzco.zzaf();
        zzco.zzgb();
        zzco.zzcl();
        zzco.zzgo().zzjk().zzbx("Resetting analytics data (FE)");
        zzco.zzgj().zzlj();
        if (zzco.zzgq().zzbe(zzco.zzgf().zzal())) {
            zzco.zzgp().zzanj.set(j);
        }
        boolean isEnabled = zzco.zzadj.isEnabled();
        if (!zzco.zzgq().zzhu()) {
            zzco.zzgp().zzi(!isEnabled);
        }
        zzco.zzgg().resetAnalyticsData();
        if (isEnabled) {
            z = false;
        }
        zzco.zzara = z;
        if (this.zzarc.zzgq().zza(zzaf.zzalk)) {
            this.zzarc.zzgg().zza(new AtomicReference());
        }
    }
}
