package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Utilities.TurboConfig$Toast;

public class ToastSettingsActivity extends BaseFragment {
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int marginRow;
    private int onlineDesRow;
    private int onlineRow;
    private int paddingRow;
    private int positionRow;
    private int rowCount = 0;
    private int textSizeRow;
    private int toBottomRow;
    private int typingRow;

    /* renamed from: turbogram.ToastSettingsActivity$1 */
    class C24641 extends ActionBarMenuOnItemClick {
        C24641() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ToastSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.ToastSettingsActivity$2 */
    class C24692 implements OnItemClickListener {
        C24692() {
        }

        public void onItemClick(View view, final int position) {
            boolean z = true;
            String str;
            if (position == ToastSettingsActivity.this.onlineRow) {
                str = "toast_status";
                if (TurboConfig$Toast.status) {
                    z = false;
                }
                TurboConfig$Toast.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig$Toast.status);
                }
            } else if (position == ToastSettingsActivity.this.typingRow) {
                str = "toast_typing";
                if (TurboConfig$Toast.typing) {
                    z = false;
                }
                TurboConfig$Toast.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig$Toast.typing);
                }
            } else if (position == ToastSettingsActivity.this.toBottomRow) {
                str = "toast_tobottom";
                if (TurboConfig$Toast.toBottom) {
                    z = false;
                }
                TurboConfig$Toast.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig$Toast.toBottom);
                }
            } else if (position == ToastSettingsActivity.this.positionRow) {
                Builder builder = new Builder(ToastSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("ToastPosition", R.string.ToastPosition));
                builder.setItems(new CharSequence[]{LocaleController.getString("ToastLeft", R.string.ToastLeft), LocaleController.getString("ToastCenter", R.string.ToastCenter), LocaleController.getString("ToastRight", R.string.ToastRight)}, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig$Toast.setIntValue("toast_pos", which);
                        if (ToastSettingsActivity.this.listAdapter != null) {
                            ToastSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                ToastSettingsActivity.this.showDialog(builder.create());
            } else if (position == ToastSettingsActivity.this.textSizeRow) {
                if (ToastSettingsActivity.this.getParentActivity() != null) {
                    builder = new AlertDialog.Builder(ToastSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("TextSize", R.string.TextSize));
                    numberPicker = new NumberPicker(ToastSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(10);
                    numberPicker.setMaxValue(20);
                    numberPicker.setValue(TurboConfig$Toast.textSize);
                    builder.setView(numberPicker);
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig$Toast.setIntValue("toast_tsize", numberPicker.getValue());
                            if (ToastSettingsActivity.this.listAdapter != null) {
                                ToastSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                        }
                    });
                    ToastSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == ToastSettingsActivity.this.paddingRow) {
                if (ToastSettingsActivity.this.getParentActivity() != null) {
                    builder = new AlertDialog.Builder(ToastSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("ToastPadding", R.string.ToastPadding));
                    numberPicker = new NumberPicker(ToastSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(0);
                    numberPicker.setMaxValue(50);
                    numberPicker.setValue(TurboConfig$Toast.padding);
                    builder.setView(numberPicker);
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig$Toast.setIntValue("toast_padding", numberPicker.getValue());
                            if (ToastSettingsActivity.this.listAdapter != null) {
                                ToastSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                        }
                    });
                    ToastSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == ToastSettingsActivity.this.marginRow && ToastSettingsActivity.this.getParentActivity() != null) {
                builder = new AlertDialog.Builder(ToastSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("ToastMargin", R.string.ToastMargin));
                numberPicker = new NumberPicker(ToastSettingsActivity.this.getParentActivity());
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(100);
                numberPicker.setValue(TurboConfig$Toast.margin);
                builder.setView(numberPicker);
                builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig$Toast.setIntValue("toast_margin", numberPicker.getValue());
                        if (ToastSettingsActivity.this.listAdapter != null) {
                            ToastSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                ToastSettingsActivity.this.showDialog(builder.create());
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
            return position == ToastSettingsActivity.this.onlineRow || position == ToastSettingsActivity.this.typingRow || position == ToastSettingsActivity.this.toBottomRow || position == ToastSettingsActivity.this.positionRow || position == ToastSettingsActivity.this.textSizeRow || position == ToastSettingsActivity.this.paddingRow || position == ToastSettingsActivity.this.marginRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextDetailSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextSettingsCell(this.mContext);
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
                    TextCheckCell checkCell = holder.itemView;
                    if (position == ToastSettingsActivity.this.onlineRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ShowOnlineToast", R.string.ShowOnlineToast), TurboConfig$Toast.status, false);
                        return;
                    } else if (position == ToastSettingsActivity.this.typingRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ShowTypingToast", R.string.ShowTypingToast), TurboConfig$Toast.typing, true);
                        return;
                    } else if (position == ToastSettingsActivity.this.toBottomRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("ToastToBottom", R.string.ToastToBottom), TurboConfig$Toast.toBottom, true);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextDetailSettingsCell detailSettingsCell = holder.itemView;
                    if (position == ToastSettingsActivity.this.positionRow) {
                        int pos = TurboConfig$Toast.position;
                        String status = null;
                        if (pos == 0) {
                            status = LocaleController.getString("ToastLeft", R.string.ToastLeft);
                        } else if (pos == 1) {
                            status = LocaleController.getString("ToastCenter", R.string.ToastCenter);
                        } else if (pos == 2) {
                            status = LocaleController.getString("ToastRight", R.string.ToastRight);
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("ToastPosition", R.string.ToastPosition), status, true);
                        return;
                    }
                    return;
                case 2:
                    TextSettingsCell settingsCell = holder.itemView;
                    if (position == ToastSettingsActivity.this.textSizeRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("ToastTextSize", R.string.ToastTextSize), String.format("%d", new Object[]{Integer.valueOf(TurboConfig$Toast.textSize)}), true);
                        return;
                    } else if (position == ToastSettingsActivity.this.paddingRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("ToastPadding", R.string.ToastPadding), String.format("%d", new Object[]{Integer.valueOf(TurboConfig$Toast.padding)}), true);
                        return;
                    } else if (position == ToastSettingsActivity.this.marginRow) {
                        settingsCell.setTextAndValue(LocaleController.getString("ToastMargin", R.string.ToastMargin), String.format("%d", new Object[]{Integer.valueOf(TurboConfig$Toast.margin)}), false);
                        return;
                    } else {
                        return;
                    }
                case 3:
                    TextInfoPrivacyCell infoPrivacyCell = holder.itemView;
                    if (position == ToastSettingsActivity.this.onlineDesRow) {
                        infoPrivacyCell.setText(LocaleController.getString("ShowOnlineToastDes", R.string.ShowOnlineToastDes));
                        infoPrivacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return ToastSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == ToastSettingsActivity.this.onlineRow || position == ToastSettingsActivity.this.typingRow || position == ToastSettingsActivity.this.toBottomRow) {
                return 0;
            }
            if (position == ToastSettingsActivity.this.positionRow) {
                return 1;
            }
            if (position == ToastSettingsActivity.this.textSizeRow || position == ToastSettingsActivity.this.paddingRow || position == ToastSettingsActivity.this.marginRow) {
                return 2;
            }
            if (position == ToastSettingsActivity.this.onlineDesRow) {
                return 3;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.typingRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.onlineRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.onlineDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.toBottomRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.positionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.textSizeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.paddingRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.marginRow = i;
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("ToastNotifications", R.string.ToastNotifications));
        this.actionBar.setActionBarMenuOnItemClick(new C24641());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24692());
        return this.fragmentView;
    }
}
