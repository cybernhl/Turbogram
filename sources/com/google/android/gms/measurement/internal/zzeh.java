package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzeh implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzef zzasp;

    zzeh(zzef zzef, ComponentName componentName) {
        this.zzasp = zzef;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzasp.zzasg.onServiceDisconnected(this.val$name);
    }
}
