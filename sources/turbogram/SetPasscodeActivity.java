package turbogram;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Components.PatternView.PatternView;
import turbogram.Components.PatternView.PatternView.OnPatternDetectedListener;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class SetPasscodeActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    private int changePatternRow;
    private int changeenablePasscodeRow;
    private int checkType = 0;
    private int enablePasscodeRow;
    private String firstPassword;
    private int hiddenInShareAlertDesRow;
    private int hiddenInShareAlertRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int lockType;
    private int pageType;
    private int passcodeDetailRow;
    private int passcodeSetStep = 0;
    private EditText passwordEditText;
    private int patternDetailRow;
    private int patternRow;
    private PatternView patternView;
    private int rowCount;
    private int showHiddenNotifDesRow;
    private int showHiddenNotifRow;
    private TextView titleTextView;

    /* renamed from: turbogram.SetPasscodeActivity$1 */
    class C24071 extends ActionBarMenuOnItemClick {
        C24071() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                SetPasscodeActivity.this.finishFragment();
            } else if (id != 1) {
            } else {
                if (SetPasscodeActivity.this.passcodeSetStep == 0) {
                    SetPasscodeActivity.this.processNext();
                } else if (SetPasscodeActivity.this.passcodeSetStep == 1) {
                    SetPasscodeActivity.this.processDone();
                }
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$2 */
    class C24082 implements OnPatternDetectedListener {
        C24082() {
        }

        public void onPatternDetected() {
            if (SetPasscodeActivity.this.pageType != 1) {
                SetPasscodeActivity.this.processDone();
            } else if (SetPasscodeActivity.this.passcodeSetStep == 0) {
                SetPasscodeActivity.this.processNext();
            } else if (SetPasscodeActivity.this.passcodeSetStep == 1) {
                SetPasscodeActivity.this.processDone();
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$3 */
    class C24093 implements OnEditorActionListener {
        C24093() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (SetPasscodeActivity.this.passcodeSetStep == 0) {
                SetPasscodeActivity.this.processNext();
                return true;
            } else if (SetPasscodeActivity.this.passcodeSetStep != 1) {
                return false;
            } else {
                SetPasscodeActivity.this.processDone();
                return true;
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$4 */
    class C24104 implements TextWatcher {
        C24104() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (SetPasscodeActivity.this.passwordEditText.length() != 4) {
                return;
            }
            if (SetPasscodeActivity.this.pageType != 1) {
                SetPasscodeActivity.this.processDone();
            } else if (SetPasscodeActivity.this.passcodeSetStep == 0) {
                SetPasscodeActivity.this.processNext();
            } else if (SetPasscodeActivity.this.passcodeSetStep == 1) {
                SetPasscodeActivity.this.processDone();
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$5 */
    class C24115 implements OnCreateContextMenuListener {
        C24115() {
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            menu.clear();
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$6 */
    class C24126 implements Callback {
        C24126() {
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$7 */
    class C24137 implements OnItemClickListener {
        C24137() {
        }

        public void onItemClick(View view, int position) {
            boolean z = true;
            if (!view.isEnabled()) {
                return;
            }
            if (position == SetPasscodeActivity.this.changeenablePasscodeRow) {
                SetPasscodeActivity.this.presentFragment(new SetPasscodeActivity(3, SetPasscodeActivity.this.checkType));
            } else if (position == SetPasscodeActivity.this.enablePasscodeRow) {
                if (SetPasscodeActivity.this.getPasscode().length() != 0) {
                    TurboConfig.setBooleanValue("enable_pattern", false);
                    if (SetPasscodeActivity.this.checkType == 1) {
                        TurboConfig.setStringValue("chat_lock_pass", "");
                        TurboConfig.setStringValue("chat_lock_patt", "");
                    } else if (SetPasscodeActivity.this.checkType == 0) {
                        TurboConfig.setStringValue("chat_password", "");
                        TurboConfig.setStringValue("chat_hide_patt", "");
                    }
                    SetPasscodeActivity.this.updateRows();
                    if (SetPasscodeActivity.this.listAdapter != null) {
                        SetPasscodeActivity.this.listAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                SetPasscodeActivity.this.presentFragment(new SetPasscodeActivity(1, SetPasscodeActivity.this.checkType));
            } else if (position == SetPasscodeActivity.this.changePatternRow) {
                SetPasscodeActivity.this.presentFragment(new SetPasscodeActivity(3, 1, SetPasscodeActivity.this.checkType));
            } else if (position == SetPasscodeActivity.this.patternRow) {
                if (SetPasscodeActivity.this.getPattern().length() != 0) {
                    if (SetPasscodeActivity.this.checkType == 1) {
                        TurboConfig.setStringValue("chat_lock_patt", "");
                    } else if (SetPasscodeActivity.this.checkType == 0) {
                        TurboConfig.setStringValue("chat_hide_patt", "");
                    } else if (SetPasscodeActivity.this.checkType == 2) {
                        TurboConfig.setStringValue("app_lock_patt", "");
                    }
                    SetPasscodeActivity.this.updateRows();
                    if (SetPasscodeActivity.this.listAdapter != null) {
                        SetPasscodeActivity.this.listAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                SetPasscodeActivity.this.presentFragment(new SetPasscodeActivity(1, 1, SetPasscodeActivity.this.checkType));
            } else if (position == SetPasscodeActivity.this.hiddenInShareAlertRow) {
                r2 = "hidden_sharealert";
                if (TurboConfig.hiddenInShareAlert) {
                    z = false;
                }
                TurboConfig.setBooleanValue(r2, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.hiddenInShareAlert);
                }
            } else if (position == SetPasscodeActivity.this.showHiddenNotifRow) {
                r2 = "show_hnotification";
                if (TurboConfig.hiddenChatNotification) {
                    z = false;
                }
                TurboConfig.setBooleanValue(r2, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.hiddenChatNotification);
                }
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$8 */
    class C24148 implements Runnable {
        C24148() {
        }

        public void run() {
            if (SetPasscodeActivity.this.passwordEditText != null) {
                SetPasscodeActivity.this.passwordEditText.requestFocus();
                AndroidUtilities.showKeyboard(SetPasscodeActivity.this.passwordEditText);
            }
        }
    }

    /* renamed from: turbogram.SetPasscodeActivity$9 */
    class C24159 implements OnPreDrawListener {
        C24159() {
        }

        public boolean onPreDraw() {
            SetPasscodeActivity.this.listView.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            int position = holder.getAdapterPosition();
            String pass = SetPasscodeActivity.this.getPasscode();
            return position == SetPasscodeActivity.this.enablePasscodeRow || ((pass.length() != 0 && position == SetPasscodeActivity.this.changeenablePasscodeRow) || ((pass.length() != 0 && position == SetPasscodeActivity.this.patternRow) || ((SetPasscodeActivity.this.getPattern().length() != 0 && position == SetPasscodeActivity.this.changePatternRow) || position == SetPasscodeActivity.this.hiddenInShareAlertRow || position == SetPasscodeActivity.this.showHiddenNotifRow)));
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            boolean z = true;
            boolean z2 = false;
            switch (holder.getItemViewType()) {
                case 0:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == SetPasscodeActivity.this.enablePasscodeRow) {
                        String string = LocaleController.getString("EnablePass", R.string.EnablePass);
                        if (SetPasscodeActivity.this.getPasscode().length() > 0) {
                            z2 = true;
                        }
                        checkCell.setTextAndCheck(string, z2, true);
                        return;
                    } else if (position == SetPasscodeActivity.this.hiddenInShareAlertRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("HiddenInShareAlert", R.string.HiddenInShareAlert), TurboConfig.hiddenInShareAlert, false);
                        return;
                    } else if (position == SetPasscodeActivity.this.showHiddenNotifRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("HiddenShowNotif", R.string.HiddenShowNotif), TurboConfig.hiddenChatNotification, false);
                        return;
                    } else if (position == SetPasscodeActivity.this.patternRow) {
                        boolean z3;
                        String string2 = LocaleController.getString("Pattern", R.string.Pattern);
                        if (SetPasscodeActivity.this.getPattern().length() > 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        checkCell.setTextAndCheck(string2, z3, true);
                        if (SetPasscodeActivity.this.getPattern().length() <= 0) {
                            z = false;
                        }
                        checkCell.setEnabled(z);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextSettingsCell textSettingsCell = holder.itemView;
                    if (position == SetPasscodeActivity.this.changeenablePasscodeRow) {
                        textSettingsCell.setText(LocaleController.getString("ChangePass", R.string.ChangePass), false);
                        textSettingsCell.setTextColor(SetPasscodeActivity.this.getPasscode().length() == 0 ? Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7) : Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                        return;
                    } else if (position == SetPasscodeActivity.this.changePatternRow) {
                        textSettingsCell.setText(LocaleController.getString("ChangePattern", R.string.ChangePattern), false);
                        textSettingsCell.setTextColor(SetPasscodeActivity.this.getPattern().length() == 0 ? Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7) : Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                        return;
                    } else {
                        return;
                    }
                case 2:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == SetPasscodeActivity.this.passcodeDetailRow) {
                        privacyCell.setText(LocaleController.getString("ChangePassInfo", R.string.ChangePassInfo));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == SetPasscodeActivity.this.hiddenInShareAlertDesRow) {
                        privacyCell.setText(LocaleController.getString("HiddenInShareAlertDes", R.string.HiddenInShareAlertDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == SetPasscodeActivity.this.patternDetailRow) {
                        privacyCell.setText(LocaleController.getString("PatternDes", R.string.PatternDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == SetPasscodeActivity.this.showHiddenNotifDesRow) {
                        privacyCell.setText(LocaleController.getString("HiddenShowNotifDes", R.string.HiddenShowNotifDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }

        public int getItemCount() {
            return SetPasscodeActivity.this.rowCount;
        }

        public int getItemViewType(int i) {
            if (i == SetPasscodeActivity.this.enablePasscodeRow || i == SetPasscodeActivity.this.hiddenInShareAlertRow || i == SetPasscodeActivity.this.showHiddenNotifRow || i == SetPasscodeActivity.this.patternRow) {
                return 0;
            }
            if (i == SetPasscodeActivity.this.changeenablePasscodeRow || i == SetPasscodeActivity.this.changePatternRow) {
                return 1;
            }
            if (i == SetPasscodeActivity.this.passcodeDetailRow || i == SetPasscodeActivity.this.hiddenInShareAlertDesRow || i == SetPasscodeActivity.this.showHiddenNotifDesRow || i == SetPasscodeActivity.this.patternDetailRow) {
                return 2;
            }
            return 0;
        }
    }

    public SetPasscodeActivity(int pageType, int checkType) {
        this.pageType = pageType;
        this.lockType = 0;
        this.checkType = checkType;
    }

    public SetPasscodeActivity(int pageType, int lockType, int checkType) {
        this.pageType = pageType;
        this.lockType = lockType;
        this.checkType = checkType;
        this.swipeBackEnabled = false;
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        updateRows();
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    public View createView(Context context) {
        if (this.pageType != 5) {
            this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        }
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new C24071());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        if (this.pageType != 0) {
            this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_done, AndroidUtilities.dp(56.0f));
            LayoutParams layoutParams;
            if (this.lockType == 1) {
                this.titleTextView = new TextView(context);
                this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
                if (this.pageType != 1) {
                    this.titleTextView.setText(LocaleController.getString("DrawCurrentPattern", R.string.DrawCurrentPattern));
                } else if (TurboConfig.chatLockPattern.length() != 0) {
                    this.titleTextView.setText(LocaleController.getString("DrawNewPattern", R.string.DrawNewPattern));
                } else {
                    this.titleTextView.setText(LocaleController.getString("DrawNewFirstPattern", R.string.DrawNewFirstPattern));
                }
                this.titleTextView.setTextSize(1, 18.0f);
                this.titleTextView.setGravity(1);
                this.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                frameLayout.addView(this.titleTextView);
                layoutParams = (LayoutParams) this.titleTextView.getLayoutParams();
                layoutParams.width = -2;
                layoutParams.height = -2;
                layoutParams.gravity = 1;
                layoutParams.topMargin = AndroidUtilities.dp(38.0f);
                this.titleTextView.setLayoutParams(layoutParams);
                this.patternView = new PatternView(context);
                this.patternView.setDotColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
                this.patternView.setCircleColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
                this.patternView.setPathColor(TurboUtils.getLighterColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6), 0.5f));
                frameLayout.addView(this.patternView);
                layoutParams = (LayoutParams) this.patternView.getLayoutParams();
                layoutParams.topMargin = AndroidUtilities.dp(90.0f);
                layoutParams.gravity = 51;
                this.patternView.setLayoutParams(layoutParams);
                this.patternView.setOnPatternDetectedListener(new C24082());
            } else if (this.lockType == 0) {
                this.titleTextView = new TextView(context);
                this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
                if (this.pageType != 1) {
                    this.titleTextView.setText(LocaleController.getString("EnterCurrentPasscode", R.string.EnterCurrentPasscode));
                } else if (TurboConfig.chatLockPasscode.length() != 0) {
                    this.titleTextView.setText(LocaleController.getString("EnterNewPasscode", R.string.EnterNewPasscode));
                } else {
                    this.titleTextView.setText(LocaleController.getString("EnterNewFirstPasscode", R.string.EnterNewFirstPasscode));
                }
                this.titleTextView.setTextSize(1, 18.0f);
                this.titleTextView.setGravity(1);
                this.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                frameLayout.addView(this.titleTextView);
                layoutParams = (LayoutParams) this.titleTextView.getLayoutParams();
                layoutParams.width = -2;
                layoutParams.height = -2;
                layoutParams.gravity = 1;
                layoutParams.topMargin = AndroidUtilities.dp(38.0f);
                this.titleTextView.setLayoutParams(layoutParams);
                this.passwordEditText = new EditText(context);
                this.passwordEditText.setTextSize(1, 20.0f);
                this.passwordEditText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                this.passwordEditText.setBackgroundDrawable(Theme.createEditTextDrawable(context, false));
                this.passwordEditText.setMaxLines(1);
                this.passwordEditText.setLines(1);
                this.passwordEditText.setInputType(2);
                this.passwordEditText.setGravity(1);
                this.passwordEditText.setSingleLine(true);
                if (this.pageType == 1) {
                    this.passcodeSetStep = 0;
                    this.passwordEditText.setImeOptions(5);
                } else {
                    this.passcodeSetStep = 1;
                    this.passwordEditText.setImeOptions(6);
                }
                this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.passwordEditText.setTypeface(Typeface.DEFAULT);
                frameLayout.addView(this.passwordEditText);
                layoutParams = (LayoutParams) this.passwordEditText.getLayoutParams();
                layoutParams.topMargin = AndroidUtilities.dp(90.0f);
                layoutParams.height = AndroidUtilities.dp(36.0f);
                layoutParams.leftMargin = AndroidUtilities.dp(40.0f);
                layoutParams.gravity = 51;
                layoutParams.rightMargin = AndroidUtilities.dp(40.0f);
                layoutParams.width = -1;
                this.passwordEditText.setLayoutParams(layoutParams);
                this.passwordEditText.setOnEditorActionListener(new C24093());
                this.passwordEditText.addTextChangedListener(new C24104());
                if (VERSION.SDK_INT < 11) {
                    this.passwordEditText.setOnCreateContextMenuListener(new C24115());
                } else {
                    this.passwordEditText.setCustomSelectionActionModeCallback(new C24126());
                }
            }
            if (this.pageType == 2) {
                this.actionBar.setTitle(LocaleController.getString("Authentication", R.string.Authentication));
            } else {
                this.actionBar.setTitle(LocaleController.getString("SetPass", R.string.SetPass));
            }
        } else {
            if (this.checkType == 1) {
                this.actionBar.setTitle(LocaleController.getString("TurboLockChats", R.string.TurboLockChats));
            } else if (this.checkType == 0) {
                this.actionBar.setTitle(LocaleController.getString("TurboHideChats", R.string.TurboHideChats));
            } else if (this.checkType == 2) {
                this.actionBar.setTitle(LocaleController.getString("TurboAppLock", R.string.TurboAppLock));
            }
            frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
            this.listView = new RecyclerListView(context);
            this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            this.listView.setVerticalScrollBarEnabled(false);
            frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
            RecyclerListView recyclerListView = this.listView;
            Adapter listAdapter = new ListAdapter(context);
            this.listAdapter = listAdapter;
            recyclerListView.setAdapter(listAdapter);
            this.listView.setOnItemClickListener(new C24137());
        }
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        updateRows();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
        if (this.pageType != 0 && this.lockType == 0) {
            AndroidUtilities.runOnUIThread(new C24148(), 200);
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.didSetPasscode && this.pageType == 0) {
            updateRows();
            if (this.listAdapter != null) {
                this.listAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRows() {
        this.rowCount = 0;
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enablePasscodeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.changeenablePasscodeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.passcodeDetailRow = i;
        if (this.checkType == 0 && TurboConfig.chatHidePasscode.length() != 0) {
            i = this.rowCount;
            this.rowCount = i + 1;
            this.patternRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.changePatternRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.patternDetailRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.hiddenInShareAlertRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.hiddenInShareAlertDesRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.showHiddenNotifRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.showHiddenNotifDesRow = i;
        } else if (this.checkType != 1 || TurboConfig.chatLockPasscode.length() == 0) {
            this.patternRow = -1;
            this.changePatternRow = -1;
            this.patternDetailRow = -1;
            this.hiddenInShareAlertRow = -1;
            this.hiddenInShareAlertDesRow = -1;
            this.showHiddenNotifRow = -1;
            this.showHiddenNotifDesRow = -1;
        } else {
            i = this.rowCount;
            this.rowCount = i + 1;
            this.patternRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.changePatternRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.patternDetailRow = i;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.listView != null) {
            this.listView.getViewTreeObserver().addOnPreDrawListener(new C24159());
        }
    }

    public void onTransitionAnimationEnd(boolean isOpen, boolean backward) {
        if (isOpen && this.pageType != 0) {
            AndroidUtilities.showKeyboard(this.passwordEditText);
        }
    }

    private void processNext() {
        if (this.lockType == 0) {
            if (this.passwordEditText.getText().length() == 0) {
                onPasscodeError();
                return;
            }
            this.titleTextView.setText(LocaleController.getString("ReEnterYourPasscode", R.string.ReEnterYourPasscode));
            this.firstPassword = this.passwordEditText.getText().toString();
            this.passwordEditText.setText("");
        } else if (this.lockType == 1) {
            if (this.patternView.getPatternString().length() == 0) {
                onPasscodeError();
                return;
            }
            this.titleTextView.setText(LocaleController.getString("ReDrawYourPattern", R.string.ReDrawYourPattern));
            this.firstPassword = this.patternView.getPatternString().toString();
            this.patternView.clearPattern();
        }
        this.passcodeSetStep = 1;
    }

    private void processDone() {
        if (this.lockType == 0) {
            if (this.passwordEditText.getText().length() == 0) {
                onPasscodeError();
            } else if (this.pageType == 1) {
                if (this.firstPassword.equals(this.passwordEditText.getText().toString())) {
                    if (this.checkType == 1) {
                        TurboConfig.setStringValue("chat_lock_pass", this.firstPassword);
                    } else if (this.checkType == 0) {
                        TurboConfig.setStringValue("chat_password", this.firstPassword);
                    }
                    finishFragment();
                    this.passwordEditText.clearFocus();
                    AndroidUtilities.hideKeyboard(this.passwordEditText);
                    return;
                }
                try {
                    TurboUtils.showToast(getParentActivity(), LocaleController.getString("PasscodeDoNotMatch", R.string.PasscodeDoNotMatch), 0);
                } catch (Exception e) {
                    FileLog.e("tmessages", e);
                }
                AndroidUtilities.shakeView(this.titleTextView, 2.0f, 0);
                this.passwordEditText.setText("");
            } else if (this.pageType == 2) {
                if (getPasscode().equals(this.passwordEditText.getText().toString())) {
                    this.passwordEditText.clearFocus();
                    AndroidUtilities.hideKeyboard(this.passwordEditText);
                    presentFragment(new SetPasscodeActivity(0, this.checkType), true);
                    return;
                }
                this.passwordEditText.setText("");
                onPasscodeError();
            } else if (this.pageType != 3) {
            } else {
                if (getPasscode().equals(this.passwordEditText.getText().toString())) {
                    this.passwordEditText.clearFocus();
                    AndroidUtilities.hideKeyboard(this.passwordEditText);
                    presentFragment(new SetPasscodeActivity(1, this.checkType), true);
                    return;
                }
                this.passwordEditText.setText("");
                onPasscodeError();
            }
        } else if (this.lockType != 1) {
        } else {
            if (this.patternView.getPatternString().length() == 0) {
                onPasscodeError();
            } else if (this.pageType == 1) {
                if (this.firstPassword.equals(this.patternView.getPatternString().toString())) {
                    if (this.checkType == 1) {
                        TurboConfig.setStringValue("chat_lock_patt", this.firstPassword);
                    } else if (this.checkType == 0) {
                        TurboConfig.setStringValue("chat_hide_patt", this.firstPassword);
                    } else if (this.checkType == 2) {
                        TurboConfig.setStringValue("app_lock_patt", this.firstPassword);
                    }
                    finishFragment();
                    return;
                }
                try {
                    TurboUtils.showToast(getParentActivity(), LocaleController.getString("PatternDoNotMatch", R.string.PatternDoNotMatch), 0);
                } catch (Exception e2) {
                    FileLog.e("tmessages", e2);
                }
                this.patternView.clearPattern();
            } else if (this.pageType != 3) {
            } else {
                if (getPattern().equals(this.patternView.getPatternString().toString())) {
                    this.patternView.clearPattern();
                    presentFragment(new SetPasscodeActivity(1, 1, this.checkType), true);
                    return;
                }
                this.patternView.clearPattern();
                onPasscodeError();
            }
        }
    }

    private void onPasscodeError() {
        if (getParentActivity() != null) {
            Vibrator v = (Vibrator) getParentActivity().getSystemService("vibrator");
            if (v != null) {
                v.vibrate(200);
            }
            AndroidUtilities.shakeView(this.titleTextView, 2.0f, 0);
        }
    }

    private String getPasscode() {
        String pass = TurboConfig.chatHidePasscode;
        if (this.checkType == 1) {
            return TurboConfig.chatLockPasscode;
        }
        return pass;
    }

    private String getPattern() {
        String patt = TurboConfig.chatHidePattern;
        if (this.checkType == 1) {
            return TurboConfig.chatLockPattern;
        }
        if (this.checkType == 2) {
            return TurboConfig.appLockPattern;
        }
        return patt;
    }
}
