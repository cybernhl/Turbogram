package com.google.android.gms.internal.ads;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.util.List;

final class zzazv extends zzazs {
    private final zzazt zzdpb = new zzazt();

    zzazv() {
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        ThrowableExtension.printStackTrace(th, printWriter);
        List<Throwable> zza = this.zzdpb.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printWriter.print("Suppressed: ");
                    ThrowableExtension.printStackTrace(th2, printWriter);
                }
            }
        }
    }
}
