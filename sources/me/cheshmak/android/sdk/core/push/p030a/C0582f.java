package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.f */
public class C0582f extends C0577h {
    /* renamed from: a */
    private final boolean f737a;

    public C0582f(Context context, Bundle bundle, boolean z) {
        super(context, bundle);
        this.f737a = z;
    }

    /* renamed from: e */
    private void m1160e() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 129);
            if (packageInfo.activities != null && packageInfo.activities.length > 0) {
                int i = 0;
                Object obj = null;
                while (i < packageInfo.activities.length && r0 == null) {
                    if (!(packageInfo.activities[i] == null || packageInfo.activities[i].metaData == null)) {
                        String string = packageInfo.activities[i].metaData.getString("cheshmakPush");
                        if (string != null && string.equals("openActivityOnPush")) {
                            obj = 1;
                            Intent intent = new Intent();
                            intent.setClassName(this.c, packageInfo.activities[i].name);
                            intent.addFlags(268435456);
                            intent.putExtra("me.cheshmak.data", this.d.getString("me.cheshmak.data"));
                            intent.putExtra("title", this.d.getString("title"));
                            intent.putExtra("message", this.d.getString("shortMessage"));
                            this.c.startActivity(intent);
                        }
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo4410a() {
        if (this.f737a) {
            m1160e();
        } else {
            m1162b();
        }
    }

    /* renamed from: b */
    public void m1162b() {
        try {
            JSONObject jSONObject = new JSONObject(this.d.getString("me.cheshmak.data"));
            CharSequence optString = jSONObject.optString("name", null);
            String optString2 = jSONObject.optString("data", null);
            if (optString != null) {
                for (ActivityInfo activityInfo : Arrays.asList(this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 1).activities)) {
                    if (activityInfo.name.contains(optString)) {
                        Intent intent = new Intent(this.c, Class.forName(activityInfo.name));
                        intent.addFlags(268435456);
                        if (optString2 != null) {
                            intent.putExtra("me.cheshmak.data", optString2);
                        }
                        this.c.startActivity(intent);
                    }
                }
            }
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }
}
