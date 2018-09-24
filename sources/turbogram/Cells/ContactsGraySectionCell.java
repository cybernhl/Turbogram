package turbogram.Cells;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboUtils;

public class ContactsGraySectionCell extends FrameLayout {
    private Switch checkBox;
    private final TextView onlineTextView;
    private TextView textView = new TextView(getContext());

    public ContactsGraySectionCell(Context context) {
        int i;
        int i2;
        int i3 = 3;
        super(context);
        setBackgroundColor(Theme.getColor(Theme.key_graySection));
        this.textView.setTextSize(1, 13.0f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        this.textView.setGravity((LocaleController.isRTL ? 5 : 3) | 16);
        View view = this.textView;
        if (LocaleController.isRTL) {
            i = 5;
        } else {
            i = 3;
        }
        addView(view, LayoutHelper.createFrame(-1, -1.0f, i | 48, 16.0f, 0.0f, 16.0f, 0.0f));
        this.textView.setTypeface(TurboUtils.getTurboTypeFace());
        this.onlineTextView = new TextView(getContext());
        this.onlineTextView.setText(LocaleController.getString("TurboOnlines", R.string.TurboOnlines));
        this.onlineTextView.setTextSize(1, 13.0f);
        this.onlineTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.onlineTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        TextView textView = this.onlineTextView;
        if (LocaleController.isRTL) {
            i2 = 3;
        } else {
            i2 = 5;
        }
        textView.setGravity(i2 | 16);
        view = this.onlineTextView;
        if (LocaleController.isRTL) {
            i = 3;
        } else {
            i = 5;
        }
        addView(view, LayoutHelper.createFrame(-1, -1.0f, i | 48, LocaleController.isRTL ? 70.0f : 16.0f, 0.0f, LocaleController.isRTL ? 16.0f : 70.0f, 0.0f));
        this.checkBox = new Switch(context);
        this.checkBox.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        view = this.checkBox;
        if (!LocaleController.isRTL) {
            i3 = 5;
        }
        addView(view, LayoutHelper.createFrame(-2, -2.0f, i3 | 16, 14.0f, 0.0f, 14.0f, 0.0f));
        this.checkBox.setChecked(TurboConfig$BG.showOnlines);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), 1073741824));
    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setChecked(boolean checked) {
        this.checkBox.setChecked(checked);
    }
}
