package com.p001a.p002a.p003a;

/* renamed from: com.a.a.a.q */
public class C0239q {
    /* renamed from: a */
    public static final C0239q f360a = new C0240a(true);
    /* renamed from: b */
    public static final C0239q f361b = new C0240a(false);
    /* renamed from: c */
    private boolean f362c;
    /* renamed from: d */
    private Long f363d;
    /* renamed from: e */
    private Integer f364e;
    /* renamed from: f */
    private boolean f365f = false;

    /* renamed from: com.a.a.a.q$a */
    static class C0240a extends C0239q {
        public C0240a(boolean z) {
            super(z);
        }

        /* renamed from: a */
        public void mo361a(Long l) {
            throw new IllegalStateException("This object is immutable. Create a new one using the constructor.");
        }
    }

    public C0239q(boolean z) {
        this.f362c = z;
    }

    /* renamed from: a */
    public static C0239q m545a(int i, long j) {
        C0239q c0239q = new C0239q(true);
        c0239q.mo361a(Long.valueOf(((long) Math.pow(2.0d, (double) Math.max(0, i - 1))) * j));
        return c0239q;
    }

    /* renamed from: a */
    public void mo361a(Long l) {
        this.f363d = l;
    }

    /* renamed from: a */
    public boolean m547a() {
        return this.f362c;
    }

    /* renamed from: b */
    public Long m548b() {
        return this.f363d;
    }

    /* renamed from: c */
    public Integer m549c() {
        return this.f364e;
    }

    /* renamed from: d */
    public boolean m550d() {
        return this.f365f;
    }
}
