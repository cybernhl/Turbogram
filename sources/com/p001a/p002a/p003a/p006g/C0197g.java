package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.a.a.a.g.g */
public class C0197g implements C0196e {
    /* renamed from: a */
    private final Object f156a = new Object();
    /* renamed from: b */
    private final C0198j[] f157b;
    /* renamed from: c */
    private final C0194a f158c;
    /* renamed from: d */
    private final C0231b f159d;
    /* renamed from: e */
    private final AtomicBoolean f160e = new AtomicBoolean(false);
    /* renamed from: f */
    private boolean f161f = false;
    /* renamed from: g */
    private final C0195c f162g;

    public C0197g(C0231b c0231b, C0195c c0195c) {
        this.f158c = new C0194a(c0195c);
        this.f162g = c0195c;
        this.f157b = new C0198j[(C0200i.f186o + 1)];
        this.f159d = c0231b;
    }

    /* renamed from: a */
    public void m308a() {
        synchronized (this.f156a) {
            for (int i = C0200i.f186o; i >= 0; i--) {
                C0198j c0198j = this.f157b[i];
                if (c0198j != null) {
                    c0198j.mo348b();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo347a(C0182b c0182b) {
        synchronized (this.f156a) {
            this.f161f = true;
            int i = c0182b.f124a.f189n;
            if (this.f157b[i] == null) {
                this.f157b[i] = new C0198j(this.f162g, "queue_" + c0182b.f124a.name());
            }
            this.f157b[i].mo347a(c0182b);
            this.f159d.mo359b(this.f156a);
        }
    }

    /* renamed from: a */
    public void m310a(C0182b c0182b, long j) {
        synchronized (this.f156a) {
            this.f161f = true;
            this.f158c.m303a(c0182b, j);
            this.f159d.mo359b(this.f156a);
        }
    }

    /* renamed from: a */
    public void m311a(C0157f c0157f) {
        if (this.f160e.getAndSet(true)) {
            throw new IllegalStateException("only 1 consumer per MQ");
        }
        while (this.f160e.get()) {
            C0182b b = m312b(c0157f);
            if (b != null) {
                C0180b.m231a("[%s] consuming message of type %s", "priority_mq", b.f124a);
                c0157f.mo338a(b);
                this.f162g.m306a(b);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public com.p001a.p002a.p003a.p006g.C0182b m312b(com.p001a.p002a.p003a.p006g.C0157f r11) {
        /*
        r10 = this;
        r1 = 1;
        r0 = 0;
    L_0x0002:
        r2 = r10.f160e;
        r2 = r2.get();
        if (r2 == 0) goto L_0x00ba;
    L_0x000a:
        r4 = r10.f156a;
        monitor-enter(r4);
        r2 = r10.f159d;	 Catch:{ all -> 0x0088 }
        r6 = r2.mo356a();	 Catch:{ all -> 0x0088 }
        r2 = "[%s] looking for next message at time %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0088 }
        r5 = 0;
        r8 = "priority_mq";
        r3[r5] = r8;	 Catch:{ all -> 0x0088 }
        r5 = 1;
        r8 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0088 }
        r3[r5] = r8;	 Catch:{ all -> 0x0088 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r2, r3);	 Catch:{ all -> 0x0088 }
        r2 = r10.f158c;	 Catch:{ all -> 0x0088 }
        r5 = r2.m302a(r6, r10);	 Catch:{ all -> 0x0088 }
        r2 = "[%s] next delayed job %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0088 }
        r8 = 0;
        r9 = "priority_mq";
        r3[r8] = r9;	 Catch:{ all -> 0x0088 }
        r8 = 1;
        r3[r8] = r5;	 Catch:{ all -> 0x0088 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r2, r3);	 Catch:{ all -> 0x0088 }
        r2 = com.p001a.p002a.p003a.p006g.C0200i.f186o;	 Catch:{ all -> 0x0088 }
        r3 = r2;
    L_0x0044:
        if (r3 < 0) goto L_0x0059;
    L_0x0046:
        r2 = r10.f157b;	 Catch:{ all -> 0x0088 }
        r2 = r2[r3];	 Catch:{ all -> 0x0088 }
        if (r2 != 0) goto L_0x0050;
    L_0x004c:
        r2 = r3 + -1;
        r3 = r2;
        goto L_0x0044;
    L_0x0050:
        r2 = r2.m318c();	 Catch:{ all -> 0x0088 }
        if (r2 == 0) goto L_0x004c;
    L_0x0056:
        monitor-exit(r4);	 Catch:{ all -> 0x0088 }
        r0 = r2;
    L_0x0058:
        return r0;
    L_0x0059:
        r2 = 0;
        r10.f161f = r2;	 Catch:{ all -> 0x0088 }
        monitor-exit(r4);	 Catch:{ all -> 0x0088 }
        if (r0 != 0) goto L_0x0063;
    L_0x005f:
        r11.mo339b();
        r0 = r1;
    L_0x0063:
        r2 = r10.f156a;
        monitor-enter(r2);
        r3 = "[%s] did on idle post a message? %s";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0085 }
        r8 = 0;
        r9 = "priority_mq";
        r4[r8] = r9;	 Catch:{ all -> 0x0085 }
        r8 = 1;
        r9 = r10.f161f;	 Catch:{ all -> 0x0085 }
        r9 = java.lang.Boolean.valueOf(r9);	 Catch:{ all -> 0x0085 }
        r4[r8] = r9;	 Catch:{ all -> 0x0085 }
        com.p001a.p002a.p003a.p011f.C0180b.m231a(r3, r4);	 Catch:{ all -> 0x0085 }
        r3 = r10.f161f;	 Catch:{ all -> 0x0085 }
        if (r3 == 0) goto L_0x008b;
    L_0x0082:
        monitor-exit(r2);	 Catch:{ all -> 0x0085 }
        goto L_0x0002;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0085 }
        throw r0;
    L_0x0088:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0088 }
        throw r0;
    L_0x008b:
        if (r5 == 0) goto L_0x0098;
    L_0x008d:
        r8 = r5.longValue();	 Catch:{ all -> 0x0085 }
        r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r3 > 0) goto L_0x0098;
    L_0x0095:
        monitor-exit(r2);	 Catch:{ all -> 0x0085 }
        goto L_0x0002;
    L_0x0098:
        r3 = r10.f160e;	 Catch:{ all -> 0x0085 }
        r3 = r3.get();	 Catch:{ all -> 0x0085 }
        if (r3 == 0) goto L_0x00a9;
    L_0x00a0:
        if (r5 != 0) goto L_0x00ac;
    L_0x00a2:
        r3 = r10.f159d;	 Catch:{ InterruptedException -> 0x00b8 }
        r4 = r10.f156a;	 Catch:{ InterruptedException -> 0x00b8 }
        r3.mo357a(r4);	 Catch:{ InterruptedException -> 0x00b8 }
    L_0x00a9:
        monitor-exit(r2);	 Catch:{ all -> 0x0085 }
        goto L_0x0002;
    L_0x00ac:
        r3 = r10.f159d;	 Catch:{ InterruptedException -> 0x00b8 }
        r4 = r10.f156a;	 Catch:{ InterruptedException -> 0x00b8 }
        r6 = r5.longValue();	 Catch:{ InterruptedException -> 0x00b8 }
        r3.mo358a(r4, r6);	 Catch:{ InterruptedException -> 0x00b8 }
        goto L_0x00a9;
    L_0x00b8:
        r3 = move-exception;
        goto L_0x00a9;
    L_0x00ba:
        r0 = 0;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.g.g.b(com.a.a.a.g.f):com.a.a.a.g.b");
    }

    /* renamed from: b */
    public void m313b() {
        this.f160e.set(false);
        synchronized (this.f156a) {
            this.f159d.mo359b(this.f156a);
        }
    }
}
