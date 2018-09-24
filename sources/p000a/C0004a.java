package p000a;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import javax.annotation.Nullable;

/* renamed from: a.a */
public final class C0004a implements C0001b, C0003c, Cloneable, ByteChannel {
    /* renamed from: c */
    private static final byte[] f0c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    @Nullable
    /* renamed from: a */
    C0011h f1a;
    /* renamed from: b */
    long f2b;

    /* renamed from: a */
    public int m4a(byte[] bArr, int i, int i2) {
        C0016n.m68a((long) bArr.length, (long) i, (long) i2);
        C0011h c0011h = this.f1a;
        if (c0011h == null) {
            return -1;
        }
        int min = Math.min(i2, c0011h.f21c - c0011h.f20b);
        System.arraycopy(c0011h.f19a, c0011h.f20b, bArr, i, min);
        c0011h.f20b += min;
        this.f2b -= (long) min;
        if (c0011h.f20b != c0011h.f21c) {
            return min;
        }
        this.f1a = c0011h.m52b();
        C0012i.m55a(c0011h);
        return min;
    }

    /* renamed from: a */
    public final long m5a() {
        long j = this.f2b;
        if (j == 0) {
            return 0;
        }
        C0011h c0011h = this.f1a.f25g;
        return (c0011h.f21c >= 8192 || !c0011h.f23e) ? j : j - ((long) (c0011h.f21c - c0011h.f20b));
    }

    /* renamed from: a */
    public long m6a(C0002l c0002l) {
        if (c0002l == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long b = c0002l.mo3b(this, 8192);
            if (b == -1) {
                return j;
            }
            j += b;
        }
    }

