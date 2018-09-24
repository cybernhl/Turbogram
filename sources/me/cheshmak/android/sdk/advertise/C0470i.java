package me.cheshmak.android.sdk.advertise;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.common.internal.GmsIntents;
import com.googlecode.mp4parser.authoring.tracks.h265.NalUnitTypes;
import java.util.Locale;

/* renamed from: me.cheshmak.android.sdk.advertise.i */
final class C0470i {
    /* renamed from: a */
    public static Object m641a(Context context, String str) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        int i = -1;
        switch (str.hashCode()) {
            case -2038781661:
                if (str.equals("TELEPHONY_SIM_SERIAL")) {
                    i = 46;
                    break;
                }
                break;
            case -1905220446:
                if (str.equals("DISPLAY")) {
                    i = 6;
                    break;
                }
                break;
            case -1852509708:
                if (str.equals("SERIAL")) {
                    i = 15;
                    break;
                }
                break;
            case -1724394759:
                if (str.equals("DISPLAY_X_DPI")) {
                    i = 37;
                    break;
                }
                break;
            case -1723471238:
                if (str.equals("DISPLAY_Y_DPI")) {
                    i = 38;
                    break;
                }
                break;
            case -1483131996:
                if (str.equals("DEVICE_ID")) {
                    i = 24;
                    break;
                }
                break;
            case -1147240649:
                if (str.equals("TELEPHONY_SIM_OPERATOR_NAME")) {
                    i = 45;
                    break;
                }
                break;
            case -1141280171:
                if (str.equals("TELEPHONY_COUNTRY_ISO")) {
                    i = 43;
                    break;
                }
                break;
            case -891806770:
                if (str.equals("TELEPHONY_NETWORK_OPERATOR_NAME")) {
                    i = 49;
                    break;
                }
                break;
            case -881870873:
                if (str.equals("NETWORK_CLASS")) {
                    i = 51;
                    break;
                }
                break;
            case -830962856:
                if (str.equals("LANGUAGE")) {
                    i = 29;
                    break;
                }
                break;
            case -693152843:
                if (str.equals("NETWORK_OPERATOR")) {
                    i = 26;
                    break;
                }
                break;
            case -618077995:
                if (str.equals("NETWORK_OPERATOR_NAME")) {
                    i = 27;
                    break;
                }
                break;
            case -591222229:
                if (str.equals("DISPLAY_DENSITY")) {
                    i = 32;
                    break;
                }
                break;
            case -531893781:
                if (str.equals("TELEPHONY_CELL_LOCATION")) {
                    i = 41;
                    break;
                }
                break;
            case -498900708:
                if (str.equals("TELEPHONY_NETWORK_OPERATOR")) {
                    i = 48;
                    break;
                }
                break;
            case -490448291:
                if (str.equals("SCREEN_ORIENTATION")) {
                    i = 39;
                    break;
                }
                break;
            case -273313821:
                if (str.equals("DISPLAY_WIDTH_PIXELS")) {
                    i = 36;
                    break;
                }
                break;
            case -143408561:
                if (str.equals("ANDROID")) {
                    i = 22;
                    break;
                }
                break;
            case 2331:
                if (str.equals("ID")) {
                    i = 10;
                    break;
                }
                break;
            case 67901:
                if (str.equals("DPI")) {
                    i = 50;
                    break;
                }
                break;
            case 2223528:
                if (str.equals("HOST")) {
                    i = 9;
                    break;
                }
                break;
            case 2567193:
                if (str.equals("TAGS")) {
                    i = 16;
                    break;
                }
                break;
            case 2575053:
                if (str.equals("TIME")) {
                    i = 17;
                    break;
                }
                break;
            case 2590522:
                if (str.equals("TYPE")) {
                    i = 19;
                    break;
                }
                break;
            case 2614219:
                if (str.equals("USER")) {
                    i = 20;
                    break;
                }
                break;
            case 63370950:
                if (str.equals("BOARD")) {
                    i = 0;
                    break;
                }
                break;
            case 63460199:
                if (str.equals("BRAND")) {
                    i = 1;
                    break;
                }
                break;
            case 73532169:
                if (str.equals("MODEL")) {
                    i = 12;
                    break;
                }
                break;
            case 77732827:
                if (str.equals("RADIO")) {
                    i = 14;
                    break;
                }
                break;
            case 131668012:
                if (str.equals("DATA_CONNECTIVITY")) {
                    i = 28;
                    break;
                }
                break;
            case 166188329:
                if (str.equals("DISPLAY_DENSITY_DPI")) {
                    i = 33;
                    break;
                }
                break;
            case 191076699:
                if (str.equals("ADVERTISE_SDK_VERSION")) {
                    i = 30;
                    break;
                }
                break;
            case 249360802:
                if (str.equals("TELEPHONY_DEVICE_SOFTWARE_VERSION")) {
                    i = 47;
                    break;
                }
                break;
            case 296640560:
                if (str.equals("TIME_ENGINEER_BUILD")) {
                    i = 18;
                    break;
                }
                break;
            case 347933649:
                if (str.equals("MANUFACTURER")) {
                    i = 11;
                    break;
                }
                break;
            case 408508623:
                if (str.equals("PRODUCT")) {
                    i = 13;
                    break;
                }
                break;
            case 469668612:
                if (str.equals(GmsIntents.EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME)) {
                    i = 25;
                    break;
                }
                break;
            case 490525672:
                if (str.equals("DISPLAY_HEIGHT_PIXELS")) {
                    i = 34;
                    break;
                }
                break;
            case 637051823:
                if (str.equals("LIBRARY_NAME")) {
                    i = 31;
                    break;
                }
                break;
            case 777026756:
                if (str.equals("FINGERPRINT")) {
                    i = 7;
                    break;
                }
                break;
            case 782939560:
                if (str.equals("BOOTLDR")) {
                    i = 2;
                    break;
                }
                break;
            case 899536360:
                if (str.equals("HARDWARE")) {
                    i = 8;
                    break;
                }
                break;
            case 1040208584:
                if (str.equals("ANDROID_VERSION")) {
                    i = 23;
                    break;
                }
                break;
            case 1208112395:
                if (str.equals("ANDROID_ID")) {
                    i = 21;
                    break;
                }
                break;
            case 1264452146:
                if (str.equals("TELEPHONY_PHONE_TYPE")) {
                    i = 42;
                    break;
                }
                break;
            case 1313240385:
                if (str.equals("CPU_ABI2")) {
                    i = 4;
                    break;
                }
                break;
            case 1611188727:
                if (str.equals("TELEPHONY_CALL_STATE")) {
                    i = 40;
                    break;
                }
                break;
            case 1663352851:
                if (str.equals("TELEPHONY_SIM_OPERATOR")) {
                    i = 44;
                    break;
                }
                break;
            case 1704930577:
                if (str.equals("CPU_ABI")) {
                    i = 3;
                    break;
                }
                break;
            case 1770099488:
                if (str.equals("DISPLAY_SCALED_DENSITY")) {
                    i = 35;
                    break;
                }
                break;
            case 2013139542:
                if (str.equals("DEVICE")) {
                    i = 5;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return Build.BOARD;
            case 1:
                return Build.BRAND;
            case 2:
                return Build.BOOTLOADER;
            case 3:
                return Build.CPU_ABI;
            case 4:
                return Build.CPU_ABI2;
            case 5:
                return Build.DEVICE;
            case 6:
                return Build.DISPLAY;
            case 7:
                return Build.FINGERPRINT;
            case 8:
                return Build.HARDWARE;
            case 9:
                return Build.HOST;
            case 10:
                return Build.ID;
            case 11:
                return Build.MANUFACTURER;
            case 12:
                return Build.MODEL;
            case 13:
                return Build.PRODUCT;
            case 14:
                return VERSION.SDK_INT < 14 ? Build.RADIO : Build.getRadioVersion();
            case 15:
                return Build.SERIAL;
            case 16:
                return Build.TAGS;
            case 17:
                return Double.toString((double) System.currentTimeMillis());
            case 18:
                return String.valueOf(Build.TIME);
            case 19:
                return Build.TYPE;
            case 20:
                return Build.USER;
            case 21:
                return Secure.getString(context.getContentResolver(), "android_id");
            case 22:
                return Integer.valueOf(VERSION.SDK_INT);
            case 23:
                return VERSION.RELEASE;
            case 24:
                return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            case 25:
                return context.getPackageName();
            case 26:
                return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            case 27:
                return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            case 28:
                return ((TelephonyManager) context.getSystemService("phone")).getDataState() == 2 ? "1" : "0";
            case NalUnitTypes.NAL_TYPE_RSV_VCL29 /*29*/:
                return Locale.getDefault().getLanguage();
            case NalUnitTypes.NAL_TYPE_RSV_VCL30 /*30*/:
                return Integer.valueOf(1);
            case NalUnitTypes.NAL_TYPE_RSV_VCL31 /*31*/:
                return "Android";
            case 32:
                return Float.valueOf(displayMetrics.density);
            case 33:
                return Integer.valueOf(displayMetrics.densityDpi);
            case 34:
                return Integer.valueOf(displayMetrics.heightPixels);
            case 35:
                return String.valueOf(displayMetrics.scaledDensity);
            case 36:
                return Integer.valueOf(displayMetrics.widthPixels);
            case 37:
                return String.valueOf(displayMetrics.xdpi);
            case 38:
                return String.valueOf(displayMetrics.ydpi);
            case 39:
                return configuration.orientation == 1 ? "portrait" : "landscape";
            case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getCallState());
            case 41:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getCellLocation());
            case 42:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getPhoneType());
            case 43:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getNetworkCountryIso());
            case 44:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
            case MotionEventCompat.AXIS_GENERIC_14 /*45*/:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getSimOperatorName());
            case MotionEventCompat.AXIS_GENERIC_15 /*46*/:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber());
            case MotionEventCompat.AXIS_GENERIC_16 /*47*/:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getDeviceSoftwareVersion());
            case 48:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getNetworkOperator());
            case 49:
                return String.valueOf(((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
            case 50:
                return Integer.valueOf(displayMetrics.densityDpi);
            case 51:
                return C0470i.m643b(context);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static String m642a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
            return "not_connected";
        }
        int type = activeNetworkInfo.getType();
        return type == 1 ? "wifi" : type == 6 ? "wimax" : "unknown";
    }

    /* renamed from: b */
    private static String m643b(Context context) {
        try {
            int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            if (networkType == 0) {
                return C0470i.m642a(context);
            }
            switch (networkType) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}
