package com.p001a.p002a.p003a.p014i.p015a;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.p001a.p002a.p003a.p011f.C0180b;

/* renamed from: com.a.a.a.i.a.c */
public class C0217c {
    /* renamed from: a */
    String f224a;
    /* renamed from: b */
    String f225b;
    /* renamed from: c */
    String f226c;
    /* renamed from: d */
    String f227d;
    /* renamed from: e */
    String f228e;
    /* renamed from: f */
    final StringBuilder f229f = new StringBuilder();
    /* renamed from: g */
    final SQLiteDatabase f230g;
    /* renamed from: h */
    final String f231h;
    /* renamed from: i */
    final String f232i;
    /* renamed from: j */
    final int f233j;
    /* renamed from: k */
    final String f234k;
    /* renamed from: l */
    final int f235l;
    /* renamed from: m */
    final long f236m;
    /* renamed from: n */
    private SQLiteStatement f237n;
    /* renamed from: o */
    private SQLiteStatement f238o;
    /* renamed from: p */
    private SQLiteStatement f239p;
    /* renamed from: q */
    private SQLiteStatement f240q;
    /* renamed from: r */
    private SQLiteStatement f241r;
    /* renamed from: s */
    private SQLiteStatement f242s;
    /* renamed from: t */
    private SQLiteStatement f243t;
    /* renamed from: u */
    private SQLiteStatement f244u;

    /* renamed from: com.a.a.a.i.a.c$a */
    public static class C0213a {
        /* renamed from: a */
        final String f212a;
        /* renamed from: b */
        final String f213b;

        public C0213a(String str, String str2) {
            this.f212a = str;
            this.f213b = str2;
        }
    }

    /* renamed from: com.a.a.a.i.a.c$b */
    public static class C0215b {
        /* renamed from: a */
        final C0216c f217a;
        /* renamed from: b */
        final C0214a f218b;

        /* renamed from: com.a.a.a.i.a.c$b$a */
        public enum C0214a {
            ASC,
            DESC
        }

        public C0215b(C0216c c0216c, C0214a c0214a) {
            this.f217a = c0216c;
            this.f218b = c0214a;
        }
    }

    /* renamed from: com.a.a.a.i.a.c$c */
    public static class C0216c {
        /* renamed from: a */
        final String f219a;
        /* renamed from: b */
        final String f220b;
        /* renamed from: c */
        public final int f221c;
        /* renamed from: d */
        public final C0213a f222d;
        /* renamed from: e */
        public final boolean f223e;

        public C0216c(String str, String str2, int i) {
            this(str, str2, i, null, false);
        }

        public C0216c(String str, String str2, int i, C0213a c0213a) {
            this(str, str2, i, c0213a, false);
        }

        public C0216c(String str, String str2, int i, C0213a c0213a, boolean z) {
            this.f219a = str;
            this.f220b = str2;
            this.f221c = i;
            this.f222d = c0213a;
            this.f223e = z;
        }
    }

    public C0217c(SQLiteDatabase sQLiteDatabase, String str, String str2, int i, String str3, int i2, long j) {
        this.f230g = sQLiteDatabase;
        this.f231h = str;
        this.f233j = i;
        this.f232i = str2;
        this.f236m = j;
        this.f235l = i2;
        this.f234k = str3;
        this.f224a = "SELECT * FROM " + str + " WHERE " + C0211a.f197b.f219a + " = ?";
        this.f225b = "SELECT * FROM " + str + " WHERE " + C0211a.f197b.f219a + " IN ( SELECT " + C0211a.f209n.f219a + " FROM " + str3 + " WHERE " + C0211a.f210o.f219a + " = ?)";
        this.f226c = "SELECT " + C0211a.f197b.f219a + " FROM " + str;
        this.f227d = "SELECT " + C0211a.f210o.f219a + " FROM " + "job_holder_tags" + " WHERE " + C0211a.f209n.f219a + " = ?";
        this.f228e = "UPDATE " + str + " SET " + C0211a.f207l.f219a + " = 0";
    }

    /* renamed from: a */
    public static String m350a(String str) {
        return "DROP TABLE IF EXISTS " + str;
    }

