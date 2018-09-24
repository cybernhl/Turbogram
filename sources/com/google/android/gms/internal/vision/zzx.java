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

@Class(creator = "LineBoxParcelCreator")
@Reserved({1})
public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzy();
    @Field(id = 7)
    private final float zzco;
    @Field(id = 8)
    public final String zzdd;
    @Field(id = 2)
    public final zzag[] zzdi;
    @Field(id = 3)
    public final zzr zzdj;
    @Field(id = 4)
    private final zzr zzdk;
    @Field(id = 5)
    private final zzr zzdl;
    @Field(id = 6)
    public final String zzdm;
    @Field(id = 9)
    private final int zzdn;
    @Field(id = 10)
    public final boolean zzdo;
    @Field(id = 11)
    public final int zzdp;
    @Field(id = 12)
    public final int zzdq;

    @Constructor
    public zzx(@Param(id = 2) zzag[] zzagArr, @Param(id = 3) zzr zzr, @Param(id = 4) zzr zzr2, @Param(id = 5) zzr zzr3, @Param(id = 6) String str, @Param(id = 7) float f, @Param(id = 8) String str2, @Param(id = 9) int i, @Param(id = 10) boolean z, @Param(id = 11) int i2, @Param(id = 12) int i3) {
        this.zzdi = zzagArr;
        this.zzdj = zzr;
        this.zzdk = zzr2;
        this.zzdl = zzr3;
        this.zzdm = str;
        this.zzco = f;
        this.zzdd = str2;
        this.zzdn = i;
        this.zzdo = z;
        this.zzdp = i2;
        this.zzdq = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzdi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdl, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzdm, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzco);
        SafeParcelWriter.writeString(parcel, 8, this.zzdd, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzdn);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzdo);
        SafeParcelWriter.writeInt(parcel, 11, this.zzdp);
        SafeParcelWriter.writeInt(parcel, 12, this.zzdq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
