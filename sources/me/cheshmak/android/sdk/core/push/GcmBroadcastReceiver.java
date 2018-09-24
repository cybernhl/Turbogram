package me.cheshmak.android.sdk.core.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0233k;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.advertise.C0469h;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0518c;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0551r;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p026h.C0503b;
import me.cheshmak.android.sdk.core.task.C0594e;
import me.cheshmak.android.sdk.core.task.C0595f;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    /* renamed from: a */
    static final /* synthetic */ boolean f726a = (!GcmBroadcastReceiver.class.desiredAssertionStatus());

    /* renamed from: a */
    private void m1139a(Context context, String str) {
        try {
            C0233k b = C0491a.m778b(context);
            if (b != null) {
                b.m488a(new C0595f(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE, str));
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private static boolean m1140a(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return false;
        }
        String stringExtra = intent.getStringExtra("message_type");
        return stringExtra == null || GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(stringExtra);
    }

    /* renamed from: a */
    private boolean m1141a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        try {
            String string = bundle.getString(TtmlNode.ATTR_ID);
            String string2 = bundle.getString("hash");
            if (string == null || "".equals(string) || string2 == null || "".equals(string2)) {
                return false;
            }
            String b = C0551r.m1067b(string + C0477a.m656a().m677b());
            string = C0551r.m1067b(string);
            C0519d.m896a("DEBUG_CHESHMAK", "isOurs:" + b.equals(string2));
            return b.equals(string2) || string.equals(string2);
        } catch (Throwable e) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "PUSH");
            weakHashMap.put("CLASS", "GCMBroadcastReceiver");
            weakHashMap.put("METHOD", "isOurs");
            C0545m.m1043a(3, weakHashMap, e);
            C0519d.m896a("DEBUG_CHESHMAK", "isOurs Error: " + e);
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: b */
    private void m1142b(Context context, String str) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("pushId", str);
            hashMap.put(Param.TYPE, GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE);
            C0503b c0503b = new C0503b(2, 22, "gcm-received", hashMap);
            c0503b.m820a(Long.valueOf(C0477a.m656a().m694e()));
            C0233k b = C0491a.m778b(context);
            if (b != null) {
                b.m488a(new C0594e(c0503b.mo4403g().toString()));
            }
        } catch (Exception e) {
            C0519d.m896a("DEBUG_CHESHMAK", "saveDeliveryEvent!!! Error : " + e);
        }
    }

    /* renamed from: b */
    private boolean m1143b(Bundle bundle) {
        boolean z = false;
        if (bundle != null) {
            try {
                CharSequence string = bundle.getString("hash");
                if (!TextUtils.isEmpty(string)) {
                    String b = C0551r.m1067b("ads");
                    C0519d.m896a("DEBUG_CHESHMAK", "isAdvPush:" + b.equals(string));
                    z = b.equals(string);
                }
            } catch (Throwable e) {
                WeakHashMap weakHashMap = new WeakHashMap();
                weakHashMap.put("SECTION", "PUSH");
                weakHashMap.put("CLASS", "GCMBroadcastReceiver");
                weakHashMap.put("METHOD", "isAdvPush");
                C0545m.m1043a(3, weakHashMap, e);
                C0519d.m896a("DEBUG_CHESHMAK", "isAdvPush Error " + e);
            } catch (Throwable th) {
            }
        }
        return z;
    }

    /* renamed from: c */
    private void m1144c(Bundle bundle) {
        try {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "PUSH");
            weakHashMap.put("CLASS", "GcmBroadcastReceiver");
            weakHashMap.put("METHOD", "onReceive");
            weakHashMap.put("MESSAGE", "PUSH_IS_RECEIVED");
            weakHashMap.put("pushId", bundle.getString(TtmlNode.ATTR_ID));
            C0545m.m1042a(6, weakHashMap);
        } catch (Throwable th) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (m1140a(intent)) {
                if (m1141a(extras)) {
                    if (f726a || extras != null) {
                        m1139a(context, intent.getStringExtra(TtmlNode.ATTR_ID));
                        m1144c(extras);
                        m1142b(context, intent.getStringExtra(TtmlNode.ATTR_ID));
                        if ("0".equals(intent.getStringExtra("showType"))) {
                            Bundle bundle = new Bundle();
                            bundle.putString("me.cheshmak.data", extras.getString("customData"));
                            C0518c.m895a(context.getApplicationContext(), extras.getString(Param.TYPE), bundle);
                        } else {
                            Intent intent2 = new Intent();
                            intent2.putExtras(extras);
                            intent2.setComponent(new ComponentName(context.getPackageName(), CheshGcmListenerService.class.getName()));
                            WakefulBroadcastReceiver.startWakefulService(context, intent2);
                        }
                    } else {
                        throw new AssertionError();
                    }
                } else if (m1143b(extras)) {
                    if (f726a || extras != null) {
                        C0469h.m640b(context, extras);
                    } else {
                        throw new AssertionError();
                    }
                }
            }
            setResultCode(-1);
        } catch (Throwable th) {
            C0519d.m898b("DEBUG_CHESHMAK", "onReceive Error : " + th);
        }
    }
}
