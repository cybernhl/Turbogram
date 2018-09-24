package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzcf {
    private static volatile boolean zzho = false;
    private static final Class<?> zzhp = zzbf();
    static final zzcf zzhq = new zzcf(true);
    private final Map<Object, Object> zzhr;

    zzcf() {
        this.zzhr = new HashMap();
    }

    private zzcf(boolean z) {
        this.zzhr = Collections.emptyMap();
    }

    private static Class<?> zzbf() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzcf zzbg() {
        return zzce.zzbe();
    }
}
