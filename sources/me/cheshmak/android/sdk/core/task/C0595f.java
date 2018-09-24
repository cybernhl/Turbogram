package me.cheshmak.android.sdk.core.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.p001a.p002a.p003a.C0238o;
import com.p001a.p002a.p003a.C0239q;
import me.cheshmak.android.sdk.core.network.C0571i;

/* renamed from: me.cheshmak.android.sdk.core.task.f */
public class C0595f extends C0459a {
    /* renamed from: f */
    private final String f753f;
    /* renamed from: g */
    private final String f754g;

    public C0595f(String str, String str2) {
        super(new C0238o(1).m533a().m536b());
        this.f753f = str;
        this.f754g = str2;
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
        if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(this.f753f)) {
            C0571i.m1123a().m1132b(this.f754g, "{}");
        }
    }
}
