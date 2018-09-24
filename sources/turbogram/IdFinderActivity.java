package turbogram;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_account_checkUsername;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Components.LayoutHelper;

public class IdFinderActivity extends BaseFragment {
    private GraySectionCell GraySectionCell;
    private ArrayList<TLRPC$Chat> chats = new ArrayList();
    private int checkReqId = 0;
    private Runnable checkRunnable = null;
    private TextView checkTextView;
    private View doneButton;
    private EditText firstNameField;
    private TextView helpTextView;
    private String lastCheckName = null;
    private int reqId = 0;
    private ProfileSearchCell userSearchCell;
    private ArrayList<User> users = new ArrayList();

    /* renamed from: turbogram.IdFinderActivity$1 */
    class C23621 extends ActionBarMenuOnItemClick {
        C23621() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                IdFinderActivity.this.finishFragment();
            } else if (id == 1) {
                MessagesController.getInstance(IdFinderActivity.this.currentAccount).openByUserName(IdFinderActivity.this.firstNameField.getText().toString(), IdFinderActivity.this, 0);
            }
        }
    }

    /* renamed from: turbogram.IdFinderActivity$2 */
    class C23632 implements OnTouchListener {
        C23632() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: turbogram.IdFinderActivity$3 */
    class C23643 implements OnEditorActionListener {
        C23643() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6 || IdFinderActivity.this.doneButton == null) {
                return false;
            }
            IdFinderActivity.this.doneButton.performClick();
            return true;
        }
    }

    /* renamed from: turbogram.IdFinderActivity$4 */
    class C23654 implements TextWatcher {
        C23654() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            IdFinderActivity.this.checkUserName(IdFinderActivity.this.firstNameField.getText().toString());
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("IdFinder", R.string.IdFinder));
        this.actionBar.setActionBarMenuOnItemClick(new C23621());
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_done, AndroidUtilities.dp(56.0f));
        this.fragmentView = new LinearLayout(context);
        ((LinearLayout) this.fragmentView).setOrientation(1);
        this.fragmentView.setOnTouchListener(new C23632());
        this.firstNameField = new EditText(context);
        this.firstNameField.setTextSize(1, 18.0f);
        this.firstNameField.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.firstNameField.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.firstNameField.setBackgroundDrawable(Theme.createEditTextDrawable(context, false));
        this.firstNameField.setMaxLines(1);
        this.firstNameField.setLines(1);
        this.firstNameField.setPadding(0, 0, 0, 0);
        this.firstNameField.setSingleLine(true);
        this.firstNameField.setGravity(LocaleController.isRTL ? 5 : 3);
        this.firstNameField.setInputType(180224);
        this.firstNameField.setImeOptions(6);
        this.firstNameField.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.firstNameField.setHint(LocaleController.getString("IdToFind", R.string.IdToFind));
        this.firstNameField.setOnEditorActionListener(new C23643());
        ((LinearLayout) this.fragmentView).addView(this.firstNameField, LayoutHelper.createLinear(-1, 36, 24.0f, 24.0f, 24.0f, 0.0f));
        this.checkTextView = new TextView(context);
        this.checkTextView.setTextSize(1, 15.0f);
        this.checkTextView.setGravity(LocaleController.isRTL ? 5 : 3);
        this.checkTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        ((LinearLayout) this.fragmentView).addView(this.checkTextView, LayoutHelper.createLinear(-2, -2, LocaleController.isRTL ? 5 : 3, 24, 12, 24, 0));
        this.GraySectionCell = new GraySectionCell(context);
        this.GraySectionCell.setText(LocaleController.getString("IdFinderResult", R.string.IdFinderResult).toUpperCase());
        this.GraySectionCell.setVisibility(8);
        ((LinearLayout) this.fragmentView).addView(this.GraySectionCell, LayoutHelper.createLinear(-1, -2, 48, 0, 15, 0, 0));
        this.userSearchCell = new ProfileSearchCell(context);
        this.userSearchCell.setVisibility(8);
        ((LinearLayout) this.fragmentView).addView(this.userSearchCell, LayoutHelper.createLinear(-1, -2, 48));
        this.helpTextView = new TextView(context);
        this.helpTextView.setTextSize(1, 15.0f);
        this.helpTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
        this.helpTextView.setGravity(LocaleController.isRTL ? 5 : 3);
        this.helpTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString("IdFinderDescription", R.string.IdFinderDescription)));
        this.helpTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        ((LinearLayout) this.fragmentView).addView(this.helpTextView, LayoutHelper.createLinear(-2, -2, LocaleController.isRTL ? 5 : 3, 24, 10, 24, 0));
        this.firstNameField.addTextChangedListener(new C23654());
        this.checkTextView.setVisibility(8);
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (!ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).getBoolean("view_animations", true)) {
            this.firstNameField.requestFocus();
            AndroidUtilities.showKeyboard(this.firstNameField);
        }
    }

    private boolean checkUserName(final String name) {
        if (name == null || name.length() <= 0) {
            this.checkTextView.setVisibility(8);
        } else {
            this.checkTextView.setVisibility(0);
        }
        if (name.length() == 0) {
            this.userSearchCell.setVisibility(8);
            this.GraySectionCell.setVisibility(8);
            this.helpTextView.setVisibility(0);
            return true;
        }
        if (this.checkRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
            this.checkRunnable = null;
            this.lastCheckName = null;
            if (this.checkReqId != 0) {
                ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkReqId, true);
            }
        }
        if (name != null) {
            if (name.startsWith("_") || name.endsWith("_")) {
                this.checkTextView.setText(LocaleController.getString("UsernameInvalid", R.string.UsernameInvalid));
                this.checkTextView.setTextColor(-3198928);
                return false;
            }
            int a = 0;
            while (a < name.length()) {
                char ch = name.charAt(a);
                if (a == 0 && ch >= '0' && ch <= '9') {
                    this.checkTextView.setText(LocaleController.getString("UsernameInvalidStartNumber", R.string.UsernameInvalidStartNumber));
                    this.checkTextView.setTextColor(-3198928);
                    return false;
                } else if ((ch < '0' || ch > '9') && ((ch < 'a' || ch > 'z') && ((ch < 'A' || ch > 'Z') && ch != '_'))) {
                    this.checkTextView.setText(LocaleController.getString("UsernameInvalid", R.string.UsernameInvalid));
                    this.checkTextView.setTextColor(-3198928);
                    return false;
                } else {
                    a++;
                }
            }
        }
        if (name == null || name.length() < 5) {
            this.checkTextView.setText(LocaleController.getString("UsernameInvalidShort", R.string.UsernameInvalidShort));
            this.checkTextView.setTextColor(-3198928);
            return false;
        } else if (name.length() > 32) {
            this.checkTextView.setText(LocaleController.getString("UsernameInvalidLong", R.string.UsernameInvalidLong));
            this.checkTextView.setTextColor(-3198928);
            return false;
        } else {
            String currentName = UserConfig.getInstance(this.currentAccount).getCurrentUser().username;
            if (currentName == null) {
                currentName = "";
            }
            if (name.equals(currentName)) {
                this.checkTextView.setText(LocaleController.formatString("UserIsAvailble", R.string.UserIsAvailble, new Object[]{name}));
                this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText));
                return true;
            }
            this.checkTextView.setText(LocaleController.getString("UsernameChecking", R.string.UsernameChecking));
            this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
            this.lastCheckName = name;
            this.checkRunnable = new Runnable() {

                /* renamed from: turbogram.IdFinderActivity$5$1 */
                class C23671 implements RequestDelegate {
                    C23671() {
                    }

                    public void run(final TLObject response, final TLRPC$TL_error error) {
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                IdFinderActivity.this.checkReqId = 0;
                                if (IdFinderActivity.this.lastCheckName != null && IdFinderActivity.this.lastCheckName.equals(name)) {
                                    if (error == null && (response instanceof TLRPC$TL_boolTrue)) {
                                        IdFinderActivity.this.checkTextView.setText(LocaleController.getString("UserIsNotAvailble", R.string.UserIsNotAvailble));
                                        IdFinderActivity.this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
                                        return;
                                    }
                                    IdFinderActivity.this.checkTextView.setText(LocaleController.getString("UserIsAvailble", R.string.UserIsAvailble));
                                    IdFinderActivity.this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText));
                                }
                            }
                        });
                    }
                }

                public void run() {
                    TLRPC$TL_account_checkUsername req = new TLRPC$TL_account_checkUsername();
                    req.username = name;
                    IdFinderActivity.this.checkReqId = ConnectionsManager.getInstance(IdFinderActivity.this.currentAccount).sendRequest(req, new C23671(), 2);
                }
            };
            AndroidUtilities.runOnUIThread(this.checkRunnable, 300);
            return true;
        }
    }

    public void onTransitionAnimationEnd(boolean isOpen, boolean backward) {
        if (isOpen) {
            this.firstNameField.requestFocus();
            AndroidUtilities.showKeyboard(this.firstNameField);
        }
    }
}
