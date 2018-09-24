package p000a;

import java.io.Serializable;
import java.util.Arrays;

/* renamed from: a.d */
public class C0005d implements Serializable, Comparable<C0005d> {
    /* renamed from: a */
    static final char[] f3a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    public static final C0005d f4b = C0005d.m22a(new byte[0]);
    /* renamed from: c */
    final byte[] f5c;
    /* renamed from: d */
    transient int f6d;
    /* renamed from: e */
    transient String f7e;

    C0005d(byte[] bArr) {
        this.f5c = bArr;
    }

    /* renamed from: a */
    static int m21a(String str, int i) {
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            if (i2 == i) {
                return i3;
            }
            int codePointAt = str.codePointAt(i3);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i2++;
            i3 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    /* renamed from: a */
    public static C0005d m22a(byte... bArr) {
        if (bArr != null) {
            return new C0005d((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    /* renamed from: a */
    public byte mo7a(int i) {
        return this.f5c[i];
    }

    /* renamed from: a */
    public int m24a(C0005d c0005d) {
        int c = mo13c();
        int c2 = c0005d.mo13c();
        int min = Math.min(c, c2);
        int i = 0;
        while (i < min) {
            int a = mo7a(i) & 255;
            int a2 = c0005d.mo7a(i) & 255;
            if (a != a2) {
                return a < a2 ? -1 : 1;
            } else {
                i++;
            }
        }
        return c == c2 ? 0 : c >= c2 ? 1 : -1;
    }

    /* renamed from: a */
    public C0005d mo8a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 > this.f5c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f5c.length + ")");
        } else {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.f5c.length) {
                return this;
            } else {
                Object obj = new byte[i3];
                System.arraycopy(this.f5c, i, obj, 0, i3);
                return new C0005d(obj);
            }
        }
    }

    /* renamed from: a */
    public String mo9a() {
        String str = this.f7e;
        if (str != null) {
            return str;
        }
        str = new String(this.f5c, C0016n.f33a);
        this.f7e = str;
        return str;
    }

    /* renamed from: a */
    public boolean mo10a(int i, C0005d c0005d, int i2, int i3) {
        return c0005d.mo11a(i2, this.f5c, i, i3);
    }

    /* renamed from: a */
    public boolean mo11a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.f5c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && C0016n.m70a(this.f5c, i, bArr, i2, i3);
    }

    /* renamed from: b */
    public String mo12b() {
        char[] cArr = new char[(this.f5c.length * 2)];
        byte[] bArr = this.f5c;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = f3a[(b >> 4) & 15];
            int i4 = i3 + 1;
            cArr[i3] = f3a[b & 15];
            i++;
            i2 = i4;
        }
        return new String(cArr);
    }

    /* renamed from: c */
    public int mo13c() {
        return this.f5c.length;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m24a((C0005d) obj);
    }

    /* renamed from: d */
    public byte[] mo14d() {
        return (byte[]) this.f5c.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof C0005d) && ((C0005d) obj).mo13c() == this.f5c.length && ((C0005d) obj).mo11a(0, this.f5c, 0, this.f5c.length);
        return z;
    }

    public int hashCode() {
        int i = this.f6d;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.f5c);
        this.f6d = i;
        return i;
    }

    public String toString() {
        if (this.f5c.length == 0) {
            return "[size=0]";
        }
        String a = mo9a();
        int a2 = C0005d.m21a(a, 64);
        if (a2 == -1) {
            return this.f5c.length <= 64 ? "[hex=" + mo12b() + "]" : "[size=" + this.f5c.length + " hex=" + mo8a(0, 64).mo12b() + "…]";
        } else {
            String replace = a.substring(0, a2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            return a2 < a.length() ? "[size=" + this.f5c.length + " text=" + replace + "…]" : "[text=" + replace + "]";
        }
    }
}
