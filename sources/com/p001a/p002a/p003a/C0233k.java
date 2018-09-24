package com.p001a.p002a.p003a;

import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.p004j.C0155a;
import com.p001a.p002a.p003a.p004j.C0155a.C0150a;
import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0195c;
import com.p001a.p002a.p003a.p006g.C0197g;
import com.p001a.p002a.p003a.p006g.p012a.C0183a;
import com.p001a.p002a.p003a.p007b.C0160a;
import com.p001a.p002a.p003a.p008c.C0164a;
import java.util.concurrent.TimeUnit;

/* renamed from: com.a.a.a.k */
public class C0233k {
    /* renamed from: a */
    public static final long f320a = TimeUnit.MILLISECONDS.toNanos(10000);
    /* renamed from: b */
    final C0236l f321b;
    /* renamed from: c */
    private final C0197g f322c;
    /* renamed from: d */
    private final C0195c f323d = new C0195c();
    /* renamed from: e */
    private Thread f324e;
    @Nullable
    /* renamed from: f */
    private C0155a f325f;

    /* renamed from: com.a.a.a.k$1 */
    class C02301 implements C0150a {
        /* renamed from: a */
        final /* synthetic */ C0233k f317a;

        C02301(C0233k c0233k) {
            this.f317a = c0233k;
        }
    }

    public C0233k(C0164a c0164a) {
        this.f322c = new C0197g(c0164a.m160m(), this.f323d);
        this.f321b = new C0236l(c0164a, this.f322c, this.f323d);
        this.f324e = new Thread(this.f321b, "job-manager");
        if (c0164a.m162o() != null) {
            this.f325f = c0164a.m162o();
            c0164a.m162o().mo334a(c0164a.m148a(), m486a());
        }
        this.f324e.start();
    }

    /* renamed from: a */
    private C0150a m486a() {
        return new C02301(this);
    }

    /* renamed from: a */
    public void m487a(C0160a c0160a) {
        this.f321b.m528a(c0160a);
    }

    /* renamed from: a */
    public void m488a(C0225i c0225i) {
        C0182b c0182b = (C0183a) this.f323d.m305a(C0183a.class);
        c0182b.m254a(c0225i);
        this.f322c.mo347a(c0182b);
    }
}
