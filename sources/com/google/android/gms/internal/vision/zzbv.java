package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbv extends zzbu {
    protected final byte[] zzha;

    zzbv(byte[] bArr) {
        this.zzha = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbo)) {
            return false;
        }
        if (size() != ((zzbo) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbv)) {
            return obj.equals(this);
        }
        zzbv zzbv = (zzbv) obj;
        int zzau = zzau();
        int zzau2 = zzbv.zzau();
        return (zzau == 0 || zzau2 == 0 || zzau == zzau2) ? zza((zzbv) obj, 0, size()) : false;
    }

    public int size() {
        return this.zzha.length;
    }

    protected final int zza(int i, int i2, int i3) {
        return zzct.zza(i, this.zzha, zzav(), i3);
    }

    protected final String zza(Charset charset) {
        return new String(this.zzha, zzav(), size(), charset);
    }

    final void zza(zzbn zzbn) throws IOException {
        zzbn.zza(this.zzha, zzav(), size());
    }

    final boolean zza(zzbo zzbo, int i, int i2) {
        if (i2 > zzbo.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i2 > zzbo.size()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzbo.size());
        } else if (!(zzbo instanceof zzbv)) {
            return zzbo.zzc(0, i2).equals(zzc(0, i2));
        } else {
            zzbv zzbv = (zzbv) zzbo;
            byte[] bArr = this.zzha;
            byte[] bArr2 = zzbv.zzha;
            int zzav = zzav() + i2;
            int zzav2 = zzav();
            int zzav3 = zzbv.zzav();
            while (zzav2 < zzav) {
                if (bArr[zzav2] != bArr2[zzav3]) {
                    return false;
                }
                zzav2++;
                zzav3++;
            }
            return true;
        }
    }

    public final boolean zzat() {
        int zzav = zzav();
        return zzfn.zze(this.zzha, zzav, size() + zzav);
    }

    protected int zzav() {
        return 0;
    }

    public final zzbo zzc(int i, int i2) {
        int zzb = zzbo.zzb(0, i2, size());
        return zzb == 0 ? zzbo.zzgt : new zzbr(this.zzha, zzav(), zzb);
    }

    public byte zzl(int i) {
        return this.zzha[i];
    }
}
