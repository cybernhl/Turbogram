package turbogram;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;
import java.util.Calendar;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.BottomSheetCell;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Services.DownloadReceiver;
import turbogram.Utilities.TurboConfig$BG;

public class DownloadSettingsActivity extends BaseFragment implements OnTimeSetListener {
    private int activeDaysRow;
    boolean[] days = new boolean[]{true, true, true, true, true, true, true};
    private int disableWifiRow;
    private int enableDMRow;
    private int enableWifiRow;
    private int endTimeRow;
    private int justTodayRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int rowCount = 0;
    private int startTimeRow;

    /* renamed from: turbogram.DownloadSettingsActivity$1 */
    class C23431 extends ActionBarMenuOnItemClick {
        C23431() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                DownloadSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.DownloadSettingsActivity$2 */
    class C23482 implements OnItemClickListener {
        C23482() {
        }

        public void onItemClick(View view, int position) {
            if (position == DownloadSettingsActivity.this.enableDMRow) {
                boolean downloadReceiver = TurboConfig$BG.downloadReceiver;
                if (downloadReceiver) {
                    new DownloadReceiver().cancelAlarm(ApplicationLoader.applicationContext);
                }
                TurboConfig$BG.setBooleanValue("download_receiver", !downloadReceiver);
                if (view instanceof TextCheckCell) {
                    boolean z;
                    TextCheckCell textCheckCell = (TextCheckCell) view;
                    if (downloadReceiver) {
                        z = false;
                    } else {
                        z = true;
                    }
                    textCheckCell.setChecked(z);
                }
            } else if (position == DownloadSettingsActivity.this.justTodayRow) {
                boolean jt = TurboConfig$BG.downloadJustToday;
                TurboConfig$BG.setBooleanValue("download_just_today", !jt);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(!jt);
                }
            } else if (position == DownloadSettingsActivity.this.activeDaysRow) {
                if (DownloadSettingsActivity.this.getParentActivity() != null) {
                    final boolean[] maskValues = new boolean[7];
                    Builder builder = new Builder(DownloadSettingsActivity.this.getParentActivity());
                    builder.setApplyTopPadding(false);
                    builder.setApplyBottomPadding(false);
                    LinearLayout linearLayout = new LinearLayout(DownloadSettingsActivity.this.getParentActivity());
                    linearLayout.setOrientation(1);
                    for (int a = 0; a < 7; a++) {
                        String name = null;
                        if (a == 0) {
                            name = LocaleController.getString("Saturday", R.string.Saturday);
                            maskValues[a] = TurboConfig$BG.downloadSaturday;
                        } else if (a == 1) {
                            name = LocaleController.getString("Sunday", R.string.Sunday);
                            maskValues[a] = TurboConfig$BG.downloadSunday;
                        } else if (a == 2) {
                            name = LocaleController.getString("Monday", R.string.Monday);
                            maskValues[a] = TurboConfig$BG.downloadMonday;
                        } else if (a == 3) {
                            name = LocaleController.getString("Tuesday", R.string.Tuesday);
                            maskValues[a] = TurboConfig$BG.downloadTuesday;
                        } else if (a == 4) {
                            name = LocaleController.getString("Wednesday", R.string.Wednesday);
                            maskValues[a] = TurboConfig$BG.downloadWednesday;
                        } else if (a == 5) {
                            name = LocaleController.getString("Thursday", R.string.Thursday);
                            maskValues[a] = TurboConfig$BG.downloadThursday;
                        } else if (a == 6) {
                            name = LocaleController.getString("Friday", R.string.Friday);
                            maskValues[a] = TurboConfig$BG.downloadFriday;
                        }
                        CheckBoxCell checkBoxCell = new CheckBoxCell(DownloadSettingsActivity.this.getParentActivity(), 1);
                        checkBoxCell.setTag(Integer.valueOf(a));
                        checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                        linearLayout.addView(checkBoxCell, LayoutHelper.createLinear(-1, 48));
                        checkBoxCell.setText(name, "", maskValues[a], true);
                        checkBoxCell.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                CheckBoxCell cell = (CheckBoxCell) v;
                                int num = ((Integer) cell.getTag()).intValue();
                                maskValues[num] = !maskValues[num];
                                cell.setChecked(maskValues[num], true);
                            }
                        });
                    }
                    BottomSheetCell cell = new BottomSheetCell(DownloadSettingsActivity.this.getParentActivity(), 1);
                    cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    cell.setTextAndIcon(LocaleController.getString("Save", R.string.Save).toUpperCase(), 0);
                    cell.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
                    r1 = position;
                    cell.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            try {
                                if (DownloadSettingsActivity.this.visibleDialog != null) {
                                    DownloadSettingsActivity.this.visibleDialog.dismiss();
                                }
                            } catch (Exception e) {
                                FileLog.e("tmessages", e);
                            }
                            for (int a = 0; a < 7; a++) {
                                if (a == 0) {
                                    TurboConfig$BG.setBooleanValue("dm_saturday", maskValues[a]);
                                } else if (a == 1) {
                                    TurboConfig$BG.setBooleanValue("dm_sunday", maskValues[a]);
                                } else if (a == 2) {
                                    TurboConfig$BG.setBooleanValue("dm_monday", maskValues[a]);
                                } else if (a == 3) {
                                    TurboConfig$BG.setBooleanValue("dm_tuesday", maskValues[a]);
                                } else if (a == 4) {
                                    TurboConfig$BG.setBooleanValue("dm_wednesday", maskValues[a]);
                                } else if (a == 5) {
                                    TurboConfig$BG.setBooleanValue("dm_thursday", maskValues[a]);
                                } else if (a == 6) {
                                    TurboConfig$BG.setBooleanValue("dm_friday", maskValues[a]);
                                }
                            }
                            if (DownloadSettingsActivity.this.listAdapter != null) {
                                DownloadSettingsActivity.this.listAdapter.notifyItemChanged(r1);
                            }
                        }
                    });
                    linearLayout.addView(cell, LayoutHelper.createLinear(-1, 48));
                    builder.setCustomView(linearLayout);
                    DownloadSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == DownloadSettingsActivity.this.startTimeRow) {
                r1 = position;
                TimePickerDialog.newInstance(new OnTimeSetListener() {
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        TurboConfig$BG.setIntValue("download_shour", hourOfDay);
                        TurboConfig$BG.setIntValue("download_sminute", minute);
                        DownloadSettingsActivity.this.saveReminder();
                        if (DownloadSettingsActivity.this.listAdapter != null) {
                            DownloadSettingsActivity.this.listAdapter.notifyItemChanged(r1);
                        }
                    }
                }, TurboConfig$BG.downloadStarthour, TurboConfig$BG.downloadStartMinute, false).show(DownloadSettingsActivity.this.getParentActivity().getFragmentManager(), "Timepickerdialog");
            } else if (position == DownloadSettingsActivity.this.endTimeRow) {
                r1 = position;
                TimePickerDialog.newInstance(new OnTimeSetListener() {
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        TurboConfig$BG.setIntValue("download_ehour", hourOfDay);
                        TurboConfig$BG.setIntValue("download_eminute", minute);
                        DownloadSettingsActivity.this.saveReminder();
                        if (DownloadSettingsActivity.this.listAdapter != null) {
                            DownloadSettingsActivity.this.listAdapter.notifyItemChanged(r1);
                        }
                    }
                }, TurboConfig$BG.downloadEndhour, TurboConfig$BG.downloadEndMinute, false).show(DownloadSettingsActivity.this.getParentActivity().getFragmentManager(), "Timepickerdialog_end");
            } else if (position == DownloadSettingsActivity.this.enableWifiRow) {
                boolean ew = TurboConfig$BG.enableWifi;
                TurboConfig$BG.setBooleanValue("download_ewifi", !ew);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(!ew);
                }
            } else if (position == DownloadSettingsActivity.this.disableWifiRow) {
                boolean dw = TurboConfig$BG.disableWifi;
                TurboConfig$BG.setBooleanValue("download_dwifi", !dw);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(!dw);
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
            return position == DownloadSettingsActivity.this.enableDMRow || position == DownloadSettingsActivity.this.startTimeRow || ((position == DownloadSettingsActivity.this.activeDaysRow && !TurboConfig$BG.downloadJustToday) || position == DownloadSettingsActivity.this.endTimeRow || position == DownloadSettingsActivity.this.enableWifiRow || position == DownloadSettingsActivity.this.disableWifiRow || position == DownloadSettingsActivity.this.justTodayRow);
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
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TextCheckCell checkCell = holder.itemView;
                    if (position == DownloadSettingsActivity.this.enableDMRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("DownloaderEnableScheduler", R.string.DownloaderEnableScheduler), TurboConfig$BG.downloadReceiver, true);
                        return;
                    } else if (position == DownloadSettingsActivity.this.enableWifiRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("DownloaderEnableWifi", R.string.DownloaderEnableWifi), TurboConfig$BG.enableWifi, true);
                        return;
                    } else if (position == DownloadSettingsActivity.this.disableWifiRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("DownloaderDisableWifi", R.string.DownloaderDisableWifi), TurboConfig$BG.disableWifi, true);
                        return;
                    } else if (position == DownloadSettingsActivity.this.justTodayRow) {
                        checkCell.setTextAndCheck(LocaleController.getString("DownloaderJustToday", R.string.DownloaderJustToday), TurboConfig$BG.downloadJustToday, false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextDetailSettingsCell detailSettingsCell = holder.itemView;
                    if (position == DownloadSettingsActivity.this.activeDaysRow) {
                        String text = "";
                        for (int a = 0; a < 7; a++) {
                            if (a == 0) {
                                if (TurboConfig$BG.downloadSaturday) {
                                    text = text + LocaleController.getString("Saturday", R.string.Saturday) + ", ";
                                }
                            } else if (a == 1) {
                                if (TurboConfig$BG.downloadSunday) {
                                    text = text + LocaleController.getString("Sunday", R.string.Sunday) + ", ";
                                }
                            } else if (a == 2) {
                                if (TurboConfig$BG.downloadMonday) {
                                    text = text + LocaleController.getString("Monday", R.string.Monday) + ", ";
                                }
                            } else if (a == 3) {
                                if (TurboConfig$BG.downloadTuesday) {
                                    text = text + LocaleController.getString("Tuesday", R.string.Tuesday) + ", ";
                                }
                            } else if (a == 4) {
                                if (TurboConfig$BG.downloadWednesday) {
                                    text = text + LocaleController.getString("Wednesday", R.string.Wednesday) + ", ";
                                }
                            } else if (a == 5) {
                                if (TurboConfig$BG.downloadThursday) {
                                    text = text + LocaleController.getString("Thursday", R.string.Thursday) + ", ";
                                }
                            } else if (a == 6 && TurboConfig$BG.downloadFriday) {
                                text = text + LocaleController.getString("Friday", R.string.Friday) + ", ";
                            }
                        }
                        StringBuilder textSB = new StringBuilder(text);
                        if (textSB.length() != 0) {
                            textSB.setCharAt(textSB.length() - 2, ' ');
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("DownloaderDays", R.string.DownloaderDays), String.valueOf(textSB), true);
                        detailSettingsCell.setMultilineDetail(false);
                        return;
                    }
                    return;
                case 2:
                    TextSettingsCell settingsCell = holder.itemView;
                    int hour;
                    String time;
                    if (position == DownloadSettingsActivity.this.startTimeRow) {
                        hour = TurboConfig$BG.downloadStarthour;
                        if (TurboConfig$BG.downloadStartMinute < 10) {
                            time = String.format("%d", new Object[]{Integer.valueOf(hour)}) + ":0" + String.format("%d", new Object[]{Integer.valueOf(minute)});
                        } else {
                            time = String.format("%d", new Object[]{Integer.valueOf(hour)}) + ":" + String.format("%d", new Object[]{Integer.valueOf(minute)});
                        }
                        settingsCell.setTextAndValue(LocaleController.getString("DownloaderStartTime", R.string.DownloaderStartTime), time, true);
                        return;
                    } else if (position == DownloadSettingsActivity.this.endTimeRow) {
                        hour = TurboConfig$BG.downloadEndhour;
                        if (TurboConfig$BG.downloadEndMinute < 10) {
                            time = String.format("%d", new Object[]{Integer.valueOf(hour)}) + ":0" + String.format("%d", new Object[]{Integer.valueOf(minute)});
                        } else {
                            time = String.format("%d", new Object[]{Integer.valueOf(hour)}) + ":" + String.format("%d", new Object[]{Integer.valueOf(minute)});
                        }
                        settingsCell.setTextAndValue(LocaleController.getString("DownloaderEndTime", R.string.DownloaderEndTime), time, true);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }

        public int getItemCount() {
            return DownloadSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == DownloadSettingsActivity.this.enableDMRow || position == DownloadSettingsActivity.this.enableWifiRow || position == DownloadSettingsActivity.this.disableWifiRow || position == DownloadSettingsActivity.this.justTodayRow) {
                return 0;
            }
            if (position == DownloadSettingsActivity.this.activeDaysRow) {
                return 1;
            }
            if (position == DownloadSettingsActivity.this.startTimeRow || position == DownloadSettingsActivity.this.endTimeRow) {
                return 2;
            }
            return 0;
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.enableDMRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.justTodayRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.activeDaysRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.startTimeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.endTimeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.enableWifiRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.disableWifiRow = i;
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("TabsSettings", R.string.TabsSettings));
        this.actionBar.setActionBarMenuOnItemClick(new C23431());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C23482());
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
    }

    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
    }

    public void saveReminder() {
        int sHour = TurboConfig$BG.downloadStarthour;
        int sMinute = TurboConfig$BG.downloadStartMinute;
        int eHour = TurboConfig$BG.downloadEndhour;
        int eMinute = TurboConfig$BG.downloadEndMinute;
        new DownloadReceiver().cancelAlarm(ApplicationLoader.applicationContext);
        if (TurboConfig$BG.downloadJustToday) {
            Calendar mCalendar = Calendar.getInstance();
            Calendar mCalendarEnd = Calendar.getInstance();
            mCalendar.set(11, sHour);
            mCalendar.set(12, sMinute);
            mCalendar.set(13, 0);
            mCalendarEnd.set(11, eHour);
            mCalendarEnd.set(12, eMinute);
            mCalendarEnd.set(13, 0);
            new DownloadReceiver().setAlarm(ApplicationLoader.applicationContext, mCalendar, mCalendarEnd, 100);
            return;
        }
        if (TurboConfig$BG.downloadSaturday) {
            setRepeatAlarm(1, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadSunday) {
            setRepeatAlarm(2, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadMonday) {
            setRepeatAlarm(3, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadTuesday) {
            setRepeatAlarm(4, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadWednesday) {
            setRepeatAlarm(5, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadThursday) {
            setRepeatAlarm(6, sHour, sMinute, eHour, eMinute);
        }
        if (TurboConfig$BG.downloadFriday) {
            setRepeatAlarm(7, sHour, sMinute, eHour, eMinute);
        }
    }

    private void setRepeatAlarm(int day, int sHour, int sMinute, int eHour, int eMinute) {
        Calendar mCalendar_r = Calendar.getInstance();
        Calendar mCalendarEnd_r = Calendar.getInstance();
        mCalendar_r.set(7, day);
        mCalendar_r.set(11, sHour);
        mCalendar_r.set(12, sMinute);
        mCalendar_r.set(13, 0);
        mCalendar_r.set(14, 0);
        mCalendarEnd_r.set(7, day);
        mCalendarEnd_r.set(11, eHour);
        mCalendarEnd_r.set(12, eMinute);
        mCalendarEnd_r.set(13, 0);
        mCalendarEnd_r.set(14, 0);
        new DownloadReceiver().setRepeatAlarm(ApplicationLoader.applicationContext, mCalendar_r, mCalendarEnd_r, day + 300);
    }
}
