package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "FaceSettingsParcelCreator")
@Reserved({1})
public final class zzc extends AbstractSafeParcelable {
    public static final Creator<zzc> CREATOR = new zzd();
    @Field(id = 2)
    public int mode;
    @Field(id = 3)
    public int zzby;
    @Field(id = 5)
    public boolean zzbz;
    @Field(id = 4)
    public int zzca;
    @Field(id = 6)
    public boolean zzcb;
    @Field(defaultValue = "-1", id = 7)
    public float zzcc;

    @Constructor
    public zzc(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) int i3, @Param(id = 5) boolean z, @Param(id = 6) boolean z2, @Param(id = 7) float f) {
        this.mode = i;
        this.zzby = i2;
        this.zzca = i3;
        this.zzbz = z;
        this.zzcb = z2;
        this.zzcc = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.writeInt(parcel, 3, this.zzby);
        SafeParcelWriter.writeInt(parcel, 4, this.zzca);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbz);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcb);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzcc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
