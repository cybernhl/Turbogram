package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baranak.turbogramf.R;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.LinearSmoothScrollerMiddle;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.State;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatInvite;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$StickerSet;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetID;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_recentMeUrlChat;
import org.telegram.tgnet.TLRPC$TL_recentMeUrlChatInvite;
import org.telegram.tgnet.TLRPC$TL_recentMeUrlStickerSet;
import org.telegram.tgnet.TLRPC$TL_recentMeUrlUnknown;
import org.telegram.tgnet.TLRPC$TL_recentMeUrlUser;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.MenuDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.Adapters.DialogsSearchAdapter;
import org.telegram.ui.Adapters.DialogsSearchAdapter.DialogsSearchAdapterDelegate;
import org.telegram.ui.Cells.AccountSelectCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.DialogsEmptyCell;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.DrawerActionCell;
import org.telegram.ui.Cells.DrawerAddCell;
import org.telegram.ui.Cells.DrawerProfileCell;
import org.telegram.ui.Cells.DrawerUserCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HashtagSearchCell;
import org.telegram.ui.Cells.HintDialogCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedArrowDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.FragmentContextView;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProxyDrawable;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickersAlert;
import turbogram.Cells.TabsViewCell;
import turbogram.ChatPreviewActivity;
import turbogram.ChatSettingsActivity;
import turbogram.Components.DialogsCategoryAlert;
import turbogram.Components.Fam.FloatingActionButton;
import turbogram.Components.Fam.FloatingActionsMenu;
import turbogram.Components.TabsView;
import turbogram.Components.TabsView.TabsViewDelegate;
import turbogram.Components.TurboPasscodeView;
import turbogram.Components.TurboPasscodeView.TurboPasscodeViewDelegate;
import turbogram.Components.TurboPatternView;
import turbogram.Components.TurboPatternView.TurboPatternViewDelegate;
import turbogram.Database.DBHelper;
import turbogram.OnlineContactsActivity;
import turbogram.SetPasscodeActivity;
import turbogram.TurboSettingsActivity;
import turbogram.Utilities.DialogsLoader;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboConfig$Tabs;
import turbogram.Utilities.TurboConfig$Toast;
import turbogram.Utilities.TurboConfig.Toolbar;
import turbogram.Utilities.TurboUtils;

