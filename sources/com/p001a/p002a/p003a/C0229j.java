package com.p001a.p002a.p003a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.Set;

/* renamed from: com.a.a.a.j */
public class C0229j {
    /* renamed from: a */
    public final String f299a;
    /* renamed from: b */
    public final boolean f300b;
    /* renamed from: c */
    public final String f301c;
    /* renamed from: d */
    int f302d;
    /* renamed from: e */
    final transient C0225i f303e;
    /* renamed from: f */
    protected final Set<String> f304f;
    /* renamed from: g */
    C0239q f305g;
    /* renamed from: h */
    private Long f306h;
    /* renamed from: i */
    private int f307i;
    /* renamed from: j */
    private int f308j;
    /* renamed from: k */
    private long f309k;
    /* renamed from: l */
    private long f310l;
    /* renamed from: m */
    private long f311m;
    /* renamed from: n */
    private long f312n;
    /* renamed from: o */
    private boolean f313o;
    /* renamed from: p */
    private volatile boolean f314p;
    /* renamed from: q */
    private volatile boolean f315q;
    @Nullable
    /* renamed from: r */
    private Throwable f316r;

    /* renamed from: com.a.a.a.j$a */
    public static class C0227a {
        /* renamed from: a */
        private int f279a;
        /* renamed from: b */
        private String f280b;
        /* renamed from: c */
        private boolean f281c;
        /* renamed from: d */
        private String f282d;
        /* renamed from: e */
        private int f283e = 0;
        /* renamed from: f */
        private C0225i f284f;
        /* renamed from: g */
        private long f285g;
        /* renamed from: h */
        private long f286h = Long.MIN_VALUE;
        /* renamed from: i */
        private Long f287i;
        /* renamed from: j */
        private long f288j;
        /* renamed from: k */
        private long f289k = Long.MAX_VALUE;
        /* renamed from: l */
        private boolean f290l = false;
        /* renamed from: m */
        private int f291m = 0;
        /* renamed from: n */
        private Set<String> f292n;
        /* renamed from: o */
        private int f293o;

        /* renamed from: a */
        public C0227a m425a(int i) {
            this.f279a = i;
            this.f291m |= 1;
            return this;
        }

        /* renamed from: a */
        public C0227a m426a(long j) {
            this.f285g = j;
            this.f291m |= 32;
            return this;
        }

        /* renamed from: a */
        public C0227a m427a(long j, boolean z) {
            this.f289k = j;
            this.f290l = z;
            this.f291m |= 128;
            return this;
        }

        /* renamed from: a */
        public C0227a m428a(C0225i c0225i) {
            this.f284f = c0225i;
            this.f291m |= 16;
            return this;
        }

        /* renamed from: a */
        public C0227a m429a(String str) {
            this.f282d = str;
            this.f291m |= 8;
            return this;
        }

        /* renamed from: a */
        public C0227a m430a(Set<String> set) {
            this.f292n = set;
            this.f291m |= 512;
            return this;
        }

        /* renamed from: a */
        public C0227a m431a(boolean z) {
            this.f281c = z;
            this.f291m |= 2;
            return this;
        }

        /* renamed from: a */
        public C0229j m432a() {
            if (this.f284f == null) {
                throw new IllegalArgumentException("must provide a job");
            }
            int i = this.f291m & 2047;
            if (i != 2047) {
                throw new IllegalArgumentException("must provide all required fields. your result:" + Long.toBinaryString((long) i));
            }
            C0229j c0229j = new C0229j(this.f280b, this.f281c, this.f279a, this.f282d, this.f283e, this.f284f, this.f285g, this.f286h, this.f288j, this.f292n, this.f293o, this.f289k, this.f290l);
            if (this.f287i != null) {
                c0229j.m449a(this.f287i.longValue());
            }
            this.f284f.m411a(c0229j);
            return c0229j;
        }

        /* renamed from: b */
        public C0227a m433b(int i) {
            this.f283e = i;
            return this;
        }

        /* renamed from: b */
        public C0227a m434b(long j) {
            this.f286h = j;
            this.f291m |= 64;
            return this;
        }

        /* renamed from: b */
        public C0227a m435b(String str) {
            this.f280b = str;
            this.f291m |= 4;
            return this;
        }

