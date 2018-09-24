package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ProxyCardCreator")
@Reserved({1})
@Deprecated
public final class ProxyCard extends AbstractSafeParcelable {
    public static final Creator<ProxyCard> CREATOR = new zzal();
    @Field(id = 2)
    private String zzej;
    @Field(id = 3)
    private String zzek;
    @Field(id = 4)
    private int zzel;
    @Field(id = 5)
    private int zzem;

    @Constructor
    public ProxyCard(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) int i, @Param(id = 5) int i2) {
        this.zzej = str;
        this.zzek = str2;
        this.zzel = i;
        this.zzem = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzej, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzek, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzel);
        SafeParcelWriter.writeInt(parcel, 5, this.zzem);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String getPan() {
        return this.zzej;
    }

    public final String getCvn() {
        return this.zzek;
    }

    public final int getExpirationMonth() {
        return this.zzel;
    }

    public final int getExpirationYear() {
        return this.zzem;
    }
}
