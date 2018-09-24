package turbogram.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Utilities.TurboUtils;

public class CheckStyleCell extends FrameLayout {
    private BackupImageView imageView;
    private SimpleTextView titleTextView;

    public CheckStyleCell(Context context) {
        int i;
        float f = 7.0f;
        int i2 = 5;
        super(context);
        this.imageView = new BackupImageView(context);
        this.imageView.setRoundRadius(AndroidUtilities.dp(24.0f));
        View view = this.imageView;
        int i3 = (LocaleController.isRTL ? 5 : 3) | 48;
        float f2 = LocaleController.isRTL ? 0.0f : 7.0f;
        if (!LocaleController.isRTL) {
            f = 0.0f;
        }
        addView(view, LayoutHelper.createFrame(55, 55.0f, i3, f2, 8.0f, f, 0.0f));
        this.titleTextView = new SimpleTextView(context);
        this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.titleTextView.setTextSize(16);
        SimpleTextView simpleTextView = this.titleTextView;
        if (LocaleController.isRTL) {
            i = 5;
        } else {
            i = 3;
        }
        simpleTextView.setGravity(i | 48);
        this.titleTextView.setTypeface(TurboUtils.getTurboTypeFace());
        View view2 = this.titleTextView;
        if (!LocaleController.isRTL) {
            i2 = 3;
        }
        addView(view2, LayoutHelper.createFrame(-1, 20.0f, i2 | 16, LocaleController.isRTL ? 31.0f : 71.0f, 0.0f, LocaleController.isRTL ? 71.0f : 31.0f, 0.0f));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(71.0f), 1073741824));
    }

    public void setData(String title, int resId, boolean selected, int iconSize) {
        this.titleTextView.setText(title);
        CombinedDrawable drawable = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40.0f), resId);
        drawable.setIconSize(AndroidUtilities.dp((float) iconSize), AndroidUtilities.dp((float) iconSize));
        if (selected) {
            Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_chat_outBubble), false);
            Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_chat_outSentCheck), true);
        } else {
            Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_files_folderIconBackground), false);
            Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_files_folderIcon), true);
        }
        this.imageView.setImageDrawable(drawable);
        setWillNotDraw(false);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), Theme.dividerPaint);
    }
}
