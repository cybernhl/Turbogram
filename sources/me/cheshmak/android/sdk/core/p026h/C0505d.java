package me.cheshmak.android.sdk.core.p026h;

import me.cheshmak.android.sdk.core.p022l.C0523h;
import org.json.JSONArray;

/* renamed from: me.cheshmak.android.sdk.core.h.d */
public class C0505d extends C0502a {
    /* renamed from: a */
    private String f501a;
    /* renamed from: b */
    private JSONArray f502b;
    /* renamed from: c */
    private String f503c;
    /* renamed from: d */
    private JSONArray f504d;
    /* renamed from: e */
    private String f505e;

    public C0505d(long j, long j2, String str, Throwable th) {
        super(j, j2, str, null);
        m832a(C0523h.m925a(th));
        m833a(C0523h.m926b(th));
        m834b(C0523h.m925a(th.getCause()));
        m835b(C0523h.m926b(th.getCause()));
        m836c(C0523h.m927c(th));
    }

    /* renamed from: a */
    private void m832a(String str) {
        this.f501a = str;
    }

    /* renamed from: a */
    private void m833a(JSONArray jSONArray) {
        this.f502b = jSONArray;
    }

    /* renamed from: b */
    private void m834b(String str) {
        this.f503c = str;
    }

    /* renamed from: b */
    private void m835b(JSONArray jSONArray) {
        this.f504d = jSONArray;
    }

    /* renamed from: c */
    private void m836c(String str) {
        this.f505e = str;
    }

    /* renamed from: h */
    private String m837h() {
        return this.f501a;
    }

    /* renamed from: i */
    private JSONArray m838i() {
        return this.f502b;
    }

    /* renamed from: j */
    private String m839j() {
        return this.f503c;
    }

    /* renamed from: k */
    private JSONArray m840k() {
        return this.f504d;
    }

    /* renamed from: l */
    private String m841l() {
        return this.f505e;
    }

    /* renamed from: g */
    public JSONArray mo4403g() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(m824e());
            jSONArray.put(m823d());
            jSONArray.put(m822c());
            jSONArray.put(m825f());
            jSONArray.put(m837h());
            jSONArray.put(m838i());
            jSONArray.put(m839j());
            jSONArray.put(m840k());
            jSONArray.put(m841l());
            jSONArray.put(m821b());
        } catch (Exception e) {
        }
        return jSONArray;
    }
}
