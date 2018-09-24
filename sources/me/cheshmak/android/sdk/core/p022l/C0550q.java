package me.cheshmak.android.sdk.core.p022l;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p018f.C0496b;

/* renamed from: me.cheshmak.android.sdk.core.l.q */
public class C0550q {
    /* renamed from: a */
    private static final HashMap<String, Intent> f667a = new HashMap();

    /* renamed from: a */
    private static String m1060a(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            return str;
        }
        return String.format(Locale.US, "%s_%s", new Object[]{str, str2});
    }

    /* renamed from: a */
    public static void m1061a(Context context) {
        Log.e("start pending services", f667a.size() + "");
        synchronized (f667a) {
            for (Entry entry : f667a.entrySet()) {
                if (C0550q.m1065a(context, (Intent) entry.getValue(), true)) {
                    f667a.remove(entry.getKey());
                }
            }
        }
    }

    /* renamed from: a */
    private static void m1062a(Intent intent) {
        String action = intent.getAction();
        ComponentName component = intent.getComponent();
        if (component != null) {
            action = C0550q.m1060a(component.getClassName(), action);
            if (action != null) {
                synchronized (f667a) {
                    if (!f667a.containsKey(action)) {
                        f667a.put(action, intent);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static void m1063a(Intent intent, boolean z) {
        WeakHashMap weakHashMap = new WeakHashMap();
        ComponentName component = intent.getComponent();
        Object obj = "";
        if (component != null) {
            obj = component.getClassName();
        }
        weakHashMap.put("componentName", obj);
        weakHashMap.put("actionName", intent.getAction());
        weakHashMap.put("openFromWhiteList", String.valueOf(z));
        weakHashMap.put("applicationIsForeground", String.valueOf(C0496b.m786a().m794b()));
        C0545m.m1042a(6, weakHashMap);
    }

    /* renamed from: a */
    public static boolean m1064a(Context context, Intent intent) {
        return C0550q.m1065a(context, intent, false);
    }

    /* renamed from: a */
    public static boolean m1065a(Context context, Intent intent, boolean z) {
        try {
            context.startService(intent);
            return true;
        } catch (Exception e) {
            C0550q.m1062a(intent);
            C0550q.m1063a(intent, z);
            return false;
        }
    }
}
