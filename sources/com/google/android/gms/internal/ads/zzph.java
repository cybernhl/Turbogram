package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONObject;

final /* synthetic */ class zzph implements zzasd {
    private final zzpg zzbjk;
    private final Map zzbjl;
    private final zzacm zzbjm;

    zzph(zzpg zzpg, Map map, zzacm zzacm) {
        this.zzbjk = zzpg;
        this.zzbjl = map;
        this.zzbjm = zzacm;
    }

    public final void zze(boolean z) {
        zzpg zzpg = this.zzbjk;
        Map map = this.zzbjl;
        zzacm zzacm = this.zzbjm;
        zzpg.zzbjj.zzbjh = (String) map.get(TtmlNode.ATTR_ID);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put(TtmlNode.ATTR_ID, zzpg.zzbjj.zzbjh);
            zzacm.zza("sendMessageToNativeJs", jSONObject);
        } catch (Throwable e) {
            zzane.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
