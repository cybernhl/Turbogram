package turbogram.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextUtils.TruncateAt;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$ChatParticipant;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$TL_channelFull;
import org.telegram.tgnet.TLRPC$TL_channelParticipantCreator;
import org.telegram.tgnet.TLRPC$TL_channelParticipantEditor_layer67;
import org.telegram.tgnet.TLRPC$TL_channelParticipantModerator_layer67;
import org.telegram.tgnet.TLRPC$TL_channelParticipantsRecent;
import org.telegram.tgnet.TLRPC$TL_channels_channelParticipants;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipants;
import org.telegram.tgnet.TLRPC$TL_chatChannelParticipant;
import org.telegram.tgnet.TLRPC$TL_chatFull;
import org.telegram.tgnet.TLRPC$TL_chatParticipantAdmin;
import org.telegram.tgnet.TLRPC$TL_chatParticipantCreator;
import org.telegram.tgnet.TLRPC$TL_chatParticipants;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener;
import turbogram.Utilities.DialogsLoader;
import turbogram.Utilities.TurboConfig$Chatbar;
import turbogram.Utilities.TurboUtils;

public class ChatDialogsView extends FrameLayout {
    private int avatarSize;
    private ImageView btn;
    private boolean centerButton;
    private int chat_id;
    private int classGuid;
    private int creatorID;
    private int currentAccount;
    private TLRPC$Chat currentChat;
    private ChatDialogsViewDelegate delegate;
    private Adapter dialogsAdapter;
    private int dialogsSize = 0;
    private int dialogsType;
    private boolean disableLongCick;
    private TLRPC$ChatFull info;
    private LinearLayoutManager layoutManager;
    private int listHeight;
    private RecyclerListView listView;
    private int listWidth;
    private int loadMoreMembersRow;
    private boolean loadingUsers;
    private Adapter membersAdapter;
    private int membersCount;
    private ArrayList<Integer> membersMap;
    private ChatActivity parentFragment;
    private boolean refresh;
    private boolean showMembers;
    private ArrayList<Integer> sortedUsers;
    private int textSize;
    private TextView tv;
    private boolean vertical;
    private boolean visible;

    public interface ChatDialogsViewDelegate {
        void didLongPressedOnSubDialog(long j, int i);

        void didPressedOnBtn(boolean z);

        void didPressedOnSubDialog(long j);
    }

    /* renamed from: turbogram.Components.ChatDialogsView$3 */
    class C22533 implements OnItemClickListener {
        C22533() {
        }

        public void onItemClick(View view, int n) {
            if (ChatDialogsView.this.delegate != null) {
                try {
                    ChatDialogsView.this.delegate.didPressedOnSubDialog(((Long) view.getTag()).longValue());
                } catch (Exception ex) {
                    FileLog.e(ex);
                }
            }
        }
    }

    /* renamed from: turbogram.Components.ChatDialogsView$4 */
    class C22544 implements OnItemLongClickListener {
        C22544() {
        }

        public boolean onItemClick(View view, int n) {
            if (ChatDialogsView.this.delegate != null) {
                try {
                    ChatDialogsView.this.delegate.didLongPressedOnSubDialog(((Long) view.getTag()).longValue(), ChatDialogsView.this.dialogsType);
                } catch (Exception ex) {
                    FileLog.e(ex);
                }
            }
            return true;
        }
    }

    /* renamed from: turbogram.Components.ChatDialogsView$5 */
    class C22555 implements OnClickListener {
        C22555() {
        }

        public void onClick(View view) {
            ChatDialogsView.this.btnPressed();
        }
    }

    /* renamed from: turbogram.Components.ChatDialogsView$6 */
    class C22566 implements OnLongClickListener {
        C22566() {
        }

        public boolean onLongClick(View view) {
            if (!ChatDialogsView.this.visible || ChatDialogsView.this.disableLongCick) {
                return false;
            }
            ChatDialogsView.this.changeDialogType();
            return true;
        }
    }

    public class OnSwipeTouchListener implements OnTouchListener {
        private final GestureDetector gestureDetector;

