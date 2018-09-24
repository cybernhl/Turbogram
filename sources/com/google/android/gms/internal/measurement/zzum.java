package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzum extends zzul {
    protected final byte[] zzbug;

    zzum(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzbug = bArr;
    }

    public byte zzal(int i) {
        return this.zzbug[i];
    }

    public int size() {
        return this.zzbug.length;
    }

    public final zzud zzb(int i, int i2) {
        int zzb = zzud.zzb(0, i2, size());
        if (zzb == 0) {
            return zzud.zzbtz;
        }
        return new zzuh(this.zzbug, zzud(), zzb);
    }

    final void zza(zzuc zzuc) throws IOException {
        zzuc.zza(this.zzbug, zzud(), size());
    }

    protected final String zza(Charset charset) {
        return new String(this.zzbug, zzud(), size(), charset);
    }

    public final boolean zzub() {
        int zzud = zzud();
        return zzyj.zzf(this.zzbug, zzud, size() + zzud);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzud)) {
            return false;
        }
        if (size() != ((zzud) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzum)) {
            return obj.equals(this);
        }
        zzum zzum = (zzum) obj;
        int zzuc = zzuc();
        int zzuc2 = zzum.zzuc();
        if (zzuc == 0 || zzuc2 == 0 || zzuc == zzuc2) {
            return zza((zzum) obj, 0, size());
        }
        return false;
    }

    final boolean zza(zzud zzud, int i, int i2) {
        if (i2 > zzud.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i2 > zzud.size()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzud.size());
        } else if (!(zzud instanceof zzum)) {
            return zzud.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzum zzum = (zzum) zzud;
            byte[] bArr = this.zzbug;
            byte[] bArr2 = zzum.zzbug;
            int zzud2 = zzud() + i2;
            int zzud3 = zzud();
            int zzud4 = zzum.zzud();
            while (zzud3 < zzud2) {
                if (bArr[zzud3] != bArr2[zzud4]) {
                    return false;
                }
                zzud3++;
                zzud4++;
            }
            return true;
        }
    }

    protected final int zza(int i, int i2, int i3) {
        return zzvo.zza(i, this.zzbug, zzud(), i3);
    }

    protected int zzud() {
        return 0;
    }
}
