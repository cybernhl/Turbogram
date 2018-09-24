package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "CustomVariableCreator")
@Reserved({1})
public final class zzz extends AbstractSafeParcelable {
    public static final Creator<zzz> CREATOR = new zzaa();
    @Field(getter = "getName", id = 2)
    private final String mName;
    @Field(getter = "getValue", id = 3)
    private final String mValue;

    @Constructor
    public zzz(@Param(id = 2) String str, @Param(id = 3) String str2) {
        this.mName = str;
        this.mValue = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.mName, false);
        SafeParcelWriter.writeString(parcel, 3, this.mValue, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
