package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

class Theme$AttachCameraDrawable extends Drawable {
    private Paint paint = new Paint(1);
    private Path segment;

    public Theme$AttachCameraDrawable() {
        int size = AndroidUtilities.dp(54.0f);
        RectF rect = new RectF(0.0f, 0.0f, (float) size, (float) size);
        this.segment = new Path();
        this.segment.moveTo((float) AndroidUtilities.dp(23.0f), (float) AndroidUtilities.dp(20.0f));
        this.segment.lineTo((float) AndroidUtilities.dp(23.0f), 0.0f);
        this.segment.arcTo(rect, -98.0f, 50.0f, false);
        this.segment.close();
    }

    public void draw(Canvas canvas) {
        canvas.save();
        int cx = AndroidUtilities.dp(27.0f);
        canvas.rotate(-90.0f, (float) cx, (float) cx);
        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon1));
                    break;
                case 1:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon2));
                    break;
                case 2:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon3));
                    break;
                case 3:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon4));
                    break;
                case 4:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon5));
                    break;
                case 5:
                    this.paint.setColor(Theme.getColor(Theme.key_chat_attachCameraIcon6));
                    break;
                default:
                    break;
            }
            canvas.rotate(60.0f, (float) cx, (float) cx);
            canvas.drawPath(this.segment, this.paint);
        }
        canvas.restore();
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
        invalidateSelf();
    }

    public int getOpacity() {
        return -2;
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(54.0f);
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(54.0f);
    }

    public int getMinimumWidth() {
        return AndroidUtilities.dp(54.0f);
    }

    public int getMinimumHeight() {
        return AndroidUtilities.dp(54.0f);
    }
}
