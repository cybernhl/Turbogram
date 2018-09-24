package com.p001a.p002a.p003a.p014i.p015a;

import android.support.v4.util.LruCache;
import com.p001a.p002a.p003a.C0171e;
import com.p001a.p002a.p003a.C0242s;

/* renamed from: com.a.a.a.i.a.f */
class C0224f {
    /* renamed from: a */
    private final LruCache<Long, C0222e> f263a = new LruCache<Long, C0222e>(this, 15) {
        /* renamed from: a */
        final /* synthetic */ C0224f f262a;

        /* renamed from: a */
        protected void m399a(boolean z, Long l, C0222e c0222e, C0222e c0222e2) {
            c0222e.m397a();
        }

        protected /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            m399a(z, (Long) obj, (C0222e) obj2, (C0222e) obj3);
        }
    };
    /* renamed from: b */
    private final String f264b;

    public C0224f(long j) {
        this.f264b = Long.toString(j);
    }

    /* renamed from: a */
    private C0222e m400a(long j, C0171e c0171e, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        stringBuilder.append("( (").append(C0211a.f205j.f219a).append(" != ").append(C0222e.f254e).append(" AND ").append(C0211a.f205j.f219a).append(" <= ?) OR ");
        stringBuilder.append(C0211a.f204i.f219a).append(" <= ?)");
        stringBuilder.append(" AND (").append(C0211a.f207l.f219a).append(" IS NULL OR ").append(C0211a.f207l.f219a).append(" != 1)");
        int i = 2;
        if (c0171e.m200f() != null) {
            stringBuilder.append(" AND ").append(C0211a.f202g.f219a).append(" <= ?");
            i = 3;
        }
        if (c0171e.m195b() != null) {
            if (c0171e.m197c().isEmpty()) {
                stringBuilder.append(" AND 0 ");
            } else {
                stringBuilder.append(" AND ").append(C0211a.f197b.f219a).append(" IN ( SELECT ").append(C0211a.f209n.f219a).append(" FROM ").append("job_holder_tags").append(" WHERE ").append(C0211a.f210o.f219a).append(" IN (");
                C0217c.m352a(stringBuilder, c0171e.m197c().size());
                stringBuilder.append(")");
                if (c0171e.m195b() == C0242s.ANY) {
                    stringBuilder.append(")");
                } else if (c0171e.m195b() == C0242s.ALL) {
                    stringBuilder.append(" GROUP BY (`").append(C0211a.f209n.f219a).append("`)").append(" HAVING count(*) = ").append(c0171e.m197c().size()).append(")");
                } else {
                    throw new IllegalArgumentException("unknown constraint " + c0171e);
                }
                i += c0171e.m197c().size();
            }
        }
        if (!c0171e.m198d().isEmpty()) {
            stringBuilder.append(" AND (").append(C0211a.f199d.f219a).append(" IS NULL OR ").append(C0211a.f199d.f219a).append(" NOT IN(");
            C0217c.m352a(stringBuilder, c0171e.m198d().size());
            stringBuilder.append("))");
            i += c0171e.m198d().size();
        }
        if (!c0171e.m201g().isEmpty()) {
            stringBuilder.append(" AND ").append(C0211a.f197b.f219a).append(" NOT IN(");
            C0217c.m352a(stringBuilder, c0171e.m201g().size());
            stringBuilder.append(")");
            i += c0171e.m201g().size();
        }
        if (c0171e.m199e()) {
            stringBuilder.append(" AND ").append(C0211a.f203h.f219a).append(" != ?");
            i++;
        }
        return new C0222e(j, stringBuilder.toString(), new String[i]);
    }

    /* renamed from: a */
    private void m401a(C0171e c0171e, C0222e c0222e) {
        int i;
        int i2;
        c0222e.f257c[0] = Long.toString(c0171e.m202h());
        c0222e.f257c[1] = Integer.toString(c0171e.m187a());
        if (c0171e.m200f() != null) {
            i = 3;
            c0222e.f257c[2] = Long.toString(c0171e.m200f().longValue());
        } else {
            i = 2;
        }
        if (c0171e.m195b() != null) {
            i2 = i;
            for (String str : c0171e.m197c()) {
                int i3 = i2 + 1;
                c0222e.f257c[i2] = str;
                i2 = i3;
            }
        } else {
            i2 = i;
        }
        for (String str2 : c0171e.m198d()) {
            i3 = i2 + 1;
            c0222e.f257c[i2] = str2;
            i2 = i3;
        }
        for (String str22 : c0171e.m201g()) {
            i3 = i2 + 1;
            c0222e.f257c[i2] = str22;
            i2 = i3;
        }
        if (c0171e.m199e()) {
            i = i2 + 1;
            c0222e.f257c[i2] = this.f264b;
            i2 = i;
        }
        if (i2 != c0222e.f257c.length) {
            throw new IllegalStateException("something is wrong with where query cache for " + c0222e.f256b);
        }
    }

    /* renamed from: a */
    private boolean m402a(C0171e c0171e) {
        return c0171e.m197c().size() < 64 && c0171e.m198d().size() < 64 && c0171e.m201g().size() < 64;
    }

    /* renamed from: b */
    private long m403b(C0171e c0171e) {
        int i = 1;
        int size = ((c0171e.m199e() ? 1 : 0) << 20) | ((c0171e.m201g().size() << 14) | ((((c0171e.m195b() == null ? 2 : c0171e.m195b().ordinal()) << 0) | (c0171e.m197c().size() << 2)) | (c0171e.m198d().size() << 8)));
        if (c0171e.m200f() != null) {
            i = 0;
        }
        return (long) (size | (i << 21));
    }

    /* renamed from: a */
    public C0222e m404a(C0171e c0171e, StringBuilder stringBuilder) {
        boolean a = m402a(c0171e);
        long b = m403b(c0171e);
        C0222e c0222e = a ? (C0222e) this.f263a.get(Long.valueOf(b)) : null;
        if (c0222e == null) {
            c0222e = m400a(b, c0171e, stringBuilder);
            if (a) {
                this.f263a.put(Long.valueOf(b), c0222e);
            }
        }
        m401a(c0171e, c0222e);
        return c0222e;
    }
}
