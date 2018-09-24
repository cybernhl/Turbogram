package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.TurboDocumentCell;
import turbogram.Database.DBHelper;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class ProfileCategoryActivity extends BaseFragment {
    Context context;
    private ProfileCategoryAdapter listAdapter;
    private RecyclerListView listView;

    /* renamed from: turbogram.ProfileCategoryActivity$1 */
    class C23931 extends ActionBarMenuOnItemClick {
        C23931() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ProfileCategoryActivity.this.finishFragment();
            } else if (id == 1) {
                ProfileCategoryActivity.this.addCategory();
            }
        }
    }

    /* renamed from: turbogram.ProfileCategoryActivity$2 */
    class C23942 implements OnClickListener {
        C23942() {
        }

        public void onClick(View v) {
            TurboConfig.setBooleanValue("cp_menu_inchat", !TurboConfig.showProfileCatMenuInChat);
            if (v instanceof TextCheckCell) {
                ((TextCheckCell) v).setChecked(TurboConfig.showProfileCatMenuInChat);
            }
        }
    }

    /* renamed from: turbogram.ProfileCategoryActivity$3 */
    class C23953 implements OnClickListener {
        C23953() {
        }

        public void onClick(View v) {
            TurboConfig.setBooleanValue("fm_notquot", !TurboConfig.saveInProfileNotQuote);
            if (v instanceof TextCheckCell) {
                ((TextCheckCell) v).setChecked(TurboConfig.saveInProfileNotQuote);
            }
        }
    }

    /* renamed from: turbogram.ProfileCategoryActivity$4 */
    class C23964 implements OnItemClickListener {
        C23964() {
        }

        public void onItemClick(View view, int position) {
            int catId = ProfileCategoryActivity.this.listAdapter.getItem(position).intValue();
            Bundle args = new Bundle();
            args.putInt("user_id", UserConfig.getInstance(ProfileCategoryActivity.this.currentAccount).getClientUserId());
            args.putInt("cat_id", catId);
            ProfileCategoryActivity.this.presentFragment(new ChatActivity(args), false);
        }
    }

    class ProfileCategoryAdapter extends SelectionAdapter {
        ArrayList<Integer> categoryIdArray = new ArrayList();
        ArrayList<String> categoryNameArray = new ArrayList();
        private DBHelper dbHelper;
        private Context mContext;

        public ProfileCategoryAdapter(Context context) {
            this.mContext = context;
            this.dbHelper = new DBHelper(this.mContext);
            getCategoryArray();
        }

        private void getCategoryArray() {
            this.categoryIdArray.clear();
            this.categoryNameArray.clear();
            this.categoryIdArray.add(Integer.valueOf(-1));
            this.categoryNameArray.add("All");
            this.categoryIdArray.add(Integer.valueOf(0));
            this.categoryNameArray.add("NotCategorized");
            this.dbHelper.open();
            try {
                this.categoryIdArray.addAll(this.dbHelper.getAllPMCategoryIds());
                this.categoryNameArray.addAll(this.dbHelper.getAllPMCategoryNames());
            } finally {
                this.dbHelper.close();
            }
        }

        public void notifyDataSetChanged() {
            getCategoryArray();
            super.notifyDataSetChanged();
        }

        public Integer getItem(int position) {
            return (Integer) this.categoryIdArray.get(position);
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TurboDocumentCell turboDocumentCell = new TurboDocumentCell(this.mContext, 10);
            turboDocumentCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            turboDocumentCell.setLayoutParams(new LayoutParams(-1, -2));
            final ViewHolder holder = new Holder(turboDocumentCell);
            turboDocumentCell.setOnMenuClick(new OnClickListener() {
                public void onClick(View v) {
                    final int position = holder.getAdapterPosition();
                    if (position > 1) {
                        Builder builder = new Builder(ProfileCategoryActivity.this.getParentActivity());
                        builder.setItems(new CharSequence[]{LocaleController.getString("TurboAddToFMCat", R.string.TurboAddToFMCat), LocaleController.getString("TurboDeleteFromFMCat", R.string.TurboDeleteFromFMCat), LocaleController.getString("TurboEditFMCatName", R.string.TurboEditFMCatName), LocaleController.getString("TurboDeleteFMCat", R.string.TurboDeleteFMCat)}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final int catId = ProfileCategoryActivity.this.listAdapter.getItem(position).intValue();
                                final DBHelper dbHelper = new DBHelper(ProfileCategoryActivity.this.context);
                                dbHelper.open();
                                switch (which) {
                                    case 0:
                                        Bundle args = new Bundle();
                                        args.putInt("user_id", UserConfig.getInstance(ProfileCategoryActivity.this.currentAccount).getClientUserId());
                                        args.putInt("cat_id", 0);
                                        args.putInt("add_to_cat", catId);
                                        ProfileCategoryActivity.this.presentFragment(new ChatActivity(args), false);
                                        break;
                                    case 1:
                                        Bundle args1 = new Bundle();
                                        args1.putInt("user_id", UserConfig.getInstance(ProfileCategoryActivity.this.currentAccount).getClientUserId());
                                        args1.putInt("cat_id", catId);
                                        args1.putInt("del_from_cat", catId);
                                        ProfileCategoryActivity.this.presentFragment(new ChatActivity(args1), false);
                                        break;
                                    case 2:
                                        ProfileCategoryActivity.this.editCategory(catId);
                                        break;
                                    case 3:
                                        Builder builder = new Builder(ProfileCategoryActivity.this.getParentActivity());
                                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                                        builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                try {
                                                    dbHelper.deletePMsByCid(catId);
                                                    dbHelper.deletePMCategoryById(catId);
                                                    ProfileCategoryActivity.this.listAdapter.notifyDataSetChanged();
                                                } finally {
                                                    dbHelper.close();
                                                }
                                            }
                                        });
                                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                        ProfileCategoryActivity.this.showDialog(builder.create());
                                        break;
                                }
                                ProfileCategoryActivity.this.listAdapter.notifyDataSetChanged();
                            }
                        });
                        ProfileCategoryActivity.this.showDialog(builder.create());
                    }
                }
            });
            return holder;
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            TurboDocumentCell documentCell = holder.itemView;
            String catName = "";
            String count = "";
            boolean showOptionButton = false;
            if (position == 0) {
                catName = LocaleController.getString("TurboAll", R.string.TurboAll);
                count = LocaleController.getString("TurboFMAllMessages", R.string.TurboFMAllMessages);
            } else if (position == 1) {
                catName = LocaleController.getString("TurboFMNotCat", R.string.TurboFMNotCat);
                count = LocaleController.getString("TurboFMNotCatMessages", R.string.TurboFMNotCatMessages);
            } else {
                showOptionButton = true;
                catName = (String) this.categoryNameArray.get(position);
                int catId = ((Integer) this.categoryIdArray.get(position)).intValue();
                this.dbHelper.open();
                try {
                    count = String.valueOf(this.dbHelper.getCategoryPMCount(catId));
                } finally {
                    this.dbHelper.close();
                }
            }
            documentCell.setData(catName, count, R.drawable.ic_directory, showOptionButton);
        }

        public int getItemCount() {
            return this.categoryIdArray.size();
        }
    }

    public View createView(Context context) {
        int i = 1;
        this.context = context;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("CategorizeProfile", R.string.CategorizeProfile));
        this.actionBar.setActionBarMenuOnItemClick(new C23931());
        this.actionBar.createMenu().addItem(1, (int) R.drawable.add);
        this.listAdapter = new ProfileCategoryAdapter(context);
        this.fragmentView = new LinearLayout(context);
        LinearLayout linearLayout = this.fragmentView;
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        TextCheckCell saveInProfileCheck = new TextCheckCell(context);
        saveInProfileCheck.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        saveInProfileCheck.setTextAndCheck(LocaleController.getString("ShowCategorizationMenu", R.string.ShowCategorizationMenu), TurboConfig.showProfileCatMenuInChat, false);
        saveInProfileCheck.setOnClickListener(new C23942());
        linearLayout.addView(saveInProfileCheck);
        TextCheckCell saveWithoutQuotingCheck = new TextCheckCell(context);
        saveWithoutQuotingCheck.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        saveWithoutQuotingCheck.setTextAndCheck(LocaleController.getString("SaveWithoutQuoting", R.string.SaveWithoutQuoting), TurboConfig.saveInProfileNotQuote, false);
        saveWithoutQuotingCheck.setOnClickListener(new C23953());
        linearLayout.addView(saveWithoutQuotingCheck);
        TextInfoPrivacyCell shadow = new TextInfoPrivacyCell(context);
        shadow.setText(null);
        shadow.setBackgroundDrawable(Theme.getThemedDrawable(context, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        linearLayout.addView(shadow);
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
        this.listView.setOnItemClickListener(new C23964());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        this.listAdapter.notifyDataSetChanged();
    }

    private void editCategory(final int catId) {
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
        final EditText editText1 = new EditText(getParentActivity());
        editText1.setTypeface(TurboUtils.getTurboTypeFace());
        editText1.setHint(LocaleController.getString("TurboAddFavoriteMessageCat", R.string.TurboAddFavoriteMessageCat));
        editText1.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText1);
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            editText1.setText(dbHelper.getPMCategoryById(catId));
            editText1.setSelection(editText1.getText().length());
            Builder builder = new Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("TurboEditCategory", R.string.TurboEditCategory));
            builder.setView(linearLayout);
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper dbHelper = new DBHelper(ProfileCategoryActivity.this.context);
                    dbHelper.open();
                    try {
                        dbHelper.editPMCategoryById(catId, editText1.getText().toString());
                        ProfileCategoryActivity.this.listAdapter.notifyDataSetChanged();
                    } finally {
                        dbHelper.close();
                    }
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
        } finally {
            dbHelper.close();
        }
    }

    private void addCategory() {
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
        final EditText editText1 = new EditText(getParentActivity());
        editText1.setTypeface(TurboUtils.getTurboTypeFace());
        editText1.setHint(LocaleController.getString("TurboAddFavoriteMessageCat", R.string.TurboAddFavoriteMessageCat));
        editText1.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText1);
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("TurboNewCategory", R.string.TurboNewCategory));
        builder.setView(linearLayout);
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(ProfileCategoryActivity.this.context);
                dbHelper.open();
                try {
                    dbHelper.addPMCategory(editText1.getText().toString());
                    ProfileCategoryActivity.this.listAdapter.notifyDataSetChanged();
                } finally {
                    dbHelper.close();
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }
}
