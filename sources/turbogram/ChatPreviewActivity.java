package turbogram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.text.style.CharacterStyle;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessageObject.GroupedMessagePosition;
import org.telegram.messenger.MessageObject.GroupedMessages;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.widget.GridLayoutManager.SpanSizeLookup;
import org.telegram.messenger.support.widget.GridLayoutManagerFixed;
import org.telegram.messenger.support.widget.LinearSmoothScrollerMiddle;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.State;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$BotInfo;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$ChatParticipant;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$TL_channelFull;
import org.telegram.tgnet.TLRPC$TL_chatFull;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_message;
import org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_replyInlineMarkup;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BotHelpCell;
import org.telegram.ui.Cells.BotHelpCell.BotHelpCellDelegate;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate;
import org.telegram.ui.Cells.ChatLoadingCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate;
import org.telegram.ui.Cells.ChatUnreadCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StatusDrawable;
import turbogram.Utilities.TurboConfig;
import turbogram.Utilities.TurboUtils;

public class ChatPreviewActivity extends Activity implements OnClickListener, NotificationCenter$NotificationCenterDelegate {
    private ActionBar actionBar;
    private ArrayList<MessageObject> animatingMessageObjects = new ArrayList();
    private boolean animationInProgress = false;
    private long animationStartTime = 0;
    private FrameLayout avatarContainer;
    private BackupImageView avatarImageView;
    private ImageView backgroundImage;
    private SparseArray<TLRPC$BotInfo> botInfo = new SparseArray();
    private String botUser;
    private ImageView btn1;
    private ImageView btn2;
    private ImageView btn3;
    private ImageView btn4;
    private ImageView btn5;
    private ImageView btn6;
    private LinearLayout buttomMenuContainer;
    private boolean[] cacheEndReached = new boolean[2];
    private int cantDeleteMessagesCount;
    private MessagesAdapter chatAdapter;
    private GridLayoutManagerFixed chatLayoutManager;
    private RecyclerListView chatListView;
    private ArrayList<ChatMessageCell> chatMessageCellsCache = new ArrayList();
    private boolean checkTextureViewPosition;
    private int classGuid;
    private SizeNotifierFrameLayout contentView;
    private int createUnreadMessageAfterId;
    private boolean createUnreadMessageAfterIdLoading;
    private int currentAccount = UserConfig.selectedAccount;
    private TLRPC$Chat currentChat;
    protected TLRPC$EncryptedChat currentEncryptedChat;
    private boolean currentFloatingDateOnScreen;
    private boolean currentFloatingTopIsNotMessage;
    private User currentUser;
    private TLRPC$TL_dialog dialog;
    private long dialog_id;
    private TextView emptyView;
    private FrameLayout emptyViewContainer;
    private boolean[] endReached = new boolean[2];
    private boolean first = true;
    private boolean firstLoading = true;
    private boolean firstUnreadSent = false;
    private int first_unread_id;
    private AnimatorSet floatingDateAnimation;
    private ChatActionCell floatingDateView;
    private boolean forceScrollToTop;
    private boolean[] forwardEndReached = new boolean[]{true, true};
    private LongSparseArray<GroupedMessages> groupedMessagesMap = new LongSparseArray();
    private boolean hasAllMentionsLocal;
    private int highlightMessageId = Integer.MAX_VALUE;
    protected TLRPC$ChatFull info;
    private InstantCameraView instantCameraView;
    private boolean isBroadcast;
    private int lastLoadIndex;
    private CharSequence lastPrintString;
    private int last_message_id = 0;
    private boolean loading;
    private boolean loadingForward;
    private boolean loadingFromOldPosition;
    private int loadsCount;
    private int[] maxDate = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
    private int[] maxMessageId = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
    private long mergeDialogId;
    private ViewGroup messageContainer;
    protected ArrayList<MessageObject> messages = new ArrayList();
    private HashMap<String, ArrayList<MessageObject>> messagesByDays = new HashMap();
    private SparseArray<MessageObject>[] messagesDict = new SparseArray[]{new SparseArray(), new SparseArray()};
    private int[] mid = new int[]{2};
    private int[] minDate = new int[2];
    private int[] minMessageId = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
    private TextView nameTextView;
    private boolean needSelectFromMessageId;
    private int newMentionsCount;
    private int newUnreadMessageCount;
    private Runnable onAnimationEndRunnable = null;
    private TextView onlineTextView;
    private boolean openAnimationEnded;
    private FrameLayout pagedownButton;
    private ObjectAnimator pagedownButtonAnimation;
    private ImageView pagedownButtonImage;
    private boolean pagedownButtonShowedByScroll;
    private boolean paused = true;
    private RelativeLayout popupContainer;
    private int prevSetUnreadCount = Integer.MIN_VALUE;
    private RadialProgressView progressBar;
    private FrameLayout progressView;
    private View progressView2;
    private int returnToMessageId;
    private FrameLayout roundVideoContainer;
    private MessageObject scrollToMessage;
    private int scrollToMessagePosition = -10000;
    private int scrollToOffsetOnRecreate = 0;
    private int scrollToPositionOnRecreate = -1;
    private boolean scrollToTopOnResume;
    private boolean scrollToTopUnReadOnResume;
    private boolean scrollingFloatingDate;
    private SparseArray<MessageObject>[] selectedMessagesCanCopyIds = new SparseArray[]{new SparseArray(), new SparseArray()};
    private SparseArray<MessageObject>[] selectedMessagesCanStarIds = new SparseArray[]{new SparseArray(), new SparseArray()};
    private SparseArray<MessageObject>[] selectedMessagesIds = new SparseArray[]{new SparseArray(), new SparseArray()};
    private int startLoadFromMessageId;
    private int startLoadFromMessageOffset = Integer.MAX_VALUE;
    private StatusDrawable[] statusDrawables = new StatusDrawable[5];
    private MessageObject unreadMessageObject;
    private ArrayList<Integer> waitingForLoad = new ArrayList();
    private boolean waitingForReplyMessageLoad;
    private boolean wasPaused = false;

