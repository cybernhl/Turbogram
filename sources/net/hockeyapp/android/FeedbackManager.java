package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;

public class FeedbackManager {
    private static final String BROADCAST_ACTION = "net.hockeyapp.android.SCREENSHOT";
    private static final int BROADCAST_REQUEST_CODE = 1;
    public static final int NEW_ANSWER_NOTIFICATION_ID = 2;
    public static final String NOTIFICATION_CHANNEL_ID = "net.hockeyapp.android.NOTIFICATION";
    public static final int SCREENSHOT_NOTIFICATION_ID = 1;
    private static String identifier = null;
    private static FeedbackManagerListener lastListener = null;
    private static boolean notificationActive = false;
    private static BroadcastReceiver receiver = null;
    private static FeedbackUserDataElement requireUserEmail = FeedbackUserDataElement.REQUIRED;
    private static FeedbackUserDataElement requireUserName = FeedbackUserDataElement.REQUIRED;
    private static String urlString = null;
    private static String userEmail;
    private static String userId;
    private static String userName;
    private static WeakReference<Activity> weakActivity;

    /* renamed from: net.hockeyapp.android.FeedbackManager$4 */
    static class C06194 extends BroadcastReceiver {
        C06194() {
        }

        public void onReceive(Context context, Intent intent) {
            FeedbackManager.takeScreenshot(context);
        }
    }

    private static class MediaScannerClient implements MediaScannerConnectionClient {
        private MediaScannerConnection connection = null;
        private String path;

        private MediaScannerClient(String path) {
            this.path = path;
        }

        public void setConnection(MediaScannerConnection connection) {
            this.connection = connection;
        }

        public void onMediaScannerConnected() {
            if (this.connection != null) {
                this.connection.scanFile(this.path, null);
            }
        }

        public void onScanCompleted(String path, Uri uri) {
            HockeyLog.verbose(String.format("Scanned path %s -> URI = %s", new Object[]{path, uri.toString()}));
            this.connection.disconnect();
        }
    }

    public static void register(Context context) {
        register(context, Util.getAppIdentifier(context));
    }

    public static void register(Context context, FeedbackManagerListener listener) {
        register(context, Util.getAppIdentifier(context), listener);
    }

    public static void register(Context context, String appIdentifier) {
        register(context, appIdentifier, null);
    }

    public static void register(Context context, String appIdentifier, FeedbackManagerListener listener) {
        register(context, Constants.BASE_URL, appIdentifier, listener);
    }

    public static void register(Context context, String urlString, String appIdentifier, FeedbackManagerListener listener) {
        if (context != null) {
            identifier = Util.sanitizeAppIdentifier(appIdentifier);
            urlString = urlString;
            lastListener = listener;
            Constants.loadFromContext(context);
        }
    }

    public static void unregister() {
        lastListener = null;
    }

