package turbogram.Components;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.LayoutHelper;

public class CopyTextAlert extends BottomSheet {
    private TextView closeButton;
    private TextView copyButton;
    private int scrollOffsetY;
    private Drawable shadowDrawable;
    private CustomTextView textView;

    /* renamed from: turbogram.Components.CopyTextAlert$2 */
    class C22622 implements OnClickListener {
        C22622() {
        }

        public void onClick(View v) {
            CopyTextAlert.this.dismiss();
        }
    }

    /* renamed from: turbogram.Components.CopyTextAlert$3 */
    class C22633 implements OnClickListener {
        C22633() {
        }

        public void onClick(View v) {
            int min = 0;
            int max = CopyTextAlert.this.textView.getText().length();
            if (CopyTextAlert.this.textView.isFocused()) {
                int selStart = CopyTextAlert.this.textView.getSelectionStart();
                int selEnd = CopyTextAlert.this.textView.getSelectionEnd();
                min = Math.max(0, Math.min(selStart, selEnd));
                max = Math.max(0, Math.max(selStart, selEnd));
            }
            CopyTextAlert.addToClipboard(CopyTextAlert.this.textView.getText().subSequence(min, max));
            CopyTextAlert.this.dismiss();
        }
    }

    private class CustomTextView extends TextView {
        public CustomTextView(Context context) {
            super(context);
        }

        public void setTypeface(Typeface tf) {
            super.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        }
    }

    public CopyTextAlert(Context context, String string) {
        super(context, true);
        this.shadowDrawable = context.getResources().getDrawable(R.drawable.sheet_shadow);
        this.containerView = new FrameLayout(context) {
            private boolean ignoreLayout = false;

            public boolean onInterceptTouchEvent(MotionEvent ev) {
                if (ev.getAction() != 0 || CopyTextAlert.this.scrollOffsetY == 0 || ev.getY() >= ((float) CopyTextAlert.this.scrollOffsetY)) {
                    return super.onInterceptTouchEvent(ev);
                }
                CopyTextAlert.this.dismiss();
                return true;
            }

            public boolean onTouchEvent(MotionEvent e) {
                return !CopyTextAlert.this.isDismissed() && super.onTouchEvent(e);
            }

            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int height = MeasureSpec.getSize(heightMeasureSpec);
                if (VERSION.SDK_INT >= 21) {
                    height -= AndroidUtilities.statusBarHeight;
                }
                super.onMeasure(widthMeasureSpec, heightMeasureSpec - AndroidUtilities.statusBarHeight);
            }

            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                super.onLayout(changed, left, top, right, bottom);
            }

            public void requestLayout() {
                if (!this.ignoreLayout) {
                    super.requestLayout();
                }
            }

            protected void onDraw(Canvas canvas) {
                CopyTextAlert.this.shadowDrawable.setBounds(0, CopyTextAlert.this.scrollOffsetY - CopyTextAlert.backgroundPaddingTop, getMeasuredWidth(), getMeasuredHeight());
                CopyTextAlert.this.shadowDrawable.draw(canvas);
            }
        };
        this.containerView.setWillNotDraw(false);
        this.containerView.setPadding(backgroundPaddingLeft, 0, backgroundPaddingLeft, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setPadding(5, 5, 5, 5);
        this.containerView.addView(frameLayout, LayoutHelper.createFrame(-1, -2.0f));
        ScrollView scrollView = new ScrollView(context);
        frameLayout.addView(scrollView, LayoutHelper.createFrame(-1, -2.0f, 83, 0.0f, 0.0f, 0.0f, 50.0f));
        this.textView = new CustomTextView(context);
        this.textView.setText(Emoji.replaceEmoji(string, this.textView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(20.0f), false));
        this.textView.setTextIsSelectable(true);
        this.textView.setBackgroundColor(-2);
        this.textView.setTextColor(-16777216);
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setGravity(48);
        if (VERSION.SDK_INT >= 16) {
            this.textView.setMinLines(10);
        }
        this.textView.setPadding(15, 5, 15, 5);
        LayoutParams textParams = new LayoutParams(-1, -2);
        textParams.addRule(10);
        this.textView.setLayoutParams(textParams);
        scrollView.addView(this.textView);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(2.0f);
        frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, 50.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        this.closeButton = new TextView(context);
        this.closeButton.setGravity(17);
        this.closeButton.setText(LocaleController.getString("Close", R.string.Close));
        this.closeButton.setBackgroundColor(-2);
        this.closeButton.setTextColor(-12940081);
        this.closeButton.setTextSize(1, 17.0f);
        this.closeButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        linearLayout.addView(this.closeButton, LayoutHelper.createLinear(0, -1, 1.0f));
        this.closeButton.setOnClickListener(new C22622());
        this.copyButton = new TextView(context);
        this.copyButton.setGravity(17);
        this.copyButton.setText(LocaleController.getString("Copy", R.string.Copy));
        this.copyButton.setBackgroundColor(-2);
        this.copyButton.setTextColor(-12940081);
        this.copyButton.setTextSize(1, 17.0f);
        this.copyButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        linearLayout.addView(this.copyButton, LayoutHelper.createLinear(0, -1, 1.0f));
        this.copyButton.setOnClickListener(new C22633());
    }

    public static void addToClipboard(CharSequence str) {
        try {
            ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
        } catch (Exception e) {
            FileLog.e("tmessages", e);
        }
    }

    protected boolean canDismissWithSwipe() {
        return false;
    }
}
