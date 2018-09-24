package turbogram.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.DownloadController$FileDownloadProgressListener;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_messageMediaEmpty;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC.User;
import turbogram.Utilities.TurboConfig$BG;

public class DownloadReceiver extends WakefulBroadcastReceiver implements DownloadController$FileDownloadProgressListener {
    private int currentAccount;
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    PendingIntent mPendingIntent_end;
    ArrayList<MessageObject> messageObjects;

    public DownloadReceiver() {
        this.currentAccount = UserConfig.selectedAccount;
        this.messageObjects = new ArrayList();
        this.messageObjects = DM_LoadMessages();
    }

    public void onReceive(Context context, Intent intent) {
        if (TurboConfig$BG.downloadReceiver) {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (intent.getIntExtra("start_end", 0) == 1000) {
                if (TurboConfig$BG.enableWifi) {
                    wifiManager.setWifiEnabled(true);
                }
                WakeLocker.acquire(context);
                startDownloading(this.messageObjects);
                return;
            }
            if (TurboConfig$BG.disableWifi) {
                wifiManager.setWifiEnabled(false);
            }
            stopDownloading(this.messageObjects);
            WakeLocker.release();
        }
    }

    public void setAlarm(Context context, Calendar calendar, Calendar calendar2, int i) {
        Intent intent = new Intent(context, DownloadReceiver.class);
        intent.putExtra("Reminder_ID", 100);
        intent.putExtra("start_end", 1000);
        this.mPendingIntent = PendingIntent.getBroadcast(context, 100, intent, 134217728);
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.mAlarmManager.set(2, (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) + SystemClock.elapsedRealtime(), this.mPendingIntent);
        Intent intent2 = new Intent(context, DownloadReceiver.class);
        intent.putExtra("Reminder_ID", Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        intent.putExtra("start_end", 900);
        this.mPendingIntent_end = PendingIntent.getBroadcast(context, Callback.DEFAULT_DRAG_ANIMATION_DURATION, intent2, 134217728);
        this.mAlarmManager.set(2, (calendar2.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) + SystemClock.elapsedRealtime(), this.mPendingIntent_end);
    }

    public void setRepeatAlarm(Context context, Calendar calendar, Calendar calendar2, int i) {
        Intent intent = new Intent(context, DownloadReceiver.class);
        intent.putExtra("start_end", 1000);
        this.mPendingIntent = PendingIntent.getBroadcast(context, i, intent, 134217728);
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.mAlarmManager.setRepeating(2, (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) + SystemClock.elapsedRealtime(), 604800000, this.mPendingIntent);
        intent = new Intent(context, DownloadReceiver.class);
        intent.putExtra("start_end", 900);
        this.mPendingIntent_end = PendingIntent.getBroadcast(context, i + 10, intent, 134217728);
        this.mAlarmManager.setRepeating(2, (calendar2.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) + SystemClock.elapsedRealtime(), 604800000, this.mPendingIntent_end);
    }

    public void cancelAlarm(Context context) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.mPendingIntent = PendingIntent.getBroadcast(context, 100, new Intent(context, DownloadReceiver.class), 0);
        this.mAlarmManager.cancel(this.mPendingIntent);
        this.mPendingIntent_end = PendingIntent.getBroadcast(context, Callback.DEFAULT_DRAG_ANIMATION_DURATION, new Intent(context, DownloadReceiver.class), 0);
        this.mAlarmManager.cancel(this.mPendingIntent_end);
        for (int i = 1; i < 8; i++) {
            this.mPendingIntent = PendingIntent.getBroadcast(context, i + 300, new Intent(context, DownloadReceiver.class), 0);
            this.mAlarmManager.cancel(this.mPendingIntent);
            this.mPendingIntent_end = PendingIntent.getBroadcast(context, (i + 300) + 10, new Intent(context, DownloadReceiver.class), 0);
            this.mAlarmManager.cancel(this.mPendingIntent_end);
        }
    }

    public TLObject getDownloadObject(MessageObject messageObject) {
        TLRPC$MessageMedia media = messageObject.messageOwner.media;
        if (media != null) {
            if (media.document != null) {
                return media.document;
            }
            if (media.webpage != null && media.webpage.document != null) {
                return media.webpage.document;
            }
            if (media.webpage != null && media.webpage.photo != null) {
                return FileLoader.getClosestPhotoSizeWithSize(media.webpage.photo.sizes, AndroidUtilities.getPhotoSize());
            }
            if (media.photo != null) {
                return FileLoader.getClosestPhotoSizeWithSize(media.photo.sizes, AndroidUtilities.getPhotoSize());
            }
        }
        return new TLRPC$TL_messageMediaEmpty();
    }

    private void loadFile(TLObject attach) {
        if (attach instanceof TLRPC$PhotoSize) {
            FileLoader.getInstance(this.currentAccount).loadFile((TLRPC$PhotoSize) attach, null, 0);
        } else if (attach instanceof TLRPC$Document) {
            FileLoader.getInstance(this.currentAccount).loadFile((TLRPC$Document) attach, true, 0);
        }
    }

