package me.cheshmak.android.sdk.core.network;

import java.util.List;
import me.cheshmak.android.sdk.core.p018f.C0499e;
import me.cheshmak.android.sdk.core.p022l.C0547o;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.network.b */
class C0556b {
    /* renamed from: a */
    JSONObject f686a;
    /* renamed from: b */
    private boolean f687b;
    /* renamed from: c */
    private List<Integer> f688c;
    /* renamed from: d */
    private List<C0499e> f689d;

    C0556b(String str) {
        this.f686a = new JSONObject(str);
        if (this.f686a.has("isSuccess")) {
            m1089a(this.f686a.getBoolean("isSuccess"));
        }
        if (this.f686a.has("messages")) {
            m1088a(C0547o.m1053a(this.f686a.getJSONArray("messages")));
        }
    }

    /* renamed from: a */
    private void m1088a(List<Integer> list) {
        this.f688c = list;
    }

    /* renamed from: a */
    private void m1089a(boolean z) {
        this.f687b = z;
    }

    /* renamed from: a */
    public List<C0499e> mo4408a() {
        return this.f689d;
    }

    /* renamed from: b */
    boolean m1091b() {
        return this.f687b;
    }

    /* renamed from: c */
    List<Integer> m1092c() {
        return this.f688c;
    }
}
