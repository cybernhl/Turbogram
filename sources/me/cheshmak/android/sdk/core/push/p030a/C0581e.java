package me.cheshmak.android.sdk.core.push.p030a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.net.URLDecoder;
import java.util.Iterator;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.push.a.e */
class C0581e extends C0577h {
    public C0581e(Context context, Bundle bundle) {
        super(context, bundle);
    }

    /* renamed from: a */
    private String m1158a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '#') {
                stringBuilder.append(Uri.encode("#"));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public void mo4410a() {
        try {
            JSONObject jSONObject = new JSONObject(this.d.getString("me.cheshmak.data"));
            String optString = jSONObject.optString("package", null);
            String optString2 = jSONObject.optString("action", "android.intent.action.VIEW");
            String optString3 = jSONObject.optString("uri", null);
            JSONArray optJSONArray = jSONObject.optJSONArray("extra");
            Intent intent = new Intent(optString2);
            intent.addFlags(268435456);
            if (optString3 != null) {
                intent.setData(Uri.parse(m1158a(URLDecoder.decode(optString3, "UTF-8"))));
            }
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        Iterator keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            intent.putExtra(str, URLDecoder.decode(jSONObject2.optString(str), "UTF-8"));
                        }
                    }
                }
            }
            if (optString == null) {
                this.c.startActivity(intent);
            } else if (C0552s.m1078c(this.c, optString)) {
                intent.setPackage(optString);
                this.c.startActivity(intent);
            } else {
                Log.e("ERROR_CHESHMAK", optString + " is not installed");
            }
        } catch (Throwable th) {
        }
    }
}
