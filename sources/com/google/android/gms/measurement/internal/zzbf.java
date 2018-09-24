package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbf {
    private String value;
    private boolean zzanx;
    private final /* synthetic */ zzba zzany;
    private final String zzaod = null;
    private final String zzoj;

    public zzbf(zzba zzba, String str, String str2) {
        this.zzany = zzba;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
    }

    @WorkerThread
    public final String zzjz() {
        if (!this.zzanx) {
            this.zzanx = true;
            this.value = this.zzany.zzjr().getString(this.zzoj, null);
        }
        return this.value;
    }

    @WorkerThread
    public final void zzcc(String str) {
        if (!zzfk.zzu(str, this.value)) {
            Editor edit = this.zzany.zzjr().edit();
            edit.putString(this.zzoj, str);
            edit.apply();
            this.value = str;
        }
    }
}
