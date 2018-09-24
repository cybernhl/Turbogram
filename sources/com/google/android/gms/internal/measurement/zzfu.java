package com.google.android.gms.internal.measurement;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

public final class zzfu extends zzza<zzfu> {
    private static volatile zzfu[] zzaux;
    public Integer zzauy;
    public zzfy[] zzauz;
    public zzfv[] zzava;
    private Boolean zzavb;
    private Boolean zzavc;

    public static zzfu[] zzmi() {
        if (zzaux == null) {
            synchronized (zzze.zzcfl) {
                if (zzaux == null) {
                    zzaux = new zzfu[0];
                }
            }
        }
        return zzaux;
    }

    public zzfu() {
        this.zzauy = null;
        this.zzauz = zzfy.zzml();
        this.zzava = zzfv.zzmj();
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfu)) {
            return false;
        }
        zzfu zzfu = (zzfu) obj;
        if (this.zzauy == null) {
            if (zzfu.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzfu.zzauy)) {
            return false;
        }
        if (!zzze.equals(this.zzauz, zzfu.zzauz)) {
            return false;
        }
        if (!zzze.equals(this.zzava, zzfu.zzava)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfu.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfu.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfu.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfu.zzavc)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzfu.zzcfc);
        }
        if (zzfu.zzcfc == null || zzfu.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzavc == null ? 0 : this.zzavc.hashCode()) + (((this.zzavb == null ? 0 : this.zzavb.hashCode()) + (((((((this.zzauy == null ? 0 : this.zzauy.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzze.hashCode(this.zzauz)) * 31) + zzze.hashCode(this.zzava)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        int i = 0;
        if (this.zzauy != null) {
            zzyy.zzd(1, this.zzauy.intValue());
        }
        if (this.zzauz != null && this.zzauz.length > 0) {
            for (zzzg zzzg : this.zzauz) {
                if (zzzg != null) {
                    zzyy.zza(2, zzzg);
                }
            }
        }
        if (this.zzava != null && this.zzava.length > 0) {
            while (i < this.zzava.length) {
                zzzg zzzg2 = this.zzava[i];
                if (zzzg2 != null) {
                    zzyy.zza(3, zzzg2);
                }
                i++;
            }
        }
        if (this.zzavb != null) {
            zzyy.zzb(4, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyy.zzb(5, this.zzavc.booleanValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int i = 0;
        int zzf = super.zzf();
        if (this.zzauy != null) {
            zzf += zzyy.zzh(1, this.zzauy.intValue());
        }
        if (this.zzauz != null && this.zzauz.length > 0) {
            int i2 = zzf;
            for (zzzg zzzg : this.zzauz) {
                if (zzzg != null) {
                    i2 += zzyy.zzb(2, zzzg);
                }
            }
            zzf = i2;
        }
        if (this.zzava != null && this.zzava.length > 0) {
            while (i < this.zzava.length) {
                zzzg zzzg2 = this.zzava[i];
                if (zzzg2 != null) {
                    zzf += zzyy.zzb(3, zzzg2);
                }
                i++;
            }
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            zzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavc == null) {
            return zzf;
        }
        this.zzavc.booleanValue();
        return zzf + (zzyy.zzbb(5) + 1);
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
                    this.zzauy = Integer.valueOf(zzyx.zzuy());
                    continue;
                case 18:
                    zzb = zzzj.zzb(zzyx, 18);
                    zzug = this.zzauz == null ? 0 : this.zzauz.length;
                    obj = new zzfy[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzauz, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzfy();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzfy();
                    zzyx.zza(obj[zzug]);
                    this.zzauz = obj;
                    continue;
                case 26:
                    zzb = zzzj.zzb(zzyx, 26);
                    zzug = this.zzava == null ? 0 : this.zzava.length;
                    obj = new zzfv[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzava, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzfv();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzfv();
                    zzyx.zza(obj[zzug]);
                    this.zzava = obj;
                    continue;
                case 32:
                    this.zzavb = Boolean.valueOf(zzyx.zzum());
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.zzavc = Boolean.valueOf(zzyx.zzum());
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
