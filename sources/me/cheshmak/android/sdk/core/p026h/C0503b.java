package me.cheshmak.android.sdk.core.p026h;

import java.util.Map;
import me.cheshmak.android.sdk.core.p022l.C0522g;
import org.json.JSONArray;

/* renamed from: me.cheshmak.android.sdk.core.h.b */
public class C0503b extends C0502a {
    public C0503b(long j, long j2, String str, Map<String, String> map) {
        super(j, j2, str, map);
    }

    /* renamed from: g */
    public JSONArray mo4403g() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(m824e());
            jSONArray.put(m823d());
            jSONArray.put(m822c());
            jSONArray.put(m821b());
            jSONArray.put(C0522g.m922a(m819a()));
            jSONArray.put(m825f());
        } catch (Exception e) {
        }
        return jSONArray;
    }
}
