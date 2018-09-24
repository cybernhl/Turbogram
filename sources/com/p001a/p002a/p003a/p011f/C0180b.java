package com.p001a.p002a.p003a.p011f;

import android.util.Log;

/* renamed from: com.a.a.a.f.b */
public class C0180b {
    /* renamed from: a */
    private static C0177a f108a;

    /* renamed from: com.a.a.a.f.b$1 */
    static class C01781 implements C0177a {
        C01781() {
        }

        /* renamed from: a */
        public void mo341a(String str, Object... objArr) {
        }

        /* renamed from: a */
        public void mo342a(Throwable th, String str, Object... objArr) {
        }

        /* renamed from: a */
        public boolean mo343a() {
            return false;
        }

        /* renamed from: b */
        public void mo344b(String str, Object... objArr) {
        }

        /* renamed from: c */
        public void mo345c(String str, Object... objArr) {
        }
    }

    /* renamed from: com.a.a.a.f.b$a */
    public static class C0179a implements C0177a {
        /* renamed from: a */
        public void mo341a(String str, Object... objArr) {
        }

        /* renamed from: a */
        public void mo342a(Throwable th, String str, Object... objArr) {
            Log.e("JobManager", String.format(str, objArr), th);
        }

        /* renamed from: a */
        public boolean mo343a() {
            return false;
        }

        /* renamed from: b */
        public void mo344b(String str, Object... objArr) {
            Log.e("JobManager", String.format(str, objArr));
        }

        /* renamed from: c */
        public void mo345c(String str, Object... objArr) {
        }
    }

    static {
        C0180b.m229a();
    }

    /* renamed from: a */
    public static void m229a() {
        C0180b.m230a(new C01781());
    }

    /* renamed from: a */
    public static void m230a(C0177a c0177a) {
        f108a = c0177a;
    }

    /* renamed from: a */
    public static void m231a(String str, Object... objArr) {
        f108a.mo341a(str, objArr);
    }

    /* renamed from: a */
    public static void m232a(Throwable th, String str, Object... objArr) {
        f108a.mo342a(th, str, objArr);
    }

    /* renamed from: b */
    public static void m233b(String str, Object... objArr) {
        f108a.mo344b(str, objArr);
    }

    /* renamed from: b */
    public static boolean m234b() {
        return f108a.mo343a();
    }

    /* renamed from: c */
    public static void m235c(String str, Object... objArr) {
        f108a.mo345c(str, objArr);
    }
}
