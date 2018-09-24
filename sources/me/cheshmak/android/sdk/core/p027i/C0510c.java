package me.cheshmak.android.sdk.core.p027i;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.HashMap;
import me.cheshmak.android.sdk.core.p022l.C0552s;

/* renamed from: me.cheshmak.android.sdk.core.i.c */
public final class C0510c {
    /* renamed from: a */
    private static C0510c f512a;
    /* renamed from: b */
    private NotificationManager f513b;
    /* renamed from: c */
    private HashMap<String, NotificationChannel> f514c = new HashMap();

    /* renamed from: me.cheshmak.android.sdk.core.i.c$a */
    public static class C0509a {
        /* renamed from: a */
        int f510a = -1;
        /* renamed from: b */
        int f511b = 0;

        public C0509a(int i, int i2) {
            this.f510a = i;
            this.f511b = i2;
        }
    }

    private C0510c() {
    }

    /* renamed from: a */
    public static C0510c m847a() {
        if (f512a == null) {
            synchronized (C0510c.class) {
                if (f512a == null) {
                    f512a = new C0510c();
                }
            }
        }
        return f512a;
    }

    /* renamed from: a */
    private void m848a(Context context) {
        if (this.f513b == null) {
            this.f513b = (NotificationManager) context.getSystemService("notification");
        }
    }

    /* renamed from: b */
    public static boolean m849b() {
        return VERSION.SDK_INT >= 26;
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    public NotificationChannel m850a(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        return m851a(context, str, str2, 4);
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    public NotificationChannel m851a(@NonNull Context context, @NonNull String str, @NonNull String str2, int i) {
        NotificationChannel notificationChannel = (NotificationChannel) this.f514c.get(str2);
        if (notificationChannel != null) {
            return notificationChannel;
        }
        m848a(context);
        notificationChannel = new NotificationChannel(str2, str, i);
        this.f513b.createNotificationChannel(notificationChannel);
        this.f514c.put(str2, notificationChannel);
        return notificationChannel;
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    public void m852a(@NonNull Context context, @NonNull String str, @NonNull C0509a c0509a) {
        NotificationChannel notificationChannel = (NotificationChannel) this.f514c.get(str);
        if (notificationChannel != null) {
            notificationChannel.setLockscreenVisibility(0);
            if (c0509a.f510a != -1) {
                notificationChannel.setLightColor(c0509a.f510a);
                notificationChannel.enableLights(true);
            }
            if (C0552s.m1074a(context, "android.permission.VIBRATE")) {
                switch (c0509a.f511b) {
                    case 0:
                        notificationChannel.enableVibration(false);
                        return;
                    case 1:
                        notificationChannel.setVibrationPattern(new long[]{0, 100, 0, 100});
                        notificationChannel.enableVibration(true);
                        return;
                    case 2:
                        notificationChannel.setVibrationPattern(new long[]{0, 1000});
                        notificationChannel.enableVibration(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
