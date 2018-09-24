package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr.zzd;
import java.io.IOException;
import java.util.Arrays;

public final class zzfg {
    private static final zzfg zzot = new zzfg(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzgl;
    private int zzks;
    private Object[] zznf;
    private int[] zzou;

    private zzfg() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzks = -1;
        this.count = i;
        this.zzou = iArr;
        this.zznf = objArr;
        this.zzgl = z;
    }

    static zzfg zza(zzfg zzfg, zzfg zzfg2) {
        int i = zzfg.count + zzfg2.count;
        Object copyOf = Arrays.copyOf(zzfg.zzou, i);
        System.arraycopy(zzfg2.zzou, 0, copyOf, zzfg.count, zzfg2.count);
        Object copyOf2 = Arrays.copyOf(zzfg.zznf, i);
        System.arraycopy(zzfg2.zznf, 0, copyOf2, zzfg.count, zzfg2.count);
        return new zzfg(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzfz zzfz) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzfz.zzi(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzfz.zzc(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzfz.zza(i2, (zzbo) obj);
                return;
            case 3:
                if (zzfz.zzbc() == zzd.zzlj) {
                    zzfz.zzac(i2);
                    ((zzfg) obj).zzb(zzfz);
                    zzfz.zzad(i2);
                    return;
                }
                zzfz.zzad(i2);
                ((zzfg) obj).zzb(zzfz);
                zzfz.zzac(i2);
                return;
            case 5:
                zzfz.zzh(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzcx.zzce());
        }
    }

    public static zzfg zzdu() {
        return zzot;
    }

    static zzfg zzdv() {
        return new zzfg();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzfg)) {
            return false;
        }
        zzfg zzfg = (zzfg) obj;
        if (this.count == zzfg.count) {
            int i;
            boolean z;
            int[] iArr = this.zzou;
            int[] iArr2 = zzfg.zzou;
            int i2 = this.count;
            for (i = 0; i < i2; i++) {
                if (iArr[i] != iArr2[i]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                Object[] objArr = this.zznf;
                Object[] objArr2 = zzfg.zznf;
                i2 = this.count;
                for (i = 0; i < i2; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 17;
        int i3 = 0;
        int i4 = (this.count + 527) * 31;
        int[] iArr = this.zzou;
        int i5 = 17;
        for (i = 0; i < this.count; i++) {
            i5 = (i5 * 31) + iArr[i];
        }
        i = (i4 + i5) * 31;
        Object[] objArr = this.zznf;
        while (i3 < this.count) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
            i3++;
        }
        return i + i2;
    }

    final void zza(zzfz zzfz) throws IOException {
        int i;
        if (zzfz.zzbc() == zzd.zzlk) {
            for (i = this.count - 1; i >= 0; i--) {
                zzfz.zza(this.zzou[i] >>> 3, this.zznf[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzfz.zza(this.zzou[i] >>> 3, this.zznf[i]);
        }
    }

    final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzea.zza(stringBuilder, i, String.valueOf(this.zzou[i2] >>> 3), this.zznf[i2]);
        }
    }

    public final void zzao() {
        this.zzgl = false;
    }

    final void zzb(int i, Object obj) {
        if (this.zzgl) {
            if (this.count == this.zzou.length) {
                int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
                this.zzou = Arrays.copyOf(this.zzou, i2);
                this.zznf = Arrays.copyOf(this.zznf, i2);
            }
            this.zzou[this.count] = i;
            this.zznf[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzb(zzfz zzfz) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzfz.zzbc() == zzd.zzlj) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzou[i], this.zznf[i], zzfz);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzou[i], this.zznf[i], zzfz);
            }
        }
    }

    public final int zzbl() {
        int i = this.zzks;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzou[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        i += zzca.zze(i4, ((Long) this.zznf[i2]).longValue());
                        break;
                    case 1:
                        i += zzca.zzg(i4, ((Long) this.zznf[i2]).longValue());
                        break;
                    case 2:
                        i += zzca.zzc(i4, (zzbo) this.zznf[i2]);
                        break;
                    case 3:
                        i += ((zzfg) this.zznf[i2]).zzbl() + (zzca.zzt(i4) << 1);
                        break;
                    case 5:
                        i += zzca.zzl(i4, ((Integer) this.zznf[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzcx.zzce());
                }
            }
            this.zzks = i;
        }
        return i;
    }

    public final int zzdw() {
        int i = this.zzks;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                i += zzca.zzd(this.zzou[i2] >>> 3, (zzbo) this.zznf[i2]);
            }
            this.zzks = i;
        }
        return i;
    }
}
