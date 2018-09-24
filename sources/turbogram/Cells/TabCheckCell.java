package turbogram.Cells;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Utilities.TurboUtils;

public class TabCheckCell extends FrameLayout {
    private CheckBoxSquare checkBox;
    private Drawable drawable;
    private TextView textView;

    public TabCheckCell(Context context) {
        super(context);
        this.textView = new TextView(context);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 20.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setGravity(19);
        this.textView.setEllipsize(TruncateAt.END);
        this.textView.setCompoundDrawablePadding(AndroidUtilities.dp(15.0f));
        this.textView.setTypeface(TurboUtils.getTurboTypeFace());
        addView(this.textView, LayoutHelper.createFrame(-1, -1.0f, 3, 17.0f, 0.0f, 54.0f, 0.0f));
        this.checkBox = new CheckBoxSquare(context, false);
        this.checkBox.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(true);
        addView(this.checkBox, LayoutHelper.createFrame(25, 25.0f, 21, 0.0f, 5.0f, 20.0f, 0.0f));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), 1073741824));
    }

    public void setChecked(boolean check) {
        this.checkBox.setChecked(check, true);
    }

    public void setTextAndIcon(String text, int resId) {
        try {
            this.textView.setText(text);
            this.drawable = getResources().getDrawable(resId);
            this.textView.setCompoundDrawablesWithIntrinsicBounds(this.drawable, null, null, null);
            this.textView.setPadding(this.textView.getPaddingLeft(), this.textView.getPaddingTop(), AndroidUtilities.dp(25.0f), this.textView.getPaddingBottom());
        } catch (Throwable e) {
            FileLog.e("tmessages", e);
        }
    }

    public void setTextColor(int textColor) {
        this.textView.setTextColor(textColor);
    }

    public void setIconColor(int color) {
        Drawable drawable = this.textView.getCompoundDrawables()[0];
        drawable.setColorFilter(color, Mode.SRC_IN);
        this.textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }
}
