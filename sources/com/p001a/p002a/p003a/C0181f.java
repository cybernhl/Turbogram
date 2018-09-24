package com.p001a.p002a.p003a;

import android.support.annotation.NonNull;
import com.p001a.p002a.p003a.p004j.C0228b;
import com.p001a.p002a.p003a.p006g.C0157f;
import com.p001a.p002a.p003a.p006g.C0173d;
import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0195c;
import com.p001a.p002a.p003a.p006g.C0196e;
import com.p001a.p002a.p003a.p006g.C0199h;
import com.p001a.p002a.p003a.p006g.C0200i;
import com.p001a.p002a.p003a.p006g.p012a.C0187e;
import com.p001a.p002a.p003a.p006g.p012a.C0189g;
import com.p001a.p002a.p003a.p006g.p012a.C0191i;
import com.p001a.p002a.p003a.p006g.p012a.C0192j;
import com.p001a.p002a.p003a.p008c.C0164a;
import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;

/* renamed from: com.a.a.a.f */
class C0181f {
    /* renamed from: a */
    final C0241r f109a;
    /* renamed from: b */
    private List<C0176a> f110b = new ArrayList();
    /* renamed from: c */
    private final List<C0176a> f111c = new ArrayList();
    /* renamed from: d */
    private final int f112d;
    /* renamed from: e */
    private final int f113e;
    /* renamed from: f */
    private final long f114f;
    /* renamed from: g */
    private final int f115g;
    /* renamed from: h */
    private final int f116h;
    /* renamed from: i */
    private final ThreadGroup f117i;
    /* renamed from: j */
    private final C0236l f118j;
    /* renamed from: k */
    private final C0231b f119k;
    /* renamed from: l */
    private final C0195c f120l;
    /* renamed from: m */
    private final Map<String, C0229j> f121m;
    /* renamed from: n */
    private final ThreadFactory f122n;
    /* renamed from: o */
    private final CopyOnWriteArrayList<Runnable> f123o = new CopyOnWriteArrayList();

    /* renamed from: com.a.a.a.f$a */
    static class C0176a implements Runnable {
        /* renamed from: g */
        static final C0173d f100g = new C01741();
        /* renamed from: a */
        final C0199h f101a;
        /* renamed from: b */
        final C0196e f102b;
        /* renamed from: c */
        final C0195c f103c;
        /* renamed from: d */
        final C0231b f104d;
        /* renamed from: e */
        boolean f105e;
        /* renamed from: f */
        volatile long f106f;
        /* renamed from: h */
        final C0157f f107h = new C01752(this);

        /* renamed from: com.a.a.a.f$a$1 */
        static class C01741 implements C0173d {
            C01741() {
            }

            /* renamed from: a */
            public boolean mo340a(C0182b c0182b) {
                return c0182b.f124a == C0200i.COMMAND && ((C0187e) c0182b).m275c() == 2;
            }
        }

        /* renamed from: com.a.a.a.f$a$2 */
        class C01752 extends C0157f {
            /* renamed from: a */
            final /* synthetic */ C0176a f99a;

            C01752(C0176a c0176a) {
                this.f99a = c0176a;
            }

            /* renamed from: a */
            public void mo338a(C0182b c0182b) {
                switch (c0182b.f124a) {
                    case RUN_JOB:
                        this.f99a.m213a((C0191i) c0182b);
                        this.f99a.m208a();
                        return;
                    case COMMAND:
                        this.f99a.m212a((C0187e) c0182b);
                        return;
                    default:
                        return;
                }
            }

            /* renamed from: b */
            public void mo339b() {
                C0180b.m231a("consumer manager on idle", new Object[0]);
                C0189g c0189g = (C0189g) this.f99a.f103c.m305a(C0189g.class);
                c0189g.m282a(this.f99a);
                c0189g.m281a(this.f99a.f106f);
                this.f99a.f102b.mo347a(c0189g);
            }
        }

        public C0176a(C0196e c0196e, C0199h c0199h, C0195c c0195c, C0231b c0231b) {
            this.f101a = c0199h;
            this.f103c = c0195c;
            this.f102b = c0196e;
            this.f104d = c0231b;
            this.f106f = c0231b.mo356a();
        }

        /* renamed from: a */
        private void m208a() {
            this.f101a.m322a(f100g);
        }

