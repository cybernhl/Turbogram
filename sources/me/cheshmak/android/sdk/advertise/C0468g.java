package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import com.google.android.gms.common.internal.GmsIntents;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import net.hockeyapp.android.UpdateFragment;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.advertise.g */
public class C0468g {
    /* renamed from: a */
    static String m631a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", "" + C0470i.m641a(context, "ANDROID_ID"));
            jSONObject.put("deviceScreenWidth", "" + C0470i.m641a(context, "DISPLAY_WIDTH_PIXELS"));
            jSONObject.put("deviceScreenHeight", "" + C0470i.m641a(context, "DISPLAY_HEIGHT_PIXELS"));
            jSONObject.put("deviceDpi", "" + C0470i.m641a(context, "DPI"));
            jSONObject.put("deviceModel", "" + C0470i.m641a(context, "MODEL"));
            jSONObject.put("deviceAndroidSDKVersion", "" + C0470i.m641a(context, "ANDROID_VERSION"));
            jSONObject.put("deviceAndroidAPILevel", "" + C0470i.m641a(context, "ANDROID"));
            jSONObject.put("deviceLang", "" + C0470i.m641a(context, "LANGUAGE"));
            jSONObject.put("deviceOrientation", "" + C0470i.m641a(context, "ORIENTATION"));
            jSONObject.put("deviceNetwork", "" + C0470i.m641a(context, "NETWORK_CLASS"));
            jSONObject.put("devicePackage", "" + C0470i.m641a(context, GmsIntents.EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME));
            jSONObject.put("deviceDisplayDensity", "" + C0470i.m641a(context, "DISPLAY_DENSITY"));
            jSONObject.put("deviceBrand", "" + C0470i.m641a(context, "BRAND"));
            jSONObject.put("deviceOperator", "" + C0470i.m641a(context, "NETWORK_OPERATOR_NAME"));
            jSONObject.put("appKey", C0477a.m656a().m677b());
            jSONObject.put("chesVersion", "" + C0470i.m641a(context, "ADVERTISE_SDK_VERSION"));
            jSONObject.put("adsType", "banner");
            jSONObject.put("testMode", Boolean.toString(CheshmakAds.isTestMode()));
            jSONObject.put("packageName", context.getPackageName() + "");
        } catch (Exception e) {
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    static String m632a(String str) {
        return "notification".equals(str) ? C0467f.f425d : UpdateFragment.FRAGMENT_DIALOG.equals(str) ? C0467f.f427f : "condition".equals(str) ? C0467f.f428g : "";
    }

    /* renamed from: a */
    static JSONObject m633a(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("params", str);
            jSONObject.put("deviceId", C0470i.m641a(context, "ANDROID_ID"));
            jSONObject.put("appKey", C0477a.m656a().m677b());
            jSONObject.put("chesVersion", C0470i.m641a(context, "ADVERTISE_SDK_VERSION"));
            jSONObject.put("deviceScreenWidth", "" + C0470i.m641a(context, "DISPLAY_WIDTH_PIXELS"));
            jSONObject.put("deviceScreenHeight", "" + C0470i.m641a(context, "DISPLAY_HEIGHT_PIXELS"));
            jSONObject.put("deviceDpi", "" + C0470i.m641a(context, "DPI"));
            jSONObject.put("deviceModel", "" + C0470i.m641a(context, "MODEL"));
            jSONObject.put("deviceAndroidSDKVersion", "" + C0470i.m641a(context, "ANDROID_VERSION"));
            jSONObject.put("deviceAndroidAPILevel", "" + C0470i.m641a(context, "ANDROID"));
            jSONObject.put("deviceLang", "" + C0470i.m641a(context, "LANGUAGE"));
            jSONObject.put("deviceOrientation", "" + C0470i.m641a(context, "ORIENTATION"));
            jSONObject.put("deviceNetwork", "" + C0470i.m641a(context, "NETWORK_CLASS"));
            jSONObject.put("devicePackage", "" + C0470i.m641a(context, GmsIntents.EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME));
            jSONObject.put("deviceDisplayDensity", "" + C0470i.m641a(context, "DISPLAY_DENSITY"));
            jSONObject.put("deviceBrand", "" + C0470i.m641a(context, "BRAND"));
            jSONObject.put("deviceOperator", "" + C0470i.m641a(context, "NETWORK_OPERATOR_NAME"));
            jSONObject.put("packageName", context.getPackageName() + "");
        } catch (Exception e) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static String m634b(String str) {
        return "click".equals(str) ? C0467f.f426e + "click" : "dialogEvents".equals(str) ? C0467f.f429h : "";
    }
}
