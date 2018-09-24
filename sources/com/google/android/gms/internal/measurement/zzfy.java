package com.google.android.gms.internal.measurement;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

public final class zzfy extends zzza<zzfy> {
    private static volatile zzfy[] zzavt;
    public Boolean zzavb;
    public Boolean zzavc;
    public Integer zzave;
    public String zzavu;
    public zzfw zzavv;

    public static zzfy[] zzml() {
        if (zzavt == null) {
            synchronized (zzze.zzcfl) {
                if (zzavt == null) {
                    zzavt = new zzfy[0];
                }
            }
        }
        return zzavt;
    }

    public zzfy() {
        this.zzave = null;
        this.zzavu = null;
        this.zzavv = null;
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        if (this.zzave == null) {
            if (zzfy.zzave != null) {
                return false;
            }
        } else if (!this.zzave.equals(zzfy.zzave)) {
            return false;
        }
        if (this.zzavu == null) {
            if (zzfy.zzavu != null) {
                return false;
            }
        } else if (!this.zzavu.equals(zzfy.zzavu)) {
            return false;
        }
        if (this.zzavv == null) {
            if (zzfy.zzavv != null) {
                return false;
            }
        } else if (!this.zzavv.equals(zzfy.zzavv)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfy.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfy.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfy.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfy.zzavc)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzfy.zzcfc);
        }
        if (zzfy.zzcfc == null || zzfy.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzavu == null ? 0 : this.zzavu.hashCode()) + (((this.zzave == null ? 0 : this.zzave.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31);
        zzfw zzfw = this.zzavv;
        hashCode = ((this.zzavc == null ? 0 : this.zzavc.hashCode()) + (((this.zzavb == null ? 0 : this.zzavb.hashCode()) + (((zzfw == null ? 0 : zzfw.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzave != null) {
            zzyy.zzd(1, this.zzave.intValue());
        }
        if (this.zzavu != null) {
            zzyy.zzb(2, this.zzavu);
        }
        if (this.zzavv != null) {
            zzyy.zza(3, this.zzavv);
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
        int zzf = super.zzf();
        if (this.zzave != null) {
            zzf += zzyy.zzh(1, this.zzave.intValue());
        }
        if (this.zzavu != null) {
            zzf += zzyy.zzc(2, this.zzavu);
        }
        if (this.zzavv != null) {
            zzf += zzyy.zzb(3, this.zzavv);
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
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    this.zzave = Integer.valueOf(zzyx.zzuy());
                    continue;
                case 18:
                    this.zzavu = zzyx.readString();
                    continue;
                case 26:
                    if (this.zzavv == null) {
                        this.zzavv = new zzfw();
                    }
                    zzyx.zza(this.zzavv);
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
