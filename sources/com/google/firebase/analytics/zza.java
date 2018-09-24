package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zzbse;

    zza(FirebaseAnalytics firebaseAnalytics) {
        this.zzbse = firebaseAnalytics;
    }

    public final /* synthetic */ Object call() throws Exception {
        Object zza = this.zzbse.zzfx();
        if (zza == null) {
            zza = this.zzbse.zzadj.zzge().zzaj(120000);
            if (zza == null) {
                throw new TimeoutException();
            }
            this.zzbse.zzcm(zza);
        }
        return zza;
    }
}
