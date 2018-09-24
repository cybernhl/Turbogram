package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzgc extends zzza<zzgc> {
    private static volatile zzgc[] zzawk;
    public String value;
    public String zzoj;

    public static zzgc[] zzmn() {
        if (zzawk == null) {
            synchronized (zzze.zzcfl) {
                if (zzawk == null) {
                    zzawk = new zzgc[0];
                }
            }
        }
        return zzawk;
    }

    public zzgc() {
        this.zzoj = null;
        this.value = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgc)) {
            return false;
        }
        zzgc zzgc = (zzgc) obj;
        if (this.zzoj == null) {
            if (zzgc.zzoj != null) {
                return false;
            }
        } else if (!this.zzoj.equals(zzgc.zzoj)) {
            return false;
        }
        if (this.value == null) {
            if (zzgc.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzgc.value)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgc.zzcfc);
        }
        if (zzgc.zzcfc == null || zzgc.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzoj == null ? 0 : this.zzoj.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (!(this.zzcfc == null || this.zzcfc.isEmpty())) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzoj != null) {
            zzyy.zzb(1, this.zzoj);
        }
        if (this.value != null) {
            zzyy.zzb(2, this.value);
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzoj != null) {
            zzf += zzyy.zzc(1, this.zzoj);
        }
        if (this.value != null) {
            return zzf + zzyy.zzc(2, this.value);
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
                    this.zzoj = zzyx.readString();
                    continue;
                case 18:
                    this.value = zzyx.readString();
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
