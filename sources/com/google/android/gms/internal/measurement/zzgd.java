package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzgd extends zzza<zzgd> {
    private static volatile zzgd[] zzawl;
    public Integer zzauy;
    public zzgj zzawm;
    public zzgj zzawn;
    public Boolean zzawo;

    public static zzgd[] zzmo() {
        if (zzawl == null) {
            synchronized (zzze.zzcfl) {
                if (zzawl == null) {
                    zzawl = new zzgd[0];
                }
            }
        }
        return zzawl;
    }

    public zzgd() {
        this.zzauy = null;
        this.zzawm = null;
        this.zzawn = null;
        this.zzawo = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgd)) {
            return false;
        }
        zzgd zzgd = (zzgd) obj;
        if (this.zzauy == null) {
            if (zzgd.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzgd.zzauy)) {
            return false;
        }
        if (this.zzawm == null) {
            if (zzgd.zzawm != null) {
                return false;
            }
        } else if (!this.zzawm.equals(zzgd.zzawm)) {
            return false;
        }
        if (this.zzawn == null) {
            if (zzgd.zzawn != null) {
                return false;
            }
        } else if (!this.zzawn.equals(zzgd.zzawn)) {
            return false;
        }
        if (this.zzawo == null) {
            if (zzgd.zzawo != null) {
                return false;
            }
        } else if (!this.zzawo.equals(zzgd.zzawo)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgd.zzcfc);
        }
        if (zzgd.zzcfc == null || zzgd.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzauy == null ? 0 : this.zzauy.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzgj zzgj = this.zzawm;
        hashCode = (zzgj == null ? 0 : zzgj.hashCode()) + (hashCode * 31);
        zzgj = this.zzawn;
        hashCode = ((this.zzawo == null ? 0 : this.zzawo.hashCode()) + (((zzgj == null ? 0 : zzgj.hashCode()) + (hashCode * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzauy != null) {
            zzyy.zzd(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            zzyy.zza(2, this.zzawm);
        }
        if (this.zzawn != null) {
            zzyy.zza(3, this.zzawn);
        }
        if (this.zzawo != null) {
            zzyy.zzb(4, this.zzawo.booleanValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzauy != null) {
            zzf += zzyy.zzh(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            zzf += zzyy.zzb(2, this.zzawm);
        }
        if (this.zzawn != null) {
            zzf += zzyy.zzb(3, this.zzawn);
        }
        if (this.zzawo == null) {
            return zzf;
        }
        this.zzawo.booleanValue();
        return zzf + (zzyy.zzbb(4) + 1);
    }

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    this.zzauy = Integer.valueOf(zzyx.zzuy());
                    continue;
                case 18:
                    if (this.zzawm == null) {
                        this.zzawm = new zzgj();
                    }
                    zzyx.zza(this.zzawm);
                    continue;
                case 26:
                    if (this.zzawn == null) {
                        this.zzawn = new zzgj();
                    }
                    zzyx.zza(this.zzawn);
                    continue;
                case 32:
                    this.zzawo = Boolean.valueOf(zzyx.zzum());
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
