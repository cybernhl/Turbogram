package com.google.android.gms.internal.config;

import java.io.IOException;

public final class zzaw extends zzbb<zzaw> {
    public zzas zzbp;
    public zzas zzbq;
    public zzas zzbr;
    public zzau zzbs;
    public zzax[] zzbt;

    public zzaw() {
        this.zzbp = null;
        this.zzbq = null;
        this.zzbr = null;
        this.zzbs = null;
        this.zzbt = zzax.zzw();
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaw)) {
            return false;
        }
        zzaw zzaw = (zzaw) obj;
        if (this.zzbp == null) {
            if (zzaw.zzbp != null) {
                return false;
            }
        } else if (!this.zzbp.equals(zzaw.zzbp)) {
            return false;
        }
        if (this.zzbq == null) {
            if (zzaw.zzbq != null) {
                return false;
            }
        } else if (!this.zzbq.equals(zzaw.zzbq)) {
            return false;
        }
        if (this.zzbr == null) {
            if (zzaw.zzbr != null) {
                return false;
            }
        } else if (!this.zzbr.equals(zzaw.zzbr)) {
            return false;
        }
        if (this.zzbs == null) {
            if (zzaw.zzbs != null) {
                return false;
            }
        } else if (!this.zzbs.equals(zzaw.zzbs)) {
            return false;
        }
        return !zzbf.equals(this.zzbt, zzaw.zzbt) ? false : (this.zzch == null || this.zzch.isEmpty()) ? zzaw.zzch == null || zzaw.zzch.isEmpty() : this.zzch.equals(zzaw.zzch);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = getClass().getName().hashCode() + 527;
        zzas zzas = this.zzbp;
        hashCode = (zzas == null ? 0 : zzas.hashCode()) + (hashCode * 31);
        zzas = this.zzbq;
        hashCode = (zzas == null ? 0 : zzas.hashCode()) + (hashCode * 31);
        zzas = this.zzbr;
        hashCode = (zzas == null ? 0 : zzas.hashCode()) + (hashCode * 31);
        zzau zzau = this.zzbs;
        hashCode = ((((zzau == null ? 0 : zzau.hashCode()) + (hashCode * 31)) * 31) + zzbf.hashCode(this.zzbt)) * 31;
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
                    if (this.zzbp == null) {
                        this.zzbp = new zzas();
                    }
                    zzay.zza(this.zzbp);
                    continue;
                case 18:
                    if (this.zzbq == null) {
                        this.zzbq = new zzas();
                    }
                    zzay.zza(this.zzbq);
                    continue;
                case 26:
                    if (this.zzbr == null) {
                        this.zzbr = new zzas();
                    }
                    zzay.zza(this.zzbr);
                    continue;
                case 34:
                    if (this.zzbs == null) {
                        this.zzbs = new zzau();
                    }
                    zzay.zza(this.zzbs);
                    continue;
                case 42:
                    int zzb = zzbk.zzb(zzay, 42);
                    zzx = this.zzbt == null ? 0 : this.zzbt.length;
                    Object obj = new zzax[(zzb + zzx)];
                    if (zzx != 0) {
                        System.arraycopy(this.zzbt, 0, obj, 0, zzx);
                    }
                    while (zzx < obj.length - 1) {
                        obj[zzx] = new zzax();
                        zzay.zza(obj[zzx]);
                        zzay.zzx();
                        zzx++;
                    }
                    obj[zzx] = new zzax();
                    zzay.zza(obj[zzx]);
                    this.zzbt = obj;
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
        if (this.zzbp != null) {
            zzaz.zza(1, this.zzbp);
        }
        if (this.zzbq != null) {
            zzaz.zza(2, this.zzbq);
        }
        if (this.zzbr != null) {
            zzaz.zza(3, this.zzbr);
        }
        if (this.zzbs != null) {
            zzaz.zza(4, this.zzbs);
        }
        if (this.zzbt != null && this.zzbt.length > 0) {
            for (zzbh zzbh : this.zzbt) {
                if (zzbh != null) {
                    zzaz.zza(5, zzbh);
                }
            }
        }
        super.zza(zzaz);
    }

    protected final int zzt() {
        int zzt = super.zzt();
        if (this.zzbp != null) {
            zzt += zzaz.zzb(1, this.zzbp);
        }
        if (this.zzbq != null) {
            zzt += zzaz.zzb(2, this.zzbq);
        }
        if (this.zzbr != null) {
            zzt += zzaz.zzb(3, this.zzbr);
        }
        if (this.zzbs != null) {
            zzt += zzaz.zzb(4, this.zzbs);
        }
        if (this.zzbt == null || this.zzbt.length <= 0) {
            return zzt;
        }
        int i = zzt;
        for (zzbh zzbh : this.zzbt) {
            if (zzbh != null) {
                i += zzaz.zzb(5, zzbh);
            }
        }
        return i;
    }
}
