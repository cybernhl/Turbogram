package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

@RequiresApi(21)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r12, android.os.CancellationSignal r13, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r14, int r15) {
        /*
        r11 = this;
        r6 = 0;
        r7 = r14.length;
        r8 = 1;
        if (r7 >= r8) goto L_0x0006;
    L_0x0005:
        return r6;
    L_0x0006:
        r0 = r11.findBestInfo(r14, r15);
        r5 = r12.getContentResolver();
        r7 = r0.getUri();	 Catch:{ IOException -> 0x0055 }
        r8 = "r";
        r4 = r5.openFileDescriptor(r7, r8, r13);	 Catch:{ IOException -> 0x0055 }
        r8 = 0;
        r2 = r11.getFile(r4);	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        if (r2 == 0) goto L_0x0026;
    L_0x0020:
        r7 = r2.canRead();	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        if (r7 != 0) goto L_0x007c;
    L_0x0026:
        r3 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        r7 = r4.getFileDescriptor();	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        r3.<init>(r7);	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        r9 = 0;
        r7 = super.createFromInputStream(r12, r3);	 Catch:{ Throwable -> 0x0068, all -> 0x009c }
        if (r3 == 0) goto L_0x003b;
    L_0x0036:
        if (r6 == 0) goto L_0x0057;
    L_0x0038:
        r3.close();	 Catch:{ Throwable -> 0x0044, all -> 0x005b }
    L_0x003b:
        if (r4 == 0) goto L_0x0042;
    L_0x003d:
        if (r6 == 0) goto L_0x0064;
    L_0x003f:
        r4.close();	 Catch:{ Throwable -> 0x005f }
    L_0x0042:
        r6 = r7;
        goto L_0x0005;
    L_0x0044:
        r10 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r9, r10);	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        goto L_0x003b;
    L_0x0049:
        r7 = move-exception;
        throw r7;	 Catch:{ all -> 0x004b }
    L_0x004b:
        r8 = move-exception;
        r9 = r7;
    L_0x004d:
        if (r4 == 0) goto L_0x0054;
    L_0x004f:
        if (r9 == 0) goto L_0x0098;
    L_0x0051:
        r4.close();	 Catch:{ Throwable -> 0x0093 }
    L_0x0054:
        throw r8;	 Catch:{ IOException -> 0x0055 }
    L_0x0055:
        r1 = move-exception;
        goto L_0x0005;
    L_0x0057:
        r3.close();	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        goto L_0x003b;
    L_0x005b:
        r7 = move-exception;
        r8 = r7;
        r9 = r6;
        goto L_0x004d;
    L_0x005f:
        r9 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r8, r9);	 Catch:{ IOException -> 0x0055 }
        goto L_0x0042;
    L_0x0064:
        r4.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x0042;
    L_0x0068:
        r8 = move-exception;
        throw r8;	 Catch:{ all -> 0x006a }
    L_0x006a:
        r7 = move-exception;
    L_0x006b:
        if (r3 == 0) goto L_0x0072;
    L_0x006d:
        if (r8 == 0) goto L_0x0078;
    L_0x006f:
        r3.close();	 Catch:{ Throwable -> 0x0073, all -> 0x005b }
    L_0x0072:
        throw r7;	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
    L_0x0073:
        r9 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r8, r9);	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        goto L_0x0072;
    L_0x0078:
        r3.close();	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        goto L_0x0072;
    L_0x007c:
        r7 = android.graphics.Typeface.createFromFile(r2);	 Catch:{ Throwable -> 0x0049, all -> 0x005b }
        if (r4 == 0) goto L_0x0087;
    L_0x0082:
        if (r6 == 0) goto L_0x008f;
    L_0x0084:
        r4.close();	 Catch:{ Throwable -> 0x008a }
    L_0x0087:
        r6 = r7;
        goto L_0x0005;
    L_0x008a:
        r9 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r8, r9);	 Catch:{ IOException -> 0x0055 }
        goto L_0x0087;
    L_0x008f:
        r4.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x0087;
    L_0x0093:
        r7 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.addSuppressed(r9, r7);	 Catch:{ IOException -> 0x0055 }
        goto L_0x0054;
    L_0x0098:
        r4.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x0054;
    L_0x009c:
        r7 = move-exception;
        r8 = r6;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
