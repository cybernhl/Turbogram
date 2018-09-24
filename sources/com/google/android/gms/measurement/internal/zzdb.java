package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class zzdb implements Runnable {
    private final /* synthetic */ zzcs zzarc;
    private final /* synthetic */ ConditionalUserProperty zzarj;

    zzdb(zzcs zzcs, ConditionalUserProperty conditionalUserProperty) {
        this.zzarc = zzcs;
        this.zzarj = conditionalUserProperty;
    }

    public final void run() {
        this.zzarc.zzc(this.zzarj);
    }
}
