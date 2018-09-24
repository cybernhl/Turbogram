package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzzg {
    protected volatile int zzcfm = -1;

    public abstract zzzg zza(zzyx zzyx) throws IOException;

    public final int zzza() {
        if (this.zzcfm < 0) {
            zzvu();
        }
        return this.zzcfm;
    }

    public final int zzvu() {
        int zzf = zzf();
        this.zzcfm = zzf;
        return zzf;
    }

    protected int zzf() {
        return 0;
    }

    public void zza(zzyy zzyy) throws IOException {
    }

    public static final void zza(zzzg zzzg, byte[] bArr, int i, int i2) {
        try {
            zzyy zzk = zzyy.zzk(bArr, 0, i2);
            zzzg.zza(zzk);
            zzk.zzyt();
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzzg> T zza(T t, byte[] bArr) throws zzzf {
        return zzb(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzzg> T zzb(T t, byte[] bArr, int i, int i2) throws zzzf {
        try {
            zzyx zzj = zzyx.zzj(bArr, 0, i2);
            t.zza(zzj);
            zzj.zzan(0);
            return t;
        } catch (zzzf e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzzh.zzc(this);
    }

    public zzzg zzyu() throws CloneNotSupportedException {
        return (zzzg) super.clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzyu();
    }
}
