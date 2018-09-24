package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.tasks.LoginTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.Util;

public class LoginManager {
    public static final int LOGIN_MODE_ANONYMOUS = 0;
    public static final int LOGIN_MODE_EMAIL_ONLY = 1;
    public static final int LOGIN_MODE_EMAIL_PASSWORD = 2;
    public static final int LOGIN_MODE_VALIDATE = 3;
    private static String identifier = null;
    static LoginManagerListener listener;
    static Class<?> mainActivity;
    private static int mode;
    private static String secret = null;
    private static String urlString = null;
    private static Handler validateHandler = null;

    private static class LoginHandler extends Handler {
        private final WeakReference<Context> mWeakContext;

        LoginHandler(Context context) {
            this.mWeakContext = new WeakReference(context);
        }

        public void handleMessage(Message msg) {
            boolean success = msg.getData().getBoolean("success");
            Context context = (Context) this.mWeakContext.get();
            if (context != null) {
                if (success) {
                    HockeyLog.verbose("HockeyAuth", "We authenticated or verified successfully");
                } else {
                    LoginManager.startLoginActivity(context);
                }
            }
        }
    }

    public static void register(Context context, String appSecret, int mode) {
        register(context, Util.getAppIdentifier(context), appSecret, mode, (Class) null);
    }

    public static void register(Context context, String appSecret, int mode, LoginManagerListener listener) {
        register(context, Util.getAppIdentifier(context), appSecret, mode, listener);
    }

    public static void register(Context context, String appIdentifier, String appSecret, int mode, LoginManagerListener listener) {
        listener = listener;
        register(context, appIdentifier, appSecret, mode, (Class) null);
    }

    public static void register(Context context, String appIdentifier, String appSecret, int mode, Class<?> activity) {
        register(context, appIdentifier, appSecret, Constants.BASE_URL, mode, activity);
    }

    public static void register(Context context, String appIdentifier, String appSecret, String urlString, int mode, Class<?> activity) {
        if (context != null) {
            identifier = Util.sanitizeAppIdentifier(appIdentifier);
            secret = appSecret;
            urlString = urlString;
            mode = mode;
            mainActivity = activity;
            if (validateHandler == null) {
                validateHandler = new LoginHandler(context);
            }
            Constants.loadFromContext(context);
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void verifyLogin(final Activity context, Intent intent) {
        if (context != null && mode != 0) {
            AsyncTaskUtils.execute(new AsyncTask<Void, Object, Object>() {
                private String auid;
                private String iuid;

                protected Object doInBackground(Void... voids) {
                    SharedPreferences prefs = context.getSharedPreferences("net.hockeyapp.android.login", 0);
                    if (prefs.getInt(LoginActivity.EXTRA_MODE, -1) != LoginManager.mode) {
                        HockeyLog.verbose("HockeyAuth", "Mode has changed, require re-auth.");
                        prefs.edit().remove("auid").remove("iuid").putInt(LoginActivity.EXTRA_MODE, LoginManager.mode).apply();
                    }
                    this.auid = prefs.getString("auid", null);
                    this.iuid = prefs.getString("iuid", null);
                    return null;
                }

                protected void onPostExecute(Object o) {
                    boolean notAuthenticated;
                    if (this.auid == null && this.iuid == null) {
                        notAuthenticated = true;
                    } else {
                        notAuthenticated = false;
                    }
                    boolean auidMissing;
                    if (this.auid == null && (LoginManager.mode == 2 || LoginManager.mode == 3)) {
                        auidMissing = true;
                    } else {
                        auidMissing = false;
                    }
                    boolean iuidMissing;
                    if (this.iuid == null && LoginManager.mode == 1) {
                        iuidMissing = true;
                    } else {
                        iuidMissing = false;
                    }
                    if (notAuthenticated || auidMissing || iuidMissing) {
                        HockeyLog.verbose("HockeyAuth", "Not authenticated or correct ID missing, re-authenticate.");
                        LoginManager.startLoginActivity(context);
                    } else if (LoginManager.mode == 3) {
                        HockeyLog.verbose("HockeyAuth", "LOGIN_MODE_VALIDATE, Validate the user's info!");
                        Map<String, String> params = new HashMap();
                        if (this.auid != null) {
                            params.put(Param.TYPE, "auid");
                            params.put(TtmlNode.ATTR_ID, this.auid);
                        } else if (this.iuid != null) {
                            params.put(Param.TYPE, "iuid");
                            params.put(TtmlNode.ATTR_ID, this.iuid);
                        }
                        AsyncTask verifyTask = new LoginTask(context, LoginManager.validateHandler, LoginManager.getURLString(3), 3, params);
                        verifyTask.setShowProgressDialog(false);
                        AsyncTaskUtils.execute(verifyTask);
                    }
                }
            });
        }
    }

    public static String getLoginEmail(Context context) {
        return context.getSharedPreferences("net.hockeyapp.android.login", 0).getString("email", null);
    }

    private static void startLoginActivity(Context context) {
        Intent intent = new Intent();
        int tempMode = Boolean.valueOf(mode == 3).booleanValue() ? 2 : mode;
        intent.setFlags(335544320);
        intent.setClass(context, LoginActivity.class);
        intent.putExtra("url", getURLString(tempMode));
        intent.putExtra(LoginActivity.EXTRA_MODE, tempMode);
        intent.putExtra(LoginActivity.EXTRA_SECRET, secret);
        context.startActivity(intent);
    }

    private static String getURLString(int mode) {
        String suffix = "";
        if (mode == 2) {
            suffix = "authorize";
        } else if (mode == 1) {
            suffix = "check";
        } else if (mode == 3) {
            suffix = "validate";
        }
        return urlString + "api/3/apps/" + identifier + "/identity/" + suffix;
    }
}
