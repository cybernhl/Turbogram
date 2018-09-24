package com.google.android.gms.internal.vision;

public class zzde {
    private static final zzcf zzgk = zzcf.zzbg();
    private zzbo zzmi;
    private volatile zzdx zzmj;
    private volatile zzbo zzmk;

    private final zzdx zzh(zzdx zzdx) {
        if (this.zzmj == null) {
            synchronized (this) {
                if (this.zzmj != null) {
                } else {
                    try {
                        this.zzmj = zzdx;
                        this.zzmk = zzbo.zzgt;
                    } catch (zzcx e) {
                        this.zzmj = zzdx;
                        this.zzmk = zzbo.zzgt;
                    }
                }
            }
        }
        return this.zzmj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzde)) {
            return false;
        }
        zzde zzde = (zzde) obj;
        zzdx zzdx = this.zzmj;
        zzdx zzdx2 = zzde.zzmj;
        return (zzdx == null && zzdx2 == null) ? zzak().equals(zzde.zzak()) : (zzdx == null || zzdx2 == null) ? zzdx != null ? zzdx.equals(zzde.zzh(zzdx.zzbw())) : zzh(zzdx2.zzbw()).equals(zzdx2) : zzdx.equals(zzdx2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzbo zzak() {
        if (this.zzmk != null) {
            return this.zzmk;
        }
        synchronized (this) {
            if (this.zzmk != null) {
                zzbo zzbo = this.zzmk;
                return zzbo;
            }
            if (this.zzmj == null) {
                this.zzmk = zzbo.zzgt;
            } else {
                this.zzmk = this.zzmj.zzak();
            }
            zzbo = this.zzmk;
            return zzbo;
        }
    }

    public final int zzbl() {
        return this.zzmk != null ? this.zzmk.size() : this.zzmj != null ? this.zzmj.zzbl() : 0;
    }

    public final zzdx zzi(zzdx zzdx) {
        zzdx zzdx2 = this.zzmj;
        this.zzmi = null;
        this.zzmk = null;
        this.zzmj = zzdx;
        return zzdx2;
    }
}
