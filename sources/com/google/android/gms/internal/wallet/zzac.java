package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet.zzb;

final class zzac extends zzb {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ String zzgh;
    private final /* synthetic */ String zzgi;

    zzac(zzy zzy, GoogleApiClient googleApiClient, String str, String str2, int i) {
        this.zzgh = str;
        this.zzgi = str2;
        this.val$requestCode = i;
        super(googleApiClient);
    }

    protected final void zza(zzaf zzaf) {
        zzaf.zza(this.zzgh, this.zzgi, this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zza((zzaf) anyClient);
    }
}
