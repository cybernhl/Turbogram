package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.DialogsActivity;
import turbogram.Cells.TurboDocumentCell;
import turbogram.Database.DBHelper;
import turbogram.Utilities.DialogsLoader;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboUtils;

public class DialogCategoryActivity extends BaseFragment {
    Context context;
    private DialogCategoryAdapter listAdapter;
    private RecyclerListView listView;

    /* renamed from: turbogram.DialogCategoryActivity$1 */
    class C23181 extends ActionBarMenuOnItemClick {
        C23181() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                DialogCategoryActivity.this.finishFragment();
            } else if (id == 1) {
                DialogCategoryActivity.this.addCategory();
            }
        }
    }

    /* renamed from: turbogram.DialogCategoryActivity$2 */
    class C23192 implements OnItemClickListener {
        C23192() {
        }

        public void onItemClick(View view, int position) {
            int catId = DialogCategoryActivity.this.listAdapter.getItem(position).intValue();
            if (catId > 1) {
                DialogsLoader.getCatDialogs(catId);
            }
            TurboConfig$BG.setIntValue("current_cat", catId);
            DialogCategoryActivity.this.finishFragment();
            NotificationCenter.getInstance(DialogCategoryActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(6));
        }
    }

    class DialogCategoryAdapter extends SelectionAdapter {
        ArrayList<Integer> categoryIdArray = new ArrayList();
        ArrayList<String> categoryNameArray = new ArrayList();
        private DBHelper dbHelper;
        private Context mContext;

        public DialogCategoryAdapter(Context context) {
            this.mContext = context;
            this.dbHelper = new DBHelper(this.mContext);
            getFavoriteMessagesArray();
        }

        private void getFavoriteMessagesArray() {
            this.categoryIdArray.clear();
            this.categoryNameArray.clear();
            this.categoryIdArray.add(Integer.valueOf(-3));
            this.categoryNameArray.add("All");
            this.categoryIdArray.add(Integer.valueOf(-2));
            this.categoryNameArray.add("Unread");
            this.categoryIdArray.add(Integer.valueOf(-4));
            this.categoryNameArray.add("Mine");
            this.categoryIdArray.add(Integer.valueOf(-1));
            this.categoryNameArray.add("Unmute");
            this.categoryIdArray.add(Integer.valueOf(0));
            this.categoryNameArray.add("Mute");
            this.dbHelper.open();
            try {
                this.categoryIdArray.addAll(this.dbHelper.getAllCategoryIds());
                this.categoryNameArray.addAll(this.dbHelper.getAllCategoryNames());
            } finally {
                this.dbHelper.close();
            }
        }

        public void notifyDataSetChanged() {
            getFavoriteMessagesArray();
            super.notifyDataSetChanged();
        }

        public Integer getItem(int position) {
            return (Integer) this.categoryIdArray.get(position);
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            TurboDocumentCell turboDocumentCell = new TurboDocumentCell(this.mContext, 10);
            turboDocumentCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            turboDocumentCell.setLayoutParams(new LayoutParams(-1, -2));
            final ViewHolder holder = new Holder(turboDocumentCell);
            turboDocumentCell.setOnMenuClick(new OnClickListener() {
                public void onClick(View v) {
                    final int position = holder.getAdapterPosition();
                    if (position > 4) {
                        Builder builder = new Builder(DialogCategoryActivity.this.getParentActivity());
                        builder.setItems(new CharSequence[]{LocaleController.getString("TurboAddDialogs", R.string.TurboAddDialogs), LocaleController.getString("TurboDeleteDialogs", R.string.TurboDeleteDialogs), LocaleController.getString("TurboEditFMCatName", R.string.TurboEditFMCatName), LocaleController.getString("TurboDeleteFMCat", R.string.TurboDeleteFMCat)}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final int catId = DialogCategoryAdapter.this.getItem(position).intValue();
                                Bundle args = new Bundle();
                                args.putBoolean("onlySelect", true);
                                args.putBoolean("catSelection", true);
                                DialogsActivity fragment = new DialogsActivity(args);
                                final DBHelper dbHelper = new DBHelper(DialogCategoryActivity.this.context);
                                dbHelper.open();
                                switch (which) {
                                    case 0:
                                        args.putInt("op_type", 1);
                                        args.putInt("cat_id", catId);
                                        DialogCategoryActivity.this.presentFragment(fragment, false);
                                        break;
                                    case 1:
                                        args.putInt("op_type", 2);
                                        args.putInt("cat_id", catId);
                                        DialogCategoryActivity.this.presentFragment(fragment, false);
                                        break;
                                    case 2:
                                        DialogCategoryActivity.this.editCategory(catId);
                                        break;
                                    case 3:
                                        Builder builder = new Builder(DialogCategoryActivity.this.getParentActivity());
                                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                                        builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
                                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                try {
                                                    dbHelper.deleteDialogsByCid(catId);
                                                    dbHelper.deleteCategoryById(catId);
                                                    DialogCategoryAdapter.this.notifyDataSetChanged();
                                                } finally {
                                                    dbHelper.close();
                                                }
                                            }
                                        });
                                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                        DialogCategoryActivity.this.showDialog(builder.create());
                                        break;
                                }
                                DialogCategoryAdapter.this.notifyDataSetChanged();
                            }
                        });
                        DialogCategoryActivity.this.showDialog(builder.create());
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
                count = LocaleController.getString("TurboAllDialogs", R.string.TurboAllDialogs);
            } else if (position == 1) {
                catName = LocaleController.getString("TurboUnread", R.string.TurboUnread);
                count = LocaleController.getString("TurboUnreadDialogs", R.string.TurboUnreadDialogs);
            } else if (position == 2) {
                catName = LocaleController.getString("TurboMine", R.string.TurboMine);
                count = LocaleController.getString("TurboMyDialogs", R.string.TurboMyDialogs);
            } else if (position == 3) {
                catName = LocaleController.getString("TurboUnmute", R.string.TurboUnmute);
                count = LocaleController.getString("TurboUnmuteDialogs", R.string.TurboUnmuteDialogs);
            } else if (position == 4) {
                catName = LocaleController.getString("TurboMute", R.string.TurboMute);
                count = LocaleController.getString("TurboMuteDialogs", R.string.TurboMuteDialogs);
            } else {
                showOptionButton = true;
                catName = (String) this.categoryNameArray.get(position);
                int catId = ((Integer) this.categoryIdArray.get(position)).intValue();
                this.dbHelper.open();
                try {
                    count = String.valueOf(this.dbHelper.getCategoryDCount(catId));
                } finally {
                    this.dbHelper.close();
                }
            }
            documentCell.setData(catName, count, R.drawable.ic_directory, showOptionButton);
        }

        public int getItemCount() {
            return this.categoryNameArray.size();
        }
    }

    public View createView(Context context) {
        int i = 1;
        this.context = context;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TurboDialogCategories", R.string.TurboDialogCategories));
        this.actionBar.setActionBarMenuOnItemClick(new C23181());
        this.actionBar.createMenu().addItem(1, (int) R.drawable.add);
        this.listAdapter = new DialogCategoryAdapter(context);
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView recyclerListView = this.listView;
        if (!LocaleController.isRTL) {
            i = 2;
        }
        recyclerListView.setVerticalScrollbarPosition(i);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C23192());
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
            editText1.setText(dbHelper.getCategoryById(catId));
            editText1.setSelection(editText1.getText().length());
            Builder builder = new Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("TurboEditCategory", R.string.TurboEditCategory));
            builder.setView(linearLayout);
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper dbHelper = new DBHelper(DialogCategoryActivity.this.context);
                    dbHelper.open();
                    try {
                        dbHelper.editCategoryById(catId, editText1.getText().toString());
                        DialogCategoryActivity.this.listAdapter.notifyDataSetChanged();
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
                DBHelper dbHelper = new DBHelper(DialogCategoryActivity.this.context);
                dbHelper.open();
                try {
                    dbHelper.addCategory(editText1.getText().toString());
                    DialogCategoryActivity.this.listAdapter.notifyDataSetChanged();
                } finally {
                    dbHelper.close();
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }
}
