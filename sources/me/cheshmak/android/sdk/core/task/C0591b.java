package me.cheshmak.android.sdk.core.task;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.p001a.p002a.p003a.C0238o;
import com.p001a.p002a.p003a.C0239q;

/* renamed from: me.cheshmak.android.sdk.core.task.b */
public class C0591b extends C0459a {
    /* renamed from: f */
    private String f745f;
    /* renamed from: g */
    private int f746g = -1;

    public C0591b(int i, int i2) {
        super(new C0238o(1).m533a().m534a((long) i2).m536b());
        this.f746g = i;
    }

    public C0591b(String str, int i) {
        super(new C0238o(1).m533a().m534a((long) i).m536b());
        this.f745f = str;
        String replaceAll = str.replaceAll("[^0-9]", "");
        if (replaceAll.length() > 9) {
            replaceAll = replaceAll.substring(0, 8);
        }
        this.f746g = Integer.parseInt(replaceAll);
    }

    /* renamed from: a */
    protected C0239q mo4386a(@NonNull Throwable th, int i, int i2) {
        return null;
    }

    /* renamed from: a */
    protected void mo4387a(int i, @Nullable Throwable th) {
    }

    /* renamed from: f */
    public void mo4388f() {
    }

    /* renamed from: g */
    public void mo4389g() {
        if (this.f746g != -1) {
            m1184p();
        }
        try {
            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(m422k());
            Intent intent = new Intent("me.cheshmak.push.action.close");
            intent.putExtra("pushId", this.f745f);
            instance.sendBroadcast(intent);
        } catch (Exception e) {
        }
    }

    /* renamed from: p */
    public void m1184p() {
        try {
            ((NotificationManager) m422k().getSystemService("notification")).cancel(this.f746g);
            m422k().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        } catch (Exception e) {
        }
    }
}
