package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzej implements Runnable {
    private final /* synthetic */ zzef zzasp;

    zzej(zzef zzef) {
        this.zzasp = zzef;
    }

    public final void run() {
        zzdr zzdr = this.zzasp.zzasg;
        Context context = this.zzasp.zzasg.getContext();
        this.zzasp.zzasg.zzgr();
        zzdr.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
