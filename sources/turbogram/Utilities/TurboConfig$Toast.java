package turbogram.Utilities;

public class TurboConfig$Toast {
    public static int margin = TurboConfig.preferences.getInt("toast_margin", 0);
    public static int padding = TurboConfig.preferences.getInt("toast_padding", 0);
    public static int position = TurboConfig.preferences.getInt("toast_pos", 1);
    public static boolean status = TurboConfig.preferences.getBoolean("toast_status", false);
    public static int textSize = TurboConfig.preferences.getInt("toast_tsize", 13);
    public static boolean toBottom = TurboConfig.preferences.getBoolean("toast_tobottom", false);
    public static boolean typing = TurboConfig.preferences.getBoolean("toast_typing", false);

    public static void setBooleanValue(String pref, boolean value) {
        TurboConfig.preferences.edit().putBoolean(pref, value).commit();
        updatePreferences();
    }

    public static void setIntValue(String pref, int value) {
        TurboConfig.preferences.edit().putInt(pref, value).commit();
        updatePreferences();
    }

    private static void updatePreferences() {
        status = TurboConfig.preferences.getBoolean("toast_status", false);
        typing = TurboConfig.preferences.getBoolean("toast_typing", false);
        toBottom = TurboConfig.preferences.getBoolean("toast_tobottom", false);
        position = TurboConfig.preferences.getInt("toast_pos", 1);
        textSize = TurboConfig.preferences.getInt("toast_tsize", 13);
        padding = TurboConfig.preferences.getInt("toast_padding", 0);
        margin = TurboConfig.preferences.getInt("toast_margin", 0);
    }
}
