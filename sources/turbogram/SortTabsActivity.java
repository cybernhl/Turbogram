package turbogram;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.Arrays;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import turbogram.Cells.TabCheckCell;
import turbogram.Utilities.TurboConfig$Tabs;
import turbogram.Utilities.TurboUtils;

public class SortTabsActivity extends BaseFragment {
    public static int[] tabIcons = new int[]{R.drawable.turbo_tab_bot, R.drawable.turbo_tab_channel, R.drawable.turbo_tab_supergroup, R.drawable.turbo_tab_group, R.drawable.turbo_tab_user, R.drawable.turbo_tab_favs, R.drawable.turbo_tab_all, R.drawable.turbo_tab_unread};
    public static String[] tabTitles = new String[]{LocaleController.getString("RobotTab", R.string.RobotTab), LocaleController.getString("ChannelTab", R.string.ChannelTab), LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab), LocaleController.getString("GroupsTab", R.string.GroupsTab), LocaleController.getString("ContactTab", R.string.ContactTab), LocaleController.getString("FavoriteTab", R.string.FavoriteTab), LocaleController.getString("AllTab", R.string.AllTab), LocaleController.getString("UnreadTab", R.string.UnreadTab)};
    private RecyclerListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean needReorder;
    private int[] tabs;
    private int[] visible;
    private boolean visibleChanged;
    private int visibleCount = 0;

    /* renamed from: turbogram.SortTabsActivity$1 */
    class C24191 extends ActionBarMenuOnItemClick {
        C24191() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                SortTabsActivity.this.finishFragment();
            }
        }
    }

    private interface ItemTouchHelperListener {
        void swapElements(int i, int i2);
    }

    private interface ViewHolderListener {
        void onItemClear();

        void onItemSelected();
    }

    public class ItemViewHolder extends ViewHolder implements OnClickListener, ViewHolderListener {
        public TabCheckCell tabCell;

        ItemViewHolder(View view) {
            super(view);
            this.tabCell = (TabCheckCell) view;
            if (this.tabCell.getChildAt(1) instanceof CheckBoxSquare) {
                this.tabCell.getChildAt(1).setOnClickListener(this);
            }
        }

        public void onClick(View view) {
            SortTabsActivity.this.visibleChanged = true;
            if (SortTabsActivity.this.visible[getPosition()] == -1) {
                this.tabCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                this.tabCell.setIconColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                this.tabCell.setChecked(true);
                SortTabsActivity.this.visible[getPosition()] = 0;
                SortTabsActivity.this.visibleCount = SortTabsActivity.this.visibleCount + 1;
            } else if (SortTabsActivity.this.visibleCount - 1 < 2) {
                TurboUtils.showToast(SortTabsActivity.this.getParentActivity(), LocaleController.getString("TabsWarning", R.string.TabsWarning), 1);
            } else {
                this.tabCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
                this.tabCell.setIconColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
                this.tabCell.setChecked(false);
                if (TurboConfig$Tabs.defaulTab == SortTabsActivity.this.tabs[getPosition()]) {
                    TurboConfig$Tabs.setIntValue("default_tab", TurboConfig$Tabs.getFirstActiveTab());
                }
                SortTabsActivity.this.visible[getPosition()] = -1;
                SortTabsActivity.this.visibleCount = SortTabsActivity.this.visibleCount - 1;
            }
        }

        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }

        public void onItemSelected() {
            this.itemView.setBackgroundColor(Theme.getColor(Theme.key_graySection));
        }
    }

    private class RecyclerListAdapter extends Adapter<ItemViewHolder> implements ItemTouchHelperListener {
        private RecyclerListAdapter() {
        }

        public int getItemCount() {
            return SortTabsActivity.this.tabs.length;
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TabCheckCell tabCheckCell = new TabCheckCell(parent.getContext());
            tabCheckCell.setLayoutParams(new LayoutParams(-1, -2));
            return new ItemViewHolder(tabCheckCell);
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
            itemViewHolder.tabCell.setTextAndIcon(SortTabsActivity.tabTitles[SortTabsActivity.this.tabs[position]], SortTabsActivity.tabIcons[SortTabsActivity.this.tabs[position]]);
            if (SortTabsActivity.this.visible[position] < 0) {
                itemViewHolder.tabCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
                itemViewHolder.tabCell.setIconColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
                itemViewHolder.tabCell.setChecked(false);
                return;
            }
            itemViewHolder.tabCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            itemViewHolder.tabCell.setIconColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            itemViewHolder.tabCell.setChecked(true);
        }

        public void swapElements(int position1, int position2) {
            if (position1 != position2) {
                SortTabsActivity.this.needReorder = true;
            }
            int newPosition1 = SortTabsActivity.this.tabs[position2];
            int newPosition2 = SortTabsActivity.this.tabs[position1];
            SortTabsActivity.this.tabs[position1] = newPosition1;
            SortTabsActivity.this.tabs[position2] = newPosition2;
            int position1Visibility = SortTabsActivity.this.visible[position1];
            int position2Visibility = SortTabsActivity.this.visible[position2];
            if (position1Visibility != position2Visibility) {
                SortTabsActivity.this.visibleChanged = true;
            }
            SortTabsActivity.this.visible[position1] = position2Visibility;
            SortTabsActivity.this.visible[position2] = position1Visibility;
            notifyItemMoved(position1, position2);
        }
    }

    private class SimpleItemTouchHelperCallback extends Callback {
        private final ItemTouchHelperListener listener;

        public SimpleItemTouchHelperCallback(ItemTouchHelperListener listener) {
            this.listener = listener;
        }

        public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            ((ViewHolderListener) viewHolder).onItemClear();
        }

        public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
            return Callback.makeMovementFlags(3, 0);
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
            this.listener.swapElements(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
            return true;
        }

        public void onSelectedChanged(ViewHolder viewHolder, int n) {
            if (n != 0) {
                ((ViewHolderListener) viewHolder).onItemSelected();
            }
            super.onSelectedChanged(viewHolder, n);
        }

        public void onSwiped(ViewHolder viewHolder, int n) {
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        this.tabs = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        this.visible = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        setTabsPriority();
        setTabsVisibility();
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        storeTabsPriority();
        storeTabsVisibility();
        if (TurboConfig$Tabs.tabsEnabled) {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(4));
        }
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("SortTabs", R.string.SortTabs));
        this.actionBar.setActionBarMenuOnItemClick(new C24191());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(context);
        this.listView.setFocusable(true);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        linearLayout.addView(this.listView, LayoutHelper.createLinear(-1, 0, 1.0f, 3));
        this.listAdapter = new RecyclerListAdapter();
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.listAdapter)).attachToRecyclerView(this.listView);
        this.listView.setAdapter(this.listAdapter);
        TextInfoPrivacyCell textInfoPrivacyCell = new TextInfoPrivacyCell(context);
        textInfoPrivacyCell.setText(LocaleController.getString("SortTabsDes", R.string.SortTabsDes));
        textInfoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(context, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        linearLayout.addView(textInfoPrivacyCell, LayoutHelper.createLinear(-1, -2, 80));
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private void storeTabsPriority() {
        TurboConfig$Tabs.setStringValue("tabs_priority", Arrays.toString(this.tabs));
    }

    private void storeTabsVisibility() {
        TurboConfig$Tabs.setStringValue("tabs_visibility", Arrays.toString(this.visible));
    }

    private void setTabsPriority() {
        String tabsPriorityArray = TurboConfig$Tabs.tabsPriority;
        if (tabsPriorityArray != null) {
            int i = 0;
            for (String item : tabsPriorityArray.substring(1, tabsPriorityArray.length() - 1).split(", ")) {
                if (item.length() > 0) {
                    this.tabs[i] = Integer.parseInt(item);
                }
                i++;
            }
        }
    }

    private void setTabsVisibility() {
        /* JADX: method processing error */
/*
Error: java.lang.IndexOutOfBoundsException: bitIndex < 0: -1
	at java.util.BitSet.get(BitSet.java:623)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.usedArgAssign(CodeShrinker.java:138)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.access$300(CodeShrinker.java:43)
	at jadx.core.dex.visitors.CodeShrinker.canMoveBetweenBlocks(CodeShrinker.java:282)
	at jadx.core.dex.visitors.CodeShrinker.shrinkBlock(CodeShrinker.java:232)
	at jadx.core.dex.visitors.CodeShrinker.shrinkMethod(CodeShrinker.java:38)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkArrayForEach(LoopRegionVisitor.java:196)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkForIndexedLoop(LoopRegionVisitor.java:119)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:65)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:52)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:46)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1740223770.run(Unknown Source)
*/
        /*
        r8 = this;
        r3 = turbogram.Utilities.TurboConfig$Tabs.tabsVisibility;
        if (r3 == 0) goto L_0x003c;
    L_0x0004:
        r4 = 1;
        r5 = r3.length();
        r5 = r5 + -1;
        r4 = r3.substring(r4, r5);
        r5 = ", ";
        r2 = r4.split(r5);
        r0 = 0;
        r5 = r2.length;
        r4 = 0;
    L_0x0019:
        if (r4 >= r5) goto L_0x003c;
    L_0x001b:
        r1 = r2[r4];
        r6 = r1.length();
        if (r6 <= 0) goto L_0x0037;
    L_0x0023:
        r6 = r8.visible;
        r7 = java.lang.Integer.parseInt(r1);
        r6[r0] = r7;
        r6 = r8.visible;
        r6 = r6[r0];
        if (r6 != 0) goto L_0x0037;
    L_0x0031:
        r6 = r8.visibleCount;
        r6 = r6 + 1;
        r8.visibleCount = r6;
    L_0x0037:
        r0 = r0 + 1;
        r4 = r4 + 1;
        goto L_0x0019;
    L_0x003c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: turbogram.SortTabsActivity.setTabsVisibility():void");
    }
}
