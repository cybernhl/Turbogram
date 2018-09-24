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

@Class(creator = "TransactionInfoCreator")
public final class TransactionInfo extends AbstractSafeParcelable {
    public static final Creator<TransactionInfo> CREATOR = new zzap();
    @Field(id = 2)
    String zzao;
    @Field(id = 3)
    String zzap;
    @Field(id = 1)
    int zzep;

    public final class Builder {
        private final /* synthetic */ TransactionInfo zzeq;

        private Builder(TransactionInfo transactionInfo) {
            this.zzeq = transactionInfo;
        }

        public final Builder setTotalPriceStatus(int i) {
            this.zzeq.zzep = i;
            return this;
        }

        public final Builder setTotalPrice(@NonNull String str) {
            this.zzeq.zzao = str;
            return this;
        }

        public final Builder setCurrencyCode(@NonNull String str) {
            this.zzeq.zzap = str;
            return this;
        }

        public final TransactionInfo build() {
            Object obj = 1;
            Preconditions.checkNotEmpty(this.zzeq.zzap, "currencyCode must be set!");
            if (!(this.zzeq.zzep == 1 || this.zzeq.zzep == 2 || this.zzeq.zzep == 3)) {
                obj = null;
            }
            if (obj == null) {
                throw new IllegalArgumentException("totalPriceStatus must be set to one of WalletConstants.TotalPriceStatus!");
            }
            if (this.zzeq.zzep == 2) {
                Preconditions.checkNotEmpty(this.zzeq.zzao, "An estimated total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_ESTIMATED!");
            }
            if (this.zzeq.zzep == 3) {
                Preconditions.checkNotEmpty(this.zzeq.zzao, "An final total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_FINAL!");
            }
            return this.zzeq;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzep);
        SafeParcelWriter.writeString(parcel, 2, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzap, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    public TransactionInfo(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2) {
        this.zzep = i;
        this.zzao = str;
        this.zzap = str2;
    }

    private TransactionInfo() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final int getTotalPriceStatus() {
        return this.zzep;
    }

    @Nullable
    public final String getTotalPrice() {
        return this.zzao;
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }
}
