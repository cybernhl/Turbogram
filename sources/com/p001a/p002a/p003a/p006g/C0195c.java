package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p011f.C0180b;
import java.util.Arrays;

/* renamed from: com.a.a.a.g.c */
public class C0195c {
    /* renamed from: a */
    C0182b[] f154a = new C0182b[C0200i.values().length];
    /* renamed from: b */
    int[] f155b = new int[this.f154a.length];

    public C0195c() {
        Arrays.fill(this.f155b, 0);
    }

    /* renamed from: a */
    public <T extends C0182b> T m305a(Class<T> cls) {
        C0200i c0200i = (C0200i) C0200i.f185m.get(cls);
        synchronized (c0200i) {
            T t = this.f154a[c0200i.ordinal()];
            if (t != null) {
                this.f154a[c0200i.ordinal()] = t.f125b;
                int[] iArr = this.f155b;
                int ordinal = c0200i.ordinal();
                iArr[ordinal] = iArr[ordinal] - 1;
                t.f125b = null;
                return t;
            }
            try {
                C0182b c0182b = (C0182b) cls.newInstance();
                return c0182b;
            } catch (Throwable e) {
                C0180b.m232a(e, "Cannot create an instance of " + cls + ". Make sure it has a empty constructor.", new Object[0]);
                return null;
            } catch (Throwable e2) {
                C0180b.m232a(e2, "Cannot create an instance of " + cls + ". Make sure it has a public empty constructor.", new Object[0]);
                return null;
            }
        }
    }

    /* renamed from: a */
    public void m306a(C0182b c0182b) {
        C0200i c0200i = c0182b.f124a;
        c0182b.m252b();
        synchronized (c0200i) {
            if (this.f155b[c0200i.ordinal()] < 20) {
                c0182b.f125b = this.f154a[c0200i.ordinal()];
                this.f154a[c0200i.ordinal()] = c0182b;
                int[] iArr = this.f155b;
                int ordinal = c0200i.ordinal();
                iArr[ordinal] = iArr[ordinal] + 1;
            }
        }
    }
}
