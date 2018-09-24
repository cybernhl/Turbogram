package com.google.android.gms.internal.wallet;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolvableVoidResult;
import com.google.android.gms.wallet.AutoResolveHelper;

final class zzaj extends zzah {
    private final TaskCompletionSource<AutoResolvableVoidResult> zzgl;

    public zzaj(TaskCompletionSource<AutoResolvableVoidResult> taskCompletionSource) {
        this.zzgl = taskCompletionSource;
    }

    public final void zza(int i, Bundle bundle) {
        Status status;
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        if (pendingIntent == null || i != 6) {
            status = new Status(i);
        } else {
            status = new Status(i, "Need to resolve PendingIntent", pendingIntent);
        }
        AutoResolveHelper.zza(status, new AutoResolvableVoidResult(), this.zzgl);
    }
}
