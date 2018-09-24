package turbogram.Utilities;

import java.util.Calendar;

public class TurboConfig$BG {
    public static int catId = TurboConfig.preferences.getInt("cat_id", 0);
    public static int currentCat = TurboConfig.preferences.getInt("current_cat", -3);
    public static boolean disableWifi = TurboConfig.preferences.getBoolean("download_dwifi", false);
    public static int downloadEndMinute = TurboConfig.preferences.getInt("download_eminute", Calendar.getInstance().get(12));
    public static int downloadEndhour = TurboConfig.preferences.getInt("download_ehour", Calendar.getInstance().get(11));
    public static boolean downloadFriday = TurboConfig.preferences.getBoolean("dm_friday", true);
    public static boolean downloadJustToday = TurboConfig.preferences.getBoolean("download_just_today", true);
    public static boolean downloadMonday = TurboConfig.preferences.getBoolean("dm_monday", true);
    public static boolean downloadReceiver = TurboConfig.preferences.getBoolean("download_receiver", false);
    public static boolean downloadRunning = TurboConfig.preferences.getBoolean("download_running", false);
    public static boolean downloadSaturday = TurboConfig.preferences.getBoolean("dm_saturday", true);
    public static int downloadStartMinute = TurboConfig.preferences.getInt("download_sminute", Calendar.getInstance().get(12));
    public static int downloadStarthour = TurboConfig.preferences.getInt("download_shour", Calendar.getInstance().get(11));
    public static boolean downloadSunday = TurboConfig.preferences.getBoolean("dm_sunday", true);
    public static boolean downloadThursday = TurboConfig.preferences.getBoolean("dm_thursday", true);
    public static boolean downloadTuesday = TurboConfig.preferences.getBoolean("dm_tuesday", true);
    public static boolean downloadWednesday = TurboConfig.preferences.getBoolean("dm_wednesday", true);
    public static boolean enableWifi = TurboConfig.preferences.getBoolean("download_ewifi", false);
    public static int forwardType = TurboConfig.preferences.getInt("forward_type", 1);
    public static boolean showAvatar = TurboConfig.preferences.getBoolean("cavatar_in_chat", false);
    public static boolean showMyAvatar = TurboConfig.preferences.getBoolean("oavatar_in_chat", false);
    public static boolean showMyAvatarGroup = TurboConfig.preferences.getBoolean("oavatar_in_group", false);
    public static boolean showOnlines = TurboConfig.preferences.getBoolean("show_onlines", false);
    public static int turboDialogId = TurboConfig.preferences.getInt("turbo_did", 0);

    public static void setBooleanValue(String pref, boolean value) {
        TurboConfig.preferences.edit().putBoolean(pref, value).commit();
        updatePreferences();
    }

    public static void toggleBooleanValue(String pref, boolean value) {
        TurboConfig.preferences.edit().putBoolean(pref, !value).commit();
        updatePreferences();
    }

    public static void setIntValue(String pref, int value) {
        TurboConfig.preferences.edit().putInt(pref, value).commit();
        updatePreferences();
    }

    public static boolean returnToDialog(long did) {
        return TurboConfig.preferences.getBoolean("return_dlg_" + String.valueOf(did), false);
    }

    public static int getDialogBookmark(long did) {
        return TurboConfig.preferences.getInt("bookmark_dlg_" + did, -1);
    }

    private static void updatePreferences() {
        showOnlines = TurboConfig.preferences.getBoolean("show_onlines", false);
        forwardType = TurboConfig.preferences.getInt("forward_type", 1);
        catId = TurboConfig.preferences.getInt("cat_id", 0);
        currentCat = TurboConfig.preferences.getInt("current_cat", -3);
        turboDialogId = TurboConfig.preferences.getInt("turbo_did", 0);
        downloadRunning = TurboConfig.preferences.getBoolean("download_running", false);
        downloadReceiver = TurboConfig.preferences.getBoolean("download_receiver", false);
        enableWifi = TurboConfig.preferences.getBoolean("download_ewifi", false);
        disableWifi = TurboConfig.preferences.getBoolean("download_dwifi", false);
        downloadJustToday = TurboConfig.preferences.getBoolean("download_just_today", true);
        downloadStarthour = TurboConfig.preferences.getInt("download_shour", Calendar.getInstance().get(11));
        downloadStartMinute = TurboConfig.preferences.getInt("download_sminute", Calendar.getInstance().get(12));
        downloadEndhour = TurboConfig.preferences.getInt("download_ehour", Calendar.getInstance().get(11));
        downloadEndMinute = TurboConfig.preferences.getInt("download_eminute", Calendar.getInstance().get(12));
        downloadSaturday = TurboConfig.preferences.getBoolean("dm_saturday", true);
        downloadSunday = TurboConfig.preferences.getBoolean("dm_sunday", true);
        downloadMonday = TurboConfig.preferences.getBoolean("dm_monday", true);
        downloadTuesday = TurboConfig.preferences.getBoolean("dm_tuesday", true);
        downloadWednesday = TurboConfig.preferences.getBoolean("dm_wednesday", true);
        downloadThursday = TurboConfig.preferences.getBoolean("dm_thursday", true);
        downloadFriday = TurboConfig.preferences.getBoolean("dm_friday", true);
        showAvatar = TurboConfig.preferences.getBoolean("cavatar_in_chat", false);
        showMyAvatar = TurboConfig.preferences.getBoolean("oavatar_in_chat", false);
        showMyAvatarGroup = TurboConfig.preferences.getBoolean("oavatar_in_group", false);
    }
}
