package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver.BitmapHolder;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PhotoViewer.PhotoViewerProvider;
import org.telegram.ui.PhotoViewer.PlaceProviderObject;
import turbogram.Utilities.TurboConfig;

public class ManageChatUserCell extends FrameLayout implements PhotoViewerProvider {
    private AvatarDrawable avatarDrawable = new AvatarDrawable();
    private BackupImageView avatarImageView;
    private int currentAccount = UserConfig.selectedAccount;
    private CharSequence currentName;
    private User currentUser;
    private CharSequence currrntStatus;
    private ManageChatUserCellDelegate delegate;
    private boolean isAdmin;
    private TLRPC$FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private SimpleTextView nameTextView;
    private ImageView optionsButton;
    private int statusColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
    private int statusOnlineColor = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
    private SimpleTextView statusTextView;

    /* renamed from: org.telegram.ui.Cells.ManageChatUserCell$1 */
    class C11311 implements OnClickListener {
        C11311() {
        }

        public void onClick(View v) {
            if (ManageChatUserCell.this.currentUser != null && ManageChatUserCell.this.currentUser.photo != null && ManageChatUserCell.this.currentUser.photo.photo_big != null) {
                PhotoViewer.getInstance().openPhoto(ManageChatUserCell.this.currentUser.photo.photo_big, ManageChatUserCell.this);
            }
        }
    }

    public interface ManageChatUserCellDelegate {
        boolean onOptionsButtonCheck(ManageChatUserCell manageChatUserCell, boolean z);
    }

