package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzbp implements Iterator {
    private final int limit = this.zzgw.size();
    private int position = 0;
    private final /* synthetic */ zzbo zzgw;

    zzbp(zzbo zzbo) {
        this.zzgw = zzbo;
    }

    private final byte nextByte() {
        try {
            zzbo zzbo = this.zzgw;
            int i = this.position;
            this.position = i + 1;
            return zzbo.zzl(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
