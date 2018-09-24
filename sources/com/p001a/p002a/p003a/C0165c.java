package com.p001a.p002a.p003a;

import com.p001a.p002a.p003a.C0168d.C0166a;
import com.p001a.p002a.p003a.p011f.C0180b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/* renamed from: com.a.a.a.c */
class C0165c {
    /* renamed from: a */
    private Set<String> f76a;
    /* renamed from: b */
    private final C0242s f77b;
    /* renamed from: c */
    private final String[] f78c;
    /* renamed from: d */
    private final Collection<C0229j> f79d = new ArrayList();
    /* renamed from: e */
    private final Collection<C0229j> f80e = new ArrayList();
    /* renamed from: f */
    private final C0166a f81f;

    C0165c(C0242s c0242s, String[] strArr, C0166a c0166a) {
        this.f77b = c0242s;
        this.f78c = strArr;
        this.f81f = c0166a;
    }

    /* renamed from: a */
    void m165a(C0229j c0229j, int i) {
        if (!this.f76a.remove(c0229j.m447a())) {
            return;
        }
        if (i == 3) {
            this.f79d.add(c0229j);
        } else {
            this.f80e.add(c0229j);
        }
    }

    /* renamed from: a */
    void m166a(C0236l c0236l) {
        for (C0229j c0229j : this.f79d) {
            try {
                c0229j.m457c(3);
            } catch (Throwable th) {
                C0180b.m232a(th, "job's on cancel has thrown an exception. Ignoring...", new Object[0]);
            }
            if (c0229j.m465j().m416e()) {
                c0236l.f329b.mo330c(c0229j);
            }
        }
        if (this.f81f != null) {
            Collection arrayList = new ArrayList(this.f79d.size());
            Collection arrayList2 = new ArrayList(this.f80e.size());
            for (C0229j c0229j2 : this.f79d) {
                arrayList.add(c0229j2.m465j());
            }
            for (C0229j c0229j22 : this.f80e) {
                arrayList2.add(c0229j22.m465j());
            }
            c0236l.f333f.m138a(new C0168d(arrayList, arrayList2), this.f81f);
        }
        for (C0229j c0229j222 : this.f79d) {
            c0236l.f333f.m141a(c0229j222.m465j(), true, c0229j222.m476u());
        }
    }

    /* renamed from: a */
    void m167a(C0236l c0236l, C0181f c0181f) {
        this.f76a = c0181f.m240a(this.f77b, this.f78c);
        C0171e c0171e = c0236l.f332e;
        c0171e.m203i();
        c0171e.m189a(c0236l.f328a.mo356a());
        c0171e.m190a(this.f77b);
        c0171e.m196b(this.f76a);
        c0171e.m194a(this.f78c);
        c0171e.m193a(true);
        c0171e.m188a(2);
        Set<C0229j> d = c0236l.f330c.mo331d(c0171e);
        Set<C0229j> d2 = c0236l.f329b.mo331d(c0171e);
        for (C0229j c0229j : d) {
            c0229j.m468m();
            this.f79d.add(c0229j);
            c0236l.f330c.mo332d(c0229j);
        }
        for (C0229j c0229j2 : d2) {
            c0229j2.m468m();
            this.f79d.add(c0229j2);
            c0236l.f329b.mo332d(c0229j2);
        }
    }

    /* renamed from: a */
    boolean m168a() {
        return this.f76a.isEmpty();
    }
}
