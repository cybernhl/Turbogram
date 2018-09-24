package com.google.android.gms.internal.config;

import java.io.IOException;

public final class zzas extends zzbb<zzas> {
    public long timestamp;
    public zzav[] zzbf;
    public byte[][] zzbg;

    public zzas() {
        this.zzbf = zzav.zzv();
        this.timestamp = 0;
        this.zzbg = zzbk.zzdc;
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzas)) {
            return false;
        }
        zzas zzas = (zzas) obj;
        return !zzbf.equals(this.zzbf, zzas.zzbf) ? false : this.timestamp != zzas.timestamp ? false : !zzbf.zza(this.zzbg, zzas.zzbg) ? false : (this.zzch == null || this.zzch.isEmpty()) ? zzas.zzch == null || zzas.zzch.isEmpty() : this.zzch.equals(zzas.zzch);
    }

    public final int hashCode() {
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzbf.hashCode(this.zzbf)) * 31) + ((int) (this.timestamp ^ (this.timestamp >>> 32)))) * 31) + zzbf.zza(this.zzbg)) * 31;
        int hashCode2 = (this.zzch == null || this.zzch.isEmpty()) ? 0 : this.zzch.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzbh zza(zzay zzay) throws IOException {
        while (true) {
            int zzx = zzay.zzx();
            int zzb;
            Object obj;
            switch (zzx) {
                case 0:
                    break;
                case 10:
                    zzb = zzbk.zzb(zzay, 10);
                    zzx = this.zzbf == null ? 0 : this.zzbf.length;
                    obj = new zzav[(zzb + zzx)];
                    if (zzx != 0) {
                        System.arraycopy(this.zzbf, 0, obj, 0, zzx);
                    }
                    while (zzx < obj.length - 1) {
                        obj[zzx] = new zzav();
                        zzay.zza(obj[zzx]);
                        zzay.zzx();
                        zzx++;
                    }
                    obj[zzx] = new zzav();
                    zzay.zza(obj[zzx]);
                    this.zzbf = obj;
                    continue;
                case 17:
                    this.timestamp = zzay.zzz();
                    continue;
                case 26:
                    zzb = zzbk.zzb(zzay, 26);
                    zzx = this.zzbg == null ? 0 : this.zzbg.length;
                    obj = new byte[(zzb + zzx)][];
                    if (zzx != 0) {
                        System.arraycopy(this.zzbg, 0, obj, 0, zzx);
                    }
                    while (zzx < obj.length - 1) {
                        obj[zzx] = zzay.readBytes();
                        zzay.zzx();
                        zzx++;
                    }
                    obj[zzx] = zzay.readBytes();
                    this.zzbg = obj;
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
        int i = 0;
        if (this.zzbf != null && this.zzbf.length > 0) {
            for (zzbh zzbh : this.zzbf) {
                if (zzbh != null) {
                    zzaz.zza(1, zzbh);
                }
            }
        }
        if (this.timestamp != 0) {
            zzaz.zza(2, this.timestamp);
        }
        if (this.zzbg != null && this.zzbg.length > 0) {
            while (i < this.zzbg.length) {
                byte[] bArr = this.zzbg[i];
                if (bArr != null) {
                    zzaz.zza(3, bArr);
                }
                i++;
            }
        }
        super.zza(zzaz);
    }

    protected final int zzt() {
        int zzt = super.zzt();
        if (this.zzbf != null && this.zzbf.length > 0) {
            for (zzbh zzbh : this.zzbf) {
                if (zzbh != null) {
                    zzt += zzaz.zzb(1, zzbh);
                }
            }
        }
        if (this.timestamp != 0) {
            zzt += zzaz.zzl(2) + 8;
        }
        if (this.zzbg == null || this.zzbg.length <= 0) {
            return zzt;
        }
        int i = 0;
        int i2 = 0;
        for (byte[] bArr : this.zzbg) {
            if (bArr != null) {
                i2++;
                i += zzaz.zzb(bArr);
            }
        }
        return (zzt + i) + (i2 * 1);
    }
}
