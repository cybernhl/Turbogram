package me.cheshmak.android.sdk.core.p028j;

import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import net.hockeyapp.android.LoginActivity;

/* renamed from: me.cheshmak.android.sdk.core.j.a */
public class C0513a {
    /* renamed from: a */
    private WeakReference<Context> f544a;

    public C0513a(Context context) {
        this.f544a = new WeakReference(context);
    }

    /* renamed from: b */
    private void m866b() {
        Context context = (Context) this.f544a.get();
        if (context != null) {
            Intent intent = new Intent("CHESH_REMOTE_DATA_ACTION");
            intent.putExtra("package_name", context.getPackageName());
            intent.putExtra("data_type", "device_id");
            intent.putExtra("sender", ((Context) this.f544a.get()).getPackageName());
            intent.putExtra(LoginActivity.EXTRA_MODE, "request");
            intent.addFlags(32);
            context.sendBroadcast(intent);
        }
    }

    /* renamed from: a */
    public void m867a() {
        m866b();
        C0477a.m656a().m693d(true);
        C0477a.m656a().m721p(C0516a.m879a());
    }
}
