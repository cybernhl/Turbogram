package p000a;

import javax.annotation.Nullable;

/* renamed from: a.h */
final class C0011h {
    /* renamed from: a */
    final byte[] f19a;
    /* renamed from: b */
    int f20b;
    /* renamed from: c */
    int f21c;
    /* renamed from: d */
    boolean f22d;
    /* renamed from: e */
    boolean f23e;
    /* renamed from: f */
    C0011h f24f;
    /* renamed from: g */
    C0011h f25g;

    C0011h() {
        this.f19a = new byte[8192];
        this.f23e = true;
        this.f22d = false;
    }

    C0011h(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f19a = bArr;
        this.f20b = i;
        this.f21c = i2;
        this.f22d = z;
        this.f23e = z2;
    }

    /* renamed from: a */
    final C0011h m48a() {
        this.f22d = true;
        return new C0011h(this.f19a, this.f20b, this.f21c, true, false);
    }

    /* renamed from: a */
    public final C0011h m49a(int i) {
        if (i <= 0 || i > this.f21c - this.f20b) {
            throw new IllegalArgumentException();
        }
        C0011h a;
        if (i >= 1024) {
            a = m48a();
        } else {
            a = C0012i.m54a();
            System.arraycopy(this.f19a, this.f20b, a.f19a, 0, i);
        }
        a.f21c = a.f20b + i;
        this.f20b += i;
        this.f25g.m50a(a);
        return a;
    }

    /* renamed from: a */
    public final C0011h m50a(C0011h c0011h) {
        c0011h.f25g = this;
        c0011h.f24f = this.f24f;
        this.f24f.f25g = c0011h;
        this.f24f = c0011h;
        return c0011h;
    }

    /* renamed from: a */
    public final void m51a(C0011h c0011h, int i) {
        if (c0011h.f23e) {
            if (c0011h.f21c + i > 8192) {
                if (c0011h.f22d) {
                    throw new IllegalArgumentException();
                } else if ((c0011h.f21c + i) - c0011h.f20b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(c0011h.f19a, c0011h.f20b, c0011h.f19a, 0, c0011h.f21c - c0011h.f20b);
                    c0011h.f21c -= c0011h.f20b;
                    c0011h.f20b = 0;
                }
            }
            System.arraycopy(this.f19a, this.f20b, c0011h.f19a, c0011h.f21c, i);
            c0011h.f21c += i;
            this.f20b += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    /* renamed from: b */
    public final C0011h m52b() {
        C0011h c0011h = this.f24f != this ? this.f24f : null;
        this.f25g.f24f = this.f24f;
        this.f24f.f25g = this.f25g;
        this.f24f = null;
        this.f25g = null;
        return c0011h;
    }

    /* renamed from: c */
    public final void m53c() {
        if (this.f25g == this) {
            throw new IllegalStateException();
        } else if (this.f25g.f23e) {
            int i = this.f21c - this.f20b;
            if (i <= (this.f25g.f22d ? 0 : this.f25g.f20b) + (8192 - this.f25g.f21c)) {
                m51a(this.f25g, i);
                m52b();
                C0012i.m55a(this);
            }
        }
    }
}