    private void startDownloading(final ArrayList<MessageObject> messageObjects) {
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new Runnable() {

            /* renamed from: turbogram.Services.DownloadReceiver$1$1 */
            class C24021 implements Runnable {
                C24021() {
                }

                public void run() {
                    TurboConfig$BG.setBooleanValue("download_running", true);
                    Iterator it = messageObjects.iterator();
                    while (it.hasNext()) {
                        MessageObject messageObject = (MessageObject) it.next();
                        TLObject attach = DownloadReceiver.this.getDownloadObject(messageObject);
                        DownloadReceiver.this.loadFile(attach);
                        File pathToMessage = FileLoader.getPathToMessage(messageObject.messageOwner);
                        if (pathToMessage != null && !pathToMessage.exists()) {
                            DownloadController.getInstance(DownloadReceiver.this.currentAccount).addLoadingFileObserver(FileLoader.getAttachFileName(attach), DownloadReceiver.this);
                            return;
                        }
                    }
                }
            }

            public void run() {
                AndroidUtilities.runOnUIThread(new C24021());
            }
        });
    }

    private void stopDownloading(ArrayList<MessageObject> messageObjects) {
        TurboConfig$BG.setBooleanValue("download_running", false);
        for (int i = 0; i < messageObjects.size(); i++) {
            MessageObject messageObject = (MessageObject) messageObjects.get(i);
            if (messageObject != null) {
                TLObject attach = getDownloadObject(messageObject);
                if (attach instanceof TLRPC$PhotoSize) {
                    FileLoader.getInstance(this.currentAccount).cancelLoadFile((TLRPC$PhotoSize) attach);
                } else if (attach instanceof TLRPC$Document) {
                    FileLoader.getInstance(this.currentAccount).cancelLoadFile((TLRPC$Document) attach);
                }
            }
        }
    }

    public ArrayList<MessageObject> DM_LoadMessages() {
        final ArrayList<MessageObject> objects = new ArrayList();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new Runnable() {
            public void run() {
                AbstractMap usersDict;
                AbstractMap chatsDict;
                int a;
                TLRPC$TL_messages_messages res = new TLRPC$TL_messages_messages();
                SQLiteCursor cursor = null;
                try {
                    cursor = MessagesStorage.getInstance(DownloadReceiver.this.currentAccount).getDatabase().queryFinalized(String.format(Locale.US, "SELECT * FROM turbo_idm ORDER BY date DESC", new Object[0]), new Object[0]);
                    while (cursor.next()) {
                        NativeByteBuffer data = cursor.byteBufferValue(3);
                        if (data != null) {
                            TLRPC$Message message = TLRPC$Message.TLdeserialize(data, data.readInt32(false), false);
                            data.reuse();
                            message.id = cursor.intValue(0);
                            message.dialog_id = (long) cursor.intValue(1);
                            message.date = cursor.intValue(2);
                            res.messages.add(message);
                        }
                    }
                    if (cursor != null) {
                        cursor.dispose();
                    }
                } catch (Exception e) {
                    FileLog.e("tmessages", e);
                    usersDict = new ConcurrentHashMap();
                    chatsDict = new ConcurrentHashMap();
                    for (a = 0; a < res.users.size(); a++) {
                        User u = (User) res.users.get(a);
                        usersDict.put(Integer.valueOf(u.id), u);
                    }
                    for (a = 0; a < res.chats.size(); a++) {
                        TLRPC$Chat c = (TLRPC$Chat) res.chats.get(a);
                        chatsDict.put(Integer.valueOf(c.id), c);
                    }
                    for (a = 0; a < res.messages.size(); a++) {
                        objects.add(new MessageObject(DownloadReceiver.this.currentAccount, (TLRPC$Message) res.messages.get(a), usersDict, chatsDict, true));
                    }
                } finally {
                    if (cursor != null) {
                        cursor.dispose();
                    }
                }
                usersDict = new ConcurrentHashMap();
                chatsDict = new ConcurrentHashMap();
                for (a = 0; a < res.users.size(); a++) {
                    User u2 = (User) res.users.get(a);
                    usersDict.put(Integer.valueOf(u2.id), u2);
                }
                for (a = 0; a < res.chats.size(); a++) {
                    TLRPC$Chat c2 = (TLRPC$Chat) res.chats.get(a);
                    chatsDict.put(Integer.valueOf(c2.id), c2);
                }
                for (a = 0; a < res.messages.size(); a++) {
                    objects.add(new MessageObject(DownloadReceiver.this.currentAccount, (TLRPC$Message) res.messages.get(a), usersDict, chatsDict, true));
                }
            }
        });
        return objects;
    }

    public void onFailedDownload(String fileName) {
    }

    public void onSuccessDownload(String fileName) {
        startDownloading(this.messageObjects);
    }

    public void onProgressDownload(String fileName, float progress) {
    }

    public void onProgressUpload(String fileName, float progress, boolean isEncrypted) {
    }

    public int getObserverTag() {
        return 0;
    }
}
