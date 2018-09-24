package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbg extends zzbfc<zzbg> {
    public Integer zzfe;
    private Integer zzff;
    public byte[] zzgq;
    public byte[][] zzgv;

    public zzbg() {
        this.zzgv = zzbfl.zzece;
        this.zzgq = null;
        this.zzebt = -1;
    }

    private final zzbg zzd(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    zzb = zzbfl.zzb(zzbez, 10);
                    zzabk = this.zzgv == null ? 0 : this.zzgv.length;
                    Object obj = new byte[(zzb + zzabk)][];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzgv, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.readBytes();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.readBytes();
                    this.zzgv = obj;
                    continue;
                case 18:
                    this.zzgq = zzbez.readBytes();
                    continue;
                case 24:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzff = Integer.valueOf(zzaz.zze(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 32:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzfe = Integer.valueOf(zzaz.zzf(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        return zzd(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzgv != null && this.zzgv.length > 0) {
            for (byte[] bArr : this.zzgv) {
                if (bArr != null) {
                    zzbfa.zza(1, bArr);
                }
            }
        }
        if (this.zzgq != null) {
            zzbfa.zza(2, this.zzgq);
        }
        if (this.zzff != null) {
            zzbfa.zzm(3, this.zzff.intValue());
        }
        if (this.zzfe != null) {
            zzbfa.zzm(4, this.zzfe.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i;
        int zzr = super.zzr();
        if (this.zzgv == null || this.zzgv.length <= 0) {
            i = zzr;
        } else {
            i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < this.zzgv.length) {
                int zzv;
                byte[] bArr = this.zzgv[i];
                if (bArr != null) {
                    i3++;
                    zzv = zzbfa.zzv(bArr) + i2;
                } else {
                    zzv = i2;
                }
                i++;
                i2 = zzv;
            }
            i = (zzr + i2) + (i3 * 1);
        }
        if (this.zzgq != null) {
            i += zzbfa.zzb(2, this.zzgq);
        }
        if (this.zzff != null) {
            i += zzbfa.zzq(3, this.zzff.intValue());
        }
        return this.zzfe != null ? i + zzbfa.zzq(4, this.zzfe.intValue()) : i;
    }
}