    public ManageChatUserCell(Context context, int padding, boolean needOption) {
        float f;
        float f2;
        int i;
        int i2;
        int i3 = 3;
        super(context);
        this.avatarImageView = new BackupImageView(context);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(24.0f));
        View view = this.avatarImageView;
        int i4 = (LocaleController.isRTL ? 5 : 3) | 48;
        if (LocaleController.isRTL) {
            f = 0.0f;
        } else {
            f = (float) (padding + 7);
        }
        if (LocaleController.isRTL) {
            f2 = (float) (padding + 7);
        } else {
            f2 = 0.0f;
        }
        addView(view, LayoutHelper.createFrame(48, 48.0f, i4, f, 8.0f, f2, 0.0f));
        if (TurboConfig.userAvatarTouch == 2) {
            this.avatarImageView.setOnClickListener(new C11311());
        }
        this.nameTextView = new SimpleTextView(context);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTextSize(17);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.nameTextView.setGravity((LocaleController.isRTL ? 5 : 3) | 48);
        view = this.nameTextView;
        if (LocaleController.isRTL) {
            i = 5;
        } else {
            i = 3;
        }
        addView(view, LayoutHelper.createFrame(-1, 20.0f, i | 48, LocaleController.isRTL ? 46.0f : (float) (padding + 68), 11.5f, LocaleController.isRTL ? (float) (padding + 68) : 46.0f, 0.0f));
        this.statusTextView = new SimpleTextView(context);
        this.statusTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.statusTextView.setTextSize(14);
        SimpleTextView simpleTextView = this.statusTextView;
        if (LocaleController.isRTL) {
            i2 = 5;
        } else {
            i2 = 3;
        }
        simpleTextView.setGravity(i2 | 48);
        view = this.statusTextView;
        if (LocaleController.isRTL) {
            i = 5;
        } else {
            i = 3;
        }
        addView(view, LayoutHelper.createFrame(-1, 20.0f, i | 48, LocaleController.isRTL ? 28.0f : (float) (padding + 68), 34.5f, LocaleController.isRTL ? (float) (padding + 68) : 28.0f, 0.0f));
        if (needOption) {
            this.optionsButton = new ImageView(context);
            this.optionsButton.setFocusable(false);
            this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector)));
            this.optionsButton.setImageResource(R.drawable.ic_ab_other);
            this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_stickers_menu), Mode.MULTIPLY));
            this.optionsButton.setScaleType(ScaleType.CENTER);
            View view2 = this.optionsButton;
            if (!LocaleController.isRTL) {
                i3 = 5;
            }
            addView(view2, LayoutHelper.createFrame(48, 64, i3 | 48));
            this.optionsButton.setOnClickListener(new ManageChatUserCell$$Lambda$0(this));
        }
    }

    final /* synthetic */ void lambda$new$0$ManageChatUserCell(View v) {
        this.delegate.onOptionsButtonCheck(this, true);
    }

    public void setData(User user, CharSequence name, CharSequence status) {
        if (user == null) {
            this.currrntStatus = null;
            this.currentName = null;
            this.currentUser = null;
            this.nameTextView.setText("");
            this.statusTextView.setText("");
            this.avatarImageView.setImageDrawable(null);
            return;
        }
        this.currrntStatus = status;
        this.currentName = name;
        this.currentUser = user;
        if (this.optionsButton != null) {
            this.optionsButton.setVisibility(this.delegate.onOptionsButtonCheck(this, false) ? 0 : 4);
        }
        update(0);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), 1073741824));
    }

    public void setStatusColors(int color, int onlineColor) {
        this.statusColor = color;
        this.statusOnlineColor = onlineColor;
    }

    public void setIsAdmin(boolean value) {
        this.isAdmin = value;
    }

    public void update(int mask) {
        if (this.currentUser != null) {
            TLObject photo = null;
            String newName = null;
            if (this.currentUser.photo != null) {
                photo = this.currentUser.photo.photo_small;
            }
            if (mask != 0) {
                boolean continueUpdate = false;
                if ((mask & 2) != 0 && ((this.lastAvatar != null && photo == null) || !(this.lastAvatar != null || photo == null || this.lastAvatar == null || photo == null || (this.lastAvatar.volume_id == photo.volume_id && this.lastAvatar.local_id == photo.local_id)))) {
                    continueUpdate = true;
                }
                if (!(this.currentUser == null || continueUpdate || (mask & 4) == 0)) {
                    int newStatus = 0;
                    if (this.currentUser.status != null) {
                        newStatus = this.currentUser.status.expires;
                    }
                    if (newStatus != this.lastStatus) {
                        continueUpdate = true;
                    }
                }
                if (!(continueUpdate || this.currentName != null || this.lastName == null || (mask & 1) == 0)) {
                    newName = UserObject.getUserName(this.currentUser);
                    if (!newName.equals(this.lastName)) {
                        continueUpdate = true;
                    }
                }
                if (!continueUpdate) {
                    return;
                }
            }
            this.avatarDrawable.setInfo(this.currentUser);
            if (this.currentUser.status != null) {
                this.lastStatus = this.currentUser.status.expires;
            } else {
                this.lastStatus = 0;
            }
            if (this.currentName != null) {
                this.lastName = null;
                this.nameTextView.setText(this.currentName);
            } else {
                if (newName == null) {
                    newName = UserObject.getUserName(this.currentUser);
                }
                this.lastName = newName;
                this.nameTextView.setText(this.lastName);
            }
            if (this.currrntStatus != null) {
                this.statusTextView.setTextColor(this.statusColor);
                this.statusTextView.setText(this.currrntStatus);
            } else if (this.currentUser != null) {
                if (this.currentUser.bot) {
                    this.statusTextView.setTextColor(this.statusColor);
                    if (this.currentUser.bot_chat_history || this.isAdmin) {
                        this.statusTextView.setText(LocaleController.getString("BotStatusRead", R.string.BotStatusRead));
                    } else {
                        this.statusTextView.setText(LocaleController.getString("BotStatusCantRead", R.string.BotStatusCantRead));
                    }
                } else if (this.currentUser.id == UserConfig.getInstance(this.currentAccount).getClientUserId() || ((this.currentUser.status != null && this.currentUser.status.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) || MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Integer.valueOf(this.currentUser.id)))) {
                    this.statusTextView.setTextColor(this.statusOnlineColor);
                    this.statusTextView.setText(LocaleController.getString("Online", R.string.Online));
                } else {
                    this.statusTextView.setTextColor(this.statusColor);
                    this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, this.currentUser));
                }
            }
            this.avatarImageView.setImage(photo, "50_50", this.avatarDrawable);
        }
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setDelegate(ManageChatUserCellDelegate manageChatUserCellDelegate) {
        this.delegate = manageChatUserCellDelegate;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
        PlaceProviderObject placeProviderObject = null;
        int i = 0;
        if (!(fileLocation == null || this.currentUser == null)) {
            TLRPC$FileLocation photoBig;
            if (this.currentUser == null || this.currentUser.photo == null || this.currentUser.photo.photo_big == null) {
                photoBig = null;
            } else {
                photoBig = this.currentUser.photo.photo_big;
            }
            if (photoBig != null && photoBig.local_id == fileLocation.local_id && photoBig.volume_id == fileLocation.volume_id && photoBig.dc_id == fileLocation.dc_id) {
                int[] coords = new int[2];
                this.avatarImageView.getLocationInWindow(coords);
                placeProviderObject = new PlaceProviderObject();
                placeProviderObject.viewX = coords[0];
                int i2 = coords[1];
                if (VERSION.SDK_INT < 21) {
                    i = AndroidUtilities.statusBarHeight;
                }
                placeProviderObject.viewY = i2 - i;
                placeProviderObject.parentView = this.avatarImageView;
                placeProviderObject.imageReceiver = this.avatarImageView.getImageReceiver();
                placeProviderObject.dialogId = this.currentUser.id;
                placeProviderObject.thumb = placeProviderObject.imageReceiver.getBitmapSafe();
                placeProviderObject.size = -1;
                placeProviderObject.radius = this.avatarImageView.getImageReceiver().getRoundRadius();
                placeProviderObject.scale = this.avatarImageView.getScaleX();
            }
        }
        return placeProviderObject;
    }

    public BitmapHolder getThumbForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
        return null;
    }

    public void willSwitchFromPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
    }

    public void willHidePhotoViewer() {
    }

    public boolean isPhotoChecked(int index) {
        return false;
    }

    public int setPhotoChecked(int index, VideoEditedInfo videoEditedInfo) {
        return 0;
    }

    public int setPhotoUnchecked(Object photoEntry) {
        return 0;
    }

    public boolean cancelButtonPressed() {
        return false;
    }

    public void needAddMorePhotos() {
    }

    public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {
    }

    public int getSelectedCount() {
        return 0;
    }

    public void updatePhotoAtIndex(int index) {
    }

    public boolean allowCaption() {
        return false;
    }

    public boolean scaleToFill() {
        return false;
    }

    public void toggleGroupPhotosEnabled() {
    }

    public ArrayList<Object> getSelectedPhotosOrder() {
        return null;
    }

    public HashMap<Object, Object> getSelectedPhotos() {
        return null;
    }

    public boolean canScrollAway() {
        return false;
    }

    public boolean allowGroupPhotos() {
        return false;
    }

    public int getPhotoIndex(int index) {
        return 0;
    }

    public void deleteImageAtIndex(int index) {
    }

    public String getDeleteMessageString() {
        return null;
    }

    public boolean canCaptureMorePhotos() {
        return false;
    }

    public void paintButtonPressed(MessageObject messageObject) {
    }
}
