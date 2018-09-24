package me.cheshmak.android.sdk.core.p024e;

import android.content.Context;
import com.p001a.p002a.p003a.C0233k;
import com.p001a.p002a.p003a.p008c.C0164a.C0163a;

/* renamed from: me.cheshmak.android.sdk.core.e.a */
public class C0491a {
    /* renamed from: a */
    private static C0233k f474a;

    /* renamed from: a */
    public static void m777a(Context context) {
        if (f474a == null) {
            f474a = C0491a.m779c(context);
            f474a.m487a(new C0493c());
        }
    }

    /* renamed from: b */
    public static C0233k m778b(Context context) {
        if (f474a == null) {
            f474a = C0491a.m779c(context);
        }
        return f474a;
    }

    /* renamed from: c */
    private static C0233k m779c(Context context) {
        try {
            return new C0233k(new C0163a(context.getApplicationContext()).m144a(45).m146b(3).m147c(1).m145a());
        } catch (Throwable th) {
            return null;
        }
    }
}
