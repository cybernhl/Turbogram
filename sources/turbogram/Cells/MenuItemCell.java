package turbogram.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Utilities.TurboUtils;

public class MenuItemCell extends FrameLayout {
    private BackupImageView imageView;
    private ImageView optionsButton;
    private SimpleTextView titleTextView;

    public MenuItemCell(Context context, int padding) {
        int i;
        int i2;
        int i3 = 3;
        super(context);
        this.imageView = new BackupImageView(context);
        this.imageView.setRoundRadius(AndroidUtilities.dp(24.0f));
        addView(this.imageView, LayoutHelper.createFrame(45, 45.0f, (LocaleController.isRTL ? 5 : 3) | 48, LocaleController.isRTL ? 0.0f : (float) (padding + 7), 8.0f, LocaleController.isRTL ? (float) (padding + 7) : 0.0f, 0.0f));
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
        View view = this.titleTextView;
        if (LocaleController.isRTL) {
            i2 = 5;
        } else {
            i2 = 3;
        }
        addView(view, LayoutHelper.createFrame(-1, 20.0f, i2 | 48, LocaleController.isRTL ? 28.0f : (float) (padding + 68), 20.0f, LocaleController.isRTL ? (float) (padding + 68) : 28.0f, 0.0f));
        this.optionsButton = new ImageView(context);
        this.optionsButton.setFocusable(false);
        this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector)));
        this.optionsButton.setImageResource(R.drawable.chats_delete);
        this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), Mode.MULTIPLY));
        this.optionsButton.setScaleType(ScaleType.CENTER);
        View view2 = this.optionsButton;
        if (!LocaleController.isRTL) {
            i3 = 5;
        }
        addView(view2, LayoutHelper.createFrame(48, 64, i3 | 48));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(63.0f), 1073741824));
    }

    public void setOnMenuClick(OnClickListener listener) {
        this.optionsButton.setOnClickListener(listener);
    }

    public void active() {
        this.imageView.setAlpha(1.0f);
        this.titleTextView.setAlpha(1.0f);
        this.titleTextView.setAlpha(1.0f);
    }

    public void inActive() {
        this.imageView.setAlpha(0.3f);
        this.titleTextView.setAlpha(0.3f);
        this.titleTextView.setAlpha(0.3f);
    }

    public void setData(String title, int resId, boolean showDelButton) {
        this.titleTextView.setText(title);
        Drawable drawable = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40.0f), resId);
        Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_files_folderIconBackground), false);
        Theme.setCombinedDrawableColor(drawable, Theme.getColor(Theme.key_files_folderIcon), true);
        this.imageView.setImageDrawable(drawable);
        this.optionsButton.setVisibility(showDelButton ? 0 : 4);
        setWillNotDraw(false);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), Theme.dividerPaint);
    }
}
