package com.p001a.p002a.p003a.p006g;

import com.p001a.p002a.p003a.p006g.p012a.C0183a;
import com.p001a.p002a.p003a.p006g.p012a.C0184b;
import com.p001a.p002a.p003a.p006g.p012a.C0185c;
import com.p001a.p002a.p003a.p006g.p012a.C0186d;
import com.p001a.p002a.p003a.p006g.p012a.C0187e;
import com.p001a.p002a.p003a.p006g.p012a.C0188f;
import com.p001a.p002a.p003a.p006g.p012a.C0189g;
import com.p001a.p002a.p003a.p006g.p012a.C0190h;
import com.p001a.p002a.p003a.p006g.p012a.C0191i;
import com.p001a.p002a.p003a.p006g.p012a.C0192j;
import com.p001a.p002a.p003a.p006g.p012a.C0193k;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.a.g.i */
public enum C0200i {
    CALLBACK(C0184b.class, 0),
    CANCEL_RESULT_CALLBACK(C0186d.class, 0),
    RUN_JOB(C0191i.class, 0),
    COMMAND(C0187e.class, 0),
    PUBLIC_QUERY(C0190h.class, 0),
    JOB_CONSUMER_IDLE(C0189g.class, 0),
    ADD_JOB(C0183a.class, 1),
    CANCEL(C0185c.class, 1),
    CONSTRAINT_CHANGE(C0188f.class, 2),
    RUN_JOB_RESULT(C0192j.class, 3),
    SCHEDULER(C0193k.class, 4);
    
    /* renamed from: m */
    static final Map<Class<? extends C0182b>, C0200i> f185m = null;
    /* renamed from: o */
    static final int f186o = 0;
    /* renamed from: l */
    final Class<? extends C0182b> f188l;
    /* renamed from: n */
    final int f189n;

    static {
        f185m = new HashMap();
        int i = 0;
        for (C0200i c0200i : C0200i.values()) {
            f185m.put(c0200i.f188l, c0200i);
            if (c0200i.f189n > i) {
                i = c0200i.f189n;
            }
        }
        f186o = i;
    }

    private C0200i(Class<? extends C0182b> cls, int i) {
        this.f188l = cls;
        this.f189n = i;
    }
}
