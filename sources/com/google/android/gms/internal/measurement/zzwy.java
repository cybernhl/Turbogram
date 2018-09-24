package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzwy<T> implements zzxj<T> {
    private final zzwt zzcbd;
    private final boolean zzcbe;
    private final zzyb<?, ?> zzcbn;
    private final zzva<?> zzcbo;

    private zzwy(zzyb<?, ?> zzyb, zzva<?> zzva, zzwt zzwt) {
        this.zzcbn = zzyb;
        this.zzcbe = zzva.zze(zzwt);
        this.zzcbo = zzva;
        this.zzcbd = zzwt;
    }

    static <T> zzwy<T> zza(zzyb<?, ?> zzyb, zzva<?> zzva, zzwt zzwt) {
        return new zzwy(zzyb, zzva, zzwt);
    }

    public final T newInstance() {
        return this.zzcbd.zzwe().zzwi();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzcbn.zzah(t).equals(this.zzcbn.zzah(t2))) {
            return false;
        }
        if (this.zzcbe) {
            return this.zzcbo.zzs(t).equals(this.zzcbo.zzs(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzcbn.zzah(t).hashCode();
        if (this.zzcbe) {
            return (hashCode * 53) + this.zzcbo.zzs(t).hashCode();
        }
        return hashCode;
    }

    public final void zzd(T t, T t2) {
        zzxl.zza(this.zzcbn, (Object) t, (Object) t2);
        if (this.zzcbe) {
            zzxl.zza(this.zzcbo, (Object) t, (Object) t2);
        }
    }

    public final void zza(T t, zzyw zzyw) throws IOException {
        Iterator it = this.zzcbo.zzs(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzvf zzvf = (zzvf) entry.getKey();
            if (zzvf.zzvx() != zzyv.MESSAGE || zzvf.zzvy() || zzvf.zzvz()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzvy) {
                zzyw.zza(zzvf.zzc(), ((zzvy) entry).zzwu().zztt());
            } else {
                zzyw.zza(zzvf.zzc(), entry.getValue());
            }
        }
        zzyb zzyb = this.zzcbn;
        zzyb.zzc(zzyb.zzah(t), zzyw);
    }

    public final void zza(T t, zzxi zzxi, zzuz zzuz) throws IOException {
        zzyb zzyb = this.zzcbn;
        zzva zzva = this.zzcbo;
        Object zzai = zzyb.zzai(t);
        zzvd zzt = zzva.zzt(t);
        while (zzxi.zzve() != Integer.MAX_VALUE) {
            try {
                boolean zza;
                int tag = zzxi.getTag();
                Object zza2;
                if (tag != 11) {
                    if ((tag & 7) == 2) {
                        zza2 = zzva.zza(zzuz, this.zzcbd, tag >>> 3);
                        if (zza2 != null) {
                            zzva.zza(zzxi, zza2, zzuz, zzt);
                        } else {
                            zza = zzyb.zza(zzai, zzxi);
                            continue;
                        }
                    } else {
                        zza = zzxi.zzvf();
                        continue;
                    }
                    if (!zza) {
                        return;
                    }
                }
                int i = 0;
                zza2 = null;
                zzud zzud = null;
                while (zzxi.zzve() != Integer.MAX_VALUE) {
                    int tag2 = zzxi.getTag();
                    if (tag2 == 16) {
                        i = zzxi.zzup();
                        zza2 = zzva.zza(zzuz, this.zzcbd, i);
                    } else if (tag2 == 26) {
                        if (zza2 != null) {
                            zzva.zza(zzxi, zza2, zzuz, zzt);
                        } else {
                            zzud = zzxi.zzuo();
                        }
                    } else if (!zzxi.zzvf()) {
                        break;
                    }
                }
                if (zzxi.getTag() != 12) {
                    throw zzvt.zzwn();
                } else if (zzud != null) {
                    if (zza2 != null) {
                        zzva.zza(zzud, zza2, zzuz, zzt);
                    } else {
                        zzyb.zza(zzai, i, zzud);
                    }
                }
                zza = true;
                continue;
                if (zza) {
                    return;
                }
            } finally {
                zzyb.zzg(t, zzai);
            }
        }
        zzyb.zzg(t, zzai);
    }

    public final void zzu(T t) {
        this.zzcbn.zzu(t);
        this.zzcbo.zzu(t);
    }

    public final boolean zzaf(T t) {
        return this.zzcbo.zzs(t).isInitialized();
    }

    public final int zzae(T t) {
        zzyb zzyb = this.zzcbn;
        int zzaj = zzyb.zzaj(zzyb.zzah(t)) + 0;
        if (this.zzcbe) {
            return zzaj + this.zzcbo.zzs(t).zzvv();
        }
        return zzaj;
    }
}
