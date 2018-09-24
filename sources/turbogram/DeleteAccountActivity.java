package turbogram;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.baranak.turbogramf.R;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.HintEditText;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SlideView;
import org.telegram.ui.CountrySelectActivity;
import org.telegram.ui.CountrySelectActivity.CountrySelectActivityDelegate;
import turbogram.Utilities.HttpClient;
import turbogram.Utilities.HttpClient.Response;
import turbogram.Utilities.TurboUtils;

public class DeleteAccountActivity extends BaseFragment {
    private CookieManager cookieManager = new CookieManager();
    private int currentViewNum = 0;
    private ActionBarMenuItem doneButton;
    private SlideView[] views = new SlideView[3];

    /* renamed from: turbogram.DeleteAccountActivity$1 */
    class C23021 extends ActionBarMenuOnItemClick {
        C23021() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                DeleteAccountActivity.this.onBackPressed();
            } else if (id == 1) {
                DeleteAccountActivity.this.views[DeleteAccountActivity.this.currentViewNum].onNextPressed();
            }
        }
    }

    public class CodeView extends SlideView {
        private String code;
        private EditText codeField;
        private TextView description;
        private String hash;
        private String phone;
        private TextView resultTextView;

        public CodeView(Context context) {
            super(context);
            setOrientation(1);
            this.codeField = new EditText(context);
            this.codeField.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.codeField.setHint(LocaleController.getString("Code", R.string.Code));
            this.codeField.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            this.codeField.setBackgroundDrawable(Theme.createEditTextDrawable(context, false));
            this.codeField.setImeOptions(268435461);
            this.codeField.setTextSize(1, 18.0f);
            this.codeField.setMaxLines(1);
            this.codeField.setPadding(0, 0, 0, 0);
            this.codeField.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.codeField, LayoutHelper.createLinear(-1, 36, 1, 0, 20, 0, 0));
            this.resultTextView = new TextView(context);
            this.resultTextView.setGravity(LocaleController.isRTL ? 5 : 3);
            this.resultTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.resultTextView.setTextSize(1, 14.0f);
            this.resultTextView.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.resultTextView, LayoutHelper.createLinear(-1, -2, LocaleController.isRTL ? 5 : 3, 0, 10, 0, 0));
            this.description = new TextView(context);
            this.description.setText(LocaleController.getString("DeleteAccountCodeDes", R.string.DeleteAccountCodeDes) + LocaleController.getString("DeleteAccountDes", R.string.DeleteAccountDes));
            this.description.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.description.setTextSize(1, 14.0f);
            this.description.setGravity(LocaleController.isRTL ? 5 : 3);
            this.description.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
            this.description.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.description, LayoutHelper.createLinear(-2, -2, LocaleController.isRTL ? 5 : 3, 0, 0, 0, 10));
            TextView cabcelButton = new TextView(context);
            cabcelButton.setTypeface(TurboUtils.getTurboTypeFace());
            cabcelButton.setGravity((LocaleController.isRTL ? 5 : 3) | 48);
            cabcelButton.setTextColor(Theme.getColor(Theme.key_chats_attachMessage));
            cabcelButton.setText(LocaleController.getString("DeleteAccountCancel", R.string.DeleteAccountCancel));
            cabcelButton.setTextSize(1, 14.0f);
            cabcelButton.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
            cabcelButton.setPadding(0, AndroidUtilities.dp(14.0f), 0, 0);
            addView(cabcelButton, LayoutHelper.createLinear(-2, -2, (LocaleController.isRTL ? 5 : 3) | 48));
            cabcelButton.setOnClickListener(new OnClickListener(DeleteAccountActivity.this) {
                public void onClick(View v) {
                    DeleteAccountActivity.this.setPage(0, true, null, true);
                }
            });
        }

        public void setParams(Bundle params, boolean restore) {
            if (params != null) {
                if (params.containsKey("phone")) {
                    this.phone = params.getString("phone");
                }
                if (params.containsKey("random_hash")) {
                    this.hash = params.getString("random_hash");
                }
            }
        }

        public String getHeaderName() {
            return LocaleController.getString("DeleteAccount", R.string.DeleteAccount);
        }

        public void onNextPressed() {
            this.code = this.codeField.getText().toString();
            if (this.code.length() > 0) {
                new DeleteAccountTask(1, this.resultTextView).execute(new String[]{this.phone, this.hash, this.code});
                AndroidUtilities.hideKeyboard(this.codeField);
                return;
            }
            this.resultTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
            this.resultTextView.setText(LocaleController.getString("DeleteAccountPasswordError", R.string.DeleteAccountPasswordError));
        }

        public void onBackPressed() {
            Editor edit = ApplicationLoader.applicationContext.getSharedPreferences("delete_account", 0).edit();
            edit.remove("phone");
            edit.remove("random_hash");
            edit.commit();
            DeleteAccountActivity.this.setPage(0, false, null, true);
        }

        public boolean needBackButton() {
            return true;
        }
    }

    private class DeleteAccountTask extends AsyncTask<String, Void, Response> {
        private String[] inputs;
        private TextView resTextView;
        private int step;

        public DeleteAccountTask(int step, TextView resTextView) {
            this.step = step;
            this.resTextView = resTextView;
        }

        protected void onPreExecute() {
            this.resTextView.setText(LocaleController.getString("DeleteAccountConnecting", R.string.DeleteAccountConnecting));
            this.resTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            if (this.step == 1) {
                Editor edit = ApplicationLoader.applicationContext.getSharedPreferences("delete_account", 0).edit();
                edit.remove("phone");
                edit.remove("random_hash");
                edit.commit();
            }
            DeleteAccountActivity.this.doneButton.setVisibility(8);
        }

        protected Response doInBackground(String... params) {
            switch (this.step) {
                case 0:
                    this.inputs = params;
                    HashMap<String, String> input = new HashMap();
                    input.put("phone", params[0]);
                    return HttpClient.performPostCall("https://my.telegram.org/auth/send_password", input, DeleteAccountActivity.this.cookieManager.getCookieStore().getCookies());
                case 1:
                    this.inputs = params;
                    HashMap<String, String> input2 = new HashMap();
                    input2.put("phone", params[0]);
                    input2.put("random_hash", params[1]);
                    input2.put("password", params[2]);
                    return HttpClient.performPostCall("https://my.telegram.org/auth/login", input2, DeleteAccountActivity.this.cookieManager.getCookieStore().getCookies());
                case 2:
                    this.inputs = params;
                    new HashMap().put("phone", params[0]);
                    return HttpClient.performGetCall("https://my.telegram.org/deactivate", DeleteAccountActivity.this.cookieManager.getCookieStore().getCookies());
                case 3:
                    this.inputs = params;
                    HashMap<String, String> input4 = new HashMap();
                    input4.put("hash", params[0]);
                    input4.put("message", "Remove all contacts and chats.");
                    return HttpClient.performPostCall("https://my.telegram.org/deactivate/do_delete", input4, DeleteAccountActivity.this.cookieManager.getCookieStore().getCookies());
                default:
                    return null;
            }
        }

        protected void onProgressUpdate(Void... values) {
        }

        protected void onPostExecute(Response result) {
            if (result.cookies != null) {
                for (String cookie : result.cookies) {
                    DeleteAccountActivity.this.cookieManager.getCookieStore().add(null, (HttpCookie) HttpCookie.parse(cookie).get(0));
                }
            }
            if (this.resTextView != null) {
                if (result.code != Callback.DEFAULT_DRAG_ANIMATION_DURATION || result.text == null) {
                    this.resTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
                    this.resTextView.setText(LocaleController.getString("ErrorOccurred", R.string.ErrorOccurred));
                    DeleteAccountActivity.this.doneButton.setVisibility(0);
                    return;
                }
                Bundle bundle;
                switch (this.step) {
                    case 0:
                        try {
                            String randomHash = new JSONObject(result.text).getString("random_hash");
                            Editor edit = ApplicationLoader.applicationContext.getSharedPreferences("delete_account", 0).edit();
                            edit.putString("phone", this.inputs[0]);
                            edit.putString("random_hash", randomHash);
                            edit.commit();
                            bundle = new Bundle();
                            bundle.putString("phone", this.inputs[0]);
                            bundle.putString("random_hash", randomHash);
                            DeleteAccountActivity.this.setPage(1, true, bundle, true);
                            this.resTextView.setText("");
                            return;
                        } catch (Exception e) {
                            this.resTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
                            this.resTextView.setText(LocaleController.getString("ErrorOccurred", R.string.ErrorOccurred));
                            DeleteAccountActivity.this.doneButton.setVisibility(0);
                            return;
                        }
                    case 1:
                        bundle = new Bundle();
                        bundle.putString("phone", this.inputs[0]);
                        DeleteAccountActivity.this.setPage(2, true, bundle, true);
                        return;
                    case 2:
                        String phone = this.inputs[0];
                        String hash = null;
                        Matcher matcher = Pattern.compile("hash: '(\\w+)',", 32).matcher(result.text);
                        while (matcher.find()) {
                            hash = matcher.group(1);
                        }
                        if (hash != null) {
                            new DeleteAccountTask(3, this.resTextView).execute(new String[]{hash, phone});
                            return;
                        }
                        this.resTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
                        this.resTextView.setText(LocaleController.getString("ErrorOccurred", R.string.ErrorOccurred));
                        DeleteAccountActivity.this.doneButton.setVisibility(0);
                        return;
                    case 3:
                        this.resTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
                        this.resTextView.setText(LocaleController.getString("DeleteAccountEnd", R.string.DeleteAccountEnd));
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class FinalView extends SlideView {
        private TextView applyTextView;
        private TextView applyTextViewDes;
        private TextView description;
        private String phone;
        private TextView resultTextView;

        /* renamed from: turbogram.DeleteAccountActivity$FinalView$3 */
        class C23093 implements DialogInterface.OnClickListener {
            C23093() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                new DeleteAccountTask(2, FinalView.this.resultTextView).execute(new String[]{FinalView.this.phone});
            }
        }

        public FinalView(Context context) {
            super(context);
            setOrientation(1);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius((float) AndroidUtilities.dp(100.0f));
            drawable.setStroke(AndroidUtilities.dp(20.0f), TurboUtils.getLighterColor(Theme.getColor(Theme.key_chats_attachMessage), 0.5f));
            drawable.setColor(Theme.getColor(Theme.key_chats_attachMessage));
            this.applyTextView = new TextView(context);
            this.applyTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundGray));
            this.applyTextView.setText(LocaleController.getString("ApplyTheme", R.string.ApplyTheme));
            this.applyTextView.setBackgroundDrawable(drawable);
            this.applyTextView.setGravity(17);
            this.applyTextView.setTextSize(1, 24.0f);
            this.applyTextView.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.applyTextView, LayoutHelper.createLinear(110, 110, 17));
            this.applyTextView.setOnClickListener(new OnClickListener(DeleteAccountActivity.this) {

                /* renamed from: turbogram.DeleteAccountActivity$FinalView$1$1 */
                class C23061 implements DialogInterface.OnClickListener {
                    C23061() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new DeleteAccountTask(2, FinalView.this.resultTextView).execute(new String[]{FinalView.this.phone});
                    }
                }

                public void onClick(View v) {
                    Builder builder = new Builder(DeleteAccountActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    builder.setMessage(LocaleController.getString("DeleteAccountApplyDialog", R.string.DeleteAccountApplyDialog));
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C23061());
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    DeleteAccountActivity.this.showDialog(builder.create());
                }
            });
            this.applyTextViewDes = new TextView(context);
            this.applyTextViewDes.setText(LocaleController.getString("DeleteAccountApplyDes", R.string.DeleteAccountApplyDes));
            this.applyTextViewDes.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.applyTextViewDes.setGravity(1);
            this.applyTextViewDes.setTextSize(1, 14.0f);
            this.applyTextViewDes.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.applyTextViewDes, LayoutHelper.createLinear(-1, -2, 0.0f, 10.0f, 0.0f, 0.0f));
            this.resultTextView = new TextView(context);
            this.resultTextView.setGravity(LocaleController.isRTL ? 5 : 3);
            this.resultTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.resultTextView.setTextSize(1, 14.0f);
            this.resultTextView.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.resultTextView, LayoutHelper.createLinear(-1, -2, LocaleController.isRTL ? 5 : 3, 0, 10, 0, 0));
            this.description = new TextView(context);
            this.description.setText(LocaleController.getString("DeleteAccountFinalDes", R.string.DeleteAccountFinalDes));
            this.description.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.description.setTextSize(1, 14.0f);
            this.description.setGravity(LocaleController.isRTL ? 5 : 3);
            this.description.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
            this.description.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.description, LayoutHelper.createLinear(-2, -2, LocaleController.isRTL ? 5 : 3, 0, 0, 0, 10));
            TextView cabcelButton = new TextView(context);
            cabcelButton.setTypeface(TurboUtils.getTurboTypeFace());
            cabcelButton.setGravity((LocaleController.isRTL ? 5 : 3) | 48);
            cabcelButton.setTextColor(Theme.getColor(Theme.key_chats_attachMessage));
            cabcelButton.setText(LocaleController.getString("DeleteAccountCancel", R.string.DeleteAccountCancel));
            cabcelButton.setTextSize(1, 14.0f);
            cabcelButton.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
            cabcelButton.setPadding(0, AndroidUtilities.dp(14.0f), 0, 0);
            addView(cabcelButton, LayoutHelper.createLinear(-2, -2, (LocaleController.isRTL ? 5 : 3) | 48));
            cabcelButton.setOnClickListener(new OnClickListener(DeleteAccountActivity.this) {
                public void onClick(View v) {
                    DeleteAccountActivity.this.setPage(0, true, null, true);
                }
            });
        }

        public void setParams(Bundle params, boolean restore) {
            if (params != null && params.containsKey("phone")) {
                this.phone = params.getString("phone");
            }
        }

        public void onNextPressed() {
            Builder builder = new Builder(DeleteAccountActivity.this.getParentActivity());
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            builder.setMessage(LocaleController.getString("DeleteAccountFinalDes", R.string.DeleteAccountFinalDes) + "\n\n" + LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C23093());
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            DeleteAccountActivity.this.showDialog(builder.create());
        }

        public String getHeaderName() {
            return LocaleController.getString("DeleteAccount", R.string.DeleteAccount);
        }

        public boolean needBackButton() {
            return true;
        }
    }

    public class PhoneView extends SlideView implements OnItemSelectedListener {
        private EditTextBoldCursor codeField;
        private HashMap<String, String> codesMap = new HashMap();
        private ArrayList<String> countriesArray = new ArrayList();
        private HashMap<String, String> countriesMap = new HashMap();
        private TextView countryButton;
        private int countryState = 0;
        private boolean ignoreOnPhoneChange = false;
        private boolean ignoreOnTextChange = false;
        private boolean ignoreSelection = false;
        private HintEditText phoneField;
        private HashMap<String, String> phoneFormatMap = new HashMap();
        private TextView resultTextView;
        private TextView textView;
        private TextView textView2;
        private View view;

        public PhoneView(Context context) {
            super(context);
            setOrientation(1);
            this.countryButton = new TextView(context);
            this.countryButton.setTextSize(1, 18.0f);
            this.countryButton.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(12.0f), 0);
            this.countryButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.countryButton.setMaxLines(1);
            this.countryButton.setSingleLine(true);
            this.countryButton.setEllipsize(TruncateAt.END);
            this.countryButton.setGravity((LocaleController.isRTL ? 5 : 3) | 1);
            this.countryButton.setBackgroundResource(R.drawable.spinner_states);
            addView(this.countryButton, LayoutHelper.createLinear(-1, 36, 0.0f, 0.0f, 0.0f, 14.0f));
            final DeleteAccountActivity deleteAccountActivity = DeleteAccountActivity.this;
            this.countryButton.setOnClickListener(new OnClickListener() {

                /* renamed from: turbogram.DeleteAccountActivity$PhoneView$1$1 */
                class C23111 implements CountrySelectActivityDelegate {

                    /* renamed from: turbogram.DeleteAccountActivity$PhoneView$1$1$1 */
                    class C23101 implements Runnable {
                        C23101() {
                        }

                        public void run() {
                            AndroidUtilities.showKeyboard(PhoneView.this.phoneField);
                        }
                    }

                    C23111() {
                    }

                    public void didSelectCountry(String name, String shortName) {
                        PhoneView.this.selectCountry(name);
                        AndroidUtilities.runOnUIThread(new C23101(), 300);
                        PhoneView.this.phoneField.requestFocus();
                        PhoneView.this.phoneField.setSelection(PhoneView.this.phoneField.length());
                    }
                }

                public void onClick(View view) {
                    CountrySelectActivity fragment = new CountrySelectActivity(true);
                    fragment.setCountrySelectActivityDelegate(new C23111());
                    DeleteAccountActivity.this.presentFragment(fragment);
                }
            });
            this.view = new View(context);
            this.view.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            this.view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayLine));
            addView(this.view, LayoutHelper.createLinear(-1, 1, 4.0f, -17.5f, 4.0f, 0.0f));
            View linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(0);
            addView(linearLayout, LayoutHelper.createLinear(-1, -2, 0.0f, 20.0f, 0.0f, 0.0f));
            this.textView = new TextView(context);
            this.textView.setText("+");
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 18.0f);
            linearLayout.addView(this.textView, LayoutHelper.createLinear(-2, -2));
            this.codeField = new EditTextBoldCursor(context);
            this.codeField.setInputType(3);
            this.codeField.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.codeField.setBackgroundDrawable(Theme.createEditTextDrawable(context, false));
            this.codeField.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.codeField.setCursorSize(AndroidUtilities.dp(20.0f));
            this.codeField.setCursorWidth(1.5f);
            this.codeField.setPadding(AndroidUtilities.dp(10.0f), 0, 0, 0);
            this.codeField.setTextSize(1, 18.0f);
            this.codeField.setMaxLines(1);
            this.codeField.setGravity(19);
            this.codeField.setImeOptions(268435461);
            this.codeField.setFilters(new InputFilter[]{new LengthFilter(5)});
            linearLayout.addView(this.codeField, LayoutHelper.createLinear(55, 36, -9.0f, 0.0f, 16.0f, 0.0f));
            final DeleteAccountActivity deleteAccountActivity2 = DeleteAccountActivity.this;
            this.codeField.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    if (!PhoneView.this.ignoreOnTextChange) {
                        PhoneView.this.ignoreOnTextChange = true;
                        String text = PhoneFormat.stripExceptNumbers(PhoneView.this.codeField.getText().toString());
                        PhoneView.this.codeField.setText(text);
                        if (text.length() == 0) {
                            PhoneView.this.countryButton.setText(LocaleController.getString("ChooseCountry", R.string.ChooseCountry));
                            PhoneView.this.phoneField.setHintText(null);
                            PhoneView.this.countryState = 1;
                        } else {
                            boolean ok = false;
                            String textToSet = null;
                            if (text.length() > 4) {
                                for (int a = 4; a >= 1; a--) {
                                    String sub = text.substring(0, a);
                                    if (((String) PhoneView.this.codesMap.get(sub)) != null) {
                                        ok = true;
                                        textToSet = text.substring(a, text.length()) + PhoneView.this.phoneField.getText().toString();
                                        text = sub;
                                        PhoneView.this.codeField.setText(sub);
                                        break;
                                    }
                                }
                                if (!ok) {
                                    textToSet = text.substring(1, text.length()) + PhoneView.this.phoneField.getText().toString();
                                    EditTextBoldCursor access$600 = PhoneView.this.codeField;
                                    text = text.substring(0, 1);
                                    access$600.setText(text);
                                }
                            }
                            String country = (String) PhoneView.this.codesMap.get(text);
                            if (country != null) {
                                int index = PhoneView.this.countriesArray.indexOf(country);
                                if (index != -1) {
                                    PhoneView.this.ignoreSelection = true;
                                    PhoneView.this.countryButton.setText((CharSequence) PhoneView.this.countriesArray.get(index));
                                    String hint = (String) PhoneView.this.phoneFormatMap.get(text);
                                    PhoneView.this.phoneField.setHintText(hint != null ? hint.replace('X', '–') : null);
                                    PhoneView.this.countryState = 0;
                                } else {
                                    PhoneView.this.countryButton.setText(LocaleController.getString("WrongCountry", R.string.WrongCountry));
                                    PhoneView.this.phoneField.setHintText(null);
                                    PhoneView.this.countryState = 2;
                                }
                            } else {
                                PhoneView.this.countryButton.setText(LocaleController.getString("WrongCountry", R.string.WrongCountry));
                                PhoneView.this.phoneField.setHintText(null);
                                PhoneView.this.countryState = 2;
                            }
                            if (!ok) {
                                PhoneView.this.codeField.setSelection(PhoneView.this.codeField.getText().length());
                            }
                            if (textToSet != null) {
                                PhoneView.this.phoneField.requestFocus();
                                PhoneView.this.phoneField.setText(textToSet);
                                PhoneView.this.phoneField.setSelection(PhoneView.this.phoneField.length());
                            }
                        }
                        PhoneView.this.ignoreOnTextChange = false;
                    }
                }
            });
            final DeleteAccountActivity deleteAccountActivity22 = DeleteAccountActivity.this;
            this.codeField.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 5) {
                        return false;
                    }
                    PhoneView.this.phoneField.requestFocus();
                    PhoneView.this.phoneField.setSelection(PhoneView.this.phoneField.length());
                    return true;
                }
            });
            this.phoneField = new HintEditText(context);
            this.phoneField.setInputType(3);
            this.phoneField.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.phoneField.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            this.phoneField.setBackgroundDrawable(Theme.createEditTextDrawable(context, false));
            this.phoneField.setPadding(0, 0, 0, 0);
            this.phoneField.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.phoneField.setCursorSize(AndroidUtilities.dp(20.0f));
            this.phoneField.setCursorWidth(1.5f);
            this.phoneField.setTextSize(1, 18.0f);
            this.phoneField.setMaxLines(1);
            this.phoneField.setGravity(19);
            this.phoneField.setImeOptions(268435461);
            linearLayout.addView(this.phoneField, LayoutHelper.createFrame(-1, 36.0f));
            final DeleteAccountActivity deleteAccountActivity222 = DeleteAccountActivity.this;
            this.phoneField.addTextChangedListener(new TextWatcher() {
                private int actionPosition;
                private int characterAction = -1;

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (count == 0 && after == 1) {
                        this.characterAction = 1;
                    } else if (count != 1 || after != 0) {
                        this.characterAction = -1;
                    } else if (s.charAt(start) != ' ' || start <= 0) {
                        this.characterAction = 2;
                    } else {
                        this.characterAction = 3;
                        this.actionPosition = start - 1;
                    }
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    if (!PhoneView.this.ignoreOnPhoneChange) {
                        int a;
                        int start = PhoneView.this.phoneField.getSelectionStart();
                        String phoneChars = "0123456789";
                        String str = PhoneView.this.phoneField.getText().toString();
                        if (this.characterAction == 3) {
                            str = str.substring(0, this.actionPosition) + str.substring(this.actionPosition + 1, str.length());
                            start--;
                        }
                        StringBuilder builder = new StringBuilder(str.length());
                        for (a = 0; a < str.length(); a++) {
                            String ch = str.substring(a, a + 1);
                            if (phoneChars.contains(ch)) {
                                builder.append(ch);
                            }
                        }
                        PhoneView.this.ignoreOnPhoneChange = true;
                        String hint = PhoneView.this.phoneField.getHintText();
                        if (hint != null) {
                            a = 0;
                            while (a < builder.length()) {
                                if (a < hint.length()) {
                                    if (hint.charAt(a) == ' ') {
                                        builder.insert(a, ' ');
                                        a++;
                                        if (!(start != a || this.characterAction == 2 || this.characterAction == 3)) {
                                            start++;
                                        }
                                    }
                                    a++;
                                } else {
                                    builder.insert(a, ' ');
                                    if (!(start != a + 1 || this.characterAction == 2 || this.characterAction == 3)) {
                                        start++;
                                    }
                                }
                            }
                        }
                        PhoneView.this.phoneField.setText(builder);
                        if (start >= 0) {
                            HintEditText access$400 = PhoneView.this.phoneField;
                            if (start > PhoneView.this.phoneField.length()) {
                                start = PhoneView.this.phoneField.length();
                            }
                            access$400.setSelection(start);
                        }
                        PhoneView.this.phoneField.onTextChange();
                        PhoneView.this.ignoreOnPhoneChange = false;
                    }
                }
            });
            final DeleteAccountActivity deleteAccountActivity2222 = DeleteAccountActivity.this;
            this.phoneField.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 5) {
                        return false;
                    }
                    PhoneView.this.onNextPressed();
                    return true;
                }
            });
            this.resultTextView = new TextView(context);
            this.resultTextView.setGravity(LocaleController.isRTL ? 5 : 3);
            this.resultTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.resultTextView.setTextSize(1, 14.0f);
            this.resultTextView.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.resultTextView, LayoutHelper.createLinear(-1, -2, LocaleController.isRTL ? 5 : 3, 0, 10, 0, 0));
            this.textView2 = new TextView(context);
            this.textView2.setText(LocaleController.getString("DeleteAccountPhoneDes", R.string.DeleteAccountPhoneDes));
            this.textView2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.textView2.setTextSize(1, 14.0f);
            this.textView2.setGravity(LocaleController.isRTL ? 5 : 3);
            this.textView2.setLineSpacing((float) AndroidUtilities.dp(2.0f), 1.0f);
            this.textView2.setTypeface(TurboUtils.getTurboTypeFace());
            addView(this.textView2, LayoutHelper.createLinear(-2, -2, LocaleController.isRTL ? 5 : 3, 0, 0, 0, 10));
            HashMap<String, String> languageMap = new HashMap();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().getAssets().open("countries.txt")));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] args = line.split(";");
                    this.countriesArray.add(0, args[2]);
                    this.countriesMap.put(args[2], args[0]);
                    this.codesMap.put(args[0], args[2]);
                    if (args.length > 3) {
                        this.phoneFormatMap.put(args[0], args[3]);
                    }
                    languageMap.put(args[1], args[2]);
                }
                bufferedReader.close();
            } catch (Exception e) {
                FileLog.e(e);
            }
            final DeleteAccountActivity deleteAccountActivity22222 = DeleteAccountActivity.this;
            Collections.sort(this.countriesArray, new Comparator<String>() {
                public int compare(String lhs, String rhs) {
                    return lhs.compareTo(rhs);
                }
            });
            String country = null;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");
                if (telephonyManager != null) {
                    country = telephonyManager.getSimCountryIso().toUpperCase();
                }
            } catch (Exception e2) {
                FileLog.e(e2);
            }
            if (country != null) {
                String countryName = (String) languageMap.get(country);
                if (!(countryName == null || this.countriesArray.indexOf(countryName) == -1)) {
                    this.codeField.setText((CharSequence) this.countriesMap.get(countryName));
                    this.countryState = 0;
                }
            }
            if (this.codeField.length() == 0) {
                this.countryButton.setText(LocaleController.getString("ChooseCountry", R.string.ChooseCountry));
                this.phoneField.setHintText(null);
                this.countryState = 1;
            }
            if (this.codeField.length() != 0) {
                this.phoneField.requestFocus();
                this.phoneField.setSelection(this.phoneField.length());
                return;
            }
            this.codeField.requestFocus();
        }

        public void selectCountry(String name) {
            if (this.countriesArray.indexOf(name) != -1) {
                this.ignoreOnTextChange = true;
                String code = (String) this.countriesMap.get(name);
                this.codeField.setText(code);
                this.countryButton.setText(name);
                String hint = (String) this.phoneFormatMap.get(code);
                this.phoneField.setHintText(hint != null ? hint.replace('X', '–') : null);
                this.countryState = 0;
                this.ignoreOnTextChange = false;
            }
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (this.ignoreSelection) {
                this.ignoreSelection = false;
                return;
            }
            this.ignoreOnTextChange = true;
            this.codeField.setText((CharSequence) this.countriesMap.get((String) this.countriesArray.get(i)));
            this.ignoreOnTextChange = false;
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public void onNextPressed() {
            String phone = "+" + PhoneFormat.stripExceptNumbers("" + this.codeField.getText() + this.phoneField.getText());
            if (phone.startsWith("+") && phone.length() == 13) {
                new DeleteAccountTask(0, this.resultTextView).execute(new String[]{phone});
                this.phoneField.setText("");
                AndroidUtilities.hideKeyboard(this.phoneField);
                return;
            }
            this.resultTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
            this.resultTextView.setText(LocaleController.getString("DeleteAccountPhoneError", R.string.DeleteAccountPhoneError));
        }

        public void onBackPressed() {
            Editor edit = ApplicationLoader.applicationContext.getSharedPreferences("delete_account", 0).edit();
            edit.remove("phone");
            edit.remove("random_hash");
            edit.commit();
        }

        public void onShow() {
            super.onShow();
            if (this.phoneField == null) {
                return;
            }
            if (this.codeField.length() != 0) {
                AndroidUtilities.showKeyboard(this.phoneField);
                this.phoneField.requestFocus();
                this.phoneField.setSelection(this.phoneField.length());
                return;
            }
            AndroidUtilities.showKeyboard(this.codeField);
            this.codeField.requestFocus();
        }

        public String getHeaderName() {
            return LocaleController.getString("DeleteAccount", R.string.DeleteAccount);
        }

        public boolean needBackButton() {
            return true;
        }

        public void saveStateParams(Bundle bundle) {
            String code = this.codeField.getText().toString();
            if (code.length() != 0) {
                bundle.putString("phoneview_code", code);
            }
            String phone = this.phoneField.getText().toString();
            if (phone.length() != 0) {
                bundle.putString("phoneview_phone", phone);
            }
        }

        public void restoreStateParams(Bundle bundle) {
            String code = bundle.getString("phoneview_code");
            if (code != null) {
                this.codeField.setText(code);
            }
            String phone = bundle.getString("phoneview_phone");
            if (phone != null) {
                this.phoneField.setText(phone);
            }
        }
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        for (int a = 0; a < this.views.length; a++) {
            if (this.views[a] != null) {
                this.views[a].onDestroyActivity();
            }
        }
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("DeleteAccount", R.string.DeleteAccount));
        this.actionBar.setActionBarMenuOnItemClick(new C23021());
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_done, AndroidUtilities.dp(56.0f));
        this.fragmentView = new ScrollView(context);
        ScrollView scrollView = this.fragmentView;
        scrollView.setFillViewport(true);
        FrameLayout frameLayout = new FrameLayout(context);
        scrollView.addView(frameLayout, LayoutHelper.createScroll(-1, -2, 51));
        this.views[0] = new PhoneView(context);
        this.views[1] = new CodeView(context);
        this.views[2] = new FinalView(context);
        int a = 0;
        while (a < this.views.length) {
            this.views[a].setVisibility(a == 0 ? 0 : 8);
            frameLayout.addView(this.views[a], LayoutHelper.createFrame(-1, a == 0 ? -2.0f : -1.0f, 51, AndroidUtilities.isTablet() ? 26.0f : 18.0f, 30.0f, AndroidUtilities.isTablet() ? 26.0f : 18.0f, 0.0f));
            a++;
        }
        SharedPreferences deletePrefs = ApplicationLoader.applicationContext.getSharedPreferences("delete_account", 0);
        if (deletePrefs.contains("phone") && deletePrefs.contains("random_hash")) {
            Bundle bundle = new Bundle();
            bundle.putString("phone", deletePrefs.getString("phone", ""));
            bundle.putString("random_hash", deletePrefs.getString("random_hash", ""));
            setPage(1, false, bundle, true);
        } else {
            setPage(0, false, null, true);
        }
        return this.fragmentView;
    }

    public void setPage(int page, boolean animated, Bundle params, boolean back) {
        int i = R.drawable.ic_ab_back;
        this.doneButton.setVisibility(0);
        if (animated) {
            float f;
            final SlideView outView = this.views[this.currentViewNum];
            final SlideView newView = this.views[page];
            this.currentViewNum = page;
            ActionBar actionBar = this.actionBar;
            if (!newView.needBackButton()) {
                i = 0;
            }
            actionBar.setBackButtonImage(i);
            newView.setParams(params, false);
            this.actionBar.setTitle(newView.getHeaderName());
            newView.onShow();
            if (back) {
                f = (float) (-AndroidUtilities.displaySize.x);
            } else {
                f = (float) AndroidUtilities.displaySize.x;
            }
            newView.setX(f);
            ViewPropertyAnimator duration = outView.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                }

                @SuppressLint({"NewApi"})
                public void onAnimationEnd(Animator animator) {
                    outView.setVisibility(8);
                    outView.setX(0.0f);
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            }).setDuration(300);
            if (back) {
                f = (float) AndroidUtilities.displaySize.x;
            } else {
                f = (float) (-AndroidUtilities.displaySize.x);
            }
            duration.translationX(f).start();
            newView.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    newView.setVisibility(0);
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            }).setDuration(300).translationX(0.0f).start();
            return;
        }
        actionBar = this.actionBar;
        if (!this.views[page].needBackButton()) {
            i = 0;
        }
        actionBar.setBackButtonImage(i);
        this.views[this.currentViewNum].setVisibility(8);
        this.currentViewNum = page;
        this.views[page].setParams(params, false);
        this.views[page].setVisibility(0);
        this.actionBar.setTitle(this.views[page].getHeaderName());
        this.views[page].onShow();
    }

    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    public boolean onBackPressed() {
        for (int a = 0; a < this.views.length; a++) {
            if (this.views[a] != null) {
                this.views[a].onDestroyActivity();
            }
        }
        finishFragment();
        return true;
    }
}
