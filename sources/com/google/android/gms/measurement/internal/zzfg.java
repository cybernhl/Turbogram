package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.telegram.messenger.NotificationBadge.NewHtcHomeBadger;

public final class zzfg extends zzez {
    zzfg(zzfa zzfa) {
        super(zzfa);
    }

    protected final boolean zzgt() {
        return false;
    }

    final void zza(zzgl zzgl, Object obj) {
        Preconditions.checkNotNull(obj);
        zzgl.zzamp = null;
        zzgl.zzawx = null;
        zzgl.zzauh = null;
        if (obj instanceof String) {
            zzgl.zzamp = (String) obj;
        } else if (obj instanceof Long) {
            zzgl.zzawx = (Long) obj;
        } else if (obj instanceof Double) {
            zzgl.zzauh = (Double) obj;
        } else {
            zzgo().zzjd().zzg("Ignoring invalid (type) user attribute value", obj);
        }
    }

    final void zza(zzgg zzgg, Object obj) {
        Preconditions.checkNotNull(obj);
        zzgg.zzamp = null;
        zzgg.zzawx = null;
        zzgg.zzauh = null;
        if (obj instanceof String) {
            zzgg.zzamp = (String) obj;
        } else if (obj instanceof Long) {
            zzgg.zzawx = (Long) obj;
        } else if (obj instanceof Double) {
            zzgg.zzauh = (Double) obj;
        } else {
            zzgo().zzjd().zzg("Ignoring invalid (type) event param value", obj);
        }
    }

