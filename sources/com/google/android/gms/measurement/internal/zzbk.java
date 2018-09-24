package com.google.android.gms.measurement.internal;

final class zzbk implements Runnable {
    private final /* synthetic */ zzbt zzaoj;
    private final /* synthetic */ zzap zzaok;

    zzbk(zzbj zzbj, zzbt zzbt, zzap zzap) {
        this.zzaoj = zzbt;
        this.zzaok = zzap;
    }

    public final void run() {
        if (this.zzaoj.zzkg() == null) {
            this.zzaok.zzjd().zzbx("Install Referrer Reporter is null");
            return;
        }
        zzbg zzkg = this.zzaoj.zzkg();
        zzkg.zzadj.zzgb();
        zzkg.zzcd(zzkg.zzadj.getContext().getPackageName());
    }
}
