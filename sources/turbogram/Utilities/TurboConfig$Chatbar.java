package turbogram.Utilities;

public class TurboConfig$Chatbar {
    public static boolean centerButton = TurboConfig.preferences.getBoolean("bar_centerbtn", false);
    public static int defaul = TurboConfig.preferences.getInt("bar_default", 0);
    public static boolean show = TurboConfig.preferences.getBoolean("bar_show", true);
    public static boolean showMembers = TurboConfig.preferences.getBoolean("show_cmember", true);
    public static boolean vertical = TurboConfig.preferences.getBoolean("bar_vertical", true);

    public static void setBooleanValue(String pref, boolean value) {
        TurboConfig.preferences.edit().putBoolean(pref, value).commit();
        updatePreferences();
    }

    public static void setIntValue(String pref, int value) {
        TurboConfig.preferences.edit().putInt(pref, value).commit();
        updatePreferences();
    }

    private static void updatePreferences() {
        show = TurboConfig.preferences.getBoolean("bar_show", true);
        vertical = TurboConfig.preferences.getBoolean("bar_vertical", true);
        centerButton = TurboConfig.preferences.getBoolean("bar_centerbtn", false);
        showMembers = TurboConfig.preferences.getBoolean("show_cmember", true);
        defaul = TurboConfig.preferences.getInt("bar_default", 0);
    }
}
