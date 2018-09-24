package com.google.android.gms.internal.config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public final class zzap implements FirebaseRemoteConfigInfo {
    private long zzay;
    private int zzaz;
    private FirebaseRemoteConfigSettings zzba;

    public final FirebaseRemoteConfigSettings getConfigSettings() {
        return this.zzba;
    }

    public final long getFetchTimeMillis() {
        return this.zzay;
    }

    public final int getLastFetchStatus() {
        return this.zzaz;
    }

    public final void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzba = firebaseRemoteConfigSettings;
    }

    public final void zzb(long j) {
        this.zzay = j;
    }

    public final void zzf(int i) {
        this.zzaz = i;
    }
}
