package turbogram.Utilities;

public class TurboConfig$Tabs {
    public static boolean autoDownloadFavorite = TurboConfig.preferences.getBoolean("fav_auto_download", false);
    public static boolean changeTabTitle = TurboConfig.preferences.getBoolean("change_tab_title", true);
    public static boolean countOnlyNotMuted = TurboConfig.preferences.getBoolean("tabs_only_not_muted", false);
    public static int defaulTab = TurboConfig.preferences.getInt("default_tab", 6);
    public static boolean hideTabsOnScroll = TurboConfig.preferences.getBoolean("hide_tabs", true);
    public static boolean mergeGroups = TurboConfig.preferences.getBoolean("merge_groups", false);
    public static boolean moveTabsToBottom = TurboConfig.preferences.getBoolean("move_tabs", false);
    public static int selectedTab = TurboConfig.preferences.getInt("selected_tab", defaulTab);
    public static boolean showHiddenInShareAlert = TurboConfig.preferences.getBoolean("hidden_sharealert", true);
    public static boolean swipeTabs = TurboConfig.preferences.getBoolean("swipe_tabs", true);
    public static boolean tabsCountChats = TurboConfig.preferences.getBoolean("tabs_count_chats", false);
    public static boolean tabsCounter = TurboConfig.preferences.getBoolean("tabs_counter", true);
    public static boolean tabsEnabled = TurboConfig.preferences.getBoolean("tabs", true);
    public static int tabsHeight = TurboConfig.preferences.getInt("tabs_height", 45);
    public static String tabsPriority = TurboConfig.preferences.getString("tabs_priority", "[0, 1, 2, 3, 4, 5, 6, 7]");
    public static String tabsVisibility = TurboConfig.preferences.getString("tabs_visibility", "[0, 0, 0, 0, 0, 0, 0, 0]");

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

    public static void setStringValue(String pref, String value) {
        TurboConfig.preferences.edit().putString(pref, value).commit();
        updatePreferences();
    }

    public static int getFirstActiveTab() {
        int i;
        int[] tabs = new int[8];
        int[] visible = new int[8];
        String tabsPriorityArray = tabsPriority;
        if (tabsPriorityArray != null) {
            i = 0;
            for (String item : tabsPriorityArray.substring(1, tabsPriorityArray.length() - 1).split(", ")) {
                if (item.length() > 0) {
                    tabs[i] = Integer.parseInt(item);
                }
                i++;
            }
        }
        String tabsVisibilityArray = tabsVisibility;
        if (tabsVisibilityArray != null) {
            i = 0;
            for (String item2 : tabsVisibilityArray.substring(1, tabsVisibilityArray.length() - 1).split(", ")) {
                if (item2.length() > 0) {
                    visible[i] = Integer.parseInt(item2);
                }
                i++;
            }
        }
        for (int a = 0; a < tabs.length; a++) {
            if (visible[a] == 0) {
                return tabs[a];
            }
        }
        return 0;
    }

    public static int[] getTabsArray() {
        int[] tabs = new int[8];
        String tabsPriorityArray = tabsPriority;
        if (tabsPriorityArray != null) {
            int i = 0;
            for (String item : tabsPriorityArray.substring(1, tabsPriorityArray.length() - 1).split(", ")) {
                if (item.length() > 0) {
                    tabs[i] = Integer.parseInt(item);
                }
                i++;
            }
        }
        return tabs;
    }

    public static int[] getVisibleArray() {
        int[] visible = new int[8];
        String tabsVisibilityArray = tabsVisibility;
        if (tabsVisibilityArray != null) {
            int i = 0;
            for (String item : tabsVisibilityArray.substring(1, tabsVisibilityArray.length() - 1).split(", ")) {
                if (item.length() > 0) {
                    visible[i] = Integer.parseInt(item);
                }
                i++;
            }
        }
        return visible;
    }

    private static void updatePreferences() {
        tabsPriority = TurboConfig.preferences.getString("tabs_priority", "[0, 1, 2, 3, 4, 5, 6, 7]");
        tabsVisibility = TurboConfig.preferences.getString("tabs_visibility", "[0, 0, 0, 0, 0, 0, 0, 0]");
        tabsEnabled = TurboConfig.preferences.getBoolean("tabs", true);
        swipeTabs = TurboConfig.preferences.getBoolean("swipe_tabs", true);
        changeTabTitle = TurboConfig.preferences.getBoolean("change_tab_title", true);
        hideTabsOnScroll = TurboConfig.preferences.getBoolean("hide_tabs", true);
        moveTabsToBottom = TurboConfig.preferences.getBoolean("move_tabs", false);
        tabsHeight = TurboConfig.preferences.getInt("tabs_height", 45);
        mergeGroups = TurboConfig.preferences.getBoolean("merge_groups", false);
        autoDownloadFavorite = TurboConfig.preferences.getBoolean("fav_auto_download", false);
        defaulTab = TurboConfig.preferences.getInt("default_tab", 6);
        selectedTab = TurboConfig.preferences.getInt("selected_tab", defaulTab);
        showHiddenInShareAlert = TurboConfig.preferences.getBoolean("hidden_sharealert", true);
        tabsCounter = TurboConfig.preferences.getBoolean("tabs_counter", true);
        tabsCountChats = TurboConfig.preferences.getBoolean("tabs_count_chats", false);
        countOnlyNotMuted = TurboConfig.preferences.getBoolean("tabs_only_not_muted", false);
    }
}
