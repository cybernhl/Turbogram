package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.TextDescriptionCell;
import turbogram.Utilities.TurboConfig;

public class VoiceSettingsActivity extends BaseFragment {
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int mode1Row;
    private int mode2DesRow;
    private int mode2Row;
    private int mode3Row;
    private int mode4Row;
    private int mode5Row;
    private int mode6DesRow;
    private int mode6Row;
    private int rowCount = 0;
    private int vcDesRow;

    /* renamed from: turbogram.VoiceSettingsActivity$1 */
    class C25041 extends ActionBarMenuOnItemClick {
        C25041() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                VoiceSettingsActivity.this.finishFragment();
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
            return position == VoiceSettingsActivity.this.mode1Row || position == VoiceSettingsActivity.this.mode2Row || position == VoiceSettingsActivity.this.mode3Row || position == VoiceSettingsActivity.this.mode4Row || position == VoiceSettingsActivity.this.mode5Row || position == VoiceSettingsActivity.this.mode6Row || position == VoiceSettingsActivity.this.mode2DesRow || position == VoiceSettingsActivity.this.mode6DesRow;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new RadioCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextDescriptionCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextInfoPrivacyCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            boolean z = false;
            boolean z2 = true;
            switch (holder.getItemViewType()) {
                case 0:
                    RadioCell radioCell = holder.itemView;
                    int voiceType = TurboConfig.voiceChangerType;
                    String string;
                    if (position == VoiceSettingsActivity.this.mode1Row) {
                        string = LocaleController.getString("VoiceChangerType1", R.string.VoiceChangerType1);
                        if (voiceType == 0) {
                            z = true;
                        }
                        radioCell.setText(string, z, true);
                        return;
                    } else if (position == VoiceSettingsActivity.this.mode2Row) {
                        string = LocaleController.getString("VoiceChangerType2", R.string.VoiceChangerType2);
                        if (voiceType != 1) {
                            z2 = false;
                        }
                        radioCell.setText(string, z2, false);
                        return;
                    } else if (position == VoiceSettingsActivity.this.mode3Row) {
                        string = LocaleController.getString("VoiceChangerType3", R.string.VoiceChangerType3);
                        if (voiceType == 2) {
                            z = true;
                        }
                        radioCell.setText(string, z, true);
                        return;
                    } else if (position == VoiceSettingsActivity.this.mode4Row) {
                        string = LocaleController.getString("VoiceChangerType4", R.string.VoiceChangerType4);
                        if (voiceType == 3) {
                            z = true;
                        }
                        radioCell.setText(string, z, true);
                        return;
                    } else if (position == VoiceSettingsActivity.this.mode5Row) {
                        string = LocaleController.getString("VoiceChangerType5", R.string.VoiceChangerType5);
                        if (voiceType == 4) {
                            z = true;
                        }
                        radioCell.setText(string, z, true);
                        return;
                    } else if (position == VoiceSettingsActivity.this.mode6Row) {
                        string = LocaleController.getString("VoiceChangerType6", R.string.VoiceChangerType6);
                        if (voiceType != 5) {
                            z2 = false;
                        }
                        radioCell.setText(string, z2, false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextDescriptionCell descriptionCell = holder.itemView;
                    if (position == VoiceSettingsActivity.this.mode2DesRow) {
                        switch (TurboConfig.voiceChangerSpeed) {
                            case 1:
                                descriptionCell.setText(LocaleController.getString("VoiceSpeed1", R.string.VoiceSpeed1), true);
                                return;
                            case 2:
                                descriptionCell.setText(LocaleController.getString("VoiceSpeed2", R.string.VoiceSpeed2), true);
                                return;
                            case 3:
                                descriptionCell.setText(LocaleController.getString("VoiceSpeed3", R.string.VoiceSpeed3), true);
                                return;
                            case 4:
                                descriptionCell.setText(LocaleController.getString("VoiceSpeed4", R.string.VoiceSpeed4), true);
                                return;
                            default:
                                return;
                        }
                    } else if (position == VoiceSettingsActivity.this.mode6DesRow) {
                        descriptionCell.setText(String.format("%d", new Object[]{Integer.valueOf(TurboConfig.voiceChangerTranspose)}), false);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    TextInfoPrivacyCell privacyCell = holder.itemView;
                    privacyCell.setText("");
                    privacyCell.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return VoiceSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == VoiceSettingsActivity.this.mode1Row || position == VoiceSettingsActivity.this.mode2Row || position == VoiceSettingsActivity.this.mode3Row || position == VoiceSettingsActivity.this.mode4Row || position == VoiceSettingsActivity.this.mode5Row || position == VoiceSettingsActivity.this.mode6Row) {
                return 0;
            }
            if (position == VoiceSettingsActivity.this.mode2DesRow || position == VoiceSettingsActivity.this.mode6DesRow) {
                return 1;
            }
            if (position == VoiceSettingsActivity.this.vcDesRow) {
                return 2;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.mode1Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode2Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode2DesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode3Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode4Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode5Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode6Row = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mode6DesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.vcDesRow = i;
        return super.onFragmentCreate();
    }

    public View createView(final Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("VoiceChanger", R.string.VoiceChanger));
        this.actionBar.setActionBarMenuOnItemClick(new C25041());
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

            /* renamed from: turbogram.VoiceSettingsActivity$2$1 */
            class C25051 implements OnClickListener {
                C25051() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    TurboConfig.setIntValue("voice_changer_type", 1);
                    if (i == 0) {
                        TurboConfig.setIntValue("voice_speed", 1);
                    } else if (i == 1) {
                        TurboConfig.setIntValue("voice_speed", 2);
                    } else if (i == 2) {
                        TurboConfig.setIntValue("voice_speed", 3);
                    } else if (i == 3) {
                        TurboConfig.setIntValue("voice_speed", 4);
                    }
                    if (VoiceSettingsActivity.this.listAdapter != null) {
                        VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                    }
                }
            }

            public void onItemClick(View view, int position) {
                if (position == VoiceSettingsActivity.this.mode1Row) {
                    TurboConfig.setIntValue("voice_changer_type", 0);
                    if (VoiceSettingsActivity.this.listAdapter != null) {
                        VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                    }
                } else if (position == VoiceSettingsActivity.this.mode2Row || position == VoiceSettingsActivity.this.mode2DesRow) {
                    Builder builder = new Builder(context);
                    ArrayList<CharSequence> items = new ArrayList();
                    items.add(LocaleController.getString("VoiceSpeed1", R.string.VoiceSpeed1));
                    items.add(LocaleController.getString("VoiceSpeed2", R.string.VoiceSpeed2));
                    items.add(LocaleController.getString("VoiceSpeed3", R.string.VoiceSpeed3));
                    items.add(LocaleController.getString("VoiceSpeed4", R.string.VoiceSpeed4));
                    builder.setItems((CharSequence[]) items.toArray(new CharSequence[items.size()]), new C25051());
                    VoiceSettingsActivity.this.turboShowDialog(builder.create());
                } else if (position == VoiceSettingsActivity.this.mode3Row) {
                    TurboConfig.setIntValue("voice_changer_type", 2);
                    if (VoiceSettingsActivity.this.listAdapter != null) {
                        VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                    }
                } else if (position == VoiceSettingsActivity.this.mode4Row) {
                    TurboConfig.setIntValue("voice_changer_type", 3);
                    if (VoiceSettingsActivity.this.listAdapter != null) {
                        VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                    }
                } else if (position == VoiceSettingsActivity.this.mode5Row) {
                    TurboConfig.setIntValue("voice_changer_type", 4);
                    if (VoiceSettingsActivity.this.listAdapter != null) {
                        VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                    }
                } else if (position == VoiceSettingsActivity.this.mode6Row || position == VoiceSettingsActivity.this.mode6DesRow) {
                    Builder builder1 = new Builder(context);
                    builder1.setTitle(LocaleController.getString("VoiceSemitone", R.string.VoiceSemitone));
                    final NumberPicker numberPicker = new NumberPicker(context);
                    numberPicker.setMinValue(-5);
                    numberPicker.setMaxValue(10);
                    numberPicker.setValue(TurboConfig.voiceChangerTranspose);
                    builder1.setView(numberPicker);
                    builder1.setNegativeButton(LocaleController.getString("Done", R.string.Done), new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig.setIntValue("voice_changer_type", 5);
                            TurboConfig.setIntValue("transpose_semitone", numberPicker.getValue());
                            if (VoiceSettingsActivity.this.listAdapter != null) {
                                VoiceSettingsActivity.this.listAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    VoiceSettingsActivity.this.turboShowDialog(builder1.create());
                }
            }
        });
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }
}
