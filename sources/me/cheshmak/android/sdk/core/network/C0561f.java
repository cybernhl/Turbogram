package me.cheshmak.android.sdk.core.network;

import java.util.List;
import me.cheshmak.android.sdk.core.p022l.C0547o;

/* renamed from: me.cheshmak.android.sdk.core.network.f */
public class C0561f extends C0556b {
    /* renamed from: b */
    private long[] f695b;

    public C0561f(String str) {
        super(str);
        m1103a(C0547o.m1054b(this.a.getJSONArray("results")));
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ List mo4408a() {
        return super.mo4408a();
    }

    /* renamed from: a */
    public void m1103a(long[] jArr) {
        this.f695b = jArr;
    }

    /* renamed from: d */
    public long[] m1104d() {
        return this.f695b;
    }
}
