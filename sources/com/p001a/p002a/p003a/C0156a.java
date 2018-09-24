package com.p001a.p002a.p003a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.p001a.p002a.p003a.p004j.C0155a;
import com.p001a.p002a.p003a.p004j.C0155a.C0150a;
import com.p001a.p002a.p003a.p004j.C0228b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.a.a.a.a */
public class C0156a extends C0155a {
    /* renamed from: a */
    public static final long f42a = TimeUnit.SECONDS.toMillis(900);
    @VisibleForTesting
    /* renamed from: b */
    final long f43b;
    @VisibleForTesting
    /* renamed from: c */
    final long f44c;
    /* renamed from: d */
    private final C0155a f45d;
    /* renamed from: e */
    private final List<C0152a> f46e;
    /* renamed from: f */
    private final C0231b f47f;

    /* renamed from: com.a.a.a.a$1 */
    class C01511 implements C0150a {
        /* renamed from: a */
        final /* synthetic */ C0156a f34a;

        C01511(C0156a c0156a) {
            this.f34a = c0156a;
        }
    }

    /* renamed from: com.a.a.a.a$a */
    private static class C0152a {
        /* renamed from: a */
        final long f35a;
        @Nullable
        /* renamed from: b */
        final Long f36b;
        /* renamed from: c */
        final C0228b f37c;

        public C0152a(long j, @Nullable Long l, C0228b c0228b) {
            this.f35a = j;
            this.f36b = l;
            this.f37c = c0228b;
        }
    }

    public C0156a(C0155a c0155a, C0231b c0231b) {
        this(c0155a, c0231b, f42a);
    }

    public C0156a(C0155a c0155a, C0231b c0231b, long j) {
        this.f46e = new ArrayList();
        this.f45d = c0155a;
        this.f47f = c0231b;
        this.f43b = j;
        this.f44c = TimeUnit.MILLISECONDS.toNanos(j);
    }

    /* renamed from: a */
    private boolean m105a(C0152a c0152a, C0228b c0228b, long j, Long l) {
        if (c0152a.f37c.m444c() != c0228b.m444c()) {
            return false;
        }
        long longValue;
        if (l != null) {
            if (c0152a.f36b == null) {
                return false;
            }
            longValue = c0152a.f36b.longValue() - l.longValue();
            if (longValue < 1 || longValue > this.f44c) {
                return false;
            }
        } else if (c0152a.f36b != null) {
            return false;
        }
        longValue = c0152a.f35a - j;
        return longValue > 0 && longValue <= this.f44c;
    }

    /* renamed from: b */
    private void m106b(C0228b c0228b) {
        synchronized (this.f46e) {
            for (int size = this.f46e.size() - 1; size >= 0; size--) {
                if (((C0152a) this.f46e.get(size)).f37c.m439a().equals(c0228b.m439a())) {
                    this.f46e.remove(size);
                }
            }
        }
    }

    /* renamed from: c */
    private boolean m107c(C0228b c0228b) {
        boolean z;
        Long l = null;
        long a = this.f47f.mo356a();
        long toNanos = TimeUnit.MILLISECONDS.toNanos(c0228b.m443b()) + a;
        Long valueOf = c0228b.m445d() == null ? null : Long.valueOf(TimeUnit.MILLISECONDS.toNanos(c0228b.m445d().longValue()) + a);
        synchronized (this.f46e) {
            Long valueOf2;
            for (C0152a a2 : this.f46e) {
                if (m105a(a2, c0228b, toNanos, valueOf)) {
                    z = false;
                    break;
                }
            }
            long b = ((c0228b.m443b() / this.f43b) + 1) * this.f43b;
            c0228b.m441a(b);
            if (c0228b.m445d() != null) {
                valueOf2 = Long.valueOf(((c0228b.m445d().longValue() / this.f43b) + 1) * this.f43b);
                c0228b.m442a(valueOf2);
            } else {
                valueOf2 = null;
            }
            List list = this.f46e;
            b = TimeUnit.MILLISECONDS.toNanos(b) + a;
            if (valueOf2 != null) {
                l = Long.valueOf(TimeUnit.MILLISECONDS.toNanos(valueOf2.longValue()) + a);
            }
            list.add(new C0152a(b, l, c0228b));
            z = true;
        }
        return z;
    }

    /* renamed from: a */
    public void mo333a() {
        synchronized (this.f46e) {
            this.f46e.clear();
        }
        this.f45d.mo333a();
    }

    /* renamed from: a */
    public void mo334a(Context context, C0150a c0150a) {
        super.mo334a(context, c0150a);
        this.f45d.mo334a(context, new C01511(this));
    }

    /* renamed from: a */
    public void mo335a(C0228b c0228b) {
        if (m107c(c0228b)) {
            this.f45d.mo335a(c0228b);
        }
    }

    /* renamed from: a */
    public void mo336a(C0228b c0228b, boolean z) {
        m106b(c0228b);
        this.f45d.mo336a(c0228b, false);
        if (z) {
            mo335a(c0228b);
        }
    }
}
