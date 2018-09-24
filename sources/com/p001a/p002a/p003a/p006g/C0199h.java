package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.a.a.a.g.h */
public class C0199h extends C0198j implements C0196e {
    /* renamed from: b */
    private final Object f168b = new Object();
    /* renamed from: c */
    private final AtomicBoolean f169c = new AtomicBoolean(false);
    /* renamed from: d */
    private final C0231b f170d;
    /* renamed from: e */
    private final C0194a f171e;
    /* renamed from: f */
    private boolean f172f = false;
    /* renamed from: g */
    private final C0195c f173g;

    public C0199h(C0231b c0231b, C0195c c0195c, String str) {
        super(c0195c, str);
        this.f173g = c0195c;
        this.f170d = c0231b;
        this.f171e = new C0194a(c0195c);
    }

    /* renamed from: a */
    public void m319a() {
        this.f169c.set(false);
        synchronized (this.f168b) {
            this.f170d.mo359b(this.f168b);
        }
    }

    /* renamed from: a */
    public void mo347a(C0182b c0182b) {
        synchronized (this.f168b) {
            this.f172f = true;
            super.mo347a(c0182b);
            this.f170d.mo359b(this.f168b);
        }
    }

    /* renamed from: a */
    public void m321a(C0182b c0182b, long j) {
        synchronized (this.f168b) {
            this.f172f = true;
            this.f171e.m303a(c0182b, j);
            this.f170d.mo359b(this.f168b);
        }
    }

    /* renamed from: a */
    public void m322a(C0173d c0173d) {
        synchronized (this.f168b) {
            super.m317b(c0173d);
            this.f171e.m304a(c0173d);
        }
    }

    /* renamed from: a */
    public void m323a(C0157f c0157f) {
        if (this.f169c.getAndSet(true)) {
            throw new IllegalStateException("only 1 consumer per MQ");
        }
        c0157f.mo337a();
        while (this.f169c.get()) {
            C0182b b = m324b(c0157f);
            if (b != null) {
                c0157f.mo338a(b);
                this.f173g.m306a(b);
            }
        }
        C0180b.m231a("[%s] finished queue", this.a);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    com.p001a.p002a.p003a.p006g.C0182b m324b(com.p001a.p002a.p003a.p006g.C0157f r11) {
        /*
        r10 = this;
        r1 = 1;
        r0 = 0;
    L_0x0002:
        r2 = r10.f169c;
        r2 = r2.get();
        if (r2 == 0) goto L_0x0095;
    L_0x000a:
        r3 = r10.f168b;
        monitor-enter(r3);
        r2 = r10.f170d;	 Catch:{ all -> 0x0038 }
        r4 = r2.mo356a();	 Catch:{ all -> 0x0038 }
        r2 = r10.f171e;	 Catch:{ all -> 0x0038 }
        r6 = r2.m302a(r4, r10);	 Catch:{ all -> 0x0038 }
        r2 = super.m318c();	 Catch:{ all -> 0x0038 }
        if (r2 == 0) goto L_0x0022;
    L_0x001f:
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
        r0 = r2;
    L_0x0021:
        return r0;
    L_0x0022:
        r2 = 0;
        r10.f172f = r2;	 Catch:{ all -> 0x0038 }
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
        if (r0 != 0) goto L_0x002c;
    L_0x0028:
        r11.mo339b();
        r0 = r1;
    L_0x002c:
        r2 = r10.f168b;
        monitor-enter(r2);
        r3 = r10.f172f;	 Catch:{ all -> 0x0035 }
        if (r3 == 0) goto L_0x003b;
    L_0x0033:
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        goto L_0x0002;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
        throw r0;
    L_0x003b:
        if (r6 == 0) goto L_0x0055;
    L_0x003d:
        r8 = r6.longValue();	 Catch:{ all -> 0x0035 }
        r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r3 > 0) goto L_0x0055;
    L_0x0045:
        r3 = "[%s] next message is ready, requery";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0035 }
        r5 = 0;
        r6 = r10.a;	 Catch:{ all -> 0x0035 }
        r4[r5] = r6;	 Catch:{ all -> 0x0035 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r3, r4);	 Catch:{ all -> 0x0035 }
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        goto L_0x0002;
    L_0x0055:
        r3 = r10.f169c;	 Catch:{ all -> 0x0035 }
        r3 = r3.get();	 Catch:{ all -> 0x0035 }
        if (r3 == 0) goto L_0x0074;
    L_0x005d:
        if (r6 != 0) goto L_0x0076;
    L_0x005f:
        r3 = "[%s] will wait on the lock forever";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x0093 }
        r5 = 0;
        r6 = r10.a;	 Catch:{ InterruptedException -> 0x0093 }
        r4[r5] = r6;	 Catch:{ InterruptedException -> 0x0093 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r3, r4);	 Catch:{ InterruptedException -> 0x0093 }
        r3 = r10.f170d;	 Catch:{ InterruptedException -> 0x0093 }
        r4 = r10.f168b;	 Catch:{ InterruptedException -> 0x0093 }
        r3.mo357a(r4);	 Catch:{ InterruptedException -> 0x0093 }
    L_0x0074:
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        goto L_0x0002;
    L_0x0076:
        r3 = "[%s] will wait on the lock until %d";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x0093 }
        r5 = 0;
        r7 = r10.a;	 Catch:{ InterruptedException -> 0x0093 }
        r4[r5] = r7;	 Catch:{ InterruptedException -> 0x0093 }
        r5 = 1;
        r4[r5] = r6;	 Catch:{ InterruptedException -> 0x0093 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r3, r4);	 Catch:{ InterruptedException -> 0x0093 }
        r3 = r10.f170d;	 Catch:{ InterruptedException -> 0x0093 }
        r4 = r10.f168b;	 Catch:{ InterruptedException -> 0x0093 }
        r6 = r6.longValue();	 Catch:{ InterruptedException -> 0x0093 }
        r3.mo358a(r4, r6);	 Catch:{ InterruptedException -> 0x0093 }
        goto L_0x0074;
    L_0x0093:
        r3 = move-exception;
        goto L_0x0074;
    L_0x0095:
        r0 = 0;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.g.h.b(com.a.a.a.g.f):com.a.a.a.g.b");
    }

    /* renamed from: b */
    public void mo348b() {
        synchronized (this.f168b) {
            super.mo348b();
        }
    }
}
