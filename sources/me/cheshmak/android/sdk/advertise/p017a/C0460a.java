package me.cheshmak.android.sdk.advertise.p017a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.C0238o;
import com.p001a.p002a.p003a.C0239q;
import me.cheshmak.android.sdk.advertise.C0457j;
import me.cheshmak.android.sdk.advertise.C0464b;
import me.cheshmak.android.sdk.core.task.C0459a;

/* renamed from: me.cheshmak.android.sdk.advertise.a.a */
public class C0460a extends C0459a {
    /* renamed from: f */
    private final String f412f;
    /* renamed from: g */
    private String f413g;

    /* renamed from: me.cheshmak.android.sdk.advertise.a.a$1 */
    class C04581 implements C0457j {
        /* renamed from: a */
        final /* synthetic */ C0460a f409a;

        C04581(C0460a c0460a) {
            this.f409a = c0460a;
        }

        /* renamed from: a */
        public void mo4383a(Bundle bundle) {
            this.f409a.m606a(bundle);
        }
    }

    public C0460a(String str, String str2) {
        super(new C0238o(1).m533a().m536b().m535a("EventJob"));
        this.f413g = str2;
        this.f412f = str;
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
        C0464b.m625a().m626a(this.f412f, this.f413g, new C04581(this));
    }
}
