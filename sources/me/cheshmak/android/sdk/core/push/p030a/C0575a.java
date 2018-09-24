package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.os.Bundle;
import android.support.media.ExifInterface;

/* renamed from: me.cheshmak.android.sdk.core.push.a.a */
public class C0575a {
    /* renamed from: a */
    private C0577h f731a;

    public C0575a(String str, Context context, Bundle bundle) {
        if ("6".equals(str)) {
            this.f731a = new C0581e(context, bundle);
        } else if (ExifInterface.GPS_MEASUREMENT_3D.equals(str)) {
            this.f731a = new C0583g(context, bundle);
        } else if ("4".equals(str)) {
            this.f731a = new C0584i(context, bundle);
        } else if ("7".equals(str)) {
            this.f731a = new C0582f(context, bundle, false);
        } else if ("8".equals(str)) {
            this.f731a = new C0578c(context, bundle);
        } else if ("10".equals(str)) {
            this.f731a = new C0580d(context, bundle);
        } else {
            this.f731a = new C0582f(context, bundle, true);
        }
    }

    /* renamed from: a */
    public void m1150a() {
        this.f731a.mo4410a();
        this.f731a.m1154c();
        this.f731a.m1155d();
        this.f731a = null;
    }
}
