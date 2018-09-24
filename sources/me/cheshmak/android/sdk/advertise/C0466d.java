package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import android.os.AsyncTask;
import java.util.HashMap;
import java.util.Map;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p026h.C0503b;
import me.cheshmak.android.sdk.core.task.C0594e;

/* renamed from: me.cheshmak.android.sdk.advertise.d */
public class C0466d extends AsyncTask<Context, Void, Void> {
    /* renamed from: a */
    private void m629a(Context context) {
        try {
            C0477a a = C0477a.m656a();
            if (a == null) {
                a = C0477a.m657a(context);
            }
            String a2 = C0465c.m628a(context);
            if (a2 != null && !a2.equals(a.m665H())) {
                a.m699f(a2);
                Map hashMap = new HashMap();
                hashMap.put("advertiseId", a2);
                C0503b c0503b = new C0503b(2, 26, "advertisingId", hashMap);
                c0503b.m820a(Long.valueOf(a.m694e()));
                C0491a.m778b(context).m488a(new C0594e(c0503b.mo4403g().toString()));
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    protected Void m630a(Context... contextArr) {
        m629a(contextArr[0]);
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m630a((Context[]) objArr);
    }
}
