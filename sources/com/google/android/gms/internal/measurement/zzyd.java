package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzyd extends zzyb<zzyc, zzyc> {
    zzyd() {
    }

    final boolean zza(zzxi zzxi) {
        return false;
    }

    private static void zza(Object obj, zzyc zzyc) {
        ((zzvm) obj).zzbym = zzyc;
    }

    final void zzu(Object obj) {
        ((zzvm) obj).zzbym.zzsm();
    }

    final /* synthetic */ int zzae(Object obj) {
        return ((zzyc) obj).zzvu();
    }

    final /* synthetic */ int zzaj(Object obj) {
        return ((zzyc) obj).zzyh();
    }

    final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzyc zzyc = (zzyc) obj;
        zzyc zzyc2 = (zzyc) obj2;
        if (zzyc2.equals(zzyc.zzyf())) {
            return zzyc;
        }
        return zzyc.zza(zzyc, zzyc2);
    }

    final /* synthetic */ void zzc(Object obj, zzyw zzyw) throws IOException {
        ((zzyc) obj).zza(zzyw);
    }

    final /* synthetic */ void zza(Object obj, zzyw zzyw) throws IOException {
        ((zzyc) obj).zzb(zzyw);
    }

    final /* synthetic */ void zzg(Object obj, Object obj2) {
        zza(obj, (zzyc) obj2);
    }

    final /* synthetic */ Object zzai(Object obj) {
        zzyc zzyc = ((zzvm) obj).zzbym;
        if (zzyc != zzyc.zzyf()) {
            return zzyc;
        }
        zzyc = zzyc.zzyg();
        zza(obj, zzyc);
        return zzyc;
    }

    final /* synthetic */ Object zzah(Object obj) {
        return ((zzvm) obj).zzbym;
    }

    final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzyc) obj2);
    }

    final /* synthetic */ Object zzab(Object obj) {
        zzyc zzyc = (zzyc) obj;
        zzyc.zzsm();
        return zzyc;
    }

    final /* synthetic */ Object zzye() {
        return zzyc.zzyg();
    }

    final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzyc) obj).zzb((i << 3) | 3, (zzyc) obj2);
    }

    final /* synthetic */ void zza(Object obj, int i, zzud zzud) {
        ((zzyc) obj).zzb((i << 3) | 2, (Object) zzud);
    }

    final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzyc) obj).zzb((i << 3) | 1, Long.valueOf(j));
    }

    final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzyc) obj).zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzyc) obj).zzb(i << 3, Long.valueOf(j));
    }
}
