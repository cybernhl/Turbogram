package com.google.android.gms.internal.vision;

import java.util.Map.Entry;

final class zzdc<K> implements Entry<K, Object> {
    private Entry<K, zzda> zzmg;

    private zzdc(Entry<K, zzda> entry) {
        this.zzmg = entry;
    }

    public final K getKey() {
        return this.zzmg.getKey();
    }

    public final Object getValue() {
        return ((zzda) this.zzmg.getValue()) == null ? null : zzda.zzci();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzdx) {
            return ((zzda) this.zzmg.getValue()).zzi((zzdx) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzda zzcj() {
        return (zzda) this.zzmg.getValue();
    }
}
