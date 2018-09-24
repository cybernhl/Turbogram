package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.dynamite.ProviderConstants;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import me.cheshmak.android.sdk.core.p022l.C0520e;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import me.cheshmak.android.sdk.core.ui.UpdateDialogActivity;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.j */
public class C0585j extends C0577h {
    public C0585j(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    public void mo4410a() {
        try {
            JSONObject jSONObject = new JSONObject(this.d.getString("customData"));
            String string = jSONObject.getString(ProviderConstants.API_COLNAME_FEATURE_VERSION);
            int f = C0520e.m914f(this.c);
            if (string.startsWith("=")) {
                if (Integer.parseInt(string.replace("=", "")) != f) {
                    return;
                }
            } else if (string.startsWith("<") && Integer.parseInt(string.replace("<", "")) <= f) {
                return;
            }
            C0477a.m656a().m717n(C0516a.m879a());
            Intent intent = new Intent(this.c, UpdateDialogActivity.class);
            intent.putExtra("data", C0544l.m1038b(this.d).toString());
            intent.putExtra("force", jSONObject.getBoolean("force"));
            intent.putExtra("updateMethod", jSONObject.getString("updateMethod"));
            intent.putExtra("url", jSONObject.getString("url"));
            intent.putExtra("markets", jSONObject.optJSONArray("markets").toString());
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        } catch (Throwable th) {
        }
    }
}
