package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzdt implements zzds {
    zzdt() {
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzdr zzdr = (zzdr) obj;
        if (!zzdr.isEmpty()) {
            Iterator it = zzdr.entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }

    public final Object zzb(Object obj, Object obj2) {
        obj = (zzdr) obj;
        zzdr zzdr = (zzdr) obj2;
        if (!zzdr.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzcq();
            }
            obj.zza(zzdr);
        }
        return obj;
    }

    public final Map<?, ?> zzh(Object obj) {
        return (zzdr) obj;
    }

    public final Map<?, ?> zzi(Object obj) {
        return (zzdr) obj;
    }

    public final boolean zzj(Object obj) {
        return !((zzdr) obj).isMutable();
    }

    public final Object zzk(Object obj) {
        ((zzdr) obj).zzao();
        return obj;
    }

    public final Object zzl(Object obj) {
        return zzdr.zzcp().zzcq();
    }

    public final zzdq<?, ?> zzm(Object obj) {
        throw new NoSuchMethodError();
    }
}
