package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzwp implements zzwo {
    zzwp() {
    }

    public final Map<?, ?> zzy(Object obj) {
        return (zzwn) obj;
    }

    public final zzwm<?, ?> zzad(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzz(Object obj) {
        return (zzwn) obj;
    }

    public final boolean zzaa(Object obj) {
        return !((zzwn) obj).isMutable();
    }

    public final Object zzab(Object obj) {
        ((zzwn) obj).zzsm();
        return obj;
    }

    public final Object zzac(Object obj) {
        return zzwn.zzxa().zzxb();
    }

    public final Object zzc(Object obj, Object obj2) {
        obj = (zzwn) obj;
        zzwn zzwn = (zzwn) obj2;
        if (!zzwn.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzxb();
            }
            obj.zza(zzwn);
        }
        return obj;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzwn zzwn = (zzwn) obj;
        if (!zzwn.isEmpty()) {
            Iterator it = zzwn.entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }
}
