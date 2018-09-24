package turbogram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils.TruncateAt;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.IntroActivity;
import turbogram.Utilities.TurboUtils;

public class IntroLanguageSelectActivity extends Activity {
    private EmptyTextProgressView emptyView;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private ListAdapter searchListViewAdapter;
    public ArrayList<LocaleController$LocaleInfo> searchResult;
    private boolean searchWas;
    private boolean searching;

    /* renamed from: turbogram.IntroLanguageSelectActivity$1 */
    class C23691 implements OnItemClickListener {
        C23691() {
        }

        public void onItemClick(View view, int position) {
            LocaleController$LocaleInfo localeInfo = null;
            if (IntroLanguageSelectActivity.this.searching && IntroLanguageSelectActivity.this.searchWas) {
                if (position >= 0 && position < IntroLanguageSelectActivity.this.searchResult.size()) {
                    localeInfo = (LocaleController$LocaleInfo) IntroLanguageSelectActivity.this.searchResult.get(position);
                }
            } else if (position >= 0 && position < LocaleController.getInstance().languages.size()) {
                localeInfo = (LocaleController$LocaleInfo) LocaleController.getInstance().languages.get(position);
            }
            if (localeInfo != null) {
                LocaleController.getInstance().applyLanguage(localeInfo, true, true, UserConfig.selectedAccount);
            }
            IntroLanguageSelectActivity.this.startActivity(new Intent(IntroLanguageSelectActivity.this, IntroActivity.class));
            IntroLanguageSelectActivity.this.finish();
        }
    }

    /* renamed from: turbogram.IntroLanguageSelectActivity$2 */
    class C23702 extends OnScrollListener {
        C23702() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 1 && IntroLanguageSelectActivity.this.searching && IntroLanguageSelectActivity.this.searchWas) {
                AndroidUtilities.hideKeyboard(IntroLanguageSelectActivity.this.getCurrentFocus());
            }
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;
        private boolean search;

        public ListAdapter(Context context, boolean isSearch) {
            this.mContext = context;
            this.search = isSearch;
        }

        public boolean isEnabled(ViewHolder holder) {
            return true;
        }

        public int getItemCount() {
            if (this.search) {
                if (IntroLanguageSelectActivity.this.searchResult == null) {
                    return 0;
                }
                return IntroLanguageSelectActivity.this.searchResult.size();
            } else if (LocaleController.getInstance().languages != null) {
                return LocaleController.getInstance().languages.size();
            } else {
                return 0;
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(new TextSettingsCell(IntroLanguageSelectActivity.this, this.mContext));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            LocaleController$LocaleInfo localeInfo;
            boolean last;
            boolean z = true;
            TextSettingsCell textSettingsCell = holder.itemView;
            if (this.search) {
                localeInfo = (LocaleController$LocaleInfo) IntroLanguageSelectActivity.this.searchResult.get(position);
                if (position == IntroLanguageSelectActivity.this.searchResult.size() - 1) {
                    last = true;
                } else {
                    last = false;
                }
            } else {
                localeInfo = (LocaleController$LocaleInfo) LocaleController.getInstance().languages.get(position);
                last = position == LocaleController.getInstance().languages.size() + -1;
            }
            String str = localeInfo.name;
            if (last) {
                z = false;
            }
            textSettingsCell.setText(str, z);
        }

        public int getItemViewType(int i) {
            return 0;
        }
    }

    public class TextSettingsCell extends FrameLayout {
        private boolean needDivider;
        private TextView textView;
        final /* synthetic */ IntroLanguageSelectActivity this$0;
        private ImageView valueImageView;
        private TextView valueTextView;

