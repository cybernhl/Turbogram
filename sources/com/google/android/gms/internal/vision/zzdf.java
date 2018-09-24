package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdf extends zzbi<String> implements zzdg, RandomAccess {
    private static final zzdf zzml;
    private static final zzdg zzmm = zzml;
    private final List<Object> zzmn;

    static {
        zzbi zzdf = new zzdf();
        zzml = zzdf;
        zzdf.zzao();
    }

    public zzdf() {
        this(10);
    }

    public zzdf(int i) {
        this(new ArrayList(i));
    }

    private zzdf(ArrayList<Object> arrayList) {
        this.zzmn = arrayList;
    }

    private static String zzf(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbo ? ((zzbo) obj).zzas() : zzct.zzg((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        zzap();
        this.zzmn.add(i, str);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        Collection zzck;
        zzap();
        if (collection instanceof zzdg) {
            zzck = ((zzdg) collection).zzck();
        }
        boolean addAll = this.zzmn.addAll(i, zzck);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        zzap();
        this.zzmn.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzmn.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        String zzas;
        if (obj instanceof zzbo) {
            zzbo zzbo = (zzbo) obj;
            zzas = zzbo.zzas();
            if (zzbo.zzat()) {
                this.zzmn.set(i, zzas);
            }
            return zzas;
        }
        byte[] bArr = (byte[]) obj;
        zzas = zzct.zzg(bArr);
        if (zzct.zzf(bArr)) {
            this.zzmn.set(i, zzas);
        }
        return zzas;
    }

    public final Object getRaw(int i) {
        return this.zzmn.get(i);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        zzap();
        Object remove = this.zzmn.remove(i);
        this.modCount++;
        return zzf(remove);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        zzap();
        return zzf(this.zzmn.set(i, str));
    }

    public final int size() {
        return this.zzmn.size();
    }

    public final /* bridge */ /* synthetic */ boolean zzan() {
        return super.zzan();
    }

    public final void zzc(zzbo zzbo) {
        zzap();
        this.zzmn.add(zzbo);
        this.modCount++;
    }

    public final List<?> zzck() {
        return Collections.unmodifiableList(this.zzmn);
    }

    public final zzdg zzcl() {
        return zzan() ? new zzfi(this) : this;
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzmn);
        return new zzdf(arrayList);
    }
}
