package me.cheshmak.android.sdk.core.p022l;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.List;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.push.CheshmakPushRegistration;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.s */
public class C0552s {
    /* renamed from: a */
    public static JSONArray m1068a(List<ScanResult> list) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (ScanResult scanResult : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("SSID", scanResult.SSID);
                jSONObject.put("BSSID", scanResult.BSSID);
                jSONObject.put(Param.LEVEL, scanResult.level);
                if (VERSION.SDK_INT >= 17) {
                    jSONObject.put(AppMeasurement.Param.TIMESTAMP, scanResult.timestamp);
                }
                jSONObject.put("tostring", scanResult.toString());
                jSONArray.put(jSONObject);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static JSONObject m1069a(WifiInfo wifiInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SSID", wifiInfo.getSSID());
            jSONObject.put("BSSID", wifiInfo.getBSSID());
            if (VERSION.SDK_INT >= 21) {
                jSONObject.put("frequency", wifiInfo.getFrequency());
            }
            jSONObject.put("ip", wifiInfo.getIpAddress());
            jSONObject.put("rssi", wifiInfo.getRssi());
            jSONObject.put("speedLink", wifiInfo.getLinkSpeed());
            jSONObject.put("tostring", wifiInfo.toString());
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static void m1070a(int i) {
        try {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("section", "PUSH");
            weakHashMap.put("CLASS", "Utils");
            weakHashMap.put("METHOD", "checkPlayServices");
            weakHashMap.put("statusCode", i + "");
            C0545m.m1042a(6, weakHashMap);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m1071a(Context context, Long l) {
        if (C0552s.m1072a(context)) {
            C0477a.m656a().m705h(l.longValue());
            C0550q.m1064a(context, new Intent(context, CheshmakPushRegistration.class));
        }
    }

    /* renamed from: a */
    public static boolean m1072a(Context context) {
        WeakHashMap weakHashMap;
        try {
            int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
            C0552s.m1070a(isGooglePlayServicesAvailable);
            return isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2 || isGooglePlayServicesAvailable == 18 || isGooglePlayServicesAvailable == 4 || isGooglePlayServicesAvailable == 17;
        } catch (Throwable e) {
            weakHashMap = new WeakHashMap();
            weakHashMap.put("CLASS", "Utils");
            weakHashMap.put("METHOD", "checkPlayServices");
            weakHashMap.put("MESSAGE", "NoClassDefFoundError");
            C0545m.m1045a(weakHashMap, e);
            return false;
        } catch (Throwable e2) {
            weakHashMap = new WeakHashMap();
            weakHashMap.put("CLASS", "Utils");
            weakHashMap.put("METHOD", "checkPlayServices");
            weakHashMap.put("MESSAGE", "Exception");
            C0545m.m1045a(weakHashMap, e2);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1073a(Context context, Class<?> cls) {
        try {
            for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
                if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1074a(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /* renamed from: b */
    public static int m1075b(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NoClassDefFoundError e) {
            return i;
        } catch (NameNotFoundException e2) {
            return -1;
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("CLASS", "Utils");
            weakHashMap.put("METHOD", "getGooglePlayServiceVersion");
            C0545m.m1045a(weakHashMap, th);
            return -2;
        }
    }

    /* renamed from: b */
    public static void m1076b(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public static JSONObject m1077c(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            GsmCellLocation gsmCellLocation = null;
            if (telephonyManager != null) {
                gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            }
            if (gsmCellLocation != null) {
                jSONObject.put("cid", gsmCellLocation.getCid());
                jSONObject.put("lac", gsmCellLocation.getLac());
                jSONObject.put("psc", gsmCellLocation.getPsc());
                String networkOperator = telephonyManager.getNetworkOperator();
                if (!(networkOperator == null || "".equals(networkOperator))) {
                    jSONObject.put("networkOperator", networkOperator);
                    jSONObject.put("mcc", networkOperator.substring(0, 3));
                    jSONObject.put("mnc", networkOperator.substring(3));
                }
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    /* renamed from: c */
    public static boolean m1078c(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (str == null) {
            return false;
        }
        try {
            packageManager.getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    public static int m1079d(Context context, String str) {
        int i = -1;
        try {
            i = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        } catch (Throwable th) {
        }
        return i;
    }

    /* renamed from: e */
    public static int m1080e(Context context, String str) {
        int i = 0;
        if (str != null) {
            try {
                i = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
            } catch (Exception e) {
            }
        }
        return i;
    }
}
