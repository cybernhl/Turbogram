package com.google.android.gms.internal.config;

import java.io.IOException;

public abstract class zzbb<M extends zzbb<M>> extends zzbh {
    protected zzbd zzch;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbb zzbb = (zzbb) super.zzad();
        zzbf.zza(this, zzbb);
        return zzbb;
    }

    public void zza(zzaz zzaz) throws IOException {
        if (this.zzch != null) {
            for (int i = 0; i < this.zzch.size(); i++) {
                this.zzch.zzp(i).zza(zzaz);
            }
        }
    }

    protected final boolean zza(zzay zzay, int i) throws IOException {
        int position = zzay.getPosition();
        if (!zzay.zzh(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbj zzbj = new zzbj(i, zzay.zza(position, zzay.getPosition() - position));
        zzbe zzbe = null;
        if (this.zzch == null) {
            this.zzch = new zzbd();
        } else {
            zzbe = this.zzch.zzo(i2);
        }
        if (zzbe == null) {
            zzbe = new zzbe();
            this.zzch.zza(i2, zzbe);
        }
        zzbe.zza(zzbj);
        return true;
    }

    public final /* synthetic */ zzbh zzad() throws CloneNotSupportedException {
        return (zzbb) clone();
    }

    protected int zzt() {
        if (this.zzch == null) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.zzch.size()) {
            i++;
            i2 = this.zzch.zzp(i).zzt() + i2;
        }
        return i2;
    }
}
