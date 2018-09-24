package com.google.android.gms.internal.stable;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

final class zzp extends zzl {
    zzp() {
    }

    public final void zza(Throwable th, Throwable th2) {
        ThrowableExtension.addSuppressed(th, th2);
    }
}
