package com.google.android.gms.internal.ads;

import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.wallet.WalletConstants;
import com.googlecode.mp4parser.boxes.microsoft.XtraBox;
import com.mohamadamin.persianmaterialdatetimepicker.Utils;
import java.io.IOException;
import org.telegram.messenger.support.widget.helper.ItemTouchHelper.Callback;

public final class zzba extends zzbfc<zzba> {
    public String zzcw;
    public String zzcx;
    public String zzcz;
    public String zzda;
    public String zzdb;
    public String zzdc;
    public Long zzdd;
    private Long zzde;
    public Long zzdf;
    public Long zzdg;
    private Long zzdh;
    private Long zzdi;
    private Long zzdj;
    private Long zzdk;
    private Long zzdl;
    public Long zzdm;
    private String zzdn;
    public Long zzdo;
    public Long zzdp;
    public Long zzdq;
    public Long zzdr;
    private Long zzds;
    private Long zzdt;
    public Long zzdu;
    public Long zzdv;
    public Long zzdw;
    public String zzdx;
    public Long zzdy;
    public Long zzdz;
    public Long zzea;
    public Long zzeb;
    public Long zzec;
    public Long zzed;
    private zzbd zzee;
    public Long zzef;
    public Long zzeg;
    public Long zzeh;
    public Long zzei;
    public Long zzej;
    public Long zzek;
    public Integer zzel;
    public Integer zzem;
    public Long zzen;
    public Long zzeo;
    public Long zzep;
    private Long zzeq;
    private Long zzer;
    public Integer zzes;
    public zzbb zzet;
    public zzbb[] zzeu;
    public zzbc zzev;
    private Long zzew;
    public Long zzex;
    public Long zzey;
    public Long zzez;
    public Long zzfa;
    public Long zzfb;
    public String zzfc;
    private Long zzfd;
    private Integer zzfe;
    private Integer zzff;
    private Integer zzfg;
    private Long zzfh;
    public String zzfi;
    public Integer zzfj;
    public Boolean zzfk;
    private String zzfl;
    public Long zzfm;
    public zzbf zzfn;

    public zzba() {
        this.zzdc = null;
        this.zzcw = null;
        this.zzdd = null;
        this.zzde = null;
        this.zzdf = null;
        this.zzdg = null;
        this.zzdh = null;
        this.zzdi = null;
        this.zzdj = null;
        this.zzdk = null;
        this.zzdl = null;
        this.zzdm = null;
        this.zzdn = null;
        this.zzdo = null;
        this.zzdp = null;
        this.zzdq = null;
        this.zzdr = null;
        this.zzds = null;
        this.zzdt = null;
        this.zzdu = null;
        this.zzdv = null;
        this.zzdw = null;
        this.zzcx = null;
        this.zzdx = null;
        this.zzdy = null;
        this.zzdz = null;
        this.zzea = null;
        this.zzcz = null;
        this.zzeb = null;
        this.zzec = null;
        this.zzed = null;
        this.zzee = null;
        this.zzef = null;
        this.zzeg = null;
        this.zzeh = null;
        this.zzei = null;
        this.zzej = null;
        this.zzek = null;
        this.zzda = null;
        this.zzdb = null;
        this.zzen = null;
        this.zzeo = null;
        this.zzep = null;
        this.zzeq = null;
        this.zzer = null;
        this.zzet = null;
        this.zzeu = zzbb.zzs();
        this.zzev = null;
        this.zzew = null;
        this.zzex = null;
        this.zzey = null;
        this.zzez = null;
        this.zzfa = null;
        this.zzfb = null;
        this.zzfc = null;
        this.zzfd = null;
        this.zzfh = null;
        this.zzfi = null;
        this.zzfk = null;
        this.zzfl = null;
        this.zzfm = null;
        this.zzfn = null;
        this.zzebt = -1;
    }

