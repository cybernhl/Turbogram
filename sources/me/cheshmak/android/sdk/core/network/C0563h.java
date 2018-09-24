package me.cheshmak.android.sdk.core.network;

import android.content.Context;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0522g;
import me.cheshmak.android.sdk.core.p022l.C0545m;

/* renamed from: me.cheshmak.android.sdk.core.network.h */
public class C0563h extends AsyncTask<Void, Void, Void> {
    /* renamed from: a */
    private final WeakReference<Context> f696a;

    C0563h(Context context) {
        this.f696a = new WeakReference(context);
    }

    /* renamed from: a */
    protected Void m1109a(Void... voidArr) {
        try {
            List a = C0480a.m735a(Long.valueOf(C0477a.m656a().m706i()));
            if (a.size() > 0) {
                new C0560e((Context) this.f696a.get()).m1100a(C0522g.m921a(a));
                C0477a.m656a().m672a(C0522g.m923b(a));
            } else if (C0516a.m879a() - C0477a.m656a().m701g() > C0477a.m656a().m723r()) {
                C0477a.m656a().m670a(C0516a.m879a());
                new C0560e((Context) this.f696a.get()).m1099a();
            }
        } catch (Throwable e) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "JobTask");
            weakHashMap.put("METHOD", "doInBackground");
            C0545m.m1043a(3, weakHashMap, e);
        }
        return null;
    }

    /* renamed from: a */
    protected void m1110a(Void voidR) {
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1109a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1110a((Void) obj);
    }
}
