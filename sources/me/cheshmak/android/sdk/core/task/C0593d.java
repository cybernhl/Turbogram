package me.cheshmak.android.sdk.core.task;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p018f.C0499e;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p020b.C0480a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import me.cheshmak.android.sdk.core.p022l.C0552s;
import me.cheshmak.android.sdk.core.p026h.C0503b;

@SuppressLint({"all"})
/* renamed from: me.cheshmak.android.sdk.core.task.d */
public class C0593d extends Service implements LocationListener {
    /* renamed from: c */
    private static C0499e f749c;
    /* renamed from: a */
    protected LocationManager f750a;
    /* renamed from: b */
    private WeakReference<Context> f751b;

    public C0593d(Context context) {
        this.f751b = new WeakReference(context);
    }

    /* renamed from: a */
    private Map<String, String> m1191a(Location location) {
        Map hashMap = new HashMap();
        hashMap.put("provider", location.getProvider());
        hashMap.put("accuracy", location.getAccuracy() + "");
        hashMap.put("altitude", location.getAltitude() + "");
        hashMap.put("latitude", location.getLatitude() + "");
        hashMap.put("longitude", location.getLongitude() + "");
        hashMap.put("bearing", location.getBearing() + "");
        hashMap.put("speed", location.getSpeed() + "");
        return hashMap;
    }

    /* renamed from: a */
    public static boolean m1192a(Context context) {
        return C0552s.m1074a(context, "android.permission.ACCESS_COARSE_LOCATION") || C0552s.m1074a(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    /* renamed from: b */
    private void m1193b(Location location) {
        if (location != null) {
            try {
                C0503b c0503b = new C0503b(2, 19, "gps-data", m1191a(location));
                c0503b.m820a(Long.valueOf(C0477a.m656a().m694e()));
                C0480a.m733a(c0503b.mo4403g().toString());
            } catch (Throwable e) {
                Log.e("DEBUG_CHESHMAK", " saveLocation -->", e);
                WeakHashMap weakHashMap = new WeakHashMap();
                weakHashMap.put("SECTION", "SERVICE");
                weakHashMap.put("CLASS", "LocationTracker");
                weakHashMap.put("METHOD", "saveLocation");
                C0545m.m1043a(4, weakHashMap, e);
                return;
            }
        }
        this.f750a.removeUpdates(this);
    }

    /* renamed from: c */
    private boolean m1194c() {
        Throwable e;
        boolean z;
        WeakHashMap weakHashMap;
        try {
            if (this.f750a != null) {
                if (!C0552s.m1074a((Context) this.f751b.get(), "android.permission.ACCESS_COARSE_LOCATION") && !C0552s.m1074a((Context) this.f751b.get(), "android.permission.ACCESS_FINE_LOCATION")) {
                    return false;
                }
                if (this.f750a.getAllProviders() != null && this.f750a.getAllProviders().contains("network")) {
                    Looper.prepare();
                    this.f750a.requestSingleUpdate("network", this, null);
                    try {
                        if (this.f750a == null) {
                            return true;
                        }
                        Location lastKnownLocation = this.f750a.getLastKnownLocation("network");
                        if (lastKnownLocation == null) {
                            return true;
                        }
                        m1193b(lastKnownLocation);
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        z = true;
                        try {
                            weakHashMap = new WeakHashMap();
                            weakHashMap.put("SECTION", "SERVICE");
                            weakHashMap.put("CLASS", "LocationTracker");
                            weakHashMap.put("METHOD", "checkLocationByNetwork");
                            C0545m.m1043a(4, weakHashMap, e);
                            return false;
                        } catch (Throwable th) {
                            return z;
                        }
                    } catch (Throwable th2) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable e3) {
            e = e3;
            z = false;
            weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "LocationTracker");
            weakHashMap.put("METHOD", "checkLocationByNetwork");
            C0545m.m1043a(4, weakHashMap, e);
            return false;
        } catch (Throwable th3) {
            return false;
        }
    }

    /* renamed from: a */
    public C0499e m1195a() {
        return f749c;
    }

    /* renamed from: a */
    public void m1196a(C0499e c0499e) {
        f749c = c0499e;
        m1197b();
    }

    /* renamed from: b */
    public Location m1197b() {
        try {
            if (this.f750a == null) {
                this.f750a = (LocationManager) ((Context) this.f751b.get()).getSystemService(Param.LOCATION);
                if (f749c.m803b() == 0) {
                    this.f750a.removeUpdates(this);
                } else {
                    m1194c();
                }
            }
        } catch (Throwable e) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "LocationTracker");
            weakHashMap.put("METHOD", "checkLocation");
            C0545m.m1043a(4, weakHashMap, e);
        }
        return null;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onLocationChanged(Location location) {
        m1193b(location);
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
        m1197b();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        m1197b();
        return 2;
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        m1197b();
    }
}
