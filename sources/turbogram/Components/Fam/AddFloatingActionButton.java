package turbogram.Components.Fam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class AddFloatingActionButton extends FloatingActionButton {
    int mPlusColor;

    public AddFloatingActionButton(Context context) {
        this(context, null);
    }

    public AddFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void init(Context context, AttributeSet attributeSet) {
        this.mPlusColor = Theme.getColor(Theme.key_chats_actionIcon);
        super.init(context, attributeSet);
    }

    public int getPlusColor() {
        return this.mPlusColor;
    }

    public void setPlusColorResId(@ColorRes int plusColor) {
        setPlusColor(getColor(plusColor));
    }

    public void setPlusColor(int color) {
        if (this.mPlusColor != color) {
            this.mPlusColor = color;
            updateBackground();
        }
    }

    public void setIcon(@DrawableRes int icon) {
        throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
    }

    Drawable getIconDrawable() {
        final float iconSize = (float) AndroidUtilities.dp(24.0f);
        final float iconHalfSize = iconSize / 2.0f;
        final float plusHalfStroke = ((float) AndroidUtilities.dp(2.0f)) / 2.0f;
        final float plusOffset = (iconSize - ((float) AndroidUtilities.dp(14.0f))) / 2.0f;
        ShapeDrawable drawable = new ShapeDrawable(new Shape() {
            public void draw(Canvas canvas, Paint paint) {
                canvas.drawRect(plusOffset, iconHalfSize - plusHalfStroke, iconSize - plusOffset, plusHalfStroke + iconHalfSize, paint);
                canvas.drawRect(iconHalfSize - plusHalfStroke, plusOffset, plusHalfStroke + iconHalfSize, iconSize - plusOffset, paint);
            }
        });
        Paint paint = drawable.getPaint();
        paint.setColor(this.mPlusColor);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        return drawable;
    }
}
