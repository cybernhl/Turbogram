package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr.zzd;

final class zzem implements zzdv {
    private final int flags;
    private final String info;
    private final Object[] zznf;
    private final zzdx zzni;

    zzem(zzdx zzdx, String str, Object[] objArr) {
        this.zzni = zzdx;
        this.info = str;
        this.zznf = objArr;
        int i = 1;
        char charAt = str.charAt(0);
        if (charAt < '?') {
            this.flags = charAt;
            return;
        }
        int i2 = charAt & 8191;
        int i3 = 13;
        while (true) {
            int i4 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 >= '?') {
                i2 |= (charAt2 & 8191) << i3;
                i3 += 13;
                i = i4;
            } else {
                this.flags = (charAt2 << i3) | i2;
                return;
            }
        }
    }

    public final int zzcv() {
        return (this.flags & 1) == 1 ? zzd.zzlg : zzd.zzlh;
    }

    public final boolean zzcw() {
        return (this.flags & 2) == 2;
    }

    public final zzdx zzcx() {
        return this.zzni;
    }

    final String zzde() {
        return this.info;
    }

    final Object[] zzdf() {
        return this.zznf;
    }
}
