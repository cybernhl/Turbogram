package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzxl {
    private static final Class<?> zzcbw = zzxu();
    private static final zzyb<?, ?> zzcbx = zzx(false);
    private static final zzyb<?, ?> zzcby = zzx(true);
    private static final zzyb<?, ?> zzcbz = new zzyd();

    public static void zzj(Class<?> cls) {
        if (!zzvm.class.isAssignableFrom(cls) && zzcbw != null && !zzcbw.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zza(i, (List) list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzb(i, (List) list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzyw zzyw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzyw zzyw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zza(i, (List) list);
        }
    }

    public static void zzb(int i, List<zzud> list, zzyw zzyw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzb(i, (List) list);
        }
    }

    public static void zza(int i, List<?> list, zzyw zzyw, zzxj zzxj) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zza(i, (List) list, zzxj);
        }
    }

    public static void zzb(int i, List<?> list, zzyw zzyw, zzxj zzxj) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzyw.zzb(i, (List) list, zzxj);
        }
    }

    static int zzx(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzwh) {
            zzwh zzwh = (zzwh) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzay(zzwh.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzay(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzx((List) list) + (list.size() * zzut.zzbb(i));
    }

    static int zzy(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzwh) {
            zzwh zzwh = (zzwh) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzaz(zzwh.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzaz(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzy(list);
    }

    static int zzz(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzwh) {
            zzwh zzwh = (zzwh) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzba(zzwh.getLong(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzba(((Long) list.get(i3)).longValue());
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzz(list);
    }

    static int zzaa(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzvn) {
            zzvn zzvn = (zzvn) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzbh(zzvn.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzbh(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzaa(list);
    }

    static int zzab(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzvn) {
            zzvn zzvn = (zzvn) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzbc(zzvn.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzbc(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzab(list);
    }

    static int zzac(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzvn) {
            zzvn zzvn = (zzvn) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzbd(zzvn.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzbd(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzac(list);
    }

    static int zzad(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i;
        if (list instanceof zzvn) {
            zzvn zzvn = (zzvn) list;
            int i2 = 0;
            for (i = 0; i < size; i++) {
                i2 += zzut.zzbe(zzvn.getInt(i));
            }
            return i2;
        }
        i = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i += zzut.zzbe(((Integer) list.get(i3)).intValue());
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzut.zzbb(i)) + zzad(list);
    }

    static int zzae(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzut.zzk(i, 0) * size;
    }

    static int zzaf(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzg(i, 0);
    }

    static int zzag(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzut.zzbb(i) * size;
        int i2;
        Object raw;
        int zzb;
        if (list instanceof zzwc) {
            zzwc zzwc = (zzwc) list;
            i2 = 0;
            while (i2 < size) {
                raw = zzwc.getRaw(i2);
                if (raw instanceof zzud) {
                    zzb = zzut.zzb((zzud) raw) + zzbb;
                } else {
                    zzb = zzut.zzfx((String) raw) + zzbb;
                }
                i2++;
                zzbb = zzb;
            }
            return zzbb;
        }
        i2 = 0;
        while (i2 < size) {
            raw = list.get(i2);
            if (raw instanceof zzud) {
                zzb = zzut.zzb((zzud) raw) + zzbb;
            } else {
                zzb = zzut.zzfx((String) raw) + zzbb;
            }
            i2++;
            zzbb = zzb;
        }
        return zzbb;
    }

    static int zzc(int i, Object obj, zzxj zzxj) {
        if (obj instanceof zzwa) {
            return zzut.zza(i, (zzwa) obj);
        }
        return zzut.zzb(i, (zzwt) obj, zzxj);
    }

    static int zzc(int i, List<?> list, zzxj zzxj) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzut.zzbb(i) * size;
        int i2 = 0;
        while (i2 < size) {
            int zza;
            Object obj = list.get(i2);
            if (obj instanceof zzwa) {
                zza = zzut.zza((zzwa) obj) + zzbb;
            } else {
                zza = zzut.zzb((zzwt) obj, zzxj) + zzbb;
            }
            i2++;
            zzbb = zza;
        }
        return zzbb;
    }

    static int zzd(int i, List<zzud> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzut.zzbb(i) * size;
        for (size = 0; size < list.size(); size++) {
            zzbb += zzut.zzb((zzud) list.get(size));
        }
        return zzbb;
    }

    static int zzd(int i, List<zzwt> list, zzxj zzxj) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzut.zzc(i, (zzwt) list.get(i3), zzxj);
        }
        return i2;
    }

    public static zzyb<?, ?> zzxr() {
        return zzcbx;
    }

    public static zzyb<?, ?> zzxs() {
        return zzcby;
    }

    public static zzyb<?, ?> zzxt() {
        return zzcbz;
    }

    private static zzyb<?, ?> zzx(boolean z) {
        try {
            Class zzxv = zzxv();
            if (zzxv == null) {
                return null;
            }
            return (zzyb) zzxv.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzxu() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzxv() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzwo zzwo, T t, T t2, long j) {
        zzyh.zza((Object) t, j, zzwo.zzc(zzyh.zzp(t, j), zzyh.zzp(t2, j)));
    }

    static <T, FT extends zzvf<FT>> void zza(zzva<FT> zzva, T t, T t2) {
        zzvd zzs = zzva.zzs(t2);
        if (!zzs.isEmpty()) {
            zzva.zzt(t).zza(zzs);
        }
    }

    static <T, UT, UB> void zza(zzyb<UT, UB> zzyb, T t, T t2) {
        zzyb.zzf(t, zzyb.zzh(zzyb.zzah(t), zzyb.zzah(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzvr zzvr, UB ub, zzyb<UT, UB> zzyb) {
        if (zzvr == null) {
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
                if (zzvr.zzb(intValue)) {
                    if (i2 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    intValue = i3 + 1;
                } else {
                    ub2 = zza(i, intValue, (Object) ub2, (zzyb) zzyb);
                    intValue = i3;
                }
                i2++;
                i3 = intValue;
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                if (!zzvr.zzb(intValue)) {
                    ub = zza(i, intValue, (Object) ub, (zzyb) zzyb);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzyb<UT, UB> zzyb) {
        Object zzye;
        if (ub == null) {
            zzye = zzyb.zzye();
        }
        zzyb.zza(zzye, i, (long) i2);
        return zzye;
    }
}
