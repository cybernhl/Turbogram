package com.google.android.gms.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator = "PaymentDataCreator")
public final class PaymentData extends AbstractSafeParcelable implements AutoResolvableResult {
    public static final Creator<PaymentData> CREATOR = new zzad();
    @Field(id = 5)
    private String zzax;
    @Field(id = 1)
    private String zzba;
    @Field(id = 4)
    private PaymentMethodToken zzbh;
    @Field(id = 7)
    private String zzbz;
    @Field(id = 2)
    private CardInfo zzdt;
    @Field(id = 3)
    private UserAddress zzdu;
    @Field(id = 6)
    private Bundle zzdv;

    public final class zza {
        final /* synthetic */ PaymentData zzdw;

        private zza(PaymentData paymentData) {
            this.zzdw = paymentData;
        }
    }

    @Constructor
    PaymentData(@Param(id = 1) String str, @Param(id = 2) CardInfo cardInfo, @Param(id = 3) UserAddress userAddress, @Param(id = 4) PaymentMethodToken paymentMethodToken, @Param(id = 5) String str2, @Param(id = 6) Bundle bundle, @Param(id = 7) String str3) {
        this.zzba = str;
        this.zzdt = cardInfo;
        this.zzdu = userAddress;
        this.zzbh = paymentMethodToken;
        this.zzax = str2;
        this.zzdv = bundle;
        this.zzbz = str3;
    }

    private PaymentData() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzba, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdt, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdu, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbh, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzax, false);
        SafeParcelWriter.writeBundle(parcel, 6, this.zzdv, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String getEmail() {
        return this.zzba;
    }

    public final CardInfo getCardInfo() {
        return this.zzdt;
    }

    @Nullable
    public final UserAddress getShippingAddress() {
        return this.zzdu;
    }

    @Nullable
    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbh;
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    @Nullable
    public final Bundle getExtraData() {
        return this.zzdv;
    }

    public static PaymentData fromJson(String str) {
        zza zza = new zza();
        zza.zzdw.zzbz = (String) Preconditions.checkNotNull(str, "paymentDataJson cannot be null!");
        return zza.zzdw;
    }

    public final String toJson() {
        return this.zzbz;
    }

    @Nullable
    public static PaymentData getFromIntent(@NonNull Intent intent) {
        return (PaymentData) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.wallet.PaymentData", CREATOR);
    }

    public final void putIntoIntent(@NonNull Intent intent) {
        SafeParcelableSerializer.serializeToIntentExtra(this, intent, "com.google.android.gms.wallet.PaymentData");
    }
}
