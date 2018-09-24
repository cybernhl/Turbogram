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

@Class(creator = "AddressCreator")
@Reserved({1})
@Deprecated
public final class zza extends AbstractSafeParcelable {
    public static final Creator<zza> CREATOR = new zzb();
    @Field(id = 2)
    private String name;
    @Field(id = 3)
    private String zzf;
    @Field(id = 4)
    private String zzg;
    @Field(id = 5)
    private String zzh;
    @Field(id = 6)
    private String zzi;
    @Field(id = 7)
    private String zzj;
    @Field(id = 8)
    private String zzk;
    @Field(id = 9)
    private String zzl;
    @Field(id = 10)
    private String zzm;
    @Field(id = 11)
    private boolean zzn;
    @Field(id = 12)
    private String zzo;

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzh, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzm, false);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzn);
        SafeParcelWriter.writeString(parcel, 12, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    zza() {
    }

    @Constructor
    zza(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) String str8, @Param(id = 10) String str9, @Param(id = 11) boolean z, @Param(id = 12) String str10) {
        this.name = str;
        this.zzf = str2;
        this.zzg = str3;
        this.zzh = str4;
        this.zzi = str5;
        this.zzj = str6;
        this.zzk = str7;
        this.zzl = str8;
        this.zzm = str9;
        this.zzn = z;
        this.zzo = str10;
    }
}
