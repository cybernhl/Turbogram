package com.google.android.gms.internal.vision;

final class zzbr extends zzbv {
    private final int zzgx;
    private final int zzgy;

    zzbr(byte[] bArr, int i, int i2) {
        super(bArr);
        zzbo.zzb(i, i + i2, bArr.length);
        this.zzgx = i;
        this.zzgy = i2;
    }

    public final int size() {
        return this.zzgy;
    }

    protected final int zzav() {
        return this.zzgx;
    }

    public final byte zzl(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzha[this.zzgx + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + size);
    }
}
