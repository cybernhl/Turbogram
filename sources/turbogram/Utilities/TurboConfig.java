package turbogram.Utilities;

import android.content.SharedPreferences;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;

public class TurboConfig {
    private static volatile TurboConfig[] Instance = new TurboConfig[3];
    public static boolean adsBlocker = preferences.getBoolean("ads_blocker", false);
    public static boolean answeringMachine = preferences.getBoolean("answering_machine", false);
    public static String answeringMachineMsg = preferences.getString("answering_machine_msg", "User is not available right now, please leave a message.");
    public static String appLockPattern = preferences.getString("app_lock_patt", "");
    public static boolean avatarInAction = preferences.getBoolean("avatar_in_action", true);
    public static boolean callsEnabled = preferences.getBoolean("calls_enabled", true);
    public static boolean categorizeDialogs = preferences.getBoolean("cd_enable", false);
    public static boolean categorizeProfile = preferences.getBoolean("cp_enable", true);
    public static int chatBarStatus = preferences.getInt("chat_bar_status", 3);
    public static boolean chatContactStatus = preferences.getBoolean("chat_contact_status", false);
    public static String chatHidePasscode = preferences.getString("chat_password", "");
    public static String chatHidePattern = preferences.getString("chat_hide_patt", "");
    public static String chatLockPasscode = preferences.getString("chat_lock_pass", "");
    public static String chatLockPattern = preferences.getString("chat_lock_patt", "");
    public static boolean chatPreview = preferences.getBoolean("chat_preview", false);
    public static boolean confirmatinAudio = preferences.getBoolean("confirmatin_audio", false);
    public static boolean confirmatinVideo = preferences.getBoolean("confirmatin_video", false);
    public static int contactAvatarTouch = preferences.getInt("contact_avatar_touch", 1);
    public static boolean contactStatus = preferences.getBoolean("contact_status", false);
    public static boolean copySender = preferences.getBoolean("copy_sender", true);
    public static boolean dayBgOverride = preferences.getBoolean("day_bg_override", false);
    public static String dayTheme = preferences.getString("day_theme", null);
    public static boolean dontHideStickertab = preferences.getBoolean("dont_hide_stab", false);
    public static int editorFontSize = preferences.getInt("editor_font_size", AndroidUtilities.isTablet() ? 20 : 18);
    public static boolean exactCount = preferences.getBoolean("exact_count", false);
    public static boolean flipDirection = preferences.getBoolean("turbo_flip", false);
    public static boolean floatingDate = preferences.getBoolean("floating_date", true);
    public static int fontType = preferences.getInt("font_type", 3);
    public static int groupAvatarTouch = preferences.getInt("group_avatar_touch", 1);
    public static boolean hiddenChatNotification = preferences.getBoolean("show_hnotification", true);
    public static boolean hiddenChatsUnlocked = preferences.getBoolean("chat_unlocked", false);
    public static boolean hiddenInShareAlert = preferences.getBoolean("hidden_sharealert", true);
    public static boolean hideCameraInAttach = preferences.getBoolean("hide_camera", true);
    public static boolean hidePhone = preferences.getBoolean("hide_phone", false);
    public static boolean hideTurboChannel = preferences.getBoolean("hide_tchannel", true);
    public static boolean inappVideoPlayer = preferences.getBoolean("inapp_player", true);
    public static boolean is24Hours = preferences.getBoolean("enable24HourFormat", false);
    public static boolean isPremium = preferences.getBoolean("is_premium", false);
    public static boolean isSilent = preferences.getBoolean("turbo_silent", false);
    public static boolean keepChatOpen = preferences.getBoolean("keep_chat_open", true);
    public static boolean keepContactsOpen = preferences.getBoolean("keep_contacts", false);
    public static boolean multiForwardTabs = preferences.getBoolean("multi_forward_tabs", true);
    public static boolean mutualContact = preferences.getBoolean("mutual_contact", false);
    public static String nightTheme = preferences.getString("night_theme", "Dark");
    public static boolean persianDate = preferences.getBoolean("persian_date", false);
    public static SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("turboconfig", 0);
    public static boolean previewSticker = preferences.getBoolean("preview_sticker", false);
    public static boolean replyVibration = preferences.getBoolean("reply_vibration", true);
    public static boolean saveInProfileNotQuote = preferences.getBoolean("fm_notquot", false);
    public static boolean separateMedia = preferences.getBoolean("separate_media", false);
    public static boolean sharedFileTitle = preferences.getBoolean("shared_file_title", true);
    public static boolean sharedGifTitle = preferences.getBoolean("shared_gif_title", true);
    public static boolean sharedLinkTitle = preferences.getBoolean("shared_link_title", true);
    public static boolean sharedMusicTitle = preferences.getBoolean("shared_music_title", true);
    public static boolean sharedPhotoTitle = preferences.getBoolean("shared_photo_title", true);
    public static boolean sharedVideoTitle = preferences.getBoolean("shared_video_title", true);
    public static boolean sharedVoiceTitle = preferences.getBoolean("shared_voice_title", true);
    public static boolean showActionbarShadow = preferences.getBoolean("action_shadow", false);
    public static boolean showCategorizeIcon = preferences.getBoolean("category_icon", true);
    public static boolean showProfileCatMenuInChat = preferences.getBoolean("cp_menu_inchat", true);
    public static boolean showProfileIcon = preferences.getBoolean("profile_icon", true);
    public static boolean specificContact = preferences.getBoolean("specific_contact", false);
    public static boolean swipeToReply = preferences.getBoolean("swipe_reply", true);
    public static boolean swipeVoice = preferences.getBoolean("swipe_voice", true);
    public static boolean tabletMode = preferences.getBoolean("tablet_mode", true);
    public static int textNicer = preferences.getInt("nicewrite", 0);
    public static boolean textNicerPopup = preferences.getBoolean("nice_popup", true);
    public static String turboBubbleStyle = preferences.getString("turbo_bubble_style", "Telegram");
    public static String turboCheckStyle = preferences.getString("turbo_check_style", "Stock");
    public static boolean typingToast = preferences.getBoolean("typing_toast", false);
    public static int userAvatarTouch = preferences.getInt("user_avatar_touch", 2);
    public static int voiceChangerSpeed = preferences.getInt("voice_speed", 1);
    public static int voiceChangerTranspose = preferences.getInt("transpose_semitone", 5);
    public static int voiceChangerType = preferences.getInt("voice_changer_type", 0);

