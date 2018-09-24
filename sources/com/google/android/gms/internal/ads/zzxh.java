package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzxh implements zzww {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private final int zzbue;
    private boolean zzbuf = false;
    private final Map<zzanz<zzxe>, zzxb> zzbug = new HashMap();
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private final zzxn zzwh;

    public zzxh(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzbue = 2;
        this.zzbto = z3;
    }

    private final void zza(zzanz<zzxe> zzanz) {
        zzakk.zzcrm.post(new zzxj(this, zzanz));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzi(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r5) {
        /*
        r4 = this;
        r2 = r4.mLock;
        monitor-enter(r2);
        r0 = r4.zzbuf;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r1 = new com.google.android.gms.internal.ads.zzxe;	 Catch:{ all -> 0x003d }
        r0 = -1;
        r1.<init>(r0);	 Catch:{ all -> 0x003d }
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
    L_0x000e:
        return r1;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
        r2 = r5.iterator();
    L_0x0014:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0040;
    L_0x001a:
        r0 = r2.next();
        r0 = (com.google.android.gms.internal.ads.zzanz) r0;
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r3 = r4.zzbui;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        r3.add(r1);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r3 = r1.zzbtv;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        if (r3 != 0) goto L_0x0014;
    L_0x0031:
        r4.zza(r0);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004b }
        goto L_0x000e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzane.zzc(r1, r0);
        goto L_0x0014;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003d }
        throw r0;
    L_0x0040:
        r0 = 0;
        r4.zza(r0);
        r1 = new com.google.android.gms.internal.ads.zzxe;
        r0 = 1;
        r1.<init>(r0);
        goto L_0x000e;
    L_0x004b:
        r0 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzi(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzxe zzj(java.util.List<com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>> r15) {
        /*
        r14 = this;
        r5 = 0;
        r4 = -1;
        r12 = 0;
        r1 = r14.mLock;
        monitor-enter(r1);
        r0 = r14.zzbuf;	 Catch:{ all -> 0x0079 }
        if (r0 == 0) goto L_0x0013;
    L_0x000b:
        r2 = new com.google.android.gms.internal.ads.zzxe;	 Catch:{ all -> 0x0079 }
        r0 = -1;
        r2.<init>(r0);	 Catch:{ all -> 0x0079 }
        monitor-exit(r1);	 Catch:{ all -> 0x0079 }
    L_0x0012:
        return r2;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x0079 }
        r0 = r14.zzbtj;
        r0 = r0.zzbsy;
        r2 = -1;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x007c;
    L_0x001e:
        r0 = r14.zzbtj;
        r0 = r0.zzbsy;
    L_0x0022:
        r8 = r15.iterator();
        r6 = r0;
        r2 = r5;
        r3 = r5;
    L_0x0029:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b0;
    L_0x002f:
        r0 = r8.next();
        r0 = (com.google.android.gms.internal.ads.zzanz) r0;
        r1 = com.google.android.gms.ads.internal.zzbv.zzer();
        r10 = r1.currentTimeMillis();
        r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x007f;
    L_0x0041:
        r1 = r0.isDone();	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        if (r1 == 0) goto L_0x007f;
    L_0x0047:
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
    L_0x004d:
        r5 = r14.zzbui;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        r5.add(r1);	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        if (r1 == 0) goto L_0x00c3;
    L_0x0054:
        r5 = r1.zzbtv;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        if (r5 != 0) goto L_0x00c3;
    L_0x0058:
        r5 = r1.zzbua;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        if (r5 == 0) goto L_0x00c3;
    L_0x005c:
        r9 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        if (r9 <= r4) goto L_0x00c3;
    L_0x0062:
        r2 = r5.zzmm();	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        r4 = r2;
    L_0x0067:
        r2 = com.google.android.gms.ads.internal.zzbv.zzer();
        r2 = r2.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        r6 = java.lang.Math.max(r2, r12);
        r2 = r1;
        r3 = r0;
        goto L_0x0029;
    L_0x0079:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0079 }
        throw r0;
    L_0x007c:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        goto L_0x0022;
    L_0x007f:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        r1 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        r1 = (com.google.android.gms.internal.ads.zzxe) r1;	 Catch:{ InterruptedException -> 0x00bd, ExecutionException -> 0x00bf, RemoteException -> 0x0088, TimeoutException -> 0x00c1 }
        goto L_0x004d;
    L_0x0088:
        r0 = move-exception;
    L_0x0089:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzane.zzc(r1, r0);	 Catch:{ all -> 0x00a0 }
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();
        r0 = r0.currentTimeMillis();
        r0 = r0 - r10;
        r0 = r6 - r0;
        r0 = java.lang.Math.max(r0, r12);
        r6 = r0;
        goto L_0x0029;
    L_0x00a0:
        r0 = move-exception;
        r1 = com.google.android.gms.ads.internal.zzbv.zzer();
        r2 = r1.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        java.lang.Math.max(r2, r12);
        throw r0;
    L_0x00b0:
        r14.zza(r3);
        if (r2 != 0) goto L_0x0012;
    L_0x00b5:
        r2 = new com.google.android.gms.internal.ads.zzxe;
        r0 = 1;
        r2.<init>(r0);
        goto L_0x0012;
    L_0x00bd:
        r0 = move-exception;
        goto L_0x0089;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x0089;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x0089;
    L_0x00c3:
        r1 = r2;
        r0 = r3;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxh.zzj(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            for (zzxb cancel : this.zzbug.values()) {
                cancel.cancel();
            }
        }
    }

    public final zzxe zzh(List<zzwx> list) {
        zzane.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn zzjn = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = iArr[0];
                int i2 = iArr[1];
                for (zzjn zzjn2 : zzjn.zzard) {
                    if (i == zzjn2.width && i2 == zzjn2.height) {
                        break;
                    }
                }
            }
        }
        zzjn zzjn22 = zzjn;
        for (zzwx zzwx : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzwx.zzbrs);
            zzane.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String zzxb : zzwx.zzbrt) {
                zzxb zzxb2 = new zzxb(this.mContext, zzxb, this.zzwh, this.zzbtj, zzwx, this.zzbuc.zzccv, zzjn22, this.zzbuc.zzacr, this.zzael, this.zzbtn, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                zzanz zza = zzaki.zza(new zzxi(this, zzxb2));
                this.zzbug.put(zza, zzxb2);
                arrayList.add(zza);
            }
        }
        switch (this.zzbue) {
            case 2:
                return zzj(arrayList);
            default:
                return zzi(arrayList);
        }
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
