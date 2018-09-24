package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbf extends zzbfc<zzbf> {
    public Long zzgl;
    private String zzgt;
    private byte[] zzgu;

    public zzbf() {
        this.zzgl = null;
        this.zzgt = null;
        this.zzgu = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzgl = Long.valueOf(zzbez.zzacd());
                    continue;
                case 26:
                    this.zzgt = zzbez.readString();
                    continue;
                case 34:
                    this.zzgu = zzbez.readBytes();
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
        if (this.zzgt != null) {
            zzbfa.zzf(3, this.zzgt);
        }
        if (this.zzgu != null) {
            zzbfa.zza(4, this.zzgu);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzgl != null) {
            zzr += zzbfa.zzd(1, this.zzgl.longValue());
        }
        if (this.zzgt != null) {
            zzr += zzbfa.zzg(3, this.zzgt);
        }
        return this.zzgu != null ? zzr + zzbfa.zzb(4, this.zzgu) : zzr;
    }
}
