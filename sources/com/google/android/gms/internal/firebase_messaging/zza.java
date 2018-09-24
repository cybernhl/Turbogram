package com.google.android.gms.internal.firebase_messaging;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;

public final class zza {
    private static final zzb zza;
    private static final int zzb;

    static final class zza extends zzb {
        zza() {
        }

        public final void zza(Throwable th, Throwable th2) {
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }

    private static Integer zza() {
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
        zzb zze;
        try {
            num = zza();
            if (num == null || num.intValue() < 19) {
                if ((!Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ? 1 : null) != null) {
                    zze = new zze();
                } else {
                    zze = new zza();
                }
                zza = zze;
                zzb = num != null ? 1 : num.intValue();
            }
            zze = new zzf();
            zza = zze;
            if (num != null) {
            }
            zzb = num != null ? 1 : num.intValue();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            ThrowableExtension.printStackTrace(th, System.err);
            zze = new zza();
        }
    }
}
