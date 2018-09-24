package me.cheshmak.android.sdk.core.p022l;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p001a.p002a.p003a.C0233k;
import me.cheshmak.android.sdk.advertise.C0468g;
import me.cheshmak.android.sdk.advertise.p017a.C0460a;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.f */
public class C0521f {
    /* renamed from: a */
    public static void m920a(String str, Context context, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("appKey", C0477a.m656a().m677b());
            jSONObject2.put("deviceId", C0477a.m656a().m684c());
            jSONObject2.put("eventType", str);
            jSONObject2.put("options", jSONObject);
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
        C0233k b = C0491a.m778b(context);
        if (b != null) {
            b.m488a(new C0460a(C0468g.m634b("dialogEvents"), jSONObject2.toString()));
        }
    }
}
