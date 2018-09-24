package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzu;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

final class zzbi implements Runnable {
    private final /* synthetic */ zzu zzaof;
    private final /* synthetic */ ServiceConnection zzaog;
    private final /* synthetic */ zzbh zzaoh;

    zzbi(zzbh zzbh, zzu zzu, ServiceConnection serviceConnection) {
        this.zzaoh = zzbh;
        this.zzaof = zzu;
        this.zzaog = serviceConnection;
    }

    public final void run() {
        zzbg zzbg = this.zzaoh.zzaoe;
        String zza = this.zzaoh.packageName;
        zzu zzu = this.zzaof;
        ServiceConnection serviceConnection = this.zzaog;
        Bundle zza2 = zzbg.zza(zza, zzu);
        zzbg.zzadj.zzgn().zzaf();
        if (zza2 != null) {
            long j = zza2.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzbg.zzadj.zzgo().zzjd().zzbx("Service response is missing Install Referrer install timestamp");
            } else {
                zza = zza2.getString("install_referrer");
                if (zza == null || zza.isEmpty()) {
                    zzbg.zzadj.zzgo().zzjd().zzbx("No referrer defined in install referrer response");
                } else {
                    zzbg.zzadj.zzgo().zzjl().zzg("InstallReferrer API result", zza);
                    zzfk zzgm = zzbg.zzadj.zzgm();
                    String str = "?";
                    zza = String.valueOf(zza);
                    Bundle zza3 = zzgm.zza(Uri.parse(zza.length() != 0 ? str.concat(zza) : new String(str)));
                    if (zza3 == null) {
                        zzbg.zzadj.zzgo().zzjd().zzbx("No campaign params defined in install referrer result");
                    } else {
                        zza = zza3.getString(Param.MEDIUM);
                        Object obj = (zza == null || "(not set)".equalsIgnoreCase(zza) || "organic".equalsIgnoreCase(zza)) ? null : 1;
                        if (obj != null) {
                            long j2 = zza2.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzbg.zzadj.zzgo().zzjd().zzbx("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza3.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzbg.zzadj.zzgp().zzank.get()) {
                            zzbg.zzadj.zzgr();
                            zzbg.zzadj.zzgo().zzjl().zzbx("Campaign has already been logged");
                        } else {
                            zzbg.zzadj.zzgp().zzank.set(j);
                            zzbg.zzadj.zzgr();
                            zzbg.zzadj.zzgo().zzjl().zzg("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza3.putString("_cis", "referrer API");
                            zzbg.zzadj.zzge().logEvent("auto", "_cmp", zza3);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzbg.zzadj.getContext(), serviceConnection);
        }
    }
}
