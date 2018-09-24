package com.google.android.gms.internal.measurement;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;

public final class zzsw {
    private static final zzsx zzbrt;
    private static final int zzbru;

    static final class zza extends zzsx {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            ThrowableExtension.printStackTrace(th, printStream);
        }
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzbrt.zza(th, printStream);
    }

    private static Integer zztk() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Throwable e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }

    static {
        Integer num = null;
        zzsx zzta;
        try {
            num = zztk();
            if (num == null || num.intValue() < 19) {
                if ((!Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ? 1 : null) != null) {
                    zzta = new zzta();
                } else {
                    zzta = new zza();
                }
                zzbrt = zzta;
                zzbru = num != null ? 1 : num.intValue();
            }
            zzta = new zztb();
            zzbrt = zzta;
            if (num != null) {
            }
            zzbru = num != null ? 1 : num.intValue();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            ThrowableExtension.printStackTrace(th, System.err);
            zzta = new zza();
        }
    }
}
