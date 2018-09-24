package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p011f.C0180b;

/* renamed from: com.a.a.a.g.a */
class C0194a {
    /* renamed from: a */
    C0182b f152a = null;
    /* renamed from: b */
    final C0195c f153b;

    C0194a(C0195c c0195c) {
        this.f153b = c0195c;
    }

    /* renamed from: a */
    Long m302a(long j, C0196e c0196e) {
        C0180b.m231a("flushing messages at time %s", Long.valueOf(j));
        while (this.f152a != null && this.f152a.f126c <= j) {
            C0182b c0182b = this.f152a;
            this.f152a = c0182b.f125b;
            c0182b.f125b = null;
            c0196e.mo347a(c0182b);
        }
        if (this.f152a == null) {
            return null;
        }
        C0180b.m231a("returning next ready at %d ns", Long.valueOf(this.f152a.f126c - j));
        return Long.valueOf(this.f152a.f126c);
    }

    /* renamed from: a */
    void m303a(C0182b c0182b, long j) {
        C0180b.m231a("add delayed message %s at time %s", c0182b, Long.valueOf(j));
        c0182b.f126c = j;
        if (this.f152a == null) {
            this.f152a = c0182b;
            return;
        }
        C0182b c0182b2 = this.f152a;
        C0182b c0182b3 = null;
        while (c0182b2 != null && c0182b2.f126c <= j) {
            c0182b3 = c0182b2;
            c0182b2 = c0182b2.f125b;
        }
        if (c0182b3 == null) {
            c0182b.f125b = this.f152a;
            this.f152a = c0182b;
            return;
        }
        c0182b3.f125b = c0182b;
        c0182b.f125b = c0182b2;
    }

    /* renamed from: a */
    public void m304a(C0173d c0173d) {
        C0182b c0182b = null;
        C0182b c0182b2 = this.f152a;
        while (c0182b2 != null) {
            boolean a = c0173d.mo340a(c0182b2);
            C0182b c0182b3 = c0182b2.f125b;
            if (a) {
                if (c0182b == null) {
                    this.f152a = c0182b2.f125b;
                } else {
                    c0182b.f125b = c0182b2.f125b;
                }
                this.f153b.m306a(c0182b2);
            } else {
                c0182b = c0182b2;
            }
            c0182b2 = c0182b3;
        }
    }
}
