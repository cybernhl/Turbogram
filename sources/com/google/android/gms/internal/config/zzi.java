package com.google.android.gms.internal.config;

import java.util.Collections;
import java.util.Map;

public final class zzi {
    private final long zzf;
    private final Map<String, String> zzg;
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final String zzk;

    private zzi(zzj zzj) {
        this.zzf = zzj.zzf;
        this.zzg = zzj.zzg;
        this.zzh = zzj.zzh;
        this.zzi = zzj.zzi;
        this.zzj = zzj.zzj;
        this.zzk = zzj.zzk;
    }

    public final String getGmpAppId() {
        return this.zzk;
    }

    public final long zza() {
        return this.zzf;
    }

    public final Map<String, String> zzb() {
        return this.zzg == null ? Collections.emptyMap() : this.zzg;
    }

    public final int zzc() {
        return this.zzh;
    }

    public final int zzd() {
        return this.zzj;
    }

    public final int zze() {
        return this.zzi;
    }
}
