package com.p001a.p002a.p003a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0168d.C0166a;
import com.p001a.p002a.p003a.p006g.C0157f;
import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0195c;
import com.p001a.p002a.p003a.p006g.C0199h;
import com.p001a.p002a.p003a.p006g.C0200i;
import com.p001a.p002a.p003a.p006g.p012a.C0184b;
import com.p001a.p002a.p003a.p006g.p012a.C0186d;
import com.p001a.p002a.p003a.p006g.p012a.C0187e;
import com.p001a.p002a.p003a.p006g.p012a.C0190h;
import com.p001a.p002a.p003a.p007b.C0160a;
import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.a.a.a.b */
public class C0161b {
    /* renamed from: a */
    final C0199h f51a;
    /* renamed from: b */
    private final CopyOnWriteArrayList<C0160a> f52b;
    /* renamed from: c */
    private final C0195c f53c;
    /* renamed from: d */
    private final AtomicInteger f54d = new AtomicInteger(0);
    /* renamed from: e */
    private final C0231b f55e;
    /* renamed from: f */
    private final AtomicBoolean f56f = new AtomicBoolean(false);

    /* renamed from: com.a.a.a.b$1 */
    class C01591 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0161b f50a;

        /* renamed from: com.a.a.a.b$1$1 */
        class C01581 extends C0157f {
            /* renamed from: a */
            long f48a = Long.MIN_VALUE;
            /* renamed from: b */
            final /* synthetic */ C01591 f49b;

            C01581(C01591 c01591) {
                this.f49b = c01591;
            }

            /* renamed from: a */
            public void mo337a() {
            }

            /* renamed from: a */
            public void mo338a(C0182b c0182b) {
                if (c0182b.f124a == C0200i.f174a) {
                    this.f49b.f50a.m127a((C0184b) c0182b);
                    this.f48a = this.f49b.f50a.f55e.mo356a();
                } else if (c0182b.f124a == C0200i.f175b) {
                    this.f49b.f50a.m128a((C0186d) c0182b);
                    this.f48a = this.f49b.f50a.f55e.mo356a();
                } else if (c0182b.f124a == C0200i.f177d) {
                    C0187e c0187e = (C0187e) c0182b;
                    int c = c0187e.m275c();
                    if (c == 1) {
                        this.f49b.f50a.f51a.m319a();
                        this.f49b.f50a.f56f.set(false);
                    } else if (c == 3) {
                        c0187e.m276d().run();
                    }
                } else if (c0182b.f124a == C0200i.f178e) {
                    ((C0190h) c0182b).m286c().m340a(0);
                }
            }

            /* renamed from: b */
            public void mo339b() {
            }
        }

        C01591(C0161b c0161b) {
            this.f50a = c0161b;
        }

        public void run() {
            this.f50a.f51a.m323a(new C01581(this));
        }
    }

    public C0161b(C0195c c0195c, C0231b c0231b) {
        this.f55e = c0231b;
        this.f51a = new C0199h(c0231b, c0195c, "jq_callback");
        this.f52b = new CopyOnWriteArrayList();
        this.f53c = c0195c;
    }

    /* renamed from: a */
    private void m124a() {
        if (!this.f56f.getAndSet(true)) {
            m130b();
        }
    }

    /* renamed from: a */
    private void m127a(@NonNull C0184b c0184b) {
        switch (c0184b.m260c()) {
            case 1:
                m135d(c0184b.m263f());
                return;
            case 2:
                m133c(c0184b.m263f(), c0184b.m261d());
                return;
            case 3:
                m131b(c0184b.m263f(), c0184b.m262e(), c0184b.m264g());
                return;
            case 4:
                m132c(c0184b.m263f());
                return;
            case 5:
                m136d(c0184b.m263f(), c0184b.m261d());
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m128a(@NonNull C0186d c0186d) {
        c0186d.m271c().m169a(c0186d.m272d());
        m124a();
    }

    /* renamed from: b */
    private void m130b() {
        try {
            new Thread(new C01591(this), "job-manager-callbacks").start();
        } catch (Throwable e) {
            C0180b.m232a(e, "Cannot start a thread. Looks like app is shutting down.See issue #294 for details.", new Object[0]);
        }
    }

    /* renamed from: b */
    private void m131b(@NonNull C0225i c0225i, boolean z, @Nullable Throwable th) {
        Iterator it = this.f52b.iterator();
        while (it.hasNext()) {
            ((C0160a) it.next()).mo4400a(c0225i, z, th);
        }
    }

    /* renamed from: c */
    private void m132c(@NonNull C0225i c0225i) {
        Iterator it = this.f52b.iterator();
        while (it.hasNext()) {
            ((C0160a) it.next()).mo4401b(c0225i);
        }
    }

    /* renamed from: c */
    private void m133c(@NonNull C0225i c0225i, int i) {
        Iterator it = this.f52b.iterator();
        while (it.hasNext()) {
            ((C0160a) it.next()).mo4399a(c0225i, i);
        }
    }

    /* renamed from: c */
    private boolean m134c() {
        return this.f54d.get() > 0;
    }

    /* renamed from: d */
    private void m135d(@NonNull C0225i c0225i) {
        Iterator it = this.f52b.iterator();
        while (it.hasNext()) {
            ((C0160a) it.next()).mo4398a(c0225i);
        }
    }

    /* renamed from: d */
    private void m136d(@NonNull C0225i c0225i, int i) {
        Iterator it = this.f52b.iterator();
        while (it.hasNext()) {
            ((C0160a) it.next()).mo4402b(c0225i, i);
        }
    }

    /* renamed from: a */
    void m137a(@NonNull C0160a c0160a) {
        this.f52b.add(c0160a);
        this.f54d.incrementAndGet();
        m124a();
    }

    /* renamed from: a */
    public void m138a(@NonNull C0168d c0168d, @NonNull C0166a c0166a) {
        C0182b c0182b = (C0186d) this.f53c.m305a(C0186d.class);
        c0182b.m270a(c0166a, c0168d);
        this.f51a.mo347a(c0182b);
        m124a();
    }

    /* renamed from: a */
    public void m139a(@NonNull C0225i c0225i) {
        if (m134c()) {
            C0182b c0182b = (C0184b) this.f53c.m305a(C0184b.class);
            c0182b.m257a(c0225i, 1);
            this.f51a.mo347a(c0182b);
        }
    }

    /* renamed from: a */
    public void m140a(@NonNull C0225i c0225i, int i) {
        if (m134c()) {
            C0182b c0182b = (C0184b) this.f53c.m305a(C0184b.class);
            c0182b.m258a(c0225i, 2, i);
            this.f51a.mo347a(c0182b);
        }
    }

    /* renamed from: a */
    public void m141a(@NonNull C0225i c0225i, boolean z, @Nullable Throwable th) {
        if (m134c()) {
            C0182b c0182b = (C0184b) this.f53c.m305a(C0184b.class);
            c0182b.m259a(c0225i, 3, z, th);
            this.f51a.mo347a(c0182b);
        }
    }

    /* renamed from: b */
    public void m142b(@NonNull C0225i c0225i) {
        if (m134c()) {
            C0182b c0182b = (C0184b) this.f53c.m305a(C0184b.class);
            c0182b.m257a(c0225i, 4);
            this.f51a.mo347a(c0182b);
        }
    }

    /* renamed from: b */
    public void m143b(@NonNull C0225i c0225i, int i) {
        if (m134c()) {
            C0182b c0182b = (C0184b) this.f53c.m305a(C0184b.class);
            c0182b.m258a(c0225i, 5, i);
            this.f51a.mo347a(c0182b);
        }
    }
}
