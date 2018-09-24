package me.cheshmak.android.sdk.core.p022l;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.wearable.WearableStatusCodes;
import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.network.C0555a;
import me.cheshmak.android.sdk.core.p018f.C0498d;
import me.cheshmak.android.sdk.core.p018f.C0499e;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.receivers.AlarmReceiver;

/* renamed from: me.cheshmak.android.sdk.core.l.a */
public class C0516a {
    /* renamed from: a */
    public static long m879a() {
        return System.currentTimeMillis() / 1000;
    }

    /* renamed from: a */
    public static void m880a(long j, long j2) {
        if (Math.abs(j - C0516a.m879a()) > j2) {
            Log.e("ERROR_CHESHMAK", "Device Time is Wrong !!!");
            try {
                WeakHashMap weakHashMap = new WeakHashMap();
                weakHashMap.put("SECTION", "NETWORK");
                weakHashMap.put("CLASS", "CheshmakRequest");
                weakHashMap.put("METHOD", "INVALID_TIME");
                weakHashMap.put("serverTime", j + "");
                weakHashMap.put("deviceTime", C0516a.m879a() + "");
                weakHashMap.put("threshold", j2 + "");
                C0545m.m1042a(3, weakHashMap);
            } catch (Throwable th) {
            }
            try {
                C0480a.m742c();
            } catch (Exception e) {
            }
        }
        C0477a.m656a().m709j(j);
        C0477a.m656a().m711k(j2);
    }

    /* renamed from: a */
    public static void m881a(Context context, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, AlarmReceiver.class), 0);
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
        }
    }

    /* renamed from: a */
    public static void m882a(Context context, Class<? extends BroadcastReceiver> cls, int i) {
        try {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, cls), 268435456);
            if (alarmManager != null) {
                alarmManager.cancel(broadcast);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m883a(Context context, Class cls, int i, long j) {
        try {
            long j2 = j < ((long) C0555a.f679a) ? j + ((long) C0555a.f679a) : j;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, cls), 134217728);
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setInexactRepeating(0, System.currentTimeMillis() + 10000, j2, broadcast);
            } else {
                alarmManager.setRepeating(0, System.currentTimeMillis() + 10000, j2, broadcast);
            }
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, AlarmReceiver.class), 1, 1);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m884a(Context context, Class<? extends BroadcastReceiver> cls, int i, Long l) {
        C0516a.m885a(context, (Class) cls, i, l, 0);
    }

    /* renamed from: a */
    private static void m885a(Context context, Class<? extends BroadcastReceiver> cls, int i, Long l, long j) {
        try {
            if (l.longValue() < ((long) C0555a.f679a)) {
                l = Long.valueOf(l.longValue() + ((long) C0555a.f679a));
            }
            C0516a.m882a(context, cls, i);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, cls), 134217728);
            long longValue = (j == 0 ? l.longValue() + 20000 : j + 20000) + System.currentTimeMillis();
            if (alarmManager != null) {
                if (VERSION.SDK_INT >= 19) {
                    alarmManager.setInexactRepeating(0, longValue, l.longValue(), broadcast);
                } else {
                    alarmManager.setRepeating(0, longValue, l.longValue(), broadcast);
                }
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m886a(Context context, String str, long j, String str2, int i) {
        C0519d.m898b("setSchedulePush", " data : " + str);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("advScheduledPush", true);
        intent.putExtra("data", str);
        intent.putExtra("adsType", str2);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 0);
        if (alarmManager != null) {
            alarmManager.set(0, j, broadcast);
        }
    }

    /* renamed from: a */
    public static void m887a(Context context, List<C0499e> list) {
        if (list != null) {
            C0498d c0498d = new C0498d(context);
            for (C0499e a : list) {
                c0498d.m801a(a);
            }
        }
    }

    /* renamed from: a */
    public static void m888a(Context context, long[] jArr) {
        try {
            C0477a.m656a().m691d(jArr[0]);
            C0477a.m656a().m686c(jArr[1]);
            C0477a.m656a().m695e(jArr[2]);
            C0477a.m656a().m698f(jArr[3]);
            C0477a.m656a().m707i(jArr[5]);
            C0516a.m884a(context, AlarmReceiver.class, 231728925, Long.valueOf(C0477a.m656a().m708j()));
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static boolean m889a(long j) {
        try {
            return C0477a.m656a().m727v() != -1 && j > C0477a.m656a().m727v();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m890a(long[] jArr) {
        try {
            if (jArr.length == 0) {
                return false;
            }
            if (C0477a.m656a().m708j() != Long.valueOf(jArr[0] * 1000).longValue()) {
                return true;
            }
            if (C0477a.m656a().m706i() != jArr[1]) {
                return true;
            }
            if (C0477a.m656a().m710k() != jArr[2]) {
                return true;
            }
            try {
                if (C0477a.m656a().m723r() != jArr[5] * 1000) {
                    return true;
                }
            } catch (Exception e) {
            }
            return C0477a.m656a().m712l() != jArr[3] * 1000;
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: b */
    public static int m891b(long j) {
        return (int) (((long) (new Random().nextInt(10000) + WearableStatusCodes.TARGET_NODE_NOT_CONNECTED)) + j);
    }

    /* renamed from: b */
    public static boolean m892b(Context context, Class<? extends AlarmReceiver> cls, int i) {
        try {
            return PendingIntent.getBroadcast(context, i, new Intent(context, cls), 536870912) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
