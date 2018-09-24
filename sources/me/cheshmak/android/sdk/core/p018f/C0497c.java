package me.cheshmak.android.sdk.core.p018f;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.p001a.p002a.p003a.C0233k;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.task.C0592c;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.f.c */
public class C0497c {
    /* renamed from: a */
    private void m795a(Context context, String str, int i) {
        try {
            C0233k b = C0491a.m778b(context);
            JSONObject a = C0520e.m903a(context, str);
            a.getJSONObject(str).put(NotificationCompat.CATEGORY_STATUS, i);
            b.m488a(new C0592c(1, a.toString()));
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void m796a(Context context) {
        try {
            C0491a.m778b(context).m488a(new C0592c(1, C0520e.m918i(context).toString()));
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void m797a(Context context, String str) {
        C0233k b = C0491a.m778b(context);
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            b.m488a(new C0592c(0, jSONArray.toString()));
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public void m798b(Context context, String str) {
        m795a(context, str, 1);
    }

    /* renamed from: c */
    public void m799c(Context context, String str) {
        m795a(context, str, 2);
    }
}
