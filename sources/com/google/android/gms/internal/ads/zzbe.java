package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbe extends zzbfc<zzbe> {
    public byte[] data;
    public byte[] zzgq;
    public byte[] zzgr;
    public byte[] zzgs;

    public zzbe() {
        this.data = null;
        this.zzgq = null;
        this.zzgr = null;
        this.zzgs = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.data = zzbez.readBytes();
                    continue;
                case 18:
                    this.zzgq = zzbez.readBytes();
                    continue;
                case 26:
                    this.zzgr = zzbez.readBytes();
                    continue;
                case 34:
                    this.zzgs = zzbez.readBytes();
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
        if (this.data != null) {
            zzbfa.zza(1, this.data);
        }
        if (this.zzgq != null) {
            zzbfa.zza(2, this.zzgq);
        }
        if (this.zzgr != null) {
            zzbfa.zza(3, this.zzgr);
        }
        if (this.zzgs != null) {
            zzbfa.zza(4, this.zzgs);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.data != null) {
            zzr += zzbfa.zzb(1, this.data);
        }
        if (this.zzgq != null) {
            zzr += zzbfa.zzb(2, this.zzgq);
        }
        if (this.zzgr != null) {
            zzr += zzbfa.zzb(3, this.zzgr);
        }
        return this.zzgs != null ? zzr + zzbfa.zzb(4, this.zzgs) : zzr;
    }
}