    /* renamed from: a */
    public static String m351a(String str, C0216c c0216c, C0216c... c0216cArr) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(str).append(" (");
        stringBuilder.append(c0216c.f219a).append(" ");
        stringBuilder.append(c0216c.f220b);
        stringBuilder.append("  primary key ");
        for (C0216c c0216c2 : c0216cArr) {
            stringBuilder.append(", `").append(c0216c2.f219a).append("` ").append(c0216c2.f220b);
            if (c0216c2.f223e) {
                stringBuilder.append(" UNIQUE");
            }
        }
        for (C0216c c0216c22 : c0216cArr) {
            if (c0216c22.f222d != null) {
                C0213a c0213a = c0216c22.f222d;
                stringBuilder.append(", FOREIGN KEY(`").append(c0216c22.f219a).append("`) REFERENCES ").append(c0213a.f212a).append("(`").append(c0213a.f213b).append("`) ON DELETE CASCADE");
            }
        }
        stringBuilder.append(" );");
        C0180b.m231a(stringBuilder.toString(), new Object[0]);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    static void m352a(StringBuilder stringBuilder, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("cannot create placeholders for 0 items");
        }
        stringBuilder.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            stringBuilder.append(",?");
        }
    }

    /* renamed from: a */
    public SQLiteStatement m353a() {
        int i = 0;
        if (this.f237n == null) {
            this.f229f.setLength(0);
            this.f229f.append("INSERT INTO ").append(this.f231h);
            this.f229f.append(" VALUES (");
            while (i < this.f233j) {
                if (i != 0) {
                    this.f229f.append(",");
                }
                this.f229f.append("?");
                i++;
            }
            this.f229f.append(")");
            this.f237n = this.f230g.compileStatement(this.f229f.toString());
        }
        return this.f237n;
    }

    /* renamed from: a */
    public String m354a(String str, Integer num, C0215b... c0215bArr) {
        this.f229f.setLength(0);
        this.f229f.append("SELECT * FROM ");
        this.f229f.append(this.f231h);
        if (str != null) {
            this.f229f.append(" WHERE ").append(str);
        }
        int i = 1;
        int length = c0215bArr.length;
        int i2 = 0;
        while (i2 < length) {
            C0215b c0215b = c0215bArr[i2];
            if (i != 0) {
                this.f229f.append(" ORDER BY ");
            } else {
                this.f229f.append(",");
            }
            this.f229f.append(c0215b.f217a.f219a).append(" ").append(c0215b.f218b);
            i2++;
            i = 0;
        }
        if (num != null) {
            this.f229f.append(" LIMIT ").append(num);
        }
        return this.f229f.toString();
    }

    /* renamed from: a */
    public String m355a(String str, String str2, Integer num, C0215b... c0215bArr) {
        this.f229f.setLength(0);
        this.f229f.append("SELECT ").append(str).append(" FROM ").append(this.f231h);
        if (str2 != null) {
            this.f229f.append(" WHERE ").append(str2);
        }
        int i = 1;
        int length = c0215bArr.length;
        int i2 = 0;
        while (i2 < length) {
            C0215b c0215b = c0215bArr[i2];
            if (i != 0) {
                this.f229f.append(" ORDER BY ");
            } else {
                this.f229f.append(",");
            }
            this.f229f.append(c0215b.f217a.f219a).append(" ").append(c0215b.f218b);
            i2++;
            i = 0;
        }
        if (num != null) {
            this.f229f.append(" LIMIT ").append(num);
        }
        return this.f229f.toString();
    }

    /* renamed from: a */
    public void m356a(long j) {
        this.f230g.execSQL("UPDATE job_holder SET " + C0211a.f202g.f219a + "=?", new Object[]{Long.valueOf(j)});
    }

    /* renamed from: b */
    public SQLiteStatement m357b() {
        int i = 0;
        if (this.f238o == null) {
            this.f229f.setLength(0);
            this.f229f.append("INSERT INTO ").append("job_holder_tags");
            this.f229f.append(" VALUES (");
            while (i < this.f235l) {
                if (i != 0) {
                    this.f229f.append(",");
                }
                this.f229f.append("?");
                i++;
            }
            this.f229f.append(")");
            this.f238o = this.f230g.compileStatement(this.f229f.toString());
        }
        return this.f238o;
    }

    /* renamed from: c */
    public SQLiteStatement m358c() {
        if (this.f243t == null) {
            this.f243t = this.f230g.compileStatement("SELECT COUNT(*) FROM " + this.f231h + " WHERE " + C0211a.f203h.f219a + " != ?");
        }
        return this.f243t;
    }

    /* renamed from: d */
    public SQLiteStatement m359d() {
        int i = 0;
        if (this.f239p == null) {
            this.f229f.setLength(0);
            this.f229f.append("INSERT OR REPLACE INTO ").append(this.f231h);
            this.f229f.append(" VALUES (");
            while (i < this.f233j) {
                if (i != 0) {
                    this.f229f.append(",");
                }
                this.f229f.append("?");
                i++;
            }
            this.f229f.append(")");
            this.f239p = this.f230g.compileStatement(this.f229f.toString());
        }
        return this.f239p;
    }

    /* renamed from: e */
    public SQLiteStatement m360e() {
        if (this.f240q == null) {
            this.f240q = this.f230g.compileStatement("DELETE FROM " + this.f231h + " WHERE " + this.f232i + " = ?");
        }
        return this.f240q;
    }

    /* renamed from: f */
    public SQLiteStatement m361f() {
        if (this.f241r == null) {
            this.f241r = this.f230g.compileStatement("DELETE FROM " + this.f234k + " WHERE " + C0211a.f209n.f219a + "= ?");
        }
        return this.f241r;
    }

    /* renamed from: g */
    public SQLiteStatement m362g() {
        if (this.f242s == null) {
            this.f242s = this.f230g.compileStatement("UPDATE " + this.f231h + " SET " + C0211a.f200e.f219a + " = ? , " + C0211a.f203h.f219a + " = ?  WHERE " + this.f232i + " = ? ");
        }
        return this.f242s;
    }

    /* renamed from: h */
    public SQLiteStatement m363h() {
        if (this.f244u == null) {
            this.f244u = this.f230g.compileStatement("UPDATE " + this.f231h + " SET " + C0211a.f207l.f219a + " = 1  WHERE " + this.f232i + " = ? ");
        }
        return this.f244u;
    }

    /* renamed from: i */
    public void m364i() {
        this.f230g.execSQL("DELETE FROM job_holder");
        this.f230g.execSQL("DELETE FROM job_holder_tags");
        m365j();
    }

    /* renamed from: j */
    public void m365j() {
        this.f230g.execSQL("VACUUM");
    }
}
