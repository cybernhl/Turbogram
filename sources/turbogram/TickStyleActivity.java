package turbogram;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import com.google.android.gms.measurement.AppMeasurement.Param;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import turbogram.Cells.CheckStyleCell;
import turbogram.Utilities.TurboConfig;

public class TickStyleActivity extends BaseFragment {
    public static Integer[] bubblesDrawableArray;
    public static String[] bubblesNamesArray;
    public static Integer[] checkDrawableArray;
    public static String[] checksNamesArray;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int type;

    /* renamed from: turbogram.TickStyleActivity$1 */
    class C24621 extends ActionBarMenuOnItemClick {
        C24621() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TickStyleActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.TickStyleActivity$2 */
    class C24632 implements OnItemClickListener {
        C24632() {
        }

        public void onItemClick(View view, int position) {
            String name;
            if (TickStyleActivity.this.type == 0) {
                name = TickStyleActivity.bubblesNamesArray[position];
                if (!TurboConfig.turboBubbleStyle.equals(name)) {
                    TurboConfig.setStringValue("turbo_bubble_style", name);
                    Theme.setBubbleStyle(TickStyleActivity.this.getParentActivity().getResources());
                    Theme.applyChatTheme(false);
                }
            } else {
                name = TickStyleActivity.checksNamesArray[position];
                if (!TurboConfig.turboCheckStyle.equals(name)) {
                    TurboConfig.setStringValue("turbo_check_style", name);
                    Theme.setTickStyle(TickStyleActivity.this.getParentActivity().getResources());
                    Theme.applyChatTheme(false);
                }
            }
            TickStyleActivity.this.listAdapter.notifyDataSetChanged();
            TickStyleActivity.this.finishFragment();
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new CheckStyleCell(this.mContext);
            view.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            CheckStyleCell menuItemCell = holder.itemView;
            if (TickStyleActivity.this.type == 0) {
                menuItemCell.setData(TickStyleActivity.bubblesNamesArray[position], TickStyleActivity.bubblesDrawableArray[position].intValue(), TickStyleActivity.bubblesNamesArray[position].equals(TurboConfig.turboBubbleStyle), 32);
            } else {
                menuItemCell.setData(TickStyleActivity.checksNamesArray[position], TickStyleActivity.checkDrawableArray[position].intValue(), TickStyleActivity.checksNamesArray[position].equals(TurboConfig.turboCheckStyle), 25);
            }
        }

        public int getItemCount() {
            if (TickStyleActivity.this.type == 0) {
                return TickStyleActivity.bubblesNamesArray.length;
            }
            return TickStyleActivity.checksNamesArray.length;
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }
    }

    public TickStyleActivity(Bundle args) {
        super(args);
    }

    public boolean onFragmentCreate() {
        this.type = this.arguments.getInt(Param.TYPE, 0);
        return super.onFragmentCreate();
    }

    public View createView(Context context) {
        String title;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        if (this.type == 0) {
            title = LocaleController.getString("BubbleStyle", R.string.BubbleStyle);
        } else {
            title = LocaleController.getString("CheckStyle", R.string.CheckStyle);
        }
        this.actionBar.setTitle(title);
        this.actionBar.setActionBarMenuOnItemClick(new C24621());
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        bubblesNamesArray = new String[]{"Telegram", "iOS", "Telegram_Old", "Hangout", "Notepad", "Lex", "OvaLex", "LineFineLex", "PictuLineLex", "Ed"};
        bubblesDrawableArray = new Integer[]{Integer.valueOf(R.drawable.msg_in), Integer.valueOf(R.drawable.msg_in_2), Integer.valueOf(R.drawable.msg_in_3), Integer.valueOf(R.drawable.msg_in_4), Integer.valueOf(R.drawable.msg_in_5), Integer.valueOf(R.drawable.msg_in_6), Integer.valueOf(R.drawable.msg_in_7), Integer.valueOf(R.drawable.msg_in_8), Integer.valueOf(R.drawable.msg_in_9), Integer.valueOf(R.drawable.msg_in_10)};
        checksNamesArray = new String[]{"Stock", "EdCheck", "Lex", "Gladiator", "MaxChecks", "ElipLex", "CubeLex", "MaxLines", "RLex", "MaxLinesPro", "ReadLex", "MaxHeart"};
        checkDrawableArray = new Integer[]{Integer.valueOf(R.drawable.msg_check), Integer.valueOf(R.drawable.msg_check_w_2), Integer.valueOf(R.drawable.msg_check_w_3), Integer.valueOf(R.drawable.msg_check_w_4), Integer.valueOf(R.drawable.msg_check_w_5), Integer.valueOf(R.drawable.msg_check_w_6), Integer.valueOf(R.drawable.msg_check_w_7), Integer.valueOf(R.drawable.msg_check_w_8), Integer.valueOf(R.drawable.msg_check_w_9), Integer.valueOf(R.drawable.msg_check_w_10), Integer.valueOf(R.drawable.msg_check_w_11), Integer.valueOf(R.drawable.msg_check_w_12)};
        this.listAdapter = new ListAdapter(context);
        this.listView = new RecyclerListView(context);
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24632());
        return this.fragmentView;
    }
}
