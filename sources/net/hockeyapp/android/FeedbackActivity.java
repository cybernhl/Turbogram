package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.wallet.WalletConstants;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import net.hockeyapp.android.adapters.MessagesAdapter;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;

public class FeedbackActivity extends Activity implements OnClickListener, OnFocusChangeListener {
    private static final int ATTACH_FILE = 2;
    private static final int ATTACH_PICTURE = 1;
    public static final String EXTRA_FORCE_NEW_THREAD = "forceNewThread";
    public static final String EXTRA_INITIAL_ATTACHMENTS = "initialAttachments";
    public static final String EXTRA_INITIAL_USER_EMAIL = "initialUserEmail";
    public static final String EXTRA_INITIAL_USER_NAME = "initialUserName";
    public static final String EXTRA_INITIAL_USER_SUBJECT = "initialUserSubject";
    public static final String EXTRA_TOKEN = "token";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_USER_ID = "userId";
    private static final int MAX_ATTACHMENTS_PER_MSG = 3;
    private static final int PAINT_IMAGE = 3;
    private AttachmentListView mAttachmentListView;
    private Context mContext;
    private EditText mEmailInput;
    private Handler mFeedbackHandler;
    private boolean mFeedbackViewInitialized;
    private boolean mForceNewThread;
    private boolean mInSendFeedback;
    private List<Uri> mInitialAttachments = new ArrayList();
    private String mInitialUserEmail;
    private String mInitialUserName;
    private String mInitialUserSubject;
    private TextView mLastUpdatedTextView;
    private MessagesAdapter mMessagesAdapter;
    private ListView mMessagesListView;
    private EditText mNameInput;
    private Handler mParseFeedbackHandler;
    private ParseFeedbackTask mParseFeedbackTask;
    private Button mSendFeedbackButton;
    private SendFeedbackTask mSendFeedbackTask;
    private EditText mSubjectInput;
    private EditText mTextInput;
    private String mToken;
    private String mUrl;
    private String mUserId;

    /* renamed from: net.hockeyapp.android.FeedbackActivity$1 */
    class C06111 extends AsyncTask<Void, Object, Object> {
        C06111() {
        }

        protected Object doInBackground(Void... voids) {
            PrefsUtil.getInstance().saveFeedbackTokenToPrefs(FeedbackActivity.this, null);
            FeedbackActivity.this.getSharedPreferences(ParseFeedbackTask.PREFERENCES_NAME, 0).edit().remove(ParseFeedbackTask.ID_LAST_MESSAGE_SEND).remove(ParseFeedbackTask.ID_LAST_MESSAGE_PROCESSED).apply();
            return null;
        }
    }

    private static class FeedbackHandler extends Handler {
        private final WeakReference<FeedbackActivity> mWeakFeedbackActivity;

        FeedbackHandler(FeedbackActivity feedbackActivity) {
            this.mWeakFeedbackActivity = new WeakReference(feedbackActivity);
        }

        public void handleMessage(Message msg) {
            boolean success = false;
            int errorMessage = 0;
            FeedbackActivity feedbackActivity = (FeedbackActivity) this.mWeakFeedbackActivity.get();
            if (feedbackActivity != null) {
                if (msg == null || msg.getData() == null) {
                    errorMessage = C0625R.string.hockeyapp_feedback_send_generic_error;
                } else {
                    Bundle bundle = msg.getData();
                    String responseString = bundle.getString(SendFeedbackTask.BUNDLE_FEEDBACK_RESPONSE);
                    String statusCode = bundle.getString(SendFeedbackTask.BUNDLE_FEEDBACK_STATUS);
                    String requestType = bundle.getString(SendFeedbackTask.BUNDLE_REQUEST_TYPE);
                    if ("send".equals(requestType) && (responseString == null || Integer.parseInt(statusCode) != 201)) {
                        errorMessage = C0625R.string.hockeyapp_feedback_send_generic_error;
                    } else if ("fetch".equals(requestType) && statusCode != null && (Integer.parseInt(statusCode) == WalletConstants.ERROR_CODE_INVALID_PARAMETERS || Integer.parseInt(statusCode) == 422)) {
                        feedbackActivity.resetFeedbackView();
                        success = true;
                    } else if (responseString != null) {
                        feedbackActivity.startParseFeedbackTask(responseString, requestType);
                        if ("send".equals(requestType)) {
                            feedbackActivity.mInitialAttachments.removeAll(feedbackActivity.mAttachmentListView.getAttachments());
                            Toast.makeText(feedbackActivity, C0625R.string.hockeyapp_feedback_sent_toast, 1).show();
                        }
                        success = true;
                    } else {
                        errorMessage = C0625R.string.hockeyapp_feedback_send_network_error;
                    }
                }
                if (!success) {
                    feedbackActivity.showError(errorMessage);
                }
                feedbackActivity.onSendFeedbackResult(success);
            }
        }
    }

