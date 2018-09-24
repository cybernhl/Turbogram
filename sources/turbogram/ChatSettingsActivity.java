package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.WallpapersActivity;
import turbogram.Components.TurboPasscodeView;
import turbogram.Components.TurboPatternView;
import turbogram.Components.TurboPatternView.TurboPatternViewDelegate;
import turbogram.Database.DBHelper;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class ChatSettingsActivity extends BaseFragment {
    private int bgDesRow;
    private int bgRow;
    private FrameLayout contentView;
    private long dialog_id = 0;
    private boolean fromChatScreen = true;
    private int hideDesRow;
    private int hideRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int lockDesRow;
    private int lockRow;
    private int lockTypingDesRow;
    private int lockTypingRow;
    private int resetDesRow;
    private int resetRow;
    private int rowCount = 0;

    /* renamed from: turbogram.ChatSettingsActivity$1 */
    class C22391 extends ActionBarMenuOnItemClick {
        C22391() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ChatSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.ChatSettingsActivity$3 */
    class C22433 implements OnItemClickListener {

        /* renamed from: turbogram.ChatSettingsActivity$3$2 */
        class C22422 implements OnClickListener {
            C22422() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                ChatSettingsActivity.this.removeCustomWallpaper();
                TurboConfig.removeValue("lock_key" + ChatSettingsActivity.this.dialog_id);
                TurboConfig.removeValue("lock_chat" + ChatSettingsActivity.this.dialog_id);
                TurboConfig.removeValue("hide_" + ChatSettingsActivity.this.dialog_id);
                TurboConfig.removeValue("mobileDataDownloadMask" + ChatSettingsActivity.this.dialog_id);
                TurboConfig.removeValue("wifiDownloadMask" + ChatSettingsActivity.this.dialog_id);
                if (ChatSettingsActivity.this.listView != null) {
                    ChatSettingsActivity.this.listView.invalidateViews();
                }
                TurboUtils.showToast(ChatSettingsActivity.this.getParentActivity(), LocaleController.getString("Done", R.string.Done), 1);
            }
        }

        C22433() {
        }

        public void onItemClick(View view, final int position) {
            boolean z = true;
            if (position == ChatSettingsActivity.this.bgRow) {
                Builder builder = new Builder(ChatSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("TurboChatBG", R.string.TurboChatBG));
                builder.setItems(new CharSequence[]{LocaleController.getString("Default", R.string.Default), LocaleController.getString("TurboChatBGSelect", R.string.TurboChatBGSelect)}, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            ChatSettingsActivity.this.removeCustomWallpaper();
                        } else if (which == 1) {
                            WallpapersActivity wallpapersActivity = new WallpapersActivity();
                            wallpapersActivity.setCustomChat(ChatSettingsActivity.this.dialog_id);
                            ChatSettingsActivity.this.presentFragment(wallpapersActivity);
                        }
                        if (ChatSettingsActivity.this.listAdapter != null) {
                            ChatSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                        NotificationCenter.getInstance(ChatSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
                    }
                });
                ChatSettingsActivity.this.showDialog(builder.create());
            } else if (position == ChatSettingsActivity.this.resetRow) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ChatSettingsActivity.this.getParentActivity());
                builder2.setTitle(LocaleController.getString("AppName", R.string.AppName));
                builder2.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                builder2.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C22422());
                builder2.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                ChatSettingsActivity.this.showDialog(builder2.create());
            } else if (position == ChatSettingsActivity.this.lockTypingRow) {
                keyLocked = TurboConfig.containValue("lock_key" + ChatSettingsActivity.this.dialog_id);
                if (keyLocked) {
                    TurboConfig.removeValue("lock_key" + ChatSettingsActivity.this.dialog_id);
                } else {
                    TurboConfig.setIntValue("lock_key" + ChatSettingsActivity.this.dialog_id, (int) ChatSettingsActivity.this.dialog_id);
                }
                if (view instanceof TextCheckCell) {
                    r11 = (TextCheckCell) view;
                    if (keyLocked) {
                        z = false;
                    }
                    r11.setChecked(z);
                }
                NotificationCenter.getInstance(ChatSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUnlockChat, new Object[0]);
            } else if (position == ChatSettingsActivity.this.lockRow) {
                if (TurboConfig.chatLockPasscode.length() == 0) {
                    ChatSettingsActivity.this.presentFragment(new SetPasscodeActivity(0, 1));
                    return;
                }
                keyLocked = TurboConfig.containValue("lock_chat" + ChatSettingsActivity.this.dialog_id);
                if (keyLocked) {
                    TurboConfig.removeValue("lock_chat" + ChatSettingsActivity.this.dialog_id);
                } else {
                    TurboConfig.setIntValue("lock_chat" + ChatSettingsActivity.this.dialog_id, (int) ChatSettingsActivity.this.dialog_id);
                }
                if (view instanceof TextCheckCell) {
                    r11 = (TextCheckCell) view;
                    if (keyLocked) {
                        z = false;
                    }
                    r11.setChecked(z);
                }
            } else if (position != ChatSettingsActivity.this.hideRow) {
            } else {
                if (TurboConfig.chatHidePasscode.length() == 0) {
                    ChatSettingsActivity.this.presentFragment(new SetPasscodeActivity(0, 0));
                    return;
                }
                boolean isHide = TurboConfig.containValue("hide_" + ChatSettingsActivity.this.dialog_id);
                if (isHide) {
                    TurboConfig.removeValue("hide_" + ChatSettingsActivity.this.dialog_id);
                } else {
                    TurboConfig.setIntValue("hide_" + ChatSettingsActivity.this.dialog_id, (int) ChatSettingsActivity.this.dialog_id);
                    DataQuery.getInstance(ChatSettingsActivity.this.currentAccount).removePeer((int) ChatSettingsActivity.this.dialog_id);
                }
                if (view instanceof TextCheckCell) {
                    r11 = (TextCheckCell) view;
                    if (isHide) {
                        z = false;
                    }
                    r11.setChecked(z);
                }
                NotificationCenter.getInstance(ChatSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.reloadHints, new Object[0]);
                NotificationCenter.getInstance(ChatSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.needReloadRecentDialogsSearch, new Object[0]);
                ContactsController.getInstance(ChatSettingsActivity.this.currentAccount).cleanup();
                ContactsController.getInstance(ChatSettingsActivity.this.currentAccount).readContacts();
            }
        }
    }

    /* renamed from: turbogram.ChatSettingsActivity$4 */
    class C22444 implements TurboPatternViewDelegate {
        C22444() {
        }

        public void didAcceptedPattern() {
            ChatSettingsActivity.this.swipeBackEnabled = true;
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            int position = holder.getAdapterPosition();
            return position == ChatSettingsActivity.this.bgRow || position == ChatSettingsActivity.this.resetRow || position == ChatSettingsActivity.this.lockTypingRow || position == ChatSettingsActivity.this.lockRow || position == ChatSettingsActivity.this.hideRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextDetailSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 3:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TextDetailSettingsCell detailSettingsCell = holder.itemView;
                    if (position == ChatSettingsActivity.this.bgRow) {
                        String text = "";
                        if (TurboConfig.containValue("selectedBackground" + ChatSettingsActivity.this.dialog_id)) {
                            text = LocaleController.getString("TurboChatBGCustom", R.string.TurboChatBGCustom);
                        } else {
                            text = LocaleController.getString("Default", R.string.Default);
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("TurboChatBG", R.string.TurboChatBG), String.valueOf(text), false);
                        return;
                    }
                    return;
                case 1:
                    TextSettingsCell settingsCell = holder.itemView;
                    if (position == ChatSettingsActivity.this.resetRow) {
                        settingsCell.setText(LocaleController.getString("ResetChatSettings", R.string.ResetChatSettings), false);
                        return;
                    }
                    return;
                case 2:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == ChatSettingsActivity.this.lockTypingRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TurboLockSending", R.string.TurboLockSending), TurboConfig.containValue("lock_key" + ChatSettingsActivity.this.dialog_id), false);
                        return;
                    } else if (position == ChatSettingsActivity.this.lockRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TurboLockChat", R.string.TurboLockChat), TurboConfig.containValue("lock_chat" + ChatSettingsActivity.this.dialog_id), false);
                        return;
                    } else if (position == ChatSettingsActivity.this.hideRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("HideChat", R.string.HideChat), TurboConfig.containValue("hide_" + ChatSettingsActivity.this.dialog_id), false);
                        return;
                    } else {
                        return;
                    }
                case 3:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == ChatSettingsActivity.this.bgDesRow) {
                        privacyCell.setText(LocaleController.getString("TurboChatBGDes", R.string.TurboChatBGDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == ChatSettingsActivity.this.lockTypingDesRow) {
                        privacyCell.setText(LocaleController.getString("TurboLockSendingDes", R.string.TurboLockSendingDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == ChatSettingsActivity.this.lockDesRow) {
                        privacyCell.setText(LocaleController.getString("TurboLockChatDes", R.string.TurboLockChatDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == ChatSettingsActivity.this.hideDesRow) {
                        privacyCell.setText(LocaleController.getString("HiddenChatsDescription", R.string.HiddenChatsDescription));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == ChatSettingsActivity.this.resetDesRow) {
                        privacyCell.setText(LocaleController.getString("ChatUndoSettings", R.string.ChatUndoSettings));
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
            return ChatSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == ChatSettingsActivity.this.bgRow) {
                return 0;
            }
            if (position == ChatSettingsActivity.this.resetRow) {
                return 1;
            }
            if (position == ChatSettingsActivity.this.lockTypingRow || position == ChatSettingsActivity.this.lockRow || position == ChatSettingsActivity.this.hideRow) {
                return 2;
            }
            return 3;
        }
    }

    public ChatSettingsActivity(Bundle args) {
        super(args);
    }

    public boolean onFragmentCreate() {
        if (getArguments() != null) {
            this.dialog_id = this.arguments.getLong("dialog_id", 0);
            this.fromChatScreen = this.arguments.getBoolean("from_chat", true);
        }
        TLRPC$Chat currentChat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf((int) (-this.dialog_id)));
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.bgRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.bgDesRow = i;
        if (!ChatObject.isChannel(currentChat) || ChatObject.canPost(currentChat) || currentChat.megagroup) {
            i = this.rowCount;
            this.rowCount = i + 1;
            this.lockTypingRow = i;
            i = this.rowCount;
            this.rowCount = i + 1;
            this.lockTypingDesRow = i;
        }
        i = this.rowCount;
        this.rowCount = i + 1;
        this.lockRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.lockDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hideRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hideDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.resetRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.resetDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setAddToContainer(false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TurboChatSettings", R.string.TurboChatSettings));
        this.actionBar.setActionBarMenuOnItemClick(new C22391());
        this.fragmentView = new FrameLayout(context) {
            protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
                boolean result = super.drawChild(canvas, child, drawingTime);
                if (child == ChatSettingsActivity.this.actionBar && ChatSettingsActivity.this.parentLayout != null) {
                    ChatSettingsActivity.this.parentLayout.drawHeaderShadow(canvas, ChatSettingsActivity.this.actionBar.getVisibility() == 0 ? ChatSettingsActivity.this.actionBar.getMeasuredHeight() : 0);
                }
                return result;
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                setMeasuredDimension(widthSize, heightSize);
                heightSize -= getPaddingTop();
                measureChildWithMargins(ChatSettingsActivity.this.actionBar, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int actionBarHeight = ChatSettingsActivity.this.actionBar.getMeasuredHeight();
                if (ChatSettingsActivity.this.actionBar.getVisibility() == 0) {
                    heightSize -= actionBarHeight;
                }
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    if (!(child == null || child.getVisibility() == 8 || child == ChatSettingsActivity.this.actionBar)) {
                        if (child == ChatSettingsActivity.this.listView) {
                            child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(Math.max(AndroidUtilities.dp(10.0f), heightSize), 1073741824));
                        } else {
                            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                        }
                    }
                }
            }

            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int count = getChildCount();
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
                                childTop = ((((b - t) - height) / 2) + lp.topMargin) - lp.bottomMargin;
                                break;
                            case 48:
                                childTop = lp.topMargin + getPaddingTop();
                                if (child != ChatSettingsActivity.this.actionBar && ChatSettingsActivity.this.actionBar.getVisibility() == 0) {
                                    childTop += ChatSettingsActivity.this.actionBar.getMeasuredHeight();
                                    break;
                                }
                            case 80:
                                childTop = ((b - t) - height) - lp.bottomMargin;
                                break;
                            default:
                                childTop = lp.topMargin;
                                break;
                        }
                        if (child == ChatSettingsActivity.this.actionBar) {
                            childTop -= getPaddingTop();
                        }
                        child.layout(childLeft, childTop, childLeft + width, childTop + height);
                    }
                }
            }
        };
        this.contentView = (FrameLayout) this.fragmentView;
        this.contentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.contentView.addView(this.actionBar);
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C22433());
        if (TurboConfig.chatLockPasscode.length() > 0 && TurboConfig.containValue("lock_chat" + this.dialog_id) && !this.fromChatScreen) {
            if (TurboConfig.chatLockPattern.length() > 0) {
                this.swipeBackEnabled = false;
                TurboPatternView turboPatternView = new TurboPatternView(context, 1);
                turboPatternView.onShow();
                this.contentView.addView(turboPatternView, LayoutHelper.createFrame(-1, -1, 17));
                turboPatternView.setDelegate(new C22444());
            } else {
                TurboPasscodeView turboPasscodeView = new TurboPasscodeView(context, 1);
                turboPasscodeView.onShow();
                this.contentView.addView(turboPasscodeView, LayoutHelper.createFrame(-1, -1, 17));
            }
        }
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private void removeCustomWallpaper() {
        TurboConfig.removeValue("selectedBackground" + this.dialog_id);
        DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);
        dbHelper.open();
        try {
            dbHelper.deleteWallpaper(this.dialog_id);
        } finally {
            dbHelper.close();
        }
    }
}
