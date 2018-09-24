package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolvableVoidResult;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;

public final class zzaf extends GmsClient<zzs> {
    private final int environment;
    private final int theme;
    private final String zzcj;
    private final boolean zzet;
    private final Context zzgj;

    public zzaf(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, int i, int i2, boolean z) {
        super(context, looper, 4, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzgj = context;
        this.environment = i;
        this.zzcj = clientSettings.getAccountName();
        this.theme = i2;
        this.zzet = z;
    }

    public final boolean requiresAccount() {
        return true;
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    public final void zzb(int i) {
        Bundle zzd = zzd();
        Object zzag = new zzag((Activity) this.zzgj, i);
        try {
            ((zzs) getService()).zza(zzd, zzag);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", e);
            zzag.zza(8, false, Bundle.EMPTY);
        }
    }

    public final void zza(String str, String str2, int i) {
        Activity activity = (Activity) this.zzgj;
        Bundle zzd = zzd();
        Object zzag = new zzag(activity, i);
        try {
            ((zzs) getService()).zza(str, str2, zzd, zzag);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", e);
            zzag.zza(8, null, Bundle.EMPTY);
        }
    }

    public final void zza(MaskedWalletRequest maskedWalletRequest, int i) {
        Activity activity = (Activity) this.zzgj;
        Bundle zzd = zzd();
        zzw zzag = new zzag(activity, i);
        try {
            ((zzs) getService()).zza(maskedWalletRequest, zzd, zzag);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", e);
            zzag.zza(8, null, Bundle.EMPTY);
        }
    }

    public final void zza(FullWalletRequest fullWalletRequest, int i) {
        zzw zzag = new zzag((Activity) this.zzgj, i);
        try {
            ((zzs) getService()).zza(fullWalletRequest, zzd(), zzag);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", e);
            zzag.zza(8, null, Bundle.EMPTY);
        }
    }

    public final void zza(CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        zzw zzag = new zzag((Activity) this.zzgj, i);
        try {
            ((zzs) getService()).zza(createWalletObjectsRequest, zzd(), zzag);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzag.zza(8, Bundle.EMPTY);
        }
    }

    public final void zza(CreateWalletObjectsRequest createWalletObjectsRequest, TaskCompletionSource<AutoResolvableVoidResult> taskCompletionSource) {
        Bundle zzd = zzd();
        zzd.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzw zzaj = new zzaj(taskCompletionSource);
        try {
            ((zzs) getService()).zza(createWalletObjectsRequest, zzd, zzaj);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzaj.zza(8, Bundle.EMPTY);
        }
    }

    public final void zza(IsReadyToPayRequest isReadyToPayRequest, ResultHolder<BooleanResult> resultHolder) {
        zzw zzak = new zzak(resultHolder);
        try {
            ((zzs) getService()).zza(isReadyToPayRequest, zzd(), zzak);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzak.zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    public final void zza(IsReadyToPayRequest isReadyToPayRequest, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzw zzai = new zzai(taskCompletionSource);
        try {
            ((zzs) getService()).zza(isReadyToPayRequest, zzd(), zzai);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzai.zza(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    public final void zza(PaymentDataRequest paymentDataRequest, TaskCompletionSource<PaymentData> taskCompletionSource) {
        Bundle zzd = zzd();
        zzd.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzw zzal = new zzal(taskCompletionSource);
        try {
            ((zzs) getService()).zza(paymentDataRequest, zzd, zzal);
        } catch (Throwable e) {
            Log.e("WalletClientImpl", "RemoteException getting payment data", e);
            zzal.zza(Status.RESULT_INTERNAL_ERROR, null, Bundle.EMPTY);
        }
    }

    private final Bundle zzd() {
        int i = this.environment;
        String packageName = this.zzgj.getPackageName();
        Object obj = this.zzcj;
        int i2 = this.theme;
        boolean z = this.zzet;
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", i);
        bundle.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", z);
        bundle.putString("androidPackageName", packageName);
        if (!TextUtils.isEmpty(obj)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(obj, AccountType.GOOGLE));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", i2);
        return bundle;
    }

    public final int getMinApkVersion() {
        return 12600000;
    }

    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        if (queryLocalInterface instanceof zzs) {
            return (zzs) queryLocalInterface;
        }
        return new zzt(iBinder);
    }
}