    public static class Toolbar {
        public static int buttonSize = TurboConfig.preferences.getInt("toolbar_bsize", 50);
        public static int buttonSpaccing = TurboConfig.preferences.getInt("toolbar_bspace", 8);
        public static int direction = TurboConfig.preferences.getInt("toolbar_direction", 0);
        public static boolean enabled = TurboConfig.preferences.getBoolean("tool_bar", true);
        public static String priority = TurboConfig.preferences.getString("tool_priority", "[0, 1, 2, 3, 4, 5, 6, 7]");
        public static int type = TurboConfig.preferences.getInt("toolbar_type", 0);

        public static void setBooleanValue(String pref, boolean value) {
            TurboConfig.preferences.edit().putBoolean(pref, value).commit();
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

        private static void updatePreferences() {
            priority = TurboConfig.preferences.getString("tool_priority", "[0, 1, 2, 3, 4, 5, 6, 7]");
            enabled = TurboConfig.preferences.getBoolean("tool_bar", true);
            type = TurboConfig.preferences.getInt("toolbar_type", 0);
            direction = TurboConfig.preferences.getInt("toolbar_direction", 0);
            buttonSize = TurboConfig.preferences.getInt("toolbar_bsize", 50);
            buttonSpaccing = TurboConfig.preferences.getInt("toolbar_bspace", 8);
        }
    }

    public static void setBooleanValue(String pref, boolean value) {
        preferences.edit().putBoolean(pref, value).commit();
        updatePreferences();
    }

    public static void setIntValue(String pref, int value) {
        preferences.edit().putInt(pref, value).commit();
        updatePreferences();
    }

    public static void setStringValue(String pref, String value) {
        preferences.edit().putString(pref, value).commit();
        updatePreferences();
    }

    public static boolean containValue(String pref) {
        return preferences.contains(pref);
    }

    public static void removeValue(String pref) {
        preferences.edit().remove(pref).commit();
    }

