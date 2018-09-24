package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzet extends zzez {
    private final /* synthetic */ zzeq zzom;

    private zzet(zzeq zzeq) {
        this.zzom = zzeq;
        super(zzeq);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzes(this.zzom);
    }
}
