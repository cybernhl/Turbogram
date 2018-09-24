package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzep {
    private static final Class<?> zzob = zzdj();
    private static final zzff<?, ?> zzoc = zzd(false);
    private static final zzff<?, ?> zzod = zzd(true);
    private static final zzff<?, ?> zzoe = new zzfh();

    static int zza(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzdl) {
            zzdl zzdl = (zzdl) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zze(zzdl.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zze(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzff<UT, UB> zzff) {
        Object zzdt;
        if (ub == null) {
            zzdt = zzff.zzdt();
        }
        zzff.zza(zzdt, i, (long) i2);
        return zzdt;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzcv<?> zzcv, UB ub, zzff<UT, UB> zzff) {
        if (zzcv == null) {
            return ub;
        }
        UB ub2;
        int intValue;
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            int i3 = 0;
            ub2 = ub;
            while (i2 < size) {
                intValue = ((Integer) list.get(i2)).intValue();
                if (zzcv.zzaf(intValue) != null) {
                    if (i2 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    intValue = i3 + 1;
                } else {
                    ub2 = zza(i, intValue, (Object) ub2, (zzff) zzff);
                    intValue = i3;
                }
                i2++;
                i3 = intValue;
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Object zza;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                if (zzcv.zzaf(intValue) == null) {
                    zza = zza(i, intValue, zza, (zzff) zzff);
                    it.remove();
                }
            }
            ub2 = zza;
        }
        return ub2;
    }

    public static void zza(int i, List<String> list, zzfz zzfz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zza(i, (List) list);
        }
    }

    public static void zza(int i, List<?> list, zzfz zzfz, zzen zzen) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zza(i, (List) list, zzen);
        }
    }

    public static void zza(int i, List<Double> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzg(i, list, z);
        }
    }

    static <T, FT extends zzcl<FT>> void zza(zzcg<FT> zzcg, T t, T t2) {
        zzcj zzb = zzcg.zzb(t2);
        if (!zzb.isEmpty()) {
            zzcg.zzc(t).zza(zzb);
        }
    }

    static <T> void zza(zzds zzds, T t, T t2, long j) {
        zzfl.zza((Object) t, j, zzds.zzb(zzfl.zzo(t, j), zzfl.zzo(t2, j)));
    }

    static <T, UT, UB> void zza(zzff<UT, UB> zzff, T t, T t2) {
        zzff.zze(t, zzff.zzg(zzff.zzr(t), zzff.zzr(t2)));
    }

    static int zzb(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzdl) {
            zzdl zzdl = (zzdl) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzf(zzdl.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzf(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    public static void zzb(int i, List<zzbo> list, zzfz zzfz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzb(i, (List) list);
        }
    }

    public static void zzb(int i, List<?> list, zzfz zzfz, zzen zzen) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzb(i, (List) list, zzen);
        }
    }

    public static void zzb(int i, List<Float> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzen zzen) {
        return obj instanceof zzde ? zzca.zza(i, (zzde) obj) : zzca.zzb(i, (zzdx) obj, zzen);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        int i2;
        if (list instanceof zzdg) {
            zzdg zzdg = (zzdg) list;
            i2 = 0;
            while (i2 < size) {
                Object raw = zzdg.getRaw(i2);
                i2++;
                zzt = raw instanceof zzbo ? zzca.zzb((zzbo) raw) + zzt : zzca.zzi((String) raw) + zzt;
            }
            return zzt;
        }
        i2 = 0;
        while (i2 < size) {
            raw = list.get(i2);
            i2++;
            zzt = raw instanceof zzbo ? zzca.zzb((zzbo) raw) + zzt : zzca.zzi((String) raw) + zzt;
        }
        return zzt;
    }

    static int zzc(int i, List<?> list, zzen zzen) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            i2++;
            zzt = obj instanceof zzde ? zzca.zza((zzde) obj) + zzt : zzca.zza((zzdx) obj, zzen) + zzt;
        }
        return zzt;
    }

    static int zzc(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzdl) {
            zzdl zzdl = (zzdl) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzg(zzdl.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzg(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    public static void zzc(int i, List<Long> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzc(i, list, z);
        }
    }

    static int zzd(int i, List<zzbo> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        for (size = 0; size < list.size(); size++) {
            zzt += zzca.zzb((zzbo) list.get(size));
        }
        return zzt;
    }

    static int zzd(int i, List<zzdx> list, zzen zzen) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzca.zzc(i, (zzdx) list.get(i3), zzen);
        }
        return i2;
    }

    static int zzd(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzcs) {
            zzcs zzcs = (zzcs) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzz(zzcs.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzz(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    private static zzff<?, ?> zzd(boolean z) {
        try {
            Class zzdk = zzdk();
            if (zzdk == null) {
                return null;
            }
            return (zzff) zzdk.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    public static void zzd(int i, List<Long> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static zzff<?, ?> zzdg() {
        return zzoc;
    }

    public static zzff<?, ?> zzdh() {
        return zzod;
    }

    public static zzff<?, ?> zzdi() {
        return zzoe;
    }

    private static Class<?> zzdj() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzdk() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzcs) {
            zzcs zzcs = (zzcs) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzu(zzcs.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzu(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    public static void zze(int i, List<Long> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzn(i, list, z);
        }
    }

    static int zzf(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzcs) {
            zzcs zzcs = (zzcs) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzv(zzcs.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzv(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    public static void zzf(int i, List<Long> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        if (!zzcr.class.isAssignableFrom(cls) && zzob != null && !zzob.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzcs) {
            zzcs zzcs = (zzcs) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzca.zzw(zzcs.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzca.zzw(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    public static void zzg(int i, List<Long> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzl(i, list, z);
        }
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static void zzh(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zza(i, (List) list, z);
        }
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static void zzi(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzj(i, list, z);
        }
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    public static void zzj(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzb(i, (List) list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzfz zzfz, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfz.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        return list.size() == 0 ? 0 : zza(list) + (list.size() * zzca.zzt(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zzb(list);
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zzc(list);
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zzd((List) list);
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zze(list);
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zzf((List) list);
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzca.zzt(i)) + zzg(list);
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : zzca.zzl(i, 0) * size;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : size * zzca.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        return size == 0 ? 0 : size * zzca.zzc(i, true);
    }
}
