package org.telegram.messenger;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.Channel.GetInputStreamResult;
import com.google.android.gms.wearable.Channel.GetOutputStreamResult;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC.User;

public class WearDataLayerListenerService extends WearableListenerService {
    private static boolean watchConnected;
    private int currentAccount = UserConfig.selectedAccount;

    public void onCreate() {
        super.onCreate();
        if (BuildVars.LOGS_ENABLED) {
            FileLog.m1221d("WearableDataLayer service created");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (BuildVars.LOGS_ENABLED) {
            FileLog.m1221d("WearableDataLayer service destroyed");
        }
    }

    public void onChannelOpened(Channel ch) {
        DataInputStream dataInputStream;
        GoogleApiClient apiClient = new Builder(this).addApi(Wearable.API).build();
        if (apiClient.blockingConnect().isSuccess()) {
            String path = ch.getPath();
            if (BuildVars.LOGS_ENABLED) {
                FileLog.m1221d("wear channel path: " + path);
            }
            DataOutputStream dataOutputStream;
            try {
                User user;
                CyclicBarrier barrier;
                NotificationCenter$NotificationCenterDelegate wearDataLayerListenerService$1;
                if ("/getCurrentUser".equals(path)) {
                    dataOutputStream = new DataOutputStream(new BufferedOutputStream(((GetOutputStreamResult) ch.getOutputStream(apiClient).await()).getOutputStream()));
                    if (UserConfig.getInstance(this.currentAccount).isClientActivated()) {
                        user = UserConfig.getInstance(this.currentAccount).getCurrentUser();
                        dataOutputStream.writeInt(user.id);
                        dataOutputStream.writeUTF(user.first_name);
                        dataOutputStream.writeUTF(user.last_name);
                        dataOutputStream.writeUTF(user.phone);
                        if (user.photo != null) {
                            File photo = FileLoader.getPathToAttach(user.photo.photo_small, true);
                            barrier = new CyclicBarrier(2);
                            if (!photo.exists()) {
                                wearDataLayerListenerService$1 = new WearDataLayerListenerService$1(this, photo, barrier);
                                AndroidUtilities.runOnUIThread(new WearDataLayerListenerService$2(this, wearDataLayerListenerService$1, user));
                                try {
                                    barrier.await(10, TimeUnit.SECONDS);
                                } catch (Exception e) {
                                }
                                AndroidUtilities.runOnUIThread(new WearDataLayerListenerService$3(this, wearDataLayerListenerService$1));
                            }
                            if (!photo.exists() || photo.length() > 52428800) {
                                dataOutputStream.writeInt(0);
                            } else {
                                byte[] photoData = new byte[((int) photo.length())];
                                InputStream fileInputStream = new FileInputStream(photo);
                                new DataInputStream(fileInputStream).readFully(photoData);
                                fileInputStream.close();
                                dataOutputStream.writeInt(photoData.length);
                                dataOutputStream.write(photoData);
                            }
                        } else {
                            dataOutputStream.writeInt(0);
                        }
                    } else {
                        dataOutputStream.writeInt(0);
                    }
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    ch.close(apiClient).await();
                    apiClient.disconnect();
                    if (!BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("WearableDataLayer channel thread exiting");
                    }
                } else if ("/waitForAuthCode".equals(path)) {
                    ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
                    String[] code = new String[]{null};
                    barrier = new CyclicBarrier(2);
                    wearDataLayerListenerService$1 = new WearDataLayerListenerService$4(this, code, barrier);
                    AndroidUtilities.runOnUIThread(new WearDataLayerListenerService$5(this, wearDataLayerListenerService$1));
                    try {
                        barrier.await(15, TimeUnit.SECONDS);
                    } catch (Exception e2) {
                    }
                    AndroidUtilities.runOnUIThread(new WearDataLayerListenerService$6(this, wearDataLayerListenerService$1));
                    dataOutputStream = new DataOutputStream(((GetOutputStreamResult) ch.getOutputStream(apiClient).await()).getOutputStream());
                    if (code[0] != null) {
                        dataOutputStream.writeUTF(code[0]);
                    } else {
                        dataOutputStream.writeUTF("");
                    }
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
                    ch.close(apiClient).await();
                    apiClient.disconnect();
                    if (!BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("WearableDataLayer channel thread exiting");
                    }
                } else {
                    if ("/getChatPhoto".equals(path)) {
                        dataInputStream = new DataInputStream(((GetInputStreamResult) ch.getInputStream(apiClient).await()).getInputStream());
                        dataOutputStream = new DataOutputStream(((GetOutputStreamResult) ch.getOutputStream(apiClient).await()).getOutputStream());
                        JSONObject jSONObject = new JSONObject(dataInputStream.readUTF());
                        int chatID = jSONObject.getInt("chat_id");
                        int accountID = jSONObject.getInt("account_id");
                        int currentAccount = -1;
                        for (int i = 0; i < UserConfig.getActivatedAccountsCount(); i++) {
                            if (UserConfig.getInstance(i).getClientUserId() == accountID) {
                                currentAccount = i;
                                break;
                            }
                        }
                        if (currentAccount != -1) {
                            TLObject location = null;
                            if (chatID > 0) {
                                user = MessagesController.getInstance(currentAccount).getUser(Integer.valueOf(chatID));
                                if (!(user == null || user.photo == null)) {
                                    location = user.photo.photo_small;
                                }
                            } else {
                                TLRPC$Chat chat = MessagesController.getInstance(currentAccount).getChat(Integer.valueOf(-chatID));
                                if (!(chat == null || chat.photo == null)) {
                                    location = chat.photo.photo_small;
                                }
                            }
                            if (location != null) {
                                File file = FileLoader.getPathToAttach(location, true);
                                if (!file.exists() || file.length() >= 102400) {
                                    dataOutputStream.writeInt(0);
                                } else {
                                    dataOutputStream.writeInt((int) file.length());
                                    FileInputStream fin = new FileInputStream(file);
                                    byte[] buf = new byte[Task.EXTRAS_LIMIT_BYTES];
                                    while (true) {
                                        int read = fin.read(buf);
                                        if (read <= 0) {
                                            break;
                                        }
                                        dataOutputStream.write(buf, 0, read);
                                    }
                                    fin.close();
                                }
                            } else {
                                dataOutputStream.writeInt(0);
                            }
                        } else {
                            dataOutputStream.writeInt(0);
                        }
                        dataOutputStream.flush();
                        dataInputStream.close();
                        dataOutputStream.close();
                    }
                    ch.close(apiClient).await();
                    apiClient.disconnect();
                    if (!BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("WearableDataLayer channel thread exiting");
                    }
                }
            } catch (Exception e3) {
                dataInputStream.close();
                dataOutputStream.close();
            } catch (Throwable x) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.m1223e("error processing wear request", x);
                }
            } catch (Throwable th) {
                dataInputStream.close();
                dataOutputStream.close();
            }
        } else if (BuildVars.LOGS_ENABLED) {
            FileLog.m1222e("failed to connect google api client");
        }
    }

    public void onMessageReceived(MessageEvent messageEvent) {
        if ("/reply".equals(messageEvent.getPath())) {
            AndroidUtilities.runOnUIThread(new WearDataLayerListenerService$7(this, messageEvent));
        }
    }

    public static void sendMessageToWatch(String path, byte[] data, String capability) {
        Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability(capability, 1).addOnCompleteListener(new WearDataLayerListenerService$8(path, data));
    }

    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        if ("remote_notifications".equals(capabilityInfo.getName())) {
            watchConnected = false;
            for (Node node : capabilityInfo.getNodes()) {
                if (node.isNearby()) {
                    watchConnected = true;
                }
            }
        }
    }

    public static void updateWatchConnectionState() {
        try {
            Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability("remote_notifications", 1).addOnCompleteListener(new WearDataLayerListenerService$9());
        } catch (Throwable th) {
        }
    }

    public static boolean isWatchConnected() {
        return watchConnected;
    }
}
