package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsq extends zzsl<Integer> {
    zzsq(zzsv zzsv, String str, Integer num) {
        super(zzsv, str, num);
    }

    private final Integer zzfl(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            String str2 = this.zzbrc;
            Log.e("PhenotypeFlag", new StringBuilder((String.valueOf(str2).length() + 28) + String.valueOf(str).length()).append("Invalid integer value for ").append(str2).append(": ").append(str).toString());
            return null;
        }
    }

    protected final /* synthetic */ Object zzfj(String str) {
        return zzfl(str);
    }
}
