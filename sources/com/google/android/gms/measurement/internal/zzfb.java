package com.google.android.gms.measurement.internal;

final class zzfb implements Runnable {
    private final /* synthetic */ zzff zzatx;
    private final /* synthetic */ zzfa zzaty;

    zzfb(zzfa zzfa, zzff zzff) {
        this.zzaty = zzfa;
        this.zzatx = zzff;
    }

    public final void run() {
        this.zzaty.zza(this.zzatx);
        this.zzaty.start();
    }
}
