package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p011f.C0180b;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.a.a.a.g.j */
class C0198j {
    /* renamed from: d */
    private static final AtomicInteger f163d = new AtomicInteger(0);
    /* renamed from: a */
    public final String f164a;
    /* renamed from: b */
    private C0182b f165b = null;
    /* renamed from: c */
    private C0182b f166c = null;
    /* renamed from: e */
    private final C0195c f167e;

    public C0198j(C0195c c0195c, String str) {
        this.f167e = c0195c;
        this.f164a = str + "_" + f163d.incrementAndGet();
    }

    /* renamed from: a */
    private void m314a(C0182b c0182b, C0182b c0182b2) {
        if (this.f166c == c0182b2) {
            this.f166c = c0182b;
        }
        if (c0182b == null) {
            this.f165b = c0182b2.f125b;
        } else {
            c0182b.f125b = c0182b2.f125b;
        }
        this.f167e.m306a(c0182b2);
    }

    /* renamed from: a */
    protected void mo347a(C0182b c0182b) {
        C0180b.m231a("[%s] post message %s", this.f164a, c0182b);
        if (this.f166c == null) {
            this.f165b = c0182b;
            this.f166c = c0182b;
            return;
        }
        this.f166c.f125b = c0182b;
        this.f166c = c0182b;
    }

    /* renamed from: b */
    public void mo348b() {
        while (this.f165b != null) {
            C0182b c0182b = this.f165b;
            this.f165b = c0182b.f125b;
            this.f167e.m306a(c0182b);
        }
        this.f166c = null;
    }

    /* renamed from: b */
    protected void m317b(C0173d c0173d) {
        C0182b c0182b = null;
        C0182b c0182b2 = this.f165b;
        while (c0182b2 != null) {
            C0182b c0182b3;
            if (c0173d.mo340a(c0182b2)) {
                c0182b3 = c0182b2.f125b;
                m314a(c0182b, c0182b2);
            } else {
                c0182b3 = c0182b2.f125b;
                c0182b = c0182b2;
            }
            c0182b2 = c0182b3;
        }
    }

    /* renamed from: c */
    C0182b m318c() {
        C0182b c0182b = this.f165b;
        C0180b.m231a("[%s] remove message %s", this.f164a, c0182b);
        if (c0182b != null) {
            this.f165b = c0182b.f125b;
            if (this.f166c == c0182b) {
                this.f166c = null;
            }
        }
        return c0182b;
    }
}
