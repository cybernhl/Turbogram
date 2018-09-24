package turbogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver.BitmapHolder;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PhotoViewer.PhotoViewerProvider;
import org.telegram.ui.PhotoViewer.PlaceProviderObject;
import turbogram.Cells.UserChangesCell;
import turbogram.Database.DBHelper;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class UserChangesActivity extends BaseFragment implements NotificationCenter$NotificationCenterDelegate, PhotoViewerProvider {
    private boolean avatarTouched = false;
    private int currentFilterType = 0;
    private UserChangesAdapter cursorAdapter;
    private DBHelper dbHelper;
    private float downX;
    private float downY;
    private TextView emptyTextView;
    private ActionBarMenuItem filterItem;
    private ListView listView;
    private boolean paused;
    private User selectedUser;
    protected BackupImageView selectedUserAvatar;
    private float upX;
    private float upY;

    /* renamed from: turbogram.UserChangesActivity$1 */
    class C24941 implements OnClickListener {
        C24941() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            UserChangesActivity.this.dbHelper.deleteAll();
            UserChangesActivity.this.forceReload();
        }
    }

    /* renamed from: turbogram.UserChangesActivity$2 */
    class C24952 extends ActionBarMenuOnItemClick {
        C24952() {
        }

        public void onItemClick(int i) {
            if (i == -1) {
                UserChangesActivity.this.finishFragment();
            } else if (i == 2) {
                UserChangesActivity.this.showDeleteHistoryConfirmation();
            } else if (i == 3) {
                UserChangesActivity.this.showFilterDialog();
            }
        }
    }

    /* renamed from: turbogram.UserChangesActivity$3 */
    class C24963 implements OnTouchListener {
        C24963() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: turbogram.UserChangesActivity$4 */
    class C24974 implements OnItemClickListener {
        C24974() {
        }

        public void onItemClick(AdapterView adapterView, View view, int position, long j) {
            UserChangesActivity.this.selectedUser = MessagesController.getInstance(UserChangesActivity.this.currentAccount).getUser(Integer.valueOf(UserChangesActivity.this.dbHelper.getUpdateModelByCursor((Cursor) UserChangesActivity.this.listView.getAdapter().getItem(position)).getUserId()));
            if (UserChangesActivity.this.selectedUser != null) {
                UserChangesActivity.this.selectedUserAvatar = ((UserChangesCell) view).getAvatarImageView();
                if (UserChangesActivity.this.avatarTouched) {
                    UserChangesActivity.this.avatarTouched = false;
                    PhotoViewer.getInstance().setParentActivity(UserChangesActivity.this.getParentActivity());
                    PhotoViewer.getInstance().openPhoto(UserChangesActivity.this.selectedUser.photo.photo_big, UserChangesActivity.this);
                    return;
                }
                UserChangesActivity.this.openChatActivity();
            }
        }
    }

    /* renamed from: turbogram.UserChangesActivity$5 */
    class C24985 implements OnTouchListener {
        C24985() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    UserChangesActivity.this.downX = event.getX();
                    UserChangesActivity.this.downY = event.getY();
                    break;
                case 1:
                    UserChangesActivity.this.upX = event.getX();
                    UserChangesActivity.this.upY = event.getY();
                    float deltaY = UserChangesActivity.this.downY - UserChangesActivity.this.upY;
                    if (Math.abs(UserChangesActivity.this.downX - UserChangesActivity.this.upX) < 10.0f && Math.abs(deltaY) < 10.0f) {
                        if (!LocaleController.isRTL) {
                            if (UserChangesActivity.this.upX < ((float) AndroidUtilities.dp((float) AndroidUtilities.leftBaseline))) {
                                UserChangesActivity.this.avatarTouched = true;
                                break;
                            }
                        } else if (UserChangesActivity.this.upX > ((float) (UserChangesActivity.this.listView.getMeasuredWidth() - AndroidUtilities.dp((float) AndroidUtilities.leftBaseline)))) {
                            UserChangesActivity.this.avatarTouched = true;
                            break;
                        }
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: turbogram.UserChangesActivity$6 */
    class C24996 implements OnClickListener {
        C24996() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == 0) {
                UserChangesActivity.this.filterItem.setIcon((int) R.drawable.turbo_ic_ab_filter);
            } else {
                UserChangesActivity.this.filterItem.setIcon((int) R.drawable.turbo_ic_ab_filter_selected);
            }
            UserChangesActivity.this.currentFilterType = i;
            UserChangesActivity.this.forceReload();
            dialogInterface.dismiss();
        }
    }

    class UserChangesAdapter extends CursorAdapter {
        private DBHelper dbHelper;

        public UserChangesAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
            this.dbHelper = new DBHelper(context);
        }

        public void bindView(View view, Context context, Cursor cursor) {
            if (!TurboUtils.isHiddenDialog(this.dbHelper.getUpdateModelByCursor(cursor).getUserId())) {
                ((UserChangesCell) view).setData(this.dbHelper.getUpdateModelByCursor(cursor));
            }
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return new UserChangesCell(this.mContext, 10);
        }
    }

    public UserChangesActivity(Bundle bundle) {
        super(bundle);
    }

    private void forceReload() {
        this.cursorAdapter = new UserChangesAdapter(getParentActivity(), this.dbHelper.m1236a(this.currentFilterType, 500));
        this.listView.setAdapter(this.cursorAdapter);
    }

    private void openChatActivity() {
        Bundle args = new Bundle();
        args.putInt("user_id", this.selectedUser.id);
        if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(args, this) && !TurboConfig.containValue("hide_" + this.selectedUser.id)) {
            presentFragment(new ChatActivity(args), false);
        }
    }

    private void showDeleteHistoryConfirmation() {
        Builder builder = new Builder(getParentActivity());
        builder.setMessage(LocaleController.getString("AreYouSureDeleteChanges", R.string.AreYouSureDeleteChanges));
        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C24941());
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    public boolean cancelButtonPressed() {
        return false;
    }

    public void needAddMorePhotos() {
    }

    public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {
    }

    public View createView(Context context) {
        this.fragmentView = new FrameLayout(context);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("ContactsChanges", R.string.ContactsChanges));
        this.actionBar.setActionBarMenuOnItemClick(new C24952());
        ActionBarMenu createMenu = this.actionBar.createMenu();
        createMenu.addItem(2, (int) R.drawable.turbo_delete);
        this.filterItem = createMenu.addItem(3, (int) R.drawable.turbo_ic_ab_filter);
        this.dbHelper = new DBHelper(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setVisibility(4);
        linearLayout.setOrientation(1);
        ((FrameLayout) this.fragmentView).addView(linearLayout);
        LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 48;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOnTouchListener(new C24963());
        this.emptyTextView = new TextView(context);
        this.emptyTextView.setTextColor(Theme.getColor(Theme.key_emptyListPlaceholder));
        this.emptyTextView.setTextSize(1, 20.0f);
        this.emptyTextView.setGravity(17);
        this.emptyTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.emptyTextView.setText(LocaleController.getString("NoContactChanges", R.string.NoContactChanges));
        linearLayout.addView(this.emptyTextView);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.emptyTextView.getLayoutParams();
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.weight = 0.5f;
        this.emptyTextView.setLayoutParams(layoutParams2);
        View frameLayout = new FrameLayout(context);
        linearLayout.addView(frameLayout);
        layoutParams2 = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.weight = 0.5f;
        frameLayout.setLayoutParams(layoutParams2);
        this.cursorAdapter = new UserChangesAdapter(context, this.dbHelper.m1236a(this.currentFilterType, 500));
        this.listView = new ListView(context);
        this.listView.setEmptyView(linearLayout);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setDivider(null);
        this.listView.setDividerHeight(0);
        this.listView.setFastScrollEnabled(true);
        this.listView.setScrollBarStyle(ConnectionsManager.FileTypeVideo);
        this.listView.setCacheColorHint(0);
        this.listView.setScrollingCacheEnabled(false);
        this.listView.setAdapter(this.cursorAdapter);
        ((FrameLayout) this.fragmentView).addView(this.listView);
        layoutParams = (LayoutParams) this.listView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.listView.setLayoutParams(layoutParams);
        this.listView.setOnItemClickListener(new C24974());
        this.listView.setOnTouchListener(new C24985());
        return this.fragmentView;
    }

    public void didReceivedNotification(int i, int account, Object... objArr) {
        if (!this.paused) {
            this.dbHelper.m1237a();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        }
        forceReload();
    }

    protected void showFilterDialog() {
        Builder builder = new Builder(getParentActivity());
        builder.setItems(new CharSequence[]{LocaleController.getString("All Changes", R.string.AllChanges), LocaleController.getString("Username Changes", R.string.change_username), LocaleController.getString("Name Changes", R.string.change_name), LocaleController.getString("Photo Changes", R.string.change_photo), LocaleController.getString("Phone Changes", R.string.change_phone)}, new C24996());
        showDialog(builder.create());
    }

    public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
        PlaceProviderObject placeProviderObject = null;
        int i = 0;
        if (fileLocation != null) {
            int user_id = this.selectedUser.id;
            TLRPC$FileLocation photoBig = null;
            if (user_id != 0) {
                User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(user_id));
                if (!(user == null || user.photo == null || user.photo.photo_big == null)) {
                    photoBig = user.photo.photo_big;
                }
            }
            if (photoBig != null && photoBig.local_id == fileLocation.local_id && photoBig.volume_id == fileLocation.volume_id && photoBig.dc_id == fileLocation.dc_id) {
                int[] coords = new int[2];
                this.selectedUserAvatar.getLocationInWindow(coords);
                placeProviderObject = new PlaceProviderObject();
                placeProviderObject.viewX = coords[0];
                int i2 = coords[1];
                if (VERSION.SDK_INT < 21) {
                    i = AndroidUtilities.statusBarHeight;
                }
                placeProviderObject.viewY = i2 - i;
                placeProviderObject.parentView = this.selectedUserAvatar;
                placeProviderObject.imageReceiver = this.selectedUserAvatar.getImageReceiver();
                if (user_id != 0) {
                    placeProviderObject.dialogId = user_id;
                }
                placeProviderObject.thumb = placeProviderObject.imageReceiver.getBitmapSafe();
                placeProviderObject.size = -1;
                placeProviderObject.radius = this.selectedUserAvatar.getImageReceiver().getRoundRadius();
                placeProviderObject.scale = this.selectedUserAvatar.getScaleX();
            }
        }
        return placeProviderObject;
    }

    public int getSelectedCount() {
        return 0;
    }

    public BitmapHolder getThumbForPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int index) {
        return null;
    }

    public boolean isPhotoChecked(int i) {
        return false;
    }

    public int setPhotoChecked(int index, VideoEditedInfo videoEditedInfo) {
        return index;
    }

    public int setPhotoUnchecked(Object photoEntry) {
        return 0;
    }

    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        return true;
    }

    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    public void onPause() {
        super.onPause();
        this.paused = true;
    }

    public void onResume() {
        super.onResume();
        this.paused = false;
        this.dbHelper.m1237a();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
    }

    public void updatePhotoAtIndex(int i) {
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

    public void willHidePhotoViewer() {
        if (this.selectedUserAvatar != null) {
            this.selectedUserAvatar.getImageReceiver().setVisible(true, true);
        }
    }

    public void willSwitchFromPhoto(MessageObject messageObject, TLRPC$FileLocation fileLocation, int i) {
    }
}
