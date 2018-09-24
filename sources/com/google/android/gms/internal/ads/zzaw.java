package com.google.android.gms.internal.ads;

import com.googlecode.mp4parser.boxes.microsoft.XtraBox;
import java.io.IOException;

public final class zzaw extends zzbfc<zzaw> {
    private String stackTrace;
    public String zzco;
    public Long zzcp;
    private String zzcq;
    private String zzcr;
    private Long zzcs;
    private Long zzct;
    private String zzcu;
    private Long zzcv;
    private String zzcw;

    public zzaw() {
        this.zzco = null;
        this.zzcp = null;
        this.stackTrace = null;
        this.zzcq = null;
        this.zzcr = null;
        this.zzcs = null;
        this.zzct = null;
        this.zzcu = null;
        this.zzcv = null;
        this.zzcw = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzco = zzbez.readString();
                    continue;
                case 16:
                    this.zzcp = Long.valueOf(zzbez.zzacd());
                    continue;
                case 26:
                    this.stackTrace = zzbez.readString();
                    continue;
                case 34:
                    this.zzcq = zzbez.readString();
                    continue;
                case 42:
                    this.zzcr = zzbez.readString();
                    continue;
                case 48:
                    this.zzcs = Long.valueOf(zzbez.zzacd());
                    continue;
                case 56:
                    this.zzct = Long.valueOf(zzbez.zzacd());
                    continue;
                case 66:
                    this.zzcu = zzbez.readString();
                    continue;
                case XtraBox.MP4_XTRA_BT_GUID /*72*/:
                    this.zzcv = Long.valueOf(zzbez.zzacd());
                    continue;
                case 82:
                    this.zzcw = zzbez.readString();
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
        if (this.zzco != null) {
            zzbfa.zzf(1, this.zzco);
        }
        if (this.zzcp != null) {
            zzbfa.zzi(2, this.zzcp.longValue());
        }
        if (this.stackTrace != null) {
            zzbfa.zzf(3, this.stackTrace);
        }
        if (this.zzcq != null) {
            zzbfa.zzf(4, this.zzcq);
        }
        if (this.zzcr != null) {
            zzbfa.zzf(5, this.zzcr);
        }
        if (this.zzcs != null) {
            zzbfa.zzi(6, this.zzcs.longValue());
        }
        if (this.zzct != null) {
            zzbfa.zzi(7, this.zzct.longValue());
        }
        if (this.zzcu != null) {
            zzbfa.zzf(8, this.zzcu);
        }
        if (this.zzcv != null) {
            zzbfa.zzi(9, this.zzcv.longValue());
        }
        if (this.zzcw != null) {
            zzbfa.zzf(10, this.zzcw);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzco != null) {
            zzr += zzbfa.zzg(1, this.zzco);
        }
        if (this.zzcp != null) {
            zzr += zzbfa.zzd(2, this.zzcp.longValue());
        }
        if (this.stackTrace != null) {
            zzr += zzbfa.zzg(3, this.stackTrace);
        }
        if (this.zzcq != null) {
            zzr += zzbfa.zzg(4, this.zzcq);
        }
        if (this.zzcr != null) {
            zzr += zzbfa.zzg(5, this.zzcr);
        }
        if (this.zzcs != null) {
            zzr += zzbfa.zzd(6, this.zzcs.longValue());
        }
        if (this.zzct != null) {
            zzr += zzbfa.zzd(7, this.zzct.longValue());
        }
        if (this.zzcu != null) {
            zzr += zzbfa.zzg(8, this.zzcu);
        }
        if (this.zzcv != null) {
            zzr += zzbfa.zzd(9, this.zzcv.longValue());
        }
        return this.zzcw != null ? zzr + zzbfa.zzg(10, this.zzcw) : zzr;
    }
}
