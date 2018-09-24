package me.cheshmak.android.sdk.core.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0238o;
import com.p001a.p002a.p003a.C0239q;
import me.cheshmak.android.sdk.core.network.C0571i;

/* renamed from: me.cheshmak.android.sdk.core.task.e */
public class C0594e extends C0459a {
    /* renamed from: f */
    private String f752f;

    public C0594e(String str) {
        super(new C0238o(1).m533a().m536b());
        this.f752f = str;
    }

    /* renamed from: a */
    protected C0239q mo4386a(@NonNull Throwable th, int i, int i2) {
        return C0239q.m545a(i, 20000);
    }

    /* renamed from: a */
    protected void mo4387a(int i, @Nullable Throwable th) {
    }

    /* renamed from: f */
    public void mo4388f() {
    }

    /* renamed from: g */
    public void mo4389g() {
        C0571i.m1123a().m1131b("[" + this.f752f + "]");
    }
}
