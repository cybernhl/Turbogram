package com.google.android.gms.internal.measurement;

public class zzwa {
    private static final zzuz zzbtt = zzuz.zzvo();
    private zzud zzcad;
    private volatile zzwt zzcae;
    private volatile zzud zzcaf;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzwa)) {
            return false;
        }
        zzwa zzwa = (zzwa) obj;
        zzwt zzwt = this.zzcae;
        zzwt zzwt2 = zzwa.zzcae;
        if (zzwt == null && zzwt2 == null) {
            return zztt().equals(zzwa.zztt());
        }
        if (zzwt != null && zzwt2 != null) {
            return zzwt.equals(zzwt2);
        }
        if (zzwt != null) {
            return zzwt.equals(zzwa.zzh(zzwt.zzwf()));
        }
        return zzh(zzwt2.zzwf()).equals(zzwt2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzwt zzh(zzwt zzwt) {
        if (this.zzcae == null) {
            synchronized (this) {
                if (this.zzcae != null) {
                } else {
                    try {
                        this.zzcae = zzwt;
                        this.zzcaf = zzud.zzbtz;
                    } catch (zzvt e) {
                        this.zzcae = zzwt;
                        this.zzcaf = zzud.zzbtz;
                    }
                }
            }
        }
        return this.zzcae;
    }

    public final zzwt zzi(zzwt zzwt) {
        zzwt zzwt2 = this.zzcae;
        this.zzcad = null;
        this.zzcaf = null;
        this.zzcae = zzwt;
        return zzwt2;
    }

    public final int zzvu() {
        if (this.zzcaf != null) {
            return this.zzcaf.size();
        }
        if (this.zzcae != null) {
            return this.zzcae.zzvu();
        }
        return 0;
    }

    public final zzud zztt() {
        if (this.zzcaf != null) {
            return this.zzcaf;
        }
        synchronized (this) {
            if (this.zzcaf != null) {
                zzud zzud = this.zzcaf;
                return zzud;
            }
            if (this.zzcae == null) {
                this.zzcaf = zzud.zzbtz;
            } else {
                this.zzcaf = this.zzcae.zztt();
            }
            zzud = this.zzcaf;
            return zzud;
        }
    }
}
