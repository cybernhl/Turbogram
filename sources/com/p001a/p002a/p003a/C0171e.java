package com.p001a.p002a.p003a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.a.a.a.e */
public class C0171e {
    /* renamed from: a */
    private int f90a;
    /* renamed from: b */
    private C0242s f91b;
    /* renamed from: c */
    private final Set<String> f92c = new HashSet();
    /* renamed from: d */
    private final List<String> f93d = new ArrayList();
    /* renamed from: e */
    private final List<String> f94e = new ArrayList();
    /* renamed from: f */
    private boolean f95f;
    /* renamed from: g */
    private Long f96g;
    /* renamed from: h */
    private long f97h;

    /* renamed from: a */
    public int m187a() {
        return this.f90a;
    }

    /* renamed from: a */
    void m188a(int i) {
        this.f90a = i;
    }

    /* renamed from: a */
    public void m189a(long j) {
        this.f97h = j;
    }

    /* renamed from: a */
    void m190a(C0242s c0242s) {
        this.f91b = c0242s;
    }

    /* renamed from: a */
    void m191a(Long l) {
        this.f96g = l;
    }

    /* renamed from: a */
    void m192a(Collection<String> collection) {
        this.f93d.clear();
        if (collection != null) {
            this.f93d.addAll(collection);
        }
    }

    /* renamed from: a */
    void m193a(boolean z) {
        this.f95f = z;
    }

    /* renamed from: a */
    void m194a(String[] strArr) {
        this.f92c.clear();
        if (strArr != null) {
            Collections.addAll(this.f92c, strArr);
        }
    }

    /* renamed from: b */
    public C0242s m195b() {
        return this.f91b;
    }

    /* renamed from: b */
    void m196b(Collection<String> collection) {
        this.f94e.clear();
        if (collection != null) {
            this.f94e.addAll(collection);
        }
    }

    /* renamed from: c */
    public Set<String> m197c() {
        return this.f92c;
    }

    /* renamed from: d */
    public List<String> m198d() {
        return this.f93d;
    }

    /* renamed from: e */
    public boolean m199e() {
        return this.f95f;
    }

    /* renamed from: f */
    public Long m200f() {
        return this.f96g;
    }

    /* renamed from: g */
    public List<String> m201g() {
        return this.f94e;
    }

    /* renamed from: h */
    public long m202h() {
        return this.f97h;
    }

    /* renamed from: i */
    void m203i() {
        this.f90a = 2;
        this.f91b = null;
        this.f92c.clear();
        this.f93d.clear();
        this.f94e.clear();
        this.f95f = false;
        this.f96g = null;
        this.f97h = Long.MIN_VALUE;
    }
}
