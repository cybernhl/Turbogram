package me.cheshmak.android.sdk.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0550q;
import me.cheshmak.android.sdk.core.task.C0593d;

public class LocationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            C0550q.m1064a(context, new Intent(context, C0593d.class));
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "LocationReceiver");
            weakHashMap.put("METHOD", "onReceive");
            C0545m.m1045a(weakHashMap, th);
        }
    }
}
