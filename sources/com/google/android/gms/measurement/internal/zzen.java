package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

final /* synthetic */ class zzen implements Runnable {
    private final JobParameters zzace;
    private final zzel zzasr;
    private final zzap zzasu;

    zzen(zzel zzel, zzap zzap, JobParameters jobParameters) {
        this.zzasr = zzel;
        this.zzasu = zzap;
        this.zzace = jobParameters;
    }

    public final void run() {
        this.zzasr.zza(this.zzasu, this.zzace);
    }
}
