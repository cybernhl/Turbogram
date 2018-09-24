package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzgj extends zzza<zzgj> {
    public long[] zzaye;
    public long[] zzayf;
    public zzge[] zzayg;
    public zzgk[] zzayh;

    public zzgj() {
        this.zzaye = zzzj.zzcfr;
        this.zzayf = zzzj.zzcfr;
        this.zzayg = zzge.zzmp();
        this.zzayh = zzgk.zzmt();
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgj)) {
            return false;
        }
        zzgj zzgj = (zzgj) obj;
        if (!zzze.equals(this.zzaye, zzgj.zzaye)) {
            return false;
        }
        if (!zzze.equals(this.zzayf, zzgj.zzayf)) {
            return false;
        }
        if (!zzze.equals(this.zzayg, zzgj.zzayg)) {
            return false;
        }
        if (!zzze.equals(this.zzayh, zzgj.zzayh)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgj.zzcfc);
        }
        if (zzgj.zzcfc == null || zzgj.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzze.hashCode(this.zzaye)) * 31) + zzze.hashCode(this.zzayf)) * 31) + zzze.hashCode(this.zzayg)) * 31) + zzze.hashCode(this.zzayh)) * 31;
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            i = 0;
        } else {
            i = this.zzcfc.hashCode();
        }
        return i + hashCode;
    }

    public final void zza(zzyy zzyy) throws IOException {
        int i = 0;
        if (this.zzaye != null && this.zzaye.length > 0) {
            for (long zza : this.zzaye) {
                zzyy.zza(1, zza);
            }
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            for (long zza2 : this.zzayf) {
                zzyy.zza(2, zza2);
            }
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            for (zzzg zzzg : this.zzayg) {
                if (zzzg != null) {
                    zzyy.zza(3, zzzg);
                }
            }
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            while (i < this.zzayh.length) {
                zzzg zzzg2 = this.zzayh[i];
                if (zzzg2 != null) {
                    zzyy.zza(4, zzzg2);
                }
                i++;
            }
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int i;
        int i2;
        int i3 = 0;
        int zzf = super.zzf();
        if (this.zzaye == null || this.zzaye.length <= 0) {
            i = zzf;
        } else {
            i2 = 0;
            for (long zzbi : this.zzaye) {
                i2 += zzyy.zzbi(zzbi);
            }
            i = (zzf + i2) + (this.zzaye.length * 1);
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            zzf = 0;
            for (long zzbi2 : this.zzayf) {
                zzf += zzyy.zzbi(zzbi2);
            }
            i = (i + zzf) + (this.zzayf.length * 1);
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            zzf = i;
            for (zzzg zzzg : this.zzayg) {
                if (zzzg != null) {
                    zzf += zzyy.zzb(3, zzzg);
                }
            }
            i = zzf;
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            while (i3 < this.zzayh.length) {
                zzzg zzzg2 = this.zzayh[i3];
                if (zzzg2 != null) {
                    i += zzyy.zzb(4, zzzg2);
                }
                i3++;
            }
        }
        return i;
    }

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            int zzb;
            Object obj;
            int zzaq;
            Object obj2;
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    zzb = zzzj.zzb(zzyx, 8);
                    if (this.zzaye == null) {
                        zzug = 0;
                    } else {
                        zzug = this.zzaye.length;
                    }
                    obj = new long[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzaye, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = zzyx.zzuz();
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = zzyx.zzuz();
                    this.zzaye = obj;
                    continue;
                case 10:
                    zzaq = zzyx.zzaq(zzyx.zzuy());
                    zzb = zzyx.getPosition();
                    zzug = 0;
                    while (zzyx.zzyr() > 0) {
                        zzyx.zzuz();
                        zzug++;
                    }
                    zzyx.zzby(zzb);
                    zzb = this.zzaye == null ? 0 : this.zzaye.length;
                    obj2 = new long[(zzug + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzaye, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = zzyx.zzuz();
                        zzb++;
                    }
                    this.zzaye = obj2;
                    zzyx.zzar(zzaq);
                    continue;
                case 16:
                    zzb = zzzj.zzb(zzyx, 16);
                    if (this.zzayf == null) {
                        zzug = 0;
                    } else {
                        zzug = this.zzayf.length;
                    }
                    obj = new long[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzayf, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = zzyx.zzuz();
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = zzyx.zzuz();
                    this.zzayf = obj;
                    continue;
                case 18:
                    zzaq = zzyx.zzaq(zzyx.zzuy());
                    zzb = zzyx.getPosition();
                    zzug = 0;
                    while (zzyx.zzyr() > 0) {
                        zzyx.zzuz();
                        zzug++;
                    }
                    zzyx.zzby(zzb);
                    zzb = this.zzayf == null ? 0 : this.zzayf.length;
                    obj2 = new long[(zzug + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzayf, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = zzyx.zzuz();
                        zzb++;
                    }
                    this.zzayf = obj2;
                    zzyx.zzar(zzaq);
                    continue;
                case 26:
                    zzb = zzzj.zzb(zzyx, 26);
                    zzug = this.zzayg == null ? 0 : this.zzayg.length;
                    obj = new zzge[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzayg, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzge();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzge();
                    zzyx.zza(obj[zzug]);
                    this.zzayg = obj;
                    continue;
                case 34:
                    zzb = zzzj.zzb(zzyx, 34);
                    zzug = this.zzayh == null ? 0 : this.zzayh.length;
                    obj = new zzgk[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzayh, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzgk();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzgk();
                    zzyx.zza(obj[zzug]);
                    this.zzayh = obj;
                    continue;
                default:
                    if (!super.zza(zzyx, zzug)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }
}
