package me.cheshmak.android.sdk.core.p022l;

import org.json.JSONArray;

/* renamed from: me.cheshmak.android.sdk.core.l.h */
public class C0523h {
    /* renamed from: a */
    public static String m925a(Throwable th) {
        return th == null ? "" : th.getMessage() == null ? "" : th.getMessage();
    }

    /* renamed from: b */
    public static JSONArray m926b(Throwable th) {
        JSONArray jSONArray = new JSONArray();
        if (th != null) {
            try {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (stackTraceElement != null) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(stackTraceElement.getFileName() != null ? stackTraceElement.getFileName() : "");
                        jSONArray2.put(stackTraceElement.getClassName() != null ? stackTraceElement.getClassName() : "");
                        jSONArray2.put(stackTraceElement.getMethodName() != null ? stackTraceElement.getMethodName() : "");
                        jSONArray2.put(stackTraceElement.getLineNumber());
                        jSONArray.put(jSONArray2);
                    }
                }
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    /* renamed from: c */
    public static String m927c(Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        Throwable th2 = th;
        while (th2 != null) {
            try {
                StackTraceElement[] stackTrace = th2.getStackTrace();
                if (stackTrace != null) {
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        if (stackTraceElement != null) {
                            stringBuilder.append(stackTraceElement.getFileName());
                            stringBuilder.append(stackTraceElement.getClassName());
                            stringBuilder.append(stackTraceElement.getMethodName());
                            stringBuilder.append(stackTraceElement.getLineNumber());
                        }
                    }
                    th2 = th2.getCause();
                }
            } catch (Exception e) {
            }
        }
        return Integer.toHexString(stringBuilder.toString().hashCode());
    }

    /* renamed from: d */
    public static boolean m928d(Throwable th) {
        boolean z = false;
        if (th != null) {
            try {
                if (th.getMessage() != null) {
                    z = th.getMessage().contains("me.cheshmak.android.sdk.core");
                }
            } catch (Throwable th2) {
            }
        }
        return z;
    }
}
