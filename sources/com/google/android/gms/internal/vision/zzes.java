package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzes implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzol;
    private final /* synthetic */ zzeq zzom;

    private zzes(zzeq zzeq) {
        this.zzom = zzeq;
        this.pos = this.zzom.zzog.size();
    }

    private final Iterator<Entry<K, V>> zzdq() {
        if (this.zzol == null) {
            this.zzol = this.zzom.zzoj.entrySet().iterator();
        }
        return this.zzol;
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzom.zzog.size()) || zzdq().hasNext();
    }

    public final /* synthetic */ Object next() {
        if (zzdq().hasNext()) {
            return (Entry) zzdq().next();
        }
        List zzb = this.zzom.zzog;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
