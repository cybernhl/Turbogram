package me.cheshmak.android.sdk.core.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class NotificationService extends Service {
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("me.cheshmak.start"));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
