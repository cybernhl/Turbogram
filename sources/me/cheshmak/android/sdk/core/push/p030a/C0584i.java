package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import me.cheshmak.android.sdk.core.p022l.C0550q;

/* renamed from: me.cheshmak.android.sdk.core.push.a.i */
public class C0584i extends C0577h {
    public C0584i(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    public void mo4410a() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 132);
            if (packageInfo.services != null && packageInfo.services.length > 0) {
                int i = 0;
                while (i < packageInfo.services.length) {
                    if (!(packageInfo.services[i] == null || packageInfo.services[i].metaData == null)) {
                        String string = packageInfo.services[i].metaData.getString("cheshmakPush");
                        if (string != null && string.equals("startServiceOnPush")) {
                            Intent intent = new Intent();
                            intent.setClassName(this.c, packageInfo.services[i].name);
                            intent.putExtra("me.cheshmak.data", this.d.getString("customData"));
                            C0550q.m1064a(this.c, intent);
                        }
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
        }
    }
}
