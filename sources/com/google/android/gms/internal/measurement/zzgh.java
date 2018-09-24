package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzgh extends zzza<zzgh> {
    public zzgi[] zzawy;

    public zzgh() {
        this.zzawy = zzgi.zzms();
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgh)) {
            return false;
        }
        zzgh zzgh = (zzgh) obj;
        if (!zzze.equals(this.zzawy, zzgh.zzawy)) {
            return false;
        }
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            return this.zzcfc.equals(zzgh.zzcfc);
        }
        if (zzgh.zzcfc == null || zzgh.zzcfc.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzze.hashCode(this.zzawy)) * 31;
        if (this.zzcfc == null || this.zzcfc.isEmpty()) {
            i = 0;
        } else {
            i = this.zzcfc.hashCode();
        }
        return i + hashCode;
    }

    public final void zza(zzyy zzyy) throws IOException {
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (zzzg zzzg : this.zzawy) {
                if (zzzg != null) {
                    zzyy.zza(1, zzzg);
                }
            }
        }
        super.zza(zzyy);
    }

    protected final int zzf() {
        int zzf = super.zzf();
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (zzzg zzzg : this.zzawy) {
                if (zzzg != null) {
                    zzf += zzyy.zzb(1, zzzg);
                }
            }
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
                    zzug = this.zzawy == null ? 0 : this.zzawy.length;
                    Object obj = new zzgi[(zzb + zzug)];
                    if (zzug != 0) {
                        System.arraycopy(this.zzawy, 0, obj, 0, zzug);
                    }
                    while (zzug < obj.length - 1) {
                        obj[zzug] = new zzgi();
                        zzyx.zza(obj[zzug]);
                        zzyx.zzug();
                        zzug++;
                    }
                    obj[zzug] = new zzgi();
                    zzyx.zza(obj[zzug]);
                    this.zzawy = obj;
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
