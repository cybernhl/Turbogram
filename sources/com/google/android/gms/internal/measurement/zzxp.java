package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzxp extends zzxv {
    private final /* synthetic */ zzxm zzcch;

    private zzxp(zzxm zzxm) {
        this.zzcch = zzxm;
        super(zzxm);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzxo(this.zzcch);
    }
}
