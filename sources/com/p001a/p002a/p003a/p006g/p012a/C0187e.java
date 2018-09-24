package com.p001a.p002a.p003a.p006g.p012a;

import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0200i;

/* renamed from: com.a.a.a.g.a.e */
public class C0187e extends C0182b {
    /* renamed from: d */
    private int f138d;
    /* renamed from: e */
    private Runnable f139e;

    public C0187e() {
        super(C0200i.COMMAND);
    }

    /* renamed from: a */
    protected void mo346a() {
        this.f138d = -1;
        this.f139e = null;
    }

    /* renamed from: a */
    public void m274a(int i) {
        this.f138d = i;
    }

    /* renamed from: c */
    public int m275c() {
        return this.f138d;
    }

    /* renamed from: d */
    public Runnable m276d() {
        return this.f139e;
    }

    public String toString() {
        return "Command[" + this.f138d + "]";
    }
}
