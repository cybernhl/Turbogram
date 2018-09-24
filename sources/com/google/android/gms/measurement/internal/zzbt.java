package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

public class zzbt implements zzcq {
    private static volatile zzbt zzapl;
    private final boolean zzadv;
    private final String zzadx;
    private final long zzagx;
    private final zzk zzaiq;
    private final String zzapm;
    private final String zzapn;
    private final zzn zzapo;
    private final zzba zzapp;
    private final zzap zzapq;
    private final zzbo zzapr;
    private final zzeq zzaps;
    private final AppMeasurement zzapt;
    private final zzfk zzapu;
    private final zzan zzapv;
    private final zzdo zzapw;
    private final zzcs zzapx;
    private final zza zzapy;
    private zzal zzapz;
    private zzdr zzaqa;
    private zzx zzaqb;
    private zzaj zzaqc;
    private zzbg zzaqd;
    private Boolean zzaqe;
    private long zzaqf;
    private volatile Boolean zzaqg;
    private int zzaqh;
    private int zzaqi;
    private final Context zzri;
    private final Clock zzrz;
    private boolean zzvz = false;

    private zzbt(zzcr zzcr) {
        Preconditions.checkNotNull(zzcr);
        this.zzaiq = new zzk(zzcr.zzri);
        zzaf.zza(this.zzaiq);
        this.zzri = zzcr.zzri;
        this.zzadx = zzcr.zzadx;
        this.zzapm = zzcr.zzapm;
        this.zzapn = zzcr.zzapn;
        this.zzadv = zzcr.zzadv;
        this.zzaqg = zzcr.zzaqg;
        zzsl.init(this.zzri);
        this.zzrz = DefaultClock.getInstance();
        this.zzagx = this.zzrz.currentTimeMillis();
        this.zzapo = new zzn(this);
        zzcp zzba = new zzba(this);
        zzba.zzq();
        this.zzapp = zzba;
        zzba = new zzap(this);
        zzba.zzq();
        this.zzapq = zzba;
        zzba = new zzfk(this);
        zzba.zzq();
        this.zzapu = zzba;
        zzba = new zzan(this);
        zzba.zzq();
        this.zzapv = zzba;
        this.zzapy = new zza(this);
        zzf zzdo = new zzdo(this);
        zzdo.zzq();
        this.zzapw = zzdo;
        zzdo = new zzcs(this);
        zzdo.zzq();
        this.zzapx = zzdo;
        this.zzapt = new AppMeasurement(this);
        zzdo = new zzeq(this);
        zzdo.zzq();
        this.zzaps = zzdo;
        zzba = new zzbo(this);
        zzba.zzq();
        this.zzapr = zzba;
        zzk zzk = this.zzaiq;
        if (this.zzri.getApplicationContext() instanceof Application) {
            zzco zzge = zzge();
            if (zzge.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzge.getContext().getApplicationContext();
                if (zzge.zzaqv == null) {
                    zzge.zzaqv = new zzdm(zzge);
                }
                application.unregisterActivityLifecycleCallbacks(zzge.zzaqv);
                application.registerActivityLifecycleCallbacks(zzge.zzaqv);
                zzge.zzgo().zzjl().zzbx("Registered activity lifecycle callback");
            }
        } else {
            zzgo().zzjg().zzbx("Application context is not an Application");
        }
        this.zzapr.zzc(new zzbu(this, zzcr));
    }

