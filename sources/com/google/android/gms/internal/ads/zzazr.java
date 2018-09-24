package com.google.android.gms.internal.ads;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzazr {
    private static final zzazs zzdov;
    private static final int zzdow;

    static final class zza extends zzazs {
        zza() {
        }

        public final void zza(Throwable th, PrintWriter printWriter) {
            ThrowableExtension.printStackTrace(th, printWriter);
        }
    }

    static {
        Integer num = null;
        zzazs zzazv;
        try {
            num = zzaau();
            if (num == null || num.intValue() < 19) {
                zzazv = (!Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ? 1 : null) != null ? new zzazv() : new zza();
                zzdov = zzazv;
                zzdow = num != null ? 1 : num.intValue();
            }
            zzazv = new zzazw();
            zzdov = zzazv;
            if (num != null) {
            }
            zzdow = num != null ? 1 : num.intValue();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            ThrowableExtension.printStackTrace(th, System.err);
            zzazv = new zza();
        }
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzdov.zza(th, printWriter);
    }

    private static Integer zzaau() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Throwable e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }
}
