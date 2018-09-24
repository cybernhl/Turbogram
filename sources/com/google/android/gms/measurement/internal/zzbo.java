package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbo extends zzcp {
    private static final AtomicLong zzape = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzaea;
    private zzbs zzaov;
    private zzbs zzaow;
    private final PriorityBlockingQueue<zzbr<?>> zzaox = new PriorityBlockingQueue();
    private final BlockingQueue<zzbr<?>> zzaoy = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzaoz = new zzbq(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzapa = new zzbq(this, "Thread death: Uncaught exception on network thread");
    private final Object zzapb = new Object();
    private final Semaphore zzapc = new Semaphore(2);
    private volatile boolean zzapd;

    zzbo(zzbt zzbt) {
        super(zzbt);
    }

    protected final boolean zzgt() {
        return false;
    }

    public final void zzaf() {
        if (Thread.currentThread() != this.zzaov) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final void zzgc() {
        if (Thread.currentThread() != this.zzaow) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final boolean zzkb() {
        return Thread.currentThread() == this.zzaov;
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(callable);
        zzbr zzbr = new zzbr(this, (Callable) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzaov) {
            if (!this.zzaox.isEmpty()) {
                zzgo().zzjg().zzbx("Callable skipped the worker queue.");
            }
            zzbr.run();
        } else {
            zza(zzbr);
        }
        return zzbr;
    }

    public final <V> Future<V> zzc(Callable<V> callable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(callable);
        zzbr zzbr = new zzbr(this, (Callable) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzaov) {
            zzbr.run();
        } else {
            zza(zzbr);
        }
        return zzbr;
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(runnable);
        zza(new zzbr(this, runnable, false, "Task exception on worker thread"));
    }

    final <T> T zza(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            zzgn().zzc(runnable);
            try {
                atomicReference.wait(15000);
            } catch (InterruptedException e) {
                zzar zzjg = zzgo().zzjg();
                String str2 = "Interrupted waiting for ";
                String valueOf = String.valueOf(str);
                zzjg.zzbx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            zzar zzjg2 = zzgo().zzjg();
            String str3 = "Timed out waiting for ";
            valueOf = String.valueOf(str);
            zzjg2.zzbx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        return t;
    }

    private final void zza(zzbr<?> zzbr) {
        synchronized (this.zzapb) {
            this.zzaox.add(zzbr);
            if (this.zzaov == null) {
                this.zzaov = new zzbs(this, "Measurement Worker", this.zzaox);
                this.zzaov.setUncaughtExceptionHandler(this.zzaoz);
                this.zzaov.start();
            } else {
                this.zzaov.zzke();
            }
        }
    }

    public final void zzd(Runnable runnable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(runnable);
        zzbr zzbr = new zzbr(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzapb) {
            this.zzaoy.add(zzbr);
            if (this.zzaow == null) {
                this.zzaow = new zzbs(this, "Measurement Network", this.zzaoy);
                this.zzaow.setUncaughtExceptionHandler(this.zzapa);
                this.zzaow.start();
            } else {
                this.zzaow.zzke();
            }
        }
    }

    public final ExecutorService zzkc() {
        ExecutorService executorService;
        synchronized (this.zzapb) {
            if (this.zzaea == null) {
                this.zzaea = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzaea;
        }
        return executorService;
    }

    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
