package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import turbogram.Cells.MenuItemCell;
import turbogram.Database.DBHelper;
import turbogram.Models.SideMenuItem;
import turbogram.Utilities.TurboUtils;

public class SortMenuActivity extends BaseFragment {
    private Context context;
    private boolean deleting = false;
    private RecyclerListAdapter listAdapter;
    private RecyclerListView listView;
    private HashMap<String, Integer> menuIcons = new HashMap(19);
    private ArrayList<SideMenuItem> menuItems = new ArrayList();
    private HashMap<String, String> menuTitles = new HashMap(19);
    private boolean moving = false;

    /* renamed from: turbogram.SortMenuActivity$1 */
    class C24161 extends ActionBarMenuOnItemClick {
        C24161() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                SortMenuActivity.this.finishFragment();
            } else if (id == 1) {
                SortMenuActivity.this.addSeparatore();
                if (SortMenuActivity.this.listAdapter != null) {
                    SortMenuActivity.this.listAdapter.notifyDataSetChanged();
                }
                TurboUtils.showToast(SortMenuActivity.this.getParentActivity(), LocaleController.getString("SideMenuSeparatoreAdded", R.string.SideMenuSeparatoreAdded), 1);
            }
        }
    }

    /* renamed from: turbogram.SortMenuActivity$2 */
    class C24172 implements OnItemClickListener {
        C24172() {
        }

        public void onItemClick(View view, int position) {
            int i = 0;
            if (SortMenuActivity.this.deleting) {
                SortMenuActivity.this.deleting = false;
            } else if (SortMenuActivity.this.moving) {
                SortMenuActivity.this.moving = false;
            } else {
                SideMenuItem menuItem = (SideMenuItem) SortMenuActivity.this.menuItems.get(position);
                ArrayList access$400 = SortMenuActivity.this.menuItems;
                int id = menuItem.getId();
                String title = menuItem.getTitle();
                if (menuItem.getShow() == 0) {
                    i = 1;
                }
                access$400.set(position, new SideMenuItem(id, title, i, menuItem.getDelButton()));
                if (SortMenuActivity.this.listAdapter != null) {
                    SortMenuActivity.this.listAdapter.notifyItemChanged(position);
                }
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
        public MenuItemCell menuItemCell;

        /* renamed from: turbogram.SortMenuActivity$ItemViewHolder$1 */
        class C24181 implements DialogInterface.OnClickListener {
            C24181() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                SortMenuActivity.this.menuItems.remove(ItemViewHolder.this.getPosition());
                if (SortMenuActivity.this.listAdapter != null) {
                    SortMenuActivity.this.listAdapter.notifyItemRemoved(ItemViewHolder.this.getPosition());
                }
            }
        }

        ItemViewHolder(View view) {
            super(view);
            this.menuItemCell = (MenuItemCell) view;
            if (this.menuItemCell.getChildAt(2) instanceof ImageView) {
                this.menuItemCell.getChildAt(2).setOnClickListener(this);
            }
        }

        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }

        public void onItemSelected() {
            this.itemView.setBackgroundColor(Theme.getColor(Theme.key_graySection));
        }

        public void onClick(View v) {
            if (((SideMenuItem) SortMenuActivity.this.menuItems.get(getPosition())).getTitle().equals("sep")) {
                SortMenuActivity.this.deleting = true;
                Builder builder = new Builder(SortMenuActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("Delete", R.string.Delete));
                builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C24181());
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                SortMenuActivity.this.showDialog(builder.create());
            }
        }
    }

    private class RecyclerListAdapter extends Adapter<ItemViewHolder> implements ItemTouchHelperListener {
        public RecyclerListAdapter() {
            SortMenuActivity.this.getMenuItems();
        }

        public int getItemCount() {
            return SortMenuActivity.this.menuItems.size();
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MenuItemCell itemCell = new MenuItemCell(parent.getContext(), 10);
            itemCell.setLayoutParams(new LayoutParams(-1, -2));
            return new ItemViewHolder(itemCell);
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
            String title = ((SideMenuItem) SortMenuActivity.this.menuItems.get(position)).getTitle();
            itemViewHolder.menuItemCell.setData((String) SortMenuActivity.this.menuTitles.get(title), ((Integer) SortMenuActivity.this.menuIcons.get(title)).intValue(), ((SideMenuItem) SortMenuActivity.this.menuItems.get(position)).getDelButton() == 1);
            if (((SideMenuItem) SortMenuActivity.this.menuItems.get(position)).getShow() == 1) {
                itemViewHolder.menuItemCell.active();
            } else {
                itemViewHolder.menuItemCell.inActive();
            }
        }

        public void swapElements(int position1, int position2) {
            int id1 = ((SideMenuItem) SortMenuActivity.this.menuItems.get(position1)).getId();
            String title1 = ((SideMenuItem) SortMenuActivity.this.menuItems.get(position1)).getTitle();
            int show1 = ((SideMenuItem) SortMenuActivity.this.menuItems.get(position1)).getShow();
            int delbtn1 = ((SideMenuItem) SortMenuActivity.this.menuItems.get(position1)).getDelButton();
            SortMenuActivity.this.menuItems.set(position1, new SideMenuItem(((SideMenuItem) SortMenuActivity.this.menuItems.get(position2)).getId(), ((SideMenuItem) SortMenuActivity.this.menuItems.get(position2)).getTitle(), ((SideMenuItem) SortMenuActivity.this.menuItems.get(position2)).getShow(), ((SideMenuItem) SortMenuActivity.this.menuItems.get(position2)).getDelButton()));
            SortMenuActivity.this.menuItems.set(position2, new SideMenuItem(id1, title1, show1, delbtn1));
            SortMenuActivity.this.moving = true;
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

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        saveMenuItems();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
    }

    public View createView(Context context) {
        this.context = context;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("SideMenu", R.string.SideMenu));
        this.actionBar.setActionBarMenuOnItemClick(new C24161());
        this.actionBar.createMenu().addItem(1, (int) R.drawable.add);
        this.menuTitles.put("sep", LocaleController.getString("SideMenuSeparatore", R.string.SideMenuSeparatore));
        this.menuTitles.put("accounts", LocaleController.getString("AppAccounts", R.string.AppAccounts));
        this.menuTitles.put("newgroup", LocaleController.getString("NewGroup", R.string.NewGroup));
        this.menuTitles.put("newschat", LocaleController.getString("NewSecretChat", R.string.NewSecretChat));
        this.menuTitles.put("newchannel", LocaleController.getString("NewChannel", R.string.NewChannel));
        this.menuTitles.put("contacts", LocaleController.getString("Contacts", R.string.Contacts));
        this.menuTitles.put("smessages", LocaleController.getString("SavedMessages", R.string.SavedMessages));
        this.menuTitles.put("calls", LocaleController.getString("Calls", R.string.Calls));
        this.menuTitles.put("scontacts", LocaleController.getString("SpecialContacts", R.string.SpecialContacts));
        this.menuTitles.put("cchanges", LocaleController.getString("UserChanges", R.string.UserChanges));
        this.menuTitles.put("idfinder", LocaleController.getString("IdFinder", R.string.IdFinder));
        this.menuTitles.put("cloud", LocaleController.getString("CategorizeProfile", R.string.CategorizeProfile));
        this.menuTitles.put("dcategory", LocaleController.getString("CategorizeDialogs", R.string.CategorizeDialogs));
        this.menuTitles.put("dmanager", LocaleController.getString("DownloadManager", R.string.DownloadManager));
        this.menuTitles.put("invite", LocaleController.getString("InviteFriends", R.string.InviteFriends));
        this.menuTitles.put("setting", LocaleController.getString("Settings", R.string.Settings));
        this.menuTitles.put("tsetting", LocaleController.getString("TurboSettings", R.string.TurboSettings));
        this.menuTitles.put("theme", LocaleController.getString("Theme", R.string.Theme));
        this.menuTitles.put("faq", LocaleController.getString("TelegramFaq", R.string.TelegramFaq));
        this.menuTitles.put("turn", LocaleController.getString("TurboTurnOff", R.string.TurboTurnOff));
        this.menuIcons.put("sep", Integer.valueOf(R.drawable.turbo_menu_separate));
        this.menuIcons.put("accounts", Integer.valueOf(R.drawable.menu_accounts));
        this.menuIcons.put("newgroup", Integer.valueOf(R.drawable.menu_newgroup));
        this.menuIcons.put("newschat", Integer.valueOf(R.drawable.menu_secret));
        this.menuIcons.put("newchannel", Integer.valueOf(R.drawable.menu_broadcast));
        this.menuIcons.put("contacts", Integer.valueOf(R.drawable.menu_contacts));
        this.menuIcons.put("smessages", Integer.valueOf(R.drawable.menu_saved));
        this.menuIcons.put("calls", Integer.valueOf(R.drawable.menu_calls));
        this.menuIcons.put("scontacts", Integer.valueOf(R.drawable.turbo_menu_scontact));
        this.menuIcons.put("cchanges", Integer.valueOf(R.drawable.turbo_menu_cchanges));
        this.menuIcons.put("idfinder", Integer.valueOf(R.drawable.turbo_menu_idfinder));
        this.menuIcons.put("cloud", Integer.valueOf(R.drawable.turbo_menu_profile));
        this.menuIcons.put("dcategory", Integer.valueOf(R.drawable.turbo_menu_dialog_cat));
        this.menuIcons.put("dmanager", Integer.valueOf(R.drawable.turbo_menu_download));
        this.menuIcons.put("invite", Integer.valueOf(R.drawable.menu_invite));
        this.menuIcons.put("setting", Integer.valueOf(R.drawable.menu_settings));
        this.menuIcons.put("tsetting", Integer.valueOf(R.drawable.menu_settings));
        this.menuIcons.put("theme", Integer.valueOf(R.drawable.menu_themes));
        this.menuIcons.put("faq", Integer.valueOf(R.drawable.menu_help));
        this.menuIcons.put("turn", Integer.valueOf(R.drawable.turbo_menu_power));
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
        this.listView.setOnItemClickListener(new C24172());
        this.listAdapter = new RecyclerListAdapter();
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.listAdapter)).attachToRecyclerView(this.listView);
        this.listView.setAdapter(this.listAdapter);
        TextInfoPrivacyCell textInfoPrivacyCell = new TextInfoPrivacyCell(context);
        textInfoPrivacyCell.setText(LocaleController.getString("SideMenuDes", R.string.SideMenuDes));
        textInfoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(context, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        linearLayout.addView(textInfoPrivacyCell, LayoutHelper.createLinear(-1, -2, 80));
        return this.fragmentView;
    }

    private void addSeparatore() {
        saveMenuItems();
        this.menuItems.clear();
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            dbHelper.addMenu("sep", 1, 1);
            this.menuItems.addAll(dbHelper.getAllMenu());
        } finally {
            dbHelper.close();
        }
    }

    private void getMenuItems() {
        this.menuItems.clear();
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            this.menuItems.addAll(dbHelper.getAllMenu());
        } finally {
            dbHelper.close();
        }
    }

    private void saveMenuItems() {
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            dbHelper.deleteAllMenu();
            for (int i = 0; i < this.menuItems.size(); i++) {
                dbHelper.addMenu(((SideMenuItem) this.menuItems.get(i)).getTitle(), ((SideMenuItem) this.menuItems.get(i)).getShow(), ((SideMenuItem) this.menuItems.get(i)).getDelButton());
            }
        } finally {
            dbHelper.close();
        }
    }
}
