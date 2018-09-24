package org.telegram.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.baranak.turbogramf.R;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.ContactsController.Contact;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.MessagesStorage.IntCallback;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate;
import org.telegram.ui.Adapters.ContactsAdapter;
import org.telegram.ui.Adapters.SearchAdapter;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.LetterSectionCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import turbogram.Cells.ContactsGraySectionCell;
import turbogram.MultiSelectContactActivity;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboUtils;

public class ContactsActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    private static final int add_button = 1;
    private static final int delete_button = 2;
    private static final int done_button = 3;
    private static final int search_button = 0;
    private ActionBarMenuItem addItem;
    private boolean addingToChannel;
    private boolean allowBots = true;
    private boolean allowUsernameSearch = true;
    private boolean askAboutContacts = true;
    private int chat_id;
    private boolean checkPermission = true;
    private boolean createSecretChat;
    private boolean creatingChat;
    private ContactsActivityDelegate delegate;
    private boolean destroyAfterSelect;
    private EmptyTextProgressView emptyView;
    private SparseArray<User> ignoreUsers;
    private RecyclerListView listView;
    private ContactsAdapter listViewAdapter;
    private boolean needFinishFragment = true;
    private boolean needForwardCount = true;
    private boolean needPhonebook;
    private boolean onlyShowContatcs = false;
    private boolean onlyUsers;
    private AlertDialog permissionDialog;
    private boolean returnAsResult;
    private SearchAdapter searchListViewAdapter;
    private boolean searchWas;
    private boolean searching;
    private String selectAlertString = null;

    public interface ContactsActivityDelegate {
        void didSelectContact(User user, String str, ContactsActivity contactsActivity);
    }

    /* renamed from: org.telegram.ui.ContactsActivity$1 */
    class C17041 extends ActionBarMenuOnItemClick {
        C17041() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ContactsActivity.this.finishFragment();
            } else if (id == 1) {
                ContactsActivity.this.presentFragment(new NewContactActivity());
            } else if (id == 2) {
                Bundle args = new Bundle();
                args.putInt(Param.TYPE, 1);
                ContactsActivity.this.presentFragment(new MultiSelectContactActivity(args));
            }
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$5 */
    class C17085 implements OnItemClickListener {
        C17085() {
        }

        public void onItemClick(View view, int position) {
            User user;
            Bundle args;
            if (ContactsActivity.this.searching && ContactsActivity.this.searchWas) {
                user = (User) ContactsActivity.this.searchListViewAdapter.getItem(position);
                if (user != null) {
                    if (ContactsActivity.this.searchListViewAdapter.isGlobalSearch(position)) {
                        ArrayList<User> users = new ArrayList();
                        users.add(user);
                        MessagesController.getInstance(ContactsActivity.this.currentAccount).putUsers(users, false);
                        MessagesStorage.getInstance(ContactsActivity.this.currentAccount).putUsersAndChats(users, null, false, true);
                    }
                    if (ContactsActivity.this.returnAsResult) {
                        if (ContactsActivity.this.ignoreUsers == null || ContactsActivity.this.ignoreUsers.indexOfKey(user.id) < 0) {
                            ContactsActivity.this.didSelectResult(user, true, null);
                            return;
                        }
                        return;
                    } else if (!ContactsActivity.this.createSecretChat) {
                        args = new Bundle();
                        args.putInt("user_id", user.id);
                        if (MessagesController.getInstance(ContactsActivity.this.currentAccount).checkCanOpenChat(args, ContactsActivity.this)) {
                            ContactsActivity.this.presentFragment(new ChatActivity(args), !TurboConfig.keepContactsOpen);
                            return;
                        }
                        return;
                    } else if (user.id != UserConfig.getInstance(ContactsActivity.this.currentAccount).getClientUserId()) {
                        ContactsActivity.this.creatingChat = true;
                        SecretChatHelper.getInstance(ContactsActivity.this.currentAccount).startSecretChat(ContactsActivity.this.getParentActivity(), user);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            int section = ContactsActivity.this.listViewAdapter.getSectionForPosition(position);
            int row = ContactsActivity.this.listViewAdapter.getPositionInSectionForPosition(position);
            if (row >= 0 && section >= 0) {
                if ((ContactsActivity.this.onlyUsers && ContactsActivity.this.chat_id == 0) || section != 0) {
                    Contact item = ContactsActivity.this.listViewAdapter.getItem(section, row);
                    if (item instanceof User) {
                        user = (User) item;
                        if (ContactsActivity.this.returnAsResult) {
                            if (ContactsActivity.this.ignoreUsers == null || ContactsActivity.this.ignoreUsers.indexOfKey(user.id) < 0) {
                                ContactsActivity.this.didSelectResult(user, true, null);
                            }
                        } else if (ContactsActivity.this.createSecretChat) {
                            ContactsActivity.this.creatingChat = true;
                            SecretChatHelper.getInstance(ContactsActivity.this.currentAccount).startSecretChat(ContactsActivity.this.getParentActivity(), user);
                        } else {
                            args = new Bundle();
                            args.putInt("user_id", user.id);
                            if (MessagesController.getInstance(ContactsActivity.this.currentAccount).checkCanOpenChat(args, ContactsActivity.this)) {
                                ContactsActivity.this.presentFragment(new ChatActivity(args), !TurboConfig.keepContactsOpen);
                            }
                        }
                    } else if (item instanceof Contact) {
                        Contact contact = item;
                        String usePhone = null;
                        if (!contact.phones.isEmpty()) {
                            usePhone = (String) contact.phones.get(0);
                        }
                        if (usePhone != null && ContactsActivity.this.getParentActivity() != null) {
                            Builder builder = new Builder(ContactsActivity.this.getParentActivity());
                            builder.setMessage(LocaleController.getString("InviteUser", R.string.InviteUser));
                            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new ContactsActivity$5$$Lambda$0(this, usePhone));
                            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                            ContactsActivity.this.showDialog(builder.create());
                        }
                    }
                } else if (ContactsActivity.this.needPhonebook) {
                    if (row == 0) {
                        ContactsActivity.this.presentFragment(new InviteContactsActivity());
                    } else if (row == 1) {
                        TurboConfig$BG.toggleBooleanValue("show_onlines", TurboConfig$BG.showOnlines);
                        if (view instanceof ContactsGraySectionCell) {
                            ((ContactsGraySectionCell) view).setChecked(TurboConfig$BG.showOnlines);
                        }
                        r13 = ContactsActivity.this.listViewAdapter;
                        r12 = TurboConfig$BG.showOnlines && !ContactsActivity.this.createSecretChat;
                        r13.setShowOnlines(r12);
                        ContactsController.getInstance(ContactsActivity.this.currentAccount).cleanup();
                        ContactsController.getInstance(ContactsActivity.this.currentAccount).readContacts();
                        if (ContactsActivity.this.listViewAdapter != null) {
                            ContactsActivity.this.listViewAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (ContactsActivity.this.chat_id != 0) {
                    if (row == 0) {
                        ContactsActivity.this.presentFragment(new GroupInviteActivity(ContactsActivity.this.chat_id));
                    }
                } else if (row == 0) {
                    ContactsActivity.this.presentFragment(new GroupCreateActivity(), false);
                } else if (row == 1) {
                    args = new Bundle();
                    args.putBoolean("onlyUsers", true);
                    args.putBoolean("destroyAfterSelect", true);
                    args.putBoolean("createSecretChat", true);
                    args.putBoolean("allowBots", false);
                    ContactsActivity.this.presentFragment(new ContactsActivity(args), false);
                } else if (row == 2) {
                    SharedPreferences preferences = MessagesController.getGlobalMainSettings();
                    if (BuildVars.DEBUG_VERSION || !preferences.getBoolean("channel_intro", false)) {
                        ContactsActivity.this.presentFragment(new ChannelIntroActivity());
                        preferences.edit().putBoolean("channel_intro", true).commit();
                        return;
                    }
                    args = new Bundle();
                    args.putInt("step", 0);
                    ContactsActivity.this.presentFragment(new ChannelCreateActivity(args));
                } else if (row == 3) {
                    TurboConfig$BG.toggleBooleanValue("show_onlines", TurboConfig$BG.showOnlines);
                    if (view instanceof ContactsGraySectionCell) {
                        ((ContactsGraySectionCell) view).setChecked(TurboConfig$BG.showOnlines);
                    }
                    r13 = ContactsActivity.this.listViewAdapter;
                    r12 = TurboConfig$BG.showOnlines && !ContactsActivity.this.createSecretChat;
                    r13.setShowOnlines(r12);
                    ContactsController.getInstance(ContactsActivity.this.currentAccount).cleanup();
                    ContactsController.getInstance(ContactsActivity.this.currentAccount).readContacts();
                    if (ContactsActivity.this.listViewAdapter != null) {
                        ContactsActivity.this.listViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

        final /* synthetic */ void lambda$onItemClick$0$ContactsActivity$5(String arg1, DialogInterface dialogInterface, int i) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.fromParts("sms", arg1, null));
                intent.putExtra("sms_body", ContactsController.getInstance(ContactsActivity.this.currentAccount).getInviteText(1));
                ContactsActivity.this.getParentActivity().startActivityForResult(intent, 500);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$6 */
    class C17096 extends OnScrollListener {
        C17096() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            boolean z = true;
            if (newState == 1 && ContactsActivity.this.searching && ContactsActivity.this.searchWas) {
                AndroidUtilities.hideKeyboard(ContactsActivity.this.getParentActivity().getCurrentFocus());
            }
            if (ContactsActivity.this.listViewAdapter != null) {
                ContactsAdapter access$500 = ContactsActivity.this.listViewAdapter;
                if (newState == 0) {
                    z = false;
                }
                access$500.setIsScrolling(z);
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$9 */
    class C17129 implements IntCallback {
        C17129() {
        }

        public void run(int param) {
            boolean z;
            ContactsActivity contactsActivity = ContactsActivity.this;
            if (param != 0) {
                z = true;
            } else {
                z = false;
            }
            contactsActivity.askAboutContacts = z;
            MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts", ContactsActivity.this.askAboutContacts).commit();
            ContactsActivity.this.askForPermissons(false);
        }
    }

    public ContactsActivity(Bundle args) {
        super(args);
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.encryptedChatCreated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.closeChats);
        MessagesController.getGlobalNotificationsSettings().getBoolean("askAboutContacts", true);
        this.checkPermission = UserConfig.getInstance(this.currentAccount).syncContacts;
        if (this.arguments != null) {
            this.onlyUsers = getArguments().getBoolean("onlyUsers", false);
            this.destroyAfterSelect = this.arguments.getBoolean("destroyAfterSelect", false);
            this.returnAsResult = this.arguments.getBoolean("returnAsResult", false);
            this.createSecretChat = this.arguments.getBoolean("createSecretChat", false);
            this.selectAlertString = this.arguments.getString("selectAlertString");
            this.allowUsernameSearch = this.arguments.getBoolean("allowUsernameSearch", true);
            this.needForwardCount = this.arguments.getBoolean("needForwardCount", true);
            this.allowBots = this.arguments.getBoolean("allowBots", true);
            this.addingToChannel = this.arguments.getBoolean("addingToChannel", false);
            this.needFinishFragment = this.arguments.getBoolean("needFinishFragment", true);
            this.chat_id = this.arguments.getInt("chat_id", 0);
            this.onlyShowContatcs = this.arguments.getBoolean("only_show_contacts", false);
        } else {
            this.needPhonebook = true;
        }
        ContactsController.getInstance(this.currentAccount).checkInviteText();
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.encryptedChatCreated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.closeChats);
        this.delegate = null;
    }

    public View createView(Context context) {
        int i;
        this.searching = false;
        this.searchWas = false;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        if (!this.destroyAfterSelect) {
            this.actionBar.setTitle(LocaleController.getString("Contacts", R.string.Contacts));
        } else if (this.returnAsResult) {
            this.actionBar.setTitle(LocaleController.getString("SelectContact", R.string.SelectContact));
        } else if (this.createSecretChat) {
            this.actionBar.setTitle(LocaleController.getString("NewSecretChat", R.string.NewSecretChat));
        } else {
            this.actionBar.setTitle(LocaleController.getString("NewMessageTitle", R.string.NewMessageTitle));
        }
        this.actionBar.setActionBarMenuOnItemClick(new C17041());
        final ActionBarMenu menu = this.actionBar.createMenu();
        if (this.onlyShowContatcs) {
            menu.addItem(2, (int) R.drawable.turbo_delete);
        }
        ActionBarMenuItem item = menu.addItem(0, (int) R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItemSearchListener() {
            public void onSearchExpand() {
                ContactsActivity.this.searching = true;
                if (ContactsActivity.this.addItem != null) {
                    ContactsActivity.this.addItem.setVisibility(8);
                }
                if (ContactsActivity.this.onlyShowContatcs) {
                    menu.getItem(2).setVisibility(8);
                }
            }

            public void onSearchCollapse() {
                if (ContactsActivity.this.addItem != null) {
                    ContactsActivity.this.addItem.setVisibility(0);
                }
                ContactsActivity.this.searchListViewAdapter.searchDialogs(null);
                ContactsActivity.this.searching = false;
                ContactsActivity.this.searchWas = false;
                ContactsActivity.this.listView.setAdapter(ContactsActivity.this.listViewAdapter);
                ContactsActivity.this.listViewAdapter.notifyDataSetChanged();
                ContactsActivity.this.listView.setFastScrollVisible(true);
                ContactsActivity.this.listView.setVerticalScrollBarEnabled(false);
                ContactsActivity.this.listView.setEmptyView(null);
                ContactsActivity.this.emptyView.setText(LocaleController.getString("NoContacts", R.string.NoContacts));
                if (ContactsActivity.this.onlyShowContatcs) {
                    menu.getItem(2).setVisibility(0);
                }
            }

            public void onTextChanged(EditText editText) {
                if (ContactsActivity.this.searchListViewAdapter != null) {
                    String text = editText.getText().toString();
                    if (text.length() != 0) {
                        ContactsActivity.this.searchWas = true;
                        if (ContactsActivity.this.listView != null) {
                            ContactsActivity.this.listView.setAdapter(ContactsActivity.this.searchListViewAdapter);
                            ContactsActivity.this.searchListViewAdapter.notifyDataSetChanged();
                            ContactsActivity.this.listView.setFastScrollVisible(false);
                            ContactsActivity.this.listView.setVerticalScrollBarEnabled(true);
                        }
                        if (ContactsActivity.this.emptyView != null) {
                            ContactsActivity.this.listView.setEmptyView(ContactsActivity.this.emptyView);
                            ContactsActivity.this.emptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
                        }
                    }
                    ContactsActivity.this.searchListViewAdapter.searchDialogs(text);
                }
            }
        });
        item.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));
        item.getSearchField().setTypeface(TurboUtils.getTurboTypeFace());
        if (this.createSecretChat || this.returnAsResult) {
            menu.addItem(3, (int) R.drawable.ic_done);
        } else {
            this.addItem = menu.addItem(1, (int) R.drawable.add);
        }
        this.searchListViewAdapter = new SearchAdapter(context, this.ignoreUsers, this.allowUsernameSearch, false, false, this.allowBots, 0);
        if (this.onlyUsers) {
            i = 1;
        } else {
            i = 0;
        }
        this.listViewAdapter = new ContactsAdapter(context, i, this.needPhonebook, this.ignoreUsers, this.chat_id != 0) {
            public void notifyDataSetChanged() {
                int i = 8;
                boolean z = true;
                boolean z2 = false;
                super.notifyDataSetChanged();
                if (ContactsActivity.this.listView.getAdapter() == this) {
                    int count = super.getItemCount();
                    EmptyTextProgressView access$700;
                    RecyclerListView access$600;
                    if (ContactsActivity.this.needPhonebook) {
                        access$700 = ContactsActivity.this.emptyView;
                        if (count == 2) {
                            i = 0;
                        }
                        access$700.setVisibility(i);
                        access$600 = ContactsActivity.this.listView;
                        if (count != 2) {
                            z2 = true;
                        }
                        access$600.setFastScrollVisible(z2);
                        return;
                    }
                    access$700 = ContactsActivity.this.emptyView;
                    if (count == 0) {
                        i = 0;
                    }
                    access$700.setVisibility(i);
                    access$600 = ContactsActivity.this.listView;
                    if (count == 0) {
                        z = false;
                    }
                    access$600.setFastScrollVisible(z);
                }
            }
        };
        ContactsAdapter contactsAdapter = this.listViewAdapter;
        boolean z = this.onlyShowContatcs && TurboConfig$BG.showOnlines && !this.createSecretChat;
        contactsAdapter.setShowOnlines(z);
        this.listViewAdapter.setShowSwitch(this.onlyShowContatcs);
        this.fragmentView = new FrameLayout(context) {
            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                super.onLayout(changed, left, top, right, bottom);
                if (ContactsActivity.this.listView.getAdapter() != ContactsActivity.this.listViewAdapter) {
                    ContactsActivity.this.emptyView.setTranslationY((float) AndroidUtilities.dp(0.0f));
                } else if (ContactsActivity.this.emptyView.getVisibility() == 0) {
                    ContactsActivity.this.emptyView.setTranslationY((float) AndroidUtilities.dp(74.0f));
                }
            }
        };
        FrameLayout frameLayout = this.fragmentView;
        this.emptyView = new EmptyTextProgressView(context);
        this.emptyView.setShowAtCenter(true);
        this.emptyView.setText(LocaleController.getString("NoContacts", R.string.NoContacts));
        this.emptyView.showTextView();
        frameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(context);
        this.listView.setSectionsType(1);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setFastScrollEnabled();
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setAdapter(this.listViewAdapter);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new C17085());
        this.listView.setOnScrollListener(new C17096());
        return this.fragmentView;
    }

    private void didSelectResult(final User user, boolean useAlert, String param) {
        if (!useAlert || this.selectAlertString == null) {
            if (this.delegate != null) {
                this.delegate.didSelectContact(user, param, this);
                this.delegate = null;
            }
            if (this.needFinishFragment) {
                finishFragment();
            }
        } else if (getParentActivity() != null) {
            if (user.bot && user.bot_nochats && !this.addingToChannel) {
                try {
                    Toast.makeText(getParentActivity(), LocaleController.getString("BotCantJoinGroups", R.string.BotCantJoinGroups), 0).show();
                    return;
                } catch (Exception e) {
                    FileLog.e(e);
                    return;
                }
            }
            Builder builder = new Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            String message = LocaleController.formatStringSimple(this.selectAlertString, new Object[]{UserObject.getUserName(user)});
            EditText editText = null;
            if (!user.bot && this.needForwardCount) {
                message = String.format("%s\n\n%s", new Object[]{message, LocaleController.getString("AddToTheGroupForwardCount", R.string.AddToTheGroupForwardCount)});
                editText = new EditText(getParentActivity());
                editText.setTextSize(1, 18.0f);
                editText.setText("50");
                editText.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
                editText.setGravity(17);
                editText.setInputType(2);
                editText.setImeOptions(6);
                editText.setBackgroundDrawable(Theme.createEditTextDrawable(getParentActivity(), true));
                final EditText editTextFinal = editText;
                editText.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    public void afterTextChanged(Editable s) {
                        try {
                            String str = s.toString();
                            if (str.length() != 0) {
                                int value = Utilities.parseInt(str).intValue();
                                if (value < 0) {
                                    editTextFinal.setText("0");
                                    editTextFinal.setSelection(editTextFinal.length());
                                } else if (value > 300) {
                                    editTextFinal.setText("300");
                                    editTextFinal.setSelection(editTextFinal.length());
                                } else if (!str.equals("" + value)) {
                                    editTextFinal.setText("" + value);
                                    editTextFinal.setSelection(editTextFinal.length());
                                }
                            }
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                    }
                });
                builder.setView(editText);
            }
            builder.setMessage(message);
            final EditText finalEditText = editText;
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ContactsActivity.this.didSelectResult(user, false, finalEditText != null ? finalEditText.getText().toString() : "0");
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
            if (editText != null) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) editText.getLayoutParams();
                if (layoutParams != null) {
                    if (layoutParams instanceof LayoutParams) {
                        ((LayoutParams) layoutParams).gravity = 1;
                    }
                    int dp = AndroidUtilities.dp(24.0f);
                    layoutParams.leftMargin = dp;
                    layoutParams.rightMargin = dp;
                    layoutParams.height = AndroidUtilities.dp(36.0f);
                    editText.setLayoutParams(layoutParams);
                }
                editText.setSelection(editText.getText().length());
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (this.listViewAdapter != null) {
            this.listViewAdapter.notifyDataSetChanged();
        }
        if (this.checkPermission && VERSION.SDK_INT >= 23) {
            Activity activity = getParentActivity();
            if (activity != null) {
                this.checkPermission = false;
                if (activity.checkSelfPermission("android.permission.READ_CONTACTS") == 0) {
                    return;
                }
                if (activity.shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                    Dialog create = AlertsCreator.createContactsPermissionDialog(activity, new C17129()).create();
                    this.permissionDialog = create;
                    showDialog(create);
                    return;
                }
                askForPermissons(true);
            }
        }
    }

    protected void onDialogDismiss(Dialog dialog) {
        super.onDialogDismiss(dialog);
        if (this.permissionDialog != null && dialog == this.permissionDialog && getParentActivity() != null) {
            askForPermissons(false);
        }
    }

    @TargetApi(23)
    private void askForPermissons(boolean alert) {
        Activity activity = getParentActivity();
        if (activity != null && UserConfig.getInstance(this.currentAccount).syncContacts && activity.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            if (alert && this.askAboutContacts) {
                showDialog(AlertsCreator.createContactsPermissionDialog(activity, new IntCallback() {
                    public void run(int param) {
                        boolean z;
                        ContactsActivity contactsActivity = ContactsActivity.this;
                        if (param != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        contactsActivity.askAboutContacts = z;
                        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts", ContactsActivity.this.askAboutContacts).commit();
                        ContactsActivity.this.askForPermissons(false);
                    }
                }).create());
                return;
            }
            ArrayList<String> permissons = new ArrayList();
            permissons.add("android.permission.READ_CONTACTS");
            permissons.add("android.permission.WRITE_CONTACTS");
            permissons.add("android.permission.GET_ACCOUNTS");
            activity.requestPermissions((String[]) permissons.toArray(new String[permissons.size()]), 1);
        }
    }

    public void onRequestPermissionsResultFragment(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int a = 0; a < permissions.length; a++) {
                if (grantResults.length > a) {
                    String str = permissions[a];
                    boolean z = true;
                    switch (str.hashCode()) {
                        case 1977429404:
                            if (str.equals("android.permission.READ_CONTACTS")) {
                                z = false;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                            if (grantResults[a] != 0) {
                                this.askAboutContacts = false;
                                MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts", false).commit();
                                break;
                            }
                            ContactsController.getInstance(this.currentAccount).forceImportContacts();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.actionBar != null) {
            this.actionBar.closeSearchField();
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.contactsDidLoaded) {
            if (this.listViewAdapter != null) {
                this.listViewAdapter.notifyDataSetChanged();
            }
        } else if (id == NotificationCenter.updateInterfaces) {
            int mask = ((Integer) args[0]).intValue();
            if ((mask & 2) != 0 || (mask & 1) != 0 || (mask & 4) != 0) {
                updateVisibleRows(mask);
            }
        } else if (id == NotificationCenter.encryptedChatCreated) {
            if (this.createSecretChat && this.creatingChat) {
                TLRPC$EncryptedChat encryptedChat = args[0];
                Bundle args2 = new Bundle();
                args2.putInt("enc_id", encryptedChat.id);
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
                presentFragment(new ChatActivity(args2), true);
            }
        } else if (id == NotificationCenter.closeChats && !this.creatingChat) {
            removeSelfFromStack();
        }
    }

    private void updateVisibleRows(int mask) {
        if (this.listView != null) {
            int count = this.listView.getChildCount();
            for (int a = 0; a < count; a++) {
                View child = this.listView.getChildAt(a);
                if (child instanceof UserCell) {
                    ((UserCell) child).update(mask);
                }
            }
        }
    }

    public void setDelegate(ContactsActivityDelegate delegate) {
        this.delegate = delegate;
    }

    public void setIgnoreUsers(SparseArray<User> users) {
        this.ignoreUsers = users;
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescription theme1;
        ThemeDescription theme2;
        ThemeDescriptionDelegate cellDelegate = new ThemeDescriptionDelegate() {
            public void didSetColor() {
                if (ContactsActivity.this.listView != null) {
                    int count = ContactsActivity.this.listView.getChildCount();
                    for (int a = 0; a < count; a++) {
                        View child = ContactsActivity.this.listView.getChildAt(a);
                        if (child instanceof UserCell) {
                            ((UserCell) child).update(0);
                        } else if (child instanceof ProfileSearchCell) {
                            ((ProfileSearchCell) child).update(0);
                        }
                    }
                }
            }
        };
        if (this.onlyShowContatcs) {
            theme1 = new ThemeDescription(this.listView, 0, new Class[]{ContactsGraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2);
            theme2 = new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ContactsGraySectionCell.class}, null, null, null, Theme.key_graySection);
        } else {
            ThemeDescription themeDescription = new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2);
            theme2 = new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection);
        }
        r12 = new ThemeDescription[30];
        r12[9] = new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{LetterSectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4);
        r12[10] = new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider);
        r12[11] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder);
        r12[12] = new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive);
        r12[13] = new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive);
        r12[14] = new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText);
        r12[15] = new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        r12[16] = new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusColor"}, null, null, cellDelegate, Theme.key_windowBackgroundWhiteGrayText);
        r12[17] = new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusOnlineColor"}, null, null, cellDelegate, Theme.key_windowBackgroundWhiteBlueText);
        r12[18] = new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, new Drawable[]{Theme.avatar_photoDrawable, Theme.avatar_broadcastDrawable, Theme.avatar_savedDrawable}, null, Theme.key_avatar_text);
        r12[19] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundRed);
        r12[20] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundOrange);
        r12[21] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundViolet);
        r12[22] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundGreen);
        r12[23] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundCyan);
        r12[24] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundBlue);
        r12[25] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundPink);
        r12[26] = new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        r12[27] = new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon);
        r12[28] = theme1;
        r12[29] = theme2;
        return r12;
    }
}
