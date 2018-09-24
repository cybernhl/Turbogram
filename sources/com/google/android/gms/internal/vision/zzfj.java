package com.google.android.gms.internal.vision;

import java.util.ListIterator;

final class zzfj implements ListIterator<String> {
    private ListIterator<String> zzow = this.zzoy.zzov.listIterator(this.zzox);
    private final /* synthetic */ int zzox;
    private final /* synthetic */ zzfi zzoy;

    zzfj(zzfi zzfi, int i) {
        this.zzoy = zzfi;
        this.zzox = i;
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzow.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzow.hasPrevious();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzow.next();
    }

    public final int nextIndex() {
        return this.zzow.nextIndex();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzow.previous();
    }

    public final int previousIndex() {
        return this.zzow.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