    private static class ParseFeedbackHandler extends Handler {
        private final WeakReference<FeedbackActivity> mWeakFeedbackActivity;

        ParseFeedbackHandler(FeedbackActivity feedbackActivity) {
            this.mWeakFeedbackActivity = new WeakReference(feedbackActivity);
        }

        @SuppressLint({"StaticFieldLeak"})
        public void handleMessage(Message msg) {
            boolean success = false;
            final FeedbackActivity feedbackActivity = (FeedbackActivity) this.mWeakFeedbackActivity.get();
            if (feedbackActivity != null) {
                if (!(msg == null || msg.getData() == null)) {
                    final FeedbackResponse feedbackResponse = (FeedbackResponse) msg.getData().getSerializable(ParseFeedbackTask.BUNDLE_PARSE_FEEDBACK_RESPONSE);
                    if (feedbackResponse != null) {
                        if (feedbackResponse.getStatus().equalsIgnoreCase("success")) {
                            success = true;
                            if (feedbackResponse.getToken() != null) {
                                feedbackActivity.mToken = feedbackResponse.getToken();
                                AsyncTaskUtils.execute(new AsyncTask<Void, Object, Object>() {
                                    protected Object doInBackground(Void... voids) {
                                        PrefsUtil.getInstance().saveFeedbackTokenToPrefs(feedbackActivity, feedbackResponse.getToken());
                                        return null;
                                    }
                                });
                                feedbackActivity.loadFeedbackMessages(feedbackResponse);
                                feedbackActivity.mInSendFeedback = false;
                            }
                        } else {
                            success = false;
                        }
                    }
                }
                if (!success) {
                    feedbackActivity.showError(C0625R.string.hockeyapp_dialog_error_message);
                }
                feedbackActivity.enableDisableSendFeedbackButton(true);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        setTitle(C0625R.string.hockeyapp_feedback_title);
        this.mContext = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUrl = extras.getString("url");
            this.mToken = extras.getString(EXTRA_TOKEN);
            this.mForceNewThread = extras.getBoolean(EXTRA_FORCE_NEW_THREAD);
            this.mInitialUserName = extras.getString(EXTRA_INITIAL_USER_NAME);
            this.mInitialUserEmail = extras.getString(EXTRA_INITIAL_USER_EMAIL);
            this.mInitialUserSubject = extras.getString(EXTRA_INITIAL_USER_SUBJECT);
            this.mUserId = extras.getString(EXTRA_USER_ID);
            Parcelable[] initialAttachmentsArray = extras.getParcelableArray(EXTRA_INITIAL_ATTACHMENTS);
            if (initialAttachmentsArray != null) {
                this.mInitialAttachments.clear();
                for (Parcelable parcelable : initialAttachmentsArray) {
                    this.mInitialAttachments.add((Uri) parcelable);
                }
            }
        }
        if (savedInstanceState != null) {
            this.mFeedbackViewInitialized = savedInstanceState.getBoolean("feedbackViewInitialized");
            this.mInSendFeedback = savedInstanceState.getBoolean("inSendFeedback");
            this.mToken = savedInstanceState.getString(EXTRA_TOKEN);
        } else {
            this.mInSendFeedback = false;
            this.mFeedbackViewInitialized = false;
        }
        Util.cancelNotification(this, 2);
        initFeedbackHandler();
        initParseFeedbackHandler();
        restoreSendFeedbackTask();
        configureAppropriateView();
    }

