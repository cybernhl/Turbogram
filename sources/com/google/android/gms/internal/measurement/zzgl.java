package com.google.android.gms.internal.measurement;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

public final class zzgl extends zzza<zzgl> {
    private static volatile zzgl[] zzayk;
    public String name;
    public String zzamp;
    private Float zzaug;
    public Double zzauh;
    public Long zzawx;
    public Long zzayl;

    public static zzgl[] zzmu() {
        if (zzayk == null) {
            synchronized (zzze.zzcfl) {
                if (zzayk == null) {
                    zzayk = new zzgl[0];
                }
            }
        }
        return zzayk;
    }

    public zzgl() {
        this.zzayl = null;
        this.name = null;
        this.zzamp = null;
        this.zzawx = null;
        this.zzaug = null;
        this.zzauh = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgl)) {
            return false;
        }
        zzgl zzgl = (zzgl) obj;
        if (this.zzayl == null) {
            if (zzgl.zzayl != null) {
                return false;
            }
        } else if (!this.zzayl.equals(zzgl.zzayl)) {
            return false;
        }
        if (this.name == null) {
            if (zzgl.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzgl.name)) {
            return false;
        }
        if (this.zzamp == null) {
            if (zzgl.zzamp != null) {
                return false;
            }
        } else if (!this.zzamp.equals(zzgl.zzamp)) {
            return false;
        }
        if (this.zzawx == null) {
            if (zzgl.zzawx != null) {
                return false;
            }
        } else if (!this.zzawx.equals(zzgl.zzawx)) {
            return false;
        }
        if (this.zzaug == null) {
            if (zzgl.zzaug != null) {
                return false;
            }
        } else if (!this.zzaug.equals(zzgl.zzaug)) {
            return false;
        }
        if (this.zzauh == null) {
            if (zzgl.zzauh != null) {
                return false;
            }
        } else if (!this.zzauh.equals(zzgl.zzauh)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgl.zzcfc);
        }
        if (zzgl.zzcfc == null || zzgl.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzauh == null ? 0 : this.zzauh.hashCode()) + (((this.zzaug == null ? 0 : this.zzaug.hashCode()) + (((this.zzawx == null ? 0 : this.zzawx.hashCode()) + (((this.zzamp == null ? 0 : this.zzamp.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzayl == null ? 0 : this.zzayl.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzayl != null) {
            zzyy.zzi(1, this.zzayl.longValue());
        }
        if (this.name != null) {
            zzyy.zzb(2, this.name);
        }
        if (this.zzamp != null) {
            zzyy.zzb(3, this.zzamp);
        }
        if (this.zzawx != null) {
            zzyy.zzi(4, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            zzyy.zza(5, this.zzaug.floatValue());
        }
        if (this.zzauh != null) {
            zzyy.zza(6, this.zzauh.doubleValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzayl != null) {
            zzf += zzyy.zzd(1, this.zzayl.longValue());
        }
        if (this.name != null) {
            zzf += zzyy.zzc(2, this.name);
        }
        if (this.zzamp != null) {
            zzf += zzyy.zzc(3, this.zzamp);
        }
        if (this.zzawx != null) {
            zzf += zzyy.zzd(4, this.zzawx.longValue());
        }
        if (this.zzaug != null) {
            this.zzaug.floatValue();
            zzf += zzyy.zzbb(5) + 4;
        }
        if (this.zzauh == null) {
            return zzf;
        }
        this.zzauh.doubleValue();
        return zzf + (zzyy.zzbb(6) + 8);
    }

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    this.zzayl = Long.valueOf(zzyx.zzuz());
                    continue;
                case 18:
                    this.name = zzyx.readString();
                    continue;
                case 26:
                    this.zzamp = zzyx.readString();
                    continue;
                case 32:
                    this.zzawx = Long.valueOf(zzyx.zzuz());
                    continue;
                case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                    this.zzaug = Float.valueOf(Float.intBitsToFloat(zzyx.zzva()));
                    continue;
                case 49:
                    this.zzauh = Double.valueOf(Double.longBitsToDouble(zzyx.zzvb()));
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
