package me.cheshmak.android.sdk.core.p022l;

import android.content.Context;
import android.os.Bundle;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.push.p030a.C0575a;

/* renamed from: me.cheshmak.android.sdk.core.l.c */
public class C0518c {
    /* renamed from: a */
    public static void m894a(Context context) {
        C0477a.m657a(context);
        Cheshmak.with(context.getApplicationContext());
        Cheshmak.initTracker(C0477a.m656a().m677b());
        Cheshmak.enableAutoActivityReports(context);
        Cheshmak.enableCheshmakExceptionHandler();
        C0491a.m777a(context);
    }

    /* renamed from: a */
    public static void m895a(Context context, String str, Bundle bundle) {
        try {
            new C0575a(str, context, bundle).m1150a();
        } catch (Throwable th) {
        }
    }
}
