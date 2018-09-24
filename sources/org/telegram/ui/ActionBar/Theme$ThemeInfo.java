package org.telegram.ui.ActionBar;

import android.text.TextUtils;
import com.baranak.turbogramf.R;
import org.json.JSONObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;

public class Theme$ThemeInfo {
    public String assetName;
    public String name;
    public String pathToFile;

    public JSONObject getSaveJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", this.name);
            jsonObject.put("path", this.pathToFile);
            return jsonObject;
        } catch (Exception e) {
            FileLog.e(e);
            return null;
        }
    }

    public String getName() {
        if ("Default".equals(this.name)) {
            return LocaleController.getString("Default", R.string.Default);
        }
        if ("Blue".equals(this.name)) {
            return LocaleController.getString("ThemeBlue", R.string.ThemeBlue);
        }
        if ("Dark".equals(this.name)) {
            return LocaleController.getString("ThemeDark", R.string.ThemeDark);
        }
        return this.name;
    }

    public static Theme$ThemeInfo createWithJson(JSONObject object) {
        if (object == null) {
            return null;
        }
        try {
            Theme$ThemeInfo themeInfo = new Theme$ThemeInfo();
            themeInfo.name = object.getString("name");
            themeInfo.pathToFile = object.getString("path");
            return themeInfo;
        } catch (Exception e) {
            FileLog.e(e);
            return null;
        }
    }

    public static Theme$ThemeInfo createWithString(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String[] args = string.split("\\|");
        if (args.length != 2) {
            return null;
        }
        Theme$ThemeInfo themeInfo = new Theme$ThemeInfo();
        themeInfo.name = args[0];
        themeInfo.pathToFile = args[1];
        return themeInfo;
    }
}
