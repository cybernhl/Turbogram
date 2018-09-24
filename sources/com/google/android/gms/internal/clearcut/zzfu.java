package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class zzfu<M extends zzfu<M>> extends zzfz {
    protected zzfw zzrj;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzeo();
    }

    public void zza(zzfs zzfs) throws IOException {
        if (this.zzrj != null) {
            for (int i = 0; i < this.zzrj.size(); i++) {
                this.zzrj.zzaq(i).zza(zzfs);
            }
        }
    }

    protected int zzen() {
        if (this.zzrj != null) {
            for (int i = 0; i < this.zzrj.size(); i++) {
                this.zzrj.zzaq(i).zzen();
            }
        }
        return 0;
    }

    public M zzeo() throws CloneNotSupportedException {
        zzfu zzfu = (zzfu) super.zzep();
        zzfy.zza(this, zzfu);
        return zzfu;
    }

    public /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzfu) clone();
    }
}
