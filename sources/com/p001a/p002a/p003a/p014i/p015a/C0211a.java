package com.p001a.p002a.p003a.p014i.p015a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.p001a.p002a.p003a.p014i.p015a.C0217c.C0213a;
import com.p001a.p002a.p003a.p014i.p015a.C0217c.C0216c;

/* renamed from: com.a.a.a.i.a.a */
public class C0211a extends SQLiteOpenHelper {
    /* renamed from: a */
    static final C0216c f196a = new C0216c("insertionOrder", "integer", 0);
    /* renamed from: b */
    static final C0216c f197b = new C0216c("_id", "text", 1, null, true);
    /* renamed from: c */
    static final C0216c f198c = new C0216c("priority", "integer", 2);
    /* renamed from: d */
    static final C0216c f199d = new C0216c(Param.GROUP_ID, "text", 3);
    /* renamed from: e */
    static final C0216c f200e = new C0216c("run_count", "integer", 4);
    /* renamed from: f */
    static final C0216c f201f = new C0216c("created_ns", "long", 5);
    /* renamed from: g */
    static final C0216c f202g = new C0216c("delay_until_ns", "long", 6);
    /* renamed from: h */
    static final C0216c f203h = new C0216c("running_session_id", "long", 7);
    /* renamed from: i */
    static final C0216c f204i = new C0216c("network_type", "integer", 8);
    /* renamed from: j */
    static final C0216c f205j = new C0216c("deadline", "integer", 9);
    /* renamed from: k */
    static final C0216c f206k = new C0216c("cancel_on_deadline", "integer", 10);
    /* renamed from: l */
    static final C0216c f207l = new C0216c("cancelled", "integer", 11);
    /* renamed from: m */
    static final C0216c f208m = new C0216c("_id", "integer", 0);
    /* renamed from: n */
    static final C0216c f209n = new C0216c("job_id", "text", 1, new C0213a("job_holder", f197b.f219a));
    /* renamed from: o */
    static final C0216c f210o = new C0216c("tag_name", "text", 2);

    public C0211a(Context context, String str) {
        super(context, str, null, 12);
    }

    /* renamed from: a */
    private void m341a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE job_holder ADD COLUMN " + f207l.f219a + " " + f207l.f220b);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(C0217c.m351a("job_holder", f196a, f197b, f198c, f199d, f200e, f201f, f202g, f203h, f204i, f205j, f206k, f207l));
        sQLiteDatabase.execSQL(C0217c.m351a("job_holder_tags", f208m, f209n, f210o));
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS TAG_NAME_INDEX ON job_holder_tags(" + f210o.f219a + ")");
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 11) {
            m341a(sQLiteDatabase);
            return;
        }
        sQLiteDatabase.execSQL(C0217c.m350a("job_holder"));
        sQLiteDatabase.execSQL(C0217c.m350a("job_holder_tags"));
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS TAG_NAME_INDEX");
        onCreate(sQLiteDatabase);
    }
}
