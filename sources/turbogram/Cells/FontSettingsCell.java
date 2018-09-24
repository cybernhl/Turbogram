package turbogram.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class FontSettingsCell extends FrameLayout {
    private Context context;
    private boolean needDivider;
    private TextView textView;
    private ImageView valueImageView;
    private TextView valueTextView;

    public FontSettingsCell(Context context) {
        int i;
        int i2;
        int i3 = 3;
        super(context);
        this.context = context;
        this.textView = new TextView(context);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
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
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.valueImageView = new ImageView(context);
        this.valueImageView.setScaleType(ScaleType.CENTER);
        this.valueImageView.setVisibility(4);
        view = this.valueImageView;
        if (!LocaleController.isRTL) {
            i3 = 5;
        }
        addView(view, LayoutHelper.createFrame(-2, -2.0f, i3 | 16, 17.0f, 0.0f, 17.0f, 0.0f));
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

    public void setTextColor(int color) {
        this.textView.setTextColor(color);
    }

    public void setText(String text, boolean divider) {
        this.textView.setText(text);
        this.valueTextView.setVisibility(4);
        this.valueImageView.setVisibility(4);
        this.needDivider = divider;
        setWillNotDraw(!divider);
    }

    public void setFont(int font) {
        Typeface face;
        switch (font) {
            case 0:
                face = Typeface.DEFAULT;
                break;
            case 1:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/rmedium.ttf");
                break;
            case 2:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans_ultralight.ttf");
                break;
            case 3:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans_light.ttf");
                break;
            case 4:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans.ttf");
                break;
            case 5:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans_medium.ttf");
                break;
            case 6:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans_bold.ttf");
                break;
            case 7:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/afsaneh.ttf");
                break;
            case 8:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/dastnevis.ttf");
                break;
            case 9:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/hama.ttf");
                break;
            case 10:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/morvarid.ttf");
                break;
            case 11:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/titr.ttf");
                break;
            case 12:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/koodak.ttf");
                break;
            case 13:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/tv.ttf");
                break;
            case 14:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iranyekan_light.ttf");
                break;
            case 15:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iranyekan.ttf");
                break;
            case 16:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iranyekan_bold.ttf");
                break;
            default:
                face = Typeface.createFromAsset(this.context.getAssets(), "fonts/iransans_light.ttf");
                break;
        }
        this.textView.setTypeface(face);
        if (font == -1) {
            this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.needDivider) {
            canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), Theme.dividerPaint);
        }
    }
}
