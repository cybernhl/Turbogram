package me.cheshmak.android.sdk.core.network;

import java.util.List;
import me.cheshmak.android.sdk.core.p022l.C0547o;

/* renamed from: me.cheshmak.android.sdk.core.network.d */
public class C0559d extends C0556b {
    /* renamed from: b */
    private long[] f693b;

    public C0559d(String str) {
        super(str);
        m1097a(C0547o.m1054b(this.a.getJSONArray("results")));
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ List mo4408a() {
        return super.mo4408a();
    }

    /* renamed from: a */
    public void m1097a(long[] jArr) {
        this.f693b = jArr;
    }

    /* renamed from: d */
    public long[] m1098d() {
        return this.f693b;
    }
}
