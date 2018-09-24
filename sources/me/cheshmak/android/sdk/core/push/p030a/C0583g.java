package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.g */
public class C0583g extends C0577h {
    public C0583g(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    private void m1163a(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m1164b(String str) {
        try {
            C0552s.m1076b(this.c, str);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo4410a() {
        try {
            String string = new JSONObject(this.d.getString("me.cheshmak.data")).getString("url");
            if (string.contains("telegram")) {
                m1164b(string);
            } else {
                m1163a(string);
            }
        } catch (Exception e) {
        }
    }
}
