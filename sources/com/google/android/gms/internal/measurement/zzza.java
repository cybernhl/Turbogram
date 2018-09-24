package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzza<M extends zzza<M>> extends zzzg {
    protected zzzc zzcfc;

    protected int zzf() {
        if (this.zzcfc == null) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.zzcfc.size()) {
            i++;
            i2 = this.zzcfc.zzcc(i).zzf() + i2;
        }
        return i2;
    }

    public void zza(zzyy zzyy) throws IOException {
        if (this.zzcfc != null) {
            for (int i = 0; i < this.zzcfc.size(); i++) {
                this.zzcfc.zzcc(i).zza(zzyy);
            }
        }
    }

    public final <T> T zza(zzzb<M, T> zzzb) {
        if (this.zzcfc == null) {
            return null;
        }
        zzzd zzcb = this.zzcfc.zzcb(zzzb.tag >>> 3);
        if (zzcb != null) {
            return zzcb.zzb(zzzb);
        }
        return null;
    }

    protected final boolean zza(zzyx zzyx, int i) throws IOException {
        int position = zzyx.getPosition();
        if (!zzyx.zzao(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzzi zzzi = new zzzi(i, zzyx.zzs(position, zzyx.getPosition() - position));
        zzzd zzzd = null;
        if (this.zzcfc == null) {
            this.zzcfc = new zzzc();
        } else {
            zzzd = this.zzcfc.zzcb(i2);
        }
        if (zzzd == null) {
            zzzd = new zzzd();
            this.zzcfc.zza(i2, zzzd);
        }
        zzzd.zza(zzzi);
        return true;
    }

    public final /* synthetic */ zzzg zzyu() throws CloneNotSupportedException {
        return (zzza) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzza zzza = (zzza) super.zzyu();
        zzze.zza(this, zzza);
        return zzza;
    }
}
