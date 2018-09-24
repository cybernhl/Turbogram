package me.cheshmak.android.sdk.core.p026h;

import java.util.HashMap;
import java.util.Map;
import me.cheshmak.android.sdk.core.p022l.C0516a;
import org.json.JSONArray;

/* renamed from: me.cheshmak.android.sdk.core.h.a */
public abstract class C0502a {
    /* renamed from: a */
    private long f493a;
    /* renamed from: b */
    private Long f494b;
    /* renamed from: c */
    private Map<String, String> f495c;
    /* renamed from: d */
    private String f496d;
    /* renamed from: e */
    private long f497e;
    /* renamed from: f */
    private long f498f;

    C0502a(long j, long j2, String str, Map<String, String> map) {
        this.f498f = j;
        this.f497e = j2;
        this.f496d = str;
        if (map == null) {
            this.f495c = new HashMap();
        } else {
            this.f495c = map;
        }
        this.f493a = C0516a.m879a();
    }

    /* renamed from: a */
    public Map<String, String> m819a() {
        return this.f495c;
    }

    /* renamed from: a */
    public void m820a(Long l) {
        this.f494b = l;
    }

    /* renamed from: b */
    public String m821b() {
        return this.f496d;
    }

    /* renamed from: c */
    public long m822c() {
        return this.f497e;
    }

    /* renamed from: d */
    public long m823d() {
        return this.f498f;
    }

    /* renamed from: e */
    public Long m824e() {
        return this.f494b;
    }

    /* renamed from: f */
    public long m825f() {
        return this.f493a;
    }

    /* renamed from: g */
    public abstract JSONArray mo4403g();
}
