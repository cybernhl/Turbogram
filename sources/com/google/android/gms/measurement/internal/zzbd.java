package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbd {
    private long value;
    private boolean zzanx;
    private final /* synthetic */ zzba zzany;
    private final long zzanz;
    private final String zzoj;

    public zzbd(zzba zzba, String str, long j) {
        this.zzany = zzba;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
        this.zzanz = j;
    }

    @WorkerThread
    public final long get() {
        if (!this.zzanx) {
            this.zzanx = true;
            this.value = this.zzany.zzjr().getLong(this.zzoj, this.zzanz);
        }
        return this.value;
    }

    @WorkerThread
    public final void set(long j) {
        Editor edit = this.zzany.zzjr().edit();
        edit.putLong(this.zzoj, j);
        edit.apply();
        this.value = j;
    }
}
