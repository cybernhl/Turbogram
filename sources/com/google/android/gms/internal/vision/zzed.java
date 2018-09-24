package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzed<T> implements zzen<T> {
    private final zzdx zzni;
    private final boolean zznj;
    private final zzff<?, ?> zzns;
    private final zzcg<?> zznt;

    private zzed(zzff<?, ?> zzff, zzcg<?> zzcg, zzdx zzdx) {
        this.zzns = zzff;
        this.zznj = zzcg.zze(zzdx);
        this.zznt = zzcg;
        this.zzni = zzdx;
    }

    static <T> zzed<T> zza(zzff<?, ?> zzff, zzcg<?> zzcg, zzdx zzdx) {
        return new zzed(zzff, zzcg, zzdx);
    }

    public final boolean equals(T t, T t2) {
        return !this.zzns.zzr(t).equals(this.zzns.zzr(t2)) ? false : this.zznj ? this.zznt.zzb(t).equals(this.zznt.zzb(t2)) : true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzns.zzr(t).hashCode();
        return this.zznj ? (hashCode * 53) + this.zznt.zzb(t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzni.zzbv().zzbz();
    }

    public final void zza(T t, zzfz zzfz) throws IOException {
        Iterator it = this.zznt.zzb(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzcl zzcl = (zzcl) entry.getKey();
            if (zzcl.zzbp() != zzfy.MESSAGE || zzcl.zzbq() || zzcl.zzbr()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzdc) {
                zzfz.zza(zzcl.zzbn(), ((zzdc) entry).zzcj().zzak());
            } else {
                zzfz.zza(zzcl.zzbn(), entry.getValue());
            }
        }
        zzff zzff = this.zzns;
        zzff.zzc(zzff.zzr(t), zzfz);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.vision.zzbl r13) throws java.io.IOException {
        /*
        r8 = this;
        r7 = 2;
        r0 = r9;
        r0 = (com.google.android.gms.internal.vision.zzcr) r0;
        r4 = r0.zzkr;
        r0 = com.google.android.gms.internal.vision.zzfg.zzdu();
        if (r4 != r0) goto L_0x0014;
    L_0x000c:
        r4 = com.google.android.gms.internal.vision.zzfg.zzdv();
        r9 = (com.google.android.gms.internal.vision.zzcr) r9;
        r9.zzkr = r4;
    L_0x0014:
        if (r11 >= r12) goto L_0x006f;
    L_0x0016:
        r2 = com.google.android.gms.internal.vision.zzbk.zza(r10, r11, r13);
        r0 = r13.zzgo;
        r1 = 11;
        if (r0 == r1) goto L_0x0031;
    L_0x0020:
        r1 = r0 & 7;
        if (r1 != r7) goto L_0x002c;
    L_0x0024:
        r1 = r10;
        r3 = r12;
        r5 = r13;
        r11 = com.google.android.gms.internal.vision.zzbk.zza(r0, r1, r2, r3, r4, r5);
        goto L_0x0014;
    L_0x002c:
        r11 = com.google.android.gms.internal.vision.zzbk.zza(r0, r10, r2, r12, r13);
        goto L_0x0014;
    L_0x0031:
        r5 = 0;
        r0 = 0;
        r3 = r0;
        r1 = r2;
    L_0x0035:
        if (r1 >= r12) goto L_0x0063;
    L_0x0037:
        r0 = com.google.android.gms.internal.vision.zzbk.zza(r10, r1, r13);
        r1 = r13.zzgo;
        r2 = r1 >>> 3;
        r6 = r1 & 7;
        switch(r2) {
            case 2: goto L_0x004d;
            case 3: goto L_0x0057;
            default: goto L_0x0044;
        };
    L_0x0044:
        r2 = 12;
        if (r1 == r2) goto L_0x0064;
    L_0x0048:
        r1 = com.google.android.gms.internal.vision.zzbk.zza(r1, r10, r0, r12, r13);
        goto L_0x0035;
    L_0x004d:
        if (r6 != 0) goto L_0x0044;
    L_0x004f:
        r1 = com.google.android.gms.internal.vision.zzbk.zza(r10, r0, r13);
        r0 = r13.zzgo;
        r5 = r0;
        goto L_0x0035;
    L_0x0057:
        if (r6 != r7) goto L_0x0044;
    L_0x0059:
        r1 = com.google.android.gms.internal.vision.zzbk.zze(r10, r0, r13);
        r0 = r13.zzgq;
        r0 = (com.google.android.gms.internal.vision.zzbo) r0;
        r3 = r0;
        goto L_0x0035;
    L_0x0063:
        r0 = r1;
    L_0x0064:
        if (r3 == 0) goto L_0x006d;
    L_0x0066:
        r1 = r5 << 3;
        r1 = r1 | 2;
        r4.zzb(r1, r3);
    L_0x006d:
        r11 = r0;
        goto L_0x0014;
    L_0x006f:
        if (r11 == r12) goto L_0x0076;
    L_0x0071:
        r0 = com.google.android.gms.internal.vision.zzcx.zzcf();
        throw r0;
    L_0x0076:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzed.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzbl):void");
    }

    public final void zzc(T t, T t2) {
        zzep.zza(this.zzns, (Object) t, (Object) t2);
        if (this.zznj) {
            zzep.zza(this.zznt, (Object) t, (Object) t2);
        }
    }

    public final void zzd(T t) {
        this.zzns.zzd(t);
        this.zznt.zzd(t);
    }

    public final int zzn(T t) {
        zzff zzff = this.zzns;
        int zzs = zzff.zzs(zzff.zzr(t)) + 0;
        return this.zznj ? zzs + this.zznt.zzb(t).zzbm() : zzs;
    }

    public final boolean zzp(T t) {
        return this.zznt.zzb(t).isInitialized();
    }
}
