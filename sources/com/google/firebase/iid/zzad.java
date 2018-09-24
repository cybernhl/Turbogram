package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class zzad implements Callback {
    private final zzac zzbz;

    zzad(zzac zzac) {
        this.zzbz = zzac;
    }

    public final boolean handleMessage(Message message) {
        return this.zzbz.zza(message);
    }
}
