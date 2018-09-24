package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfw extends zzza<zzfw> {
    private static volatile zzfw[] zzavj;
    public zzfz zzavk;
    public zzfx zzavl;
    public Boolean zzavm;
    public String zzavn;

    public static zzfw[] zzmk() {
        if (zzavj == null) {
            synchronized (zzze.zzcfl) {
                if (zzavj == null) {
                    zzavj = new zzfw[0];
                }
            }
        }
        return zzavj;
    }

    public zzfw() {
        this.zzavk = null;
        this.zzavl = null;
        this.zzavm = null;
        this.zzavn = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfw)) {
            return false;
        }
        zzfw zzfw = (zzfw) obj;
        if (this.zzavk == null) {
            if (zzfw.zzavk != null) {
                return false;
            }
        } else if (!this.zzavk.equals(zzfw.zzavk)) {
            return false;
        }
        if (this.zzavl == null) {
            if (zzfw.zzavl != null) {
                return false;
            }
        } else if (!this.zzavl.equals(zzfw.zzavl)) {
            return false;
        }
        if (this.zzavm == null) {
            if (zzfw.zzavm != null) {
                return false;
            }
        } else if (!this.zzavm.equals(zzfw.zzavm)) {
            return false;
        }
        if (this.zzavn == null) {
            if (zzfw.zzavn != null) {
                return false;
            }
        } else if (!this.zzavn.equals(zzfw.zzavn)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzfw.zzcfc);
        }
        if (zzfw.zzcfc == null || zzfw.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = getClass().getName().hashCode() + 527;
        zzfz zzfz = this.zzavk;
        hashCode = (zzfz == null ? 0 : zzfz.hashCode()) + (hashCode * 31);
        zzfx zzfx = this.zzavl;
        hashCode = ((this.zzavn == null ? 0 : this.zzavn.hashCode()) + (((this.zzavm == null ? 0 : this.zzavm.hashCode()) + (((zzfx == null ? 0 : zzfx.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzavk != null) {
            zzyy.zza(1, this.zzavk);
        }
        if (this.zzavl != null) {
            zzyy.zza(2, this.zzavl);
        }
        if (this.zzavm != null) {
            zzyy.zzb(3, this.zzavm.booleanValue());
        }
        if (this.zzavn != null) {
            zzyy.zzb(4, this.zzavn);
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzavk != null) {
            zzf += zzyy.zzb(1, this.zzavk);
        }
        if (this.zzavl != null) {
            zzf += zzyy.zzb(2, this.zzavl);
        }
        if (this.zzavm != null) {
            this.zzavm.booleanValue();
            zzf += zzyy.zzbb(3) + 1;
        }
        if (this.zzavn != null) {
            return zzf + zzyy.zzc(4, this.zzavn);
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
                    if (this.zzavk == null) {
                        this.zzavk = new zzfz();
                    }
                    zzyx.zza(this.zzavk);
                    continue;
                case 18:
                    if (this.zzavl == null) {
                        this.zzavl = new zzfx();
                    }
                    zzyx.zza(this.zzavl);
                    continue;
                case 24:
                    this.zzavm = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 34:
                    this.zzavn = zzyx.readString();
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
