package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Map.Entry;

abstract class zzva<T extends zzvf<T>> {
    zzva() {
    }

    abstract Object zza(zzuz zzuz, zzwt zzwt, int i);

    abstract <UT, UB> UB zza(zzxi zzxi, Object obj, zzuz zzuz, zzvd<T> zzvd, UB ub, zzyb<UT, UB> zzyb) throws IOException;

    abstract void zza(zzud zzud, Object obj, zzuz zzuz, zzvd<T> zzvd) throws IOException;

    abstract void zza(zzxi zzxi, Object obj, zzuz zzuz, zzvd<T> zzvd) throws IOException;

    abstract void zza(zzyw zzyw, Entry<?, ?> entry) throws IOException;

    abstract void zza(Object obj, zzvd<T> zzvd);

    abstract int zzb(Entry<?, ?> entry);

    abstract boolean zze(zzwt zzwt);

    abstract zzvd<T> zzs(Object obj);

    abstract zzvd<T> zzt(Object obj);

    abstract void zzu(Object obj);
}
