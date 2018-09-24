package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.TLRPC$TL_contact;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.SpecialContactCell;
import turbogram.Utilities.TurboConfig;

public class SpecialContactsActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    TextCheckCell enableSCTextCheck;
    private SpecialContactAdapter listAdapter;
    private RecyclerListView listView;

    /* renamed from: turbogram.SpecialContactsActivity$1 */
    class C24221 extends ActionBarMenuOnItemClick {
        C24221() {
        }

        public void onItemClick(int i) {
            if (i == -1) {
                SpecialContactsActivity.this.finishFragment();
            } else if (i == 1) {
                Bundle args = new Bundle();
                args.putInt(Param.TYPE, 2);
                SpecialContactsActivity.this.presentFragment(new MultiSelectContactActivity(args));
            } else {
                SpecialContactsActivity.this.presentFragment(new SpecialNotificationsActivity());
            }
        }
    }

    /* renamed from: turbogram.SpecialContactsActivity$2 */
    class C24232 implements OnClickListener {
        C24232() {
        }

        public void onClick(View v) {
            TurboConfig.setBooleanValue("specific_contact", !TurboConfig.specificContact);
            if (v instanceof TextCheckCell) {
                ((TextCheckCell) v).setChecked(TurboConfig.specificContact);
            }
        }
    }

    /* renamed from: turbogram.SpecialContactsActivity$3 */
    class C24263 implements OnItemLongClickListener {
        C24263() {
        }

        public boolean onItemClick(View view, final int position) {
            Builder builder = new Builder(SpecialContactsActivity.this.getParentActivity());
            builder.setItems(new CharSequence[]{LocaleController.getString("Delete", R.string.Delete)}, new DialogInterface.OnClickListener() {

                /* renamed from: turbogram.SpecialContactsActivity$3$1$1 */
                class C24241 implements DialogInterface.OnClickListener {
                    C24241() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        int uid = ((User) ((SpecialContactAdapter) SpecialContactsActivity.this.listView.getAdapter()).getItem(position)).id;
                        if (TurboConfig.containValue("specific_c" + uid)) {
                            TurboConfig.removeValue("specific_c" + uid);
                            if (SpecialContactsActivity.this.listAdapter != null) {
                                SpecialContactsActivity.this.listAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }

                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SpecialContactsActivity.this.getParentActivity());
                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                        builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C24241());
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        SpecialContactsActivity.this.showDialog(builder.create());
                    }
                }
            });
            SpecialContactsActivity.this.showDialog(builder.create());
            return true;
        }
    }

    /* renamed from: turbogram.SpecialContactsActivity$4 */
    class C24274 implements OnItemClickListener {
        C24274() {
        }

        public void onItemClick(View view, int position) {
            Object user = ((SpecialContactAdapter) SpecialContactsActivity.this.listView.getAdapter()).getItem(position);
            if (user != null) {
                Bundle args = new Bundle();
                args.putInt("user_id", ((User) user).id);
                if (MessagesController.getInstance(SpecialContactsActivity.this.currentAccount).checkCanOpenChat(args, SpecialContactsActivity.this)) {
                    SpecialContactsActivity.this.presentFragment(new ChatActivity(args), true);
                }
            }
        }
    }

    class SpecialContactAdapter extends SelectionAdapter {
        private Context mContext;
        ArrayList<Integer> specialContactsArray = new ArrayList();

        public SpecialContactAdapter(Context context) {
            this.mContext = context;
        }

        private void getSpecialContactsArray() {
            ArrayList<TLRPC$TL_contact> allContactsArray = new ArrayList();
            allContactsArray.addAll(ContactsController.getInstance(SpecialContactsActivity.this.currentAccount).contacts);
            for (int i = 0; i < allContactsArray.size(); i++) {
                int uid = ((TLRPC$TL_contact) allContactsArray.get(i)).user_id;
                if (TurboConfig.containValue("specific_c" + uid)) {
                    this.specialContactsArray.add(Integer.valueOf(uid));
                }
            }
        }

        public void notifyDataSetChanged() {
            this.specialContactsArray.clear();
            getSpecialContactsArray();
            super.notifyDataSetChanged();
        }

        public Object getItem(int position) {
            return MessagesController.getInstance(SpecialContactsActivity.this.currentAccount).getUser((Integer) this.specialContactsArray.get(position));
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new SpecialContactCell(this.mContext, 10);
            view.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setData(MessagesController.getInstance(SpecialContactsActivity.this.currentAccount).getUser((Integer) this.specialContactsArray.get(position)));
        }

        public int getItemCount() {
            return this.specialContactsArray.size();
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
    }

    public View createView(Context context) {
        int i = 1;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("SpecialContacts", R.string.SpecialContacts));
        this.actionBar.setActionBarMenuOnItemClick(new C24221());
        ActionBarMenu menu = this.actionBar.createMenu();
        menu.addItem(1, (int) R.drawable.add);
        menu.addItem(2, (int) R.drawable.menu_settings);
        this.listAdapter = new SpecialContactAdapter(context);
        this.fragmentView = new LinearLayout(context);
        LinearLayout linearLayout = this.fragmentView;
        linearLayout.setOrientation(1);
        this.enableSCTextCheck = new TextCheckCell(context);
        this.enableSCTextCheck.setBackgroundColor(-1);
        this.enableSCTextCheck.setTextAndCheck(LocaleController.getString("EnableSpecialContacts", R.string.EnableSpecialContacts), TurboConfig.specificContact, true);
        this.enableSCTextCheck.setOnClickListener(new C24232());
        this.enableSCTextCheck.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        linearLayout.addView(this.enableSCTextCheck);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView recyclerListView = this.listView;
        if (!LocaleController.isRTL) {
            i = 2;
        }
        recyclerListView.setVerticalScrollbarPosition(i);
        linearLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemLongClickListener(new C24263());
        this.listView.setOnItemClickListener(new C24274());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.updateInterfaces) {
            int mask = ((Integer) args[0]).intValue();
            if ((mask & 2) != 0 || (mask & 1) != 0 || (mask & 4) != 0) {
                updateVisibleRows(mask);
            }
        }
    }

    private void updateVisibleRows(int mask) {
        if (this.listView != null) {
            int count = this.listView.getChildCount();
            for (int a = 0; a < count; a++) {
                View child = this.listView.getChildAt(a);
                if (child instanceof SpecialContactCell) {
                    ((SpecialContactCell) child).update(mask);
                }
            }
        }
    }
}
