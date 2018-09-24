package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzane;
import java.util.Map;

final class zzp implements zzv<Object> {
    zzp() {
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = "Received log message: ";
        String valueOf = String.valueOf((String) map.get("string"));
        zzane.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
