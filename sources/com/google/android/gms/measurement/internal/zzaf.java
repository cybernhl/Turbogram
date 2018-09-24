package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.WorkerThread;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.internal.measurement.zzsv;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendarConstants;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;

@VisibleForTesting
public final class zzaf {
    private static volatile zzbt zzadj;
    static zzk zzaiq;
    static List<zza<Integer>> zzair = new ArrayList();
    static List<zza<Long>> zzais = new ArrayList();
    static List<zza<Boolean>> zzait = new ArrayList();
    static List<zza<String>> zzaiu = new ArrayList();
    static List<zza<Double>> zzaiv = new ArrayList();
    private static final zzsv zzaiw;
    @VisibleForTesting
    private static Boolean zzaix;
    private static zza<Boolean> zzaiy = zza.zzb("measurement.log_third_party_store_events_enabled", false, false);
    private static zza<Boolean> zzaiz = zza.zzb("measurement.log_installs_enabled", false, false);
    private static zza<Boolean> zzaja = zza.zzb("measurement.log_upgrades_enabled", false, false);
    public static zza<Boolean> zzajb = zza.zzb("measurement.log_androidId_enabled", false, false);
    public static zza<Boolean> zzajc = zza.zzb("measurement.upload_dsid_enabled", false, false);
    public static zza<String> zzajd = zza.zzd("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> zzaje = zza.zzb("measurement.ad_id_cache_time", 10000, 10000);
    public static zza<Long> zzajf = zza.zzb("measurement.monitoring.sample_period_millis", (long) PersianCalendarConstants.MILLIS_OF_A_DAY, (long) PersianCalendarConstants.MILLIS_OF_A_DAY);
    public static zza<Long> zzajg = zza.zzb("measurement.config.cache_time", (long) PersianCalendarConstants.MILLIS_OF_A_DAY, 3600000);
    public static zza<String> zzajh;
    public static zza<String> zzaji;
    public static zza<Integer> zzajj = zza.zzc("measurement.upload.max_bundles", 100, 100);
    public static zza<Integer> zzajk = zza.zzc("measurement.upload.max_batch_size", 65536, 65536);
    public static zza<Integer> zzajl = zza.zzc("measurement.upload.max_bundle_size", 65536, 65536);
    public static zza<Integer> zzajm = zza.zzc("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static zza<Integer> zzajn = zza.zzc("measurement.upload.max_events_per_day", DefaultOggSeeker.MATCH_BYTE_RANGE, DefaultOggSeeker.MATCH_BYTE_RANGE);
    public static zza<Integer> zzajo = zza.zzc("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static zza<Integer> zzajp = zza.zzc("measurement.upload.max_public_events_per_day", DefaultLoadControl.DEFAULT_MAX_BUFFER_MS, DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
    public static zza<Integer> zzajq = zza.zzc("measurement.upload.max_conversions_per_day", 500, 500);
    public static zza<Integer> zzajr = zza.zzc("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static zza<Integer> zzajs = zza.zzc("measurement.store.max_stored_events_per_app", DefaultOggSeeker.MATCH_BYTE_RANGE, DefaultOggSeeker.MATCH_BYTE_RANGE);
    public static zza<String> zzajt;
    public static zza<Long> zzaju = zza.zzb("measurement.upload.backoff_period", 43200000, 43200000);
    public static zza<Long> zzajv = zza.zzb("measurement.upload.window_interval", 3600000, 3600000);
    public static zza<Long> zzajw = zza.zzb("measurement.upload.interval", 3600000, 3600000);
    public static zza<Long> zzajx = zza.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static zza<Long> zzajy = zza.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
    public static zza<Long> zzajz = zza.zzb("measurement.upload.minimum_delay", 500, 500);
    public static zza<Long> zzaka = zza.zzb("measurement.alarm_manager.minimum_interval", (long) ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS, (long) ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
    public static zza<Long> zzakb = zza.zzb("measurement.upload.stale_data_deletion_interval", (long) PersianCalendarConstants.MILLIS_OF_A_DAY, (long) PersianCalendarConstants.MILLIS_OF_A_DAY);
    public static zza<Long> zzakc = zza.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static zza<Long> zzakd = zza.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static zza<Long> zzake = zza.zzb("measurement.upload.retry_time", 1800000, 1800000);
    public static zza<Integer> zzakf = zza.zzc("measurement.upload.retry_count", 6, 6);
    public static zza<Long> zzakg = zza.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static zza<Integer> zzakh = zza.zzc("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static zza<Integer> zzaki = zza.zzc("measurement.audience.filter_result_max_count", Callback.DEFAULT_DRAG_ANIMATION_DURATION, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
    public static zza<Long> zzakj = zza.zzb("measurement.service_client.idle_disconnect_millis", (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    public static zza<Boolean> zzakk = zza.zzb("measurement.test.boolean_flag", false, false);
    public static zza<String> zzakl;
    public static zza<Long> zzakm = zza.zzb("measurement.test.long_flag", -1, -1);
    public static zza<Integer> zzakn = zza.zzc("measurement.test.int_flag", -2, -2);
    public static zza<Double> zzako = zza.zza("measurement.test.double_flag", -3.0d, -3.0d);
    public static zza<Boolean> zzakp = zza.zzb("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
    public static zza<Boolean> zzakq = zza.zzb("measurement.audience.complex_param_evaluation", false, false);
    public static zza<Boolean> zzakr = zza.zzb("measurement.validation.internal_limits_internal_event_params", false, false);
    public static zza<Boolean> zzaks = zza.zzb("measurement.quality.unsuccessful_update_retry_counter", false, false);
    public static zza<Boolean> zzakt = zza.zzb("measurement.iid.disable_on_collection_disabled", true, true);
    public static zza<Boolean> zzaku = zza.zzb("measurement.app_launch.call_only_when_enabled", true, true);
    public static zza<Boolean> zzakv = zza.zzb("measurement.run_on_worker_inline", true, false);
    public static zza<Boolean> zzakw = zza.zzb("measurement.audience.dynamic_filters", false, false);
    public static zza<Boolean> zzakx = zza.zzb("measurement.reset_analytics.persist_time", false, false);
    public static zza<Boolean> zzaky = zza.zzb("measurement.validation.value_and_currency_params", false, false);
    public static zza<Boolean> zzakz = zza.zzb("measurement.sampling.time_zone_offset_enabled", false, false);
    public static zza<Boolean> zzala = zza.zzb("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", false, false);
    public static zza<Boolean> zzalb = zza.zzb("measurement.disconnect_from_remote_service", false, false);
    public static zza<Boolean> zzalc = zza.zzb("measurement.clear_local_database", false, false);
    public static zza<Boolean> zzald = zza.zzb("measurement.fetch_config_with_admob_app_id", false, false);
    public static zza<Boolean> zzale = zza.zzb("measurement.sessions.session_id_enabled", false, false);
    public static zza<Boolean> zzalf = zza.zzb("measurement.sessions.immediate_start_enabled", false, false);
    private static zza<Boolean> zzalg = zza.zzb("measurement.sessions.background_sessions_enabled", false, false);
    public static zza<Boolean> zzalh = zza.zzb("measurement.collection.firebase_global_collection_flag_enabled", true, true);
    private static zza<Boolean> zzali = zza.zzb("measurement.collection.efficient_engagement_reporting_enabled", false, false);
    public static zza<Boolean> zzalj = zza.zzb("measurement.personalized_ads_feature_enabled", false, false);
    public static zza<Boolean> zzalk = zza.zzb("measurement.remove_app_instance_id_cache_enabled", true, true);

    @VisibleForTesting
    public static final class zza<V> {
        private final V zzaan;
        private zzsl<V> zzall;
        private final V zzalm;
        private volatile V zzaln;
        private final String zzoj;

        private zza(String str, V v, V v2) {
            this.zzoj = str;
            this.zzaan = v;
            this.zzalm = v2;
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            zza<Boolean> zza = new zza(str, Boolean.valueOf(z), Boolean.valueOf(z2));
            zzaf.zzait.add(zza);
            return zza;
        }

        static zza<String> zzd(String str, String str2, String str3) {
            zza<String> zza = new zza(str, str2, str3);
            zzaf.zzaiu.add(zza);
            return zza;
        }

        static zza<Long> zzb(String str, long j, long j2) {
            zza<Long> zza = new zza(str, Long.valueOf(j), Long.valueOf(j2));
            zzaf.zzais.add(zza);
            return zza;
        }

        static zza<Integer> zzc(String str, int i, int i2) {
            zza<Integer> zza = new zza(str, Integer.valueOf(i), Integer.valueOf(i2));
            zzaf.zzair.add(zza);
            return zza;
        }

        static zza<Double> zza(String str, double d, double d2) {
            zza<Double> zza = new zza(str, Double.valueOf(-3.0d), Double.valueOf(-3.0d));
            zzaf.zzaiv.add(zza);
            return zza;
        }

        public final String getKey() {
            return this.zzoj;
        }

        private static void zzq() {
            synchronized (zza.class) {
                for (zza zza : zzaf.zzait) {
                    zzsv zziw = zzaf.zzaiw;
                    String str = zza.zzoj;
                    zzk zzk = zzaf.zzaiq;
                    zza.zzall = zziw.zzf(str, ((Boolean) zza.zzaan).booleanValue());
                }
                for (zza zza2 : zzaf.zzaiu) {
                    zziw = zzaf.zzaiw;
                    str = zza2.zzoj;
                    zzk = zzaf.zzaiq;
                    zza2.zzall = zziw.zzx(str, (String) zza2.zzaan);
                }
                for (zza zza22 : zzaf.zzais) {
                    zziw = zzaf.zzaiw;
                    str = zza22.zzoj;
                    zzk = zzaf.zzaiq;
                    zza22.zzall = zziw.zze(str, ((Long) zza22.zzaan).longValue());
                }
                for (zza zza222 : zzaf.zzair) {
                    zziw = zzaf.zzaiw;
                    str = zza222.zzoj;
                    zzk = zzaf.zzaiq;
                    zza222.zzall = zziw.zzd(str, ((Integer) zza222.zzaan).intValue());
                }
                for (zza zza2222 : zzaf.zzaiv) {
                    zziw = zzaf.zzaiw;
                    str = zza2222.zzoj;
                    zzk = zzaf.zzaiq;
                    zza2222.zzall = zziw.zzb(str, ((Double) zza2222.zzaan).doubleValue());
                }
            }
        }

        @WorkerThread
        private static void zzix() {
            synchronized (zza.class) {
                if (zzk.isMainThread()) {
                    throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
                }
                zzk zzk = zzaf.zzaiq;
                try {
                    for (zza zza : zzaf.zzait) {
                        zza.zzaln = zza.zzall.get();
                    }
                    for (zza zza2 : zzaf.zzaiu) {
                        zza2.zzaln = zza2.zzall.get();
                    }
                    for (zza zza22 : zzaf.zzais) {
                        zza22.zzaln = zza22.zzall.get();
                    }
                    for (zza zza222 : zzaf.zzair) {
                        zza222.zzaln = zza222.zzall.get();
                    }
                    for (zza zza2222 : zzaf.zzaiv) {
                        zza2222.zzaln = zza2222.zzall.get();
                    }
                } catch (Exception e) {
                    zzaf.zza(e);
                }
            }
        }

        public final V get() {
            if (zzaf.zzaiq == null) {
                return this.zzaan;
            }
            zzk zzk = zzaf.zzaiq;
            if (zzk.isMainThread()) {
                return this.zzaln == null ? this.zzaan : this.zzaln;
            } else {
                zzix();
                try {
                    return this.zzall.get();
                } catch (Exception e) {
                    zzaf.zza(e);
                    return this.zzall.getDefaultValue();
                }
            }
        }

        public final V get(V v) {
            if (v != null) {
                return v;
            }
            if (zzaf.zzaiq == null) {
                return this.zzaan;
            }
            zzk zzk = zzaf.zzaiq;
            if (zzk.isMainThread()) {
                return this.zzaln == null ? this.zzaan : this.zzaln;
            } else {
                zzix();
                try {
                    return this.zzall.get();
                } catch (Exception e) {
                    zzaf.zza(e);
                    return this.zzall.getDefaultValue();
                }
            }
        }
    }

    static void zza(zzbt zzbt) {
        zzadj = zzbt;
    }

    @VisibleForTesting
    static void zza(Exception exception) {
        if (zzadj != null) {
            Context context = zzadj.getContext();
            if (zzaix == null) {
                zzaix = Boolean.valueOf(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000) == 0);
            }
            if (zzaix.booleanValue()) {
                zzadj.zzgo().zzjd().zzg("Got Exception on PhenotypeFlag.get on Play device", exception);
            }
        }
    }

    static void zza(zzk zzk) {
        zzaiq = zzk;
        zza.zzq();
    }

    static {
        String str = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        zzaiw = new zzsv(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
        String str2 = "https";
        zzajh = zza.zzd("measurement.config.url_scheme", str2, str2);
        str2 = "app-measurement.com";
        zzaji = zza.zzd("measurement.config.url_authority", str2, str2);
        str2 = "https://app-measurement.com/a";
        zzajt = zza.zzd("measurement.upload.url", str2, str2);
        str2 = "---";
        zzakl = zza.zzd("measurement.test.string_flag", str2, str2);
    }
}
