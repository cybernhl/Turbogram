package me.cheshmak.android.sdk.core.push.p030a;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* renamed from: me.cheshmak.android.sdk.core.push.a.h */
public abstract class C0577h {
    /* renamed from: c */
    protected Context f732c;
    /* renamed from: d */
    protected Bundle f733d;

    public C0577h(Context context, Bundle bundle) {
        this.f732c = context;
        this.f733d = bundle;
    }

    /* renamed from: a */
    abstract void mo4410a();

    /* renamed from: c */
    public void m1154c() {
        try {
            ((NotificationManager) this.f732c.getSystemService("notification")).cancel(this.f733d.getInt("notifId"));
            this.f732c.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        } catch (Exception e) {
        }
    }

    /* renamed from: d */
    public void m1155d() {
        this.f732c = null;
        this.f733d = null;
    }
}
