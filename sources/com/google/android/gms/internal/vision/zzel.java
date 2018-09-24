package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.List;

final class zzel<E> extends zzbi<E> {
    private static final zzel<Object> zzoa;
    private final List<E> zzmn;

    static {
        zzbi zzel = new zzel();
        zzoa = zzel;
        zzel.zzao();
    }

    zzel() {
        this(new ArrayList(10));
    }

    private zzel(List<E> list) {
        this.zzmn = list;
    }

    public static <E> zzel<E> zzdd() {
        return zzoa;
    }

    public final void add(int i, E e) {
        zzap();
        this.zzmn.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzmn.get(i);
    }

    public final E remove(int i) {
        zzap();
        E remove = this.zzmn.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzap();
        E e2 = this.zzmn.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzmn.size();
    }

    public final /* synthetic */ zzcw zzk(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        List arrayList = new ArrayList(i);
        arrayList.addAll(this.zzmn);
        return new zzel(arrayList);
    }
}
