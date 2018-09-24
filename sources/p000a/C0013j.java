package p000a;

import java.util.Arrays;

/* renamed from: a.j */
final class C0013j extends C0005d {
    /* renamed from: f */
    final transient byte[][] f28f;
    /* renamed from: g */
    final transient int[] f29g;

    C0013j(C0004a c0004a, int i) {
        super(null);
        C0016n.m68a(c0004a.f2b, 0, (long) i);
        C0011h c0011h = c0004a.f1a;
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            if (c0011h.f21c == c0011h.f20b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += c0011h.f21c - c0011h.f20b;
            i2++;
            c0011h = c0011h.f24f;
        }
        this.f28f = new byte[i2][];
        this.f29g = new int[(i2 * 2)];
        C0011h c0011h2 = c0004a.f1a;
        i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            this.f28f[i3] = c0011h2.f19a;
            int i5 = (c0011h2.f21c - c0011h2.f20b) + i4;
            if (i5 > i) {
                i5 = i;
            }
            this.f29g[i3] = i5;
            this.f29g[this.f28f.length + i3] = c0011h2.f20b;
            c0011h2.f22d = true;
            i3++;
            c0011h2 = c0011h2.f24f;
            i4 = i5;
        }
    }

    /* renamed from: b */
    private int m56b(int i) {
        int binarySearch = Arrays.binarySearch(this.f29g, 0, this.f28f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    /* renamed from: e */
    private C0005d m57e() {
        return new C0005d(mo14d());
    }

    /* renamed from: a */
    public byte mo7a(int i) {
        C0016n.m68a((long) this.f29g[this.f28f.length - 1], (long) i, 1);
        int b = m56b(i);
        return this.f28f[b][(i - (b == 0 ? 0 : this.f29g[b - 1])) + this.f29g[this.f28f.length + b]];
    }

    /* renamed from: a */
    public C0005d mo8a(int i, int i2) {
        return m57e().mo8a(i, i2);
    }

    /* renamed from: a */
    public String mo9a() {
        return m57e().mo9a();
    }

    /* renamed from: a */
    public boolean mo10a(int i, C0005d c0005d, int i2, int i3) {
        if (i < 0 || i > mo13c() - i3) {
            return false;
        }
        int b = m56b(i);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.f29g[b - 1];
            int min = Math.min(i3, ((this.f29g[b] - i4) + i4) - i);
            if (!c0005d.mo11a(i2, this.f28f[b], (i - i4) + this.f29g[this.f28f.length + b], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo11a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > mo13c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b = m56b(i);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.f29g[b - 1];
            int min = Math.min(i3, ((this.f29g[b] - i4) + i4) - i);
            if (!C0016n.m70a(this.f28f[b], (i - i4) + this.f29g[this.f28f.length + b], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    /* renamed from: b */
    public String mo12b() {
        return m57e().mo12b();
    }

    /* renamed from: c */
    public int mo13c() {
        return this.f29g[this.f28f.length - 1];
    }

    /* renamed from: d */
    public byte[] mo14d() {
        Object obj = new byte[this.f29g[this.f28f.length - 1]];
        int length = this.f28f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f29g[length + i];
            int i4 = this.f29g[i];
            System.arraycopy(this.f28f[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof C0005d) && ((C0005d) obj).mo13c() == mo13c() && mo10a(0, (C0005d) obj, 0, mo13c());
        return z;
    }

    public int hashCode() {
        int i = this.d;
        if (i == 0) {
            i = 1;
            int length = this.f28f.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f28f[i2];
                int i4 = this.f29g[length + i2];
                int i5 = this.f29g[i2];
                i3 = i4 + (i5 - i3);
                int i6 = i;
                while (i4 < i3) {
                    i6 = bArr[i4] + (i6 * 31);
                    i4++;
                }
                i2++;
                i3 = i5;
                i = i6;
            }
            this.d = i;
        }
        return i;
    }

    public String toString() {
        return m57e().toString();
    }
}
