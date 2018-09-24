package com.p001a.p002a.p003a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0229j.C0227a;
import com.p001a.p002a.p003a.p004j.C0155a;
import com.p001a.p002a.p003a.p004j.C0228b;
import com.p001a.p002a.p003a.p006g.C0157f;
import com.p001a.p002a.p003a.p006g.C0182b;
import com.p001a.p002a.p003a.p006g.C0195c;
import com.p001a.p002a.p003a.p006g.C0197g;
import com.p001a.p002a.p003a.p006g.p012a.C0183a;
import com.p001a.p002a.p003a.p006g.p012a.C0185c;
import com.p001a.p002a.p003a.p006g.p012a.C0187e;
import com.p001a.p002a.p003a.p006g.p012a.C0188f;
import com.p001a.p002a.p003a.p006g.p012a.C0189g;
import com.p001a.p002a.p003a.p006g.p012a.C0190h;
import com.p001a.p002a.p003a.p006g.p012a.C0192j;
import com.p001a.p002a.p003a.p006g.p012a.C0193k;
import com.p001a.p002a.p003a.p007b.C0160a;
import com.p001a.p002a.p003a.p008c.C0164a;
import com.p001a.p002a.p003a.p009d.C0167a;
import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p013h.C0204a;
import com.p001a.p002a.p003a.p013h.C0204a.C0203a;
import com.p001a.p002a.p003a.p013h.C0205b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* renamed from: com.a.a.a.l */
class C0236l implements C0203a, Runnable {
    /* renamed from: a */
    final C0231b f328a;
    /* renamed from: b */
    final C0153m f329b;
    /* renamed from: c */
    final C0153m f330c;
    /* renamed from: d */
    final C0181f f331d;
    /* renamed from: e */
    final C0171e f332e = new C0171e();
    /* renamed from: f */
    final C0161b f333f;
    /* renamed from: g */
    final C0197g f334g;
    @Nullable
    /* renamed from: h */
    C0155a f335h;
    /* renamed from: i */
    private final Context f336i;
    /* renamed from: j */
    private final long f337j;
    /* renamed from: k */
    private final C0205b f338k;
    /* renamed from: l */
    private final C0167a f339l;
    /* renamed from: m */
    private final C0195c f340m;
    @Nullable
    /* renamed from: n */
    private List<C0165c> f341n;
    @Nullable
    /* renamed from: o */
    private List<C0228b> f342o;
    /* renamed from: p */
    private boolean f343p = true;
    /* renamed from: q */
    private boolean f344q = false;
    /* renamed from: r */
    private boolean f345r = true;

    /* renamed from: com.a.a.a.l$1 */
    class C02341 extends C0157f {
        /* renamed from: a */
        final /* synthetic */ C0236l f326a;

        C02341(C0236l c0236l) {
            this.f326a = c0236l;
        }

