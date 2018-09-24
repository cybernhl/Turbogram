package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfv extends zzza<zzfv> {
    private static volatile zzfv[] zzavd;
    public Boolean zzavb;
    public Boolean zzavc;
    public Integer zzave;
    public String zzavf;
    public zzfw[] zzavg;
    private Boolean zzavh;
    public zzfx zzavi;

    public static zzfv[] zzmj() {
        if (zzavd == null) {
            synchronized (zzze.zzcfl) {
                if (zzavd == null) {
                    zzavd = new zzfv[0];
                }
            }
        }
        return zzavd;
    }

    public zzfv() {
        this.zzave = null;
        this.zzavf = null;
        this.zzavg = zzfw.zzmk();
        this.zzavh = null;
        this.zzavi = null;
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfv = (zzfv) obj;
        if (this.zzave == null) {
            if (zzfv.zzave != null) {
                return false;
            }
        } else if (!this.zzave.equals(zzfv.zzave)) {
            return false;
        }
        if (this.zzavf == null) {
            if (zzfv.zzavf != null) {
                return false;
            }
        } else if (!this.zzavf.equals(zzfv.zzavf)) {
            return false;
        }
        if (!zzze.equals(this.zzavg, zzfv.zzavg)) {
            return false;
        }
        if (this.zzavh == null) {
            if (zzfv.zzavh != null) {
                return false;
            }
        } else if (!this.zzavh.equals(zzfv.zzavh)) {
            return false;
        }
        if (this.zzavi == null) {
            if (zzfv.zzavi != null) {
                return false;
            }
        } else if (!this.zzavi.equals(zzfv.zzavi)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzfv.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzfv.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzfv.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzfv.zzavc)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzfv.zzcfc);
        }
        if (zzfv.zzcfc == null || zzfv.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzavh == null ? 0 : this.zzavh.hashCode()) + (((((this.zzavf == null ? 0 : this.zzavf.hashCode()) + (((this.zzave == null ? 0 : this.zzave.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzze.hashCode(this.zzavg)) * 31);
        zzfx zzfx = this.zzavi;
        hashCode = ((this.zzavc == null ? 0 : this.zzavc.hashCode()) + (((this.zzavb == null ? 0 : this.zzavb.hashCode()) + (((zzfx == null ? 0 : zzfx.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzave != null) {
            zzyy.zzd(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            zzyy.zzb(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            for (zzzg zzzg : this.zzavg) {
                if (zzzg != null) {
                    zzyy.zza(3, zzzg);
                }
            }
        }
        if (this.zzavh != null) {
            zzyy.zzb(4, this.zzavh.booleanValue());
        }
        if (this.zzavi != null) {
            zzyy.zza(5, this.zzavi);
        }
        if (this.zzavb != null) {
            zzyy.zzb(6, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyy.zzb(7, this.zzavc.booleanValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzave != null) {
            zzf += zzyy.zzh(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            zzf += zzyy.zzc(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            int i = zzf;
            for (zzzg zzzg : this.zzavg) {
                if (zzzg != null) {
                    i += zzyy.zzb(3, zzzg);
                }
            }
            zzf = i;
        }
        if (this.zzavh != null) {
            this.zzavh.booleanValue();
            zzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavi != null) {
            zzf += zzyy.zzb(5, this.zzavi);
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            zzf += zzyy.zzbb(6) + 1;
        }
        if (this.zzavc == null) {
            return zzf;
        }
        this.zzavc.booleanValue();
        return zzf + (zzyy.zzbb(7) + 1);
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
                    this.zzavf = zzyx.readString();
                    continue;
                case 26:
                    int zzb = zzzj.zzb(zzyx, 26);
                    zzug = this.zzavg == null ? 0 : this.zzavg.length;
                    Object obj = new zzfw[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzavg, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzfw();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzfw();
                    zzyx.zza(obj[zzug]);
                    this.zzavg = obj;
                    continue;
                case 32:
                    this.zzavh = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 42:
                    if (this.zzavi == null) {
                        this.zzavi = new zzfx();
                    }
                    zzyx.zza(this.zzavi);
                    continue;
                case 48:
                    this.zzavb = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 56:
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