    /* renamed from: a */
    C0011h m7a(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.f1a == null) {
            this.f1a = C0012i.m54a();
            C0011h c0011h = this.f1a;
            C0011h c0011h2 = this.f1a;
            r0 = this.f1a;
            c0011h2.f25g = r0;
            c0011h.f24f = r0;
            return r0;
        } else {
            r0 = this.f1a.f25g;
            return (r0.f21c + i > 8192 || !r0.f23e) ? r0.m50a(C0012i.m54a()) : r0;
        }
    }

    /* renamed from: a */
    public void mo2a(C0004a c0004a, long j) {
        if (c0004a == null) {
            throw new IllegalArgumentException("source == null");
        } else if (c0004a == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            C0016n.m68a(c0004a.f2b, 0, j);
            while (j > 0) {
                C0011h c0011h;
                if (j < ((long) (c0004a.f1a.f21c - c0004a.f1a.f20b))) {
                    c0011h = this.f1a != null ? this.f1a.f25g : null;
                    if (c0011h != null && c0011h.f23e) {
                        if ((((long) c0011h.f21c) + j) - ((long) (c0011h.f22d ? 0 : c0011h.f20b)) <= 8192) {
                            c0004a.f1a.m51a(c0011h, (int) j);
                            c0004a.f2b -= j;
                            this.f2b += j;
                            return;
                        }
                    }
                    c0004a.f1a = c0004a.f1a.m49a((int) j);
                }
                C0011h c0011h2 = c0004a.f1a;
                long j2 = (long) (c0011h2.f21c - c0011h2.f20b);
                c0004a.f1a = c0011h2.m52b();
                if (this.f1a == null) {
                    this.f1a = c0011h2;
                    c0011h2 = this.f1a;
                    c0011h = this.f1a;
                    C0011h c0011h3 = this.f1a;
                    c0011h.f25g = c0011h3;
                    c0011h2.f24f = c0011h3;
                } else {
                    this.f1a.f25g.m50a(c0011h2).m53c();
                }
                c0004a.f2b -= j2;
                this.f2b += j2;
                j -= j2;
            }
        }
    }

    /* renamed from: a */
    public void m9a(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int a = m4a(bArr, i, bArr.length - i);
            if (a == -1) {
                throw new EOFException();
            }
            i += a;
        }
    }

    /* renamed from: a */
    public byte[] m10a(long j) {
        C0016n.m68a(this.f2b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        m9a(bArr);
        return bArr;
    }

    /* renamed from: b */
    public long mo3b(C0004a c0004a, long j) {
        if (c0004a == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f2b == 0) {
            return -1;
        } else {
            if (j > this.f2b) {
                j = this.f2b;
            }
            c0004a.mo2a(this, j);
            return j;
        }
    }

    /* renamed from: b */
    public C0004a m12b(byte[] bArr) {
        if (bArr != null) {
            return m13b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: b */
    public C0004a m13b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        C0016n.m68a((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            C0011h a = m7a(1);
            int min = Math.min(i3 - i, 8192 - a.f21c);
            System.arraycopy(bArr, i, a.f19a, a.f21c, min);
            i += min;
            a.f21c = min + a.f21c;
        }
        this.f2b += (long) i2;
        return this;
    }

    /* renamed from: b */
    public final C0005d m14b(int i) {
        return i == 0 ? C0005d.f4b : new C0013j(this, i);
    }

    /* renamed from: b */
    public void m15b(long j) {
        while (j > 0) {
            if (this.f1a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.f1a.f21c - this.f1a.f20b));
            this.f2b -= (long) min;
            j -= (long) min;
            C0011h c0011h = this.f1a;
            c0011h.f20b = min + c0011h.f20b;
            if (this.f1a.f20b == this.f1a.f21c) {
                C0011h c0011h2 = this.f1a;
                this.f1a = c0011h2.m52b();
                C0012i.m55a(c0011h2);
            }
        }
    }

    /* renamed from: b */
    public byte[] mo4b() {
        try {
            return m10a(this.f2b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: c */
    public /* synthetic */ C0001b mo5c(byte[] bArr) {
        return m12b(bArr);
    }

    /* renamed from: c */
    public final void m18c() {
        try {
            m15b(this.f2b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public /* synthetic */ Object clone() {
        return m19d();
    }

    public void close() {
    }

    /* renamed from: d */
    public C0004a m19d() {
        C0004a c0004a = new C0004a();
        if (this.f2b == 0) {
            return c0004a;
        }
        c0004a.f1a = this.f1a.m48a();
        C0011h c0011h = c0004a.f1a;
        C0011h c0011h2 = c0004a.f1a;
        C0011h c0011h3 = c0004a.f1a;
        c0011h2.f25g = c0011h3;
        c0011h.f24f = c0011h3;
        for (c0011h = this.f1a.f24f; c0011h != this.f1a; c0011h = c0011h.f24f) {
            c0004a.f1a.f25g.m50a(c0011h.m48a());
        }
        c0004a.f2b = this.f2b;
        return c0004a;
    }

    /* renamed from: e */
    public final C0005d m20e() {
        if (this.f2b <= 2147483647L) {
            return m14b((int) this.f2b);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f2b);
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0004a)) {
            return false;
        }
        C0004a c0004a = (C0004a) obj;
        if (this.f2b != c0004a.f2b) {
            return false;
        }
        if (this.f2b == 0) {
            return true;
        }
        C0011h c0011h = this.f1a;
        C0011h c0011h2 = c0004a.f1a;
        int i = c0011h.f20b;
        int i2 = c0011h2.f20b;
        while (j < this.f2b) {
            long min = (long) Math.min(c0011h.f21c - i, c0011h2.f21c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (c0011h.f19a[i] != c0011h2.f19a[i2]) {
                    return false;
                }
                i3++;
                i2 = i5;
                i = i4;
            }
            if (i == c0011h.f21c) {
                c0011h = c0011h.f24f;
                i = c0011h.f20b;
            }
            if (i2 == c0011h2.f21c) {
                c0011h2 = c0011h2.f24f;
                i2 = c0011h2.f20b;
            }
            j += min;
        }
        return true;
    }

    public void flush() {
    }

    public int hashCode() {
        C0011h c0011h = this.f1a;
        if (c0011h == null) {
            return 0;
        }
        int i = 1;
        do {
            for (int i2 = c0011h.f20b; i2 < c0011h.f21c; i2++) {
                i = (i * 31) + c0011h.f19a[i2];
            }
            c0011h = c0011h.f24f;
        } while (c0011h != this.f1a);
        return i;
    }

    public boolean isOpen() {
        return true;
    }

    public int read(ByteBuffer byteBuffer) {
        C0011h c0011h = this.f1a;
        if (c0011h == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), c0011h.f21c - c0011h.f20b);
        byteBuffer.put(c0011h.f19a, c0011h.f20b, min);
        c0011h.f20b += min;
        this.f2b -= (long) min;
        if (c0011h.f20b != c0011h.f21c) {
            return min;
        }
        this.f1a = c0011h.m52b();
        C0012i.m55a(c0011h);
        return min;
    }

    public String toString() {
        return m20e().toString();
    }

    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            C0011h a = m7a(1);
            int min = Math.min(i, 8192 - a.f21c);
            byteBuffer.get(a.f19a, a.f21c, min);
            i -= min;
            a.f21c = min + a.f21c;
        }
        this.f2b += (long) remaining;
        return remaining;
    }
}
