package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzbo implements Serializable, Iterable<Byte> {
    public static final zzbo zzgt = new zzbv(zzct.zzlo);
    private static final zzbs zzgu = (zzbj.zzaq() ? new zzbw() : new zzbq());
    private int zzgv = 0;

    zzbo() {
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((((i | i2) | i4) | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }

    public static zzbo zzb(byte[] bArr, int i, int i2) {
        return new zzbv(zzgu.zzc(bArr, i, i2));
    }

    public static zzbo zzg(String str) {
        return new zzbv(str.getBytes(zzct.UTF_8));
    }

    static zzbt zzm(int i) {
        return new zzbt(i);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzgv;
        if (i == 0) {
            i = size();
            i = zza(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzgv = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzbp(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    protected abstract int zza(int i, int i2, int i3);

    protected abstract String zza(Charset charset);

    abstract void zza(zzbn zzbn) throws IOException;

    public final String zzas() {
        return size() == 0 ? "" : zza(zzct.UTF_8);
    }

    public abstract boolean zzat();

    protected final int zzau() {
        return this.zzgv;
    }

    public abstract zzbo zzc(int i, int i2);

    public abstract byte zzl(int i);
}