        /* renamed from: c */
        public C0227a m436c(int i) {
            this.f293o = i;
            this.f291m |= 1024;
            return this;
        }

        /* renamed from: c */
        public C0227a m437c(long j) {
            this.f287i = Long.valueOf(j);
            return this;
        }

        /* renamed from: d */
        public C0227a m438d(long j) {
            this.f288j = j;
            this.f291m |= 256;
            return this;
        }
    }

    private C0229j(String str, boolean z, int i, String str2, int i2, C0225i c0225i, long j, long j2, long j3, Set<String> set, int i3, long j4, boolean z2) {
        this.f299a = str;
        this.f300b = z;
        this.f307i = i;
        this.f301c = str2;
        this.f308j = i2;
        this.f310l = j;
        this.f309k = j2;
        this.f303e = c0225i;
        this.f311m = j3;
        this.f302d = i3;
        this.f304f = set;
        this.f312n = j4;
        this.f313o = z2;
    }

    /* renamed from: a */
    int m446a(int i, C0231b c0231b) {
        return this.f303e.m406a(this, i, c0231b);
    }

    @NonNull
    /* renamed from: a */
    public String m447a() {
        return this.f299a;
    }

    /* renamed from: a */
    public void m448a(int i) {
        this.f307i = i;
        this.f303e.f266b = this.f307i;
    }

    /* renamed from: a */
    public void m449a(long j) {
        this.f306h = Long.valueOf(j);
    }

    /* renamed from: a */
    public void m450a(Context context) {
        this.f303e.m410a(context);
    }

    /* renamed from: a */
    void m451a(@Nullable Throwable th) {
        this.f316r = th;
    }

    /* renamed from: a */
    public void m452a(boolean z) {
        this.f303e.m412a(z);
    }

    /* renamed from: b */
    public int m453b() {
        return this.f307i;
    }

    /* renamed from: b */
    public void m454b(int i) {
        this.f308j = i;
    }

    /* renamed from: b */
    public void m455b(long j) {
        this.f309k = j;
    }

    /* renamed from: c */
    public Long m456c() {
        return this.f306h;
    }

    /* renamed from: c */
    public void m457c(int i) {
        this.f303e.mo4387a(i, this.f316r);
    }

    /* renamed from: c */
    public void m458c(long j) {
        this.f311m = j;
    }

    /* renamed from: d */
    public int m459d() {
        return this.f308j;
    }

    /* renamed from: e */
    public long m460e() {
        return this.f310l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0229j)) {
            return false;
        }
        return this.f299a.equals(((C0229j) obj).f299a);
    }

    /* renamed from: f */
    public long m461f() {
        return this.f311m;
    }

    /* renamed from: g */
    public long m462g() {
        return this.f312n;
    }

    /* renamed from: h */
    public boolean m463h() {
        return this.f313o;
    }

    public int hashCode() {
        return this.f299a.hashCode();
    }

    /* renamed from: i */
    public long m464i() {
        return this.f309k;
    }

    /* renamed from: j */
    public C0225i m465j() {
        return this.f303e;
    }

    /* renamed from: k */
    public String m466k() {
        return this.f301c;
    }

    /* renamed from: l */
    public Set<String> m467l() {
        return this.f304f;
    }

    /* renamed from: m */
    public void m468m() {
        this.f314p = true;
        this.f303e.f267c = true;
    }

    /* renamed from: n */
    public boolean m469n() {
        return this.f314p;
    }

    /* renamed from: o */
    public void m470o() {
        this.f315q = true;
        m468m();
    }

    /* renamed from: p */
    public boolean m471p() {
        return this.f315q;
    }

    /* renamed from: q */
    public boolean m472q() {
        return this.f304f != null && this.f304f.size() > 0;
    }

    /* renamed from: r */
    public boolean m473r() {
        return this.f312n != Long.MAX_VALUE;
    }

    /* renamed from: s */
    public boolean m474s() {
        return this.f309k != Long.MIN_VALUE;
    }

    /* renamed from: t */
    public C0239q m475t() {
        return this.f305g;
    }

    @Nullable
    /* renamed from: u */
    Throwable m476u() {
        return this.f316r;
    }

    /* renamed from: v */
    public int m477v() {
        return this.f302d;
    }
}
