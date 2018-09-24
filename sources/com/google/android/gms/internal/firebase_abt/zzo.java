package com.google.android.gms.internal.firebase_abt;

import android.support.v4.view.MotionEventCompat;
import com.google.ads.AdSize;
import java.io.IOException;
import org.telegram.messenger.voip.VoIPService;

public final class zzo extends zzd<zzo> {
    public String zzaq;
    public String zzar;
    public long zzas;
    public String zzat;
    public long zzau;
    public long zzav;
    private String zzaw;
    private String zzax;
    private String zzay;
    private String zzaz;
    private String zzba;
    public zzn[] zzbb;
    public int zzc;

    public zzo() {
        this.zzaq = "";
        this.zzar = "";
        this.zzas = 0;
        this.zzat = "";
        this.zzau = 0;
        this.zzav = 0;
        this.zzaw = "";
        this.zzax = "";
        this.zzay = "";
        this.zzaz = "";
        this.zzba = "";
        this.zzc = 0;
        this.zzbb = zzn.zzo();
        this.zzs = null;
        this.zzab = -1;
    }

    public final /* synthetic */ zzj zza(zza zza) throws IOException {
        while (true) {
            int zzd = zza.zzd();
            switch (zzd) {
                case 0:
                    break;
                case 10:
                    this.zzaq = zza.readString();
                    continue;
                case 18:
                    this.zzar = zza.readString();
                    continue;
                case 24:
                    this.zzas = zza.zze();
                    continue;
                case 34:
                    this.zzat = zza.readString();
                    continue;
                case MotionEventCompat.AXIS_GENERIC_9 /*40*/:
                    this.zzau = zza.zze();
                    continue;
                case 48:
                    this.zzav = zza.zze();
                    continue;
                case 58:
                    this.zzaw = zza.readString();
                    continue;
                case 66:
                    this.zzax = zza.readString();
                    continue;
                case VoIPService.CALL_MAX_LAYER /*74*/:
                    this.zzay = zza.readString();
                    continue;
                case 82:
                    this.zzaz = zza.readString();
                    continue;
                case AdSize.LARGE_AD_HEIGHT /*90*/:
                    this.zzba = zza.readString();
                    continue;
                case 96:
                    this.zzc = zza.zzf();
                    continue;
                case 106:
                    int zzb = zzm.zzb(zza, 106);
                    zzd = this.zzbb == null ? 0 : this.zzbb.length;
                    Object obj = new zzn[(zzb + zzd)];
                    if (zzd != 0) {
                        System.arraycopy(this.zzbb, 0, obj, 0, zzd);
                    }
                    while (zzd < obj.length - 1) {
                        obj[zzd] = new zzn();
                        zza.zza(obj[zzd]);
                        zza.zzd();
                        zzd++;
                    }
                    obj[zzd] = new zzn();
                    zza.zza(obj[zzd]);
                    this.zzbb = obj;
                    continue;
                default:
                    if (!super.zza(zza, zzd)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }
}
