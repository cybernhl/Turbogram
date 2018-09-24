package com.google.android.gms.internal.vision;

import java.io.IOException;

final class zzfh extends zzff<zzfg, zzfg> {
    zzfh() {
    }

    private static void zza(Object obj, zzfg zzfg) {
        ((zzcr) obj).zzkr = zzfg;
    }

    final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzfg) obj).zzb(i << 3, Long.valueOf(j));
    }

    final /* synthetic */ void zza(Object obj, int i, zzbo zzbo) {
        ((zzfg) obj).zzb((i << 3) | 2, zzbo);
    }

    final /* synthetic */ void zza(Object obj, zzfz zzfz) throws IOException {
        ((zzfg) obj).zzb(zzfz);
    }

    final /* synthetic */ void zzc(Object obj, zzfz zzfz) throws IOException {
        ((zzfg) obj).zza(zzfz);
    }

    final void zzd(Object obj) {
        ((zzcr) obj).zzkr.zzao();
    }

    final /* synthetic */ Object zzdt() {
        return zzfg.zzdv();
    }

    final /* synthetic */ void zze(Object obj, Object obj2) {
        zza(obj, (zzfg) obj2);
    }

    final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzfg) obj2);
    }

    final /* synthetic */ Object zzg(Object obj, Object obj2) {
        zzfg zzfg = (zzfg) obj;
        zzfg zzfg2 = (zzfg) obj2;
        return zzfg2.equals(zzfg.zzdu()) ? zzfg : zzfg.zza(zzfg, zzfg2);
    }

    final /* synthetic */ int zzn(Object obj) {
        return ((zzfg) obj).zzbl();
    }

    final /* synthetic */ Object zzr(Object obj) {
        return ((zzcr) obj).zzkr;
    }

    final /* synthetic */ int zzs(Object obj) {
        return ((zzfg) obj).zzdw();
    }
}
