package me.cheshmak.android.sdk.core.network;

import android.content.Context;
import android.content.Intent;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.network.C0555a.C0461b;
import me.cheshmak.android.sdk.core.p018f.C0497c;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p019a.C0478b;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.receivers.AlarmReceiver;

/* renamed from: me.cheshmak.android.sdk.core.network.g */
public class C0562g implements C0461b {
    /* renamed from: a */
    private void m1105a(Context context) {
        C0477a.m656a().m691d((C0477a.m656a().m708j() / 1000) * 2);
        C0516a.m884a(context, AlarmReceiver.class, 231728925, Long.valueOf(C0477a.m656a().m708j()));
    }

    /* renamed from: a */
    public void mo4390a(int i, String str) {
    }

    /* renamed from: a */
    public void mo4391a(Exception exception) {
        m1105a(Cheshmak.applicationContext);
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("SECTION", "NETWORK");
        weakHashMap.put("CLASS", "InitiateRequest");
        weakHashMap.put("METHOD", "onErrorResponse");
        weakHashMap.put("MESSAGE", "Availability is broken");
        C0545m.m1043a(1, weakHashMap, exception);
    }

    /* renamed from: a */
    public void mo4392a(String str) {
        WeakHashMap weakHashMap;
        try {
            C0477a.m656a().m674a(true);
            C0561f c0561f = new C0561f(str);
            Context context = Cheshmak.applicationContext;
            if (c0561f.m1104d() == null) {
                return;
            }
            if (c0561f.m1091b()) {
                if (C0516a.m890a(c0561f.m1104d())) {
                    C0516a.m888a(context, c0561f.m1104d());
                }
                if (c0561f.m1104d().length > 0) {
                    C0552s.m1071a(context, Long.valueOf(c0561f.m1104d()[4]));
                }
                C0477a.m656a().m670a(C0516a.m879a());
                new C0497c().m796a(context);
                C0516a.m880a(c0561f.m1104d()[7], c0561f.m1104d()[6]);
                return;
            }
            WeakHashMap weakHashMap2 = new WeakHashMap();
            weakHashMap2.put("SECTION", "NETWORK");
            weakHashMap2.put("CLASS", "InitiateRequest");
            weakHashMap2.put("METHOD", "onErrorResponse");
            weakHashMap2.put("MESSAGE", str);
            C0545m.m1042a(3, weakHashMap2);
            try {
                c0561f = new C0561f(str);
                if (c0561f.m1092c() == null || !C0478b.f451c.equals(c0561f.m1092c().get(0))) {
                    if (c0561f.m1104d() != null && C0516a.m890a(c0561f.m1104d())) {
                        C0516a.m888a(context, c0561f.m1104d());
                    }
                    m1105a(context);
                    return;
                }
                try {
                    C0477a.m656a().m682b(false);
                    C0516a.m882a(context, AlarmReceiver.class, 231728925);
                    C0480a.m737a(false);
                    context.stopService(new Intent(context, EventSendService.class));
                    weakHashMap2 = new WeakHashMap();
                    weakHashMap2.put("SECTION", "NETWORK");
                    weakHashMap2.put("CLASS", "InitiateRequest");
                    weakHashMap2.put("METHOD", "shutDownSDK");
                    weakHashMap2.put("MESSAGE", "sdk is ShutDown");
                    C0545m.m1042a(1, weakHashMap2);
                } catch (Throwable th) {
                }
            } catch (Throwable e) {
                C0477a.m656a().m691d((C0477a.m656a().m708j() / 1000) * 2);
                C0516a.m884a(context, AlarmReceiver.class, 231728925, Long.valueOf(C0477a.m656a().m708j()));
                weakHashMap = new WeakHashMap();
                weakHashMap.put("SECTION", "NETWORK");
                weakHashMap.put("CLASS", "InitiateRequest");
                weakHashMap.put("METHOD", "onErrorResponse");
                weakHashMap.put("MESSAGE", "initiate is broken");
                C0545m.m1043a(1, weakHashMap, e);
            }
        } catch (Throwable e2) {
            weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "NETWORK");
            weakHashMap.put("CLASS", "InitiateRequest");
            weakHashMap.put("METHOD", "onResponse");
            weakHashMap.put("MESSAGE", "Availability is broken");
            C0545m.m1043a(1, weakHashMap, e2);
        }
    }
}
