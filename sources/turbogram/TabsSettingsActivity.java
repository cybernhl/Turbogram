package turbogram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig$Tabs;

public class TabsSettingsActivity extends BaseFragment {
    private AnimatorSet animatorSet;
    private int changeTabTitleRow;
    private int countChatsRow;
    private int countNotMutedRow;
    private int counterSectionRow;
    private int counterSectionRow2;
    private boolean customEnabled;
    private int defaultTabRow;
    private int enableTabsRow;
    private int favAutoDownloadTabRow;
    private int hideTabsOnScrollRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int mergeTabsRow;
    private int moveTabsRow;
    private int rowCount = 0;
    private int shadowRow;
    private int showTabsCounterRow;
    private int sortTabsRow;
    private int swipeTabRow;
    private int tabSectionRow2;
    private int tabsHeightRow;

    /* renamed from: turbogram.TabsSettingsActivity$1 */
    class C24491 extends ActionBarMenuOnItemClick {
        C24491() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TabsSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.TabsSettingsActivity$2 */
    class C24522 implements OnItemClickListener {
        C24522() {
        }

        public void onItemClick(View view, int position) {
            if (position == TabsSettingsActivity.this.enableTabsRow) {
                TabsSettingsActivity.this.customEnabled = !TabsSettingsActivity.this.customEnabled;
                TurboConfig$Tabs.setBooleanValue("tabs", TabsSettingsActivity.this.customEnabled);
                if (TabsSettingsActivity.this.customEnabled) {
                    TurboConfig$Tabs.setBooleanValue("fav_auto_download", TabsSettingsActivity.this.customEnabled);
                }
                if (view instanceof TextCheckBoxCell) {
                    ((TextCheckBoxCell) view).setChecked(TabsSettingsActivity.this.customEnabled);
                }
                TabsSettingsActivity.this.enableRows(TabsSettingsActivity.this.enableTabsRow, TabsSettingsActivity.this.customEnabled);
                NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(1));
            } else if (!TabsSettingsActivity.this.customEnabled) {
            } else {
                if (position == TabsSettingsActivity.this.sortTabsRow) {
                    TabsSettingsActivity.this.presentFragment(new SortTabsActivity());
                } else if (position == TabsSettingsActivity.this.mergeTabsRow) {
                    TurboConfig$Tabs.toggleBooleanValue("merge_groups", TurboConfig$Tabs.mergeGroups);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.mergeGroups);
                    }
                    NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(4));
                } else if (position == TabsSettingsActivity.this.defaultTabRow) {
                    int[] tabs = new int[8];
                    int[] visible = new int[8];
                    String tabsPriorityArray = TurboConfig$Tabs.tabsPriority;
                    if (tabsPriorityArray != null) {
                        int k = 0;
                        for (String item : tabsPriorityArray.substring(1, tabsPriorityArray.length() - 1).split(", ")) {
                            if (item.length() > 0) {
                                tabs[k] = Integer.parseInt(item);
                            }
                            k++;
                        }
                    }
                    String tabsVisibilityArray = TurboConfig$Tabs.tabsVisibility;
                    if (tabsVisibilityArray != null) {
                        int j = 0;
                        for (String item2 : tabsVisibilityArray.substring(1, tabsVisibilityArray.length() - 1).split(", ")) {
                            if (item2.length() > 0) {
                                visible[j] = Integer.parseInt(item2);
                            }
                            j++;
                        }
                    }
                    builder = new Builder(TabsSettingsActivity.this.getParentActivity());
                    ArrayList<CharSequence> items = new ArrayList();
                    final ArrayList<Integer> options = new ArrayList();
                    for (int a = 0; a < tabs.length; a++) {
                        if (visible[a] == 0) {
                            switch (tabs[a]) {
                                case 0:
                                    items.add(LocaleController.getString("RobotTab", R.string.RobotTab));
                                    options.add(Integer.valueOf(0));
                                    break;
                                case 1:
                                    items.add(LocaleController.getString("ChannelTab", R.string.ChannelTab));
                                    options.add(Integer.valueOf(1));
                                    break;
                                case 2:
                                    if (!TurboConfig$Tabs.mergeGroups) {
                                        items.add(LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab));
                                        options.add(Integer.valueOf(2));
                                        break;
                                    }
                                    break;
                                case 3:
                                    items.add(LocaleController.getString("GroupsTab", R.string.GroupsTab));
                                    options.add(Integer.valueOf(3));
                                    break;
                                case 4:
                                    items.add(LocaleController.getString("ContactTab", R.string.ContactTab));
                                    options.add(Integer.valueOf(4));
                                    break;
                                case 5:
                                    items.add(LocaleController.getString("FavoriteTab", R.string.FavoriteTab));
                                    options.add(Integer.valueOf(5));
                                    break;
                                case 6:
                                    items.add(LocaleController.getString("AllTab", R.string.AllTab));
                                    options.add(Integer.valueOf(6));
                                    break;
                                case 7:
                                    items.add(LocaleController.getString("UnreadTab", R.string.UnreadTab));
                                    options.add(Integer.valueOf(7));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    items.add(LocaleController.getString("LastSelectedTab", R.string.LastSelectedTab));
                    options.add(Integer.valueOf(8));
                    r2 = position;
                    builder.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i >= 0 && i < options.size()) {
                                TurboConfig$Tabs.setIntValue("default_tab", ((Integer) options.get(i)).intValue());
                                if (TabsSettingsActivity.this.listAdapter != null) {
                                    TabsSettingsActivity.this.listAdapter.notifyItemChanged(r2);
                                }
                            }
                        }
                    });
                    builder.setTitle(LocaleController.getString("DefaultTab", R.string.DefaultTab));
                    TabsSettingsActivity.this.showDialog(builder.create());
                } else if (position == TabsSettingsActivity.this.swipeTabRow) {
                    TurboConfig$Tabs.toggleBooleanValue("swipe_tabs", TurboConfig$Tabs.swipeTabs);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.swipeTabs);
                    }
                    NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(2), Boolean.valueOf(TurboConfig$Tabs.swipeTabs));
                } else if (position == TabsSettingsActivity.this.changeTabTitleRow) {
                    TurboConfig$Tabs.toggleBooleanValue("change_tab_title", TurboConfig$Tabs.changeTabTitle);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.changeTabTitle);
                    }
                } else if (position == TabsSettingsActivity.this.hideTabsOnScrollRow) {
                    TurboConfig$Tabs.toggleBooleanValue("hide_tabs", TurboConfig$Tabs.hideTabsOnScroll);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.hideTabsOnScroll);
                    }
                } else if (position == TabsSettingsActivity.this.moveTabsRow) {
                    TurboConfig$Tabs.setBooleanValue("move_tabs", !TurboConfig$Tabs.moveTabsToBottom);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.moveTabsToBottom);
                    }
                    NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(1));
                } else if (position == TabsSettingsActivity.this.showTabsCounterRow) {
                    TurboConfig$Tabs.toggleBooleanValue("tabs_counter", TurboConfig$Tabs.tabsCounter);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.tabsCounter);
                    }
                    NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(4));
                } else if (position == TabsSettingsActivity.this.countChatsRow) {
                    TurboConfig$Tabs.toggleBooleanValue("tabs_count_chats", TurboConfig$Tabs.tabsCountChats);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.tabsCountChats);
                    }
                } else if (position == TabsSettingsActivity.this.countNotMutedRow) {
                    TurboConfig$Tabs.toggleBooleanValue("tabs_only_not_muted", TurboConfig$Tabs.countOnlyNotMuted);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.countOnlyNotMuted);
                    }
                } else if (position == TabsSettingsActivity.this.favAutoDownloadTabRow) {
                    TurboConfig$Tabs.toggleBooleanValue("fav_auto_download", TurboConfig$Tabs.autoDownloadFavorite);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Tabs.autoDownloadFavorite);
                    }
                } else if (position == TabsSettingsActivity.this.tabsHeightRow) {
                    builder = new Builder(TabsSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    final NumberPicker numberPicker = new NumberPicker(TabsSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(40);
                    numberPicker.setMaxValue(54);
                    numberPicker.setValue(TurboConfig$Tabs.tabsHeight);
                    builder.setView(numberPicker);
                    r2 = position;
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig$Tabs.setIntValue("tabs_height", numberPicker.getValue());
                            if (TabsSettingsActivity.this.listAdapter != null) {
                                TabsSettingsActivity.this.listAdapter.notifyItemChanged(r2);
                            }
                        }
                    });
                    TabsSettingsActivity.this.showDialog(builder.create());
                    NotificationCenter.getInstance(TabsSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(1));
                }
            }
        }
    }

    /* renamed from: turbogram.TabsSettingsActivity$3 */
    class C24533 extends AnimatorListenerAdapter {
        C24533() {
        }

        public void onAnimationEnd(Animator animator) {
            if (animator.equals(TabsSettingsActivity.this.animatorSet)) {
                TabsSettingsActivity.this.animatorSet = null;
            }
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            boolean z = true;
            int position = holder.getAdapterPosition();
            if (!TurboConfig$Tabs.tabsEnabled) {
                if (position != TabsSettingsActivity.this.enableTabsRow) {
                    z = false;
                }
                return z;
            } else if (position == TabsSettingsActivity.this.enableTabsRow || position == TabsSettingsActivity.this.defaultTabRow || position == TabsSettingsActivity.this.showTabsCounterRow || position == TabsSettingsActivity.this.countChatsRow || position == TabsSettingsActivity.this.countNotMutedRow || position == TabsSettingsActivity.this.swipeTabRow || position == TabsSettingsActivity.this.changeTabTitleRow || position == TabsSettingsActivity.this.favAutoDownloadTabRow || position == TabsSettingsActivity.this.mergeTabsRow || position == TabsSettingsActivity.this.sortTabsRow || position == TabsSettingsActivity.this.hideTabsOnScrollRow || position == TabsSettingsActivity.this.moveTabsRow || position == TabsSettingsActivity.this.tabsHeightRow) {
                return true;
            } else {
                return false;
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new ShadowSectionCell(this.mContext);
                    break;
                case 1:
                    view = new HeaderCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 3:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 4:
                    view = new TextCheckBoxCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 5:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 1:
                    HeaderCell headerCell = holder.itemView;
                    if (position == TabsSettingsActivity.this.tabSectionRow2) {
                        headerCell.setText(LocaleController.getString("TurboTabs", R.string.TurboTabs));
                        return;
                    } else if (position == TabsSettingsActivity.this.counterSectionRow2) {
                        headerCell.setText(LocaleController.getString("TurboTabsCounter", R.string.TurboTabsCounter));
                        return;
                    } else {
                        return;
                    }
                case 2:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == TabsSettingsActivity.this.mergeTabsRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("MergeTabs", R.string.MergeTabs), TurboConfig$Tabs.mergeGroups, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.swipeTabRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("SwipeTabs", R.string.SwipeTabs), TurboConfig$Tabs.swipeTabs, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.changeTabTitleRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ChangeTabTitle", R.string.ChangeTabTitle), TurboConfig$Tabs.changeTabTitle, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.hideTabsOnScrollRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("HideTabs", R.string.HideTabs), TurboConfig$Tabs.hideTabsOnScroll, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.favAutoDownloadTabRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("FavAutoDownload", R.string.FavAutoDownload), TurboConfig$Tabs.autoDownloadFavorite, false);
                        return;
                    } else if (position == TabsSettingsActivity.this.showTabsCounterRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TabsCounter", R.string.TabsCounter), TurboConfig$Tabs.tabsCounter, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.countChatsRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TabsCountChats", R.string.TabsCountChats), TurboConfig$Tabs.tabsCountChats, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.countNotMutedRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TabsCountNotMuted", R.string.TabsCountNotMuted), TurboConfig$Tabs.countOnlyNotMuted, true);
                        return;
                    } else if (position == TabsSettingsActivity.this.moveTabsRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("MoveTabs", R.string.MoveTabs), TurboConfig$Tabs.moveTabsToBottom, true);
                        return;
                    } else {
                        return;
                    }
                case 3:
                    TextSettingsCell settingsCell = holder.itemView;
                    if (position == TabsSettingsActivity.this.defaultTabRow) {
                        switch (TurboConfig$Tabs.defaulTab) {
                            case 0:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("RobotTab", R.string.RobotTab), true);
                                return;
                            case 1:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("ChannelTab", R.string.ChannelTab), true);
                                return;
                            case 2:
                                if (!TurboConfig$Tabs.mergeGroups) {
                                    settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab), true);
                                    return;
                                }
                                return;
                            case 3:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("GroupsTab", R.string.GroupsTab), true);
                                return;
                            case 4:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("ContactTab", R.string.ContactTab), true);
                                return;
                            case 5:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("FavoriteTab", R.string.FavoriteTab), true);
                                return;
                            case 6:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("AllTab", R.string.AllTab), true);
                                return;
                            case 7:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("UnreadTab", R.string.UnreadTab), true);
                                return;
                            case 8:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("LastSelectedTab", R.string.LastSelectedTab), true);
                                return;
                            default:
                                settingsCell.setTextAndValue(LocaleController.getString("DefaultTab", R.string.DefaultTab), LocaleController.getString("AllTab", R.string.AllTab), true);
                                return;
                        }
                    } else if (position == TabsSettingsActivity.this.sortTabsRow) {
                        settingsCell.setText(LocaleController.getString("SortTabs", R.string.SortTabs), true);
                        return;
                    } else if (position == TabsSettingsActivity.this.tabsHeightRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("ThemingTabsHeight", R.string.ThemingTabsHeight), String.format("%d", new Object[]{Integer.valueOf(TurboConfig$Tabs.tabsHeight)}), true);
                        return;
                    } else {
                        return;
                    }
                case 4:
                    TextCheckBoxCell checkBoxCell = holder.itemView;
                    if (position == TabsSettingsActivity.this.enableTabsRow) {
                        checkBoxCell.setTextAndCheck(LocaleController.getString("EnableTabs", R.string.EnableTabs), TabsSettingsActivity.this.customEnabled, false);
                        return;
                    }
                    return;
                case 5:
                    TextInfoPrivacyCell infoPrivacyCell = holder.itemView;
                    if (position == TabsSettingsActivity.this.shadowRow) {
                        infoPrivacyCell.setText(null);
                        infoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void onViewAttachedToWindow(ViewHolder holder) {
            if (holder.getItemViewType() != 0) {
                switch (holder.getItemViewType()) {
                    case 2:
                        holder.itemView.setEnabled(TabsSettingsActivity.this.customEnabled);
                        return;
                    case 3:
                        holder.itemView.setEnabled(TabsSettingsActivity.this.customEnabled, null);
                        return;
                    case 5:
                        holder.itemView.setEnabled(TabsSettingsActivity.this.customEnabled, null);
                        return;
                    default:
                        return;
                }
            }
        }

        public int getItemCount() {
            return TabsSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == TabsSettingsActivity.this.counterSectionRow) {
                return 0;
            }
            if (position == TabsSettingsActivity.this.tabSectionRow2 || position == TabsSettingsActivity.this.counterSectionRow2) {
                return 1;
            }
            if (position == TabsSettingsActivity.this.swipeTabRow || position == TabsSettingsActivity.this.showTabsCounterRow || position == TabsSettingsActivity.this.countNotMutedRow || position == TabsSettingsActivity.this.changeTabTitleRow || position == TabsSettingsActivity.this.countChatsRow || position == TabsSettingsActivity.this.favAutoDownloadTabRow || position == TabsSettingsActivity.this.mergeTabsRow || position == TabsSettingsActivity.this.hideTabsOnScrollRow || position == TabsSettingsActivity.this.moveTabsRow) {
                return 2;
            }
            if (position == TabsSettingsActivity.this.defaultTabRow || position == TabsSettingsActivity.this.sortTabsRow || position == TabsSettingsActivity.this.tabsHeightRow) {
                return 3;
            }
            if (position == TabsSettingsActivity.this.enableTabsRow) {
                return 4;
            }
            if (position == TabsSettingsActivity.this.shadowRow) {
                return 5;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enableTabsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.shadowRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tabSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.sortTabsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mergeTabsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.defaultTabRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.swipeTabRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.changeTabTitleRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hideTabsOnScrollRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.moveTabsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tabsHeightRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.favAutoDownloadTabRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.counterSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.counterSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showTabsCounterRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.countNotMutedRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.countChatsRow = i;
        this.customEnabled = TurboConfig$Tabs.tabsEnabled;
        return true;
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TabsSettings", R.string.TabsSettings));
        this.actionBar.setActionBarMenuOnItemClick(new C24491());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24522());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private void enableRows(int currentRow, boolean enabled) {
        int count = this.listView.getChildCount();
        ArrayList<Animator> animators = new ArrayList();
        for (int a = 0; a < count; a++) {
            Holder holder = (Holder) this.listView.getChildViewHolder(this.listView.getChildAt(a));
            int type = holder.getItemViewType();
            if (!(holder.getAdapterPosition() == currentRow || type == 1 || type == 0)) {
                switch (type) {
                    case 2:
                        holder.itemView.setEnabled(enabled);
                        break;
                    case 3:
                        holder.itemView.setEnabled(enabled, animators);
                        break;
                    case 4:
                        holder.itemView.setEnabled(enabled, animators);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!animators.isEmpty()) {
            if (this.animatorSet != null) {
                this.animatorSet.cancel();
            }
            this.animatorSet = new AnimatorSet();
            this.animatorSet.playTogether(animators);
            this.animatorSet.addListener(new C24533());
            this.animatorSet.setDuration(150);
            this.animatorSet.start();
        }
    }
}
