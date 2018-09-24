package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr.zzd;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzcc implements zzfz {
    private final zzca zzgz;

    private zzcc(zzca zzca) {
        this.zzgz = (zzca) zzct.zza((Object) zzca, "output");
    }

    public static zzcc zza(zzca zzca) {
        return zzca.zzhk != null ? zzca.zzhk : new zzcc(zzca);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzgz.zza(i, d);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzgz.zza(i, f);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzgz.zza(i, j);
    }

    public final void zza(int i, zzbo zzbo) throws IOException {
        this.zzgz.zza(i, zzbo);
    }

    public final <K, V> void zza(int i, zzdq<K, V> zzdq, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzgz.zzd(i, 2);
            this.zzgz.zzq(zzdp.zza(zzdq, entry.getKey(), entry.getValue()));
            zzdp.zza(this.zzgz, zzdq, entry.getKey(), entry.getValue());
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbo) {
            this.zzgz.zzb(i, (zzbo) obj);
        } else {
            this.zzgz.zza(i, (zzdx) obj);
        }
    }

    public final void zza(int i, Object obj, zzen zzen) throws IOException {
        this.zzgz.zza(i, (zzdx) obj, zzen);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzgz.zza(i, str);
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzdg) {
            zzdg zzdg = (zzdg) list;
            for (int i3 = 0; i3 < list.size(); i3++) {
                Object raw = zzdg.getRaw(i3);
                if (raw instanceof String) {
                    this.zzgz.zza(i, (String) raw);
                } else {
                    this.zzgz.zza(i, (zzbo) raw);
                }
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzen zzen) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzen);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzu(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzp(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzac(int i) throws IOException {
        this.zzgz.zzd(i, 3);
    }

    public final void zzad(int i) throws IOException {
        this.zzgz.zzd(i, 4);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzgz.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzen zzen) throws IOException {
        zzca zzca = this.zzgz;
        zzdx zzdx = (zzdx) obj;
        zzca.zzd(i, 3);
        zzen.zza(zzdx, zzca.zzhk);
        zzca.zzd(i, 4);
    }

    public final void zzb(int i, List<zzbo> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzgz.zza(i, (zzbo) list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzen zzen) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzen);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzx(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzs(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzh(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zzgz.zzb(i, z);
    }

    public final int zzbc() {
        return zzd.zzlj;
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzgz.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zze(((Long) list.get(i4)).longValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzf(((Long) list.get(i4)).longValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzgz.zze(i, i2);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzh(((Long) list.get(i4)).longValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzgz.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzd(((Float) list.get(i4)).floatValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzc(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, int i2) throws IOException {
        this.zzgz.zzg(i, i2);
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzb(((Double) list.get(i4)).doubleValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, int i2) throws IOException {
        this.zzgz.zzh(i, i2);
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzz(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzp(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzgz.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzb(((Boolean) list.get(i4)).booleanValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zza(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzb(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzgz.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzv(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzy(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzs(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzh(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzi(((Long) list.get(i4)).longValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzw(((Integer) list.get(i4)).intValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzr(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzg(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgz.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzca.zzg(((Long) list.get(i4)).longValue());
            }
            this.zzgz.zzq(i3);
            while (i2 < list.size()) {
                this.zzgz.zzc(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgz.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzgz.zzh(i, i2);
    }

    public final void zzp(int i, int i2) throws IOException {
        this.zzgz.zze(i, i2);
    }
}
