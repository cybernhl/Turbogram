package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzyg implements Iterator<String> {
    private final /* synthetic */ zzye zzcct;
    private Iterator<String> zzccu = this.zzcct.zzccq.iterator();

    zzyg(zzye zzye) {
        this.zzcct = zzye;
    }

    public final boolean hasNext() {
        return this.zzccu.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzccu.next();
    }
}
