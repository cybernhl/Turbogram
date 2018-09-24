package de.jurihock.voicesmith;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public final class Utils {
    private static final Map<String, Long> tics = new HashMap();
    private final Context context;
    private final Preferences preferences;

    public Utils(Context context) {
        this.context = context;
        this.preferences = new Preferences(context);
    }

    public static void loadNativeLibrary() {
        try {
            System.loadLibrary("Voicesmith");
        } catch (UnsatisfiedLinkError e) {
            Log.d("Voicesmith", String.format("Native library %s could not be loaded!", new Object[]{"Voicesmith"}));
        }
    }

    public void log(String message) {
        if (this.preferences.isLoggingOn()) {
            Log.d("Voicesmith", message);
        }
    }

    public synchronized void toc(String tag) {
        long toc = SystemClock.elapsedRealtime();
        if (tics.containsKey(tag)) {
            ((Long) tics.remove(tag)).longValue();
        }
    }
}