    public static void showFeedbackActivity(Context context, Uri... attachments) {
        showFeedbackActivity(context, null, attachments);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void showFeedbackActivity(Context context, Bundle extras, Uri... attachments) {
        if (urlString == null || identifier == null) {
            HockeyLog.error("FeedbackManager hasn't been registered.");
        } else if (context != null) {
            final Class<?> activityClass = lastListener != null ? lastListener.getFeedbackActivityClass() : null;
            final boolean forceNewThread = lastListener != null && lastListener.shouldCreateNewFeedbackThread();
            final Bundle bundle = extras;
            final Context context2 = context;
            final Uri[] uriArr = attachments;
            AsyncTaskUtils.execute(new AsyncTask<Void, Object, Intent>() {

                /* renamed from: net.hockeyapp.android.FeedbackManager$1$1 */
                class C06151 implements FilenameFilter {
                    C06151() {
                    }

                    public boolean accept(File dir, String name) {
                        return name.endsWith(".jpg");
                    }
                }

                protected Intent doInBackground(Void... voids) {
                    Intent intent = new Intent();
                    if (!(bundle == null || bundle.isEmpty())) {
                        intent.putExtras(bundle);
                    }
                    intent.setFlags(268435456);
                    intent.setClass(context2, activityClass != null ? activityClass : FeedbackActivity.class);
                    intent.putExtra("url", FeedbackManager.getURLString());
                    intent.putExtra(FeedbackActivity.EXTRA_TOKEN, !forceNewThread ? PrefsUtil.getInstance().getFeedbackTokenFromPrefs(context2) : null);
                    intent.putExtra(FeedbackActivity.EXTRA_FORCE_NEW_THREAD, forceNewThread);
                    String userName = FeedbackManager.userName;
                    String userEmail = FeedbackManager.userEmail;
                    String userSubject = null;
                    String nameEmailSubject = PrefsUtil.getInstance().getNameEmailFromPrefs(context2);
                    if (nameEmailSubject != null) {
                        String[] nameEmailSubjectArray = nameEmailSubject.split("\\|");
                        if (nameEmailSubjectArray != null && nameEmailSubjectArray.length >= 2) {
                            userName = nameEmailSubjectArray[0];
                            userEmail = nameEmailSubjectArray[1];
                            if (!forceNewThread && nameEmailSubjectArray.length >= 3) {
                                userSubject = nameEmailSubjectArray[2];
                            }
                        }
                    }
                    intent.putExtra(FeedbackActivity.EXTRA_INITIAL_USER_NAME, userName);
                    intent.putExtra(FeedbackActivity.EXTRA_INITIAL_USER_EMAIL, userEmail);
                    intent.putExtra(FeedbackActivity.EXTRA_INITIAL_USER_SUBJECT, userSubject);
                    intent.putExtra(FeedbackActivity.EXTRA_INITIAL_ATTACHMENTS, getInitialAttachments(uriArr));
                    intent.putExtra(FeedbackActivity.EXTRA_USER_ID, FeedbackManager.userId);
                    return intent;
                }

                protected void onPostExecute(Intent intent) {
                    context2.startActivity(intent);
                }

                private Uri[] getInitialAttachments(Uri[] userAttachments) {
                    ArrayList<Uri> initialAttachments = new ArrayList();
                    File[] screenshots = searchScreenshots();
                    if (screenshots != null) {
                        for (File screenshot : screenshots) {
                            initialAttachments.add(Uri.fromFile(screenshot));
                        }
                    }
                    if (uriArr != null && uriArr.length > 0) {
                        initialAttachments.addAll(Arrays.asList(uriArr));
                    }
                    if (initialAttachments.size() > 0) {
                        return (Uri[]) initialAttachments.toArray(new Uri[0]);
                    }
                    return null;
                }

                private File[] searchScreenshots() {
                    File dir = Constants.getHockeyAppStorageDir(context2);
                    if (dir != null) {
                        return dir.listFiles(new C06151());
                    }
                    return null;
                }
            });
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void checkForAnswersAndNotify(Context context) {
        if (urlString == null || identifier == null) {
            HockeyLog.error("FeedbackManager hasn't been registered.");
            return;
        }
        String token = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(context);
        if (token != null) {
            int lastMessageId = context.getSharedPreferences(ParseFeedbackTask.PREFERENCES_NAME, 0).getInt(ParseFeedbackTask.ID_LAST_MESSAGE_SEND, -1);
            final Context context2 = context;
            AsyncTask sendFeedbackTask = new SendFeedbackTask(context, getURLString(), null, null, null, null, null, null, token, null, true) {
                protected void onPostExecute(HashMap<String, String> result) {
                    super.onPostExecute((HashMap) result);
                    String responseString = (String) result.get("response");
                    if (responseString != null) {
                        AsyncTask task = new ParseFeedbackTask(context2, responseString, null, "fetch");
                        task.setUrlString(FeedbackManager.getURLString());
                        AsyncTaskUtils.execute(task);
                    }
                }
            };
            sendFeedbackTask.setShowProgressDialog(false);
            sendFeedbackTask.setLastMessageId(lastMessageId);
            AsyncTaskUtils.execute(sendFeedbackTask);
        }
    }

    public static FeedbackManagerListener getLastListener() {
        return lastListener;
    }

    private static String getURLString() {
        if (urlString != null && identifier != null) {
            return urlString + "api/2/apps/" + identifier + "/feedback/";
        }
        HockeyLog.error("FeedbackManager hasn't been registered.");
        return null;
    }

    public static FeedbackUserDataElement getRequireUserName() {
        return requireUserName;
    }

    public static void setRequireUserName(FeedbackUserDataElement requireUserName) {
        requireUserName = requireUserName;
    }

    public static FeedbackUserDataElement getRequireUserEmail() {
        return requireUserEmail;
    }

    public static void setRequireUserEmail(FeedbackUserDataElement requireUserEmail) {
        requireUserEmail = requireUserEmail;
    }

    public static void setUserName(String userName) {
        userName = userName;
    }

    public static void setUserEmail(String userEmail) {
        userEmail = userEmail;
    }

    public static void setUserId(String userId) {
        userId = userId;
    }

    public static void setActivityForScreenshot(Activity activity) {
        weakActivity = new WeakReference(activity);
        if (!notificationActive) {
            startScreenshotNotification();
        }
    }

    public static void unsetCurrentActivityForScreenshot(Activity activity) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null && currentActivity == activity) {
            endScreenshotNotification();
            weakActivity = null;
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void takeScreenshot(final Context context) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            View view = currentActivity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            final Bitmap bitmap = view.getDrawingCache();
            final String filename = currentActivity.getLocalClassName();
            AsyncTaskUtils.execute(new AsyncTask<Void, Void, Boolean>() {
                File result;

                protected Boolean doInBackground(Void... args) {
                    File dir = Constants.getHockeyAppStorageDir(context);
                    this.result = new File(dir, filename + ".jpg");
                    int suffix = 1;
                    while (this.result.exists()) {
                        this.result = new File(dir, filename + "_" + suffix + ".jpg");
                        suffix++;
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(this.result);
                        bitmap.compress(CompressFormat.JPEG, 100, out);
                        out.close();
                        HockeyLog.debug("Screenshot '" + this.result.getName() + "' has been saved");
                        return Boolean.valueOf(true);
                    } catch (Throwable e) {
                        HockeyLog.error("Could not save screenshot.", e);
                        return Boolean.valueOf(false);
                    }
                }

                protected void onPostExecute(Boolean success) {
                    if (!success.booleanValue()) {
                        Toast.makeText(context, C0625R.string.hockeyapp_feedback_screenshot_fail, 1).show();
                    }
                }
            });
        }
    }

    private static void startScreenshotNotification() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            notificationActive = true;
            int iconId = currentActivity.getResources().getIdentifier("ic_menu_camera", "drawable", "android");
            Intent intent = new Intent();
            intent.setAction(BROADCAST_ACTION);
            Util.sendNotification(currentActivity, 1, Util.createNotification(currentActivity, PendingIntent.getBroadcast(currentActivity, 1, intent, 1073741824), currentActivity.getString(C0625R.string.hockeyapp_feedback_notification_title), currentActivity.getString(C0625R.string.hockeyapp_feedback_screenshot_notification_message), iconId, NOTIFICATION_CHANNEL_ID), NOTIFICATION_CHANNEL_ID, currentActivity.getString(C0625R.string.hockeyapp_feedback_notification_channel));
            if (receiver == null) {
                receiver = new C06194();
            }
            currentActivity.registerReceiver(receiver, new IntentFilter(BROADCAST_ACTION));
        }
    }

    private static void endScreenshotNotification() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            notificationActive = false;
            currentActivity.unregisterReceiver(receiver);
            Util.cancelNotification(currentActivity, 1);
        }
    }

    private static Activity getCurrentActivity() {
        return weakActivity != null ? (Activity) weakActivity.get() : null;
    }
}
