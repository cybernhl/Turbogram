package me.cheshmak.android.sdk.core.p029k;

import java.util.HashMap;
import java.util.Map;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p026h.C0502a;
import me.cheshmak.android.sdk.core.p026h.C0503b;

/* renamed from: me.cheshmak.android.sdk.core.k.a */
class C0514a implements Runnable {
    /* renamed from: a */
    private final C0502a f545a;

    C0514a(C0502a c0502a) {
        this.f545a = c0502a;
    }

    public void run() {
        try {
            if (this.f545a.m822c() == 13 || this.f545a.m822c() == 1) {
                long h = C0477a.m656a().m704h();
                long f = this.f545a.m825f() - h;
                if (this.f545a.m822c() == 13) {
                    this.f545a.m820a(Long.valueOf(C0477a.m656a().m694e()));
                    C0480a.m733a(this.f545a.mo4403g().toString());
                }
                if (h != 0 && f > 4) {
                    Map hashMap = new HashMap();
                    hashMap.put("duration", f + "");
                    C0503b c0503b = new C0503b(2, 14, "", hashMap);
                    c0503b.m820a(Long.valueOf(C0477a.m656a().m694e()));
                    C0480a.m733a(c0503b.mo4403g().toString());
                    C0477a.m656a().m689d();
                }
                C0477a.m656a().m679b(0);
            } else {
                this.f545a.m820a(Long.valueOf(C0477a.m656a().m694e()));
                C0480a.m733a(this.f545a.mo4403g().toString());
            }
            if (!C0516a.m889a(this.f545a.m825f())) {
            }
        } catch (Throwable th) {
        }
    }
}
