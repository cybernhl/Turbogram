package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzsy {
    private final ConcurrentHashMap<zzsz, List<Throwable>> zzbrw = new ConcurrentHashMap(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzbrx = new ReferenceQueue();

    zzsy() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Object poll = this.zzbrx.poll();
        while (poll != null) {
            this.zzbrw.remove(poll);
            poll = this.zzbrx.poll();
        }
        return (List) this.zzbrw.get(new zzsz(th, null));
    }
}
