package com.google.android.gms.internal.vision;

import java.util.Collection;

final class zzdk extends zzdh {
    private zzdk() {
        super();
    }

    private static <E> zzcw<E> zzc(Object obj, long j) {
        return (zzcw) zzfl.zzo(obj, j);
    }

    final void zza(Object obj, long j) {
        zzc(obj, j).zzao();
    }

    final <E> void zza(Object obj, Object obj2, long j) {
        Object zzc = zzc(obj, j);
        Collection zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzan()) {
                zzc = zzc.zzk(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size <= 0) {
            Collection collection = zzc2;
        }
        zzfl.zza(obj, j, zzc);
    }
}
