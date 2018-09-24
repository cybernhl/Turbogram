package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzgb extends zzza<zzgb> {
    public String zzafx;
    public Long zzawe;
    private Integer zzawf;
    public zzgc[] zzawg;
    public zzga[] zzawh;
    public zzfu[] zzawi;
    private String zzawj;

    public zzgb() {
        this.zzawe = null;
        this.zzafx = null;
        this.zzawf = null;
        this.zzawg = zzgc.zzmn();
        this.zzawh = zzga.zzmm();
        this.zzawi = zzfu.zzmi();
        this.zzawj = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgb)) {
            return false;
        }
        zzgb zzgb = (zzgb) obj;
        if (this.zzawe == null) {
            if (zzgb.zzawe != null) {
                return false;
            }
        } else if (!this.zzawe.equals(zzgb.zzawe)) {
            return false;
        }
        if (this.zzafx == null) {
            if (zzgb.zzafx != null) {
                return false;
            }
        } else if (!this.zzafx.equals(zzgb.zzafx)) {
            return false;
        }
        if (this.zzawf == null) {
            if (zzgb.zzawf != null) {
                return false;
            }
        } else if (!this.zzawf.equals(zzgb.zzawf)) {
            return false;
        }
        if (!zzze.equals(this.zzawg, zzgb.zzawg)) {
            return false;
        }
        if (!zzze.equals(this.zzawh, zzgb.zzawh)) {
            return false;
        }
        if (!zzze.equals(this.zzawi, zzgb.zzawi)) {
            return false;
        }
        if (this.zzawj == null) {
            if (zzgb.zzawj != null) {
                return false;
            }
        } else if (!this.zzawj.equals(zzgb.zzawj)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgb.zzcfc);
        }
        if (zzgb.zzcfc == null || zzgb.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzawj == null ? 0 : this.zzawj.hashCode()) + (((((((((this.zzawf == null ? 0 : this.zzawf.hashCode()) + (((this.zzafx == null ? 0 : this.zzafx.hashCode()) + (((this.zzawe == null ? 0 : this.zzawe.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzze.hashCode(this.zzawg)) * 31) + zzze.hashCode(this.zzawh)) * 31) + zzze.hashCode(this.zzawi)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        int i = 0;
        if (this.zzawe != null) {
            zzyy.zzi(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            zzyy.zzb(2, this.zzafx);
        }
        if (this.zzawf != null) {
            zzyy.zzd(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            for (zzzg zzzg : this.zzawg) {
                if (zzzg != null) {
                    zzyy.zza(4, zzzg);
                }
            }
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            for (zzzg zzzg2 : this.zzawh) {
                if (zzzg2 != null) {
                    zzyy.zza(5, zzzg2);
                }
            }
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            while (i < this.zzawi.length) {
                zzzg zzzg3 = this.zzawi[i];
                if (zzzg3 != null) {
                    zzyy.zza(6, zzzg3);
                }
                i++;
            }
        }
        if (this.zzawj != null) {
            zzyy.zzb(7, this.zzawj);
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int i;
        int i2 = 0;
        int zzf = super.zzf();
        if (this.zzawe != null) {
            zzf += zzyy.zzd(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            zzf += zzyy.zzc(2, this.zzafx);
        }
        if (this.zzawf != null) {
            zzf += zzyy.zzh(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            i = zzf;
            for (zzzg zzzg : this.zzawg) {
                if (zzzg != null) {
                    i += zzyy.zzb(4, zzzg);
                }
            }
            zzf = i;
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            i = zzf;
            for (zzzg zzzg2 : this.zzawh) {
                if (zzzg2 != null) {
                    i += zzyy.zzb(5, zzzg2);
                }
            }
            zzf = i;
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            while (i2 < this.zzawi.length) {
                zzzg zzzg3 = this.zzawi[i2];
                if (zzzg3 != null) {
                    zzf += zzyy.zzb(6, zzzg3);
                }
                i2++;
            }
        }
        if (this.zzawj != null) {
            return zzf + zzyy.zzc(7, this.zzawj);
        }
        return zzf;
    }

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            int zzb;
            Object obj;
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    this.zzawe = Long.valueOf(zzyx.zzuz());
                    continue;
                case 18:
                    this.zzafx = zzyx.readString();
                    continue;
                case 24:
                    this.zzawf = Integer.valueOf(zzyx.zzuy());
                    continue;
                case 34:
                    zzb = zzzj.zzb(zzyx, 34);
                    zzug = this.zzawg == null ? 0 : this.zzawg.length;
                    obj = new zzgc[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzawg, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzgc();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzgc();
                    zzyx.zza(obj[zzug]);
                    this.zzawg = obj;
                    continue;
                case 42:
                    zzb = zzzj.zzb(zzyx, 42);
                    zzug = this.zzawh == null ? 0 : this.zzawh.length;
                    obj = new zzga[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzawh, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzga();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzga();
                    zzyx.zza(obj[zzug]);
                    this.zzawh = obj;
                    continue;
                case 50:
                    zzb = zzzj.zzb(zzyx, 50);
                    zzug = this.zzawi == null ? 0 : this.zzawi.length;
                    obj = new zzfu[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzawi, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzfu();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzfu();
                    zzyx.zza(obj[zzug]);
                    this.zzawi = obj;
                    continue;
                case 58:
                    this.zzawj = zzyx.readString();
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
