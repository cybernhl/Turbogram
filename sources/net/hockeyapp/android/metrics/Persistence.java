package net.hockeyapp.android.metrics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.HockeyLog;

class Persistence {
    private static final String BIT_TELEMETRY_DIRECTORY = "/net.hockeyapp.android/telemetry/";
    private static final Integer MAX_FILE_COUNT = Integer.valueOf(50);
    private static final String TAG = "HA-MetricsPersistence";
    ArrayList<File> mServedFiles = new ArrayList(MAX_FILE_COUNT.intValue() + 1);
    private final WeakReference<Context> mWeakContext;
    private WeakReference<Sender> mWeakSender;

    /* renamed from: net.hockeyapp.android.metrics.Persistence$1 */
    class C06321 extends AsyncTask<Void, Object, Object> {
        C06321() {
        }

        protected Object doInBackground(Void... voids) {
            if (Persistence.this.hasFilesAvailable()) {
                Sender sender = Persistence.this.getSender();
                if (sender != null) {
                    sender.triggerSending();
                }
            }
            return null;
        }
    }

    Persistence(Context context, Sender sender) {
        this.mWeakContext = new WeakReference(context);
        this.mWeakSender = new WeakReference(sender);
    }

    protected void persist(String[] data) {
        if (isFreeSpaceAvailable()) {
            StringBuilder buffer = new StringBuilder();
            for (String aData : data) {
                if (buffer.length() > 0) {
                    buffer.append('\n');
                }
                buffer.append(aData);
            }
            if (!writeToDisk(buffer.toString())) {
                return;
            }
        }
        HockeyLog.warn(TAG, "Failed to persist file: Too many files on disk.");
        Sender sender = getSender();
        if (sender != null) {
            sender.triggerSending();
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    void sendAvailable() {
        AsyncTaskUtils.execute(new C06321());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean writeToDisk(java.lang.String r11) {
        /*
        r10 = this;
        r7 = 0;
        r0 = r10.getTelemetryDirectory();
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r7;
    L_0x0008:
        r8 = java.util.UUID.randomUUID();
        r6 = r8.toString();
        r3 = java.lang.Boolean.valueOf(r7);
        r4 = 0;
        monitor-enter(r10);	 Catch:{ Exception -> 0x005a }
        r2 = new java.io.File;	 Catch:{ all -> 0x0057 }
        r2.<init>(r0, r6);	 Catch:{ all -> 0x0057 }
        r5 = new java.io.FileOutputStream;	 Catch:{ all -> 0x0057 }
        r7 = 1;
        r5.<init>(r2, r7);	 Catch:{ all -> 0x0057 }
        r7 = r11.getBytes();	 Catch:{ all -> 0x007e }
        r5.write(r7);	 Catch:{ all -> 0x007e }
        r7 = "HA-MetricsPersistence";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007e }
        r8.<init>();	 Catch:{ all -> 0x007e }
        r9 = "Saving data to: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x007e }
        r9 = r2.toString();	 Catch:{ all -> 0x007e }
        r8 = r8.append(r9);	 Catch:{ all -> 0x007e }
        r8 = r8.toString();	 Catch:{ all -> 0x007e }
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8);	 Catch:{ all -> 0x007e }
        monitor-exit(r10);	 Catch:{ all -> 0x007e }
        r7 = 1;
        r3 = java.lang.Boolean.valueOf(r7);	 Catch:{ Exception -> 0x007b, all -> 0x0078 }
        if (r5 == 0) goto L_0x0081;
    L_0x004e:
        r5.close();	 Catch:{ IOException -> 0x006c }
        r4 = r5;
    L_0x0052:
        r7 = r3.booleanValue();
        goto L_0x0007;
    L_0x0057:
        r7 = move-exception;
    L_0x0058:
        monitor-exit(r10);	 Catch:{ all -> 0x0057 }
        throw r7;	 Catch:{ Exception -> 0x005a }
    L_0x005a:
        r1 = move-exception;
    L_0x005b:
        r7 = "HA-MetricsPersistence";
        r8 = "Failed to save data with exception";
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8, r1);	 Catch:{ all -> 0x006f }
        if (r4 == 0) goto L_0x0052;
    L_0x0066:
        r4.close();	 Catch:{ IOException -> 0x006a }
        goto L_0x0052;
    L_0x006a:
        r7 = move-exception;
        goto L_0x0052;
    L_0x006c:
        r7 = move-exception;
        r4 = r5;
        goto L_0x0052;
    L_0x006f:
        r7 = move-exception;
    L_0x0070:
        if (r4 == 0) goto L_0x0075;
    L_0x0072:
        r4.close();	 Catch:{ IOException -> 0x0076 }
    L_0x0075:
        throw r7;
    L_0x0076:
        r8 = move-exception;
        goto L_0x0075;
    L_0x0078:
        r7 = move-exception;
        r4 = r5;
        goto L_0x0070;
    L_0x007b:
        r1 = move-exception;
        r4 = r5;
        goto L_0x005b;
    L_0x007e:
        r7 = move-exception;
        r4 = r5;
        goto L_0x0058;
    L_0x0081:
        r4 = r5;
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.metrics.Persistence.writeToDisk(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.lang.String load(java.io.File r10) {
        /*
        r9 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        if (r10 == 0) goto L_0x0037;
    L_0x0007:
        r4 = 0;
        monitor-enter(r9);	 Catch:{ Exception -> 0x0028 }
        r3 = new java.io.FileInputStream;	 Catch:{ all -> 0x0050 }
        r3.<init>(r10);	 Catch:{ all -> 0x0050 }
        r6 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0050 }
        r6.<init>(r3);	 Catch:{ all -> 0x0050 }
        r5 = new java.io.BufferedReader;	 Catch:{ all -> 0x0050 }
        r5.<init>(r6);	 Catch:{ all -> 0x0050 }
    L_0x0018:
        r1 = r5.read();	 Catch:{ all -> 0x0024 }
        r7 = -1;
        if (r1 == r7) goto L_0x003c;
    L_0x001f:
        r7 = (char) r1;	 Catch:{ all -> 0x0024 }
        r0.append(r7);	 Catch:{ all -> 0x0024 }
        goto L_0x0018;
    L_0x0024:
        r7 = move-exception;
        r4 = r5;
    L_0x0026:
        monitor-exit(r9);	 Catch:{ all -> 0x0050 }
        throw r7;	 Catch:{ Exception -> 0x0028 }
    L_0x0028:
        r2 = move-exception;
        r7 = "HA-MetricsPersistence";
        r8 = "Error reading telemetry data from file";
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8, r2);	 Catch:{ all -> 0x0045 }
        if (r4 == 0) goto L_0x0037;
    L_0x0034:
        r4.close();	 Catch:{ IOException -> 0x004c }
    L_0x0037:
        r7 = r0.toString();
        return r7;
    L_0x003c:
        monitor-exit(r9);	 Catch:{ all -> 0x0024 }
        if (r5 == 0) goto L_0x0037;
    L_0x003f:
        r5.close();	 Catch:{ IOException -> 0x0043 }
        goto L_0x0037;
    L_0x0043:
        r7 = move-exception;
        goto L_0x0037;
    L_0x0045:
        r7 = move-exception;
        if (r4 == 0) goto L_0x004b;
    L_0x0048:
        r4.close();	 Catch:{ IOException -> 0x004e }
    L_0x004b:
        throw r7;
    L_0x004c:
        r7 = move-exception;
        goto L_0x0037;
    L_0x004e:
        r8 = move-exception;
        goto L_0x004b;
    L_0x0050:
        r7 = move-exception;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.metrics.Persistence.load(java.io.File):java.lang.String");
    }

    protected boolean hasFilesAvailable() {
        File file = nextAvailableFileInDirectory();
        makeAvailable(file);
        return file != null;
    }

    protected synchronized File nextAvailableFileInDirectory() {
        File file;
        File[] files;
        File dir = getTelemetryDirectory();
        if (dir != null) {
            files = dir.listFiles();
        } else {
            files = null;
        }
        if (files != null) {
            for (File file2 : files) {
                if (!this.mServedFiles.contains(file2)) {
                    HockeyLog.info(TAG, "The directory " + file2 + " (ADDING TO SERVED AND RETURN)");
                    this.mServedFiles.add(file2);
                    break;
                }
                HockeyLog.info(TAG, "The directory " + file2 + " (WAS ALREADY SERVED)");
            }
        }
        HockeyLog.info(TAG, "The directory " + dir + " did not contain any unserved files");
        file2 = null;
        return file2;
    }

    protected synchronized void deleteFile(File file) {
        if (file == null) {
            HockeyLog.warn(TAG, "Couldn't delete file, the reference to the file was null");
        } else if (file.delete()) {
            HockeyLog.warn(TAG, "Successfully deleted telemetry file at: " + file.toString());
            this.mServedFiles.remove(file);
        } else {
            HockeyLog.warn(TAG, "Error deleting telemetry file " + file.toString());
        }
    }

    protected synchronized void makeAvailable(File file) {
        if (file != null) {
            this.mServedFiles.remove(file);
        }
    }

    private synchronized boolean isFreeSpaceAvailable() {
        boolean z;
        File dir = getTelemetryDirectory();
        File[] files = dir != null ? dir.listFiles() : null;
        z = files != null && files.length < MAX_FILE_COUNT.intValue();
        return z;
    }

    protected File getTelemetryDirectory() {
        Context context = getContext();
        if (!(context == null || context.getFilesDir() == null)) {
            File dir = new File(context.getFilesDir(), BIT_TELEMETRY_DIRECTORY);
            if (dir.exists() || dir.mkdirs()) {
                return dir;
            }
            HockeyLog.error("Couldn't create directory for telemetry data");
        }
        return null;
    }

    private Context getContext() {
        return (Context) this.mWeakContext.get();
    }

    protected Sender getSender() {
        return this.mWeakSender != null ? (Sender) this.mWeakSender.get() : null;
    }

    protected void setSender(Sender sender) {
        this.mWeakSender = new WeakReference(sender);
    }
}
