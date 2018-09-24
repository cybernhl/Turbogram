package me.cheshmak.android.sdk.core.p020b;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.b.a */
public class C0480a {
    /* renamed from: a */
    private static boolean f454a = true;
    /* renamed from: b */
    private static final String[] f455b = new String[]{NotificationCompat.CATEGORY_EVENT, "push"};
    /* renamed from: c */
    private static C0479a f456c = null;
    /* renamed from: d */
    private static int f457d = 0;
    /* renamed from: e */
    private static SQLiteDatabase f458e;

    /* renamed from: me.cheshmak.android.sdk.core.b.a$a */
    private static class C0479a extends SQLiteOpenHelper {
        public C0479a(Context context) {
            super(context, "cheshdb", null, 17);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("create table event( _id integer primary key autoincrement,text text, retry_count integer );");
                sQLiteDatabase.execSQL("create table push( _id text ,type integer,text text );");
            } catch (Throwable th) {
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                for (String str : C0480a.f455b) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
                onCreate(sQLiteDatabase);
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public static long m732a(int i, JSONObject jSONObject) {
        long j = -1;
        try {
            SQLiteDatabase i2 = C0480a.m752i();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", C0480a.m748f(jSONObject.getString(TtmlNode.ATTR_ID)));
            contentValues.put(Param.TYPE, Integer.valueOf(i));
            contentValues.put("text", C0480a.m748f(jSONObject.toString()));
            j = i2.insert("push", null, contentValues);
        } catch (Throwable th) {
        } finally {
            C0480a.m739b();
        }
        return j;
    }

    /* renamed from: a */
    public static long m733a(String str) {
        return f454a ? C0480a.m734a(str, 2) : 0;
    }

    /* renamed from: a */
    public static long m734a(String str, int i) {
        long j = -1;
        if (C0544l.m1037a(str)) {
            try {
                SQLiteDatabase i2 = C0480a.m752i();
                String f = C0480a.m748f(str);
                ContentValues contentValues = new ContentValues();
                contentValues.put("text", f);
                contentValues.put("retry_count", Integer.valueOf(i));
                j = i2.insert(NotificationCompat.CATEGORY_EVENT, null, contentValues);
            } catch (Throwable th) {
            } finally {
                C0480a.m739b();
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static java.util.List<me.cheshmak.android.sdk.core.p026h.C0504c> m735a(java.lang.Long r12) {
        /*
        r2 = new java.util.ArrayList;
        r2.<init>();
        r1 = "";
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r0.<init>();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r3 = "SELECT * FROM event order by _id asc limit ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r0 = r0.append(r12);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r1 = r0.toString();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r0 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r3 = 0;
        r3 = r0.rawQuery(r1, r3);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r4 = r3.moveToFirst();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        if (r4 == 0) goto L_0x0063;
    L_0x002b:
        r4 = new me.cheshmak.android.sdk.core.h.c;	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r4.<init>();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r5 = 0;
        r6 = r3.getLong(r5);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r4.m829a(r6);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r4.m830a(r5);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r5 = 2;
        r6 = r3.getLong(r5);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r8 = 0;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 != 0) goto L_0x006a;
    L_0x004b:
        r4 = "event";
        r5 = "retry_count= ?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r7 = 0;
        r8 = "0";
        r6[r7] = r8;	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r0.delete(r4, r5, r6);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
    L_0x005d:
        r4 = r3.moveToNext();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        if (r4 != 0) goto L_0x002b;
    L_0x0063:
        r3.close();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0069:
        return r2;
    L_0x006a:
        r5 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r5.<init>();	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r8 = "retry_count";
        r10 = 1;
        r6 = r6 - r10;
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r5.put(r8, r6);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r6 = "event";
        r7 = "_id = ?";
        r8 = 1;
        r8 = new java.lang.String[r8];	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r9 = 0;
        r10 = 0;
        r10 = r3.getLong(r10);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r10 = java.lang.String.valueOf(r10);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r0.update(r6, r5, r7, r8);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        r2.add(r4);	 Catch:{ Throwable -> 0x0098, all -> 0x00c7 }
        goto L_0x005d;
    L_0x0098:
        r0 = move-exception;
        r3 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r3.<init>();	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r4 = "SECTION";
        r5 = "DB";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r4 = "CLASS";
        r5 = "DBAdapter";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r4 = "METHOD";
        r5 = "getTopEvent";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r4 = "query";
        r3.put(r4, r1);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
        r1 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r1, r3, r0);	 Catch:{ Throwable -> 0x00cc, all -> 0x00c7 }
    L_0x00c3:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x0069;
    L_0x00c7:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x00cc:
        r0 = move-exception;
        goto L_0x00c3;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.a(java.lang.Long):java.util.List<me.cheshmak.android.sdk.core.h.c>");
    }

    /* renamed from: a */
    public static void m736a(Context context) {
        try {
            if (f456c == null) {
                f456c = new C0479a(context);
            }
        } catch (Throwable e) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "DB");
            weakHashMap.put("CLASS", "DBAdapter");
            weakHashMap.put("METHOD", "init");
            C0545m.m1043a(3, weakHashMap, e);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m737a(boolean z) {
        f454a = z;
    }

    /* renamed from: a */
    public static boolean m738a() {
        return f456c != null;
    }

    /* renamed from: b */
    public static synchronized void m739b() {
        synchronized (C0480a.class) {
            try {
                f457d--;
                if (f457d == 0) {
                    f458e.close();
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public static boolean m740b(java.lang.Long r6) {
        /*
        r0 = 0;
        r2 = "";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r1.<init>();	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r3 = "delete from event where _id in (select _id from event order by _id asc LIMIT ";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r1 = r1.append(r6);	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r3 = ");";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r2 = r1.toString();	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r1 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r1.execSQL(r2);	 Catch:{ Exception -> 0x002b, all -> 0x005a }
        r0 = 1;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x002a:
        return r0;
    L_0x002b:
        r1 = move-exception;
        r3 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r3.<init>();	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r4 = "SECTION";
        r5 = "DB";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r4 = "CLASS";
        r5 = "DBAdapter";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r4 = "METHOD";
        r5 = "deleteTopEventRecord";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r4 = "query";
        r3.put(r4, r2);	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
        r2 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r2, r3, r1);	 Catch:{ Throwable -> 0x005f, all -> 0x005a }
    L_0x0056:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x002a;
    L_0x005a:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x005f:
        r1 = move-exception;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.b(java.lang.Long):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public static boolean m741b(java.lang.String r6) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x001a, all -> 0x0044 }
        r3 = "DELETE FROM rows WHERE ids IN (%s);";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x001a, all -> 0x0044 }
        r5 = 0;
        r4[r5] = r6;	 Catch:{ Throwable -> 0x001a, all -> 0x0044 }
        r3 = java.lang.String.format(r3, r4);	 Catch:{ Throwable -> 0x001a, all -> 0x0044 }
        r2.execSQL(r3);	 Catch:{ Throwable -> 0x001a, all -> 0x0044 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
        r2.<init>();	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
        r3 = "METHOD";
        r4 = "deleteEventsByIds";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r0);	 Catch:{ Throwable -> 0x0049, all -> 0x0044 }
    L_0x003f:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        r0 = r1;
        goto L_0x0019;
    L_0x0044:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x0049:
        r0 = move-exception;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.b(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    public static boolean m742c() {
        /*
        r1 = 0;
        r0 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x000f, all -> 0x0038 }
        r2 = "delete from event";
        r0.execSQL(r2);	 Catch:{ Throwable -> 0x000f, all -> 0x0038 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x000e:
        return r1;
    L_0x000f:
        r0 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
        r2.<init>();	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
        r3 = "METHOD";
        r4 = "alterEvents";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r0);	 Catch:{ Throwable -> 0x003d, all -> 0x0038 }
    L_0x0034:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x000e;
    L_0x0038:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.c():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    public static boolean m743c(java.lang.String r7) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x0019, all -> 0x0043 }
        r3 = "push";
        r4 = "_id= ?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Throwable -> 0x0019, all -> 0x0043 }
        r6 = 0;
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0019, all -> 0x0043 }
        r2.delete(r3, r4, r5);	 Catch:{ Throwable -> 0x0019, all -> 0x0043 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r2.<init>();	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r3 = "METHOD";
        r4 = "deletePushMessageById";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r0);	 Catch:{ Throwable -> 0x0048, all -> 0x0043 }
    L_0x003e:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        r0 = r1;
        goto L_0x0018;
    L_0x0043:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x0048:
        r0 = move-exception;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.c(java.lang.String):boolean");
    }

    /* renamed from: d */
    public static long m744d() {
        long j = 0;
        try {
            j = C0480a.m752i().compileStatement("select count(*) from event").simpleQueryForLong();
        } catch (Exception e) {
        } finally {
            C0480a.m739b();
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    public static long m745d(java.lang.String r7) {
        /*
        r3 = "";
        r0 = 0;
        r2 = 0;
        r4 = "SELECT * FROM push";
        r5 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r6 = 0;
        r4 = r5.rawQuery(r4, r6);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r5 = r4.moveToFirst();	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        if (r5 == 0) goto L_0x0030;
    L_0x0018:
        r3 = 2;
        r3 = r4.getString(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r5 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r5.<init>(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r6 = "id";
        r5 = r5.getString(r6);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r5 = r5.equals(r7);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        if (r5 == 0) goto L_0x0053;
    L_0x002f:
        r2 = 1;
    L_0x0030:
        r4.close();	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        if (r2 == 0) goto L_0x004f;
    L_0x0035:
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r3 = "data";
        r2 = r2.getJSONObject(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r3 = "setting";
        r2 = r2.getJSONObject(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        r3 = "showTime";
        r0 = r2.getLong(r3);	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
    L_0x004f:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0052:
        return r0;
    L_0x0053:
        r5 = r4.moveToNext();	 Catch:{ Throwable -> 0x005a, all -> 0x0083 }
        if (r5 != 0) goto L_0x0018;
    L_0x0059:
        goto L_0x0030;
    L_0x005a:
        r2 = move-exception;
        r3 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
        r3.<init>();	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
        r4 = "SECTION";
        r5 = "DB";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
        r4 = "CLASS";
        r5 = "DBAdapter";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
        r4 = "METHOD";
        r5 = "getScheduledTimeById";
        r3.put(r4, r5);	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
        r4 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r4, r3, r2);	 Catch:{ Throwable -> 0x0088, all -> 0x0083 }
    L_0x007f:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x0052;
    L_0x0083:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x0088:
        r2 = move-exception;
        goto L_0x007f;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.d(java.lang.String):long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: e */
    public static int m746e(java.lang.String r5) {
        /*
        r0 = 0;
        r2 = "";
        r1 = "SELECT * FROM push";
        r3 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r4 = 0;
        r3 = r3.rawQuery(r1, r4);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r1 = r3.moveToFirst();	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        if (r1 == 0) goto L_0x0049;
    L_0x0016:
        r1 = 2;
        r2 = r3.getString(r1);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r4 = "id";
        r1 = r1.getString(r4);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r1 = r1.equals(r5);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        if (r1 == 0) goto L_0x0043;
    L_0x002d:
        r1 = 1;
    L_0x002e:
        r3.close();	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        if (r1 == 0) goto L_0x003f;
    L_0x0033:
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        r2 = "scheduleID";
        r0 = r1.getInt(r2);	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
    L_0x003f:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0042:
        return r0;
    L_0x0043:
        r1 = r3.moveToNext();	 Catch:{ Throwable -> 0x004b, all -> 0x0074 }
        if (r1 != 0) goto L_0x0016;
    L_0x0049:
        r1 = r0;
        goto L_0x002e;
    L_0x004b:
        r1 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
        r2.<init>();	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
        r3 = "METHOD";
        r4 = "getScheduledTimeById";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r1);	 Catch:{ Throwable -> 0x0079, all -> 0x0074 }
    L_0x0070:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x0042;
    L_0x0074:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x0079:
        r1 = move-exception;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.e(java.lang.String):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: e */
    public static boolean m747e() {
        /*
        r0 = 0;
        r1 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x0014, all -> 0x003d }
        r2 = "push";
        r3 = "type= 0";
        r4 = 0;
        r1.delete(r2, r3, r4);	 Catch:{ Throwable -> 0x0014, all -> 0x003d }
        r0 = 1;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x0013:
        return r0;
    L_0x0014:
        r1 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
        r2.<init>();	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
        r3 = "METHOD";
        r4 = "deletePushMessageById";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r1);	 Catch:{ Throwable -> 0x0042, all -> 0x003d }
    L_0x0039:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x0013;
    L_0x003d:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x0042:
        r1 = move-exception;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.e():boolean");
    }

    /* renamed from: f */
    private static String m748f(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        str2 = DatabaseUtils.sqlEscapeString(str);
        return str2.substring(1, str2.length() - 1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: f */
    public static java.util.List<java.lang.String> m749f() {
        /*
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = "SELECT  * FROM push where type = 0";
        r2 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r3 = 0;
        r0 = r2.rawQuery(r0, r3);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r2 = r0.moveToFirst();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        if (r2 == 0) goto L_0x0025;
    L_0x0017:
        r2 = 2;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r1.add(r2);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r2 = r0.moveToNext();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        if (r2 != 0) goto L_0x0017;
    L_0x0025:
        r0.close();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x002b:
        return r1;
    L_0x002c:
        r0 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r2.<init>();	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "METHOD";
        r4 = "getAllPushMessage";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r0);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
    L_0x0051:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x002b;
    L_0x0055:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x005a:
        r0 = move-exception;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.f():java.util.List<java.lang.String>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: g */
    public static java.util.List<java.lang.String> m750g() {
        /*
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = "SELECT  * FROM push where type = 1";
        r2 = me.cheshmak.android.sdk.core.p020b.C0480a.m752i();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r3 = 0;
        r0 = r2.rawQuery(r0, r3);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r2 = r0.moveToFirst();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        if (r2 == 0) goto L_0x0025;
    L_0x0017:
        r2 = 2;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r1.add(r2);	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        r2 = r0.moveToNext();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        if (r2 != 0) goto L_0x0017;
    L_0x0025:
        r0.close();	 Catch:{ Throwable -> 0x002c, all -> 0x0055 }
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
    L_0x002b:
        return r1;
    L_0x002c:
        r0 = move-exception;
        r2 = new java.util.WeakHashMap;	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r2.<init>();	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "SECTION";
        r4 = "DB";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "CLASS";
        r4 = "DBAdapter";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = "METHOD";
        r4 = "getAllPushMessage";
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
        r3 = 7;
        me.cheshmak.android.sdk.core.p022l.C0545m.m1043a(r3, r2, r0);	 Catch:{ Throwable -> 0x005a, all -> 0x0055 }
    L_0x0051:
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        goto L_0x002b;
    L_0x0055:
        r0 = move-exception;
        me.cheshmak.android.sdk.core.p020b.C0480a.m739b();
        throw r0;
    L_0x005a:
        r0 = move-exception;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.cheshmak.android.sdk.core.b.a.g():java.util.List<java.lang.String>");
    }

    /* renamed from: i */
    private static synchronized SQLiteDatabase m752i() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (C0480a.class) {
            if (f457d == 0) {
                f458e = f456c.getWritableDatabase();
            }
            f457d++;
            sQLiteDatabase = f458e;
        }
        return sQLiteDatabase;
    }
}
