package com.google.android.gms.internal.firebase_abt;

import java.io.IOException;

public final class zzn extends zzd<zzn> {
    private static volatile zzn[] zzap;
    public String zzaq;

    public zzn() {
        this.zzaq = "";
        this.zzs = null;
        this.zzab = -1;
    }

    public static zzn[] zzo() {
        if (zzap == null) {
            synchronized (zzh.zzaa) {
                if (zzap == null) {
                    zzap = new zzn[0];
                }
            }
        }
        return zzap;
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
