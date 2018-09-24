package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

@WorkerThread
final class zzaw implements Runnable {
    private final String packageName;
    private final int status;
    private final zzav zzamr;
    private final Throwable zzams;
    private final byte[] zzamt;
    private final Map<String, List<String>> zzamu;

    private zzaw(String str, zzav zzav, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        Preconditions.checkNotNull(zzav);
        this.zzamr = zzav;
        this.status = i;
        this.zzams = th;
        this.zzamt = bArr;
        this.packageName = str;
        this.zzamu = map;
    }

    public final void run() {
        this.zzamr.zza(this.packageName, this.status, this.zzams, this.zzamt, this.zzamu);
    }
}
