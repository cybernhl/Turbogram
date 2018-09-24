package turbogram.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.GridLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.ItemDecoration;
import org.telegram.messenger.support.widget.RecyclerView.LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.State;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import turbogram.Cells.CategoryAlertCell;
import turbogram.Database.DBHelper;
import turbogram.DialogCategoryActivity;
import turbogram.Utilities.DialogsLoader;
import turbogram.Utilities.TurboConfig$BG;

public class DialogsCategoryAlert extends BottomSheet {
    private final FrameLayout frameLayout;
    private final RecyclerListView gridView;
    private final GridLayoutManager layoutManager;
    private final CategoryAlertAdapter listAdapter;
    private int scrollOffsetY;
    private Drawable shadowDrawable;
    private final TextView titleTextView;

    /* renamed from: turbogram.Components.DialogsCategoryAlert$2 */
    class C22652 implements OnTouchListener {
        C22652() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: turbogram.Components.DialogsCategoryAlert$4 */
    class C22674 extends ItemDecoration {
        C22674() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int i = 0;
            Holder holder = (Holder) parent.getChildViewHolder(view);
            if (holder != null) {
                int pos = holder.getAdapterPosition();
                outRect.left = pos % 4 == 0 ? 0 : AndroidUtilities.dp(4.0f);
                if (pos % 4 != 3) {
                    i = AndroidUtilities.dp(4.0f);
                }
                outRect.right = i;
                return;
            }
            outRect.left = AndroidUtilities.dp(4.0f);
            outRect.right = AndroidUtilities.dp(4.0f);
        }
    }

    private class CategoryAlertAdapter extends Adapter {
        private ArrayList<Integer> categoryIdsArray = new ArrayList();
        private ArrayList<String> categoryNamesArray = new ArrayList();
        private Context context;
        private DBHelper dbHelper;

        public CategoryAlertAdapter(Context context) {
            this.context = context;
            this.dbHelper = new DBHelper(context);
            getDialogsArray();
        }

        private void getDialogsArray() {
            this.categoryIdsArray.clear();
            this.categoryNamesArray.clear();
            this.categoryIdsArray.add(Integer.valueOf(-3));
            this.categoryNamesArray.add(LocaleController.getString("TurboAll", R.string.TurboAll));
            this.categoryIdsArray.add(Integer.valueOf(-2));
            this.categoryNamesArray.add(LocaleController.getString("TurboUnread", R.string.TurboUnread));
            this.categoryIdsArray.add(Integer.valueOf(-4));
            this.categoryNamesArray.add(LocaleController.getString("TurboMine", R.string.TurboMine));
            this.categoryIdsArray.add(Integer.valueOf(TurboConfig$BG.currentCat == -1 ? 0 : -1));
            this.categoryNamesArray.add(TurboConfig$BG.currentCat == -1 ? LocaleController.getString("TurboMute", R.string.TurboMute) : LocaleController.getString("TurboUnmute", R.string.TurboUnmute));
            this.dbHelper.open();
            try {
                this.categoryIdsArray.addAll(this.dbHelper.getAllCategoryIds());
                this.categoryNamesArray.addAll(this.dbHelper.getAllCategoryNames());
            } finally {
                this.dbHelper.close();
            }
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            getDialogsArray();
        }

        public int getItemCount() {
            return this.categoryNamesArray.size();
        }

        public Integer getItem(int i) {
            if (i < 0 || i >= this.categoryIdsArray.size()) {
                return null;
            }
            return (Integer) this.categoryIdsArray.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new CategoryAlertCell(this.context);
            view.setLayoutParams(new LayoutParams(-1, AndroidUtilities.dp(100.0f)));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setDialog(false, (CharSequence) this.categoryNamesArray.get(position));
        }

        public int getItemViewType(int i) {
            return 0;
        }
    }

