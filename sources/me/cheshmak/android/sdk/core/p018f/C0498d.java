package me.cheshmak.android.sdk.core.p018f;

import android.content.Context;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p024e.C0492b;
import me.cheshmak.android.sdk.core.receivers.LocationReceiver;
import me.cheshmak.android.sdk.core.task.C0593d;
import me.cheshmak.android.sdk.core.task.WifiTask;

/* renamed from: me.cheshmak.android.sdk.core.f.d */
public class C0498d {
    /* renamed from: a */
    private HashMap<String, Object> f484a;
    /* renamed from: b */
    private WeakReference<Context> f485b;

    public C0498d(Context context) {
        this.f485b = new WeakReference(context);
        if (this.f484a == null) {
            this.f484a = new HashMap();
        }
    }

    /* renamed from: b */
    private void m800b(C0499e c0499e) {
        WeakHashMap weakHashMap;
        try {
            if (Param.LOCATION.equals(c0499e.m802a())) {
                if (C0593d.m1192a((Context) this.f485b.get())) {
                    if (!this.f484a.containsKey(c0499e.m802a())) {
                        this.f484a.put(c0499e.m802a(), new C0593d((Context) this.f485b.get()));
                    }
                    C0593d c0593d = (C0593d) this.f484a.get(c0499e.m802a());
                    if (c0593d.m1195a() == null) {
                        C0516a.m883a((Context) this.f485b.get(), LocationReceiver.class, 231728925, c0499e.m804c());
                        c0593d.m1196a(c0499e);
                    } else if (c0593d.m1195a().m804c() != c0499e.m804c() || c0593d.m1195a().m803b() != c0499e.m803b()) {
                        if (c0499e.m803b() == 0) {
                            C0516a.m882a((Context) this.f485b.get(), LocationReceiver.class, 231728925);
                        } else {
                            C0516a.m884a((Context) this.f485b.get(), LocationReceiver.class, 231728925, Long.valueOf(c0499e.m804c()));
                        }
                        c0593d.m1196a(c0499e);
                    }
                }
            } else if ("log".equals(c0499e.m802a())) {
                if (c0499e.m803b() == 0) {
                    C0545m.m1046a(false);
                    return;
                }
                if (!C0545m.m1047a()) {
                    C0545m.m1044a((Context) this.f485b.get());
                }
                C0545m.m1046a(true);
                C0545m.m1041a(c0499e.m805d());
            } else if ("c".equals(c0499e.m802a())) {
                try {
                    GcmNetworkManager instance = GcmNetworkManager.getInstance((Context) this.f485b.get());
                    if (c0499e.m803b() == 0) {
                        instance.cancelAllTasks(WifiTask.class);
                    } else {
                        instance.schedule(C0492b.m780a(c0499e.m804c()));
                    }
                    C0500f.m808a(c0499e.m804c());
                    C0500f.m807a(c0499e.m803b());
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: a */
    public void m801a(C0499e c0499e) {
        m800b(c0499e);
    }
}
