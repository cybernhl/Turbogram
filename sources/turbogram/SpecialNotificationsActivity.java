package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings.System;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextColorCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;

public class SpecialNotificationsActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int rowCount = 0;
    private int settingsLedRow;
    private int settingsSoundRow;
    private int settingsVibrateRow;

    /* renamed from: turbogram.SpecialNotificationsActivity$1 */
    class C24281 extends ActionBarMenuOnItemClick {
        C24281() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                SpecialNotificationsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.SpecialNotificationsActivity$2 */
    class C24302 implements OnItemClickListener {
        C24302() {
        }

        public void onItemClick(View view, final int position) {
            if (position == SpecialNotificationsActivity.this.settingsVibrateRow) {
                Builder builder = new Builder(SpecialNotificationsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("Vibrate", R.string.Vibrate));
                builder.setItems(new CharSequence[]{LocaleController.getString("VibrationDisabled", R.string.VibrationDisabled), LocaleController.getString("VibrationDefault", R.string.VibrationDefault), LocaleController.getString("VibrationDefault", R.string.VibrationDefault), LocaleController.getString("Short", R.string.Short), LocaleController.getString("Long", R.string.Long)}, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0).edit();
                        if (which == 0) {
                            editor.putInt("vibrate_sc", 2);
                        } else if (which == 1) {
                            editor.putInt("vibrate_sc", 0);
                        } else if (which == 2) {
                            editor.putInt("vibrate_sc", 4);
                        } else if (which == 3) {
                            editor.putInt("vibrate_sc", 1);
                        } else if (which == 4) {
                            editor.putInt("vibrate_sc", 3);
                        }
                        editor.commit();
                        if (SpecialNotificationsActivity.this.listAdapter != null) {
                            SpecialNotificationsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                SpecialNotificationsActivity.this.showDialog(builder.create());
            } else if (position == SpecialNotificationsActivity.this.settingsSoundRow) {
                try {
                    Intent tmpIntent = new Intent("android.intent.action.RINGTONE_PICKER");
                    tmpIntent.putExtra("android.intent.extra.ringtone.TYPE", 2);
                    tmpIntent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
                    tmpIntent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(2));
                    SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0);
                    Uri currentSound = null;
                    String defaultPath = null;
                    Uri defaultUri = System.DEFAULT_NOTIFICATION_URI;
                    if (defaultUri != null) {
                        defaultPath = defaultUri.getPath();
                    }
                    String path = preferences.getString("sound_path_sc", defaultPath);
                    if (!(path == null || path.equals("NoSound"))) {
                        currentSound = path.equals(defaultPath) ? defaultUri : Uri.parse(path);
                    }
                    tmpIntent.putExtra("android.intent.extra.ringtone.EXISTING_URI", currentSound);
                    SpecialNotificationsActivity.this.startActivityForResult(tmpIntent, 12);
                    if (SpecialNotificationsActivity.this.listAdapter != null) {
                        SpecialNotificationsActivity.this.listAdapter.notifyItemChanged(position);
                    }
                } catch (Exception e) {
                    FileLog.e("tmessages", e);
                }
            } else if (position == SpecialNotificationsActivity.this.settingsLedRow && SpecialNotificationsActivity.this.getParentActivity() != null) {
                SpecialNotificationsActivity.this.createColorDialog(position);
            }
        }
    }

    public class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new TextDetailSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new TextColorCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            SharedPreferences preferences = this.mContext.getSharedPreferences("Special_notifications", 0);
            switch (holder.getItemViewType()) {
                case 0:
                    TextDetailSettingsCell detailSettingsCell = holder.itemView;
                    if (position == SpecialNotificationsActivity.this.settingsVibrateRow) {
                        int value = preferences.getInt("vibrate_sc", 3);
                        if (value == 0) {
                            detailSettingsCell.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("VibrationDefault", R.string.VibrationDefault), true);
                            return;
                        } else if (value == 1) {
                            detailSettingsCell.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("Short", R.string.Short), true);
                            return;
                        } else if (value == 2) {
                            detailSettingsCell.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("VibrationDisabled", R.string.VibrationDisabled), true);
                            return;
                        } else if (value == 3) {
                            detailSettingsCell.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("Long", R.string.Long), true);
                            return;
                        } else if (value == 4) {
                            detailSettingsCell.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("VibrationDefault", R.string.VibrationDefault), true);
                            return;
                        } else {
                            return;
                        }
                    } else if (position == SpecialNotificationsActivity.this.settingsSoundRow) {
                        String value2 = preferences.getString("sound_sc", LocaleController.getString("SoundDefault", R.string.SoundDefault));
                        if (value2.equals("NoSound")) {
                            value2 = LocaleController.getString("NoSound", R.string.NoSound);
                        }
                        detailSettingsCell.setTextAndValue(LocaleController.getString("Sound", R.string.Sound), value2, false);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    TextColorCell textColorCell = holder.itemView;
                    if (preferences.contains("color_sc")) {
                        textColorCell.setTextAndColor(LocaleController.getString("LedColor", R.string.LedColor), preferences.getInt("color_sc", -16711936), true);
                        return;
                    } else {
                        textColorCell.setTextAndColor(LocaleController.getString("LedColor", R.string.LedColor), preferences.getInt("MessagesLed", -16711936), true);
                        return;
                    }
                default:
                    return;
            }
        }

        public int getItemViewType(int i) {
            if (i == SpecialNotificationsActivity.this.settingsVibrateRow || i == SpecialNotificationsActivity.this.settingsSoundRow || i != SpecialNotificationsActivity.this.settingsLedRow) {
                return 0;
            }
            return 1;
        }

        public int getItemCount() {
            return SpecialNotificationsActivity.this.rowCount;
        }
    }

    public boolean onFragmentCreate() {
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.settingsLedRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.settingsVibrateRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.settingsSoundRow = i;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        return super.onFragmentCreate();
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("NotificationsAndSounds", R.string.NotificationsAndSounds));
        this.actionBar.setActionBarMenuOnItemClick(new C24281());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24302());
        return this.fragmentView;
    }

    public void onActivityResultFragment(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && data != null) {
            Uri ringtone = (Uri) data.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            String name = null;
            if (ringtone != null) {
                Ringtone rng = RingtoneManager.getRingtone(ApplicationLoader.applicationContext, ringtone);
                if (rng != null) {
                    if (ringtone.equals(System.DEFAULT_NOTIFICATION_URI)) {
                        name = LocaleController.getString("SoundDefault", R.string.SoundDefault);
                    } else {
                        name = rng.getTitle(getParentActivity());
                    }
                    rng.stop();
                }
            }
            Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0).edit();
            if (requestCode == 12) {
                if (name != null) {
                    editor.putString("sound_sc", name);
                    editor.putString("sound_path_sc", ringtone.toString());
                } else {
                    editor.putString("sound_sc", "NoSound");
                    editor.putString("sound_path_sc", "NoSound");
                }
            }
            editor.commit();
            this.listView.invalidateViews();
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.notificationsSettingsUpdated) {
            this.listView.invalidateViews();
        }
    }

    private void createColorDialog(final int position) {
        int currentColor;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0);
        if (preferences.contains("color_sc")) {
            currentColor = preferences.getInt("color_sc", -16711936);
        } else {
            currentColor = preferences.getInt("MessagesLed", -16711936);
        }
        final LinearLayout linearLayout = new LinearLayout(getParentActivity());
        linearLayout.setOrientation(1);
        String[] descriptions = new String[]{LocaleController.getString("ColorRed", R.string.ColorRed), LocaleController.getString("ColorOrange", R.string.ColorOrange), LocaleController.getString("ColorYellow", R.string.ColorYellow), LocaleController.getString("ColorGreen", R.string.ColorGreen), LocaleController.getString("ColorCyan", R.string.ColorCyan), LocaleController.getString("ColorBlue", R.string.ColorBlue), LocaleController.getString("ColorViolet", R.string.ColorViolet), LocaleController.getString("ColorPink", R.string.ColorPink), LocaleController.getString("ColorWhite", R.string.ColorWhite)};
        final int[] selectedColor = new int[]{currentColor};
        for (int a = 0; a < 9; a++) {
            RadioColorCell cell = new RadioColorCell(getParentActivity());
            cell.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            cell.setTag(Integer.valueOf(a));
            cell.setCheckColor(TextColorCell.colors[a], TextColorCell.colors[a]);
            cell.setTextAndValue(descriptions[a], currentColor == TextColorCell.colorsToSave[a]);
            linearLayout.addView(cell);
            cell.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int count = linearLayout.getChildCount();
                    for (int a = 0; a < count; a++) {
                        boolean z;
                        View cell = (RadioColorCell) linearLayout.getChildAt(a);
                        if (cell == v) {
                            z = true;
                        } else {
                            z = false;
                        }
                        cell.setChecked(z, true);
                    }
                    selectedColor[0] = TextColorCell.colorsToSave[((Integer) v.getTag()).intValue()];
                }
            });
        }
        Builder builder = new Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("LedColor", R.string.LedColor));
        builder.setView(linearLayout);
        builder.setPositiveButton(LocaleController.getString("Set", R.string.Set), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0).edit().putInt("color_sc", selectedColor[0]).commit();
                if (SpecialNotificationsActivity.this.listAdapter != null) {
                    SpecialNotificationsActivity.this.listAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNeutralButton(LocaleController.getString("LedDisabled", R.string.LedDisabled), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0).edit();
                editor.putInt("color_sc", 0);
                editor.commit();
                if (SpecialNotificationsActivity.this.listAdapter != null) {
                    SpecialNotificationsActivity.this.listAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Default", R.string.Default), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("Special_notifications", 0).edit();
                editor.remove("color_sc");
                editor.commit();
                if (SpecialNotificationsActivity.this.listAdapter != null) {
                    SpecialNotificationsActivity.this.listAdapter.notifyItemChanged(position);
                }
            }
        });
        showDialog(builder.create());
    }
}
