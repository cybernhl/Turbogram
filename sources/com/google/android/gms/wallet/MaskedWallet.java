package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator = "MaskedWalletCreator")
@Reserved({1})
public final class MaskedWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MaskedWallet> CREATOR = new zzx();
    @Field(id = 2)
    String zzax;
    @Field(id = 3)
    String zzay;
    @Field(id = 5)
    String zzba;
    @Field(id = 6)
    private zza zzbb;
    @Field(id = 7)
    private zza zzbc;
    @Field(id = 4)
    String[] zzbd;
    @Field(id = 10)
    UserAddress zzbe;
    @Field(id = 11)
    UserAddress zzbf;
    @Field(id = 12)
    InstrumentInfo[] zzbg;
    @Field(id = 8)
    private LoyaltyWalletObject[] zzdb;
    @Field(id = 9)
    private OfferWalletObject[] zzdc;

    public final class Builder {
        final /* synthetic */ MaskedWallet zzdd;

        private Builder(MaskedWallet maskedWallet) {
            this.zzdd = maskedWallet;
        }

        public final Builder setGoogleTransactionId(String str) {
            this.zzdd.zzax = str;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            this.zzdd.zzay = str;
            return this;
        }

        public final Builder setPaymentDescriptions(String[] strArr) {
            this.zzdd.zzbd = strArr;
            return this;
        }

        public final Builder setInstrumentInfos(InstrumentInfo[] instrumentInfoArr) {
            this.zzdd.zzbg = instrumentInfoArr;
            return this;
        }

        public final Builder setEmail(String str) {
            this.zzdd.zzba = str;
            return this;
        }

        public final Builder setBuyerBillingAddress(UserAddress userAddress) {
            this.zzdd.zzbe = userAddress;
            return this;
        }

        public final Builder setBuyerShippingAddress(UserAddress userAddress) {
            this.zzdd.zzbf = userAddress;
            return this;
        }

        public final MaskedWallet build() {
            return this.zzdd;
        }
    }

    public static Builder newBuilderFrom(MaskedWallet maskedWallet) {
        Preconditions.checkNotNull(maskedWallet);
        Builder email = new Builder().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail());
        email.zzdd.zzdb = maskedWallet.zzdb;
        email.zzdd.zzdc = maskedWallet.zzdc;
        return email.setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzax, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzay, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zzbd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzba, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzbb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbc, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzdb, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzdc, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzbe, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzbf, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzbg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    MaskedWallet(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String[] strArr, @Param(id = 5) String str3, @Param(id = 6) zza zza, @Param(id = 7) zza zza2, @Param(id = 8) LoyaltyWalletObject[] loyaltyWalletObjectArr, @Param(id = 9) OfferWalletObject[] offerWalletObjectArr, @Param(id = 10) UserAddress userAddress, @Param(id = 11) UserAddress userAddress2, @Param(id = 12) InstrumentInfo[] instrumentInfoArr) {
        this.zzax = str;
        this.zzay = str2;
        this.zzbd = strArr;
        this.zzba = str3;
        this.zzbb = zza;
        this.zzbc = zza2;
        this.zzdb = loyaltyWalletObjectArr;
        this.zzdc = offerWalletObjectArr;
        this.zzbe = userAddress;
        this.zzbf = userAddress2;
        this.zzbg = instrumentInfoArr;
    }

    private MaskedWallet() {
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzbd;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzbg;
    }

    public final String getEmail() {
        return this.zzba;
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzbe;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzbf;
    }
}
