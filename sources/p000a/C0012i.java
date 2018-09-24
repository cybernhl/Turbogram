package p000a;

import javax.annotation.Nullable;

/* renamed from: a.i */
final class C0012i {
    @Nullable
    /* renamed from: a */
    static C0011h f26a;
    /* renamed from: b */
    static long f27b;

    private C0012i() {
    }

    /* renamed from: a */
    static C0011h m54a() {
        synchronized (C0012i.class) {
            if (f26a != null) {
                C0011h c0011h = f26a;
                f26a = c0011h.f24f;
                c0011h.f24f = null;
                f27b -= 8192;
                return c0011h;
            }
            return new C0011h();
        }
    }

    /* renamed from: a */
    static void m55a(C0011h c0011h) {
        if (c0011h.f24f != null || c0011h.f25g != null) {
            throw new IllegalArgumentException();
        } else if (!c0011h.f22d) {
            synchronized (C0012i.class) {
                if (f27b + 8192 > 65536) {
                    return;
                }
                f27b += 8192;
                c0011h.f24f = f26a;
                c0011h.f21c = 0;
                c0011h.f20b = 0;
                f26a = c0011h;
            }
        }
    }
}
