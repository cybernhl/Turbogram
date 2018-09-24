package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzdd<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzmh;

    public zzdd(Iterator<Entry<K, Object>> it) {
        this.zzmh = it;
    }

    public final boolean hasNext() {
        return this.zzmh.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzmh.next();
        return entry.getValue() instanceof zzda ? new zzdc(entry) : entry;
    }

    public final void remove() {
        this.zzmh.remove();
    }
}