    final byte[] zza(zzgh zzgh) {
        try {
            byte[] bArr = new byte[zzgh.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgh.zza(zzk);
            zzk.zzyt();
            return bArr;
        } catch (IOException e) {
            zzgo().zzjd().zzg("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    static zzgg zza(zzgf zzgf, String str) {
        for (zzgg zzgg : zzgf.zzawt) {
            if (zzgg.name.equals(str)) {
                return zzgg;
            }
        }
        return null;
    }

    static Object zzb(zzgf zzgf, String str) {
        zzgg zza = zza(zzgf, str);
        if (zza != null) {
            if (zza.zzamp != null) {
                return zza.zzamp;
            }
            if (zza.zzawx != null) {
                return zza.zzawx;
            }
            if (zza.zzauh != null) {
                return zza.zzauh;
            }
        }
        return null;
    }

    static zzgg[] zza(zzgg[] zzggArr, String str, Object obj) {
        for (zzgg zzgg : zzggArr) {
            if (str.equals(zzgg.name)) {
                zzgg.zzawx = null;
                zzgg.zzamp = null;
                zzgg.zzauh = null;
                if (obj instanceof Long) {
                    zzgg.zzawx = (Long) obj;
                    return zzggArr;
                } else if (obj instanceof String) {
                    zzgg.zzamp = (String) obj;
                    return zzggArr;
                } else if (!(obj instanceof Double)) {
                    return zzggArr;
                } else {
                    zzgg.zzauh = (Double) obj;
                    return zzggArr;
                }
            }
        }
        Object obj2 = new zzgg[(zzggArr.length + 1)];
        System.arraycopy(zzggArr, 0, obj2, 0, zzggArr.length);
        zzgg zzgg2 = new zzgg();
        zzgg2.name = str;
        if (obj instanceof Long) {
            zzgg2.zzawx = (Long) obj;
        } else if (obj instanceof String) {
            zzgg2.zzamp = (String) obj;
        } else if (obj instanceof Double) {
            zzgg2.zzauh = (Double) obj;
        }
        obj2[zzggArr.length] = zzgg2;
        return obj2;
    }

    final String zzb(zzgh zzgh) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (zzgh.zzawy != null) {
            for (zzgi zzgi : zzgh.zzawy) {
                if (!(zzgi == null || zzgi == null)) {
                    zza(stringBuilder, 1);
                    stringBuilder.append("bundle {\n");
                    zza(stringBuilder, 1, "protocol_version", zzgi.zzaxa);
                    zza(stringBuilder, 1, "platform", zzgi.zzaxi);
                    zza(stringBuilder, 1, "gmp_version", zzgi.zzaxm);
                    zza(stringBuilder, 1, "uploading_gmp_version", zzgi.zzaxn);
                    zza(stringBuilder, 1, "config_version", zzgi.zzaxy);
                    zza(stringBuilder, 1, "gmp_app_id", zzgi.zzafx);
                    zza(stringBuilder, 1, "admob_app_id", zzgi.zzawj);
                    zza(stringBuilder, 1, "app_id", zzgi.zztt);
                    zza(stringBuilder, 1, "app_version", zzgi.zzts);
                    zza(stringBuilder, 1, "app_version_major", zzgi.zzaxu);
                    zza(stringBuilder, 1, "firebase_instance_id", zzgi.zzafz);
                    zza(stringBuilder, 1, "dev_cert_hash", zzgi.zzaxq);
                    zza(stringBuilder, 1, "app_store", zzgi.zzage);
                    zza(stringBuilder, 1, "upload_timestamp_millis", zzgi.zzaxd);
                    zza(stringBuilder, 1, "start_timestamp_millis", zzgi.zzaxe);
                    zza(stringBuilder, 1, "end_timestamp_millis", zzgi.zzaxf);
                    zza(stringBuilder, 1, "previous_bundle_start_timestamp_millis", zzgi.zzaxg);
                    zza(stringBuilder, 1, "previous_bundle_end_timestamp_millis", zzgi.zzaxh);
                    zza(stringBuilder, 1, "app_instance_id", zzgi.zzafw);
                    zza(stringBuilder, 1, "resettable_device_id", zzgi.zzaxo);
                    zza(stringBuilder, 1, "device_id", zzgi.zzaxx);
                    zza(stringBuilder, 1, "ds_id", zzgi.zzaya);
                    zza(stringBuilder, 1, "limited_ad_tracking", zzgi.zzaxp);
                    zza(stringBuilder, 1, "os_version", zzgi.zzaxj);
                    zza(stringBuilder, 1, "device_model", zzgi.zzaxk);
                    zza(stringBuilder, 1, "user_default_language", zzgi.zzaia);
                    zza(stringBuilder, 1, "time_zone_offset_minutes", zzgi.zzaxl);
                    zza(stringBuilder, 1, "bundle_sequential_index", zzgi.zzaxr);
                    zza(stringBuilder, 1, "service_upload", zzgi.zzaxs);
                    zza(stringBuilder, 1, "health_monitor", zzgi.zzagv);
                    if (!(zzgi.zzaxz == null || zzgi.zzaxz.longValue() == 0)) {
                        zza(stringBuilder, 1, "android_id", zzgi.zzaxz);
                    }
                    if (zzgi.zzayc != null) {
                        zza(stringBuilder, 1, "retry_counter", zzgi.zzayc);
                    }
                    zzgl[] zzglArr = zzgi.zzaxc;
                    if (zzglArr != null) {
                        for (zzgl zzgl : zzglArr) {
                            if (zzgl != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("user_property {\n");
                                zza(stringBuilder, 2, "set_timestamp_millis", zzgl.zzayl);
                                zza(stringBuilder, 2, "name", zzgl().zzbu(zzgl.name));
                                zza(stringBuilder, 2, "string_value", zzgl.zzamp);
                                zza(stringBuilder, 2, "int_value", zzgl.zzawx);
                                zza(stringBuilder, 2, "double_value", zzgl.zzauh);
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zzgd[] zzgdArr = zzgi.zzaxt;
                    if (zzgdArr != null) {
                        for (zzgd zzgd : zzgdArr) {
                            if (zzgd != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("audience_membership {\n");
                                zza(stringBuilder, 2, "audience_id", zzgd.zzauy);
                                zza(stringBuilder, 2, "new_audience", zzgd.zzawo);
                                zza(stringBuilder, 2, "current_data", zzgd.zzawm);
                                zza(stringBuilder, 2, "previous_data", zzgd.zzawn);
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zzgf[] zzgfArr = zzgi.zzaxb;
                    if (zzgfArr != null) {
                        for (zzgf zzgf : zzgfArr) {
                            if (zzgf != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("event {\n");
                                zza(stringBuilder, 2, "name", zzgl().zzbs(zzgf.name));
                                zza(stringBuilder, 2, "timestamp_millis", zzgf.zzawu);
                                zza(stringBuilder, 2, "previous_timestamp_millis", zzgf.zzawv);
                                zza(stringBuilder, 2, NewHtcHomeBadger.COUNT, zzgf.count);
                                zzgg[] zzggArr = zzgf.zzawt;
                                if (zzggArr != null) {
                                    for (zzgg zzgg : zzggArr) {
                                        if (zzgg != null) {
                                            zza(stringBuilder, 3);
                                            stringBuilder.append("param {\n");
                                            zza(stringBuilder, 3, "name", zzgl().zzbt(zzgg.name));
                                            zza(stringBuilder, 3, "string_value", zzgg.zzamp);
                                            zza(stringBuilder, 3, "int_value", zzgg.zzawx);
                                            zza(stringBuilder, 3, "double_value", zzgg.zzauh);
                                            zza(stringBuilder, 3);
                                            stringBuilder.append("}\n");
                                        }
                                    }
                                }
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zza(stringBuilder, 1);
                    stringBuilder.append("}\n");
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    final String zza(zzfv zzfv) {
        int i = 0;
        if (zzfv == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        zza(stringBuilder, 0, "filter_id", zzfv.zzave);
        zza(stringBuilder, 0, "event_name", zzgl().zzbs(zzfv.zzavf));
        zza(stringBuilder, 1, "event_count_filter", zzfv.zzavi);
        stringBuilder.append("  filters {\n");
        zzfw[] zzfwArr = zzfv.zzavg;
        int length = zzfwArr.length;
        while (i < length) {
            zza(stringBuilder, 2, zzfwArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    final String zza(zzfy zzfy) {
        if (zzfy == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", zzfy.zzave);
        zza(stringBuilder, 0, "property_name", zzgl().zzbu(zzfy.zzavu));
        zza(stringBuilder, 1, zzfy.zzavv);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzgj zzgj) {
        if (zzgj != null) {
            int i2;
            int i3;
            zza(stringBuilder, 3);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzgj.zzayf != null) {
                zza(stringBuilder, 4);
                stringBuilder.append("results: ");
                long[] jArr = zzgj.zzayf;
                int length = jArr.length;
                i2 = 0;
                i3 = 0;
                while (i2 < length) {
                    Long valueOf = Long.valueOf(jArr[i2]);
                    int i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append('\n');
            }
            if (zzgj.zzaye != null) {
                zza(stringBuilder, 4);
                stringBuilder.append("status: ");
                long[] jArr2 = zzgj.zzaye;
                int length2 = jArr2.length;
                i2 = 0;
                i3 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i5 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i2++;
                    i3 = i5;
                }
                stringBuilder.append('\n');
            }
            zza(stringBuilder, 3);
            stringBuilder.append("}\n");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, String str, zzfx zzfx) {
        if (zzfx != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzfx.zzavo != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (zzfx.zzavo.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", zzfx.zzavp);
            zza(stringBuilder, i, "comparison_value", zzfx.zzavq);
            zza(stringBuilder, i, "min_comparison_value", zzfx.zzavr);
            zza(stringBuilder, i, "max_comparison_value", zzfx.zzavs);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzfw zzfw) {
        if (zzfw != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", zzfw.zzavm);
            zza(stringBuilder, i, "param_name", zzgl().zzbt(zzfw.zzavn));
            int i2 = i + 1;
            String str = "string_filter";
            zzfz zzfz = zzfw.zzavk;
            if (zzfz != null) {
                zza(stringBuilder, i2);
                stringBuilder.append(str);
                stringBuilder.append(" {\n");
                if (zzfz.zzavw != null) {
                    Object obj = "UNKNOWN_MATCH_TYPE";
                    switch (zzfz.zzavw.intValue()) {
                        case 1:
                            obj = "REGEXP";
                            break;
                        case 2:
                            obj = "BEGINS_WITH";
                            break;
                        case 3:
                            obj = "ENDS_WITH";
                            break;
                        case 4:
                            obj = "PARTIAL";
                            break;
                        case 5:
                            obj = "EXACT";
                            break;
                        case 6:
                            obj = "IN_LIST";
                            break;
                    }
                    zza(stringBuilder, i2, "match_type", obj);
                }
                zza(stringBuilder, i2, "expression", zzfz.zzavx);
                zza(stringBuilder, i2, "case_sensitive", zzfz.zzavy);
                if (zzfz.zzavz.length > 0) {
                    zza(stringBuilder, i2 + 1);
                    stringBuilder.append("expression_list {\n");
                    for (String str2 : zzfz.zzavz) {
                        zza(stringBuilder, i2 + 2);
                        stringBuilder.append(str2);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("}\n");
                }
                zza(stringBuilder, i2);
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i + 1, "number_filter", zzfw.zzavl);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append('\n');
        }
    }

    final <T extends Parcelable> T zza(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        T t;
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            t = (Parcelable) creator.createFromParcel(obtain);
            return t;
        } catch (ParseException e) {
            t = zzgo().zzjd();
            t.zzbx("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    @WorkerThread
    final boolean zze(zzad zzad, zzh zzh) {
        Preconditions.checkNotNull(zzad);
        Preconditions.checkNotNull(zzh);
        if (!TextUtils.isEmpty(zzh.zzafx) || !TextUtils.isEmpty(zzh.zzagk)) {
            return true;
        }
        zzgr();
        return false;
    }

    static boolean zzcp(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(long[] jArr, int i) {
        if (i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0) {
            return true;
        }
        return false;
    }

    static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i << 6) + i2 < bitSet.length()) {
                if (bitSet.get((i << 6) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    final boolean zzb(long j, long j2) {
        if (j == 0 || j2 <= 0 || Math.abs(zzbx().currentTimeMillis() - j) > j2) {
            return true;
        }
        return false;
    }

    final byte[] zza(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzgo().zzjd().zzg("Failed to ungzip content", e);
            throw e;
        }
    }

    final byte[] zzb(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzgo().zzjd().zzg("Failed to gzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ zzfg zzjo() {
        return super.zzjo();
    }

    public final /* bridge */ /* synthetic */ zzj zzjp() {
        return super.zzjp();
    }

    public final /* bridge */ /* synthetic */ zzq zzjq() {
        return super.zzjq();
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
