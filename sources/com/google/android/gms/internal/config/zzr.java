package com.google.android.gms.internal.config;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class zzr<R extends Result> extends ApiMethodImpl<R, zzw> {
    public zzr(GoogleApiClient googleApiClient) {
        super(zze.API, googleApiClient);
    }

    protected /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzw zzw = (zzw) anyClient;
        zza(zzw.getContext(), (zzah) zzw.getService());
    }

    protected abstract void zza(Context context, zzah zzah) throws RemoteException;
}
