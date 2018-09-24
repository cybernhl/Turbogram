package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzak extends zzah {
    private final ResultHolder<BooleanResult> zzgm;

    public zzak(ResultHolder<BooleanResult> resultHolder) {
        this.zzgm = resultHolder;
    }

    public final void zza(Status status, boolean z, Bundle bundle) {
        this.zzgm.setResult(new BooleanResult(status, z));
    }
}
