package turbogram;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig;

public class TPrivacySettingsActivity extends BaseFragment {
    private int hiddenChatsDesRow;
    private int hiddenChatsRow;
    private int hidePhoneDesRow;
    private int hidePhoneRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int lockChatsDesRow;
    private int lockChatsRow;
    private int rowCount = 0;

    /* renamed from: turbogram.TPrivacySettingsActivity$1 */
    class C24471 extends ActionBarMenuOnItemClick {
        C24471() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TPrivacySettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.TPrivacySettingsActivity$2 */
    class C24482 implements OnItemClickListener {
        C24482() {
        }

        public void onItemClick(View view, int position) {
            boolean z = true;
            if (position == TPrivacySettingsActivity.this.hiddenChatsRow) {
                if (TurboConfig.chatHidePasscode.length() != 0) {
                    TPrivacySettingsActivity.this.presentFragment(new SetPasscodeActivity(2, 0));
                } else {
                    TPrivacySettingsActivity.this.presentFragment(new SetPasscodeActivity(0, 0));
                }
            } else if (position == TPrivacySettingsActivity.this.hidePhoneRow) {
                String str = "hide_phone";
                if (TurboConfig.hidePhone) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.hidePhone);
                }
            } else if (position != TPrivacySettingsActivity.this.lockChatsRow) {
            } else {
                if (TurboConfig.chatLockPasscode.length() != 0) {
                    TPrivacySettingsActivity.this.presentFragment(new SetPasscodeActivity(2, 1));
                } else {
                    TPrivacySettingsActivity.this.presentFragment(new SetPasscodeActivity(0, 1));
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
            return position == TPrivacySettingsActivity.this.hiddenChatsRow || position == TPrivacySettingsActivity.this.hidePhoneRow || position == TPrivacySettingsActivity.this.lockChatsRow;
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
                    if (position == TPrivacySettingsActivity.this.hiddenChatsRow) {
                        textCell.setText(LocaleController.getString("TurboHideChats", R.string.TurboHideChats), false);
                        return;
                    } else if (position == TPrivacySettingsActivity.this.lockChatsRow) {
                        textCell.setText(LocaleController.getString("TurboLockChats", R.string.TurboLockChats), false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == TPrivacySettingsActivity.this.hidePhoneRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("HidePhone", R.string.HidePhone), TurboConfig.hidePhone, false);
                        return;
                    }
                    return;
                case 2:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == TPrivacySettingsActivity.this.hiddenChatsDesRow) {
                        privacyCell.setText(LocaleController.getString("HiddenChatsDescription", R.string.HiddenChatsDescription));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == TPrivacySettingsActivity.this.lockChatsDesRow) {
                        privacyCell.setText(LocaleController.getString("TurboLockChatDes", R.string.TurboLockChatDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == TPrivacySettingsActivity.this.hidePhoneDesRow) {
                        privacyCell.setText(LocaleController.getString("HideNumberDescription", R.string.HideNumberDescription));
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
            return TPrivacySettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == TPrivacySettingsActivity.this.hiddenChatsRow || position == TPrivacySettingsActivity.this.lockChatsRow) {
                return 0;
            }
            if (position == TPrivacySettingsActivity.this.hidePhoneRow) {
                return 1;
            }
            return 2;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.hiddenChatsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hiddenChatsDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.lockChatsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.lockChatsDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hidePhoneRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hidePhoneDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TurboPrivacySettings", R.string.TurboPrivacySettings));
        this.actionBar.setActionBarMenuOnItemClick(new C24471());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24482());
        return this.fragmentView;
    }
}
