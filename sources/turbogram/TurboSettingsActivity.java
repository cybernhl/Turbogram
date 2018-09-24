package turbogram;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.ArrayList;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_photos_photo;
import org.telegram.tgnet.TLRPC$TL_photos_uploadProfilePhoto;
import org.telegram.tgnet.TLRPC$TL_secureFile;
import org.telegram.tgnet.TLRPC$TL_userProfilePhoto;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.BottomSheetCell;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.PhotoViewer;
import turbogram.Cells.TextDescriptionCell;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboConfig$BG;
import turbogram.Utilities.TurboUtils;

public class TurboSettingsActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate {
    private int CategorizeDialogsRow;
    private int actionbarIconsRow;
    private int actionbarShadowRow;
    private int adsBlockerRow;
    private int answeringMachineRow;
    private BackupImageView avatarImage;
    private int avatarInActionRow;
    private int categorizeSavedMessagesRow;
    private int chatBarRow;
    private int chatPreviewDesRow;
    private int chatPreviewRow;
    private int chatScreenSectionRow;
    private int chatScreenSectionRow2;
    private int checkBubbleRow;
    private int checkStyleRow;
    private int confirmatinAudioRow;
    private int confirmatinVideoRow;
    private int contactAvatarRow;
    private int contactScreenSectionRow;
    private int contactScreenSectionRow2;
    private int copySenderNameDesRow;
    private int copySenderNameRow;
    private int deleteAccountRow;
    private int draftRow;
    private int editorTextSizeRow;
    private int emojiAndStickerRow;
    private int emptyRow;
    private int enableCallsRow;
    private int extraHeight;
    private View extraHeightView;
    private int flipRow;
    private int fontRow;
    private int forwardAndReplyRow;
    private int generalSectionRow2;
    private int groupAvatarRow;
    private int hideCameraRow;
    private int hideTurboChannelRow;
    private ImageUpdater imageUpdater = new ImageUpdater();
    private int is24HoursRow;
    private int keepChatDesRow;
    private int keepChatRow;
    private int keepContactsPageDesRow;
    private int keepContactsPageRow;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int mainScreenSectionRow;
    private int mainScreenSectionRow2;
    private int menuSettingsRow;
    private TextView nameTextView;
    private TextView onlineTextView;
    private int overscrollRow;
    private int persianDateRow;
    private int privacyRow;
    private int profileScreenSectionRow;
    private int profileScreenSectionRow2;
    private int rowCount;
    private int saveSectionRow;
    private int saveSectionRow2;
    private int separateMediaRow;
    private View shadowView;
    private int sharedMediaRow;
    private int showChatUserStatusDesRow;
    private int showChatUserStatusRow;
    private int showContactsAvatarRow;
    private int showExactCountRow;
    private int showMutualDesRow;
    private int showMutualRow;
    private int showUserStatusDesRow;
    private int showUserStatusRow;
    private int storageRow;
    private int tabletModeDesRow;
    private int tabletModeRow;
    private int tabsSettingsRow;
    private int tagRow;
    private int textNicerRow;
    private int toastRow;
    private int toolBarRow;
    private int userAvatarRow;
    private int versionRow;
    private int videoPlayerRow;
    private int voiceChangerRow;

    /* renamed from: turbogram.TurboSettingsActivity$1 */
    class C24791 implements ImageUpdaterDelegate {

        /* renamed from: turbogram.TurboSettingsActivity$1$1 */
        class C24781 implements RequestDelegate {

            /* renamed from: turbogram.TurboSettingsActivity$1$1$1 */
            class C24771 implements Runnable {
                C24771() {
                }

                public void run() {
                    NotificationCenter.getInstance(TurboSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, Integer.valueOf(MessagesController.UPDATE_MASK_ALL));
                    NotificationCenter.getInstance(TurboSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
                    UserConfig.getInstance(TurboSettingsActivity.this.currentAccount).saveConfig(true);
                }
            }

            C24781() {
            }

            public void run(TLObject response, TLRPC$TL_error error) {
                if (error == null) {
                    User user = MessagesController.getInstance(TurboSettingsActivity.this.currentAccount).getUser(Integer.valueOf(UserConfig.getInstance(TurboSettingsActivity.this.currentAccount).getClientUserId()));
                    if (user == null) {
                        user = UserConfig.getInstance(TurboSettingsActivity.this.currentAccount).getCurrentUser();
                        if (user != null) {
                            MessagesController.getInstance(TurboSettingsActivity.this.currentAccount).putUser(user, false);
                        } else {
                            return;
                        }
                    }
                    UserConfig.getInstance(TurboSettingsActivity.this.currentAccount).setCurrentUser(user);
                    TLRPC$TL_photos_photo photo = (TLRPC$TL_photos_photo) response;
                    ArrayList<TLRPC$PhotoSize> sizes = photo.photo.sizes;
                    TLRPC$PhotoSize smallSize = FileLoader.getClosestPhotoSizeWithSize(sizes, 100);
                    TLRPC$PhotoSize bigSize = FileLoader.getClosestPhotoSizeWithSize(sizes, 1000);
                    user.photo = new TLRPC$TL_userProfilePhoto();
                    user.photo.photo_id = photo.photo.id;
                    if (smallSize != null) {
                        user.photo.photo_small = smallSize.location;
                    }
                    if (bigSize != null) {
                        user.photo.photo_big = bigSize.location;
                    } else if (smallSize != null) {
                        user.photo.photo_small = smallSize.location;
                    }
                    MessagesStorage.getInstance(TurboSettingsActivity.this.currentAccount).clearUserPhotos(user.id);
                    ArrayList<User> users = new ArrayList();
                    users.add(user);
                    MessagesStorage.getInstance(TurboSettingsActivity.this.currentAccount).putUsersAndChats(users, null, false, true);
                    AndroidUtilities.runOnUIThread(new C24771());
                }
            }
        }

        C24791() {
        }

        public void didUploadedPhoto(TLRPC$InputFile file, TLRPC$PhotoSize small, TLRPC$PhotoSize big, TLRPC$TL_secureFile secureFile) {
            TLRPC$TL_photos_uploadProfilePhoto req = new TLRPC$TL_photos_uploadProfilePhoto();
            req.file = file;
            ConnectionsManager.getInstance(TurboSettingsActivity.this.currentAccount).sendRequest(req, new C24781());
        }
    }

