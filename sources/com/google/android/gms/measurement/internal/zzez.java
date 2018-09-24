package com.google.android.gms.measurement.internal;

abstract class zzez extends zzey {
    private boolean zzvz;

    zzez(zzfa zzfa) {
        super(zzfa);
        this.zzamz.zzb(this);
    }

    protected abstract boolean zzgt();

    final boolean isInitialized() {
        return this.zzvz;
    }

    protected final void zzcl() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzq() {
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgt();
        this.zzamz.zzma();
        this.zzvz = true;
    }
}
