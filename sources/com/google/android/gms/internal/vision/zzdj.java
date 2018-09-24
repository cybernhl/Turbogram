package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzdj extends zzdh {
    private static final Class<?> zzmq = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzdj() {
        super();
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzfl.zzo(obj, j);
    }

    final void zza(Object obj, long j) {
        Object zzcl;
        List list = (List) zzfl.zzo(obj, j);
        if (list instanceof zzdg) {
            zzcl = ((zzdg) list).zzcl();
        } else if (!zzmq.isAssignableFrom(list.getClass())) {
            zzcl = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzfl.zza(obj, j, zzcl);
    }

    final <E> void zza(Object obj, Object obj2, long j) {
        Collection zzb = zzb(obj2, j);
        int size = zzb.size();
        Object zzb2 = zzb(obj, j);
        if (zzb2.isEmpty()) {
            zzb2 = zzb2 instanceof zzdg ? new zzdf(size) : new ArrayList(size);
            zzfl.zza(obj, j, zzb2);
        } else if (zzmq.isAssignableFrom(zzb2.getClass())) {
            r1 = new ArrayList(size + zzb2.size());
            r1.addAll(zzb2);
            zzfl.zza(obj, j, r1);
            zzb2 = r1;
        } else if (zzb2 instanceof zzfi) {
            r1 = new zzdf(size + zzb2.size());
            r1.addAll((zzfi) zzb2);
            zzfl.zza(obj, j, r1);
            zzb2 = r1;
        }
        int size2 = zzb2.size();
        size = zzb.size();
        if (size2 > 0 && size > 0) {
            zzb2.addAll(zzb);
        }
        if (size2 <= 0) {
            Collection collection = zzb;
        }
        zzfl.zza(obj, j, zzb2);
    }
}
