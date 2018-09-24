package me.cheshmak.android.sdk.core.receivers;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.advertise.C0469h;
import me.cheshmak.android.sdk.core.network.EventSendService;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0546n;
import me.cheshmak.android.sdk.core.p022l.C0550q;
import org.json.JSONObject;

public class AlarmReceiver extends BroadcastReceiver {

    /* renamed from: me.cheshmak.android.sdk.core.receivers.AlarmReceiver$a */
    static class C0588a extends AsyncTask<Void, Void, Void> {
        /* renamed from: a */
        WeakReference<Context> f739a;
        /* renamed from: b */
        Intent f740b;

        public C0588a(Context context, Intent intent) {
            this.f739a = new WeakReference(context);
            this.f740b = intent;
        }

        /* renamed from: a */
        protected Void m1171a(Void... voidArr) {
            Context context = (Context) this.f739a.get();
            if (context != null) {
                if (!C0480a.m738a()) {
                    C0480a.m736a(context);
                }
                if (C0546n.m1050c(context.getApplicationContext())) {
                    try {
                        if ("android.intent.action.TIME_SET".equals(this.f740b.getAction())) {
                            C0480a.m742c();
                        }
                        if (C0477a.m656a().m724s()) {
                            Intent intent;
                            if (!C0477a.m656a().m700f()) {
                                intent = new Intent(context, EventSendService.class);
                                intent.putExtra("initiate", false);
                                C0550q.m1064a(context, intent);
                            } else if (C0480a.m744d() == 0) {
                                if (C0477a.m656a().m723r() != C0477a.m656a().m728w()) {
                                    C0516a.m884a(context, AlarmReceiver.class, 231728925, Long.valueOf(C0477a.m656a().m723r()));
                                    C0477a.m656a().m713l(C0477a.m656a().m723r());
                                }
                            } else if (C0480a.m744d() != 0) {
                                intent = new Intent(context, EventSendService.class);
                                intent.putExtra("sendEvent", true);
                                C0550q.m1064a(context, intent);
                            }
                        }
                    } catch (Throwable e) {
                        WeakHashMap weakHashMap = new WeakHashMap();
                        weakHashMap.put("SECTION", "SERVICE");
                        weakHashMap.put("CLASS", "AlarmReceiver");
                        weakHashMap.put("METHOD", "AsycTask");
                        C0545m.m1045a(weakHashMap, e);
                    } catch (Throwable th) {
                    }
                }
            }
            return null;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1171a((Void[]) objArr);
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.hasExtra("advScheduledPush")) {
                C0519d.m898b("advScheduledPush", "mikhad namayesh bede !!!");
                String stringExtra = intent.getStringExtra("adsType");
                JSONObject jSONObject = new JSONObject(intent.getStringExtra("data"));
                C0519d.m896a("AlarmReceiver", "data : " + jSONObject.toString());
                if (!jSONObject.getJSONObject("data").getJSONObject("setting").getBoolean("showWhenScreenOn")) {
                    C0469h.m639a(context, stringExtra, jSONObject, jSONObject.getString("params"));
                    return;
                } else if (C0520e.m917h(context)) {
                    C0469h.m639a(context, stringExtra, jSONObject, jSONObject.getString("params"));
                    return;
                } else {
                    context.registerReceiver(new C0590a(), new IntentFilter("android.intent.action.SCREEN_ON"));
                    return;
                }
            }
            if (C0477a.m656a() == null) {
                C0477a.m657a(context.getApplicationContext());
            }
            new C0588a(context, intent).execute(new Void[0]);
        } catch (Throwable th) {
        }
    }
}
