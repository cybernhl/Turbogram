package com.google.android.gms.internal.ads;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

public final class zzbd extends zzbfc<zzbd> {
    private Long zzgl;
    private Integer zzgm;
    private Boolean zzgn;
    private int[] zzgo;
    private Long zzgp;

    public zzbd() {
        this.zzgl = null;
        this.zzgm = null;
        this.zzgn = null;
        this.zzgo = zzbfl.zzeby;
        this.zzgp = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzgl = Long.valueOf(zzbez.zzacd());
                    continue;
                case 16:
                    this.zzgm = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 24:
                    this.zzgn = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 32:
                    zzb = zzbfl.zzb(zzbez, 32);
                    zzabk = this.zzgo == null ? 0 : this.zzgo.length;
                    Object obj = new int[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzgo, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.zzacc();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.zzacc();
                    this.zzgo = obj;
                    continue;
                case 34:
                    int zzbr = zzbez.zzbr(zzbez.zzacc());
                    zzb = zzbez.getPosition();
                    zzabk = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzacc();
                        zzabk++;
                    }
                    zzbez.zzdc(zzb);
                    zzb = this.zzgo == null ? 0 : this.zzgo.length;
                    Object obj2 = new int[(zzabk + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzgo, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = zzbez.zzacc();
                        zzb++;
                    }
                    this.zzgo = obj2;
                    zzbez.zzbs(zzbr);
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.zzgp = Long.valueOf(zzbez.zzacd());
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzgl != null) {
            zzbfa.zzi(1, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            zzbfa.zzm(2, this.zzgm.intValue());
        }
        if (this.zzgn != null) {
            zzbfa.zzf(3, this.zzgn.booleanValue());
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            for (int zzm : this.zzgo) {
                zzbfa.zzm(4, zzm);
            }
        }
        if (this.zzgp != null) {
            zzbfa.zza(5, this.zzgp.longValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzgl != null) {
            zzr += zzbfa.zzd(1, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            zzr += zzbfa.zzq(2, this.zzgm.intValue());
        }
        if (this.zzgn != null) {
            this.zzgn.booleanValue();
            zzr += zzbfa.zzcd(3) + 1;
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.zzgo.length) {
                i++;
                i2 = zzbfa.zzce(this.zzgo[i]) + i2;
            }
            zzr = (zzr + i2) + (this.zzgo.length * 1);
        }
        return this.zzgp != null ? zzr + zzbfa.zze(5, this.zzgp.longValue()) : zzr;
    }
}
