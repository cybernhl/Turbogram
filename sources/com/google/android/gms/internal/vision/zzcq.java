package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr.zzd;

final class zzcq implements zzdw {
    private static final zzcq zzkq = new zzcq();

    private zzcq() {
    }

    public static zzcq zzbs() {
        return zzkq;
    }

    public final boolean zza(Class<?> cls) {
        return zzcr.class.isAssignableFrom(cls);
    }

    public final zzdv zzb(Class<?> cls) {
        if (zzcr.class.isAssignableFrom(cls)) {
            try {
                return (zzdv) zzcr.zzc(cls.asSubclass(zzcr.class)).zza(zzd.zzla, null, null);
            } catch (Throwable e) {
                Throwable th = e;
                String str = "Unable to get message info for ";
                String valueOf = String.valueOf(cls.getName());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            }
        }
        String str2 = "Unsupported message type: ";
        valueOf = String.valueOf(cls.getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
