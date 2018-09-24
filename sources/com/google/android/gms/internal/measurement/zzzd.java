package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzzd implements Cloneable {
    private Object value;
    private zzzb<?, ?> zzcfj;
    private List<zzzi> zzcfk = new ArrayList();

    zzzd() {
    }

    final void zza(zzzi zzzi) throws IOException {
        if (this.zzcfk != null) {
            this.zzcfk.add(zzzi);
            return;
        }
        Object zza;
        if (this.value instanceof zzzg) {
            byte[] bArr = zzzi.zzbug;
            zzyx zzj = zzyx.zzj(bArr, 0, bArr.length);
            int zzuy = zzj.zzuy();
            if (zzuy != bArr.length - zzyy.zzbc(zzuy)) {
                throw zzzf.zzyw();
            }
            zza = ((zzzg) this.value).zza(zzj);
        } else if (this.value instanceof zzzg[]) {
            zzzg[] zzzgArr = (zzzg[]) this.zzcfj.zzah(Collections.singletonList(zzzi));
            zzzg[] zzzgArr2 = (zzzg[]) this.value;
            zzzg[] zzzgArr3 = (zzzg[]) Arrays.copyOf(zzzgArr2, zzzgArr2.length + zzzgArr.length);
            System.arraycopy(zzzgArr, 0, zzzgArr3, zzzgArr2.length, zzzgArr.length);
        } else {
            zza = this.zzcfj.zzah(Collections.singletonList(zzzi));
        }
        this.zzcfj = this.zzcfj;
        this.value = zza;
        this.zzcfk = null;
    }

    final <T> T zzb(zzzb<?, T> zzzb) {
        if (this.value == null) {
            this.zzcfj = zzzb;
            this.value = zzzb.zzah(this.zzcfk);
            this.zzcfk = null;
        } else if (!this.zzcfj.equals(zzzb)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    final int zzf() {
        int i = 0;
        if (this.value != null) {
            zzzb zzzb = this.zzcfj;
            Object obj = this.value;
            if (!zzzb.zzcfe) {
                return zzzb.zzak(obj);
            }
            int length = Array.getLength(obj);
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (Array.get(obj, i3) != null) {
                    i2 += zzzb.zzak(Array.get(obj, i3));
                }
            }
            return i2;
        }
        for (zzzi zzzi : this.zzcfk) {
            i = (zzzi.zzbug.length + (zzyy.zzbj(zzzi.tag) + 0)) + i;
        }
        return i;
    }

    final void zza(zzyy zzyy) throws IOException {
        if (this.value != null) {
            zzzb zzzb = this.zzcfj;
            Object obj = this.value;
            if (zzzb.zzcfe) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzzb.zza(obj2, zzyy);
                    }
                }
                return;
            }
            zzzb.zza(obj, zzyy);
            return;
        }
        for (zzzi zzzi : this.zzcfk) {
            zzyy.zzca(zzzi.tag);
            zzyy.zzp(zzzi.zzbug);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzd)) {
            return false;
        }
        zzzd zzzd = (zzzd) obj;
        if (this.value == null || zzzd.value == null) {
            if (this.zzcfk != null && zzzd.zzcfk != null) {
                return this.zzcfk.equals(zzzd.zzcfk);
            }
            try {
                return Arrays.equals(toByteArray(), zzzd.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcfj != zzzd.zzcfj) {
            return false;
        } else {
            if (!this.zzcfj.zzcfd.isArray()) {
                return this.value.equals(zzzd.value);
            }
            if (this.value instanceof byte[]) {
                return Arrays.equals((byte[]) this.value, (byte[]) zzzd.value);
            }
            if (this.value instanceof int[]) {
                return Arrays.equals((int[]) this.value, (int[]) zzzd.value);
            }
            if (this.value instanceof long[]) {
                return Arrays.equals((long[]) this.value, (long[]) zzzd.value);
            }
            if (this.value instanceof float[]) {
                return Arrays.equals((float[]) this.value, (float[]) zzzd.value);
            }
            if (this.value instanceof double[]) {
                return Arrays.equals((double[]) this.value, (double[]) zzzd.value);
            }
            if (this.value instanceof boolean[]) {
                return Arrays.equals((boolean[]) this.value, (boolean[]) zzzd.value);
            }
            return Arrays.deepEquals((Object[]) this.value, (Object[]) zzzd.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzf()];
        zza(zzyy.zzo(bArr));
        return bArr;
    }

    private final zzzd zzyv() {
        zzzd zzzd = new zzzd();
        try {
            zzzd.zzcfj = this.zzcfj;
            if (this.zzcfk == null) {
                zzzd.zzcfk = null;
            } else {
                zzzd.zzcfk.addAll(this.zzcfk);
            }
            if (this.value != null) {
                if (this.value instanceof zzzg) {
                    zzzd.value = (zzzg) ((zzzg) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzzd.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zzzd.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzzd.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzzd.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzzd.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzzd.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzzd.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzzg[]) {
                    zzzg[] zzzgArr = (zzzg[]) this.value;
                    r4 = new zzzg[zzzgArr.length];
                    zzzd.value = r4;
                    for (r2 = 0; r2 < zzzgArr.length; r2++) {
                        r4[r2] = (zzzg) zzzgArr[r2].clone();
                    }
                }
            }
            return zzzd;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzyv();
    }
}
