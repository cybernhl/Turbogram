package me.cheshmak.android.sdk.core.p022l;

import android.content.Context;
import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.logentries.logger.AndroidLogger;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.zip.GZIPOutputStream;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import org.json.JSONObject;

/* renamed from: me.cheshmak.android.sdk.core.l.m */
public class C0545m {
    /* renamed from: a */
    private static String f657a;
    /* renamed from: b */
    private static String f658b;
    /* renamed from: c */
    private static AndroidLogger f659c;
    /* renamed from: d */
    private static boolean f660d = false;
    /* renamed from: e */
    private static int f661e = 7;

    public C0545m(Context context) {
        if (f657a == null) {
            f657a = C0520e.m901a(context);
        }
        if (f658b == null) {
            f658b = C0477a.m656a().m677b();
        }
        if (f659c == null) {
            try {
                f659c = AndroidLogger.createInstance(context, false, true, false, " String dataHubAddr", 0, "44131d07-c8a0-4b2a-a8a6-00939b8e313e", true);
                f661e = C0477a.m656a().m730y();
                f660d = C0477a.m656a().m729x();
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* renamed from: a */
    private static JSONObject m1040a(Throwable th) {
        JSONObject jSONObject = new JSONObject();
        try {
            Writer stringWriter = new StringWriter();
            ThrowableExtension.printStackTrace(th, new PrintWriter(stringWriter));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(stringWriter.toString().length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(stringWriter.toString().getBytes());
            gZIPOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            jSONObject.put("class", th.toString());
            jSONObject.put("compressed", Base64.encodeToString(toByteArray, 2));
        } catch (Throwable th2) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m1041a(int i) {
        f661e = i;
        if (C0477a.m656a().m730y() != i) {
            C0477a.m656a().m685c(i);
        }
    }

    /* renamed from: a */
    public static void m1042a(int i, WeakHashMap<String, String> weakHashMap) {
        if (f659c != null && f660d && i <= f661e) {
            JSONObject a = C0522g.m922a((Map) weakHashMap);
            try {
                a.put(Param.LEVEL, i);
                a.put("deviceId", f657a);
                a.put("appKey", f658b);
                a.put("SDK_VERSION_NUMBER", 52);
                f659c.log(a.toString());
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public static void m1043a(int i, WeakHashMap<String, String> weakHashMap, Throwable th) {
        if (f659c != null && f660d && i <= f661e) {
            JSONObject a = C0522g.m922a((Map) weakHashMap);
            try {
                a.put(Param.LEVEL, i);
                a.put("deviceId", f657a);
                a.put("appKey", f658b);
                a.put("SDK_VERSION_NUMBER", 52);
                a.put("ex", C0545m.m1040a(th));
                f659c.log(a.toString());
            } catch (Throwable th2) {
            }
        }
    }

    /* renamed from: a */
    public static void m1044a(Context context) {
        if (f659c == null) {
            C0545m c0545m = new C0545m(context);
        }
    }

    /* renamed from: a */
    public static void m1045a(WeakHashMap<String, String> weakHashMap, Throwable th) {
        C0545m.m1043a(3, weakHashMap, th);
    }

    /* renamed from: a */
    public static void m1046a(boolean z) {
        f660d = z;
        if (C0477a.m656a().m729x() != z) {
            C0477a.m656a().m688c(z);
        }
    }

    /* renamed from: a */
    public static boolean m1047a() {
        return f659c != null;
    }
}
