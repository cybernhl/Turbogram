package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbc {
    private boolean value;
    private final boolean zzanw = true;
    private boolean zzanx;
    private final /* synthetic */ zzba zzany;
    private final String zzoj;

    public zzbc(zzba zzba, String str, boolean z) {
        this.zzany = zzba;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
    }

    @WorkerThread
    public final boolean get() {
        if (!this.zzanx) {
            this.zzanx = true;
            this.value = this.zzany.zzjr().getBoolean(this.zzoj, this.zzanw);
        }
        return this.value;
    }

    @WorkerThread
    public final void set(boolean z) {
        Editor edit = this.zzany.zzjr().edit();
        edit.putBoolean(this.zzoj, z);
        edit.apply();
        this.value = z;
    }
}
