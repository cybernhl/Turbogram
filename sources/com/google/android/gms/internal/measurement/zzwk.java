package com.google.android.gms.internal.measurement;

final class zzwk implements zzws {
    private zzws[] zzcaq;

    zzwk(zzws... zzwsArr) {
        this.zzcaq = zzwsArr;
    }

    public final boolean zze(Class<?> cls) {
        for (zzws zze : this.zzcaq) {
            if (zze.zze(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzwr zzf(Class<?> cls) {
        for (zzws zzws : this.zzcaq) {
            if (zzws.zze(cls)) {
                return zzws.zzf(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
