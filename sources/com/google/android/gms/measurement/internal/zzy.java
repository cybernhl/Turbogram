package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzy {
    final String name;
    private final String origin;
    final long timestamp;
    final long zzaic;
    final zzaa zzaid;
    final String zztt;

    private zzy(zzbt zzbt, String str, String str2, String str3, long j, long j2, zzaa zzaa) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzaa);
        this.zztt = str2;
        this.name = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.origin = str;
        this.timestamp = j;
        this.zzaic = j2;
        if (this.zzaic != 0 && this.zzaic > this.timestamp) {
            zzbt.zzgo().zzjg().zze("Event created with reverse previous/current timestamps. appId, name", zzap.zzbv(str2), zzap.zzbv(str3));
        }
        this.zzaid = zzaa;
    }

    zzy(zzbt zzbt, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzaa zzaa;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zztt = str2;
        this.name = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.origin = str;
        this.timestamp = j;
        this.zzaic = j2;
        if (this.zzaic != 0 && this.zzaic > this.timestamp) {
            zzbt.zzgo().zzjg().zzg("Event created with reverse previous/current timestamps. appId", zzap.zzbv(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzaa = new zzaa(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    zzbt.zzgo().zzjd().zzbx("Param name can't be null");
                    it.remove();
                } else {
                    Object zzh = zzbt.zzgm().zzh(str4, bundle2.get(str4));
                    if (zzh == null) {
                        zzbt.zzgo().zzjg().zzg("Param value can't be null", zzbt.zzgl().zzbt(str4));
                        it.remove();
                    } else {
                        zzbt.zzgm().zza(bundle2, str4, zzh);
                    }
                }
            }
            zzaa = new zzaa(bundle2);
        }
        this.zzaid = zzaa;
    }

    final zzy zza(zzbt zzbt, long j) {
        return new zzy(zzbt, this.origin, this.zztt, this.name, this.timestamp, j, this.zzaid);
    }

    public final String toString() {
        String str = this.zztt;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzaid);
        return new StringBuilder(((String.valueOf(str).length() + 33) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("', name='").append(str2).append("', params=").append(valueOf).append('}').toString();
    }
}
