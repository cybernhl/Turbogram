package me.cheshmak.android.sdk.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.advertise.C0469h;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0519d;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p027i.C0512d;
import me.cheshmak.android.sdk.core.push.p030a.C0587k;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.receivers.a */
public class C0590a extends BroadcastReceiver {

    /* renamed from: me.cheshmak.android.sdk.core.receivers.a$a */
    private static class C0589a extends AsyncTask<Void, Void, Void> {
        /* renamed from: a */
        private final WeakReference<List<String>> f742a;
        /* renamed from: b */
        private final WeakReference<C0590a> f743b;
        /* renamed from: c */
        private WeakReference<Context> f744c;

        C0589a(C0590a c0590a, Context context, List<String> list) {
            this.f744c = new WeakReference(context);
            this.f742a = new WeakReference(list);
            this.f743b = new WeakReference(c0590a);
        }

        /* renamed from: a */
        protected Void m1173a(Void... voidArr) {
            try {
                for (String str : (List) this.f742a.get()) {
                    JSONObject jSONObject = new JSONObject(str);
                    if ("11".equals(jSONObject.optString(Param.TYPE, null))) {
                        new C0587k((Context) this.f744c.get(), C0544l.m1035a(jSONObject)).mo4410a();
                    } else {
                        new C0512d((Context) this.f744c.get(), str).m864a();
                    }
                }
                ((Context) this.f744c.get()).unregisterReceiver((BroadcastReceiver) this.f743b.get());
            } catch (Throwable th) {
                WeakHashMap weakHashMap = new WeakHashMap();
                weakHashMap.put("SECTION", "SERVICE");
                weakHashMap.put("CLASS", "UnlockListener:ShowNotification");
                weakHashMap.put("METHOD", "doInBackground");
                C0545m.m1045a(weakHashMap, th);
            }
            return null;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1173a((Void[]) objArr);
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            C0519d.m896a("UnlockListener", "UnlockListener:onReceive started!!!");
            if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                if (!C0480a.m738a()) {
                    C0480a.m736a(context);
                }
                List g = C0480a.m750g();
                if (g.size() > 0) {
                    Iterator it = g.iterator();
                    while (it.hasNext()) {
                        JSONObject jSONObject = new JSONObject((String) it.next());
                        if (jSONObject.has(Param.TYPE) && "200".equals(jSONObject.optString(Param.TYPE))) {
                            C0469h.m639a(context, jSONObject.getString("adsTyp"), jSONObject, jSONObject.getString("params"));
                            it.remove();
                        }
                    }
                    new C0589a(this, context, g).execute(new Void[0]);
                }
            }
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "UnlockListener");
            weakHashMap.put("METHOD", "onReceive");
            C0545m.m1045a(weakHashMap, th);
        }
    }
}
