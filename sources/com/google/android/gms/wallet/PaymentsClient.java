package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet.WalletOptions;

public class PaymentsClient extends GoogleApi<WalletOptions> {
    PaymentsClient(@NonNull Activity activity, @NonNull WalletOptions walletOptions) {
        super(activity, Wallet.API, (ApiOptions) walletOptions, Settings.DEFAULT_SETTINGS);
    }

    PaymentsClient(@NonNull Context context, @NonNull WalletOptions walletOptions) {
        super(context, Wallet.API, (ApiOptions) walletOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<Boolean> isReadyToPay(@NonNull IsReadyToPayRequest isReadyToPayRequest) {
        return doRead(new zzaj(this, isReadyToPayRequest));
    }

    public Task<PaymentData> loadPaymentData(@NonNull PaymentDataRequest paymentDataRequest) {
        return doWrite(new zzak(this, paymentDataRequest));
    }
}
