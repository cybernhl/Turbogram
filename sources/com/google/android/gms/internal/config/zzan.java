package com.google.android.gms.internal.config;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzan implements Runnable {
    private final Context mContext;
    private final zzar zzaj;
    private final zzao zzat;
    private final zzao zzau;
    private final zzao zzav;

    public zzan(Context context, zzao zzao, zzao zzao2, zzao zzao3, zzar zzar) {
        this.mContext = context;
        this.zzat = zzao;
        this.zzau = zzao2;
        this.zzav = zzao3;
        this.zzaj = zzar;
    }

    private static zzas zza(zzao zzao) {
        zzas zzas = new zzas();
        if (zzao.zzp() != null) {
            Map zzp = zzao.zzp();
            List arrayList = new ArrayList();
            if (zzp != null) {
                for (String str : zzp.keySet()) {
                    List arrayList2 = new ArrayList();
                    Map map = (Map) zzp.get(str);
                    if (map != null) {
                        for (String str2 : map.keySet()) {
                            zzat zzat = new zzat();
                            zzat.zzbi = str2;
                            zzat.zzbj = (byte[]) map.get(str2);
                            arrayList2.add(zzat);
                        }
                    }
                    zzav zzav = new zzav();
                    zzav.namespace = str;
                    zzav.zzbo = (zzat[]) arrayList2.toArray(new zzat[arrayList2.size()]);
                    arrayList.add(zzav);
                }
            }
            zzas.zzbf = (zzav[]) arrayList.toArray(new zzav[arrayList.size()]);
        }
        if (zzao.zzg() != null) {
            List zzg = zzao.zzg();
            zzas.zzbg = (byte[][]) zzg.toArray(new byte[zzg.size()][]);
        }
        zzas.timestamp = zzao.getTimestamp();
        return zzas;
    }

    public final void run() {
        zzbh zzaw = new zzaw();
        if (this.zzat != null) {
            zzaw.zzbp = zza(this.zzat);
        }
        if (this.zzau != null) {
            zzaw.zzbq = zza(this.zzau);
        }
        if (this.zzav != null) {
            zzaw.zzbr = zza(this.zzav);
        }
        if (this.zzaj != null) {
            zzau zzau = new zzau();
            zzau.zzbk = this.zzaj.getLastFetchStatus();
            zzau.zzbl = this.zzaj.isDeveloperModeEnabled();
            zzaw.zzbs = zzau;
        }
        if (!(this.zzaj == null || this.zzaj.zzr() == null)) {
            List arrayList = new ArrayList();
            Map zzr = this.zzaj.zzr();
            for (String str : zzr.keySet()) {
                if (zzr.get(str) != null) {
                    zzax zzax = new zzax();
                    zzax.namespace = str;
                    zzax.zzbv = ((zzal) zzr.get(str)).zzo();
                    zzax.resourceId = ((zzal) zzr.get(str)).getResourceId();
                    arrayList.add(zzax);
                }
            }
            zzaw.zzbt = (zzax[]) arrayList.toArray(new zzax[arrayList.size()]);
        }
        byte[] bArr = new byte[zzaw.zzah()];
        try {
            zzaz zzb = zzaz.zzb(bArr, 0, bArr.length);
            zzaw.zza(zzb);
            zzb.zzac();
            try {
                FileOutputStream openFileOutput = this.mContext.openFileOutput("persisted_config", 0);
                openFileOutput.write(bArr);
                openFileOutput.close();
            } catch (Throwable e) {
                Log.e("AsyncPersisterTask", "Could not persist config.", e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e2);
        }
    }
}
