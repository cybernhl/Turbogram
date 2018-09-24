package com.google.android.gms.internal.config;

import java.io.IOException;

public final class zzav extends zzbb<zzav> {
    private static volatile zzav[] zzbn;
    public String namespace;
    public zzat[] zzbo;

    public zzav() {
        this.namespace = "";
        this.zzbo = zzat.zzu();
        this.zzch = null;
        this.zzcq = -1;
    }

    public static zzav[] zzv() {
        if (zzbn == null) {
            synchronized (zzbf.zzcp) {
                if (zzbn == null) {
                    zzbn = new zzav[0];
                }
            }
        }
        return zzbn;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzav)) {
            return false;
        }
        zzav zzav = (zzav) obj;
        if (this.namespace == null) {
            if (zzav.namespace != null) {
                return false;
            }
        } else if (!this.namespace.equals(zzav.namespace)) {
            return false;
        }
        return !zzbf.equals(this.zzbo, zzav.zzbo) ? false : (this.zzch == null || this.zzch.isEmpty()) ? zzav.zzch == null || zzav.zzch.isEmpty() : this.zzch.equals(zzav.zzch);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.namespace == null ? 0 : this.namespace.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzbf.hashCode(this.zzbo)) * 31;
        if (!(this.zzch == null || this.zzch.isEmpty())) {
            i = this.zzch.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzbh zza(zzay zzay) throws IOException {
        while (true) {
            int zzx = zzay.zzx();
            switch (zzx) {
                case 0:
                    break;
                case 10:
                    this.namespace = zzay.readString();
                    continue;
                case 18:
                    int zzb = zzbk.zzb(zzay, 18);
                    zzx = this.zzbo == null ? 0 : this.zzbo.length;
                    Object obj = new zzat[(zzb + zzx)];
                    if (zzx != 0) {
                        System.arraycopy(this.zzbo, 0, obj, 0, zzx);
                    }
                    while (zzx < obj.length - 1) {
                        obj[zzx] = new zzat();
                        zzay.zza(obj[zzx]);
                        zzay.zzx();
                        zzx++;
                    }
                    obj[zzx] = new zzat();
                    zzay.zza(obj[zzx]);
                    this.zzbo = obj;
                    continue;
                default:
                    if (!super.zza(zzay, zzx)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzaz zzaz) throws IOException {
        if (!(this.namespace == null || this.namespace.equals(""))) {
            zzaz.zza(1, this.namespace);
        }
        if (this.zzbo != null && this.zzbo.length > 0) {
            for (zzbh zzbh : this.zzbo) {
                if (zzbh != null) {
                    zzaz.zza(2, zzbh);
                }
            }
        }
        super.zza(zzaz);
    }

    protected final int zzt() {
        int zzt = super.zzt();
        if (!(this.namespace == null || this.namespace.equals(""))) {
            zzt += zzaz.zzb(1, this.namespace);
        }
        if (this.zzbo == null || this.zzbo.length <= 0) {
            return zzt;
        }
        int i = zzt;
        for (zzbh zzbh : this.zzbo) {
            if (zzbh != null) {
                i += zzaz.zzb(2, zzbh);
            }
        }
        return i;
    }
}
