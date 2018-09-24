package me.cheshmak.android.sdk.core.p025g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Date;
import me.cheshmak.android.sdk.core.p022l.C0542j;
import me.cheshmak.android.sdk.core.p022l.C0552s;

/* renamed from: me.cheshmak.android.sdk.core.g.a */
public class C0501a implements LocationListener {
    /* renamed from: a */
    private static volatile C0501a f490a;
    /* renamed from: b */
    private Location f491b;
    /* renamed from: c */
    private Context f492c;

    private C0501a() {
    }

    /* renamed from: a */
    public static C0501a m809a(Context context) {
        if (f490a == null) {
            synchronized (C0501a.class) {
                if (f490a == null) {
                    f490a = new C0501a();
                }
            }
        }
        if (context != null) {
            f490a.f492c = context;
        }
        return f490a;
    }

    /* renamed from: a */
    private void m810a(Location location) {
        this.f491b = location;
    }

    /* renamed from: a */
    private boolean m811a(Location location, Location location2) {
        return location2 == null ? true : location == null ? false : Math.abs(location2.getTime() - location.getTime()) > ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS ? location.getTime() > location2.getTime() : (location.hasAccuracy() && location2.hasAccuracy()) ? (location.getAccuracy() >= location2.getAccuracy() || location2.getAccuracy() / location.getAccuracy() <= 3.0f) ? (location2.getAccuracy() >= location.getAccuracy() || location.getAccuracy() / location2.getAccuracy() <= 3.0f) ? location.getTime() > location2.getTime() : false : true : location.getTime() > location2.getTime();
    }

    /* renamed from: f */
    private void m812f() {
        try {
            Location lastKnownLocation = m813g().getLastKnownLocation("network");
            if (m811a(lastKnownLocation, this.f491b)) {
                m810a(lastKnownLocation);
            }
        } catch (Throwable th) {
            C0542j c0542j = new C0542j(th.getMessage());
        }
    }

    /* renamed from: g */
    private LocationManager m813g() {
        Object obj = (C0552s.m1074a(this.f492c, "android.permission.ACCESS_FINE_LOCATION") || C0552s.m1074a(this.f492c, "android.permission.ACCESS_COARSE_LOCATION")) ? 1 : null;
        if (obj != null) {
            return (LocationManager) this.f492c.getSystemService(Param.LOCATION);
        }
        throw new C0542j("android.permission.ACCESS_COARSE_LOCATION");
    }

    /* renamed from: a */
    public Location m814a() {
        m812f();
        return this.f491b;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    public void m815b() {
        try {
            m813g().requestSingleUpdate("network", this, this.f492c.getMainLooper());
        } catch (Throwable th) {
            C0542j c0542j = new C0542j(th.getMessage());
        }
    }

    /* renamed from: c */
    public boolean m816c() {
        m812f();
        return this.f491b != null && new Date().getTime() - this.f491b.getTime() < 3600000;
    }

    /* renamed from: d */
    public boolean m817d() {
        try {
            boolean z = C0552s.m1074a(this.f492c, "android.permission.ACCESS_FINE_LOCATION") || C0552s.m1074a(this.f492c, "android.permission.ACCESS_COARSE_LOCATION");
            if (!z) {
                return false;
            }
            LocationManager locationManager = (LocationManager) this.f492c.getSystemService(Param.LOCATION);
            z = locationManager != null && locationManager.isProviderEnabled("network");
            return z;
        } catch (SecurityException e) {
            Log.e("DEBUG_CHESHMAK", "Network Permissions not given to application");
            return false;
        }
    }

    /* renamed from: e */
    public boolean m818e() {
        return Secure.getString(this.f492c.getContentResolver(), "location_providers_allowed").toLowerCase().contains("network");
    }

    public void onLocationChanged(Location location) {
        if (m811a(location, this.f491b)) {
            m810a(location);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