    /* renamed from: turbogram.TurboSettingsActivity$2 */
    class C24802 extends ActionBarMenuOnItemClick {
        C24802() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                TurboSettingsActivity.this.finishFragment();
            }
        }
    }

    /* renamed from: turbogram.TurboSettingsActivity$4 */
    class C24914 implements OnItemClickListener {
        C24914() {
        }

        public void onItemClick(View view, final int position) {
            if (position == TurboSettingsActivity.this.tabletModeRow) {
                TurboConfig.setBooleanValue("tablet_mode", !TurboConfig.tabletMode);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.tabletMode);
                }
                TurboUtils.resetApp();
            } else if (position == TurboSettingsActivity.this.fontRow) {
                TurboSettingsActivity.this.presentFragment(new FontSelectActivity());
            } else if (position == TurboSettingsActivity.this.toastRow) {
                TurboSettingsActivity.this.presentFragment(new ToastSettingsActivity());
            } else if (position == TurboSettingsActivity.this.privacyRow) {
                TurboSettingsActivity.this.presentFragment(new TPrivacySettingsActivity());
            } else if (position == TurboSettingsActivity.this.storageRow) {
                TurboSettingsActivity.this.presentFragment(new StorageSettingsActivity());
            } else if (position == TurboSettingsActivity.this.tabsSettingsRow) {
                TurboSettingsActivity.this.presentFragment(new TabsSettingsActivity());
            } else if (position == TurboSettingsActivity.this.avatarInActionRow) {
                TurboConfig.setBooleanValue("avatar_in_action", !TurboConfig.avatarInAction);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.avatarInAction);
                }
                TurboSettingsActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
            } else if (position == TurboSettingsActivity.this.menuSettingsRow) {
                TurboSettingsActivity.this.presentFragment(new SortMenuActivity());
            } else if (position == TurboSettingsActivity.this.CategorizeDialogsRow) {
                TurboSettingsActivity.this.presentFragment(new DialogCategoryActivity());
            } else if (position == TurboSettingsActivity.this.emojiAndStickerRow) {
                TurboSettingsActivity.this.presentFragment(new EmojiSettingsActivity());
            } else if (position == TurboSettingsActivity.this.persianDateRow) {
                TurboConfig.setBooleanValue("persian_date", !TurboConfig.persianDate);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.persianDate);
                }
            } else if (position == TurboSettingsActivity.this.is24HoursRow) {
                TurboConfig.setBooleanValue("enable24HourFormat", !TurboConfig.is24Hours);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.is24Hours);
                }
            } else if (position == TurboSettingsActivity.this.flipRow) {
                TurboConfig.setBooleanValue("turbo_flip", !TurboConfig.flipDirection);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.flipDirection);
                }
                TurboUtils.resetApp();
            } else if (position == TurboSettingsActivity.this.adsBlockerRow) {
                TurboConfig.setBooleanValue("ads_blocker", !TurboConfig.adsBlocker);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.adsBlocker);
                }
            } else if (position == TurboSettingsActivity.this.enableCallsRow) {
                TurboConfig.setBooleanValue("calls_enabled", !TurboConfig.callsEnabled);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.callsEnabled);
                }
                NotificationCenter.getInstance(TurboSettingsActivity.this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            } else if (position == TurboSettingsActivity.this.hideTurboChannelRow) {
                TurboConfig.setBooleanValue("hide_tchannel", !TurboConfig.hideTurboChannel);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.hideTurboChannel);
                }
            } else if (position == TurboSettingsActivity.this.actionbarShadowRow) {
                TurboConfig.setBooleanValue("action_shadow", !TurboConfig.showActionbarShadow);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.showActionbarShadow);
                }
                TurboSettingsActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
            } else if (position == TurboSettingsActivity.this.actionbarIconsRow) {
                if (TurboSettingsActivity.this.getParentActivity() != null) {
                    maskValues = new boolean[1];
                    builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                    builder.setApplyTopPadding(false);
                    builder.setApplyBottomPadding(false);
                    linearLayout = new LinearLayout(TurboSettingsActivity.this.getParentActivity());
                    linearLayout.setOrientation(1);
                    for (a = 0; a < 1; a++) {
                        name = null;
                        if (a == 0) {
                            name = LocaleController.getString("ShowCategorizeIcon", R.string.ShowCategorizeIcon);
                            maskValues[a] = TurboConfig.showCategorizeIcon;
                        }
                        checkBoxCell = new CheckBoxCell(TurboSettingsActivity.this.getParentActivity(), 1);
                        checkBoxCell.setTag(Integer.valueOf(a));
                        checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                        linearLayout.addView(checkBoxCell, LayoutHelper.createLinear(-1, 48));
                        checkBoxCell.setText(name, "", maskValues[a], true);
                        checkBoxCell.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                CheckBoxCell cell = (CheckBoxCell) v;
                                int num = ((Integer) cell.getTag()).intValue();
                                maskValues[num] = !maskValues[num];
                                cell.setChecked(maskValues[num], true);
                            }
                        });
                    }
                    cell = new BottomSheetCell(TurboSettingsActivity.this.getParentActivity(), 1);
                    cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    cell.setTextAndIcon(LocaleController.getString("Save", R.string.Save).toUpperCase(), 0);
                    cell.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
                    cell.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            try {
                                if (TurboSettingsActivity.this.visibleDialog != null) {
                                    TurboSettingsActivity.this.visibleDialog.dismiss();
                                }
                            } catch (Exception e) {
                                FileLog.e("tmessages", e);
                            }
                            for (int a = 0; a < 1; a++) {
                                if (a == 0) {
                                    TurboConfig.setBooleanValue("category_icon", maskValues[a]);
                                }
                            }
                            if (TurboSettingsActivity.this.listAdapter != null) {
                                TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                            TurboSettingsActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
                        }
                    });
                    linearLayout.addView(cell, LayoutHelper.createLinear(-1, 48));
                    builder.setCustomView(linearLayout);
                    TurboSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == TurboSettingsActivity.this.showExactCountRow) {
                TurboConfig.setBooleanValue("exact_count", !TurboConfig.exactCount);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.exactCount);
                }
            } else if (position == TurboSettingsActivity.this.chatBarRow) {
                TurboSettingsActivity.this.presentFragment(new ChatbarSettingsActivity());
            } else if (position == TurboSettingsActivity.this.categorizeSavedMessagesRow) {
                TurboConfig.setBooleanValue("cp_enable", !TurboConfig.categorizeProfile);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.categorizeProfile);
                }
            } else if (position == TurboSettingsActivity.this.forwardAndReplyRow) {
                TurboSettingsActivity.this.presentFragment(new ForwardSettingsActivity());
            } else if (position == TurboSettingsActivity.this.copySenderNameRow) {
                TurboConfig.setBooleanValue("copy_sender", !TurboConfig.copySender);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.copySender);
                }
            } else if (position == TurboSettingsActivity.this.confirmatinAudioRow) {
                TurboConfig.setBooleanValue("confirmatin_audio", !TurboConfig.confirmatinAudio);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.confirmatinAudio);
                }
            } else if (position == TurboSettingsActivity.this.confirmatinVideoRow) {
                TurboConfig.setBooleanValue("confirmatin_video", !TurboConfig.confirmatinVideo);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.confirmatinVideo);
                }
            } else if (position == TurboSettingsActivity.this.voiceChangerRow) {
                TurboSettingsActivity.this.presentFragment(new VoiceSettingsActivity());
            } else if (position == TurboSettingsActivity.this.textNicerRow) {
                TurboSettingsActivity.this.presentFragment(new TextNicerActivity());
            } else if (position == TurboSettingsActivity.this.showMutualRow) {
                TurboConfig.setBooleanValue("mutual_contact", !TurboConfig.mutualContact);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.mutualContact);
                }
            } else if (position == TurboSettingsActivity.this.showUserStatusRow) {
                TurboConfig.setBooleanValue("contact_status", !TurboConfig.contactStatus);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.contactStatus);
                }
            } else if (position == TurboSettingsActivity.this.showChatUserStatusRow) {
                TurboConfig.setBooleanValue("chat_contact_status", !TurboConfig.chatContactStatus);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.chatContactStatus);
                }
            } else if (position == TurboSettingsActivity.this.toolBarRow) {
                TurboSettingsActivity.this.presentFragment(new ToolbarSettingsActivity());
            } else if (position == TurboSettingsActivity.this.chatPreviewRow) {
                TurboConfig.setBooleanValue("chat_preview", !TurboConfig.chatPreview);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.chatPreview);
                }
            } else if (position == TurboSettingsActivity.this.keepChatRow) {
                TurboConfig.setBooleanValue("keep_chat_open", !TurboConfig.keepChatOpen);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.keepChatOpen);
                }
            } else if (position == TurboSettingsActivity.this.keepContactsPageRow) {
                TurboConfig.setBooleanValue("keep_contacts", !TurboConfig.keepContactsOpen);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.keepContactsOpen);
                }
            } else if (position == TurboSettingsActivity.this.hideCameraRow) {
                TurboConfig.setBooleanValue("hide_camera", !TurboConfig.hideCameraInAttach);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.hideCameraInAttach);
                }
            } else if (position == TurboSettingsActivity.this.editorTextSizeRow) {
                if (TurboSettingsActivity.this.getParentActivity() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurboSettingsActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("TextSize", R.string.TextSize));
                    final NumberPicker numberPicker = new NumberPicker(TurboSettingsActivity.this.getParentActivity());
                    numberPicker.setMinValue(12);
                    numberPicker.setMaxValue(30);
                    numberPicker.setValue(TurboConfig.editorFontSize);
                    builder.setView(numberPicker);
                    builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurboConfig.setIntValue("editor_font_size", numberPicker.getValue());
                            if (TurboSettingsActivity.this.listAdapter != null) {
                                TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                        }
                    });
                    TurboSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == TurboSettingsActivity.this.answeringMachineRow) {
                TurboSettingsActivity.this.presentFragment(new AMSettingsActivity());
            } else if (position == TurboSettingsActivity.this.videoPlayerRow) {
                TurboConfig.setBooleanValue("inapp_player", !TurboConfig.inappVideoPlayer);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.inappVideoPlayer);
                }
            } else if (position == TurboSettingsActivity.this.contactAvatarRow) {
                builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("TouchContactAvatar", R.string.TouchContactAvatar));
                builder.setItems(new CharSequence[]{LocaleController.getString("Disabled", R.string.Disabled), LocaleController.getString("OpenProfile", R.string.OpenProfile), LocaleController.getString("OpenProfilePhotos", R.string.OpenProfilePhotos)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig.setIntValue("contact_avatar_touch", which + 1);
                        if (TurboSettingsActivity.this.listAdapter != null) {
                            TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                TurboSettingsActivity.this.showDialog(builder.create());
            } else if (position == TurboSettingsActivity.this.groupAvatarRow) {
                builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("TouchGroupAvatar", R.string.TouchGroupAvatar));
                builder.setItems(new CharSequence[]{LocaleController.getString("Disabled", R.string.Disabled), LocaleController.getString("OpenProfile", R.string.OpenProfile), LocaleController.getString("OpenProfilePhotos", R.string.OpenProfilePhotos)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig.setIntValue("group_avatar_touch", which + 1);
                        if (TurboSettingsActivity.this.listAdapter != null) {
                            TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                TurboSettingsActivity.this.showDialog(builder.create());
            } else if (position == TurboSettingsActivity.this.userAvatarRow) {
                builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                builder.setTitle(LocaleController.getString("TouchContactAvatar", R.string.TouchContactAvatar));
                builder.setItems(new CharSequence[]{LocaleController.getString("Default", R.string.Default), LocaleController.getString("OpenProfilePhotos", R.string.OpenProfilePhotos)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TurboConfig.setIntValue("member_avatar_touch", which + 1);
                        if (TurboSettingsActivity.this.listAdapter != null) {
                            TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                TurboSettingsActivity.this.showDialog(builder.create());
            } else if (position == TurboSettingsActivity.this.sharedMediaRow) {
                if (TurboSettingsActivity.this.getParentActivity() != null) {
                    maskValues = new boolean[6];
                    builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                    builder.setApplyTopPadding(false);
                    builder.setApplyBottomPadding(false);
                    linearLayout = new LinearLayout(TurboSettingsActivity.this.getParentActivity());
                    linearLayout.setOrientation(1);
                    for (a = 0; a < 6; a++) {
                        name = null;
                        if (a == 0) {
                            name = LocaleController.getString("SharedPhotoTitle", R.string.SharedPhotoTitle);
                            maskValues[a] = TurboConfig.sharedPhotoTitle;
                        } else if (a == 1) {
                            name = LocaleController.getString("SharedVideoTitle", R.string.SharedVideoTitle);
                            maskValues[a] = TurboConfig.sharedVideoTitle;
                        } else if (a == 2) {
                            name = LocaleController.getString("SharedFileTitle", R.string.SharedFileTitle);
                            maskValues[a] = TurboConfig.sharedFileTitle;
                        } else if (a == 3) {
                            name = LocaleController.getString("SharedLinkTitle", R.string.SharedLinkTitle);
                            maskValues[a] = TurboConfig.sharedLinkTitle;
                        } else if (a == 4) {
                            name = LocaleController.getString("SharedMusicTitle", R.string.SharedMusicTitle);
                            maskValues[a] = TurboConfig.sharedMusicTitle;
                        } else if (a == 5) {
                            name = LocaleController.getString("SharedVoiceTitle", R.string.SharedVoiceTitle);
                            maskValues[a] = TurboConfig.sharedVoiceTitle;
                        }
                        checkBoxCell = new CheckBoxCell(TurboSettingsActivity.this.getParentActivity(), 1);
                        checkBoxCell.setTag(Integer.valueOf(a));
                        checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                        linearLayout.addView(checkBoxCell, LayoutHelper.createLinear(-1, 48));
                        checkBoxCell.setText(name, "", maskValues[a], true);
                        checkBoxCell.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                CheckBoxCell cell = (CheckBoxCell) v;
                                int num = ((Integer) cell.getTag()).intValue();
                                maskValues[num] = !maskValues[num];
                                cell.setChecked(maskValues[num], true);
                            }
                        });
                    }
                    cell = new BottomSheetCell(TurboSettingsActivity.this.getParentActivity(), 1);
                    cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    cell.setTextAndIcon(LocaleController.getString("Save", R.string.Save).toUpperCase(), 0);
                    cell.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
                    cell.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            try {
                                if (TurboSettingsActivity.this.visibleDialog != null) {
                                    TurboSettingsActivity.this.visibleDialog.dismiss();
                                }
                            } catch (Exception e) {
                                FileLog.e("tmessages", e);
                            }
                            for (int a = 0; a < 6; a++) {
                                if (a == 0) {
                                    TurboConfig.setBooleanValue("shared_photo_title", maskValues[a]);
                                } else if (a == 1) {
                                    TurboConfig.setBooleanValue("shared_video_title", maskValues[a]);
                                } else if (a == 2) {
                                    TurboConfig.setBooleanValue("shared_file_title", maskValues[a]);
                                } else if (a == 3) {
                                    TurboConfig.setBooleanValue("shared_link_title", maskValues[a]);
                                } else if (a == 4) {
                                    TurboConfig.setBooleanValue("shared_music_title", maskValues[a]);
                                } else if (a == 5) {
                                    TurboConfig.setBooleanValue("shared_voice_title", maskValues[a]);
                                }
                            }
                            if (TurboSettingsActivity.this.noneSelected()) {
                                TurboConfig.setBooleanValue("separate_media", false);
                            }
                            if (TurboSettingsActivity.this.listAdapter != null) {
                                TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                            }
                        }
                    });
                    linearLayout.addView(cell, LayoutHelper.createLinear(-1, 48));
                    builder.setCustomView(linearLayout);
                    TurboSettingsActivity.this.showDialog(builder.create());
                }
            } else if (position == TurboSettingsActivity.this.separateMediaRow) {
                TurboConfig.setBooleanValue("separate_media", !TurboConfig.separateMedia);
                if (view instanceof TextCheckCell) {
                    ((TextCheckCell) view).setChecked(TurboConfig.separateMedia);
                }
            } else if (position == TurboSettingsActivity.this.showContactsAvatarRow) {
                maskValues = new boolean[6];
                builder = new Builder(TurboSettingsActivity.this.getParentActivity());
                builder.setApplyTopPadding(false);
                builder.setApplyBottomPadding(false);
                linearLayout = new LinearLayout(TurboSettingsActivity.this.getParentActivity());
                linearLayout.setOrientation(1);
                for (a = 0; a < 3; a++) {
                    name = null;
                    if (a == 0) {
                        name = LocaleController.getString("ShowContactAvatarChat", R.string.ShowContactAvatarChat);
                        maskValues[a] = TurboConfig$BG.showAvatar;
                    } else if (a == 1) {
                        name = LocaleController.getString("ShowOwnAvatarChat", R.string.ShowOwnAvatarChat);
                        maskValues[a] = TurboConfig$BG.showMyAvatar;
                    } else if (a == 2) {
                        name = LocaleController.getString("ShowOwnAvatarGroup", R.string.ShowOwnAvatarGroup);
                        maskValues[a] = TurboConfig$BG.showMyAvatarGroup;
                    }
                    checkBoxCell = new CheckBoxCell(TurboSettingsActivity.this.getParentActivity(), 1);
                    checkBoxCell.setTag(Integer.valueOf(a));
                    checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                    linearLayout.addView(checkBoxCell, LayoutHelper.createLinear(-1, 48));
                    checkBoxCell.setText(name, "", maskValues[a], true);
                    checkBoxCell.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            CheckBoxCell cell = (CheckBoxCell) v;
                            int num = ((Integer) cell.getTag()).intValue();
                            maskValues[num] = !maskValues[num];
                            cell.setChecked(maskValues[num], true);
                        }
                    });
                }
                cell = new BottomSheetCell(TurboSettingsActivity.this.getParentActivity(), 1);
                cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                cell.setTextAndIcon(LocaleController.getString("Save", R.string.Save).toUpperCase(), 0);
                cell.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
                cell.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        try {
                            if (TurboSettingsActivity.this.visibleDialog != null) {
                                TurboSettingsActivity.this.visibleDialog.dismiss();
                            }
                        } catch (Exception e) {
                            FileLog.e("tmessages", e);
                        }
                        for (int a = 0; a < 4; a++) {
                            if (a == 0) {
                                TurboConfig$BG.setBooleanValue("cavatar_in_chat", maskValues[a]);
                            } else if (a == 1) {
                                TurboConfig$BG.setBooleanValue("oavatar_in_chat", maskValues[a]);
                            } else if (a == 2) {
                                TurboConfig$BG.setBooleanValue("oavatar_in_group", maskValues[a]);
                            }
                        }
                        if (TurboSettingsActivity.this.listAdapter != null) {
                            TurboSettingsActivity.this.listAdapter.notifyItemChanged(position);
                        }
                    }
                });
                linearLayout.addView(cell, LayoutHelper.createLinear(-1, 48));
                builder.setCustomView(linearLayout);
                TurboSettingsActivity.this.showDialog(builder.create());
            } else if (position == TurboSettingsActivity.this.checkBubbleRow) {
                args = new Bundle();
                args.putInt(Param.TYPE, 0);
                TurboSettingsActivity.this.presentFragment(new TickStyleActivity(args));
            } else if (position == TurboSettingsActivity.this.checkStyleRow) {
                args = new Bundle();
                args.putInt(Param.TYPE, 1);
                TurboSettingsActivity.this.presentFragment(new TickStyleActivity(args));
            } else if (position == TurboSettingsActivity.this.draftRow) {
                TurboSettingsActivity.this.presentFragment(new DraftsActivity());
            } else if (position == TurboSettingsActivity.this.tagRow) {
                TurboSettingsActivity.this.presentFragment(new TagLinksActivity());
            } else if (position == TurboSettingsActivity.this.deleteAccountRow) {
                TurboSettingsActivity.this.presentFragment(new DeleteAccountActivity(), true);
            }
        }
    }

    /* renamed from: turbogram.TurboSettingsActivity$5 */
    class C24925 extends OnScrollListener {
        C24925() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int i = 0;
            if (TurboSettingsActivity.this.layoutManager.getItemCount() != 0) {
                int height = 0;
                View child = recyclerView.getChildAt(0);
                if (child != null) {
                    if (TurboSettingsActivity.this.layoutManager.findFirstVisibleItemPosition() == 0) {
                        int dp = AndroidUtilities.dp(88.0f);
                        if (child.getTop() < 0) {
                            i = child.getTop();
                        }
                        height = dp + i;
                    }
                    if (TurboSettingsActivity.this.extraHeight != height) {
                        TurboSettingsActivity.this.extraHeight = height;
                        TurboSettingsActivity.this.needLayout();
                    }
                }
            }
        }
    }

    /* renamed from: turbogram.TurboSettingsActivity$6 */
    class C24936 implements OnPreDrawListener {
        C24936() {
        }

        public boolean onPreDraw() {
            if (TurboSettingsActivity.this.fragmentView != null) {
                TurboSettingsActivity.this.needLayout();
                TurboSettingsActivity.this.fragmentView.getViewTreeObserver().removeOnPreDrawListener(this);
            }
            return true;
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            int position = holder.getAdapterPosition();
            if (position == TurboSettingsActivity.this.tabletModeRow || position == TurboSettingsActivity.this.fontRow || position == TurboSettingsActivity.this.persianDateRow || position == TurboSettingsActivity.this.is24HoursRow || position == TurboSettingsActivity.this.flipRow || position == TurboSettingsActivity.this.showContactsAvatarRow || position == TurboSettingsActivity.this.enableCallsRow || position == TurboSettingsActivity.this.answeringMachineRow || position == TurboSettingsActivity.this.adsBlockerRow || position == TurboSettingsActivity.this.hideTurboChannelRow || position == TurboSettingsActivity.this.tabsSettingsRow || position == TurboSettingsActivity.this.actionbarShadowRow || position == TurboSettingsActivity.this.menuSettingsRow || position == TurboSettingsActivity.this.actionbarIconsRow || position == TurboSettingsActivity.this.contactAvatarRow || position == TurboSettingsActivity.this.groupAvatarRow || position == TurboSettingsActivity.this.toolBarRow || position == TurboSettingsActivity.this.CategorizeDialogsRow || position == TurboSettingsActivity.this.avatarInActionRow || position == TurboSettingsActivity.this.showExactCountRow || position == TurboSettingsActivity.this.showUserStatusRow || position == TurboSettingsActivity.this.showUserStatusDesRow || position == TurboSettingsActivity.this.showChatUserStatusRow || position == TurboSettingsActivity.this.showChatUserStatusDesRow || position == TurboSettingsActivity.this.checkStyleRow || position == TurboSettingsActivity.this.checkBubbleRow || position == TurboSettingsActivity.this.chatBarRow || position == TurboSettingsActivity.this.copySenderNameRow || position == TurboSettingsActivity.this.hideCameraRow || position == TurboSettingsActivity.this.keepChatRow || position == TurboSettingsActivity.this.editorTextSizeRow || position == TurboSettingsActivity.this.deleteAccountRow || position == TurboSettingsActivity.this.videoPlayerRow || position == TurboSettingsActivity.this.voiceChangerRow || position == TurboSettingsActivity.this.textNicerRow || position == TurboSettingsActivity.this.confirmatinAudioRow || position == TurboSettingsActivity.this.emojiAndStickerRow || position == TurboSettingsActivity.this.storageRow || position == TurboSettingsActivity.this.chatPreviewRow || position == TurboSettingsActivity.this.sharedMediaRow || position == TurboSettingsActivity.this.showMutualRow || position == TurboSettingsActivity.this.keepContactsPageRow || position == TurboSettingsActivity.this.userAvatarRow || position == TurboSettingsActivity.this.privacyRow || position == TurboSettingsActivity.this.toastRow || position == TurboSettingsActivity.this.confirmatinVideoRow || position == TurboSettingsActivity.this.forwardAndReplyRow || position == TurboSettingsActivity.this.versionRow || position == TurboSettingsActivity.this.separateMediaRow || position == TurboSettingsActivity.this.draftRow || position == TurboSettingsActivity.this.tagRow || position == TurboSettingsActivity.this.categorizeSavedMessagesRow) {
                return true;
            }
            return false;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new EmptyCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 1:
                    view = new ShadowSectionCell(this.mContext);
                    break;
                case 2:
                    view = new TextSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 3:
                    view = new TextCheckCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 4:
                    view = new HeaderCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 5:
                    view = new TextInfoCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    try {
                        PackageInfo pInfo = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);
                        ((TextInfoCell) view).setText(String.format(Locale.US, "Turbogram for Android v%s (%d)", new Object[]{pInfo.versionName, Integer.valueOf(pInfo.versionCode)}));
                        break;
                    } catch (Exception e) {
                        FileLog.e("tmessages", e);
                        break;
                    }
                case 6:
                    view = new TextDetailSettingsCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 7:
                    view = new TextDescriptionCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
            }
            view.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(view);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onBindViewHolder(org.telegram.messenger.support.widget.RecyclerView.ViewHolder r18, int r19) {
            /*
            r17 = this;
            r12 = r18.getItemViewType();
            switch(r12) {
                case 0: goto L_0x0008;
                case 1: goto L_0x0007;
                case 2: goto L_0x0034;
                case 3: goto L_0x0428;
                case 4: goto L_0x06e0;
                case 5: goto L_0x0007;
                case 6: goto L_0x07a6;
                case 7: goto L_0x0816;
                default: goto L_0x0007;
            };
        L_0x0007:
            return;
        L_0x0008:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.overscrollRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0024;
        L_0x0014:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.EmptyCell) r12;
            r13 = 1118830592; // 0x42b00000 float:88.0 double:5.52775759E-315;
            r13 = org.telegram.messenger.AndroidUtilities.dp(r13);
            r12.setHeight(r13);
            goto L_0x0007;
        L_0x0024:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.EmptyCell) r12;
            r13 = 1098907648; // 0x41800000 float:16.0 double:5.42932517E-315;
            r13 = org.telegram.messenger.AndroidUtilities.dp(r13);
            r12.setHeight(r13);
            goto L_0x0007;
        L_0x0034:
            r0 = r18;
            r8 = r0.itemView;
            r8 = (org.telegram.ui.Cells.TextSettingsCell) r8;
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.fontRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0133;
        L_0x0046:
            r12 = turbogram.Utilities.TurboConfig.fontType;	 Catch:{ Exception -> 0x0126 }
            switch(r12) {
                case 0: goto L_0x0064;
                case 1: goto L_0x006f;
                case 2: goto L_0x007a;
                case 3: goto L_0x0085;
                case 4: goto L_0x0090;
                case 5: goto L_0x009b;
                case 6: goto L_0x00a6;
                case 7: goto L_0x00b1;
                case 8: goto L_0x00bc;
                case 9: goto L_0x00c7;
                case 10: goto L_0x00d2;
                case 11: goto L_0x00de;
                case 12: goto L_0x00ea;
                case 13: goto L_0x00f6;
                case 14: goto L_0x0102;
                case 15: goto L_0x010e;
                case 16: goto L_0x011a;
                default: goto L_0x004b;
            };	 Catch:{ Exception -> 0x0126 }
        L_0x004b:
            r12 = "IranSansLight";
            r13 = 2131493952; // 0x7f0c0440 float:1.8611399E38 double:1.053097936E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
        L_0x0055:
            r12 = "FontType";
            r13 = 2131493762; // 0x7f0c0382 float:1.8611013E38 double:1.053097842E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setTextAndValue(r12, r4, r13);
            goto L_0x0007;
        L_0x0064:
            r12 = "SystemFont";
            r13 = 2131495070; // 0x7f0c089e float:1.8613666E38 double:1.0530984884E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x006f:
            r12 = "TelegramFont";
            r13 = 2131495099; // 0x7f0c08bb float:1.8613725E38 double:1.0530985027E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x007a:
            r12 = "IranSansUltraLight";
            r13 = 2131493955; // 0x7f0c0443 float:1.8611405E38 double:1.0530979375E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x0085:
            r12 = "IranSansLight";
            r13 = 2131493952; // 0x7f0c0440 float:1.8611399E38 double:1.053097936E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x0090:
            r12 = "IranSansNormal";
            r13 = 2131493954; // 0x7f0c0442 float:1.8611403E38 double:1.053097937E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x009b:
            r12 = "IranSansMedium";
            r13 = 2131493953; // 0x7f0c0441 float:1.86114E38 double:1.0530979365E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00a6:
            r12 = "IranSansBold";
            r13 = 2131493951; // 0x7f0c043f float:1.8611397E38 double:1.0530979355E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00b1:
            r12 = "Afsaneh";
            r13 = 2131492977; // 0x7f0c0071 float:1.8609421E38 double:1.0530974543E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00bc:
            r12 = "Dastnevis";
            r13 = 2131493449; // 0x7f0c0249 float:1.8610378E38 double:1.0530976875E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00c7:
            r12 = "Homa";
            r13 = 2131493898; // 0x7f0c040a float:1.861129E38 double:1.0530979093E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00d2:
            r12 = "Morvarid";
            r13 = 2131494142; // 0x7f0c04fe float:1.8611784E38 double:1.05309803E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00de:
            r12 = "Titr";
            r13 = 2131495158; // 0x7f0c08f6 float:1.8613845E38 double:1.053098532E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00ea:
            r12 = "Kodak";
            r13 = 2131493987; // 0x7f0c0463 float:1.861147E38 double:1.0530979533E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x00f6:
            r12 = "Telvision";
            r13 = 2131495106; // 0x7f0c08c2 float:1.861374E38 double:1.053098506E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x0102:
            r12 = "IranYekanLight";
            r13 = 2131493957; // 0x7f0c0445 float:1.8611409E38 double:1.0530979385E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x010e:
            r12 = "IranYekanNormal";
            r13 = 2131493958; // 0x7f0c0446 float:1.861141E38 double:1.053097939E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x011a:
            r12 = "IranYekanBold";
            r13 = 2131493956; // 0x7f0c0444 float:1.8611407E38 double:1.053097938E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);	 Catch:{ Exception -> 0x0126 }
            goto L_0x0055;
        L_0x0126:
            r3 = move-exception;
            r12 = "IranSansLight";
            r13 = 2131493952; // 0x7f0c0440 float:1.8611399E38 double:1.053097936E-314;
            r4 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x0055;
        L_0x0133:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.contactAvatarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x017b;
        L_0x013f:
            r2 = turbogram.Utilities.TurboConfig.contactAvatarTouch;
            r6 = 0;
            r12 = 1;
            if (r2 != r12) goto L_0x015f;
        L_0x0145:
            r12 = "Disabled";
            r13 = 2131493534; // 0x7f0c029e float:1.861055E38 double:1.0530977295E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
        L_0x014f:
            r12 = "TouchContactAvatar";
            r13 = 2131495184; // 0x7f0c0910 float:1.8613897E38 double:1.0530985447E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setTextAndValue(r12, r6, r13);
            goto L_0x0007;
        L_0x015f:
            r12 = 2;
            if (r2 != r12) goto L_0x016d;
        L_0x0162:
            r12 = "OpenProfile";
            r13 = 2131494361; // 0x7f0c05d9 float:1.8612228E38 double:1.053098138E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x014f;
        L_0x016d:
            r12 = 3;
            if (r2 != r12) goto L_0x014f;
        L_0x0170:
            r12 = "OpenProfilePhotos";
            r13 = 2131494362; // 0x7f0c05da float:1.861223E38 double:1.0530981386E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x014f;
        L_0x017b:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.groupAvatarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x01c3;
        L_0x0187:
            r5 = turbogram.Utilities.TurboConfig.groupAvatarTouch;
            r6 = 0;
            r12 = 1;
            if (r5 != r12) goto L_0x01a7;
        L_0x018d:
            r12 = "Disabled";
            r13 = 2131493534; // 0x7f0c029e float:1.861055E38 double:1.0530977295E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
        L_0x0197:
            r12 = "TouchGroupAvatar";
            r13 = 2131495185; // 0x7f0c0911 float:1.86139E38 double:1.053098545E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setTextAndValue(r12, r6, r13);
            goto L_0x0007;
        L_0x01a7:
            r12 = 2;
            if (r5 != r12) goto L_0x01b5;
        L_0x01aa:
            r12 = "OpenProfile";
            r13 = 2131494361; // 0x7f0c05d9 float:1.8612228E38 double:1.053098138E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x0197;
        L_0x01b5:
            r12 = 3;
            if (r5 != r12) goto L_0x0197;
        L_0x01b8:
            r12 = "OpenProfilePhotos";
            r13 = 2131494362; // 0x7f0c05da float:1.861223E38 double:1.0530981386E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x0197;
        L_0x01c3:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.userAvatarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x01fd;
        L_0x01cf:
            r10 = turbogram.Utilities.TurboConfig.userAvatarTouch;
            r6 = 0;
            r12 = 1;
            if (r10 != r12) goto L_0x01ef;
        L_0x01d5:
            r12 = "Default";
            r13 = 2131493480; // 0x7f0c0268 float:1.8610441E38 double:1.053097703E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
        L_0x01df:
            r12 = "TouchContactAvatar";
            r13 = 2131495184; // 0x7f0c0910 float:1.8613897E38 double:1.0530985447E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 0;
            r8.setTextAndValue(r12, r6, r13);
            goto L_0x0007;
        L_0x01ef:
            r12 = 2;
            if (r10 != r12) goto L_0x01df;
        L_0x01f2:
            r12 = "OpenProfilePhotos";
            r13 = 2131494362; // 0x7f0c05da float:1.861223E38 double:1.0530981386E-314;
            r6 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x01df;
        L_0x01fd:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.menuSettingsRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0219;
        L_0x0209:
            r12 = "SideMenu";
            r13 = 2131494978; // 0x7f0c0842 float:1.861348E38 double:1.053098443E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0219:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.tabsSettingsRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0235;
        L_0x0225:
            r12 = "TurboTabs";
            r13 = 2131495273; // 0x7f0c0969 float:1.8614078E38 double:1.0530985887E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0235:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.CategorizeDialogsRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0251;
        L_0x0241:
            r12 = "CategorizeDialogs";
            r13 = 2131493209; // 0x7f0c0159 float:1.8609892E38 double:1.053097569E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0251:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.toolBarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x026d;
        L_0x025d:
            r12 = "ToolBar";
            r13 = 2131495173; // 0x7f0c0905 float:1.8613875E38 double:1.0530985393E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 0;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x026d:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.emojiAndStickerRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0289;
        L_0x0279:
            r12 = "EmojiAndSticker";
            r13 = 2131493604; // 0x7f0c02e4 float:1.8610693E38 double:1.053097764E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0289:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.chatBarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x02a5;
        L_0x0295:
            r12 = "Chatbar";
            r13 = 2131493331; // 0x7f0c01d3 float:1.861014E38 double:1.053097629E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x02a5:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.forwardAndReplyRow;
            r0 = r19;
            if (r0 != r12) goto L_0x02c1;
        L_0x02b1:
            r12 = "ForwardSetting";
            r13 = 2131493767; // 0x7f0c0387 float:1.8611023E38 double:1.0530978446E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x02c1:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.answeringMachineRow;
            r0 = r19;
            if (r0 != r12) goto L_0x02dd;
        L_0x02cd:
            r12 = "AnsweringMachine";
            r13 = 2131493015; // 0x7f0c0097 float:1.8609498E38 double:1.053097473E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x02dd:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.voiceChangerRow;
            r0 = r19;
            if (r0 != r12) goto L_0x02f9;
        L_0x02e9:
            r12 = "VoiceChanger";
            r13 = 2131495407; // 0x7f0c09ef float:1.861435E38 double:1.053098655E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x02f9:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.textNicerRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0315;
        L_0x0305:
            r12 = "TextNicer";
            r13 = 2131495117; // 0x7f0c08cd float:1.8613762E38 double:1.0530985116E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0315:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.editorTextSizeRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0344;
        L_0x0321:
            r12 = "EditorFontSize";
            r13 = 2131493600; // 0x7f0c02e0 float:1.8610685E38 double:1.053097762E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = "%d";
            r14 = 1;
            r14 = new java.lang.Object[r14];
            r15 = 0;
            r16 = turbogram.Utilities.TurboConfig.editorFontSize;
            r16 = java.lang.Integer.valueOf(r16);
            r14[r15] = r16;
            r13 = java.lang.String.format(r13, r14);
            r14 = 1;
            r8.setTextAndValue(r12, r13, r14);
            goto L_0x0007;
        L_0x0344:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.privacyRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0360;
        L_0x0350:
            r12 = "TurboPrivacySettings";
            r13 = 2131495253; // 0x7f0c0955 float:1.8614037E38 double:1.053098579E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 0;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0360:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.storageRow;
            r0 = r19;
            if (r0 != r12) goto L_0x037c;
        L_0x036c:
            r12 = "StorageSettings";
            r13 = 2131495038; // 0x7f0c087e float:1.8613601E38 double:1.0530984726E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x037c:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.toastRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0398;
        L_0x0388:
            r12 = "ToastNotifications";
            r13 = 2131495163; // 0x7f0c08fb float:1.8613855E38 double:1.0530985343E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x0398:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.draftRow;
            r0 = r19;
            if (r0 != r12) goto L_0x03b4;
        L_0x03a4:
            r12 = "Drafts";
            r13 = 2131493571; // 0x7f0c02c3 float:1.8610626E38 double:1.053097748E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x03b4:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.tagRow;
            r0 = r19;
            if (r0 != r12) goto L_0x03d0;
        L_0x03c0:
            r12 = "TagLinks";
            r13 = 2131495084; // 0x7f0c08ac float:1.8613695E38 double:1.0530984953E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 0;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x03d0:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.deleteAccountRow;
            r0 = r19;
            if (r0 != r12) goto L_0x03ec;
        L_0x03dc:
            r12 = "DeleteAccount";
            r13 = 2131493485; // 0x7f0c026d float:1.8610451E38 double:1.0530977053E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = 1;
            r8.setText(r12, r13);
            goto L_0x0007;
        L_0x03ec:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.checkBubbleRow;
            r0 = r19;
            if (r0 != r12) goto L_0x040a;
        L_0x03f8:
            r12 = "BubbleStyle";
            r13 = 2131493172; // 0x7f0c0134 float:1.8609817E38 double:1.0530975506E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.turboBubbleStyle;
            r14 = 1;
            r8.setTextAndValue(r12, r13, r14);
            goto L_0x0007;
        L_0x040a:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.checkStyleRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0007;
        L_0x0416:
            r12 = "CheckStyle";
            r13 = 2131493346; // 0x7f0c01e2 float:1.861017E38 double:1.0530976366E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.turboCheckStyle;
            r14 = 1;
            r8.setTextAndValue(r12, r13, r14);
            goto L_0x0007;
        L_0x0428:
            r0 = r18;
            r8 = r0.itemView;
            r8 = (org.telegram.ui.Cells.TextCheckCell) r8;
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.tabletModeRow;
            r0 = r19;
            if (r0 != r12) goto L_0x044c;
        L_0x043a:
            r12 = "TabletMode";
            r13 = 2131495073; // 0x7f0c08a1 float:1.8613672E38 double:1.05309849E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.tabletMode;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x044c:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.persianDateRow;
            r0 = r19;
            if (r0 != r12) goto L_0x046a;
        L_0x0458:
            r12 = "UsePersianDate";
            r13 = 2131495335; // 0x7f0c09a7 float:1.8614204E38 double:1.0530986193E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.persianDate;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x046a:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.is24HoursRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0488;
        L_0x0476:
            r12 = "Is24Hours";
            r13 = 2131493959; // 0x7f0c0447 float:1.8611413E38 double:1.0530979395E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.is24Hours;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0488:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.flipRow;
            r0 = r19;
            if (r0 != r12) goto L_0x04a6;
        L_0x0494:
            r12 = "TurboFlip";
            r13 = 2131495233; // 0x7f0c0941 float:1.8613997E38 double:1.053098569E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.flipDirection;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x04a6:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.adsBlockerRow;
            r0 = r19;
            if (r0 != r12) goto L_0x04c4;
        L_0x04b2:
            r12 = "AdsBlocker";
            r13 = 2131492976; // 0x7f0c0070 float:1.860942E38 double:1.053097454E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.adsBlocker;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x04c4:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.categorizeSavedMessagesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x04e2;
        L_0x04d0:
            r12 = "CategorizeProfile";
            r13 = 2131493210; // 0x7f0c015a float:1.8609894E38 double:1.0530975694E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.categorizeProfile;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x04e2:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.enableCallsRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0500;
        L_0x04ee:
            r12 = "TurboEnableCalls";
            r13 = 2131495223; // 0x7f0c0937 float:1.8613977E38 double:1.053098564E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.callsEnabled;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0500:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.hideTurboChannelRow;
            r0 = r19;
            if (r0 != r12) goto L_0x051e;
        L_0x050c:
            r12 = "HideTurboChannel";
            r13 = 2131493892; // 0x7f0c0404 float:1.8611277E38 double:1.0530979064E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.hideTurboChannel;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x051e:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showExactCountRow;
            r0 = r19;
            if (r0 != r12) goto L_0x053c;
        L_0x052a:
            r12 = "ShowExactCount";
            r13 = 2131494962; // 0x7f0c0832 float:1.8613447E38 double:1.053098435E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.exactCount;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x053c:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.confirmatinAudioRow;
            r0 = r19;
            if (r0 != r12) goto L_0x055a;
        L_0x0548:
            r12 = "ConfirmatinAudio";
            r13 = 2131493387; // 0x7f0c020b float:1.8610253E38 double:1.053097657E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.confirmatinAudio;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x055a:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.confirmatinVideoRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0578;
        L_0x0566:
            r12 = "ConfirmatinVideo";
            r13 = 2131493388; // 0x7f0c020c float:1.8610255E38 double:1.0530976573E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.confirmatinVideo;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0578:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showMutualRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0596;
        L_0x0584:
            r12 = "ShowMutualContacts";
            r13 = 2131494966; // 0x7f0c0836 float:1.8613455E38 double:1.053098437E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.mutualContact;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0596:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showUserStatusRow;
            r0 = r19;
            if (r0 != r12) goto L_0x05b4;
        L_0x05a2:
            r12 = "ShowContactStatus";
            r13 = 2131494960; // 0x7f0c0830 float:1.8613443E38 double:1.053098434E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.contactStatus;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x05b4:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showChatUserStatusRow;
            r0 = r19;
            if (r0 != r12) goto L_0x05d2;
        L_0x05c0:
            r12 = "ShowContactStatusGroup";
            r13 = 2131494961; // 0x7f0c0831 float:1.8613445E38 double:1.0530984345E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.chatContactStatus;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x05d2:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.copySenderNameRow;
            r0 = r19;
            if (r0 != r12) goto L_0x05f0;
        L_0x05de:
            r12 = "TurboCopySender";
            r13 = 2131495211; // 0x7f0c092b float:1.8613952E38 double:1.053098558E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.copySender;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x05f0:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.keepChatRow;
            r0 = r19;
            if (r0 != r12) goto L_0x060e;
        L_0x05fc:
            r12 = "KeepChatPage";
            r13 = 2131493975; // 0x7f0c0457 float:1.8611445E38 double:1.0530979474E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.keepChatOpen;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x060e:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.keepContactsPageRow;
            r0 = r19;
            if (r0 != r12) goto L_0x062c;
        L_0x061a:
            r12 = "KeepContactsPage";
            r13 = 2131493976; // 0x7f0c0458 float:1.8611447E38 double:1.053097948E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.keepContactsOpen;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x062c:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.hideCameraRow;
            r0 = r19;
            if (r0 != r12) goto L_0x064a;
        L_0x0638:
            r12 = "HideAttachCamera";
            r13 = 2131493885; // 0x7f0c03fd float:1.8611263E38 double:1.053097903E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.hideCameraInAttach;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x064a:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.actionbarShadowRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0668;
        L_0x0656:
            r12 = "ShowActionShadow";
            r13 = 2131494952; // 0x7f0c0828 float:1.8613427E38 double:1.05309843E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.showActionbarShadow;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0668:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.separateMediaRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0686;
        L_0x0674:
            r12 = "SeprateSharedMedia";
            r13 = 2131494897; // 0x7f0c07f1 float:1.8613315E38 double:1.053098403E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.separateMedia;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x0686:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.avatarInActionRow;
            r0 = r19;
            if (r0 != r12) goto L_0x06a4;
        L_0x0692:
            r12 = "TurboAvatarInAction";
            r13 = 2131495201; // 0x7f0c0921 float:1.8613932E38 double:1.053098553E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.avatarInAction;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x06a4:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.videoPlayerRow;
            r0 = r19;
            if (r0 != r12) goto L_0x06c2;
        L_0x06b0:
            r12 = "TurboInAppPlayer";
            r13 = 2131495237; // 0x7f0c0945 float:1.8614005E38 double:1.053098571E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.inappVideoPlayer;
            r14 = 1;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x06c2:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.chatPreviewRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0007;
        L_0x06ce:
            r12 = "ChatPreview";
            r13 = 2131493319; // 0x7f0c01c7 float:1.8610115E38 double:1.0530976233E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = turbogram.Utilities.TurboConfig.chatPreview;
            r14 = 0;
            r8.setTextAndCheck(r12, r13, r14);
            goto L_0x0007;
        L_0x06e0:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.generalSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0701;
        L_0x06ec:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "GeneralSettings";
            r14 = 2131493850; // 0x7f0c03da float:1.8611192E38 double:1.0530978856E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x0701:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.mainScreenSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0722;
        L_0x070d:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "MainScreenSettings";
            r14 = 2131494055; // 0x7f0c04a7 float:1.8611608E38 double:1.053097987E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x0722:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.chatScreenSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0743;
        L_0x072e:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "chatScreenSettings";
            r14 = 2131495534; // 0x7f0c0a6e float:1.8614607E38 double:1.0530987176E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x0743:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.contactScreenSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0764;
        L_0x074f:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "ContactPageSettings";
            r14 = 2131493399; // 0x7f0c0217 float:1.8610277E38 double:1.053097663E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x0764:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.saveSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0785;
        L_0x0770:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "TurboAccountHeader";
            r14 = 2131495189; // 0x7f0c0915 float:1.8613908E38 double:1.053098547E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x0785:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.profileScreenSectionRow2;
            r0 = r19;
            if (r0 != r12) goto L_0x0007;
        L_0x0791:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (org.telegram.ui.Cells.HeaderCell) r12;
            r13 = "ProfileScreenSettings";
            r14 = 2131494719; // 0x7f0c073f float:1.8612954E38 double:1.053098315E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12.setText(r13);
            goto L_0x0007;
        L_0x07a6:
            r0 = r18;
            r8 = r0.itemView;
            r8 = (org.telegram.ui.Cells.TextDetailSettingsCell) r8;
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.actionbarIconsRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0843;
        L_0x07b8:
            r7 = "";
            r1 = 0;
        L_0x07bc:
            r12 = 1;
            if (r1 >= r12) goto L_0x07ea;
        L_0x07bf:
            if (r1 != 0) goto L_0x07e7;
        L_0x07c1:
            r12 = turbogram.Utilities.TurboConfig.showCategorizeIcon;
            if (r12 == 0) goto L_0x07e7;
        L_0x07c5:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "ShowCategorizeIcon";
            r14 = 2131494955; // 0x7f0c082b float:1.8613433E38 double:1.0530984315E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
        L_0x07e7:
            r1 = r1 + 1;
            goto L_0x07bc;
        L_0x07ea:
            r9 = new java.lang.StringBuilder;
            r9.<init>(r7);
            r12 = r9.length();
            if (r12 == 0) goto L_0x0838;
        L_0x07f5:
            r12 = r9.length();
            r12 = r12 + -2;
            r13 = 32;
            r9.setCharAt(r12, r13);
            r7 = r9.toString();
        L_0x0804:
            r12 = "TurboActionBarIcons";
            r13 = 2131495190; // 0x7f0c0916 float:1.861391E38 double:1.0530985477E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = java.lang.String.valueOf(r7);
            r14 = 1;
            r8.setTextAndValue(r12, r13, r14);
        L_0x0816:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.tabletModeDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0a61;
        L_0x0822:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "TabletModeDescription";
            r14 = 2131495074; // 0x7f0c08a2 float:1.8613674E38 double:1.0530984903E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0838:
            r12 = "TurboNothing";
            r13 = 2131495250; // 0x7f0c0952 float:1.8614031E38 double:1.0530985773E-314;
            r7 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x0804;
        L_0x0843:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.sharedMediaRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0992;
        L_0x084f:
            r7 = "";
            r1 = 0;
        L_0x0853:
            r12 = 6;
            if (r1 >= r12) goto L_0x0955;
        L_0x0856:
            if (r1 != 0) goto L_0x0881;
        L_0x0858:
            r12 = turbogram.Utilities.TurboConfig.sharedPhotoTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x085c:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedPhotoTitle";
            r14 = 2131494942; // 0x7f0c081e float:1.8613407E38 double:1.053098425E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
        L_0x087e:
            r1 = r1 + 1;
            goto L_0x0853;
        L_0x0881:
            r12 = 1;
            if (r1 != r12) goto L_0x08ab;
        L_0x0884:
            r12 = turbogram.Utilities.TurboConfig.sharedVideoTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x0888:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedVideoTitle";
            r14 = 2131494944; // 0x7f0c0820 float:1.861341E38 double:1.053098426E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x087e;
        L_0x08ab:
            r12 = 2;
            if (r1 != r12) goto L_0x08d5;
        L_0x08ae:
            r12 = turbogram.Utilities.TurboConfig.sharedFileTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x08b2:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedFileTitle";
            r14 = 2131494931; // 0x7f0c0813 float:1.8613384E38 double:1.0530984197E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x087e;
        L_0x08d5:
            r12 = 3;
            if (r1 != r12) goto L_0x08ff;
        L_0x08d8:
            r12 = turbogram.Utilities.TurboConfig.sharedLinkTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x08dc:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedLinkTitle";
            r14 = 2131494935; // 0x7f0c0817 float:1.8613392E38 double:1.0530984217E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x087e;
        L_0x08ff:
            r12 = 4;
            if (r1 != r12) goto L_0x092a;
        L_0x0902:
            r12 = turbogram.Utilities.TurboConfig.sharedMusicTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x0906:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedMusicTitle";
            r14 = 2131494941; // 0x7f0c081d float:1.8613405E38 double:1.0530984246E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x087e;
        L_0x092a:
            r12 = 5;
            if (r1 != r12) goto L_0x087e;
        L_0x092d:
            r12 = turbogram.Utilities.TurboConfig.sharedVoiceTitle;
            if (r12 == 0) goto L_0x087e;
        L_0x0931:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "SharedVoiceTitle";
            r14 = 2131494946; // 0x7f0c0822 float:1.8613415E38 double:1.053098427E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x087e;
        L_0x0955:
            r9 = new java.lang.StringBuilder;
            r9.<init>(r7);
            r12 = r9.length();
            if (r12 == 0) goto L_0x0987;
        L_0x0960:
            r12 = r9.length();
            r12 = r12 + -2;
            r13 = 32;
            r9.setCharAt(r12, r13);
            r7 = r9.toString();
        L_0x096f:
            r12 = "SharedMedia";
            r13 = 2131494937; // 0x7f0c0819 float:1.8613396E38 double:1.0530984227E-314;
            r12 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r13 = java.lang.String.valueOf(r7);
            r14 = 0;
            r8.setTextAndValue(r12, r13, r14);
            r12 = 0;
            r8.setMultilineDetail(r12);
            goto L_0x0816;
        L_0x0987:
            r12 = "TurboNothing";
            r13 = 2131495250; // 0x7f0c0952 float:1.8614031E38 double:1.0530985773E-314;
            r7 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x096f;
        L_0x0992:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showContactsAvatarRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0816;
        L_0x099e:
            r7 = "";
            r1 = 0;
        L_0x09a2:
            r12 = 3;
            if (r1 >= r12) goto L_0x0a24;
        L_0x09a5:
            if (r1 != 0) goto L_0x09d0;
        L_0x09a7:
            r12 = turbogram.Utilities.TurboConfig$BG.showAvatar;
            if (r12 == 0) goto L_0x09cd;
        L_0x09ab:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "ShowContactInChat";
            r14 = 2131494959; // 0x7f0c082f float:1.8613441E38 double:1.0530984335E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
        L_0x09cd:
            r1 = r1 + 1;
            goto L_0x09a2;
        L_0x09d0:
            r12 = 1;
            if (r1 != r12) goto L_0x09fa;
        L_0x09d3:
            r12 = turbogram.Utilities.TurboConfig$BG.showMyAvatar;
            if (r12 == 0) goto L_0x09cd;
        L_0x09d7:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "ShowOwnInChat";
            r14 = 2131494972; // 0x7f0c083c float:1.8613467E38 double:1.05309844E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x09cd;
        L_0x09fa:
            r12 = 2;
            if (r1 != r12) goto L_0x09cd;
        L_0x09fd:
            r12 = turbogram.Utilities.TurboConfig$BG.showMyAvatarGroup;
            if (r12 == 0) goto L_0x09cd;
        L_0x0a01:
            r12 = new java.lang.StringBuilder;
            r12.<init>();
            r12 = r12.append(r7);
            r13 = "ShowOwnInGroup";
            r14 = 2131494973; // 0x7f0c083d float:1.861347E38 double:1.0530984404E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r12 = r12.append(r13);
            r13 = ", ";
            r12 = r12.append(r13);
            r7 = r12.toString();
            goto L_0x09cd;
        L_0x0a24:
            r9 = new java.lang.StringBuilder;
            r9.<init>(r7);
            r12 = r9.length();
            if (r12 == 0) goto L_0x0a56;
        L_0x0a2f:
            r12 = r9.length();
            r12 = r12 + -2;
            r13 = 32;
            r9.setCharAt(r12, r13);
            r7 = r9.toString();
        L_0x0a3e:
            r12 = "ShowContactAvatarInChat";
            r13 = 2131494958; // 0x7f0c082e float:1.861344E38 double:1.053098433E-314;
            r11 = org.telegram.messenger.LocaleController.getString(r12, r13);
            r12 = java.lang.String.valueOf(r7);
            r13 = 1;
            r8.setTextAndValue(r11, r12, r13);
            r12 = 0;
            r8.setMultilineDetail(r12);
            goto L_0x0816;
        L_0x0a56:
            r12 = "TurboNothing";
            r13 = 2131495250; // 0x7f0c0952 float:1.8614031E38 double:1.0530985773E-314;
            r7 = org.telegram.messenger.LocaleController.getString(r12, r13);
            goto L_0x0a3e;
        L_0x0a61:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showMutualDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0a83;
        L_0x0a6d:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "MutualContactDescription";
            r14 = 2131494150; // 0x7f0c0506 float:1.86118E38 double:1.053098034E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0a83:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showUserStatusDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0aa5;
        L_0x0a8f:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "ContactStatusDescription";
            r14 = 2131493403; // 0x7f0c021b float:1.8610285E38 double:1.053097665E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0aa5:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.showChatUserStatusDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0ac7;
        L_0x0ab1:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "GroupContactStatusDescription";
            r14 = 2131493861; // 0x7f0c03e5 float:1.8611214E38 double:1.053097891E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0ac7:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.keepChatDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0ae9;
        L_0x0ad3:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "keepChatDescription";
            r14 = 2131495674; // 0x7f0c0afa float:1.8614891E38 double:1.053098787E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0ae9:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.copySenderNameDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0b0b;
        L_0x0af5:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "TurboCopySenderDes";
            r14 = 2131495212; // 0x7f0c092c float:1.8613954E38 double:1.0530985585E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0b0b:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.keepContactsPageDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0b2d;
        L_0x0b17:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "KeepContactsPageDes";
            r14 = 2131493977; // 0x7f0c0459 float:1.861145E38 double:1.0530979484E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
        L_0x0b2d:
            r0 = r17;
            r12 = turbogram.TurboSettingsActivity.this;
            r12 = r12.chatPreviewDesRow;
            r0 = r19;
            if (r0 != r12) goto L_0x0007;
        L_0x0b39:
            r0 = r18;
            r12 = r0.itemView;
            r12 = (turbogram.Cells.TextDescriptionCell) r12;
            r13 = "ChatPreviewDes";
            r14 = 2131493320; // 0x7f0c01c8 float:1.8610117E38 double:1.0530976238E-314;
            r13 = org.telegram.messenger.LocaleController.getString(r13, r14);
            r14 = 1;
            r12.setText(r13, r14);
            goto L_0x0007;
            */
            throw new UnsupportedOperationException("Method not decompiled: turbogram.TurboSettingsActivity.ListAdapter.onBindViewHolder(org.telegram.messenger.support.widget.RecyclerView$ViewHolder, int):void");
        }

        public int getItemCount() {
            return TurboSettingsActivity.this.rowCount;
        }

        public int getItemViewType(int position) {
            if (position == TurboSettingsActivity.this.emptyRow || position == TurboSettingsActivity.this.overscrollRow) {
                return 0;
            }
            if (position == TurboSettingsActivity.this.mainScreenSectionRow || position == TurboSettingsActivity.this.chatScreenSectionRow || position == TurboSettingsActivity.this.contactScreenSectionRow || position == TurboSettingsActivity.this.saveSectionRow || position == TurboSettingsActivity.this.profileScreenSectionRow) {
                return 1;
            }
            if (position == TurboSettingsActivity.this.chatBarRow || position == TurboSettingsActivity.this.fontRow || position == TurboSettingsActivity.this.forwardAndReplyRow || position == TurboSettingsActivity.this.toastRow || position == TurboSettingsActivity.this.deleteAccountRow || position == TurboSettingsActivity.this.storageRow || position == TurboSettingsActivity.this.tabsSettingsRow || position == TurboSettingsActivity.this.answeringMachineRow || position == TurboSettingsActivity.this.emojiAndStickerRow || position == TurboSettingsActivity.this.voiceChangerRow || position == TurboSettingsActivity.this.CategorizeDialogsRow || position == TurboSettingsActivity.this.menuSettingsRow || position == TurboSettingsActivity.this.textNicerRow || position == TurboSettingsActivity.this.editorTextSizeRow || position == TurboSettingsActivity.this.toolBarRow || position == TurboSettingsActivity.this.privacyRow || position == TurboSettingsActivity.this.draftRow || position == TurboSettingsActivity.this.tagRow || position == TurboSettingsActivity.this.contactAvatarRow || position == TurboSettingsActivity.this.groupAvatarRow || position == TurboSettingsActivity.this.userAvatarRow || position == TurboSettingsActivity.this.checkStyleRow || position == TurboSettingsActivity.this.checkBubbleRow) {
                return 2;
            }
            if (position == TurboSettingsActivity.this.tabletModeRow || position == TurboSettingsActivity.this.persianDateRow || position == TurboSettingsActivity.this.is24HoursRow || position == TurboSettingsActivity.this.showMutualRow || position == TurboSettingsActivity.this.showUserStatusRow || position == TurboSettingsActivity.this.showChatUserStatusRow || position == TurboSettingsActivity.this.confirmatinAudioRow || position == TurboSettingsActivity.this.adsBlockerRow || position == TurboSettingsActivity.this.confirmatinVideoRow || position == TurboSettingsActivity.this.categorizeSavedMessagesRow || position == TurboSettingsActivity.this.showExactCountRow || position == TurboSettingsActivity.this.keepContactsPageRow || position == TurboSettingsActivity.this.copySenderNameRow || position == TurboSettingsActivity.this.flipRow || position == TurboSettingsActivity.this.enableCallsRow || position == TurboSettingsActivity.this.keepChatRow || position == TurboSettingsActivity.this.hideCameraRow || position == TurboSettingsActivity.this.separateMediaRow || position == TurboSettingsActivity.this.avatarInActionRow || position == TurboSettingsActivity.this.chatPreviewRow || position == TurboSettingsActivity.this.actionbarShadowRow || position == TurboSettingsActivity.this.hideTurboChannelRow || position == TurboSettingsActivity.this.videoPlayerRow) {
                return 3;
            }
            if (position == TurboSettingsActivity.this.generalSectionRow2 || position == TurboSettingsActivity.this.mainScreenSectionRow2 || position == TurboSettingsActivity.this.chatScreenSectionRow2 || position == TurboSettingsActivity.this.contactScreenSectionRow2 || position == TurboSettingsActivity.this.saveSectionRow2 || position == TurboSettingsActivity.this.saveSectionRow2 || position == TurboSettingsActivity.this.profileScreenSectionRow2) {
                return 4;
            }
            if (position == TurboSettingsActivity.this.versionRow) {
                return 5;
            }
            if (position == TurboSettingsActivity.this.sharedMediaRow || position == TurboSettingsActivity.this.showContactsAvatarRow || position == TurboSettingsActivity.this.actionbarIconsRow) {
                return 6;
            }
            if (position == TurboSettingsActivity.this.tabletModeDesRow || position == TurboSettingsActivity.this.copySenderNameDesRow || position == TurboSettingsActivity.this.showMutualDesRow || position == TurboSettingsActivity.this.showUserStatusDesRow || position == TurboSettingsActivity.this.showChatUserStatusDesRow || position == TurboSettingsActivity.this.keepChatDesRow || position == TurboSettingsActivity.this.keepContactsPageDesRow || position == TurboSettingsActivity.this.chatPreviewDesRow) {
                return 7;
            }
            return 2;
        }
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        this.imageUpdater.parentFragment = this;
        this.imageUpdater.delegate = new C24791();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        this.rowCount = 0;
        int i = this.rowCount;
        this.rowCount = i + 1;
        this.overscrollRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.emptyRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.generalSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tabletModeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tabletModeDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.fontRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.persianDateRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.is24HoursRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.flipRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.adsBlockerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.enableCallsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hideTurboChannelRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.answeringMachineRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.storageRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.toastRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.privacyRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mainScreenSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.mainScreenSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.avatarInActionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.actionbarShadowRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.actionbarIconsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.menuSettingsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tabsSettingsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.CategorizeDialogsRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showUserStatusRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showUserStatusDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.contactAvatarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.groupAvatarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.chatPreviewRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.chatPreviewDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.toolBarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.chatScreenSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.chatScreenSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.categorizeSavedMessagesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.chatBarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.forwardAndReplyRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showContactsAvatarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.checkBubbleRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.checkStyleRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showExactCountRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showChatUserStatusRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showChatUserStatusDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.copySenderNameRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.copySenderNameDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.hideCameraRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.editorTextSizeRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepChatRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepChatDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.videoPlayerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.voiceChangerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.textNicerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.confirmatinAudioRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.confirmatinVideoRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.emojiAndStickerRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.draftRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.tagRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.profileScreenSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.profileScreenSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.separateMediaRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.sharedMediaRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.contactScreenSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.contactScreenSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showMutualRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.showMutualDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepContactsPageRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.keepContactsPageDesRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.userAvatarRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.saveSectionRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.saveSectionRow2 = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.deleteAccountRow = i;
        i = this.rowCount;
        this.rowCount = i + 1;
        this.versionRow = i;
        MessagesController.getInstance(this.currentAccount).loadFullUser(UserConfig.getInstance(this.currentAccount).getCurrentUser(), this.classGuid, true);
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.avatarImage != null) {
            this.avatarImage.setImageDrawable(null);
        }
        MessagesController.getInstance(this.currentAccount).cancelLoadFullUser(UserConfig.getInstance(this.currentAccount).clientUserId);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        this.imageUpdater.clear();
    }

    public View createView(Context context) {
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_avatar_backgroundActionBarBlue));
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_avatar_actionBarSelectorBlue), false);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_avatar_actionBarIconBlue), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAddToContainer(false);
        this.extraHeight = 88;
        if (AndroidUtilities.isTablet()) {
            this.actionBar.setOccupyStatusBar(false);
        }
        this.actionBar.setActionBarMenuOnItemClick(new C24802());
        this.listAdapter = new ListAdapter(context);
        this.fragmentView = new FrameLayout(context) {
            protected boolean drawChild(@NonNull Canvas canvas, @NonNull View child, long drawingTime) {
                if (child != TurboSettingsActivity.this.listView) {
                    return super.drawChild(canvas, child, drawingTime);
                }
                boolean result = super.drawChild(canvas, child, drawingTime);
                if (TurboSettingsActivity.this.parentLayout == null) {
                    return result;
                }
                int actionBarHeight = 0;
                int childCount = getChildCount();
                for (int a = 0; a < childCount; a++) {
                    View view = getChildAt(a);
                    if (view != child && (view instanceof ActionBar) && view.getVisibility() == 0) {
                        if (((ActionBar) view).getCastShadows()) {
                            actionBarHeight = view.getMeasuredHeight();
                        }
                        TurboSettingsActivity.this.parentLayout.drawHeaderShadow(canvas, actionBarHeight);
                        return result;
                    }
                }
                TurboSettingsActivity.this.parentLayout.drawHeaderShadow(canvas, actionBarHeight);
                return result;
            }
        };
        this.fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout frameLayout = this.fragmentView;
        this.listView = new RecyclerListView(context);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView recyclerListView = this.listView;
        LayoutManager linearLayoutManager = new LinearLayoutManager(context, 1, false);
        this.layoutManager = linearLayoutManager;
        recyclerListView.setLayoutManager(linearLayoutManager);
        this.listView.setGlowColor(Theme.getColor(Theme.key_avatar_backgroundActionBarBlue));
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new C24914());
        frameLayout.addView(this.actionBar);
        this.extraHeightView = new View(context);
        this.extraHeightView.setPivotY(0.0f);
        this.extraHeightView.setBackgroundColor(Theme.getColor(Theme.key_avatar_backgroundActionBarBlue));
        frameLayout.addView(this.extraHeightView, LayoutHelper.createFrame(-1, 88.0f));
        this.shadowView = new View(context);
        this.shadowView.setBackgroundResource(R.drawable.header_shadow);
        frameLayout.addView(this.shadowView, LayoutHelper.createFrame(-1, 3.0f));
        this.avatarImage = new BackupImageView(context);
        this.avatarImage.setRoundRadius(AndroidUtilities.dp(21.0f));
        this.avatarImage.setPivotX(0.0f);
        this.avatarImage.setPivotY(0.0f);
        frameLayout.addView(this.avatarImage, LayoutHelper.createFrame(42, 42.0f, 51, 64.0f, 0.0f, 0.0f, 0.0f));
        this.nameTextView = new TextView(context);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_profile_title));
        this.nameTextView.setTextSize(1, 18.0f);
        this.nameTextView.setLines(1);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TruncateAt.END);
        this.nameTextView.setGravity(3);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.nameTextView.setPivotX(0.0f);
        this.nameTextView.setPivotY(0.0f);
        frameLayout.addView(this.nameTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 118.0f, 0.0f, 48.0f, 0.0f));
        this.onlineTextView = new TextView(context);
        this.onlineTextView.setTextColor(Theme.getColor(Theme.key_avatar_subtitleInProfileBlue));
        this.onlineTextView.setTextSize(1, 14.0f);
        this.onlineTextView.setLines(1);
        this.onlineTextView.setMaxLines(1);
        this.onlineTextView.setSingleLine(true);
        this.onlineTextView.setEllipsize(TruncateAt.END);
        this.onlineTextView.setGravity(3);
        frameLayout.addView(this.onlineTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 118.0f, 0.0f, 48.0f, 0.0f));
        this.onlineTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        needLayout();
        this.listView.setOnScrollListener(new C24925());
        return this.fragmentView;
    }

    private boolean noneSelected() {
        int counter = 0;
        for (int a = 0; a < 6; a++) {
            if (a == 0) {
                int i;
                if (TurboConfig.sharedPhotoTitle) {
                    i = 1;
                } else {
                    i = 0;
                }
                counter += i;
            } else if (a == 1) {
                counter += TurboConfig.sharedVideoTitle ? 1 : 0;
            } else if (a == 2) {
                counter += TurboConfig.sharedFileTitle ? 1 : 0;
            } else if (a == 3) {
                counter += TurboConfig.sharedLinkTitle ? 1 : 0;
            } else if (a == 4) {
                counter += TurboConfig.sharedMusicTitle ? 1 : 0;
            } else if (a == 5) {
                counter += TurboConfig.sharedVoiceTitle ? 1 : 0;
            }
        }
        if (counter == 0) {
            return true;
        }
        return false;
    }

    protected void onDialogDismiss(Dialog dialog) {
    }

    public void onActivityResultFragment(int requestCode, int resultCode, Intent data) {
        this.imageUpdater.onActivityResult(requestCode, resultCode, data);
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.updateInterfaces) {
            int mask = ((Integer) args[0]).intValue();
            if ((mask & 2) != 0 || (mask & 1) != 0) {
                updateUserData();
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
        updateUserData();
        fixLayout();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        fixLayout();
    }

    private void needLayout() {
        int newTop = (this.actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight();
        if (this.listView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
            if (layoutParams.topMargin != newTop) {
                layoutParams.topMargin = newTop;
                this.listView.setLayoutParams(layoutParams);
                this.extraHeightView.setTranslationY((float) newTop);
            }
        }
        if (this.avatarImage != null) {
            float diff = ((float) this.extraHeight) / ((float) AndroidUtilities.dp(88.0f));
            this.extraHeightView.setScaleY(diff);
            this.shadowView.setTranslationY((float) (this.extraHeight + newTop));
            this.avatarImage.setScaleX(((18.0f * diff) + 42.0f) / 42.0f);
            this.avatarImage.setScaleY(((18.0f * diff) + 42.0f) / 42.0f);
            float avatarY = ((((float) (this.actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0)) + ((((float) ActionBar.getCurrentActionBarHeight()) / 2.0f) * (1.0f + diff))) - (21.0f * AndroidUtilities.density)) + ((27.0f * AndroidUtilities.density) * diff);
            this.avatarImage.setTranslationX(((float) (-AndroidUtilities.dp(47.0f))) * diff);
            this.avatarImage.setTranslationY((float) Math.ceil((double) avatarY));
            this.nameTextView.setTranslationX((AndroidUtilities.density * -21.0f) * diff);
            this.nameTextView.setTranslationY((((float) Math.floor((double) avatarY)) - ((float) Math.ceil((double) AndroidUtilities.density))) + ((float) Math.floor((double) ((7.0f * AndroidUtilities.density) * diff))));
            this.onlineTextView.setTranslationX((AndroidUtilities.density * -21.0f) * diff);
            this.onlineTextView.setTranslationY((((float) Math.floor((double) avatarY)) + ((float) AndroidUtilities.dp(22.0f))) + (((float) Math.floor((double) (11.0f * AndroidUtilities.density))) * diff));
            this.nameTextView.setScaleX((0.12f * diff) + 1.0f);
            this.nameTextView.setScaleY((0.12f * diff) + 1.0f);
        }
    }

    private void fixLayout() {
        if (this.fragmentView != null) {
            this.fragmentView.getViewTreeObserver().addOnPreDrawListener(new C24936());
        }
    }

    private void updateUserData() {
        boolean z = true;
        User user = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        TLObject photo = null;
        TLRPC$FileLocation photoBig = null;
        if (user.photo != null) {
            photo = user.photo.photo_small;
            photoBig = user.photo.photo_big;
        }
        Drawable avatarDrawable = new AvatarDrawable(user, true);
        avatarDrawable.setColor(-10708787);
        if (this.avatarImage != null) {
            this.avatarImage.setImage(photo, "50_50", avatarDrawable);
            ImageReceiver imageReceiver = this.avatarImage.getImageReceiver();
            PhotoViewer.getInstance();
            imageReceiver.setVisible(!PhotoViewer.isShowingImage(photoBig), false);
            this.nameTextView.setText(UserObject.getUserName(user));
            this.onlineTextView.setText(LocaleController.getString("Online", R.string.Online));
            ImageReceiver imageReceiver2 = this.avatarImage.getImageReceiver();
            PhotoViewer.getInstance();
            if (PhotoViewer.isShowingImage(photoBig)) {
                z = false;
            }
            imageReceiver2.setVisible(z, false);
        }
    }
}
