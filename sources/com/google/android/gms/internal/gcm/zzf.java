package com.google.android.gms.internal.gcm;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;

public final class zzf {
    private static final zzg zzdc;
    private static final int zzdd;

    static final class zzd extends zzg {
        zzd() {
        }

        public final void zzd(Throwable th, Throwable th2) {
        }
    }

    static {
        Integer num = null;
        zzg zzj;
        try {
            num = zzy();
            if (num == null || num.intValue() < 19) {
                zzj = (!Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ? 1 : null) != null ? new zzj() : new zzd();
                zzdc = zzj;
                zzdd = num != null ? 1 : num.intValue();
            }
            zzj = new zzk();
            zzdc = zzj;
            if (num != null) {
            }
            zzdd = num != null ? 1 : num.intValue();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zzd.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            ThrowableExtension.printStackTrace(th, System.err);
            zzj = new zzd();
        }
    }

    public static void zzd(Throwable th, Throwable th2) {
        zzdc.zzd(th, th2);
    }

    private static Integer zzy() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Throwable e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }
}
