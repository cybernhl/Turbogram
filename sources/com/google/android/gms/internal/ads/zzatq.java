package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.exoplayer2.DefaultLoadControl;

public final class zzatq {
    public static zzba zzl(Context context, String str, String str2) {
        return new zzatr(context, str, str2).zzak(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS);
    }
}
