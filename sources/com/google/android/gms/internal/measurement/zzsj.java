package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzsj extends ContentObserver {
    private final /* synthetic */ zzsi zzbqx;

    zzsj(zzsi zzsi, Handler handler) {
        this.zzbqx = zzsi;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzbqx.zzta();
        this.zzbqx.zztc();
    }
}
