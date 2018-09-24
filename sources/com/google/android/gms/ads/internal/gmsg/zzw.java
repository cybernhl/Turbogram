package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzue;
import java.util.Map;
import org.json.JSONObject;

final class zzw implements Runnable {
    private final /* synthetic */ Map zzbmh;
    final /* synthetic */ zzue zzbmi;
    private final /* synthetic */ HttpClient zzbmj;

    zzw(HttpClient httpClient, Map map, zzue zzue) {
        this.zzbmj = httpClient;
        this.zzbmh = map;
        this.zzbmi = zzue;
    }

    public final void run() {
        zzane.zzck("Received Http request.");
        try {
            JSONObject send = this.zzbmj.send(new JSONObject((String) this.zzbmh.get("http_request")));
            if (send == null) {
                zzane.m588e("Response should not be null.");
            } else {
                zzakk.zzcrm.post(new zzx(this, send));
            }
        } catch (Throwable e) {
            zzane.zzb("Error converting request to json.", e);
        }
    }
}