    private void restoreSendFeedbackTask() {
        Object object = getLastNonConfigurationInstance();
        if (object != null && (object instanceof SendFeedbackTask)) {
            this.mSendFeedbackTask = (SendFeedbackTask) object;
            this.mSendFeedbackTask.setHandler(this.mFeedbackHandler);
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ArrayList<Uri> attachmentsUris = savedInstanceState.getParcelableArrayList("attachments");
            if (attachmentsUris != null) {
                Iterator it = attachmentsUris.iterator();
                while (it.hasNext()) {
                    Uri attachmentUri = (Uri) it.next();
                    if (!this.mInitialAttachments.contains(attachmentUri)) {
                        this.mAttachmentListView.addView(new AttachmentView((Context) this, this.mAttachmentListView, attachmentUri, true));
                    }
                }
            }
            this.mFeedbackViewInitialized = savedInstanceState.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("attachments", this.mAttachmentListView.getAttachments());
        outState.putBoolean("feedbackViewInitialized", this.mFeedbackViewInitialized);
        outState.putBoolean("inSendFeedback", this.mInSendFeedback);
        outState.putString(EXTRA_TOKEN, this.mToken);
        super.onSaveInstanceState(outState);
    }

    protected void onStart() {
        super.onStart();
        if (this.mSendFeedbackTask != null) {
            this.mSendFeedbackTask.attach(this);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.mSendFeedbackTask != null) {
            this.mSendFeedbackTask.detach();
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.mSendFeedbackTask != null) {
            this.mSendFeedbackTask.detach();
        }
        return this.mSendFeedbackTask;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (this.mInSendFeedback) {
            this.mInSendFeedback = false;
            configureAppropriateView();
        } else {
            finish();
        }
        return true;
    }

    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == C0625R.id.button_send) {
            sendFeedback();
        } else if (viewId == C0625R.id.button_attachment) {
            if (this.mAttachmentListView.getChildCount() >= 3) {
                Toast.makeText(this, getString(C0625R.string.hockeyapp_feedback_max_attachments_allowed, new Object[]{Integer.valueOf(3)}), 0).show();
                return;
            }
            openContextMenu(v);
        } else if (viewId == C0625R.id.button_add_response) {
            this.mInSendFeedback = true;
            configureFeedbackView(false);
        } else if (viewId == C0625R.id.button_refresh) {
            sendFetchFeedback(this.mUrl, null, null, null, null, null, null, this.mToken, this.mFeedbackHandler, true);
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        if (v instanceof EditText) {
            showKeyboard(v);
        } else if ((v instanceof Button) || (v instanceof ImageButton)) {
            hideKeyboard();
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 2, 0, getString(C0625R.string.hockeyapp_feedback_attach_file));
        menu.add(0, 1, 0, getString(C0625R.string.hockeyapp_feedback_attach_picture));
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
            case 2:
                return addAttachment(item.getItemId());
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            Uri uri;
            if (requestCode == 2) {
                uri = data.getData();
                if (uri != null) {
                    this.mAttachmentListView.addView(new AttachmentView((Context) this, this.mAttachmentListView, uri, true));
                    Util.announceForAccessibility(this.mAttachmentListView, getString(C0625R.string.hockeyapp_feedback_attachment_added));
                }
            } else if (requestCode == 1) {
                uri = data.getData();
                if (uri != null) {
                    try {
                        Intent intent = new Intent(this, PaintActivity.class);
                        intent.putExtra(PaintActivity.EXTRA_IMAGE_URI, uri);
                        startActivityForResult(intent, 3);
                    } catch (Throwable e) {
                        HockeyLog.error("Paint activity not declared!", e);
                    }
                }
            } else if (requestCode == 3) {
                uri = (Uri) data.getParcelableExtra(PaintActivity.EXTRA_IMAGE_URI);
                if (uri != null) {
                    this.mAttachmentListView.addView(new AttachmentView((Context) this, this.mAttachmentListView, uri, true));
                    Util.announceForAccessibility(this.mAttachmentListView, getString(C0625R.string.hockeyapp_feedback_attachment_added));
                }
            }
        }
    }

