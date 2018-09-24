package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzane;
import org.json.JSONObject;

final class zzx implements Runnable {
    private final /* synthetic */ JSONObject zzbmk;
    private final /* synthetic */ zzw zzbml;

    zzx(zzw zzw, JSONObject jSONObject) {
        this.zzbml = zzw;
        this.zzbmk = jSONObject;
    }

    public final void run() {
        this.zzbml.zzbmi.zza("fetchHttpRequestCompleted", this.zzbmk);
        zzane.zzck("Dispatched http response.");
    }
}
