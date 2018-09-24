package p000a;

import java.nio.charset.Charset;

/* renamed from: a.n */
final class C0016n {
    /* renamed from: a */
    public static final Charset f33a = Charset.forName("UTF-8");

    /* renamed from: a */
    public static void m68a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    /* renamed from: a */
    public static void m69a(Throwable th) {
        C0016n.m71b(th);
    }

    /* renamed from: a */
    public static boolean m70a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    private static <T extends Throwable> void m71b(Throwable th) {
        throw th;
    }
}
