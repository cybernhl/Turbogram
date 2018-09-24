package turbogram.Cells;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Utilities.TurboConfig$Tabs;
import turbogram.Utilities.TurboUtils;

public class TabsViewCell extends FrameLayout {
    private TextView countTxt;
    private GradientDrawable counterDrawable;
    private Drawable iconDrawable;
    private ImageView imageView;

    public TabsViewCell(Context context, boolean needCounter) {
        super(context);
        this.imageView = new ImageView(context);
        this.imageView.setFocusable(true);
        this.imageView.setScaleType(ScaleType.CENTER_INSIDE);
        addView(this.imageView);
        LinearLayout container = new LinearLayout(context);
        container.setGravity(17);
        addView(container);
        if (needCounter) {
            this.counterDrawable = new GradientDrawable();
            this.counterDrawable.setCornerRadius((float) AndroidUtilities.dp(13.0f));
            this.countTxt = new TextView(context);
            this.countTxt.setGravity(17);
            this.countTxt.setTextSize(1, 13.0f);
            this.countTxt.setTypeface(TurboUtils.getTurboTypeFace());
            this.countTxt.setBackgroundDrawable(this.counterDrawable);
            this.countTxt.setMinWidth(AndroidUtilities.dp(17.0f));
            this.countTxt.setPadding(AndroidUtilities.dp(4.2f), AndroidUtilities.dp(-1.4f), AndroidUtilities.dp(4.2f), AndroidUtilities.dp(2.0f));
            container.addView(this.countTxt, LayoutHelper.createLinear(-2, 17, 81, 10, 0, 0, 3));
        }
    }

    public void setData(Drawable drawable) {
        this.iconDrawable = drawable;
        this.imageView.setImageDrawable(this.iconDrawable);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_tabIcons) == -1 ? TurboUtils.getLighterColor(Theme.getColor(Theme.key_actionBarDefaultIcon), 0.4f) : Theme.getColor(Theme.key_tabIcons), Mode.SRC_IN));
    }

    public void select() {
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_tabSelectedIcon) == -1 ? Theme.getColor(Theme.key_actionBarDefaultIcon) : Theme.getColor(Theme.key_tabSelectedIcon), Mode.SRC_IN));
    }

    public void unselect() {
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_tabIcons) == -1 ? TurboUtils.getLighterColor(Theme.getColor(Theme.key_actionBarDefaultIcon), 0.4f) : Theme.getColor(Theme.key_tabIcons), Mode.SRC_IN));
    }

    public void updateCounter(Integer count, boolean unmute) {
        if (this.countTxt != null) {
            if (!TurboConfig$Tabs.tabsCounter || count.intValue() == 0) {
                this.countTxt.setVisibility(4);
            } else {
                this.countTxt.setVisibility(0);
                if (count.intValue() > 999) {
                    this.countTxt.setText("+" + String.format("%d", new Object[]{Integer.valueOf(999)}));
                } else {
                    this.countTxt.setText(String.format("%d", new Object[]{count}));
                }
            }
            int backColor = Theme.getColor(Theme.key_tabCounter) == -1 ? Theme.getColor(Theme.key_actionBarDefaultIcon) : Theme.getColor(Theme.key_tabCounter);
            int textColor = Theme.getColor(Theme.key_tabCounterText) == -1 ? Theme.getColor(Theme.key_actionBarDefault) : Theme.getColor(Theme.key_tabCounterText);
            int backMuteColor = Theme.getColor(Theme.key_muteTabCounter) == -1 ? TurboUtils.getLighterColor(Theme.getColor(Theme.key_actionBarDefaultIcon), 0.5f) : Theme.getColor(Theme.key_muteTabCounter);
            int textMuteColor = Theme.getColor(Theme.key_muteTabCounterText) == -1 ? Theme.getColor(Theme.key_actionBarDefault) : Theme.getColor(Theme.key_muteTabCounterText);
            if (unmute) {
                this.countTxt.setTextColor(textColor);
                this.counterDrawable.setColor(backColor);
                return;
            }
            this.countTxt.setTextColor(textMuteColor);
            this.counterDrawable.setColor(backMuteColor);
        }
    }
}
