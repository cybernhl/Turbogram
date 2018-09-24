package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "MaskedWalletRequestCreator")
@Reserved({1})
public final class MaskedWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MaskedWalletRequest> CREATOR = new zzz();
    @Field(id = 17)
    ArrayList<Integer> zzaj;
    @Field(id = 7)
    String zzap;
    @Field(id = 2)
    String zzay;
    @Field(id = 9)
    Cart zzbi;
    @Field(id = 3)
    boolean zzde;
    @Field(id = 4)
    boolean zzdf;
    @Field(id = 5)
    boolean zzdg;
    @Field(id = 6)
    String zzdh;
    @Field(id = 8)
    String zzdi;
    @Field(id = 10)
    private boolean zzdj;
    @Field(id = 11)
    boolean zzdk;
    @Field(id = 12)
    private CountrySpecification[] zzdl;
    @Field(defaultValue = "true", id = 13)
    boolean zzdm;
    @Field(defaultValue = "true", id = 14)
    boolean zzdn;
    @Field(id = 15)
    ArrayList<CountrySpecification> zzdo;
    @Field(id = 16)
    PaymentMethodTokenizationParameters zzdp;
    @Field(id = 18)
    String zzi;

    public final class Builder {
        private final /* synthetic */ MaskedWalletRequest zzdq;

        private Builder(MaskedWalletRequest maskedWalletRequest) {
            this.zzdq = maskedWalletRequest;
        }

        public final Builder setMerchantTransactionId(String str) {
            this.zzdq.zzay = str;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            this.zzdq.zzde = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            this.zzdq.zzdf = z;
            return this;
        }

        @Deprecated
        public final Builder setUseMinimalBillingAddress(boolean z) {
            this.zzdq.zzdg = z;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String str) {
            this.zzdq.zzdh = str;
            return this;
        }

        public final Builder setCurrencyCode(String str) {
            this.zzdq.zzap = str;
            return this;
        }

        public final Builder setMerchantName(String str) {
            this.zzdq.zzdi = str;
            return this;
        }

        public final Builder setCart(Cart cart) {
            this.zzdq.zzbi = cart;
            return this;
        }

        @Deprecated
        public final Builder setIsBillingAgreement(boolean z) {
            this.zzdq.zzdk = z;
            return this;
        }

        public final Builder setAllowPrepaidCard(boolean z) {
            this.zzdq.zzdm = z;
            return this;
        }

        public final Builder setAllowDebitCard(boolean z) {
            this.zzdq.zzdn = z;
            return this;
        }

        public final Builder addAllowedCountrySpecificationForShipping(CountrySpecification countrySpecification) {
            if (this.zzdq.zzdo == null) {
                this.zzdq.zzdo = new ArrayList();
            }
            this.zzdq.zzdo.add(countrySpecification);
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> collection) {
            if (collection != null) {
                if (this.zzdq.zzdo == null) {
                    this.zzdq.zzdo = new ArrayList();
                }
                this.zzdq.zzdo.addAll(collection);
            }
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            this.zzdq.zzdp = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (this.zzdq.zzaj == null) {
                this.zzdq.zzaj = new ArrayList();
            }
            this.zzdq.zzaj.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            if (collection != null) {
                if (this.zzdq.zzaj == null) {
                    this.zzdq.zzaj = new ArrayList();
                }
                this.zzdq.zzaj.addAll(collection);
            }
            return this;
        }

        public final Builder setCountryCode(String str) {
            this.zzdq.zzi = str;
            return this;
        }

        public final MaskedWalletRequest build() {
            return this.zzdq;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzay, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzde);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzdf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdg);
        SafeParcelWriter.writeString(parcel, 6, this.zzdh, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzap, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdi, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzbi, i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzdj);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzdk);
        SafeParcelWriter.writeTypedArray(parcel, 12, this.zzdl, i, false);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzdm);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzdn);
        SafeParcelWriter.writeTypedList(parcel, 15, this.zzdo, false);
        SafeParcelWriter.writeParcelable(parcel, 16, this.zzdp, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 17, this.zzaj, false);
        SafeParcelWriter.writeString(parcel, 18, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    MaskedWalletRequest(@Param(id = 2) String str, @Param(id = 3) boolean z, @Param(id = 4) boolean z2, @Param(id = 5) boolean z3, @Param(id = 6) String str2, @Param(id = 7) String str3, @Param(id = 8) String str4, @Param(id = 9) Cart cart, @Param(id = 10) boolean z4, @Param(id = 11) boolean z5, @Param(id = 12) CountrySpecification[] countrySpecificationArr, @Param(id = 13) boolean z6, @Param(id = 14) boolean z7, @Param(id = 15) ArrayList<CountrySpecification> arrayList, @Param(id = 16) PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, @Param(id = 17) ArrayList<Integer> arrayList2, @Param(id = 18) String str5) {
        this.zzay = str;
        this.zzde = z;
        this.zzdf = z2;
        this.zzdg = z3;
        this.zzdh = str2;
        this.zzap = str3;
        this.zzdi = str4;
        this.zzbi = cart;
        this.zzdj = z4;
        this.zzdk = z5;
        this.zzdl = countrySpecificationArr;
        this.zzdm = z6;
        this.zzdn = z7;
        this.zzdo = arrayList;
        this.zzdp = paymentMethodTokenizationParameters;
        this.zzaj = arrayList2;
        this.zzi = str5;
    }

    MaskedWalletRequest() {
        this.zzdm = true;
        this.zzdn = true;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzde;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzdf;
    }

    @Deprecated
    public final boolean useMinimalBillingAddress() {
        return this.zzdg;
    }

    public final String getEstimatedTotalPrice() {
        return this.zzdh;
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final String getMerchantName() {
        return this.zzdi;
    }

    public final Cart getCart() {
        return this.zzbi;
    }

    @Deprecated
    public final boolean isBillingAgreement() {
        return this.zzdk;
    }

    public final CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzdl;
    }

    public final boolean allowPrepaidCard() {
        return this.zzdm;
    }

    public final boolean allowDebitCard() {
        return this.zzdn;
    }

    public final ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.zzdo;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdp;
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzaj;
    }

    public final String getCountryCode() {
        return this.zzi;
    }
}
