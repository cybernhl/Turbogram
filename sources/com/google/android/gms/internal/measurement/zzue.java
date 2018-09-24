package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zzue implements zzuj {
    private final int limit = this.zzbuc.size();
    private int position = 0;
    private final /* synthetic */ zzud zzbuc;

    zzue(zzud zzud) {
        this.zzbuc = zzud;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        try {
            zzud zzud = this.zzbuc;
            int i = this.position;
            this.position = i + 1;
            return zzud.zzal(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }
}
