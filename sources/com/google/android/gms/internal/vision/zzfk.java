package com.google.android.gms.internal.vision;

import java.util.Iterator;

final class zzfk implements Iterator<String> {
    private final /* synthetic */ zzfi zzoy;
    private Iterator<String> zzoz = this.zzoy.zzov.iterator();

    zzfk(zzfi zzfi) {
        this.zzoy = zzfi;
    }

    public final boolean hasNext() {
        return this.zzoz.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzoz.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
