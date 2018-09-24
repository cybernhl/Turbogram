package turbogram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig.Toolbar;

public class ToolbarSettingsActivity extends BaseFragment {
    private AnimatorSet animatorSet;
    private int buttonSizeRow;
    private int buttonSpacingRow;
    private boolean customEnabled;
    private int directionRow;
    private int enableRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int rowCount = 0;
    private int shadowRow;
    private int sortRow;
    private int typeRow;

    /* renamed from: turbogram.ToolbarSettingsActivity$1 */
    class C24701 extends ActionBarMenuOnItemClick {
        C24701() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ToolbarSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.ToolbarSettingsActivity$2 */
    class C24752 implements OnItemClickListener {
        C24752() {
        }

        public void onItemClick(View view, final int position) {
            if (position == ToolbarSettingsActivity.this.enableRow) {
                ToolbarSettingsActivity.this.customEnabled = !ToolbarSettingsActivity.this.customEnabled;
                Toolbar.setBooleanValue("tool_bar", ToolbarSettingsActivity.this.customEnabled);
                if (view instanceof TextCheckBoxCell) {
                    ((TextCheckBoxCell) view).setChecked(ToolbarSettingsActivity.this.customEnabled);
                }
                ToolbarSettingsActivity.this.enableRows(ToolbarSettingsActivity.this.enableRow, ToolbarSettingsActivity.this.customEnabled);
                NotificationCenter.getInstance(ToolbarSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(5));
            } else if (!ToolbarSettingsActivity.this.customEnabled) {
            } else {
                Builder builder;
                if (position == ToolbarSettingsActivity.this.typeRow) {
                    builder = new Builder(ToolbarSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("ToolbarType", R.string.ToolbarType));
                    builder.setItems(new CharSequence[]{LocaleController.getString("SlidingToolBar", R.string.SlidingToolBar), LocaleController.getString("FixedToolBar", R.string.FixedToolBar)}, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toolbar.setIntValue("toolbar_type", which);
                            if (ToolbarSettingsActivity.this.listAdapter != null) {
                                ToolbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                            NotificationCenter.getInstance(ToolbarSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(5));
                        }
                    });
                    ToolbarSettingsActivity.this.showDialog(builder.create());
                } else if (position == ToolbarSettingsActivity.this.sortRow) {
                    ToolbarSettingsActivity.this.presentFragment(new SortToolbarActivity());
                } else if (position == ToolbarSettingsActivity.this.directionRow) {
                    builder = new Builder(ToolbarSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("ToolbarDirection", R.string.ToolbarDirection));
                    builder.setItems(new CharSequence[]{LocaleController.getString("ToolbarVertical", R.string.ToolbarVertical), LocaleController.getString("ToolbarHorizontal", R.string.ToolbarHorizontal)}, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toolbar.setIntValue("toolbar_direction", which);
                            if (ToolbarSettingsActivity.this.listAdapter != null) {
                                ToolbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                            ToolbarSettingsActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
                        }
                    });
                    ToolbarSettingsActivity.this.showDialog(builder.create());
                } else if (position == ToolbarSettingsActivity.this.buttonSizeRow) {
                    builder = new AlertDialog.Builder(ToolbarSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    numberPicker = new NumberPicker(ToolbarSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(40);
                    numberPicker.setMaxValue(56);
                    numberPicker.setValue(Toolbar.buttonSize);
                    builder.setView(numberPicker);
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toolbar.setIntValue("toolbar_bsize", numberPicker.getValue());
                            if (ToolbarSettingsActivity.this.listAdapter != null) {
                                ToolbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                            NotificationCenter.getInstance(ToolbarSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(5));
                        }
                    });
                    ToolbarSettingsActivity.this.showDialog(builder.create());
                } else if (position == ToolbarSettingsActivity.this.buttonSpacingRow) {
                    builder = new AlertDialog.Builder(ToolbarSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                    numberPicker = new NumberPicker(ToolbarSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(1);
                    numberPicker.setMaxValue(16);
                    numberPicker.setValue(Toolbar.buttonSpaccing);
                    builder.setView(numberPicker);
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toolbar.setIntValue("toolbar_bspace", numberPicker.getValue());
                            if (ToolbarSettingsActivity.this.listAdapter != null) {
                                ToolbarSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                            NotificationCenter.getInstance(ToolbarSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(5));
                        }
                    });
                    ToolbarSettingsActivity.this.showDialog(builder.create());
                }
            }
        }
    }

    /* renamed from: turbogram.ToolbarSettingsActivity$3 */
    class C24763 extends AnimatorListenerAdapter {
        C24763() {
        }

        public void onAnimationEnd(Animator animator) {
            if (animator.equals(ToolbarSettingsActivity.this.animatorSet)) {
                ToolbarSettingsActivity.this.animatorSet = null;
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
            return position == ToolbarSettingsActivity.this.enableRow || position == ToolbarSettingsActivity.this.typeRow || position == ToolbarSettingsActivity.this.sortRow || position == ToolbarSettingsActivity.this.directionRow || position == ToolbarSettingsActivity.this.buttonSizeRow || position == ToolbarSettingsActivity.this.buttonSpacingRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckBoxCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextDetailSettingsCell(this.mContext);
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
                    TextCheckBoxCell checkCell = holder.itemView;
                    if (position == ToolbarSettingsActivity.this.enableRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ShowToolBar", R.string.ShowToolBar), ToolbarSettingsActivity.this.customEnabled, false);
                        return;
                    }
                    return;
                case 1:
                    TextSettingsCell settingsCell = holder.itemView;
                    if (position == ToolbarSettingsActivity.this.sortRow) {
                        settingsCell.setText(LocaleController.getString("SortSlidingToolbar", R.string.SortSlidingToolbar), true);
                        return;
                    } else if (position == ToolbarSettingsActivity.this.buttonSizeRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("SlidingToolbarButtonSize", R.string.SlidingToolbarButtonSize), String.format("%d", new Object[]{Integer.valueOf(Toolbar.buttonSize)}), true);
                        return;
                    } else if (position == ToolbarSettingsActivity.this.buttonSpacingRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("SlidingToolbarButtonSpacing", R.string.SlidingToolbarButtonSpacing), String.format("%d", new Object[]{Integer.valueOf(Toolbar.buttonSpaccing)}), false);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    TextDetailSettingsCell detailSettingsCell = holder.itemView;
                    String text;
                    if (position == ToolbarSettingsActivity.this.typeRow) {
                        int toolBar = Toolbar.type;
                        text = null;
                        if (toolBar == 0) {
                            text = LocaleController.getString("SlidingToolBar", R.string.SlidingToolBar);
                        } else if (toolBar == 1) {
                            text = LocaleController.getString("FixedToolBar", R.string.FixedToolBar);
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("ToolbarType", R.string.ToolbarType), text, true);
                        return;
                    } else if (position == ToolbarSettingsActivity.this.directionRow) {
                        int direction = Toolbar.direction;
                        text = null;
                        if (direction == 0) {
                            text = LocaleController.getString("ToolbarVertical", R.string.ToolbarVertical);
                        } else if (direction == 1) {
                            text = LocaleController.getString("ToolbarHorizontal", R.string.ToolbarHorizontal);
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("ToolbarDirection", R.string.ToolbarDirection), text, true);
                        return;
                    } else {
                        return;
                    }
                case 3:
                    TextInfoPrivacyCell infoPrivacyCell = holder.itemView;
                    if (position == ToolbarSettingsActivity.this.shadowRow) {
                        infoPrivacyCell.setText(null);
                        infoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void onViewAttachedToWindow(ViewHolder holder) {
            if (holder.getItemViewType() != 0) {
                switch (holder.getItemViewType()) {
                    case 1:
                        holder.itemView.setEnabled(ToolbarSettingsActivity.this.customEnabled, null);
                        return;
                    case 2:
                        holder.itemView.setEnabled(ToolbarSettingsActivity.this.customEnabled, null);
                        return;
                    case 3:
                        holder.itemView.setEnabled(ToolbarSettingsActivity.this.customEnabled, null);
                        return;
                    default:
                        return;
                }
            }
        }

        public int getItemCount() {
            return ToolbarSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == ToolbarSettingsActivity.this.enableRow) {
                return 0;
            }
            if (position == ToolbarSettingsActivity.this.sortRow || position == ToolbarSettingsActivity.this.buttonSizeRow || position == ToolbarSettingsActivity.this.buttonSpacingRow) {
                return 1;
            }
            if (position == ToolbarSettingsActivity.this.typeRow || position == ToolbarSettingsActivity.this.directionRow) {
                return 2;
            }
            if (position == ToolbarSettingsActivity.this.shadowRow) {
                return 3;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enableRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.shadowRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.typeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.sortRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.directionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.buttonSizeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.buttonSpacingRow = i;
        this.customEnabled = Toolbar.enabled;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("ToolBar", R.string.ToolBar));
        this.actionBar.setActionBarMenuOnItemClick(new C24701());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24752());
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
                        holder.itemView.setEnabled(enabled, animators);
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
            this.animatorSet.addListener(new C24763());
            this.animatorSet.setDuration(150);
            this.animatorSet.start();
        }
    }
}
