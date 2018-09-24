package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "FetchConfigIpcRequestCreator")
@Reserved({1})
public final class zzab extends AbstractSafeParcelable {
    public static final Creator<zzab> CREATOR = new zzac();
    @Field(getter = "getPackageName", id = 2)
    private final String mPackageName;
    @Field(getter = "getAnalyticsUserProperties", id = 10)
    private final List<zzl> zzaa;
    @Field(getter = "getSdkVersion", id = 9)
    private final int zzh;
    @Field(getter = "getFetchedConfigAgeSeconds", id = 12)
    private final int zzi;
    @Field(getter = "getActiveConfigAgeSeconds", id = 11)
    private final int zzj;
    @Field(getter = "getCacheExpirationInSeconds", id = 3)
    private final long zzu;
    @Field(getter = "getCustomVariablesHolder", id = 4)
    private final DataHolder zzv;
    @Field(getter = "getGmpProjectId", id = 5)
    private final String zzw;
    @Field(getter = "getAppInstanceId", id = 6)
    private final String zzx;
    @Field(getter = "getAppInstanceIdToken", id = 7)
    private final String zzy;
    @Field(getter = "getRegisteredHiddenNamespaces", id = 8)
    private final List<String> zzz;

    @Constructor
    public zzab(@Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) DataHolder dataHolder, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) String str4, @Param(id = 8) List<String> list, @Param(id = 9) int i, @Param(id = 10) List<zzl> list2, @Param(id = 11) int i2, @Param(id = 12) int i3) {
        this.mPackageName = str;
        this.zzu = j;
        this.zzv = dataHolder;
        this.zzw = str2;
        this.zzx = str3;
        this.zzy = str4;
        this.zzz = list;
        this.zzh = i;
        this.zzaa = list2;
        this.zzj = i2;
        this.zzi = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.mPackageName, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzu);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzv, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzw, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzx, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzy, false);
        SafeParcelWriter.writeStringList(parcel, 8, this.zzz, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzh);
        SafeParcelWriter.writeTypedList(parcel, 10, this.zzaa, false);
        SafeParcelWriter.writeInt(parcel, 11, this.zzj);
        SafeParcelWriter.writeInt(parcel, 12, this.zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
