package me.cheshmak.android.sdk.core.task;

import android.os.Bundle;
import com.p001a.p002a.p003a.C0225i;
import com.p001a.p002a.p003a.C0238o;

/* renamed from: me.cheshmak.android.sdk.core.task.a */
public abstract class C0459a extends C0225i {
    /* renamed from: d */
    public transient Bundle f410d;
    /* renamed from: e */
    public String f411e;

    public C0459a(C0238o c0238o) {
        super(c0238o);
    }

    /* renamed from: a */
    public void m606a(Bundle bundle) {
        this.f410d = bundle;
    }

    /* renamed from: a */
    public void mo4384a(String str) {
        this.f411e = str;
    }

    /* renamed from: j */
    protected int mo4385j() {
        return 6;
    }

    /* renamed from: n */
    public Bundle m609n() {
        return this.f410d;
    }

    /* renamed from: o */
    public String m610o() {
        return this.f411e;
    }
}
