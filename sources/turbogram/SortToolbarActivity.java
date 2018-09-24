package turbogram;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import turbogram.Cells.ToolbarCheckCell;
import turbogram.Utilities.TurboConfig.Toolbar;

public class SortToolbarActivity extends BaseFragment {
    public static String[] toolbarTitles = new String[]{LocaleController.getString("Contacts", R.string.Contacts), LocaleController.getString("MultiTasks", R.string.MultiTasks), LocaleController.getString("TurboFMCategories", R.string.TurboFMCategories), LocaleController.getString("OnlineContacts", R.string.OnlineContacts), LocaleController.getString("TurboSettings", R.string.TurboSettings), LocaleController.getString("HideChats", R.string.HideChats), LocaleController.getString("TurboTurnOff", R.string.TurboTurnOff), LocaleController.getString("OpenProfile", R.string.OpenProfile), LocaleController.getString("AnsweringMachine", R.string.AnsweringMachine)};
    private RecyclerListAdapter listAdapter;
    private RecyclerListView listView;
    private int[] tootbar;

    /* renamed from: turbogram.SortToolbarActivity$1 */
    class C24201 extends ActionBarMenuOnItemClick {
        C24201() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                SortToolbarActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.SortToolbarActivity$2 */
    class C24212 implements OnTouchListener {
        C24212() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 1:
                    if (SortToolbarActivity.this.listAdapter != null) {
                        SortToolbarActivity.this.listAdapter.notifyDataSetChanged();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    private interface ItemTouchHelperListener {
        void swapElements(int i, int i2);
    }

    private interface ViewHolderListener {
        void onItemClear();

        void onItemSelected();
    }

    public class ItemViewHolder extends ViewHolder implements ViewHolderListener {
        public ToolbarCheckCell toolCheckCell;

        ItemViewHolder(View view) {
            super(view);
            this.toolCheckCell = (ToolbarCheckCell) view;
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
            return SortToolbarActivity.this.tootbar.length;
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ToolbarCheckCell toolCheckCell = new ToolbarCheckCell(parent.getContext());
            toolCheckCell.setLayoutParams(new LayoutParams(-1, -2));
            return new ItemViewHolder(toolCheckCell);
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
            itemViewHolder.toolCheckCell.setTextAndIcon(SortToolbarActivity.toolbarTitles[SortToolbarActivity.this.tootbar[position]], R.drawable.profile_list);
            if (position > 3) {
                itemViewHolder.toolCheckCell.setTextAndIcon(SortToolbarActivity.toolbarTitles[SortToolbarActivity.this.tootbar[position]], R.drawable.profile_list_a);
                itemViewHolder.toolCheckCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
                return;
            }
            itemViewHolder.toolCheckCell.setTextAndIcon(SortToolbarActivity.toolbarTitles[SortToolbarActivity.this.tootbar[position]], R.drawable.profile_list);
            itemViewHolder.toolCheckCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        }

        public void swapElements(int position1, int position2) {
            int newPosition1 = SortToolbarActivity.this.tootbar[position2];
            int newPosition2 = SortToolbarActivity.this.tootbar[position1];
            SortToolbarActivity.this.tootbar[position1] = newPosition1;
            SortToolbarActivity.this.tootbar[position2] = newPosition2;
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
        this.tootbar = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        setPriority();
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        storePriority();
        if (Toolbar.enabled) {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(5));
        }
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("SortToolbar", R.string.SortToolbar));
        this.actionBar.setActionBarMenuOnItemClick(new C24201());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(context);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.listView.setFocusable(true);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        linearLayout.addView(this.listView, LayoutHelper.createLinear(-1, 0, 1.0f, 3));
        this.listView.setOnTouchListener(new C24212());
        this.listAdapter = new RecyclerListAdapter();
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.listAdapter)).attachToRecyclerView(this.listView);
        this.listView.setAdapter(this.listAdapter);
        TextInfoPrivacyCell textInfoPrivacyCell = new TextInfoPrivacyCell(context);
        textInfoPrivacyCell.setText(LocaleController.getString("SortToolbarDes", R.string.SortToolbarDes));
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

    private void storePriority() {
        Toolbar.setStringValue("tool_priority", Arrays.toString(this.tootbar));
    }

    private void setPriority() {
        String priorityArray = Toolbar.priority;
        if (priorityArray != null) {
            int i = 0;
            for (String item : priorityArray.substring(1, priorityArray.length() - 1).split(", ")) {
                if (item.length() > 0) {
                    this.tootbar[i] = Integer.parseInt(item);
                }
                i++;
            }
        }
    }
}
