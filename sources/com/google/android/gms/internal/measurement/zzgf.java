package com.google.android.gms.internal.measurement;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

public final class zzgf extends zzza<zzgf> {
    private static volatile zzgf[] zzaws;
    public Integer count;
    public String name;
    public zzgg[] zzawt;
    public Long zzawu;
    public Long zzawv;

    public static zzgf[] zzmq() {
        if (zzaws == null) {
            synchronized (zzze.zzcfl) {
                if (zzaws == null) {
                    zzaws = new zzgf[0];
                }
            }
        }
        return zzaws;
    }

    public zzgf() {
        this.zzawt = zzgg.zzmr();
        this.name = null;
        this.zzawu = null;
        this.zzawv = null;
        this.count = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgf)) {
            return false;
        }
        zzgf zzgf = (zzgf) obj;
        if (!zzze.equals(this.zzawt, zzgf.zzawt)) {
            return false;
        }
        if (this.name == null) {
            if (zzgf.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzgf.name)) {
            return false;
        }
        if (this.zzawu == null) {
            if (zzgf.zzawu != null) {
                return false;
            }
        } else if (!this.zzawu.equals(zzgf.zzawu)) {
            return false;
        }
        if (this.zzawv == null) {
            if (zzgf.zzawv != null) {
                return false;
            }
        } else if (!this.zzawv.equals(zzgf.zzawv)) {
            return false;
        }
        if (this.count == null) {
            if (zzgf.count != null) {
                return false;
            }
        } else if (!this.count.equals(zzgf.count)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgf.zzcfc);
        }
        if (zzgf.zzcfc == null || zzgf.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zzawv == null ? 0 : this.zzawv.hashCode()) + (((this.zzawu == null ? 0 : this.zzawu.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzze.hashCode(this.zzawt)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzawt != null && this.zzawt.length > 0) {
            for (zzzg zzzg : this.zzawt) {
                if (zzzg != null) {
                    zzyy.zza(1, zzzg);
                }
            }
        }
        if (this.name != null) {
            zzyy.zzb(2, this.name);
        }
        if (this.zzawu != null) {
            zzyy.zzi(3, this.zzawu.longValue());
        }
        if (this.zzawv != null) {
            zzyy.zzi(4, this.zzawv.longValue());
        }
        if (this.count != null) {
            zzyy.zzd(5, this.count.intValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzawt != null && this.zzawt.length > 0) {
            for (zzzg zzzg : this.zzawt) {
                if (zzzg != null) {
                    zzf += zzyy.zzb(1, zzzg);
                }
            }
        }
        if (this.name != null) {
            zzf += zzyy.zzc(2, this.name);
        }
        if (this.zzawu != null) {
            zzf += zzyy.zzd(3, this.zzawu.longValue());
        }
        if (this.zzawv != null) {
            zzf += zzyy.zzd(4, this.zzawv.longValue());
        }
        if (this.count != null) {
            return zzf + zzyy.zzh(5, this.count.intValue());
        }
        return zzf;
    }

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            switch (zzug) {
                case 0:
                    break;
                case 10:
                    int zzb = zzzj.zzb(zzyx, 10);
                    zzug = this.zzawt == null ? 0 : this.zzawt.length;
                    Object obj = new zzgg[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzawt, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzgg();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzgg();
                    zzyx.zza(obj[zzug]);
                    this.zzawt = obj;
                    continue;
                case 18:
                    this.name = zzyx.readString();
                    continue;
                case 24:
                    this.zzawu = Long.valueOf(zzyx.zzuz());
                    continue;
                case 32:
                    this.zzawv = Long.valueOf(zzyx.zzuz());
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.count = Integer.valueOf(zzyx.zzuy());
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
