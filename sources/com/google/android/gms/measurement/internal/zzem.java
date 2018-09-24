package com.google.android.gms.measurement.internal;

import android.content.Intent;

final /* synthetic */ class zzem implements Runnable {
    private final int zzacb;
    private final zzel zzasr;
    private final zzap zzass;
    private final Intent zzast;

    zzem(zzel zzel, int i, zzap zzap, Intent intent) {
        this.zzasr = zzel;
        this.zzacb = i;
        this.zzass = zzap;
        this.zzast = intent;
    }

    public final void run() {
        this.zzasr.zza(this.zzacb, this.zzass, this.zzast);
    }
}
