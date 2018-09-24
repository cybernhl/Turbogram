package me.cheshmak.android.sdk.core.push;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;
import me.cheshmak.android.sdk.core.p022l.C0550q;

public class CheshIDListenerService extends InstanceIDListenerService {
    public void onTokenRefresh() {
        C0550q.m1064a((Context) this, new Intent(this, CheshmakPushRegistration.class));
    }
}