    private static void updatePreferences() {
        dayTheme = preferences.getString("day_theme", null);
        nightTheme = preferences.getString("night_theme", "Dark");
        dayBgOverride = preferences.getBoolean("day_bg_override", false);
        isPremium = preferences.getBoolean("is_premium", false);
        hideTurboChannel = preferences.getBoolean("hide_tchannel", true);
        callsEnabled = preferences.getBoolean("calls_enabled", true);
        chatPreview = preferences.getBoolean("chat_preview", false);
        adsBlocker = preferences.getBoolean("ads_blocker", false);
        tabletMode = preferences.getBoolean("tablet_mode", true);
        persianDate = preferences.getBoolean("persian_date", false);
        is24Hours = preferences.getBoolean("enable24HourFormat", false);
        flipDirection = preferences.getBoolean("turbo_flip", false);
        multiForwardTabs = preferences.getBoolean("multi_forward_tabs", true);
        hidePhone = preferences.getBoolean("hide_phone", false);
        avatarInAction = preferences.getBoolean("avatar_in_action", true);
        exactCount = preferences.getBoolean("exact_count", false);
        copySender = preferences.getBoolean("copy_sender", true);
        floatingDate = preferences.getBoolean("floating_date", true);
        previewSticker = preferences.getBoolean("preview_sticker", false);
        confirmatinAudio = preferences.getBoolean("confirmatin_audio", false);
        confirmatinVideo = preferences.getBoolean("confirmatin_video", false);
        mutualContact = preferences.getBoolean("mutual_contact", false);
        contactStatus = preferences.getBoolean("contact_status", false);
        chatContactStatus = preferences.getBoolean("chat_contact_status", false);
        keepChatOpen = preferences.getBoolean("keep_chat_open", true);
        keepContactsOpen = preferences.getBoolean("keep_contacts", false);
        hideCameraInAttach = preferences.getBoolean("hide_camera", true);
        typingToast = preferences.getBoolean("typing_toast", false);
        dontHideStickertab = preferences.getBoolean("dont_hide_stab", false);
        answeringMachine = preferences.getBoolean("answering_machine", false);
        answeringMachineMsg = preferences.getString("answering_machine_msg", "User is not available right now, please leave a message.");
        isSilent = preferences.getBoolean("turbo_silent", false);
        categorizeProfile = preferences.getBoolean("cp_enable", true);
        showProfileCatMenuInChat = preferences.getBoolean("cp_menu_inchat", true);
        saveInProfileNotQuote = preferences.getBoolean("fm_notquot", false);
        categorizeDialogs = preferences.getBoolean("cd_enable", false);
        specificContact = preferences.getBoolean("specific_contact", false);
        fontType = preferences.getInt("font_type", 3);
        editorFontSize = preferences.getInt("editor_font_size", AndroidUtilities.isTablet() ? 20 : 18);
        inappVideoPlayer = preferences.getBoolean("inapp_player", true);
        showActionbarShadow = preferences.getBoolean("action_shadow", false);
        showCategorizeIcon = preferences.getBoolean("category_icon", true);
        showProfileIcon = preferences.getBoolean("profile_icon", true);
        separateMedia = preferences.getBoolean("separate_media", false);
        sharedPhotoTitle = preferences.getBoolean("shared_photo_title", true);
        sharedVideoTitle = preferences.getBoolean("shared_video_title", true);
        sharedGifTitle = preferences.getBoolean("shared_gif_title", true);
        sharedFileTitle = preferences.getBoolean("shared_file_title", true);
        sharedLinkTitle = preferences.getBoolean("shared_link_title", true);
        sharedMusicTitle = preferences.getBoolean("shared_music_title", true);
        sharedVoiceTitle = preferences.getBoolean("shared_voice_title", true);
        swipeToReply = preferences.getBoolean("swipe_reply", true);
        swipeVoice = preferences.getBoolean("swipe_voice", true);
        replyVibration = preferences.getBoolean("reply_vibration", true);
        textNicerPopup = preferences.getBoolean("nice_popup", true);
        textNicer = preferences.getInt("nicewrite", 0);
        chatBarStatus = preferences.getInt("chat_bar_status", 3);
        contactAvatarTouch = preferences.getInt("contact_avatar_touch", 1);
        groupAvatarTouch = preferences.getInt("group_avatar_touch", 1);
        userAvatarTouch = preferences.getInt("member_avatar_touch", 2);
        voiceChangerType = preferences.getInt("voice_changer_type", 0);
        voiceChangerSpeed = preferences.getInt("voice_speed", 1);
        voiceChangerTranspose = preferences.getInt("transpose_semitone", 5);
        appLockPattern = preferences.getString("app_lock_patt", "");
        chatLockPasscode = preferences.getString("chat_lock_pass", "");
        chatHidePasscode = preferences.getString("chat_password", "");
        chatLockPattern = preferences.getString("chat_lock_patt", "");
        chatHidePattern = preferences.getString("chat_hide_patt", "");
        hiddenChatsUnlocked = preferences.getBoolean("chat_unlocked", false);
        hiddenChatNotification = preferences.getBoolean("show_hnotification", true);
        hiddenInShareAlert = preferences.getBoolean("hidden_sharealert", true);
        turboBubbleStyle = preferences.getString("turbo_bubble_style", "Telegram");
        turboCheckStyle = preferences.getString("turbo_check_style", "Stock");
    }

    public static SharedPreferences getTurboConfigPreferences() {
        return preferences;
    }
}
