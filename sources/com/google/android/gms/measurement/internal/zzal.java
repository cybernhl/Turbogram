package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import java.util.List;

public final class zzal extends zzf {
    private final zzam zzalq = new zzam(this, getContext(), "google_app_measurement_local.db");
    private boolean zzalr;

    zzal(zzbt zzbt) {
        super(zzbt);
    }

    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzgb();
        zzaf();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzgo().zzjl().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zzg("Error resetting local analytics data. error", e);
        }
    }

    @WorkerThread
    private final boolean zza(int i, byte[] bArr) {
        zzgb();
        zzaf();
        if (this.zzalr) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Param.TYPE, Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 5;
        int i3 = 0;
        while (i3 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            Cursor cursor = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zzalr = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                long j = 0;
                cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                if (cursor != null && cursor.moveToFirst()) {
                    j = cursor.getLong(0);
                }
                if (j >= 100000) {
                    zzgo().zzjd().zzbx("Data loss, local db full");
                    j = (100000 - j) + 1;
                    long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j)});
                    if (delete != j) {
                        zzgo().zzjd().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j), Long.valueOf(delete), Long.valueOf(j - delete));
                    }
                }
                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzgo().zzjd().zzg("Error writing entry to local database", e);
                this.zzalr = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i3++;
            } catch (SQLiteDatabaseLockedException e2) {
                SystemClock.sleep((long) i2);
                i2 += 20;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i3++;
            } catch (SQLiteException e3) {
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                }
                zzgo().zzjd().zzg("Error writing entry to local database", e3);
                this.zzalr = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i3++;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzgo().zzjg().zzbx("Failed to write entry to local database");
        return false;
    }

    public final boolean zza(zzad zzad) {
        Parcel obtain = Parcel.obtain();
        zzad.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgo().zzjg().zzbx("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzfh zzfh) {
        Parcel obtain = Parcel.obtain();
        zzfh.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgo().zzjg().zzbx("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzc(zzl zzl) {
        zzgm();
        byte[] zza = zzfk.zza((Parcelable) zzl);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgo().zzjg().zzbx("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final List<AbstractSafeParcelable> zzr(int i) {
        Cursor cursor;
        int i2;
        Throwable th;
        Parcel obtain;
        zzaf();
        zzgb();
        if (this.zzalr) {
            return null;
        }
        List<AbstractSafeParcelable> arrayList = new ArrayList();
        if (!getContext().getDatabasePath("google_app_measurement_local.db").exists()) {
            return arrayList;
        }
        int i3 = 5;
        int i4 = 0;
        while (i4 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            Object e;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        this.zzalr = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return null;
                    } catch (SQLiteFullException e2) {
                        e = e2;
                        cursor = null;
                        sQLiteDatabase = writableDatabase;
                    } catch (SQLiteDatabaseLockedException e3) {
                        cursor = null;
                        sQLiteDatabase = writableDatabase;
                        SystemClock.sleep((long) i3);
                        i2 = i3 + 20;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        i4++;
                        i3 = i2;
                    } catch (SQLiteException e4) {
                        e = e4;
                        cursor = null;
                        sQLiteDatabase = writableDatabase;
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        }
                        zzgo().zzjd().zzg("Error reading entries from local database", e);
                        this.zzalr = true;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                            i2 = i3;
                            i4++;
                            i3 = i2;
                        }
                        i2 = i3;
                        i4++;
                        i3 = i2;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        sQLiteDatabase = writableDatabase;
                    }
                } else {
                    writableDatabase.beginTransaction();
                    cursor = writableDatabase.query("messages", new String[]{"rowid", Param.TYPE, "entry"}, null, null, null, null, "rowid asc", Integer.toString(100));
                    long j = -1;
                    while (cursor.moveToNext()) {
                        try {
                            j = cursor.getLong(0);
                            int i5 = cursor.getInt(1);
                            byte[] blob = cursor.getBlob(2);
                            if (i5 == 0) {
                                Parcel obtain2 = Parcel.obtain();
                                try {
                                    obtain2.unmarshall(blob, 0, blob.length);
                                    obtain2.setDataPosition(0);
                                    zzad zzad = (zzad) zzad.CREATOR.createFromParcel(obtain2);
                                    obtain2.recycle();
                                    if (zzad != null) {
                                        arrayList.add(zzad);
                                    }
                                } catch (ParseException e5) {
                                    zzgo().zzjd().zzbx("Failed to load event from local database");
                                    obtain2.recycle();
                                } catch (Throwable th3) {
                                    obtain2.recycle();
                                    throw th3;
                                }
                            } else if (i5 == 1) {
                                obtain = Parcel.obtain();
                                try {
                                    obtain.unmarshall(blob, 0, blob.length);
                                    obtain.setDataPosition(0);
                                    e = (zzfh) zzfh.CREATOR.createFromParcel(obtain);
                                    obtain.recycle();
                                } catch (ParseException e6) {
                                    zzgo().zzjd().zzbx("Failed to load user property from local database");
                                    obtain.recycle();
                                    e = null;
                                } catch (Throwable th32) {
                                    obtain.recycle();
                                    throw th32;
                                }
                                if (e != null) {
                                    arrayList.add(e);
                                }
                            } else if (i5 == 2) {
                                obtain = Parcel.obtain();
                                try {
                                    obtain.unmarshall(blob, 0, blob.length);
                                    obtain.setDataPosition(0);
                                    e = (zzl) zzl.CREATOR.createFromParcel(obtain);
                                    obtain.recycle();
                                } catch (ParseException e7) {
                                    zzgo().zzjd().zzbx("Failed to load user property from local database");
                                    obtain.recycle();
                                    e = null;
                                } catch (Throwable th322) {
                                    obtain.recycle();
                                    throw th322;
                                }
                                if (e != null) {
                                    arrayList.add(e);
                                }
                            } else {
                                zzgo().zzjd().zzbx("Unknown record type in local database");
                            }
                        } catch (SQLiteFullException e8) {
                            e = e8;
                            sQLiteDatabase = writableDatabase;
                        } catch (SQLiteDatabaseLockedException e9) {
                            sQLiteDatabase = writableDatabase;
                        } catch (SQLiteException e10) {
                            e = e10;
                            sQLiteDatabase = writableDatabase;
                        } catch (Throwable th4) {
                            th322 = th4;
                            sQLiteDatabase = writableDatabase;
                        }
                    }
                    if (writableDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                        zzgo().zzjd().zzbx("Fewer entries removed from local database than expected");
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return arrayList;
                }
            } catch (SQLiteFullException e11) {
                SQLiteFullException sQLiteFullException = e11;
                cursor = null;
                try {
                    zzgo().zzjd().zzg("Error reading entries from local database", e);
                    this.zzalr = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                        i2 = i3;
                        i4++;
                        i3 = i2;
                    }
                    i2 = i3;
                    i4++;
                    i3 = i2;
                } catch (Throwable th5) {
                    th322 = th5;
                }
            } catch (SQLiteDatabaseLockedException e12) {
                cursor = null;
                SystemClock.sleep((long) i3);
                i2 = i3 + 20;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i4++;
                i3 = i2;
            } catch (SQLiteException e13) {
                SQLiteException sQLiteException = e13;
                cursor = null;
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                }
                zzgo().zzjd().zzg("Error reading entries from local database", e);
                this.zzalr = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    i2 = i3;
                    i4++;
                    i3 = i2;
                }
                i2 = i3;
                i4++;
                i3 = i2;
            } catch (Throwable th52) {
                th322 = th52;
                cursor = null;
            }
        }
        zzgo().zzjg().zzbx("Failed to read events from database in reasonable time");
        return null;
        if (cursor != null) {
            cursor.close();
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        throw th322;
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzalr) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzalq.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzalr = true;
        return null;
    }

    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    public final /* bridge */ /* synthetic */ void zzgc() {
        super.zzgc();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zza zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzcs zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzaj zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzdr zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzdo zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzal zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzeq zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
