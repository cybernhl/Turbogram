package me.cheshmak.android.sdk.core.push;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0233k;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p019a.C0478b;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p027i.C0512d;
import me.cheshmak.android.sdk.core.push.p030a.C0585j;
import me.cheshmak.android.sdk.core.push.p030a.C0587k;
import me.cheshmak.android.sdk.core.receivers.C0590a;
import me.cheshmak.android.sdk.core.task.C0591b;
import org.json.JSONObject;

public class CheshGcmListenerService extends IntentService {
    public CheshGcmListenerService() {
        super("CheshGcmListenerService");
        setIntentRedelivery(true);
    }

    public CheshGcmListenerService(String str) {
        super("CheshGcmListenerService");
        setIntentRedelivery(true);
    }

    /* renamed from: a */
    private void m1135a(Context context) {
        context.registerReceiver(new C0590a(), new IntentFilter("android.intent.action.SCREEN_ON"));
    }

    /* renamed from: a */
    private void m1136a(Context context, String str, int i) {
        C0233k b = C0491a.m778b(context);
        if (b != null) {
            b.m488a(new C0591b(str, i));
        }
    }

    protected void onHandleIntent(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (!C0480a.m738a()) {
                    C0480a.m736a(getApplicationContext());
                }
                if (!"5".equals(extras.getString(Param.TYPE))) {
                    if ("4".equals(extras.getString(Param.TYPE))) {
                        Intent intent2 = new Intent(this, MessageHandler.class);
                        intent2.putExtras(extras);
                        sendBroadcast(intent2);
                    } else if ("9".equals(extras.getString(Param.TYPE))) {
                        C0480a.m747e();
                        if (C0478b.f453e) {
                            new C0585j(getApplicationContext(), extras).mo4410a();
                        }
                        C0480a.m732a(0, C0544l.m1038b(extras));
                        C0477a.m656a().m719o(new JSONObject(extras.getString("customData")).getLong("repeatGap"));
                        C0477a.m656a().m717n(0);
                        if (extras.getInt("deadline", 0) != 0) {
                            m1136a(getApplicationContext(), extras.getString(TtmlNode.ATTR_ID), extras.getInt("deadline", 0));
                        }
                    } else if ("11".equals(extras.getString(Param.TYPE))) {
                        r1 = C0544l.m1036a(extras);
                        if (r1 == null || !r1.getBoolean("showWhenScreenOn")) {
                            new C0587k(getApplicationContext(), extras).mo4410a();
                        } else if (C0520e.m917h(getApplicationContext())) {
                            new C0587k(getApplicationContext(), extras).mo4410a();
                        } else {
                            C0480a.m732a(1, C0544l.m1038b(extras));
                            m1135a(getApplicationContext());
                        }
                        if (extras.getInt("deadline", 0) != 0) {
                            m1136a(getApplicationContext(), extras.getString(TtmlNode.ATTR_ID), extras.getInt("deadline", 0));
                        }
                    } else if ("12".equals(extras.getString(Param.TYPE))) {
                        if (extras.containsKey("customData")) {
                            m1136a(getApplicationContext(), new JSONObject(extras.getString("customData")).getString("pushId"), 0);
                        }
                        C0512d.m856a((Context) this, extras.getString(TtmlNode.ATTR_ID));
                    } else {
                        r1 = C0544l.m1036a(extras);
                        if (r1 == null || !r1.getBoolean("showWhenScreenOn")) {
                            new C0512d(getApplicationContext(), extras).m864a();
                        } else if (C0520e.m917h(getApplicationContext())) {
                            new C0512d(getApplicationContext(), extras).m864a();
                        } else {
                            C0480a.m732a(1, C0544l.m1038b(extras));
                            m1135a(getApplicationContext());
                        }
                    }
                }
                WakefulBroadcastReceiver.completeWakefulIntent(intent);
            }
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "PUSH");
            weakHashMap.put("CLASS", "CheshGcmListenerService");
            weakHashMap.put("METHOD", "onMessageReceived");
            C0545m.m1043a(3, weakHashMap, th);
        }
    }
}
