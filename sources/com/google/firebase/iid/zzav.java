package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class zzav {
    private final SharedPreferences zzcz;
    private final zzx zzda;
    @GuardedBy("this")
    private final Map<String, zzy> zzdb;
    private final Context zzv;

    public zzav(Context context) {
        this(context, new zzx());
    }

    private zzav(Context context, zzx zzx) {
        this.zzdb = new ArrayMap();
        this.zzv = context;
        this.zzcz = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzda = zzx;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzv), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    zzak();
                    FirebaseInstanceId.getInstance().zzl();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str = "FirebaseInstanceId";
                    String str2 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
    }

    public final synchronized String zzaj() {
        return this.zzcz.getString("topic_operaion_queue", "");
    }

    public final synchronized void zzf(String str) {
        this.zzcz.edit().putString("topic_operaion_queue", str).apply();
    }

    private final synchronized boolean isEmpty() {
        return this.zzcz.getAll().isEmpty();
    }

    private static String zza(String str, String str2, String str3) {
        return new StringBuilder(((String.valueOf(str).length() + 4) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append("|T|").append(str2).append("|").append(str3).toString();
    }

    static String zzd(String str, String str2) {
        return new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length()).append(str).append("|S|").append(str2).toString();
    }

    public final synchronized void zzak() {
        this.zzdb.clear();
        zzx.zza(this.zzv);
        this.zzcz.edit().clear().commit();
    }

    public final synchronized zzaw zzb(String str, String str2, String str3) {
        return zzaw.zzi(this.zzcz.getString(zza(str, str2, str3), null));
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zza = zzaw.zza(str4, str5, System.currentTimeMillis());
        if (zza != null) {
            Editor edit = this.zzcz.edit();
            edit.putString(zza(str, str2, str3), zza);
            edit.commit();
        }
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        String zza = zza(str, str2, str3);
        Editor edit = this.zzcz.edit();
        edit.remove(zza);
        edit.commit();
    }

    public final synchronized zzy zzg(String str) {
        zzy zzy;
        zzy = (zzy) this.zzdb.get(str);
        if (zzy == null) {
            try {
                zzy = this.zzda.zzb(this.zzv, str);
            } catch (zzz e) {
                Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
                FirebaseInstanceId.getInstance().zzl();
                zzy = this.zzda.zzc(this.zzv, str);
            }
            this.zzdb.put(str, zzy);
        }
        return zzy;
    }

    public final synchronized void zzh(String str) {
        String concat = String.valueOf(str).concat("|T|");
        Editor edit = this.zzcz.edit();
        for (String str2 : this.zzcz.getAll().keySet()) {
            if (str2.startsWith(concat)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }
}
