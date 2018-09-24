package me.cheshmak.android.sdk.core.p026h;

import java.util.List;
import me.cheshmak.android.sdk.core.p022l.C0522g;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.h.e */
public class C0506e extends C0502a {
    /* renamed from: a */
    private final List<String> f506a;

    public C0506e(long j, long j2, String str, List<String> list) {
        super(j, j2, str, null);
        this.f506a = list;
    }

    /* renamed from: g */
    public JSONArray mo4403g() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(m824e());
            jSONArray.put(m823d());
            jSONArray.put(m822c());
            jSONArray.put(m821b());
            JSONObject jSONObject = new JSONObject();
            if (this.f506a != null) {
                jSONObject.put("tags", C0522g.m924c(this.f506a));
            }
            jSONArray.put(jSONObject);
            jSONArray.put(m825f());
        } catch (Exception e) {
        }
        return jSONArray;
    }
}
