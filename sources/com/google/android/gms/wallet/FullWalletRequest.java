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

@Class(creator = "FullWalletRequestCreator")
@Reserved({1})
public final class FullWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<FullWalletRequest> CREATOR = new zzm();
    @Field(id = 2)
    String zzax;
    @Field(id = 3)
    String zzay;
    @Field(id = 4)
    Cart zzbi;

    public final class Builder {
        private final /* synthetic */ FullWalletRequest zzbj;

        private Builder(FullWalletRequest fullWalletRequest) {
            this.zzbj = fullWalletRequest;
        }

        public final Builder setGoogleTransactionId(String str) {
            this.zzbj.zzax = str;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            this.zzbj.zzay = str;
            return this;
        }

        public final Builder setCart(Cart cart) {
            this.zzbj.zzbi = cart;
            return this;
        }

        public final FullWalletRequest build() {
            return this.zzbj;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzax, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzay, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    FullWalletRequest(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) Cart cart) {
        this.zzax = str;
        this.zzay = str2;
        this.zzbi = cart;
    }

    FullWalletRequest() {
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final Cart getCart() {
        return this.zzbi;
    }
}
