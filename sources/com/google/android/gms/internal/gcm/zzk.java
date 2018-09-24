package com.google.android.gms.internal.gcm;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

final class zzk extends zzg {
    zzk() {
    }

    public final void zzd(Throwable th, Throwable th2) {
        ThrowableExtension.addSuppressed(th, th2);
    }
}
