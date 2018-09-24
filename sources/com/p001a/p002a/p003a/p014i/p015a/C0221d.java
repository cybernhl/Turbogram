package com.p001a.p002a.p003a.p014i.p015a;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import com.p001a.p002a.p003a.C0153m;
import com.p001a.p002a.p003a.C0171e;
import com.p001a.p002a.p003a.C0225i;
import com.p001a.p002a.p003a.C0229j;
import com.p001a.p002a.p003a.C0229j.C0227a;
import com.p001a.p002a.p003a.p008c.C0164a;
import com.p001a.p002a.p003a.p011f.C0180b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.a.a.a.i.a.d */
public class C0221d implements C0153m {
    /* renamed from: a */
    private C0211a f245a;
    /* renamed from: b */
    private final long f246b;
    /* renamed from: c */
    private SQLiteDatabase f247c;
    /* renamed from: d */
    private C0217c f248d;
    /* renamed from: e */
    private C0219c f249e;
    /* renamed from: f */
    private C0212b f250f;
    /* renamed from: g */
    private final StringBuilder f251g = new StringBuilder();
    /* renamed from: h */
    private final C0224f f252h;

    /* renamed from: com.a.a.a.i.a.d$a */
    static class C0218a extends Exception {
        C0218a(String str) {
            super(str);
        }

        C0218a(String str, Throwable th) {
            super(str, th);
        }
    }

    /* renamed from: com.a.a.a.i.a.d$c */
    public interface C0219c {
        /* renamed from: a */
        <T extends C0225i> T mo353a(byte[] bArr);

        /* renamed from: a */
        byte[] mo354a(Object obj);
    }

