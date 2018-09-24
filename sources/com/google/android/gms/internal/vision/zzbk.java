package com.google.android.gms.internal.vision;

import java.io.IOException;

final class zzbk {
    static int zza(int i, byte[] bArr, int i2, int i3, zzbl zzbl) throws zzcx {
        if ((i >>> 3) == 0) {
            throw zzcx.zzcd();
        }
        switch (i & 7) {
            case 0:
                return zzb(bArr, i2, zzbl);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, zzbl) + zzbl.zzgo;
            case 3:
                int i4 = (i & -8) | 4;
                int i5 = 0;
                int i6 = i2;
                while (i6 < i3) {
                    i6 = zza(bArr, i6, zzbl);
                    i5 = zzbl.zzgo;
                    if (i5 != i4) {
                        i6 = zza(i5, bArr, i6, i3, zzbl);
                    } else if (i6 > i3 && r1 == i4) {
                        return i6;
                    } else {
                        throw zzcx.zzcf();
                    }
                }
                if (i6 > i3) {
                }
                throw zzcx.zzcf();
            case 5:
                return i2 + 4;
            default:
                throw zzcx.zzcd();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzcw<?> zzcw, zzbl zzbl) {
        zzcs zzcs = (zzcs) zzcw;
        int zza = zza(bArr, i2, zzbl);
        zzcs.zzae(zzbl.zzgo);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzbl);
            if (i != zzbl.zzgo) {
                break;
            }
            zza = zza(bArr, zza2, zzbl);
            zzcs.zzae(zzbl.zzgo);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzfg zzfg, zzbl zzbl) throws zzcx {
        if ((i >>> 3) == 0) {
            throw zzcx.zzcd();
        }
        int zzb;
        int i4;
        switch (i & 7) {
            case 0:
                zzb = zzb(bArr, i2, zzbl);
                zzfg.zzb(i, Long.valueOf(zzbl.zzgp));
                return zzb;
            case 1:
                zzfg.zzb(i, Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            case 2:
                zzb = zza(bArr, i2, zzbl);
                i4 = zzbl.zzgo;
                if (i4 < 0) {
                    throw zzcx.zzcc();
                }
                if (i4 == 0) {
                    zzfg.zzb(i, zzbo.zzgt);
                } else {
                    zzfg.zzb(i, zzbo.zzb(bArr, zzb, i4));
                }
                return zzb + i4;
            case 3:
                zzfg zzdv = zzfg.zzdv();
                int i5 = (i & -8) | 4;
                zzb = 0;
                i4 = i2;
                while (i4 < i3) {
                    int zza = zza(bArr, i4, zzbl);
                    zzb = zzbl.zzgo;
                    if (zzb != i5) {
                        i4 = zza(zzb, bArr, zza, i3, zzdv, zzbl);
                    } else {
                        i4 = zza;
                        if (i4 <= i3 || r0 != i5) {
                            throw zzcx.zzcf();
                        }
                        zzfg.zzb(i, zzdv);
                        return i4;
                    }
                }
                if (i4 <= i3) {
                }
                throw zzcx.zzcf();
            case 5:
                zzfg.zzb(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            default:
                throw zzcx.zzcd();
        }
    }

    static int zza(int i, byte[] bArr, int i2, zzbl zzbl) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= (byte) 0) {
            zzbl.zzgo = i3 | (b << 7);
            return i4;
        }
        int i5 = ((b & 127) << 7) | i3;
        i3 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= (byte) 0) {
            zzbl.zzgo = (b2 << 14) | i5;
            return i3;
        }
        i4 = ((b2 & 127) << 14) | i5;
        i5 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 >= (byte) 0) {
            zzbl.zzgo = i4 | (b3 << 21);
            return i5;
        }
        i3 = ((b3 & 127) << 21) | i4;
        i4 = i5 + 1;
        b = bArr[i5];
        if (b >= (byte) 0) {
            zzbl.zzgo = i3 | (b << 28);
            return i4;
        }
        i5 = ((b & 127) << 28) | i3;
        while (true) {
            i3 = i4 + 1;
            if (bArr[i4] >= (byte) 0) {
                zzbl.zzgo = i5;
                return i3;
            }
            i4 = i3;
        }
    }

    static int zza(byte[] bArr, int i) {
        return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
    }

    static int zza(byte[] bArr, int i, zzbl zzbl) {
        int i2 = i + 1;
        int i3 = bArr[i];
        if (i3 < (byte) 0) {
            return zza(i3, bArr, i2, zzbl);
        }
        zzbl.zzgo = i3;
        return i2;
    }

    static int zza(byte[] bArr, int i, zzcw<?> zzcw, zzbl zzbl) throws IOException {
        zzcs zzcs = (zzcs) zzcw;
        int zza = zza(bArr, i, zzbl);
        int i2 = zzbl.zzgo + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzbl);
            zzcs.zzae(zzbl.zzgo);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzcx.zzcb();
    }

    static int zzb(byte[] bArr, int i, zzbl zzbl) {
        int i2 = 7;
        int i3 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzbl.zzgp = j;
        } else {
            long j2 = 127 & j;
            int i4 = i3 + 1;
            byte b = bArr[i3];
            j2 |= ((long) (b & 127)) << 7;
            i3 = i4;
            while (b < (byte) 0) {
                i4 = i3 + 1;
                b = bArr[i3];
                i2 += 7;
                j2 |= ((long) (b & 127)) << i2;
                i3 = i4;
            }
            zzbl.zzgp = j2;
        }
        return i3;
    }

    static long zzb(byte[] bArr, int i) {
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzbl zzbl) throws zzcx {
        int zza = zza(bArr, i, zzbl);
        int i2 = zzbl.zzgo;
        if (i2 < 0) {
            throw zzcx.zzcc();
        } else if (i2 == 0) {
            zzbl.zzgq = "";
            return zza;
        } else {
            zzbl.zzgq = new String(bArr, zza, i2, zzct.UTF_8);
            return zza + i2;
        }
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzd(byte[] bArr, int i, zzbl zzbl) throws zzcx {
        int zza = zza(bArr, i, zzbl);
        int i2 = zzbl.zzgo;
        if (i2 < 0) {
            throw zzcx.zzcc();
        } else if (i2 == 0) {
            zzbl.zzgq = "";
            return zza;
        } else if (zzfn.zze(bArr, zza, zza + i2)) {
            zzbl.zzgq = new String(bArr, zza, i2, zzct.UTF_8);
            return zza + i2;
        } else {
            throw zzcx.zzcg();
        }
    }

    static int zze(byte[] bArr, int i, zzbl zzbl) throws zzcx {
        int zza = zza(bArr, i, zzbl);
        int i2 = zzbl.zzgo;
        if (i2 < 0) {
            throw zzcx.zzcc();
        } else if (i2 == 0) {
            zzbl.zzgq = zzbo.zzgt;
            return zza;
        } else {
            zzbl.zzgq = zzbo.zzb(bArr, zza, i2);
            return zza + i2;
        }
    }
}
