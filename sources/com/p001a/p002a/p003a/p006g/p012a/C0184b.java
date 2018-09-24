package com.p001a.p002a.p003a.p006g.p012a;

import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0225i;
import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0200i;

/* renamed from: com.a.a.a.g.a.b */
public class C0184b extends C0182b {
    /* renamed from: d */
    private int f128d;
    /* renamed from: e */
    private int f129e;
    /* renamed from: f */
    private boolean f130f;
    /* renamed from: g */
    private C0225i f131g;
    @Nullable
    /* renamed from: h */
    private Throwable f132h;

    public C0184b() {
        super(C0200i.CALLBACK);
    }

    /* renamed from: a */
    protected void mo346a() {
        this.f131g = null;
        this.f132h = null;
    }

    /* renamed from: a */
    public void m257a(C0225i c0225i, int i) {
        this.f128d = i;
        this.f131g = c0225i;
    }

    /* renamed from: a */
    public void m258a(C0225i c0225i, int i, int i2) {
        this.f128d = i;
        this.f129e = i2;
        this.f131g = c0225i;
    }

    /* renamed from: a */
    public void m259a(C0225i c0225i, int i, boolean z, @Nullable Throwable th) {
        this.f128d = i;
        this.f130f = z;
        this.f131g = c0225i;
        this.f132h = th;
    }

    /* renamed from: c */
    public int m260c() {
        return this.f128d;
    }

    /* renamed from: d */
    public int m261d() {
        return this.f129e;
    }

    /* renamed from: e */
    public boolean m262e() {
        return this.f130f;
    }

    /* renamed from: f */
    public C0225i m263f() {
        return this.f131g;
    }

    @Nullable
    /* renamed from: g */
    public Throwable m264g() {
        return this.f132h;
    }
}
