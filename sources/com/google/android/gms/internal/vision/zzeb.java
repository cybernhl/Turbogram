package com.google.android.gms.internal.vision;

import android.support.v4.text.HtmlCompat;
import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.internal.vision.zzcr.zzd;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.googlecode.mp4parser.authoring.tracks.h265.NalUnitTypes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.telegram.messenger.voip.VoIPService;
import sun.misc.Unsafe;

final class zzeb<T> implements zzen<T> {
    private static final int[] zznc = new int[0];
    private static final Unsafe zznd = zzfl.zzdz();
    private final int[] zzne;
    private final Object[] zznf;
    private final int zzng;
    private final int zznh;
    private final zzdx zzni;
    private final boolean zznj;
    private final boolean zznk;
    private final boolean zznl;
    private final boolean zznm;
    private final int[] zznn;
    private final int zzno;
    private final int zznp;
    private final zzef zznq;
    private final zzdh zznr;
    private final zzff<?, ?> zzns;
    private final zzcg<?> zznt;
    private final zzds zznu;

    private zzeb(int[] iArr, Object[] objArr, int i, int i2, zzdx zzdx, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzef zzef, zzdh zzdh, zzff<?, ?> zzff, zzcg<?> zzcg, zzds zzds) {
        this.zzne = iArr;
        this.zznf = objArr;
        this.zzng = i;
        this.zznh = i2;
        this.zznk = zzdx instanceof zzcr;
        this.zznl = z;
        boolean z3 = zzcg != null && zzcg.zze(zzdx);
        this.zznj = z3;
        this.zznm = false;
        this.zznn = iArr2;
        this.zzno = i3;
        this.zznp = i4;
        this.zznq = zzef;
        this.zznr = zzdh;
        this.zzns = zzff;
        this.zznt = zzcg;
        this.zzni = zzdx;
        this.zznu = zzds;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbl zzbl) throws IOException {
        return zzbk.zza(i, bArr, i2, i3, zzo(obj), zzbl);
    }

    private static int zza(zzen<?> zzen, int i, byte[] bArr, int i2, int i3, zzcw<?> zzcw, zzbl zzbl) throws IOException {
        int zza = zza((zzen) zzen, bArr, i2, i3, zzbl);
        zzcw.add(zzbl.zzgq);
        while (zza < i3) {
            int zza2 = zzbk.zza(bArr, zza, zzbl);
            if (i != zzbl.zzgo) {
                break;
            }
            zza = zza((zzen) zzen, bArr, zza2, i3, zzbl);
            zzcw.add(zzbl.zzgq);
        }
        return zza;
    }

    private static int zza(zzen zzen, byte[] bArr, int i, int i2, int i3, zzbl zzbl) throws IOException {
        zzeb zzeb = (zzeb) zzen;
        Object newInstance = zzeb.newInstance();
        int zza = zzeb.zza(newInstance, bArr, i, i2, i3, zzbl);
        zzeb.zzd(newInstance);
        zzbl.zzgq = newInstance;
        return zza;
    }

