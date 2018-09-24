package com.google.android.gms.internal.vision;

final class zzdo implements zzdw {
    private zzdw[] zzmv;

    zzdo(zzdw... zzdwArr) {
        this.zzmv = zzdwArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzdw zza : this.zzmv) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzdv zzb(Class<?> cls) {
        for (zzdw zzdw : this.zzmv) {
            if (zzdw.zza(cls)) {
                return zzdw.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
