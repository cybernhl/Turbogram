package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzcj<FieldDescriptorType extends zzcl<FieldDescriptorType>> {
    private static final zzcj zzhx = new zzcj(true);
    private final zzeq<FieldDescriptorType, Object> zzhu = zzeq.zzam(16);
    private boolean zzhv;
    private boolean zzhw = false;

    private zzcj() {
    }

    private zzcj(boolean z) {
        zzao();
    }

    static int zza(zzft zzft, int i, Object obj) {
        int i2;
        int zzt = zzca.zzt(i);
        if (zzft == zzft.GROUP) {
            zzct.zzf((zzdx) obj);
            i2 = zzt << 1;
        } else {
            i2 = zzt;
        }
        return i2 + zzb(zzft, obj);
    }

    private final Object zza(FieldDescriptorType fieldDescriptorType) {
        Object obj = this.zzhu.get(fieldDescriptorType);
        return obj instanceof zzda ? zzda.zzci() : obj;
    }

    static void zza(zzca zzca, zzft zzft, int i, Object obj) throws IOException {
        if (zzft == zzft.GROUP) {
            zzct.zzf((zzdx) obj);
            zzdx zzdx = (zzdx) obj;
            zzca.zzd(i, 3);
            zzdx.zzb(zzca);
            zzca.zzd(i, 4);
            return;
        }
        zzca.zzd(i, zzft.zzee());
        switch (zzck.zzhz[zzft.ordinal()]) {
            case 1:
                zzca.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzca.zzc(((Float) obj).floatValue());
                return;
            case 3:
                zzca.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzca.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzca.zzp(((Integer) obj).intValue());
                return;
            case 6:
                zzca.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzca.zzs(((Integer) obj).intValue());
                return;
            case 8:
                zzca.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzdx) obj).zzb(zzca);
                return;
            case 10:
                zzca.zzb((zzdx) obj);
                return;
            case 11:
                if (obj instanceof zzbo) {
                    zzca.zza((zzbo) obj);
                    return;
                } else {
                    zzca.zzh((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbo) {
                    zzca.zza((zzbo) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzca.zzd(bArr, 0, bArr.length);
                return;
            case 13:
                zzca.zzq(((Integer) obj).intValue());
                return;
            case 14:
                zzca.zzs(((Integer) obj).intValue());
                return;
            case 15:
                zzca.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzca.zzr(((Integer) obj).intValue());
                return;
            case 17:
                zzca.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzcu) {
                    zzca.zzp(((zzcu) obj).zzbn());
                    return;
                } else {
                    zzca.zzp(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        Object obj2;
        if (!fieldDescriptorType.zzbq()) {
            zza(fieldDescriptorType.zzbo(), obj);
            obj2 = obj;
        } else if (obj instanceof List) {
            obj2 = new ArrayList();
            obj2.addAll((List) obj);
            ArrayList arrayList = (ArrayList) obj2;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj3 = arrayList.get(i);
                i++;
                zza(fieldDescriptorType.zzbo(), obj3);
            }
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj2 instanceof zzda) {
            this.zzhw = true;
        }
        this.zzhu.zza((Comparable) fieldDescriptorType, obj2);
    }

    private static void zza(zzft zzft, Object obj) {
        boolean z = false;
        zzct.checkNotNull(obj);
        switch (zzck.zzhy[zzft.zzed().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzbo) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzcu)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzdx) || (obj instanceof zzda)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private static int zzb(zzcl<?> zzcl, Object obj) {
        int i = 0;
        zzft zzbo = zzcl.zzbo();
        int zzbn = zzcl.zzbn();
        if (!zzcl.zzbq()) {
            return zza(zzbo, zzbn, obj);
        }
        if (zzcl.zzbr()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzbo, zzb);
            }
            return zzca.zzab(i) + (zzca.zzt(zzbn) + i);
        }
        for (Object zzb2 : (List) obj) {
            i += zza(zzbo, zzbn, zzb2);
        }
        return i;
    }

    private static int zzb(zzft zzft, Object obj) {
        switch (zzck.zzhz[zzft.ordinal()]) {
            case 1:
                return zzca.zzb(((Double) obj).doubleValue());
            case 2:
                return zzca.zzd(((Float) obj).floatValue());
            case 3:
                return zzca.zze(((Long) obj).longValue());
            case 4:
                return zzca.zzf(((Long) obj).longValue());
            case 5:
                return zzca.zzu(((Integer) obj).intValue());
            case 6:
                return zzca.zzh(((Long) obj).longValue());
            case 7:
                return zzca.zzx(((Integer) obj).intValue());
            case 8:
                return zzca.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzca.zzd((zzdx) obj);
            case 10:
                return obj instanceof zzda ? zzca.zza((zzda) obj) : zzca.zzc((zzdx) obj);
            case 11:
                return obj instanceof zzbo ? zzca.zzb((zzbo) obj) : zzca.zzi((String) obj);
            case 12:
                return obj instanceof zzbo ? zzca.zzb((zzbo) obj) : zzca.zze((byte[]) obj);
            case 13:
                return zzca.zzv(((Integer) obj).intValue());
            case 14:
                return zzca.zzy(((Integer) obj).intValue());
            case 15:
                return zzca.zzi(((Long) obj).longValue());
            case 16:
                return zzca.zzw(((Integer) obj).intValue());
            case 17:
                return zzca.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzcu ? zzca.zzz(((zzcu) obj).zzbn()) : zzca.zzz(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Entry<FieldDescriptorType, Object> entry) {
        zzcl zzcl = (zzcl) entry.getKey();
        if (zzcl.zzbp() == zzfy.MESSAGE) {
            if (zzcl.zzbq()) {
                for (zzdx isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof zzdx) {
                if (!((zzdx) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof zzda) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    public static <T extends zzcl<T>> zzcj<T> zzbk() {
        return zzhx;
    }

    private final void zzc(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (zzcl) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzda) {
            value = zzda.zzci();
        }
        Object zza;
        if (comparable.zzbq()) {
            zza = zza((zzcl) comparable);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zze : (List) value) {
                ((List) zza).add(zze(zze));
            }
            this.zzhu.zza(comparable, zza);
        } else if (comparable.zzbp() == zzfy.MESSAGE) {
            zza = zza((zzcl) comparable);
            if (zza == null) {
                this.zzhu.zza(comparable, zze(value));
            } else {
                this.zzhu.zza(comparable, zza instanceof zzee ? comparable.zza((zzee) zza, (zzee) value) : comparable.zza(((zzdx) zza).zzbu(), (zzdx) value).zzca());
            }
        } else {
            this.zzhu.zza(comparable, zze(value));
        }
    }

    private static int zzd(Entry<FieldDescriptorType, Object> entry) {
        zzcl zzcl = (zzcl) entry.getKey();
        Object value = entry.getValue();
        return (zzcl.zzbp() != zzfy.MESSAGE || zzcl.zzbq() || zzcl.zzbr()) ? zzb(zzcl, value) : value instanceof zzda ? zzca.zzb(((zzcl) entry.getKey()).zzbn(), (zzda) value) : zzca.zzb(((zzcl) entry.getKey()).zzbn(), (zzdx) value);
    }

    private static Object zze(Object obj) {
        if (obj instanceof zzee) {
            return ((zzee) obj).zzcy();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        Object obj2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj2, 0, bArr.length);
        return obj2;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzcj zzcj = new zzcj();
        for (int i = 0; i < this.zzhu.zzdl(); i++) {
            Entry zzan = this.zzhu.zzan(i);
            zzcj.zza((zzcl) zzan.getKey(), zzan.getValue());
        }
        for (Entry entry : this.zzhu.zzdm()) {
            zzcj.zza((zzcl) entry.getKey(), entry.getValue());
        }
        zzcj.zzhw = this.zzhw;
        return zzcj;
    }

    final Iterator<Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzhw ? new zzdd(this.zzhu.zzdn().iterator()) : this.zzhu.zzdn().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcj)) {
            return false;
        }
        return this.zzhu.equals(((zzcj) obj).zzhu);
    }

    public final int hashCode() {
        return this.zzhu.hashCode();
    }

    final boolean isEmpty() {
        return this.zzhu.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzhu.zzdl(); i++) {
            if (!zzb(this.zzhu.zzan(i))) {
                return false;
            }
        }
        for (Entry zzb : this.zzhu.zzdm()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzhw ? new zzdd(this.zzhu.entrySet().iterator()) : this.zzhu.entrySet().iterator();
    }

    public final void zza(zzcj<FieldDescriptorType> zzcj) {
        for (int i = 0; i < zzcj.zzhu.zzdl(); i++) {
            zzc(zzcj.zzhu.zzan(i));
        }
        for (Entry zzc : zzcj.zzhu.zzdm()) {
            zzc(zzc);
        }
    }

    public final void zzao() {
        if (!this.zzhv) {
            this.zzhu.zzao();
            this.zzhv = true;
        }
    }

    public final int zzbl() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzhu.zzdl(); i2++) {
            Entry zzan = this.zzhu.zzan(i2);
            i += zzb((zzcl) zzan.getKey(), zzan.getValue());
        }
        for (Entry entry : this.zzhu.zzdm()) {
            i += zzb((zzcl) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzbm() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzhu.zzdl()) {
            i++;
            i2 = zzd(this.zzhu.zzan(i)) + i2;
        }
        for (Entry zzd : this.zzhu.zzdm()) {
            i2 += zzd(zzd);
        }
        return i2;
    }
}
