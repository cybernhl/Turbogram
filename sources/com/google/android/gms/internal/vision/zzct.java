package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzct {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzlo;
    private static final ByteBuffer zzlp;
    private static final zzbx zzlq;

    static {
        byte[] bArr = new byte[0];
        zzlo = bArr;
        zzlp = ByteBuffer.wrap(bArr);
        bArr = zzlo;
        zzlq = zzbx.zza(bArr, 0, bArr.length, false);
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        length = zza(length, bArr, 0, length);
        return length == 0 ? 1 : length;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzdx) obj).zzbu().zza((zzdx) obj2).zzbz();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static int zzc(boolean z) {
        return z ? 1231 : 1237;
    }

    static boolean zzf(zzdx zzdx) {
        return false;
    }

    public static boolean zzf(byte[] bArr) {
        return zzfn.zzf(bArr);
    }

    public static String zzg(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzk(long j) {
        return (int) ((j >>> 32) ^ j);
    }
}
