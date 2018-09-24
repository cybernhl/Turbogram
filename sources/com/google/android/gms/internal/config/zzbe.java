package com.google.android.gms.internal.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzbe implements Cloneable {
    private Object value;
    private zzbc<?, ?> zzcn;
    private List<zzbj> zzco = new ArrayList();

    zzbe() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzt()];
        zza(zzaz.zza(bArr));
        return bArr;
    }

    private final zzbe zzae() {
        zzbe zzbe = new zzbe();
        try {
            zzbe.zzcn = this.zzcn;
            if (this.zzco == null) {
                zzbe.zzco = null;
            } else {
                zzbe.zzco.addAll(this.zzco);
            }
            if (this.value != null) {
                if (this.value instanceof zzbh) {
                    zzbe.value = (zzbh) ((zzbh) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbe.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zzbe.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbe.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbe.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbe.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbe.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbe.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbh[]) {
                    zzbh[] zzbhArr = (zzbh[]) this.value;
                    r4 = new zzbh[zzbhArr.length];
                    zzbe.value = r4;
                    for (r2 = 0; r2 < zzbhArr.length; r2++) {
                        r4[r2] = (zzbh) zzbhArr[r2].clone();
                    }
                }
            }
            return zzbe;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzae();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbe)) {
            return false;
        }
        zzbe zzbe = (zzbe) obj;
        if (this.value != null && zzbe.value != null) {
            return this.zzcn == zzbe.zzcn ? !this.zzcn.zzci.isArray() ? this.value.equals(zzbe.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbe.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbe.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbe.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbe.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbe.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbe.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbe.value) : false;
        } else {
            if (this.zzco != null && zzbe.zzco != null) {
                return this.zzco.equals(zzbe.zzco);
            }
            try {
                return Arrays.equals(toByteArray(), zzbe.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzaz zzaz) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzbj zzbj : this.zzco) {
            zzaz.zzm(zzbj.tag);
            zzaz.zzc(zzbj.zzcr);
        }
    }

    final void zza(zzbj zzbj) throws IOException {
        if (this.zzco != null) {
            this.zzco.add(zzbj);
        } else if (this.value instanceof zzbh) {
            byte[] bArr = zzbj.zzcr;
            zzay zza = zzay.zza(bArr, 0, bArr.length);
            int zzy = zza.zzy();
            if (zzy != bArr.length - zzaz.zzj(zzy)) {
                throw zzbg.zzaf();
            }
            zzbh zza2 = ((zzbh) this.value).zza(zza);
            this.zzcn = this.zzcn;
            this.value = zza2;
            this.zzco = null;
        } else if (this.value instanceof zzbh[]) {
            Collections.singletonList(zzbj);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zzbj);
            throw new NoSuchMethodError();
        }
    }

    final int zzt() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzbj zzbj : this.zzco) {
            i = (zzbj.zzcr.length + (zzaz.zzn(zzbj.tag) + 0)) + i;
        }
        return i;
    }
}
