package com.google.android.gms.internal.config;

import java.io.IOException;

public final class zzau extends zzbb<zzau> {
    public int zzbk;
    public boolean zzbl;
    private long zzbm;

    public zzau() {
        this.zzbk = 0;
        this.zzbl = false;
        this.zzbm = 0;
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzau)) {
            return false;
        }
        zzau zzau = (zzau) obj;
        return this.zzbk != zzau.zzbk ? false : this.zzbl != zzau.zzbl ? false : this.zzbm != zzau.zzbm ? false : (this.zzch == null || this.zzch.isEmpty()) ? zzau.zzch == null || zzau.zzch.isEmpty() : this.zzch.equals(zzau.zzch);
    }

    public final int hashCode() {
        int hashCode = ((((this.zzbl ? 1231 : 1237) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzbk) * 31)) * 31) + ((int) (this.zzbm ^ (this.zzbm >>> 32)))) * 31;
        int hashCode2 = (this.zzch == null || this.zzch.isEmpty()) ? 0 : this.zzch.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzbh zza(zzay zzay) throws IOException {
        while (true) {
            int zzx = zzay.zzx();
            switch (zzx) {
                case 0:
                    break;
                case 8:
                    this.zzbk = zzay.zzy();
                    continue;
                case 16:
                    this.zzbl = zzay.zzy() != 0;
                    continue;
                case 25:
                    this.zzbm = zzay.zzz();
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
        int i = 1;
        if (this.zzbk != 0) {
            zzaz.zzc(1, this.zzbk);
        }
        if (this.zzbl) {
            boolean z = this.zzbl;
            zzaz.zze(2, 0);
            if (!z) {
                i = 0;
            }
            zzaz.zza((byte) i);
        }
        if (this.zzbm != 0) {
            zzaz.zza(3, this.zzbm);
        }
        super.zza(zzaz);
    }

    protected final int zzt() {
        int zzt = super.zzt();
        if (this.zzbk != 0) {
            zzt += zzaz.zzd(1, this.zzbk);
        }
        if (this.zzbl) {
            zzt += zzaz.zzl(2) + 1;
        }
        return this.zzbm != 0 ? zzt + (zzaz.zzl(3) + 8) : zzt;
    }
}
