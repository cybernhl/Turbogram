package com.p001a.p002a.p003a;

import com.p001a.p002a.p003a.p011f.C0180b;
import com.p001a.p002a.p003a.p016k.C0231b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

/* renamed from: com.a.a.a.r */
public class C0241r {
    /* renamed from: a */
    private ArrayList<String> f366a;
    /* renamed from: b */
    private final TreeSet<String> f367b = new TreeSet();
    /* renamed from: c */
    private final Map<String, Long> f368c = new HashMap();
    /* renamed from: d */
    private long f369d = Long.MAX_VALUE;
    /* renamed from: e */
    private final C0231b f370e;

    public C0241r(C0231b c0231b) {
        this.f370e = c0231b;
    }

    /* renamed from: c */
    private long m552c() {
        long j = Long.MAX_VALUE;
        for (Long l : this.f368c.values()) {
            j = l.longValue() < j ? l.longValue() : j;
        }
        return j;
    }

    /* renamed from: a */
    public synchronized Collection<String> m553a() {
        long a = this.f370e.mo356a();
        if (this.f366a == null || a > this.f369d) {
            if (this.f368c.isEmpty()) {
                this.f366a = new ArrayList(this.f367b);
                this.f369d = Long.MAX_VALUE;
            } else {
                Collection treeSet = new TreeSet(this.f367b);
                Iterator it = this.f368c.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (((Long) entry.getValue()).longValue() <= a) {
                        it.remove();
                    } else if (!treeSet.contains(entry.getKey())) {
                        treeSet.add(entry.getKey());
                    }
                }
                this.f366a = new ArrayList(treeSet);
                this.f369d = m552c();
            }
        }
        return this.f366a;
    }

    /* renamed from: a */
    public synchronized void m554a(String str) {
        if (str != null) {
            if (this.f367b.add(str)) {
                this.f366a = null;
            }
        }
    }

    /* renamed from: a */
    public synchronized void m555a(String str, long j) {
        C0180b.m231a("add group delay to %s until %s", str, Long.valueOf(j));
        Long l = (Long) this.f368c.get(str);
        if (l == null || l.longValue() <= j) {
            this.f368c.put(str, Long.valueOf(j));
            this.f369d = m552c();
            this.f366a = null;
        }
    }

    /* renamed from: b */
    public Long m556b() {
        return this.f369d == Long.MAX_VALUE ? null : Long.valueOf(this.f369d);
    }

    /* renamed from: b */
    public synchronized void m557b(String str) {
        if (str != null) {
            if (this.f367b.remove(str)) {
                this.f366a = null;
            }
        }
    }
}
