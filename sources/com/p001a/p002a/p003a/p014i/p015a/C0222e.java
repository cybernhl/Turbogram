package com.p001a.p002a.p003a.p014i.p015a;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.p001a.p002a.p003a.p014i.p015a.C0217c.C0215b;
import com.p001a.p002a.p003a.p014i.p015a.C0217c.C0215b.C0214a;

/* renamed from: com.a.a.a.i.a.e */
public class C0222e {
    /* renamed from: d */
    static final String f253d = Long.toString(Long.MIN_VALUE);
    /* renamed from: e */
    static final String f254e = Long.toString(Long.MAX_VALUE);
    /* renamed from: a */
    public final long f255a;
    /* renamed from: b */
    public final String f256b;
    /* renamed from: c */
    public final String[] f257c;
    /* renamed from: f */
    private SQLiteStatement f258f;
    /* renamed from: g */
    private String f259g;
    /* renamed from: h */
    private SQLiteStatement f260h;
    /* renamed from: i */
    private String f261i;

    public C0222e(long j, String str, String[] strArr) {
        this.f255a = j;
        this.f256b = str;
        this.f257c = strArr;
    }

    /* renamed from: a */
    public SQLiteStatement m394a(SQLiteDatabase sQLiteDatabase, C0217c c0217c) {
        if (this.f260h == null) {
            String a = c0217c.m355a(C0211a.f205j.f219a, this.f256b, null, new C0215b[0]);
            String a2 = c0217c.m355a(C0211a.f202g.f219a, this.f256b, null, new C0215b[0]);
            StringBuilder stringBuilder = c0217c.f229f;
            stringBuilder.setLength(0);
            stringBuilder.append("SELECT * FROM (").append(a).append(" ORDER BY 1 ASC LIMIT 1").append(") UNION SELECT * FROM (").append(a2).append(" ORDER BY 1 ASC LIMIT 1").append(") ORDER BY 1 ASC LIMIT 1");
            this.f260h = sQLiteDatabase.compileStatement(stringBuilder.toString());
        } else {
            this.f260h.clearBindings();
        }
        for (int i = 1; i <= this.f257c.length; i++) {
            this.f260h.bindString(i, this.f257c[i - 1]);
            this.f260h.bindString(this.f257c.length + i, this.f257c[i - 1]);
        }
        this.f260h.bindString(1, f254e);
        this.f260h.bindString(this.f257c.length + 1, f253d);
        return this.f260h;
    }

    /* renamed from: a */
    public SQLiteStatement m395a(SQLiteDatabase sQLiteDatabase, StringBuilder stringBuilder) {
        if (this.f258f == null) {
            stringBuilder.setLength(0);
            stringBuilder.append("SELECT SUM(case WHEN ").append(C0211a.f199d.f219a).append(" is null then group_cnt else 1 end) from (").append("SELECT count(*) group_cnt, ").append(C0211a.f199d.f219a).append(" FROM ").append("job_holder").append(" WHERE ").append(this.f256b).append(" GROUP BY ").append(C0211a.f199d.f219a).append(")");
            this.f258f = sQLiteDatabase.compileStatement(stringBuilder.toString());
        } else {
            this.f258f.clearBindings();
        }
        for (int i = 1; i <= this.f257c.length; i++) {
            this.f258f.bindString(i, this.f257c[i - 1]);
        }
        return this.f258f;
    }

    /* renamed from: a */
    public String m396a(C0217c c0217c) {
        if (this.f261i == null) {
            this.f261i = c0217c.m354a(this.f256b, Integer.valueOf(1), new C0215b(C0211a.f198c, C0214a.DESC), new C0215b(C0211a.f201f, C0214a.ASC), new C0215b(C0211a.f196a, C0214a.ASC));
        }
        return this.f261i;
    }

    /* renamed from: a */
    public void m397a() {
        if (this.f258f != null) {
            this.f258f.close();
            this.f258f = null;
        }
        if (this.f260h != null) {
            this.f260h.close();
            this.f260h = null;
        }
    }

    /* renamed from: b */
    public String m398b(C0217c c0217c) {
        if (this.f259g == null) {
            this.f259g = c0217c.m354a(this.f256b, null, new C0215b[0]);
        }
        return this.f259g;
    }
}
