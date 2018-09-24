package turbogram.Utilities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_channelForbidden;
import org.telegram.tgnet.TLRPC$TL_contacts_resolveUsername;
import org.telegram.tgnet.TLRPC$TL_contacts_resolvedPeer;
import org.telegram.tgnet.TLRPC$TL_error;
import turbogram.Database.DBHelper;

public class TurboUtils {
    private static int reqId;

    public static void checkPakage() {
        if (!BuildConfig.APPLICATION_ID.contains(".beta")) {
            String a = ApplicationLoader.applicationContext.getPackageName();
            if (a.length() < 21 || a.length() > 22) {
                System.exit(0);
            }
            if (!(a.charAt(3) == '.' && a.charAt(11) == '.')) {
                System.exit(0);
            }
            if (!(a.charAt(0) == 'c' && a.charAt(1) == 'o' && a.charAt(2) == 'm')) {
                System.exit(0);
            }
            if (!(a.charAt(4) == 'b' && a.charAt(5) == 'a' && a.charAt(6) == 'r' && a.charAt(7) == 'a' && a.charAt(8) == 'n' && a.charAt(9) == 'a' && a.charAt(10) == 'k')) {
                System.exit(0);
            }
            if (!(a.charAt(12) == 't' && a.charAt(13) == 'u' && a.charAt(14) == 'r' && a.charAt(15) == 'b' && a.charAt(16) == 'o' && a.charAt(17) == 'g' && a.charAt(18) == 'r' && a.charAt(19) == 'a' && a.charAt(20) == 'm')) {
                System.exit(0);
            }
            if (a.length() != 22) {
                return;
            }
            if ("free".equals("free")) {
                if (a.charAt(21) != 'f') {
                    System.exit(0);
                }
            } else if ("free".equals("second") && a.charAt(21) != '2') {
                System.exit(0);
            }
        }
    }

