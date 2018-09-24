package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zzuf implements Comparator<zzud> {
    zzuf() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzud zzud = (zzud) obj;
        zzud zzud2 = (zzud) obj2;
        zzuj zzuj = (zzuj) zzud.iterator();
        zzuj zzuj2 = (zzuj) zzud2.iterator();
        while (zzuj.hasNext() && zzuj2.hasNext()) {
            int compare = Integer.compare(zzud.zza(zzuj.nextByte()), zzud.zza(zzuj2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzud.size(), zzud2.size());
    }
}
