package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.util.concurrent.atomic.AtomicReference;

public final class zzan extends zzcp {
    private static final AtomicReference<String[]> zzalt = new AtomicReference();
    private static final AtomicReference<String[]> zzalu = new AtomicReference();
    private static final AtomicReference<String[]> zzalv = new AtomicReference();

    zzan(zzbt zzbt) {
        super(zzbt);
    }

    protected final boolean zzgt() {
        return false;
    }

    private final boolean zzjc() {
        zzgr();
        return this.zzadj.zzkj() && this.zzadj.zzgo().isLoggable(3);
    }

    @Nullable
    protected final String zzbs(String str) {
        if (str == null) {
            return null;
        }
        return zzjc() ? zza(str, Event.zzadl, Event.zzadk, zzalt) : str;
    }

    @Nullable
    protected final String zzbt(String str) {
        if (str == null) {
            return null;
        }
        return zzjc() ? zza(str, Param.zzadn, Param.zzadm, zzalu) : str;
    }

    @Nullable
    protected final String zzbu(String str) {
        if (str == null) {
            return null;
        }
        if (!zzjc()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, UserProperty.zzadp, UserProperty.zzado, zzalv);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("experiment_id");
        stringBuilder.append("(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        int i = 0;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        while (i < strArr.length) {
            if (zzfk.zzu(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(strArr2[i]);
                        stringBuilder.append("(");
                        stringBuilder.append(strArr[i]);
                        stringBuilder.append(")");
                        strArr3[i] = stringBuilder.toString();
                    }
                    str = strArr3[i];
                }
                return str;
            }
            i++;
        }
        return str;
    }

    @Nullable
    protected final String zzb(zzad zzad) {
        if (zzad == null) {
            return null;
        }
        if (!zzjc()) {
            return zzad.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("origin=");
        stringBuilder.append(zzad.origin);
        stringBuilder.append(",name=");
        stringBuilder.append(zzbs(zzad.name));
        stringBuilder.append(",params=");
        stringBuilder.append(zzb(zzad.zzaid));
        return stringBuilder.toString();
    }

    @Nullable
    protected final String zza(zzy zzy) {
        if (zzy == null) {
            return null;
        }
        if (!zzjc()) {
            return zzy.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event{appId='");
        stringBuilder.append(zzy.zztt);
        stringBuilder.append("', name='");
        stringBuilder.append(zzbs(zzy.name));
        stringBuilder.append("', params=");
        stringBuilder.append(zzb(zzy.zzaid));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Nullable
    private final String zzb(zzaa zzaa) {
        if (zzaa == null) {
            return null;
        }
        if (zzjc()) {
            return zzd(zzaa.zziv());
        }
        return zzaa.toString();
    }

    @Nullable
    protected final String zzd(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzjc()) {
            return bundle.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("Bundle[{");
            }
            stringBuilder.append(zzbt(str));
            stringBuilder.append("=");
            stringBuilder.append(bundle.get(str));
        }
        stringBuilder.append("}]");
        return stringBuilder.toString();
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
