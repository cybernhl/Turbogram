package me.cheshmak.android.sdk.core.push;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.p001a.p002a.p003a.C0233k;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.advertise.C0468g;
import me.cheshmak.android.sdk.advertise.CheshmakAds;
import me.cheshmak.android.sdk.advertise.p017a.C0460a;
import me.cheshmak.android.sdk.core.network.C0558c;
import me.cheshmak.android.sdk.core.network.C0571i;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0517b;
import me.cheshmak.android.sdk.core.p022l.C0518c;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p026h.C0503b;
import me.cheshmak.android.sdk.core.task.C0594e;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageHandler extends BroadcastReceiver {
    /* renamed from: a */
    private Context f730a;

    /* renamed from: a */
    private void m1146a(String str) {
        try {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "PUSH");
            weakHashMap.put("CLASS", "MessageHandler");
            weakHashMap.put("METHOD", "onReceive");
            weakHashMap.put("MESSAGE", "PUSH_IS_OPEN");
            weakHashMap.put("pushId", str);
            C0545m.m1042a(6, weakHashMap);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private void m1147a(String str, String str2) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("pushId", str);
            hashMap.put("button", str2);
            C0503b c0503b = new C0503b(2, 21, "gcm-open", hashMap);
            c0503b.m820a(Long.valueOf(C0477a.m656a().m694e()));
            C0233k b = C0491a.m778b(this.f730a);
            if (b != null) {
                b.m488a(new C0594e(c0503b.mo4403g().toString()));
            }
        } catch (Throwable th) {
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public void onReceive(final Context context, final Intent intent) {
        this.f730a = context;
        C0517b.m893a(new AsyncTask<Object, Object, Object>(this) {
            /* renamed from: c */
            final /* synthetic */ MessageHandler f729c;

            protected Object doInBackground(Object... objArr) {
                try {
                    if (intent.getExtras().getBoolean("isAdvertise")) {
                        if (CheshmakAds.isAdsEnabled()) {
                            if (intent.getExtras().getString("params") != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("appKey", C0477a.m656a().m677b());
                                jSONObject.put("params", intent.getExtras().getString("params"));
                                jSONObject.put("deviceId", C0477a.m656a().m684c());
                                jSONObject.put("options", new JSONObject());
                                C0233k b = C0491a.m778b(context);
                                if (b != null) {
                                    b.m488a(new C0460a(C0468g.m634b("click"), jSONObject.toString()));
                                }
                            }
                        }
                        return null;
                    } else if (!"4".equals(intent.getExtras().getString(Param.TYPE))) {
                        String string = intent.getExtras().getString("pushId");
                        this.f729c.m1147a(string, intent.getExtras().getString("button"));
                        this.f729c.m1146a(string);
                        C0571i.m1123a().m1128a(string, "{}");
                    }
                    C0518c.m895a(this.f729c.f730a, intent.getExtras().getString(Param.TYPE), intent.getExtras());
                    if (intent.hasExtra("loadInParallel") && !"".equals(intent.getStringExtra("loadInParallel"))) {
                        new C0558c(new JSONArray(intent.getStringExtra("loadInParallel"))).m1095a();
                    }
                } catch (Throwable th) {
                }
                return null;
            }
        }, new Object[0]);
    }
}
