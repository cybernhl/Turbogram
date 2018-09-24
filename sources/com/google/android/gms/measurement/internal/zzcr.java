package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzcr {
    boolean zzadv = true;
    String zzadx;
    String zzapm;
    String zzapn;
    Boolean zzaqg;
    final Context zzri;

    @VisibleForTesting
    public zzcr(Context context, zzak zzak) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzri = applicationContext;
        if (zzak != null) {
            this.zzadx = zzak.zzadx;
            this.zzapm = zzak.origin;
            this.zzapn = zzak.zzadw;
            this.zzadv = zzak.zzadv;
            if (zzak.zzady != null) {
                this.zzaqg = Boolean.valueOf(zzak.zzady.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
