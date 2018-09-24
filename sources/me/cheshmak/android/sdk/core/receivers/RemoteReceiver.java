package me.cheshmak.android.sdk.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.WeakHashMap;
import me.cheshmak.android.sdk.core.p019a.C0477a;
import me.cheshmak.android.sdk.core.p022l.C0545m;
import net.hockeyapp.android.LoginActivity;

public class RemoteReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private static final String f741a = RemoteReceiver.class.getSimpleName();

    /* renamed from: a */
    private void m1172a(Context context, String str, String str2, String str3, String str4) {
        Intent intent = new Intent("CHESH_REMOTE_DATA_ACTION");
        intent.addFlags(32);
        intent.putExtra("package_name", context.getPackageName());
        intent.putExtra("data_type", str);
        intent.putExtra(LoginActivity.EXTRA_MODE, str2);
        intent.putExtra("sender", str3);
        intent.putExtra("data", str4);
        context.sendBroadcast(intent);
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getExtras() != null && intent.getExtras().getString("package_name") != null && !context.getPackageName().equals(intent.getExtras().getString("package_name")) && intent.getExtras().getString(LoginActivity.EXTRA_MODE) != null) {
                if ("request".equals(intent.getExtras().getString(LoginActivity.EXTRA_MODE))) {
                    String string = intent.getExtras().getString("data_type");
                    if (string != null && string.equals("device_id")) {
                        Context context2 = context;
                        m1172a(context2, string, "response", intent.getExtras().getString("sender"), C0477a.m656a().m684c());
                    }
                } else if ("response".equals(intent.getExtras().getString(LoginActivity.EXTRA_MODE)) && context.getPackageName().equals(intent.getExtras().getString("sender"))) {
                    if ("device_id".equals(intent.getExtras().getString("data_type"))) {
                        C0477a.m656a().m681b(intent.getExtras().getString("data"));
                    }
                }
            }
        } catch (Throwable th) {
            WeakHashMap weakHashMap = new WeakHashMap();
            weakHashMap.put("SECTION", "SERVICE");
            weakHashMap.put("CLASS", "RemoteReceiver");
            weakHashMap.put("METHOD", "onReceive");
            C0545m.m1045a(weakHashMap, th);
        }
    }
}
