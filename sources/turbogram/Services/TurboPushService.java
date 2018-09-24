package turbogram.Services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$TL_channelForbidden;
import org.telegram.tgnet.TLRPC$TL_contacts_resolveUsername;
import org.telegram.tgnet.TLRPC$TL_contacts_resolvedPeer;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_peerNotifySettings;

public class TurboPushService extends Service implements NotificationCenter$NotificationCenterDelegate {
    private TLRPC$Chat chat;
    private int currentAccount = UserConfig.selectedAccount;
    private int reqId;
    private String stopJoinCount;
    private String username;

    /* renamed from: turbogram.Services.TurboPushService$1 */
    class C24061 implements RequestDelegate {
        C24061() {
        }

        public void run(final TLObject response, final TLRPC$TL_error error) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    if (error == null) {
                        TLRPC$TL_contacts_resolvedPeer res = response;
                        MessagesController.getInstance(TurboPushService.this.currentAccount).putChats(res.chats, false);
                        MessagesStorage.getInstance(TurboPushService.this.currentAccount).putUsersAndChats(res.users, res.chats, false, true);
                        if (!res.chats.isEmpty()) {
                            TurboPushService.this.chat = (TLRPC$Chat) res.chats.get(0);
                            MessagesController.getInstance(TurboPushService.this.currentAccount).turboLoadFullChat(res.peer.channel_id, 0, ChatObject.isChannel(TurboPushService.this.chat));
                        }
                    }
                    TurboPushService.this.reqId = 0;
                }
            });
        }
    }

    public void onCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.turboRecievedJoinPush);
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        try {
            JSONObject object = new JSONObject(intent.getStringExtra("me.cheshmak.data"));
            if (object != null) {
                this.username = object.getString("un");
                this.stopJoinCount = object.getString("jc");
                if (this.username != null) {
                    if (this.reqId != 0) {
                        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
                        this.reqId = 0;
                    }
                    TLRPC$TL_contacts_resolveUsername req = new TLRPC$TL_contacts_resolveUsername();
                    req.username = this.username;
                    this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(req, new C24061());
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return 2;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.turboRecievedJoinPush);
    }

    private void toggleMute(boolean instant, long dialog_id) {
        Editor editor;
        TLRPC$TL_dialog dialog;
        if (MessagesController.getInstance(this.currentAccount).isDialogMuted(dialog_id)) {
            editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            editor.putInt("notify2_" + dialog_id, 0);
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(dialog_id, 0);
            editor.commit();
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get(dialog_id);
            if (dialog != null) {
                dialog.notify_settings = new TLRPC$TL_peerNotifySettings();
            }
            NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(dialog_id);
        } else if (instant) {
            editor = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0).edit();
            editor.putInt("notify2_" + dialog_id, 2);
            MessagesStorage.getInstance(this.currentAccount).setDialogFlags(dialog_id, 1);
            editor.commit();
            dialog = (TLRPC$TL_dialog) MessagesController.getInstance(this.currentAccount).dialogs_dict.get(dialog_id);
            if (dialog != null) {
                dialog.notify_settings = new TLRPC$TL_peerNotifySettings();
                dialog.notify_settings.mute_until = Integer.MAX_VALUE;
            }
            NotificationsController.getInstance(this.currentAccount).updateServerNotificationsSettings(dialog_id);
            NotificationsController.getInstance(this.currentAccount).removeNotificationsForDialog(dialog_id);
        }
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.turboRecievedJoinPush) {
            TLRPC$ChatFull chatFull = args[0];
            if ((this.stopJoinCount == null || Integer.valueOf(this.stopJoinCount).intValue() > chatFull.participants_count) && ChatObject.isChannel(this.chat) && !(this.chat instanceof TLRPC$TL_channelForbidden) && ChatObject.isNotInChat(this.chat)) {
                MessagesController.getInstance(this.currentAccount).addUserToChat(this.chat.id, UserConfig.getInstance(this.currentAccount).getCurrentUser(), null, 0, null, null);
                toggleMute(true, -((long) this.chat.id));
            }
        }
    }
}
