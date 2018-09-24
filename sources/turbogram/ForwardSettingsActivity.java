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
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig;

public class ForwardSettingsActivity extends BaseFragment {
    private int enableTabsInDirectFRow;
    private int fastReplyRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int replyVibrationRow;
    private int rowCount = 0;
    private int voiceSwipeReplyDesRow;
    private int voiceSwipeReplyRow;

    /* renamed from: turbogram.ForwardSettingsActivity$1 */
    class C23601 extends ActionBarMenuOnItemClick {
        C23601() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ForwardSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.ForwardSettingsActivity$2 */
    class C23612 implements OnItemClickListener {
        C23612() {
        }

        public void onItemClick(View view, int position) {
            boolean z = true;
            String str;
            if (position == ForwardSettingsActivity.this.enableTabsInDirectFRow) {
                str = "multi_forward_tabs";
                if (TurboConfig.multiForwardTabs) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.multiForwardTabs);
                }
            } else if (position == ForwardSettingsActivity.this.fastReplyRow) {
                str = "swipe_reply";
                if (TurboConfig.swipeToReply) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.swipeToReply);
                }
            } else if (position == ForwardSettingsActivity.this.replyVibrationRow) {
                str = "reply_vibration";
                if (TurboConfig.replyVibration) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.replyVibration);
                }
            } else if (position == ForwardSettingsActivity.this.voiceSwipeReplyRow) {
                str = "swipe_voice";
                if (TurboConfig.swipeVoice) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.swipeVoice);
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
            return position == ForwardSettingsActivity.this.enableTabsInDirectFRow || position == ForwardSettingsActivity.this.voiceSwipeReplyRow || position == ForwardSettingsActivity.this.fastReplyRow || position == ForwardSettingsActivity.this.replyVibrationRow;
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
                    if (position == ForwardSettingsActivity.this.enableTabsInDirectFRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("EnableTabsForMultiForward", R.string.EnableTabsForMultiForward), TurboConfig.multiForwardTabs, true);
                        return;
                    } else if (position == ForwardSettingsActivity.this.voiceSwipeReplyRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("SwipeForVoice", R.string.SwipeForVoice), TurboConfig.swipeVoice, false);
                        return;
                    } else if (position == ForwardSettingsActivity.this.fastReplyRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("SwipeToReply", R.string.SwipeToReply), TurboConfig.swipeToReply, true);
                        return;
                    } else if (position == ForwardSettingsActivity.this.replyVibrationRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("SwipeToReplyVibration", R.string.SwipeToReplyVibration), TurboConfig.replyVibration, true);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == ForwardSettingsActivity.this.voiceSwipeReplyDesRow) {
                        privacyCell.setText(LocaleController.getString("SwipeForVoiceDes", R.string.SwipeForVoiceDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return ForwardSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == ForwardSettingsActivity.this.enableTabsInDirectFRow || position == ForwardSettingsActivity.this.voiceSwipeReplyRow || position == ForwardSettingsActivity.this.fastReplyRow || position == ForwardSettingsActivity.this.replyVibrationRow || position != ForwardSettingsActivity.this.voiceSwipeReplyDesRow) {
                return 0;
            }
            return 1;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enableTabsInDirectFRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.fastReplyRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.replyVibrationRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.voiceSwipeReplyRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.voiceSwipeReplyDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("ForwardSetting", R.string.ForwardSetting));
        this.actionBar.setActionBarMenuOnItemClick(new C23601());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C23612());
        return this.fragmentView;
    }
}
