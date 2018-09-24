package com.google.android.gms.internal.ads;

import android.support.v4.view.MotionEventCompat;
import com.googlecode.mp4parser.boxes.microsoft.XtraBox;
import java.io.IOException;

public final class zzbb extends zzbfc<zzbb> {
    private static volatile zzbb[] zzfo;
    public Long zzdo;
    public Long zzdp;
    public Long zzfp;
    public Long zzfq;
    public Long zzfr;
    public Long zzfs;
    public Integer zzft;
    public Long zzfu;
    public Long zzfv;
    public Long zzfw;
    public Integer zzfx;
    public Long zzfy;
    public Long zzfz;
    public Long zzga;
    public Long zzgb;
    public Long zzgc;
    public Long zzgd;
    public Long zzge;
    public Long zzgf;
    private Long zzgg;
    private Long zzgh;

    public zzbb() {
        this.zzdo = null;
        this.zzdp = null;
        this.zzfp = null;
        this.zzfq = null;
        this.zzfr = null;
        this.zzfs = null;
        this.zzfu = null;
        this.zzfv = null;
        this.zzfw = null;
        this.zzfy = null;
        this.zzfz = null;
        this.zzga = null;
        this.zzgb = null;
        this.zzgc = null;
        this.zzgd = null;
        this.zzge = null;
        this.zzgf = null;
        this.zzgg = null;
        this.zzgh = null;
        this.zzebt = -1;
    }

