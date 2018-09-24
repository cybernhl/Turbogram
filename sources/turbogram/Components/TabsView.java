package turbogram.Components;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Cells.TabsViewCell;
import turbogram.Database.DBHelper;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboConfig$Tabs;

public class TabsView extends LinearLayout {
    private TabsViewCell allCell;
    private float animateTabXTo = 0.0f;
    private TabsViewCell botCell;
    private TabsViewCell channelCell;
    private TabsViewCell contactCell;
    private Context context;
    private int currentAccount = UserConfig.selectedAccount;
    private TabsViewDelegate delegate;
    private TabsViewCell favoriteCell;
    private BaseFragment fragment;
    private TabsViewCell groupCell;
    private DecelerateInterpolator interpolator;
    private boolean needCounter;
    private Paint paint = new Paint();
    private HashMap<Integer, Integer> positionOfTab = new HashMap();
    private int selectedIconColor;
    private int selectedTab = 0;
    private long startAnimationTime = 0;
    private float startAnimationX = 0.0f;
    private TabsViewCell supergroupCell;
    private int tabCount = 0;
    private HashMap<Integer, Integer> tabInPosition = new HashMap();
    private float tabWidth = 0.0f;
    private float tabX = 0.0f;
    private HashMap<Integer, TabsViewCell> tabs = new HashMap();
    private long totalAnimationDiff = 0;
    private TabsViewCell unreadCell;

    public interface TabsViewDelegate {
        void didPressedMarkedDialogs();

        void didTabChanged(int i);
    }

    public TabsView(BaseFragment fragment, Context context, boolean needCounter) {
        super(context);
        this.fragment = fragment;
        this.context = context;
        this.needCounter = needCounter;
        setOrientation(0);
        this.selectedIconColor = Theme.getColor(Theme.key_tabSelectedIcon);
        this.paint.setColor(this.selectedIconColor);
        setWillNotDraw(false);
        this.interpolator = new DecelerateInterpolator();
    }

