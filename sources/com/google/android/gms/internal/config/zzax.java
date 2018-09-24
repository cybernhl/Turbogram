package com.google.android.gms.internal.config;

import java.io.IOException;

public final class zzax extends zzbb<zzax> {
    private static volatile zzax[] zzbu;
    public String namespace;
    public int resourceId;
    public long zzbv;

    public zzax() {
        this.resourceId = 0;
        this.zzbv = 0;
        this.namespace = "";
        this.zzch = null;
        this.zzcq = -1;
    }

    public static zzax[] zzw() {
        if (zzbu == null) {
            synchronized (zzbf.zzcp) {
                if (zzbu == null) {
                    zzbu = new zzax[0];
                }
            }
        }
        return zzbu;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzax)) {
            return false;
        }
        zzax zzax = (zzax) obj;
        if (this.resourceId != zzax.resourceId) {
            return false;
        }
        if (this.zzbv != zzax.zzbv) {
            return false;
        }
        if (this.namespace == null) {
            if (zzax.namespace != null) {
                return false;
            }
        } else if (!this.namespace.equals(zzax.namespace)) {
            return false;
        }
        return (this.zzch == null || this.zzch.isEmpty()) ? zzax.zzch == null || zzax.zzch.isEmpty() : this.zzch.equals(zzax.zzch);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.namespace == null ? 0 : this.namespace.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.resourceId) * 31) + ((int) (this.zzbv ^ (this.zzbv >>> 32)))) * 31)) * 31;
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
                case 8:
                    this.resourceId = zzay.zzy();
                    continue;
                case 17:
                    this.zzbv = zzay.zzz();
                    continue;
                case 26:
                    this.namespace = zzay.readString();
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
        if (this.resourceId != 0) {
            zzaz.zzc(1, this.resourceId);
        }
        if (this.zzbv != 0) {
            zzaz.zza(2, this.zzbv);
        }
        if (!(this.namespace == null || this.namespace.equals(""))) {
            zzaz.zza(3, this.namespace);
        }
        super.zza(zzaz);
    }

    protected final int zzt() {
        int zzt = super.zzt();
        if (this.resourceId != 0) {
            zzt += zzaz.zzd(1, this.resourceId);
        }
        if (this.zzbv != 0) {
            zzt += zzaz.zzl(2) + 8;
        }
        return (this.namespace == null || this.namespace.equals("")) ? zzt : zzt + zzaz.zzb(3, this.namespace);
    }
}
