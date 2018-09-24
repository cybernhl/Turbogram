package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzga extends zzza<zzga> {
    private static volatile zzga[] zzawa;
    public String name;
    public Boolean zzawb;
    public Boolean zzawc;
    public Integer zzawd;

    public static zzga[] zzmm() {
        if (zzawa == null) {
            synchronized (zzze.zzcfl) {
                if (zzawa == null) {
                    zzawa = new zzga[0];
                }
            }
        }
        return zzawa;
    }

    public zzga() {
        this.name = null;
        this.zzawb = null;
        this.zzawc = null;
        this.zzawd = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzga)) {
            return false;
        }
        zzga zzga = (zzga) obj;
        if (this.name == null) {
            if (zzga.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzga.name)) {
            return false;
        }
        if (this.zzawb == null) {
            if (zzga.zzawb != null) {
                return false;
            }
        } else if (!this.zzawb.equals(zzga.zzawb)) {
            return false;
        }
        if (this.zzawc == null) {
            if (zzga.zzawc != null) {
                return false;
            }
        } else if (!this.zzawc.equals(zzga.zzawc)) {
            return false;
        }
        if (this.zzawd == null) {
            if (zzga.zzawd != null) {
                return false;
            }
        } else if (!this.zzawd.equals(zzga.zzawd)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzga.zzcfc);
        }
        if (zzga.zzcfc == null || zzga.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzawd == null ? 0 : this.zzawd.hashCode()) + (((this.zzawc == null ? 0 : this.zzawc.hashCode()) + (((this.zzawb == null ? 0 : this.zzawb.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.name != null) {
            zzyy.zzb(1, this.name);
        }
        if (this.zzawb != null) {
            zzyy.zzb(2, this.zzawb.booleanValue());
        }
        if (this.zzawc != null) {
            zzyy.zzb(3, this.zzawc.booleanValue());
        }
        if (this.zzawd != null) {
            zzyy.zzd(4, this.zzawd.intValue());
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.name != null) {
            zzf += zzyy.zzc(1, this.name);
        }
        if (this.zzawb != null) {
            this.zzawb.booleanValue();
            zzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzawc != null) {
            this.zzawc.booleanValue();
            zzf += zzyy.zzbb(3) + 1;
        }
        if (this.zzawd != null) {
            return zzf + zzyy.zzh(4, this.zzawd.intValue());
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
                    this.name = zzyx.readString();
                    continue;
                case 16:
                    this.zzawb = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 24:
                    this.zzawc = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 32:
                    this.zzawd = Integer.valueOf(zzyx.zzuy());
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
