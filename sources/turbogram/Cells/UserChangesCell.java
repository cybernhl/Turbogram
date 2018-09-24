package turbogram.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.baranak.turbogramf.R;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import turbogram.Models.UpdateModel;
import turbogram.Utilities.SolarCalendar;
import turbogram.Utilities.TurboUtils;

public class UserChangesCell extends FrameLayout {
    private static Paint paint;
    private AvatarDrawable avatarDrawable = new AvatarDrawable();
    private BackupImageView avatarImageView;
    private User currentUser = null;
    private SimpleTextView dateTextView;
    private ImageView imageView;
    private String lastName = null;
    private SimpleTextView nameTextView;
    private SimpleTextView newValueTextView;
    private SimpleTextView oldValueTextView;
    private UpdateModel updateModel;

    @SuppressLint({"RtlHardcoded"})
    public UserChangesCell(Context context, int i) {
        super(context);
        paint = new Paint(1);
        this.avatarImageView = new BackupImageView(context);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(24.0f));
        boolean isRTL = LocaleController.isRTL;
        addView(this.avatarImageView, LayoutHelper.createFrame(48, 48.0f, (isRTL ? 5 : 3) | 48, isRTL ? 0.0f : (float) (i + 7), 8.0f, isRTL ? (float) (i + 7) : 0.0f, 0.0f));
        this.nameTextView = new SimpleTextView(context);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTextSize(17);
        this.nameTextView.setGravity((isRTL ? 5 : 3) | 48);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        addView(this.nameTextView, LayoutHelper.createFrame(-1, 20.0f, (isRTL ? 5 : 3) | 48, isRTL ? 28.0f : (float) (i + 68), 11.5f, isRTL ? (float) (i + 68) : 28.0f, 0.0f));
        this.newValueTextView = new SimpleTextView(context);
        this.newValueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
        this.newValueTextView.setTextSize(14);
        this.newValueTextView.setGravity((isRTL ? 5 : 3) | 48);
        this.newValueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        addView(this.newValueTextView, LayoutHelper.createFrame(-1, 20.0f, (isRTL ? 5 : 3) | 48, isRTL ? 28.0f : (float) (i + 68), 36.0f, isRTL ? (float) (i + 68) : 28.0f, 0.0f));
        this.oldValueTextView = new SimpleTextView(context);
        this.oldValueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
        this.oldValueTextView.setTextSize(14);
        this.oldValueTextView.setGravity((isRTL ? 5 : 3) | 48);
        this.oldValueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        addView(this.oldValueTextView, LayoutHelper.createFrame(-1, 20.0f, (isRTL ? 5 : 3) | 48, isRTL ? 28.0f : (float) (i + 68), 58.0f, isRTL ? (float) (i + 68) : 28.0f, 0.0f));
        this.dateTextView = new SimpleTextView(context);
        this.dateTextView.setTextColor(Theme.getColor(Theme.key_chats_message));
        this.dateTextView.setTextSize(14);
        this.dateTextView.setGravity((isRTL ? 3 : 5) | 48);
        this.dateTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        addView(this.dateTextView, LayoutHelper.createFrame(-1, 20.0f, (isRTL ? 3 : 5) | 48, isRTL ? (float) (i + 5) : 28.0f, 77.5f, isRTL ? 28.0f : (float) (i + 10), 0.0f));
        this.imageView = new ImageView(context);
        this.imageView.setScaleType(ScaleType.CENTER);
        this.imageView.setImageResource(R.drawable.turbo_new_uc);
        this.imageView.setVisibility(4);
        addView(this.imageView, LayoutHelper.createFrame(-2, -2.0f, (LocaleController.isRTL ? 3 : 5) | 48, LocaleController.isRTL ? 5.0f : 74.0f, 5.0f, LocaleController.isRTL ? 74.0f : 5.0f, 0.0f));
        TurboUtils.setColorFilter(this.imageView, Theme.getColor(Theme.key_chats_message));
    }

    public BackupImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(104.0f), 1073741824));
    }

    public void setData(UpdateModel updateModel) {
        int i = 0;
        User user = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(updateModel.getUserId()));
        if (user == null) {
            this.nameTextView.setText("");
            this.avatarImageView.setImageDrawable(null);
        }
        this.currentUser = user;
        this.updateModel = updateModel;
        setWillNotDraw(false);
        ImageView imageView = this.imageView;
        if (!updateModel.isNew()) {
            i = 4;
        }
        imageView.setVisibility(i);
        update();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), Theme.dividerPaint);
    }

    public void update() {
        if (this.currentUser != null) {
            TLObject photo = null;
            if (this.currentUser.photo != null) {
                photo = this.currentUser.photo.photo_small;
            }
            this.avatarDrawable.setInfo(this.currentUser);
            this.avatarImageView.setImage(photo, "50_50", this.avatarDrawable);
            this.lastName = ContactsController.formatName(this.currentUser.first_name, this.currentUser.last_name);
            this.nameTextView.setText(this.lastName);
        }
        if (this.updateModel.getType() == 1) {
            this.newValueTextView.setText(LocaleController.getString("changed_username", R.string.changed_username));
            this.oldValueTextView.setText(LocaleController.getString("new username", R.string.new_username) + " " + this.updateModel.getNewValue());
        } else if (this.updateModel.getType() == 2) {
            this.newValueTextView.setText(LocaleController.getString("changed_name", R.string.changed_name));
            this.oldValueTextView.setText(LocaleController.getString("new name", R.string.new_name) + " " + this.updateModel.getNewValue());
        } else if (this.updateModel.getType() == 3) {
            this.oldValueTextView.setText("");
            this.newValueTextView.setText(LocaleController.getString("changed_photo", R.string.changed_photo));
        } else if (this.updateModel.getType() == 4) {
            this.newValueTextView.setText(LocaleController.getString("changed_phone", R.string.changed_phone));
            this.oldValueTextView.setText(LocaleController.getString("new phone", R.string.new_phone) + " " + this.updateModel.getNewValue());
        }
        Long valueOf = Long.valueOf(Long.parseLong(this.updateModel.getChangeDate()));
        if (valueOf.longValue() != 0) {
            Date date = new Date(valueOf.longValue());
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            this.dateTextView.setText(formatDate(date) + " - " + format(instance.get(11), 2) + ":" + format(instance.get(12), 2));
        }
    }

    public static String formatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SolarCalendar(cal).getShortDesDate();
    }

    public static String format(int var0, int var1) {
        char[] var2 = new char[var1];
        Arrays.fill(var2, '0');
        return new DecimalFormat(String.valueOf(var2)).format((long) var0);
    }
}
