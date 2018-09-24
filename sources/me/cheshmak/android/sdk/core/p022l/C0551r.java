package me.cheshmak.android.sdk.core.p022l;

import android.util.Base64;
import java.security.MessageDigest;

/* renamed from: me.cheshmak.android.sdk.core.l.r */
public class C0551r {
    /* renamed from: a */
    public static String m1066a(String str) {
        return str != null ? str : "";
    }

    /* renamed from: b */
    public static String m1067b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            return Base64.encodeToString(instance.digest(), 2);
        } catch (Throwable th) {
        }
        return "";
    }
}
