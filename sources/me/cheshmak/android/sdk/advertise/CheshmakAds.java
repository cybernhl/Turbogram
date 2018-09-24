package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import me.cheshmak.android.sdk.core.p019a.C0477a;

public class CheshmakAds {
    /* renamed from: a */
    private static boolean f400a = false;
    /* renamed from: b */
    private static boolean f401b = false;
    /* renamed from: c */
    private static boolean f402c = false;

    public static void initiate(Context context) {
        boolean z = false;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                f400a = applicationInfo.metaData.getBoolean("CheshmakAdsTestMode", f400a);
                f401b = applicationInfo.metaData.getBoolean("CheshmakAdsLoggingEnabled", f401b);
                if (applicationInfo.metaData.getBoolean("CheshmakAdsEnabled", false) && C0477a.m656a().m668K()) {
                    z = true;
                }
                setAdsEnabled(z);
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static boolean isAdsEnabled() {
        return f402c;
    }

    public static boolean isLoggingEnabled() {
        return f401b;
    }

    public static boolean isTestMode() {
        return f400a;
    }

    public static void setAdsEnabled(boolean z) {
        f402c = z;
        C0477a.m656a().m697e(z);
    }

    public static void setLoggingEnabled(boolean z) {
        f401b = z;
    }

    public static void setTestMode(boolean z) {
        f400a = z;
    }
}
