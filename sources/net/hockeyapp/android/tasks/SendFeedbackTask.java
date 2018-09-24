package net.hockeyapp.android.tasks;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.media.ExifInterface;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.io.File;
import java.io.FilenameFilter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.hockeyapp.android.C0625R;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import net.hockeyapp.android.utils.Util;

@SuppressLint({"StaticFieldLeak"})
public class SendFeedbackTask extends ConnectionTask<Void, Void, HashMap<String, String>> {
    public static final String BUNDLE_FEEDBACK_RESPONSE = "feedback_response";
    public static final String BUNDLE_FEEDBACK_STATUS = "feedback_status";
    public static final String BUNDLE_REQUEST_TYPE = "request_type";
    private static final String FILE_TAG = "HockeyApp";
    private static final String TAG = "SendFeedbackTask";
    private List<Uri> mAttachmentUris;
    private Context mContext;
    private String mEmail;
    private Handler mHandler;
    private boolean mIsFetchMessages;
    private int mLastMessageId = -1;
    private String mName;
    private ProgressDialog mProgressDialog;
    private boolean mShowProgressDialog = true;
    private String mSubject;
    private String mText;
    private String mToken;
    private String mUrlString;
    private String mUserId;

    /* renamed from: net.hockeyapp.android.tasks.SendFeedbackTask$1 */
    class C06431 implements FilenameFilter {
        C06431() {
        }

        public boolean accept(File dir, String name) {
            return name.endsWith(".jpg");
        }
    }

    public SendFeedbackTask(Context context, String urlString, String name, String email, String subject, String text, String userId, List<Uri> attachmentUris, String token, Handler handler, boolean isFetchMessages) {
        this.mContext = context;
        this.mUrlString = urlString;
        this.mName = name;
        this.mEmail = email;
        this.mSubject = subject;
        this.mText = text;
        this.mUserId = userId;
        this.mAttachmentUris = attachmentUris;
        this.mToken = token;
        this.mHandler = handler;
        this.mIsFetchMessages = isFetchMessages;
        if (context != null) {
            Constants.loadFromContext(context);
        }
    }

    public void setShowProgressDialog(boolean showProgressDialog) {
        this.mShowProgressDialog = showProgressDialog;
    }