    private static int zza(zzen zzen, byte[] bArr, int i, int i2, zzbl zzbl) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < (byte) 0) {
            i4 = zzbk.zza(i5, bArr, i4, zzbl);
            i3 = zzbl.zzgo;
        } else {
            i3 = i5;
        }
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzcx.zzcb();
        }
        Object newInstance = zzen.newInstance();
        zzen.zza(newInstance, bArr, i4, i4 + i3, zzbl);
        zzen.zzd(newInstance);
        zzbl.zzgq = newInstance;
        return i4 + i3;
    }

    private static <UT, UB> int zza(zzff<UT, UB> zzff, T t) {
        return zzff.zzn(zzff.zzr(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzbl zzbl) throws IOException {
        int i9;
        Unsafe unsafe = zznd;
        long j2 = (long) (this.zzne[i8 + 2] & 1048575);
        int zzb;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzbk.zzc(bArr, i)));
                    i9 = i + 8;
                    break;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzbk.zzd(bArr, i)));
                    i9 = i + 4;
                    break;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    i9 = zzbk.zzb(bArr, i, zzbl);
                    unsafe.putObject(t, j, Long.valueOf(zzbl.zzgp));
                    break;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    i9 = zzbk.zza(bArr, i, zzbl);
                    unsafe.putObject(t, j, Integer.valueOf(zzbl.zzgo));
                    break;
                }
                return i;
            case 56:
            case VoIPService.CALL_MIN_LAYER /*65*/:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzbk.zzb(bArr, i)));
                    i9 = i + 8;
                    break;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzbk.zza(bArr, i)));
                    i9 = i + 4;
                    break;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzb = zzbk.zzb(bArr, i, zzbl);
                    unsafe.putObject(t, j, Boolean.valueOf(zzbl.zzgp != 0));
                    i9 = zzb;
                    break;
                }
                return i;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                i9 = zzbk.zza(bArr, i, zzbl);
                zzb = zzbl.zzgo;
                if (zzb == 0) {
                    unsafe.putObject(t, j, "");
                } else if ((536870912 & i6) == 0 || zzfn.zze(bArr, i9, i9 + zzb)) {
                    unsafe.putObject(t, j, new String(bArr, i9, zzb, zzct.UTF_8));
                    i9 += zzb;
                } else {
                    throw zzcx.zzcg();
                }
                unsafe.putInt(t, j2, i4);
                return i9;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                i = zza(zzag(i8), bArr, i, i2, zzbl);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzbl.zzgq);
                } else {
                    unsafe.putObject(t, j, zzct.zza(object, zzbl.zzgq));
                }
                unsafe.putInt(t, j2, i4);
                return i;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                i9 = zzbk.zza(bArr, i, zzbl);
                zzb = zzbl.zzgo;
                if (zzb == 0) {
                    unsafe.putObject(t, j, zzbo.zzgt);
                } else {
                    unsafe.putObject(t, j, zzbo.zzb(bArr, i9, zzb));
                    i9 += zzb;
                }
                unsafe.putInt(t, j2, i4);
                return i9;
            case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                if (i5 != 0) {
                    return i;
                }
                i9 = zzbk.zza(bArr, i, zzbl);
                zzb = zzbl.zzgo;
                zzcv zzai = zzai(i8);
                if (zzai == null || zzai.zzaf(zzb) != null) {
                    unsafe.putObject(t, j, Integer.valueOf(zzb));
                    break;
                }
                zzo(t).zzb(i3, Long.valueOf((long) zzb));
                return i9;
            case 66:
                if (i5 == 0) {
                    i9 = zzbk.zza(bArr, i, zzbl);
                    unsafe.putObject(t, j, Integer.valueOf(zzbx.zzo(zzbl.zzgo)));
                    break;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    i9 = zzbk.zzb(bArr, i, zzbl);
                    unsafe.putObject(t, j, Long.valueOf(zzbx.zza(zzbl.zzgp)));
                    break;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    i9 = zza(zzag(i8), bArr, i, i2, (i3 & -8) | 4, zzbl);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 != null) {
                        unsafe.putObject(t, j, zzct.zza(object2, zzbl.zzgq));
                        break;
                    }
                    unsafe.putObject(t, j, zzbl.zzgq);
                    break;
                }
                return i;
            default:
                return i;
        }
        unsafe.putInt(t, j2, i4);
        return i9;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzbl zzbl) throws IOException {
        zzcw zzcw;
        zzcw zzcw2 = (zzcw) zznd.getObject(t, j2);
        if (zzcw2.zzan()) {
            zzcw = zzcw2;
        } else {
            int size = zzcw2.size();
            zzcw = zzcw2.zzk(size == 0 ? 10 : size << 1);
            zznd.putObject(t, j2, zzcw);
        }
        int i8;
        zzdl zzdl;
        zzcs zzcs;
        switch (i7) {
            case 18:
            case 35:
                zzcd zzcd;
                if (i5 == 2) {
                    zzcd = (zzcd) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        zzcd.zzc(zzbk.zzc(bArr, i));
                        i += 8;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzcd = (zzcd) zzcw;
                    zzcd.zzc(zzbk.zzc(bArr, i));
                    i += 8;
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        zzcd.zzc(zzbk.zzc(bArr, i8));
                        i = i8 + 8;
                    }
                    return i;
                }
            case 19:
            case 36:
                zzcp zzcp;
                if (i5 == 2) {
                    zzcp = (zzcp) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        zzcp.zze(zzbk.zzd(bArr, i));
                        i += 4;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzcp = (zzcp) zzcw;
                    zzcp.zze(zzbk.zzd(bArr, i));
                    i += 4;
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        zzcp.zze(zzbk.zzd(bArr, i8));
                        i = i8 + 4;
                    }
                    return i;
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzdl = (zzdl) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        i = zzbk.zzb(bArr, i, zzbl);
                        zzdl.zzl(zzbl.zzgp);
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzdl = (zzdl) zzcw;
                    i = zzbk.zzb(bArr, i, zzbl);
                    zzdl.zzl(zzbl.zzgp);
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        i = zzbk.zzb(bArr, i8, zzbl);
                        zzdl.zzl(zzbl.zzgp);
                    }
                    return i;
                }
            case 22:
            case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
            case 39:
            case 43:
                return i5 == 2 ? zzbk.zza(bArr, i, zzcw, zzbl) : i5 == 0 ? zzbk.zza(i3, bArr, i, i2, zzcw, zzbl) : i;
            case 23:
            case 32:
            case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
            case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                if (i5 == 2) {
                    zzdl = (zzdl) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        zzdl.zzl(zzbk.zzb(bArr, i));
                        i += 8;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzdl = (zzdl) zzcw;
                    zzdl.zzl(zzbk.zzb(bArr, i));
                    i += 8;
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        zzdl.zzl(zzbk.zzb(bArr, i8));
                        i = i8 + 8;
                    }
                    return i;
                }
            case 24:
            case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
            case 41:
            case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                if (i5 == 2) {
                    zzcs = (zzcs) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        zzcs.zzae(zzbk.zza(bArr, i));
                        i += 4;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzcs = (zzcs) zzcw;
                    zzcs.zzae(zzbk.zza(bArr, i));
                    i += 4;
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        zzcs.zzae(zzbk.zza(bArr, i8));
                        i = i8 + 4;
                    }
                    return i;
                }
            case 25:
            case 42:
                zzbm zzbm;
                if (i5 == 2) {
                    zzbm = (zzbm) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    size = i + zzbl.zzgo;
                    while (i < size) {
                        i = zzbk.zzb(bArr, i, zzbl);
                        zzbm.addBoolean(zzbl.zzgp != 0);
                    }
                    if (i == size) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzbm = (zzbm) zzcw;
                    i = zzbk.zzb(bArr, i, zzbl);
                    zzbm.addBoolean(zzbl.zzgp != 0);
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        i = zzbk.zzb(bArr, i8, zzbl);
                        zzbm.addBoolean(zzbl.zzgp != 0);
                    }
                    return i;
                }
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((536870912 & j) == 0) {
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo;
                    if (i8 < 0) {
                        throw zzcx.zzcc();
                    }
                    if (i8 == 0) {
                        zzcw.add("");
                    } else {
                        zzcw.add(new String(bArr, i, i8, zzct.UTF_8));
                        i += i8;
                    }
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        i = zzbk.zza(bArr, i8, zzbl);
                        i8 = zzbl.zzgo;
                        if (i8 < 0) {
                            throw zzcx.zzcc();
                        } else if (i8 == 0) {
                            zzcw.add("");
                        } else {
                            zzcw.add(new String(bArr, i, i8, zzct.UTF_8));
                            i += i8;
                        }
                    }
                    return i;
                }
                i = zzbk.zza(bArr, i, zzbl);
                i8 = zzbl.zzgo;
                if (i8 < 0) {
                    throw zzcx.zzcc();
                }
                if (i8 == 0) {
                    zzcw.add("");
                } else {
                    if (zzfn.zze(bArr, i, i + i8)) {
                        zzcw.add(new String(bArr, i, i8, zzct.UTF_8));
                        i += i8;
                    } else {
                        throw zzcx.zzcg();
                    }
                }
                while (i < i2) {
                    i8 = zzbk.zza(bArr, i, zzbl);
                    if (i3 != zzbl.zzgo) {
                        return i;
                    }
                    i = zzbk.zza(bArr, i8, zzbl);
                    i8 = zzbl.zzgo;
                    if (i8 < 0) {
                        throw zzcx.zzcc();
                    } else if (i8 == 0) {
                        zzcw.add("");
                    } else {
                        if (zzfn.zze(bArr, i, i + i8)) {
                            zzcw.add(new String(bArr, i, i8, zzct.UTF_8));
                            i += i8;
                        } else {
                            throw zzcx.zzcg();
                        }
                    }
                }
                return i;
            case 27:
                return i5 == 2 ? zza(zzag(i6), i3, bArr, i, i2, zzcw, zzbl) : i;
            case 28:
                if (i5 != 2) {
                    return i;
                }
                i = zzbk.zza(bArr, i, zzbl);
                i8 = zzbl.zzgo;
                if (i8 < 0) {
                    throw zzcx.zzcc();
                }
                if (i8 == 0) {
                    zzcw.add(zzbo.zzgt);
                } else {
                    zzcw.add(zzbo.zzb(bArr, i, i8));
                    i += i8;
                }
                while (i < i2) {
                    i8 = zzbk.zza(bArr, i, zzbl);
                    if (i3 != zzbl.zzgo) {
                        return i;
                    }
                    i = zzbk.zza(bArr, i8, zzbl);
                    i8 = zzbl.zzgo;
                    if (i8 < 0) {
                        throw zzcx.zzcc();
                    } else if (i8 == 0) {
                        zzcw.add(zzbo.zzgt);
                    } else {
                        zzcw.add(zzbo.zzb(bArr, i, i8));
                        i += i8;
                    }
                }
                return i;
            case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
            case 44:
                if (i5 == 2) {
                    size = zzbk.zza(bArr, i, zzcw, zzbl);
                } else if (i5 != 0) {
                    return i;
                } else {
                    size = zzbk.zza(i3, bArr, i, i2, zzcw, zzbl);
                }
                Object obj = ((zzcr) t).zzkr;
                if (obj == zzfg.zzdu()) {
                    obj = null;
                }
                zzfg zzfg = (zzfg) zzep.zza(i4, zzcw, zzai(i6), obj, this.zzns);
                if (zzfg == null) {
                    return size;
                }
                ((zzcr) t).zzkr = zzfg;
                return size;
            case 33:
            case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                if (i5 == 2) {
                    zzcs = (zzcs) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        i = zzbk.zza(bArr, i, zzbl);
                        zzcs.zzae(zzbx.zzo(zzbl.zzgo));
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzcs = (zzcs) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    zzcs.zzae(zzbx.zzo(zzbl.zzgo));
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        i = zzbk.zza(bArr, i8, zzbl);
                        zzcs.zzae(zzbx.zzo(zzbl.zzgo));
                    }
                    return i;
                }
            case 34:
            case 48:
                if (i5 == 2) {
                    zzdl = (zzdl) zzcw;
                    i = zzbk.zza(bArr, i, zzbl);
                    i8 = zzbl.zzgo + i;
                    while (i < i8) {
                        i = zzbk.zzb(bArr, i, zzbl);
                        zzdl.zzl(zzbx.zza(zzbl.zzgp));
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzcx.zzcb();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzdl = (zzdl) zzcw;
                    i = zzbk.zzb(bArr, i, zzbl);
                    zzdl.zzl(zzbx.zza(zzbl.zzgp));
                    while (i < i2) {
                        i8 = zzbk.zza(bArr, i, zzbl);
                        if (i3 != zzbl.zzgo) {
                            return i;
                        }
                        i = zzbk.zzb(bArr, i8, zzbl);
                        zzdl.zzl(zzbx.zza(zzbl.zzgp));
                    }
                    return i;
                }
            case 49:
                if (i5 != 3) {
                    return i;
                }
                zzen zzag = zzag(i6);
                int i9 = (i3 & -8) | 4;
                i = zza(zzag, bArr, i, i2, i9, zzbl);
                zzcw.add(zzbl.zzgq);
                while (i < i2) {
                    int zza = zzbk.zza(bArr, i, zzbl);
                    if (i3 != zzbl.zzgo) {
                        return i;
                    }
                    i = zza(zzag, bArr, zza, i2, i9, zzbl);
                    zzcw.add(zzbl.zzgq);
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r17, byte[] r18, int r19, int r20, int r21, long r22, com.google.android.gms.internal.vision.zzbl r24) throws java.io.IOException {
        /*
        r16 = this;
        r5 = zznd;
        r0 = r16;
        r1 = r21;
        r6 = r0.zzah(r1);
        r0 = r17;
        r1 = r22;
        r4 = r5.getObject(r0, r1);
        r0 = r16;
        r3 = r0.zznu;
        r3 = r3.zzj(r4);
        if (r3 == 0) goto L_0x00d2;
    L_0x001c:
        r0 = r16;
        r3 = r0.zznu;
        r3 = r3.zzl(r6);
        r0 = r16;
        r7 = r0.zznu;
        r7.zzb(r3, r4);
        r0 = r17;
        r1 = r22;
        r5.putObject(r0, r1, r3);
    L_0x0032:
        r0 = r16;
        r4 = r0.zznu;
        r11 = r4.zzm(r6);
        r0 = r16;
        r4 = r0.zznu;
        r12 = r4.zzh(r3);
        r0 = r18;
        r1 = r19;
        r2 = r24;
        r5 = com.google.android.gms.internal.vision.zzbk.zza(r0, r1, r2);
        r0 = r24;
        r3 = r0.zzgo;
        if (r3 < 0) goto L_0x0056;
    L_0x0052:
        r4 = r20 - r5;
        if (r3 <= r4) goto L_0x005b;
    L_0x0056:
        r3 = com.google.android.gms.internal.vision.zzcx.zzcb();
        throw r3;
    L_0x005b:
        r13 = r5 + r3;
        r4 = r11.zzmx;
        r3 = r11.zzew;
        r9 = r3;
        r10 = r4;
    L_0x0063:
        if (r5 >= r13) goto L_0x00c7;
    L_0x0065:
        r4 = r5 + 1;
        r3 = r18[r5];
        if (r3 >= 0) goto L_0x0077;
    L_0x006b:
        r0 = r18;
        r1 = r24;
        r4 = com.google.android.gms.internal.vision.zzbk.zza(r3, r0, r4, r1);
        r0 = r24;
        r3 = r0.zzgo;
    L_0x0077:
        r5 = r3 >>> 3;
        r6 = r3 & 7;
        switch(r5) {
            case 1: goto L_0x008a;
            case 2: goto L_0x00a6;
            default: goto L_0x007e;
        };
    L_0x007e:
        r0 = r18;
        r1 = r20;
        r2 = r24;
        r3 = com.google.android.gms.internal.vision.zzbk.zza(r3, r0, r4, r1, r2);
        r5 = r3;
        goto L_0x0063;
    L_0x008a:
        r5 = r11.zzmw;
        r5 = r5.zzee();
        if (r6 != r5) goto L_0x007e;
    L_0x0092:
        r6 = r11.zzmw;
        r7 = 0;
        r3 = r18;
        r5 = r20;
        r8 = r24;
        r4 = zza(r3, r4, r5, r6, r7, r8);
        r0 = r24;
        r3 = r0.zzgq;
        r10 = r3;
        r5 = r4;
        goto L_0x0063;
    L_0x00a6:
        r5 = r11.zzmy;
        r5 = r5.zzee();
        if (r6 != r5) goto L_0x007e;
    L_0x00ae:
        r6 = r11.zzmy;
        r3 = r11.zzew;
        r7 = r3.getClass();
        r3 = r18;
        r5 = r20;
        r8 = r24;
        r4 = zza(r3, r4, r5, r6, r7, r8);
        r0 = r24;
        r3 = r0.zzgq;
        r9 = r3;
        r5 = r4;
        goto L_0x0063;
    L_0x00c7:
        if (r5 == r13) goto L_0x00ce;
    L_0x00c9:
        r3 = com.google.android.gms.internal.vision.zzcx.zzcf();
        throw r3;
    L_0x00ce:
        r12.put(r10, r9);
        return r13;
    L_0x00d2:
        r3 = r4;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.vision.zzbl):int");
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, zzbl zzbl) throws IOException {
        int i4;
        int i5;
        int i6;
        Object obj;
        Object zzo;
        Unsafe unsafe = zznd;
        int i7 = -1;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = -1;
        int i12 = i;
        while (i12 < i2) {
            int i13;
            zzcv zzai;
            zzff zzff;
            int i14 = i12 + 1;
            i9 = bArr[i12];
            if (i9 < 0) {
                i14 = zzbk.zza(i9, bArr, i14, zzbl);
                i9 = zzbl.zzgo;
            }
            int i15 = i9 >>> 3;
            int i16 = i9 & 7;
            i10 = i15 > i11 ? zzr(i15, i10 / 3) : zzal(i15);
            if (i10 == -1) {
                i10 = 0;
                i4 = i8;
                i13 = i7;
                i12 = i14;
            } else {
                int i17 = this.zzne[i10 + 1];
                int i18 = (267386880 & i17) >>> 20;
                long j = (long) (1048575 & i17);
                if (i18 <= 17) {
                    i5 = this.zzne[i10 + 2];
                    int i19 = 1 << (i5 >>> 20);
                    i5 &= 1048575;
                    if (i5 != i7) {
                        if (i7 != -1) {
                            unsafe.putInt(t, (long) i7, i8);
                        }
                        i8 = unsafe.getInt(t, (long) i5);
                        i7 = i5;
                    }
                    switch (i18) {
                        case 0:
                            if (i16 == 1) {
                                zzfl.zza((Object) t, j, zzbk.zzc(bArr, i14));
                                i12 = i14 + 8;
                                i8 |= i19;
                                i11 = i15;
                                continue;
                            }
                            break;
                        case 1:
                            if (i16 == 5) {
                                zzfl.zza((Object) t, j, zzbk.zzd(bArr, i14));
                                i12 = i14 + 4;
                                i8 |= i19;
                                i11 = i15;
                                continue;
                            }
                            break;
                        case 2:
                        case 3:
                            if (i16 == 0) {
                                i = zzbk.zzb(bArr, i14, zzbl);
                                unsafe.putLong(t, j, zzbl.zzgp);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 4:
                        case 11:
                            if (i16 == 0) {
                                i = zzbk.zza(bArr, i14, zzbl);
                                unsafe.putInt(t, j, zzbl.zzgo);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 5:
                        case 14:
                            if (i16 == 1) {
                                unsafe.putLong(t, j, zzbk.zzb(bArr, i14));
                                i12 = i14 + 8;
                                i8 |= i19;
                                i11 = i15;
                                continue;
                            }
                            break;
                        case 6:
                        case 13:
                            if (i16 == 5) {
                                unsafe.putInt(t, j, zzbk.zza(bArr, i14));
                                i12 = i14 + 4;
                                i8 |= i19;
                                i11 = i15;
                                continue;
                            }
                            break;
                        case 7:
                            if (i16 == 0) {
                                i = zzbk.zzb(bArr, i14, zzbl);
                                zzfl.zza((Object) t, j, zzbl.zzgp != 0);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 8:
                            if (i16 == 2) {
                                i5 = (536870912 & i17) == 0 ? zzbk.zzc(bArr, i14, zzbl) : zzbk.zzd(bArr, i14, zzbl);
                                unsafe.putObject(t, j, zzbl.zzgq);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i5;
                                continue;
                            }
                            break;
                        case 9:
                            if (i16 == 2) {
                                i = zza(zzag(i10), bArr, i14, i2, zzbl);
                                if ((i8 & i19) == 0) {
                                    unsafe.putObject(t, j, zzbl.zzgq);
                                } else {
                                    unsafe.putObject(t, j, zzct.zza(unsafe.getObject(t, j), zzbl.zzgq));
                                }
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 10:
                            if (i16 == 2) {
                                i = zzbk.zze(bArr, i14, zzbl);
                                unsafe.putObject(t, j, zzbl.zzgq);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 12:
                            if (i16 == 0) {
                                i = zzbk.zza(bArr, i14, zzbl);
                                i5 = zzbl.zzgo;
                                zzai = zzai(i10);
                                if (zzai != null && zzai.zzaf(i5) == null) {
                                    zzo(t).zzb(i9, Long.valueOf((long) i5));
                                    i11 = i15;
                                    i12 = i;
                                    break;
                                }
                                unsafe.putInt(t, j, i5);
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 15:
                            if (i16 == 0) {
                                i = zzbk.zza(bArr, i14, zzbl);
                                unsafe.putInt(t, j, zzbx.zzo(zzbl.zzgo));
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 16:
                            if (i16 == 0) {
                                i = zzbk.zzb(bArr, i14, zzbl);
                                unsafe.putLong(t, j, zzbx.zza(zzbl.zzgp));
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                        case 17:
                            if (i16 == 3) {
                                i = zza(zzag(i10), bArr, i14, i2, (i15 << 3) | 4, zzbl);
                                if ((i8 & i19) == 0) {
                                    unsafe.putObject(t, j, zzbl.zzgq);
                                } else {
                                    unsafe.putObject(t, j, zzct.zza(unsafe.getObject(t, j), zzbl.zzgq));
                                }
                                i8 |= i19;
                                i11 = i15;
                                i12 = i;
                                continue;
                            }
                            break;
                    }
                    i4 = i8;
                    i13 = i7;
                    i12 = i14;
                } else {
                    if (i18 != 27) {
                        if (i18 <= 49) {
                            i12 = zza((Object) t, bArr, i14, i2, i9, i15, i16, i10, (long) i17, i18, j, zzbl);
                            if (i12 == i14) {
                                i4 = i8;
                                i13 = i7;
                            }
                        } else if (i18 != 50) {
                            i12 = zza((Object) t, bArr, i14, i2, i9, i15, i16, i17, i18, j, i10, zzbl);
                            if (i12 == i14) {
                                i4 = i8;
                                i13 = i7;
                            }
                        } else if (i16 == 2) {
                            i12 = zza((Object) t, bArr, i14, i2, i10, j, zzbl);
                            if (i12 == i14) {
                                i4 = i8;
                                i13 = i7;
                            }
                        }
                        i11 = i15;
                    } else if (i16 == 2) {
                        zzcw zzcw;
                        zzcw zzcw2 = (zzcw) unsafe.getObject(t, j);
                        if (zzcw2.zzan()) {
                            zzcw = zzcw2;
                        } else {
                            int size = zzcw2.size();
                            zzcw = zzcw2.zzk(size == 0 ? 10 : size << 1);
                            unsafe.putObject(t, j, zzcw);
                        }
                        i12 = zza(zzag(i10), i9, bArr, i14, i2, zzcw, zzbl);
                        i11 = i15;
                    }
                    i4 = i8;
                    i13 = i7;
                    i12 = i14;
                }
            }
            if (i9 != i3 || i3 == 0) {
                i12 = zza(i9, bArr, i12, i2, (Object) t, zzbl);
                i11 = i15;
                i8 = i4;
                i7 = i13;
            } else {
                i5 = i4;
                i11 = i13;
                i6 = i12;
                if (i11 != -1) {
                    unsafe.putInt(t, (long) i11, i5);
                }
                obj = null;
                i4 = this.zzno;
                while (i4 < this.zznp) {
                    i5 = this.zznn[i4];
                    zzff = this.zzns;
                    i11 = this.zzne[i5];
                    zzo = zzfl.zzo(t, (long) (zzaj(i5) & 1048575));
                    if (zzo != null) {
                        zzo = obj;
                    } else {
                        zzai = zzai(i5);
                        zzo = zzai != null ? obj : zza(i5, i11, this.zznu.zzh(zzo), zzai, obj, zzff);
                    }
                    i4++;
                    zzfg zzfg = (zzfg) zzo;
                }
                if (obj != null) {
                    this.zzns.zzf(t, obj);
                }
                if (i3 != 0) {
                    if (i6 != i2) {
                        throw zzcx.zzcf();
                    }
                } else if (i6 > i2 || r17 != i3) {
                    throw zzcx.zzcf();
                }
                return i6;
            }
        }
        i5 = i8;
        i11 = i7;
        i6 = i12;
        if (i11 != -1) {
            unsafe.putInt(t, (long) i11, i5);
        }
        obj = null;
        i4 = this.zzno;
        while (i4 < this.zznp) {
            i5 = this.zznn[i4];
            zzff = this.zzns;
            i11 = this.zzne[i5];
            zzo = zzfl.zzo(t, (long) (zzaj(i5) & 1048575));
            if (zzo != null) {
                zzai = zzai(i5);
                if (zzai != null) {
                }
            } else {
                zzo = obj;
            }
            i4++;
            zzfg zzfg2 = (zzfg) zzo;
        }
        if (obj != null) {
            this.zzns.zzf(t, obj);
        }
        if (i3 != 0) {
            throw zzcx.zzcf();
        } else if (i6 != i2) {
            throw zzcx.zzcf();
        }
        return i6;
    }

    private static int zza(byte[] bArr, int i, int i2, zzft zzft, Class<?> cls, zzbl zzbl) throws IOException {
        int zza;
        switch (zzec.zzhz[zzft.ordinal()]) {
            case 1:
                int zzb = zzbk.zzb(bArr, i, zzbl);
                zzbl.zzgq = Boolean.valueOf(zzbl.zzgp != 0);
                return zzb;
            case 2:
                return zzbk.zze(bArr, i, zzbl);
            case 3:
                zzbl.zzgq = Double.valueOf(zzbk.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzbl.zzgq = Integer.valueOf(zzbk.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzbl.zzgq = Long.valueOf(zzbk.zzb(bArr, i));
                return i + 8;
            case 8:
                zzbl.zzgq = Float.valueOf(zzbk.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                zza = zzbk.zza(bArr, i, zzbl);
                zzbl.zzgq = Integer.valueOf(zzbl.zzgo);
                return zza;
            case 12:
            case 13:
                zza = zzbk.zzb(bArr, i, zzbl);
                zzbl.zzgq = Long.valueOf(zzbl.zzgp);
                return zza;
            case 14:
                return zza(zzek.zzdc().zze(cls), bArr, i, i2, zzbl);
            case 15:
                zza = zzbk.zza(bArr, i, zzbl);
                zzbl.zzgq = Integer.valueOf(zzbx.zzo(zzbl.zzgo));
                return zza;
            case 16:
                zza = zzbk.zzb(bArr, i, zzbl);
                zzbl.zzgq = Long.valueOf(zzbx.zza(zzbl.zzgp));
                return zza;
            case 17:
                return zzbk.zzd(bArr, i, zzbl);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    static <T> zzeb<T> zza(Class<T> cls, zzdv zzdv, zzef zzef, zzdh zzdh, zzff<?, ?> zzff, zzcg<?> zzcg, zzds zzds) {
        if (zzdv instanceof zzem) {
            int i;
            int i2;
            int i3;
            char charAt;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int[] iArr;
            int i10;
            int i11;
            char charAt2;
            char charAt3;
            zzem zzem = (zzem) zzdv;
            boolean z = zzem.zzcv() == zzd.zzlh;
            String zzde = zzem.zzde();
            int length = zzde.length();
            int i12 = 1;
            char charAt4 = zzde.charAt(0);
            if (charAt4 >= '?') {
                i = charAt4 & 8191;
                i2 = 13;
                while (true) {
                    i3 = i12 + 1;
                    charAt = zzde.charAt(i12);
                    if (charAt < '?') {
                        break;
                    }
                    i |= (charAt & 8191) << i2;
                    i2 += 13;
                    i12 = i3;
                }
                i4 = (charAt << i2) | i;
            } else {
                char c = charAt4;
                i3 = 1;
            }
            i12 = i3 + 1;
            i2 = zzde.charAt(i3);
            if (i2 >= 55296) {
                i = i2 & 8191;
                i2 = 13;
                while (true) {
                    i3 = i12 + 1;
                    charAt = zzde.charAt(i12);
                    if (charAt < '?') {
                        break;
                    }
                    i |= (charAt & 8191) << i2;
                    i2 += 13;
                    i12 = i3;
                }
                i2 = (charAt << i2) | i;
                i5 = i3;
            } else {
                i5 = i12;
            }
            if (i2 == 0) {
                i3 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
                iArr = zznc;
                i10 = 0;
                i11 = 0;
            } else {
                char charAt5;
                char charAt6;
                char charAt7;
                char charAt8;
                i12 = i5 + 1;
                i2 = zzde.charAt(i5);
                if (i2 >= 55296) {
                    i = i2 & 8191;
                    i2 = 13;
                    while (true) {
                        i3 = i12 + 1;
                        charAt = zzde.charAt(i12);
                        if (charAt < '?') {
                            break;
                        }
                        i |= (charAt & 8191) << i2;
                        i2 += 13;
                        i12 = i3;
                    }
                    i2 = (charAt << i2) | i;
                } else {
                    i3 = i12;
                }
                i6 = i3 + 1;
                i = zzde.charAt(i3);
                if (i >= 55296) {
                    i12 = i & 8191;
                    i = 13;
                    i3 = i6;
                    while (true) {
                        i6 = i3 + 1;
                        charAt5 = zzde.charAt(i3);
                        if (charAt5 < '?') {
                            break;
                        }
                        i12 |= (charAt5 & 8191) << i;
                        i += 13;
                        i3 = i6;
                    }
                    i = (charAt5 << i) | i12;
                }
                i7 = i6 + 1;
                charAt = zzde.charAt(i6);
                if (charAt >= '?') {
                    i3 = charAt & 8191;
                    i12 = 13;
                    i6 = i7;
                    while (true) {
                        i7 = i6 + 1;
                        charAt6 = zzde.charAt(i6);
                        if (charAt6 < '?') {
                            break;
                        }
                        i3 |= (charAt6 & 8191) << i12;
                        i12 += 13;
                        i6 = i7;
                    }
                    charAt = (charAt6 << i12) | i3;
                }
                int i13 = i7 + 1;
                charAt5 = zzde.charAt(i7);
                if (charAt5 >= '?') {
                    i6 = charAt5 & 8191;
                    i3 = 13;
                    i7 = i13;
                    while (true) {
                        i13 = i7 + 1;
                        charAt2 = zzde.charAt(i7);
                        if (charAt2 < '?') {
                            break;
                        }
                        i6 |= (charAt2 & 8191) << i3;
                        i3 += 13;
                        i7 = i13;
                    }
                    i6 = (charAt2 << i3) | i6;
                } else {
                    charAt6 = charAt5;
                }
                i9 = i13 + 1;
                charAt5 = zzde.charAt(i13);
                if (charAt5 >= '?') {
                    i7 = charAt5 & 8191;
                    i3 = 13;
                    i13 = i9;
                    while (true) {
                        i9 = i13 + 1;
                        charAt7 = zzde.charAt(i13);
                        if (charAt7 < '?') {
                            break;
                        }
                        i7 |= (charAt7 & 8191) << i3;
                        i3 += 13;
                        i13 = i9;
                    }
                    i7 = (charAt7 << i3) | i7;
                } else {
                    charAt2 = charAt5;
                }
                i10 = i9 + 1;
                charAt5 = zzde.charAt(i9);
                if (charAt5 >= '?') {
                    i13 = charAt5 & 8191;
                    i3 = 13;
                    i9 = i10;
                    while (true) {
                        i10 = i9 + 1;
                        charAt8 = zzde.charAt(i9);
                        if (charAt8 < '?') {
                            break;
                        }
                        i13 |= (charAt8 & 8191) << i3;
                        i3 += 13;
                        i9 = i10;
                    }
                    i8 = (charAt8 << i3) | i13;
                } else {
                    char c2 = charAt5;
                }
                i9 = i10 + 1;
                i3 = zzde.charAt(i10);
                if (i3 >= 55296) {
                    i13 = i3 & 8191;
                    i3 = 13;
                    while (true) {
                        i10 = i9 + 1;
                        charAt8 = zzde.charAt(i9);
                        if (charAt8 < '?') {
                            break;
                        }
                        i13 |= (charAt8 & 8191) << i3;
                        i3 += 13;
                        i9 = i10;
                    }
                    i3 = (charAt8 << i3) | i13;
                } else {
                    i10 = i9;
                }
                i5 = i10 + 1;
                charAt7 = zzde.charAt(i10);
                if (charAt7 >= '?') {
                    i9 = charAt7 & 8191;
                    i13 = 13;
                    i10 = i5;
                    while (true) {
                        i5 = i10 + 1;
                        charAt3 = zzde.charAt(i10);
                        if (charAt3 < '?') {
                            break;
                        }
                        i9 |= (charAt3 & 8191) << i13;
                        i13 += 13;
                        i10 = i5;
                    }
                    i9 = (charAt3 << i13) | i9;
                } else {
                    charAt8 = charAt7;
                }
                iArr = new int[(i3 + (i9 + i8))];
                i10 = i + (i2 << 1);
                charAt5 = charAt;
                i11 = i2;
            }
            Unsafe unsafe = zznd;
            Object[] zzdf = zzem.zzdf();
            int i14 = 0;
            Class cls2 = zzem.zzcx().getClass();
            int[] iArr2 = new int[(i7 * 3)];
            Object[] objArr = new Object[(i7 << 1)];
            int i15 = i9 + i8;
            int i16 = 0;
            int i17 = i9;
            int i18 = i10;
            int i19;
            for (i5 = 
/*
Method generation error in method: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Class, com.google.android.gms.internal.vision.zzdv, com.google.android.gms.internal.vision.zzef, com.google.android.gms.internal.vision.zzdh, com.google.android.gms.internal.vision.zzff, com.google.android.gms.internal.vision.zzcg, com.google.android.gms.internal.vision.zzds):com.google.android.gms.internal.vision.zzeb<T>, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r14_2 'i5' int) = (r14_1 'i5' int), (r14_63 'i5' int) binds: {(r14_1 'i5' int)=B:21:0x0071, (r14_63 'i5' int)=B:87:0x021b} in method: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Class, com.google.android.gms.internal.vision.zzdv, com.google.android.gms.internal.vision.zzef, com.google.android.gms.internal.vision.zzdh, com.google.android.gms.internal.vision.zzff, com.google.android.gms.internal.vision.zzcg, com.google.android.gms.internal.vision.zzds):com.google.android.gms.internal.vision.zzeb<T>, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1740223770.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 23 more

*/

            private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzcv<?> zzcv, UB ub, zzff<UT, UB> zzff) {
                zzdq zzm = this.zznu.zzm(zzah(i));
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (zzcv.zzaf(((Integer) entry.getValue()).intValue()) == null) {
                        if (ub == null) {
                            ub = zzff.zzdt();
                        }
                        zzbt zzm2 = zzbo.zzm(zzdp.zza(zzm, entry.getKey(), entry.getValue()));
                        try {
                            zzdp.zza(zzm2.zzax(), zzm, entry.getKey(), entry.getValue());
                            zzff.zza((Object) ub, i2, zzm2.zzaw());
                            it.remove();
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return ub;
            }

            private static Field zza(Class<?> cls, String str) {
                Field declaredField;
                try {
                    declaredField = cls.getDeclaredField(str);
                } catch (NoSuchFieldException e) {
                    Field[] declaredFields = cls.getDeclaredFields();
                    int length = declaredFields.length;
                    int i = 0;
                    while (i < length) {
                        declaredField = declaredFields[i];
                        if (!str.equals(declaredField.getName())) {
                            i++;
                        }
                    }
                    String name = cls.getName();
                    String arrays = Arrays.toString(declaredFields);
                    throw new RuntimeException(new StringBuilder(((String.valueOf(str).length() + 40) + String.valueOf(name).length()) + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
                }
                return declaredField;
            }

            private static void zza(int i, Object obj, zzfz zzfz) throws IOException {
                if (obj instanceof String) {
                    zzfz.zza(i, (String) obj);
                } else {
                    zzfz.zza(i, (zzbo) obj);
                }
            }

            private static <UT, UB> void zza(zzff<UT, UB> zzff, T t, zzfz zzfz) throws IOException {
                zzff.zza(zzff.zzr(t), zzfz);
            }

            private final <K, V> void zza(zzfz zzfz, int i, Object obj, int i2) throws IOException {
                if (obj != null) {
                    zzfz.zza(i, this.zznu.zzm(zzah(i2)), this.zznu.zzi(obj));
                }
            }

            private final void zza(T t, T t2, int i) {
                long zzaj = (long) (zzaj(i) & 1048575);
                if (zza((Object) t2, i)) {
                    Object zzo = zzfl.zzo(t, zzaj);
                    Object zzo2 = zzfl.zzo(t2, zzaj);
                    if (zzo != null && zzo2 != null) {
                        zzfl.zza((Object) t, zzaj, zzct.zza(zzo, zzo2));
                        zzb((Object) t, i);
                    } else if (zzo2 != null) {
                        zzfl.zza((Object) t, zzaj, zzo2);
                        zzb((Object) t, i);
                    }
                }
            }

            private final boolean zza(T t, int i) {
                int zzaj;
                if (this.zznl) {
                    zzaj = zzaj(i);
                    long j = (long) (zzaj & 1048575);
                    switch ((zzaj & 267386880) >>> 20) {
                        case 0:
                            return zzfl.zzn(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                        case 1:
                            return zzfl.zzm(t, j) != 0.0f;
                        case 2:
                            return zzfl.zzk(t, j) != 0;
                        case 3:
                            return zzfl.zzk(t, j) != 0;
                        case 4:
                            return zzfl.zzj(t, j) != 0;
                        case 5:
                            return zzfl.zzk(t, j) != 0;
                        case 6:
                            return zzfl.zzj(t, j) != 0;
                        case 7:
                            return zzfl.zzl(t, j);
                        case 8:
                            Object zzo = zzfl.zzo(t, j);
                            if (zzo instanceof String) {
                                return !((String) zzo).isEmpty();
                            } else {
                                if (zzo instanceof zzbo) {
                                    return !zzbo.zzgt.equals(zzo);
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                        case 9:
                            return zzfl.zzo(t, j) != null;
                        case 10:
                            return !zzbo.zzgt.equals(zzfl.zzo(t, j));
                        case 11:
                            return zzfl.zzj(t, j) != 0;
                        case 12:
                            return zzfl.zzj(t, j) != 0;
                        case 13:
                            return zzfl.zzj(t, j) != 0;
                        case 14:
                            return zzfl.zzk(t, j) != 0;
                        case 15:
                            return zzfl.zzj(t, j) != 0;
                        case 16:
                            return zzfl.zzk(t, j) != 0;
                        case 17:
                            return zzfl.zzo(t, j) != null;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
                zzaj = zzak(i);
                return (zzfl.zzj(t, (long) (zzaj & 1048575)) & (1 << (zzaj >>> 20))) != 0;
            }

            private final boolean zza(T t, int i, int i2) {
                return zzfl.zzj(t, (long) (zzak(i2) & 1048575)) == i;
            }

            private final boolean zza(T t, int i, int i2, int i3) {
                return this.zznl ? zza((Object) t, i) : (i2 & i3) != 0;
            }

            private static boolean zza(Object obj, int i, zzen zzen) {
                return zzen.zzp(zzfl.zzo(obj, (long) (1048575 & i)));
            }

            private final zzen zzag(int i) {
                int i2 = (i / 3) << 1;
                zzen zzen = (zzen) this.zznf[i2];
                if (zzen != null) {
                    return zzen;
                }
                zzen = zzek.zzdc().zze((Class) this.zznf[i2 + 1]);
                this.zznf[i2] = zzen;
                return zzen;
            }

            private final Object zzah(int i) {
                return this.zznf[(i / 3) << 1];
            }

            private final zzcv<?> zzai(int i) {
                return (zzcv) this.zznf[((i / 3) << 1) + 1];
            }

            private final int zzaj(int i) {
                return this.zzne[i + 1];
            }

            private final int zzak(int i) {
                return this.zzne[i + 2];
            }

            private final int zzal(int i) {
                return (i < this.zzng || i > this.zznh) ? -1 : zzs(i, 0);
            }

            private final void zzb(T t, int i) {
                if (!this.zznl) {
                    int zzak = zzak(i);
                    long j = (long) (zzak & 1048575);
                    zzfl.zza((Object) t, j, zzfl.zzj(t, j) | (1 << (zzak >>> 20)));
                }
            }

            private final void zzb(T t, int i, int i2) {
                zzfl.zza((Object) t, (long) (zzak(i2) & 1048575), i);
            }

            private final void zzb(T t, zzfz zzfz) throws IOException {
                Iterator it = null;
                Entry entry = null;
                if (this.zznj) {
                    zzcj zzb = this.zznt.zzb(t);
                    if (!zzb.isEmpty()) {
                        it = zzb.iterator();
                        entry = (Entry) it.next();
                    }
                }
                int i = -1;
                int i2 = 0;
                int length = this.zzne.length;
                Unsafe unsafe = zznd;
                int i3 = 0;
                Entry entry2 = entry;
                while (i3 < length) {
                    int i4;
                    int i5;
                    int zzaj = zzaj(i3);
                    int i6 = this.zzne[i3];
                    int i7 = (267386880 & zzaj) >>> 20;
                    if (this.zznl || i7 > 17) {
                        i4 = 0;
                        i5 = i2;
                    } else {
                        int i8;
                        i4 = this.zzne[i3 + 2];
                        int i9 = i4 & 1048575;
                        if (i9 != i) {
                            i8 = unsafe.getInt(t, (long) i9);
                        } else {
                            i8 = i2;
                            i9 = i;
                        }
                        i4 = 1 << (i4 >>> 20);
                        i5 = i8;
                        i = i9;
                    }
                    while (entry2 != null && this.zznt.zza(entry2) <= i6) {
                        this.zznt.zza(zzfz, entry2);
                        entry2 = it.hasNext() ? (Entry) it.next() : null;
                    }
                    long j = (long) (1048575 & zzaj);
                    switch (i7) {
                        case 0:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zza(i6, zzfl.zzn(t, j));
                            break;
                        case 1:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zza(i6, zzfl.zzm(t, j));
                            break;
                        case 2:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzi(i6, unsafe.getLong(t, j));
                            break;
                        case 3:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zza(i6, unsafe.getLong(t, j));
                            break;
                        case 4:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zze(i6, unsafe.getInt(t, j));
                            break;
                        case 5:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzc(i6, unsafe.getLong(t, j));
                            break;
                        case 6:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzh(i6, unsafe.getInt(t, j));
                            break;
                        case 7:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzb(i6, zzfl.zzl(t, j));
                            break;
                        case 8:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zza(i6, unsafe.getObject(t, j), zzfz);
                            break;
                        case 9:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zza(i6, unsafe.getObject(t, j), zzag(i3));
                            break;
                        case 10:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zza(i6, (zzbo) unsafe.getObject(t, j));
                            break;
                        case 11:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzf(i6, unsafe.getInt(t, j));
                            break;
                        case 12:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzp(i6, unsafe.getInt(t, j));
                            break;
                        case 13:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzo(i6, unsafe.getInt(t, j));
                            break;
                        case 14:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzj(i6, unsafe.getLong(t, j));
                            break;
                        case 15:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzg(i6, unsafe.getInt(t, j));
                            break;
                        case 16:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzb(i6, unsafe.getLong(t, j));
                            break;
                        case 17:
                            if ((i5 & i4) == 0) {
                                break;
                            }
                            zzfz.zzb(i6, unsafe.getObject(t, j), zzag(i3));
                            break;
                        case 18:
                            zzep.zza(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 19:
                            zzep.zzb(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 20:
                            zzep.zzc(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 21:
                            zzep.zzd(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 22:
                            zzep.zzh(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 23:
                            zzep.zzf(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 24:
                            zzep.zzk(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 25:
                            zzep.zzn(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 26:
                            zzep.zza(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz);
                            break;
                        case 27:
                            zzep.zza(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, zzag(i3));
                            break;
                        case 28:
                            zzep.zzb(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz);
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                            zzep.zzi(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                            zzep.zzm(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                            zzep.zzl(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 32:
                            zzep.zzg(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 33:
                            zzep.zzj(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 34:
                            zzep.zze(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, false);
                            break;
                        case 35:
                            zzep.zza(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 36:
                            zzep.zzb(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 37:
                            zzep.zzc(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 38:
                            zzep.zzd(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 39:
                            zzep.zzh(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                            zzep.zzf(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 41:
                            zzep.zzk(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 42:
                            zzep.zzn(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 43:
                            zzep.zzi(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 44:
                            zzep.zzm(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                            zzep.zzl(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                            zzep.zzg(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                            zzep.zzj(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 48:
                            zzep.zze(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, true);
                            break;
                        case 49:
                            zzep.zzb(this.zzne[i3], (List) unsafe.getObject(t, j), zzfz, zzag(i3));
                            break;
                        case 50:
                            zza(zzfz, i6, unsafe.getObject(t, j), i3);
                            break;
                        case 51:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zza(i6, zze(t, j));
                            break;
                        case 52:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zza(i6, zzf(t, j));
                            break;
                        case 53:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzi(i6, zzh(t, j));
                            break;
                        case 54:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zza(i6, zzh(t, j));
                            break;
                        case 55:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zze(i6, zzg(t, j));
                            break;
                        case 56:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzc(i6, zzh(t, j));
                            break;
                        case 57:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzh(i6, zzg(t, j));
                            break;
                        case 58:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzb(i6, zzi(t, j));
                            break;
                        case 59:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zza(i6, unsafe.getObject(t, j), zzfz);
                            break;
                        case 60:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zza(i6, unsafe.getObject(t, j), zzag(i3));
                            break;
                        case 61:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zza(i6, (zzbo) unsafe.getObject(t, j));
                            break;
                        case 62:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzf(i6, zzg(t, j));
                            break;
                        case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzp(i6, zzg(t, j));
                            break;
                        case 64:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzo(i6, zzg(t, j));
                            break;
                        case VoIPService.CALL_MIN_LAYER /*65*/:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzj(i6, zzh(t, j));
                            break;
                        case 66:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzg(i6, zzg(t, j));
                            break;
                        case 67:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzb(i6, zzh(t, j));
                            break;
                        case 68:
                            if (!zza((Object) t, i6, i3)) {
                                break;
                            }
                            zzfz.zzb(i6, unsafe.getObject(t, j), zzag(i3));
                            break;
                        default:
                            break;
                    }
                    i3 += 3;
                    i2 = i5;
                }
                for (entry = entry2; entry != null; entry = it.hasNext() ? (Entry) it.next() : null) {
                    this.zznt.zza(zzfz, entry);
                }
                zza(this.zzns, (Object) t, zzfz);
            }

            private final void zzb(T t, T t2, int i) {
                int zzaj = zzaj(i);
                int i2 = this.zzne[i];
                long j = (long) (zzaj & 1048575);
                if (zza((Object) t2, i2, i)) {
                    Object zzo = zzfl.zzo(t, j);
                    Object zzo2 = zzfl.zzo(t2, j);
                    if (zzo != null && zzo2 != null) {
                        zzfl.zza((Object) t, j, zzct.zza(zzo, zzo2));
                        zzb((Object) t, i2, i);
                    } else if (zzo2 != null) {
                        zzfl.zza((Object) t, j, zzo2);
                        zzb((Object) t, i2, i);
                    }
                }
            }

            private final boolean zzc(T t, T t2, int i) {
                return zza((Object) t, i) == zza((Object) t2, i);
            }

            private static <E> List<E> zzd(Object obj, long j) {
                return (List) zzfl.zzo(obj, j);
            }

            private static <T> double zze(T t, long j) {
                return ((Double) zzfl.zzo(t, j)).doubleValue();
            }

            private static <T> float zzf(T t, long j) {
                return ((Float) zzfl.zzo(t, j)).floatValue();
            }

            private static <T> int zzg(T t, long j) {
                return ((Integer) zzfl.zzo(t, j)).intValue();
            }

            private static <T> long zzh(T t, long j) {
                return ((Long) zzfl.zzo(t, j)).longValue();
            }

            private static <T> boolean zzi(T t, long j) {
                return ((Boolean) zzfl.zzo(t, j)).booleanValue();
            }

            private static zzfg zzo(Object obj) {
                zzfg zzfg = ((zzcr) obj).zzkr;
                if (zzfg != zzfg.zzdu()) {
                    return zzfg;
                }
                zzfg = zzfg.zzdv();
                ((zzcr) obj).zzkr = zzfg;
                return zzfg;
            }

            private final int zzr(int i, int i2) {
                return (i < this.zzng || i > this.zznh) ? -1 : zzs(i, i2);
            }

            private final int zzs(int i, int i2) {
                int length = (this.zzne.length / 3) - 1;
                while (i2 <= length) {
                    int i3 = (length + i2) >>> 1;
                    int i4 = i3 * 3;
                    int i5 = this.zzne[i4];
                    if (i == i5) {
                        return i4;
                    }
                    if (i < i5) {
                        length = i3 - 1;
                    } else {
                        i2 = i3 + 1;
                    }
                }
                return -1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final boolean equals(T r12, T r13) {
                /*
                r11 = this;
                r1 = 1;
                r10 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
                r0 = 0;
                r2 = r11.zzne;
                r4 = r2.length;
                r3 = r0;
            L_0x0009:
                if (r3 >= r4) goto L_0x01cf;
            L_0x000b:
                r2 = r11.zzaj(r3);
                r5 = r2 & r10;
                r6 = (long) r5;
                r5 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
                r2 = r2 & r5;
                r2 = r2 >>> 20;
                switch(r2) {
                    case 0: goto L_0x001e;
                    case 1: goto L_0x0032;
                    case 2: goto L_0x0044;
                    case 3: goto L_0x0058;
                    case 4: goto L_0x006c;
                    case 5: goto L_0x007e;
                    case 6: goto L_0x0092;
                    case 7: goto L_0x00a5;
                    case 8: goto L_0x00b8;
                    case 9: goto L_0x00cf;
                    case 10: goto L_0x00e6;
                    case 11: goto L_0x00fd;
                    case 12: goto L_0x0110;
                    case 13: goto L_0x0123;
                    case 14: goto L_0x0136;
                    case 15: goto L_0x014b;
                    case 16: goto L_0x015e;
                    case 17: goto L_0x0173;
                    case 18: goto L_0x018a;
                    case 19: goto L_0x018a;
                    case 20: goto L_0x018a;
                    case 21: goto L_0x018a;
                    case 22: goto L_0x018a;
                    case 23: goto L_0x018a;
                    case 24: goto L_0x018a;
                    case 25: goto L_0x018a;
                    case 26: goto L_0x018a;
                    case 27: goto L_0x018a;
                    case 28: goto L_0x018a;
                    case 29: goto L_0x018a;
                    case 30: goto L_0x018a;
                    case 31: goto L_0x018a;
                    case 32: goto L_0x018a;
                    case 33: goto L_0x018a;
                    case 34: goto L_0x018a;
                    case 35: goto L_0x018a;
                    case 36: goto L_0x018a;
                    case 37: goto L_0x018a;
                    case 38: goto L_0x018a;
                    case 39: goto L_0x018a;
                    case 40: goto L_0x018a;
                    case 41: goto L_0x018a;
                    case 42: goto L_0x018a;
                    case 43: goto L_0x018a;
                    case 44: goto L_0x018a;
                    case 45: goto L_0x018a;
                    case 46: goto L_0x018a;
                    case 47: goto L_0x018a;
                    case 48: goto L_0x018a;
                    case 49: goto L_0x018a;
                    case 50: goto L_0x0198;
                    case 51: goto L_0x01a6;
                    case 52: goto L_0x01a6;
                    case 53: goto L_0x01a6;
                    case 54: goto L_0x01a6;
                    case 55: goto L_0x01a6;
                    case 56: goto L_0x01a6;
                    case 57: goto L_0x01a6;
                    case 58: goto L_0x01a6;
                    case 59: goto L_0x01a6;
                    case 60: goto L_0x01a6;
                    case 61: goto L_0x01a6;
                    case 62: goto L_0x01a6;
                    case 63: goto L_0x01a6;
                    case 64: goto L_0x01a6;
                    case 65: goto L_0x01a6;
                    case 66: goto L_0x01a6;
                    case 67: goto L_0x01a6;
                    case 68: goto L_0x01a6;
                    default: goto L_0x001a;
                };
            L_0x001a:
                r2 = r1;
            L_0x001b:
                if (r2 != 0) goto L_0x01ca;
            L_0x001d:
                return r0;
            L_0x001e:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0030;
            L_0x0024:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x0030:
                r2 = r0;
                goto L_0x001b;
            L_0x0032:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0042;
            L_0x0038:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x0042:
                r2 = r0;
                goto L_0x001b;
            L_0x0044:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0056;
            L_0x004a:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x0056:
                r2 = r0;
                goto L_0x001b;
            L_0x0058:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x006a;
            L_0x005e:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x006a:
                r2 = r0;
                goto L_0x001b;
            L_0x006c:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x007c;
            L_0x0072:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x007c:
                r2 = r0;
                goto L_0x001b;
            L_0x007e:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0090;
            L_0x0084:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x0090:
                r2 = r0;
                goto L_0x001b;
            L_0x0092:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x00a2;
            L_0x0098:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x00a2:
                r2 = r0;
                goto L_0x001b;
            L_0x00a5:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x00b5;
            L_0x00ab:
                r2 = com.google.android.gms.internal.vision.zzfl.zzl(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzl(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x00b5:
                r2 = r0;
                goto L_0x001b;
            L_0x00b8:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x00cc;
            L_0x00be:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                if (r2 != 0) goto L_0x001a;
            L_0x00cc:
                r2 = r0;
                goto L_0x001b;
            L_0x00cf:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x00e3;
            L_0x00d5:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                if (r2 != 0) goto L_0x001a;
            L_0x00e3:
                r2 = r0;
                goto L_0x001b;
            L_0x00e6:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x00fa;
            L_0x00ec:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                if (r2 != 0) goto L_0x001a;
            L_0x00fa:
                r2 = r0;
                goto L_0x001b;
            L_0x00fd:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x010d;
            L_0x0103:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x010d:
                r2 = r0;
                goto L_0x001b;
            L_0x0110:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0120;
            L_0x0116:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x0120:
                r2 = r0;
                goto L_0x001b;
            L_0x0123:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0133;
            L_0x0129:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x0133:
                r2 = r0;
                goto L_0x001b;
            L_0x0136:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0148;
            L_0x013c:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x0148:
                r2 = r0;
                goto L_0x001b;
            L_0x014b:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x015b;
            L_0x0151:
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r6);
                if (r2 == r5) goto L_0x001a;
            L_0x015b:
                r2 = r0;
                goto L_0x001b;
            L_0x015e:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0170;
            L_0x0164:
                r8 = com.google.android.gms.internal.vision.zzfl.zzk(r12, r6);
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r13, r6);
                r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x001a;
            L_0x0170:
                r2 = r0;
                goto L_0x001b;
            L_0x0173:
                r2 = r11.zzc(r12, r13, r3);
                if (r2 == 0) goto L_0x0187;
            L_0x0179:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                if (r2 != 0) goto L_0x001a;
            L_0x0187:
                r2 = r0;
                goto L_0x001b;
            L_0x018a:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                goto L_0x001b;
            L_0x0198:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                goto L_0x001b;
            L_0x01a6:
                r2 = r11.zzak(r3);
                r5 = r2 & r10;
                r8 = (long) r5;
                r5 = com.google.android.gms.internal.vision.zzfl.zzj(r12, r8);
                r2 = r2 & r10;
                r8 = (long) r2;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r13, r8);
                if (r5 != r2) goto L_0x01c7;
            L_0x01b9:
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r12, r6);
                r5 = com.google.android.gms.internal.vision.zzfl.zzo(r13, r6);
                r2 = com.google.android.gms.internal.vision.zzep.zzd(r2, r5);
                if (r2 != 0) goto L_0x001a;
            L_0x01c7:
                r2 = r0;
                goto L_0x001b;
            L_0x01ca:
                r2 = r3 + 3;
                r3 = r2;
                goto L_0x0009;
            L_0x01cf:
                r2 = r11.zzns;
                r2 = r2.zzr(r12);
                r3 = r11.zzns;
                r3 = r3.zzr(r13);
                r2 = r2.equals(r3);
                if (r2 == 0) goto L_0x001d;
            L_0x01e1:
                r0 = r11.zznj;
                if (r0 == 0) goto L_0x01f7;
            L_0x01e5:
                r0 = r11.zznt;
                r0 = r0.zzb(r12);
                r1 = r11.zznt;
                r1 = r1.zzb(r13);
                r0 = r0.equals(r1);
                goto L_0x001d;
            L_0x01f7:
                r0 = r1;
                goto L_0x001d;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.equals(java.lang.Object, java.lang.Object):boolean");
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final int hashCode(T r10) {
                /*
                r9 = this;
                r1 = 37;
                r0 = 0;
                r2 = r9.zzne;
                r4 = r2.length;
                r3 = r0;
                r2 = r0;
            L_0x0008:
                if (r3 >= r4) goto L_0x0254;
            L_0x000a:
                r0 = r9.zzaj(r3);
                r5 = r9.zzne;
                r5 = r5[r3];
                r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
                r6 = r6 & r0;
                r6 = (long) r6;
                r8 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
                r0 = r0 & r8;
                r0 = r0 >>> 20;
                switch(r0) {
                    case 0: goto L_0x0024;
                    case 1: goto L_0x0034;
                    case 2: goto L_0x0040;
                    case 3: goto L_0x004c;
                    case 4: goto L_0x0058;
                    case 5: goto L_0x0060;
                    case 6: goto L_0x006c;
                    case 7: goto L_0x0074;
                    case 8: goto L_0x0080;
                    case 9: goto L_0x008e;
                    case 10: goto L_0x009c;
                    case 11: goto L_0x00a9;
                    case 12: goto L_0x00b2;
                    case 13: goto L_0x00bb;
                    case 14: goto L_0x00c4;
                    case 15: goto L_0x00d1;
                    case 16: goto L_0x00da;
                    case 17: goto L_0x00e7;
                    case 18: goto L_0x00f6;
                    case 19: goto L_0x00f6;
                    case 20: goto L_0x00f6;
                    case 21: goto L_0x00f6;
                    case 22: goto L_0x00f6;
                    case 23: goto L_0x00f6;
                    case 24: goto L_0x00f6;
                    case 25: goto L_0x00f6;
                    case 26: goto L_0x00f6;
                    case 27: goto L_0x00f6;
                    case 28: goto L_0x00f6;
                    case 29: goto L_0x00f6;
                    case 30: goto L_0x00f6;
                    case 31: goto L_0x00f6;
                    case 32: goto L_0x00f6;
                    case 33: goto L_0x00f6;
                    case 34: goto L_0x00f6;
                    case 35: goto L_0x00f6;
                    case 36: goto L_0x00f6;
                    case 37: goto L_0x00f6;
                    case 38: goto L_0x00f6;
                    case 39: goto L_0x00f6;
                    case 40: goto L_0x00f6;
                    case 41: goto L_0x00f6;
                    case 42: goto L_0x00f6;
                    case 43: goto L_0x00f6;
                    case 44: goto L_0x00f6;
                    case 45: goto L_0x00f6;
                    case 46: goto L_0x00f6;
                    case 47: goto L_0x00f6;
                    case 48: goto L_0x00f6;
                    case 49: goto L_0x00f6;
                    case 50: goto L_0x0103;
                    case 51: goto L_0x0110;
                    case 52: goto L_0x0127;
                    case 53: goto L_0x013a;
                    case 54: goto L_0x014d;
                    case 55: goto L_0x0160;
                    case 56: goto L_0x016f;
                    case 57: goto L_0x0182;
                    case 58: goto L_0x0191;
                    case 59: goto L_0x01a4;
                    case 60: goto L_0x01b9;
                    case 61: goto L_0x01cc;
                    case 62: goto L_0x01df;
                    case 63: goto L_0x01ee;
                    case 64: goto L_0x01fd;
                    case 65: goto L_0x020c;
                    case 66: goto L_0x021f;
                    case 67: goto L_0x022e;
                    case 68: goto L_0x0241;
                    default: goto L_0x001f;
                };
            L_0x001f:
                r0 = r2;
            L_0x0020:
                r3 = r3 + 3;
                r2 = r0;
                goto L_0x0008;
            L_0x0024:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzn(r10, r6);
                r6 = java.lang.Double.doubleToLongBits(r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0034:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzm(r10, r6);
                r2 = java.lang.Float.floatToIntBits(r2);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0040:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x004c:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0058:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0060:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x006c:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0074:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzl(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzc(r2);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0080:
                r2 = r2 * 53;
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r0 = (java.lang.String) r0;
                r0 = r0.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x008e:
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                if (r0 == 0) goto L_0x0276;
            L_0x0094:
                r0 = r0.hashCode();
            L_0x0098:
                r2 = r2 * 53;
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x009c:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00a9:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00b2:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00bb:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00c4:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00d1:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzj(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00da:
                r0 = r2 * 53;
                r6 = com.google.android.gms.internal.vision.zzfl.zzk(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00e7:
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                if (r0 == 0) goto L_0x0273;
            L_0x00ed:
                r0 = r0.hashCode();
            L_0x00f1:
                r2 = r2 * 53;
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x00f6:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0103:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0110:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0116:
                r0 = r2 * 53;
                r6 = zze(r10, r6);
                r6 = java.lang.Double.doubleToLongBits(r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0127:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x012d:
                r0 = r2 * 53;
                r2 = zzf(r10, r6);
                r2 = java.lang.Float.floatToIntBits(r2);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x013a:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0140:
                r0 = r2 * 53;
                r6 = zzh(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x014d:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0153:
                r0 = r2 * 53;
                r6 = zzh(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0160:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0166:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x016f:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0175:
                r0 = r2 * 53;
                r6 = zzh(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0182:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0188:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0191:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0197:
                r0 = r2 * 53;
                r2 = zzi(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzc(r2);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01a4:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x01aa:
                r2 = r2 * 53;
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r0 = (java.lang.String) r0;
                r0 = r0.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01b9:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x01bf:
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2 * 53;
                r0 = r0.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01cc:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x01d2:
                r0 = r2 * 53;
                r2 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01df:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x01e5:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01ee:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x01f4:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x01fd:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0203:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x020c:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0212:
                r0 = r2 * 53;
                r6 = zzh(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x021f:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0225:
                r0 = r2 * 53;
                r2 = zzg(r10, r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x022e:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0234:
                r0 = r2 * 53;
                r6 = zzh(r10, r6);
                r2 = com.google.android.gms.internal.vision.zzct.zzk(r6);
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0241:
                r0 = r9.zza(r10, r5, r3);
                if (r0 == 0) goto L_0x001f;
            L_0x0247:
                r0 = com.google.android.gms.internal.vision.zzfl.zzo(r10, r6);
                r2 = r2 * 53;
                r0 = r0.hashCode();
                r0 = r0 + r2;
                goto L_0x0020;
            L_0x0254:
                r0 = r2 * 53;
                r1 = r9.zzns;
                r1 = r1.zzr(r10);
                r1 = r1.hashCode();
                r0 = r0 + r1;
                r1 = r9.zznj;
                if (r1 == 0) goto L_0x0272;
            L_0x0265:
                r0 = r0 * 53;
                r1 = r9.zznt;
                r1 = r1.zzb(r10);
                r1 = r1.hashCode();
                r0 = r0 + r1;
            L_0x0272:
                return r0;
            L_0x0273:
                r0 = r1;
                goto L_0x00f1;
            L_0x0276:
                r0 = r1;
                goto L_0x0098;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.hashCode(java.lang.Object):int");
            }

            public final T newInstance() {
                return this.zznq.newInstance(this.zzni);
            }

            public final void zza(T t, zzfz zzfz) throws IOException {
                Iterator it;
                Entry entry;
                zzcj zzb;
                int length;
                int zzaj;
                int i;
                Entry entry2;
                if (zzfz.zzbc() == zzd.zzlk) {
                    zza(this.zzns, (Object) t, zzfz);
                    it = null;
                    entry = null;
                    if (this.zznj) {
                        zzb = this.zznt.zzb(t);
                        if (!zzb.isEmpty()) {
                            it = zzb.descendingIterator();
                            entry = (Entry) it.next();
                        }
                    }
                    length = this.zzne.length - 3;
                    while (length >= 0) {
                        zzaj = zzaj(length);
                        i = this.zzne[length];
                        entry2 = entry;
                        while (entry2 != null && this.zznt.zza(entry2) > i) {
                            this.zznt.zza(zzfz, entry2);
                            entry2 = it.hasNext() ? (Entry) it.next() : null;
                        }
                        switch ((267386880 & zzaj) >>> 20) {
                            case 0:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzfl.zzn(t, (long) (1048575 & zzaj)));
                                break;
                            case 1:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzfl.zzm(t, (long) (1048575 & zzaj)));
                                break;
                            case 2:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzi(i, zzfl.zzk(t, (long) (1048575 & zzaj)));
                                break;
                            case 3:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzfl.zzk(t, (long) (1048575 & zzaj)));
                                break;
                            case 4:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zze(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 5:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzc(i, zzfl.zzk(t, (long) (1048575 & zzaj)));
                                break;
                            case 6:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzh(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 7:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzfl.zzl(t, (long) (1048575 & zzaj)));
                                break;
                            case 8:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zza(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz);
                                break;
                            case 9:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzag(length));
                                break;
                            case 10:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i, (zzbo) zzfl.zzo(t, (long) (1048575 & zzaj)));
                                break;
                            case 11:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzf(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 12:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzp(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 13:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzo(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 14:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzj(i, zzfl.zzk(t, (long) (1048575 & zzaj)));
                                break;
                            case 15:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzg(i, zzfl.zzj(t, (long) (1048575 & zzaj)));
                                break;
                            case 16:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzfl.zzk(t, (long) (1048575 & zzaj)));
                                break;
                            case 17:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzag(length));
                                break;
                            case 18:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 19:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 20:
                                zzep.zzc(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 21:
                                zzep.zzd(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 22:
                                zzep.zzh(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 23:
                                zzep.zzf(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 24:
                                zzep.zzk(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 25:
                                zzep.zzn(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 26:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz);
                                break;
                            case 27:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, zzag(length));
                                break;
                            case 28:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                                zzep.zzi(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                                zzep.zzm(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                                zzep.zzl(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 32:
                                zzep.zzg(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 33:
                                zzep.zzj(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 34:
                                zzep.zze(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, false);
                                break;
                            case 35:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 36:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 37:
                                zzep.zzc(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 38:
                                zzep.zzd(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 39:
                                zzep.zzh(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                                zzep.zzf(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 41:
                                zzep.zzk(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 42:
                                zzep.zzn(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 43:
                                zzep.zzi(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 44:
                                zzep.zzm(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                                zzep.zzl(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                                zzep.zzg(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                                zzep.zzj(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 48:
                                zzep.zze(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, true);
                                break;
                            case 49:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz, zzag(length));
                                break;
                            case 50:
                                zza(zzfz, i, zzfl.zzo(t, (long) (1048575 & zzaj)), length);
                                break;
                            case 51:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zza(i, zze(t, (long) (1048575 & zzaj)));
                                break;
                            case 52:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzf(t, (long) (1048575 & zzaj)));
                                break;
                            case 53:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzi(i, zzh(t, (long) (1048575 & zzaj)));
                                break;
                            case 54:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzh(t, (long) (1048575 & zzaj)));
                                break;
                            case 55:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zze(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case 56:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzc(i, zzh(t, (long) (1048575 & zzaj)));
                                break;
                            case 57:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzh(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case 58:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzi(t, (long) (1048575 & zzaj)));
                                break;
                            case 59:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zza(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzfz);
                                break;
                            case 60:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zza(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzag(length));
                                break;
                            case 61:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zza(i, (zzbo) zzfl.zzo(t, (long) (1048575 & zzaj)));
                                break;
                            case 62:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzf(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzp(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case 64:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzo(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case VoIPService.CALL_MIN_LAYER /*65*/:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzj(i, zzh(t, (long) (1048575 & zzaj)));
                                break;
                            case 66:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzg(i, zzg(t, (long) (1048575 & zzaj)));
                                break;
                            case 67:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzh(t, (long) (1048575 & zzaj)));
                                break;
                            case 68:
                                if (!zza((Object) t, i, length)) {
                                    break;
                                }
                                zzfz.zzb(i, zzfl.zzo(t, (long) (1048575 & zzaj)), zzag(length));
                                break;
                            default:
                                break;
                        }
                        length -= 3;
                        entry = entry2;
                    }
                    while (entry != null) {
                        this.zznt.zza(zzfz, entry);
                        entry = it.hasNext() ? (Entry) it.next() : null;
                    }
                } else if (this.zznl) {
                    it = null;
                    entry = null;
                    if (this.zznj) {
                        zzb = this.zznt.zzb(t);
                        if (!zzb.isEmpty()) {
                            it = zzb.iterator();
                            entry = (Entry) it.next();
                        }
                    }
                    zzaj = this.zzne.length;
                    length = 0;
                    while (length < zzaj) {
                        i = zzaj(length);
                        int i2 = this.zzne[length];
                        entry2 = entry;
                        while (entry2 != null && this.zznt.zza(entry2) <= i2) {
                            this.zznt.zza(zzfz, entry2);
                            entry2 = it.hasNext() ? (Entry) it.next() : null;
                        }
                        switch ((267386880 & i) >>> 20) {
                            case 0:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzfl.zzn(t, (long) (1048575 & i)));
                                break;
                            case 1:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzfl.zzm(t, (long) (1048575 & i)));
                                break;
                            case 2:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzi(i2, zzfl.zzk(t, (long) (1048575 & i)));
                                break;
                            case 3:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzfl.zzk(t, (long) (1048575 & i)));
                                break;
                            case 4:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zze(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 5:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzc(i2, zzfl.zzk(t, (long) (1048575 & i)));
                                break;
                            case 6:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzh(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 7:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzfl.zzl(t, (long) (1048575 & i)));
                                break;
                            case 8:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zza(i2, zzfl.zzo(t, (long) (1048575 & i)), zzfz);
                                break;
                            case 9:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzfl.zzo(t, (long) (1048575 & i)), zzag(length));
                                break;
                            case 10:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zza(i2, (zzbo) zzfl.zzo(t, (long) (1048575 & i)));
                                break;
                            case 11:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzf(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 12:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzp(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 13:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzo(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 14:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzj(i2, zzfl.zzk(t, (long) (1048575 & i)));
                                break;
                            case 15:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzg(i2, zzfl.zzj(t, (long) (1048575 & i)));
                                break;
                            case 16:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzfl.zzk(t, (long) (1048575 & i)));
                                break;
                            case 17:
                                if (!zza((Object) t, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzfl.zzo(t, (long) (1048575 & i)), zzag(length));
                                break;
                            case 18:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 19:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 20:
                                zzep.zzc(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 21:
                                zzep.zzd(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 22:
                                zzep.zzh(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 23:
                                zzep.zzf(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 24:
                                zzep.zzk(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 25:
                                zzep.zzn(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 26:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz);
                                break;
                            case 27:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, zzag(length));
                                break;
                            case 28:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                                zzep.zzi(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                                zzep.zzm(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                                zzep.zzl(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 32:
                                zzep.zzg(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 33:
                                zzep.zzj(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 34:
                                zzep.zze(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, false);
                                break;
                            case 35:
                                zzep.zza(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 36:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 37:
                                zzep.zzc(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 38:
                                zzep.zzd(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 39:
                                zzep.zzh(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                                zzep.zzf(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 41:
                                zzep.zzk(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 42:
                                zzep.zzn(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 43:
                                zzep.zzi(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 44:
                                zzep.zzm(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                                zzep.zzl(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                                zzep.zzg(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                                zzep.zzj(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 48:
                                zzep.zze(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, true);
                                break;
                            case 49:
                                zzep.zzb(this.zzne[length], (List) zzfl.zzo(t, (long) (1048575 & i)), zzfz, zzag(length));
                                break;
                            case 50:
                                zza(zzfz, i2, zzfl.zzo(t, (long) (1048575 & i)), length);
                                break;
                            case 51:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zze(t, (long) (1048575 & i)));
                                break;
                            case 52:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzf(t, (long) (1048575 & i)));
                                break;
                            case 53:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzi(i2, zzh(t, (long) (1048575 & i)));
                                break;
                            case 54:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzh(t, (long) (1048575 & i)));
                                break;
                            case 55:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zze(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case 56:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzc(i2, zzh(t, (long) (1048575 & i)));
                                break;
                            case 57:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzh(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case 58:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzi(t, (long) (1048575 & i)));
                                break;
                            case 59:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zza(i2, zzfl.zzo(t, (long) (1048575 & i)), zzfz);
                                break;
                            case 60:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zza(i2, zzfl.zzo(t, (long) (1048575 & i)), zzag(length));
                                break;
                            case 61:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zza(i2, (zzbo) zzfl.zzo(t, (long) (1048575 & i)));
                                break;
                            case 62:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzf(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzp(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case 64:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzo(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case VoIPService.CALL_MIN_LAYER /*65*/:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzj(i2, zzh(t, (long) (1048575 & i)));
                                break;
                            case 66:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzg(i2, zzg(t, (long) (1048575 & i)));
                                break;
                            case 67:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzh(t, (long) (1048575 & i)));
                                break;
                            case 68:
                                if (!zza((Object) t, i2, length)) {
                                    break;
                                }
                                zzfz.zzb(i2, zzfl.zzo(t, (long) (1048575 & i)), zzag(length));
                                break;
                            default:
                                break;
                        }
                        length += 3;
                        entry = entry2;
                    }
                    while (entry != null) {
                        this.zznt.zza(zzfz, entry);
                        entry = it.hasNext() ? (Entry) it.next() : null;
                    }
                    zza(this.zzns, (Object) t, zzfz);
                } else {
                    zzb((Object) t, zzfz);
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void zza(T r35, byte[] r36, int r37, int r38, com.google.android.gms.internal.vision.zzbl r39) throws java.io.IOException {
                /*
                r34 = this;
                r0 = r34;
                r4 = r0.zznl;
                if (r4 == 0) goto L_0x0284;
            L_0x0006:
                r4 = zznd;
                r14 = -1;
                r16 = 0;
                r6 = r14;
                r7 = r37;
            L_0x000e:
                r0 = r38;
                if (r7 >= r0) goto L_0x027b;
            L_0x0012:
                r11 = r7 + 1;
                r13 = r36[r7];
                if (r13 >= 0) goto L_0x0024;
            L_0x0018:
                r0 = r36;
                r1 = r39;
                r11 = com.google.android.gms.internal.vision.zzbk.zza(r13, r0, r11, r1);
                r0 = r39;
                r13 = r0.zzgo;
            L_0x0024:
                r14 = r13 >>> 3;
                r15 = r13 & 7;
                if (r14 <= r6) goto L_0x0049;
            L_0x002a:
                r5 = r16 / 3;
                r0 = r34;
                r16 = r0.zzr(r14, r5);
            L_0x0032:
                r5 = -1;
                r0 = r16;
                if (r0 != r5) goto L_0x0050;
            L_0x0037:
                r16 = 0;
                r7 = r11;
            L_0x003a:
                r5 = r13;
                r6 = r36;
                r8 = r38;
                r9 = r35;
                r10 = r39;
                r7 = zza(r5, r6, r7, r8, r9, r10);
                r6 = r14;
                goto L_0x000e;
            L_0x0049:
                r0 = r34;
                r16 = r0.zzal(r14);
                goto L_0x0032;
            L_0x0050:
                r0 = r34;
                r5 = r0.zzne;
                r6 = r16 + 1;
                r28 = r5[r6];
                r5 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
                r5 = r5 & r28;
                r19 = r5 >>> 20;
                r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
                r5 = r5 & r28;
                r6 = (long) r5;
                r5 = 17;
                r0 = r19;
                if (r0 > r5) goto L_0x01d8;
            L_0x006a:
                switch(r19) {
                    case 0: goto L_0x006f;
                    case 1: goto L_0x0081;
                    case 2: goto L_0x0094;
                    case 3: goto L_0x0094;
                    case 4: goto L_0x00ac;
                    case 5: goto L_0x00c4;
                    case 6: goto L_0x00d7;
                    case 7: goto L_0x00ea;
                    case 8: goto L_0x010b;
                    case 9: goto L_0x0132;
                    case 10: goto L_0x016f;
                    case 11: goto L_0x00ac;
                    case 12: goto L_0x0188;
                    case 13: goto L_0x00d7;
                    case 14: goto L_0x00c4;
                    case 15: goto L_0x01a0;
                    case 16: goto L_0x01bc;
                    default: goto L_0x006d;
                };
            L_0x006d:
                r7 = r11;
                goto L_0x003a;
            L_0x006f:
                r5 = 1;
                if (r15 != r5) goto L_0x0295;
            L_0x0072:
                r0 = r36;
                r8 = com.google.android.gms.internal.vision.zzbk.zzc(r0, r11);
                r0 = r35;
                com.google.android.gms.internal.vision.zzfl.zza(r0, r6, r8);
                r7 = r11 + 8;
                r6 = r14;
                goto L_0x000e;
            L_0x0081:
                r5 = 5;
                if (r15 != r5) goto L_0x0295;
            L_0x0084:
                r0 = r36;
                r5 = com.google.android.gms.internal.vision.zzbk.zzd(r0, r11);
                r0 = r35;
                com.google.android.gms.internal.vision.zzfl.zza(r0, r6, r5);
                r7 = r11 + 4;
                r6 = r14;
                goto L_0x000e;
            L_0x0094:
                if (r15 != 0) goto L_0x0295;
            L_0x0096:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zzb(r0, r11, r1);
                r0 = r39;
                r8 = r0.zzgp;
                r5 = r35;
                r4.putLong(r5, r6, r8);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x00ac:
                if (r15 != 0) goto L_0x0295;
            L_0x00ae:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zza(r0, r11, r1);
                r0 = r39;
                r5 = r0.zzgo;
                r0 = r35;
                r4.putInt(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x00c4:
                r5 = 1;
                if (r15 != r5) goto L_0x0295;
            L_0x00c7:
                r0 = r36;
                r8 = com.google.android.gms.internal.vision.zzbk.zzb(r0, r11);
                r5 = r35;
                r4.putLong(r5, r6, r8);
                r7 = r11 + 8;
                r6 = r14;
                goto L_0x000e;
            L_0x00d7:
                r5 = 5;
                if (r15 != r5) goto L_0x0295;
            L_0x00da:
                r0 = r36;
                r5 = com.google.android.gms.internal.vision.zzbk.zza(r0, r11);
                r0 = r35;
                r4.putInt(r0, r6, r5);
                r7 = r11 + 4;
                r6 = r14;
                goto L_0x000e;
            L_0x00ea:
                if (r15 != 0) goto L_0x0295;
            L_0x00ec:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zzb(r0, r11, r1);
                r0 = r39;
                r8 = r0.zzgp;
                r10 = 0;
                r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
                if (r5 == 0) goto L_0x0109;
            L_0x00fe:
                r5 = 1;
            L_0x00ff:
                r0 = r35;
                com.google.android.gms.internal.vision.zzfl.zza(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x0109:
                r5 = 0;
                goto L_0x00ff;
            L_0x010b:
                r5 = 2;
                if (r15 != r5) goto L_0x0295;
            L_0x010e:
                r5 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
                r5 = r5 & r28;
                if (r5 != 0) goto L_0x0129;
            L_0x0114:
                r0 = r36;
                r1 = r39;
                r5 = com.google.android.gms.internal.vision.zzbk.zzc(r0, r11, r1);
            L_0x011c:
                r0 = r39;
                r8 = r0.zzgq;
                r0 = r35;
                r4.putObject(r0, r6, r8);
                r6 = r14;
                r7 = r5;
                goto L_0x000e;
            L_0x0129:
                r0 = r36;
                r1 = r39;
                r5 = com.google.android.gms.internal.vision.zzbk.zzd(r0, r11, r1);
                goto L_0x011c;
            L_0x0132:
                r5 = 2;
                if (r15 != r5) goto L_0x0295;
            L_0x0135:
                r0 = r34;
                r1 = r16;
                r5 = r0.zzag(r1);
                r0 = r36;
                r1 = r38;
                r2 = r39;
                r37 = zza(r5, r0, r11, r1, r2);
                r0 = r35;
                r5 = r4.getObject(r0, r6);
                if (r5 != 0) goto L_0x015d;
            L_0x014f:
                r0 = r39;
                r5 = r0.zzgq;
                r0 = r35;
                r4.putObject(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x015d:
                r0 = r39;
                r8 = r0.zzgq;
                r5 = com.google.android.gms.internal.vision.zzct.zza(r5, r8);
                r0 = r35;
                r4.putObject(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x016f:
                r5 = 2;
                if (r15 != r5) goto L_0x0295;
            L_0x0172:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zze(r0, r11, r1);
                r0 = r39;
                r5 = r0.zzgq;
                r0 = r35;
                r4.putObject(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x0188:
                if (r15 != 0) goto L_0x0295;
            L_0x018a:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zza(r0, r11, r1);
                r0 = r39;
                r5 = r0.zzgo;
                r0 = r35;
                r4.putInt(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x01a0:
                if (r15 != 0) goto L_0x0295;
            L_0x01a2:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zza(r0, r11, r1);
                r0 = r39;
                r5 = r0.zzgo;
                r5 = com.google.android.gms.internal.vision.zzbx.zzo(r5);
                r0 = r35;
                r4.putInt(r0, r6, r5);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x01bc:
                if (r15 != 0) goto L_0x0295;
            L_0x01be:
                r0 = r36;
                r1 = r39;
                r37 = com.google.android.gms.internal.vision.zzbk.zzb(r0, r11, r1);
                r0 = r39;
                r8 = r0.zzgp;
                r8 = com.google.android.gms.internal.vision.zzbx.zza(r8);
                r5 = r35;
                r4.putLong(r5, r6, r8);
                r6 = r14;
                r7 = r37;
                goto L_0x000e;
            L_0x01d8:
                r5 = 27;
                r0 = r19;
                if (r0 != r5) goto L_0x021a;
            L_0x01de:
                r5 = 2;
                if (r15 != r5) goto L_0x0295;
            L_0x01e1:
                r0 = r35;
                r5 = r4.getObject(r0, r6);
                r5 = (com.google.android.gms.internal.vision.zzcw) r5;
                r8 = r5.zzan();
                if (r8 != 0) goto L_0x0298;
            L_0x01ef:
                r8 = r5.size();
                if (r8 != 0) goto L_0x0217;
            L_0x01f5:
                r8 = 10;
            L_0x01f7:
                r10 = r5.zzk(r8);
                r0 = r35;
                r4.putObject(r0, r6, r10);
            L_0x0200:
                r0 = r34;
                r1 = r16;
                r5 = r0.zzag(r1);
                r6 = r13;
                r7 = r36;
                r8 = r11;
                r9 = r38;
                r11 = r39;
                r7 = zza(r5, r6, r7, r8, r9, r10, r11);
                r6 = r14;
                goto L_0x000e;
            L_0x0217:
                r8 = r8 << 1;
                goto L_0x01f7;
            L_0x021a:
                r5 = 49;
                r0 = r19;
                if (r0 > r5) goto L_0x023a;
            L_0x0220:
                r0 = r28;
                r0 = (long) r0;
                r17 = r0;
                r8 = r34;
                r9 = r35;
                r10 = r36;
                r12 = r38;
                r20 = r6;
                r22 = r39;
                r7 = r8.zza(r9, r10, r11, r12, r13, r14, r15, r16, r17, r19, r20, r22);
                if (r7 == r11) goto L_0x003a;
            L_0x0237:
                r6 = r14;
                goto L_0x000e;
            L_0x023a:
                r5 = 50;
                r0 = r19;
                if (r0 != r5) goto L_0x025b;
            L_0x0240:
                r5 = 2;
                if (r15 != r5) goto L_0x0295;
            L_0x0243:
                r18 = r34;
                r19 = r35;
                r20 = r36;
                r21 = r11;
                r22 = r38;
                r23 = r16;
                r24 = r6;
                r26 = r39;
                r7 = r18.zza(r19, r20, r21, r22, r23, r24, r26);
                if (r7 != r11) goto L_0x0237;
            L_0x0259:
                goto L_0x003a;
            L_0x025b:
                r20 = r34;
                r21 = r35;
                r22 = r36;
                r23 = r11;
                r24 = r38;
                r25 = r13;
                r26 = r14;
                r27 = r15;
                r29 = r19;
                r30 = r6;
                r32 = r16;
                r33 = r39;
                r7 = r20.zza(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r32, r33);
                if (r7 != r11) goto L_0x0237;
            L_0x0279:
                goto L_0x003a;
            L_0x027b:
                r0 = r38;
                if (r7 == r0) goto L_0x0294;
            L_0x027f:
                r4 = com.google.android.gms.internal.vision.zzcx.zzcf();
                throw r4;
            L_0x0284:
                r9 = 0;
                r4 = r34;
                r5 = r35;
                r6 = r36;
                r7 = r37;
                r8 = r38;
                r10 = r39;
                r4.zza(r5, r6, r7, r8, r9, r10);
            L_0x0294:
                return;
            L_0x0295:
                r7 = r11;
                goto L_0x003a;
            L_0x0298:
                r10 = r5;
                goto L_0x0200;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzbl):void");
            }

            public final void zzc(T t, T t2) {
                if (t2 == null) {
                    throw new NullPointerException();
                }
                for (int i = 0; i < this.zzne.length; i += 3) {
                    int zzaj = zzaj(i);
                    long j = (long) (1048575 & zzaj);
                    int i2 = this.zzne[i];
                    switch ((zzaj & 267386880) >>> 20) {
                        case 0:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzn(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 1:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzm(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 2:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 3:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 4:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 5:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 6:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 7:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzl(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 8:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzo(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 9:
                            zza((Object) t, (Object) t2, i);
                            break;
                        case 10:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzo(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 11:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 12:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 13:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 14:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 15:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 16:
                            if (!zza((Object) t2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                            zzb((Object) t, i);
                            break;
                        case 17:
                            zza((Object) t, (Object) t2, i);
                            break;
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                        case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                        case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                        case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                        case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                        case 48:
                        case 49:
                            this.zznr.zza(t, t2, j);
                            break;
                        case 50:
                            zzep.zza(this.zznu, (Object) t, (Object) t2, j);
                            break;
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                            if (!zza((Object) t2, i2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzo(t2, j));
                            zzb((Object) t, i2, i);
                            break;
                        case 60:
                            zzb((Object) t, (Object) t2, i);
                            break;
                        case 61:
                        case 62:
                        case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                        case 64:
                        case VoIPService.CALL_MIN_LAYER /*65*/:
                        case 66:
                        case 67:
                            if (!zza((Object) t2, i2, i)) {
                                break;
                            }
                            zzfl.zza((Object) t, j, zzfl.zzo(t2, j));
                            zzb((Object) t, i2, i);
                            break;
                        case 68:
                            zzb((Object) t, (Object) t2, i);
                            break;
                        default:
                            break;
                    }
                }
                if (!this.zznl) {
                    zzep.zza(this.zzns, (Object) t, (Object) t2);
                    if (this.zznj) {
                        zzep.zza(this.zznt, (Object) t, (Object) t2);
                    }
                }
            }

            public final void zzd(T t) {
                int i;
                for (i = this.zzno; i < this.zznp; i++) {
                    long zzaj = (long) (zzaj(this.zznn[i]) & 1048575);
                    Object zzo = zzfl.zzo(t, zzaj);
                    if (zzo != null) {
                        zzfl.zza((Object) t, zzaj, this.zznu.zzk(zzo));
                    }
                }
                int length = this.zznn.length;
                for (i = this.zznp; i < length; i++) {
                    this.zznr.zza(t, (long) this.zznn[i]);
                }
                this.zzns.zzd(t);
                if (this.zznj) {
                    this.zznt.zzd(t);
                }
            }

            public final int zzn(T t) {
                int i;
                int i2;
                int zzaj;
                int i3;
                int i4;
                int i5;
                Object zzo;
                if (this.zznl) {
                    Unsafe unsafe = zznd;
                    i = 0;
                    for (i2 = 0; i2 < this.zzne.length; i2 += 3) {
                        zzaj = zzaj(i2);
                        i3 = (267386880 & zzaj) >>> 20;
                        i4 = this.zzne[i2];
                        long j = (long) (zzaj & 1048575);
                        i5 = (i3 < zzcm.DOUBLE_LIST_PACKED.id() || i3 > zzcm.SINT64_LIST_PACKED.id()) ? 0 : this.zzne[i2 + 2] & 1048575;
                        switch (i3) {
                            case 0:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzb(i4, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                break;
                            case 1:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzb(i4, 0.0f);
                                break;
                            case 2:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzd(i4, zzfl.zzk(t, j));
                                break;
                            case 3:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zze(i4, zzfl.zzk(t, j));
                                break;
                            case 4:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzi(i4, zzfl.zzj(t, j));
                                break;
                            case 5:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzg(i4, 0);
                                break;
                            case 6:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzl(i4, 0);
                                break;
                            case 7:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, true);
                                break;
                            case 8:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                zzo = zzfl.zzo(t, j);
                                if (!(zzo instanceof zzbo)) {
                                    i += zzca.zzb(i4, (String) zzo);
                                    break;
                                }
                                i += zzca.zzc(i4, (zzbo) zzo);
                                break;
                            case 9:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzep.zzc(i4, zzfl.zzo(t, j), zzag(i2));
                                break;
                            case 10:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, (zzbo) zzfl.zzo(t, j));
                                break;
                            case 11:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzj(i4, zzfl.zzj(t, j));
                                break;
                            case 12:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzn(i4, zzfl.zzj(t, j));
                                break;
                            case 13:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzm(i4, 0);
                                break;
                            case 14:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzh(i4, 0);
                                break;
                            case 15:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzk(i4, zzfl.zzj(t, j));
                                break;
                            case 16:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzf(i4, zzfl.zzk(t, j));
                                break;
                            case 17:
                                if (!zza((Object) t, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, (zzdx) zzfl.zzo(t, j), zzag(i2));
                                break;
                            case 18:
                                i += zzep.zzw(i4, zzd(t, j), false);
                                break;
                            case 19:
                                i += zzep.zzv(i4, zzd(t, j), false);
                                break;
                            case 20:
                                i += zzep.zzo(i4, zzd(t, j), false);
                                break;
                            case 21:
                                i += zzep.zzp(i4, zzd(t, j), false);
                                break;
                            case 22:
                                i += zzep.zzs(i4, zzd(t, j), false);
                                break;
                            case 23:
                                i += zzep.zzw(i4, zzd(t, j), false);
                                break;
                            case 24:
                                i += zzep.zzv(i4, zzd(t, j), false);
                                break;
                            case 25:
                                i += zzep.zzx(i4, zzd(t, j), false);
                                break;
                            case 26:
                                i += zzep.zzc(i4, zzd(t, j));
                                break;
                            case 27:
                                i += zzep.zzc(i4, zzd(t, j), zzag(i2));
                                break;
                            case 28:
                                i += zzep.zzd(i4, zzd(t, j));
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                                i += zzep.zzt(i4, zzd(t, j), false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                                i += zzep.zzr(i4, zzd(t, j), false);
                                break;
                            case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                                i += zzep.zzv(i4, zzd(t, j), false);
                                break;
                            case 32:
                                i += zzep.zzw(i4, zzd(t, j), false);
                                break;
                            case 33:
                                i += zzep.zzu(i4, zzd(t, j), false);
                                break;
                            case 34:
                                i += zzep.zzq(i4, zzd(t, j), false);
                                break;
                            case 35:
                                zzaj = zzep.zzi((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 36:
                                zzaj = zzep.zzh((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 37:
                                zzaj = zzep.zza((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 38:
                                zzaj = zzep.zzb((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 39:
                                zzaj = zzep.zze((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                                zzaj = zzep.zzi((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 41:
                                zzaj = zzep.zzh((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 42:
                                zzaj = zzep.zzj((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 43:
                                zzaj = zzep.zzf((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 44:
                                zzaj = zzep.zzd((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                                zzaj = zzep.zzh((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                                zzaj = zzep.zzi((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                                zzaj = zzep.zzg((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 48:
                                zzaj = zzep.zzc((List) unsafe.getObject(t, j));
                                if (zzaj > 0) {
                                    if (this.zznm) {
                                        unsafe.putInt(t, (long) i5, zzaj);
                                    }
                                    i += zzaj + (zzca.zzt(i4) + zzca.zzv(zzaj));
                                    break;
                                }
                                break;
                            case 49:
                                i += zzep.zzd(i4, zzd(t, j), zzag(i2));
                                break;
                            case 50:
                                i += this.zznu.zzb(i4, zzfl.zzo(t, j), zzah(i2));
                                break;
                            case 51:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzb(i4, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                break;
                            case 52:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzb(i4, 0.0f);
                                break;
                            case 53:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzd(i4, zzh(t, j));
                                break;
                            case 54:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zze(i4, zzh(t, j));
                                break;
                            case 55:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzi(i4, zzg(t, j));
                                break;
                            case 56:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzg(i4, 0);
                                break;
                            case 57:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzl(i4, 0);
                                break;
                            case 58:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, true);
                                break;
                            case 59:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                zzo = zzfl.zzo(t, j);
                                if (!(zzo instanceof zzbo)) {
                                    i += zzca.zzb(i4, (String) zzo);
                                    break;
                                }
                                i += zzca.zzc(i4, (zzbo) zzo);
                                break;
                            case 60:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzep.zzc(i4, zzfl.zzo(t, j), zzag(i2));
                                break;
                            case 61:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, (zzbo) zzfl.zzo(t, j));
                                break;
                            case 62:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzj(i4, zzg(t, j));
                                break;
                            case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzn(i4, zzg(t, j));
                                break;
                            case 64:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzm(i4, 0);
                                break;
                            case VoIPService.CALL_MIN_LAYER /*65*/:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzh(i4, 0);
                                break;
                            case 66:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzk(i4, zzg(t, j));
                                break;
                            case 67:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzf(i4, zzh(t, j));
                                break;
                            case 68:
                                if (!zza((Object) t, i4, i2)) {
                                    break;
                                }
                                i += zzca.zzc(i4, (zzdx) zzfl.zzo(t, j), zzag(i2));
                                break;
                            default:
                                break;
                        }
                    }
                    return zza(this.zzns, (Object) t) + i;
                }
                int i6 = 0;
                Unsafe unsafe2 = zznd;
                i5 = -1;
                i = 0;
                for (i2 = 0; i2 < this.zzne.length; i2 += 3) {
                    int zzaj2 = zzaj(i2);
                    int i7 = this.zzne[i2];
                    int i8 = (267386880 & zzaj2) >>> 20;
                    zzaj = 0;
                    if (i8 <= 17) {
                        i4 = this.zzne[i2 + 2];
                        zzaj = 1048575 & i4;
                        i3 = 1 << (i4 >>> 20);
                        if (zzaj != i5) {
                            i = unsafe2.getInt(t, (long) zzaj);
                            i5 = zzaj;
                        }
                        zzaj = i3;
                    } else {
                        i4 = (!this.zznm || i8 < zzcm.DOUBLE_LIST_PACKED.id() || i8 > zzcm.SINT64_LIST_PACKED.id()) ? 0 : this.zzne[i2 + 2] & 1048575;
                    }
                    long j2 = (long) (1048575 & zzaj2);
                    switch (i8) {
                        case 0:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzb(i7, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            break;
                        case 1:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzb(i7, 0.0f);
                            break;
                        case 2:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzd(i7, unsafe2.getLong(t, j2));
                            break;
                        case 3:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zze(i7, unsafe2.getLong(t, j2));
                            break;
                        case 4:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzi(i7, unsafe2.getInt(t, j2));
                            break;
                        case 5:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzg(i7, 0);
                            break;
                        case 6:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzl(i7, 0);
                            break;
                        case 7:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzc(i7, true);
                            break;
                        case 8:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            zzo = unsafe2.getObject(t, j2);
                            if (!(zzo instanceof zzbo)) {
                                i6 += zzca.zzb(i7, (String) zzo);
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzbo) zzo);
                            break;
                        case 9:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzep.zzc(i7, unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        case 10:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzbo) unsafe2.getObject(t, j2));
                            break;
                        case 11:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzj(i7, unsafe2.getInt(t, j2));
                            break;
                        case 12:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzn(i7, unsafe2.getInt(t, j2));
                            break;
                        case 13:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzm(i7, 0);
                            break;
                        case 14:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzh(i7, 0);
                            break;
                        case 15:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzk(i7, unsafe2.getInt(t, j2));
                            break;
                        case 16:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzf(i7, unsafe2.getLong(t, j2));
                            break;
                        case 17:
                            if ((zzaj & i) == 0) {
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzdx) unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        case 18:
                            i6 += zzep.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i6 += zzep.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i6 += zzep.zzo(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i6 += zzep.zzp(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i6 += zzep.zzs(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i6 += zzep.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i6 += zzep.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i6 += zzep.zzx(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i6 += zzep.zzc(i7, (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i6 += zzep.zzc(i7, (List) unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        case 28:
                            i6 += zzep.zzd(i7, (List) unsafe2.getObject(t, j2));
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                            i6 += zzep.zzt(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                            i6 += zzep.zzr(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                            i6 += zzep.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i6 += zzep.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i6 += zzep.zzu(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i6 += zzep.zzq(i7, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            zzaj = zzep.zzi((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 36:
                            zzaj = zzep.zzh((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 37:
                            zzaj = zzep.zza((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 38:
                            zzaj = zzep.zzb((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 39:
                            zzaj = zzep.zze((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                            zzaj = zzep.zzi((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 41:
                            zzaj = zzep.zzh((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 42:
                            zzaj = zzep.zzj((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 43:
                            zzaj = zzep.zzf((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 44:
                            zzaj = zzep.zzd((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                            zzaj = zzep.zzh((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                            zzaj = zzep.zzi((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                            zzaj = zzep.zzg((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 48:
                            zzaj = zzep.zzc((List) unsafe2.getObject(t, j2));
                            if (zzaj > 0) {
                                if (this.zznm) {
                                    unsafe2.putInt(t, (long) i4, zzaj);
                                }
                                i6 += zzaj + (zzca.zzt(i7) + zzca.zzv(zzaj));
                                break;
                            }
                            break;
                        case 49:
                            i6 += zzep.zzd(i7, (List) unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        case 50:
                            i6 += this.zznu.zzb(i7, unsafe2.getObject(t, j2), zzah(i2));
                            break;
                        case 51:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzb(i7, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            break;
                        case 52:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzb(i7, 0.0f);
                            break;
                        case 53:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzd(i7, zzh(t, j2));
                            break;
                        case 54:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zze(i7, zzh(t, j2));
                            break;
                        case 55:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzi(i7, zzg(t, j2));
                            break;
                        case 56:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzg(i7, 0);
                            break;
                        case 57:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzl(i7, 0);
                            break;
                        case 58:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzc(i7, true);
                            break;
                        case 59:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            zzo = unsafe2.getObject(t, j2);
                            if (!(zzo instanceof zzbo)) {
                                i6 += zzca.zzb(i7, (String) zzo);
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzbo) zzo);
                            break;
                        case 60:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzep.zzc(i7, unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        case 61:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzbo) unsafe2.getObject(t, j2));
                            break;
                        case 62:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzj(i7, zzg(t, j2));
                            break;
                        case HtmlCompat.FROM_HTML_MODE_COMPACT /*63*/:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzn(i7, zzg(t, j2));
                            break;
                        case 64:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzm(i7, 0);
                            break;
                        case VoIPService.CALL_MIN_LAYER /*65*/:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzh(i7, 0);
                            break;
                        case 66:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzk(i7, zzg(t, j2));
                            break;
                        case 67:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzf(i7, zzh(t, j2));
                            break;
                        case 68:
                            if (!zza((Object) t, i7, i2)) {
                                break;
                            }
                            i6 += zzca.zzc(i7, (zzdx) unsafe2.getObject(t, j2), zzag(i2));
                            break;
                        default:
                            break;
                    }
                }
                zzaj = zza(this.zzns, (Object) t) + i6;
                return this.zznj ? zzaj + this.zznt.zzb(t).zzbl() : zzaj;
            }

            public final boolean zzp(T t) {
                int i = 0;
                int i2 = -1;
                for (int i3 = 0; i3 < this.zzno; i3++) {
                    int i4;
                    int i5;
                    int i6 = this.zznn[i3];
                    int i7 = this.zzne[i6];
                    int zzaj = zzaj(i6);
                    if (this.zznl) {
                        i4 = 0;
                    } else {
                        i5 = this.zzne[i6 + 2];
                        int i8 = i5 & 1048575;
                        i5 = 1 << (i5 >>> 20);
                        if (i8 != i2) {
                            i = zznd.getInt(t, (long) i8);
                            i4 = i5;
                            i2 = i8;
                        } else {
                            i4 = i5;
                        }
                    }
                    if ((268435456 & zzaj) != 0) {
                        i5 = 1;
                    } else {
                        boolean z = false;
                    }
                    if (i5 != 0 && !zza((Object) t, i6, i, i4)) {
                        return false;
                    }
                    switch ((267386880 & zzaj) >>> 20) {
                        case 9:
                        case 17:
                            if (zza((Object) t, i6, i, i4) && !zza((Object) t, zzaj, zzag(i6))) {
                                return false;
                            }
                        case 27:
                        case 49:
                            List list = (List) zzfl.zzo(t, (long) (zzaj & 1048575));
                            if (!list.isEmpty()) {
                                zzen zzag = zzag(i6);
                                for (i4 = 0; i4 < list.size(); i4++) {
                                    if (!zzag.zzp(list.get(i4))) {
                                        z = false;
                                        if (z) {
                                            break;
                                        }
                                        return false;
                                    }
                                }
                            }
                            z = true;
                            if (z) {
                                return false;
                            }
                        case 50:
                            Map zzi = this.zznu.zzi(zzfl.zzo(t, (long) (zzaj & 1048575)));
                            if (!zzi.isEmpty()) {
                                if (this.zznu.zzm(zzah(i6)).zzmy.zzed() == zzfy.MESSAGE) {
                                    zzen zzen = null;
                                    for (Object next : zzi.values()) {
                                        if (zzen == null) {
                                            zzen = zzek.zzdc().zze(next.getClass());
                                        }
                                        if (!zzen.zzp(next)) {
                                            z = false;
                                            if (z) {
                                                break;
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }
                            z = true;
                            if (z) {
                                return false;
                            }
                        case 60:
                        case 68:
                            if (zza((Object) t, i7, i6) && !zza((Object) t, zzaj, zzag(i6))) {
                                return false;
                            }
                        default:
                            break;
                    }
                }
                return !this.zznj || this.zznt.zzb(t).isInitialized();
            }
        }
