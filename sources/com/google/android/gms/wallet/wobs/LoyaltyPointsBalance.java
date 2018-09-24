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

@Class(creator = "LoyaltyPointsBalanceCreator")
@Reserved({1})
public final class LoyaltyPointsBalance extends AbstractSafeParcelable {
    public static final Creator<LoyaltyPointsBalance> CREATOR = new zzh();
    @Field(id = 5)
    String zzbo;
    @Field(id = 2)
    int zzgv;
    @Field(id = 3)
    String zzgw;
    @Field(id = 4)
    double zzgx;
    @Field(id = 6)
    long zzgy;
    @Field(defaultValueUnchecked = "com.google.android.gms.wallet.wobs.LoyaltyPointsBalance.Type.UNDEFINED", id = 7)
    int zzgz;

    public final class Builder {
        private final /* synthetic */ LoyaltyPointsBalance zzha;

        private Builder(LoyaltyPointsBalance loyaltyPointsBalance) {
            this.zzha = loyaltyPointsBalance;
        }

        public final Builder setInt(int i) {
            this.zzha.zzgv = i;
            this.zzha.zzgz = 0;
            return this;
        }

        public final Builder setString(String str) {
            this.zzha.zzgw = str;
            this.zzha.zzgz = 1;
            return this;
        }

        public final Builder setDouble(double d) {
            this.zzha.zzgx = d;
            this.zzha.zzgz = 2;
            return this;
        }

        public final Builder setMoney(String str, long j) {
            this.zzha.zzbo = str;
            this.zzha.zzgy = j;
            this.zzha.zzgz = 3;
            return this;
        }

        public final LoyaltyPointsBalance build() {
            return this.zzha;
        }
    }

    public interface Type {
        public static final int DOUBLE = 2;
        public static final int INT = 0;
        public static final int MONEY = 3;
        public static final int STRING = 1;
        public static final int UNDEFINED = -1;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgv);
        SafeParcelWriter.writeString(parcel, 3, this.zzgw, false);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzgx);
        SafeParcelWriter.writeString(parcel, 5, this.zzbo, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzgy);
        SafeParcelWriter.writeInt(parcel, 7, this.zzgz);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    LoyaltyPointsBalance(@Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) double d, @Param(id = 5) String str2, @Param(id = 6) long j, @Param(id = 7) int i2) {
        this.zzgv = i;
        this.zzgw = str;
        this.zzgx = d;
        this.zzbo = str2;
        this.zzgy = j;
        this.zzgz = i2;
    }

    LoyaltyPointsBalance() {
        this.zzgz = -1;
        this.zzgv = -1;
        this.zzgx = -1.0d;
    }

    public final int getInt() {
        return this.zzgv;
    }

    public final String getString() {
        return this.zzgw;
    }

    public final double getDouble() {
        return this.zzgx;
    }

    public final String getCurrencyCode() {
        return this.zzbo;
    }

    public final long getCurrencyMicros() {
        return this.zzgy;
    }

    public final int getType() {
        return this.zzgz;
    }
}
