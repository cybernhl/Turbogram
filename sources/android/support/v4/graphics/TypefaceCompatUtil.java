package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), prefix + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Nullable
    @RequiresApi(19)
    private static ByteBuffer mmap(File file) {
        try {
            Throwable th;
            Throwable th2;
            FileInputStream fis = new FileInputStream(file);
            try {
                FileChannel channel = fis.getChannel();
                ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                if (fis == null) {
                    return map;
                }
                if (null != null) {
                    try {
                        fis.close();
                        return map;
                    } catch (Throwable th3) {
                        ThrowableExtension.addSuppressed(null, th3);
                        return map;
                    }
                }
                fis.close();
                return map;
            } catch (Throwable th4) {
                th3 = th4;
                th2 = th;
            }
            throw th3;
            if (fis != null) {
                if (th2 != null) {
                    try {
                        fis.close();
                    } catch (Throwable th5) {
                        ThrowableExtension.addSuppressed(th2, th5);
                    }
                } else {
                    fis.close();
                }
            }
            throw th3;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    @android.support.annotation.RequiresApi(19)
    public static java.nio.ByteBuffer mmap(android.content.Context r13, android.os.CancellationSignal r14, android.net.Uri r15) {
        /*
        r10 = 0;
        r9 = r13.getContentResolver();
        r1 = "r";
        r8 = r9.openFileDescriptor(r15, r1, r14);	 Catch:{ IOException -> 0x001d }
        r11 = 0;
        if (r8 != 0) goto L_0x0024;
    L_0x000f:
        if (r8 == 0) goto L_0x0016;
    L_0x0011:
        if (r10 == 0) goto L_0x0020;
    L_0x0013:
        r8.close();	 Catch:{ Throwable -> 0x0018 }
    L_0x0016:
        r1 = r10;
    L_0x0017:
        return r1;
    L_0x0018:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r11, r1);	 Catch:{ IOException -> 0x001d }
        goto L_0x0016;
    L_0x001d:
        r6 = move-exception;
        r1 = r10;
        goto L_0x0017;
    L_0x0020:
        r8.close();	 Catch:{ IOException -> 0x001d }
        goto L_0x0016;
    L_0x0024:
        r7 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        r1 = r8.getFileDescriptor();	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        r7.<init>(r1);	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        r12 = 0;
        r0 = r7.getChannel();	 Catch:{ Throwable -> 0x006f, all -> 0x008d }
        r4 = r0.size();	 Catch:{ Throwable -> 0x006f, all -> 0x008d }
        r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Throwable -> 0x006f, all -> 0x008d }
        r2 = 0;
        r1 = r0.map(r1, r2, r4);	 Catch:{ Throwable -> 0x006f, all -> 0x008d }
        if (r7 == 0) goto L_0x0045;
    L_0x0040:
        if (r10 == 0) goto L_0x0063;
    L_0x0042:
        r7.close();	 Catch:{ Throwable -> 0x0052, all -> 0x0067 }
    L_0x0045:
        if (r8 == 0) goto L_0x0017;
    L_0x0047:
        if (r10 == 0) goto L_0x006b;
    L_0x0049:
        r8.close();	 Catch:{ Throwable -> 0x004d }
        goto L_0x0017;
    L_0x004d:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r11, r2);	 Catch:{ IOException -> 0x001d }
        goto L_0x0017;
    L_0x0052:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r12, r2);	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        goto L_0x0045;
    L_0x0057:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x0059 }
    L_0x0059:
        r2 = move-exception;
        r3 = r1;
    L_0x005b:
        if (r8 == 0) goto L_0x0062;
    L_0x005d:
        if (r3 == 0) goto L_0x0089;
    L_0x005f:
        r8.close();	 Catch:{ Throwable -> 0x0084 }
    L_0x0062:
        throw r2;	 Catch:{ IOException -> 0x001d }
    L_0x0063:
        r7.close();	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        goto L_0x0045;
    L_0x0067:
        r1 = move-exception;
        r2 = r1;
        r3 = r10;
        goto L_0x005b;
    L_0x006b:
        r8.close();	 Catch:{ IOException -> 0x001d }
        goto L_0x0017;
    L_0x006f:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r2 = move-exception;
        r3 = r1;
    L_0x0073:
        if (r7 == 0) goto L_0x007a;
    L_0x0075:
        if (r3 == 0) goto L_0x0080;
    L_0x0077:
        r7.close();	 Catch:{ Throwable -> 0x007b, all -> 0x0067 }
    L_0x007a:
        throw r2;	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
    L_0x007b:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r3, r1);	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        goto L_0x007a;
    L_0x0080:
        r7.close();	 Catch:{ Throwable -> 0x0057, all -> 0x0067 }
        goto L_0x007a;
    L_0x0084:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r3, r1);	 Catch:{ IOException -> 0x001d }
        goto L_0x0062;
    L_0x0089:
        r8.close();	 Catch:{ IOException -> 0x001d }
        goto L_0x0062;
    L_0x008d:
        r1 = move-exception;
        r2 = r1;
        r3 = r10;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        ByteBuffer byteBuffer = null;
        File tmpFile = getTempFile(context);
        if (tmpFile != null) {
            try {
                if (copyToFile(tmpFile, res, id)) {
                    byteBuffer = mmap(tmpFile);
                    tmpFile.delete();
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream is) {
        IOException e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        ThreadPolicy old = StrictMode.allowThreadDiskWrites();
        try {
            FileOutputStream os = new FileOutputStream(file, false);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen != -1) {
                        os.write(buffer, 0, readLen);
                    } else {
                        closeQuietly(os);
                        StrictMode.setThreadPolicy(old);
                        fileOutputStream = os;
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = os;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(old);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(old);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = os;
                closeQuietly(fileOutputStream);
                StrictMode.setThreadPolicy(old);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            StrictMode.setThreadPolicy(old);
            return false;
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream inputStream = null;
        try {
            inputStream = res.openRawResource(id);
            boolean copyToFile = copyToFile(file, inputStream);
            return copyToFile;
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
