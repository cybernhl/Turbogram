package me.cheshmak.android.sdk.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.p018f.C0497c;
import me.cheshmak.android.sdk.core.p019a.C0477a;

public class AppChangeListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            Cheshmak.with(context.getApplicationContext());
            Cheshmak.initTracker(C0477a.m656a().m677b());
            if (intent != null && intent.getAction() != null && intent.getData() != null) {
                C0497c c0497c = new C0497c();
                if ("android.intent.action.PACKAGE_INSTALL".equals(intent.getAction()) || "android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                    c0497c.m798b(context, intent.getData().getEncodedSchemeSpecificPart());
                } else if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                    c0497c.m797a(context, intent.getData().getEncodedSchemeSpecificPart());
                } else if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
                    c0497c.m799c(context, intent.getData().getEncodedSchemeSpecificPart());
                }
            }
        } catch (Throwable th) {
        }
    }
}
