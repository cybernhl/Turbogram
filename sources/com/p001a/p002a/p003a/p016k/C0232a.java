package com.p001a.p002a.p003a.p016k;

import com.p001a.p002a.p003a.p011f.C0180b;
import java.util.concurrent.TimeUnit;

/* renamed from: com.a.a.a.k.a */
public class C0232a implements C0231b {
    /* renamed from: a */
    final long f318a = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    /* renamed from: b */
    final long f319b = System.nanoTime();

    public C0232a() {
        C0180b.m231a("creating system timer", new Object[0]);
    }

    /* renamed from: a */
    public long mo356a() {
        return (System.nanoTime() - this.f319b) + this.f318a;
    }

    /* renamed from: a */
    public void mo357a(Object obj) {
        obj.wait();
    }

    /* renamed from: a */
    public void mo358a(Object obj, long j) {
        long a = mo356a();
        if (a > j) {
            obj.wait(1);
        } else {
            TimeUnit.NANOSECONDS.timedWait(obj, j - a);
        }
    }

    /* renamed from: b */
    public void mo359b(Object obj) {
        obj.notifyAll();
    }
}
