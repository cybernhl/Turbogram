package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

final class zzg {
    private final zzbt zzadj;
    private long zzadt;
    private String zzafw;
    private String zzafx;
    private String zzafy;
    private String zzafz;
    private long zzaga;
    private long zzagb;
    private long zzagc;
    private long zzagd;
    private String zzage;
    private long zzagf;
    private boolean zzagg;
    private long zzagh;
    private boolean zzagi;
    private boolean zzagj;
    private String zzagk;
    private long zzagl;
    private long zzagm;
    private long zzagn;
    private long zzago;
    private long zzagp;
    private long zzagq;
    private String zzagr;
    private boolean zzags;
    private long zzagt;
    private long zzagu;
    private String zzts;
    private final String zztt;

    @WorkerThread
    zzg(zzbt zzbt, String str) {
        Preconditions.checkNotNull(zzbt);
        Preconditions.checkNotEmpty(str);
        this.zzadj = zzbt;
        this.zztt = str;
        this.zzadj.zzgn().zzaf();
    }

    @WorkerThread
    public final void zzgv() {
        this.zzadj.zzgn().zzaf();
        this.zzags = false;
    }

    @WorkerThread
    public final String zzal() {
        this.zzadj.zzgn().zzaf();
        return this.zztt;
    }

    @WorkerThread
    public final String getAppInstanceId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafw;
    }

    @WorkerThread
    public final void zzam(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzafw, str) ? 1 : 0) | this.zzags;
        this.zzafw = str;
    }

    @WorkerThread
    public final String getGmpAppId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafx;
    }

    @WorkerThread
    public final void zzan(String str) {
        this.zzadj.zzgn().zzaf();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzags = (!zzfk.zzu(this.zzafx, str) ? 1 : 0) | this.zzags;
        this.zzafx = str;
    }

    @WorkerThread
    public final String zzgw() {
        this.zzadj.zzgn().zzaf();
        return this.zzagk;
    }

    @WorkerThread
    public final void zzao(String str) {
        this.zzadj.zzgn().zzaf();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzags = (!zzfk.zzu(this.zzagk, str) ? 1 : 0) | this.zzags;
        this.zzagk = str;
    }

    @WorkerThread
    public final String zzgx() {
        this.zzadj.zzgn().zzaf();
        return this.zzafy;
    }

    @WorkerThread
    public final void zzap(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzafy, str) ? 1 : 0) | this.zzags;
        this.zzafy = str;
    }

    @WorkerThread
    public final String getFirebaseInstanceId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafz;
    }

    @WorkerThread
    public final void zzaq(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzafz, str) ? 1 : 0) | this.zzags;
        this.zzafz = str;
    }

    @WorkerThread
    public final long zzgy() {
        this.zzadj.zzgn().zzaf();
        return this.zzagb;
    }

    @WorkerThread
    public final void zzs(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagb != j ? 1 : 0) | this.zzags;
        this.zzagb = j;
    }

    @WorkerThread
    public final long zzgz() {
        this.zzadj.zzgn().zzaf();
        return this.zzagc;
    }

    @WorkerThread
    public final void zzt(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagc != j ? 1 : 0) | this.zzags;
        this.zzagc = j;
    }

    @WorkerThread
    public final String zzak() {
        this.zzadj.zzgn().zzaf();
        return this.zzts;
    }

    @WorkerThread
    public final void setAppVersion(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzts, str) ? 1 : 0) | this.zzags;
        this.zzts = str;
    }

    @WorkerThread
    public final long zzha() {
        this.zzadj.zzgn().zzaf();
        return this.zzagd;
    }

    @WorkerThread
    public final void zzu(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagd != j ? 1 : 0) | this.zzags;
        this.zzagd = j;
    }

    @WorkerThread
    public final String zzhb() {
        this.zzadj.zzgn().zzaf();
        return this.zzage;
    }

    @WorkerThread
    public final void zzar(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzage, str) ? 1 : 0) | this.zzags;
        this.zzage = str;
    }

    @WorkerThread
    public final long zzhc() {
        this.zzadj.zzgn().zzaf();
        return this.zzadt;
    }

    @WorkerThread
    public final void zzv(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzadt != j ? 1 : 0) | this.zzags;
        this.zzadt = j;
    }

    @WorkerThread
    public final long zzhd() {
        this.zzadj.zzgn().zzaf();
        return this.zzagf;
    }

    @WorkerThread
    public final void zzw(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagf != j ? 1 : 0) | this.zzags;
        this.zzagf = j;
    }

    @WorkerThread
    public final boolean isMeasurementEnabled() {
        this.zzadj.zzgn().zzaf();
        return this.zzagg;
    }

    @WorkerThread
    public final void setMeasurementEnabled(boolean z) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagg != z ? 1 : 0) | this.zzags;
        this.zzagg = z;
    }

    @WorkerThread
    public final void zzx(long j) {
        int i = 1;
        Preconditions.checkArgument(j >= 0);
        this.zzadj.zzgn().zzaf();
        boolean z = this.zzags;
        if (this.zzaga == j) {
            i = 0;
        }
        this.zzags = z | i;
        this.zzaga = j;
    }

    @WorkerThread
    public final long zzhe() {
        this.zzadj.zzgn().zzaf();
        return this.zzaga;
    }

    @WorkerThread
    public final long zzhf() {
        this.zzadj.zzgn().zzaf();
        return this.zzagt;
    }

    @WorkerThread
    public final void zzy(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagt != j ? 1 : 0) | this.zzags;
        this.zzagt = j;
    }

    @WorkerThread
    public final long zzhg() {
        this.zzadj.zzgn().zzaf();
        return this.zzagu;
    }

    @WorkerThread
    public final void zzz(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagu != j ? 1 : 0) | this.zzags;
        this.zzagu = j;
    }

    @WorkerThread
    public final void zzhh() {
        this.zzadj.zzgn().zzaf();
        long j = this.zzaga + 1;
        if (j > 2147483647L) {
            this.zzadj.zzgo().zzjg().zzg("Bundle index overflow. appId", zzap.zzbv(this.zztt));
            j = 0;
        }
        this.zzags = true;
        this.zzaga = j;
    }

    @WorkerThread
    public final long zzhi() {
        this.zzadj.zzgn().zzaf();
        return this.zzagl;
    }

    @WorkerThread
    public final void zzaa(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagl != j ? 1 : 0) | this.zzags;
        this.zzagl = j;
    }

    @WorkerThread
    public final long zzhj() {
        this.zzadj.zzgn().zzaf();
        return this.zzagm;
    }

    @WorkerThread
    public final void zzab(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagm != j ? 1 : 0) | this.zzags;
        this.zzagm = j;
    }

    @WorkerThread
    public final long zzhk() {
        this.zzadj.zzgn().zzaf();
        return this.zzagn;
    }

    @WorkerThread
    public final void zzac(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagn != j ? 1 : 0) | this.zzags;
        this.zzagn = j;
    }

    @WorkerThread
    public final long zzhl() {
        this.zzadj.zzgn().zzaf();
        return this.zzago;
    }

    @WorkerThread
    public final void zzad(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzago != j ? 1 : 0) | this.zzags;
        this.zzago = j;
    }

    @WorkerThread
    public final long zzhm() {
        this.zzadj.zzgn().zzaf();
        return this.zzagq;
    }

    @WorkerThread
    public final void zzae(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagq != j ? 1 : 0) | this.zzags;
        this.zzagq = j;
    }

    @WorkerThread
    public final long zzhn() {
        this.zzadj.zzgn().zzaf();
        return this.zzagp;
    }

    @WorkerThread
    public final void zzaf(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagp != j ? 1 : 0) | this.zzags;
        this.zzagp = j;
    }

    @WorkerThread
    public final String zzho() {
        this.zzadj.zzgn().zzaf();
        return this.zzagr;
    }

    @WorkerThread
    public final String zzhp() {
        this.zzadj.zzgn().zzaf();
        String str = this.zzagr;
        zzas(null);
        return str;
    }

    @WorkerThread
    public final void zzas(String str) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (!zzfk.zzu(this.zzagr, str) ? 1 : 0) | this.zzags;
        this.zzagr = str;
    }

    @WorkerThread
    public final long zzhq() {
        this.zzadj.zzgn().zzaf();
        return this.zzagh;
    }

    @WorkerThread
    public final void zzag(long j) {
        this.zzadj.zzgn().zzaf();
        this.zzags = (this.zzagh != j ? 1 : 0) | this.zzags;
        this.zzagh = j;
    }

    @WorkerThread
    public final boolean zzhr() {
        this.zzadj.zzgn().zzaf();
        return this.zzagi;
    }

    @WorkerThread
    public final void zze(boolean z) {
        this.zzadj.zzgn().zzaf();
        this.zzags = this.zzagi != z;
        this.zzagi = z;
    }

    @WorkerThread
    public final boolean zzhs() {
        this.zzadj.zzgn().zzaf();
        return this.zzagj;
    }

    @WorkerThread
    public final void zzf(boolean z) {
        this.zzadj.zzgn().zzaf();
        this.zzags = this.zzagj != z;
        this.zzagj = z;
    }
}
