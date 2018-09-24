package com.p001a.p002a.p003a.p004j;

import android.support.annotation.Nullable;

/* renamed from: com.a.a.a.j.b */
public class C0228b {
    /* renamed from: a */
    private String f294a;
    /* renamed from: b */
    private long f295b;
    /* renamed from: c */
    private int f296c;
    /* renamed from: d */
    private Long f297d;
    /* renamed from: e */
    private Object f298e;

    public C0228b(String str) {
        this.f294a = str;
    }

    /* renamed from: a */
    public String m439a() {
        return this.f294a;
    }

    /* renamed from: a */
    public void m440a(int i) {
        this.f296c = i;
    }

    /* renamed from: a */
    public void m441a(long j) {
        this.f295b = j;
    }

    /* renamed from: a */
    public void m442a(Long l) {
        this.f297d = l;
    }

    /* renamed from: b */
    public long m443b() {
        return this.f295b;
    }

    /* renamed from: c */
    public int m444c() {
        return this.f296c;
    }

    @Nullable
    /* renamed from: d */
    public Long m445d() {
        return this.f297d;
    }

    public String toString() {
        return "SchedulerConstraint{uuid='" + this.f294a + '\'' + ", delayInMs=" + this.f295b + ", networkStatus=" + this.f296c + ", overrideDeadlineInMs=" + this.f297d + ", data=" + this.f298e + '}';
    }
}
