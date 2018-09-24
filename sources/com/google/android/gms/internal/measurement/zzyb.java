package com.google.android.gms.internal.measurement;

import java.io.IOException;

abstract class zzyb<T, B> {
    zzyb() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzud zzud);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzyw zzyw) throws IOException;

    abstract boolean zza(zzxi zzxi);

    abstract T zzab(B b);

    abstract int zzae(T t);

    abstract T zzah(Object obj);

    abstract B zzai(Object obj);

    abstract int zzaj(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzyw zzyw) throws IOException;

    abstract void zzf(Object obj, T t);

    abstract void zzg(Object obj, B b);

    abstract T zzh(T t, T t2);

    abstract void zzu(Object obj);

    abstract B zzye();

    final boolean zza(B b, zzxi zzxi) throws IOException {
        int tag = zzxi.getTag();
        int i = tag >>> 3;
        switch (tag & 7) {
            case 0:
                zza((Object) b, i, zzxi.zzui());
                return true;
            case 1:
                zzb(b, i, zzxi.zzuk());
                return true;
            case 2:
                zza((Object) b, i, zzxi.zzuo());
                return true;
            case 3:
                Object zzye = zzye();
                int i2 = (i << 3) | 4;
                while (zzxi.zzve() != Integer.MAX_VALUE) {
                    if (!zza(zzye, zzxi)) {
                        if (i2 == zzxi.getTag()) {
                            throw zzvt.zzwn();
                        }
                        zza((Object) b, i, zzab(zzye));
                        return true;
                    }
                }
                if (i2 == zzxi.getTag()) {
                    zza((Object) b, i, zzab(zzye));
                    return true;
                }
                throw zzvt.zzwn();
            case 4:
                return false;
            case 5:
                zzc(b, i, zzxi.zzul());
                return true;
            default:
                throw zzvt.zzwo();
        }
    }
}
