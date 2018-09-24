package com.p001a.p002a.p003a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.a.a.a.i */
public abstract class C0225i implements Serializable {
    /* renamed from: a */
    transient int f265a;
    /* renamed from: b */
    transient int f266b;
    /* renamed from: c */
    volatile transient boolean f267c;
    /* renamed from: d */
    private transient String f268d = UUID.randomUUID().toString();
    /* renamed from: e */
    private transient String f269e;
    /* renamed from: f */
    private transient boolean f270f;
    /* renamed from: g */
    private transient Set<String> f271g;
    /* renamed from: h */
    private transient int f272h;
    /* renamed from: i */
    private transient long f273i;
    /* renamed from: j */
    private transient long f274j;
    /* renamed from: k */
    private transient boolean f275k;
    /* renamed from: l */
    private transient Context f276l;
    /* renamed from: m */
    private volatile transient boolean f277m;
    /* renamed from: n */
    private volatile transient boolean f278n;

    protected C0225i(C0238o c0238o) {
        this.f265a = c0238o.f351a;
        this.f270f = c0238o.m539e();
        this.f269e = c0238o.m537c();
        this.f266b = c0238o.m540f();
        this.f273i = Math.max(0, c0238o.m541g());
        this.f274j = Math.max(0, c0238o.m542h());
        this.f275k = c0238o.m544j();
        String d = c0238o.m538d();
        if (!(c0238o.m543i() == null && d == null)) {
            Set i = c0238o.m543i() != null ? c0238o.m543i() : new HashSet();
            if (d != null) {
                d = mo4384a(d);
                i.add(d);
                if (this.f269e == null) {
                    this.f269e = d;
                }
            }
            this.f271g = Collections.unmodifiableSet(i);
        }
        if (this.f274j > 0 && this.f274j < this.f273i) {
            throw new IllegalArgumentException("deadline cannot be less than the delay. It does not make sense. deadline:" + this.f274j + ",delay:" + this.f273i);
        }
    }

    /* renamed from: a */
    private String mo4384a(String str) {
        return "job-single-id:" + str;
    }

    /* renamed from: a */
    final int m406a(C0229j c0229j, int i, C0231b c0231b) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        this.f272h = i;
        if (C0180b.m234b()) {
            C0180b.m231a("running job %s", getClass().getSimpleName());
        }
        Throwable th = null;
        try {
            mo4389g();
            if (C0180b.m234b()) {
                C0180b.m231a("finished job %s", this);
            }
            z = false;
            z2 = false;
            z3 = false;
        } catch (Throwable th2) {
            C0180b.m232a(th2, "shouldReRunOnThrowable did throw an exception", new Object[0]);
            z2 = true;
        }
        String str = "safeRunResult for %s : %s. re run:%s. cancelled: %s";
        Object[] objArr = new Object[4];
        objArr[0] = this;
        if (!z2) {
            z4 = true;
        }
        objArr[1] = Boolean.valueOf(z4);
        objArr[2] = Boolean.valueOf(z3);
        objArr[3] = Boolean.valueOf(this.f267c);
        C0180b.m231a(str, objArr);
        if (!z2) {
            return 1;
        }
        if (c0229j.m471p()) {
            return 6;
        }
        if (c0229j.m469n()) {
            return 3;
        }
        if (z3) {
            return 4;
        }
        if (z) {
            return 7;
        }
        if (i < mo4385j()) {
            c0229j.m451a(th);
            return 5;
        }
        c0229j.m451a(th);
        return 2;
    }

    /* renamed from: a */
    protected abstract C0239q mo4386a(@NonNull Throwable th, int i, int i2);

    /* renamed from: a */
    public final String m408a() {
        return this.f268d;
    }

    /* renamed from: a */
    protected abstract void mo4387a(int i, @Nullable Throwable th);

    /* renamed from: a */
    void m410a(Context context) {
        this.f276l = context;
    }

    /* renamed from: a */
    final void m411a(C0229j c0229j) {
        if (this.f277m) {
            throw new IllegalStateException("Cannot set a Job from JobHolder after it is sealed.");
        }
        this.f268d = c0229j.f299a;
        this.f269e = c0229j.f301c;
        this.f266b = c0229j.m453b();
        this.f270f = c0229j.f300b;
        this.f271g = c0229j.f304f;
        this.f265a = c0229j.f302d;
        this.f277m = true;
    }

    /* renamed from: a */
    void m412a(boolean z) {
        this.f278n = z;
    }

    /* renamed from: b */
    public final int m413b() {
        return this.f266b;
    }

    /* renamed from: c */
    public final long m414c() {
        return this.f273i;
    }

    @Nullable
    /* renamed from: d */
    public final Set<String> m415d() {
        return this.f271g;
    }

    /* renamed from: e */
    public final boolean m416e() {
        return this.f270f;
    }

    /* renamed from: f */
    public abstract void mo4388f();

    /* renamed from: g */
    public abstract void mo4389g();

    /* renamed from: h */
    public final String m419h() {
        return this.f269e;
    }

    /* renamed from: i */
    public final String m420i() {
        if (this.f271g != null) {
            for (String str : this.f271g) {
                if (str.startsWith("job-single-id:")) {
                    return str;
                }
            }
        }
        return null;
    }

    /* renamed from: j */
    protected int mo4385j() {
        return 20;
    }

    /* renamed from: k */
    public Context m422k() {
        return this.f276l;
    }

    /* renamed from: l */
    long m423l() {
        return this.f274j;
    }

    /* renamed from: m */
    boolean m424m() {
        return this.f275k;
    }
}
