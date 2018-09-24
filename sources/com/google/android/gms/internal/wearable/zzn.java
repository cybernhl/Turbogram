package com.google.android.gms.internal.wearable;

import java.io.IOException;

public abstract class zzn<M extends zzn<M>> extends zzt {
    protected zzp zzhc;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzn zzn = (zzn) super.zzs();
        zzr.zza(this, zzn);
        return zzn;
    }

    public void zza(zzl zzl) throws IOException {
        if (this.zzhc != null) {
            for (int i = 0; i < this.zzhc.size(); i++) {
                this.zzhc.zzp(i).zza(zzl);
            }
        }
    }

    protected final boolean zza(zzk zzk, int i) throws IOException {
        int position = zzk.getPosition();
        if (!zzk.zzd(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzv zzv = new zzv(i, zzk.zzb(position, zzk.getPosition() - position));
        zzq zzq = null;
        if (this.zzhc == null) {
            this.zzhc = new zzp();
        } else {
            zzq = this.zzhc.zzo(i2);
        }
        if (zzq == null) {
            zzq = new zzq();
            this.zzhc.zza(i2, zzq);
        }
        zzq.zza(zzv);
        return true;
    }

    protected int zzg() {
        if (this.zzhc == null) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.zzhc.size()) {
            i++;
            i2 = this.zzhc.zzp(i).zzg() + i2;
        }
        return i2;
    }

    public final /* synthetic */ zzt zzs() throws CloneNotSupportedException {
        return (zzn) clone();
    }
}