    /* renamed from: com.a.a.a.i.a.d$b */
    public static class C0220b implements C0219c {
        /* renamed from: a */
        public <T extends C0225i> T mo353a(byte[] bArr) {
            ObjectInputStream objectInputStream;
            Throwable th;
            T t = null;
            if (!(bArr == null || bArr.length == 0)) {
                try {
                    objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                    try {
                        C0225i c0225i = (C0225i) objectInputStream.readObject();
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            }
            return t;
        }

        /* renamed from: a */
        public byte[] mo354a(Object obj) {
            Throwable th;
            byte[] bArr = null;
            if (obj != null) {
                ByteArrayOutputStream byteArrayOutputStream;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
                        bArr = byteArrayOutputStream.toByteArray();
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            return bArr;
        }
    }

    public C0221d(C0164a c0164a, long j, C0219c c0219c) {
        this.f246b = j;
        this.f250f = new C0212b(c0164a.m148a(), "jobs_" + c0164a.m149b());
        this.f252h = new C0224f(j);
        this.f245a = new C0211a(c0164a.m148a(), c0164a.m159l() ? null : "db_" + c0164a.m149b());
        this.f247c = this.f245a.getWritableDatabase();
        this.f248d = new C0217c(this.f247c, "job_holder", C0211a.f197b.f219a, 12, "job_holder_tags", 3, j);
        this.f249e = c0219c;
        if (c0164a.m161n()) {
            this.f248d.m356a(Long.MIN_VALUE);
        }
        m376c();
        m377d();
    }

    /* renamed from: a */
    private C0225i m370a(byte[] bArr) {
        try {
            return this.f249e.mo353a(bArr);
        } catch (Throwable th) {
            C0180b.m232a(th, "error while deserializing job", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private C0229j m371a(Cursor cursor) {
        boolean z = true;
        String string = cursor.getString(C0211a.f197b.f221c);
        try {
            C0225i a = m370a(this.f250f.m349b(string));
            if (a == null) {
                throw new C0218a("null job");
            }
            C0227a a2 = new C0227a().m437c(cursor.getLong(C0211a.f196a.f221c)).m425a(cursor.getInt(C0211a.f198c.f221c)).m429a(cursor.getString(C0211a.f199d.f221c)).m433b(cursor.getInt(C0211a.f200e.f221c)).m428a(a).m435b(string).m430a(m375c(string)).m431a(true);
            long j = cursor.getLong(C0211a.f205j.f221c);
            if (cursor.getInt(C0211a.f206k.f221c) != 1) {
                z = false;
            }
            return a2.m427a(j, z).m426a(cursor.getLong(C0211a.f201f.f221c)).m434b(cursor.getLong(C0211a.f202g.f221c)).m438d(cursor.getLong(C0211a.f203h.f221c)).m436c(cursor.getInt(C0211a.f204i.f221c)).m432a();
        } catch (Throwable e) {
            throw new C0218a("cannot load job from disk", e);
        }
    }

    /* renamed from: a */
    private void m372a(SQLiteStatement sQLiteStatement, C0229j c0229j) {
        long j = 1;
        if (c0229j.m456c() != null) {
            sQLiteStatement.bindLong(C0211a.f196a.f221c + 1, c0229j.m456c().longValue());
        }
        sQLiteStatement.bindString(C0211a.f197b.f221c + 1, c0229j.m447a());
        sQLiteStatement.bindLong(C0211a.f198c.f221c + 1, (long) c0229j.m453b());
        if (c0229j.m466k() != null) {
            sQLiteStatement.bindString(C0211a.f199d.f221c + 1, c0229j.m466k());
        }
        sQLiteStatement.bindLong(C0211a.f200e.f221c + 1, (long) c0229j.m459d());
        sQLiteStatement.bindLong(C0211a.f201f.f221c + 1, c0229j.m460e());
        sQLiteStatement.bindLong(C0211a.f202g.f221c + 1, c0229j.m464i());
        sQLiteStatement.bindLong(C0211a.f203h.f221c + 1, c0229j.m461f());
        sQLiteStatement.bindLong(C0211a.f204i.f221c + 1, (long) c0229j.m477v());
        sQLiteStatement.bindLong(C0211a.f205j.f221c + 1, c0229j.m462g());
        sQLiteStatement.bindLong(C0211a.f206k.f221c + 1, c0229j.m463h() ? 1 : 0);
        int i = C0211a.f207l.f221c + 1;
        if (!c0229j.m469n()) {
            j = 0;
        }
        sQLiteStatement.bindLong(i, j);
    }

    /* renamed from: a */
    private void m373a(SQLiteStatement sQLiteStatement, String str, String str2) {
        sQLiteStatement.bindString(C0211a.f209n.f221c + 1, str);
        sQLiteStatement.bindString(C0211a.f210o.f221c + 1, str2);
    }

    /* renamed from: b */
    private void m374b(String str) {
        this.f247c.beginTransaction();
        try {
            SQLiteStatement e = this.f248d.m360e();
            e.clearBindings();
            e.bindString(1, str);
            e.execute();
            e = this.f248d.m361f();
            e.bindString(1, str);
            e.execute();
            this.f247c.setTransactionSuccessful();
            this.f250f.m346a(str);
        } finally {
            this.f247c.endTransaction();
        }
    }

    /* renamed from: c */
    private Set<String> m375c(String str) {
        Cursor rawQuery = this.f247c.rawQuery(this.f248d.f227d, new String[]{str});
        try {
            Set<String> set;
            if (rawQuery.getCount() == 0) {
                set = Collections.EMPTY_SET;
            } else {
                set = new HashSet();
                while (rawQuery.moveToNext()) {
                    set.add(rawQuery.getString(0));
                }
                rawQuery.close();
            }
            return set;
        } finally {
            rawQuery.close();
        }
    }

    /* renamed from: c */
    private void m376c() {
        this.f247c.execSQL(this.f248d.f228e);
    }

    /* renamed from: d */
    private void m377d() {
        Cursor rawQuery = this.f247c.rawQuery(this.f248d.f226c, null);
        Set hashSet = new HashSet();
        while (rawQuery.moveToNext()) {
            try {
                hashSet.add(rawQuery.getString(0));
            } finally {
                rawQuery.close();
            }
        }
        this.f250f.m348a(hashSet);
    }

    /* renamed from: e */
    private C0222e m378e(C0171e c0171e) {
        return this.f252h.m404a(c0171e, this.f251g);
    }

    /* renamed from: e */
    private void m379e(@NonNull C0229j c0229j) {
        try {
            this.f250f.m347a(c0229j.m447a(), this.f249e.mo354a(c0229j.m465j()));
        } catch (Throwable e) {
            throw new RuntimeException("cannot save job to disk", e);
        }
    }

    /* renamed from: f */
    private boolean m380f(C0229j c0229j) {
        SQLiteDatabase sQLiteDatabase = null;
        SQLiteStatement a = this.f248d.m353a();
        SQLiteStatement b = this.f248d.m357b();
        this.f247c.beginTransaction();
        try {
            a.clearBindings();
            m372a(a, c0229j);
            if ((a.executeInsert() != -1 ? 1 : null) == null) {
                return sQLiteDatabase;
            }
            for (String str : c0229j.m467l()) {
                b.clearBindings();
                m373a(b, c0229j.m447a(), str);
                b.executeInsert();
            }
            this.f247c.setTransactionSuccessful();
            this.f247c.endTransaction();
            return true;
        } catch (Throwable th) {
            C0180b.m232a(th, "error while inserting job with tags", new Object[0]);
            return sQLiteDatabase;
        } finally {
            sQLiteDatabase = this.f247c;
            sQLiteDatabase.endTransaction();
        }
    }

    /* renamed from: g */
    private void m381g(C0229j c0229j) {
        SQLiteStatement g = this.f248d.m362g();
        c0229j.m454b(c0229j.m459d() + 1);
        c0229j.m458c(this.f246b);
        g.clearBindings();
        g.bindLong(1, (long) c0229j.m459d());
        g.bindLong(2, this.f246b);
        g.bindString(3, c0229j.m447a());
        g.execute();
    }

    /* renamed from: a */
    public int mo321a() {
        SQLiteStatement c = this.f248d.m358c();
        c.clearBindings();
        c.bindLong(1, this.f246b);
        return (int) c.simpleQueryForLong();
    }

    /* renamed from: a */
    public int mo322a(@NonNull C0171e c0171e) {
        return (int) m378e(c0171e).m395a(this.f247c, this.f251g).simpleQueryForLong();
    }

    /* renamed from: a */
    public C0229j mo323a(@NonNull String str) {
        C0229j c0229j = null;
        Cursor rawQuery = this.f247c.rawQuery(this.f248d.f224a, new String[]{str});
        try {
            if (rawQuery.moveToFirst()) {
                c0229j = m371a(rawQuery);
                rawQuery.close();
            }
        } catch (Throwable e) {
            C0180b.m232a(e, "invalid job on findJobById", new Object[0]);
        } finally {
            rawQuery.close();
        }
        return c0229j;
    }

    /* renamed from: a */
    public void mo324a(@NonNull C0229j c0229j, @NonNull C0229j c0229j2) {
        this.f247c.beginTransaction();
        try {
            mo330c(c0229j2);
            mo325a(c0229j);
            this.f247c.setTransactionSuccessful();
        } finally {
            this.f247c.endTransaction();
        }
    }

    /* renamed from: a */
    public boolean mo325a(@NonNull C0229j c0229j) {
        m379e(c0229j);
        if (c0229j.m472q()) {
            return m380f(c0229j);
        }
        SQLiteStatement a = this.f248d.m353a();
        a.clearBindings();
        m372a(a, c0229j);
        long executeInsert = a.executeInsert();
        c0229j.m449a(executeInsert);
        return executeInsert != -1;
    }

    /* renamed from: b */
    public C0229j mo326b(@NonNull C0171e c0171e) {
        Cursor rawQuery;
        C0229j a;
        C0222e e = m378e(c0171e);
        String a2 = e.m396a(this.f248d);
        while (true) {
            rawQuery = this.f247c.rawQuery(a2, e.f257c);
            try {
                if (rawQuery.moveToNext()) {
                    a = m371a(rawQuery);
                    m381g(a);
                    break;
                }
                rawQuery.close();
                return null;
            } catch (C0218a e2) {
                String string = rawQuery.getString(C0211a.f197b.f221c);
                if (string == null) {
                    C0180b.m233b("cannot find job id on a retrieved job", new Object[0]);
                } else {
                    m374b(string);
                }
                rawQuery.close();
            } catch (Throwable th) {
                rawQuery.close();
            }
        }
        rawQuery.close();
        return a;
    }

    /* renamed from: b */
    public void mo327b() {
        this.f248d.m364i();
        m377d();
    }

    /* renamed from: b */
    public boolean mo328b(@NonNull C0229j c0229j) {
        if (c0229j.m456c() == null) {
            return mo325a(c0229j);
        }
        m379e(c0229j);
        c0229j.m458c(Long.MIN_VALUE);
        SQLiteStatement d = this.f248d.m359d();
        d.clearBindings();
        m372a(d, c0229j);
        C0180b.m231a("reinsert job result %s", Boolean.valueOf(d.executeInsert() != -1));
        return d.executeInsert() != -1;
    }

    /* renamed from: c */
    public Long mo329c(@NonNull C0171e c0171e) {
        Long l = null;
        try {
            long simpleQueryForLong = m378e(c0171e).m394a(this.f247c, this.f248d).simpleQueryForLong();
            if (simpleQueryForLong != Long.MAX_VALUE) {
                l = Long.valueOf(simpleQueryForLong);
            }
        } catch (SQLiteDoneException e) {
        }
        return l;
    }

    /* renamed from: c */
    public void mo330c(@NonNull C0229j c0229j) {
        m374b(c0229j.m447a());
    }

    @NonNull
    /* renamed from: d */
    public Set<C0229j> mo331d(@NonNull C0171e c0171e) {
        C0222e e = m378e(c0171e);
        Cursor rawQuery = this.f247c.rawQuery(e.m398b(this.f248d), e.f257c);
        Set<C0229j> hashSet = new HashSet();
        while (rawQuery.moveToNext()) {
            try {
                hashSet.add(m371a(rawQuery));
            } catch (Throwable e2) {
                C0180b.m232a(e2, "invalid job found by tags.", new Object[0]);
            } finally {
                rawQuery.close();
            }
        }
        return hashSet;
    }

    /* renamed from: d */
    public void mo332d(C0229j c0229j) {
        SQLiteStatement h = this.f248d.m363h();
        h.clearBindings();
        h.bindString(1, c0229j.m447a());
        h.execute();
    }
}
