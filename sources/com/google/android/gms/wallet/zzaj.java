package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzaf;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaj extends TaskApiCall<zzaf, Boolean> {
    private final /* synthetic */ IsReadyToPayRequest zzeh;

    zzaj(PaymentsClient paymentsClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzeh = isReadyToPayRequest;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzaf) anyClient).zza(this.zzeh, taskCompletionSource);
    }
}
