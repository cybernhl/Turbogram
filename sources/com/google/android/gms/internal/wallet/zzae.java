package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet.zza;

final class zzae extends zza<BooleanResult> {
    private final /* synthetic */ IsReadyToPayRequest zzeh;

    zzae(zzy zzy, GoogleApiClient googleApiClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzeh = isReadyToPayRequest;
        super(googleApiClient);
    }

    protected final void zza(zzaf zzaf) {
        zzaf.zza(this.zzeh, (ResultHolder) this);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzaf) anyClient);
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new BooleanResult(status, false);
    }
}
