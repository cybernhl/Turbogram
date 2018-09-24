package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.concurrent.TimeUnit;
import net.hockeyapp.android.FeedbackActivity;
import org.json.JSONException;
import org.json.JSONObject;

final class zzaw {
    private static final long zzdc = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzbn;
    private final String zzdd;

    private zzaw(String str, String str2, long j) {
        this.zzbn = str;
        this.zzdd = str2;
        this.timestamp = j;
    }

    static zzaw zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith("{")) {
            return new zzaw(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzaw(jSONObject.getString(FeedbackActivity.EXTRA_TOKEN), jSONObject.getString("appVersion"), jSONObject.getLong(Param.TIMESTAMP));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to parse token: ").append(valueOf).toString());
            return null;
        }
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(FeedbackActivity.EXTRA_TOKEN, str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(Param.TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to encode token: ").append(valueOf).toString());
            return null;
        }
    }

    static String zza(@Nullable zzaw zzaw) {
        if (zzaw == null) {
            return null;
        }
        return zzaw.zzbn;
    }

    final boolean zzj(String str) {
        return System.currentTimeMillis() > this.timestamp + zzdc || !str.equals(this.zzdd);
    }
}
