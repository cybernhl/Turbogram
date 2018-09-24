package com.p001a.p002a.p003a;

import java.util.Collections;
import java.util.HashSet;

/* renamed from: com.a.a.a.o */
public class C0238o {
    /* renamed from: a */
    int f351a = 0;
    /* renamed from: b */
    private String f352b = null;
    /* renamed from: c */
    private String f353c = null;
    /* renamed from: d */
    private boolean f354d = false;
    /* renamed from: e */
    private int f355e;
    /* renamed from: f */
    private long f356f;
    /* renamed from: g */
    private HashSet<String> f357g;
    /* renamed from: h */
    private long f358h = 0;
    /* renamed from: i */
    private Boolean f359i;

    public C0238o(int i) {
        this.f355e = i;
    }

    /* renamed from: a */
    public C0238o m533a() {
        if (this.f351a != 2) {
            this.f351a = 1;
        }
        return this;
    }

    /* renamed from: a */
    public C0238o m534a(long j) {
        this.f356f = j;
        return this;
    }

    /* renamed from: a */
    public C0238o m535a(String... strArr) {
        if (this.f357g == null) {
            this.f357g = new HashSet();
        }
        Collections.addAll(this.f357g, strArr);
        return this;
    }

    /* renamed from: b */
    public C0238o m536b() {
        this.f354d = true;
        return this;
    }

    /* renamed from: c */
    public String m537c() {
        return this.f352b;
    }

    /* renamed from: d */
    public String m538d() {
        return this.f353c;
    }

    /* renamed from: e */
    public boolean m539e() {
        return this.f354d;
    }

    /* renamed from: f */
    public int m540f() {
        return this.f355e;
    }

    /* renamed from: g */
    public long m541g() {
        return this.f356f;
    }

    /* renamed from: h */
    public long m542h() {
        return this.f358h;
    }

    /* renamed from: i */
    public HashSet<String> m543i() {
        return this.f357g;
    }

    /* renamed from: j */
    public boolean m544j() {
        return Boolean.TRUE.equals(this.f359i);
    }
}
