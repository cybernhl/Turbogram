package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzdr<K, V> extends LinkedHashMap<K, V> {
    private static final zzdr zzmz;
    private boolean zzgl = true;

    static {
        zzdr zzdr = new zzdr();
        zzmz = zzdr;
        zzdr.zzgl = false;
    }

    private zzdr() {
    }

    private zzdr(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzdr<K, V> zzcp() {
        return zzmz;
    }

    private final void zzcr() {
        if (!this.zzgl) {
            throw new UnsupportedOperationException();
        }
    }

    private static int zzg(Object obj) {
        if (obj instanceof byte[]) {
            return zzct.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzcu)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        zzcr();
        super.clear();
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            Object obj2;
            obj = (Map) obj;
            if (this != obj) {
                if (size() == obj.size()) {
                    for (Entry entry : entrySet()) {
                        if (!obj.containsKey(entry.getKey())) {
                            obj2 = null;
                            break;
                        }
                        boolean equals;
                        Object value = entry.getValue();
                        Object obj3 = obj.get(entry.getKey());
                        if ((value instanceof byte[]) && (obj3 instanceof byte[])) {
                            equals = Arrays.equals((byte[]) value, (byte[]) obj3);
                            continue;
                        } else {
                            equals = value.equals(obj3);
                            continue;
                        }
                        if (!equals) {
                            obj2 = null;
                            break;
                        }
                    }
                }
                obj2 = null;
                if (obj2 != null) {
                    return true;
                }
            }
            int i = 1;
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        for (Entry entry : entrySet()) {
            i = (zzg(entry.getValue()) ^ zzg(entry.getKey())) + i;
        }
        return i;
    }

    public final boolean isMutable() {
        return this.zzgl;
    }

    public final V put(K k, V v) {
        zzcr();
        zzct.checkNotNull(k);
        zzct.checkNotNull(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzcr();
        for (Object next : map.keySet()) {
            zzct.checkNotNull(next);
            zzct.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzcr();
        return super.remove(obj);
    }

    public final void zza(zzdr<K, V> zzdr) {
        zzcr();
        if (!zzdr.isEmpty()) {
            putAll(zzdr);
        }
    }

    public final void zzao() {
        this.zzgl = false;
    }

    public final zzdr<K, V> zzcq() {
        return isEmpty() ? new zzdr() : new zzdr(this);
    }
}
