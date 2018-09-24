package net.hockeyapp.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefsUtil {
    private static final String PREFS_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_feedback_token";
    private static final String PREFS_KEY_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_key_feedback_token";
    private static final String PREFS_KEY_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_key_name_email";
    private static final String PREFS_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_name_email";
    private SharedPreferences mFeedbackTokenPrefs;
    private SharedPreferences mNameEmailSubjectPrefs;

    private static class PrefsUtilHolder {
        static final PrefsUtil INSTANCE = new PrefsUtil();

        private PrefsUtilHolder() {
        }
    }

    private PrefsUtil() {
    }

    public static PrefsUtil getInstance() {
        return PrefsUtilHolder.INSTANCE;
    }

    public void saveFeedbackTokenToPrefs(Context context, String token) {
        if (context != null) {
            this.mFeedbackTokenPrefs = context.getSharedPreferences(PREFS_FEEDBACK_TOKEN, 0);
            if (this.mFeedbackTokenPrefs != null) {
                Editor editor = this.mFeedbackTokenPrefs.edit();
                editor.putString(PREFS_KEY_FEEDBACK_TOKEN, token);
                editor.apply();
            }
        }
    }

    public String getFeedbackTokenFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.mFeedbackTokenPrefs = context.getSharedPreferences(PREFS_FEEDBACK_TOKEN, 0);
        if (this.mFeedbackTokenPrefs != null) {
            return this.mFeedbackTokenPrefs.getString(PREFS_KEY_FEEDBACK_TOKEN, null);
        }
        return null;
    }

    public void saveNameEmailSubjectToPrefs(Context context, String name, String email, String subject) {
        if (context != null) {
            this.mNameEmailSubjectPrefs = context.getSharedPreferences(PREFS_NAME_EMAIL_SUBJECT, 0);
            if (this.mNameEmailSubjectPrefs != null) {
                Editor editor = this.mNameEmailSubjectPrefs.edit();
                if (name == null || email == null || subject == null) {
                    editor.putString(PREFS_KEY_NAME_EMAIL_SUBJECT, null);
                } else {
                    editor.putString(PREFS_KEY_NAME_EMAIL_SUBJECT, String.format("%s|%s|%s", new Object[]{name, email, subject}));
                }
                editor.apply();
            }
        }
    }

    public String getNameEmailFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.mNameEmailSubjectPrefs = context.getSharedPreferences(PREFS_NAME_EMAIL_SUBJECT, 0);
        if (this.mNameEmailSubjectPrefs != null) {
            return this.mNameEmailSubjectPrefs.getString(PREFS_KEY_NAME_EMAIL_SUBJECT, null);
        }
        return null;
    }
}