    public void setLastMessageId(int lastMessageId) {
        this.mLastMessageId = lastMessageId;
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void attach(Context context) {
        this.mContext = context;
        if (getStatus() != Status.RUNNING) {
            return;
        }
        if ((this.mProgressDialog == null || !this.mProgressDialog.isShowing()) && this.mShowProgressDialog) {
            this.mProgressDialog = ProgressDialog.show(this.mContext, "", getLoadingMessage(), true, false);
        }
    }

    public void detach() {
        this.mContext = null;
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected void onPreExecute() {
        if ((this.mProgressDialog == null || !this.mProgressDialog.isShowing()) && this.mShowProgressDialog) {
            this.mProgressDialog = ProgressDialog.show(this.mContext, "", getLoadingMessage(), true, false);
        }
    }

    protected HashMap<String, String> doInBackground(Void... args) {
        if (this.mIsFetchMessages && this.mToken != null) {
            return doGet();
        }
        if (this.mIsFetchMessages) {
            return null;
        }
        if (this.mAttachmentUris.isEmpty()) {
            return doPostPut();
        }
        HashMap<String, String> result = doPostPutWithAttachments();
        if (result == null) {
            return result;
        }
        clearTemporaryFolder(result);
        return result;
    }

    private void clearTemporaryFolder(HashMap<String, String> result) {
        int i = 0;
        String status = (String) result.get(NotificationCompat.CATEGORY_STATUS);
        if (status != null && status.startsWith(ExifInterface.GPS_MEASUREMENT_2D) && this.mContext != null) {
            File folder = new File(this.mContext.getCacheDir(), "HockeyApp");
            if (folder.exists()) {
                for (File file : folder.listFiles()) {
                    if (!(file == null || Boolean.valueOf(file.delete()).booleanValue())) {
                        HockeyLog.debug(TAG, "Error deleting file from temporary folder");
                    }
                }
            }
            File[] screenshots = Constants.getHockeyAppStorageDir(this.mContext).listFiles(new C06431());
            int length = screenshots.length;
            while (i < length) {
                File screenshot = screenshots[i];
                if (this.mAttachmentUris.contains(Uri.fromFile(screenshot))) {
                    if (screenshot.delete()) {
                        HockeyLog.debug(TAG, "Screenshot '" + screenshot.getName() + "' has been deleted");
                    } else {
                        HockeyLog.error(TAG, "Error deleting screenshot");
                    }
                }
                i++;
            }
        }
    }

    protected void onPostExecute(HashMap<String, String> result) {
        if (this.mProgressDialog != null) {
            try {
                this.mProgressDialog.dismiss();
            } catch (Exception e) {
            }
        }
        if (this.mHandler != null) {
            Message msg = new Message();
            Bundle bundle = new Bundle();
            if (result != null) {
                bundle.putString(BUNDLE_REQUEST_TYPE, (String) result.get(Param.TYPE));
                bundle.putString(BUNDLE_FEEDBACK_RESPONSE, (String) result.get("response"));
                bundle.putString(BUNDLE_FEEDBACK_STATUS, (String) result.get(NotificationCompat.CATEGORY_STATUS));
            } else {
                bundle.putString(BUNDLE_REQUEST_TYPE, "unknown");
            }
            msg.setData(bundle);
            this.mHandler.sendMessage(msg);
        }
    }

    private HashMap<String, String> doPostPut() {
        HashMap<String, String> result = new HashMap();
        result.put(Param.TYPE, "send");
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> parameters = new HashMap();
            parameters.put("name", this.mName);
            parameters.put("email", this.mEmail);
            parameters.put("subject", this.mSubject);
            parameters.put("text", this.mText);
            parameters.put("bundle_identifier", Constants.APP_PACKAGE);
            parameters.put("bundle_short_version", Constants.APP_VERSION_NAME);
            parameters.put("bundle_version", Constants.APP_VERSION);
            parameters.put("os_version", Constants.ANDROID_VERSION);
            parameters.put("oem", Constants.PHONE_MANUFACTURER);
            parameters.put("model", Constants.PHONE_MODEL);
            parameters.put("sdk_version", "5.1.0");
            if (this.mUserId != null) {
                parameters.put("user_string", this.mUserId);
            }
            if (this.mToken != null) {
                this.mUrlString += this.mToken + "/";
            }
            urlConnection = new HttpURLConnectionBuilder(this.mUrlString).setRequestMethod(this.mToken != null ? "PUT" : "POST").writeFormFields(parameters).build();
            urlConnection.connect();
            result.put(NotificationCompat.CATEGORY_STATUS, String.valueOf(urlConnection.getResponseCode()));
            result.put("response", ConnectionTask.getStringFromConnection(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable e) {
            HockeyLog.error("Failed to send feedback message", e);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private HashMap<String, String> doPostPutWithAttachments() {
        HashMap<String, String> result = new HashMap();
        result.put(Param.TYPE, "send");
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> parameters = new HashMap();
            parameters.put("name", this.mName);
            parameters.put("email", this.mEmail);
            parameters.put("subject", this.mSubject);
            parameters.put("text", this.mText);
            parameters.put("bundle_identifier", Constants.APP_PACKAGE);
            parameters.put("bundle_short_version", Constants.APP_VERSION_NAME);
            parameters.put("bundle_version", Constants.APP_VERSION);
            parameters.put("os_version", Constants.ANDROID_VERSION);
            parameters.put("oem", Constants.PHONE_MANUFACTURER);
            parameters.put("model", Constants.PHONE_MODEL);
            parameters.put("sdk_version", "5.1.0");
            if (this.mUserId != null) {
                parameters.put("user_string", this.mUserId);
            }
            if (this.mToken != null) {
                this.mUrlString += this.mToken + "/";
            }
            urlConnection = new HttpURLConnectionBuilder(this.mUrlString).setRequestMethod(this.mToken != null ? "PUT" : "POST").writeMultipartData(parameters, this.mContext, this.mAttachmentUris).build();
            urlConnection.connect();
            result.put(NotificationCompat.CATEGORY_STATUS, String.valueOf(urlConnection.getResponseCode()));
            result.put("response", ConnectionTask.getStringFromConnection(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable e) {
            HockeyLog.error("Failed to send feedback message", e);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private HashMap<String, String> doGet() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mUrlString).append(Util.encodeParam(this.mToken));
        if (this.mLastMessageId != -1) {
            sb.append("?last_message_id=").append(this.mLastMessageId);
        }
        HashMap<String, String> result = new HashMap();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = new HttpURLConnectionBuilder(sb.toString()).build();
            result.put(Param.TYPE, "fetch");
            urlConnection.connect();
            result.put(NotificationCompat.CATEGORY_STATUS, String.valueOf(urlConnection.getResponseCode()));
            result.put("response", ConnectionTask.getStringFromConnection(urlConnection));
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable e) {
            HockeyLog.error("Failed to fetching feedback messages", e);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private String getLoadingMessage() {
        String loadingMessage = this.mContext.getString(C0625R.string.hockeyapp_feedback_sending_feedback_text);
        if (this.mIsFetchMessages) {
            return this.mContext.getString(C0625R.string.hockeyapp_feedback_fetching_feedback_text);
        }
        return loadingMessage;
    }
}
