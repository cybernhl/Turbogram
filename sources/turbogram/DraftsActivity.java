package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
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
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import turbogram.Cells.TurboDocumentCell;
import turbogram.Database.DBHelper;
import turbogram.Models.Draft;
import turbogram.Utilities.TurboUtils;

public class DraftsActivity extends BaseFragment {
    Context context;
    private DraftsActivityDelegate delegate;
    ArrayList<Draft> drafts = new ArrayList();
    private EmptyTextProgressView emptyView;
    private RecyclerListAdapter listAdapter;
    private RecyclerListView listView;

    public interface DraftsActivityDelegate {
        void onItemSelected(String str);
    }

    /* renamed from: turbogram.DraftsActivity$1 */
    class C23491 extends ActionBarMenuOnItemClick {
        C23491() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                DraftsActivity.this.finishFragment();
            } else if (id == 1) {
                DraftsActivity.this.addDraft();
            }
        }
    }

    /* renamed from: turbogram.DraftsActivity$2 */
    class C23502 implements OnItemClickListener {
        C23502() {
        }

        public void onItemClick(View view, int position) {
            if (DraftsActivity.this.delegate != null) {
                DraftsActivity.this.delegate.onItemSelected(DraftsActivity.this.listAdapter.getItem(position));
                DraftsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.DraftsActivity$3 */
    class C23513 implements OnTouchListener {
        C23513() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 1:
                    DraftsActivity.this.delAllDrafts();
                    DraftsActivity.this.addAllDrafts();
                    if (DraftsActivity.this.listAdapter != null) {
                        DraftsActivity.this.listAdapter.notifyDataSetChanged();
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

    public class ItemViewHolder extends ViewHolder implements OnClickListener, ViewHolderListener {
        public TurboDocumentCell draftCell;

        ItemViewHolder(View view) {
            super(view);
            this.draftCell = (TurboDocumentCell) view;
            if (this.draftCell.getChildAt(3) instanceof ImageView) {
                this.draftCell.getChildAt(3).setOnClickListener(this);
            }
        }

        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }

        public void onItemSelected() {
            this.itemView.setBackgroundColor(Theme.getColor(Theme.key_graySection));
        }

        public void onClick(View v) {
            Builder builder = new Builder(DraftsActivity.this.getParentActivity());
            ArrayList<CharSequence> items = new ArrayList();
            final ArrayList<Integer> options = new ArrayList();
            items.add(LocaleController.getString("Copy", R.string.Copy));
            options.add(Integer.valueOf(0));
            items.add(LocaleController.getString("Edit", R.string.Edit));
            options.add(Integer.valueOf(1));
            items.add(LocaleController.getString("Delete", R.string.Delete));
            options.add(Integer.valueOf(2));
            builder.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (((Integer) options.get(i)).intValue() == 0) {
                        AndroidUtilities.addToClipboard(((Draft) DraftsActivity.this.drafts.get(ItemViewHolder.this.getPosition())).getText());
                    } else if (((Integer) options.get(i)).intValue() == 1) {
                        DraftsActivity.this.editDraft(((Draft) DraftsActivity.this.drafts.get(ItemViewHolder.this.getPosition())).getTitle(), ((Draft) DraftsActivity.this.drafts.get(ItemViewHolder.this.getPosition())).getText());
                    } else if (((Integer) options.get(i)).intValue() == 2) {
                        DraftsActivity.this.delDraft(((Draft) DraftsActivity.this.drafts.get(ItemViewHolder.this.getPosition())).getTitle());
                    }
                }
            });
            DraftsActivity.this.showDialog(builder.create());
        }
    }

    private class RecyclerListAdapter extends Adapter<ItemViewHolder> implements ItemTouchHelperListener {
        public RecyclerListAdapter() {
            getAllDrafts();
        }

        public void notifyDataSetChanged() {
            getAllDrafts();
            super.notifyDataSetChanged();
        }

        private void getAllDrafts() {
            DraftsActivity.this.drafts.clear();
            DBHelper DBHelper = new DBHelper(DraftsActivity.this.context);
            DBHelper.open();
            try {
                DraftsActivity.this.drafts.addAll(DBHelper.getAllDrafts());
            } finally {
                DBHelper.close();
            }
        }

        public int getItemCount() {
            return DraftsActivity.this.drafts.size();
        }

        public String getItem(int position) {
            return ((Draft) DraftsActivity.this.drafts.get(position)).getText();
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TurboDocumentCell itemCell = new TurboDocumentCell(parent.getContext(), 10);
            itemCell.setLayoutParams(new LayoutParams(-1, -2));
            return new ItemViewHolder(itemCell);
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
            itemViewHolder.draftCell.setData(((Draft) DraftsActivity.this.drafts.get(position)).getTitle(), ((Draft) DraftsActivity.this.drafts.get(position)).getText(), R.drawable.turbo_ic_draft, true);
        }

        public void swapElements(int position1, int position2) {
            String title1 = ((Draft) DraftsActivity.this.drafts.get(position1)).getTitle();
            String text1 = ((Draft) DraftsActivity.this.drafts.get(position1)).getText();
            DraftsActivity.this.drafts.set(position1, new Draft(((Draft) DraftsActivity.this.drafts.get(position2)).getTitle(), ((Draft) DraftsActivity.this.drafts.get(position2)).getText()));
            DraftsActivity.this.drafts.set(position2, new Draft(title1, text1));
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

    public View createView(Context context) {
        this.context = context;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("Drafts", R.string.Drafts));
        this.actionBar.setActionBarMenuOnItemClick(new C23491());
        this.actionBar.createMenu().addItem(1, (int) R.drawable.add);
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView = new EmptyTextProgressView(context);
        this.emptyView.setShowAtCenter(true);
        this.emptyView.showTextView();
        this.emptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
        frameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(context);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.listView.setEmptyView(this.emptyView);
        this.listView.setFocusable(true);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        linearLayout.addView(this.listView, LayoutHelper.createLinear(-1, 0, 1.0f, 3));
        this.listView.setOnItemClickListener(new C23502());
        this.listView.setOnTouchListener(new C23513());
        this.listAdapter = new RecyclerListAdapter();
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.listAdapter)).attachToRecyclerView(this.listView);
        this.listView.setAdapter(this.listAdapter);
        TextInfoPrivacyCell textInfoPrivacyCell = new TextInfoPrivacyCell(context);
        textInfoPrivacyCell.setText(LocaleController.getString("DraftDes", R.string.DraftDes));
        textInfoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(context, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        linearLayout.addView(textInfoPrivacyCell, LayoutHelper.createLinear(-1, -2, 80));
        return this.fragmentView;
    }

    private void addDraft() {
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
        final EditTextBoldCursor editText1 = new EditTextBoldCursor(getParentActivity());
        editText1.setTypeface(TurboUtils.getTurboTypeFace());
        editText1.setHint(LocaleController.getString("DraftTitle", R.string.DraftTitle));
        editText1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        editText1.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        editText1.setCursorSize(AndroidUtilities.dp(20.0f));
        editText1.setCursorWidth(1.5f);
        editText1.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText1);
        final EditTextBoldCursor editText2 = new EditTextBoldCursor(getParentActivity());
        editText2.setTypeface(TurboUtils.getTurboTypeFace());
        editText2.setHint(LocaleController.getString("DraftText", R.string.DraftText));
        editText2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        editText2.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        editText2.setCursorSize(AndroidUtilities.dp(20.0f));
        editText2.setCursorWidth(1.5f);
        editText2.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText2);
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("DraftNew", R.string.DraftNew));
        builder.setView(linearLayout);
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editText1.getText().toString();
                String text = editText2.getText().toString();
                if (!title.equals("") && !text.equals("")) {
                    DBHelper dbHelper = new DBHelper(DraftsActivity.this.context);
                    dbHelper.open();
                    try {
                        if (!dbHelper.ifExistsDTitle(title)) {
                            dbHelper.addDraft(title, text);
                        }
                        dbHelper.close();
                        DraftsActivity.this.listAdapter.notifyDataSetChanged();
                    } catch (Throwable th) {
                        dbHelper.close();
                    }
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void delDraft(final String title) {
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
        builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(DraftsActivity.this.context);
                dbHelper.open();
                try {
                    dbHelper.deleteDraft(title);
                    DraftsActivity.this.listAdapter.notifyDataSetChanged();
                } finally {
                    dbHelper.close();
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void editDraft(String title, String text) {
        final String oldTitle = title;
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
        final EditTextBoldCursor editText1 = new EditTextBoldCursor(getParentActivity());
        editText1.setText(title);
        editText1.setSelection(editText1.getText().length());
        editText1.setTypeface(TurboUtils.getTurboTypeFace());
        editText1.setHint(LocaleController.getString("DraftTitle", R.string.DraftTitle));
        editText1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        editText1.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        editText1.setCursorSize(AndroidUtilities.dp(20.0f));
        editText1.setCursorWidth(1.5f);
        editText1.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText1);
        final EditTextBoldCursor editText2 = new EditTextBoldCursor(getParentActivity());
        editText2.setText(text);
        editText2.setSelection(editText2.getText().length());
        editText2.setTypeface(TurboUtils.getTurboTypeFace());
        editText2.setHint(LocaleController.getString("DraftText", R.string.DraftText));
        editText2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        editText2.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        editText2.setCursorSize(AndroidUtilities.dp(20.0f));
        editText2.setCursorWidth(1.5f);
        editText2.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText2);
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("Edit", R.string.Edit));
        builder.setView(linearLayout);
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editText1.getText().toString();
                String text = editText2.getText().toString();
                if (!title.equals("") && !text.equals("")) {
                    DBHelper dbHelper = new DBHelper(DraftsActivity.this.context);
                    dbHelper.open();
                    try {
                        if (!(dbHelper.ifExistsDTitle(title) && dbHelper.ifExistsDText(text))) {
                            dbHelper.editDraft(oldTitle, title, text);
                        }
                        dbHelper.close();
                        DraftsActivity.this.listAdapter.notifyDataSetChanged();
                    } catch (Throwable th) {
                        dbHelper.close();
                    }
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void delAllDrafts() {
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            dbHelper.deleteAllDrafts();
        } finally {
            dbHelper.close();
        }
    }

    private void addAllDrafts() {
        DBHelper DBHelper = new DBHelper(this.context);
        DBHelper.open();
        int i = 0;
        while (i < this.drafts.size()) {
            try {
                DBHelper.addDraft(((Draft) this.drafts.get(i)).getTitle(), ((Draft) this.drafts.get(i)).getText());
                i++;
            } finally {
                DBHelper.close();
            }
        }
    }

    public void setDelegate(DraftsActivityDelegate delegate) {
        this.delegate = delegate;
    }
}
