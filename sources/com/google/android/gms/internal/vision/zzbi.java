package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzbi<E> extends AbstractList<E> implements zzcw<E> {
    private boolean zzgl = true;

    zzbi() {
    }

    public void add(int i, E e) {
        zzap();
        super.add(i, e);
    }

    public boolean add(E e) {
        zzap();
        return super.add(e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzap();
        return super.addAll(i, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzap();
        return super.addAll(collection);
    }

    public void clear() {
        zzap();
        super.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public E remove(int i) {
        zzap();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzap();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzap();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzap();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzap();
        return super.set(i, e);
    }

    public boolean zzan() {
        return this.zzgl;
    }

    public final void zzao() {
        this.zzgl = false;
    }

    protected final void zzap() {
        if (!this.zzgl) {
            throw new UnsupportedOperationException();
        }
    }
}
