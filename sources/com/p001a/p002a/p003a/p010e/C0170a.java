package com.p001a.p002a.p003a.p010e;

import android.support.annotation.NonNull;
import com.p001a.p002a.p003a.C0153m;
import com.p001a.p002a.p003a.C0171e;
import com.p001a.p002a.p003a.C0229j;
import com.p001a.p002a.p003a.p008c.C0164a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.a.a.a.e.a */
public class C0170a implements C0153m {
    /* renamed from: a */
    private final TreeSet<C0229j> f85a = new TreeSet(new C01691(this));
    /* renamed from: b */
    private final Map<String, C0229j> f86b = new HashMap();
    /* renamed from: c */
    private final AtomicLong f87c = new AtomicLong(0);
    /* renamed from: d */
    private final List<String> f88d = new ArrayList();
    /* renamed from: e */
    private final long f89e;

    /* renamed from: com.a.a.a.e.a$1 */
    class C01691 implements Comparator<C0229j> {
        /* renamed from: a */
        final /* synthetic */ C0170a f84a;

        C01691(C0170a c0170a) {
            this.f84a = c0170a;
        }

        /* renamed from: a */
        private int m171a(int i, int i2) {
            return i > i2 ? -1 : i2 > i ? 1 : 0;
        }

        /* renamed from: a */
        private int m172a(long j, long j2) {
            return j > j2 ? -1 : j2 > j ? 1 : 0;
        }

        /* renamed from: a */
        public int m173a(C0229j c0229j, C0229j c0229j2) {
            if (c0229j.m465j().m408a().equals(c0229j2.m465j().m408a())) {
                return 0;
            }
            int a = m171a(c0229j.m453b(), c0229j2.m453b());
            if (a != 0) {
                return a;
            }
            a = -m172a(c0229j.m460e(), c0229j2.m460e());
            return a == 0 ? -m172a(c0229j.m456c().longValue(), c0229j2.m456c().longValue()) : a;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m173a((C0229j) obj, (C0229j) obj2);
        }
    }

    public C0170a(C0164a c0164a, long j) {
        this.f89e = j;
    }

    /* renamed from: a */
    private static boolean m174a(C0229j c0229j, C0171e c0171e, boolean z) {
        boolean z2 = c0171e.m202h() >= c0229j.m462g() || (z && c0229j.m473r());
        return (z2 || c0171e.m187a() >= c0229j.m477v()) ? (c0171e.m200f() == null || c0229j.m464i() <= c0171e.m200f().longValue()) ? ((c0229j.m466k() == null || !c0171e.m198d().contains(c0229j.m466k())) && !c0171e.m201g().contains(c0229j.m447a())) ? c0171e.m195b() == null || !(c0229j.m467l() == null || c0171e.m197c().isEmpty() || !c0171e.m195b().m558a(c0171e.m197c(), c0229j.m467l())) : false : false : false;
    }

    /* renamed from: a */
    public int mo321a() {
        return this.f85a.size();
    }

    /* renamed from: a */
    public int mo322a(@NonNull C0171e c0171e) {
        this.f88d.clear();
        Iterator it = this.f85a.iterator();
        int i = 0;
        while (it.hasNext()) {
            C0229j c0229j = (C0229j) it.next();
            String k = c0229j.m466k();
            if ((k == null || !this.f88d.contains(k)) && C0170a.m174a(c0229j, c0171e, false)) {
                i++;
                if (k != null) {
                    this.f88d.add(k);
                }
            }
            i = i;
        }
        this.f88d.clear();
        return i;
    }

    /* renamed from: a */
    public C0229j mo323a(@NonNull String str) {
        return (C0229j) this.f86b.get(str);
    }

    /* renamed from: a */
    public void mo324a(@NonNull C0229j c0229j, @NonNull C0229j c0229j2) {
        mo330c(c0229j2);
        mo325a(c0229j);
    }

    /* renamed from: a */
    public boolean mo325a(@NonNull C0229j c0229j) {
        c0229j.m449a(this.f87c.incrementAndGet());
        if (((C0229j) this.f86b.get(c0229j.m447a())) != null) {
            throw new IllegalArgumentException("cannot add a job with the same id twice");
        }
        this.f86b.put(c0229j.m447a(), c0229j);
        this.f85a.add(c0229j);
        return true;
    }

    /* renamed from: b */
    public C0229j mo326b(@NonNull C0171e c0171e) {
        Iterator it = this.f85a.iterator();
        while (it.hasNext()) {
            C0229j c0229j = (C0229j) it.next();
            if (C0170a.m174a(c0229j, c0171e, false)) {
                mo330c(c0229j);
                c0229j.m454b(c0229j.m459d() + 1);
                c0229j.m458c(this.f89e);
                return c0229j;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void mo327b() {
        this.f85a.clear();
        this.f86b.clear();
    }

    /* renamed from: b */
    public boolean mo328b(@NonNull C0229j c0229j) {
        if (c0229j.m456c() == null) {
            return mo325a(c0229j);
        }
        C0229j c0229j2 = (C0229j) this.f86b.get(c0229j.m447a());
        if (c0229j2 != null) {
            mo330c(c0229j2);
        }
        this.f86b.put(c0229j.m447a(), c0229j);
        this.f85a.add(c0229j);
        return true;
    }

    /* renamed from: c */
    public Long mo329c(@NonNull C0171e c0171e) {
        Long l = null;
        Iterator it = this.f85a.iterator();
        while (it.hasNext()) {
            Long valueOf;
            C0229j c0229j = (C0229j) it.next();
            if (C0170a.m174a(c0229j, c0171e, true)) {
                boolean z = c0229j.m474s() && C0170a.m174a(c0229j, c0171e, false);
                boolean r = c0229j.m473r();
                long min = r == z ? Math.min(c0229j.m462g(), c0229j.m464i()) : r ? c0229j.m462g() : c0229j.m464i();
                if (l == null || min < l.longValue()) {
                    valueOf = Long.valueOf(min);
                    l = valueOf;
                }
            }
            valueOf = l;
            l = valueOf;
        }
        return l;
    }

    /* renamed from: c */
    public void mo330c(@NonNull C0229j c0229j) {
        this.f86b.remove(c0229j.m447a());
        this.f85a.remove(c0229j);
    }

    @NonNull
    /* renamed from: d */
    public Set<C0229j> mo331d(@NonNull C0171e c0171e) {
        Set<C0229j> hashSet = new HashSet();
        Iterator it = this.f85a.iterator();
        while (it.hasNext()) {
            C0229j c0229j = (C0229j) it.next();
            if (C0170a.m174a(c0229j, c0171e, false)) {
                hashSet.add(c0229j);
            }
        }
        return hashSet;
    }

    /* renamed from: d */
    public void mo332d(C0229j c0229j) {
        mo330c(c0229j);
    }
}
