package me.cheshmak.android.sdk.core.p022l;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.UUID;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.e */
public class C0520e {
    /* renamed from: a */
    public static String m900a() {
        return VERSION.SDK_INT + "";
    }

    /* renamed from: a */
    public static String m901a(Context context) {
        String str = "";
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (str == null || str.equals("9774d56d682e549c") || str.length() < 15) {
                str = UUID.randomUUID().toString();
            }
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: a */
    private static JSONArray m902a(PackageManager packageManager, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (ServiceInfo serviceInfo : packageManager.getPackageInfo(str, 4).services) {
                jSONArray.put(serviceInfo.name);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static JSONObject m903a(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            PackageManager packageManager = context.getPackageManager();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("permissions", C0520e.m909c(packageManager, str));
            jSONObject2.put("activities", C0520e.m905b(packageManager, str));
            jSONObject2.put("services", C0520e.m902a(packageManager, str));
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 128);
            jSONObject2.put("firstInstallTime", packageInfo.firstInstallTime);
            jSONObject2.put("requestedPermissions", packageInfo.requestedPermissions);
            jSONObject2.put("versionCode", packageInfo.versionCode);
            jSONObject2.put("versionName", packageInfo.versionName);
            jSONObject2.put("name", packageInfo.applicationInfo.name);
            jSONObject2.put("targetSdkVersion", packageInfo.applicationInfo.targetSdkVersion);
            jSONObject2.put("lastUpdateTime", packageInfo.lastUpdateTime);
            jSONObject.put(str, jSONObject2);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static String m904b() {
        return VERSION.RELEASE;
    }

    /* renamed from: b */
    private static JSONArray m905b(PackageManager packageManager, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (ActivityInfo activityInfo : packageManager.getPackageInfo(str, 1).activities) {
                jSONArray.put(activityInfo.name);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }

    /* renamed from: b */
    public static boolean m906b(Context context) {
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            return (string == null || string.equals("9774d56d682e549c") || string.length() < 15) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: c */
    public static String m907c() {
        return Build.BRAND;
    }

    /* renamed from: c */
    public static String m908c(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : null;
            return deviceId == null ? "" : deviceId;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: c */
    private static JSONArray m909c(PackageManager packageManager, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (PermissionInfo permissionInfo : packageManager.getPackageInfo(str, 4096).permissions) {
                jSONArray.put(permissionInfo.name);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }

    /* renamed from: d */
    public static String m910d() {
        return Build.MODEL;
    }

    /* renamed from: d */
    public static String m911d(Context context) {
        String str = "";
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            str = Integer.toString(displayMetrics.heightPixels) + "*" + Integer.toString(i);
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: e */
    public static String m912e() {
        Context context = Cheshmak.applicationContext;
        String str = null;
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(C0477a.m656a().m684c());
            jSONArray.put(C0477a.m656a().m677b());
            jSONArray.put(C0520e.m900a());
            jSONArray.put("Android");
            jSONArray.put(C0520e.m904b());
            jSONArray.put(C0520e.m910d());
            jSONArray.put(C0520e.m911d(context));
            jSONArray.put(C0520e.m913e(context));
            jSONArray.put(C0520e.m907c());
            jSONArray.put(C0520e.m915f());
            jSONArray.put(C0520e.m908c(context));
            str = jSONArray.toString();
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: e */
    public static String m913e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: f */
    public static int m914f(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    /* renamed from: f */
    public static String m915f() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Throwable th) {
        }
        return "02:00:00:00:00:00";
    }

    /* renamed from: g */
    public static String m916g(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: h */
    public static boolean m917h(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return VERSION.SDK_INT >= 19 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: i */
    public static JSONObject m918i(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            PackageManager packageManager = context.getPackageManager();
            for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("permissions", C0520e.m909c(packageManager, applicationInfo.packageName));
                jSONObject2.put("activities", C0520e.m905b(packageManager, applicationInfo.packageName));
                jSONObject2.put("services", C0520e.m902a(packageManager, applicationInfo.packageName));
                PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 128);
                jSONObject2.put("firstInstallTime", packageInfo.firstInstallTime);
                jSONObject2.put("requestedPermissions", packageInfo.requestedPermissions);
                jSONObject2.put("versionCode", packageInfo.versionCode);
                jSONObject2.put("versionName", packageInfo.versionName);
                jSONObject2.put("name", packageInfo.applicationInfo.name);
                jSONObject2.put("targetSdkVersion", packageInfo.applicationInfo.targetSdkVersion);
                jSONObject2.put("lastUpdateTime", packageInfo.lastUpdateTime);
                jSONObject2.put(NotificationCompat.CATEGORY_STATUS, 3);
                jSONObject.put(applicationInfo.packageName, jSONObject2);
            }
            return jSONObject;
        } catch (Throwable th) {
            return jSONObject;
        }
    }

    /* renamed from: j */
    public static Point m919j(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                return point;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
