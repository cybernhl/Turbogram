package turbogram;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.FontSettingsCell;
import turbogram.Utilities.TurboConfig;

public class FontSelectActivity extends BaseFragment {
    private ListAdapter listAdapter;
    private RecyclerListView listView;

    /* renamed from: turbogram.FontSelectActivity$1 */
    class C23581 extends ActionBarMenuOnItemClick {
        C23581() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                FontSelectActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.FontSelectActivity$2 */
    class C23592 implements OnItemClickListener {
        C23592() {
        }

        public void onItemClick(View view, int position) {
            TurboConfig.setIntValue("font_type", position);
            FontSelectActivity.this.reLunchApp();
        }
    }

    private class ListAdapter extends SelectionAdapter {
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
                    view = new FontSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    FontSettingsCell fontCell = holder.itemView;
                    fontCell.setFont(position);
                    switch (position) {
                        case 0:
                            fontCell.setText(LocaleController.getString("SystemFont", R.string.SystemFont), true);
                            return;
                        case 1:
                            fontCell.setText(LocaleController.getString("TelegramFont", R.string.TelegramFont), true);
                            return;
                        case 2:
                            fontCell.setText(LocaleController.getString("IranSansUltraLight", R.string.IranSansUltraLight), true);
                            return;
                        case 3:
                            fontCell.setText(LocaleController.getString("IranSansLight", R.string.IranSansLight), true);
                            return;
                        case 4:
                            fontCell.setText(LocaleController.getString("IranSansNormal", R.string.IranSansNormal), true);
                            return;
                        case 5:
                            fontCell.setText(LocaleController.getString("IranSansMedium", R.string.IranSansMedium), true);
                            return;
                        case 6:
                            fontCell.setText(LocaleController.getString("IranSansBold", R.string.IranSansBold), true);
                            return;
                        case 7:
                            fontCell.setText(LocaleController.getString("Afsaneh", R.string.Afsaneh), true);
                            return;
                        case 8:
                            fontCell.setText(LocaleController.getString("Dastnevis", R.string.Dastnevis), true);
                            return;
                        case 9:
                            fontCell.setText(LocaleController.getString("Homa", R.string.Homa), true);
                            return;
                        case 10:
                            fontCell.setText(LocaleController.getString("Morvarid", R.string.Morvarid), true);
                            return;
                        case 11:
                            fontCell.setText(LocaleController.getString("Titr", R.string.Titr), true);
                            return;
                        case 12:
                            fontCell.setText(LocaleController.getString("Kodak", R.string.Kodak), true);
                            return;
                        case 13:
                            fontCell.setText(LocaleController.getString("Telvision", R.string.Telvision), true);
                            return;
                        case 14:
                            fontCell.setText(LocaleController.getString("IranYekanLight", R.string.IranYekanLight), true);
                            return;
                        case 15:
                            fontCell.setText(LocaleController.getString("IranYekanNormal", R.string.IranYekanNormal), true);
                            return;
                        case 16:
                            fontCell.setText(LocaleController.getString("IranYekanBold", R.string.IranYekanBold), false);
                            return;
                        default:
                            fontCell.setText(LocaleController.getString("IranSansLight", R.string.IranSansLight), true);
                            return;
                    }
                default:
                    return;
            }
        }

        public int getItemCount() {
            return 17;
        }

        public int getItemViewType(int position) {
            return 0;
        }
    }

    public View createView(Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("Fonts", R.string.Fonts));
        this.actionBar.setActionBarMenuOnItemClick(new C23581());
        this.listAdapter = new ListAdapter(context);
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C23592());
        return this.fragmentView;
    }

    private void reLunchApp() {
        Context context = getParentActivity().getBaseContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(ConnectionsManager.FileTypeFile);
        intent.addFlags(268435456);
        if (VERSION.SDK_INT >= 11) {
            intent.addFlags(32768);
        }
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 1, PendingIntent.getActivity(context, 0, intent, 268435456));
        System.exit(2);
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }
}
