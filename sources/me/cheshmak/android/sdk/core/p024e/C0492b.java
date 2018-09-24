package me.cheshmak.android.sdk.core.p024e;

import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.PeriodicTask.Builder;
import me.cheshmak.android.sdk.core.task.WifiTask;

/* renamed from: me.cheshmak.android.sdk.core.e.b */
public class C0492b {
    /* renamed from: a */
    public static PeriodicTask m780a(long j) {
        return new Builder().setService(WifiTask.class).setPersisted(true).setUpdateCurrent(true).setTag("WIFI_PERIODIC_TASK").setPeriod(j).build();
    }
}
