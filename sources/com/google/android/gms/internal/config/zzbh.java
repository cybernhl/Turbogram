package com.google.android.gms.internal.config;

import java.io.IOException;

public abstract class zzbh {
    protected volatile int zzcq = -1;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzad();
    }

    public String toString() {
        return zzbi.zzb(this);
    }

    public abstract zzbh zza(zzay zzay) throws IOException;

    public void zza(zzaz zzaz) throws IOException {
    }

    public zzbh zzad() throws CloneNotSupportedException {
        return (zzbh) super.clone();
    }

    public final int zzah() {
        int zzt = zzt();
        this.zzcq = zzt;
        return zzt;
    }

    protected int zzt() {
        return 0;
    }
}