    @WorkerThread
    private final void zza(zzcr zzcr) {
        zzgn().zzaf();
        zzn.zzht();
        zzcp zzx = new zzx(this);
        zzx.zzq();
        this.zzaqb = zzx;
        zzf zzaj = new zzaj(this);
        zzaj.zzq();
        this.zzaqc = zzaj;
        zzf zzal = new zzal(this);
        zzal.zzq();
        this.zzapz = zzal;
        zzal = new zzdr(this);
        zzal.zzq();
        this.zzaqa = zzal;
        this.zzapu.zzgs();
        this.zzapp.zzgs();
        this.zzaqd = new zzbg(this);
        this.zzaqc.zzgs();
        zzgo().zzjj().zzg("App measurement is starting up, version", Long.valueOf(this.zzapo.zzhc()));
        zzk zzk = this.zzaiq;
        zzgo().zzjj().zzbx("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzk = this.zzaiq;
        String zzal2 = zzaj.zzal();
        if (TextUtils.isEmpty(this.zzadx)) {
            zzar zzjj;
            if (zzgm().zzcw(zzal2)) {
                zzjj = zzgo().zzjj();
                zzal2 = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzjj = zzgo().zzjj();
                String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
                zzal2 = String.valueOf(zzal2);
                zzal2 = zzal2.length() != 0 ? str.concat(zzal2) : new String(str);
            }
            zzjj.zzbx(zzal2);
        }
        zzgo().zzjk().zzbx("Debug-level message logging enabled");
        if (this.zzaqh != this.zzaqi) {
            zzgo().zzjd().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi));
        }
        this.zzvz = true;
    }

    @WorkerThread
    protected final void start() {
        zzgn().zzaf();
        if (zzgp().zzane.get() == 0) {
            zzgp().zzane.set(this.zzrz.currentTimeMillis());
        }
        if (Long.valueOf(zzgp().zzanj.get()).longValue() == 0) {
            zzgo().zzjl().zzg("Persisting first open", Long.valueOf(this.zzagx));
            zzgp().zzanj.set(this.zzagx);
        }
        zzk zzk;
        if (zzkr()) {
            zzk = this.zzaiq;
            if (!(TextUtils.isEmpty(zzgf().getGmpAppId()) && TextUtils.isEmpty(zzgf().zzgw()))) {
                zzgm();
                if (zzfk.zza(zzgf().getGmpAppId(), zzgp().zzjs(), zzgf().zzgw(), zzgp().zzjt())) {
                    zzgo().zzjj().zzbx("Rechecking which service to use due to a GMP App Id change");
                    zzgp().zzjv();
                    if (this.zzapo.zza(zzaf.zzalc)) {
                        zzgi().resetAnalyticsData();
                    }
                    this.zzaqa.disconnect();
                    this.zzaqa.zzdj();
                    zzgp().zzanj.set(this.zzagx);
                    zzgp().zzanl.zzcc(null);
                }
                zzgp().zzca(zzgf().getGmpAppId());
                zzgp().zzcb(zzgf().zzgw());
                if (this.zzapo.zzbj(zzgf().zzal())) {
                    this.zzaps.zzam(this.zzagx);
                }
            }
            zzge().zzcm(zzgp().zzanl.zzjz());
            zzk = this.zzaiq;
            if (!TextUtils.isEmpty(zzgf().getGmpAppId()) || !TextUtils.isEmpty(zzgf().zzgw())) {
                boolean isEnabled = isEnabled();
                if (!(zzgp().zzjy() || this.zzapo.zzhu())) {
                    zzgp().zzi(!isEnabled);
                }
                if (this.zzapo.zze(zzgf().zzal(), zzaf.zzalj)) {
                    zzj(false);
                }
                if (!this.zzapo.zzbd(zzgf().zzal()) || isEnabled) {
                    zzge().zzkz();
                }
                zzgg().zza(new AtomicReference());
            }
        } else if (isEnabled()) {
            if (!zzgm().zzx("android.permission.INTERNET")) {
                zzgo().zzjd().zzbx("App is missing INTERNET permission");
            }
            if (!zzgm().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                zzgo().zzjd().zzbx("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzk = this.zzaiq;
            if (!(Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapo.zzib())) {
                if (!zzbj.zza(this.zzri)) {
                    zzgo().zzjd().zzbx("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzfk.zza(this.zzri, false)) {
                    zzgo().zzjd().zzbx("AppMeasurementService not registered/enabled");
                }
            }
            zzgo().zzjd().zzbx("Uploading is not possible. App measurement disabled");
        }
    }

    final void zzj(boolean z) {
        Object obj;
        zzgn().zzaf();
        Object zzjz = zzgp().zzans.zzjz();
        if (z || zzjz == null) {
            int i = 1;
        } else if ("unset".equals(zzjz)) {
            zzge().zza("app", "_ap", null, this.zzrz.currentTimeMillis());
            obj = 1;
        } else {
            zzge().zza("app", "_ap", zzjz, this.zzrz.currentTimeMillis());
            obj = null;
        }
        if (obj != null) {
            Boolean zzau = this.zzapo.zzau("google_analytics_default_allow_ad_personalization_signals");
            if (zzau != null) {
                zzge().zza("auto", "_ap", Long.valueOf(zzau.booleanValue() ? 1 : 0), this.zzrz.currentTimeMillis());
            } else {
                zzge().zza("auto", "_ap", null, this.zzrz.currentTimeMillis());
            }
        }
    }

    public final zzk zzgr() {
        return this.zzaiq;
    }

    public final zzn zzgq() {
        return this.zzapo;
    }

    public final zzba zzgp() {
        zza(this.zzapp);
        return this.zzapp;
    }

    public final zzap zzgo() {
        zza(this.zzapq);
        return this.zzapq;
    }

    public final zzap zzkf() {
        return (this.zzapq == null || !this.zzapq.isInitialized()) ? null : this.zzapq;
    }

    public final zzbo zzgn() {
        zza(this.zzapr);
        return this.zzapr;
    }

    public final zzeq zzgj() {
        zza(this.zzaps);
        return this.zzaps;
    }

    public final zzbg zzkg() {
        return this.zzaqd;
    }

    final zzbo zzkh() {
        return this.zzapr;
    }

    public final zzcs zzge() {
        zza(this.zzapx);
        return this.zzapx;
    }

    public final AppMeasurement zzki() {
        return this.zzapt;
    }

    public final zzfk zzgm() {
        zza(this.zzapu);
        return this.zzapu;
    }

    public final zzan zzgl() {
        zza(this.zzapv);
        return this.zzapv;
    }

    public final zzal zzgi() {
        zza(this.zzapz);
        return this.zzapz;
    }

    public final Context getContext() {
        return this.zzri;
    }

    public final boolean zzkj() {
        return TextUtils.isEmpty(this.zzadx);
    }

    public final String zzkk() {
        return this.zzadx;
    }

    public final String zzkl() {
        return this.zzapm;
    }

    public final String zzkm() {
        return this.zzapn;
    }

    public final boolean zzkn() {
        return this.zzadv;
    }

    public final Clock zzbx() {
        return this.zzrz;
    }

    public final zzdo zzgh() {
        zza(this.zzapw);
        return this.zzapw;
    }

    public final zzdr zzgg() {
        zza(this.zzaqa);
        return this.zzaqa;
    }

    public final zzx zzgk() {
        zza(this.zzaqb);
        return this.zzaqb;
    }

    public final zzaj zzgf() {
        zza(this.zzaqc);
        return this.zzaqc;
    }

    public final zza zzgd() {
        if (this.zzapy != null) {
            return this.zzapy;
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzbt zza(Context context, zzak zzak) {
        if (zzak != null && (zzak.origin == null || zzak.zzadx == null)) {
            zzak = new zzak(zzak.zzadt, zzak.zzadu, zzak.zzadv, zzak.zzadw, null, null, zzak.zzady);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzapl == null) {
            synchronized (zzbt.class) {
                if (zzapl == null) {
                    zzapl = new zzbt(new zzcr(context, zzak));
                }
            }
        } else if (!(zzak == null || zzak.zzady == null || !zzak.zzady.containsKey("dataCollectionDefaultEnabled"))) {
            zzapl.zzd(zzak.zzady.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzapl;
    }

    private final void zzcl() {
        if (!this.zzvz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zza(zzcp zzcp) {
        if (zzcp == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzcp.isInitialized()) {
            String valueOf = String.valueOf(zzcp.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    private static void zza(zzf zzf) {
        if (zzf == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzf.isInitialized()) {
            String valueOf = String.valueOf(zzf.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    private static void zza(zzco zzco) {
        if (zzco == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    @WorkerThread
    final void zzd(boolean z) {
        this.zzaqg = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzko() {
        return this.zzaqg != null && this.zzaqg.booleanValue();
    }

    @WorkerThread
    public final boolean isEnabled() {
        zzgn().zzaf();
        zzcl();
        if (this.zzapo.zzhu()) {
            return false;
        }
        boolean booleanValue;
        Boolean zzhv = this.zzapo.zzhv();
        if (zzhv != null) {
            booleanValue = zzhv.booleanValue();
        } else {
            boolean z;
            if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                z = false;
            } else {
                z = true;
            }
            if (z && this.zzaqg != null && ((Boolean) zzaf.zzalh.get()).booleanValue()) {
                booleanValue = this.zzaqg.booleanValue();
            } else {
                booleanValue = z;
            }
        }
        return zzgp().zzh(booleanValue);
    }

    final long zzkp() {
        Long valueOf = Long.valueOf(zzgp().zzanj.get());
        if (valueOf.longValue() == 0) {
            return this.zzagx;
        }
        return Math.min(this.zzagx, valueOf.longValue());
    }

    final void zzgb() {
        zzk zzk = this.zzaiq;
    }

    final void zzga() {
        zzk zzk = this.zzaiq;
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void zzb(zzcp zzcp) {
        this.zzaqh++;
    }

    final void zzb(zzf zzf) {
        this.zzaqh++;
    }

    final void zzkq() {
        this.zzaqi++;
    }

    @WorkerThread
    protected final boolean zzkr() {
        boolean z = false;
        zzcl();
        zzgn().zzaf();
        if (this.zzaqe == null || this.zzaqf == 0 || !(this.zzaqe == null || this.zzaqe.booleanValue() || Math.abs(this.zzrz.elapsedRealtime() - this.zzaqf) <= 1000)) {
            this.zzaqf = this.zzrz.elapsedRealtime();
            zzk zzk = this.zzaiq;
            boolean z2 = zzgm().zzx("android.permission.INTERNET") && zzgm().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapo.zzib() || (zzbj.zza(this.zzri) && zzfk.zza(this.zzri, false)));
            this.zzaqe = Boolean.valueOf(z2);
            if (this.zzaqe.booleanValue()) {
                if (zzgm().zzt(zzgf().getGmpAppId(), zzgf().zzgw()) || !TextUtils.isEmpty(zzgf().zzgw())) {
                    z = true;
                }
                this.zzaqe = Boolean.valueOf(z);
            }
        }
        return this.zzaqe.booleanValue();
    }
}
