package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzeq<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzhv;
    private final int zzof;
    private List<zzex> zzog;
    private Map<K, V> zzoh;
    private volatile zzez zzoi;
    private Map<K, V> zzoj;
    private volatile zzet zzok;

    private zzeq(int i) {
        this.zzof = i;
        this.zzog = Collections.emptyList();
        this.zzoh = Collections.emptyMap();
        this.zzoj = Collections.emptyMap();
    }

    private final int zza(K k) {
        int compareTo;
        int i = 0;
        int size = this.zzog.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzex) this.zzog.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i2 = size;
        while (i <= i2) {
            size = (i + i2) / 2;
            compareTo = k.compareTo((Comparable) ((zzex) this.zzog.get(size)).getKey());
            if (compareTo < 0) {
                i2 = size - 1;
            } else if (compareTo <= 0) {
                return size;
            } else {
                i = size + 1;
            }
        }
        return -(i + 1);
    }

    static <FieldDescriptorType extends zzcl<FieldDescriptorType>> zzeq<FieldDescriptorType, Object> zzam(int i) {
        return new zzer(i);
    }

    private final V zzao(int i) {
        zzdo();
        V value = ((zzex) this.zzog.remove(i)).getValue();
        if (!this.zzoh.isEmpty()) {
            Iterator it = zzdp().entrySet().iterator();
            this.zzog.add(new zzex(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final void zzdo() {
        if (this.zzhv) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzdp() {
        zzdo();
        if (this.zzoh.isEmpty() && !(this.zzoh instanceof TreeMap)) {
            this.zzoh = new TreeMap();
            this.zzoj = ((TreeMap) this.zzoh).descendingMap();
        }
        return (SortedMap) this.zzoh;
    }

    public void clear() {
        zzdo();
        if (!this.zzog.isEmpty()) {
            this.zzog.clear();
        }
        if (!this.zzoh.isEmpty()) {
            this.zzoh.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzoh.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzoi == null) {
            this.zzoi = new zzez();
        }
        return this.zzoi;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeq)) {
            return super.equals(obj);
        }
        zzeq zzeq = (zzeq) obj;
        int size = size();
        if (size != zzeq.size()) {
            return false;
        }
        int zzdl = zzdl();
        if (zzdl != zzeq.zzdl()) {
            return entrySet().equals(zzeq.entrySet());
        }
        for (int i = 0; i < zzdl; i++) {
            if (!zzan(i).equals(zzeq.zzan(i))) {
                return false;
            }
        }
        return zzdl != size ? this.zzoh.equals(zzeq.zzoh) : true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? ((zzex) this.zzog.get(zza)).getValue() : this.zzoh.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzdl(); i2++) {
            i += ((zzex) this.zzog.get(i2)).hashCode();
        }
        return this.zzoh.size() > 0 ? this.zzoh.hashCode() + i : i;
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((Comparable) obj, obj2);
    }

    public V remove(Object obj) {
        zzdo();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? zzao(zza) : this.zzoh.isEmpty() ? null : this.zzoh.remove(comparable);
    }

    public int size() {
        return this.zzog.size() + this.zzoh.size();
    }

    public final V zza(K k, V v) {
        zzdo();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzex) this.zzog.get(zza)).setValue(v);
        }
        zzdo();
        if (this.zzog.isEmpty() && !(this.zzog instanceof ArrayList)) {
            this.zzog = new ArrayList(this.zzof);
        }
        int i = -(zza + 1);
        if (i >= this.zzof) {
            return zzdp().put(k, v);
        }
        if (this.zzog.size() == this.zzof) {
            zzex zzex = (zzex) this.zzog.remove(this.zzof - 1);
            zzdp().put((Comparable) zzex.getKey(), zzex.getValue());
        }
        this.zzog.add(i, new zzex(this, k, v));
        return null;
    }

    public final Entry<K, V> zzan(int i) {
        return (Entry) this.zzog.get(i);
    }

    public void zzao() {
        if (!this.zzhv) {
            this.zzoh = this.zzoh.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoh);
            this.zzoj = this.zzoj.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzoj);
            this.zzhv = true;
        }
    }

    public final int zzdl() {
        return this.zzog.size();
    }

    public final Iterable<Entry<K, V>> zzdm() {
        return this.zzoh.isEmpty() ? zzeu.zzdr() : this.zzoh.entrySet();
    }

    final Set<Entry<K, V>> zzdn() {
        if (this.zzok == null) {
            this.zzok = new zzet();
        }
        return this.zzok;
    }
}
