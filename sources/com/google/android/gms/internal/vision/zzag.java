package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "WordBoxParcelCreator")
@Reserved({1})
public final class zzag extends AbstractSafeParcelable {
    public static final Creator<zzag> CREATOR = new zzah();
    @Field(id = 6)
    private final float zzco;
    @Field(id = 7)
    public final String zzdd;
    @Field(id = 3)
    public final zzr zzdj;
    @Field(id = 4)
    private final zzr zzdk;
    @Field(id = 5)
    public final String zzdm;
    @Field(id = 2)
    private final zzab[] zzds;
    @Field(id = 8)
    private final boolean zzdt;

    @Constructor
    public zzag(@Param(id = 2) zzab[] zzabArr, @Param(id = 3) zzr zzr, @Param(id = 4) zzr zzr2, @Param(id = 5) String str, @Param(id = 6) float f, @Param(id = 7) String str2, @Param(id = 8) boolean z) {
        this.zzds = zzabArr;
        this.zzdj = zzr;
        this.zzdk = zzr2;
        this.zzdm = str;
        this.zzco = f;
        this.zzdd = str2;
        this.zzdt = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzds, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdk, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzdm, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzco);
        SafeParcelWriter.writeString(parcel, 7, this.zzdd, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdt);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
