package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfi extends AbstractList<String> implements zzdg, RandomAccess {
    private final zzdg zzov;

    public zzfi(zzdg zzdg) {
        this.zzov = zzdg;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzov.get(i);
    }

    public final Object getRaw(int i) {
        return this.zzov.getRaw(i);
    }

    public final Iterator<String> iterator() {
        return new zzfk(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzfj(this, i);
    }

    public final int size() {
        return this.zzov.size();
    }

    public final void zzc(zzbo zzbo) {
        throw new UnsupportedOperationException();
    }

    public final List<?> zzck() {
        return this.zzov.zzck();
    }

    public final zzdg zzcl() {
        return this;
    }
}
