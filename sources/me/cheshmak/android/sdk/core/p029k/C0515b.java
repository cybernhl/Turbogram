package me.cheshmak.android.sdk.core.p029k;

import android.support.media.ExifInterface;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0551r;
import me.cheshmak.android.sdk.core.p026h.C0502a;
import me.cheshmak.android.sdk.core.p026h.C0503b;
import me.cheshmak.android.sdk.core.p026h.C0505d;
import me.cheshmak.android.sdk.core.p026h.C0506e;

/* renamed from: me.cheshmak.android.sdk.core.k.b */
public class C0515b {
    /* renamed from: b */
    private static ThreadPoolExecutor f546b;
    /* renamed from: a */
    private HashMap<String, Object> f547a = new HashMap();

    public C0515b() {
        m868a();
    }

    /* renamed from: a */
    private ThreadPoolExecutor m868a() {
        if (f546b == null) {
            f546b = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        }
        return f546b;
    }

    /* renamed from: a */
    private void m869a(C0502a c0502a) {
        f546b.execute(new C0514a(c0502a));
    }

    /* renamed from: a */
    public void m870a(String str) {
        if (str != null) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("CH_EVENT_CATEGORY", ExifInterface.GPS_MEASUREMENT_2D);
                hashMap.put("CH_EVENT_ACTION", "16");
                ArrayList arrayList = new ArrayList();
                arrayList.add(Long.valueOf(C0516a.m879a()));
                arrayList.add(Long.valueOf(C0477a.m656a().m694e()));
                this.f547a.put(str, arrayList);
                m874a(str, hashMap);
                return;
            } catch (Exception e) {
                return;
            }
        }
        Log.e("ERROR_CHESHMAK", "View Label is Null");
    }

    /* renamed from: a */
    public void m871a(String str, Throwable th) {
        m872a(str, th, false);
    }

    /* renamed from: a */
    public void m872a(String str, Throwable th, boolean z) {
        C0502a c0505d;
        if (z) {
            try {
                c0505d = new C0505d(2, 13, str, th);
            } catch (Exception e) {
                return;
            }
        }
        c0505d = new C0505d(2, 15, str, th);
        m869a(c0505d);
    }

    /* renamed from: a */
    public void m873a(String str, List<String> list) {
        try {
            m869a(new C0506e(3, 24, str, list));
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public void m874a(String str, Map<String, String> map) {
        if (str != null) {
            C0502a c0503b;
            if (map != null) {
                try {
                    if (map.containsKey("CH_EVENT_CATEGORY") && map.containsKey("CH_EVENT_ACTION")) {
                        long longValue = Long.valueOf((String) map.get("CH_EVENT_CATEGORY")).longValue();
                        long longValue2 = Long.valueOf((String) map.get("CH_EVENT_ACTION")).longValue();
                        map.remove("CH_EVENT_CATEGORY");
                        map.remove("CH_EVENT_ACTION");
                        c0503b = new C0503b(longValue, longValue2, C0551r.m1066a(str), map);
                        m869a(c0503b);
                    }
                } catch (Exception e) {
                    return;
                }
            }
            c0503b = new C0503b(3, 18, C0551r.m1066a(str), map);
            m869a(c0503b);
        }
    }

    /* renamed from: a */
    public void m875a(Throwable th) {
        m872a("", th, false);
    }

    /* renamed from: a */
    public void m876a(Throwable th, boolean z) {
        m872a("", th, z);
    }

    /* renamed from: a */
    public void m877a(List<String> list) {
        try {
            m869a(new C0506e(3, 23, "tags", list));
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public void m878b(String str) {
        if (str != null) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("CH_EVENT_CATEGORY", ExifInterface.GPS_MEASUREMENT_2D);
                hashMap.put("CH_EVENT_ACTION", "17");
                if (this.f547a.get(str) != null) {
                    ArrayList arrayList = (ArrayList) this.f547a.get(str);
                    if (arrayList.get(1).equals(Long.valueOf(C0477a.m656a().m694e()))) {
                        hashMap.put("duration", (C0516a.m879a() - ((Long) arrayList.get(0)).longValue()) + "");
                        m874a(str, hashMap);
                    }
                    this.f547a.remove(str);
                    return;
                }
                return;
            } catch (Exception e) {
                return;
            }
        }
        Log.e("ERROR_CHESHMAK", "View Label is Null");
    }
}
