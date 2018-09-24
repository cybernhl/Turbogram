package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator = "CardInfoCreator")
public final class CardInfo extends AbstractSafeParcelable {
    public static final Creator<CardInfo> CREATOR = new zzc();
    @Field(id = 1)
    private String zzae;
    @Field(id = 2)
    private String zzaf;
    @Field(id = 3)
    private String zzag;
    @Field(id = 4)
    private int zzah;
    @Field(id = 5)
    private UserAddress zzai;

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzae, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzaf, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzag, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzah);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzai, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    CardInfo(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) String str3, @Param(id = 4) int i, @Param(id = 5) UserAddress userAddress) {
        this.zzae = str;
        this.zzaf = str2;
        this.zzag = str3;
        this.zzah = i;
        this.zzai = userAddress;
    }

    private CardInfo() {
    }

    public final String getCardDescription() {
        return this.zzae;
    }

    public final String getCardNetwork() {
        return this.zzaf;
    }

    public final String getCardDetails() {
        return this.zzag;
    }

    public final int getCardClass() {
        switch (this.zzah) {
            case 1:
            case 2:
            case 3:
                return this.zzah;
            default:
                return 0;
        }
    }

    @Nullable
    public final UserAddress getBillingAddress() {
        return this.zzai;
    }
}