        /* renamed from: a */
        private void m212a(C0187e c0187e) {
            switch (c0187e.m275c()) {
                case 1:
                    this.f101a.m319a();
                    return;
                case 2:
                    C0180b.m231a("Consumer has been poked.", new Object[0]);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private void m213a(C0191i c0191i) {
            C0180b.m231a("running job %s", c0191i.m291c().getClass().getSimpleName());
            C0229j c = c0191i.m291c();
            int a = c.m446a(c.m459d(), this.f104d);
            C0192j c0192j = (C0192j) this.f103c.m305a(C0192j.class);
            c0192j.m294a(c);
            c0192j.m293a(a);
            c0192j.m295a((Object) this);
            this.f106f = this.f104d.mo356a();
            this.f102b.mo347a(c0192j);
        }

        public void run() {
            this.f101a.m323a(this.f107h);
        }
    }

    C0181f(C0236l c0236l, C0231b c0231b, C0195c c0195c, C0164a c0164a) {
        this.f118j = c0236l;
        this.f119k = c0231b;
        this.f120l = c0195c;
        this.f116h = c0164a.m158k();
        this.f113e = c0164a.m156i();
        this.f112d = c0164a.m155h();
        this.f114f = ((long) (c0164a.m153f() * 1000)) * 1000000;
        this.f115g = c0164a.m163p();
        this.f122n = c0164a.m164q();
        this.f121m = new HashMap();
        this.f109a = new C0241r(c0231b);
        this.f117i = new ThreadGroup("JobConsumers");
    }

    /* renamed from: a */
    private Set<String> m236a(C0242s c0242s, String[] strArr, boolean z) {
        Set<String> hashSet = new HashSet();
        for (C0229j c0229j : this.f121m.values()) {
            C0180b.m231a("checking job tag %s. tags of job: %s", c0229j.m465j(), c0229j.m465j().m415d());
            if (c0229j.m472q() && !c0229j.m469n() && c0242s.m559a(strArr, c0229j.m467l())) {
                hashSet.add(c0229j.m447a());
                if (z) {
                    c0229j.m470o();
                } else {
                    c0229j.m468m();
                }
            }
        }
        return hashSet;
    }

    /* renamed from: a */
    private boolean m237a(boolean z) {
        C0180b.m231a("considering adding a new consumer. Should poke all waiting? %s isRunning? %s waiting workers? %d", Boolean.valueOf(z), Boolean.valueOf(this.f118j.m531c()), Integer.valueOf(this.f110b.size()));
        if (!this.f118j.m531c()) {
            C0180b.m231a("jobqueue is not running, no consumers will be added", new Object[0]);
            return false;
        } else if (this.f110b.size() > 0) {
            C0180b.m231a("there are waiting workers, will poke them instead", new Object[0]);
            for (int size = this.f110b.size() - 1; size >= 0; size--) {
                C0176a c0176a = (C0176a) this.f110b.remove(size);
                C0182b c0182b = (C0187e) this.f120l.m305a(C0187e.class);
                c0182b.m274a(2);
                c0176a.f101a.mo347a(c0182b);
                if (!z) {
                    break;
                }
            }
            C0180b.m231a("there were waiting workers, poked them and I'm done", new Object[0]);
            return true;
        } else {
            C0180b.m231a("nothing has been poked. are we above load factor? %s", Boolean.valueOf(m239g()));
            if (!m239g()) {
                return false;
            }
            m238f();
            return true;
        }
    }

    /* renamed from: f */
    private void m238f() {
        Thread newThread;
        C0180b.m231a("adding another consumer", new Object[0]);
        Runnable c0176a = new C0176a(this.f118j.f334g, new C0199h(this.f119k, this.f120l, "consumer"), this.f120l, this.f119k);
        if (this.f122n != null) {
            newThread = this.f122n.newThread(c0176a);
        } else {
            newThread = new Thread(this.f117i, c0176a, "job-queue-worker-" + UUID.randomUUID());
            newThread.setPriority(this.f115g);
        }
        this.f111c.add(c0176a);
        try {
            newThread.start();
        } catch (Throwable e) {
            C0180b.m232a(e, "Cannot start a thread. Looks like app is shutting down.See issue #294 for details.", new Object[0]);
        }
    }

    /* renamed from: g */
    private boolean m239g() {
        int size = this.f111c.size();
        if (size >= this.f112d) {
            C0180b.m231a("too many consumers, clearly above load factor %s", Integer.valueOf(size));
            return false;
        }
        int d = this.f118j.m532d();
        int size2 = this.f121m.size();
        boolean z = this.f116h * size < d + size2 || (size < this.f113e && size < d + size2);
        C0180b.m231a("check above load factor: totalCons:%s minCons:%s maxConsCount: %s, loadFactor %s remainingJobs: %s running holders: %s. isAbove:%s", Integer.valueOf(size), Integer.valueOf(this.f113e), Integer.valueOf(this.f112d), Integer.valueOf(this.f116h), Integer.valueOf(d), Integer.valueOf(size2), Boolean.valueOf(z));
        return z;
    }

    /* renamed from: a */
    Set<String> m240a(C0242s c0242s, String[] strArr) {
        return m236a(c0242s, strArr, false);
    }

    /* renamed from: a */
    void m241a() {
        m237a(false);
    }

    /* renamed from: a */
    void m242a(C0192j c0192j, C0229j c0229j, C0239q c0239q) {
        C0176a c0176a = (C0176a) c0192j.m298e();
        if (c0176a.f105e) {
            c0176a.f105e = false;
            this.f121m.remove(c0229j.m465j().m408a());
            if (c0229j.m466k() != null) {
                this.f109a.m557b(c0229j.m466k());
                if (c0239q != null && c0239q.m550d() && c0239q.m548b().longValue() > 0) {
                    this.f109a.m555a(c0229j.m466k(), this.f119k.mo356a() + (c0239q.m548b().longValue() * 1000000));
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("this worker should not have a job");
    }

    /* renamed from: a */
    boolean m243a(@NonNull C0189g c0189g) {
        C0176a c0176a = (C0176a) c0189g.m284d();
        if (c0176a.f105e) {
            return true;
        }
        boolean c = this.f118j.m531c();
        C0229j a = c ? this.f118j.m524a(this.f109a.m553a()) : null;
        if (a != null) {
            c0176a.f105e = true;
            this.f109a.m554a(a.m466k());
            C0182b c0182b = (C0191i) this.f120l.m305a(C0191i.class);
            c0182b.m290a(a);
            this.f121m.put(a.m465j().m408a(), a);
            if (a.m466k() != null) {
                this.f109a.m554a(a.m466k());
            }
            c0176a.f101a.mo347a(c0182b);
            return true;
        }
        long c2 = c0189g.m283c() + this.f114f;
        C0180b.m235c("keep alive: %s", Long.valueOf(c2));
        boolean z = this.f111c.size() > this.f113e;
        boolean z2 = !c || (z && c2 < this.f119k.mo356a());
        C0180b.m235c("Consumer idle, will kill? %s. isRunning: %s. too many? %s timeout: %s now: %s", Boolean.valueOf(z2), Boolean.valueOf(c), Boolean.valueOf(z), Long.valueOf(c2), Long.valueOf(this.f119k.mo356a()));
        if (z2) {
            c0182b = (C0187e) this.f120l.m305a(C0187e.class);
            c0182b.m274a(1);
            c0176a.f101a.mo347a(c0182b);
            this.f110b.remove(c0176a);
            this.f111c.remove(c0176a);
            C0180b.m231a("killed consumers. remaining consumers %d", Integer.valueOf(this.f111c.size()));
            if (this.f111c.isEmpty() && this.f123o != null) {
                Iterator it = this.f123o.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
        } else {
            if (!this.f110b.contains(c0176a)) {
                this.f110b.add(c0176a);
            }
            if (z || !this.f118j.m529a()) {
                C0187e c0187e = (C0187e) this.f120l.m305a(C0187e.class);
                c0187e.m274a(2);
                if (!z) {
                    c2 = this.f119k.mo356a() + this.f114f;
                }
                c0176a.f101a.m321a(c0187e, c2);
                C0180b.m231a("poke consumer manager at %s", Long.valueOf(c2));
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m244a(C0228b c0228b) {
        for (C0229j c0229j : this.f121m.values()) {
            if (c0229j.m465j().m416e() && c0228b.m444c() >= c0229j.f302d) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    boolean m245a(String str) {
        return this.f121m.get(str) != null;
    }

    /* renamed from: b */
    Set<String> m246b(C0242s c0242s, String[] strArr) {
        return m236a(c0242s, strArr, true);
    }

    /* renamed from: b */
    boolean m247b() {
        return m237a(true);
    }

    /* renamed from: c */
    void m248c() {
        Iterator it;
        for (C0176a c0176a : this.f111c) {
            C0199h c0199h = c0176a.f101a;
            C0182b c0182b = (C0187e) this.f120l.m305a(C0187e.class);
            c0182b.m274a(2);
            c0199h.mo347a(c0182b);
        }
        if (this.f111c.isEmpty()) {
            it = this.f123o.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    /* renamed from: d */
    public int m249d() {
        return this.f111c.size();
    }

    /* renamed from: e */
    public boolean m250e() {
        return this.f110b.size() == this.f111c.size();
    }
}