    private class Holder extends ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public DialogsCategoryAlert(final Context context, final DialogsAdapter dialogsAdapter, final DialogsActivity dialogsActivity) {
        super(context, true);
        this.shadowDrawable = context.getResources().getDrawable(R.drawable.sheet_shadow);
        this.containerView = new FrameLayout(context) {
            private boolean ignoreLayout = false;

            public boolean onInterceptTouchEvent(MotionEvent ev) {
                if (ev.getAction() != 0 || DialogsCategoryAlert.this.scrollOffsetY == 0 || ev.getY() >= ((float) DialogsCategoryAlert.this.scrollOffsetY)) {
                    return super.onInterceptTouchEvent(ev);
                }
                DialogsCategoryAlert.this.dismiss();
                return true;
            }

            public boolean onTouchEvent(MotionEvent e) {
                return !DialogsCategoryAlert.this.isDismissed() && super.onTouchEvent(e);
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int height = MeasureSpec.getSize(heightMeasureSpec);
                if (VERSION.SDK_INT >= 21) {
                    height -= AndroidUtilities.statusBarHeight;
                }
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Math.min((AndroidUtilities.dp(48.0f) + (Math.max(3, (int) Math.ceil((double) (((float) Math.max(DialogsCategoryAlert.this.listAdapter.getItemCount(), DialogsCategoryAlert.this.listAdapter.getItemCount())) / 4.0f))) * AndroidUtilities.dp(100.0f))) + DialogsCategoryAlert.backgroundPaddingTop, height), 1073741824));
            }

            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                super.onLayout(changed, left, top, right, bottom);
                DialogsCategoryAlert.this.updateLayout();
            }

            public void requestLayout() {
                if (!this.ignoreLayout) {
                    super.requestLayout();
                }
            }

            protected void onDraw(Canvas canvas) {
                DialogsCategoryAlert.this.shadowDrawable.setBounds(0, DialogsCategoryAlert.this.scrollOffsetY - DialogsCategoryAlert.backgroundPaddingTop, getMeasuredWidth(), getMeasuredHeight());
                DialogsCategoryAlert.this.shadowDrawable.draw(canvas);
            }
        };
        this.containerView.setWillNotDraw(false);
        this.containerView.setPadding(backgroundPaddingLeft, 0, backgroundPaddingLeft, 0);
        this.frameLayout = new FrameLayout(context);
        this.frameLayout.setBackgroundColor(-1);
        this.frameLayout.setOnTouchListener(new C22652());
        this.containerView.addView(this.frameLayout, LayoutHelper.createFrame(-1, 50, 51));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setPadding(45, 0, 45, 0);
        this.frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, 50, 51));
        this.titleTextView = new TextView(context);
        this.titleTextView.setTextColor(-12303292);
        this.titleTextView.setTextSize(1, 16.0f);
        this.titleTextView.setGravity(19);
        this.titleTextView.setText(LocaleController.getString("TurboFMCategories", R.string.TurboFMCategories));
        this.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        linearLayout.addView(this.titleTextView, LayoutHelper.createLinear(-2, -1));
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.menu_settings);
        imageView.setColorFilter(new PorterDuffColorFilter(-9211021, Mode.MULTIPLY));
        imageView.setScaleType(ScaleType.CENTER);
        this.containerView.addView(imageView, LayoutHelper.createFrame(50, 50, 53));
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ((LaunchActivity) context).presentFragment(new DialogCategoryActivity());
            }
        });
        View shadow = new View(context);
        shadow.setBackgroundResource(R.drawable.header_shadow);
        this.containerView.addView(shadow, LayoutHelper.createFrame(-1, 3.0f, 51, 0.0f, 50.0f, 0.0f, 0.0f));
        this.gridView = new RecyclerListView(context);
        this.gridView.setTag(Integer.valueOf(13));
        this.gridView.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        this.gridView.setClipToPadding(false);
        RecyclerListView recyclerListView = this.gridView;
        LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        this.layoutManager = gridLayoutManager;
        recyclerListView.setLayoutManager(gridLayoutManager);
        this.gridView.setHorizontalScrollBarEnabled(false);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.gridView.addItemDecoration(new C22674());
        this.containerView.addView(this.gridView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 53.0f, 0.0f, 0.0f));
        RecyclerListView recyclerListView2 = this.gridView;
        Adapter categoryAlertAdapter = new CategoryAlertAdapter(context);
        this.listAdapter = categoryAlertAdapter;
        recyclerListView2.setAdapter(categoryAlertAdapter);
        this.gridView.setGlowColor(-657673);
        this.gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                if (position >= 0) {
                    int catId = DialogsCategoryAlert.this.listAdapter.getItem(position).intValue();
                    if (catId > 1) {
                        DialogsLoader.getCatDialogs(catId);
                    }
                    TurboConfig$BG.setIntValue("current_cat", catId);
                    dialogsAdapter.notifyDataSetChanged();
                    dialogsActivity.updateSubTitle(catId);
                    DialogsCategoryAlert.this.dismiss();
                }
            }
        });
    }

    @SuppressLint({"NewApi"})
    private void updateLayout() {
        int newOffset = 0;
        if (this.gridView.getChildCount() > 0) {
            View child = this.gridView.getChildAt(0);
            Holder holder = (Holder) this.gridView.findContainingViewHolder(child);
            int top = child.getTop() - AndroidUtilities.dp(8.0f);
            if (top > 0 && holder != null && holder.getAdapterPosition() == 0) {
                newOffset = top;
            }
            if (this.scrollOffsetY != newOffset) {
                RecyclerListView recyclerListView = this.gridView;
                this.scrollOffsetY = newOffset;
                recyclerListView.setTopGlowOffset(newOffset);
                this.frameLayout.setTranslationY((float) this.scrollOffsetY);
                this.containerView.invalidate();
            }
        }
    }
}
