package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;

final class zze implements OnEventListener {
    private final /* synthetic */ zzd zzbsv;

    public zze(zzd zzd) {
        this.zzbsv = zzd;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (this.zzbsv.zzbss.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("events", zzc.zzfs(str2));
            this.zzbsv.zzbst.onMessageTriggered(2, bundle2);
        }
    }
}