        /* renamed from: a */
        public void mo338a(C0182b c0182b) {
            boolean z = true;
            this.f326a.f345r = true;
            switch (c0182b.f124a) {
                case ADD_JOB:
                    this.f326a.m492a((C0183a) c0182b);
                    return;
                case JOB_CONSUMER_IDLE:
                    if (!this.f326a.f331d.m243a((C0189g) c0182b)) {
                        this.f326a.m520e();
                        return;
                    }
                    return;
                case RUN_JOB_RESULT:
                    this.f326a.m496a((C0192j) c0182b);
                    return;
                case CONSTRAINT_CHANGE:
                    boolean b = this.f326a.f331d.m247b();
                    C0188f c0188f = (C0188f) c0182b;
                    C0236l c0236l = this.f326a;
                    if (!b && c0188f.m279c()) {
                        z = false;
                    }
                    c0236l.f345r = z;
                    return;
                case CANCEL:
                    this.f326a.m493a((C0185c) c0182b);
                    return;
                case PUBLIC_QUERY:
                    this.f326a.m495a((C0190h) c0182b);
                    return;
                case COMMAND:
                    this.f326a.m494a((C0187e) c0182b);
                    return;
                case SCHEDULER:
                    this.f326a.m497a((C0193k) c0182b);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: b */
        public void mo339b() {
            C0180b.m235c("joq idle. running:? %s", Boolean.valueOf(this.f326a.f343p));
            if (!this.f326a.f343p) {
                return;
            }
            if (this.f326a.f345r) {
                Long a = this.f326a.m526a(true);
                C0180b.m231a("Job queue idle. next job at: %s", a);
                if (a != null) {
                    C0188f c0188f = (C0188f) this.f326a.f340m.m305a(C0188f.class);
                    c0188f.m278a(true);
                    this.f326a.f334g.m310a(c0188f, a.longValue());
                    return;
                } else if (this.f326a.f335h != null && this.f326a.f344q && this.f326a.f329b.mo321a() == 0) {
                    this.f326a.f344q = false;
                    this.f326a.f335h.mo333a();
                    return;
                } else {
                    return;
                }
            }
            C0180b.m235c("skipping scheduling a new idle callback because looks like last one did not do anything", new Object[0]);
        }
    }

    C0236l(C0164a c0164a, C0197g c0197g, C0195c c0195c) {
        this.f334g = c0197g;
        if (c0164a.m157j() != null) {
            C0180b.m230a(c0164a.m157j());
        }
        this.f340m = c0195c;
        this.f328a = c0164a.m160m();
        this.f336i = c0164a.m148a();
        this.f337j = this.f328a.mo356a();
        this.f335h = c0164a.m162o();
        if (!(this.f335h == null || !c0164a.m150c() || (this.f335h instanceof C0156a))) {
            this.f335h = new C0156a(this.f335h, this.f328a);
        }
        this.f329b = c0164a.m151d().mo349a(c0164a, this.f337j);
        this.f330c = c0164a.m151d().mo350b(c0164a, this.f337j);
        this.f338k = c0164a.m154g();
        this.f339l = c0164a.m152e();
        if (this.f338k instanceof C0204a) {
            ((C0204a) this.f338k).mo352a(this);
        }
        this.f331d = new C0181f(this, this.f328a, c0195c, c0164a);
        this.f333f = new C0161b(c0195c, this.f328a);
    }

    /* renamed from: a */
    private C0229j m491a(String str) {
        if (str != null) {
            this.f332e.m203i();
            this.f332e.m194a(new String[]{str});
            this.f332e.m190a(C0242s.ANY);
            this.f332e.m188a(2);
            Set<C0229j> d = this.f330c.mo331d(this.f332e);
            d.addAll(this.f329b.mo331d(this.f332e));
            if (!d.isEmpty()) {
                for (C0229j c0229j : d) {
                    if (!this.f331d.m245a(c0229j.m447a())) {
                        return c0229j;
                    }
                }
                return (C0229j) d.iterator().next();
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m492a(C0183a c0183a) {
        C0225i c = c0183a.m255c();
        long a = this.f328a.mo356a();
        C0229j a2 = new C0227a().m425a(c.m413b()).m428a(c).m429a(c.m419h()).m426a(a).m434b(c.m414c() > 0 ? (c.m414c() * 1000000) + a : Long.MIN_VALUE).m435b(c.m408a()).m430a(c.m415d()).m431a(c.m416e()).m433b(0).m427a(c.m423l() > 0 ? (c.m423l() * 1000000) + a : Long.MAX_VALUE, c.m424m()).m436c(c.f265a).m438d(Long.MIN_VALUE).m432a();
        C0229j a3 = m491a(c.m420i());
        Object obj = (a3 == null || this.f331d.m245a(a3.m447a())) ? 1 : null;
        if (obj != null) {
            C0153m c0153m = c.m416e() ? this.f329b : this.f330c;
            if (a3 != null) {
                this.f331d.m246b(C0242s.ANY, new String[]{c.m420i()});
                c0153m.mo324a(a2, a3);
            } else {
                c0153m.mo325a(a2);
            }
            if (C0180b.m234b()) {
                C0180b.m231a("added job class: %s priority: %d delay: %d group : %s persistent: %s", c.getClass().getSimpleName(), Integer.valueOf(c.m413b()), Long.valueOf(c.m414c()), c.m419h(), Boolean.valueOf(c.m416e()));
            }
        } else {
            C0180b.m231a("another job with same singleId: %s was already queued", c.m420i());
        }
        if (this.f339l != null) {
            this.f339l.m170a(c);
        }
        a2.m450a(this.f336i);
        a2.m465j().mo4388f();
        this.f333f.m139a(a2.m465j());
        if (obj != null) {
            this.f331d.m241a();
            if (c.m416e()) {
                m500a(a2, a);
                return;
            }
            return;
        }
        m499a(a2, 1);
        this.f333f.m142b(a2.m465j());
    }

    /* renamed from: a */
    private void m493a(C0185c c0185c) {
        C0165c c0165c = new C0165c(c0185c.m266c(), c0185c.m267d(), c0185c.m268e());
        c0165c.m167a(this, this.f331d);
        if (c0165c.m168a()) {
            c0165c.m166a(this);
            return;
        }
        if (this.f341n == null) {
            this.f341n = new ArrayList();
        }
        this.f341n.add(c0165c);
    }

    /* renamed from: a */
    private void m494a(C0187e c0187e) {
        if (c0187e.m275c() == 1) {
            this.f334g.m313b();
            this.f334g.m308a();
        }
    }

    /* renamed from: a */
    private void m495a(C0190h c0190h) {
        switch (c0190h.m287d()) {
            case 0:
                c0190h.m286c().m340a(m530b());
                return;
            case 1:
                c0190h.m286c().m340a(m510b(m523g()));
                return;
            case 2:
                C0180b.m231a("handling start request...", new Object[0]);
                if (!this.f343p) {
                    this.f343p = true;
                    this.f331d.m247b();
                    return;
                }
                return;
            case 3:
                C0180b.m231a("handling stop request...", new Object[0]);
                this.f343p = false;
                this.f331d.m248c();
                return;
            case 4:
                c0190h.m286c().m340a(m511b(c0190h.m288e()).ordinal());
                return;
            case 5:
                m522f();
                if (c0190h.m286c() != null) {
                    c0190h.m286c().m340a(0);
                    return;
                }
                return;
            case 6:
                c0190h.m286c().m340a(this.f331d.m249d());
                return;
            case 101:
                c0190h.m286c().m340a(0);
                return;
            default:
                throw new IllegalArgumentException("cannot handle public query with type " + c0190h.m287d());
        }
    }

    /* renamed from: a */
    private void m496a(C0192j c0192j) {
        int i = 0;
        int d = c0192j.m297d();
        C0229j c = c0192j.m296c();
        this.f333f.m140a(c.m465j(), d);
        C0239q c0239q = null;
        switch (d) {
            case 1:
                m517c(c);
                break;
            case 2:
                m499a(c, 2);
                m517c(c);
                break;
            case 3:
                C0180b.m231a("running job failed and cancelled, doing nothing. Will be removed after it's onCancel is called by the CancelHandler", new Object[0]);
                break;
            case 4:
                c0239q = c.m475t();
                m498a(c);
                break;
            case 5:
                m499a(c, 5);
                m517c(c);
                break;
            case 6:
                m499a(c, 6);
                m517c(c);
                break;
            case 7:
                m499a(c, 7);
                m517c(c);
                break;
            default:
                throw new IllegalArgumentException("unknown job holder result");
        }
        this.f331d.m242a(c0192j, c, c0239q);
        this.f333f.m143b(c.m465j(), d);
        if (this.f341n != null) {
            int size = this.f341n.size();
            while (i < size) {
                int i2;
                C0165c c0165c = (C0165c) this.f341n.get(i);
                c0165c.m165a(c, d);
                if (c0165c.m168a()) {
                    c0165c.m166a(this);
                    this.f341n.remove(i);
                    i2 = i - 1;
                    size--;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
            }
        }
    }

    /* renamed from: a */
    private void m497a(C0193k c0193k) {
        int c = c0193k.m300c();
        if (c == 1) {
            m516c(c0193k.m301d());
        } else if (c == 2) {
            m512b(c0193k.m301d());
        } else {
            throw new IllegalArgumentException("Unknown scheduler message with what " + c);
        }
    }

    /* renamed from: a */
    private void m498a(C0229j c0229j) {
        C0239q t = c0229j.m475t();
        if (t == null) {
            m513b(c0229j);
            return;
        }
        if (t.m549c() != null) {
            c0229j.m448a(t.m549c().intValue());
        }
        long j = -1;
        if (t.m548b() != null) {
            j = t.m548b().longValue();
        }
        if (j > 0) {
            j = (j * 1000000) + this.f328a.mo356a();
        } else {
            j = Long.MIN_VALUE;
        }
        c0229j.m455b(j);
        m513b(c0229j);
    }

    /* renamed from: a */
    private void m499a(C0229j c0229j, int i) {
        try {
            c0229j.m457c(i);
        } catch (Throwable th) {
            C0180b.m232a(th, "job's onCancel did throw an exception, ignoring...", new Object[0]);
        }
        this.f333f.m141a(c0229j.m465j(), false, c0229j.m476u());
    }

    /* renamed from: a */
    private void m500a(C0229j c0229j, long j) {
        if (this.f335h != null) {
            int i = c0229j.f302d;
            long i2 = c0229j.m464i();
            long g = c0229j.m462g();
            long toMillis = i2 > j ? TimeUnit.NANOSECONDS.toMillis(i2 - j) : 0;
            Long valueOf = g != Long.MAX_VALUE ? Long.valueOf(TimeUnit.NANOSECONDS.toMillis(g - j)) : null;
            Object obj = (i2 <= j || toMillis < 30000) ? null : 1;
            Object obj2 = (valueOf == null || valueOf.longValue() < 30000) ? null : 1;
            if (i != 0 || obj != null || obj2 != null) {
                C0228b c0228b = new C0228b(UUID.randomUUID().toString());
                c0228b.m440a(i);
                c0228b.m441a(toMillis);
                c0228b.m442a(valueOf);
                this.f335h.mo335a(c0228b);
                this.f344q = true;
            }
        }
    }

    /* renamed from: a */
    private boolean m508a(C0228b c0228b) {
        if (this.f331d.m244a(c0228b)) {
            return true;
        }
        this.f332e.m203i();
        this.f332e.m189a(this.f328a.mo356a());
        this.f332e.m188a(c0228b.m444c());
        return this.f329b.mo322a(this.f332e) > 0;
    }

    /* renamed from: b */
    private int m510b(int i) {
        Collection a = this.f331d.f109a.m553a();
        this.f332e.m203i();
        this.f332e.m189a(this.f328a.mo356a());
        this.f332e.m188a(i);
        this.f332e.m192a(a);
        this.f332e.m193a(true);
        this.f332e.m191a(Long.valueOf(this.f328a.mo356a()));
        return (0 + this.f330c.mo322a(this.f332e)) + this.f329b.mo322a(this.f332e);
    }

    /* renamed from: b */
    private C0237n m511b(String str) {
        if (this.f331d.m245a(str)) {
            return C0237n.RUNNING;
        }
        C0229j a = this.f330c.mo323a(str);
        if (a == null) {
            a = this.f329b.mo323a(str);
        }
        if (a == null) {
            return C0237n.UNKNOWN;
        }
        return m523g() < a.f302d ? C0237n.WAITING_NOT_READY : a.m464i() > this.f328a.mo356a() ? C0237n.WAITING_NOT_READY : C0237n.WAITING_READY;
    }

    /* renamed from: b */
    private void m512b(C0228b c0228b) {
        List list = this.f342o;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (((C0228b) list.get(size)).m439a().equals(c0228b.m439a())) {
                    list.remove(size);
                }
            }
        }
        if (this.f335h != null && m508a(c0228b)) {
            this.f335h.mo335a(c0228b);
        }
    }

    /* renamed from: b */
    private void m513b(C0229j c0229j) {
        if (c0229j.m469n()) {
            C0180b.m231a("not re-adding cancelled job " + c0229j, new Object[0]);
        } else if (c0229j.m465j().m416e()) {
            this.f329b.mo328b(c0229j);
        } else {
            this.f330c.mo328b(c0229j);
        }
    }

    /* renamed from: c */
    private void m516c(C0228b c0228b) {
        if (m531c()) {
            if (m508a(c0228b)) {
                if (this.f342o == null) {
                    this.f342o = new ArrayList();
                }
                this.f342o.add(c0228b);
                this.f331d.m247b();
            } else if (this.f335h != null) {
                this.f335h.mo336a(c0228b, false);
            }
        } else if (this.f335h != null) {
            this.f335h.mo336a(c0228b, true);
        }
    }

    /* renamed from: c */
    private void m517c(C0229j c0229j) {
        if (c0229j.m465j().m416e()) {
            this.f329b.mo330c(c0229j);
        } else {
            this.f330c.mo330c(c0229j);
        }
        this.f333f.m142b(c0229j.m465j());
    }

    /* renamed from: e */
    private void m520e() {
        if (this.f335h != null && this.f342o != null && !this.f342o.isEmpty() && this.f331d.m250e()) {
            for (int size = this.f342o.size() - 1; size >= 0; size--) {
                C0228b c0228b = (C0228b) this.f342o.remove(size);
                this.f335h.mo336a(c0228b, m508a(c0228b));
            }
        }
    }

    /* renamed from: f */
    private void m522f() {
        this.f330c.mo327b();
        this.f329b.mo327b();
    }

    /* renamed from: g */
    private int m523g() {
        return this.f338k == null ? 2 : this.f338k.mo351a(this.f336i);
    }

    /* renamed from: a */
    C0229j m524a(Collection<String> collection) {
        return m525a((Collection) collection, false);
    }

    /* renamed from: a */
    C0229j m525a(Collection<String> collection, boolean z) {
        if (!this.f343p && !z) {
            return null;
        }
        C0229j c0229j = null;
        while (c0229j == null) {
            boolean z2;
            int g = m523g();
            C0180b.m235c("looking for next job", new Object[0]);
            this.f332e.m203i();
            long a = this.f328a.mo356a();
            this.f332e.m189a(a);
            this.f332e.m188a(g);
            this.f332e.m192a((Collection) collection);
            this.f332e.m193a(true);
            this.f332e.m191a(Long.valueOf(a));
            C0229j b = this.f330c.mo326b(this.f332e);
            C0180b.m235c("non persistent result %s", b);
            if (b == null) {
                b = this.f329b.mo326b(this.f332e);
                C0180b.m235c("persistent result %s", b);
                z2 = true;
            } else {
                z2 = false;
            }
            if (b == null) {
                return null;
            }
            if (z2 && this.f339l != null) {
                this.f339l.m170a(b.m465j());
            }
            b.m450a(this.f336i);
            b.m452a(b.m462g() <= a);
            if (b.m462g() > a || !b.m463h()) {
                c0229j = b;
            } else {
                m499a(b, 7);
                m517c(b);
                c0229j = null;
            }
        }
        return c0229j;
    }

    /* renamed from: a */
    Long m526a(boolean z) {
        Long b = this.f331d.f109a.m556b();
        int g = m523g();
        Collection a = this.f331d.f109a.m553a();
        this.f332e.m203i();
        this.f332e.m189a(this.f328a.mo356a());
        this.f332e.m188a(g);
        this.f332e.m192a(a);
        this.f332e.m193a(true);
        Long c = this.f330c.mo329c(this.f332e);
        Long c2 = this.f329b.mo329c(this.f332e);
        if (b == null) {
            b = null;
        }
        if (c != null) {
            b = Long.valueOf(b == null ? c.longValue() : Math.min(c.longValue(), b.longValue()));
        }
        if (c2 != null) {
            b = Long.valueOf(b == null ? c2.longValue() : Math.min(c2.longValue(), b.longValue()));
        }
        if (!z || (this.f338k instanceof C0204a)) {
            return b;
        }
        long a2 = this.f328a.mo356a() + C0233k.f320a;
        if (b != null) {
            a2 = Math.min(a2, b.longValue());
        }
        return Long.valueOf(a2);
    }

    /* renamed from: a */
    public void mo360a(int i) {
        this.f334g.mo347a((C0188f) this.f340m.m305a(C0188f.class));
    }

    /* renamed from: a */
    void m528a(C0160a c0160a) {
        this.f333f.m137a(c0160a);
    }

    /* renamed from: a */
    boolean m529a() {
        return this.f338k instanceof C0204a;
    }

    /* renamed from: b */
    int m530b() {
        return this.f329b.mo321a() + this.f330c.mo321a();
    }

    /* renamed from: c */
    boolean m531c() {
        return this.f343p;
    }

    /* renamed from: d */
    int m532d() {
        return m510b(m523g());
    }

    public void run() {
        this.f334g.m311a(new C02341(this));
    }
}