    private final zzba zzb(zzbez zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzacc;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzdc = zzbez.readString();
                    continue;
                case 18:
                    this.zzcw = zzbez.readString();
                    continue;
                case 24:
                    this.zzdd = Long.valueOf(zzbez.zzacd());
                    continue;
                case 32:
                    this.zzde = Long.valueOf(zzbez.zzacd());
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.zzdf = Long.valueOf(zzbez.zzacd());
                    continue;
                case 48:
                    this.zzdg = Long.valueOf(zzbez.zzacd());
                    continue;
                case 56:
                    this.zzdh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 64:
                    this.zzdi = Long.valueOf(zzbez.zzacd());
                    continue;
                case XtraBox.MP4_XTRA_BT_GUID /*72*/:
                    this.zzdj = Long.valueOf(zzbez.zzacd());
                    continue;
                case 80:
                    this.zzdk = Long.valueOf(zzbez.zzacd());
                    continue;
                case 88:
                    this.zzdl = Long.valueOf(zzbez.zzacd());
                    continue;
                case 96:
                    this.zzdm = Long.valueOf(zzbez.zzacd());
                    continue;
                case 106:
                    this.zzdn = zzbez.readString();
                    continue;
                case 112:
                    this.zzdo = Long.valueOf(zzbez.zzacd());
                    continue;
                case 120:
                    this.zzdp = Long.valueOf(zzbez.zzacd());
                    continue;
                case 128:
                    this.zzdq = Long.valueOf(zzbez.zzacd());
                    continue;
                case 136:
                    this.zzdr = Long.valueOf(zzbez.zzacd());
                    continue;
                case 144:
                    this.zzds = Long.valueOf(zzbez.zzacd());
                    continue;
                case 152:
                    this.zzdt = Long.valueOf(zzbez.zzacd());
                    continue;
                case 160:
                    this.zzdu = Long.valueOf(zzbez.zzacd());
                    continue;
                case 168:
                    this.zzfh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 176:
                    this.zzdv = Long.valueOf(zzbez.zzacd());
                    continue;
                case 184:
                    this.zzdw = Long.valueOf(zzbez.zzacd());
                    continue;
                case 194:
                    this.zzfi = zzbez.readString();
                    continue;
                case Callback.DEFAULT_DRAG_ANIMATION_DURATION /*200*/:
                    this.zzfm = Long.valueOf(zzbez.zzacd());
                    continue;
                case 208:
                    position = zzbez.getPosition();
                    try {
                        zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 6) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum DeviceIdType");
                        }
                        this.zzfj = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 218:
                    this.zzcx = zzbez.readString();
                    continue;
                case 224:
                    this.zzfk = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 234:
                    this.zzdx = zzbez.readString();
                    continue;
                case 242:
                    this.zzfl = zzbez.readString();
                    continue;
                case 248:
                    this.zzdy = Long.valueOf(zzbez.zzacd());
                    continue;
                case 256:
                    this.zzdz = Long.valueOf(zzbez.zzacd());
                    continue;
                case 264:
                    this.zzea = Long.valueOf(zzbez.zzacd());
                    continue;
                case 274:
                    this.zzcz = zzbez.readString();
                    continue;
                case 280:
                    this.zzeb = Long.valueOf(zzbez.zzacd());
                    continue;
                case 288:
                    this.zzec = Long.valueOf(zzbez.zzacd());
                    continue;
                case 296:
                    this.zzed = Long.valueOf(zzbez.zzacd());
                    continue;
                case 306:
                    if (this.zzee == null) {
                        this.zzee = new zzbd();
                    }
                    zzbez.zza(this.zzee);
                    continue;
                case 312:
                    this.zzef = Long.valueOf(zzbez.zzacd());
                    continue;
                case 320:
                    this.zzeg = Long.valueOf(zzbez.zzacd());
                    continue;
                case 328:
                    this.zzeh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 336:
                    this.zzei = Long.valueOf(zzbez.zzacd());
                    continue;
                case 346:
                    position = zzbfl.zzb(zzbez, 346);
                    zzabk = this.zzeu == null ? 0 : this.zzeu.length;
                    Object obj = new zzbb[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzeu, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzbb();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzbb();
                    zzbez.zza(obj[zzabk]);
                    this.zzeu = obj;
                    continue;
                case 352:
                    this.zzej = Long.valueOf(zzbez.zzacd());
                    continue;
                case 360:
                    this.zzek = Long.valueOf(zzbez.zzacd());
                    continue;
                case 370:
                    this.zzda = zzbez.readString();
                    continue;
                case 378:
                    this.zzdb = zzbez.readString();
                    continue;
                case 384:
                    position = zzbez.getPosition();
                    try {
                        this.zzel = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 392:
                    position = zzbez.getPosition();
                    try {
                        this.zzem = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e3) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case WalletConstants.ERROR_CODE_SERVICE_UNAVAILABLE /*402*/:
                    if (this.zzet == null) {
                        this.zzet = new zzbb();
                    }
                    zzbez.zza(this.zzet);
                    continue;
                case 408:
                    this.zzen = Long.valueOf(zzbez.zzacd());
                    continue;
                case 416:
                    this.zzeo = Long.valueOf(zzbez.zzacd());
                    continue;
                case 424:
                    this.zzep = Long.valueOf(zzbez.zzacd());
                    continue;
                case 432:
                    this.zzeq = Long.valueOf(zzbez.zzacd());
                    continue;
                case 440:
                    this.zzer = Long.valueOf(zzbez.zzacd());
                    continue;
                case 448:
                    position = zzbez.getPosition();
                    try {
                        this.zzes = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e4) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 458:
                    if (this.zzev == null) {
                        this.zzev = new zzbc();
                    }
                    zzbez.zza(this.zzev);
                    continue;
                case 464:
                    this.zzew = Long.valueOf(zzbez.zzacd());
                    continue;
                case 472:
                    this.zzex = Long.valueOf(zzbez.zzacd());
                    continue;
                case 480:
                    this.zzey = Long.valueOf(zzbez.zzacd());
                    continue;
                case 488:
                    this.zzez = Long.valueOf(zzbez.zzacd());
                    continue;
                case 496:
                    this.zzfa = Long.valueOf(zzbez.zzacd());
                    continue;
                case 504:
                    this.zzfb = Long.valueOf(zzbez.zzacd());
                    continue;
                case 512:
                    this.zzfd = Long.valueOf(zzbez.zzacd());
                    continue;
                case 520:
                    position = zzbez.getPosition();
                    try {
                        this.zzfe = Integer.valueOf(zzaz.zzf(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e5) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 528:
                    position = zzbez.getPosition();
                    try {
                        this.zzff = Integer.valueOf(zzaz.zze(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e6) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 538:
                    this.zzfc = zzbez.readString();
                    continue;
                case Utils.PULSE_ANIMATOR_DURATION /*544*/:
                    position = zzbez.getPosition();
                    try {
                        zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 3) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum DebuggerState");
                        }
                        this.zzfg = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e7) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 1610:
                    if (this.zzfn == null) {
                        this.zzfn = new zzbf();
                    }
                    zzbez.zza(this.zzfn);
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

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        return zzb(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzdc != null) {
            zzbfa.zzf(1, this.zzdc);
        }
        if (this.zzcw != null) {
            zzbfa.zzf(2, this.zzcw);
        }
        if (this.zzdd != null) {
            zzbfa.zzi(3, this.zzdd.longValue());
        }
        if (this.zzde != null) {
            zzbfa.zzi(4, this.zzde.longValue());
        }
        if (this.zzdf != null) {
            zzbfa.zzi(5, this.zzdf.longValue());
        }
        if (this.zzdg != null) {
            zzbfa.zzi(6, this.zzdg.longValue());
        }
        if (this.zzdh != null) {
            zzbfa.zzi(7, this.zzdh.longValue());
        }
        if (this.zzdi != null) {
            zzbfa.zzi(8, this.zzdi.longValue());
        }
        if (this.zzdj != null) {
            zzbfa.zzi(9, this.zzdj.longValue());
        }
        if (this.zzdk != null) {
            zzbfa.zzi(10, this.zzdk.longValue());
        }
        if (this.zzdl != null) {
            zzbfa.zzi(11, this.zzdl.longValue());
        }
        if (this.zzdm != null) {
            zzbfa.zzi(12, this.zzdm.longValue());
        }
        if (this.zzdn != null) {
            zzbfa.zzf(13, this.zzdn);
        }
        if (this.zzdo != null) {
            zzbfa.zzi(14, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzbfa.zzi(15, this.zzdp.longValue());
        }
        if (this.zzdq != null) {
            zzbfa.zzi(16, this.zzdq.longValue());
        }
        if (this.zzdr != null) {
            zzbfa.zzi(17, this.zzdr.longValue());
        }
        if (this.zzds != null) {
            zzbfa.zzi(18, this.zzds.longValue());
        }
        if (this.zzdt != null) {
            zzbfa.zzi(19, this.zzdt.longValue());
        }
        if (this.zzdu != null) {
            zzbfa.zzi(20, this.zzdu.longValue());
        }
        if (this.zzfh != null) {
            zzbfa.zzi(21, this.zzfh.longValue());
        }
        if (this.zzdv != null) {
            zzbfa.zzi(22, this.zzdv.longValue());
        }
        if (this.zzdw != null) {
            zzbfa.zzi(23, this.zzdw.longValue());
        }
        if (this.zzfi != null) {
            zzbfa.zzf(24, this.zzfi);
        }
        if (this.zzfm != null) {
            zzbfa.zzi(25, this.zzfm.longValue());
        }
        if (this.zzfj != null) {
            zzbfa.zzm(26, this.zzfj.intValue());
        }
        if (this.zzcx != null) {
            zzbfa.zzf(27, this.zzcx);
        }
        if (this.zzfk != null) {
            zzbfa.zzf(28, this.zzfk.booleanValue());
        }
        if (this.zzdx != null) {
            zzbfa.zzf(29, this.zzdx);
        }
        if (this.zzfl != null) {
            zzbfa.zzf(30, this.zzfl);
        }
        if (this.zzdy != null) {
            zzbfa.zzi(31, this.zzdy.longValue());
        }
        if (this.zzdz != null) {
            zzbfa.zzi(32, this.zzdz.longValue());
        }
        if (this.zzea != null) {
            zzbfa.zzi(33, this.zzea.longValue());
        }
        if (this.zzcz != null) {
            zzbfa.zzf(34, this.zzcz);
        }
        if (this.zzeb != null) {
            zzbfa.zzi(35, this.zzeb.longValue());
        }
        if (this.zzec != null) {
            zzbfa.zzi(36, this.zzec.longValue());
        }
        if (this.zzed != null) {
            zzbfa.zzi(37, this.zzed.longValue());
        }
        if (this.zzee != null) {
            zzbfa.zza(38, this.zzee);
        }
        if (this.zzef != null) {
            zzbfa.zzi(39, this.zzef.longValue());
        }
        if (this.zzeg != null) {
            zzbfa.zzi(40, this.zzeg.longValue());
        }
        if (this.zzeh != null) {
            zzbfa.zzi(41, this.zzeh.longValue());
        }
        if (this.zzei != null) {
            zzbfa.zzi(42, this.zzei.longValue());
        }
        if (this.zzeu != null && this.zzeu.length > 0) {
            for (zzbfi zzbfi : this.zzeu) {
                if (zzbfi != null) {
                    zzbfa.zza(43, zzbfi);
                }
            }
        }
        if (this.zzej != null) {
            zzbfa.zzi(44, this.zzej.longValue());
        }
        if (this.zzek != null) {
            zzbfa.zzi(45, this.zzek.longValue());
        }
        if (this.zzda != null) {
            zzbfa.zzf(46, this.zzda);
        }
        if (this.zzdb != null) {
            zzbfa.zzf(47, this.zzdb);
        }
        if (this.zzel != null) {
            zzbfa.zzm(48, this.zzel.intValue());
        }
        if (this.zzem != null) {
            zzbfa.zzm(49, this.zzem.intValue());
        }
        if (this.zzet != null) {
            zzbfa.zza(50, this.zzet);
        }
        if (this.zzen != null) {
            zzbfa.zzi(51, this.zzen.longValue());
        }
        if (this.zzeo != null) {
            zzbfa.zzi(52, this.zzeo.longValue());
        }
        if (this.zzep != null) {
            zzbfa.zzi(53, this.zzep.longValue());
        }
        if (this.zzeq != null) {
            zzbfa.zzi(54, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            zzbfa.zzi(55, this.zzer.longValue());
        }
        if (this.zzes != null) {
            zzbfa.zzm(56, this.zzes.intValue());
        }
        if (this.zzev != null) {
            zzbfa.zza(57, this.zzev);
        }
        if (this.zzew != null) {
            zzbfa.zzi(58, this.zzew.longValue());
        }
        if (this.zzex != null) {
            zzbfa.zzi(59, this.zzex.longValue());
        }
        if (this.zzey != null) {
            zzbfa.zzi(60, this.zzey.longValue());
        }
        if (this.zzez != null) {
            zzbfa.zzi(61, this.zzez.longValue());
        }
        if (this.zzfa != null) {
            zzbfa.zzi(62, this.zzfa.longValue());
        }
        if (this.zzfb != null) {
            zzbfa.zzi(63, this.zzfb.longValue());
        }
        if (this.zzfd != null) {
            zzbfa.zzi(64, this.zzfd.longValue());
        }
        if (this.zzfe != null) {
            zzbfa.zzm(65, this.zzfe.intValue());
        }
        if (this.zzff != null) {
            zzbfa.zzm(66, this.zzff.intValue());
        }
        if (this.zzfc != null) {
            zzbfa.zzf(67, this.zzfc);
        }
        if (this.zzfg != null) {
            zzbfa.zzm(68, this.zzfg.intValue());
        }
        if (this.zzfn != null) {
            zzbfa.zza(201, this.zzfn);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzdc != null) {
            zzr += zzbfa.zzg(1, this.zzdc);
        }
        if (this.zzcw != null) {
            zzr += zzbfa.zzg(2, this.zzcw);
        }
        if (this.zzdd != null) {
            zzr += zzbfa.zzd(3, this.zzdd.longValue());
        }
        if (this.zzde != null) {
            zzr += zzbfa.zzd(4, this.zzde.longValue());
        }
        if (this.zzdf != null) {
            zzr += zzbfa.zzd(5, this.zzdf.longValue());
        }
        if (this.zzdg != null) {
            zzr += zzbfa.zzd(6, this.zzdg.longValue());
        }
        if (this.zzdh != null) {
            zzr += zzbfa.zzd(7, this.zzdh.longValue());
        }
        if (this.zzdi != null) {
            zzr += zzbfa.zzd(8, this.zzdi.longValue());
        }
        if (this.zzdj != null) {
            zzr += zzbfa.zzd(9, this.zzdj.longValue());
        }
        if (this.zzdk != null) {
            zzr += zzbfa.zzd(10, this.zzdk.longValue());
        }
        if (this.zzdl != null) {
            zzr += zzbfa.zzd(11, this.zzdl.longValue());
        }
        if (this.zzdm != null) {
            zzr += zzbfa.zzd(12, this.zzdm.longValue());
        }
        if (this.zzdn != null) {
            zzr += zzbfa.zzg(13, this.zzdn);
        }
        if (this.zzdo != null) {
            zzr += zzbfa.zzd(14, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzr += zzbfa.zzd(15, this.zzdp.longValue());
        }
        if (this.zzdq != null) {
            zzr += zzbfa.zzd(16, this.zzdq.longValue());
        }
        if (this.zzdr != null) {
            zzr += zzbfa.zzd(17, this.zzdr.longValue());
        }
        if (this.zzds != null) {
            zzr += zzbfa.zzd(18, this.zzds.longValue());
        }
        if (this.zzdt != null) {
            zzr += zzbfa.zzd(19, this.zzdt.longValue());
        }
        if (this.zzdu != null) {
            zzr += zzbfa.zzd(20, this.zzdu.longValue());
        }
        if (this.zzfh != null) {
            zzr += zzbfa.zzd(21, this.zzfh.longValue());
        }
        if (this.zzdv != null) {
            zzr += zzbfa.zzd(22, this.zzdv.longValue());
        }
        if (this.zzdw != null) {
            zzr += zzbfa.zzd(23, this.zzdw.longValue());
        }
        if (this.zzfi != null) {
            zzr += zzbfa.zzg(24, this.zzfi);
        }
        if (this.zzfm != null) {
            zzr += zzbfa.zzd(25, this.zzfm.longValue());
        }
        if (this.zzfj != null) {
            zzr += zzbfa.zzq(26, this.zzfj.intValue());
        }
        if (this.zzcx != null) {
            zzr += zzbfa.zzg(27, this.zzcx);
        }
        if (this.zzfk != null) {
            this.zzfk.booleanValue();
            zzr += zzbfa.zzcd(28) + 1;
        }
        if (this.zzdx != null) {
            zzr += zzbfa.zzg(29, this.zzdx);
        }
        if (this.zzfl != null) {
            zzr += zzbfa.zzg(30, this.zzfl);
        }
        if (this.zzdy != null) {
            zzr += zzbfa.zzd(31, this.zzdy.longValue());
        }
        if (this.zzdz != null) {
            zzr += zzbfa.zzd(32, this.zzdz.longValue());
        }
        if (this.zzea != null) {
            zzr += zzbfa.zzd(33, this.zzea.longValue());
        }
        if (this.zzcz != null) {
            zzr += zzbfa.zzg(34, this.zzcz);
        }
        if (this.zzeb != null) {
            zzr += zzbfa.zzd(35, this.zzeb.longValue());
        }
        if (this.zzec != null) {
            zzr += zzbfa.zzd(36, this.zzec.longValue());
        }
        if (this.zzed != null) {
            zzr += zzbfa.zzd(37, this.zzed.longValue());
        }
        if (this.zzee != null) {
            zzr += zzbfa.zzb(38, this.zzee);
        }
        if (this.zzef != null) {
            zzr += zzbfa.zzd(39, this.zzef.longValue());
        }
        if (this.zzeg != null) {
            zzr += zzbfa.zzd(40, this.zzeg.longValue());
        }
        if (this.zzeh != null) {
            zzr += zzbfa.zzd(41, this.zzeh.longValue());
        }
        if (this.zzei != null) {
            zzr += zzbfa.zzd(42, this.zzei.longValue());
        }
        if (this.zzeu != null && this.zzeu.length > 0) {
            int i = zzr;
            for (zzbfi zzbfi : this.zzeu) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(43, zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzej != null) {
            zzr += zzbfa.zzd(44, this.zzej.longValue());
        }
        if (this.zzek != null) {
            zzr += zzbfa.zzd(45, this.zzek.longValue());
        }
        if (this.zzda != null) {
            zzr += zzbfa.zzg(46, this.zzda);
        }
        if (this.zzdb != null) {
            zzr += zzbfa.zzg(47, this.zzdb);
        }
        if (this.zzel != null) {
            zzr += zzbfa.zzq(48, this.zzel.intValue());
        }
        if (this.zzem != null) {
            zzr += zzbfa.zzq(49, this.zzem.intValue());
        }
        if (this.zzet != null) {
            zzr += zzbfa.zzb(50, this.zzet);
        }
        if (this.zzen != null) {
            zzr += zzbfa.zzd(51, this.zzen.longValue());
        }
        if (this.zzeo != null) {
            zzr += zzbfa.zzd(52, this.zzeo.longValue());
        }
        if (this.zzep != null) {
            zzr += zzbfa.zzd(53, this.zzep.longValue());
        }
        if (this.zzeq != null) {
            zzr += zzbfa.zzd(54, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            zzr += zzbfa.zzd(55, this.zzer.longValue());
        }
        if (this.zzes != null) {
            zzr += zzbfa.zzq(56, this.zzes.intValue());
        }
        if (this.zzev != null) {
            zzr += zzbfa.zzb(57, this.zzev);
        }
        if (this.zzew != null) {
            zzr += zzbfa.zzd(58, this.zzew.longValue());
        }
        if (this.zzex != null) {
            zzr += zzbfa.zzd(59, this.zzex.longValue());
        }
        if (this.zzey != null) {
            zzr += zzbfa.zzd(60, this.zzey.longValue());
        }
        if (this.zzez != null) {
            zzr += zzbfa.zzd(61, this.zzez.longValue());
        }
        if (this.zzfa != null) {
            zzr += zzbfa.zzd(62, this.zzfa.longValue());
        }
        if (this.zzfb != null) {
            zzr += zzbfa.zzd(63, this.zzfb.longValue());
        }
        if (this.zzfd != null) {
            zzr += zzbfa.zzd(64, this.zzfd.longValue());
        }
        if (this.zzfe != null) {
            zzr += zzbfa.zzq(65, this.zzfe.intValue());
        }
        if (this.zzff != null) {
            zzr += zzbfa.zzq(66, this.zzff.intValue());
        }
        if (this.zzfc != null) {
            zzr += zzbfa.zzg(67, this.zzfc);
        }
        if (this.zzfg != null) {
            zzr += zzbfa.zzq(68, this.zzfg.intValue());
        }
        return this.zzfn != null ? zzr + zzbfa.zzb(201, this.zzfn) : zzr;
    }
}
