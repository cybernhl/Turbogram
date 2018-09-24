package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import me.cheshmak.android.sdk.advertise.DialogActivity;
import me.cheshmak.android.sdk.core.network.C0571i;
import me.cheshmak.android.sdk.core.p022l.C0544l;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.k */
public class C0587k extends C0577h {

    /* renamed from: me.cheshmak.android.sdk.core.push.a.k$1 */
    class C05861 extends C0576b {
        /* renamed from: a */
        final /* synthetic */ C0587k f738a;

        C05861(C0587k c0587k) {
            this.f738a = c0587k;
        }

        /* renamed from: a */
        public void mo4411a(Exception exception) {
        }

        /* renamed from: a */
        public void mo4412a(String str) {
            this.f738a.d.putString("html", str);
            Intent intent = new Intent(this.f738a.c, DialogActivity.class);
            intent.putExtra("pushId", this.f738a.d.getString(TtmlNode.ATTR_ID));
            intent.putExtra("data", C0544l.m1038b(this.f738a.d).toString());
            intent.addFlags(268468224);
            this.f738a.c.startActivity(intent);
        }
    }

    public C0587k(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    public void mo4410a() {
        String str = null;
        try {
            str = new JSONObject(this.d.getString("customData")).optString("url", null);
        } catch (Exception e) {
        }
        if (str != null) {
            try {
                Intent intent = new Intent(this.c, DialogActivity.class);
                intent.putExtra("data", C0544l.m1038b(this.d).toString());
                intent.putExtra("pushId", this.d.getString(TtmlNode.ATTR_ID));
                intent.addFlags(268468224);
                this.c.startActivity(intent);
                return;
            } catch (Throwable th) {
                return;
            }
        }
        C0571i.m1123a().m1129a(this.d.getString(TtmlNode.ATTR_ID), new C05861(this));
    }
}
