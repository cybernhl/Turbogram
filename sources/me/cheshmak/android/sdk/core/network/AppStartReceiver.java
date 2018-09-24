package me.cheshmak.android.sdk.core.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0518c;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.receivers.C0590a;
import org.json.JSONObject;

public class AppStartReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            C0518c.m894a(context.getApplicationContext());
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "AlarmReceiver");
            weakHashMap.put("METHOD", "onReceive");
            weakHashMap.put("action", "REBOOT");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Param.TYPE, "reboot");
            jSONObject.put(Param.TIMESTAMP, C0516a.m879a());
            C0477a.m656a().m673a(jSONObject);
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                weakHashMap.put("MESSAGE", "BOOT_COMPLETED");
            } else {
                weakHashMap.put("MESSAGE", "QUICKBOOT_POWERON");
            }
            C0545m.m1042a(6, weakHashMap);
            Intent intent2 = new Intent("me.cheshmak.push.action.BOOT_COMPLETED");
            intent2.setClass(context, C0590a.class);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
        } catch (Throwable th) {
        }
    }
}