        private final class GestureListener extends SimpleOnGestureListener {
            private GestureListener() {
            }

            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float n, float n2) {
                ChatDialogsView.this.disableLongCick = true;
                float n3 = motionEvent2.getY() - motionEvent.getY();
                float n4 = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(n4) > Math.abs(n3)) {
                    if (Math.abs(n4) > 10.0f && Math.abs(n) > 10.0f) {
                        if (n4 > 0.0f) {
                            OnSwipeTouchListener.this.onSwipeRight();
                        } else {
                            OnSwipeTouchListener.this.onSwipeLeft();
                        }
                    }
                } else if (Math.abs(n3) > 10.0f && Math.abs(n2) > 10.0f) {
                    if (n3 > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeBottom();
                    } else {
                        OnSwipeTouchListener.this.onSwipeTop();
                    }
                }
                return true;
            }
        }

        public OnSwipeTouchListener(Context context) {
            this.gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeBottom() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public void onSwipeTop() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.gestureDetector.onTouchEvent(motionEvent);
        }
    }

    /* renamed from: turbogram.Components.ChatDialogsView$8 */
    class C22588 extends OnScrollListener {
        C22588() {
        }

        public void onScrolled(RecyclerView recyclerView, int n, int n2) {
            if (ChatDialogsView.this.listView.getAdapter() == ChatDialogsView.this.membersAdapter && ChatDialogsView.this.membersMap != null && ChatDialogsView.this.layoutManager.findLastVisibleItemPosition() > ChatDialogsView.this.loadMoreMembersRow - 5) {
                ChatDialogsView.this.getChannelParticipants(false);
            }
        }
    }

    public class ChatDialogCell extends FrameLayout {
        private ImageView adminImage;
        private AvatarDrawable avatarDrawable = new AvatarDrawable();
        private StaticLayout countLayout;
        private int countWidth;
        private long dialog_id;
        public Paint dialogs_countGrayPaint;
        public Paint dialogs_countPaint;
        private boolean hideCounter;
        private BackupImageView imageView;
        private int lastUnreadCount;
        private TextView nameTextView;
        private RectF rect = new RectF();

        public ChatDialogCell(Context context) {
            super(context);
            this.imageView = new BackupImageView(context);
            this.imageView.setRoundRadius(AndroidUtilities.dp(54.0f));
            addView(this.imageView, LayoutHelper.createFrame(ChatDialogsView.this.avatarSize, (float) ChatDialogsView.this.avatarSize, 49, 0.0f, 5.0f, 0.0f, 0.0f));
            this.nameTextView = new TextView(context);
            this.nameTextView.setTextColor(Theme.getColor(Theme.key_chat_goDownButtonIcon));
            this.nameTextView.setTextSize(1, (float) ChatDialogsView.this.textSize);
            this.nameTextView.setMaxLines(2);
            this.nameTextView.setGravity(49);
            this.nameTextView.setLines(2);
            this.nameTextView.setEllipsize(TruncateAt.END);
            this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            addView(this.nameTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 6.0f, (float) (ChatDialogsView.this.avatarSize + 5), 6.0f, 0.0f));
            Theme.dialogs_countTextPaint.setTextSize((float) AndroidUtilities.dp(11.0f));
            Theme.dialogs_countTextPaint.setColor(Theme.getColor(Theme.key_chats_unreadCounterText));
            this.dialogs_countPaint = new Paint(1);
            this.dialogs_countPaint.setColor(Theme.getColor(Theme.key_chats_unreadCounter));
            this.dialogs_countGrayPaint = new Paint(1);
            this.dialogs_countGrayPaint.setColor(Theme.getColor(Theme.key_chats_unreadCounterMuted));
            this.adminImage = new ImageView(context);
            this.adminImage.setVisibility(8);
            addView(this.adminImage, LayoutHelper.createFrame(16, 16, 53));
        }

        public void checkUnreadCounter(int n) {
            if (n == 0 || (n & 256) != 0 || (n & 2048) != 0) {
                TLRPC$TL_dialog tl_dialog = (TLRPC$TL_dialog) MessagesController.getInstance(ChatDialogsView.this.currentAccount).dialogs_dict.get(this.dialog_id);
                if (tl_dialog == null || tl_dialog.unread_count == 0) {
                    if (this.countLayout != null) {
                        if (n != 0) {
                            invalidate();
                        }
                        this.lastUnreadCount = 0;
                        this.countLayout = null;
                    }
                } else if (this.lastUnreadCount != tl_dialog.unread_count) {
                    this.lastUnreadCount = tl_dialog.unread_count;
                    String countString = String.format("%d", new Object[]{Integer.valueOf(this.lastUnreadCount)});
                    if (this.lastUnreadCount > 99) {
                        countString = "+99";
                    }
                    this.countWidth = Math.max(AndroidUtilities.dp(12.0f), (int) Math.ceil((double) Theme.dialogs_countTextPaint.measureText(countString)));
                    this.countLayout = new StaticLayout(countString, Theme.dialogs_countTextPaint, this.countWidth, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
                    if (n != 0) {
                        invalidate();
                    }
                }
            }
        }

        protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
            boolean result = super.drawChild(canvas, child, drawingTime);
            if (child == this.imageView && this.countLayout != null) {
                int top = AndroidUtilities.dp(2.0f);
                int left = AndroidUtilities.dp(3.0f);
                this.rect.set((float) AndroidUtilities.dp(3.0f), (float) (AndroidUtilities.dp(2.0f) + top), (float) ((this.countWidth + left) + AndroidUtilities.dp(5.0f)), (float) (AndroidUtilities.dp(19.0f) + top));
                canvas.drawRoundRect(this.rect, 11.5f * AndroidUtilities.density, 11.5f * AndroidUtilities.density, MessagesController.getInstance(ChatDialogsView.this.currentAccount).isDialogMuted(this.dialog_id) ? this.dialogs_countGrayPaint : this.dialogs_countPaint);
                canvas.save();
                canvas.translate((float) (AndroidUtilities.dp(2.3f) + left), (float) (AndroidUtilities.dp(4.0f) + top));
                this.countLayout.draw(canvas);
                canvas.restore();
            }
            return result;
        }

        public long getDialogId() {
            return this.dialog_id;
        }

        public void hideCounter(boolean hCounter) {
            this.hideCounter = hCounter;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (VERSION.SDK_INT >= 21 && getBackground() != null && (motionEvent.getAction() == 0 || motionEvent.getAction() == 2)) {
                getBackground().setHotspot(motionEvent.getX(), motionEvent.getY());
            }
            return super.onTouchEvent(motionEvent);
        }

        public void setDialog(long did) {
            TLRPC$Chat chat2;
            this.dialog_id = did;
            TLObject tlObject = null;
            int n = (int) this.dialog_id;
            int n2 = (int) (this.dialog_id >> 32);
            User info = null;
            if (n == 0) {
                TLRPC$EncryptedChat encryptedChat = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getEncryptedChat(Integer.valueOf(n2));
                chat2 = null;
                if (encryptedChat != null) {
                    info = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getUser(Integer.valueOf(encryptedChat.user_id));
                    chat2 = null;
                }
            } else if (n > 0) {
                info = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getUser(Integer.valueOf(n));
                chat2 = null;
            } else {
                chat2 = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getChat(Integer.valueOf(-n));
            }
            if (info != null) {
                this.nameTextView.setText(ContactsController.formatName(info.first_name, info.last_name));
                this.avatarDrawable.setInfo(info);
                if (info.photo != null) {
                    tlObject = info.photo.photo_small;
                }
            } else if (chat2 != null) {
                this.nameTextView.setText(chat2.title);
                this.avatarDrawable.setInfo(chat2);
                if (chat2.photo != null) {
                    tlObject = chat2.photo.photo_small;
                }
            } else {
                this.nameTextView.setText("");
            }
            this.imageView.setImage(tlObject, "50_50", this.avatarDrawable);
            if (!this.hideCounter) {
                checkUnreadCounter(0);
            }
        }

        public void setIsAdmin(int n) {
            if (this.adminImage != null) {
                int visibility;
                if (n != 0) {
                    visibility = 0;
                } else {
                    visibility = 8;
                }
                this.adminImage.setVisibility(visibility);
                if (n == 1) {
                    this.adminImage.setImageResource(R.drawable.admin_star);
                    this.adminImage.setColorFilter(Theme.getColor(Theme.key_profile_creatorIcon), Mode.SRC_IN);
                } else if (n == 2) {
                    this.adminImage.setImageResource(R.drawable.admin_star);
                    this.adminImage.setColorFilter(Theme.getColor(Theme.key_profile_adminIcon), Mode.SRC_IN);
                }
            }
        }
    }

    public class ChatDialogsAdapter extends Adapter {
        private int ChatDialogRow;
        private int InvisibleRow;
        private int NoChatsRow;
        private long chatId;
        private Context mContext;

        private class Holder extends ViewHolder {
            public Holder(View view) {
                super(view);
            }
        }

        private ChatDialogsAdapter(Context context, long cid) {
            this.InvisibleRow = 0;
            this.NoChatsRow = 1;
            this.ChatDialogRow = 2;
            this.mContext = context;
            this.chatId = cid;
        }

        private ArrayList<TLRPC$TL_dialog> getDialogsArray() {
            ArrayList<TLRPC$TL_dialog> dialogsArray = new ArrayList();
            dialogsArray.addAll(new DialogsLoader(0).getBarDialogsArray(ChatDialogsView.this.dialogsType));
            if (dialogsArray.size() <= 0) {
                changeType();
            }
            return dialogsArray;
        }

        private void changeType() {
            switch (ChatDialogsView.this.dialogsType) {
                case 0:
                    ChatDialogsView.this.dialogsType = 8;
                    return;
                case 3:
                    ChatDialogsView.this.dialogsType = 4;
                    return;
                case 4:
                    ChatDialogsView.this.dialogsType = 7;
                    return;
                case 5:
                    ChatDialogsView.this.dialogsType = 6;
                    return;
                case 7:
                    ChatDialogsView.this.dialogsType = 5;
                    return;
                case 8:
                    ChatDialogsView.this.dialogsType = 3;
                    return;
                default:
                    ChatDialogsView.this.dialogsType = 0;
                    return;
            }
        }

        private int getTitleRes() {
            switch (ChatDialogsView.this.dialogsType) {
                case 3:
                    return R.string.ContactTab;
                case 4:
                    return R.string.GroupsTab;
                case 5:
                    return R.string.ChannelTab;
                case 6:
                    return R.string.RobotTab;
                case 7:
                    return R.string.SuperGroupsTab;
                case 8:
                    return R.string.FavoriteTab;
                default:
                    return R.string.AllTab;
            }
        }

        public int getItemCount() {
            return getDialogsArray().size();
        }

        public long getItemId(int n) {
            ArrayList<TLRPC$TL_dialog> dialogsArray = getDialogsArray();
            if (n < 0 || n >= dialogsArray.size()) {
                return 0;
            }
            return ((TLRPC$TL_dialog) dialogsArray.get(n)).id;
        }

        public int getItemViewType(int n) {
            if (this.chatId != getItemId(n)) {
                return this.ChatDialogRow;
            }
            if (ChatDialogsView.this.dialogsType == 0 || getItemCount() > 1) {
                return this.InvisibleRow;
            }
            return this.NoChatsRow;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            if (viewHolder.getItemViewType() == this.ChatDialogRow) {
                long itemId = getItemId(position);
                ChatDialogCell chatDialogCell = viewHolder.itemView;
                chatDialogCell.setTag(Long.valueOf(itemId));
                chatDialogCell.setDialog(itemId);
            } else if (viewHolder.getItemViewType() == this.NoChatsRow) {
                TextView textView = viewHolder.itemView;
                textView.setGravity(17);
                textView.setText(LocaleController.formatString("NoChatsYet", 2131166958, new Object[]{Integer.valueOf(getTitleRes())}));
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int dp) {
            int dp2 = -1;
            Object o = null;
            if (dp == this.ChatDialogRow) {
                o = new ChatDialogCell(this.mContext);
                ((View) o).setLayoutParams(new LayoutParams(AndroidUtilities.dp((float) ChatDialogsView.this.listWidth), AndroidUtilities.dp((float) ChatDialogsView.this.listHeight)));
            } else if (dp == this.NoChatsRow) {
                o = new TextView(this.mContext);
                if (ChatDialogsView.this.vertical) {
                    dp = AndroidUtilities.dp((float) ChatDialogsView.this.listWidth);
                } else {
                    dp = -1;
                }
                if (!ChatDialogsView.this.vertical) {
                    dp2 = AndroidUtilities.dp((float) ChatDialogsView.this.listHeight);
                }
                ((View) o).setLayoutParams(new LayoutParams(dp, dp2));
            } else if (dp == this.InvisibleRow) {
                o = new View(this.mContext);
                ((View) o).setLayoutParams(new LayoutParams(0, 0));
                ((View) o).setVisibility(8);
            }
            return new Holder((View) o);
        }
    }

    private class ListAdapter extends Adapter {
        private Context mContext;

        private class Holder extends ViewHolder {
            public Holder(View view) {
                super(view);
            }
        }

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        private int getTitleRes() {
            return R.string.ChannelMembers;
        }

        public int getItemCount() {
            if (!ChatDialogsView.this.currentChat.megagroup) {
                return ChatDialogsView.this.sortedUsers.size();
            }
            if (ChatDialogsView.this.info == null || ChatDialogsView.this.info.participants == null || ChatDialogsView.this.info.participants.participants.isEmpty()) {
                return 0;
            }
            return ChatDialogsView.this.info.participants.participants.size();
        }

        public int getItemViewType(int n) {
            return 0;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            TLRPC$ChatParticipant chatParticipant;
            if (ChatDialogsView.this.sortedUsers.isEmpty()) {
                chatParticipant = (TLRPC$ChatParticipant) ChatDialogsView.this.info.participants.participants.get(position);
            } else {
                chatParticipant = (TLRPC$ChatParticipant) ChatDialogsView.this.info.participants.participants.get(((Integer) ChatDialogsView.this.sortedUsers.get(position)).intValue());
            }
            if (chatParticipant != null) {
                ChatDialogCell chatDialogCell = viewHolder.itemView;
                if (chatParticipant instanceof TLRPC$TL_chatChannelParticipant) {
                    TLRPC$ChannelParticipant channelParticipant = ((TLRPC$TL_chatChannelParticipant) chatParticipant).channelParticipant;
                    if (channelParticipant instanceof TLRPC$TL_channelParticipantCreator) {
                        chatDialogCell.setIsAdmin(1);
                    } else if ((channelParticipant instanceof TLRPC$TL_channelParticipantEditor_layer67) || (channelParticipant instanceof TLRPC$TL_channelParticipantModerator_layer67)) {
                        chatDialogCell.setIsAdmin(2);
                    } else {
                        chatDialogCell.setIsAdmin(0);
                    }
                } else if (chatParticipant instanceof TLRPC$TL_chatParticipantCreator) {
                    chatDialogCell.setIsAdmin(1);
                } else if (ChatDialogsView.this.currentChat.admins_enabled && (chatParticipant instanceof TLRPC$TL_chatParticipantAdmin)) {
                    chatDialogCell.setIsAdmin(2);
                } else {
                    chatDialogCell.setIsAdmin(0);
                }
                long dialog = (long) chatParticipant.user_id;
                chatDialogCell.setTag(Long.valueOf(dialog));
                chatDialogCell.hideCounter(true);
                chatDialogCell.setDialog(dialog);
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ChatDialogCell chatDialogCell = new ChatDialogCell(this.mContext);
            chatDialogCell.setLayoutParams(new LayoutParams(AndroidUtilities.dp((float) ChatDialogsView.this.listWidth), AndroidUtilities.dp((float) ChatDialogsView.this.listHeight)));
            return new Holder(chatDialogCell);
        }
    }

    public ChatDialogsView(Context context, BaseFragment baseFragment, long did, int currentAccount) {
        Adapter adapter;
        float bottomMargin;
        super(context);
        this.currentAccount = currentAccount;
        this.textSize = 10;
        this.avatarSize = 40;
        this.listWidth = this.avatarSize + 20;
        this.listHeight = (this.avatarSize + this.textSize) + 25;
        this.parentFragment = (ChatActivity) baseFragment;
        this.vertical = TurboConfig$Chatbar.vertical;
        this.visible = false;
        this.refresh = false;
        this.dialogsType = TurboConfig$Chatbar.defaul;
        this.centerButton = TurboConfig$Chatbar.centerButton;
        if (this.vertical) {
            setTranslationX((float) AndroidUtilities.dp((float) this.listWidth));
        } else {
            setTranslationY((float) (-AndroidUtilities.dp((float) this.listHeight)));
        }
        ((ViewGroup) baseFragment.getFragmentView()).setClipToPadding(false);
        setBackgroundColor(0);
        if (did < 0 && TurboConfig$Chatbar.showMembers) {
            this.currentChat = this.parentFragment.getCurrentChat();
            if (this.currentChat != null && (!ChatObject.isChannel(this.currentChat) || this.currentChat.megagroup)) {
                this.showMembers = true;
                this.sortedUsers = new ArrayList();
                if (this.currentChat.megagroup) {
                    this.loadMoreMembersRow = 32;
                    this.membersMap = new ArrayList();
                    this.classGuid = this.parentFragment.getCurrentClassGuid();
                    this.chat_id = -((int) did);
                    getChannelParticipants(true);
                } else {
                    this.membersMap = null;
                }
                updateOnlineCount();
            }
        }
        if (!this.showMembers && this.dialogsType == -1) {
            this.dialogsType = 0;
        }
        this.listView = new RecyclerListView(context) {
            public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
                if (!(getParent() == null || getParent().getParent() == null)) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(true);
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        };
        this.listView.setTag(Integer.valueOf(9));
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_chat_goDownButton));
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        this.layoutManager = new LinearLayoutManager(context) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
        this.layoutManager.setOrientation(this.vertical ? 1 : 0);
        this.listView.setLayoutManager(this.layoutManager);
        this.dialogsAdapter = new ChatDialogsAdapter(context, did);
        this.membersAdapter = new ListAdapter(context);
        if (!this.showMembers || this.dialogsType >= 0) {
            adapter = this.dialogsAdapter;
        } else {
            adapter = this.membersAdapter;
        }
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(new C22533());
        this.listView.setOnItemLongClickListener(new C22544());
        addView(this.listView, LayoutHelper.createFrame(-1, -1, 5));
        Drawable drawable = context.getResources().getDrawable(this.vertical ? R.drawable.ic_bar_bg_v : R.drawable.ic_bar_bg);
        drawable.setColorFilter(Theme.getColor(Theme.key_chat_goDownButton), Mode.MULTIPLY);
        this.btn = new ImageView(context);
        this.btn.setColorFilter(Theme.getColor(Theme.key_chat_goDownButtonIcon), Mode.SRC_IN);
        this.btn.setImageResource(this.vertical ? R.drawable.ic_bar_open : R.drawable.ic_bar_open_h);
        this.btn.setScaleType(ScaleType.CENTER);
        this.btn.setBackgroundDrawable(drawable);
        int gravity = this.vertical ? this.centerButton ? 16 : 80 : this.centerButton ? 1 : 5;
        float topMargin = this.vertical ? 0.0f : (float) this.listHeight;
        float rightMargin = this.vertical ? (float) this.listWidth : 0.0f;
        if (this.vertical) {
            int i;
            if (this.centerButton) {
                i = 0;
            } else {
                i = this.listWidth;
            }
            bottomMargin = (float) i;
        } else {
            bottomMargin = 0.0f;
        }
        addView(this.btn, LayoutHelper.createFrame(-2, -2.0f, gravity, 0.0f, topMargin, rightMargin, bottomMargin));
        this.btn.setOnClickListener(new C22555());
        this.btn.setOnLongClickListener(new C22566());
        this.btn.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeBottom() {
                if (!ChatDialogsView.this.visible && !ChatDialogsView.this.vertical) {
                    ChatDialogsView.this.btnPressed();
                }
            }

            public void onSwipeLeft() {
                if (!ChatDialogsView.this.visible && ChatDialogsView.this.vertical) {
                    ChatDialogsView.this.btnPressed();
                }
            }

            public void onSwipeRight() {
                if (ChatDialogsView.this.visible && ChatDialogsView.this.vertical) {
                    ChatDialogsView.this.btnPressed();
                }
            }

            public void onSwipeTop() {
                if (ChatDialogsView.this.visible && !ChatDialogsView.this.vertical) {
                    ChatDialogsView.this.btnPressed();
                }
            }
        });
        this.tv = new TextView(context);
        this.tv.setTextColor(Theme.getColor(Theme.key_chat_goDownButtonIcon));
        this.tv.setTextSize(1, 9.0f);
        this.tv.setBackgroundColor(Theme.getColor(Theme.key_chat_goDownButton));
        this.tv.setVisibility(4);
        addView(this.tv, LayoutHelper.createFrame(-2, -2.0f, 49, this.vertical ? (float) AndroidUtilities.dp(4.0f) : 0.0f, 0.0f, 0.0f, 0.0f));
        if (this.showMembers) {
            this.listView.setOnScrollListener(new C22588());
        }
    }

    private void fetchUsersFromChannelInfo() {
        if ((this.info instanceof TLRPC$TL_channelFull) && this.info.participants != null) {
            for (int i = 0; i < this.info.participants.participants.size(); i++) {
                TLRPC$ChatParticipant chatParticipant = (TLRPC$ChatParticipant) this.info.participants.participants.get(i);
                if (((TLRPC$TL_chatChannelParticipant) chatParticipant).channelParticipant instanceof TLRPC$TL_channelParticipantCreator) {
                    this.creatorID = chatParticipant.user_id;
                }
            }
        }
    }

    private void getChannelParticipants(boolean b) {
        int size = 0;
        if (!this.loadingUsers && this.membersMap != null && this.info != null) {
            int n;
            this.loadingUsers = true;
            if (this.membersMap.isEmpty() || !b) {
                n = 0;
            } else {
                n = 300;
            }
            final TLRPC$TL_channels_getParticipants tl_channels_getParticipants = new TLRPC$TL_channels_getParticipants();
            tl_channels_getParticipants.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chat_id);
            tl_channels_getParticipants.filter = new TLRPC$TL_channelParticipantsRecent();
            if (!b) {
                size = this.membersMap.size();
            }
            tl_channels_getParticipants.offset = size;
            tl_channels_getParticipants.limit = Callback.DEFAULT_DRAG_ANIMATION_DURATION;
            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(tl_channels_getParticipants, new RequestDelegate() {
                public void run(final TLObject tlObject, final TLRPC$TL_error tl_error) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            if (tl_error == null) {
                                TLRPC$TL_channels_channelParticipants tl_channels_channelParticipants = tlObject;
                                MessagesController.getInstance(ChatDialogsView.this.currentAccount).putUsers(tl_channels_channelParticipants.users, false);
                                if (tl_channels_getParticipants.offset == 0) {
                                    ChatDialogsView.this.membersMap.clear();
                                    ChatDialogsView.this.info.participants = new TLRPC$TL_chatParticipants();
                                    MessagesStorage.getInstance(ChatDialogsView.this.currentAccount).putUsersAndChats(tl_channels_channelParticipants.users, null, true, true);
                                    MessagesStorage.getInstance(ChatDialogsView.this.currentAccount).updateChannelUsers(ChatDialogsView.this.chat_id, tl_channels_channelParticipants.participants);
                                }
                                for (int i = 0; i < tl_channels_channelParticipants.participants.size(); i++) {
                                    TLRPC$TL_chatChannelParticipant tl_chatChannelParticipant = new TLRPC$TL_chatChannelParticipant();
                                    tl_chatChannelParticipant.channelParticipant = (TLRPC$ChannelParticipant) tl_channels_channelParticipants.participants.get(i);
                                    tl_chatChannelParticipant.inviter_id = tl_chatChannelParticipant.channelParticipant.inviter_id;
                                    tl_chatChannelParticipant.user_id = tl_chatChannelParticipant.channelParticipant.user_id;
                                    tl_chatChannelParticipant.date = tl_chatChannelParticipant.channelParticipant.date;
                                    if (!ChatDialogsView.this.membersMap.contains(Integer.valueOf(tl_chatChannelParticipant.user_id))) {
                                        ChatDialogsView.this.info.participants.participants.add(tl_chatChannelParticipant);
                                        ChatDialogsView.this.membersMap.add(Integer.valueOf(tl_chatChannelParticipant.user_id));
                                    }
                                }
                            }
                            ChatDialogsView.this.updateOnlineCount();
                            ChatDialogsView.this.loadingUsers = false;
                            if (!(ChatDialogsView.this.info == null || ChatDialogsView.this.info.participants == null || ChatDialogsView.this.info.participants.participants.isEmpty() || ChatDialogsView.this.membersMap.size() <= ChatDialogsView.this.loadMoreMembersRow)) {
                                ChatDialogsView.this.loadMoreMembersRow = ChatDialogsView.this.info.participants.participants.size();
                            }
                            if (ChatDialogsView.this.listView.getAdapter() != null) {
                                ChatDialogsView.this.listView.getAdapter().notifyDataSetChanged();
                            }
                        }
                    }, (long) n);
                }
            }), this.classGuid);
        }
    }

    private void updateOnlineCount() {
        int currentTime = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        this.sortedUsers.clear();
        if ((this.info instanceof TLRPC$TL_chatFull) || ((this.info instanceof TLRPC$TL_channelFull) && this.info.participants_count <= Callback.DEFAULT_DRAG_ANIMATION_DURATION && this.info.participants != null)) {
            for (int i = 0; i < this.info.participants.participants.size(); i++) {
                TLRPC$ChatParticipant chatParticipant = (TLRPC$ChatParticipant) this.info.participants.participants.get(i);
                User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(chatParticipant.user_id));
                if (user == null || user.status == null || ((user.status.expires <= currentTime && user.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) || user.status.expires <= 10000)) {
                    this.sortedUsers.add(Integer.valueOf(i));
                } else {
                    this.sortedUsers.add(Integer.valueOf(i));
                }
                if (chatParticipant instanceof TLRPC$TL_chatParticipantCreator) {
                    this.creatorID = chatParticipant.user_id;
                }
            }
            while (true) {
                try {
                    break;
                } catch (Exception ex) {
                    FileLog.e(ex);
                }
            }
            Collections.sort(this.sortedUsers, new Comparator<Integer>() {
                public int compare(Integer n, Integer n2) {
                    User user = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getUser(Integer.valueOf(((TLRPC$ChatParticipant) ChatDialogsView.this.info.participants.participants.get(n2.intValue())).user_id));
                    User user2 = MessagesController.getInstance(ChatDialogsView.this.currentAccount).getUser(Integer.valueOf(((TLRPC$ChatParticipant) ChatDialogsView.this.info.participants.participants.get(n.intValue())).user_id));
                    int expires = 0;
                    if (user != null) {
                        expires = 0;
                        if (user.status != null) {
                            if (user.id == UserConfig.getInstance(ChatDialogsView.this.currentAccount).getClientUserId()) {
                                expires = ConnectionsManager.getInstance(ChatDialogsView.this.currentAccount).getCurrentTime() + DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
                            } else {
                                expires = user.status.expires;
                            }
                            if (user.id == ChatDialogsView.this.creatorID) {
                                expires = (ConnectionsManager.getInstance(ChatDialogsView.this.currentAccount).getCurrentTime() + DefaultLoadControl.DEFAULT_MAX_BUFFER_MS) - 100;
                            }
                        }
                    }
                    int expires2 = 0;
                    if (user2 != null) {
                        expires2 = 0;
                        if (user2.status != null) {
                            if (user2.id == UserConfig.getInstance(ChatDialogsView.this.currentAccount).getClientUserId()) {
                                expires2 = ConnectionsManager.getInstance(ChatDialogsView.this.currentAccount).getCurrentTime() + DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
                            } else {
                                expires2 = user2.status.expires;
                            }
                            if (user2.id == ChatDialogsView.this.creatorID) {
                                expires2 = (ConnectionsManager.getInstance(ChatDialogsView.this.currentAccount).getCurrentTime() + DefaultLoadControl.DEFAULT_MAX_BUFFER_MS) - 100;
                            }
                        }
                    }
                    if (expires <= 0 || expires2 <= 0) {
                        if (expires >= 0 || expires2 >= 0) {
                            if ((expires < 0 && expires2 > 0) || (expires == 0 && expires2 != 0)) {
                                return -1;
                            }
                            if ((expires2 >= 0 || expires <= 0) && (expires2 != 0 || expires == 0)) {
                                return 0;
                            }
                            return 1;
                        } else if (expires > expires2) {
                            return 1;
                        } else {
                            if (expires < expires2) {
                                return -1;
                            }
                            return 0;
                        }
                    } else if (expires > expires2) {
                        return 1;
                    } else {
                        if (expires < expires2) {
                            return -1;
                        }
                        return 0;
                    }
                }
            });
            if (this.listView.getAdapter() != null) {
                this.listView.getAdapter().notifyItemRangeChanged(0, this.sortedUsers.size());
            }
        }
    }

    public void btnPressed() {
        if (this.delegate != null) {
            this.delegate.didPressedOnBtn(this.visible);
        }
        if (!this.visible) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    ChatDialogsView.this.disableLongCick = false;
                }
            }, 500);
        }
        this.visible = !this.visible;
    }

    public void changeDialogType() {
        switch (this.dialogsType) {
            case -1:
                this.dialogsType = 0;
                if (this.showMembers && this.listView.getAdapter() != this.dialogsAdapter) {
                    this.listView.setAdapter(this.dialogsAdapter);
                    break;
                }
            case 0:
                this.dialogsType = 8;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            case 3:
                this.dialogsType = 4;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            case 4:
                this.dialogsType = 7;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            case 5:
                this.dialogsType = 6;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            case 6:
                if (!this.showMembers) {
                    this.dialogsType = 0;
                    if (this.listView.getAdapter() != this.dialogsAdapter) {
                        this.listView.setAdapter(this.dialogsAdapter);
                        break;
                    }
                }
                this.dialogsType = -1;
                if (this.listView.getAdapter() != this.membersAdapter) {
                    this.listView.setAdapter(this.membersAdapter);
                    break;
                }
                break;
            case 7:
                this.dialogsType = 5;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            case 8:
                this.dialogsType = 3;
                if (new DialogsLoader(0).getBarDialogsArray(this.dialogsType).size() == 0) {
                    changeDialogType();
                    break;
                }
                break;
            default:
                this.dialogsType = 0;
                break;
        }
        int text = R.drawable.ic_visibility_off;
        int n = R.drawable.ic_visibility_off;
        if (this.listView != null) {
            if (this.listView.getAdapter() != null) {
                this.listView.getAdapter().notifyDataSetChanged();
                if (this.listView.getAdapter() instanceof ChatDialogsAdapter) {
                    n = ((ChatDialogsAdapter) this.listView.getAdapter()).getTitleRes();
                } else {
                    n = ((ListAdapter) this.listView.getAdapter()).getTitleRes();
                }
            }
            this.listView.scrollToPosition(0);
            text = n;
        }
        this.tv.setText(text);
        this.tv.setVisibility(0);
        this.tv.setTypeface(TurboUtils.getTurboTypeFace());
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(1000);
        this.tv.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ChatDialogsView.this.tv.setVisibility(4);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    public int getListHeight() {
        return this.listHeight;
    }

    public int getListWidth() {
        return this.listWidth;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void needRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public void onDestroy() {
        this.delegate = null;
        this.dialogsAdapter = null;
        this.membersAdapter = null;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.refresh) {
            if (this.listView.getAdapter() != null) {
                this.listView.getAdapter().notifyDataSetChanged();
            }
            this.refresh = false;
        }
    }

    public void refreshList() {
        if (this.listView.getAdapter() != null) {
            this.listView.getAdapter().notifyDataSetChanged();
        }
    }

    public void setBtnResId(int imageResource) {
        this.btn.setImageResource(imageResource);
    }

    public void setChatInfo(TLRPC$ChatFull info) {
        if (this.showMembers) {
            this.info = info;
            if (this.currentChat == null) {
                this.currentChat = this.parentFragment.getCurrentChat();
            }
            if (this.currentChat.megagroup) {
                this.membersCount = info.participants_count;
                fetchUsersFromChannelInfo();
            } else {
                this.membersCount = info.participants.participants.size();
            }
            if (this.membersCount <= 1) {
                this.showMembers = false;
                if (this.listView.getAdapter() != this.dialogsAdapter) {
                    this.listView.setAdapter(this.dialogsAdapter);
                    this.dialogsAdapter.notifyDataSetChanged();
                }
            }
            updateOnlineCount();
        }
    }

    public void setDelegate(ChatDialogsViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
