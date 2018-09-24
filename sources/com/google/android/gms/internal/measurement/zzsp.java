package com.google.android.gms.internal.measurement;

import android.util.Log;

final class zzsp extends zzsl<Long> {
    zzsp(zzsv zzsv, String str, Long l) {
        super(zzsv, str, l);
    }

    private final Long zzfk(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            String str2 = this.zzbrc;
            Log.e("PhenotypeFlag", new StringBuilder((String.valueOf(str2).length() + 25) + String.valueOf(str).length()).append("Invalid long value for ").append(str2).append(": ").append(str).toString());
            return null;
        }
    }

    protected final /* synthetic */ Object zzfj(String str) {
        return zzfk(str);
    }
}
