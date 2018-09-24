package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzwl<K, V> {
    static <K, V> void zza(zzut zzut, zzwm<K, V> zzwm, K k, V v) throws IOException {
        zzvd.zza(zzut, zzwm.zzcar, 1, k);
        zzvd.zza(zzut, zzwm.zzcat, 2, v);
    }

    static <K, V> int zza(zzwm<K, V> zzwm, K k, V v) {
        return zzvd.zza(zzwm.zzcar, 1, k) + zzvd.zza(zzwm.zzcat, 2, v);
    }
}