    private final zzbb zzc(zzbez zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzdo = Long.valueOf(zzbez.zzacd());
                    continue;
                case 16:
                    this.zzdp = Long.valueOf(zzbez.zzacd());
                    continue;
                case 24:
                    this.zzfp = Long.valueOf(zzbez.zzacd());
                    continue;
                case 32:
                    this.zzfq = Long.valueOf(zzbez.zzacd());
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.zzfr = Long.valueOf(zzbez.zzacd());
                    continue;
                case 48:
                    this.zzfs = Long.valueOf(zzbez.zzacd());
                    continue;
                case 56:
                    position = zzbez.getPosition();
                    try {
                        this.zzft = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 64:
                    this.zzfu = Long.valueOf(zzbez.zzacd());
                    continue;
                case XtraBox.MP4_XTRA_BT_GUID /*72*/:
                    this.zzfv = Long.valueOf(zzbez.zzacd());
                    continue;
                case 80:
                    this.zzfw = Long.valueOf(zzbez.zzacd());
                    continue;
                case 88:
                    position = zzbez.getPosition();
                    try {
                        this.zzfx = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 96:
                    this.zzfy = Long.valueOf(zzbez.zzacd());
                    continue;
                case 104:
                    this.zzfz = Long.valueOf(zzbez.zzacd());
                    continue;
                case 112:
                    this.zzga = Long.valueOf(zzbez.zzacd());
                    continue;
                case 120:
                    this.zzgb = Long.valueOf(zzbez.zzacd());
                    continue;
                case 128:
                    this.zzgc = Long.valueOf(zzbez.zzacd());
                    continue;
                case 136:
                    this.zzgd = Long.valueOf(zzbez.zzacd());
                    continue;
                case 144:
                    this.zzge = Long.valueOf(zzbez.zzacd());
                    continue;
                case 152:
                    this.zzgf = Long.valueOf(zzbez.zzacd());
                    continue;
                case 160:
                    this.zzgg = Long.valueOf(zzbez.zzacd());
                    continue;
                case 168:
                    this.zzgh = Long.valueOf(zzbez.zzacd());
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

    public static zzbb[] zzs() {
        if (zzfo == null) {
            synchronized (zzbfg.zzebs) {
                if (zzfo == null) {
                    zzfo = new zzbb[0];
                }
            }
        }
        return zzfo;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        return zzc(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzdo != null) {
            zzbfa.zzi(1, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzbfa.zzi(2, this.zzdp.longValue());
        }
        if (this.zzfp != null) {
            zzbfa.zzi(3, this.zzfp.longValue());
        }
        if (this.zzfq != null) {
            zzbfa.zzi(4, this.zzfq.longValue());
        }
        if (this.zzfr != null) {
            zzbfa.zzi(5, this.zzfr.longValue());
        }
        if (this.zzfs != null) {
            zzbfa.zzi(6, this.zzfs.longValue());
        }
        if (this.zzft != null) {
            zzbfa.zzm(7, this.zzft.intValue());
        }
        if (this.zzfu != null) {
            zzbfa.zzi(8, this.zzfu.longValue());
        }
        if (this.zzfv != null) {
            zzbfa.zzi(9, this.zzfv.longValue());
        }
        if (this.zzfw != null) {
            zzbfa.zzi(10, this.zzfw.longValue());
        }
        if (this.zzfx != null) {
            zzbfa.zzm(11, this.zzfx.intValue());
        }
        if (this.zzfy != null) {
            zzbfa.zzi(12, this.zzfy.longValue());
        }
        if (this.zzfz != null) {
            zzbfa.zzi(13, this.zzfz.longValue());
        }
        if (this.zzga != null) {
            zzbfa.zzi(14, this.zzga.longValue());
        }
        if (this.zzgb != null) {
            zzbfa.zzi(15, this.zzgb.longValue());
        }
        if (this.zzgc != null) {
            zzbfa.zzi(16, this.zzgc.longValue());
        }
        if (this.zzgd != null) {
            zzbfa.zzi(17, this.zzgd.longValue());
        }
        if (this.zzge != null) {
            zzbfa.zzi(18, this.zzge.longValue());
        }
        if (this.zzgf != null) {
            zzbfa.zzi(19, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            zzbfa.zzi(20, this.zzgg.longValue());
        }
        if (this.zzgh != null) {
            zzbfa.zzi(21, this.zzgh.longValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzdo != null) {
            zzr += zzbfa.zzd(1, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzr += zzbfa.zzd(2, this.zzdp.longValue());
        }
        if (this.zzfp != null) {
            zzr += zzbfa.zzd(3, this.zzfp.longValue());
        }
        if (this.zzfq != null) {
            zzr += zzbfa.zzd(4, this.zzfq.longValue());
        }
        if (this.zzfr != null) {
            zzr += zzbfa.zzd(5, this.zzfr.longValue());
        }
        if (this.zzfs != null) {
            zzr += zzbfa.zzd(6, this.zzfs.longValue());
        }
        if (this.zzft != null) {
            zzr += zzbfa.zzq(7, this.zzft.intValue());
        }
        if (this.zzfu != null) {
            zzr += zzbfa.zzd(8, this.zzfu.longValue());
        }
        if (this.zzfv != null) {
            zzr += zzbfa.zzd(9, this.zzfv.longValue());
        }
        if (this.zzfw != null) {
            zzr += zzbfa.zzd(10, this.zzfw.longValue());
        }
        if (this.zzfx != null) {
            zzr += zzbfa.zzq(11, this.zzfx.intValue());
        }
        if (this.zzfy != null) {
            zzr += zzbfa.zzd(12, this.zzfy.longValue());
        }
        if (this.zzfz != null) {
            zzr += zzbfa.zzd(13, this.zzfz.longValue());
        }
        if (this.zzga != null) {
            zzr += zzbfa.zzd(14, this.zzga.longValue());
        }
        if (this.zzgb != null) {
            zzr += zzbfa.zzd(15, this.zzgb.longValue());
        }
        if (this.zzgc != null) {
            zzr += zzbfa.zzd(16, this.zzgc.longValue());
        }
        if (this.zzgd != null) {
            zzr += zzbfa.zzd(17, this.zzgd.longValue());
        }
        if (this.zzge != null) {
            zzr += zzbfa.zzd(18, this.zzge.longValue());
        }
        if (this.zzgf != null) {
            zzr += zzbfa.zzd(19, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            zzr += zzbfa.zzd(20, this.zzgg.longValue());
        }
        return this.zzgh != null ? zzr + zzbfa.zzd(21, this.zzgh.longValue()) : zzr;
    }
}
