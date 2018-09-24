package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public final class zzdo extends zzf {
    @VisibleForTesting
    protected zzdn zzaro;
    private volatile zzdn zzarp;
    private zzdn zzarq;
    private final Map<Activity, zzdn> zzarr = new ArrayMap();
    private zzdn zzars;
    private String zzart;

    public zzdo(zzbt zzbt) {
        super(zzbt);
    }

    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final zzdn zzla() {
        zzcl();
        zzaf();
        return this.zzaro;
    }

    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        if (this.zzarp == null) {
            zzgo().zzjg().zzbx("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzarr.get(activity) == null) {
            zzgo().zzjg().zzbx("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzcn(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzarp.zzarl.equals(str2);
            boolean zzu = zzfk.zzu(this.zzarp.zzuw, str);
            if (equals && zzu) {
                zzgo().zzji().zzbx("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzgo().zzjg().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                Object obj;
                zzar zzjl = zzgo().zzjl();
                String str3 = "Setting current screen to name, class";
                if (str == null) {
                    obj = "null";
                } else {
                    String str4 = str;
                }
                zzjl.zze(str3, obj, str2);
                zzdn zzdn = new zzdn(str, str2, zzgm().zzmc());
                this.zzarr.put(activity, zzdn);
                zza(activity, zzdn, true);
            } else {
                zzgo().zzjg().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzdn zzlb() {
        zzgb();
        return this.zzarp;
    }

    @MainThread
    private final void zza(Activity activity, zzdn zzdn, boolean z) {
        zzdn zzdn2 = this.zzarp == null ? this.zzarq : this.zzarp;
        if (zzdn.zzarl == null) {
            zzdn = new zzdn(zzdn.zzuw, zzcn(activity.getClass().getCanonicalName()), zzdn.zzarm);
        }
        this.zzarq = this.zzarp;
        this.zzarp = zzdn;
        zzgn().zzc(new zzdp(this, z, zzdn2, zzdn));
    }

    @WorkerThread
    private final void zza(@NonNull zzdn zzdn) {
        zzgd().zzq(zzbx().elapsedRealtime());
        if (zzgj().zzn(zzdn.zzarn)) {
            zzdn.zzarn = false;
        }
    }

    public static void zza(zzdn zzdn, Bundle bundle, boolean z) {
        if (bundle != null && zzdn != null && (!bundle.containsKey("_sc") || z)) {
            if (zzdn.zzuw != null) {
                bundle.putString("_sn", zzdn.zzuw);
            } else {
                bundle.remove("_sn");
            }
            bundle.putString("_sc", zzdn.zzarl);
            bundle.putLong("_si", zzdn.zzarm);
        } else if (bundle != null && zzdn == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    @WorkerThread
    public final void zza(String str, zzdn zzdn) {
        zzaf();
        synchronized (this) {
            if (this.zzart == null || this.zzart.equals(str) || zzdn != null) {
                this.zzart = str;
                this.zzars = zzdn;
            }
        }
    }

    @VisibleForTesting
    private static String zzcn(String str) {
        String str2;
        String[] split = str.split("\\.");
        if (split.length > 0) {
            str2 = split[split.length - 1];
        } else {
            str2 = "";
        }
        if (str2.length() > 100) {
            return str2.substring(0, 100);
        }
        return str2;
    }

    @MainThread
    private final zzdn zze(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzdn zzdn = (zzdn) this.zzarr.get(activity);
        if (zzdn != null) {
            return zzdn;
        }
        zzdn = new zzdn(null, zzcn(activity.getClass().getCanonicalName()), zzgm().zzmc());
        this.zzarr.put(activity, zzdn);
        return zzdn;
    }

    @MainThread
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.app_measurement.screen_service");
            if (bundle2 != null) {
                this.zzarr.put(activity, new zzdn(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong(TtmlNode.ATTR_ID)));
            }
        }
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zza(activity, zze(activity), false);
        zzco zzgd = zzgd();
        zzgd.zzgn().zzc(new zzd(zzgd, zzgd.zzbx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        zzdn zze = zze(activity);
        this.zzarq = this.zzarp;
        this.zzarp = null;
        zzgn().zzc(new zzdq(this, zze));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zzdn zzdn = (zzdn) this.zzarr.get(activity);
            if (zzdn != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong(TtmlNode.ATTR_ID, zzdn.zzarm);
                bundle2.putString("name", zzdn.zzuw);
                bundle2.putString("referrer_name", zzdn.zzarl);
                bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
            }
        }
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzarr.remove(activity);
    }

    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    public final /* bridge */ /* synthetic */ void zzgc() {
        super.zzgc();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zza zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzcs zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzaj zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzdr zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzdo zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzal zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzeq zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