        public TextSettingsCell(IntroLanguageSelectActivity this$0, Context context) {
            int i;
            int i2;
            int i3 = 3;
            this.this$0 = this$0;
            super(context);
            this.textView = new TextView(context);
            this.textView.setTextColor(-14606047);
            this.textView.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            this.textView.setEllipsize(TruncateAt.END);
            this.textView.setGravity((LocaleController.isRTL ? 5 : 3) | 16);
            View view = this.textView;
            if (LocaleController.isRTL) {
                i = 5;
            } else {
                i = 3;
            }
            addView(view, LayoutHelper.createFrame(-1, -1.0f, i | 48, 17.0f, 0.0f, 17.0f, 0.0f));
            this.valueTextView = new TextView(context);
            this.valueTextView.setTextColor(-13660983);
            this.valueTextView.setTextSize(1, 16.0f);
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setEllipsize(TruncateAt.END);
            TextView textView = this.valueTextView;
            if (LocaleController.isRTL) {
                i2 = 3;
            } else {
                i2 = 5;
            }
            textView.setGravity(i2 | 16);
            view = this.valueTextView;
            if (LocaleController.isRTL) {
                i = 3;
            } else {
                i = 5;
            }
            addView(view, LayoutHelper.createFrame(-2, -1.0f, i | 48, 17.0f, 0.0f, 17.0f, 0.0f));
            this.valueImageView = new ImageView(context);
            this.valueImageView.setScaleType(ScaleType.CENTER);
            this.valueImageView.setVisibility(4);
            this.valueImageView.setColorFilter(new PorterDuffColorFilter(-9211021, Mode.MULTIPLY));
            view = this.valueImageView;
            if (!LocaleController.isRTL) {
                i3 = 5;
            }
            addView(view, LayoutHelper.createFrame(-2, -2.0f, i3 | 16, 17.0f, 0.0f, 17.0f, 0.0f));
            this.textView.setTypeface(TurboUtils.getTurboTypeFace());
            this.valueTextView.setTypeface(TurboUtils.getTurboTypeFace());
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (this.needDivider ? 1 : 0) + AndroidUtilities.dp(48.0f));
            int availableWidth = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
            int width = availableWidth / 2;
            if (this.valueImageView.getVisibility() == 0) {
                this.valueImageView.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            }
            if (this.valueTextView.getVisibility() == 0) {
                this.valueTextView.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                width = (availableWidth - this.valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(8.0f);
            } else {
                width = availableWidth;
            }
            this.textView.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        }

        public TextView getTextView() {
            return this.textView;
        }

        public TextView getValueTextView() {
            return this.valueTextView;
        }

        public void setTextColor(int color) {
            this.textView.setTextColor(color);
        }

        public void setTextValueColor(int color) {
            this.valueTextView.setTextColor(color);
        }

        public void setText(String text, boolean divider) {
            this.textView.setText(text);
            this.valueTextView.setVisibility(4);
            this.valueImageView.setVisibility(4);
            this.needDivider = divider;
            setWillNotDraw(!divider);
        }

        protected void onDraw(Canvas canvas) {
            if (this.needDivider) {
                Paint dividerPaint = new Paint();
                dividerPaint.setColor(-2500135);
                dividerPaint.setStrokeWidth(1.0f);
                canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), dividerPaint);
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        SpannableString title = new SpannableString(LocaleController.getString("Language", R.string.Language));
        title.setSpan(new TypefaceSpan(TurboUtils.getTurboTypeFace()), 0, title.length(), 33);
        getActionBar().setTitle(title);
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new LayoutParams(-1, -1));
        this.searching = false;
        this.searchWas = false;
        this.listAdapter = new ListAdapter(this, false);
        this.searchListViewAdapter = new ListAdapter(this, true);
        this.emptyView = new EmptyTextProgressView(this);
        this.emptyView.setText(LocaleController.getString("NoResult", R.string.NoResult));
        this.emptyView.showTextView();
        this.emptyView.setShowAtCenter(true);
        frameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(this);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new C23691());
        this.listView.setOnScrollListener(new C23702());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                startActivity(new Intent(this, IntroActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }
}
