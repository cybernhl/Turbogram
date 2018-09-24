package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig$Storage;
import turbogram.Utilities.TurboUtils;

public class StorageSettingsActivity extends BaseFragment {
    private int folderDesRow;
    private int folderRow;
    private int keepNameDesRow;
    private int keepNameRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int rowCount = 0;
    private int storageDesRow;
    private int storageRow;

    /* renamed from: turbogram.StorageSettingsActivity$1 */
    class C24431 extends ActionBarMenuOnItemClick {
        C24431() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                StorageSettingsActivity.this.finishFragment();
            }
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            int position = holder.getAdapterPosition();
            return position == StorageSettingsActivity.this.storageRow || position == StorageSettingsActivity.this.folderRow || position == StorageSettingsActivity.this.keepNameRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TextSettingsCell textCell = holder.itemView;
                    if (position == StorageSettingsActivity.this.storageRow) {
                        textCell.setText(LocaleController.getString("StorageDevice", R.string.StorageDevice), false);
                        return;
                    } else if (position == StorageSettingsActivity.this.folderRow) {
                        textCell.setTextAndValue(LocaleController.getString("StorageFolderName", R.string.StorageFolderName), TurboConfig$Storage.folderName, false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == StorageSettingsActivity.this.keepNameRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("KeepOriginalFileName", R.string.KeepOriginalFileName), TurboConfig$Storage.keepFilename, false);
                        return;
                    }
                    return;
                case 2:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == StorageSettingsActivity.this.storageDesRow) {
                        privacyCell.setText(LocaleController.getString("StorageDeviceDes", R.string.StorageDeviceDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == StorageSettingsActivity.this.folderDesRow) {
                        privacyCell.setText(LocaleController.getString("StorageFolderNameDes", R.string.StorageFolderNameDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == StorageSettingsActivity.this.keepNameDesRow) {
                        privacyCell.setText(LocaleController.getString("KeepOriginalFileNameDes", R.string.KeepOriginalFileNameDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }

        public int getItemCount() {
            return StorageSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == StorageSettingsActivity.this.storageRow || position == StorageSettingsActivity.this.folderRow) {
                return 0;
            }
            if (position == StorageSettingsActivity.this.keepNameRow) {
                return 1;
            }
            return 2;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.storageRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.storageDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.folderRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.folderDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepNameRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepNameDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(final Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("StorageSettings", R.string.StorageSettings));
        this.actionBar.setActionBarMenuOnItemClick(new C24431());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(View view, final int position) {
                boolean z = true;
                if (position == StorageSettingsActivity.this.storageRow) {
                    StorageSettingsActivity.this.presentFragment(new StorageSelectActivity());
                } else if (position == StorageSettingsActivity.this.folderRow) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(1);
                    linearLayout.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
                    final EditTextBoldCursor editText = new EditTextBoldCursor(StorageSettingsActivity.this.getParentActivity());
                    editText.setTypeface(TurboUtils.getTurboTypeFace());
                    editText.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
                    editText.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                    editText.setCursorSize(AndroidUtilities.dp(20.0f));
                    editText.setCursorWidth(1.5f);
                    editText.setHint("Telegram");
                    editText.setText(TurboConfig$Storage.folderName);
                    editText.setSelection(editText.getText().length());
                    editText.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f));
                    linearLayout.addView(editText);
                    Builder builder = new Builder(StorageSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("StorageNewName", R.string.StorageNewName));
                    builder.setView(linearLayout);
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String newDir = editText.getText().toString();
                            if (!newDir.contains("\u0000") && !newDir.contains("/")) {
                                TurboConfig$Storage.setStringValue("folder_name", newDir);
                                final SparseArray<File> paths = ImageLoader.getInstance().createMediaPaths();
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    public void run() {
                                        FileLoader.getInstance(StorageSettingsActivity.this.currentAccount);
                                        FileLoader.setMediaDirs(paths);
                                    }
                                });
                                if (StorageSettingsActivity.this.listAdapter != null) {
                                    StorageSettingsActivity.this.listAdapter.notifyItemChanged(position);
                                }
                            }
                        }
                    });
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    StorageSettingsActivity.this.showDialog(builder.create());
                } else if (position == StorageSettingsActivity.this.keepNameRow) {
                    String str = "keep_filename";
                    if (TurboConfig$Storage.keepFilename) {
                        z = false;
                    }
                    TurboConfig$Storage.setBooleanValue(str, z);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Storage.keepFilename);
                    }
                }
            }
        });
        return this.fragmentView;
    }
}
