package com.p001a.p002a.p003a.p014i.p015a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.p001a.p002a.p003a.p011f.C0180b;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import p000a.C0008e;

/* renamed from: com.a.a.a.i.a.b */
class C0212b {
    /* renamed from: a */
    private final File f211a;

    C0212b(Context context, String str) {
        this.f211a = new File(context.getDir("com_birbit_jobqueue_jobs", 0), "files_" + str);
        this.f211a.mkdirs();
    }

    /* renamed from: a */
    private static void m342a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    /* renamed from: c */
    private static String m343c(String str) {
        return str + ".jobs";
    }

    /* renamed from: d */
    private File m344d(String str) {
        return new File(this.f211a, C0212b.m343c(str));
    }

    @Nullable
    /* renamed from: e */
    private static String m345e(String str) {
        return str.length() < ".jobs".length() + 1 ? null : str.substring(0, str.length() - ".jobs".length());
    }

    /* renamed from: a */
    void m346a(String str) {
        File d = m344d(str);
        if (d.exists()) {
            d.delete();
        }
    }

    /* renamed from: a */
    void m347a(String str, byte[] bArr) {
        Closeable a = C0008e.m34a(C0008e.m42b(m344d(str)));
        try {
            a.mo5c(bArr).flush();
        } finally {
            C0212b.m342a(a);
        }
    }

    /* renamed from: a */
    void m348a(Set<String> set) {
        for (String str : this.f211a.list()) {
            if (str.endsWith(".jobs") && !set.contains(C0212b.m345e(str))) {
                File file = new File(this.f211a, str);
                if (!file.delete()) {
                    C0180b.m231a("cannot delete unused job toFile " + file.getAbsolutePath(), new Object[0]);
                }
            }
        }
    }

    @Nullable
    /* renamed from: b */
    byte[] m349b(String str) {
        File d = m344d(str);
        if (!d.exists() || !d.canRead()) {
            return null;
        }
        Closeable a = C0008e.m35a(C0008e.m38a(d));
        try {
            byte[] b = a.mo4b();
            return b;
        } finally {
            C0212b.m342a(a);
        }
    }
}
