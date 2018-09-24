package com.google.android.gms.internal.stable;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;

public final class zzk {
    private static final zzl zzahg;
    private static final int zzahh;

    static final class zza extends zzl {
        zza() {
        }

        public final void zza(Throwable th, Throwable th2) {
        }
    }

    static {
        Integer num = null;
        zzl zzo;
        try {
            num = zzdw();
            if (num == null || num.intValue() < 19) {
                zzo = (!Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ? 1 : null) != null ? new zzo() : new zza();
                zzahg = zzo;
                zzahh = num != null ? 1 : num.intValue();
            }
            zzo = new zzp();
            zzahg = zzo;
            if (num != null) {
            }
            zzahh = num != null ? 1 : num.intValue();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            ThrowableExtension.printStackTrace(th, System.err);
            zzo = new zza();
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zzahg.zza(th, th2);
    }

    private static Integer zzdw() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Throwable e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }
}
