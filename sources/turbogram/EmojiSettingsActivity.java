package turbogram;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig;

public class EmojiSettingsActivity extends BaseFragment {
    private int allowbigEmojiDesRow;
    private int allowbigEmojiRow;
    long dialog_id = 0;
    private int dontHideStickerTabDesRow;
    private int dontHideStickerTabRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int previewStickerDesRow;
    private int previewStickerRow;
    private int rowCount = 0;

    /* renamed from: turbogram.EmojiSettingsActivity$1 */
    class C23561 extends ActionBarMenuOnItemClick {
        C23561() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                EmojiSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.EmojiSettingsActivity$2 */
    class C23572 implements OnItemClickListener {
        C23572() {
        }

        public void onItemClick(View view, int position) {
            boolean z = true;
            String str;
            if (position == EmojiSettingsActivity.this.previewStickerRow) {
                str = "preview_sticker";
                if (TurboConfig.previewSticker) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.previewSticker);
                }
            } else if (position == EmojiSettingsActivity.this.allowbigEmojiRow) {
                Editor editor = MessagesController.getGlobalMainSettings().edit();
                str = "allowBigEmoji";
                if (SharedConfig.allowBigEmoji) {
                    z = false;
                }
                SharedConfig.allowBigEmoji = z;
                editor.putBoolean(str, z);
                editor.commit();
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(SharedConfig.allowBigEmoji);
                }
            } else if (position == EmojiSettingsActivity.this.dontHideStickerTabRow) {
                str = "dont_hide_stab";
                if (TurboConfig.dontHideStickertab) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.dontHideStickertab);
                }
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
            return position == EmojiSettingsActivity.this.previewStickerRow || position == EmojiSettingsActivity.this.allowbigEmojiRow || position == EmojiSettingsActivity.this.dontHideStickerTabRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == EmojiSettingsActivity.this.previewStickerRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("PreviewSticker", R.string.PreviewSticker), TurboConfig.previewSticker, false);
                        return;
                    } else if (position == EmojiSettingsActivity.this.allowbigEmojiRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("TurboEmojiBigSize", R.string.TurboEmojiBigSize), SharedConfig.allowBigEmoji, false);
                        return;
                    } else if (position == EmojiSettingsActivity.this.dontHideStickerTabRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("DontHideStickerTab", R.string.DontHideStickerTab), TurboConfig.dontHideStickertab, false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == EmojiSettingsActivity.this.previewStickerDesRow) {
                        privacyCell.setText(LocaleController.getString("PreviewStickerDes", R.string.PreviewStickerDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == EmojiSettingsActivity.this.allowbigEmojiDesRow) {
                        privacyCell.setText(LocaleController.getString("BigEmojiDes", R.string.BigEmojiDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == EmojiSettingsActivity.this.dontHideStickerTabDesRow) {
                        privacyCell.setText(LocaleController.getString("DontHideStickerTabDes", R.string.DontHideStickerTabDes));
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
            return EmojiSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == EmojiSettingsActivity.this.previewStickerRow || position == EmojiSettingsActivity.this.allowbigEmojiRow || position == EmojiSettingsActivity.this.dontHideStickerTabRow) {
                return 0;
            }
            if (position == EmojiSettingsActivity.this.previewStickerDesRow || position == EmojiSettingsActivity.this.allowbigEmojiDesRow || position == EmojiSettingsActivity.this.dontHideStickerTabDesRow) {
                return 1;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.previewStickerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.previewStickerDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.allowbigEmojiRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.allowbigEmojiDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.dontHideStickerTabRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.dontHideStickerTabDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("EmojiAndSticker", R.string.EmojiAndSticker));
        this.actionBar.setActionBarMenuOnItemClick(new C23561());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C23572());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }
}
