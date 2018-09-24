package me.cheshmak.android.sdk.core.network;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0545m;

/* renamed from: me.cheshmak.android.sdk.core.network.e */
public class C0560e {
    /* renamed from: a */
    private final WeakReference<Context> f694a;

    public C0560e(Context context) {
        this.f694a = new WeakReference(context);
    }

    /* renamed from: a */
    public void m1099a() {
        try {
            C0571i.m1123a().m1125a((Context) this.f694a.get());
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "NETWORK");
            weakHashMap.put("CLASS", "HandleRequest");
            weakHashMap.put("METHOD", "checkAvailability");
            C0545m.m1042a(6, weakHashMap);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public void m1100a(String str) {
        try {
            C0571i.m1123a().m1126a((Context) this.f694a.get(), str);
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public void m1101b() {
        try {
            C0571i.m1123a().m1127a(C0520e.m912e());
        } catch (Exception e) {
        }
    }
}
