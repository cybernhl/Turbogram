package me.cheshmak.android.sdk.core.task;

import android.location.Location;
import android.net.wifi.WifiManager;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.PeriodicTask.Builder;
import com.google.android.gms.gcm.TaskParams;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.List;
import me.cheshmak.android.sdk.core.p018f.C0500f;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0542j;
import me.cheshmak.android.sdk.core.p022l.C0546n;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.p024e.C0491a;
import me.cheshmak.android.sdk.core.p025g.C0501a;
import org.json.JSONObject;

public class WifiTask extends GcmTaskService {
    /* renamed from: a */
    public static PeriodicTask m1174a(long j) {
        return new Builder().setService(WifiTask.class).setPersisted(true).setUpdateCurrent(true).setTag("WIFI_PERIODIC_TASK").setPeriod(j).build();
    }

    /* renamed from: a */
    private void m1175a(int i, String str) {
        try {
            C0491a.m778b(getApplicationContext()).m488a(new C0592c(i, str));
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private void m1176a(int i, JSONObject jSONObject) {
        try {
            C0491a.m778b(getApplicationContext()).m488a(new C0592c(i, jSONObject.toString()));
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m1177b() {
        try {
            String E = C0477a.m656a().m662E();
            if (E != null) {
                m1175a(3, E);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: c */
    private void m1178c() {
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            if (wifiManager.isWifiEnabled() && wifiManager.getWifiState() != 2) {
                List scanResults = wifiManager.getScanResults();
                JSONObject a;
                if (scanResults.size() != 0) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("wifiList", C0552s.m1068a(scanResults));
                    if (C0552s.m1074a(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") || C0552s.m1074a(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                        jSONObject.put("connectedBTS", C0552s.m1077c(getApplicationContext()));
                        if (C0546n.m1048a(getApplicationContext()) == C0546n.f662a) {
                            jSONObject.put("currentWifi", C0552s.m1069a(C0546n.m1049b(getApplicationContext())));
                        }
                        a = m1179a();
                        if (a != null) {
                            jSONObject.put(Param.LOCATION, a);
                        }
                    }
                    jSONObject.put("signals", C0477a.m656a().m661D());
                    m1176a(2, jSONObject);
                    return;
                }
                a = new JSONObject();
                a.put("signals", C0477a.m656a().m661D());
                m1176a(2, a);
                wifiManager.startScan();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public JSONObject m1179a() {
        synchronized (this) {
            try {
                if ((C0501a.m809a(getApplicationContext()).m817d() || C0501a.m809a(getApplicationContext()).m818e()) && !C0501a.m809a(getApplicationContext()).m816c()) {
                    C0501a.m809a(getApplicationContext()).m815b();
                    wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            } catch (C0542j e) {
            } catch (InterruptedException e2) {
            }
        }
        try {
            Location a = C0501a.m809a(getApplicationContext()).m814a();
            if (a != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("lat", String.valueOf(a.getLatitude()));
                    jSONObject.put("long", String.valueOf(a.getLongitude()));
                    jSONObject.put("acc", String.valueOf(a.getAccuracy()));
                    jSONObject.put("speed", String.valueOf(a.getSpeed()));
                    jSONObject.put("provider", String.valueOf(a.getProvider()));
                    jSONObject.put("time", a.getTime());
                    return jSONObject;
                } catch (Throwable e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            }
            return null;
        } catch (C0542j e4) {
            return null;
        }
    }

    public void onInitializeTasks() {
        super.onInitializeTasks();
        try {
            GcmNetworkManager.getInstance(getApplicationContext()).schedule(m1174a(C0500f.m806a()));
        } catch (Throwable th) {
        }
    }

    public int onRunTask(TaskParams taskParams) {
        m1178c();
        m1177b();
        return 0;
    }
}
