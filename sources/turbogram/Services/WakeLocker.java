package turbogram.Services;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public abstract class WakeLocker {
    private static WakeLock wakeLock;

    public static void acquire(Context context) {
        if (wakeLock != null) {
            wakeLock.release();
        }
        wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "MyWakelockTag");
        wakeLock.acquire();
    }

    public static void release() {
        if (wakeLock != null) {
            wakeLock.release();
        }
        wakeLock = null;
    }
}