    @SuppressLint({"InflateParams"})
    public View getLayoutView() {
        return getLayoutInflater().inflate(C0625R.layout.hockeyapp_activity_feedback, null);
    }

    public void enableDisableSendFeedbackButton(boolean isEnable) {
        if (this.mSendFeedbackButton != null) {
            this.mSendFeedbackButton.setEnabled(isEnable);
        }
    }

    protected void configureFeedbackView(boolean haveToken) {
        ScrollView feedbackScrollView = (ScrollView) findViewById(C0625R.id.wrapper_feedback_scroll);
        LinearLayout wrapperLayoutFeedbackAndMessages = (LinearLayout) findViewById(C0625R.id.wrapper_messages);
        this.mMessagesListView = (ListView) findViewById(C0625R.id.list_feedback_messages);
        this.mAttachmentListView = (AttachmentListView) findViewById(C0625R.id.wrapper_attachments);
        if (haveToken) {
            wrapperLayoutFeedbackAndMessages.setVisibility(0);
            feedbackScrollView.setVisibility(8);
            this.mLastUpdatedTextView = (TextView) findViewById(C0625R.id.label_last_updated);
            this.mLastUpdatedTextView.setVisibility(4);
            Button addResponseButton = (Button) findViewById(C0625R.id.button_add_response);
            addResponseButton.setOnClickListener(this);
            addResponseButton.setOnFocusChangeListener(this);
            Button refreshButton = (Button) findViewById(C0625R.id.button_refresh);
            refreshButton.setOnClickListener(this);
            refreshButton.setOnFocusChangeListener(this);
            return;
        }
        int i;
        wrapperLayoutFeedbackAndMessages.setVisibility(8);
        feedbackScrollView.setVisibility(0);
        this.mNameInput = (EditText) findViewById(C0625R.id.input_name);
        this.mNameInput.setOnFocusChangeListener(this);
        this.mEmailInput = (EditText) findViewById(C0625R.id.input_email);
        this.mEmailInput.setOnFocusChangeListener(this);
        this.mSubjectInput = (EditText) findViewById(C0625R.id.input_subject);
        this.mSubjectInput.setOnFocusChangeListener(this);
        this.mTextInput = (EditText) findViewById(C0625R.id.input_message);
        this.mTextInput.setOnFocusChangeListener(this);
        configureHints();
        if (!this.mFeedbackViewInitialized) {
            this.mNameInput.setText(this.mInitialUserName);
            this.mEmailInput.setText(this.mInitialUserEmail);
            this.mSubjectInput.setText(this.mInitialUserSubject);
            if (TextUtils.isEmpty(this.mInitialUserName)) {
                this.mNameInput.requestFocus();
            } else if (TextUtils.isEmpty(this.mInitialUserEmail)) {
                this.mEmailInput.requestFocus();
            } else if (TextUtils.isEmpty(this.mInitialUserSubject)) {
                this.mSubjectInput.requestFocus();
            } else {
                this.mTextInput.requestFocus();
            }
            this.mFeedbackViewInitialized = true;
        }
        EditText editText = this.mNameInput;
        if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.DONT_SHOW) {
            i = 8;
        } else {
            i = 0;
        }
        editText.setVisibility(i);
        editText = this.mEmailInput;
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.DONT_SHOW) {
            i = 8;
        } else {
            i = 0;
        }
        editText.setVisibility(i);
        this.mTextInput.setText("");
        if ((!this.mForceNewThread || this.mInSendFeedback) && this.mToken != null) {
            this.mSubjectInput.setVisibility(8);
        } else {
            this.mSubjectInput.setVisibility(0);
        }
        this.mAttachmentListView.removeAllViews();
        for (Uri attachmentUri : this.mInitialAttachments) {
            this.mAttachmentListView.addView(new AttachmentView((Context) this, this.mAttachmentListView, attachmentUri, true));
        }
        Button addAttachmentButton = (Button) findViewById(C0625R.id.button_attachment);
        addAttachmentButton.setOnClickListener(this);
        addAttachmentButton.setOnFocusChangeListener(this);
        registerForContextMenu(addAttachmentButton);
        this.mSendFeedbackButton = (Button) findViewById(C0625R.id.button_send);
        this.mSendFeedbackButton.setOnClickListener(this);
        addAttachmentButton.setOnFocusChangeListener(this);
    }

    protected void onSendFeedbackResult(boolean success) {
    }

    private boolean addAttachment(int request) {
        Intent intent;
        if (request == 2) {
            intent = new Intent();
            intent.setType("*/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, getString(C0625R.string.hockeyapp_feedback_select_file)), 2);
            return true;
        } else if (request != 1) {
            return false;
        } else {
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, getString(C0625R.string.hockeyapp_feedback_select_picture)), 1);
            return true;
        }
    }

    private void configureHints() {
        if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.REQUIRED) {
            this.mNameInput.setHint(getString(C0625R.string.hockeyapp_feedback_name_hint_required));
        }
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED) {
            this.mEmailInput.setHint(getString(C0625R.string.hockeyapp_feedback_email_hint_required));
        }
        this.mSubjectInput.setHint(getString(C0625R.string.hockeyapp_feedback_subject_hint_required));
        this.mTextInput.setHint(getString(C0625R.string.hockeyapp_feedback_message_hint_required));
    }

    private void configureAppropriateView() {
        if (this.mToken == null || this.mInSendFeedback) {
            configureFeedbackView(false);
            return;
        }
        configureFeedbackView(true);
        sendFetchFeedback(this.mUrl, null, null, null, null, null, null, this.mToken, this.mFeedbackHandler, true);
    }

    private void createParseFeedbackTask(String feedbackResponseString, String requestType) {
        this.mParseFeedbackTask = new ParseFeedbackTask(this, feedbackResponseString, this.mParseFeedbackHandler, requestType);
    }

    private void showKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(view, 1);
    }

    private void hideKeyboard() {
        if (this.mTextInput != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.mTextInput.getWindowToken(), 0);
        }
    }

    private void showError(int message) {
        new Builder(this).setTitle(C0625R.string.hockeyapp_dialog_error_title).setMessage(message).setCancelable(false).setPositiveButton(C0625R.string.hockeyapp_dialog_positive_button, null).create().show();
    }

    private void initFeedbackHandler() {
        this.mFeedbackHandler = new FeedbackHandler(this);
    }

    private void initParseFeedbackHandler() {
        this.mParseFeedbackHandler = new ParseFeedbackHandler(this);
    }

    @SuppressLint({"SimpleDateFormat"})
    private void loadFeedbackMessages(FeedbackResponse feedbackResponse) {
        configureFeedbackView(true);
        if (feedbackResponse != null && feedbackResponse.getFeedback() != null && feedbackResponse.getFeedback().getMessages() != null && feedbackResponse.getFeedback().getMessages().size() > 0) {
            ArrayList<FeedbackMessage> feedbackMessages = feedbackResponse.getFeedback().getMessages();
            Collections.reverse(feedbackMessages);
            try {
                DateFormat dateFormatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                dateFormatIn.setTimeZone(TimeZone.getTimeZone("UTC"));
                DateFormat dateFormatOut = DateFormat.getDateTimeInstance(3, 3);
                Date date = dateFormatIn.parse(((FeedbackMessage) feedbackMessages.get(0)).getCreatedAt());
                this.mLastUpdatedTextView.setText(String.format(getString(C0625R.string.hockeyapp_feedback_last_updated_text), new Object[]{dateFormatOut.format(date)}));
                this.mLastUpdatedTextView.setContentDescription(this.mLastUpdatedTextView.getText());
                this.mLastUpdatedTextView.setVisibility(0);
            } catch (Throwable e1) {
                HockeyLog.error("Failed to parse feedback", e1);
            }
            if (this.mMessagesAdapter == null) {
                this.mMessagesAdapter = new MessagesAdapter(this.mContext, feedbackMessages);
            } else {
                this.mMessagesAdapter.clear();
                Iterator it = feedbackMessages.iterator();
                while (it.hasNext()) {
                    this.mMessagesAdapter.add((FeedbackMessage) it.next());
                }
                this.mMessagesAdapter.notifyDataSetChanged();
            }
            this.mMessagesListView.setAdapter(this.mMessagesAdapter);
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    private void resetFeedbackView() {
        this.mToken = null;
        AsyncTaskUtils.execute(new C06111());
        configureFeedbackView(false);
    }

    @SuppressLint({"StaticFieldLeak"})
    private void sendFeedback() {
        if (Util.isConnectedToNetwork(this)) {
            enableDisableSendFeedbackButton(false);
            String token = (!this.mForceNewThread || this.mInSendFeedback) ? this.mToken : null;
            final String name = this.mNameInput.getText().toString().trim();
            final String email = this.mEmailInput.getText().toString().trim();
            final String subject = this.mSubjectInput.getText().toString().trim();
            String text = this.mTextInput.getText().toString().trim();
            if (TextUtils.isEmpty(subject)) {
                this.mSubjectInput.setVisibility(0);
                setError(this.mSubjectInput, C0625R.string.hockeyapp_feedback_validate_subject_error);
                return;
            } else if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(name)) {
                setError(this.mNameInput, C0625R.string.hockeyapp_feedback_validate_name_error);
                return;
            } else if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(email)) {
                setError(this.mEmailInput, C0625R.string.hockeyapp_feedback_validate_email_empty);
                return;
            } else if (TextUtils.isEmpty(text)) {
                setError(this.mTextInput, C0625R.string.hockeyapp_feedback_validate_text_error);
                return;
            } else if (FeedbackManager.getRequireUserEmail() != FeedbackUserDataElement.REQUIRED || Util.isValidEmail(email)) {
                AsyncTaskUtils.execute(new AsyncTask<Void, Object, Object>() {
                    protected Object doInBackground(Void... voids) {
                        PrefsUtil.getInstance().saveNameEmailSubjectToPrefs(FeedbackActivity.this.mContext, name, email, subject);
                        return null;
                    }
                });
                sendFetchFeedback(this.mUrl, name, email, subject, text, this.mUserId, this.mAttachmentListView.getAttachments(), token, this.mFeedbackHandler, false);
                hideKeyboard();
                return;
            } else {
                setError(this.mEmailInput, C0625R.string.hockeyapp_feedback_validate_email_error);
                return;
            }
        }
        Toast.makeText(this, C0625R.string.hockeyapp_error_no_network_message, 1).show();
    }

    private void setError(final EditText inputField, int feedbackStringId) {
        inputField.setError(getString(feedbackStringId));
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                inputField.requestFocus();
            }
        });
        enableDisableSendFeedbackButton(true);
    }

    private void sendFetchFeedback(String url, String name, String email, String subject, String text, String userId, List<Uri> attachmentUris, String token, Handler feedbackHandler, boolean isFetchMessages) {
        this.mSendFeedbackTask = new SendFeedbackTask(this.mContext, url, name, email, subject, text, userId, attachmentUris, token, feedbackHandler, isFetchMessages);
        AsyncTaskUtils.execute(this.mSendFeedbackTask);
    }

    private void startParseFeedbackTask(String feedbackResponseString, String requestType) {
        createParseFeedbackTask(feedbackResponseString, requestType);
        AsyncTaskUtils.execute(this.mParseFeedbackTask);
    }
}