    public static void joinTurboChannel(final int currentAccount) {
        String PackageName = String.valueOf(new char[]{'t', 'u', 'r', 'b', 'o', 't', 'e', 'l'});
        if (!TurboConfig.isPremium) {
            PackageName = String.valueOf(new char[]{'t', 'u', 'r', 'b', 'o', 't', 'e', 'l', '_', 'F', 'r', 'e', 'e'});
        }
        if (reqId != 0) {
            ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId, true);
            reqId = 0;
        }
        TLRPC$TL_contacts_resolveUsername req = new TLRPC$TL_contacts_resolveUsername();
        req.username = PackageName;
        reqId = ConnectionsManager.getInstance(currentAccount).sendRequest(req, new RequestDelegate() {
            public void run(final TLObject response, final TLRPC$TL_error error) {
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        if (error == null) {
                            TLRPC$TL_contacts_resolvedPeer res = response;
                            MessagesController.getInstance(currentAccount);
                            MessagesController.getInstance(currentAccount).putChats(res.chats, false);
                            MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users, res.chats, false, true);
                            if (!res.chats.isEmpty()) {
                                TLRPC$Chat chat = (TLRPC$Chat) res.chats.get(0);
                                if (ChatObject.isChannel(chat) && !(chat instanceof TLRPC$TL_channelForbidden)) {
                                    if (ChatObject.isNotInChat(chat)) {
                                        MessagesController.getInstance(currentAccount).addUserToChat(chat.id, UserConfig.getInstance(currentAccount).getCurrentUser(), null, 0, null, null);
                                    }
                                    TurboConfig$BG.setIntValue("turbo_did", chat.id);
                                }
                            }
                        }
                        TurboUtils.reqId = 0;
                    }
                });
            }
        });
    }

    public static Typeface getTurboTypeFace() {
        return AndroidUtilities.getTypeface("fonts/rmedium.ttf");
    }

    public static void showToast(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        ((TextView) ((ViewGroup) toast.getView()).getChildAt(0)).setTypeface(getTurboTypeFace());
        toast.show();
    }

    @SuppressLint({"WrongConstant"})
    public static void resetApp() {
        Intent intent = ApplicationLoader.applicationContext.getPackageManager().getLaunchIntentForPackage(ApplicationLoader.applicationContext.getPackageName());
        intent.addFlags(ConnectionsManager.FileTypeFile);
        intent.addFlags(268435456);
        if (VERSION.SDK_INT >= 11) {
            intent.addFlags(32768);
        }
        ((AlarmManager) ApplicationLoader.applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 1, PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, intent, 268435456));
        System.exit(2);
    }

    public static void clearChatData(int currentAccount, long dialog_id) {
        TurboConfig.removeValue("fav_" + dialog_id);
        TurboConfig.removeValue("lock_key" + dialog_id);
        TurboConfig.removeValue("lock_chat" + dialog_id);
        TurboConfig.removeValue("hide_" + dialog_id);
        TurboConfig.removeValue("selectedBackground" + dialog_id);
        DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);
        dbHelper.open();
        try {
            dbHelper.deleteWallpaper(dialog_id);
            dbHelper.deleteFMsByDid(dialog_id);
            if (dialog_id == ((long) UserConfig.getInstance(currentAccount).getClientUserId())) {
                dbHelper.deleteAllPMs();
            }
            dbHelper.close();
        } catch (Throwable th) {
            dbHelper.close();
        }
    }

    public static void deleteProfileAndFavoriteMessages(ArrayList<Integer> messages) {
        ArrayList<Integer> messagesArr1 = new ArrayList();
        ArrayList<Integer> messagesArr2 = new ArrayList();
        DBHelper dbHelper = new DBHelper(ApplicationLoader.applicationContext);
        dbHelper.open();
        try {
            messagesArr1.addAll(dbHelper.getAllFMs());
            messagesArr2.addAll(dbHelper.getAllPMs());
            for (int a = 0; a < messages.size(); a++) {
                if (messagesArr1.contains(messages.get(a))) {
                    dbHelper.deleteFMByMid(((Integer) messages.get(a)).intValue());
                }
                if (messagesArr2.contains(messages.get(a))) {
                    dbHelper.deletePMByMid(((Integer) messages.get(a)).intValue());
                }
            }
        } finally {
            dbHelper.close();
        }
    }

    public static boolean isHiddenDialog(int id) {
        if (TurboConfig.hiddenChatsUnlocked || !TurboConfig.containValue("hide_" + id)) {
            return false;
        }
        return true;
    }

    public static int getLighterColor(int color, float factor) {
        return Color.argb(Math.round(((float) Color.alpha(color)) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static int contrastColor(int color) {
        if (((0.299d * ((double) Color.red(color))) + (0.587d * ((double) Color.green(color)))) + (0.114d * ((double) Color.blue(color))) > 186.0d) {
            return -16777216;
        }
        return -1;
    }

    public static void setColorFilter(ImageView imageView, int color) {
        imageView.setColorFilter(color, Mode.SRC_IN);
    }

    public static void animMove(View view, String translation, int val, int duration) {
        if (view.getVisibility() == 0) {
            ObjectAnimator.ofFloat(view, translation, new float[]{(float) val}).setDuration((long) duration).start();
        }
    }

    public static boolean testWrite(String path) {
        File directory = new File(path);
        directory.mkdirs();
        File file = new File(directory, ".turbo_temp");
        try {
            if (file.createNewFile()) {
                file.delete();
                return true;
            }
            file.delete();
            return false;
        } catch (Throwable e) {
            FileLog.e(e);
        }
    }

    public static boolean isExternalStorageMounted() {
        String sd = TurboConfig$Storage.sdCard;
        if (sd == null || (!testWrite(sd) && !testWrite(new File(sd, "Android/data/" + ApplicationLoader.applicationContext.getPackageName() + "/files").getPath()))) {
            return "mounted".equals(Environment.getExternalStorageState());
        }
        return true;
    }

    public static File getExternalStorageDirectory() {
        String sd = TurboConfig$Storage.sdCard;
        if (sd != null) {
            if (testWrite(sd)) {
                return new File(sd);
            }
            File filesDir = new File(sd, "Android/data/" + ApplicationLoader.applicationContext.getPackageName() + "/files");
            if (testWrite(filesDir.getPath())) {
                return filesDir;
            }
        }
        return Environment.getExternalStorageDirectory();
    }

    public static boolean renameTo(File from, File to) {
        if (!to.getPath().startsWith(Environment.getExternalStorageDirectory().getPath())) {
            try {
                InputStream in = new FileInputStream(from);
                OutputStream out = new FileOutputStream(to);
                byte[] buf = new byte[1024];
                while (true) {
                    int len = in.read(buf);
                    if (len > 0) {
                        out.write(buf, 0, len);
                    } else {
                        in.close();
                        out.close();
                        return from.delete();
                    }
                }
            } catch (Throwable e) {
                FileLog.e(e);
            }
        }
        return from.renameTo(to);
    }
}
