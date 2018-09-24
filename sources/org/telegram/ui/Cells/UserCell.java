package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
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
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PhotoViewer.PhotoViewerProvider;
import org.telegram.ui.PhotoViewer.PlaceProviderObject;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class UserCell extends FrameLayout implements PhotoViewerProvider {
    private ImageView adminImage;
    private AvatarDrawable avatarDrawable = new AvatarDrawable();
    private BackupImageView avatarImageView;
    private CheckBox checkBox;
    private CheckBoxSquare checkBoxBig;
    private int currentAccount = UserConfig.selectedAccount;
    private int currentDrawable;
    private int currentId;
    private CharSequence currentName;
    private TLObject currentObject;
    private CharSequence currrntStatus;
    private ImageView imageView;
    private TLRPC$FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private ImageView mutualImageView;
    private SimpleTextView nameTextView;
    private int statusColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
    private int statusOnlineColor = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
    private SimpleTextView statusTextView;

    /* renamed from: org.telegram.ui.Cells.UserCell$1 */
    class C11521 implements OnClickListener {
        C11521() {
        }

        public void onClick(View v) {
            if (UserCell.this.currentObject instanceof User) {
                User user = (User) UserCell.this.currentObject;
                if (user != null && user.photo != null && user.photo.photo_big != null) {
                    PhotoViewer.getInstance().openPhoto(user.photo.photo_big, UserCell.this);
                }
            }
        }
    }

    public UserCell(Context context, int padding, int checkbox, boolean admin) {
        float f;
        float f2;
        super(context);
        this.avatarImageView = new BackupImageView(context);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(24.0f));
        View view = this.avatarImageView;
        int i = (LocaleController.isRTL ? 5 : 3) | 48;
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
        addView(view, LayoutHelper.createFrame(48, 48.0f, i, f, 8.0f, f2, 0.0f));
        if (TurboConfig.userAvatarTouch == 2) {
            this.avatarImageView.setOnClickListener(new C11521());
        }
        this.nameTextView = new SimpleTextView(context);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTextSize(17);
        this.nameTextView.setGravity((LocaleController.isRTL ? 5 : 3) | 48);
        view = this.nameTextView;
        i = (LocaleController.isRTL ? 5 : 3) | 48;
        if (LocaleController.isRTL) {
            f = (float) ((checkbox == 2 ? 18 : 0) + 28);
        } else {
            f = (float) (padding + 68);
        }
        if (LocaleController.isRTL) {
            f2 = (float) (padding + 68);
        } else {
            f2 = (float) ((checkbox == 2 ? 18 : 0) + 28);
        }
        addView(view, LayoutHelper.createFrame(-1, 20.0f, i, f, 11.5f, f2, 0.0f));
        this.statusTextView = new SimpleTextView(context);
        this.statusTextView.setTextSize(14);
        this.statusTextView.setGravity((LocaleController.isRTL ? 5 : 3) | 48);
        addView(this.statusTextView, LayoutHelper.createFrame(-1, 20.0f, (LocaleController.isRTL ? 5 : 3) | 48, LocaleController.isRTL ? 28.0f : (float) (padding + 68), 34.5f, LocaleController.isRTL ? (float) (padding + 68) : 28.0f, 0.0f));
        this.nameTextView.setTypeface(TurboUtils.getTurboTypeFace());
        this.statusTextView.setTypeface(TurboUtils.getTurboTypeFace());
        this.imageView = new ImageView(context);
        this.imageView.setScaleType(ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), Mode.MULTIPLY));
        this.imageView.setVisibility(8);
        addView(this.imageView, LayoutHelper.createFrame(-2, -2.0f, (LocaleController.isRTL ? 5 : 3) | 16, LocaleController.isRTL ? 0.0f : 16.0f, 0.0f, LocaleController.isRTL ? 16.0f : 0.0f, 0.0f));
        if (checkbox == 2) {
            this.checkBoxBig = new CheckBoxSquare(context, false);
            addView(this.checkBoxBig, LayoutHelper.createFrame(18, 18.0f, (LocaleController.isRTL ? 3 : 5) | 16, LocaleController.isRTL ? 19.0f : 0.0f, 0.0f, LocaleController.isRTL ? 0.0f : 19.0f, 0.0f));
        } else if (checkbox == 1) {
            this.checkBox = new CheckBox(context, R.drawable.round_check2);
            this.checkBox.setVisibility(4);
            this.checkBox.setColor(Theme.getColor(Theme.key_checkbox), Theme.getColor(Theme.key_checkboxCheck));
            addView(this.checkBox, LayoutHelper.createFrame(22, 22.0f, (LocaleController.isRTL ? 5 : 3) | 48, LocaleController.isRTL ? 0.0f : (float) (padding + 37), 38.0f, LocaleController.isRTL ? (float) (padding + 37) : 0.0f, 0.0f));
        }
        if (admin) {
            this.adminImage = new ImageView(context);
            this.adminImage.setImageResource(R.drawable.admin_star);
            addView(this.adminImage, LayoutHelper.createFrame(16, 16.0f, (LocaleController.isRTL ? 5 : 3) | 16, LocaleController.isRTL ? 0.0f : 16.0f, 0.0f, LocaleController.isRTL ? 16.0f : 0.0f, 0.0f));
        }
        this.mutualImageView = new ImageView(context);
        this.mutualImageView.setScaleType(ScaleType.CENTER);
        this.mutualImageView.setImageResource(R.drawable.turbo_ic_mutual);
        this.mutualImageView.setVisibility(8);
        this.mutualImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText), Mode.MULTIPLY));
        addView(this.mutualImageView, LayoutHelper.createFrame(-2, -2.0f, (checkbox == 2 ? 48 : 16) | (LocaleController.isRTL ? 3 : 5), 16.0f, 0.0f, 16.0f, 0.0f));
    }

    public void setIsAdmin(int value) {
        if (this.adminImage != null) {
            int i;
            this.adminImage.setVisibility(value != 0 ? 0 : 8);
            SimpleTextView simpleTextView = this.nameTextView;
            if (!LocaleController.isRTL || value == 0) {
                i = 0;
            } else {
                i = AndroidUtilities.dp(0.0f);
            }
            int dp = (LocaleController.isRTL || value == 0) ? 0 : AndroidUtilities.dp(16.0f);
            simpleTextView.setPadding(i, 0, dp, 0);
            if (value == 1) {
                setTag(Theme.key_profile_creatorIcon);
                this.adminImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_profile_creatorIcon), Mode.MULTIPLY));
            } else if (value == 2) {
                setTag(Theme.key_profile_adminIcon);
                this.adminImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_profile_adminIcon), Mode.MULTIPLY));
            }
        }
    }

    public void setData(TLObject object, CharSequence name, CharSequence status, int resId) {
        if (object == null && name == null && status == null) {
            this.currrntStatus = null;
            this.currentName = null;
            this.currentObject = null;
            this.nameTextView.setText("");
            this.statusTextView.setText("");
            this.avatarImageView.setImageDrawable(null);
            return;
        }
        User u = (User) object;
        if (TurboConfig.mutualContact && u.mutual_contact && u.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) {
            this.mutualImageView.setVisibility(0);
        } else {
            this.mutualImageView.setVisibility(8);
        }
        this.currrntStatus = status;
        this.currentName = name;
        this.currentObject = object;
        this.currentDrawable = resId;
        update(0);
    }

    public void setNameTypeface(Typeface typeface) {
        this.nameTextView.setTypeface(typeface);
    }

    public void setCurrentId(int id) {
        this.currentId = id;
    }

    public void setChecked(boolean checked, boolean animated) {
        if (this.checkBox != null) {
            if (this.checkBox.getVisibility() != 0) {
                this.checkBox.setVisibility(0);
            }
            this.checkBox.setChecked(checked, animated);
        } else if (this.checkBoxBig != null) {
            if (this.checkBoxBig.getVisibility() != 0) {
                this.checkBoxBig.setVisibility(0);
            }
            this.checkBoxBig.setChecked(checked, animated);
        }
    }

    public void setCheckDisabled(boolean disabled) {
        if (this.checkBoxBig != null) {
            this.checkBoxBig.setDisabled(disabled);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), 1073741824));
    }

    public void setStatusColors(int color, int onlineColor) {
        this.statusColor = color;
        this.statusOnlineColor = onlineColor;
    }

    public void invalidate() {
        super.invalidate();
        if (this.checkBoxBig != null) {
            this.checkBoxBig.invalidate();
        }
    }

    public void update(int mask) {
        TLObject photo = null;
        String newName = null;
        User currentUser = null;
        TLRPC$Chat currentChat = null;
        if (this.currentObject instanceof User) {
            currentUser = this.currentObject;
            if (currentUser.photo != null) {
                photo = currentUser.photo.photo_small;
            }
        } else if (this.currentObject instanceof TLRPC$Chat) {
            currentChat = this.currentObject;
            if (currentChat.photo != null) {
                photo = currentChat.photo.photo_small;
            }
        }
        if (mask != 0) {
            boolean continueUpdate = false;
            if ((mask & 2) != 0 && ((this.lastAvatar != null && photo == null) || !(this.lastAvatar != null || photo == null || this.lastAvatar == null || photo == null || (this.lastAvatar.volume_id == photo.volume_id && this.lastAvatar.local_id == photo.local_id)))) {
                continueUpdate = true;
            }
            if (!(currentUser == null || continueUpdate || (mask & 4) == 0)) {
                int newStatus = 0;
                if (currentUser.status != null) {
                    newStatus = currentUser.status.expires;
                }
                if (newStatus != this.lastStatus) {
                    continueUpdate = true;
                }
            }
            if (!(continueUpdate || this.currentName != null || this.lastName == null || (mask & 1) == 0)) {
                if (currentUser != null) {
                    newName = UserObject.getUserName(currentUser);
                } else {
                    newName = currentChat.title;
                }
                if (!newName.equals(this.lastName)) {
                    continueUpdate = true;
                }
            }
            if (!continueUpdate) {
                return;
            }
        }
        if (currentUser != null) {
            this.avatarDrawable.setInfo(currentUser);
            if (currentUser.status != null) {
                this.lastStatus = currentUser.status.expires;
            } else {
                this.lastStatus = 0;
            }
        } else if (currentChat != null) {
            this.avatarDrawable.setInfo(currentChat);
        } else if (this.currentName != null) {
            this.avatarDrawable.setInfo(this.currentId, this.currentName.toString(), null, false);
        } else {
            this.avatarDrawable.setInfo(this.currentId, "#", null, false);
        }
        if (this.currentName != null) {
            this.lastName = null;
            this.nameTextView.setText(this.currentName);
        } else {
            if (currentUser != null) {
                String userName;
                if (newName == null) {
                    userName = UserObject.getUserName(currentUser);
                } else {
                    userName = newName;
                }
                this.lastName = userName;
            } else {
                this.lastName = newName == null ? currentChat.title : newName;
            }
            this.nameTextView.setText(this.lastName);
        }
        if (this.currrntStatus != null) {
            this.statusTextView.setTextColor(this.statusColor);
            this.statusTextView.setText(this.currrntStatus);
        } else if (currentUser != null) {
            if (currentUser.bot) {
                this.statusTextView.setTextColor(this.statusColor);
                if (currentUser.bot_chat_history || (this.adminImage != null && this.adminImage.getVisibility() == 0)) {
                    this.statusTextView.setText(LocaleController.getString("BotStatusRead", R.string.BotStatusRead));
                } else {
                    this.statusTextView.setText(LocaleController.getString("BotStatusCantRead", R.string.BotStatusCantRead));
                }
            } else if (currentUser.id == UserConfig.getInstance(this.currentAccount).getClientUserId() || ((currentUser.status != null && currentUser.status.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) || MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Integer.valueOf(currentUser.id)))) {
                this.statusTextView.setTextColor(this.statusOnlineColor);
                this.statusTextView.setText(LocaleController.getString("Online", R.string.Online));
            } else {
                this.statusTextView.setTextColor(this.statusColor);
                this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, currentUser));
            }
        }
        if ((this.imageView.getVisibility() == 0 && this.currentDrawable == 0) || (this.imageView.getVisibility() == 8 && this.currentDrawable != 0)) {
            this.imageView.setVisibility(this.currentDrawable == 0 ? 8 : 0);
            this.imageView.setImageResource(this.currentDrawable);
        }
        this.avatarImageView.setImage(photo, "50_50", this.avatarDrawable);
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
        PlaceProviderObject placeProviderObject = null;
        int i = 0;
        if (!(fileLocation == null || this.currentObject == null)) {
            TLRPC$FileLocation photoBig;
            if (this.currentObject instanceof User) {
                User user = this.currentObject;
                index = user.id;
                if (user == null || user.photo == null || user.photo.photo_big == null) {
                    photoBig = null;
                } else {
                    photoBig = user.photo.photo_big;
                }
            } else {
                TLRPC$Chat chat = this.currentObject;
                if (chat == null || chat.photo == null || chat.photo.photo_big == null) {
                    photoBig = null;
                } else {
                    photoBig = chat.photo.photo_big;
                    index = 0;
                }
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
                placeProviderObject.dialogId = index;
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
