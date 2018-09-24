package me.cheshmak.android.sdk.core.push;

import android.app.IntentService;
import android.content.Intent;
import com.google.android.gms.iid.InstanceID;
import com.p001a.p002a.p003a.C0233k;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p026h.C0503b;
import me.cheshmak.android.sdk.core.task.C0594e;
import net.hockeyapp.android.FeedbackActivity;

public class CheshmakPushRegistration extends IntentService {
    public CheshmakPushRegistration() {
        super("CheshmakPushRegistration");
    }

    /* renamed from: a */
    private void m1137a() {
        try {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("section", "PUSH");
            weakHashMap.put("CLASS", "CheshmakPushRegistration");
            weakHashMap.put("METHOD", "onHandleIntent");
            weakHashMap.put("MESSAGE", "service_stated");
            C0545m.m1042a(6, weakHashMap);
            weakHashMap.clear();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private void m1138a(String str) {
        try {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("section", "PUSH");
            weakHashMap.put("CLASS", "CheshmakPushRegistration");
            weakHashMap.put("METHOD", "onHandleIntent");
            weakHashMap.put("MESSAGE", "token_is_generated");
            weakHashMap.put(FeedbackActivity.EXTRA_TOKEN, str.charAt(0) + "" + str.charAt(str.length() - 1));
            C0545m.m1042a(6, weakHashMap);
            weakHashMap.clear();
        } catch (Exception e) {
        }
    }

    protected void onHandleIntent(Intent intent) {
        try {
            m1137a();
            String token = InstanceID.getInstance(this).getToken(C0477a.m656a().m720p() + "", "GCM", null);
            m1138a(token);
            if (!token.equals(C0477a.m656a().m718o())) {
                C0477a.m656a().m687c(token);
                Map hashMap = new HashMap();
                hashMap.put("registerationId", token);
                C0503b c0503b = new C0503b(2, 20, "sdk-gcm", hashMap);
                c0503b.m820a(Long.valueOf(C0477a.m656a().m694e()));
                C0233k b = C0491a.m778b(this);
                if (b != null) {
                    b.m488a(new C0594e(c0503b.mo4403g().toString()));
                }
            }
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "PUSH");
            weakHashMap.put("CLASS", "CheshmakPushRegistration");
            weakHashMap.put("METHOD", "onHandleIntent");
            C0545m.m1043a(1, weakHashMap, th);
        }
    }
}
