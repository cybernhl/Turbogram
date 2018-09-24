package com.p001a.p002a.p003a.p013h;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import android.os.PowerManager;
import com.p001a.p002a.p003a.p013h.C0204a.C0203a;

/* renamed from: com.a.a.a.h.c */
public class C0209c implements C0204a, C0205b {
    /* renamed from: a */
    private C0203a f195a;

    /* renamed from: com.a.a.a.h.c$1 */
    class C02061 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0209c f191a;

        C02061(C0209c c0209c) {
            this.f191a = c0209c;
        }

        public void onReceive(Context context, Intent intent) {
            this.f191a.m339b(context);
        }
    }

    /* renamed from: com.a.a.a.h.c$3 */
    class C02083 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0209c f194a;

        C02083(C0209c c0209c) {
            this.f194a = c0209c;
        }

        public void onReceive(Context context, Intent intent) {
            this.f194a.m339b(context);
        }
    }

    public C0209c(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (VERSION.SDK_INT >= 21) {
            if (VERSION.SDK_INT >= 23) {
                m335d(applicationContext);
            }
            m334c(applicationContext);
            return;
        }
        applicationContext.registerReceiver(new C02061(this), C0209c.m333a());
    }

    @TargetApi(23)
    /* renamed from: a */
    private static IntentFilter m333a() {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (VERSION.SDK_INT >= 23) {
            intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
        return intentFilter;
    }

    @TargetApi(23)
    /* renamed from: c */
    private void m334c(final Context context) {
        ((ConnectivityManager) context.getSystemService("connectivity")).registerNetworkCallback(new Builder().addCapability(12).addCapability(13).build(), new NetworkCallback(this) {
            /* renamed from: b */
            final /* synthetic */ C0209c f193b;

            public void onAvailable(Network network) {
                this.f193b.m339b(context);
            }
        });
    }

    @TargetApi(23)
    /* renamed from: d */
    private void m335d(Context context) {
        context.registerReceiver(new C02083(this), new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
    }

    @TargetApi(23)
    /* renamed from: e */
    private static boolean m336e(Context context) {
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager.isDeviceIdleMode() && !powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
    }

    /* renamed from: a */
    public int mo351a(Context context) {
        if (C0209c.m336e(context)) {
            return 0;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo == null ? 0 : (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 9) ? 2 : 1;
    }

    /* renamed from: a */
    public void mo352a(C0203a c0203a) {
        this.f195a = c0203a;
    }

    /* renamed from: b */
    void m339b(Context context) {
        if (this.f195a != null) {
            this.f195a.mo360a(mo351a(context));
        }
    }
}
