package com.google.android.gms.internal.vision;

final class zzbz extends zzbx {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzhf;
    private int zzhg;
    private int zzhh;
    private int zzhi;

    private zzbz(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzhi = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i + i2;
        this.pos = i;
        this.zzhh = this.pos;
        this.zzhf = z;
    }

    public final int zzay() {
        return this.pos - this.zzhh;
    }

    public final int zzn(int i) throws zzcx {
        if (i < 0) {
            throw zzcx.zzcc();
        }
        int zzay = zzay() + i;
        int i2 = this.zzhi;
        if (zzay > i2) {
            throw zzcx.zzcb();
        }
        this.zzhi = zzay;
        this.limit += this.zzhg;
        zzay = this.limit - this.zzhh;
        if (zzay > this.zzhi) {
            this.zzhg = zzay - this.zzhi;
            this.limit -= this.zzhg;
        } else {
            this.zzhg = 0;
        }
        return i2;
    }
}
