package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfx extends zzza<zzfx> {
    public Integer zzavo;
    public Boolean zzavp;
    public String zzavq;
    public String zzavr;
    public String zzavs;

    public zzfx() {
        this.zzavo = null;
        this.zzavp = null;
        this.zzavq = null;
        this.zzavr = null;
        this.zzavs = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx)) {
            return false;
        }
        zzfx zzfx = (zzfx) obj;
        if (this.zzavo == null) {
            if (zzfx.zzavo != null) {
                return false;
            }
        } else if (!this.zzavo.equals(zzfx.zzavo)) {
            return false;
        }
        if (this.zzavp == null) {
            if (zzfx.zzavp != null) {
                return false;
            }
        } else if (!this.zzavp.equals(zzfx.zzavp)) {
            return false;
        }
        if (this.zzavq == null) {
            if (zzfx.zzavq != null) {
                return false;
            }
        } else if (!this.zzavq.equals(zzfx.zzavq)) {
            return false;
        }
        if (this.zzavr == null) {
            if (zzfx.zzavr != null) {
                return false;
            }
        } else if (!this.zzavr.equals(zzfx.zzavr)) {
            return false;
        }
        if (this.zzavs == null) {
            if (zzfx.zzavs != null) {
                return false;
            }
        } else if (!this.zzavs.equals(zzfx.zzavs)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzfx.zzcfc);
        }
        if (zzfx.zzcfc == null || zzfx.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzavs == null ? 0 : this.zzavs.hashCode()) + (((this.zzavr == null ? 0 : this.zzavr.hashCode()) + (((this.zzavq == null ? 0 : this.zzavq.hashCode()) + (((this.zzavp == null ? 0 : this.zzavp.hashCode()) + (((this.zzavo == null ? 0 : this.zzavo.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzavo != null) {
            zzyy.zzd(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            zzyy.zzb(2, this.zzavp.booleanValue());
        }
        if (this.zzavq != null) {
            zzyy.zzb(3, this.zzavq);
        }
        if (this.zzavr != null) {
            zzyy.zzb(4, this.zzavr);
        }
        if (this.zzavs != null) {
            zzyy.zzb(5, this.zzavs);
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzavo != null) {
            zzf += zzyy.zzh(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            this.zzavp.booleanValue();
            zzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzavq != null) {
            zzf += zzyy.zzc(3, this.zzavq);
        }
        if (this.zzavr != null) {
            zzf += zzyy.zzc(4, this.zzavr);
        }
        if (this.zzavs != null) {
            return zzf + zzyy.zzc(5, this.zzavs);
        }
        return zzf;
    }

    private final zzfx zzc(zzyx zzyx) throws IOException {
        while (true) {
            int zzug = zzyx.zzug();
            switch (zzug) {
                case 0:
                    break;
                case 8:
                    int position = zzyx.getPosition();
                    try {
                        int zzuy = zzyx.zzuy();
                        if (zzuy < 0 || zzuy > 4) {
                            throw new IllegalArgumentException(zzuy + " is not a valid enum ComparisonType");
                        }
                        this.zzavo = Integer.valueOf(zzuy);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzyx.zzby(position);
                        zza(zzyx, zzug);
                        break;
                    }
                case 16:
                    this.zzavp = Boolean.valueOf(zzyx.zzum());
                    continue;
                case 26:
                    this.zzavq = zzyx.readString();
                    continue;
                case 34:
                    this.zzavr = zzyx.readString();
                    continue;
                case 42:
                    this.zzavs = zzyx.readString();
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

    public final /* synthetic */ zzzg zza(zzyx zzyx) throws IOException {
        return zzc(zzyx);
    }
}
