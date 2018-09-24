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
import turbogram.Cells.TurboDocumentCell;
import turbogram.Database.DBHelper;
import turbogram.Models.TagLink;
import turbogram.Utilities.TurboUtils;

public class TagLinksActivity extends BaseFragment {
    ArrayList<TagLink> Tags = new ArrayList();
    Context context;
    private EmptyTextProgressView emptyView;
    private RecyclerListAdapter listAdapter;
    private RecyclerListView listView;

    /* renamed from: turbogram.TagLinksActivity$1 */
    class C24541 extends ActionBarMenuOnItemClick {
        C24541() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TagLinksActivity.this.finishFragment();
            } else if (id == 1) {
                TagLinksActivity.this.addTag();
            }
        }
    }

    /* renamed from: turbogram.TagLinksActivity$2 */
    class C24552 implements OnTouchListener {
        C24552() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 1:
                    TagLinksActivity.this.delAllTags();
                    TagLinksActivity.this.addAllTags();
                    if (TagLinksActivity.this.listAdapter != null) {
                        TagLinksActivity.this.listAdapter.notifyDataSetChanged();
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
        public TurboDocumentCell TagCell;

        ItemViewHolder(View view) {
            super(view);
            this.TagCell = (TurboDocumentCell) view;
            if (this.TagCell.getChildAt(3) instanceof ImageView) {
                this.TagCell.getChildAt(3).setOnClickListener(this);
            }
        }

        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }

        public void onItemSelected() {
            this.itemView.setBackgroundColor(Theme.getColor(Theme.key_graySection));
        }

        public void onClick(View v) {
            Builder builder = new Builder(TagLinksActivity.this.getParentActivity());
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
                        AndroidUtilities.addToClipboard(((TagLink) TagLinksActivity.this.Tags.get(ItemViewHolder.this.getPosition())).getTag());
                    } else if (((Integer) options.get(i)).intValue() == 1) {
                        TagLinksActivity.this.editTag(((TagLink) TagLinksActivity.this.Tags.get(ItemViewHolder.this.getPosition())).getTitle(), ((TagLink) TagLinksActivity.this.Tags.get(ItemViewHolder.this.getPosition())).getTag());
                    } else if (((Integer) options.get(i)).intValue() == 2) {
                        TagLinksActivity.this.delTag(((TagLink) TagLinksActivity.this.Tags.get(ItemViewHolder.this.getPosition())).getTag());
                    }
                }
            });
            TagLinksActivity.this.showDialog(builder.create());
        }
    }

    private class RecyclerListAdapter extends Adapter<ItemViewHolder> implements ItemTouchHelperListener {
        public RecyclerListAdapter() {
            getAllTags();
        }

        public void notifyDataSetChanged() {
            getAllTags();
            super.notifyDataSetChanged();
        }

        private void getAllTags() {
            TagLinksActivity.this.Tags.clear();
            DBHelper DBHelper = new DBHelper(TagLinksActivity.this.context);
            DBHelper.open();
            try {
                TagLinksActivity.this.Tags.addAll(DBHelper.getAllTags());
            } finally {
                DBHelper.close();
            }
        }

        public int getItemCount() {
            return TagLinksActivity.this.Tags.size();
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TurboDocumentCell itemCell = new TurboDocumentCell(parent.getContext(), 10);
            itemCell.setLayoutParams(new LayoutParams(-1, -2));
            return new ItemViewHolder(itemCell);
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
            itemViewHolder.TagCell.setData(((TagLink) TagLinksActivity.this.Tags.get(position)).getTitle(), ((TagLink) TagLinksActivity.this.Tags.get(position)).getTag(), R.drawable.turbo_ic_taglink, true);
        }

        public void swapElements(int position1, int position2) {
            String title1 = ((TagLink) TagLinksActivity.this.Tags.get(position1)).getTitle();
            String tag1 = ((TagLink) TagLinksActivity.this.Tags.get(position1)).getTag();
            TagLinksActivity.this.Tags.set(position1, new TagLink(((TagLink) TagLinksActivity.this.Tags.get(position2)).getTitle(), ((TagLink) TagLinksActivity.this.Tags.get(position2)).getTag()));
            TagLinksActivity.this.Tags.set(position2, new TagLink(title1, tag1));
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
        this.actionBar.setTitle(LocaleController.getString("TagLinks", R.string.TagLinks));
        this.actionBar.setActionBarMenuOnItemClick(new C24541());
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
        this.listView.setOnTouchListener(new C24552());
        this.listAdapter = new RecyclerListAdapter();
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.listAdapter)).attachToRecyclerView(this.listView);
        this.listView.setAdapter(this.listAdapter);
        TextInfoPrivacyCell textInfoPrivacyCell = new TextInfoPrivacyCell(context);
        textInfoPrivacyCell.setText(LocaleController.getString("TagLinkDes", R.string.TagLinkDes));
        textInfoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(context, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        linearLayout.addView(textInfoPrivacyCell, LayoutHelper.createLinear(-1, -2, 80));
        return this.fragmentView;
    }

    private void addTag() {
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
        editText2.setHint(LocaleController.getString("TagTitle", R.string.TagLink));
        editText2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        editText2.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        editText2.setCursorSize(AndroidUtilities.dp(20.0f));
        editText2.setCursorWidth(1.5f);
        editText2.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
        linearLayout.addView(editText2);
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("NewTag", R.string.TagNewLink));
        builder.setView(linearLayout);
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editText1.getText().toString();
                String tag = editText2.getText().toString();
                if (!title.equals("") && !tag.equals("")) {
                    DBHelper dbHelper = new DBHelper(TagLinksActivity.this.context);
                    dbHelper.open();
                    try {
                        if (!dbHelper.ifExistsTag(tag)) {
                            dbHelper.addTag(title, tag);
                        }
                        dbHelper.close();
                        TagLinksActivity.this.listAdapter.notifyDataSetChanged();
                    } catch (Throwable th) {
                        dbHelper.close();
                    }
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void delTag(final String title) {
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
        builder.setMessage(LocaleController.getString("AreYouSureToContinue", R.string.AreYouSureToContinue));
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(TagLinksActivity.this.context);
                dbHelper.open();
                try {
                    dbHelper.deleteTag(title);
                    TagLinksActivity.this.listAdapter.notifyDataSetChanged();
                } finally {
                    dbHelper.close();
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void editTag(String title, String tag) {
        final String oldTag = tag;
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
        editText2.setText(tag);
        editText2.setSelection(editText2.getText().length());
        editText2.setTypeface(TurboUtils.getTurboTypeFace());
        editText2.setHint(LocaleController.getString("TagTitle", R.string.TagLink));
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
                String tag = editText2.getText().toString();
                if (!title.equals("") && !tag.equals("")) {
                    DBHelper dbHelper = new DBHelper(TagLinksActivity.this.context);
                    dbHelper.open();
                    try {
                        if (!(dbHelper.ifExistsTag(tag) && dbHelper.ifExistsTitle(title))) {
                            dbHelper.editTag(oldTag, title, tag);
                        }
                        dbHelper.close();
                        TagLinksActivity.this.listAdapter.notifyDataSetChanged();
                    } catch (Throwable th) {
                        dbHelper.close();
                    }
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void delAllTags() {
        DBHelper dbHelper = new DBHelper(this.context);
        dbHelper.open();
        try {
            dbHelper.deleteAllTags();
        } finally {
            dbHelper.close();
        }
    }

    private void addAllTags() {
        DBHelper DBHelper = new DBHelper(this.context);
        DBHelper.open();
        int i = 0;
        while (i < this.Tags.size()) {
            try {
                DBHelper.addTag(((TagLink) this.Tags.get(i)).getTitle(), ((TagLink) this.Tags.get(i)).getTag());
                i++;
            } finally {
                DBHelper.close();
            }
        }
    }
}