public class DialogsActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    public static boolean[] dialogsLoaded = new boolean[3];
    private boolean addToGroup;
    private String addToGroupAlertString;
    private boolean allowSwitchAccount;
    private AnimatedArrowDrawable arrowDrawable;
    private boolean askAboutContacts = true;
    private BackupImageView avatarImage;
    private ImageView button1;
    private ImageView button2;
    private ImageView button3;
    private ImageView button4;
    private ImageView button5;
    private ImageView button6;
    private ImageView button7;
    private boolean cantSendToChannels;
    private int catId;
    private int catOperationType;
    private ActionBarMenuItem categorytem;
    private boolean checkPermission = true;
    private ChatActivityEnterView commentView;
    private SizeNotifierFrameLayout contentView;
    private int currentConnectionState;
    private int currentUnreadCount;
    private DialogsActivityDelegate delegate;
    private DialogsAdapter dialogsAdapter;
    private DialogsSearchAdapter dialogsSearchAdapter;
    private int dialogsType;
    private float downX;
    private float downY;
    private FloatingActionsMenu fam;
    private ImageView floatingButton;
    private boolean floatingHidden;
    private final AccelerateDecelerateInterpolator floatingInterpolator = new AccelerateDecelerateInterpolator();
    private ActionBarMenuItem headerItem;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private boolean multiSelection;
    private boolean onlySelect;
    private long openedDialogId;
    private ActionBarMenuItem passcodeItem;
    private AlertDialog permissionDialog;
    private int prevPosition;
    private int prevTop;
    private RadialProgressView progressView;
    private ProxyDrawable proxyDrawable;
    private ActionBarMenuItem proxyItem;
    private boolean proxyItemVisisble;
    private ActionBarMenuItem quotItem;
    private boolean scrollUpdated;
    private EmptyTextProgressView searchEmptyView;
    private String searchString;
    private boolean searchWas;
    private boolean searching;
    private String selectAlertString;
    private String selectAlertStringGroup;
    private long selectedDialog;
    private RecyclerView sideMenu;
    private ActionBarMenuItem switchItem;
    private int tabsHeight;
    private boolean tabsHidden;
    private TabsView tabsView;
    private LinearLayout toolBar;
    private ImageView unreadFloatingButton;
    private FrameLayout unreadFloatingButtonContainer;
    private TextView unreadFloatingButtonCounter;
    private float upX;
    private float upY;

    public interface DialogsActivityDelegate {
        void didSelectDialogs(DialogsActivity dialogsActivity, ArrayList<Long> arrayList, CharSequence charSequence, boolean z);
    }

    /* renamed from: org.telegram.ui.DialogsActivity$5 */
    class C17395 implements OnItemLongClickListenerExtended {

        /* renamed from: org.telegram.ui.DialogsActivity$5$1 */
        class C17361 implements OnClickListener {
            C17361() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(DialogsActivity.this.selectedDialog, 2);
            }
        }

        /* renamed from: org.telegram.ui.DialogsActivity$5$2 */
        class C17372 implements OnClickListener {
            C17372() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                TurboUtils.clearChatData(DialogsActivity.this.currentAccount, DialogsActivity.this.selectedDialog);
                MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteUserFromChat((int) (-DialogsActivity.this.selectedDialog), UserConfig.getInstance(DialogsActivity.this.currentAccount).getCurrentUser(), null);
                if (AndroidUtilities.isTablet()) {
                    NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(DialogsActivity.this.selectedDialog));
                }
            }
        }

        C17395() {
        }

        public boolean onItemClick(View view, int position, float x, float y) {
            if (DialogsActivity.this.getParentActivity() == null) {
                return false;
            }
            int high_id;
            TLRPC$Chat chat;
            if (!(AndroidUtilities.isTablet() || DialogsActivity.this.onlySelect || !(view instanceof DialogCell))) {
                DialogCell cell = (DialogCell) view;
                if (cell.isPointInsideAvatar(x, y)) {
                    long dialog_id = cell.getDialogId();
                    Bundle args = new Bundle();
                    int lower_part = (int) dialog_id;
                    high_id = (int) (dialog_id >> 32);
                    int message_id = cell.getMessageId();
                    if (lower_part == 0) {
                        return false;
                    }
                    if (high_id == 1) {
                        args.putInt("chat_id", lower_part);
                    } else if (lower_part > 0) {
                        args.putInt("user_id", lower_part);
                    } else if (lower_part < 0) {
                        if (message_id != 0) {
                            chat = MessagesController.getInstance(DialogsActivity.this.currentAccount).getChat(Integer.valueOf(-lower_part));
                            if (!(chat == null || chat.migrated_to == null)) {
                                args.putInt("migrated_to", lower_part);
                                lower_part = -chat.migrated_to.channel_id;
                            }
                        }
                        args.putInt("chat_id", -lower_part);
                    }
                    if (message_id != 0) {
                        args.putInt("message_id", message_id);
                    }
                    if (TurboConfig.chatLockPasscode.length() != 0 && TurboConfig.containValue("lock_chat" + dialog_id)) {
                        return false;
                    }
                    if (TurboConfig.chatPreview) {
                        Intent intent = new Intent(DialogsActivity.this.getParentActivity(), ChatPreviewActivity.class);
                        if (lower_part != 0) {
                            if (high_id == 1) {
                                intent.putExtra("chat_id", lower_part);
                            } else if (lower_part > 0) {
                                intent.putExtra("user_id", lower_part);
                            } else if (lower_part < 0) {
                                intent.putExtra("chat_id", -lower_part);
                            }
                        }
                        DialogsActivity.this.getParentActivity().startActivity(intent);
                        return true;
                    }
                    if (DialogsActivity.this.searchString != null) {
                        if (MessagesController.getInstance(DialogsActivity.this.currentAccount).checkCanOpenChat(args, DialogsActivity.this)) {
                            NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
                            DialogsActivity.this.presentFragmentAsPreview(new ChatActivity(args));
                        }
                    } else {
                        if (MessagesController.getInstance(DialogsActivity.this.currentAccount).checkCanOpenChat(args, DialogsActivity.this)) {
                            DialogsActivity.this.presentFragmentAsPreview(new ChatActivity(args));
                        }
                    }
                    return true;
                }
            }
            if (DialogsActivity.this.listView.getAdapter() != DialogsActivity.this.dialogsSearchAdapter) {
                ArrayList<TLRPC$TL_dialog> dialogs = DialogsActivity.this.getDialogsArray();
                if (position < 0 || position >= dialogs.size()) {
                    return false;
                }
                TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) dialogs.get(position);
                if (!DialogsActivity.this.onlySelect) {
                    DialogsActivity.this.selectedDialog = dialog.id;
                    boolean pinned = dialog.pinned;
                    boolean isFavorite = TurboConfig.containValue("fav_" + DialogsActivity.this.selectedDialog);
                    boolean muted = MessagesController.getInstance(DialogsActivity.this.currentAccount).isDialogMuted(DialogsActivity.this.selectedDialog);
                    Builder builder = new Builder(DialogsActivity.this.getParentActivity());
                    int lower_id = (int) DialogsActivity.this.selectedDialog;
                    high_id = (int) (DialogsActivity.this.selectedDialog >> 32);
                    if (lower_id > 0) {
                        builder.setTitle(UserObject.getFullName(MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(lower_id))));
                    } else {
                        TLRPC$EncryptedChat encryptedChat = MessagesController.getInstance(DialogsActivity.this.currentAccount).getEncryptedChat(Integer.valueOf(high_id));
                        if (encryptedChat != null) {
                            builder.setTitle(UserObject.getFullName(MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(encryptedChat.user_id))));
                        } else {
                            builder.setTitle(MessagesController.getInstance(DialogsActivity.this.currentAccount).getChat(Integer.valueOf(-lower_id)).title);
                        }
                    }
                    boolean hasUnread = dialog.unread_count != 0 || dialog.unread_mark;
                    int i;
                    String string;
                    if (DialogObject.isChannel(dialog)) {
                        CharSequence[] items;
                        chat = MessagesController.getInstance(DialogsActivity.this.currentAccount).getChat(Integer.valueOf(-lower_id));
                        int[] icons = new int[7];
                        icons[0] = R.drawable.turbo_tb_settings;
                        icons[1] = dialog.pinned ? R.drawable.chats_unpin : R.drawable.chats_pin;
                        if (isFavorite) {
                            i = R.drawable.turbo_del_fav;
                        } else {
                            i = R.drawable.turbo_add_fav;
                        }
                        icons[2] = i;
                        if (hasUnread) {
                            i = R.drawable.menu_read;
                        } else {
                            i = R.drawable.menu_unread;
                        }
                        icons[3] = i;
                        if (muted) {
                            i = R.drawable.notifications_s_on;
                        } else {
                            i = R.drawable.notifications_s_off;
                        }
                        icons[4] = i;
                        icons[5] = R.drawable.chats_clear;
                        icons[6] = R.drawable.chats_leave;
                        if (chat == null || !chat.megagroup) {
                            items = new CharSequence[7];
                            items[0] = LocaleController.getString("TurboChatSettings", R.string.TurboChatSettings);
                            string = (dialog.pinned || MessagesController.getInstance(DialogsActivity.this.currentAccount).canPinDialog(false)) ? dialog.pinned ? LocaleController.getString("UnpinFromTop", R.string.UnpinFromTop) : LocaleController.getString("PinToTop", R.string.PinToTop) : null;
                            items[1] = string;
                            items[2] = isFavorite ? LocaleController.getString("RemoveFromFavorites", R.string.RemoveFromFavorites) : LocaleController.getString("AddToFavorites", R.string.AddToFavorites);
                            items[3] = hasUnread ? LocaleController.getString("MarkAsRead", R.string.MarkAsRead) : LocaleController.getString("MarkAsUnread", R.string.MarkAsUnread);
                            items[4] = !muted ? LocaleController.getString("MuteNotifications", R.string.MuteNotifications) : LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications);
                            items[5] = LocaleController.getString("ClearHistoryCache", R.string.ClearHistoryCache);
                            if (chat == null || !chat.creator) {
                                string = LocaleController.getString("LeaveChannelMenu", R.string.LeaveChannelMenu);
                            } else {
                                string = LocaleController.getString("ChannelDeleteMenu", R.string.ChannelDeleteMenu);
                            }
                            items[6] = string;
                        } else {
                            items = new CharSequence[7];
                            items[0] = LocaleController.getString("TurboChatSettings", R.string.TurboChatSettings);
                            string = (dialog.pinned || MessagesController.getInstance(DialogsActivity.this.currentAccount).canPinDialog(false)) ? dialog.pinned ? LocaleController.getString("UnpinFromTop", R.string.UnpinFromTop) : LocaleController.getString("PinToTop", R.string.PinToTop) : null;
                            items[1] = string;
                            items[2] = isFavorite ? LocaleController.getString("RemoveFromFavorites", R.string.RemoveFromFavorites) : LocaleController.getString("AddToFavorites", R.string.AddToFavorites);
                            items[3] = hasUnread ? LocaleController.getString("MarkAsRead", R.string.MarkAsRead) : LocaleController.getString("MarkAsUnread", R.string.MarkAsUnread);
                            items[4] = !muted ? LocaleController.getString("MuteNotifications", R.string.MuteNotifications) : LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications);
                            items[5] = LocaleController.getString("ClearHistoryCache", R.string.ClearHistoryCache);
                            string = (chat == null || !chat.creator) ? LocaleController.getString("LeaveMegaMenu", R.string.LeaveMegaMenu) : LocaleController.getString("DeleteMegaMenu", R.string.DeleteMegaMenu);
                            items[6] = string;
                        }
                        builder.setItems(items, icons, new DialogsActivity$5$$Lambda$1(this, pinned, isFavorite, hasUnread, dialog, muted, chat));
                        DialogsActivity.this.showDialog(builder.create());
                    } else {
                        int[] iArr;
                        boolean isChat = lower_id < 0 && high_id != 1;
                        User user = null;
                        if (!(isChat || lower_id <= 0 || high_id == 1)) {
                            user = MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(lower_id));
                        }
                        boolean isBot = user != null && user.bot;
                        CharSequence[] charSequenceArr = new CharSequence[7];
                        charSequenceArr[0] = LocaleController.getString("TurboChatSettings", R.string.TurboChatSettings);
                        if (!dialog.pinned) {
                            if (!MessagesController.getInstance(DialogsActivity.this.currentAccount).canPinDialog(lower_id == 0)) {
                                string = null;
                                charSequenceArr[1] = string;
                                charSequenceArr[2] = isFavorite ? LocaleController.getString("RemoveFromFavorites", R.string.RemoveFromFavorites) : LocaleController.getString("AddToFavorites", R.string.AddToFavorites);
                                charSequenceArr[3] = hasUnread ? LocaleController.getString("MarkAsRead", R.string.MarkAsRead) : LocaleController.getString("MarkAsUnread", R.string.MarkAsUnread);
                                charSequenceArr[4] = muted ? LocaleController.getString("MuteNotifications", R.string.MuteNotifications) : LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications);
                                charSequenceArr[5] = LocaleController.getString("ClearHistory", R.string.ClearHistory);
                                string = isChat ? LocaleController.getString("DeleteChat", R.string.DeleteChat) : isBot ? LocaleController.getString("DeleteAndStop", R.string.DeleteAndStop) : LocaleController.getString("Delete", R.string.Delete);
                                charSequenceArr[6] = string;
                                iArr = new int[7];
                                iArr[0] = R.drawable.turbo_tb_settings;
                                if (dialog.pinned) {
                                    i = R.drawable.chats_pin;
                                } else {
                                    i = R.drawable.chats_unpin;
                                }
                                iArr[1] = i;
                                if (isFavorite) {
                                    i = R.drawable.turbo_add_fav;
                                } else {
                                    i = R.drawable.turbo_del_fav;
                                }
                                iArr[2] = i;
                                if (hasUnread) {
                                    i = R.drawable.menu_unread;
                                } else {
                                    i = R.drawable.menu_read;
                                }
                                iArr[3] = i;
                                if (muted) {
                                    i = R.drawable.notifications_s_off;
                                } else {
                                    i = R.drawable.notifications_s_on;
                                }
                                iArr[4] = i;
                                iArr[5] = R.drawable.chats_clear;
                                if (isChat) {
                                    i = R.drawable.chats_delete;
                                } else {
                                    i = R.drawable.chats_leave;
                                }
                                iArr[6] = i;
                                builder.setItems(charSequenceArr, iArr, new DialogsActivity$5$$Lambda$2(this, pinned, isFavorite, hasUnread, dialog, muted, isChat, isBot));
                                DialogsActivity.this.showDialog(builder.create());
                            }
                        }
                        string = dialog.pinned ? LocaleController.getString("UnpinFromTop", R.string.UnpinFromTop) : LocaleController.getString("PinToTop", R.string.PinToTop);
                        charSequenceArr[1] = string;
                        if (isFavorite) {
                        }
                        charSequenceArr[2] = isFavorite ? LocaleController.getString("RemoveFromFavorites", R.string.RemoveFromFavorites) : LocaleController.getString("AddToFavorites", R.string.AddToFavorites);
                        if (hasUnread) {
                        }
                        charSequenceArr[3] = hasUnread ? LocaleController.getString("MarkAsRead", R.string.MarkAsRead) : LocaleController.getString("MarkAsUnread", R.string.MarkAsUnread);
                        if (muted) {
                        }
                        charSequenceArr[4] = muted ? LocaleController.getString("MuteNotifications", R.string.MuteNotifications) : LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications);
                        charSequenceArr[5] = LocaleController.getString("ClearHistory", R.string.ClearHistory);
                        if (isChat) {
                        }
                        charSequenceArr[6] = string;
                        iArr = new int[7];
                        iArr[0] = R.drawable.turbo_tb_settings;
                        if (dialog.pinned) {
                            i = R.drawable.chats_pin;
                        } else {
                            i = R.drawable.chats_unpin;
                        }
                        iArr[1] = i;
                        if (isFavorite) {
                            i = R.drawable.turbo_add_fav;
                        } else {
                            i = R.drawable.turbo_del_fav;
                        }
                        iArr[2] = i;
                        if (hasUnread) {
                            i = R.drawable.menu_unread;
                        } else {
                            i = R.drawable.menu_read;
                        }
                        iArr[3] = i;
                        if (muted) {
                            i = R.drawable.notifications_s_on;
                        } else {
                            i = R.drawable.notifications_s_off;
                        }
                        iArr[4] = i;
                        iArr[5] = R.drawable.chats_clear;
                        if (isChat) {
                            i = R.drawable.chats_delete;
                        } else {
                            i = R.drawable.chats_leave;
                        }
                        iArr[6] = i;
                        builder.setItems(charSequenceArr, iArr, new DialogsActivity$5$$Lambda$2(this, pinned, isFavorite, hasUnread, dialog, muted, isChat, isBot));
                        DialogsActivity.this.showDialog(builder.create());
                    }
                } else if (DialogsActivity.this.dialogsType != 3 || DialogsActivity.this.selectAlertString != null) {
                    return false;
                } else {
                    DialogsActivity.this.dialogsAdapter.addOrRemoveSelectedDialog(dialog.id, view);
                    DialogsActivity.this.updateSelectedCount();
                }
                return true;
            } else if (!(DialogsActivity.this.dialogsSearchAdapter.getItem(position) instanceof String) && !DialogsActivity.this.dialogsSearchAdapter.isRecentSearchDisplayed()) {
                return false;
            } else {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogsActivity.this.getParentActivity());
                builder2.setTitle(LocaleController.getString("AppName", R.string.AppName));
                builder2.setMessage(LocaleController.getString("ClearSearch", R.string.ClearSearch));
                builder2.setPositiveButton(LocaleController.getString("ClearButton", R.string.ClearButton).toUpperCase(), new DialogsActivity$5$$Lambda$0(this));
                builder2.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                DialogsActivity.this.showDialog(builder2.create());
                return true;
            }
        }

        final /* synthetic */ void lambda$onItemClick$0$DialogsActivity$5(DialogInterface dialogInterface, int i) {
            if (DialogsActivity.this.dialogsSearchAdapter.isRecentSearchDisplayed()) {
                DialogsActivity.this.dialogsSearchAdapter.clearRecentSearch();
            } else {
                DialogsActivity.this.dialogsSearchAdapter.clearRecentHashtags();
            }
        }

        final /* synthetic */ void lambda$onItemClick$1$DialogsActivity$5(boolean pinned, boolean isFavorite, boolean hasUnread, TLRPC$TL_dialog dialog, boolean muted, TLRPC$Chat chat, DialogInterface d, int which) {
            if (which == 0) {
                Bundle args = new Bundle();
                args.putLong("dialog_id", DialogsActivity.this.selectedDialog);
                args.putBoolean("from_chat", false);
                DialogsActivity.this.presentFragment(new ChatSettingsActivity(args), false);
                return;
            }
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogsActivity.this.getParentActivity());
            builder1.setTitle(LocaleController.getString("AppName", R.string.AppName));
            if (which == 1) {
                if (MessagesController.getInstance(DialogsActivity.this.currentAccount).pinDialog(DialogsActivity.this.selectedDialog, !pinned, null, 0) && !pinned) {
                    DialogsActivity.this.listView.smoothScrollToPosition(0);
                    if (TurboConfig$Tabs.tabsEnabled) {
                        DialogsActivity.this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? 0 : DialogsActivity.this.tabsHeight)), AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? DialogsActivity.this.tabsHeight : 0)));
                    }
                }
            } else if (which == 2) {
                if (isFavorite) {
                    TurboConfig.removeValue("fav_" + DialogsActivity.this.selectedDialog);
                    DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                } else {
                    TurboConfig.setIntValue("fav_" + DialogsActivity.this.selectedDialog, (int) DialogsActivity.this.selectedDialog);
                }
            } else if (which == 3) {
                if (hasUnread) {
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markMentionsAsRead(DialogsActivity.this.selectedDialog);
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsRead(DialogsActivity.this.selectedDialog, dialog.top_message, dialog.top_message, dialog.last_message_date, false, 0, true);
                } else {
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsUnread(DialogsActivity.this.selectedDialog, null, 0);
                }
            } else if (which == 4) {
                DialogsActivity.this.toggleMute((int) DialogsActivity.this.selectedDialog, muted);
            } else if (which == 5) {
                if (chat == null || !chat.megagroup) {
                    builder1.setMessage(LocaleController.getString("AreYouSureClearHistoryChannel", R.string.AreYouSureClearHistoryChannel));
                } else if (TextUtils.isEmpty(chat.username)) {
                    builder1.setMessage(LocaleController.getString("AreYouSureClearHistory", R.string.AreYouSureClearHistory));
                } else {
                    builder1.setMessage(LocaleController.getString("AreYouSureClearHistoryGroup", R.string.AreYouSureClearHistoryGroup));
                }
                builder1.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C17361());
            } else if (which == 6) {
                if (chat != null && chat.megagroup) {
                    if (chat.creator) {
                        builder1.setMessage(LocaleController.getString("ChannelLeaveAlert", R.string.ChannelLeaveAlert));
                    } else {
                        builder1.setMessage(LocaleController.getString("MegaLeaveAlert", R.string.MegaLeaveAlert));
                    }
                }
                builder1.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C17372());
            }
            if (which == 5 || which == 6) {
                builder1.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                DialogsActivity.this.showDialog(builder1.create());
            }
        }

        final /* synthetic */ void lambda$onItemClick$2$DialogsActivity$5(boolean pinned, boolean isFavorite, boolean hasUnread, TLRPC$TL_dialog dialog, boolean muted, boolean isChat, boolean isBot, DialogInterface d, int which) {
            if (which == 0) {
                Bundle args = new Bundle();
                args.putLong("dialog_id", DialogsActivity.this.selectedDialog);
                args.putBoolean("from_chat", false);
                DialogsActivity.this.presentFragment(new ChatSettingsActivity(args), false);
                return;
            }
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogsActivity.this.getParentActivity());
            builder1.setTitle(LocaleController.getString("AppName", R.string.AppName));
            if (which == 1) {
                if (MessagesController.getInstance(DialogsActivity.this.currentAccount).pinDialog(DialogsActivity.this.selectedDialog, !pinned, null, 0) && !pinned) {
                    DialogsActivity.this.listView.smoothScrollToPosition(0);
                    if (TurboConfig$Tabs.tabsEnabled) {
                        DialogsActivity.this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? 0 : DialogsActivity.this.tabsHeight)), AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? DialogsActivity.this.tabsHeight : 0)));
                    }
                }
            } else if (which == 2) {
                if (isFavorite) {
                    TurboConfig.removeValue("fav_" + DialogsActivity.this.selectedDialog);
                    DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                } else {
                    TurboConfig.setIntValue("fav_" + DialogsActivity.this.selectedDialog, (int) DialogsActivity.this.selectedDialog);
                }
            } else if (which == 3) {
                if (hasUnread) {
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markMentionsAsRead(DialogsActivity.this.selectedDialog);
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsRead(DialogsActivity.this.selectedDialog, dialog.top_message, dialog.top_message, dialog.last_message_date, false, 0, true);
                } else {
                    MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsUnread(DialogsActivity.this.selectedDialog, null, 0);
                }
            } else if (which == 4) {
                DialogsActivity.this.toggleMute((int) DialogsActivity.this.selectedDialog, muted);
            } else if (which == 5) {
                builder1.setMessage(LocaleController.getString("AreYouSureClearHistory", R.string.AreYouSureClearHistory));
            } else if (which == 6) {
                if (isChat) {
                    builder1.setMessage(LocaleController.getString("AreYouSureDeleteAndExit", R.string.AreYouSureDeleteAndExit));
                } else {
                    builder1.setMessage(LocaleController.getString("AreYouSureDeleteThisChat", R.string.AreYouSureDeleteThisChat));
                }
            }
            final int i = which;
            final boolean z = isChat;
            final boolean z2 = isBot;
            builder1.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 6) {
                        TurboUtils.clearChatData(DialogsActivity.this.currentAccount, DialogsActivity.this.selectedDialog);
                        if (z) {
                            TLRPC$Chat currentChat = MessagesController.getInstance(DialogsActivity.this.currentAccount).getChat(Integer.valueOf((int) (-DialogsActivity.this.selectedDialog)));
                            if (currentChat == null || !ChatObject.isNotInChat(currentChat)) {
                                MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteUserFromChat((int) (-DialogsActivity.this.selectedDialog), UserConfig.getInstance(DialogsActivity.this.currentAccount).getCurrentUser(), null);
                            } else {
                                MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(DialogsActivity.this.selectedDialog, 0);
                            }
                        } else {
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(DialogsActivity.this.selectedDialog, 0);
                        }
                        if (z2) {
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).blockUser((int) DialogsActivity.this.selectedDialog);
                        }
                        if (AndroidUtilities.isTablet()) {
                            NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(DialogsActivity.this.selectedDialog));
                        }
                    } else if (i == 5) {
                        MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(DialogsActivity.this.selectedDialog, 1);
                    }
                }
            });
            if (which == 5 || which == 6) {
                builder1.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                DialogsActivity.this.showDialog(builder1.create());
            }
        }

        public void onLongClickRelease() {
            DialogsActivity.this.finishPreviewFragment();
        }

        public void onMove(float dx, float dy) {
            DialogsActivity.this.movePreviewFragment(dy);
        }
    }

    /* renamed from: org.telegram.ui.DialogsActivity$6 */
    class C17406 implements OnTouchListener {
        C17406() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            DialogsActivity.this.onSwipe(event);
            return false;
        }
    }

    /* renamed from: org.telegram.ui.DialogsActivity$7 */
    class C17417 extends ViewOutlineProvider {
        C17417() {
        }

        @SuppressLint({"NewApi"})
        public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
        }
    }

    /* renamed from: org.telegram.ui.DialogsActivity$8 */
    class C17428 implements OnLongClickListener {
        C17428() {
        }

        public boolean onLongClick(View v) {
            DialogsActivity.this.toggleHiddenMode(null);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.DialogsActivity$9 */
    class C17439 extends ViewOutlineProvider {
        C17439() {
        }

        @SuppressLint({"NewApi"})
        public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
        }
    }

    public DialogsActivity(Bundle args) {
        super(args);
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        if (getArguments() != null) {
            this.onlySelect = this.arguments.getBoolean("onlySelect", false);
            this.cantSendToChannels = this.arguments.getBoolean("cantSendToChannels", false);
            this.dialogsType = this.arguments.getInt("dialogsType", 0);
            this.selectAlertString = this.arguments.getString("selectAlertString");
            this.selectAlertStringGroup = this.arguments.getString("selectAlertStringGroup");
            this.addToGroupAlertString = this.arguments.getString("addToGroupAlertString");
            this.allowSwitchAccount = this.arguments.getBoolean("allowSwitchAccount");
            this.addToGroup = this.arguments.getBoolean("addToGroup", false);
            this.multiSelection = this.arguments.getBoolean("multiSelection", false);
            this.catOperationType = this.arguments.getInt("op_type", 0);
            this.catId = this.arguments.getInt("cat_id", 0);
        }
        if (this.dialogsType == 0) {
            this.askAboutContacts = MessagesController.getGlobalNotificationsSettings().getBoolean("askAboutContacts", true);
            SharedConfig.loadProxyList();
        }
        if (this.searchString == null) {
            this.currentConnectionState = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogsNeedReload);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiDidLoaded);
            if (!this.onlySelect) {
                NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.closeSearchByActiveAction);
                NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
            }
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.encryptedChatUpdated);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoaded);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.appDidLogout);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.openedChatChanged);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.notificationsSettingsUpdated);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messageReceivedByAck);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messageReceivedByServer);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messageSendError);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.needReloadRecentDialogsSearch);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didLoadedReplyMessages);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.reloadHints);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didUpdatedConnectionState);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogsUnreadCounterChanged);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.turboUpdateInterface);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didSetNewWallpapper);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetPasscode);
        }
        if (UserConfig.isRobot) {
            dialogsLoaded[this.currentAccount] = true;
        }
        if (!dialogsLoaded[this.currentAccount]) {
            MessagesController.getInstance(this.currentAccount).loadGlobalNotificationsSettings();
            MessagesController.getInstance(this.currentAccount).loadDialogs(0, 100, true);
            MessagesController.getInstance(this.currentAccount).loadHintDialogs();
            ContactsController.getInstance(this.currentAccount).checkInviteText();
            MessagesController.getInstance(this.currentAccount).loadPinnedDialogs(0, null);
            DataQuery.getInstance(this.currentAccount).loadRecents(2, false, true, false);
            DataQuery.getInstance(this.currentAccount).checkFeaturedStickers();
            dialogsLoaded[this.currentAccount] = true;
        }
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.searchString == null) {
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogsNeedReload);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiDidLoaded);
            if (!this.onlySelect) {
                NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.closeSearchByActiveAction);
                NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxySettingsChanged);
            }
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.encryptedChatUpdated);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoaded);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.appDidLogout);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.openedChatChanged);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messageReceivedByAck);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messageReceivedByServer);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messageSendError);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.needReloadRecentDialogsSearch);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didLoadedReplyMessages);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.reloadHints);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didUpdatedConnectionState);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogsUnreadCounterChanged);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.turboUpdateInterface);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didSetNewWallpapper);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetPasscode);
        }
        if (this.commentView != null) {
            this.commentView.onDestroy();
        }
        this.delegate = null;
    }

    public View createView(Context context) {
        float f;
        int i;
        float f2;
        float f3;
        int i2;
        this.searching = false;
        this.searchWas = false;
        AndroidUtilities.runOnUIThread(new DialogsActivity$$Lambda$0(context));
        TurboUtils.checkPakage();
        ActionBarMenu menu = this.actionBar.createMenu();
        if (!this.onlySelect && this.searchString == null) {
            this.proxyDrawable = new ProxyDrawable(context);
            this.proxyItem = menu.addItem(2, this.proxyDrawable);
            this.passcodeItem = menu.addItem(1, (int) R.drawable.lock_close);
            updatePasscodeButton();
            updateProxyButton(false);
        }
        this.avatarImage = new BackupImageView(context);
        this.avatarImage.setRoundRadius(AndroidUtilities.dp(21.0f));
        PhotoViewer.getInstance().setParentActivity(getParentActivity());
        TurboUtils.joinTurboChannel(this.currentAccount);
        if (!TurboConfig.containValue("swipe_tabs")) {
            TurboConfig.setBooleanValue("swipe_tabs", true);
        }
        this.tabsHeight = TurboConfig$Tabs.tabsHeight;
        int toolBarType = Toolbar.type;
        if (!this.onlySelect) {
            this.categorytem = menu.addItem(3, (int) R.drawable.turbo_dialog_cat);
        } else if (this.catOperationType > 0) {
            menu.addItem(11, (int) R.drawable.ic_done);
        } else if (this.multiSelection) {
            this.headerItem = menu.addItem(5, (int) R.drawable.ic_ab_other);
            this.headerItem.addSubItem(6, LocaleController.getString("MuteNotifications", R.string.MuteNotifications));
            this.headerItem.addSubItem(7, LocaleController.getString("UnmuteNotifications", R.string.UnmuteNotifications));
            this.headerItem.addSubItem(8, LocaleController.getString("MarkAsRead", R.string.MarkAsRead));
            this.headerItem.addSubItem(9, LocaleController.getString("AddToFavorites", R.string.AddToFavorites));
            this.headerItem.addSubItem(10, LocaleController.getString("DeleteChat", R.string.DeleteChat));
        } else if (!this.addToGroup) {
            this.quotItem = menu.addItem(4, (int) R.drawable.turbo_ab_quote);
            this.quotItem.setAlpha(TurboConfig$BG.forwardType == 1 ? 1.0f : 0.4f);
        }
        final int i3 = toolBarType;
        ActionBarMenuItem item = menu.addItem(0, (int) R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItemSearchListener() {
            public void onSearchExpand() {
                DialogsActivity.this.hideTabs(true);
                DialogsActivity.this.actionBar.hideMenuPhoto();
                DialogsActivity.this.showActionbarIcons(2);
                DialogsActivity.this.searching = true;
                if (DialogsActivity.this.switchItem != null) {
                    DialogsActivity.this.switchItem.setVisibility(8);
                }
                if (DialogsActivity.this.proxyItem != null && DialogsActivity.this.proxyItemVisisble) {
                    DialogsActivity.this.proxyItem.setVisibility(8);
                }
                if (DialogsActivity.this.listView != null) {
                    if (DialogsActivity.this.searchString != null) {
                        DialogsActivity.this.listView.setEmptyView(DialogsActivity.this.searchEmptyView);
                        DialogsActivity.this.progressView.setVisibility(8);
                    }
                    if (!DialogsActivity.this.onlySelect) {
                        if (!Toolbar.enabled) {
                            DialogsActivity.this.floatingButton.setVisibility(8);
                            DialogsActivity.this.unreadFloatingButtonContainer.setVisibility(8);
                        } else if (i3 == 0) {
                            DialogsActivity.this.fam.setVisibility(8);
                        } else if (i3 == 1) {
                            DialogsActivity.this.toolBar.setVisibility(8);
                        }
                    }
                }
                DialogsActivity.this.updatePasscodeButton();
            }

            public boolean canCollapseSearch() {
                if (DialogsActivity.this.switchItem != null) {
                    DialogsActivity.this.switchItem.setVisibility(0);
                }
                if (DialogsActivity.this.proxyItem != null && DialogsActivity.this.proxyItemVisisble) {
                    DialogsActivity.this.proxyItem.setVisibility(0);
                }
                if (DialogsActivity.this.searchString == null) {
                    return true;
                }
                DialogsActivity.this.finishFragment();
                return false;
            }

            public void onSearchCollapse() {
                DialogsActivity.this.hideTabs(false);
                DialogsActivity.this.updateLayout();
                DialogsActivity.this.actionBar.showMenuPhoto();
                DialogsActivity.this.showActionbarIcons(1);
                DialogsActivity.this.searching = false;
                DialogsActivity.this.searchWas = false;
                if (DialogsActivity.this.listView != null) {
                    DialogsActivity.this.listView.setEmptyView(DialogsActivity.this.progressView);
                    DialogsActivity.this.searchEmptyView.setVisibility(8);
                    if (!DialogsActivity.this.onlySelect) {
                        if (!Toolbar.enabled) {
                            DialogsActivity.this.floatingButton.setVisibility(0);
                            if (DialogsActivity.this.currentUnreadCount != 0) {
                                DialogsActivity.this.unreadFloatingButtonContainer.setVisibility(0);
                                DialogsActivity.this.unreadFloatingButtonContainer.setTranslationY((float) AndroidUtilities.dp(74.0f));
                            }
                            DialogsActivity.this.floatingHidden = true;
                            DialogsActivity.this.floatingButton.setTranslationY((float) AndroidUtilities.dp(100.0f));
                            DialogsActivity.this.hideFloatingButton(false);
                        } else if (i3 == 0) {
                            DialogsActivity.this.fam.setVisibility(0);
                            DialogsActivity.this.floatingHidden = true;
                            DialogsActivity.this.fam.setTranslationY((float) AndroidUtilities.dp(100.0f));
                            DialogsActivity.this.hideFloatingButton(false);
                        } else if (i3 == 1) {
                            DialogsActivity.this.toolBar.setVisibility(0);
                            DialogsActivity.this.floatingHidden = true;
                            DialogsActivity.this.hideFloatingButton(false);
                        }
                    }
                    if (DialogsActivity.this.listView.getAdapter() != DialogsActivity.this.dialogsAdapter) {
                        DialogsActivity.this.listView.setAdapter(DialogsActivity.this.dialogsAdapter);
                        DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                    }
                }
                if (DialogsActivity.this.dialogsSearchAdapter != null) {
                    DialogsActivity.this.dialogsSearchAdapter.searchDialogs(null);
                }
                DialogsActivity.this.updatePasscodeButton();
            }

            public void onTextChanged(EditText editText) {
                String text = editText.getText().toString();
                if (text.length() != 0 || (DialogsActivity.this.dialogsSearchAdapter != null && DialogsActivity.this.dialogsSearchAdapter.hasRecentRearch())) {
                    DialogsActivity.this.searchWas = true;
                    if (!(DialogsActivity.this.dialogsSearchAdapter == null || DialogsActivity.this.listView.getAdapter() == DialogsActivity.this.dialogsSearchAdapter)) {
                        DialogsActivity.this.listView.setAdapter(DialogsActivity.this.dialogsSearchAdapter);
                        DialogsActivity.this.dialogsSearchAdapter.notifyDataSetChanged();
                    }
                    if (!(DialogsActivity.this.searchEmptyView == null || DialogsActivity.this.listView.getEmptyView() == DialogsActivity.this.searchEmptyView)) {
                        DialogsActivity.this.progressView.setVisibility(8);
                        DialogsActivity.this.searchEmptyView.showTextView();
                        DialogsActivity.this.listView.setEmptyView(DialogsActivity.this.searchEmptyView);
                    }
                }
                if (DialogsActivity.this.dialogsSearchAdapter != null) {
                    DialogsActivity.this.dialogsSearchAdapter.searchDialogs(text);
                }
            }
        });
        item.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));
        item.getSearchField().setTypeface(TurboUtils.getTurboTypeFace());
        if (this.onlySelect) {
            if (this.multiSelection || this.catOperationType > 0) {
                item.setVisibility(8);
            }
            this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
            if (this.dialogsType == 3 && this.selectAlertString == null) {
                this.actionBar.setTitle(LocaleController.getString("ForwardTo", R.string.ForwardTo));
            } else {
                this.actionBar.setTitle(LocaleController.getString("SelectChat", R.string.SelectChat));
            }
        } else {
            if (this.searchString != null) {
                this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
                this.actionBar.hideMenuPhoto();
                showActionbarIcons(2);
            } else {
                this.actionBar.setBackButtonDrawable(new MenuDrawable());
                this.actionBar.showMenuPhoto();
                showActionbarIcons(1);
            }
            if (BuildVars.DEBUG_VERSION) {
                this.actionBar.setTitle("Telegram Beta");
            } else {
                this.actionBar.setTitle(LocaleController.getString("AppName", R.string.AppName));
            }
            this.actionBar.setSupportsHolidayImage(true);
        }
        this.actionBar.setTitleActionRunnable(new DialogsActivity$$Lambda$1(this));
        if (this.allowSwitchAccount && UserConfig.getActivatedAccountsCount() > 1) {
            TLObject avatar;
            this.switchItem = menu.addItemWithWidth(1, 0, AndroidUtilities.dp(56.0f));
            Drawable avatarDrawable = new AvatarDrawable();
            avatarDrawable.setTextSize(AndroidUtilities.dp(12.0f));
            View backupImageView = new BackupImageView(context);
            backupImageView.setRoundRadius(AndroidUtilities.dp(18.0f));
            this.switchItem.addView(backupImageView, LayoutHelper.createFrame(36, 36, 17));
            User user = UserConfig.getInstance(this.currentAccount).getCurrentUser();
            avatarDrawable.setInfo(user);
            if (user.photo == null || user.photo.photo_small == null || user.photo.photo_small.volume_id == 0 || user.photo.photo_small.local_id == 0) {
                avatar = null;
            } else {
                avatar = user.photo.photo_small;
            }
            backupImageView.getImageReceiver().setCurrentAccount(this.currentAccount);
            backupImageView.setImage(avatar, "50_50", avatarDrawable);
            for (int a = 0; a < 3; a++) {
                if (UserConfig.getInstance(a).getCurrentUser() != null) {
                    AccountSelectCell cell = new AccountSelectCell(context);
                    cell.setAccount(a, true);
                    this.switchItem.addSubItem(a + 10, cell, AndroidUtilities.dp(230.0f), AndroidUtilities.dp(48.0f));
                }
            }
        }
        this.actionBar.setAllowOverlayTitle(true);
        final Context context2 = context;
        this.actionBar.setActionBarMenuOnItemClick(new ActionBarMenuOnItemClick() {

            /* renamed from: org.telegram.ui.DialogsActivity$2$1 */
            class C17321 implements OnClickListener {
                C17321() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int a = 0; a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size(); a++) {
                        long selectedDialog = ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue();
                        if (DialogObject.isChannel((TLRPC$TL_dialog) MessagesController.getInstance(DialogsActivity.this.currentAccount).dialogs_dict.get(selectedDialog))) {
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteUserFromChat((int) (-selectedDialog), UserConfig.getInstance(DialogsActivity.this.currentAccount).getCurrentUser(), null);
                            if (AndroidUtilities.isTablet()) {
                                NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(selectedDialog));
                            }
                        } else {
                            int lower_id = (int) selectedDialog;
                            int high_id = (int) (selectedDialog >> 32);
                            boolean isChat = lower_id < 0 && high_id != 1;
                            User user = null;
                            if (!(isChat || lower_id <= 0 || high_id == 1)) {
                                user = MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(lower_id));
                            }
                            boolean isBot = user != null && user.bot;
                            if (isChat) {
                                TLRPC$Chat currentChat = MessagesController.getInstance(DialogsActivity.this.currentAccount).getChat(Integer.valueOf((int) (-selectedDialog)));
                                if (currentChat == null || !ChatObject.isNotInChat(currentChat)) {
                                    MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteUserFromChat((int) (-selectedDialog), MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(UserConfig.getInstance(DialogsActivity.this.currentAccount).getClientUserId())), null);
                                    MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(selectedDialog, 0);
                                } else {
                                    MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(selectedDialog, 0);
                                }
                            } else {
                                MessagesController.getInstance(DialogsActivity.this.currentAccount).deleteDialog(selectedDialog, 0);
                            }
                            if (isBot) {
                                MessagesController.getInstance(DialogsActivity.this.currentAccount).blockUser((int) selectedDialog);
                            }
                            if (AndroidUtilities.isTablet()) {
                                NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(selectedDialog));
                            }
                        }
                    }
                    DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                    DialogsActivity.this.finishFragment();
                }
            }

            public void onItemClick(int id) {
                if (id == -1) {
                    if (DialogsActivity.this.onlySelect) {
                        DialogsActivity.this.finishFragment();
                    } else if (DialogsActivity.this.parentLayout != null) {
                        DialogsActivity.this.parentLayout.getDrawerLayoutContainer().openDrawer(false);
                    }
                } else if (id == 1) {
                    SharedConfig.appLocked = !SharedConfig.appLocked;
                    SharedConfig.saveConfig();
                    DialogsActivity.this.updatePasscodeButton();
                } else if (id == 2) {
                    DialogsActivity.this.presentFragment(new ProxyListActivity());
                } else if (id == 3) {
                    DialogsActivity.this.showDialog(new DialogsCategoryAlert(DialogsActivity.this.getParentActivity(), DialogsActivity.this.dialogsAdapter, DialogsActivity.this));
                } else if (id == 4) {
                    TurboConfig$BG.setIntValue("forward_type", TurboConfig$BG.forwardType == 1 ? 2 : 1);
                    DialogsActivity.this.quotItem.setAlpha(TurboConfig$BG.forwardType == 1 ? 1.0f : 0.4f);
                } else if (id == 6) {
                    for (a = 0; a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size(); a++) {
                        DialogsActivity.this.toggleMute((int) ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue(), false);
                    }
                    DialogsActivity.this.finishFragment();
                } else if (id == 7) {
                    for (a = 0; a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size(); a++) {
                        DialogsActivity.this.toggleMute((int) ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue(), true);
                    }
                    DialogsActivity.this.finishFragment();
                } else if (id == 8) {
                    for (a = 0; a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size(); a++) {
                        selectedDialog = ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue();
                        TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) MessagesController.getInstance(DialogsActivity.this.currentAccount).dialogs_dict.get(selectedDialog);
                        if (dialog.unread_count != 0 || dialog.unread_mark) {
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).markMentionsAsRead(selectedDialog);
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsRead(selectedDialog, dialog.top_message, dialog.top_message, dialog.last_message_date, false, 0, true);
                        }
                    }
                    DialogsActivity.this.finishFragment();
                } else if (id == 9) {
                    for (a = 0; a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size(); a++) {
                        selectedDialog = ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue();
                        if (!TurboConfig.containValue("fav_" + String.valueOf(selectedDialog))) {
                            TurboConfig.setIntValue("fav_" + String.valueOf(selectedDialog), (int) selectedDialog);
                        }
                    }
                    DialogsActivity.this.finishFragment();
                } else if (id == 10) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DialogsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C17321());
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    DialogsActivity.this.showDialog(builder.create());
                } else if (id == 11) {
                    DBHelper dbHelper = new DBHelper(context2);
                    switch (DialogsActivity.this.catOperationType) {
                        case 1:
                            dbHelper.open();
                            a = 0;
                            while (a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size()) {
                                try {
                                    selectedDialog = ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue();
                                    if (dbHelper.ifExistsDialog((int) selectedDialog)) {
                                        dbHelper.changeDialogCategory((int) selectedDialog, DialogsActivity.this.catId);
                                    } else {
                                        dbHelper.addDialog((int) selectedDialog, DialogsActivity.this.catId);
                                    }
                                    a++;
                                } finally {
                                    dbHelper.close();
                                }
                            }
                            break;
                        case 2:
                            dbHelper.open();
                            a = 0;
                            while (a < DialogsActivity.this.dialogsAdapter.getSelectedDialogs().size()) {
                                try {
                                    selectedDialog = ((Long) DialogsActivity.this.dialogsAdapter.getSelectedDialogs().get(a)).longValue();
                                    if (dbHelper.ifExistsDialog((int) selectedDialog)) {
                                        dbHelper.deleteDialogByDid((int) selectedDialog);
                                    }
                                    a++;
                                } finally {
                                    dbHelper.close();
                                }
                            }
                            break;
                    }
                    DialogsActivity.this.finishFragment();
                } else if (id >= 10 && id < 13 && DialogsActivity.this.getParentActivity() != null) {
                    DialogsActivityDelegate oldDelegate = DialogsActivity.this.delegate;
                    LaunchActivity launchActivity = (LaunchActivity) DialogsActivity.this.getParentActivity();
                    launchActivity.switchToAccount(id - 10, true);
                    DialogsActivity dialogsActivity = new DialogsActivity(DialogsActivity.this.arguments);
                    dialogsActivity.setDelegate(oldDelegate);
                    launchActivity.presentFragment(dialogsActivity, false, true);
                }
            }
        });
        if (this.sideMenu != null) {
            this.sideMenu.setBackgroundColor(Theme.getColor(Theme.key_chats_menuBackground));
            this.sideMenu.setGlowColor(Theme.getColor(Theme.key_chats_menuBackground));
            this.sideMenu.getAdapter().notifyDataSetChanged();
        }
        this.actionBar.setAddToContainer(false);
        this.contentView = new SizeNotifierFrameLayout(context) {
            int inputFieldHeight = 0;

            protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
                boolean result = super.drawChild(canvas, child, drawingTime);
                if (child == DialogsActivity.this.actionBar && DialogsActivity.this.parentLayout != null && TurboConfig.showActionbarShadow) {
                    DialogsActivity.this.parentLayout.drawHeaderShadow(canvas, DialogsActivity.this.actionBar.getVisibility() == 0 ? DialogsActivity.this.actionBar.getMeasuredHeight() : 0);
                }
                return result;
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                setMeasuredDimension(widthSize, heightSize);
                heightSize -= getPaddingTop();
                measureChildWithMargins(DialogsActivity.this.actionBar, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int keyboardSize = getKeyboardHeight();
                int childCount = getChildCount();
                if (DialogsActivity.this.commentView != null) {
                    measureChildWithMargins(DialogsActivity.this.commentView, widthMeasureSpec, 0, heightMeasureSpec, 0);
                    Object tag = DialogsActivity.this.commentView.getTag();
                    if (tag == null || !tag.equals(Integer.valueOf(2))) {
                        this.inputFieldHeight = 0;
                    } else {
                        if (keyboardSize <= AndroidUtilities.dp(20.0f) && !AndroidUtilities.isInMultiwindow) {
                            heightSize -= DialogsActivity.this.commentView.getEmojiPadding();
                        }
                        this.inputFieldHeight = DialogsActivity.this.commentView.getMeasuredHeight();
                    }
                }
                heightSize -= DialogsActivity.this.actionBar.getMeasuredHeight();
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    if (!(child == null || child.getVisibility() == 8 || child == DialogsActivity.this.commentView || child == DialogsActivity.this.actionBar)) {
                        if (child == DialogsActivity.this.listView || child == DialogsActivity.this.progressView || child == DialogsActivity.this.searchEmptyView) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(Math.max(AndroidUtilities.dp(10.0f), (heightSize - this.inputFieldHeight) + AndroidUtilities.dp(2.0f)), 1073741824));
                        } else if (DialogsActivity.this.commentView == null || !DialogsActivity.this.commentView.isPopupView(child)) {
                            if (child == DialogsActivity.this.tabsView) {
                                child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp((float) DialogsActivity.this.tabsHeight), 1073741824));
                            } else {
                                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                            }
                        } else if (!AndroidUtilities.isInMultiwindow) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(child.getLayoutParams().height, 1073741824));
                        } else if (AndroidUtilities.isTablet()) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(320.0f), ((heightSize - this.inputFieldHeight) - AndroidUtilities.statusBarHeight) + getPaddingTop()), 1073741824));
                        } else {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(((heightSize - this.inputFieldHeight) - AndroidUtilities.statusBarHeight) + getPaddingTop(), 1073741824));
                        }
                    }
                }
            }

            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int count = getChildCount();
                Object tag = DialogsActivity.this.commentView != null ? DialogsActivity.this.commentView.getTag() : null;
                int paddingBottom = (tag == null || !tag.equals(Integer.valueOf(2))) ? 0 : (getKeyboardHeight() > AndroidUtilities.dp(20.0f) || AndroidUtilities.isInMultiwindow) ? 0 : DialogsActivity.this.commentView.getEmojiPadding();
                setBottomClip(paddingBottom);
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() != 8) {
                        int childLeft;
                        int childTop;
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        int width = child.getMeasuredWidth();
                        int height = child.getMeasuredHeight();
                        int gravity = lp.gravity;
                        if (gravity == -1) {
                            gravity = 51;
                        }
                        int verticalGravity = gravity & 112;
                        switch ((gravity & 7) & 7) {
                            case 1:
                                childLeft = ((((r - l) - width) / 2) + lp.leftMargin) - lp.rightMargin;
                                break;
                            case 5:
                                childLeft = (r - width) - lp.rightMargin;
                                break;
                            default:
                                childLeft = lp.leftMargin;
                                break;
                        }
                        switch (verticalGravity) {
                            case 16:
                                childTop = (((((b - paddingBottom) - t) - height) / 2) + lp.topMargin) - lp.bottomMargin;
                                break;
                            case 48:
                                childTop = lp.topMargin + getPaddingTop();
                                if (child != DialogsActivity.this.actionBar && DialogsActivity.this.actionBar.getVisibility() == 0) {
                                    childTop += DialogsActivity.this.actionBar.getMeasuredHeight();
                                    break;
                                }
                            case 80:
                                childTop = (((b - paddingBottom) - t) - height) - lp.bottomMargin;
                                break;
                            default:
                                childTop = lp.topMargin;
                                break;
                        }
                        if (DialogsActivity.this.commentView != null && DialogsActivity.this.commentView.isPopupView(child)) {
                            if (AndroidUtilities.isInMultiwindow) {
                                childTop = (DialogsActivity.this.commentView.getTop() - child.getMeasuredHeight()) + AndroidUtilities.dp(1.0f);
                            } else {
                                childTop = DialogsActivity.this.commentView.getBottom();
                            }
                        }
                        if (child == DialogsActivity.this.actionBar) {
                            childTop -= getPaddingTop();
                        }
                        child.layout(childLeft, childTop, childLeft + width, childTop + height);
                    }
                }
                notifyHeightChanged();
            }
        };
        this.fragmentView = this.contentView;
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper());
        this.listView = new RecyclerListView(context);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_dialogsBackgroundWhite));
        this.listView.setVerticalScrollBarEnabled(true);
        this.listView.setItemAnimator(null);
        this.listView.setInstantClick(true);
        this.listView.setLayoutAnimation(null);
        this.listView.setTag(Integer.valueOf(4));
        this.layoutManager = new LinearLayoutManager(context) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }

            public void smoothScrollToPosition(RecyclerView recyclerView, State state, int position) {
                LinearSmoothScrollerMiddle linearSmoothScroller = new LinearSmoothScrollerMiddle(recyclerView.getContext());
                linearSmoothScroller.setTargetPosition(position);
                startSmoothScroll(linearSmoothScroller);
            }
        };
        this.layoutManager.setOrientation(1);
        this.listView.setLayoutManager(this.layoutManager);
        this.listView.setVerticalScrollbarPosition(LocaleController.isRTL ? 1 : 2);
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new DialogsActivity$$Lambda$2(this));
        this.listView.setOnItemLongClickListener(new C17395());
        if (TurboConfig$Tabs.swipeTabs) {
            this.listView.setOnTouchListener(new C17406());
        }
        this.searchEmptyView = new EmptyTextProgressView(context);
        this.searchEmptyView.setBackgroundColor(Theme.getColor(Theme.key_dialogsBackgroundWhite));
        this.searchEmptyView.setVisibility(8);
        this.searchEmptyView.setShowAtCenter(true);
        this.searchEmptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
        this.contentView.addView(this.searchEmptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.progressView = new RadialProgressView(context);
        this.progressView.setVisibility(8);
        this.contentView.addView(this.progressView, LayoutHelper.createFrame(-2, -2, 17));
        this.floatingButton = new ImageView(context);
        this.floatingButton.setVisibility(this.onlySelect ? 8 : 0);
        this.floatingButton.setScaleType(ScaleType.CENTER);
        Drawable drawable = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56.0f), Theme.getColor(Theme.key_chats_actionBackground), Theme.getColor(Theme.key_chats_actionPressedBackground));
        if (VERSION.SDK_INT < 21) {
            Drawable shadowDrawable = context.getResources().getDrawable(R.drawable.floating_shadow).mutate();
            shadowDrawable.setColorFilter(new PorterDuffColorFilter(-16777216, Mode.MULTIPLY));
            Drawable combinedDrawable = new CombinedDrawable(shadowDrawable, drawable, 0, 0);
            combinedDrawable.setIconSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
            drawable = combinedDrawable;
        }
        this.floatingButton.setBackgroundDrawable(drawable);
        this.floatingButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.MULTIPLY));
        this.floatingButton.setImageResource(R.drawable.floating_pencil);
        if (VERSION.SDK_INT >= 21) {
            StateListAnimator animator = new StateListAnimator();
            animator.addState(new int[]{16842919}, ObjectAnimator.ofFloat(this.floatingButton, "translationZ", new float[]{(float) AndroidUtilities.dp(2.0f), (float) AndroidUtilities.dp(4.0f)}).setDuration(200));
            animator.addState(new int[0], ObjectAnimator.ofFloat(this.floatingButton, "translationZ", new float[]{(float) AndroidUtilities.dp(4.0f), (float) AndroidUtilities.dp(2.0f)}).setDuration(200));
            this.floatingButton.setStateListAnimator(animator);
            this.floatingButton.setOutlineProvider(new C17417());
        }
        SizeNotifierFrameLayout sizeNotifierFrameLayout = this.contentView;
        View view = this.floatingButton;
        int i4 = VERSION.SDK_INT >= 21 ? 56 : 60;
        if (VERSION.SDK_INT >= 21) {
            f = 56.0f;
        } else {
            f = 60.0f;
        }
        if (LocaleController.isRTL) {
            i = 3;
        } else {
            i = 5;
        }
        i |= 80;
        if (LocaleController.isRTL) {
            f2 = 14.0f;
        } else {
            f2 = 0.0f;
        }
        if (LocaleController.isRTL) {
            f3 = 0.0f;
        } else {
            f3 = 14.0f;
        }
        sizeNotifierFrameLayout.addView(view, LayoutHelper.createFrame(i4, f, i, f2, 0.0f, f3, 14.0f));
        this.floatingButton.setOnClickListener(new DialogsActivity$$Lambda$3(this));
        this.floatingButton.setOnLongClickListener(new C17428());
        if (Toolbar.enabled) {
            this.floatingButton.setVisibility(8);
            if (toolBarType == 0) {
                createSlidingToolBar(context);
            } else if (toolBarType == 1) {
                createToolBar(context);
            }
        }
        this.unreadFloatingButtonContainer = new FrameLayout(context);
        if (this.onlySelect) {
            this.unreadFloatingButtonContainer.setVisibility(8);
        } else {
            this.unreadFloatingButtonContainer.setVisibility(this.currentUnreadCount != 0 ? 0 : 4);
            this.unreadFloatingButtonContainer.setTag(this.currentUnreadCount != 0 ? Integer.valueOf(1) : null);
        }
        sizeNotifierFrameLayout = this.contentView;
        view = this.unreadFloatingButtonContainer;
        i4 = (VERSION.SDK_INT >= 21 ? 56 : 60) + 20;
        if (VERSION.SDK_INT >= 21) {
            i2 = 56;
        } else {
            i2 = 60;
        }
        f = (float) (i2 + 20);
        if (LocaleController.isRTL) {
            i = 3;
        } else {
            i = 5;
        }
        i |= 80;
        if (LocaleController.isRTL) {
            f2 = 4.0f;
        } else {
            f2 = 0.0f;
        }
        if (LocaleController.isRTL) {
            f3 = 0.0f;
        } else {
            f3 = 4.0f;
        }
        sizeNotifierFrameLayout.addView(view, LayoutHelper.createFrame(i4, f, i, f2, 0.0f, f3, 81.0f));
        this.unreadFloatingButtonContainer.setOnClickListener(new DialogsActivity$$Lambda$4(this));
        this.unreadFloatingButton = new ImageView(context);
        this.unreadFloatingButton.setScaleType(ScaleType.CENTER);
        drawable = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56.0f), Theme.getColor(Theme.key_chats_actionUnreadBackground), Theme.getColor(Theme.key_chats_actionUnreadPressedBackground));
        if (VERSION.SDK_INT < 21) {
            shadowDrawable = context.getResources().getDrawable(R.drawable.floating_shadow_profile).mutate();
            shadowDrawable.setColorFilter(new PorterDuffColorFilter(-16777216, Mode.MULTIPLY));
            combinedDrawable = new CombinedDrawable(shadowDrawable, drawable, 0, 0);
            combinedDrawable.setIconSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
            drawable = combinedDrawable;
        }
        this.unreadFloatingButton.setBackgroundDrawable(drawable);
        ImageView imageView = this.unreadFloatingButton;
        Drawable animatedArrowDrawable = new AnimatedArrowDrawable(-1);
        this.arrowDrawable = animatedArrowDrawable;
        imageView.setImageDrawable(animatedArrowDrawable);
        this.unreadFloatingButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionUnreadIcon), Mode.MULTIPLY));
        this.unreadFloatingButton.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        this.arrowDrawable.setAnimationProgress(1.0f);
        if (VERSION.SDK_INT >= 21) {
            animator = new StateListAnimator();
            animator.addState(new int[]{16842919}, ObjectAnimator.ofFloat(this.unreadFloatingButton, "translationZ", new float[]{(float) AndroidUtilities.dp(2.0f), (float) AndroidUtilities.dp(4.0f)}).setDuration(200));
            animator.addState(new int[0], ObjectAnimator.ofFloat(this.unreadFloatingButton, "translationZ", new float[]{(float) AndroidUtilities.dp(4.0f), (float) AndroidUtilities.dp(2.0f)}).setDuration(200));
            this.unreadFloatingButton.setStateListAnimator(animator);
            this.unreadFloatingButton.setOutlineProvider(new C17439());
        }
        FrameLayout frameLayout = this.unreadFloatingButtonContainer;
        view = this.unreadFloatingButton;
        i4 = VERSION.SDK_INT >= 21 ? 56 : 60;
        if (VERSION.SDK_INT >= 21) {
            f = 56.0f;
        } else {
            f = 60.0f;
        }
        if (LocaleController.isRTL) {
            i = 3;
        } else {
            i = 5;
        }
        frameLayout.addView(view, LayoutHelper.createFrame(i4, f, i | 48, 10.0f, 13.0f, 10.0f, 0.0f));
        this.unreadFloatingButtonCounter = new TextView(context);
        this.unreadFloatingButtonCounter.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.unreadFloatingButtonCounter.setTextSize(1, 13.0f);
        if (this.currentUnreadCount > 0) {
            this.unreadFloatingButtonCounter.setText(String.format("%d", new Object[]{Integer.valueOf(this.currentUnreadCount)}));
        }
        if (VERSION.SDK_INT >= 21) {
            this.unreadFloatingButtonCounter.setElevation((float) AndroidUtilities.dp(5.0f));
            this.unreadFloatingButtonCounter.setOutlineProvider(new ViewOutlineProvider() {
                @SuppressLint({"NewApi"})
                public void getOutline(View view, Outline outline) {
                    outline.setEmpty();
                }
            });
        }
        this.unreadFloatingButtonCounter.setTextColor(Theme.getColor(Theme.key_chat_goDownButtonCounter));
        this.unreadFloatingButtonCounter.setGravity(17);
        this.unreadFloatingButtonCounter.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(11.5f), Theme.getColor(Theme.key_chat_goDownButtonCounterBackground)));
        this.unreadFloatingButtonCounter.setMinWidth(AndroidUtilities.dp(23.0f));
        this.unreadFloatingButtonCounter.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        this.unreadFloatingButtonContainer.addView(this.unreadFloatingButtonCounter, LayoutHelper.createFrame(-2, 23, 49));
        this.listView.setOnScrollListener(new OnScrollListener() {
            private boolean scrollingManually;

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    if (DialogsActivity.this.searching && DialogsActivity.this.searchWas) {
                        AndroidUtilities.hideKeyboard(DialogsActivity.this.getParentActivity().getCurrentFocus());
                    }
                    this.scrollingManually = true;
                    return;
                }
                this.scrollingManually = false;
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int firstVisibleItem = DialogsActivity.this.layoutManager.findFirstVisibleItemPosition();
                int visibleItemCount = Math.abs(DialogsActivity.this.layoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                if (!DialogsActivity.this.searching || !DialogsActivity.this.searchWas) {
                    if (visibleItemCount > 0 && DialogsActivity.this.layoutManager.findLastVisibleItemPosition() >= DialogsActivity.this.getDialogsArray().size() - 10) {
                        boolean fromCache = !MessagesController.getInstance(DialogsActivity.this.currentAccount).dialogsEndReached;
                        if (fromCache || !MessagesController.getInstance(DialogsActivity.this.currentAccount).serverDialogsEndReached) {
                            MessagesController.getInstance(DialogsActivity.this.currentAccount).loadDialogs(-1, 100, fromCache);
                        }
                    }
                    DialogsActivity.this.checkUnreadButton(true);
                    View view = DialogsActivity.this.floatingButton;
                    if (Toolbar.enabled) {
                        if (Toolbar.type == 0) {
                            view = DialogsActivity.this.fam;
                        } else if (Toolbar.type == 1) {
                            view = DialogsActivity.this.toolBar;
                        }
                    }
                    if (view.getVisibility() != 8) {
                        boolean goingDown;
                        View topChild = recyclerView.getChildAt(0);
                        int firstViewTop = 0;
                        if (topChild != null) {
                            firstViewTop = topChild.getTop();
                        }
                        boolean changed = true;
                        if (DialogsActivity.this.prevPosition == firstVisibleItem) {
                            int topDelta = DialogsActivity.this.prevTop - firstViewTop;
                            goingDown = firstViewTop < DialogsActivity.this.prevTop;
                            changed = Math.abs(topDelta) > 1;
                        } else {
                            goingDown = firstVisibleItem > DialogsActivity.this.prevPosition;
                        }
                        if (changed && DialogsActivity.this.scrollUpdated && ((goingDown || (!goingDown && this.scrollingManually)) && (!TurboConfig$Tabs.tabsEnabled || TurboConfig$Tabs.hideTabsOnScroll))) {
                            DialogsActivity.this.hideFloatingButton(goingDown);
                        }
                        DialogsActivity.this.prevPosition = firstVisibleItem;
                        DialogsActivity.this.prevTop = firstViewTop;
                        DialogsActivity.this.scrollUpdated = true;
                    }
                    if (!TurboConfig$Tabs.tabsEnabled) {
                        return;
                    }
                    if (dy <= 1 || recyclerView.getChildAt(0).getTop() >= 0) {
                        if (dy < -1) {
                            if (TurboConfig$Tabs.hideTabsOnScroll) {
                                DialogsActivity.this.hideTabs(false);
                            } else {
                                DialogsActivity.this.hideFloatingButton(false);
                            }
                            if (firstVisibleItem == 0) {
                                DialogsActivity.this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? 0 : DialogsActivity.this.tabsHeight)), AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) (TurboConfig$Tabs.moveTabsToBottom ? DialogsActivity.this.tabsHeight : 0)));
                            }
                        }
                    } else if (TurboConfig$Tabs.hideTabsOnScroll) {
                        DialogsActivity.this.hideTabs(true);
                    } else {
                        DialogsActivity.this.hideFloatingButton(true);
                    }
                } else if (visibleItemCount > 0 && DialogsActivity.this.layoutManager.findLastVisibleItemPosition() == totalItemCount - 1 && !DialogsActivity.this.dialogsSearchAdapter.isMessagesSearchEndReached()) {
                    DialogsActivity.this.dialogsSearchAdapter.loadMoreSearchMessages();
                }
            }
        });
        createTabs(context);
        updateLayout();
        if (this.searchString == null) {
            this.dialogsAdapter = new DialogsAdapter(context, this.dialogsType, this.onlySelect);
            if (AndroidUtilities.isTablet() && this.openedDialogId != 0) {
                this.dialogsAdapter.setOpenedDialogId(this.openedDialogId);
            }
            this.listView.setAdapter(this.dialogsAdapter);
        }
        int type = 0;
        if (this.searchString != null) {
            type = 2;
        } else if (!this.onlySelect) {
            type = 1;
        }
        this.dialogsSearchAdapter = new DialogsSearchAdapter(context, type, this.dialogsType);
        this.dialogsSearchAdapter.setDelegate(new DialogsSearchAdapterDelegate() {
            public void searchStateChanged(boolean search) {
                if (!DialogsActivity.this.searching || !DialogsActivity.this.searchWas || DialogsActivity.this.searchEmptyView == null) {
                    return;
                }
                if (search) {
                    DialogsActivity.this.searchEmptyView.showProgress();
                } else {
                    DialogsActivity.this.searchEmptyView.showTextView();
                }
            }

            public void didPressedOnSubDialog(long did) {
                if (!DialogsActivity.this.onlySelect) {
                    int lower_id = (int) did;
                    Bundle args = new Bundle();
                    if (lower_id > 0) {
                        args.putInt("user_id", lower_id);
                    } else {
                        args.putInt("chat_id", -lower_id);
                    }
                    if (DialogsActivity.this.actionBar != null) {
                        DialogsActivity.this.actionBar.closeSearchField();
                    }
                    if (AndroidUtilities.isTablet() && DialogsActivity.this.dialogsAdapter != null) {
                        DialogsActivity.this.dialogsAdapter.setOpenedDialogId(DialogsActivity.this.openedDialogId = did);
                        DialogsActivity.this.updateVisibleRows(512);
                    }
                    if (DialogsActivity.this.searchString != null) {
                        if (MessagesController.getInstance(DialogsActivity.this.currentAccount).checkCanOpenChat(args, DialogsActivity.this)) {
                            NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
                            DialogsActivity.this.presentFragment(new ChatActivity(args));
                        }
                    } else if (MessagesController.getInstance(DialogsActivity.this.currentAccount).checkCanOpenChat(args, DialogsActivity.this)) {
                        DialogsActivity.this.presentFragment(new ChatActivity(args));
                    }
                } else if (DialogsActivity.this.dialogsAdapter.hasSelectedDialogs()) {
                    DialogsActivity.this.dialogsAdapter.addOrRemoveSelectedDialog(did, null);
                    DialogsActivity.this.updateSelectedCount();
                    DialogsActivity.this.actionBar.closeSearchField();
                } else {
                    DialogsActivity.this.didSelectResult(did, true, false);
                }
            }

            public void needRemoveHint(int did) {
                if (DialogsActivity.this.getParentActivity() != null && MessagesController.getInstance(DialogsActivity.this.currentAccount).getUser(Integer.valueOf(did)) != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DialogsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    builder.setMessage(LocaleController.formatString("ChatHintsDelete", R.string.ChatHintsDelete, new Object[]{ContactsController.formatName(user.first_name, user.last_name)}));
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogsActivity$12$$Lambda$0(this, did));
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    DialogsActivity.this.showDialog(builder.create());
                }
            }

            final /* synthetic */ void lambda$needRemoveHint$0$DialogsActivity$12(int did, DialogInterface dialogInterface, int i) {
                DataQuery.getInstance(DialogsActivity.this.currentAccount).removePeer(did);
            }
        });
        this.listView.setEmptyView(this.progressView);
        if (this.searchString != null) {
            this.actionBar.openSearchField(this.searchString);
        }
        if (!this.onlySelect && this.dialogsType == 0) {
            backupImageView = new FragmentContextView(context, this, true);
            this.contentView.addView(backupImageView, LayoutHelper.createFrame(-1, 39.0f, 51, 0.0f, -36.0f, 0.0f, 0.0f));
            backupImageView = new FragmentContextView(context, this, false);
            this.contentView.addView(backupImageView, LayoutHelper.createFrame(-1, 39.0f, 51, 0.0f, -36.0f, 0.0f, 0.0f));
            backupImageView.setAdditionalContextView(backupImageView);
            backupImageView.setAdditionalContextView(backupImageView);
        } else if (this.dialogsType == 3 && this.selectAlertString == null) {
            if (this.commentView != null) {
                this.commentView.onDestroy();
            }
            this.commentView = new ChatActivityEnterView(getParentActivity(), this.contentView, null, false);
            this.commentView.setAllowStickersAndGifs(false, false);
            this.commentView.setForceShowSendButton(true, false);
            this.commentView.setVisibility(8);
            this.contentView.addView(this.commentView, LayoutHelper.createFrame(-1, -2, 83));
            this.commentView.setDelegate(new ChatActivityEnterViewDelegate() {
                public void onMessageSend(CharSequence message) {
                    if (DialogsActivity.this.delegate != null) {
                        ArrayList<Long> selectedDialogs = DialogsActivity.this.dialogsAdapter.getSelectedDialogs();
                        if (!selectedDialogs.isEmpty()) {
                            DialogsActivity.this.delegate.didSelectDialogs(DialogsActivity.this, selectedDialogs, message, false);
                        }
                    }
                }

                public void onSwitchRecordMode(boolean video) {
                }

                public void onTextSelectionChanged(int start, int end) {
                }

                public void onStickersExpandedChange() {
                }

                public void onPreAudioVideoRecord() {
                }

                public void onTextChanged(CharSequence text, boolean bigChange) {
                }

                public void onTextSpansChanged(CharSequence text) {
                }

                public void needSendTyping() {
                }

                public void onAttachButtonHidden() {
                }

                public void onAttachButtonShow() {
                }

                public void onMessageEditEnd(boolean loading) {
                }

                public void onWindowSizeChanged(int size) {
                }

                public void onStickersTab(boolean opened) {
                }

                public void didPressedAttachButton() {
                }

                public void needStartRecordVideo(int state) {
                }

                public void needChangeVideoPreviewState(int state, float seekProgress) {
                }

                public void needStartRecordAudio(int state) {
                }

                public void needShowMediaBanHint() {
                }
            });
        }
        if (!this.onlySelect) {
            checkUnreadCount(false);
        }
        this.contentView.addView(this.actionBar);
        return this.fragmentView;
    }

    final /* synthetic */ void lambda$createView$1$DialogsActivity() {
        hideFloatingButton(false);
        this.listView.smoothScrollToPosition(0);
    }

    final /* synthetic */ void lambda$createView$2$DialogsActivity(View view, int position, float x, float y) {
        if (this.listView != null && this.listView.getAdapter() != null && getParentActivity() != null) {
            Bundle args;
            if (!this.onlySelect && (view instanceof DialogCell)) {
                DialogCell cell = (DialogCell) view;
                if (cell.isPointInsideAvatar(x, y)) {
                    if (cell.getUser() != null) {
                        if (TurboConfig.contactAvatarTouch == 2) {
                            args = new Bundle();
                            args.putInt("user_id", cell.getUser().id);
                            presentFragment(new ProfileActivity(args));
                            return;
                        } else if (TurboConfig.contactAvatarTouch == 3) {
                            cell.showAvatar(cell.getUser(), null);
                            return;
                        }
                    } else if (cell.getChat() != null) {
                        if (TurboConfig.groupAvatarTouch == 2) {
                            args = new Bundle();
                            args.putInt("chat_id", cell.getChat().id);
                            presentFragment(new ProfileActivity(args));
                            return;
                        } else if (TurboConfig.groupAvatarTouch == 3) {
                            cell.showAvatar(null, cell.getChat());
                            return;
                        }
                    }
                }
            }
            long dialog_id = 0;
            int message_id = 0;
            boolean isGlobalSearch = false;
            Adapter adapter = this.listView.getAdapter();
            if (adapter == this.dialogsAdapter) {
                TLObject object = this.dialogsAdapter.getItem(position);
                if (object instanceof User) {
                    dialog_id = (long) ((User) object).id;
                } else if (object instanceof TLRPC$TL_dialog) {
                    dialog_id = ((TLRPC$TL_dialog) object).id;
                } else if (object instanceof TLRPC$TL_recentMeUrlChat) {
                    dialog_id = (long) (-((TLRPC$TL_recentMeUrlChat) object).chat_id);
                } else if (object instanceof TLRPC$TL_recentMeUrlUser) {
                    dialog_id = (long) ((TLRPC$TL_recentMeUrlUser) object).user_id;
                } else if (object instanceof TLRPC$TL_recentMeUrlChatInvite) {
                    TLRPC$TL_recentMeUrlChatInvite chatInvite = (TLRPC$TL_recentMeUrlChatInvite) object;
                    TLRPC$ChatInvite invite = chatInvite.chat_invite;
                    if ((invite.chat == null && (!invite.channel || invite.megagroup)) || (invite.chat != null && (!ChatObject.isChannel(invite.chat) || invite.chat.megagroup))) {
                        String hash = chatInvite.url;
                        int index = hash.indexOf(47);
                        if (index > 0) {
                            hash = hash.substring(index + 1);
                        }
                        showDialog(new JoinGroupAlert(getParentActivity(), invite, hash, this));
                        return;
                    } else if (invite.chat != null) {
                        dialog_id = (long) (-invite.chat.id);
                    } else {
                        return;
                    }
                } else if (object instanceof TLRPC$TL_recentMeUrlStickerSet) {
                    TLRPC$StickerSet stickerSet = ((TLRPC$TL_recentMeUrlStickerSet) object).set.set;
                    TLRPC$TL_inputStickerSetID set = new TLRPC$TL_inputStickerSetID();
                    set.id = stickerSet.id;
                    set.access_hash = stickerSet.access_hash;
                    showDialog(new StickersAlert(getParentActivity(), this, set, null, null));
                    return;
                } else if (!(object instanceof TLRPC$TL_recentMeUrlUnknown)) {
                    return;
                } else {
                    return;
                }
                if (this.addToGroup && this.delegate != null) {
                    ArrayList<Long> dlgs = new ArrayList();
                    dlgs.add(Long.valueOf(dialog_id));
                    this.delegate.didSelectDialogs(this, dlgs, null, false);
                    this.delegate = null;
                }
            } else if (adapter == this.dialogsSearchAdapter) {
                MessageObject obj = this.dialogsSearchAdapter.getItem(position);
                isGlobalSearch = this.dialogsSearchAdapter.isGlobalSearch(position);
                if (obj instanceof User) {
                    dialog_id = (long) ((User) obj).id;
                    if (!this.onlySelect) {
                        this.dialogsSearchAdapter.putRecentSearch(dialog_id, (User) obj);
                    }
                } else if (obj instanceof TLRPC$Chat) {
                    if (((TLRPC$Chat) obj).id > 0) {
                        dialog_id = (long) (-((TLRPC$Chat) obj).id);
                    } else {
                        dialog_id = AndroidUtilities.makeBroadcastId(((TLRPC$Chat) obj).id);
                    }
                    if (!this.onlySelect) {
                        this.dialogsSearchAdapter.putRecentSearch(dialog_id, (TLRPC$Chat) obj);
                    }
                } else if (obj instanceof TLRPC$EncryptedChat) {
                    dialog_id = ((long) ((TLRPC$EncryptedChat) obj).id) << 32;
                    if (!this.onlySelect) {
                        this.dialogsSearchAdapter.putRecentSearch(dialog_id, (TLRPC$EncryptedChat) obj);
                    }
                } else if (obj instanceof MessageObject) {
                    MessageObject messageObject = obj;
                    dialog_id = messageObject.getDialogId();
                    message_id = messageObject.getId();
                    this.dialogsSearchAdapter.addHashtagsFromMessage(this.dialogsSearchAdapter.getLastSearchString());
                } else if (obj instanceof String) {
                    this.actionBar.openSearchField((String) obj);
                }
            }
            if (dialog_id == 0) {
                return;
            }
            if (!this.onlySelect) {
                args = new Bundle();
                int lower_part = (int) dialog_id;
                int high_id = (int) (dialog_id >> 32);
                if (lower_part == 0) {
                    args.putInt("enc_id", high_id);
                } else if (high_id == 1) {
                    args.putInt("chat_id", lower_part);
                } else if (lower_part > 0) {
                    args.putInt("user_id", lower_part);
                } else if (lower_part < 0) {
                    if (message_id != 0) {
                        TLRPC$Chat chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_part));
                        if (!(chat == null || chat.migrated_to == null)) {
                            args.putInt("migrated_to", lower_part);
                            lower_part = -chat.migrated_to.channel_id;
                        }
                    }
                    args.putInt("chat_id", -lower_part);
                }
                if (message_id != 0) {
                    args.putInt("message_id", message_id);
                } else if (!(isGlobalSearch || this.actionBar == null)) {
                    this.actionBar.closeSearchField();
                }
                if (AndroidUtilities.isTablet()) {
                    if (this.openedDialogId == dialog_id && adapter != this.dialogsSearchAdapter) {
                        return;
                    }
                    if (this.dialogsAdapter != null) {
                        DialogsAdapter dialogsAdapter = this.dialogsAdapter;
                        this.openedDialogId = dialog_id;
                        dialogsAdapter.setOpenedDialogId(dialog_id);
                        updateVisibleRows(512);
                    }
                }
                if (this.searchString != null) {
                    if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(args, this)) {
                        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
                        presentFragment(new ChatActivity(args));
                    }
                } else if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(args, this)) {
                    presentFragment(new ChatActivity(args));
                }
            } else if (this.dialogsAdapter.hasSelectedDialogs() || this.multiSelection || this.catOperationType > 0) {
                this.dialogsAdapter.addOrRemoveSelectedDialog(dialog_id, view);
                updateSelectedCount();
            } else {
                didSelectResult(dialog_id, true, false);
            }
        }
    }

    final /* synthetic */ void lambda$createView$3$DialogsActivity(View v) {
        Bundle args = new Bundle();
        args.putBoolean("destroyAfterSelect", true);
        args.putBoolean("only_show_contacts", true);
        presentFragment(new ContactsActivity(args));
    }

    final /* synthetic */ void lambda$createView$4$DialogsActivity(View view) {
        if (this.listView.getAdapter() != this.dialogsAdapter) {
            return;
        }
        ArrayList<TLRPC$TL_dialog> array;
        int a;
        TLRPC$TL_dialog dialog;
        if (this.layoutManager.findFirstVisibleItemPosition() == 0) {
            array = getDialogsArray();
            for (a = array.size() - 1; a >= 0; a--) {
                dialog = (TLRPC$TL_dialog) array.get(a);
                if ((dialog.unread_count != 0 || dialog.unread_mark) && !MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                    this.listView.smoothScrollToPosition(a);
                    return;
                }
            }
            return;
        }
        int middle = this.listView.getMeasuredHeight() / 2;
        boolean found = false;
        int b = 0;
        int count = this.listView.getChildCount();
        while (b < count) {
            View child = this.listView.getChildAt(b);
            if (!(child instanceof DialogCell) || child.getTop() > middle || child.getBottom() < middle) {
                b++;
            } else {
                Holder holder = (Holder) this.listView.findContainingViewHolder(child);
                if (holder != null) {
                    array = getDialogsArray();
                    for (a = Math.min(holder.getAdapterPosition(), array.size()) - 1; a >= 0; a--) {
                        dialog = (TLRPC$TL_dialog) array.get(a);
                        if ((dialog.unread_count != 0 || dialog.unread_mark) && !MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                            found = true;
                            this.listView.smoothScrollToPosition(a);
                            break;
                        }
                    }
                }
                if (!found) {
                    hideFloatingButton(false);
                    this.listView.smoothScrollToPosition(0);
                }
            }
        }
        if (!found) {
            hideFloatingButton(false);
            this.listView.smoothScrollToPosition(0);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.dialogsAdapter != null) {
            this.dialogsAdapter.notifyDataSetChanged();
        }
        if (this.commentView != null) {
            this.commentView.onResume();
        }
        if (this.dialogsSearchAdapter != null) {
            this.dialogsSearchAdapter.notifyDataSetChanged();
        }
        if (this.checkPermission && !this.onlySelect && VERSION.SDK_INT >= 23) {
            Context activity = getParentActivity();
            if (activity != null) {
                this.checkPermission = false;
                if (!(activity.checkSelfPermission("android.permission.READ_CONTACTS") == 0 && activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
                    Dialog create;
                    if (UserConfig.getInstance(this.currentAccount).syncContacts && activity.shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                        create = AlertsCreator.createContactsPermissionDialog(activity, new DialogsActivity$$Lambda$5(this)).create();
                        this.permissionDialog = create;
                        showDialog(create);
                    } else if (activity.shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                        builder.setMessage(LocaleController.getString("PermissionStorage", R.string.PermissionStorage));
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                        create = builder.create();
                        this.permissionDialog = create;
                        showDialog(create);
                    } else {
                        askForPermissons(true);
                    }
                }
            }
        }
        TurboConfig$BG.setBooleanValue("internal_intent", false);
        this.tabsView.updateCounters();
        this.tabsView.setSelectedTab(TurboConfig$Tabs.selectedTab);
        updateTitle(TurboConfig$Tabs.selectedTab);
    }

    final /* synthetic */ void lambda$onResume$5$DialogsActivity(int param) {
        boolean z;
        if (param != 0) {
            z = true;
        } else {
            z = false;
        }
        this.askAboutContacts = z;
        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts", this.askAboutContacts).commit();
        askForPermissons(false);
    }

    public void onPause() {
        super.onPause();
        if (this.commentView != null) {
            this.commentView.onResume();
        }
    }

    private void checkUnreadCount(boolean animated) {
        if (BuildVars.DEBUG_PRIVATE_VERSION) {
            int newCount = MessagesController.getInstance(this.currentAccount).unreadUnmutedDialogs;
            if (newCount != this.currentUnreadCount) {
                this.currentUnreadCount = newCount;
                if (this.unreadFloatingButtonContainer != null) {
                    if (this.currentUnreadCount > 0) {
                        this.unreadFloatingButtonCounter.setText(String.format("%d", new Object[]{Integer.valueOf(this.currentUnreadCount)}));
                    }
                    checkUnreadButton(animated);
                }
            }
        }
    }

    private void checkUnreadButton(boolean animated) {
        if (!this.onlySelect && this.listView.getAdapter() == this.dialogsAdapter) {
            boolean found = false;
            if (this.currentUnreadCount > 0) {
                int b;
                View child;
                int middle = this.listView.getMeasuredHeight() / 2;
                int firstVisibleItem = this.layoutManager.findFirstVisibleItemPosition();
                int count = this.listView.getChildCount();
                int unreadOnScreen = 0;
                for (b = 0; b < count; b++) {
                    child = this.listView.getChildAt(b);
                    if ((child instanceof DialogCell) && ((DialogCell) child).isUnread()) {
                        unreadOnScreen++;
                    }
                }
                b = 0;
                while (b < count) {
                    child = this.listView.getChildAt(b);
                    if (!(child instanceof DialogCell) || child.getTop() > middle || child.getBottom() < middle) {
                        b++;
                    } else {
                        Holder holder = (Holder) this.listView.findContainingViewHolder(child);
                        if (holder != null) {
                            ArrayList<TLRPC$TL_dialog> array = getDialogsArray();
                            if (firstVisibleItem != 0) {
                                found = true;
                                this.arrowDrawable.setAnimationProgressAnimated(0.0f);
                            } else if (unreadOnScreen != this.currentUnreadCount) {
                                int size = array.size();
                                for (int a = holder.getAdapterPosition() + 1; a < size; a++) {
                                    TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) array.get(a);
                                    if ((dialog.unread_count != 0 || dialog.unread_mark) && !MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                        this.arrowDrawable.setAnimationProgressAnimated(1.0f);
                                        found = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (found) {
                if (this.unreadFloatingButtonContainer.getTag() == null) {
                    this.unreadFloatingButtonContainer.setTag(Integer.valueOf(1));
                    this.unreadFloatingButtonContainer.setVisibility(0);
                    if (animated) {
                        this.unreadFloatingButtonContainer.animate().alpha(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator()).setListener(null).start();
                    } else {
                        this.unreadFloatingButtonContainer.setAlpha(1.0f);
                    }
                }
            } else if (this.unreadFloatingButtonContainer.getTag() != null) {
                this.unreadFloatingButtonContainer.setTag(null);
                if (animated) {
                    this.unreadFloatingButtonContainer.animate().alpha(0.0f).setDuration(200).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            DialogsActivity.this.unreadFloatingButtonContainer.setVisibility(4);
                        }
                    }).start();
                    return;
                }
                this.unreadFloatingButtonContainer.setAlpha(0.0f);
                this.unreadFloatingButtonContainer.setVisibility(4);
            }
        }
    }

    private void updateProxyButton(boolean animated) {
        boolean z = false;
        if (this.proxyDrawable != null) {
            boolean proxyEnabled;
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            String proxyAddress = preferences.getString("proxy_ip", "");
            if (!preferences.getBoolean("proxy_enabled", false) || TextUtils.isEmpty(proxyAddress)) {
                proxyEnabled = false;
            } else {
                proxyEnabled = true;
            }
            if (proxyEnabled || (MessagesController.getInstance(this.currentAccount).blockedCountry && !SharedConfig.proxyList.isEmpty())) {
                if (!this.actionBar.isSearchFieldVisible()) {
                    this.proxyItem.setVisibility(0);
                }
                ProxyDrawable proxyDrawable = this.proxyDrawable;
                if (this.currentConnectionState == 3 || this.currentConnectionState == 5) {
                    z = true;
                }
                proxyDrawable.setConnected(proxyEnabled, z, animated);
                this.proxyItemVisisble = true;
                return;
            }
            this.proxyItem.setVisibility(8);
            this.proxyItemVisisble = false;
        }
    }

    private void updateSelectedCount() {
        if (this.commentView != null) {
            AnimatorSet animatorSet;
            Animator[] animatorArr;
            if (this.dialogsAdapter.hasSelectedDialogs()) {
                if (this.commentView.getTag() == null) {
                    this.commentView.setFieldText("");
                    this.commentView.setVisibility(0);
                    animatorSet = new AnimatorSet();
                    animatorArr = new Animator[1];
                    animatorArr[0] = ObjectAnimator.ofFloat(this.commentView, "translationY", new float[]{(float) this.commentView.getMeasuredHeight(), 0.0f});
                    animatorSet.playTogether(animatorArr);
                    animatorSet.setDuration(180);
                    animatorSet.setInterpolator(new DecelerateInterpolator());
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            DialogsActivity.this.commentView.setTag(Integer.valueOf(2));
                        }
                    });
                    animatorSet.start();
                    this.commentView.setTag(Integer.valueOf(1));
                }
                this.actionBar.setTitle(LocaleController.formatPluralString("Recipient", this.dialogsAdapter.getSelectedDialogs().size()));
                return;
            }
            if (this.dialogsType == 3 && this.selectAlertString == null) {
                this.actionBar.setTitle(LocaleController.getString("ForwardTo", R.string.ForwardTo));
            } else {
                this.actionBar.setTitle(LocaleController.getString("SelectChat", R.string.SelectChat));
            }
            if (this.commentView.getTag() != null) {
                this.commentView.hidePopup(false);
                this.commentView.closeKeyboard();
                animatorSet = new AnimatorSet();
                animatorArr = new Animator[1];
                animatorArr[0] = ObjectAnimator.ofFloat(this.commentView, "translationY", new float[]{0.0f, (float) this.commentView.getMeasuredHeight()});
                animatorSet.playTogether(animatorArr);
                animatorSet.setDuration(180);
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        DialogsActivity.this.commentView.setVisibility(8);
                    }
                });
                animatorSet.start();
                this.commentView.setTag(null);
                this.listView.requestLayout();
            }
        }
    }

    @TargetApi(23)
    private void askForPermissons(boolean alert) {
        Activity activity = getParentActivity();
        if (activity != null) {
            ArrayList<String> permissons = new ArrayList();
            if (UserConfig.getInstance(this.currentAccount).syncContacts && this.askAboutContacts && activity.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
                if (alert) {
                    Dialog create = AlertsCreator.createContactsPermissionDialog(activity, new DialogsActivity$$Lambda$6(this)).create();
                    this.permissionDialog = create;
                    showDialog(create);
                    return;
                }
                permissons.add("android.permission.READ_CONTACTS");
                permissons.add("android.permission.WRITE_CONTACTS");
                permissons.add("android.permission.GET_ACCOUNTS");
            }
            if (activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                permissons.add("android.permission.READ_EXTERNAL_STORAGE");
                permissons.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (!permissons.isEmpty()) {
                try {
                    activity.requestPermissions((String[]) permissons.toArray(new String[permissons.size()]), 1);
                } catch (Exception e) {
                }
            }
        }
    }

    final /* synthetic */ void lambda$askForPermissons$6$DialogsActivity(int param) {
        boolean z;
        if (param != 0) {
            z = true;
        } else {
            z = false;
        }
        this.askAboutContacts = z;
        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts", this.askAboutContacts).commit();
        askForPermissons(false);
    }

    protected void onDialogDismiss(Dialog dialog) {
        super.onDialogDismiss(dialog);
        if (this.permissionDialog != null && dialog == this.permissionDialog && getParentActivity() != null) {
            askForPermissons(false);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!this.onlySelect && this.floatingButton != null) {
            this.floatingButton.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    float f = 0.0f;
                    DialogsActivity.this.floatingButton.setTranslationY(DialogsActivity.this.floatingHidden ? (float) AndroidUtilities.dp(100.0f) : 0.0f);
                    FrameLayout access$1300 = DialogsActivity.this.unreadFloatingButtonContainer;
                    if (DialogsActivity.this.floatingHidden) {
                        f = (float) AndroidUtilities.dp(74.0f);
                    }
                    access$1300.setTranslationY(f);
                    DialogsActivity.this.floatingButton.setClickable(!DialogsActivity.this.floatingHidden);
                    if (DialogsActivity.this.floatingButton != null) {
                        DialogsActivity.this.floatingButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    public void onRequestPermissionsResultFragment(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int a = 0; a < permissions.length; a++) {
                if (grantResults.length > a) {
                    String str = permissions[a];
                    boolean z = true;
                    switch (str.hashCode()) {
                        case 1365911975:
                            if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                                z = true;
                                break;
                            }
                            break;
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
                        case true:
                            if (grantResults[a] != 0) {
                                break;
                            }
                            ImageLoader.getInstance().checkMediaPaths();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.dialogsNeedReload) {
            checkUnreadCount(true);
            if (this.dialogsAdapter != null) {
                if (this.dialogsAdapter.isDataSetChanged() || args.length > 0) {
                    this.dialogsAdapter.notifyDataSetChanged();
                } else {
                    updateVisibleRows(2048);
                }
            }
            if (this.listView != null) {
                try {
                    if (this.listView.getAdapter() == this.dialogsAdapter) {
                        this.searchEmptyView.setVisibility(8);
                        this.listView.setEmptyView(this.progressView);
                    } else {
                        if (this.searching && this.searchWas) {
                            this.listView.setEmptyView(this.searchEmptyView);
                        } else {
                            this.searchEmptyView.setVisibility(8);
                            this.listView.setEmptyView(null);
                        }
                        this.progressView.setVisibility(8);
                    }
                    if (UserConfig.isRobot) {
                        this.progressView.setVisibility(8);
                    }
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
        } else if (id == NotificationCenter.emojiDidLoaded) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.closeSearchByActiveAction) {
            if (this.actionBar != null) {
                this.actionBar.closeSearchField();
            }
        } else if (id == NotificationCenter.proxySettingsChanged) {
            updateProxyButton(false);
        } else if (id == NotificationCenter.updateInterfaces) {
            Integer mask = args[0];
            updateVisibleRows(mask.intValue());
            if (!((mask.intValue() & 2048) == 0 && (mask.intValue() & 256) == 0)) {
                checkUnreadCount(true);
            }
            updateVisibleRows(((Integer) args[0]).intValue());
            if (TurboConfig$Toast.typing) {
                showToast(((Integer) args[0]).intValue());
            }
        } else if (id == NotificationCenter.appDidLogout) {
            dialogsLoaded[this.currentAccount] = false;
        } else if (id == NotificationCenter.encryptedChatUpdated) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.contactsDidLoaded) {
            if (this.dialogsType != 0 || !MessagesController.getInstance(this.currentAccount).dialogs.isEmpty()) {
                updateVisibleRows(0);
            } else if (this.dialogsAdapter != null) {
                this.dialogsAdapter.notifyDataSetChanged();
            }
        } else if (id == NotificationCenter.openedChatChanged) {
            if (this.dialogsType == 0 && AndroidUtilities.isTablet()) {
                boolean close = ((Boolean) args[1]).booleanValue();
                long dialog_id = ((Long) args[0]).longValue();
                if (!close) {
                    this.openedDialogId = dialog_id;
                } else if (dialog_id == this.openedDialogId) {
                    this.openedDialogId = 0;
                }
                if (this.dialogsAdapter != null) {
                    this.dialogsAdapter.setOpenedDialogId(this.openedDialogId);
                }
                updateVisibleRows(512);
            }
        } else if (id == NotificationCenter.notificationsSettingsUpdated) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.messageReceivedByAck || id == NotificationCenter.messageReceivedByServer || id == NotificationCenter.messageSendError) {
            updateVisibleRows(4096);
        } else if (id == NotificationCenter.didSetPasscode) {
            updatePasscodeButton();
        } else if (id == NotificationCenter.needReloadRecentDialogsSearch) {
            if (this.dialogsSearchAdapter != null) {
                this.dialogsSearchAdapter.loadRecentSearch();
            }
        } else if (id == NotificationCenter.didLoadedReplyMessages) {
            updateVisibleRows(32768);
        } else if (id == NotificationCenter.reloadHints) {
            if (this.dialogsSearchAdapter != null) {
                this.dialogsSearchAdapter.notifyDataSetChanged();
            }
        } else if (id == NotificationCenter.didUpdatedConnectionState) {
            int state = ConnectionsManager.getInstance(account).getConnectionState();
            if (this.currentConnectionState != state) {
                this.currentConnectionState = state;
                updateProxyButton(true);
            }
        } else if (id == NotificationCenter.dialogsUnreadCounterChanged) {
        } else {
            if (id == NotificationCenter.didSetNewWallpapper) {
                if (this.contentView != null) {
                    this.contentView.setBackgroundImage(Theme.getCachedWallpaper());
                    this.listView.invalidateViews();
                }
            } else if (id == NotificationCenter.turboUpdateInterface) {
                int type = ((Integer) args[0]).intValue();
                if (type == 1) {
                    updateLayout();
                } else if (type == 2) {
                    if (((Boolean) args[1]).booleanValue()) {
                        this.listView.setOnTouchListener(new OnTouchListener() {
                            public boolean onTouch(View v, MotionEvent event) {
                                DialogsActivity.this.onSwipe(event);
                                return false;
                            }
                        });
                    }
                } else if (type == 4) {
                    if (!(this.tabsView == null || this.tabsView.getParent() == null)) {
                        ((FrameLayout) this.fragmentView).removeView(this.tabsView);
                    }
                    createTabs(getParentActivity());
                } else if (type == 5) {
                    if (Toolbar.enabled) {
                        this.floatingButton.setVisibility(8);
                        if (!(this.fam == null || this.fam.getParent() == null)) {
                            this.contentView.removeView(this.fam);
                        }
                        if (!(this.toolBar == null || this.toolBar.getParent() == null)) {
                            this.contentView.removeView(this.toolBar);
                        }
                        if (Toolbar.type == 0) {
                            createSlidingToolBar(getParentActivity());
                            updateLayout();
                            return;
                        } else if (Toolbar.type == 1) {
                            createToolBar(getParentActivity());
                            updateLayout();
                            return;
                        } else {
                            return;
                        }
                    }
                    this.floatingButton.setVisibility(0);
                    if (!(this.toolBar == null || this.toolBar.getParent() == null)) {
                        this.contentView.removeView(this.toolBar);
                    }
                    if (this.fam != null && this.fam.getParent() != null) {
                        this.contentView.removeView(this.fam);
                    }
                } else if (type == 6) {
                    updateSubTitle(TurboConfig$BG.currentCat);
                } else if (type == 7) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("dialog_id", ((Long) args[1]).longValue());
                    bundle.putBoolean("from_chat", false);
                    presentFragment(new ChatSettingsActivity(bundle));
                }
            }
        }
    }

    private ArrayList<TLRPC$TL_dialog> getDialogsArray() {
        if (this.dialogsType == 0) {
            return new DialogsLoader(this.dialogsType).getDialogsArray();
        }
        if (this.dialogsType == 1) {
            return MessagesController.getInstance(this.currentAccount).dialogsServerOnly;
        }
        if (this.dialogsType == 2) {
            return MessagesController.getInstance(this.currentAccount).dialogsGroupsOnly;
        }
        if (this.dialogsType == 3) {
            return new DialogsLoader(this.dialogsType).getDialogsArray();
        }
        return null;
    }

    public void setSideMenu(RecyclerView recyclerView) {
        this.sideMenu = recyclerView;
        this.sideMenu.setBackgroundColor(Theme.getColor(Theme.key_chats_menuBackground));
        this.sideMenu.setGlowColor(Theme.getColor(Theme.key_chats_menuBackground));
    }

    private void updatePasscodeButton() {
        if (this.passcodeItem != null) {
            if (SharedConfig.passcodeHash.length() == 0 || this.searching) {
                this.passcodeItem.setVisibility(8);
                return;
            }
            this.passcodeItem.setVisibility(0);
            if (SharedConfig.appLocked) {
                this.passcodeItem.setIcon((int) R.drawable.lock_close);
            } else {
                this.passcodeItem.setIcon((int) R.drawable.lock_open);
            }
        }
    }

    private void hideFloatingButton(boolean hide) {
        float f = 0.0f;
        boolean z = true;
        if (this.floatingHidden != hide) {
            this.floatingHidden = hide;
            int des = 100;
            if (TurboConfig$Tabs.moveTabsToBottom) {
                des = 125;
            }
            if (!Toolbar.enabled) {
                boolean z2;
                AnimatorSet animatorSet = new AnimatorSet();
                Animator[] animatorArr = new Animator[2];
                ImageView imageView = this.floatingButton;
                String str = "translationY";
                float[] fArr = new float[1];
                fArr[0] = this.floatingHidden ? (float) AndroidUtilities.dp(100.0f) : 0.0f;
                animatorArr[0] = ObjectAnimator.ofFloat(imageView, str, fArr);
                FrameLayout frameLayout = this.unreadFloatingButtonContainer;
                String str2 = "translationY";
                float[] fArr2 = new float[1];
                if (this.floatingHidden) {
                    f = (float) AndroidUtilities.dp(74.0f);
                }
                fArr2[0] = f;
                animatorArr[1] = ObjectAnimator.ofFloat(frameLayout, str2, fArr2);
                animatorSet.playTogether(animatorArr);
                animatorSet.setDuration(300);
                animatorSet.setInterpolator(this.floatingInterpolator);
                ImageView imageView2 = this.floatingButton;
                if (hide) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                imageView2.setClickable(z2);
                animatorSet.start();
            } else if (Toolbar.type == 0) {
                FloatingActionsMenu floatingActionsMenu = this.fam;
                r7 = "translationY";
                float[] fArr3 = new float[1];
                if (this.floatingHidden) {
                    f = (float) AndroidUtilities.dp((float) des);
                }
                fArr3[0] = f;
                animator = ObjectAnimator.ofFloat(floatingActionsMenu, r7, fArr3).setDuration(300);
                animator.setInterpolator(this.floatingInterpolator);
                animator.start();
            } else if (Toolbar.type == 1) {
                LinearLayout linearLayout = this.toolBar;
                r7 = "translationY";
                float[] fArr4 = new float[1];
                if (this.floatingHidden) {
                    f = (float) AndroidUtilities.dp((float) des);
                }
                fArr4[0] = f;
                animator = ObjectAnimator.ofFloat(linearLayout, r7, fArr4).setDuration(300);
                animator.setInterpolator(this.floatingInterpolator);
                linearLayout = this.toolBar;
                if (hide) {
                    z = false;
                }
                linearLayout.setClickable(z);
                animator.start();
            }
        }
    }

    private void updateVisibleRows(int mask) {
        if (this.listView != null) {
            int count = this.listView.getChildCount();
            for (int a = 0; a < count; a++) {
                View child = this.listView.getChildAt(a);
                if (child instanceof DialogCell) {
                    if (this.listView.getAdapter() != this.dialogsSearchAdapter) {
                        DialogCell cell = (DialogCell) child;
                        if ((mask & 2048) != 0) {
                            cell.checkCurrentDialogIndex();
                            if (this.dialogsType == 0 && AndroidUtilities.isTablet()) {
                                boolean z;
                                if (cell.getDialogId() == this.openedDialogId) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                cell.setDialogSelected(z);
                            }
                        } else if ((mask & 512) == 0) {
                            cell.update(mask);
                        } else if (this.dialogsType == 0 && AndroidUtilities.isTablet()) {
                            cell.setDialogSelected(cell.getDialogId() == this.openedDialogId);
                        }
                    }
                } else if (child instanceof UserCell) {
                    ((UserCell) child).update(mask);
                } else if (child instanceof ProfileSearchCell) {
                    ((ProfileSearchCell) child).update(mask);
                } else if (child instanceof RecyclerListView) {
                    RecyclerListView innerListView = (RecyclerListView) child;
                    int count2 = innerListView.getChildCount();
                    for (int b = 0; b < count2; b++) {
                        View child2 = innerListView.getChildAt(b);
                        if (child2 instanceof HintDialogCell) {
                            ((HintDialogCell) child2).checkUnreadCounter(mask);
                        }
                    }
                }
            }
            this.tabsView.updateCounters();
        }
    }

    public void setDelegate(DialogsActivityDelegate dialogsActivityDelegate) {
        this.delegate = dialogsActivityDelegate;
    }

    public void setSearchString(String string) {
        this.searchString = string;
    }

    public boolean isMainDialogList() {
        return this.delegate == null && this.searchString == null;
    }

    private void didSelectResult(long dialog_id, boolean useAlert, boolean param) {
        TLRPC$Chat chat;
        if (this.addToGroupAlertString == null && ((int) dialog_id) < 0) {
            chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-((int) dialog_id)));
            if (ChatObject.isChannel(chat) && !chat.megagroup && (this.cantSendToChannels || !ChatObject.isCanWriteToChannel(-((int) dialog_id), this.currentAccount))) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                builder.setMessage(LocaleController.getString("ChannelCantSendMessage", R.string.ChannelCantSendMessage));
                builder.setNegativeButton(LocaleController.getString("OK", R.string.OK), null);
                showDialog(builder.create());
                return;
            }
        }
        if (!useAlert || ((this.selectAlertString == null || this.selectAlertStringGroup == null) && this.addToGroupAlertString == null)) {
            if (this.delegate != null) {
                ArrayList<Long> dids = new ArrayList();
                dids.add(Long.valueOf(dialog_id));
                this.delegate.didSelectDialogs(this, dids, null, param);
                this.delegate = null;
                return;
            }
            finishFragment();
        } else if (getParentActivity() != null) {
            builder = new AlertDialog.Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            int lower_part = (int) dialog_id;
            int high_id = (int) (dialog_id >> 32);
            if (lower_part == 0) {
                if (MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(high_id)).user_id)) != null) {
                    builder.setMessage(LocaleController.formatStringSimple(this.selectAlertString, new Object[]{UserObject.getUserName(user)}));
                } else {
                    return;
                }
            } else if (high_id == 1) {
                if (MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(lower_part)) != null) {
                    builder.setMessage(LocaleController.formatStringSimple(this.selectAlertStringGroup, new Object[]{chat.title}));
                } else {
                    return;
                }
            } else if (lower_part == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                builder.setMessage(LocaleController.formatStringSimple(this.selectAlertStringGroup, new Object[]{LocaleController.getString("SavedMessages", R.string.SavedMessages)}));
            } else if (lower_part > 0) {
                if (MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_part)) != null) {
                    builder.setMessage(LocaleController.formatStringSimple(this.selectAlertString, new Object[]{UserObject.getUserName(user)}));
                } else {
                    return;
                }
            } else if (lower_part < 0) {
                if (MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_part)) == null) {
                    return;
                }
                if (this.addToGroupAlertString != null) {
                    builder.setMessage(LocaleController.formatStringSimple(this.addToGroupAlertString, new Object[]{chat.title}));
                } else {
                    builder.setMessage(LocaleController.formatStringSimple(this.selectAlertStringGroup, new Object[]{chat.title}));
                }
            }
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogsActivity$$Lambda$7(this, dialog_id));
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
        }
    }

    final /* synthetic */ void lambda$didSelectResult$7$DialogsActivity(long dialog_id, DialogInterface dialogInterface, int i) {
        didSelectResult(dialog_id, false, false);
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescriptionDelegate cellDelegate = new DialogsActivity$$Lambda$8(this);
        r11 = new ThemeDescription[148];
        r11[9] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, null, this.tabsView.getTabDrawables(), null, Theme.key_tabIcons);
        r11[10] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, this.tabsView.getSelectedTabPaint(), new Drawable[]{this.tabsView.getSelectedTabDrawable()}, null, Theme.key_tabSelectedIcon);
        r11[11] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, null, null, null, Theme.key_tabCounter);
        r11[12] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, null, null, null, Theme.key_tabCounterText);
        r11[13] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, null, null, null, Theme.key_muteTabCounter);
        r11[14] = new ThemeDescription(this.tabsView, 0, new Class[]{TabsViewCell.class}, null, null, null, Theme.key_muteTabCounterText, true);
        r11[15] = new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector);
        r11[16] = new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider);
        r11[17] = new ThemeDescription(this.searchEmptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder);
        r11[18] = new ThemeDescription(this.searchEmptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle, true);
        r11[19] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{DialogsEmptyCell.class}, new String[]{"emptyTextView1"}, null, null, null, Theme.key_emptyListPlaceholder);
        r11[20] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{DialogsEmptyCell.class}, new String[]{"emptyTextView2"}, null, null, null, Theme.key_emptyListPlaceholder);
        r11[21] = new ThemeDescription(this.floatingButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chats_actionIcon);
        r11[22] = new ThemeDescription(this.floatingButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chats_actionBackground);
        r11[23] = new ThemeDescription(this.floatingButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chats_actionPressedBackground, true);
        r11[24] = new ThemeDescription(this.unreadFloatingButtonCounter, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chat_goDownButtonCounterBackground);
        r11[25] = new ThemeDescription(this.unreadFloatingButtonCounter, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_goDownButtonCounter);
        r11[26] = new ThemeDescription(this.unreadFloatingButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chats_actionUnreadIcon);
        r11[27] = new ThemeDescription(this.unreadFloatingButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chats_actionUnreadBackground);
        r11[28] = new ThemeDescription(this.unreadFloatingButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chats_actionUnreadPressedBackground);
        r11[29] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.avatar_photoDrawable, Theme.avatar_broadcastDrawable, Theme.avatar_savedDrawable}, null, Theme.key_avatar_text);
        r11[30] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundRed);
        r11[31] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundOrange);
        r11[32] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundViolet);
        r11[33] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundGreen);
        r11[34] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundCyan);
        r11[35] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundBlue);
        r11[36] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundPink);
        r11[37] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_avatar_backgroundSaved);
        r11[38] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countPaint, null, null, Theme.key_chats_unreadCounter);
        r11[39] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countGrayPaint, null, null, Theme.key_chats_unreadCounterMuted);
        r11[40] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countTextPaint, null, null, Theme.key_chats_unreadCounterText);
        r11[41] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, Theme.dialogs_namePaint, null, null, Theme.key_chats_name);
        r11[42] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, Theme.dialogs_nameEncryptedPaint, null, null, Theme.key_chats_secretName);
        r11[43] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_lockDrawable}, null, Theme.key_chats_secretIcon);
        r11[44] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_groupDrawable, Theme.dialogs_broadcastDrawable, Theme.dialogs_botDrawable}, null, Theme.key_chats_nameIcon);
        r11[45] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_pinnedDrawable}, null, Theme.key_chats_pinnedIcon);
        r11[46] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_messagePaint, null, null, Theme.key_chats_message);
        r11[47] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_chats_nameMessage);
        r11[48] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_chats_draft);
        r11[49] = new ThemeDescription(null, 0, null, null, null, cellDelegate, Theme.key_chats_attachMessage);
        r11[50] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_messagePrintingPaint, null, null, Theme.key_chats_actionMessage);
        r11[51] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_timePaint, null, null, Theme.key_chats_date);
        r11[52] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_pinnedPaint, null, null, Theme.key_chats_pinnedOverlay);
        r11[53] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_tabletSeletedPaint, null, null, Theme.key_chats_tabletSelectedOverlay);
        r11[54] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_checkDrawable, Theme.dialogs_halfCheckDrawable}, null, Theme.key_chats_sentCheck);
        r11[55] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_clockDrawable}, null, Theme.key_chats_sentClock);
        r11[56] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, Theme.dialogs_errorPaint, null, null, Theme.key_chats_sentError);
        r11[57] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_errorDrawable}, null, Theme.key_chats_sentErrorIcon);
        r11[58] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedCheckDrawable}, null, Theme.key_chats_verifiedCheck);
        r11[59] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedDrawable}, null, Theme.key_chats_verifiedBackground);
        r11[60] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_muteDrawable}, null, Theme.key_chats_muteIcon);
        r11[61] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_mentionDrawable}, null, Theme.key_chats_mentionIcon);
        r11[62] = new ThemeDescription(this.listView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_muteDrawable}, null, Theme.key_chats_muteIcon, true);
        r11[63] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_chats_menuBackground);
        r11[64] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chats_menuName);
        r11[65] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chats_menuPhone);
        r11[66] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chats_menuPhoneCats);
        r11[67] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chats_menuCloudBackgroundCats);
        r11[68] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chat_serviceBackground);
        r11[69] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_chats_menuTopShadow);
        r11[70] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{DrawerProfileCell.class}, null, null, null, Theme.key_avatar_backgroundActionBarBlue);
        r11[71] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{DrawerActionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chats_menuItemIcon);
        r11[72] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerActionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chats_menuItemText, true);
        r11[73] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerUserCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chats_menuItemText);
        r11[74] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{DrawerUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_chats_unreadCounterText);
        r11[75] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{DrawerUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_chats_unreadCounter);
        r11[76] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{DrawerUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_chats_menuBackground);
        r11[77] = new ThemeDescription(this.sideMenu, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{DrawerAddCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chats_menuItemIcon);
        r11[78] = new ThemeDescription(this.sideMenu, 0, new Class[]{DrawerAddCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chats_menuItemText);
        r11[79] = new ThemeDescription(this.sideMenu, 0, new Class[]{DividerCell.class}, Theme.dividerPaint, null, null, Theme.key_divider);
        r11[80] = new ThemeDescription(this.listView, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle);
        r11[81] = new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, Theme.dialogs_offlinePaint, null, null, Theme.key_windowBackgroundWhiteGrayText3);
        r11[82] = new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, Theme.dialogs_onlinePaint, null, null, Theme.key_windowBackgroundWhiteBlueText3);
        r11[83] = new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText);
        r11[84] = new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection);
        r11[85] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{HashtagSearchCell.class}, null, null, null, Theme.key_windowBackgroundWhiteBlackText, true);
        r11[86] = new ThemeDescription(this.progressView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle);
        r11[87] = new ThemeDescription(this.dialogsSearchAdapter != null ? this.dialogsSearchAdapter.getInnerListView() : null, 0, new Class[]{HintDialogCell.class}, Theme.dialogs_countPaint, null, null, Theme.key_chats_unreadCounter);
        r11[88] = new ThemeDescription(this.dialogsSearchAdapter != null ? this.dialogsSearchAdapter.getInnerListView() : null, 0, new Class[]{HintDialogCell.class}, Theme.dialogs_countGrayPaint, null, null, Theme.key_chats_unreadCounterMuted);
        r11[89] = new ThemeDescription(this.dialogsSearchAdapter != null ? this.dialogsSearchAdapter.getInnerListView() : null, 0, new Class[]{HintDialogCell.class}, Theme.dialogs_countTextPaint, null, null, Theme.key_chats_unreadCounterText);
        r11[90] = new ThemeDescription(this.dialogsSearchAdapter != null ? this.dialogsSearchAdapter.getInnerListView() : null, 0, new Class[]{HintDialogCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        r11[91] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_inappPlayerBackground);
        r11[92] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{FragmentContextView.class}, new String[]{"playButton"}, null, null, null, Theme.key_inappPlayerPlayPause);
        r11[93] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_inappPlayerTitle);
        r11[94] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_FASTSCROLL, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_inappPlayerPerformer);
        r11[95] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{FragmentContextView.class}, new String[]{"closeButton"}, null, null, null, Theme.key_inappPlayerClose);
        r11[96] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_returnToCallBackground);
        r11[97] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_returnToCallText);
        r11[98] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogBackground);
        r11[99] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogBackgroundGray);
        r11[100] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextBlack);
        r11[101] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextLink);
        r11[102] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogLinkSelection);
        r11[103] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextBlue);
        r11[104] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextBlue2);
        r11[105] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextBlue3);
        r11[106] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextBlue4);
        r11[107] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextRed);
        r11[108] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextGray);
        r11[109] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextGray2);
        r11[110] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextGray3);
        r11[111] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextGray4);
        r11[112] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogIcon);
        r11[113] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogTextHint);
        r11[114] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogInputField);
        r11[115] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogInputFieldActivated);
        r11[116] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogCheckboxSquareBackground);
        r11[117] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogCheckboxSquareCheck);
        r11[118] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogCheckboxSquareUnchecked);
        r11[119] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogCheckboxSquareDisabled);
        r11[120] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogRadioBackground);
        r11[121] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogRadioBackgroundChecked);
        r11[122] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogProgressCircle);
        r11[123] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogButton);
        r11[124] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogButtonSelector);
        r11[125] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogScrollGlow);
        r11[126] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogRoundCheckBox);
        r11[127] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogRoundCheckBoxCheck);
        r11[128] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogBadgeBackground);
        r11[129] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogBadgeText);
        r11[TsExtractor.TS_STREAM_TYPE_HDMV_DTS] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogLineProgress);
        r11[131] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogLineProgressBackground);
        r11[132] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialogGrayLine);
        r11[133] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBar);
        r11[TsExtractor.TS_STREAM_TYPE_SPLICE_INFO] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBarSelector);
        r11[TsExtractor.TS_STREAM_TYPE_E_AC3] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBarTitle);
        r11[136] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBarTop);
        r11[137] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBarSubtitle);
        r11[TsExtractor.TS_STREAM_TYPE_DTS] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_actionBarItems);
        r11[139] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_background);
        r11[140] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_time);
        r11[141] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_progressBackground);
        r11[142] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_progressCachedBackground);
        r11[143] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_progress);
        r11[144] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_placeholder);
        r11[145] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_placeholderBackground);
        r11[146] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_button);
        r11[147] = new ThemeDescription(null, 0, null, null, null, null, Theme.key_player_buttonActive);
        return r11;
    }

    final /* synthetic */ void lambda$getThemeDescriptions$8$DialogsActivity() {
        int count;
        int a;
        View child;
        if (this.listView != null) {
            count = this.listView.getChildCount();
            for (a = 0; a < count; a++) {
                child = this.listView.getChildAt(a);
                if (child instanceof ProfileSearchCell) {
                    ((ProfileSearchCell) child).update(0);
                } else if (child instanceof DialogCell) {
                    ((DialogCell) child).update(0);
                }
            }
        }
        if (this.dialogsSearchAdapter != null) {
            RecyclerListView recyclerListView = this.dialogsSearchAdapter.getInnerListView();
            if (recyclerListView != null) {
                count = recyclerListView.getChildCount();
                for (a = 0; a < count; a++) {
                    child = recyclerListView.getChildAt(a);
                    if (child instanceof HintDialogCell) {
                        ((HintDialogCell) child).update();
                    }
                }
            }
        }
    }

    public boolean onBackPressed() {
        LaunchActivity launchActivity = (LaunchActivity) getParentActivity();
        TurboPasscodeView turboPasscodeView = launchActivity.getTurboPasscodeView();
        TurboPatternView turboPatternView = launchActivity.getTurboPatternView();
        if (turboPasscodeView.isVisible()) {
            turboPasscodeView.hide();
            launchActivity.setAllowOpenDrawer(true);
            return false;
        } else if (turboPatternView.isVisible()) {
            turboPatternView.hide();
            launchActivity.setAllowOpenDrawer(true);
            return false;
        } else if (!TurboConfig.hiddenChatsUnlocked) {
            return true;
        } else {
            lockHidden(null);
            return false;
        }
    }

    private void lockHidden(FloatingActionButton fab) {
        LaunchActivity launchActivity = (LaunchActivity) getParentActivity();
        TurboConfig.setBooleanValue("chat_unlocked", false);
        if (fab != null) {
            fab.setIcon(R.drawable.turbo_tb_lock_close);
        }
        if (this.fam != null) {
            this.fam.collapse();
        }
        if (this.button6 != null) {
            this.button6.setImageResource(R.drawable.turbo_tb_lock_close);
        }
        this.dialogsAdapter.notifyDataSetChanged();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
        ContactsController.getInstance(this.currentAccount).cleanup();
        ContactsController.getInstance(this.currentAccount).readContacts();
        launchActivity.setAllowOpenDrawer(true);
        this.actionBar.hideHiddenDot();
    }

    private void openHidden(final FloatingActionButton fab) {
        final LaunchActivity launchActivity = (LaunchActivity) getParentActivity();
        if (TurboConfig.chatHidePattern.length() > 0) {
            launchActivity.setAllowOpenDrawer(false);
            TurboPatternView turboPatternView = launchActivity.getTurboPatternView();
            turboPatternView.setCheckType(0);
            turboPatternView.onShow();
            turboPatternView.setDelegate(new TurboPatternViewDelegate() {
                public void didAcceptedPattern() {
                    TurboConfig.setBooleanValue("chat_unlocked", true);
                    if (fab != null) {
                        fab.setIcon(R.drawable.turbo_tb_lock_open);
                    }
                    if (DialogsActivity.this.fam != null) {
                        DialogsActivity.this.fam.collapse();
                    }
                    if (DialogsActivity.this.button6 != null) {
                        DialogsActivity.this.button6.setImageResource(R.drawable.turbo_tb_lock_open);
                    }
                    DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                    NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                    NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
                    ContactsController.getInstance(DialogsActivity.this.currentAccount).cleanup();
                    ContactsController.getInstance(DialogsActivity.this.currentAccount).readContacts();
                    launchActivity.setAllowOpenDrawer(true);
                    DialogsActivity.this.actionBar.showHiddenDot();
                }
            });
            return;
        }
        TurboPasscodeView turboPasscodeView = ((LaunchActivity) getParentActivity()).getTurboPasscodeView();
        turboPasscodeView.onShow();
        turboPasscodeView.setDelegate(new TurboPasscodeViewDelegate() {
            public void didAcceptedPassword() {
                TurboConfig.setBooleanValue("chat_unlocked", true);
                if (fab != null) {
                    fab.setIcon(R.drawable.turbo_tb_lock_open);
                }
                if (DialogsActivity.this.fam != null) {
                    DialogsActivity.this.fam.collapse();
                }
                if (DialogsActivity.this.button6 != null) {
                    DialogsActivity.this.button6.setImageResource(R.drawable.turbo_tb_lock_open);
                }
                DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                NotificationCenter.getInstance(DialogsActivity.this.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
                ContactsController.getInstance(DialogsActivity.this.currentAccount).cleanup();
                ContactsController.getInstance(DialogsActivity.this.currentAccount).readContacts();
                launchActivity.setAllowOpenDrawer(true);
                DialogsActivity.this.actionBar.showHiddenDot();
            }
        });
    }

    private void toggleHiddenMode(FloatingActionButton fab) {
        if (TurboConfig.chatHidePasscode.length() == 0) {
            presentFragment(new SetPasscodeActivity(0, 0));
        } else if (TurboConfig.hiddenChatsUnlocked) {
            lockHidden(fab);
        } else {
            openHidden(fab);
        }
    }

    private void showActionbarIcons(int type) {
        if (type == 1) {
            if (this.categorytem != null) {
                this.categorytem.setVisibility(8);
                if (TurboConfig.showCategorizeIcon) {
                    this.categorytem.setVisibility(0);
                }
            }
        } else if (type == 2 && this.categorytem != null) {
            this.categorytem.setVisibility(8);
        }
    }

    private void createTabs(Context context) {
        int[] tabs = TurboConfig$Tabs.getTabsArray();
        int[] visible = TurboConfig$Tabs.getVisibleArray();
        this.tabsView = new TabsView(this, context, true);
        this.tabsView.setBackgroundColor(Theme.getColor(Theme.key_tabsBackground));
        int defaultTab = TurboConfig$Tabs.defaulTab == 8 ? TurboConfig$Tabs.selectedTab : TurboConfig$Tabs.defaulTab;
        this.tabsView.setSelectedTab(defaultTab);
        this.tabsView.setDelegate(new TabsViewDelegate() {
            public void didTabChanged(int tab) {
                DialogsActivity.this.updateTitle(tab);
                if (DialogsActivity.this.dialogsAdapter != null) {
                    DialogsActivity.this.dialogsAdapter.notifyDataSetChanged();
                }
                DialogsActivity.this.listView.scrollToPosition(0);
                DialogsActivity.this.hideFloatingButton(false);
                DialogsActivity.this.prevPosition = 0;
                DialogsActivity.this.prevTop = 0;
                DialogsActivity.this.scrollUpdated = false;
                DialogsActivity.this.hideTabs(false);
                if (TurboConfig$Tabs.tabsEnabled) {
                    int dp2;
                    int dp = TurboConfig$Tabs.moveTabsToBottom ? 0 : DialogsActivity.this.tabsHeight;
                    if (TurboConfig$Tabs.moveTabsToBottom) {
                        dp2 = DialogsActivity.this.tabsHeight;
                    } else {
                        dp2 = 0;
                    }
                    DialogsActivity.this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) dp), AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) dp2));
                }
            }

            public void didPressedMarkedDialogs() {
                ArrayList<TLRPC$TL_dialog> dialogs = DialogsActivity.this.getDialogsArray();
                for (int i = 0; i < dialogs.size(); i++) {
                    TLRPC$TL_dialog dialog = (TLRPC$TL_dialog) dialogs.get(i);
                    long sDialog = dialog.id;
                    if (dialog.unread_count != 0 || dialog.unread_mark) {
                        MessagesController.getInstance(DialogsActivity.this.currentAccount).markMentionsAsRead(sDialog);
                        MessagesController.getInstance(DialogsActivity.this.currentAccount).markDialogAsRead(sDialog, dialog.top_message, dialog.top_message, dialog.last_message_date, false, 0, true);
                    }
                }
                DialogsActivity.this.listView.invalidate();
            }
        });
        for (int a = 0; a < tabs.length; a++) {
            this.tabsView.addTab(tabs[a], visible[a]);
        }
        this.tabsView.didSelectTab(this.tabsView.getPositonOfTab(defaultTab));
        this.contentView.addView(this.tabsView, LayoutHelper.createFrame(-1, (float) this.tabsHeight));
    }

    private void updateTitle(int tab) {
        if (TurboConfig.isSilent) {
            this.actionBar.setTitle(LocaleController.getString("TurboTurnOff", R.string.TurboTurnOff));
        } else if (this.onlySelect) {
            if (this.dialogsAdapter.hasSelectedDialogs()) {
                if (TurboConfig$Tabs.changeTabTitle) {
                    switch (tab) {
                        case 0:
                            this.actionBar.setSubtitle(LocaleController.getString("RobotTab", R.string.RobotTab));
                            return;
                        case 1:
                            this.actionBar.setSubtitle(LocaleController.getString("ChannelTab", R.string.ChannelTab));
                            return;
                        case 2:
                            this.actionBar.setSubtitle(LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab));
                            return;
                        case 3:
                            this.actionBar.setSubtitle(LocaleController.getString("GroupsTab", R.string.GroupsTab));
                            return;
                        case 4:
                            this.actionBar.setSubtitle(LocaleController.getString("ContactTab", R.string.ContactTab));
                            return;
                        case 5:
                            this.actionBar.setSubtitle(LocaleController.getString("FavoriteTab", R.string.FavoriteTab));
                            return;
                        case 6:
                            this.actionBar.setSubtitle(LocaleController.getString("AppName1", R.string.AppName1));
                            return;
                        case 7:
                            this.actionBar.setSubtitle(LocaleController.getString("UnreadTab", R.string.UnreadTab));
                            return;
                        default:
                            return;
                    }
                }
                this.actionBar.setTitle(LocaleController.getString("AppName1", R.string.AppName1));
            } else if (this.dialogsType == 3 && this.selectAlertString == null) {
                this.actionBar.setTitle(LocaleController.getString("ForwardTo", R.string.ForwardTo));
            } else {
                this.actionBar.setTitle(LocaleController.getString("SelectChat", R.string.SelectChat));
            }
        } else if (TurboConfig$Tabs.changeTabTitle) {
            switch (tab) {
                case 0:
                    this.actionBar.setTitle(LocaleController.getString("RobotTab", R.string.RobotTab));
                    return;
                case 1:
                    this.actionBar.setTitle(LocaleController.getString("ChannelTab", R.string.ChannelTab));
                    return;
                case 2:
                    this.actionBar.setTitle(LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab));
                    return;
                case 3:
                    this.actionBar.setTitle(LocaleController.getString("GroupsTab", R.string.GroupsTab));
                    return;
                case 4:
                    this.actionBar.setTitle(LocaleController.getString("ContactTab", R.string.ContactTab));
                    return;
                case 5:
                    this.actionBar.setTitle(LocaleController.getString("FavoriteTab", R.string.FavoriteTab));
                    return;
                case 6:
                    this.actionBar.setTitle(LocaleController.getString("AppName1", R.string.AppName1));
                    return;
                case 7:
                    this.actionBar.setTitle(LocaleController.getString("UnreadTab", R.string.UnreadTab));
                    return;
                default:
                    return;
            }
        } else {
            this.actionBar.setTitle(LocaleController.getString("AppName1", R.string.AppName1));
        }
    }

    public void updateSubTitle(int catId) {
        if (catId == -4) {
            this.actionBar.setSubtitle(LocaleController.getString("TurboMine", R.string.TurboMine));
        } else if (catId == -2) {
            this.actionBar.setSubtitle(LocaleController.getString("TurboUnread", R.string.TurboUnread));
        } else if (catId == -1) {
            this.actionBar.setSubtitle(LocaleController.getString("TurboUnmute", R.string.TurboUnmute));
        } else if (catId == 0) {
            this.actionBar.setSubtitle(LocaleController.getString("TurboMute", R.string.TurboMute));
        } else {
            DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);
            dbHelper.open();
            try {
                this.actionBar.setSubtitle(dbHelper.getCategoryById(catId));
            } finally {
                dbHelper.close();
            }
        }
    }

    private void hideTabs(boolean hide) {
        if (this.tabsHidden != hide) {
            this.tabsHidden = hide;
            int des = 0;
            if (this.tabsHidden) {
                this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f));
                des = (TurboConfig$Tabs.moveTabsToBottom ? -1 : 1) * AndroidUtilities.dp((float) (-this.tabsHeight));
            }
            TurboUtils.animMove(this.tabsView, "translationY", des, 300);
        }
    }

    private void createToolBar(Context context) {
        GradientDrawable toolBarDrawable = new GradientDrawable();
        toolBarDrawable.setColor(Theme.getColor(Theme.key_chats_actionBackground));
        toolBarDrawable.setCornerRadius((float) AndroidUtilities.dp(8.0f));
        this.toolBar = new LinearLayout(context);
        this.toolBar.setBackground(toolBarDrawable);
        this.toolBar.setVisibility(this.onlySelect ? 8 : 0);
        this.toolBar.setGravity(17);
        this.toolBar.setPadding(0, AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f));
        this.toolBar.setWeightSum(7.0f);
        this.contentView.addView(this.toolBar, LayoutHelper.createFrame(-1, 56, 80));
        this.button1 = new ImageView(getParentActivity());
        this.button1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button1.setFocusable(true);
        this.button1.setScaleType(ScaleType.CENTER_INSIDE);
        this.button1.setImageResource(R.drawable.turbo_tb_pencil);
        this.toolBar.addView(this.button1, LayoutHelper.createLinear(0, 35, 1.0f));
        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putBoolean("destroyAfterSelect", true);
                args.putBoolean("only_show_contacts", true);
                DialogsActivity.this.presentFragment(new ContactsActivity(args));
            }
        });
        this.button2 = new ImageView(getParentActivity());
        this.button2.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button2.setFocusable(true);
        this.button2.setScaleType(ScaleType.CENTER_INSIDE);
        this.button2.setImageResource(R.drawable.turbo_tb_settings);
        this.toolBar.addView(this.button2, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogsActivity.this.presentFragment(new TurboSettingsActivity());
            }
        });
        this.button3 = new ImageView(getParentActivity());
        this.button3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button3.setFocusable(true);
        this.button3.setScaleType(ScaleType.CENTER_INSIDE);
        this.button3.setImageResource(R.drawable.turbo_tb_am_on);
        this.toolBar.addView(this.button3, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean z;
                String message;
                String str = "answering_machine";
                if (TurboConfig.answeringMachine) {
                    z = false;
                } else {
                    z = true;
                }
                TurboConfig.setBooleanValue(str, z);
                if (TurboConfig.answeringMachine) {
                    message = LocaleController.getString("AnsweringMachineIsOn", R.string.AnsweringMachineIsOn);
                } else {
                    message = LocaleController.getString("AnsweringMachineIsOff", R.string.AnsweringMachineIsOff);
                }
                TurboUtils.showToast(DialogsActivity.this.getParentActivity(), message, 0);
            }
        });
        this.button4 = new ImageView(getParentActivity());
        this.button4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button4.setFocusable(true);
        this.button4.setScaleType(ScaleType.CENTER_INSIDE);
        this.button4.setImageResource(R.drawable.turbo_tb_online);
        this.toolBar.addView(this.button4, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogsActivity.this.presentFragment(new OnlineContactsActivity(null));
            }
        });
        this.button5 = new ImageView(getParentActivity());
        this.button5.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button5.setFocusable(true);
        this.button5.setScaleType(ScaleType.CENTER_INSIDE);
        this.button5.setImageResource(R.drawable.turbo_tb_dialog_cat);
        this.toolBar.addView(this.button5, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogsActivity.this.showDialog(new DialogsCategoryAlert(DialogsActivity.this.getParentActivity(), DialogsActivity.this.dialogsAdapter, DialogsActivity.this));
            }
        });
        this.button6 = new ImageView(getParentActivity());
        this.button6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button6.setFocusable(true);
        this.button6.setScaleType(ScaleType.CENTER_INSIDE);
        if (TurboConfig.hiddenChatsUnlocked) {
            this.button6.setImageResource(R.drawable.turbo_tb_lock_open);
        } else {
            this.button6.setImageResource(R.drawable.turbo_tb_lock_close);
        }
        this.toolBar.addView(this.button6, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogsActivity.this.toggleHiddenMode(null);
            }
        });
        this.button7 = new ImageView(getParentActivity());
        this.button7.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), Mode.SRC_IN));
        this.button7.setFocusable(true);
        this.button7.setScaleType(ScaleType.CENTER_INSIDE);
        this.button7.setImageResource(R.drawable.turbo_tb_check_all);
        this.toolBar.addView(this.button7, LayoutHelper.createLinear(0, -1, 1.0f));
        this.button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putBoolean("onlySelect", true);
                args.putBoolean("multiSelection", true);
                DialogsActivity.this.presentFragment(new DialogsActivity(args));
            }
        });
    }

    private void createSlidingToolBar(Context context) {
        float f;
        float f2 = 5.0f;
        this.fam = new FloatingActionsMenu(context);
        this.fam.setVisibility(this.onlySelect ? 8 : 0);
        this.fam.setMenuButtonSize();
        SizeNotifierFrameLayout sizeNotifierFrameLayout = this.contentView;
        View view = this.fam;
        int i = (LocaleController.isRTL ? 3 : 5) | 80;
        if (LocaleController.isRTL) {
            f = 5.0f;
        } else {
            f = 0.0f;
        }
        if (LocaleController.isRTL) {
            f2 = 0.0f;
        }
        sizeNotifierFrameLayout.addView(view, LayoutHelper.createFrame(-2, -2.0f, i, f, 0.0f, f2, 2.0f));
        this.fam.getMenuButton().setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                DialogsActivity.this.toggleHiddenMode(null);
                return true;
            }
        });
        String priorityArray = Toolbar.priority;
        if (priorityArray != null) {
            String[] items = priorityArray.substring(1, priorityArray.length() - 1).split(", ");
            for (int a = 0; a < 4; a++) {
                if (items[a].length() > 0) {
                    int tool = Integer.parseInt(items[a]);
                    final FloatingActionButton fab = new FloatingActionButton(context);
                    fab.setColorNormal(Theme.getColor(Theme.key_chats_actionBackground));
                    fab.setColorPressed(Theme.getColor(Theme.key_chats_actionPressedBackground));
                    switch (tool) {
                        case 0:
                            fab.setIcon(R.drawable.floating_pencil);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Bundle args = new Bundle();
                                    args.putBoolean("destroyAfterSelect", true);
                                    args.putBoolean("only_show_contacts", true);
                                    DialogsActivity.this.presentFragment(new ContactsActivity(args));
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 1:
                            fab.setIcon(R.drawable.turbo_tb_check_all);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Bundle args = new Bundle();
                                    args.putBoolean("onlySelect", true);
                                    args.putBoolean("multiSelection", true);
                                    DialogsActivity.this.presentFragment(new DialogsActivity(args));
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 2:
                            fab.setIcon(R.drawable.turbo_tb_dialog_cat);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    DialogsActivity.this.showDialog(new DialogsCategoryAlert(DialogsActivity.this.getParentActivity(), DialogsActivity.this.dialogsAdapter, DialogsActivity.this));
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 3:
                            fab.setIcon(R.drawable.turbo_tb_online);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    DialogsActivity.this.presentFragment(new OnlineContactsActivity(null));
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 4:
                            fab.setIcon(R.drawable.turbo_tb_settings);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    DialogsActivity.this.presentFragment(new TurboSettingsActivity());
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 5:
                            if (TurboConfig.hiddenChatsUnlocked) {
                                fab.setIcon(R.drawable.turbo_tb_lock_open);
                            } else {
                                fab.setIcon(R.drawable.turbo_tb_lock_close);
                            }
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    DialogsActivity.this.toggleHiddenMode(fab);
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 6:
                            fab.setIcon(R.drawable.turbo_tb_power);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    TurboConfig.setBooleanValue("turbo_silent", !TurboConfig.isSilent);
                                    TurboUtils.resetApp();
                                }
                            });
                            break;
                        case 7:
                            fab.setIcon(R.drawable.turbo_tb_profile);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    User user = UserConfig.getInstance(DialogsActivity.this.currentAccount).getCurrentUser();
                                    MessagesController.getInstance(DialogsActivity.this.currentAccount);
                                    MessagesController.openChatOrProfileWith(user, null, DialogsActivity.this, 1, false);
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                        case 8:
                            fab.setIcon(R.drawable.turbo_tb_am_on);
                            fab.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    boolean z;
                                    String message;
                                    String str = "answering_machine";
                                    if (TurboConfig.answeringMachine) {
                                        z = false;
                                    } else {
                                        z = true;
                                    }
                                    TurboConfig.setBooleanValue(str, z);
                                    if (TurboConfig.answeringMachine) {
                                        message = LocaleController.getString("AnsweringMachineIsOn", R.string.AnsweringMachineIsOn);
                                    } else {
                                        message = LocaleController.getString("AnsweringMachineIsOff", R.string.AnsweringMachineIsOff);
                                    }
                                    TurboUtils.showToast(DialogsActivity.this.getParentActivity(), message, 0);
                                    DialogsActivity.this.fam.collapse();
                                }
                            });
                            break;
                    }
                    this.fam.addButton(fab);
                }
            }
        }
    }

    private void updateLayout() {
        LayoutParams fButtonsParams;
        LayoutParams famParams;
        LayoutParams toolbarParams;
        if (!TurboConfig$Tabs.tabsEnabled) {
            this.tabsView.setVisibility(8);
            this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f));
            if (!Toolbar.enabled) {
                fButtonsParams = (LayoutParams) this.floatingButton.getLayoutParams();
                fButtonsParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(14.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
                this.floatingButton.setLayoutParams(fButtonsParams);
            } else if (Toolbar.type == 0) {
                famParams = (LayoutParams) this.fam.getLayoutParams();
                famParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(5.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(5.0f), AndroidUtilities.dp(2.0f));
                this.fam.setLayoutParams(famParams);
            } else if (Toolbar.type == 1) {
                toolbarParams = (LayoutParams) this.toolBar.getLayoutParams();
                toolbarParams.setMargins(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(14.0f));
                this.toolBar.setLayoutParams(toolbarParams);
            }
        } else if (TurboConfig$Tabs.moveTabsToBottom) {
            this.tabsView.setVisibility(0);
            tabsParams = (LayoutParams) this.tabsView.getLayoutParams();
            tabsParams.gravity = 80;
            this.tabsView.setLayoutParams(tabsParams);
            this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) this.tabsHeight));
            if (!Toolbar.enabled) {
                fButtonsParams = (LayoutParams) this.floatingButton.getLayoutParams();
                fButtonsParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(14.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(14.0f), AndroidUtilities.dp((float) (this.tabsHeight + 14)));
                this.floatingButton.setLayoutParams(fButtonsParams);
            } else if (Toolbar.type == 0) {
                famParams = (LayoutParams) this.fam.getLayoutParams();
                famParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(5.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(5.0f), AndroidUtilities.dp((float) (this.tabsHeight + 2)));
                this.fam.setLayoutParams(famParams);
            } else if (Toolbar.type == 1) {
                toolbarParams = (LayoutParams) this.toolBar.getLayoutParams();
                toolbarParams.setMargins(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp((float) (this.tabsHeight + 14)));
                this.toolBar.setLayoutParams(toolbarParams);
            }
        } else {
            this.tabsView.setVisibility(0);
            tabsParams = (LayoutParams) this.tabsView.getLayoutParams();
            tabsParams.gravity = 48;
            this.tabsView.setLayoutParams(tabsParams);
            this.listView.setPadding(AndroidUtilities.dp(0.0f), AndroidUtilities.dp((float) this.tabsHeight), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f));
            if (!Toolbar.enabled) {
                fButtonsParams = (LayoutParams) this.floatingButton.getLayoutParams();
                fButtonsParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(14.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
                this.floatingButton.setLayoutParams(fButtonsParams);
            } else if (Toolbar.type == 0) {
                famParams = (LayoutParams) this.fam.getLayoutParams();
                famParams.setMargins(LocaleController.isRTL ? AndroidUtilities.dp(5.0f) : AndroidUtilities.dp(0.0f), AndroidUtilities.dp(0.0f), LocaleController.isRTL ? AndroidUtilities.dp(0.0f) : AndroidUtilities.dp(5.0f), AndroidUtilities.dp(2.0f));
                this.fam.setLayoutParams(famParams);
            } else if (Toolbar.type == 1) {
                toolbarParams = (LayoutParams) this.toolBar.getLayoutParams();
                toolbarParams.setMargins(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(14.0f));
                this.toolBar.setLayoutParams(toolbarParams);
            }
        }
    }

    private void showToast(int mask) {
        String toastMessage = "";
        if ((mask & 64) != 0) {
            Iterator myVeryOwnIterator = MessagesController.getInstance(this.currentAccount).turboPrintingStrings.keySet().iterator();
            while (myVeryOwnIterator.hasNext()) {
                toastMessage = toastMessage + ((CharSequence) MessagesController.getInstance(this.currentAccount).turboPrintingStrings.get((Long) myVeryOwnIterator.next()));
                if (myVeryOwnIterator.hasNext()) {
                    toastMessage = toastMessage + "\n";
                }
            }
            if (!toastMessage.equals("")) {
                Toast toast = Toast.makeText(ApplicationLoader.applicationContext, toastMessage, 0);
                if (toast != null) {
                    int gravity = 48;
                    if (TurboConfig$Toast.toBottom) {
                        gravity = 80;
                    }
                    int hGravity = 1;
                    if (TurboConfig$Toast.position != 1) {
                        hGravity = TurboConfig$Toast.position == 0 ? 3 : 5;
                    }
                    toast.setGravity(gravity | hGravity, 0, AndroidUtilities.dp((float) TurboConfig$Toast.margin));
                    LinearLayout toastLayout = (LinearLayout) toast.getView();
                    toastLayout.setBackgroundColor(0);
                    toastLayout.setPadding(AndroidUtilities.dp(1.0f), 0, AndroidUtilities.dp(1.0f), 0);
                    if (toastLayout.getChildAt(0) instanceof TextView) {
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                        toastTV.setTextSize(1, (float) TurboConfig$Toast.textSize);
                        toastTV.setBackgroundColor(0);
                        toastTV.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp((float) TurboConfig$Toast.padding), AndroidUtilities.dp(1.0f), AndroidUtilities.dp((float) TurboConfig$Toast.padding));
                    }
                    GradientDrawable toastDrawable = new GradientDrawable();
                    toastDrawable.setCornerRadius((float) AndroidUtilities.dp(4.0f));
                    toastDrawable.setColor(-2013265920);
                    toastLayout.setBackgroundDrawable(toastDrawable);
                }
                toast.show();
            }
        }
    }

    private void onSwipe(MotionEvent event) {
        if (!this.searching || !this.searchWas) {
            switch (event.getAction()) {
                case 0:
                    this.downX = event.getX();
                    this.downY = event.getY();
                    return;
                case 1:
                    if (this.fam != null) {
                        this.fam.collapse();
                    }
                    this.upX = event.getX();
                    this.upY = event.getY();
                    if (TurboConfig$Tabs.tabsEnabled) {
                        float deltaX = this.downX - this.upX;
                        float deltaY = this.downY - this.upY;
                        if (Math.abs(deltaX) > 80.0f && Math.abs(deltaY) < 50.0f) {
                            int position;
                            if (deltaX < 0.0f) {
                                position = this.tabsView.getSelectedTabPosition() - 1;
                                if (position >= 0) {
                                    this.tabsView.didSelectTab(position);
                                } else {
                                    this.tabsView.didSelectTab(this.tabsView.getTabCount() - 1);
                                }
                            }
                            if (deltaX > 0.0f) {
                                position = this.tabsView.getSelectedTabPosition() + 1;
                                if (position < this.tabsView.getTabCount()) {
                                    this.tabsView.didSelectTab(position);
                                } else {
                                    this.tabsView.didSelectTab(0);
                                }
                            }
                            if (this.dialogsAdapter != null) {
                                this.dialogsAdapter.notifyDataSetChanged();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void toggleMute(int selectedDialog, boolean muted) {
        TLRPC$TL_dialog dlg;
        if (muted) {
            Editor editor = MessagesController.getNotificationsSettings(this.currentAccount).edit();
            editor.putInt("notify2_" + selectedDialog, 0);
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags((long) selectedDialog, 0);
            editor.commit();
            dlg = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get((long) selectedDialog);
            if (dlg != null) {
                dlg.notify_settings = new TLRPC$TL_peerNotifySettings();
            }
            NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings((long) selectedDialog);
            return;
        }
        editor = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        editor.putInt("notify2_" + selectedDialog, 2);
        MessagesStorage.getInstance(this.currentAccount).setDialogFlags((long) selectedDialog, 1);
        editor.commit();
        dlg = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get((long) selectedDialog);
        if (dlg != null) {
            dlg.notify_settings = new TLRPC$TL_peerNotifySettings();
            dlg.notify_settings.mute_until = Integer.MAX_VALUE;
        }
        NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings((long) selectedDialog);
        NotificationsController.getInstance(this.currentAccount).removeNotificationsForDialog((long) selectedDialog);
    }
}
