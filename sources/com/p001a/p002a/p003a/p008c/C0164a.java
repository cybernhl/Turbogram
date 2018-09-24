package com.p001a.p002a.p003a.p008c;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0201p;
import com.p001a.p002a.p003a.C0202g;
import com.p001a.p002a.p003a.p004j.C0155a;
import com.p001a.p002a.p003a.p009d.C0167a;
import com.p001a.p002a.p003a.p011f.C0177a;
import com.p001a.p002a.p003a.p011f.C0180b.C0179a;
import com.p001a.p002a.p003a.p013h.C0205b;
import com.p001a.p002a.p003a.p013h.C0209c;
import com.p001a.p002a.p003a.p016k.C0231b;
import com.p001a.p002a.p003a.p016k.C0232a;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;

/* renamed from: com.a.a.a.c.a */
public class C0164a {
    /* renamed from: a */
    String f59a;
    /* renamed from: b */
    int f60b;
    /* renamed from: c */
    int f61c;
    /* renamed from: d */
    int f62d;
    /* renamed from: e */
    int f63e;
    /* renamed from: f */
    Context f64f;
    /* renamed from: g */
    C0201p f65g;
    /* renamed from: h */
    C0167a f66h;
    /* renamed from: i */
    C0205b f67i;
    /* renamed from: j */
    C0177a f68j;
    /* renamed from: k */
    C0231b f69k;
    /* renamed from: l */
    C0155a f70l;
    /* renamed from: m */
    boolean f71m;
    /* renamed from: n */
    boolean f72n;
    /* renamed from: o */
    int f73o;
    /* renamed from: p */
    boolean f74p;
    /* renamed from: q */
    ThreadFactory f75q;

    /* renamed from: com.a.a.a.c.a$a */
    public static final class C0163a {
        /* renamed from: a */
        private Pattern f57a = Pattern.compile("^([A-Za-z]|[0-9]|_|-)+$");
        /* renamed from: b */
        private C0164a f58b = new C0164a();

        public C0163a(@NonNull Context context) {
            this.f58b.f64f = context.getApplicationContext();
        }

        @NonNull
        /* renamed from: a */
        public C0163a m144a(int i) {
            this.f58b.f62d = i;
            return this;
        }

        @NonNull
        /* renamed from: a */
        public C0164a m145a() {
            if (this.f58b.f65g == null) {
                this.f58b.f65g = new C0202g();
            }
            if (this.f58b.f67i == null) {
                this.f58b.f67i = new C0209c(this.f58b.f64f);
            }
            if (this.f58b.f69k == null) {
                this.f58b.f69k = new C0232a();
            }
            return this.f58b;
        }

        @NonNull
        /* renamed from: b */
        public C0163a m146b(int i) {
            this.f58b.f60b = i;
            return this;
        }

        @NonNull
        /* renamed from: c */
        public C0163a m147c(int i) {
            this.f58b.f61c = i;
            return this;
        }
    }

    private C0164a() {
        this.f59a = "default_job_manager";
        this.f60b = 5;
        this.f61c = 0;
        this.f62d = 15;
        this.f63e = 3;
        this.f68j = new C0179a();
        this.f71m = false;
        this.f72n = false;
        this.f73o = 5;
        this.f74p = true;
        this.f75q = null;
    }

    @NonNull
    /* renamed from: a */
    public Context m148a() {
        return this.f64f;
    }

    @NonNull
    /* renamed from: b */
    public String m149b() {
        return this.f59a;
    }

    /* renamed from: c */
    public boolean m150c() {
        return this.f74p;
    }

    @NonNull
    /* renamed from: d */
    public C0201p m151d() {
        return this.f65g;
    }

    @Nullable
    /* renamed from: e */
    public C0167a m152e() {
        return this.f66h;
    }

    /* renamed from: f */
    public int m153f() {
        return this.f62d;
    }

    @NonNull
    /* renamed from: g */
    public C0205b m154g() {
        return this.f67i;
    }

    /* renamed from: h */
    public int m155h() {
        return this.f60b;
    }

    /* renamed from: i */
    public int m156i() {
        return this.f61c;
    }

    @Nullable
    /* renamed from: j */
    public C0177a m157j() {
        return this.f68j;
    }

    /* renamed from: k */
    public int m158k() {
        return this.f63e;
    }

    /* renamed from: l */
    public boolean m159l() {
        return this.f71m;
    }

    @NonNull
    /* renamed from: m */
    public C0231b m160m() {
        return this.f69k;
    }

    /* renamed from: n */
    public boolean m161n() {
        return this.f72n;
    }

    @Nullable
    /* renamed from: o */
    public C0155a m162o() {
        return this.f70l;
    }

    /* renamed from: p */
    public int m163p() {
        return this.f73o;
    }

    @Nullable
    /* renamed from: q */
    public ThreadFactory m164q() {
        return this.f75q;
    }
}
