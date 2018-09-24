package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzsz extends WeakReference<Throwable> {
    private final int zzbry;

    public zzsz(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, null);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzbry = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzbry;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzsz zzsz = (zzsz) obj;
        if (this.zzbry == zzsz.zzbry && get() == zzsz.get()) {
            return true;
        }
        return false;
    }
}
