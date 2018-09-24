package com.logentries.logger;

import android.content.Context;

public class AndroidLogger {
    private static AndroidLogger instance;
    private AsyncLoggingWorker loggingWorker;

    private AndroidLogger(Context context, boolean z, boolean z2, boolean z3, String str, int i, String str2, boolean z4) {
        this.loggingWorker = new AsyncLoggingWorker(context, z2, z, z3, str2, str, i, z4);
    }

    public static synchronized AndroidLogger createInstance(Context context, boolean z, boolean z2, boolean z3, String str, int i, String str2, boolean z4) {
        AndroidLogger androidLogger;
        synchronized (AndroidLogger.class) {
            if (instance != null) {
                instance.loggingWorker.close();
            }
            instance = new AndroidLogger(context, z, z2, z3, str, i, str2, z4);
            androidLogger = instance;
        }
        return androidLogger;
    }

    public static synchronized AndroidLogger getInstance() {
        AndroidLogger androidLogger;
        synchronized (AndroidLogger.class) {
            if (instance != null) {
                androidLogger = instance;
            } else {
                throw new IllegalArgumentException("Logger instance is not initialized. Call createInstance() first!");
            }
        }
        return androidLogger;
    }

    public void log(String str) {
        this.loggingWorker.addLineToQueue(str);
    }
}
