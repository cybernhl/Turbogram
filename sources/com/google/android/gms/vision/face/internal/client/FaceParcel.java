package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@UsedByNative("wrapper.cc")
@Class(creator = "FaceParcelCreator")
public class FaceParcel extends AbstractSafeParcelable {
    public static final Creator<FaceParcel> CREATOR = new zzb();
    @Field(id = 3)
    public final float centerX;
    @Field(id = 4)
    public final float centerY;
    @Field(id = 6)
    public final float height;
    @Field(id = 2)
    public final int id;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 5)
    public final float width;
    @Field(id = 10)
    public final float zzbs;
    @Field(id = 11)
    public final float zzbt;
    @Field(id = 12)
    public final float zzbu;
    @Field(id = 7)
    public final float zzcf;
    @Field(id = 8)
    public final float zzcg;
    @Field(id = 9)
    public final LandmarkParcel[] zzch;

    @Constructor
    public FaceParcel(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) float f, @Param(id = 4) float f2, @Param(id = 5) float f3, @Param(id = 6) float f4, @Param(id = 7) float f5, @Param(id = 8) float f6, @Param(id = 9) LandmarkParcel[] landmarkParcelArr, @Param(id = 10) float f7, @Param(id = 11) float f8, @Param(id = 12) float f9) {
        this.versionCode = i;
        this.id = i2;
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.zzcf = f5;
        this.zzcg = f6;
        this.zzch = landmarkParcelArr;
        this.zzbs = f7;
        this.zzbt = f8;
        this.zzbu = f9;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.id);
        SafeParcelWriter.writeFloat(parcel, 3, this.centerX);
        SafeParcelWriter.writeFloat(parcel, 4, this.centerY);
        SafeParcelWriter.writeFloat(parcel, 5, this.width);
        SafeParcelWriter.writeFloat(parcel, 6, this.height);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzcf);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzcg);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzch, i, false);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzbs);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzbt);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzbu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
