package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator = "CreateWalletObjectsRequestCreator")
@Reserved({1})
public final class CreateWalletObjectsRequest extends AbstractSafeParcelable {
    public static final Creator<CreateWalletObjectsRequest> CREATOR = new zzj();
    public static final int REQUEST_IMMEDIATE_SAVE = 1;
    public static final int SHOW_SAVE_PROMPT = 0;
    @Field(id = 2)
    LoyaltyWalletObject zzas;
    @Field(id = 3)
    OfferWalletObject zzat;
    @Field(id = 4)
    GiftCardWalletObject zzau;
    @Field(id = 5)
    int zzav;

    public final class Builder {
        private final /* synthetic */ CreateWalletObjectsRequest zzaw;

        private Builder(CreateWalletObjectsRequest createWalletObjectsRequest) {
            this.zzaw = createWalletObjectsRequest;
        }

        public final Builder setLoyaltyWalletObject(LoyaltyWalletObject loyaltyWalletObject) {
            this.zzaw.zzas = loyaltyWalletObject;
            return this;
        }

        public final Builder setOfferWalletObject(OfferWalletObject offerWalletObject) {
            this.zzaw.zzat = offerWalletObject;
            return this;
        }

        public final Builder setGiftCardWalletObject(GiftCardWalletObject giftCardWalletObject) {
            this.zzaw.zzau = giftCardWalletObject;
            return this;
        }

        public final Builder setCreateMode(int i) {
            this.zzaw.zzav = i;
            return this;
        }

        public final CreateWalletObjectsRequest build() {
            int i;
            int i2;
            boolean z = true;
            if (this.zzaw.zzau == null) {
                i = 0;
            } else {
                i = 1;
            }
            if (this.zzaw.zzas == null) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            i2 += i;
            if (this.zzaw.zzat == null) {
                i = 0;
            } else {
                i = 1;
            }
            if (i + i2 != 1) {
                z = false;
            }
            Preconditions.checkState(z, "CreateWalletObjectsRequest must have exactly one Wallet Object");
            return this.zzaw;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CreateMode {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzas, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzat, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzau, i, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzav);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    CreateWalletObjectsRequest(@Param(id = 2) LoyaltyWalletObject loyaltyWalletObject, @Param(id = 3) OfferWalletObject offerWalletObject, @Param(id = 4) GiftCardWalletObject giftCardWalletObject, @Param(id = 5) int i) {
        this.zzas = loyaltyWalletObject;
        this.zzat = offerWalletObject;
        this.zzau = giftCardWalletObject;
        this.zzav = i;
    }

    CreateWalletObjectsRequest() {
    }

    @Deprecated
    public CreateWalletObjectsRequest(LoyaltyWalletObject loyaltyWalletObject) {
        this.zzas = loyaltyWalletObject;
    }

    @Deprecated
    public CreateWalletObjectsRequest(OfferWalletObject offerWalletObject) {
        this.zzat = offerWalletObject;
    }

    @Deprecated
    public CreateWalletObjectsRequest(GiftCardWalletObject giftCardWalletObject) {
        this.zzau = giftCardWalletObject;
    }

    public final LoyaltyWalletObject getLoyaltyWalletObject() {
        return this.zzas;
    }

    public final OfferWalletObject getOfferWalletObject() {
        return this.zzat;
    }

    public final GiftCardWalletObject getGiftCardWalletObject() {
        return this.zzau;
    }

    public final int getCreateMode() {
        return this.zzav;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
