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
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.FontSettingsCell;
import turbogram.Utilities.TurboConfig;

public class TextNicerActivity extends BaseFragment {
    private int enablePopupRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int nicerDesRow;
    private int rowCount = 0;
    private int shadowRow;
    private int type1Row;
    private int type2Row;
    private int type3Row;
    private int type4Row;
    private int type5Row;
    private int type6Row;
    private int type7Row;
    private int type8Row;

    /* renamed from: turbogram.TextNicerActivity$1 */
    class C24601 extends ActionBarMenuOnItemClick {
        C24601() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TextNicerActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.TextNicerActivity$2 */
    class C24612 implements OnItemClickListener {
        C24612() {
        }

        public void onItemClick(View view, int position) {
            boolean z = true;
            if (position == TextNicerActivity.this.enablePopupRow) {
                String str = "nice_popup";
                if (TurboConfig.textNicerPopup) {
                    z = false;
                }
                TurboConfig.setBooleanValue(str, z);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.textNicerPopup);
                }
            } else if (position == TextNicerActivity.this.type1Row) {
                TurboConfig.setIntValue("nicewrite", 0);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type2Row) {
                TurboConfig.setIntValue("nicewrite", 1);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type3Row) {
                TurboConfig.setIntValue("nicewrite", 2);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type4Row) {
                TurboConfig.setIntValue("nicewrite", 3);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type5Row) {
                TurboConfig.setIntValue("nicewrite", 4);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type6Row) {
                TurboConfig.setIntValue("nicewrite", 5);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type7Row) {
                TurboConfig.setIntValue("nicewrite", 6);
                TextNicerActivity.this.finishFragment();
            } else if (position == TextNicerActivity.this.type8Row) {
                TurboConfig.setIntValue("nicewrite", 7);
                TextNicerActivity.this.finishFragment();
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
            if (position == TextNicerActivity.this.shadowRow || position == TextNicerActivity.this.nicerDesRow) {
                return false;
            }
            return true;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new ShadowSectionCell(this.mContext);
                    break;
                case 2:
                    view = new FontSettingsCell(this.mContext);
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
                    holder.itemView.setTextAndCheck(LocaleController.getString("TextNicerPopup", R.string.TextNicerPopup), TurboConfig.textNicerPopup, false);
                    return;
                case 2:
                    FontSettingsCell fontSettingsCell = holder.itemView;
                    fontSettingsCell.setFont(-1);
                    if (position == TextNicerActivity.this.type1Row) {
                        fontSettingsCell.setText(LocaleController.getString("Normal", R.string.Normal), true);
                        return;
                    } else if (position == TextNicerActivity.this.type2Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer1", R.string.nicer1), true);
                        return;
                    } else if (position == TextNicerActivity.this.type3Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer2", R.string.nicer2), true);
                        return;
                    } else if (position == TextNicerActivity.this.type4Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer3", R.string.nicer3), true);
                        return;
                    } else if (position == TextNicerActivity.this.type5Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer4", R.string.nicer4), true);
                        return;
                    } else if (position == TextNicerActivity.this.type6Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer5", R.string.nicer5), true);
                        return;
                    } else if (position == TextNicerActivity.this.type7Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer6", R.string.nicer6), true);
                        return;
                    } else if (position == TextNicerActivity.this.type8Row) {
                        fontSettingsCell.setText(LocaleController.getString("nicer7", R.string.nicer7), false);
                        return;
                    } else {
                        return;
                    }
                case 3:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    if (position == TextNicerActivity.this.nicerDesRow) {
                        privacyCell.setText(LocaleController.getString("TextNicerDes", R.string.TextNicerDes));
                        privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return TextNicerActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == TextNicerActivity.this.enablePopupRow) {
                return 0;
            }
            if (position == TextNicerActivity.this.shadowRow) {
                return 1;
            }
            if (position == TextNicerActivity.this.nicerDesRow) {
                return 3;
            }
            return 2;
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enablePopupRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.shadowRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type1Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type2Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type3Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type4Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type5Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type6Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type7Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.type8Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.nicerDesRow = i;
        return true;
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TextNicer", R.string.TextNicer));
        this.actionBar.setActionBarMenuOnItemClick(new C24601());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24612());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }
}
