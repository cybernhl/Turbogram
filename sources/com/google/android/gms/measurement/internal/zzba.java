package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.util.Locale;

final class zzba extends zzcp {
    @VisibleForTesting
    static final Pair<String, Long> zzanc = new Pair("", Long.valueOf(0));
    private SharedPreferences zzabr;
    public zzbe zzand;
    public final zzbd zzane = new zzbd(this, "last_upload", 0);
    public final zzbd zzanf = new zzbd(this, "last_upload_attempt", 0);
    public final zzbd zzang = new zzbd(this, "backoff", 0);
    public final zzbd zzanh = new zzbd(this, "last_delete_stale", 0);
    public final zzbd zzani = new zzbd(this, "midnight_offset", 0);
    public final zzbd zzanj = new zzbd(this, "first_open_time", 0);
    public final zzbd zzank = new zzbd(this, "app_install_time", 0);
    public final zzbf zzanl = new zzbf(this, "app_instance_id", null);
    private String zzanm;
    private boolean zzann;
    private long zzano;
    public final zzbd zzanp = new zzbd(this, "time_before_start", 10000);
    public final zzbd zzanq = new zzbd(this, "session_timeout", 1800000);
    public final zzbc zzanr = new zzbc(this, "start_new_session", true);
    public final zzbf zzans = new zzbf(this, "allow_ad_personalization", null);
    public final zzbd zzant = new zzbd(this, "last_pause_time", 0);
    public final zzbd zzanu = new zzbd(this, "time_active", 0);
    public boolean zzanv;

    @WorkerThread
    @NonNull
    final Pair<String, Boolean> zzby(String str) {
        zzaf();
        long elapsedRealtime = zzbx().elapsedRealtime();
        if (this.zzanm != null && elapsedRealtime < this.zzano) {
            return new Pair(this.zzanm, Boolean.valueOf(this.zzann));
        }
        this.zzano = elapsedRealtime + zzgq().zza(str, zzaf.zzaje);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzanm = advertisingIdInfo.getId();
                this.zzann = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzanm == null) {
                this.zzanm = "";
            }
        } catch (Exception e) {
            zzgo().zzjk().zzg("Unable to get advertising id", e);
            this.zzanm = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzanm, Boolean.valueOf(this.zzann));
    }

    @WorkerThread
    final String zzbz(String str) {
        zzaf();
        String str2 = (String) zzby(str).first;
        if (zzfk.getMessageDigest() == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzfk.getMessageDigest().digest(str2.getBytes()))});
    }

    zzba(zzbt zzbt) {
        super(zzbt);
    }

    protected final boolean zzgt() {
        return true;
    }

    @WorkerThread
    protected final void zzgu() {
        this.zzabr = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzanv = this.zzabr.getBoolean("has_been_opened", false);
        if (!this.zzanv) {
            Editor edit = this.zzabr.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzand = new zzbe(this, "health_monitor", Math.max(0, ((Long) zzaf.zzajf.get()).longValue()));
    }

    @WorkerThread
    private final SharedPreferences zzjr() {
        zzaf();
        zzcl();
        return this.zzabr;
    }

    @WorkerThread
    final void zzca(String str) {
        zzaf();
        Editor edit = zzjr().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    @WorkerThread
    final String zzjs() {
        zzaf();
        return zzjr().getString("gmp_app_id", null);
    }

    @WorkerThread
    final void zzcb(String str) {
        zzaf();
        Editor edit = zzjr().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    @WorkerThread
    final String zzjt() {
        zzaf();
        return zzjr().getString("admob_app_id", null);
    }

    @WorkerThread
    final Boolean zzju() {
        zzaf();
        if (zzjr().contains("use_service")) {
            return Boolean.valueOf(zzjr().getBoolean("use_service", false));
        }
        return null;
    }

    @WorkerThread
    final void zzg(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Setting useService", Boolean.valueOf(z));
        Editor edit = zzjr().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    @WorkerThread
    final void zzjv() {
        boolean z = true;
        zzaf();
        zzgo().zzjl().zzbx("Clearing collection preferences.");
        boolean contains = zzjr().contains("measurement_enabled");
        if (contains) {
            z = zzh(true);
        }
        Editor edit = zzjr().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    @WorkerThread
    final void setMeasurementEnabled(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Setting measurementEnabled", Boolean.valueOf(z));
        Editor edit = zzjr().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    @WorkerThread
    final boolean zzh(boolean z) {
        zzaf();
        return zzjr().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    protected final String zzjw() {
        zzaf();
        String string = zzjr().getString("previous_os_version", null);
        zzgk().zzcl();
        String str = VERSION.RELEASE;
        if (!(TextUtils.isEmpty(str) || str.equals(string))) {
            Editor edit = zzjr().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    @WorkerThread
    final void zzi(boolean z) {
        zzaf();
        zzgo().zzjl().zzg("Updating deferred analytics collection", Boolean.valueOf(z));
        Editor edit = zzjr().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    @WorkerThread
    final boolean zzjx() {
        zzaf();
        return zzjr().getBoolean("deferred_analytics_collection", false);
    }

    @WorkerThread
    final boolean zzjy() {
        return this.zzabr.contains("deferred_analytics_collection");
    }
}
