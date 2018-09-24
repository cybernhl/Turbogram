package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends zzah {
    private final TaskCompletionSource<Boolean> zzgl;

    zzai(TaskCompletionSource<Boolean> taskCompletionSource) {
        this.zzgl = taskCompletionSource;
    }

    public final void zza(int i, boolean z, Bundle bundle) {
        TaskUtil.setResultOrApiException(new Status(i), Boolean.valueOf(z), this.zzgl);
    }

    public final void zza(Status status, boolean z, Bundle bundle) {
        TaskUtil.setResultOrApiException(status, Boolean.valueOf(z), this.zzgl);
    }
}
