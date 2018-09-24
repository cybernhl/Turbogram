package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzch extends zzcg<Object> {
    zzch() {
    }

    final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(zzfz zzfz, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(Object obj, zzcj<Object> zzcj) {
        ((zzc) obj).zzkx = zzcj;
    }

    final zzcj<Object> zzb(Object obj) {
        return ((zzc) obj).zzkx;
    }

    final zzcj<Object> zzc(Object obj) {
        zzcj<Object> zzb = zzb(obj);
        if (!zzb.isImmutable()) {
            return zzb;
        }
        zzcj zzcj = (zzcj) zzb.clone();
        zza(obj, zzcj);
        return zzcj;
    }

    final void zzd(Object obj) {
        zzb(obj).zzao();
    }

    final boolean zze(zzdx zzdx) {
        return zzdx instanceof zzc;
    }
}