    public void addTab(int tab, int visibility) {
        if (!(TurboConfig$Tabs.mergeGroups && tab == 2) && visibility == 0) {
            final int position = this.tabCount;
            TabsViewCell cell = null;
            switch (tab) {
                case 0:
                    this.botCell = new TabsViewCell(this.context, this.needCounter);
                    this.botCell.setData(Theme.tabs_botDrawable);
                    cell = this.botCell;
                    break;
                case 1:
                    this.channelCell = new TabsViewCell(this.context, this.needCounter);
                    this.channelCell.setData(Theme.tabs_channelDrawable);
                    cell = this.channelCell;
                    break;
                case 2:
                    this.supergroupCell = new TabsViewCell(this.context, this.needCounter);
                    this.supergroupCell.setData(Theme.tabs_superGroupDrawable);
                    cell = this.supergroupCell;
                    break;
                case 3:
                    this.groupCell = new TabsViewCell(this.context, this.needCounter);
                    this.groupCell.setData(Theme.tabs_groupDrawable);
                    cell = this.groupCell;
                    break;
                case 4:
                    this.contactCell = new TabsViewCell(this.context, this.needCounter);
                    this.contactCell.setData(Theme.tabs_contactDrawable);
                    cell = this.contactCell;
                    break;
                case 5:
                    this.favoriteCell = new TabsViewCell(this.context, this.needCounter);
                    this.favoriteCell.setData(Theme.tabs_favoriteDrawable);
                    cell = this.favoriteCell;
                    break;
                case 6:
                    this.allCell = new TabsViewCell(this.context, this.needCounter);
                    this.allCell.setData(Theme.tabs_allDrawable);
                    cell = this.allCell;
                    break;
                case 7:
                    this.unreadCell = new TabsViewCell(this.context, this.needCounter);
                    this.unreadCell.setData(Theme.tabs_unreadDrawable);
                    cell = this.unreadCell;
                    break;
            }
            if (tab == this.selectedTab) {
                cell.select();
            }
            addView(cell, LayoutHelper.createLinear(0, -1, 1.0f));
            cell.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    TabsView.this.tabOnClick(position);
                }
            });
            this.tabs.put(Integer.valueOf(tab), cell);
            this.tabInPosition.put(Integer.valueOf(position), Integer.valueOf(tab));
            this.positionOfTab.put(Integer.valueOf(tab), Integer.valueOf(position));
            this.tabCount++;
            setWeightSum((float) this.tabCount);
        }
    }

    private void tabOnClick(final int position) {
        final int tab = ((Integer) this.tabInPosition.get(Integer.valueOf(position))).intValue();
        if (tab != TurboConfig$Tabs.selectedTab) {
            didSelectTab(position);
        } else if (this.needCounter) {
            Builder builder = new Builder(this.context);
            builder.setItems(new CharSequence[]{LocaleController.getString("DisableTab", R.string.DisableTab), LocaleController.getString("SetTabAsDefault", R.string.SetTabAsDefault), LocaleController.getString("MarkChatsAsRead", R.string.MarkChatsAsRead)}, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new AlertDialog.Builder(TabsView.this.context).setTitle(LocaleController.getString("AppName", R.string.AppName));
                    switch (which) {
                        case 0:
                            int[] tabsArray = TurboConfig$Tabs.getTabsArray();
                            int[] visibleArray = TurboConfig$Tabs.getVisibleArray();
                            for (int a = 0; a < tabsArray.length; a++) {
                                if (tabsArray[a] == tab) {
                                    visibleArray[a] = -1;
                                }
                            }
                            TurboConfig$Tabs.setStringValue("tabs_priority", Arrays.toString(tabsArray));
                            TurboConfig$Tabs.setStringValue("tabs_visibility", Arrays.toString(visibleArray));
                            TabsView.this.delegate.didTabChanged(position);
                            NotificationCenter.getInstance(TabsView.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(4));
                            return;
                        case 1:
                            TurboConfig$Tabs.setIntValue("default_tab", tab);
                            return;
                        case 2:
                            TabsView.this.delegate.didPressedMarkedDialogs();
                            return;
                        default:
                            return;
                    }
                }
            });
            this.fragment.showDialog(builder.create());
        }
    }

    public void didSelectTab(int position) {
        int tab = ((Integer) this.tabInPosition.get(Integer.valueOf(position))).intValue();
        if (this.selectedTab != tab) {
            setSelectedTab(tab);
            animateToTab(position);
            for (Integer intValue : this.tabs.keySet()) {
                ((TabsViewCell) this.tabs.get(Integer.valueOf(intValue.intValue()))).unselect();
            }
            ((TabsViewCell) this.tabs.get(Integer.valueOf(tab))).select();
            this.delegate.didTabChanged(tab);
        }
    }

    public void setSelectedTab(int tab) {
        this.selectedTab = tab;
        TurboConfig$Tabs.setIntValue("selected_tab", this.selectedTab);
    }

    public int getSelectedTabPosition() {
        return ((Integer) this.positionOfTab.get(Integer.valueOf(this.selectedTab))).intValue();
    }

    public int getPositonOfTab(int tab) {
        if (this.positionOfTab.containsKey(Integer.valueOf(tab))) {
            return ((Integer) this.positionOfTab.get(Integer.valueOf(tab))).intValue();
        }
        return ((Integer) this.positionOfTab.get(Integer.valueOf(TurboConfig$Tabs.getFirstActiveTab()))).intValue();
    }

    public int getTabCount() {
        return this.tabCount;
    }

    public Drawable getSelectedTabDrawable() {
        switch (TurboConfig$Tabs.selectedTab) {
            case 0:
                return Theme.tabs_botDrawable;
            case 1:
                return Theme.tabs_channelDrawable;
            case 2:
                return Theme.tabs_superGroupDrawable;
            case 3:
                return Theme.tabs_groupDrawable;
            case 4:
                return Theme.tabs_contactDrawable;
            case 5:
                return Theme.tabs_favoriteDrawable;
            case 6:
                return Theme.tabs_allDrawable;
            default:
                return Theme.tabs_unreadDrawable;
        }
    }

    public Drawable[] getTabDrawables() {
        Drawable[] drawables = new Drawable[]{Theme.tabs_botDrawable, Theme.tabs_channelDrawable, Theme.tabs_superGroupDrawable, Theme.tabs_groupDrawable, Theme.tabs_contactDrawable, Theme.tabs_favoriteDrawable, Theme.tabs_allDrawable, Theme.tabs_unreadDrawable};
        ArrayList<Drawable> list = new ArrayList();
        Drawable selectedTabDrawable = getSelectedTabDrawable();
        for (int i = 0; i < drawables.length; i++) {
            if (drawables[i] != selectedTabDrawable) {
                list.add(drawables[i]);
            }
        }
        list.toArray(drawables);
        return drawables;
    }

    public Paint getSelectedTabPaint() {
        return this.paint;
    }

    private void animateToTab(int tab) {
        this.animateTabXTo = ((float) tab) * this.tabWidth;
        this.startAnimationX = this.tabX;
        this.totalAnimationDiff = 0;
        this.startAnimationTime = System.currentTimeMillis();
        invalidate();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.tabWidth = ((float) (r - l)) / ((float) this.tabCount);
        float intValue = ((float) ((Integer) this.positionOfTab.get(Integer.valueOf(this.selectedTab))).intValue()) * this.tabWidth;
        this.tabX = intValue;
        this.animateTabXTo = intValue;
    }

    protected void onDraw(Canvas canvas) {
        if (this.tabX != this.animateTabXTo) {
            long dt = System.currentTimeMillis() - this.startAnimationTime;
            this.startAnimationTime = System.currentTimeMillis();
            this.totalAnimationDiff += dt;
            if (this.totalAnimationDiff > 1) {
                this.totalAnimationDiff = 1;
                this.tabX = this.animateTabXTo;
            } else {
                this.tabX = this.startAnimationX + (this.interpolator.getInterpolation(((float) this.totalAnimationDiff) / 1.0f) * (this.animateTabXTo - this.startAnimationX));
                invalidate();
            }
        }
        Canvas canvas2 = canvas;
        canvas2.drawRect(this.tabX, (float) (getHeight() - AndroidUtilities.dp(4.0f)), this.tabWidth + this.tabX, (float) getHeight(), this.paint);
    }

    public void setDelegate(TabsViewDelegate tabsViewDelegate) {
        this.delegate = tabsViewDelegate;
    }

    public void updateCounters() {
        int i;
        TLRPC$TL_dialog dialog;
        TLRPC$Chat chat;
        int botCount;
        int channelCount;
        int sgroupCount;
        int groupCount;
        int contactCount;
        int favoriteCount;
        int allCount;
        ArrayList<TLRPC$TL_dialog> dialogs = new ArrayList();
        int currentCat = TurboConfig$BG.currentCat;
        if (currentCat == -4) {
            for (i = 0; i < MessagesController.getInstance(this.currentAccount).dialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs.get(i);
                chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-((int) dialog.id)));
                if (chat != null && (chat.creator || ChatObject.canPost(chat))) {
                    dialogs.add(dialog);
                }
            }
        } else if (currentCat == -3) {
            dialogs.addAll(MessagesController.getInstance(UserConfig.selectedAccount).dialogs);
        } else if (currentCat == -2) {
            for (i = 0; i < MessagesController.getInstance(this.currentAccount).dialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs.get(i);
                if (dialog.unread_count > 0) {
                    dialogs.add(dialog);
                }
            }
        } else if (currentCat == -1) {
            for (i = 0; i < MessagesController.getInstance(this.currentAccount).dialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs.get(i);
                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                    dialogs.add(dialog);
                }
            }
        } else if (currentCat == 0) {
            for (i = 0; i < MessagesController.getInstance(this.currentAccount).dialogs.size(); i++) {
                dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs.get(i);
                if (MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                    dialogs.add(dialog);
                }
            }
        } else {
            ArrayList<Integer> ds = new ArrayList();
            DBHelper dBHelper = new DBHelper(ApplicationLoader.applicationContext);
            dBHelper.open();
            try {
                ds.addAll(dBHelper.getDialogsByCid(currentCat));
                for (i = 0; i < ds.size(); i++) {
                    dialogs.add((TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get((long) ((Integer) ds.get(i)).intValue()));
                }
            } finally {
                dBHelper.close();
            }
        }
        boolean onlyNotMuted = TurboConfig$Tabs.countOnlyNotMuted;
        boolean botHasUnmute = false;
        boolean chHasUnmute = false;
        boolean sgHasUnmute = false;
        boolean gHasUnmute = false;
        boolean cHasUnmute = false;
        boolean favHasUnmute = false;
        boolean allHasUnmute = false;
        int botDialogCount = 0;
        int channelDialogCount = 0;
        int sgroupDialogCount = 0;
        int groupDialogCount = 0;
        int contactDialogCount = 0;
        int favoriteDialogCount = 0;
        int allDialogCount = 0;
        int botMessageCount = 0;
        int channelMessageCount = 0;
        int sgroupMessageCount = 0;
        int groupMessageCount = 0;
        int contactMessageCount = 0;
        int favoriteMessageCount = 0;
        int allMessageCount = 0;
        if (TurboConfig$Tabs.tabsEnabled && TurboConfig$Tabs.tabsCounter) {
            int lower_id;
            int high_id;
            boolean isChat;
            User user;
            boolean isBot;
            if (TurboConfig.hiddenChatsUnlocked) {
                for (i = 0; i < dialogs.size(); i++) {
                    dialog = (TLRPC$TL_dialog) dialogs.get(i);
                    if (TurboConfig.containValue("hide_" + dialog.id) && dialog.unread_count > 0) {
                        lower_id = (int) dialog.id;
                        high_id = (int) (dialog.id >> 32);
                        if (onlyNotMuted) {
                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                allMessageCount += dialog.unread_count;
                                allDialogCount++;
                                allHasUnmute = true;
                            }
                        } else {
                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                allHasUnmute = true;
                            }
                            allMessageCount += dialog.unread_count;
                            allDialogCount++;
                        }
                        if (TurboConfig.containValue("fav_" + lower_id)) {
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    favoriteMessageCount += dialog.unread_count;
                                    favoriteDialogCount++;
                                    favHasUnmute = true;
                                }
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    favHasUnmute = true;
                                }
                                favoriteMessageCount += dialog.unread_count;
                                favoriteDialogCount++;
                            }
                        }
                        if (!DialogObject.isChannel(dialog)) {
                            isChat = lower_id < 0 && high_id != 1;
                            if (!(isChat || lower_id <= 0 || high_id == 1)) {
                                user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                                isBot = user != null && user.bot;
                                if (isBot) {
                                    if (isBot) {
                                        if (onlyNotMuted) {
                                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                                botMessageCount += dialog.unread_count;
                                                botDialogCount++;
                                                botHasUnmute = true;
                                            }
                                        } else {
                                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                                botHasUnmute = true;
                                            }
                                            botMessageCount += dialog.unread_count;
                                            botDialogCount++;
                                        }
                                    }
                                } else if (onlyNotMuted) {
                                    if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                        contactMessageCount += dialog.unread_count;
                                        contactDialogCount++;
                                        cHasUnmute = true;
                                    }
                                } else {
                                    if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                        cHasUnmute = true;
                                    }
                                    contactMessageCount += dialog.unread_count;
                                    contactDialogCount++;
                                }
                            }
                        }
                        isChat = lower_id < 0 && high_id != 1;
                        if (!DialogObject.isChannel(dialog) && isChat) {
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    groupMessageCount += dialog.unread_count;
                                    groupDialogCount++;
                                    gHasUnmute = true;
                                }
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    gHasUnmute = true;
                                }
                                groupMessageCount += dialog.unread_count;
                                groupDialogCount++;
                            }
                        }
                        if (DialogObject.isChannel(dialog)) {
                            chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    if (chat.megagroup) {
                                        sgroupMessageCount += dialog.unread_count;
                                        sgroupDialogCount++;
                                        sgHasUnmute = true;
                                    } else {
                                        channelMessageCount += dialog.unread_count;
                                        channelDialogCount++;
                                        chHasUnmute = true;
                                    }
                                }
                            } else if (chat.megagroup) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    sgHasUnmute = true;
                                }
                                sgroupMessageCount += dialog.unread_count;
                                sgroupDialogCount++;
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    chHasUnmute = true;
                                }
                                channelMessageCount += dialog.unread_count;
                                channelDialogCount++;
                            }
                        }
                    }
                }
            } else {
                for (i = 0; i < dialogs.size(); i++) {
                    dialog = (TLRPC$TL_dialog) dialogs.get(i);
                    if (!TurboConfig.containValue("hide_" + dialog.id) && dialog.unread_count > 0) {
                        lower_id = (int) dialog.id;
                        high_id = (int) (dialog.id >> 32);
                        if (onlyNotMuted) {
                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                allMessageCount += dialog.unread_count;
                                allDialogCount++;
                                allHasUnmute = true;
                            }
                        } else {
                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                allHasUnmute = true;
                            }
                            allMessageCount += dialog.unread_count;
                            allDialogCount++;
                        }
                        if (TurboConfig.containValue("fav_" + lower_id)) {
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    favoriteMessageCount += dialog.unread_count;
                                    favoriteDialogCount++;
                                    favHasUnmute = true;
                                }
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    favHasUnmute = true;
                                }
                                favoriteMessageCount += dialog.unread_count;
                                favoriteDialogCount++;
                            }
                        }
                        if (!DialogObject.isChannel(dialog)) {
                            isChat = lower_id < 0 && high_id != 1;
                            if (!(isChat || lower_id <= 0 || high_id == 1)) {
                                user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
                                isBot = user != null && user.bot;
                                if (isBot) {
                                    if (isBot) {
                                        if (onlyNotMuted) {
                                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                                botMessageCount += dialog.unread_count;
                                                botDialogCount++;
                                                botHasUnmute = true;
                                            }
                                        } else {
                                            if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                                botHasUnmute = true;
                                            }
                                            botMessageCount += dialog.unread_count;
                                            botDialogCount++;
                                        }
                                    }
                                } else if (onlyNotMuted) {
                                    if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                        contactMessageCount += dialog.unread_count;
                                        contactDialogCount++;
                                        cHasUnmute = true;
                                    }
                                } else {
                                    if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                        cHasUnmute = true;
                                    }
                                    contactMessageCount += dialog.unread_count;
                                    contactDialogCount++;
                                }
                            }
                        }
                        isChat = lower_id < 0 && high_id != 1;
                        if (!DialogObject.isChannel(dialog) && isChat) {
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    groupMessageCount += dialog.unread_count;
                                    groupDialogCount++;
                                    gHasUnmute = true;
                                }
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    gHasUnmute = true;
                                }
                                groupMessageCount += dialog.unread_count;
                                groupDialogCount++;
                            }
                        }
                        if (DialogObject.isChannel(dialog)) {
                            chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                            if (onlyNotMuted) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    if (chat.megagroup) {
                                        sgroupMessageCount += dialog.unread_count;
                                        sgroupDialogCount++;
                                        sgHasUnmute = true;
                                    } else {
                                        channelMessageCount += dialog.unread_count;
                                        channelDialogCount++;
                                        chHasUnmute = true;
                                    }
                                }
                            } else if (chat.megagroup) {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    sgHasUnmute = true;
                                }
                                sgroupMessageCount += dialog.unread_count;
                                sgroupDialogCount++;
                            } else {
                                if (!MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog.id)) {
                                    chHasUnmute = true;
                                }
                                channelMessageCount += dialog.unread_count;
                                channelDialogCount++;
                            }
                        }
                    }
                }
            }
        }
        if (TurboConfig$Tabs.tabsCountChats) {
            botCount = botDialogCount;
            channelCount = channelDialogCount;
            sgroupCount = sgroupDialogCount;
            groupCount = groupDialogCount;
            contactCount = contactDialogCount;
            favoriteCount = favoriteDialogCount;
            allCount = allDialogCount;
        } else {
            botCount = botMessageCount;
            channelCount = channelMessageCount;
            sgroupCount = sgroupMessageCount;
            groupCount = groupMessageCount;
            contactCount = contactMessageCount;
            favoriteCount = favoriteMessageCount;
            allCount = allMessageCount;
        }
        if (this.botCell != null) {
            this.botCell.updateCounter(Integer.valueOf(botCount), botHasUnmute);
        }
        if (this.channelCell != null) {
            this.channelCell.updateCounter(Integer.valueOf(channelCount), chHasUnmute);
        }
        if (this.supergroupCell != null) {
            this.supergroupCell.updateCounter(Integer.valueOf(sgroupCount), sgHasUnmute);
        }
        if (this.groupCell != null) {
            if (TurboConfig$Tabs.mergeGroups) {
                groupCount += sgroupCount;
            }
            TabsViewCell tabsViewCell = this.groupCell;
            Integer valueOf = Integer.valueOf(groupCount);
            if (TurboConfig$Tabs.mergeGroups) {
                gHasUnmute = sgHasUnmute || gHasUnmute;
            }
            tabsViewCell.updateCounter(valueOf, gHasUnmute);
        }
        if (this.contactCell != null) {
            this.contactCell.updateCounter(Integer.valueOf(contactCount), cHasUnmute);
        }
        if (this.favoriteCell != null) {
            this.favoriteCell.updateCounter(Integer.valueOf(favoriteCount), favHasUnmute);
        }
        if (this.allCell != null) {
            this.allCell.updateCounter(Integer.valueOf(allCount), allHasUnmute);
        }
        if (this.unreadCell != null) {
            this.unreadCell.updateCounter(Integer.valueOf(allCount), allHasUnmute);
        }
    }
}
