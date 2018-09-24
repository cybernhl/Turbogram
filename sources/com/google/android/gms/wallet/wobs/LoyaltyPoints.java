package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "LoyaltyPointsCreator")
@Reserved({1, 4})
public final class LoyaltyPoints extends AbstractSafeParcelable {
    public static final Creator<LoyaltyPoints> CREATOR = new zzi();
    @Field(id = 2)
    String label;
    @Field(id = 5)
    @Deprecated
    TimeInterval zzcq;
    @Field(id = 3)
    LoyaltyPointsBalance zzgt;

    public final class Builder {
        private final /* synthetic */ LoyaltyPoints zzgu;

        private Builder(LoyaltyPoints loyaltyPoints) {
            this.zzgu = loyaltyPoints;
        }

        public final Builder setLabel(String str) {
            this.zzgu.label = str;
            return this;
        }

        public final Builder setBalance(LoyaltyPointsBalance loyaltyPointsBalance) {
            this.zzgu.zzgt = loyaltyPointsBalance;
            return this;
        }

        @Deprecated
        public final Builder setType(String str) {
            return this;
        }

        @Deprecated
        public final Builder setValidTimeInterval(TimeInterval timeInterval) {
            this.zzgu.zzcq = timeInterval;
            return this;
        }

        public final LoyaltyPoints build() {
            return this.zzgu;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.label, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzgt, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    LoyaltyPoints(@Param(id = 2) String str, @Param(id = 3) LoyaltyPointsBalance loyaltyPointsBalance, @Param(id = 5) TimeInterval timeInterval) {
        this.label = str;
        this.zzgt = loyaltyPointsBalance;
        this.zzcq = timeInterval;
    }

    LoyaltyPoints() {
    }

    public final String getLabel() {
        return this.label;
    }

    public final LoyaltyPointsBalance getBalance() {
        return this.zzgt;
    }

    @Deprecated
    public final String getType() {
        return null;
    }

    @Deprecated
    public final TimeInterval getValidTimeInterval() {
        return this.zzcq;
    }
}
