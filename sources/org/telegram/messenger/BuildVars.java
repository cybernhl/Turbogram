package org.telegram.messenger;

public class BuildVars {
    public static String APP_HASH = "76cac4363826d1e762d41399839a5870";
    public static int APP_ID = 49985;
    public static int BUILD_VERSION = BuildConfig.VERSION_CODE;
    public static String BUILD_VERSION_STRING = "4.9.1";
    public static boolean CHECK_UPDATES = false;
    public static boolean DEBUG_PRIVATE_VERSION = false;
    public static boolean DEBUG_VERSION = false;
    public static String HOCKEY_APP_HASH = "276a81bacd1b4d7ab564f36254796cbe";
    public static String HOCKEY_APP_HASH_DEBUG = "ef5566371c7b42cda8ad0da1ade555d3";
    public static boolean LOGS_ENABLED;
    public static String PLAYSTORE_APP_URL = "";

    static {
        LOGS_ENABLED = false;
        if (ApplicationLoader.applicationContext != null) {
            LOGS_ENABLED = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0).getBoolean("logsEnabled", DEBUG_VERSION);
        }
    }
}
