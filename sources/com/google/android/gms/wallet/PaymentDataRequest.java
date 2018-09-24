package com.google.android.gms.wallet;

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
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "PaymentDataRequestCreator")
public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Creator<PaymentDataRequest> CREATOR = new zzaf();
    @Field(id = 6)
    ArrayList<Integer> zzbx;
    @Field(id = 10)
    String zzbz;
    @Field(id = 2)
    boolean zzde;
    @Field(id = 4)
    boolean zzdf;
    @Field(id = 7)
    PaymentMethodTokenizationParameters zzdp;
    @Field(id = 1)
    boolean zzdx;
    @Field(id = 3)
    CardRequirements zzdy;
    @Field(id = 5)
    ShippingAddressRequirements zzdz;
    @Field(id = 8)
    TransactionInfo zzea;
    @Field(defaultValue = "true", id = 9)
    boolean zzeb;

    public final class Builder {
        final /* synthetic */ PaymentDataRequest zzec;

        private Builder(PaymentDataRequest paymentDataRequest) {
            this.zzec = paymentDataRequest;
        }

        public final Builder setEmailRequired(boolean z) {
            this.zzec.zzdx = z;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            this.zzec.zzde = z;
            return this;
        }

        public final Builder setCardRequirements(@NonNull CardRequirements cardRequirements) {
            this.zzec.zzdy = cardRequirements;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            this.zzec.zzdf = z;
            return this;
        }

        public final Builder setShippingAddressRequirements(@NonNull ShippingAddressRequirements shippingAddressRequirements) {
            this.zzec.zzdz = shippingAddressRequirements;
            return this;
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (this.zzec.zzbx == null) {
                this.zzec.zzbx = new ArrayList();
            }
            this.zzec.zzbx.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(@NonNull Collection<Integer> collection) {
            boolean z = (collection == null || collection.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "allowedPaymentMethods can't be null or empty!");
            if (this.zzec.zzbx == null) {
                this.zzec.zzbx = new ArrayList();
            }
            this.zzec.zzbx.addAll(collection);
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            this.zzec.zzdp = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setTransactionInfo(@NonNull TransactionInfo transactionInfo) {
            this.zzec.zzea = transactionInfo;
            return this;
        }

        public final Builder setUiRequired(boolean z) {
            this.zzec.zzeb = z;
            return this;
        }

        public final PaymentDataRequest build() {
            if (this.zzec.zzbz == null) {
                Preconditions.checkNotNull(this.zzec.zzbx, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
                Preconditions.checkNotNull(this.zzec.zzdy, "Card requirements must be set!");
                if (this.zzec.zzdp != null) {
                    Preconditions.checkNotNull(this.zzec.zzea, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
                }
            }
            return this.zzec;
        }
    }

    @Constructor
    PaymentDataRequest(@Param(id = 1) boolean z, @Param(id = 2) boolean z2, @Param(id = 3) CardRequirements cardRequirements, @Param(id = 4) boolean z3, @Param(id = 5) ShippingAddressRequirements shippingAddressRequirements, @Param(id = 6) ArrayList<Integer> arrayList, @Param(id = 7) PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, @Param(id = 8) TransactionInfo transactionInfo, @Param(id = 9) boolean z4, @Param(id = 10) String str) {
        this.zzdx = z;
        this.zzde = z2;
        this.zzdy = cardRequirements;
        this.zzdf = z3;
        this.zzdz = shippingAddressRequirements;
        this.zzbx = arrayList;
        this.zzdp = paymentMethodTokenizationParameters;
        this.zzea = transactionInfo;
        this.zzeb = z4;
        this.zzbz = str;
    }

    private PaymentDataRequest() {
        this.zzeb = true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzdx);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzde);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdy, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzdf);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdz, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbx, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzea, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzeb);
        SafeParcelWriter.writeString(parcel, 10, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean isEmailRequired() {
        return this.zzdx;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzde;
    }

    @Nullable
    public final CardRequirements getCardRequirements() {
        return this.zzdy;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzdf;
    }

    @Nullable
    public final ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zzdz;
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzbx;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdp;
    }

    public final TransactionInfo getTransactionInfo() {
        return this.zzea;
    }

    public final boolean isUiRequired() {
        return this.zzeb;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static PaymentDataRequest fromJson(String str) {
        Builder newBuilder = newBuilder();
        newBuilder.zzec.zzbz = (String) Preconditions.checkNotNull(str, "paymentDataRequestJson cannot be null!");
        return newBuilder.build();
    }

    public final String toJson() {
        return this.zzbz;
    }
}
