package com.google.android.gms.internal.measurement;

import java.util.Arrays;

final class zzzi {
    final int tag;
    final byte[] zzbug;

    zzzi(int i, byte[] bArr) {
        this.tag = i;
        this.zzbug = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzi)) {
            return false;
        }
        zzzi zzzi = (zzzi) obj;
        if (this.tag == zzzi.tag && Arrays.equals(this.zzbug, zzzi.zzbug)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbug);
    }
}