    /* renamed from: turbogram.ChatPreviewActivity$4 */
    class C22264 implements OnTouchListener {
        C22264() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: turbogram.ChatPreviewActivity$5 */
    class C22275 extends ActionBarMenuOnItemClick {
        C22275() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ChatPreviewActivity.this.onFinish();
                ChatPreviewActivity.this.finish();
            }
        }
    }

    /* renamed from: turbogram.ChatPreviewActivity$6 */
    class C22286 implements OnTouchListener {
        C22286() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: turbogram.ChatPreviewActivity$9 */
    class C22319 extends SpanSizeLookup {
        C22319() {
        }

        public int getSpanSize(int position) {
            if (position >= ChatPreviewActivity.this.chatAdapter.messagesStartRow && position < ChatPreviewActivity.this.chatAdapter.messagesEndRow) {
                int idx = position - ChatPreviewActivity.this.chatAdapter.messagesStartRow;
                if (idx >= 0 && idx < ChatPreviewActivity.this.messages.size()) {
                    MessageObject message = (MessageObject) ChatPreviewActivity.this.messages.get(idx);
                    GroupedMessages groupedMessages = ChatPreviewActivity.this.getValidGroupedMessage(message);
                    if (groupedMessages != null) {
                        return ((GroupedMessagePosition) groupedMessages.positions.get(message)).spanSize;
                    }
                }
            }
            return 1000;
        }
    }

    public class MessagesAdapter extends Adapter {
        private int botInfoRow = -1;
        private boolean isBot;
        private int loadingDownRow;
        private int loadingUpRow;
        private Context mContext;
        private int messagesEndRow;
        private int messagesStartRow;
        private int rowCount;

        /* renamed from: turbogram.ChatPreviewActivity$MessagesAdapter$1 */
        class C22321 implements ChatMessageCellDelegate {
            C22321() {
            }

            public void didPressedUserAvatar(ChatMessageCell cell, User user) {
            }

            public void didPressedViaBot(ChatMessageCell cell, String username) {
            }

            public void didPressedChannelAvatar(ChatMessageCell cell, TLRPC$Chat chat, int postId) {
            }

            public void didPressedCancelSendButton(ChatMessageCell cell) {
            }

            public void didLongPressed(ChatMessageCell cell) {
            }

            public void didPressedReplyMessage(ChatMessageCell cell, int id) {
            }

            public void didPressedUrl(MessageObject messageObject, CharacterStyle url, boolean longPress) {
            }

            public void needOpenWebView(String url, String title, String description, String originalUrl, int w, int h) {
            }

            public void didPressedImage(ChatMessageCell cell) {
            }

            public void didPressedShare(ChatMessageCell cell) {
            }

            public void didPressedOther(ChatMessageCell cell) {
            }

            public void didPressedBotButton(ChatMessageCell cell, TLRPC$KeyboardButton button) {
            }

            public void didPressedInstantButton(ChatMessageCell cell, int type) {
            }

            public boolean isChatAdminCell(int uid) {
                return false;
            }

            public boolean needPlayMessage(MessageObject messageObject) {
                return false;
            }

            public boolean canPerformActions() {
                return false;
            }

            public void didLongPressedUserAvatar(ChatMessageCell cell, User user) {
            }
        }

        /* renamed from: turbogram.ChatPreviewActivity$MessagesAdapter$2 */
        class C22332 implements ChatActionCellDelegate {
            C22332() {
            }

            public void didClickedImage(ChatActionCell cell) {
            }

            public void didLongPressed(ChatActionCell cell) {
            }

            public void needOpenUserProfile(int uid) {
            }

            public void didPressedBotButton(MessageObject messageObject, TLRPC$KeyboardButton button) {
            }

            public void didPressedReplyMessage(ChatActionCell cell, int id) {
            }
        }

        /* renamed from: turbogram.ChatPreviewActivity$MessagesAdapter$3 */
        class C22343 implements BotHelpCellDelegate {
            C22343() {
            }

            public void didPressUrl(String url) {
                if (url.startsWith("@") || !url.startsWith("#")) {
                }
            }
        }

        public MessagesAdapter(Context context) {
            this.mContext = context;
            boolean z = ChatPreviewActivity.this.currentUser != null && ChatPreviewActivity.this.currentUser.bot;
            this.isBot = z;
        }

        public void updateRows() {
            int i;
            this.rowCount = 0;
            if (ChatPreviewActivity.this.messages.isEmpty()) {
                this.loadingUpRow = -1;
                this.loadingDownRow = -1;
                this.messagesStartRow = -1;
                this.messagesEndRow = -1;
            } else {
                if (ChatPreviewActivity.this.forwardEndReached[0] && (ChatPreviewActivity.this.mergeDialogId == 0 || ChatPreviewActivity.this.forwardEndReached[1])) {
                    this.loadingDownRow = -1;
                } else {
                    i = this.rowCount;
                    this.rowCount = i + 1;
                    this.loadingDownRow = i;
                }
                this.messagesStartRow = this.rowCount;
                this.rowCount += ChatPreviewActivity.this.messages.size();
                this.messagesEndRow = this.rowCount;
                if (ChatPreviewActivity.this.endReached[0] && (ChatPreviewActivity.this.mergeDialogId == 0 || ChatPreviewActivity.this.endReached[1])) {
                    this.loadingUpRow = -1;
                } else {
                    i = this.rowCount;
                    this.rowCount = i + 1;
                    this.loadingUpRow = i;
                }
            }
            if (ChatPreviewActivity.this.currentUser == null || !ChatPreviewActivity.this.currentUser.bot) {
                this.botInfoRow = -1;
                return;
            }
            i = this.rowCount;
            this.rowCount = i + 1;
            this.botInfoRow = i;
        }

        public int getItemCount() {
            return this.rowCount;
        }

        public long getItemId(int i) {
            return -1;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == 0) {
                if (ChatPreviewActivity.this.chatMessageCellsCache.isEmpty()) {
                    view = new ChatMessageCell(this.mContext);
                } else {
                    view = (View) ChatPreviewActivity.this.chatMessageCellsCache.get(0);
                    ChatPreviewActivity.this.chatMessageCellsCache.remove(0);
                }
                ChatMessageCell chatMessageCell = (ChatMessageCell) view;
                chatMessageCell.setDelegate(new C22321());
                if (ChatPreviewActivity.this.currentEncryptedChat == null) {
                    chatMessageCell.setAllowAssistant(true);
                }
            } else if (viewType == 1) {
                view = new ChatActionCell(this.mContext);
                ((ChatActionCell) view).setDelegate(new C22332());
            } else if (viewType == 2) {
                view = new ChatUnreadCell(this.mContext);
            } else if (viewType == 3) {
                view = new BotHelpCell(this.mContext);
                ((BotHelpCell) view).setDelegate(new C22343());
            } else if (viewType == 4) {
                view = new ChatLoadingCell(this.mContext);
            }
            view.setLayoutParams(new LayoutParams(-1, -2));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == this.botInfoRow) {
                String str;
                BotHelpCell helpView = holder.itemView;
                if (ChatPreviewActivity.this.botInfo.size() != 0) {
                    str = ((TLRPC$BotInfo) ChatPreviewActivity.this.botInfo.get(ChatPreviewActivity.this.currentUser.id)).description;
                } else {
                    str = null;
                }
                helpView.setText(str);
                return;
            }
            if (position == this.loadingDownRow || position == this.loadingUpRow) {
                holder.itemView.setProgressVisible(ChatPreviewActivity.this.loadsCount > 1);
                return;
            }
            if (position >= this.messagesStartRow && position < this.messagesEndRow) {
                MessageObject message = (MessageObject) ChatPreviewActivity.this.messages.get(position - this.messagesStartRow);
                View view = holder.itemView;
                if (view instanceof ChatMessageCell) {
                    int prevPosition;
                    int nextPosition;
                    final ChatMessageCell messageCell = (ChatMessageCell) view;
                    boolean z = ChatPreviewActivity.this.currentChat != null || UserObject.isUserSelf(ChatPreviewActivity.this.currentUser);
                    messageCell.isChat = z;
                    boolean pinnedBottom = false;
                    boolean pinnedTop = false;
                    GroupedMessages groupedMessages = ChatPreviewActivity.this.getValidGroupedMessage(message);
                    if (groupedMessages != null) {
                        GroupedMessagePosition pos = (GroupedMessagePosition) groupedMessages.positions.get(message);
                        if (pos != null) {
                            if ((pos.flags & 4) != 0) {
                                prevPosition = (groupedMessages.posArray.indexOf(pos) + position) + 1;
                            } else {
                                pinnedTop = true;
                                prevPosition = -100;
                            }
                            if ((pos.flags & 8) != 0) {
                                nextPosition = (position - groupedMessages.posArray.size()) + groupedMessages.posArray.indexOf(pos);
                            } else {
                                pinnedBottom = true;
                                nextPosition = -100;
                            }
                        } else {
                            prevPosition = -100;
                            nextPosition = -100;
                        }
                    } else {
                        nextPosition = position - 1;
                        prevPosition = position + 1;
                    }
                    int nextType = getItemViewType(nextPosition);
                    int prevType = getItemViewType(prevPosition);
                    if (!(message.messageOwner.reply_markup instanceof TLRPC$TL_replyInlineMarkup) && nextType == holder.getItemViewType()) {
                        MessageObject nextMessage = (MessageObject) ChatPreviewActivity.this.messages.get(nextPosition - this.messagesStartRow);
                        pinnedBottom = nextMessage.isOutOwner() == message.isOutOwner() && Math.abs(nextMessage.messageOwner.date - message.messageOwner.date) <= 300;
                        if (pinnedBottom) {
                            if (ChatPreviewActivity.this.currentChat != null) {
                                pinnedBottom = nextMessage.messageOwner.from_id == message.messageOwner.from_id;
                            } else if (UserObject.isUserSelf(ChatPreviewActivity.this.currentUser)) {
                                pinnedBottom = nextMessage.getFromId() == message.getFromId();
                            }
                        }
                    }
                    if (prevType == holder.getItemViewType()) {
                        MessageObject prevMessage = (MessageObject) ChatPreviewActivity.this.messages.get(prevPosition - this.messagesStartRow);
                        pinnedTop = !(prevMessage.messageOwner.reply_markup instanceof TLRPC$TL_replyInlineMarkup) && prevMessage.isOutOwner() == message.isOutOwner() && Math.abs(prevMessage.messageOwner.date - message.messageOwner.date) <= 300;
                        if (pinnedTop) {
                            if (ChatPreviewActivity.this.currentChat != null) {
                                pinnedTop = prevMessage.messageOwner.from_id == message.messageOwner.from_id;
                            } else if (UserObject.isUserSelf(ChatPreviewActivity.this.currentUser)) {
                                pinnedTop = prevMessage.getFromId() == message.getFromId();
                            }
                        }
                    }
                    messageCell.setMessageObject(message, groupedMessages, pinnedBottom, pinnedTop);
                    if ((view instanceof ChatMessageCell) && DownloadController.getInstance(ChatPreviewActivity.this.currentAccount).canDownloadMedia(message)) {
                        ((ChatMessageCell) view).downloadAudioIfNeed();
                    }
                    z = ChatPreviewActivity.this.highlightMessageId != Integer.MAX_VALUE && message.getId() == ChatPreviewActivity.this.highlightMessageId;
                    messageCell.setHighlighted(z);
                    int index = ChatPreviewActivity.this.animatingMessageObjects.indexOf(message);
                    if (index != -1) {
                        ChatPreviewActivity.this.animatingMessageObjects.remove(index);
                        messageCell.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                            public boolean onPreDraw() {
                                PipRoundVideoView pipRoundVideoView = PipRoundVideoView.getInstance();
                                if (pipRoundVideoView != null) {
                                    pipRoundVideoView.showTemporary(true);
                                }
                                messageCell.getViewTreeObserver().removeOnPreDrawListener(this);
                                ImageReceiver imageReceiver = messageCell.getPhotoImage();
                                float scale = ((float) imageReceiver.getImageWidth()) / ChatPreviewActivity.this.instantCameraView.getCameraRect().width;
                                int[] position = new int[2];
                                messageCell.setAlpha(0.0f);
                                messageCell.getLocationOnScreen(position);
                                position[0] = position[0] + imageReceiver.getImageX();
                                position[1] = position[1] + imageReceiver.getImageY();
                                final View cameraContainer = ChatPreviewActivity.this.instantCameraView.getCameraContainer();
                                cameraContainer.setPivotX(0.0f);
                                cameraContainer.setPivotY(0.0f);
                                AnimatorSet animatorSet = new AnimatorSet();
                                r8 = new Animator[8];
                                r8[0] = ObjectAnimator.ofFloat(ChatPreviewActivity.this.instantCameraView, "alpha", new float[]{0.0f});
                                r8[1] = ObjectAnimator.ofFloat(cameraContainer, "scaleX", new float[]{scale});
                                r8[2] = ObjectAnimator.ofFloat(cameraContainer, "scaleY", new float[]{scale});
                                r8[3] = ObjectAnimator.ofFloat(cameraContainer, "translationX", new float[]{((float) position[0]) - rect.f830x});
                                r8[4] = ObjectAnimator.ofFloat(cameraContainer, "translationY", new float[]{((float) position[1]) - rect.f831y});
                                r8[5] = ObjectAnimator.ofFloat(ChatPreviewActivity.this.instantCameraView.getSwitchButtonView(), "alpha", new float[]{0.0f});
                                r8[6] = ObjectAnimator.ofInt(ChatPreviewActivity.this.instantCameraView.getPaint(), "alpha", new int[]{0});
                                r8[7] = ObjectAnimator.ofFloat(ChatPreviewActivity.this.instantCameraView.getMuteImageView(), "alpha", new float[]{0.0f});
                                animatorSet.playTogether(r8);
                                animatorSet.setDuration(180);
                                animatorSet.setInterpolator(new DecelerateInterpolator());
                                animatorSet.addListener(new AnimatorListenerAdapter() {

                                    /* renamed from: turbogram.ChatPreviewActivity$MessagesAdapter$4$1$1 */
                                    class C22351 extends AnimatorListenerAdapter {
                                        C22351() {
                                        }

                                        public void onAnimationEnd(Animator animation) {
                                            ChatPreviewActivity.this.instantCameraView.hideCamera(true);
                                            ChatPreviewActivity.this.instantCameraView.setVisibility(4);
                                        }
                                    }

                                    public void onAnimationEnd(Animator animation) {
                                        AnimatorSet animatorSet = new AnimatorSet();
                                        r1 = new Animator[2];
                                        r1[0] = ObjectAnimator.ofFloat(cameraContainer, "alpha", new float[]{0.0f});
                                        r1[1] = ObjectAnimator.ofFloat(messageCell, "alpha", new float[]{1.0f});
                                        animatorSet.playTogether(r1);
                                        animatorSet.setDuration(100);
                                        animatorSet.setInterpolator(new DecelerateInterpolator());
                                        animatorSet.addListener(new C22351());
                                        animatorSet.start();
                                    }
                                });
                                animatorSet.start();
                                return true;
                            }
                        });
                    }
                } else if (view instanceof ChatActionCell) {
                    ChatActionCell actionCell = (ChatActionCell) view;
                    actionCell.setMessageObject(message);
                    actionCell.setAlpha(1.0f);
                } else if (view instanceof ChatUnreadCell) {
                    ((ChatUnreadCell) view).setText(LocaleController.getString("UnreadMessages", R.string.UnreadMessages));
                    if (ChatPreviewActivity.this.createUnreadMessageAfterId != 0) {
                        ChatPreviewActivity.this.createUnreadMessageAfterId = 0;
                    }
                }
                if (message != null && message.messageOwner != null && message.messageOwner.media_unread && message.messageOwner.mentioned) {
                    if (!(message.isVoice() || message.isRoundVideo())) {
                        ChatPreviewActivity.this.newMentionsCount = ChatPreviewActivity.this.newMentionsCount - 1;
                        if (ChatPreviewActivity.this.newMentionsCount <= 0) {
                            ChatPreviewActivity.this.newMentionsCount = 0;
                            ChatPreviewActivity.this.hasAllMentionsLocal = true;
                        }
                        MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).markMentionMessageAsRead(message.getId(), ChatObject.isChannel(ChatPreviewActivity.this.currentChat) ? ChatPreviewActivity.this.currentChat.id : 0, ChatPreviewActivity.this.dialog_id);
                        message.setContentIsRead();
                    }
                    if (view instanceof ChatMessageCell) {
                        ((ChatMessageCell) view).setHighlightedAnimated();
                    }
                }
            }
        }

        public int getItemViewType(int position) {
            if (position >= this.messagesStartRow && position < this.messagesEndRow) {
                return ((MessageObject) ChatPreviewActivity.this.messages.get(position - this.messagesStartRow)).contentType;
            }
            if (position == this.botInfoRow) {
                return 3;
            }
            return 4;
        }

        public void onViewAttachedToWindow(ViewHolder holder) {
            if (holder.itemView instanceof ChatMessageCell) {
                boolean z;
                boolean z2;
                final ChatMessageCell messageCell = holder.itemView;
                MessageObject message = messageCell.getMessageObject();
                boolean selected = false;
                boolean disableSelection = false;
                if (ChatPreviewActivity.this.actionBar.isActionModeShowed()) {
                    int idx = message.getDialogId() == ChatPreviewActivity.this.dialog_id ? 0 : 1;
                    if (null == message || ChatPreviewActivity.this.selectedMessagesIds[idx].indexOfKey(message.getId()) >= 0) {
                        selected = true;
                    } else {
                        messageCell.setBackgroundDrawable(null);
                    }
                    disableSelection = true;
                } else {
                    messageCell.setBackgroundDrawable(null);
                }
                if (disableSelection) {
                    z = false;
                } else {
                    z = true;
                }
                if (disableSelection && selected) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                messageCell.setCheckPressed(z, z2);
                messageCell.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        messageCell.getViewTreeObserver().removeOnPreDrawListener(this);
                        int height = ChatPreviewActivity.this.chatListView.getMeasuredHeight();
                        int top = messageCell.getTop();
                        int bottom = messageCell.getBottom();
                        int viewTop = top >= 0 ? 0 : -top;
                        int viewBottom = messageCell.getMeasuredHeight();
                        if (viewBottom > height) {
                            viewBottom = viewTop + height;
                        }
                        messageCell.setVisiblePart(viewTop, viewBottom - viewTop);
                        return true;
                    }
                });
                if (!messageCell.isHighlighted()) {
                    z2 = ChatPreviewActivity.this.highlightMessageId != Integer.MAX_VALUE && messageCell.getMessageObject().getId() == ChatPreviewActivity.this.highlightMessageId;
                    messageCell.setHighlighted(z2);
                }
            }
        }

        public void notifyDataSetChanged() {
            updateRows();
            try {
                super.notifyDataSetChanged();
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemChanged(int position) {
            updateRows();
            try {
                super.notifyItemChanged(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            updateRows();
            try {
                super.notifyItemRangeChanged(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemInserted(int position) {
            updateRows();
            try {
                super.notifyItemInserted(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemMoved(int fromPosition, int toPosition) {
            updateRows();
            try {
                super.notifyItemMoved(fromPosition, toPosition);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            updateRows();
            try {
                super.notifyItemRangeInserted(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRemoved(int position) {
            updateRows();
            try {
                super.notifyItemRemoved(position);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }

        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            updateRows();
            try {
                super.notifyItemRangeRemoved(positionStart, itemCount);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }
    }

    private void loadMessages(Intent intent) {
        CountDownLatch countDownLatch;
        int chatId = intent.getIntExtra("chat_id", 0);
        int userId = intent.getIntExtra("user_id", 0);
        int migrated_to = intent.getIntExtra("migrated_to", 0);
        MessagesStorage messagesStorage;
        final MessagesStorage messagesStorage2;
        final int i;
        final CountDownLatch countDownLatch2;
        if (chatId != 0) {
            this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(chatId));
            if (this.currentChat == null) {
                countDownLatch = new CountDownLatch(1);
                messagesStorage = MessagesStorage.getInstance(this.currentAccount);
                messagesStorage2 = messagesStorage;
                i = chatId;
                countDownLatch2 = countDownLatch;
                messagesStorage.getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatPreviewActivity.this.currentChat = messagesStorage2.getChat(i);
                        countDownLatch2.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (Exception e) {
                    FileLog.e(e);
                }
                if (this.currentChat != null) {
                    MessagesController.getInstance(this.currentAccount).putChat(this.currentChat, true);
                }
            }
            if (chatId > 0) {
                this.dialog_id = (long) (-chatId);
            } else {
                this.isBroadcast = true;
                this.dialog_id = AndroidUtilities.makeBroadcastId(chatId);
            }
            if (ChatObject.isChannel(this.currentChat)) {
                MessagesController.getInstance(this.currentAccount).startShortPoll(chatId, false);
            }
        } else if (userId != 0) {
            this.currentUser = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(userId));
            if (this.currentUser == null) {
                messagesStorage = MessagesStorage.getInstance(this.currentAccount);
                countDownLatch = new CountDownLatch(1);
                messagesStorage2 = messagesStorage;
                i = userId;
                countDownLatch2 = countDownLatch;
                messagesStorage.getStorageQueue().postRunnable(new Runnable() {
                    public void run() {
                        ChatPreviewActivity.this.currentUser = messagesStorage2.getUser(i);
                        countDownLatch2.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                } catch (Exception e2) {
                    FileLog.e(e2);
                }
                if (this.currentUser != null) {
                    MessagesController.getInstance(this.currentAccount).putUser(this.currentUser, true);
                }
            }
            this.dialog_id = (long) userId;
            this.botUser = intent.getStringExtra("botUser");
        }
        this.dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id);
        if (this.currentUser != null) {
            MediaController.getInstance().startMediaObserver();
        }
        if (this.currentEncryptedChat == null && !this.isBroadcast) {
            DataQuery.getInstance(this.currentAccount).loadBotKeyboard(this.dialog_id);
        }
        this.loading = true;
        MessagesController.getInstance(this.currentAccount).loadPeerSettings(this.currentUser, this.currentChat);
        MessagesController.getInstance(this.currentAccount).setLastCreatedDialogId(this.dialog_id, true);
        if (this.startLoadFromMessageId == 0) {
            SharedPreferences sharedPreferences = MessagesController.getNotificationsSettings(this.currentAccount);
            int messageId = sharedPreferences.getInt("diditem" + this.dialog_id, 0);
            if (messageId != 0) {
                this.loadingFromOldPosition = true;
                this.startLoadFromMessageOffset = sharedPreferences.getInt("diditemo" + this.dialog_id, 0);
                this.startLoadFromMessageId = messageId;
            }
        } else {
            this.needSelectFromMessageId = true;
        }
        MessagesController instance;
        long j;
        int i2;
        int i3;
        boolean isChannel;
        int i4;
        if (this.startLoadFromMessageId != 0) {
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            int i5;
            if (migrated_to != 0) {
                this.mergeDialogId = (long) migrated_to;
                instance = MessagesController.getInstance(this.currentAccount);
                j = this.mergeDialogId;
                i2 = this.loadingFromOldPosition ? 50 : AndroidUtilities.isTablet() ? 30 : 20;
                i5 = this.startLoadFromMessageId;
                i3 = this.classGuid;
                isChannel = ChatObject.isChannel(this.currentChat);
                i4 = this.lastLoadIndex;
                this.lastLoadIndex = i4 + 1;
                instance.loadMessages(j, i2, i5, 0, true, 0, i3, 3, 0, isChannel, i4);
            } else {
                instance = MessagesController.getInstance(this.currentAccount);
                j = this.dialog_id;
                i2 = this.loadingFromOldPosition ? 50 : AndroidUtilities.isTablet() ? 30 : 20;
                i5 = this.startLoadFromMessageId;
                i3 = this.classGuid;
                isChannel = ChatObject.isChannel(this.currentChat);
                i4 = this.lastLoadIndex;
                this.lastLoadIndex = i4 + 1;
                instance.loadMessages(j, i2, i5, 0, true, 0, i3, 3, 0, isChannel, i4);
            }
        } else {
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            instance = MessagesController.getInstance(this.currentAccount);
            j = this.dialog_id;
            i2 = AndroidUtilities.isTablet() ? 30 : 20;
            i3 = this.classGuid;
            isChannel = ChatObject.isChannel(this.currentChat);
            i4 = this.lastLoadIndex;
            this.lastLoadIndex = i4 + 1;
            instance.loadMessages(j, i2, 0, 0, true, 0, i3, 2, 0, isChannel, i4);
        }
        if (this.currentChat != null) {
            CountDownLatch countDownLatch3 = null;
            if (this.isBroadcast) {
                countDownLatch = new CountDownLatch(1);
            }
            MessagesController.getInstance(this.currentAccount).loadChatInfo(this.currentChat.id, countDownLatch3, ChatObject.isChannel(this.currentChat));
            if (this.isBroadcast && countDownLatch3 != null) {
                try {
                    countDownLatch3.await();
                } catch (Exception e22) {
                    FileLog.e(e22);
                }
            }
        }
        if (userId != 0 && this.currentUser.bot) {
            DataQuery.getInstance(this.currentAccount).loadBotInfo(userId, true, this.classGuid);
        } else if (this.info instanceof TLRPC$TL_chatFull) {
            for (int a = 0; a < this.info.participants.participants.size(); a++) {
                User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TLRPC$ChatParticipant) this.info.participants.participants.get(a)).user_id));
                if (user != null && user.bot) {
                    DataQuery.getInstance(this.currentAccount).loadBotInfo(user.id, true, this.classGuid);
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        int a;
        loadMessages(getIntent());
        super.onCreate(savedInstanceState);
        if (this.chatMessageCellsCache.isEmpty()) {
            for (a = 0; a < 8; a++) {
                this.chatMessageCellsCache.add(new ChatMessageCell(this));
            }
        }
        for (a = 1; a >= 0; a--) {
            this.selectedMessagesIds[a].clear();
            this.selectedMessagesCanCopyIds[a].clear();
        }
        this.cantDeleteMessagesCount = 0;
        Theme.createChatResources(this, false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.appDidLogout);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagesDidLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoaded);
        this.contentView = new SizeNotifierFrameLayout(this) {
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                setMeasuredDimension(widthSize, heightSize);
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    if (child == ChatPreviewActivity.this.popupContainer) {
                        child.measure(MeasureSpec.makeMeasureSpec(widthSize, 1073741824), MeasureSpec.makeMeasureSpec(heightSize, 1073741824));
                    } else if (child == ChatPreviewActivity.this.backgroundImage) {
                        measureChildWithMargins(child, MeasureSpec.makeMeasureSpec(widthSize, 1073741824), 0, MeasureSpec.makeMeasureSpec(heightSize, 1073741824), 0);
                    }
                }
            }

            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() != 8) {
                        int childLeft;
                        int childTop;
                        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                        int width = child.getMeasuredWidth();
                        int height = child.getMeasuredHeight();
                        int gravity = lp.gravity;
                        if (gravity == -1) {
                            gravity = 51;
                        }
                        int verticalGravity = gravity & 112;
                        switch ((gravity & 7) & 7) {
                            case 1:
                                childLeft = ((((r - l) - width) / 2) + lp.leftMargin) - lp.rightMargin;
                                break;
                            case 5:
                                childLeft = (r - width) - lp.rightMargin;
                                break;
                            default:
                                childLeft = lp.leftMargin;
                                break;
                        }
                        switch (verticalGravity) {
                            case 16:
                                childTop = (((((b - 0) - t) - height) / 2) + lp.topMargin) - lp.bottomMargin;
                                break;
                            case 48:
                                childTop = lp.topMargin;
                                break;
                            case 80:
                                childTop = (((b - 0) - t) - height) - lp.bottomMargin;
                                break;
                            default:
                                childTop = lp.topMargin;
                                break;
                        }
                        child.layout(childLeft, childTop, childLeft + width, childTop + height);
                    }
                }
                notifyHeightChanged();
            }
        };
        setContentView(this.contentView, new ViewGroup.LayoutParams(-1, -1));
        this.backgroundImage = new ImageView(this);
        this.backgroundImage.setScaleType(ScaleType.FIT_XY);
        this.contentView.addView(this.backgroundImage, LayoutHelper.createFrame(-1, -1.0f));
        this.backgroundImage.setOnTouchListener(new C22264());
        this.backgroundImage.setImageDrawable(Theme.getCachedWallpaper());
        this.popupContainer = new RelativeLayout(this);
        this.contentView.addView(this.popupContainer, LayoutHelper.createRelative(-1, -1, 0, 0, 0, 0, 13));
        this.buttomMenuContainer = new LinearLayout(this);
        this.buttomMenuContainer.setOrientation(0);
        this.buttomMenuContainer.setBackgroundColor(Theme.getColor(Theme.key_chat_messagePanelBackground));
        this.popupContainer.addView(this.buttomMenuContainer, LayoutHelper.createRelative(-1, 52, 12));
        createButtomMenu();
        this.messageContainer = new FrameLayout(this);
        this.popupContainer.addView(this.messageContainer, 0);
        this.actionBar = new ActionBar(this);
        this.actionBar.setOccupyStatusBar(false);
        this.actionBar.setBackButtonImage(R.drawable.ic_close_white);
        this.actionBar.getBackButtonImage().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon), Mode.MULTIPLY));
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefault));
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSelector), false);
        this.popupContainer.addView(this.actionBar);
        ViewGroup.LayoutParams layoutParams = this.actionBar.getLayoutParams();
        layoutParams.width = -1;
        this.actionBar.setLayoutParams(layoutParams);
        this.actionBar.setActionBarMenuOnItemClick(new C22275());
        this.avatarContainer = new FrameLayout(this);
        this.avatarContainer.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.actionBar.addView(this.avatarContainer);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.avatarContainer.getLayoutParams();
        layoutParams2.height = -1;
        layoutParams2.width = -2;
        layoutParams2.rightMargin = AndroidUtilities.dp(48.0f);
        layoutParams2.leftMargin = AndroidUtilities.dp(60.0f);
        layoutParams2.gravity = 51;
        this.avatarContainer.setLayoutParams(layoutParams2);
        this.avatarImageView = new BackupImageView(this);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(21.0f));
        this.avatarContainer.addView(this.avatarImageView);
        layoutParams2 = (FrameLayout.LayoutParams) this.avatarImageView.getLayoutParams();
        layoutParams2.width = AndroidUtilities.dp(42.0f);
        layoutParams2.height = AndroidUtilities.dp(42.0f);
        layoutParams2.topMargin = AndroidUtilities.dp(3.0f);
        this.avatarImageView.setLayoutParams(layoutParams2);
        this.nameTextView = new TextView(this);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle));
        this.nameTextView.setTextSize(1, 18.0f);
        this.nameTextView.setLines(1);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TruncateAt.END);
        this.nameTextView.setGravity(3);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.avatarContainer.addView(this.nameTextView);
        layoutParams2 = (FrameLayout.LayoutParams) this.nameTextView.getLayoutParams();
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.leftMargin = AndroidUtilities.dp(54.0f);
        layoutParams2.bottomMargin = AndroidUtilities.dp(22.0f);
        layoutParams2.gravity = 80;
        this.nameTextView.setLayoutParams(layoutParams2);
        this.onlineTextView = new TextView(this);
        this.onlineTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.onlineTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubtitle));
        this.onlineTextView.setTextSize(1, 14.0f);
        this.onlineTextView.setLines(1);
        this.onlineTextView.setMaxLines(1);
        this.onlineTextView.setSingleLine(true);
        this.onlineTextView.setEllipsize(TruncateAt.END);
        this.onlineTextView.setGravity(3);
        this.avatarContainer.addView(this.onlineTextView);
        layoutParams2 = (FrameLayout.LayoutParams) this.onlineTextView.getLayoutParams();
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.leftMargin = AndroidUtilities.dp(54.0f);
        layoutParams2.bottomMargin = AndroidUtilities.dp(1.0f);
        layoutParams2.gravity = 80;
        this.onlineTextView.setLayoutParams(layoutParams2);
        this.emptyViewContainer = new FrameLayout(this);
        this.emptyViewContainer.setVisibility(4);
        this.messageContainer.addView(this.emptyViewContainer, LayoutHelper.createFrame(-1, -2, 17));
        this.emptyViewContainer.setOnTouchListener(new C22286());
        this.emptyView = new TextView(this);
        this.emptyView.setTextSize(1, 14.0f);
        this.emptyView.setGravity(17);
        this.emptyView.setTextColor(Theme.getColor(Theme.key_chat_serviceText));
        this.emptyView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), Theme.getServiceMessageColor()));
        this.emptyView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.emptyViewContainer.addView(this.emptyView, LayoutHelper.createFrame(-2, -2.0f, 17, 16.0f, 0.0f, 16.0f, 0.0f));
        this.chatListView = new RecyclerListView(this) {
            ArrayList<ChatMessageCell> drawCaptionAfter = new ArrayList();
            ArrayList<ChatMessageCell> drawNamesAfter = new ArrayList();
            ArrayList<ChatMessageCell> drawTimeAfter = new ArrayList();

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean drawChild(android.graphics.Canvas r36, android.view.View r37, long r38) {
                /*
                r35 = this;
                r10 = 0;
                r9 = 0;
                r0 = r37;
                r0 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
                r31 = r0;
                if (r31 == 0) goto L_0x0050;
            L_0x000a:
                r7 = r37;
                r7 = (org.telegram.ui.Cells.ChatMessageCell) r7;
                r22 = r7.getCurrentPosition();
                r12 = r7.getCurrentMessagesGroup();
                if (r22 == 0) goto L_0x004a;
            L_0x0018:
                r0 = r22;
                r0 = r0.pw;
                r31 = r0;
                r0 = r22;
                r0 = r0.spanSize;
                r32 = r0;
                r0 = r31;
                r1 = r32;
                if (r0 == r1) goto L_0x0104;
            L_0x002a:
                r0 = r22;
                r0 = r0.spanSize;
                r31 = r0;
                r32 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                r0 = r31;
                r1 = r32;
                if (r0 != r1) goto L_0x0104;
            L_0x0038:
                r0 = r22;
                r0 = r0.siblingHeights;
                r31 = r0;
                if (r31 != 0) goto L_0x0104;
            L_0x0040:
                r0 = r12.hasSibling;
                r31 = r0;
                if (r31 == 0) goto L_0x0104;
            L_0x0046:
                r10 = r7.getBackgroundDrawableLeft();
            L_0x004a:
                r31 = r7.needDelayRoundProgressDraw();
                if (r31 == 0) goto L_0x0050;
            L_0x0050:
                if (r10 == 0) goto L_0x012a;
            L_0x0052:
                r36.save();
                r0 = (float) r10;
                r31 = r0;
                r32 = r37.getTranslationX();
                r31 = r31 + r32;
                r32 = r37.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r33 = r37.getRight();
                r0 = r33;
                r0 = (float) r0;
                r33 = r0;
                r34 = r37.getTranslationX();
                r33 = r33 + r34;
                r34 = r37.getBottom();
                r0 = r34;
                r0 = (float) r0;
                r34 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r3 = r33;
                r4 = r34;
                r0.clipRect(r1, r2, r3, r4);
            L_0x008c:
                r24 = super.drawChild(r36, r37, r38);
                if (r10 != 0) goto L_0x0094;
            L_0x0092:
                if (r9 == 0) goto L_0x0097;
            L_0x0094:
                r36.restore();
            L_0x0097:
                r20 = 0;
                r11 = r35.getChildCount();
                r5 = 0;
            L_0x009e:
                if (r5 >= r11) goto L_0x00ae;
            L_0x00a0:
                r0 = r35;
                r31 = r0.getChildAt(r5);
                r0 = r31;
                r1 = r37;
                if (r0 != r1) goto L_0x0168;
            L_0x00ac:
                r20 = r5;
            L_0x00ae:
                r31 = r11 + -1;
                r0 = r20;
                r1 = r31;
                if (r0 != r1) goto L_0x023f;
            L_0x00b6:
                r0 = r35;
                r0 = r0.drawTimeAfter;
                r31 = r0;
                r25 = r31.size();
                if (r25 <= 0) goto L_0x0175;
            L_0x00c2:
                r5 = 0;
            L_0x00c3:
                r0 = r25;
                if (r5 >= r0) goto L_0x016c;
            L_0x00c7:
                r0 = r35;
                r0 = r0.drawTimeAfter;
                r31 = r0;
                r0 = r31;
                r7 = r0.get(r5);
                r7 = (org.telegram.ui.Cells.ChatMessageCell) r7;
                r36.save();
                r31 = r7.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r7.getTranslationX();
                r31 = r31 + r32;
                r32 = r7.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r0.translate(r1, r2);
                r0 = r36;
                r7.drawTimeLayout(r0);
                r36.restore();
                r5 = r5 + 1;
                goto L_0x00c3;
            L_0x0104:
                r0 = r22;
                r0 = r0.siblingHeights;
                r31 = r0;
                if (r31 == 0) goto L_0x004a;
            L_0x010c:
                r32 = r37.getBottom();
                r31 = r7.isPinnedBottom();
                if (r31 == 0) goto L_0x0127;
            L_0x0116:
                r31 = 1;
            L_0x0118:
                r31 = r31 + 1;
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r9 = r32 - r31;
                goto L_0x004a;
            L_0x0127:
                r31 = 0;
                goto L_0x0118;
            L_0x012a:
                if (r9 == 0) goto L_0x008c;
            L_0x012c:
                r36.save();
                r31 = r37.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r37.getTranslationX();
                r31 = r31 + r32;
                r32 = r37.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r33 = r37.getRight();
                r0 = r33;
                r0 = (float) r0;
                r33 = r0;
                r34 = r37.getTranslationX();
                r33 = r33 + r34;
                r0 = (float) r9;
                r34 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r3 = r33;
                r4 = r34;
                r0.clipRect(r1, r2, r3, r4);
                goto L_0x008c;
            L_0x0168:
                r5 = r5 + 1;
                goto L_0x009e;
            L_0x016c:
                r0 = r35;
                r0 = r0.drawTimeAfter;
                r31 = r0;
                r31.clear();
            L_0x0175:
                r0 = r35;
                r0 = r0.drawNamesAfter;
                r31 = r0;
                r25 = r31.size();
                if (r25 <= 0) goto L_0x01cc;
            L_0x0181:
                r5 = 0;
            L_0x0182:
                r0 = r25;
                if (r5 >= r0) goto L_0x01c3;
            L_0x0186:
                r0 = r35;
                r0 = r0.drawNamesAfter;
                r31 = r0;
                r0 = r31;
                r7 = r0.get(r5);
                r7 = (org.telegram.ui.Cells.ChatMessageCell) r7;
                r36.save();
                r31 = r7.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r7.getTranslationX();
                r31 = r31 + r32;
                r32 = r7.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r0.translate(r1, r2);
                r0 = r36;
                r7.drawNamesLayout(r0);
                r36.restore();
                r5 = r5 + 1;
                goto L_0x0182;
            L_0x01c3:
                r0 = r35;
                r0 = r0.drawNamesAfter;
                r31 = r0;
                r31.clear();
            L_0x01cc:
                r0 = r35;
                r0 = r0.drawCaptionAfter;
                r31 = r0;
                r25 = r31.size();
                if (r25 <= 0) goto L_0x023f;
            L_0x01d8:
                r5 = 0;
            L_0x01d9:
                r0 = r25;
                if (r5 >= r0) goto L_0x0236;
            L_0x01dd:
                r0 = r35;
                r0 = r0.drawCaptionAfter;
                r31 = r0;
                r0 = r31;
                r7 = r0.get(r5);
                r7 = (org.telegram.ui.Cells.ChatMessageCell) r7;
                r31 = r7.getCurrentPosition();
                if (r31 != 0) goto L_0x01f4;
            L_0x01f1:
                r5 = r5 + 1;
                goto L_0x01d9;
            L_0x01f4:
                r36.save();
                r31 = r7.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r7.getTranslationX();
                r31 = r31 + r32;
                r32 = r7.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r0.translate(r1, r2);
                r31 = r7.getCurrentPosition();
                r0 = r31;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 1;
                if (r31 != 0) goto L_0x0233;
            L_0x0226:
                r31 = 1;
            L_0x0228:
                r0 = r36;
                r1 = r31;
                r7.drawCaptionLayout(r0, r1);
                r36.restore();
                goto L_0x01f1;
            L_0x0233:
                r31 = 0;
                goto L_0x0228;
            L_0x0236:
                r0 = r35;
                r0 = r0.drawCaptionAfter;
                r31 = r0;
                r31.clear();
            L_0x023f:
                r0 = r37;
                r0 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
                r31 = r0;
                if (r31 == 0) goto L_0x041f;
            L_0x0247:
                r8 = r37;
                r8 = (org.telegram.ui.Cells.ChatMessageCell) r8;
                r22 = r8.getCurrentPosition();
                if (r22 == 0) goto L_0x030c;
            L_0x0251:
                r0 = r22;
                r0 = r0.last;
                r31 = r0;
                if (r31 != 0) goto L_0x0269;
            L_0x0259:
                r0 = r22;
                r0 = r0.minX;
                r31 = r0;
                if (r31 != 0) goto L_0x02ba;
            L_0x0261:
                r0 = r22;
                r0 = r0.minY;
                r31 = r0;
                if (r31 != 0) goto L_0x02ba;
            L_0x0269:
                r31 = r11 + -1;
                r0 = r20;
                r1 = r31;
                if (r0 != r1) goto L_0x0420;
            L_0x0271:
                r36.save();
                r31 = r8.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r8.getTranslationX();
                r31 = r31 + r32;
                r32 = r8.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r0.translate(r1, r2);
                r0 = r22;
                r0 = r0.last;
                r31 = r0;
                if (r31 == 0) goto L_0x02a2;
            L_0x029d:
                r0 = r36;
                r8.drawTimeLayout(r0);
            L_0x02a2:
                r0 = r22;
                r0 = r0.minX;
                r31 = r0;
                if (r31 != 0) goto L_0x02b7;
            L_0x02aa:
                r0 = r22;
                r0 = r0.minY;
                r31 = r0;
                if (r31 != 0) goto L_0x02b7;
            L_0x02b2:
                r0 = r36;
                r8.drawNamesLayout(r0);
            L_0x02b7:
                r36.restore();
            L_0x02ba:
                r31 = r11 + -1;
                r0 = r20;
                r1 = r31;
                if (r0 != r1) goto L_0x045a;
            L_0x02c2:
                r36.save();
                r31 = r8.getLeft();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r8.getTranslationX();
                r31 = r31 + r32;
                r32 = r8.getTop();
                r0 = r32;
                r0 = (float) r0;
                r32 = r0;
                r0 = r36;
                r1 = r31;
                r2 = r32;
                r0.translate(r1, r2);
                r31 = r8.hasCaptionLayout();
                if (r31 == 0) goto L_0x0309;
            L_0x02ec:
                r0 = r22;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 8;
                if (r31 == 0) goto L_0x0309;
            L_0x02f6:
                r0 = r22;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 1;
                if (r31 != 0) goto L_0x0456;
            L_0x0300:
                r31 = 1;
            L_0x0302:
                r0 = r36;
                r1 = r31;
                r8.drawCaptionLayout(r0, r1);
            L_0x0309:
                r36.restore();
            L_0x030c:
                r18 = r8.getMessageObject();
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.roundVideoContainer;
                if (r31 == 0) goto L_0x0344;
            L_0x031c:
                r31 = r18.isRoundVideo();
                if (r31 == 0) goto L_0x0344;
            L_0x0322:
                r31 = org.telegram.messenger.MediaController.getInstance();
                r0 = r31;
                r1 = r18;
                r31 = r0.isPlayingMessage(r1);
                if (r31 == 0) goto L_0x0344;
            L_0x0330:
                r16 = r8.getPhotoImage();
                r6 = 0;
                r31 = r16.getImageX();
                r0 = r31;
                r0 = (float) r0;
                r31 = r0;
                r32 = r8.getTranslationX();
                r31 = r31 + r32;
            L_0x0344:
                r16 = r8.getAvatarImage();
                r26 = r8.getStatusDrawable();
                if (r16 == 0) goto L_0x041f;
            L_0x034e:
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r0 = r31;
                r1 = r18;
                r13 = r0.getValidGroupedMessage(r1);
                r27 = r37.getTop();
                r31 = r8.isPinnedBottom();
                if (r31 == 0) goto L_0x04a6;
            L_0x0366:
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.chatListView;
                r0 = r31;
                r1 = r37;
                r14 = r0.getChildViewHolder(r1);
                if (r14 == 0) goto L_0x04a6;
            L_0x037a:
                r21 = r14.getAdapterPosition();
                if (r13 == 0) goto L_0x04a2;
            L_0x0380:
                if (r22 == 0) goto L_0x04a2;
            L_0x0382:
                r0 = r13.posArray;
                r31 = r0;
                r0 = r31;
                r1 = r22;
                r15 = r0.indexOf(r1);
                r0 = r13.posArray;
                r31 = r0;
                r25 = r31.size();
                r0 = r22;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 8;
                if (r31 == 0) goto L_0x0477;
            L_0x03a0:
                r31 = r21 - r25;
                r19 = r31 + r15;
            L_0x03a4:
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.chatListView;
                r0 = r31;
                r1 = r19;
                r14 = r0.findViewHolderForAdapterPosition(r1);
                if (r14 == 0) goto L_0x04a6;
            L_0x03b8:
                r31 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r0 = r31;
                r0 = -r0;
                r31 = r0;
                r0 = r16;
                r1 = r31;
                r0.setImageY(r1);
                r0 = r16;
                r1 = r36;
                r0.draw(r1);
                if (r26 == 0) goto L_0x041f;
            L_0x03d3:
                r31 = r16.getDrawRegion();
                r0 = r31;
                r0 = r0.right;
                r31 = r0;
                r32 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
                r32 = org.telegram.messenger.AndroidUtilities.dp(r32);
                r31 = r31 - r32;
                r32 = r16.getDrawRegion();
                r0 = r32;
                r0 = r0.bottom;
                r32 = r0;
                r33 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
                r33 = org.telegram.messenger.AndroidUtilities.dp(r33);
                r32 = r32 - r33;
                r33 = r16.getDrawRegion();
                r0 = r33;
                r0 = r0.right;
                r33 = r0;
                r34 = r16.getDrawRegion();
                r0 = r34;
                r0 = r0.bottom;
                r34 = r0;
                r0 = r26;
                r1 = r31;
                r2 = r32;
                r3 = r33;
                r4 = r34;
                r0.setBounds(r1, r2, r3, r4);
                r0 = r26;
                r1 = r36;
                r0.draw(r1);
            L_0x041f:
                return r24;
            L_0x0420:
                r0 = r22;
                r0 = r0.last;
                r31 = r0;
                if (r31 == 0) goto L_0x0433;
            L_0x0428:
                r0 = r35;
                r0 = r0.drawTimeAfter;
                r31 = r0;
                r0 = r31;
                r0.add(r8);
            L_0x0433:
                r0 = r22;
                r0 = r0.minX;
                r31 = r0;
                if (r31 != 0) goto L_0x02ba;
            L_0x043b:
                r0 = r22;
                r0 = r0.minY;
                r31 = r0;
                if (r31 != 0) goto L_0x02ba;
            L_0x0443:
                r31 = r8.hasNameLayout();
                if (r31 == 0) goto L_0x02ba;
            L_0x0449:
                r0 = r35;
                r0 = r0.drawNamesAfter;
                r31 = r0;
                r0 = r31;
                r0.add(r8);
                goto L_0x02ba;
            L_0x0456:
                r31 = 0;
                goto L_0x0302;
            L_0x045a:
                r31 = r8.hasCaptionLayout();
                if (r31 == 0) goto L_0x030c;
            L_0x0460:
                r0 = r22;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 8;
                if (r31 == 0) goto L_0x030c;
            L_0x046a:
                r0 = r35;
                r0 = r0.drawCaptionAfter;
                r31 = r0;
                r0 = r31;
                r0.add(r8);
                goto L_0x030c;
            L_0x0477:
                r19 = r21 + -1;
                r5 = r15 + 1;
            L_0x047b:
                r0 = r25;
                if (r15 >= r0) goto L_0x03a4;
            L_0x047f:
                r0 = r13.posArray;
                r31 = r0;
                r0 = r31;
                r31 = r0.get(r5);
                r31 = (org.telegram.messenger.MessageObject.GroupedMessagePosition) r31;
                r0 = r31;
                r0 = r0.minY;
                r31 = r0;
                r0 = r22;
                r0 = r0.maxY;
                r32 = r0;
                r0 = r31;
                r1 = r32;
                if (r0 > r1) goto L_0x03a4;
            L_0x049d:
                r19 = r19 + -1;
                r5 = r5 + 1;
                goto L_0x047b;
            L_0x04a2:
                r19 = r21 + -1;
                goto L_0x03a4;
            L_0x04a6:
                r29 = r8.getTranslationX();
                r31 = r37.getTop();
                r32 = r8.getLayoutHeight();
                r30 = r31 + r32;
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.chatListView;
                r31 = r31.getMeasuredHeight();
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r32 = r0;
                r32 = r32.chatListView;
                r32 = r32.getPaddingBottom();
                r17 = r31 - r32;
                r0 = r30;
                r1 = r17;
                if (r0 <= r1) goto L_0x04da;
            L_0x04d8:
                r30 = r17;
            L_0x04da:
                r31 = r8.isPinnedTop();
                if (r31 == 0) goto L_0x04fe;
            L_0x04e0:
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.chatListView;
                r0 = r31;
                r1 = r37;
                r14 = r0.getChildViewHolder(r1);
                if (r14 == 0) goto L_0x04fe;
            L_0x04f4:
                r28 = 0;
            L_0x04f6:
                r31 = 20;
                r0 = r28;
                r1 = r31;
                if (r0 < r1) goto L_0x0597;
            L_0x04fe:
                r31 = 1111490560; // 0x42400000 float:48.0 double:5.491493014E-315;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r31 = r30 - r31;
                r0 = r31;
                r1 = r27;
                if (r0 >= r1) goto L_0x0514;
            L_0x050c:
                r31 = 1111490560; // 0x42400000 float:48.0 double:5.491493014E-315;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r30 = r27 + r31;
            L_0x0514:
                r31 = 0;
                r31 = (r29 > r31 ? 1 : (r29 == r31 ? 0 : -1));
                if (r31 == 0) goto L_0x0528;
            L_0x051a:
                r36.save();
                r31 = 0;
                r0 = r36;
                r1 = r29;
                r2 = r31;
                r0.translate(r1, r2);
            L_0x0528:
                r31 = 1110441984; // 0x42300000 float:44.0 double:5.48631236E-315;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r31 = r30 - r31;
                r0 = r16;
                r1 = r31;
                r0.setImageY(r1);
                r0 = r16;
                r1 = r36;
                r0.draw(r1);
                r31 = 0;
                r31 = (r29 > r31 ? 1 : (r29 == r31 ? 0 : -1));
                if (r31 == 0) goto L_0x0547;
            L_0x0544:
                r36.restore();
            L_0x0547:
                if (r26 == 0) goto L_0x041f;
            L_0x0549:
                r31 = r16.getDrawRegion();
                r0 = r31;
                r0 = r0.right;
                r31 = r0;
                r32 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
                r32 = org.telegram.messenger.AndroidUtilities.dp(r32);
                r31 = r31 - r32;
                r32 = r16.getDrawRegion();
                r0 = r32;
                r0 = r0.bottom;
                r32 = r0;
                r33 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
                r33 = org.telegram.messenger.AndroidUtilities.dp(r33);
                r32 = r32 - r33;
                r33 = r16.getDrawRegion();
                r0 = r33;
                r0 = r0.right;
                r33 = r0;
                r34 = r16.getDrawRegion();
                r0 = r34;
                r0 = r0.bottom;
                r34 = r0;
                r0 = r26;
                r1 = r31;
                r2 = r32;
                r3 = r33;
                r4 = r34;
                r0.setBounds(r1, r2, r3, r4);
                r0 = r26;
                r1 = r36;
                r0.draw(r1);
                goto L_0x041f;
            L_0x0597:
                r28 = r28 + 1;
                r21 = r14.getAdapterPosition();
                if (r13 == 0) goto L_0x0648;
            L_0x059f:
                if (r22 == 0) goto L_0x0648;
            L_0x05a1:
                r0 = r13.posArray;
                r31 = r0;
                r0 = r31;
                r1 = r22;
                r15 = r0.indexOf(r1);
                if (r15 < 0) goto L_0x04fe;
            L_0x05af:
                r0 = r13.posArray;
                r31 = r0;
                r25 = r31.size();
                r0 = r22;
                r0 = r0.flags;
                r31 = r0;
                r31 = r31 & 4;
                if (r31 == 0) goto L_0x061f;
            L_0x05c1:
                r31 = r21 + r15;
                r23 = r31 + 1;
            L_0x05c5:
                r0 = r35;
                r0 = turbogram.ChatPreviewActivity.this;
                r31 = r0;
                r31 = r31.chatListView;
                r0 = r31;
                r1 = r23;
                r14 = r0.findViewHolderForAdapterPosition(r1);
                if (r14 == 0) goto L_0x04fe;
            L_0x05d9:
                r0 = r14.itemView;
                r31 = r0;
                r27 = r31.getTop();
                r31 = 1111490560; // 0x42400000 float:48.0 double:5.491493014E-315;
                r31 = org.telegram.messenger.AndroidUtilities.dp(r31);
                r31 = r30 - r31;
                r0 = r14.itemView;
                r32 = r0;
                r32 = r32.getBottom();
                r0 = r31;
                r1 = r32;
                if (r0 >= r1) goto L_0x0607;
            L_0x05f7:
                r0 = r14.itemView;
                r31 = r0;
                r31 = r31.getTranslationX();
                r0 = r31;
                r1 = r29;
                r29 = java.lang.Math.min(r0, r1);
            L_0x0607:
                r0 = r14.itemView;
                r31 = r0;
                r0 = r31;
                r0 = r0 instanceof org.telegram.ui.Cells.ChatMessageCell;
                r31 = r0;
                if (r31 == 0) goto L_0x04fe;
            L_0x0613:
                r7 = r14.itemView;
                r7 = (org.telegram.ui.Cells.ChatMessageCell) r7;
                r31 = r7.isPinnedTop();
                if (r31 != 0) goto L_0x04f6;
            L_0x061d:
                goto L_0x04fe;
            L_0x061f:
                r23 = r21 + 1;
                r5 = r15 + -1;
            L_0x0623:
                if (r15 < 0) goto L_0x05c5;
            L_0x0625:
                r0 = r13.posArray;
                r31 = r0;
                r0 = r31;
                r31 = r0.get(r5);
                r31 = (org.telegram.messenger.MessageObject.GroupedMessagePosition) r31;
                r0 = r31;
                r0 = r0.maxY;
                r31 = r0;
                r0 = r22;
                r0 = r0.minY;
                r32 = r0;
                r0 = r31;
                r1 = r32;
                if (r0 < r1) goto L_0x05c5;
            L_0x0643:
                r23 = r23 + 1;
                r5 = r5 + -1;
                goto L_0x0623;
            L_0x0648:
                r23 = r21 + 1;
                goto L_0x05c5;
                */
                throw new UnsupportedOperationException("Method not decompiled: turbogram.ChatPreviewActivity.7.drawChild(android.graphics.Canvas, android.view.View, long):boolean");
            }
        };
        this.chatListView.setTag(Integer.valueOf(1));
        this.chatListView.setVerticalScrollBarEnabled(true);
        RecyclerListView recyclerListView = this.chatListView;
        Adapter messagesAdapter = new MessagesAdapter(this);
        this.chatAdapter = messagesAdapter;
        recyclerListView.setAdapter(messagesAdapter);
        this.chatListView.setClipToPadding(false);
        this.chatListView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(3.0f));
        this.chatListView.setItemAnimator(null);
        this.chatListView.setLayoutAnimation(null);
        this.chatLayoutManager = new GridLayoutManagerFixed(this, 1000, 1, true) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }

            public void smoothScrollToPosition(RecyclerView recyclerView, State state, int position) {
                LinearSmoothScrollerMiddle linearSmoothScroller = new LinearSmoothScrollerMiddle(recyclerView.getContext());
                linearSmoothScroller.setTargetPosition(position);
                startSmoothScroll(linearSmoothScroller);
            }

            public boolean shouldLayoutChildFromOpositeSide(View child) {
                if (!(child instanceof ChatMessageCell) || ((ChatMessageCell) child).getMessageObject().isOutOwner()) {
                    return false;
                }
                return true;
            }

            protected boolean hasSiblingChild(int position) {
                if (position < ChatPreviewActivity.this.chatAdapter.messagesStartRow || position >= ChatPreviewActivity.this.chatAdapter.messagesEndRow) {
                    return false;
                }
                int index = position - ChatPreviewActivity.this.chatAdapter.messagesStartRow;
                if (index < 0 || index >= ChatPreviewActivity.this.messages.size()) {
                    return false;
                }
                MessageObject message = (MessageObject) ChatPreviewActivity.this.messages.get(index);
                GroupedMessages group = ChatPreviewActivity.this.getValidGroupedMessage(message);
                if (group == null) {
                    return false;
                }
                GroupedMessagePosition pos = (GroupedMessagePosition) group.positions.get(message);
                if (pos.minX == pos.maxX || pos.minY != pos.maxY || pos.minY == (byte) 0) {
                    return false;
                }
                int count = group.posArray.size();
                for (int a = 0; a < count; a++) {
                    GroupedMessagePosition p = (GroupedMessagePosition) group.posArray.get(a);
                    if (p != pos && p.minY <= pos.minY && p.maxY >= pos.minY) {
                        return true;
                    }
                }
                return false;
            }
        };
        this.chatLayoutManager.setSpanSizeLookup(new C22319());
        this.chatListView.setLayoutManager(this.chatLayoutManager);
        this.messageContainer.addView(this.chatListView, LayoutHelper.createFrame(-1, -1.0f));
        this.chatListView.setOnScrollListener(new OnScrollListener() {
            private final int scrollValue = AndroidUtilities.dp(100.0f);
            private float totalDy = 0.0f;

            /* renamed from: turbogram.ChatPreviewActivity$10$1 */
            class C22221 extends AnimatorListenerAdapter {
                C22221() {
                }

                public void onAnimationEnd(Animator animation) {
                    if (animation.equals(ChatPreviewActivity.this.floatingDateAnimation)) {
                        ChatPreviewActivity.this.floatingDateAnimation = null;
                    }
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    ChatPreviewActivity.this.scrollingFloatingDate = true;
                    ChatPreviewActivity.this.checkTextureViewPosition = true;
                } else if (newState == 0) {
                    ChatPreviewActivity.this.scrollingFloatingDate = false;
                    ChatPreviewActivity.this.checkTextureViewPosition = false;
                    ChatPreviewActivity.this.hideFloatingDateView(true);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ChatPreviewActivity.this.chatListView.invalidate();
                if (!(dy == 0 || !ChatPreviewActivity.this.scrollingFloatingDate || ChatPreviewActivity.this.currentFloatingTopIsNotMessage)) {
                    if (ChatPreviewActivity.this.highlightMessageId != Integer.MAX_VALUE) {
                        ChatPreviewActivity.this.highlightMessageId = Integer.MAX_VALUE;
                        ChatPreviewActivity.this.updateVisibleRows();
                    }
                    if (ChatPreviewActivity.this.floatingDateView.getTag() == null) {
                        if (ChatPreviewActivity.this.floatingDateAnimation != null) {
                            ChatPreviewActivity.this.floatingDateAnimation.cancel();
                        }
                        ChatPreviewActivity.this.floatingDateView.setTag(Integer.valueOf(1));
                        ChatPreviewActivity.this.floatingDateAnimation = new AnimatorSet();
                        ChatPreviewActivity.this.floatingDateAnimation.setDuration(150);
                        AnimatorSet access$1700 = ChatPreviewActivity.this.floatingDateAnimation;
                        Animator[] animatorArr = new Animator[1];
                        animatorArr[0] = ObjectAnimator.ofFloat(ChatPreviewActivity.this.floatingDateView, "alpha", new float[]{1.0f});
                        access$1700.playTogether(animatorArr);
                        ChatPreviewActivity.this.floatingDateAnimation.addListener(new C22221());
                        ChatPreviewActivity.this.floatingDateAnimation.start();
                    }
                }
                ChatPreviewActivity.this.checkScrollForLoad(true);
                int firstVisibleItem = ChatPreviewActivity.this.chatLayoutManager.findFirstVisibleItemPosition();
                int visibleItemCount = firstVisibleItem == -1 ? 0 : Math.abs(ChatPreviewActivity.this.chatLayoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
                if (visibleItemCount > 0) {
                    if (firstVisibleItem + visibleItemCount == ChatPreviewActivity.this.chatAdapter.getItemCount() && ChatPreviewActivity.this.forwardEndReached[0]) {
                        ChatPreviewActivity.this.showPagedownButton(false, true);
                    } else if (dy > 0) {
                        if (ChatPreviewActivity.this.pagedownButton.getTag() == null) {
                            this.totalDy += (float) dy;
                            if (this.totalDy > ((float) this.scrollValue)) {
                                this.totalDy = 0.0f;
                                ChatPreviewActivity.this.showPagedownButton(true, true);
                                ChatPreviewActivity.this.pagedownButtonShowedByScroll = true;
                            }
                        }
                    } else if (ChatPreviewActivity.this.pagedownButtonShowedByScroll && ChatPreviewActivity.this.pagedownButton.getTag() != null) {
                        this.totalDy += (float) dy;
                        if (this.totalDy < ((float) (-this.scrollValue))) {
                            ChatPreviewActivity.this.showPagedownButton(false, true);
                            this.totalDy = 0.0f;
                        }
                    }
                }
                ChatPreviewActivity.this.updateMessagesVisisblePart();
            }
        });
        if (this.scrollToPositionOnRecreate != -1) {
            this.chatLayoutManager.scrollToPositionWithOffset(this.scrollToPositionOnRecreate, this.scrollToOffsetOnRecreate);
            this.scrollToPositionOnRecreate = -1;
        }
        this.progressView = new FrameLayout(this);
        this.progressView.setVisibility(4);
        this.messageContainer.addView(this.progressView, LayoutHelper.createFrame(-1, -1, 51));
        this.progressView2 = new View(this);
        this.progressView2.setBackgroundResource(R.drawable.system_loader);
        this.progressView2.getBackground().setColorFilter(Theme.colorFilter);
        this.progressView.addView(this.progressView2, LayoutHelper.createFrame(36, 36, 17));
        this.progressBar = new RadialProgressView(this);
        this.progressBar.setSize(AndroidUtilities.dp(28.0f));
        this.progressBar.setProgressColor(Theme.getColor(Theme.key_chat_serviceText));
        this.progressView.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
        this.floatingDateView = new ChatActionCell(this);
        this.floatingDateView.setAlpha(0.0f);
        this.messageContainer.addView(this.floatingDateView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        this.chatAdapter.updateRows();
        if (this.loading && this.messages.isEmpty()) {
            this.progressView.setVisibility(this.chatAdapter.botInfoRow == -1 ? 0 : 4);
            this.chatListView.setEmptyView(null);
        } else {
            this.progressView.setVisibility(4);
            this.chatListView.setEmptyView(this.emptyViewContainer);
        }
        this.pagedownButton = new FrameLayout(this);
        this.pagedownButton.setVisibility(4);
        this.messageContainer.addView(this.pagedownButton, LayoutHelper.createFrame(46, 59.0f, 85, 0.0f, 0.0f, 7.0f, 5.0f));
        this.pagedownButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ChatPreviewActivity.this.checkTextureViewPosition = true;
                ChatPreviewActivity.this.scrollToLastMessage(true);
            }
        });
        this.pagedownButtonImage = new ImageView(this);
        this.pagedownButtonImage.setImageResource(R.drawable.pagedown);
        this.pagedownButtonImage.setScaleType(ScaleType.CENTER);
        this.pagedownButtonImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonIcon), Mode.MULTIPLY));
        this.pagedownButtonImage.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
        Drawable drawable = Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), Theme.getColor(Theme.key_chat_goDownButton));
        Drawable shadowDrawable = getResources().getDrawable(R.drawable.pagedown_shadow).mutate();
        shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_goDownButtonShadow), Mode.MULTIPLY));
        Drawable combinedDrawable = new CombinedDrawable(shadowDrawable, drawable, 0, 0);
        combinedDrawable.setIconSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        this.pagedownButtonImage.setBackgroundDrawable(combinedDrawable);
        this.pagedownButton.addView(this.pagedownButtonImage, LayoutHelper.createFrame(46, 46, 83));
    }

    private void createButtomMenu() {
        this.btn1 = new ImageView(this);
        this.btn1.setTag(Integer.valueOf(1));
        this.btn1.setScaleType(ScaleType.CENTER);
        this.btn1.setImageResource(R.drawable.turbo_tb_settings);
        this.btn1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn1, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn1.setOnClickListener(this);
        this.btn2 = new ImageView(this);
        this.btn2.setTag(Integer.valueOf(2));
        this.btn2.setScaleType(ScaleType.CENTER);
        this.btn2.setImageResource(this.dialog.pinned ? R.drawable.chats_unpin : R.drawable.chats_pin);
        this.btn2.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn2, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn2.setOnClickListener(this);
        this.btn3 = new ImageView(this);
        this.btn3.setTag(Integer.valueOf(3));
        this.btn3.setScaleType(ScaleType.CENTER);
        this.btn3.setImageResource(TurboConfig.containValue(new StringBuilder().append("fav_").append(this.dialog_id).toString()) ? R.drawable.turbo_del_fav : R.drawable.turbo_add_fav);
        this.btn3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn3, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn3.setOnClickListener(this);
        this.btn4 = new ImageView(this);
        this.btn4.setTag(Integer.valueOf(4));
        this.btn4.setScaleType(ScaleType.CENTER);
        this.btn4.setImageResource(MessagesController.getInstance(this.currentAccount).isDialogMuted(this.dialog_id) ? R.drawable.notifications_s_on : R.drawable.notifications_s_off);
        this.btn4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn4, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn4.setOnClickListener(this);
        this.btn5 = new ImageView(this);
        this.btn5.setTag(Integer.valueOf(5));
        this.btn5.setScaleType(ScaleType.CENTER);
        this.btn5.setImageResource(R.drawable.chats_clear);
        this.btn5.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn5, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn5.setOnClickListener(this);
        this.btn6 = new ImageView(this);
        this.btn6.setTag(Integer.valueOf(6));
        this.btn6.setScaleType(ScaleType.CENTER);
        this.btn6.setImageResource(DialogObject.isChannel(this.dialog) ? R.drawable.chats_leave : R.drawable.chats_delete);
        this.btn6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), Mode.MULTIPLY));
        this.buttomMenuContainer.addView(this.btn6, LayoutHelper.createLinear(0, -1, 1.0f));
        this.btn6.setOnClickListener(this);
    }

    public void onClick(View v) {
        int lower_id = (int) this.dialog_id;
        int high_id = (int) (this.dialog_id >> 32);
        boolean isChat = lower_id < 0 && high_id != 1;
        User user = null;
        if (!(isChat || lower_id <= 0 || high_id == 1)) {
            user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(lower_id));
        }
        boolean isBot = user != null && user.bot;
        Builder builder;
        TLRPC$Chat chat;
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.turboUpdateInterface, Integer.valueOf(7), Long.valueOf(this.dialog_id));
                onFinish();
                finish();
                return;
            case 2:
                MessagesController.getInstance(this.currentAccount).pinDialog(this.dialog_id, !this.dialog.pinned, null, 0);
                this.btn2.setImageResource(this.dialog.pinned ? R.drawable.chats_unpin : R.drawable.chats_pin);
                return;
            case 3:
                if (TurboConfig.containValue("fav_" + this.dialog_id)) {
                    TurboConfig.removeValue("fav_" + this.dialog_id);
                } else {
                    TurboConfig.setIntValue("fav_" + this.dialog_id, (int) this.dialog_id);
                }
                this.btn3.setImageResource(TurboConfig.containValue(new StringBuilder().append("fav_").append(this.dialog_id).toString()) ? R.drawable.turbo_del_fav : R.drawable.turbo_add_fav);
                return;
            case 4:
                int i;
                boolean muted = MessagesController.getInstance(this.currentAccount).isDialogMuted(this.dialog_id);
                Editor editor;
                TLRPC$TL_dialog dlg;
                if (muted) {
                    editor = MessagesController.getNotificationsSettings(this.currentAccount).edit();
                    editor.putInt("notify2_" + this.dialog_id, 0);
                    MessagesStorage.getInstance(this.currentAccount).setDialogFlags(this.dialog_id, 0);
                    editor.commit();
                    dlg = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id);
                    if (dlg != null) {
                        dlg.notify_settings = new TLRPC$TL_peerNotifySettings();
                    }
                    NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(this.dialog_id);
                } else {
                    editor = MessagesController.getNotificationsSettings(this.currentAccount).edit();
                    editor.putInt("notify2_" + this.dialog_id, 2);
                    MessagesStorage.getInstance(this.currentAccount).setDialogFlags(this.dialog_id, 1);
                    editor.commit();
                    dlg = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id);
                    if (dlg != null) {
                        dlg.notify_settings = new TLRPC$TL_peerNotifySettings();
                        dlg.notify_settings.mute_until = Integer.MAX_VALUE;
                    }
                    NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(this.dialog_id);
                    NotificationsController.getInstance(this.currentAccount).removeNotificationsForDialog(this.dialog_id);
                }
                ImageView imageView = this.btn4;
                if (muted) {
                    i = R.drawable.notifications_s_off;
                } else {
                    i = R.drawable.notifications_s_on;
                }
                imageView.setImageResource(i);
                return;
            case 5:
                builder = new Builder((Context) this);
                builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                if (DialogObject.isChannel(this.dialog)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (chat == null || !chat.megagroup) {
                        builder.setMessage(LocaleController.getString("AreYouSureClearHistoryChannel", R.string.AreYouSureClearHistoryChannel));
                    } else {
                        builder.setMessage(LocaleController.getString("AreYouSureClearHistoryGroup", R.string.AreYouSureClearHistoryGroup));
                    }
                } else {
                    builder.setMessage(LocaleController.getString("AreYouSureClearHistory", R.string.AreYouSureClearHistory));
                }
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (DialogObject.isChannel(ChatPreviewActivity.this.dialog)) {
                            MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteDialog(ChatPreviewActivity.this.dialog_id, 2);
                        } else {
                            MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteDialog(ChatPreviewActivity.this.dialog_id, 1);
                        }
                        ChatPreviewActivity.this.onFinish();
                        ChatPreviewActivity.this.finish();
                    }
                });
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                builder.create().show();
                return;
            case 6:
                builder = new Builder((Context) this);
                builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                if (DialogObject.isChannel(this.dialog)) {
                    chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-lower_id));
                    if (chat == null || !chat.megagroup) {
                        if (chat == null || !chat.creator) {
                            builder.setMessage(LocaleController.getString("ChannelLeaveAlert", R.string.ChannelLeaveAlert));
                        } else {
                            builder.setMessage(LocaleController.getString("ChannelDeleteAlert", R.string.ChannelDeleteAlert));
                        }
                    } else if (chat.creator) {
                        builder.setMessage(LocaleController.getString("MegaDeleteAlert", R.string.MegaDeleteAlert));
                    } else {
                        builder.setMessage(LocaleController.getString("MegaLeaveAlert", R.string.MegaLeaveAlert));
                    }
                } else if (isChat) {
                    builder.setMessage(LocaleController.getString("AreYouSureDeleteAndExit", R.string.AreYouSureDeleteAndExit));
                } else {
                    builder.setMessage(LocaleController.getString("AreYouSureDeleteThisChat", R.string.AreYouSureDeleteThisChat));
                }
                final boolean z = isChat;
                final boolean z2 = isBot;
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TurboUtils.clearChatData(ChatPreviewActivity.this.currentAccount, ChatPreviewActivity.this.dialog_id);
                        if (DialogObject.isChannel(ChatPreviewActivity.this.dialog)) {
                            MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteUserFromChat((int) (-ChatPreviewActivity.this.dialog_id), UserConfig.getInstance(ChatPreviewActivity.this.currentAccount).getCurrentUser(), null);
                            if (AndroidUtilities.isTablet()) {
                                NotificationCenter.getInstance(ChatPreviewActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(ChatPreviewActivity.this.dialog_id));
                            }
                        } else {
                            if (z) {
                                TLRPC$Chat currentChat = MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).getChat(Integer.valueOf((int) (-ChatPreviewActivity.this.dialog_id)));
                                if (currentChat == null || !ChatObject.isNotInChat(currentChat)) {
                                    MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteUserFromChat((int) (-ChatPreviewActivity.this.dialog_id), MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).getUser(Integer.valueOf(UserConfig.getInstance(ChatPreviewActivity.this.currentAccount).getClientUserId())), null);
                                } else {
                                    MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteDialog(ChatPreviewActivity.this.dialog_id, 0);
                                }
                            } else {
                                MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).deleteDialog(ChatPreviewActivity.this.dialog_id, 0);
                            }
                            if (z2) {
                                MessagesController.getInstance(ChatPreviewActivity.this.currentAccount).blockUser((int) ChatPreviewActivity.this.dialog_id);
                            }
                            if (AndroidUtilities.isTablet()) {
                                NotificationCenter.getInstance(ChatPreviewActivity.this.currentAccount).postNotificationName(NotificationCenter.closeChats, Long.valueOf(ChatPreviewActivity.this.dialog_id));
                            }
                        }
                        ChatPreviewActivity.this.onFinish();
                        ChatPreviewActivity.this.finish();
                    }
                });
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                builder.create().show();
                return;
            default:
                return;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AndroidUtilities.checkDisplaySize(this, newConfig);
        fixLayout();
    }

    private void fixLayout() {
        if (this.avatarContainer != null) {
            this.avatarContainer.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (ChatPreviewActivity.this.avatarContainer != null) {
                        ChatPreviewActivity.this.avatarContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    int padding = (ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(48.0f)) / 2;
                    ChatPreviewActivity.this.avatarContainer.setPadding(ChatPreviewActivity.this.avatarContainer.getPaddingLeft(), padding, ChatPreviewActivity.this.avatarContainer.getPaddingRight(), padding);
                    return true;
                }
            });
        }
        if (this.messageContainer != null) {
            this.messageContainer.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    ChatPreviewActivity.this.messageContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (!ChatPreviewActivity.this.checkTransitionAnimation()) {
                        MarginLayoutParams layoutParams = (MarginLayoutParams) ChatPreviewActivity.this.messageContainer.getLayoutParams();
                        layoutParams.topMargin = ActionBar.getCurrentActionBarHeight();
                        layoutParams.bottomMargin = AndroidUtilities.dp(52.0f);
                        layoutParams.width = -1;
                        layoutParams.height = -1;
                        ChatPreviewActivity.this.messageContainer.setLayoutParams(layoutParams);
                    }
                    return true;
                }
            });
        }
    }

    protected void onResume() {
        super.onResume();
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
        fixLayout();
        checkAndUpdateAvatar();
        updateSubtitle();
        if (this.scrollToTopOnResume) {
            if (!this.scrollToTopUnReadOnResume || this.scrollToMessage == null) {
                moveScrollToLastMessage();
            } else if (this.chatListView != null) {
                int yOffset;
                int paddingTop;
                if (this.scrollToMessagePosition == -9000) {
                    yOffset = Math.max(0, (this.chatListView.getHeight() - this.scrollToMessage.getApproximateHeight()) / 2);
                } else if (this.scrollToMessagePosition == -10000) {
                    yOffset = 0;
                } else {
                    yOffset = this.scrollToMessagePosition;
                }
                GridLayoutManagerFixed gridLayoutManagerFixed = this.chatLayoutManager;
                int size = this.messages.size() - this.messages.indexOf(this.scrollToMessage);
                int i = (-AndroidUtilities.dp(7.0f)) + yOffset;
                if (this.scrollToMessage == this.unreadMessageObject) {
                    paddingTop = this.chatListView.getPaddingTop();
                } else {
                    paddingTop = 0;
                }
                gridLayoutManagerFixed.scrollToPositionWithOffset(size, i - paddingTop);
            }
            this.scrollToTopUnReadOnResume = false;
            this.scrollToTopOnResume = false;
            this.scrollToMessage = null;
        }
        this.paused = false;
        checkScrollForLoad(false);
        if (this.wasPaused) {
            this.wasPaused = false;
            if (this.chatAdapter != null) {
                this.chatAdapter.notifyDataSetChanged();
            }
        }
    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
        this.paused = true;
        this.wasPaused = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        onFinish();
        if (this.avatarImageView != null) {
            this.avatarImageView.setImageDrawable(null);
        }
    }

    protected void onFinish() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.appDidLogout);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagesDidLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoaded);
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.appDidLogout) {
            onFinish();
            finish();
        } else if (id == NotificationCenter.updateInterfaces) {
            int updateMask = ((Integer) args[0]).intValue();
            if (!((updateMask & 1) == 0 && (updateMask & 4) == 0 && (updateMask & 16) == 0 && (updateMask & 32) == 0)) {
                updateSubtitle();
            }
            if (!((updateMask & 2) == 0 && (updateMask & 8) == 0)) {
                checkAndUpdateAvatar();
            }
            if ((updateMask & 64) != 0) {
                CharSequence printString = (CharSequence) MessagesController.getInstance(this.currentAccount).printingStrings.get(this.dialog_id);
                if ((this.lastPrintString != null && printString == null) || ((this.lastPrintString == null && printString != null) || (this.lastPrintString != null && printString != null && !this.lastPrintString.equals(printString)))) {
                    updateSubtitle();
                }
            }
        } else if (id == NotificationCenter.contactsDidLoaded) {
            updateSubtitle();
        } else if (id == NotificationCenter.emojiDidLoaded) {
            if (this.chatListView != null) {
                this.chatListView.invalidateViews();
            }
        } else if (id == NotificationCenter.chatInfoDidLoaded) {
            TLRPC$ChatFull chatFull = args[0];
            if ((this.info instanceof TLRPC$TL_channelFull) && chatFull.participants == null && this.info != null) {
                chatFull.participants = this.info.participants;
            }
            this.info = chatFull;
            updateSubtitle();
        } else if (id == NotificationCenter.messagesDidLoaded && ((Integer) args[10]).intValue() == this.classGuid) {
            if (!this.openAnimationEnded) {
                NotificationCenter.getInstance(this.currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.chatInfoDidLoaded, NotificationCenter.dialogsNeedReload, NotificationCenter.closeChats, NotificationCenter.botKeyboardDidLoaded});
            }
            int index = this.waitingForLoad.indexOf(Integer.valueOf(((Integer) args[11]).intValue()));
            int currentUserId = UserConfig.getInstance(this.currentAccount).getClientUserId();
            if (index != -1) {
                int a;
                MessageObject obj;
                GroupedMessages groupedMessages;
                this.waitingForLoad.remove(index);
                ArrayList<MessageObject> messArr = args[2];
                boolean createUnreadLoading = false;
                if (this.waitingForReplyMessageLoad) {
                    if (!this.createUnreadMessageAfterIdLoading) {
                        boolean found = false;
                        for (a = 0; a < messArr.size(); a++) {
                            obj = (MessageObject) messArr.get(a);
                            if (obj.getId() == this.startLoadFromMessageId) {
                                found = true;
                                break;
                            }
                            if (a + 1 < messArr.size()) {
                                MessageObject obj2 = (MessageObject) messArr.get(a + 1);
                                if (obj.getId() >= this.startLoadFromMessageId && obj2.getId() < this.startLoadFromMessageId) {
                                    this.startLoadFromMessageId = obj.getId();
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            this.startLoadFromMessageId = 0;
                            return;
                        }
                    }
                    int startLoadFrom = this.startLoadFromMessageId;
                    boolean needSelect = this.needSelectFromMessageId;
                    int unreadAfterId = this.createUnreadMessageAfterId;
                    createUnreadLoading = this.createUnreadMessageAfterIdLoading;
                    clearChatData();
                    this.createUnreadMessageAfterId = unreadAfterId;
                    this.startLoadFromMessageId = startLoadFrom;
                    this.needSelectFromMessageId = needSelect;
                }
                this.loadsCount++;
                int loadIndex = ((Long) args[0]).longValue() == this.dialog_id ? 0 : 1;
                int count = ((Integer) args[1]).intValue();
                boolean isCache = ((Boolean) args[3]).booleanValue();
                int fnid = ((Integer) args[4]).intValue();
                int last_unread_date = ((Integer) args[7]).intValue();
                int load_type = ((Integer) args[8]).intValue();
                int loaded_max_id = ((Integer) args[12]).intValue();
                int loaded_mentions_count = ((Integer) args[13]).intValue();
                if (loaded_mentions_count < 0) {
                    loaded_mentions_count *= -1;
                    this.hasAllMentionsLocal = false;
                } else if (this.first) {
                    this.hasAllMentionsLocal = true;
                }
                if (load_type == 4) {
                    this.startLoadFromMessageId = loaded_max_id;
                    for (a = messArr.size() - 1; a > 0; a--) {
                        obj = (MessageObject) messArr.get(a);
                        if (obj.type < 0 && obj.getId() == this.startLoadFromMessageId) {
                            this.startLoadFromMessageId = ((MessageObject) messArr.get(a - 1)).getId();
                            break;
                        }
                    }
                }
                int unread_to_load = 0;
                if (fnid != 0) {
                    this.last_message_id = ((Integer) args[5]).intValue();
                    if (load_type == 3) {
                        if (this.loadingFromOldPosition) {
                            unread_to_load = ((Integer) args[6]).intValue();
                            if (unread_to_load != 0) {
                                this.createUnreadMessageAfterId = fnid;
                            }
                            this.loadingFromOldPosition = false;
                        }
                        this.first_unread_id = 0;
                    } else {
                        this.first_unread_id = fnid;
                        unread_to_load = ((Integer) args[6]).intValue();
                    }
                } else if (this.startLoadFromMessageId != 0 && (load_type == 3 || load_type == 4)) {
                    this.last_message_id = ((Integer) args[5]).intValue();
                }
                int newRowsCount = 0;
                if (!(load_type == 0 || (this.startLoadFromMessageId == 0 && this.last_message_id == 0))) {
                    this.forwardEndReached[loadIndex] = false;
                }
                if ((load_type == 1 || load_type == 3) && loadIndex == 1) {
                    boolean[] zArr = this.endReached;
                    this.cacheEndReached[0] = true;
                    zArr[0] = true;
                    this.forwardEndReached[0] = false;
                    this.minMessageId[0] = 0;
                }
                if (this.loadsCount == 1 && messArr.size() > 20) {
                    this.loadsCount++;
                }
                if (this.firstLoading) {
                    if (!this.forwardEndReached[loadIndex]) {
                        this.messages.clear();
                        this.messagesByDays.clear();
                        this.groupedMessagesMap.clear();
                        for (a = 0; a < 2; a++) {
                            this.messagesDict[a].clear();
                            if (this.currentEncryptedChat == null) {
                                this.maxMessageId[a] = Integer.MAX_VALUE;
                                this.minMessageId[a] = Integer.MIN_VALUE;
                            } else {
                                this.maxMessageId[a] = Integer.MIN_VALUE;
                                this.minMessageId[a] = Integer.MAX_VALUE;
                            }
                            this.maxDate[a] = Integer.MIN_VALUE;
                            this.minDate[a] = 0;
                        }
                    }
                    this.firstLoading = false;
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                        }
                    });
                }
                if (load_type == 1) {
                    Collections.reverse(messArr);
                }
                if (this.currentEncryptedChat == null) {
                    DataQuery.getInstance(this.currentAccount).loadReplyMessagesForMessages(messArr, this.dialog_id);
                }
                int approximateHeightSum = 0;
                if (load_type == 2 && messArr.isEmpty() && !isCache) {
                    this.forwardEndReached[0] = true;
                }
                LongSparseArray<GroupedMessages> newGroups = null;
                LongSparseArray<GroupedMessages> changedGroups = null;
                MediaController mediaController = MediaController.getInstance();
                a = 0;
                while (a < messArr.size()) {
                    obj = (MessageObject) messArr.get(a);
                    approximateHeightSum += obj.getApproximateHeight();
                    if (this.currentUser != null) {
                        if (this.currentUser.self) {
                            obj.messageOwner.out = true;
                        }
                        if ((this.currentUser.bot && obj.isOut()) || this.currentUser.id == currentUserId) {
                            obj.setIsRead();
                        }
                    }
                    if (this.messagesDict[loadIndex].indexOfKey(obj.getId()) < 0) {
                        if (mediaController.isPlayingMessage(obj)) {
                            MessageObject player = mediaController.getPlayingMessageObject();
                            obj.audioProgress = player.audioProgress;
                            obj.audioProgressSec = player.audioProgressSec;
                            obj.audioPlayerDuration = player.audioPlayerDuration;
                        }
                        if (loadIndex == 0 && ChatObject.isChannel(this.currentChat) && obj.getId() == 1) {
                            this.endReached[loadIndex] = true;
                            this.cacheEndReached[loadIndex] = true;
                        }
                        if (obj.getId() > 0) {
                            this.maxMessageId[loadIndex] = Math.min(obj.getId(), this.maxMessageId[loadIndex]);
                            this.minMessageId[loadIndex] = Math.max(obj.getId(), this.minMessageId[loadIndex]);
                        } else if (this.currentEncryptedChat != null) {
                            this.maxMessageId[loadIndex] = Math.max(obj.getId(), this.maxMessageId[loadIndex]);
                            this.minMessageId[loadIndex] = Math.min(obj.getId(), this.minMessageId[loadIndex]);
                        }
                        if (obj.messageOwner.date != 0) {
                            this.maxDate[loadIndex] = Math.max(this.maxDate[loadIndex], obj.messageOwner.date);
                            if (this.minDate[loadIndex] == 0 || obj.messageOwner.date < this.minDate[loadIndex]) {
                                this.minDate[loadIndex] = obj.messageOwner.date;
                            }
                        }
                        if (obj.getId() == this.last_message_id) {
                            this.forwardEndReached[loadIndex] = true;
                        }
                        if (obj.type >= 0 && !(loadIndex == 1 && (obj.messageOwner.action instanceof TLRPC$TL_messageActionChatMigrateTo))) {
                            TLRPC$Message dateMsg;
                            MessageObject dateObj;
                            if (!obj.isOut() && obj.isUnread()) {
                            }
                            this.messagesDict[loadIndex].put(obj.getId(), obj);
                            ArrayList<MessageObject> dayArray = (ArrayList) this.messagesByDays.get(obj.dateKey);
                            if (dayArray == null) {
                                dayArray = new ArrayList();
                                this.messagesByDays.put(obj.dateKey, dayArray);
                                dateMsg = new TLRPC$TL_message();
                                dateMsg.message = LocaleController.formatDateChat((long) obj.messageOwner.date);
                                dateMsg.id = 0;
                                dateMsg.date = obj.messageOwner.date;
                                dateObj = new MessageObject(this.currentAccount, dateMsg, false);
                                dateObj.type = 10;
                                dateObj.contentType = 1;
                                dateObj.isDateObject = true;
                                if (load_type == 1) {
                                    this.messages.add(0, dateObj);
                                } else {
                                    this.messages.add(dateObj);
                                }
                                newRowsCount++;
                            }
                            if (obj.hasValidGroupId()) {
                                groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(obj.getGroupIdForUse());
                                if (groupedMessages != null && this.messages.size() > 1) {
                                    MessageObject previous;
                                    if (load_type == 1) {
                                        previous = (MessageObject) this.messages.get(0);
                                    } else {
                                        previous = (MessageObject) this.messages.get(this.messages.size() - 2);
                                    }
                                    if (previous.getGroupIdForUse() == obj.getGroupIdForUse()) {
                                        if (previous.localGroupId != 0) {
                                            obj.localGroupId = previous.localGroupId;
                                            groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(previous.localGroupId);
                                        }
                                    } else if (previous.getGroupIdForUse() != obj.getGroupIdForUse()) {
                                        obj.localGroupId = Utilities.random.nextLong();
                                        groupedMessages = null;
                                    }
                                }
                                if (groupedMessages == null) {
                                    groupedMessages = new GroupedMessages();
                                    groupedMessages.groupId = obj.getGroupId();
                                    this.groupedMessagesMap.put(groupedMessages.groupId, groupedMessages);
                                } else if (newGroups == null || newGroups.indexOfKey(obj.getGroupId()) < 0) {
                                    if (changedGroups == null) {
                                        changedGroups = new LongSparseArray();
                                    }
                                    changedGroups.put(obj.getGroupId(), groupedMessages);
                                }
                                if (newGroups == null) {
                                    newGroups = new LongSparseArray();
                                }
                                newGroups.put(groupedMessages.groupId, groupedMessages);
                                if (load_type == 1) {
                                    groupedMessages.messages.add(obj);
                                } else {
                                    groupedMessages.messages.add(0, obj);
                                }
                            } else if (obj.getGroupIdForUse() != 0) {
                                obj.messageOwner.grouped_id = 0;
                                obj.localSentGroupId = 0;
                            }
                            newRowsCount++;
                            dayArray.add(obj);
                            if (load_type == 1) {
                                this.messages.add(0, obj);
                            } else {
                                this.messages.add(this.messages.size() - 1, obj);
                            }
                            MessageObject prevObj;
                            if (this.currentEncryptedChat == null) {
                                if (this.createUnreadMessageAfterId == 0 || load_type == 1 || a + 1 >= messArr.size()) {
                                    prevObj = null;
                                } else {
                                    prevObj = (MessageObject) messArr.get(a + 1);
                                    if (obj.isOut() || prevObj.getId() >= this.createUnreadMessageAfterId) {
                                        prevObj = null;
                                    }
                                }
                            } else if (this.createUnreadMessageAfterId == 0 || load_type == 1 || a - 1 < 0) {
                                prevObj = null;
                            } else {
                                prevObj = (MessageObject) messArr.get(a - 1);
                                if (obj.isOut() || prevObj.getId() >= this.createUnreadMessageAfterId) {
                                    prevObj = null;
                                }
                            }
                            if (load_type == 2 && obj.getId() == this.first_unread_id) {
                                if (approximateHeightSum > AndroidUtilities.displaySize.y / 2 || !this.forwardEndReached[0]) {
                                    dateMsg = new TLRPC$TL_message();
                                    dateMsg.message = "";
                                    dateMsg.id = 0;
                                    dateObj = new MessageObject(this.currentAccount, dateMsg, false);
                                    dateObj.type = 6;
                                    dateObj.contentType = 2;
                                    this.messages.add(this.messages.size() - 1, dateObj);
                                    this.unreadMessageObject = dateObj;
                                    this.scrollToMessage = this.unreadMessageObject;
                                    this.scrollToMessagePosition = -10000;
                                    newRowsCount++;
                                }
                            } else if ((load_type == 3 || load_type == 4) && obj.getId() == this.startLoadFromMessageId) {
                                if (this.needSelectFromMessageId) {
                                    this.highlightMessageId = obj.getId();
                                } else {
                                    this.highlightMessageId = Integer.MAX_VALUE;
                                }
                                this.scrollToMessage = obj;
                                this.startLoadFromMessageId = 0;
                                if (this.scrollToMessagePosition == -10000) {
                                    this.scrollToMessagePosition = -9000;
                                }
                            }
                            if (load_type != 2 && this.unreadMessageObject == null && this.createUnreadMessageAfterId != 0 && (((this.currentEncryptedChat == null && !obj.isOut() && obj.getId() >= this.createUnreadMessageAfterId) || !(this.currentEncryptedChat == null || obj.isOut() || obj.getId() > this.createUnreadMessageAfterId)) && (load_type == 1 || prevObj != null || (prevObj == null && createUnreadLoading && a == messArr.size() - 1)))) {
                                dateMsg = new TLRPC$TL_message();
                                dateMsg.message = "";
                                dateMsg.id = 0;
                                dateObj = new MessageObject(this.currentAccount, dateMsg, false);
                                dateObj.type = 6;
                                dateObj.contentType = 2;
                                if (load_type == 1) {
                                    this.messages.add(1, dateObj);
                                } else {
                                    this.messages.add(this.messages.size() - 1, dateObj);
                                }
                                this.unreadMessageObject = dateObj;
                                if (load_type == 3) {
                                    this.scrollToMessage = this.unreadMessageObject;
                                    this.startLoadFromMessageId = 0;
                                    this.scrollToMessagePosition = -9000;
                                }
                                newRowsCount++;
                            }
                        }
                    }
                    a++;
                }
                if (createUnreadLoading) {
                    this.createUnreadMessageAfterId = 0;
                }
                if (load_type == 0 && newRowsCount == 0) {
                    this.loadsCount--;
                }
                if (newGroups != null) {
                    a = 0;
                    while (a < newGroups.size()) {
                        groupedMessages = (GroupedMessages) newGroups.valueAt(a);
                        groupedMessages.calculate();
                        if (!(this.chatAdapter == null || changedGroups == null || changedGroups.indexOfKey(newGroups.keyAt(a)) < 0)) {
                            int idx = this.messages.indexOf((MessageObject) groupedMessages.messages.get(groupedMessages.messages.size() - 1));
                            if (idx >= 0) {
                                this.chatAdapter.notifyItemRangeChanged(this.chatAdapter.messagesStartRow + idx, groupedMessages.messages.size());
                            }
                        }
                        a++;
                    }
                }
                if (this.forwardEndReached[loadIndex] && loadIndex != 1) {
                    this.first_unread_id = 0;
                    this.last_message_id = 0;
                    this.createUnreadMessageAfterId = 0;
                }
                int firstVisPos;
                View firstVisView;
                int top;
                if (load_type == 1) {
                    int rowsRemoved = 0;
                    if (!(messArr.size() == count || (isCache && this.currentEncryptedChat == null && !this.forwardEndReached[loadIndex]))) {
                        this.forwardEndReached[loadIndex] = true;
                        if (loadIndex != 1) {
                            this.first_unread_id = 0;
                            this.last_message_id = 0;
                            this.createUnreadMessageAfterId = 0;
                            this.chatAdapter.notifyItemRemoved(this.chatAdapter.loadingDownRow);
                            rowsRemoved = 0 + 1;
                        }
                        this.startLoadFromMessageId = 0;
                    }
                    if (newRowsCount > 0) {
                        firstVisPos = this.chatLayoutManager.findFirstVisibleItemPosition();
                        if (firstVisPos == 0) {
                            firstVisPos++;
                        }
                        firstVisView = this.chatLayoutManager.findViewByPosition(firstVisPos);
                        top = firstVisView == null ? 0 : (this.chatListView.getMeasuredHeight() - firstVisView.getBottom()) - this.chatListView.getPaddingBottom();
                        this.chatAdapter.notifyItemRangeInserted(1, newRowsCount);
                        if (firstVisPos != -1) {
                            this.chatLayoutManager.scrollToPositionWithOffset((firstVisPos + newRowsCount) - rowsRemoved, top);
                        }
                    }
                    this.loadingForward = false;
                } else {
                    if (!(messArr.size() >= count || load_type == 3 || load_type == 4)) {
                        if (isCache) {
                            if (this.currentEncryptedChat != null || this.isBroadcast) {
                                this.endReached[loadIndex] = true;
                            }
                            if (load_type != 2) {
                                this.cacheEndReached[loadIndex] = true;
                            }
                        } else if (load_type != 2 || (messArr.size() == 0 && this.messages.isEmpty())) {
                            this.endReached[loadIndex] = true;
                        }
                    }
                    this.loading = false;
                    if (this.chatListView != null) {
                        if (this.first || this.scrollToTopOnResume || this.forceScrollToTop) {
                            this.forceScrollToTop = false;
                            this.chatAdapter.notifyDataSetChanged();
                            if (this.scrollToMessage != null) {
                                int yOffset;
                                boolean bottom = true;
                                if (this.startLoadFromMessageOffset != Integer.MAX_VALUE) {
                                    yOffset = (-this.startLoadFromMessageOffset) - this.chatListView.getPaddingBottom();
                                    this.startLoadFromMessageOffset = Integer.MAX_VALUE;
                                } else if (this.scrollToMessagePosition == -9000) {
                                    yOffset = getScrollOffsetForMessage(this.scrollToMessage);
                                    bottom = false;
                                } else if (this.scrollToMessagePosition == -10000) {
                                    yOffset = -AndroidUtilities.dp(11.0f);
                                    bottom = false;
                                } else {
                                    yOffset = this.scrollToMessagePosition;
                                }
                                if (!this.messages.isEmpty()) {
                                    if (this.messages.get(this.messages.size() - 1) == this.scrollToMessage || this.messages.get(this.messages.size() - 2) == this.scrollToMessage) {
                                        this.chatLayoutManager.scrollToPositionWithOffset(this.chatAdapter.loadingUpRow, yOffset, bottom);
                                    } else {
                                        this.chatLayoutManager.scrollToPositionWithOffset(this.chatAdapter.messagesStartRow + this.messages.indexOf(this.scrollToMessage), yOffset, bottom);
                                    }
                                }
                                this.chatListView.invalidate();
                                if (this.scrollToMessagePosition == -10000 || this.scrollToMessagePosition == -9000) {
                                    showPagedownButton(true, true);
                                    if (!(unread_to_load == 0 || this.prevSetUnreadCount == this.newUnreadMessageCount)) {
                                        this.prevSetUnreadCount = this.newUnreadMessageCount;
                                    }
                                }
                                this.scrollToMessagePosition = -10000;
                                this.scrollToMessage = null;
                            } else {
                                moveScrollToLastMessage();
                            }
                        } else if (newRowsCount != 0) {
                            boolean end = false;
                            if (this.endReached[loadIndex] && ((loadIndex == 0 && this.mergeDialogId == 0) || loadIndex == 1)) {
                                end = true;
                                this.chatAdapter.notifyItemRangeChanged(this.chatAdapter.loadingUpRow - 1, 2);
                                this.chatAdapter.updateRows();
                            }
                            firstVisPos = this.chatLayoutManager.findFirstVisibleItemPosition();
                            firstVisView = this.chatLayoutManager.findViewByPosition(firstVisPos);
                            top = firstVisView == null ? 0 : (this.chatListView.getMeasuredHeight() - firstVisView.getBottom()) - this.chatListView.getPaddingBottom();
                            if (newRowsCount - (end ? 1 : 0) > 0) {
                                int insertStart = this.chatAdapter.messagesEndRow;
                                this.chatAdapter.notifyItemChanged(this.chatAdapter.loadingUpRow);
                                this.chatAdapter.notifyItemRangeInserted(insertStart, newRowsCount - (end ? 1 : 0));
                            }
                            if (firstVisPos != -1) {
                                this.chatLayoutManager.scrollToPositionWithOffset(firstVisPos, top);
                            }
                        } else if (this.endReached[loadIndex] && ((loadIndex == 0 && this.mergeDialogId == 0) || loadIndex == 1)) {
                            this.chatAdapter.notifyItemRemoved(this.chatAdapter.loadingUpRow);
                        }
                        if (this.paused) {
                            this.scrollToTopOnResume = true;
                            if (this.scrollToMessage != null) {
                                this.scrollToTopUnReadOnResume = true;
                            }
                        }
                        if (this.first && this.chatListView != null) {
                            this.chatListView.setEmptyView(this.emptyViewContainer);
                        }
                    } else {
                        this.scrollToTopOnResume = true;
                        if (this.scrollToMessage != null) {
                            this.scrollToTopUnReadOnResume = true;
                        }
                    }
                }
                if (this.first && this.messages.size() > 0) {
                    this.first = false;
                }
                if (this.messages.isEmpty() && this.currentEncryptedChat == null && this.currentUser != null && this.currentUser.bot && this.botUser == null) {
                    this.botUser = "";
                }
                if (newRowsCount == 0 && this.currentEncryptedChat != null && !this.endReached[0]) {
                    this.first = true;
                    if (this.chatListView != null) {
                        this.chatListView.setEmptyView(null);
                    }
                    if (this.emptyViewContainer != null) {
                        this.emptyViewContainer.setVisibility(4);
                    }
                } else if (this.progressView != null) {
                    this.progressView.setVisibility(4);
                }
                checkScrollForLoad(false);
            }
        }
    }

    private void checkAndUpdateAvatar() {
        TLObject newPhoto = null;
        Drawable avatarDrawable = null;
        if (this.currentChat != null) {
            TLRPC$Chat chat = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(this.currentChat.id));
            if (chat != null) {
                this.currentChat = chat;
                if (this.currentChat.photo != null) {
                    newPhoto = this.currentChat.photo.photo_small;
                }
                avatarDrawable = new AvatarDrawable(this.currentChat);
            } else {
                return;
            }
        } else if (this.currentUser != null) {
            User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.currentUser.id));
            if (user != null) {
                this.currentUser = user;
                if (this.currentUser.photo != null) {
                    newPhoto = this.currentUser.photo.photo_small;
                }
                avatarDrawable = new AvatarDrawable(this.currentUser);
            } else {
                return;
            }
        }
        if (this.avatarImageView != null) {
            this.avatarImageView.setImage(newPhoto, "50_50", avatarDrawable);
        }
    }

    private void updateSubtitle() {
        if (this.actionBar != null) {
            if (this.currentChat != null) {
                this.nameTextView.setText(this.currentChat.title);
                if (ChatObject.isChannel(this.currentChat)) {
                    if (this.info != null) {
                        int[] result = new int[1];
                        String shortNumber = LocaleController.formatShortNumber(this.info.participants_count, result);
                        this.onlineTextView.setText(LocaleController.formatPluralString("Members", result[0]).replace(String.format("%d", new Object[]{Integer.valueOf(result[0])}), shortNumber));
                    } else if (this.currentChat.megagroup) {
                        this.onlineTextView.setText(LocaleController.getString("Loading", R.string.Loading).toLowerCase());
                    } else if ((this.currentChat.flags & 64) != 0) {
                        this.onlineTextView.setText(LocaleController.getString("ChannelPublic", R.string.ChannelPublic).toLowerCase());
                    } else {
                        this.onlineTextView.setText(LocaleController.getString("ChannelPrivate", R.string.ChannelPrivate).toLowerCase());
                    }
                } else if (ChatObject.isKickedFromChat(this.currentChat)) {
                    this.onlineTextView.setText(LocaleController.getString("YouWereKicked", R.string.YouWereKicked));
                } else if (ChatObject.isLeftFromChat(this.currentChat)) {
                    this.onlineTextView.setText(LocaleController.getString("YouLeft", R.string.YouLeft));
                } else {
                    int count = this.currentChat.participants_count;
                    if (!(this.info == null || this.info.participants == null)) {
                        count = this.info.participants.participants.size();
                    }
                    this.onlineTextView.setText(LocaleController.formatPluralString("Members", count));
                }
            } else if (this.currentUser != null) {
                if (this.currentUser.id / 1000 == 777 || this.currentUser.id / 1000 == 333 || ContactsController.getInstance(this.currentAccount).contactsDict.get(Integer.valueOf(this.currentUser.id)) != null || (ContactsController.getInstance(this.currentAccount).contactsDict.size() == 0 && ContactsController.getInstance(this.currentAccount).isLoadingContacts())) {
                    this.nameTextView.setText(UserObject.getUserName(this.currentUser));
                } else if (this.currentUser.phone == null || this.currentUser.phone.length() == 0) {
                    this.nameTextView.setText(UserObject.getUserName(this.currentUser));
                } else {
                    this.nameTextView.setText(PhoneFormat.getInstance().format("+" + this.currentUser.phone));
                }
                CharSequence printString = (CharSequence) MessagesController.getInstance(this.currentAccount).printingStrings.get(this.dialog_id);
                if (printString == null || printString.length() == 0) {
                    this.lastPrintString = null;
                    User user = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(this.currentUser.id));
                    if (user != null) {
                        this.currentUser = user;
                    }
                    this.onlineTextView.setText(LocaleController.formatUserStatus(this.currentAccount, this.currentUser));
                    return;
                }
                this.lastPrintString = printString;
                this.onlineTextView.setText(printString);
            }
        }
    }

    private int getScrollOffsetForMessage(MessageObject object) {
        int offset = Integer.MAX_VALUE;
        GroupedMessages groupedMessages = getValidGroupedMessage(object);
        if (groupedMessages != null) {
            float itemHeight;
            GroupedMessagePosition currentPosition = (GroupedMessagePosition) groupedMessages.positions.get(object);
            float maxH = ((float) Math.max(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y)) * 0.5f;
            if (currentPosition.siblingHeights != null) {
                itemHeight = currentPosition.siblingHeights[0];
            } else {
                itemHeight = currentPosition.ph;
            }
            float totalHeight = 0.0f;
            float moveDiff = 0.0f;
            SparseBooleanArray array = new SparseBooleanArray();
            for (int a = 0; a < groupedMessages.posArray.size(); a++) {
                GroupedMessagePosition pos = (GroupedMessagePosition) groupedMessages.posArray.get(a);
                if (array.indexOfKey(pos.minY) < 0 && pos.siblingHeights == null) {
                    array.put(pos.minY, true);
                    if (pos.minY < currentPosition.minY) {
                        moveDiff -= pos.ph;
                    } else if (pos.minY > currentPosition.minY) {
                        moveDiff += pos.ph;
                    }
                    totalHeight += pos.ph;
                }
            }
            if (Math.abs(totalHeight - itemHeight) < 0.02f) {
                offset = ((((int) (((float) this.chatListView.getMeasuredHeight()) - (totalHeight * maxH))) / 2) - this.chatListView.getPaddingTop()) - AndroidUtilities.dp(7.0f);
            } else {
                offset = ((((int) (((float) this.chatListView.getMeasuredHeight()) - ((itemHeight + moveDiff) * maxH))) / 2) - this.chatListView.getPaddingTop()) - AndroidUtilities.dp(7.0f);
            }
        }
        if (offset == Integer.MAX_VALUE) {
            offset = (this.chatListView.getMeasuredHeight() - object.getApproximateHeight()) / 2;
        }
        return Math.max(0, offset);
    }

    public boolean checkTransitionAnimation() {
        if (this.animationInProgress && this.animationStartTime < System.currentTimeMillis() - 400) {
            this.animationInProgress = false;
            if (this.onAnimationEndRunnable != null) {
                this.onAnimationEndRunnable.run();
                this.onAnimationEndRunnable = null;
            }
        }
        return this.animationInProgress;
    }

    private void clearChatData() {
        this.messages.clear();
        this.messagesByDays.clear();
        this.waitingForLoad.clear();
        this.progressView.setVisibility(this.chatAdapter.botInfoRow == -1 ? 0 : 4);
        this.chatListView.setEmptyView(null);
        for (int a = 0; a < 2; a++) {
            this.messagesDict[a].clear();
            this.maxMessageId[a] = Integer.MIN_VALUE;
            this.minMessageId[a] = Integer.MAX_VALUE;
            this.maxDate[a] = Integer.MIN_VALUE;
            this.minDate[a] = 0;
            this.endReached[a] = false;
            this.cacheEndReached[a] = false;
            this.forwardEndReached[a] = true;
        }
        this.first = true;
        this.firstLoading = true;
        this.loading = true;
        this.loadingForward = false;
        this.waitingForReplyMessageLoad = false;
        this.startLoadFromMessageId = 0;
        this.last_message_id = 0;
        this.createUnreadMessageAfterId = 0;
        this.createUnreadMessageAfterIdLoading = false;
        this.needSelectFromMessageId = false;
        this.chatAdapter.notifyDataSetChanged();
    }

    private void hideFloatingDateView(boolean animated) {
        if (this.floatingDateView.getTag() != null && !this.currentFloatingDateOnScreen) {
            if (!this.scrollingFloatingDate || this.currentFloatingTopIsNotMessage) {
                this.floatingDateView.setTag(null);
                if (animated) {
                    this.floatingDateAnimation = new AnimatorSet();
                    this.floatingDateAnimation.setDuration(150);
                    AnimatorSet animatorSet = this.floatingDateAnimation;
                    Animator[] animatorArr = new Animator[1];
                    animatorArr[0] = ObjectAnimator.ofFloat(this.floatingDateView, "alpha", new float[]{0.0f});
                    animatorSet.playTogether(animatorArr);
                    this.floatingDateAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            if (animation.equals(ChatPreviewActivity.this.floatingDateAnimation)) {
                                ChatPreviewActivity.this.floatingDateAnimation = null;
                            }
                        }
                    });
                    this.floatingDateAnimation.setStartDelay(500);
                    this.floatingDateAnimation.start();
                    return;
                }
                if (this.floatingDateAnimation != null) {
                    this.floatingDateAnimation.cancel();
                    this.floatingDateAnimation = null;
                }
                this.floatingDateView.setAlpha(0.0f);
            }
        }
    }

    private void checkScrollForLoad(boolean scroll) {
        if (this.chatLayoutManager != null && !this.paused) {
            int firstVisibleItem = this.chatLayoutManager.findFirstVisibleItemPosition();
            int visibleItemCount = firstVisibleItem == -1 ? 0 : Math.abs(this.chatLayoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
            if (visibleItemCount > 0 || this.currentEncryptedChat != null) {
                int checkLoadCount;
                MessagesController instance;
                long j;
                int i;
                int i2;
                int i3;
                boolean isChannel;
                int i4;
                int totalItemCount = this.chatAdapter.getItemCount();
                if (scroll) {
                    checkLoadCount = 25;
                } else {
                    checkLoadCount = 5;
                }
                if ((totalItemCount - firstVisibleItem) - visibleItemCount <= checkLoadCount && !this.loading) {
                    boolean z;
                    if (!this.endReached[0]) {
                        this.loading = true;
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        if (this.messagesByDays.size() != 0) {
                            instance = MessagesController.getInstance(this.currentAccount);
                            j = this.dialog_id;
                            i = this.maxMessageId[0];
                            z = !this.cacheEndReached[0];
                            i2 = this.minDate[0];
                            i3 = this.classGuid;
                            isChannel = ChatObject.isChannel(this.currentChat);
                            i4 = this.lastLoadIndex;
                            this.lastLoadIndex = i4 + 1;
                            instance.loadMessages(j, 50, i, 0, z, i2, i3, 0, 0, isChannel, i4);
                        } else {
                            instance = MessagesController.getInstance(this.currentAccount);
                            j = this.dialog_id;
                            z = !this.cacheEndReached[0];
                            i2 = this.minDate[0];
                            i3 = this.classGuid;
                            isChannel = ChatObject.isChannel(this.currentChat);
                            i4 = this.lastLoadIndex;
                            this.lastLoadIndex = i4 + 1;
                            instance.loadMessages(j, 50, 0, 0, z, i2, i3, 0, 0, isChannel, i4);
                        }
                    } else if (!(this.mergeDialogId == 0 || this.endReached[1])) {
                        this.loading = true;
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance(this.currentAccount);
                        j = this.mergeDialogId;
                        i = this.maxMessageId[1];
                        z = !this.cacheEndReached[1];
                        i2 = this.minDate[1];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, z, i2, i3, 0, 0, isChannel, i4);
                    }
                }
                if (visibleItemCount > 0 && !this.loadingForward && firstVisibleItem <= 10) {
                    if (this.mergeDialogId != 0 && !this.forwardEndReached[1]) {
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance(this.currentAccount);
                        j = this.mergeDialogId;
                        i = this.minMessageId[1];
                        i2 = this.maxDate[1];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, true, i2, i3, 1, 0, isChannel, i4);
                        this.loadingForward = true;
                    } else if (!this.forwardEndReached[0]) {
                        this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
                        instance = MessagesController.getInstance(this.currentAccount);
                        j = this.dialog_id;
                        i = this.minMessageId[0];
                        i2 = this.maxDate[0];
                        i3 = this.classGuid;
                        isChannel = ChatObject.isChannel(this.currentChat);
                        i4 = this.lastLoadIndex;
                        this.lastLoadIndex = i4 + 1;
                        instance.loadMessages(j, 50, i, 0, true, i2, i3, 1, 0, isChannel, i4);
                        this.loadingForward = true;
                    }
                }
            }
        }
    }

    private void scrollToLastMessage(boolean pagedown) {
        if (!this.forwardEndReached[0] || this.first_unread_id != 0 || this.startLoadFromMessageId != 0) {
            clearChatData();
            this.waitingForLoad.add(Integer.valueOf(this.lastLoadIndex));
            MessagesController instance = MessagesController.getInstance(this.currentAccount);
            long j = this.dialog_id;
            int i = this.classGuid;
            boolean isChannel = ChatObject.isChannel(this.currentChat);
            int i2 = this.lastLoadIndex;
            this.lastLoadIndex = i2 + 1;
            instance.loadMessages(j, 30, 0, 0, true, 0, i, 0, 0, isChannel, i2);
        } else if (pagedown && this.chatLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            showPagedownButton(false, true);
            this.highlightMessageId = Integer.MAX_VALUE;
            updateVisibleRows();
        } else {
            this.chatLayoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    private void updateMessagesVisisblePart() {
        if (this.chatListView != null) {
            MessageObject messageObject;
            int count = this.chatListView.getChildCount();
            int height = this.chatListView.getMeasuredHeight();
            int minPositionHolder = Integer.MAX_VALUE;
            int minPositionDateHolder = Integer.MAX_VALUE;
            View minDateChild = null;
            View minChild = null;
            View minMessageChild = null;
            boolean foundTextureViewMessage = false;
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                if (view instanceof ChatMessageCell) {
                    int viewTop;
                    ChatMessageCell messageCell = (ChatMessageCell) view;
                    int top = messageCell.getTop();
                    int bottom = messageCell.getBottom();
                    if (top >= 0) {
                        viewTop = 0;
                    } else {
                        viewTop = -top;
                    }
                    int viewBottom = messageCell.getMeasuredHeight();
                    if (viewBottom > height) {
                        viewBottom = viewTop + height;
                    }
                    messageCell.setVisiblePart(viewTop, viewBottom - viewTop);
                    messageObject = messageCell.getMessageObject();
                    if (this.roundVideoContainer != null && messageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(messageObject)) {
                        ImageReceiver imageReceiver = messageCell.getPhotoImage();
                        this.roundVideoContainer.setTranslationX((float) imageReceiver.getImageX());
                        this.roundVideoContainer.setTranslationY((float) (((this.contentView.getPaddingTop() + top) + imageReceiver.getImageY()) - 0));
                        this.contentView.invalidate();
                        this.roundVideoContainer.invalidate();
                        foundTextureViewMessage = true;
                    }
                }
                if (view.getBottom() > this.chatListView.getPaddingTop()) {
                    int position = view.getBottom();
                    if (position < minPositionHolder) {
                        minPositionHolder = position;
                        if ((view instanceof ChatMessageCell) || (view instanceof ChatActionCell)) {
                            minMessageChild = view;
                        }
                        minChild = view;
                    }
                    if ((view instanceof ChatActionCell) && ((ChatActionCell) view).getMessageObject().isDateObject) {
                        if (view.getAlpha() != 1.0f) {
                            view.setAlpha(1.0f);
                        }
                        if (position < minPositionDateHolder) {
                            minPositionDateHolder = position;
                            minDateChild = view;
                        }
                    }
                }
            }
            if (this.roundVideoContainer != null) {
                if (foundTextureViewMessage) {
                    MediaController.getInstance().setCurrentRoundVisible(true);
                } else {
                    this.roundVideoContainer.setTranslationY((float) ((-AndroidUtilities.roundMessageSize) - 100));
                    this.contentView.invalidate();
                    messageObject = MediaController.getInstance().getPlayingMessageObject();
                    if (messageObject != null && messageObject.isRoundVideo() && messageObject.eventId == 0 && this.checkTextureViewPosition) {
                        MediaController.getInstance().setCurrentRoundVisible(false);
                    }
                }
            }
            if (minMessageChild != null) {
                if (minMessageChild instanceof ChatMessageCell) {
                    messageObject = ((ChatMessageCell) minMessageChild).getMessageObject();
                } else {
                    messageObject = ((ChatActionCell) minMessageChild).getMessageObject();
                }
                this.floatingDateView.setCustomDate(messageObject.messageOwner.date);
            }
            this.currentFloatingDateOnScreen = false;
            boolean z = ((minChild instanceof ChatMessageCell) || (minChild instanceof ChatActionCell)) ? false : true;
            this.currentFloatingTopIsNotMessage = z;
            if (minDateChild != null) {
                if (minDateChild.getTop() > this.chatListView.getPaddingTop() || this.currentFloatingTopIsNotMessage) {
                    if (minDateChild.getAlpha() != 1.0f) {
                        minDateChild.setAlpha(1.0f);
                    }
                    if (this.currentFloatingTopIsNotMessage) {
                        z = false;
                    } else {
                        z = true;
                    }
                    hideFloatingDateView(z);
                } else {
                    if (minDateChild.getAlpha() != 0.0f) {
                        minDateChild.setAlpha(0.0f);
                    }
                    if (this.floatingDateAnimation != null) {
                        this.floatingDateAnimation.cancel();
                        this.floatingDateAnimation = null;
                    }
                    if (this.floatingDateView.getTag() == null) {
                        this.floatingDateView.setTag(Integer.valueOf(1));
                    }
                    if (this.floatingDateView.getAlpha() != 1.0f) {
                        this.floatingDateView.setAlpha(1.0f);
                    }
                    this.currentFloatingDateOnScreen = true;
                }
                int offset = minDateChild.getBottom() - this.chatListView.getPaddingTop();
                if (offset <= this.floatingDateView.getMeasuredHeight() || offset >= this.floatingDateView.getMeasuredHeight() * 2) {
                    this.floatingDateView.setTranslationY(0.0f);
                    return;
                } else {
                    this.floatingDateView.setTranslationY((float) (((-this.floatingDateView.getMeasuredHeight()) * 2) + offset));
                    return;
                }
            }
            hideFloatingDateView(true);
            this.floatingDateView.setTranslationY(0.0f);
        }
    }

    private void showPagedownButton(boolean show, boolean animated) {
        if (this.pagedownButton != null) {
            if (show) {
                this.pagedownButtonShowedByScroll = false;
                if (this.pagedownButton.getTag() == null) {
                    if (this.pagedownButtonAnimation != null) {
                        this.pagedownButtonAnimation.cancel();
                        this.pagedownButtonAnimation = null;
                    }
                    if (animated) {
                        if (this.pagedownButton.getTranslationY() == 0.0f) {
                            this.pagedownButton.setTranslationY((float) AndroidUtilities.dp(100.0f));
                        }
                        this.pagedownButton.setVisibility(0);
                        this.pagedownButton.setTag(Integer.valueOf(1));
                        this.pagedownButtonAnimation = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{0.0f}).setDuration(200);
                        this.pagedownButtonAnimation.start();
                        return;
                    }
                    this.pagedownButton.setVisibility(0);
                    return;
                }
                return;
            }
            this.returnToMessageId = 0;
            this.newUnreadMessageCount = 0;
            if (this.pagedownButton.getTag() != null) {
                this.pagedownButton.setTag(null);
                if (this.pagedownButtonAnimation != null) {
                    this.pagedownButtonAnimation.cancel();
                    this.pagedownButtonAnimation = null;
                }
                if (animated) {
                    this.pagedownButtonAnimation = ObjectAnimator.ofFloat(this.pagedownButton, "translationY", new float[]{(float) AndroidUtilities.dp(100.0f)}).setDuration(200);
                    this.pagedownButtonAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            ChatPreviewActivity.this.pagedownButton.setVisibility(4);
                        }
                    });
                    this.pagedownButtonAnimation.start();
                    return;
                }
                this.pagedownButton.setVisibility(4);
            }
        }
    }

    private void moveScrollToLastMessage() {
        if (this.chatListView != null && !this.messages.isEmpty()) {
            this.chatLayoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    private void updateVisibleRows() {
        if (this.chatListView != null) {
            int lastVisibleItem = -1;
            if (!(this.unreadMessageObject == null || this.chatListView.getMeasuredHeight() == 0)) {
                int pos = this.messages.indexOf(this.unreadMessageObject);
                if (pos >= 0) {
                    lastVisibleItem = this.chatAdapter.messagesStartRow + pos;
                }
            }
            int count = this.chatListView.getChildCount();
            for (int a = 0; a < count; a++) {
                View view = this.chatListView.getChildAt(a);
                if (view instanceof ChatMessageCell) {
                    ChatMessageCell cell = (ChatMessageCell) view;
                    MessageObject messageObject = cell.getMessageObject();
                    boolean disableSelection = false;
                    boolean selected = false;
                    if (this.actionBar.isActionModeShowed()) {
                        int idx = messageObject.getDialogId() == this.dialog_id ? 0 : 1;
                        if (messageObject == null || this.selectedMessagesIds[idx].indexOfKey(messageObject.getId()) >= 0) {
                            selected = true;
                        } else {
                            view.setBackgroundDrawable(null);
                        }
                        disableSelection = true;
                    } else {
                        view.setBackgroundDrawable(null);
                    }
                    cell.setMessageObject(cell.getMessageObject(), cell.getCurrentMessagesGroup(), cell.isPinnedBottom(), cell.isPinnedTop());
                    boolean z = !disableSelection;
                    boolean z2 = disableSelection && selected;
                    cell.setCheckPressed(z, z2);
                    z2 = (this.highlightMessageId == Integer.MAX_VALUE || messageObject == null || messageObject.getId() != this.highlightMessageId) ? false : true;
                    cell.setHighlighted(z2);
                    cell.setHighlightedText(null);
                } else if (view instanceof ChatActionCell) {
                    ChatActionCell cell2 = (ChatActionCell) view;
                    cell2.setMessageObject(cell2.getMessageObject());
                }
            }
            this.chatListView.invalidate();
            if (lastVisibleItem != -1) {
                this.chatLayoutManager.scrollToPositionWithOffset(lastVisibleItem, ((this.chatListView.getMeasuredHeight() - this.chatListView.getPaddingBottom()) - this.chatListView.getPaddingTop()) - AndroidUtilities.dp(29.0f));
            }
        }
    }

    private GroupedMessages getValidGroupedMessage(MessageObject message) {
        if (message.getGroupId() == 0) {
            return null;
        }
        GroupedMessages groupedMessages = (GroupedMessages) this.groupedMessagesMap.get(message.getGroupId());
        if (groupedMessages == null) {
            return groupedMessages;
        }
        if (groupedMessages.messages.size() <= 1 || groupedMessages.positions.get(message) == null) {
            return null;
        }
        return groupedMessages;
    }
}
