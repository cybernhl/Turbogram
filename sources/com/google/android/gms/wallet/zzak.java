package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.wallet.zzaf;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzak extends TaskApiCall<zzaf, PaymentData> {
    private final /* synthetic */ PaymentDataRequest zzei;

    zzak(PaymentsClient paymentsClient, PaymentDataRequest paymentDataRequest) {
        this.zzei = paymentDataRequest;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzaf) anyClient).zza(this.zzei, taskCompletionSource);
    }
}
