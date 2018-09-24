package com.google.android.gms.internal.firebase_abt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzg implements Cloneable {
    private Object value;
    private zze<?, ?> zzy;
    private List<zzl> zzz = new ArrayList();

    zzg() {
    }

    private final byte[] toByteArray() throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzl zzl : this.zzz) {
            i = (zzl.zzac.length + (zzb.zzf(zzl.tag) + 0)) + i;
        }
        byte[] bArr = new byte[i];
        zzb zzb = zzb.zzb(bArr);
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzl zzl2 : this.zzz) {
            zzb.zze(zzl2.tag);
            zzb.zzc(zzl2.zzac);
        }
        return bArr;
    }

    private final zzg zzk() {
        zzg zzg = new zzg();
        try {
            zzg.zzy = this.zzy;
            if (this.zzz == null) {
                zzg.zzz = null;
            } else {
                zzg.zzz.addAll(this.zzz);
            }
            if (this.value != null) {
                if (this.value instanceof zzj) {
                    zzg.value = (zzj) ((zzj) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzg.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zzg.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzg.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzg.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzg.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzg.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzg.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzj[]) {
                    zzj[] zzjArr = (zzj[]) this.value;
                    r4 = new zzj[zzjArr.length];
                    zzg.value = r4;
                    for (r2 = 0; r2 < zzjArr.length; r2++) {
                        r4[r2] = (zzj) zzjArr[r2].clone();
                    }
                }
            }
            return zzg;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzk();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzg)) {
            return false;
        }
        zzg zzg = (zzg) obj;
        if (this.value != null && zzg.value != null) {
            return this.zzy == zzg.zzy ? !this.zzy.zzt.isArray() ? this.value.equals(zzg.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzg.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzg.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzg.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzg.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzg.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzg.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzg.value) : false;
        } else {
            if (this.zzz != null && zzg.zzz != null) {
                return this.zzz.equals(zzg.zzz);
            }
            try {
                return Arrays.equals(toByteArray(), zzg.toByteArray());
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

    final void zza(zzl zzl) throws IOException {
        if (this.zzz != null) {
            this.zzz.add(zzl);
        } else if (this.value instanceof zzj) {
            byte[] bArr = zzl.zzac;
            zza zza = zza.zza(bArr, 0, bArr.length);
            int zzg = zza.zzg();
            if (zzg != bArr.length - (zzg >= 0 ? zzb.zzf(zzg) : 10)) {
                throw zzi.zzl();
            }
            zzj zza2 = ((zzj) this.value).zza(zza);
            this.zzy = this.zzy;
            this.value = zza2;
            this.zzz = null;
        } else if (this.value instanceof zzj[]) {
            Collections.singletonList(zzl);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zzl);
            throw new NoSuchMethodError();
        }
    }
}
