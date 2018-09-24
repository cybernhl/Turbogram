package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.media.ExifInterface;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;
import org.telegram.tgnet.TLRPC;

final class zzaz {
    @GuardedBy("itself")
    private final zzav zzag;
    @GuardedBy("this")
    private int zzdi = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdj = new ArrayMap();

    zzaz(zzav zzav) {
        this.zzag = zzav;
    }

    final synchronized Task<Void> zza(String str) {
        TaskCompletionSource taskCompletionSource;
        int i;
        synchronized (this.zzag) {
            Object zzaj = this.zzag.zzaj();
            this.zzag.zzf(new StringBuilder((String.valueOf(zzaj).length() + 1) + String.valueOf(str).length()).append(zzaj).append(",").append(str).toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        Map map = this.zzdj;
        if (TextUtils.isEmpty(zzaj)) {
            i = 0;
        } else {
            i = zzaj.split(",").length - 1;
        }
        map.put(Integer.valueOf(i + this.zzdi), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    final synchronized boolean zzap() {
        return zzaq() != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r4) {
        /*
        r3 = this;
    L_0x0000:
        monitor-enter(r3);
        r1 = r3.zzaq();	 Catch:{ all -> 0x001c }
        if (r1 != 0) goto L_0x0013;
    L_0x0007:
        r0 = "FirebaseInstanceId";
        r1 = "topic sync succeeded";
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x001c }
        r0 = 1;
        monitor-exit(r3);	 Catch:{ all -> 0x001c }
    L_0x0012:
        return r0;
    L_0x0013:
        monitor-exit(r3);	 Catch:{ all -> 0x001c }
        r0 = zza(r4, r1);
        if (r0 != 0) goto L_0x001f;
    L_0x001a:
        r0 = 0;
        goto L_0x0012;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001c }
        throw r0;
    L_0x001f:
        monitor-enter(r3);
        r0 = r3.zzdj;	 Catch:{ all -> 0x003f }
        r2 = r3.zzdi;	 Catch:{ all -> 0x003f }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x003f }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x003f }
        r0 = (com.google.android.gms.tasks.TaskCompletionSource) r0;	 Catch:{ all -> 0x003f }
        r3.zzk(r1);	 Catch:{ all -> 0x003f }
        r1 = r3.zzdi;	 Catch:{ all -> 0x003f }
        r1 = r1 + 1;
        r3.zzdi = r1;	 Catch:{ all -> 0x003f }
        monitor-exit(r3);	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0000;
    L_0x003a:
        r1 = 0;
        r0.setResult(r1);
        goto L_0x0000;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x003f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaz.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzaq() {
        synchronized (this.zzag) {
            Object zzaj = this.zzag.zzaj();
        }
        if (!TextUtils.isEmpty(zzaj)) {
            String[] split = zzaj.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        boolean z;
        synchronized (this.zzag) {
            String zzaj = this.zzag.zzaj();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzaj.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.zzag.zzf(zzaj.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length()));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String str2;
        String valueOf;
        String[] split = str.split("!");
        String str3;
        if (split.length != 2) {
            return true;
        }
        str3 = split[0];
        String str4 = split[1];
        int i = -1;
        try {
            switch (str3.hashCode()) {
                case 83:
                    if (str3.equals(ExifInterface.LATITUDE_SOUTH)) {
                        i = 0;
                        break;
                    }
                    break;
                case TLRPC.LAYER /*85*/:
                    if (str3.equals("U")) {
                        boolean z = true;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    firebaseInstanceId.zzb(str4);
                    if (!FirebaseInstanceId.zzk()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    firebaseInstanceId.zzc(str4);
                    if (!FirebaseInstanceId.zzk()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            str2 = "FirebaseInstanceId";
            str3 = "Topic sync failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
                valueOf = new String(str3);
            } else {
                valueOf = str3.concat(valueOf);
            }
            Log.e(str2, valueOf);
            return false;
        }
        str2 = "FirebaseInstanceId";
        str3 = "Topic sync failed: ";
        valueOf = String.valueOf(e.getMessage());
        if (valueOf.length() == 0) {
            valueOf = str3.concat(valueOf);
        } else {
            valueOf = new String(str3);
        }
        Log.e(str2, valueOf);
        return false;
    }
}
