package turbogram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig$Chatbar;

public class ChatbarSettingsActivity extends BaseFragment {
    private AnimatorSet animatorSet;
    private int centerButtonRow;
    private boolean customEnabled;
    private int defaultRow;
    private int desRow;
    private int enabelRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int rowCount = 0;
    private int shadowRow;
    private int showMemberRow;
    private int verticalRow;

    /* renamed from: turbogram.ChatbarSettingsActivity$1 */
    class C22451 extends ActionBarMenuOnItemClick {
        C22451() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ChatbarSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.ChatbarSettingsActivity$2 */
    class C22482 implements OnItemClickListener {
        C22482() {
        }

        public void onItemClick(View view, final int position) {
            boolean z = true;
            if (position == ChatbarSettingsActivity.this.enabelRow) {
                ChatbarSettingsActivity chatbarSettingsActivity = ChatbarSettingsActivity.this;
                if (ChatbarSettingsActivity.this.customEnabled) {
                    z = false;
                }
                chatbarSettingsActivity.customEnabled = z;
                TurboConfig$Chatbar.setBooleanValue("bar_show", ChatbarSettingsActivity.this.customEnabled);
                if (view instanceof TextCheckBoxCell) {
                    ((TextCheckBoxCell) view).setChecked(ChatbarSettingsActivity.this.customEnabled);
                }
                ChatbarSettingsActivity.this.enableRows(ChatbarSettingsActivity.this.enabelRow, ChatbarSettingsActivity.this.customEnabled);
            } else if (!ChatbarSettingsActivity.this.customEnabled) {
            } else {
                if (position == ChatbarSettingsActivity.this.verticalRow) {
                    Builder builder = new Builder(ChatbarSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("Chatbar", R.string.Chatbar));
                    builder.setItems(new CharSequence[]{LocaleController.getString("ToolbarVertical", R.string.ToolbarVertical), LocaleController.getString("ToolbarHorizontal", R.string.ToolbarHorizontal)}, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig$Chatbar.setBooleanValue("bar_vertical", which == 0);
                            if (ChatbarSettingsActivity.this.listAdapter != null) {
                                ChatbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                        }
                    });
                    ChatbarSettingsActivity.this.showDialog(builder.create());
                } else if (position == ChatbarSettingsActivity.this.centerButtonRow) {
                    r8 = "bar_centerbtn";
                    if (TurboConfig$Chatbar.centerButton) {
                        z = false;
                    }
                    TurboConfig$Chatbar.setBooleanValue(r8, z);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Chatbar.centerButton);
                    }
                } else if (position == ChatbarSettingsActivity.this.showMemberRow) {
                    r8 = "show_cmember";
                    if (TurboConfig$Chatbar.showMembers) {
                        z = false;
                    }
                    TurboConfig$Chatbar.setBooleanValue(r8, z);
                    if (view instanceof TextCheckCell) {
                        ((TextCheckCell) view).setChecked(TurboConfig$Chatbar.showMembers);
                    }
                } else if (position == ChatbarSettingsActivity.this.defaultRow) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(ChatbarSettingsActivity.this.getParentActivity());
                    ArrayList<CharSequence> items = new ArrayList();
                    final ArrayList<Integer> options = new ArrayList();
                    items.add(LocaleController.getString("RobotTab", R.string.RobotTab));
                    options.add(Integer.valueOf(6));
                    items.add(LocaleController.getString("ChannelTab", R.string.ChannelTab));
                    options.add(Integer.valueOf(5));
                    items.add(LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab));
                    options.add(Integer.valueOf(7));
                    items.add(LocaleController.getString("GroupsTab", R.string.GroupsTab));
                    options.add(Integer.valueOf(4));
                    items.add(LocaleController.getString("ContactTab", R.string.ContactTab));
                    options.add(Integer.valueOf(3));
                    items.add(LocaleController.getString("FavoriteTab", R.string.FavoriteTab));
                    options.add(Integer.valueOf(8));
                    items.add(LocaleController.getString("AllTab", R.string.AllTab));
                    options.add(Integer.valueOf(0));
                    builder2.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i >= 0 && i < options.size()) {
                                TurboConfig$Chatbar.setIntValue("bar_default", ((Integer) options.get(i)).intValue());
                                if (ChatbarSettingsActivity.this.listAdapter != null) {
                                    ChatbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                                }
                            }
                        }
                    });
                    builder2.setTitle(LocaleController.getString("Default", R.string.Default));
                    ChatbarSettingsActivity.this.showDialog(builder2.create());
                }
            }
        }
    }

    /* renamed from: turbogram.ChatbarSettingsActivity$3 */
    class C22493 extends AnimatorListenerAdapter {
        C22493() {
        }

        public void onAnimationEnd(Animator animator) {
            if (animator.equals(ChatbarSettingsActivity.this.animatorSet)) {
                ChatbarSettingsActivity.this.animatorSet = null;
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
            return position == ChatbarSettingsActivity.this.enabelRow || position == ChatbarSettingsActivity.this.verticalRow || position == ChatbarSettingsActivity.this.centerButtonRow || position == ChatbarSettingsActivity.this.showMemberRow || position == ChatbarSettingsActivity.this.defaultRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckBoxCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextSettingsCell(this.mContext) {
                        public boolean onTouchEvent(MotionEvent event) {
                            if (VERSION.SDK_INT >= 21 && getBackground() != null && (event.getAction() == 0 || event.getAction() == 2)) {
                                getBackground().setHotspot(event.getX(), event.getY());
                            }
                            return super.onTouchEvent(event);
                        }
                    };
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 3:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TextCheckBoxCell checkBoxCell = holder.itemView;
                    if (position == ChatbarSettingsActivity.this.enabelRow) {
                        checkBoxCell.setTextAndCheck(LocaleController.getString("ChatbarEnabled", R.string.ChatbarEnabled), ChatbarSettingsActivity.this.customEnabled, false);
                        return;
                    }
                    return;
                case 1:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == ChatbarSettingsActivity.this.centerButtonRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ChatbarCenterButton", R.string.ChatbarCenterButton), TurboConfig$Chatbar.centerButton, true);
                        return;
                    } else if (position == ChatbarSettingsActivity.this.showMemberRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ChatbarShowMembers", R.string.ChatbarShowMembers), TurboConfig$Chatbar.showMembers, true);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    TextSettingsCell textCell = holder.itemView;
                    if (position == ChatbarSettingsActivity.this.verticalRow) {
                        String status = LocaleController.getString("ToolbarHorizontal", R.string.ToolbarHorizontal);
                        if (TurboConfig$Chatbar.vertical) {
                            status = LocaleController.getString("ToolbarVertical", R.string.ToolbarVertical);
                        }
                        textCell.setTextAndValue(LocaleController.getString("Chatbar", R.string.Chatbar), status, true);
                        return;
                    } else if (position == ChatbarSettingsActivity.this.defaultRow) {
                        switch (TurboConfig$Chatbar.defaul) {
                            case 0:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("AllTab", R.string.AllTab), true);
                                return;
                            case 3:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("ContactTab", R.string.ContactTab), true);
                                return;
                            case 4:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("GroupsTab", R.string.GroupsTab), true);
                                return;
                            case 5:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("ChannelTab", R.string.ChannelTab), true);
                                return;
                            case 6:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("RobotTab", R.string.RobotTab), true);
                                return;
                            case 7:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("SuperGroupsTab", R.string.SuperGroupsTab), true);
                                return;
                            case 8:
                                textCell.setTextAndValue(LocaleController.getString("ChatbarDefault", R.string.ChatbarDefault), LocaleController.getString("FavoriteTab", R.string.FavoriteTab), true);
                                return;
                            default:
                                return;
                        }
                    } else {
                        return;
                    }
                case 3:
                    TextInfoPrivacyCell infoPrivacyCell = holder.itemView;
                    if (position == ChatbarSettingsActivity.this.shadowRow) {
                        infoPrivacyCell.setText(null);
                        infoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else if (position == ChatbarSettingsActivity.this.desRow) {
                        infoPrivacyCell.setText(LocaleController.getString("ChatbarDes", R.string.ChatbarDes));
                        infoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }

        public void onViewAttachedToWindow(ViewHolder holder) {
            if (holder.getItemViewType() != 0) {
                switch (holder.getItemViewType()) {
                    case 1:
                        holder.itemView.setEnabled(ChatbarSettingsActivity.this.customEnabled);
                        return;
                    case 2:
                        holder.itemView.setEnabled(ChatbarSettingsActivity.this.customEnabled, null);
                        return;
                    case 3:
                        holder.itemView.setEnabled(ChatbarSettingsActivity.this.customEnabled, null);
                        return;
                    default:
                        return;
                }
            }
        }

        public int getItemCount() {
            return ChatbarSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == ChatbarSettingsActivity.this.enabelRow) {
                return 0;
            }
            if (position == ChatbarSettingsActivity.this.centerButtonRow || position == ChatbarSettingsActivity.this.showMemberRow) {
                return 1;
            }
            if (position == ChatbarSettingsActivity.this.verticalRow || position == ChatbarSettingsActivity.this.defaultRow) {
                return 2;
            }
            if (position == ChatbarSettingsActivity.this.shadowRow || position == ChatbarSettingsActivity.this.desRow) {
                return 3;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enabelRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.shadowRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.verticalRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.centerButtonRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showMemberRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.defaultRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.desRow = i;
        this.customEnabled = TurboConfig$Chatbar.show;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("Chatbar", R.string.Chatbar));
        this.actionBar.setActionBarMenuOnItemClick(new C22451());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C22482());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private void enableRows(int currentRow, boolean enabled) {
        int count = this.listView.getChildCount();
        ArrayList<Animator> animators = new ArrayList();
        for (int a = 0; a < count; a++) {
            Holder holder = (Holder) this.listView.getChildViewHolder(this.listView.getChildAt(a));
            int type = holder.getItemViewType();
            if (holder.getAdapterPosition() != currentRow) {
                switch (type) {
                    case 1:
                        holder.itemView.setEnabled(enabled);
                        break;
                    case 2:
                        holder.itemView.setEnabled(enabled, animators);
                        break;
                    case 3:
                        holder.itemView.setEnabled(enabled, animators);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!animators.isEmpty()) {
            if (this.animatorSet != null) {
                this.animatorSet.cancel();
            }
            this.animatorSet = new AnimatorSet();
            this.animatorSet.playTogether(animators);
            this.animatorSet.addListener(new C22493());
            this.animatorSet.setDuration(150);
            this.animatorSet.start();
        }
    }
}
