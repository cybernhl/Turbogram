package turbogram.Utilities;

import android.content.SharedPreferences;
import org.telegram.messenger.ApplicationLoader;

public class TurboConfig$Storage {
    public static String folderName = storagePref.getString("folder_name", "Telegram");
    public static boolean keepFilename = storagePref.getBoolean("keep_filename", false);
    public static String sdCard = storagePref.getString("storage_device", null);
    public static SharedPreferences storagePref = ApplicationLoader.applicationContext.getSharedPreferences("turbostorage", 0);

    public static void setBooleanValue(String pref, boolean value) {
        storagePref.edit().putBoolean(pref, value).commit();
        updatePreferences();
    }

    public static void setStringValue(String pref, String value) {
        storagePref.edit().putString(pref, value).commit();
        updatePreferences();
    }

    public static void removeValue(String pref) {
        storagePref.edit().remove(pref).commit();
    }

    private static void updatePreferences() {
        folderName = storagePref.getString("folder_name", "Telegram");
        keepFilename = storagePref.getBoolean("keep_filename", false);
        sdCard = storagePref.getString("storage_device", null);
    }
}
