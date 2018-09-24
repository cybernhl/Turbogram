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

@Class(creator = "FetchConfigIpcResponseCreator")
@Reserved({1})
public final class zzad extends AbstractSafeParcelable {
    public static final Creator<zzad> CREATOR = new zzae();
    @Field(getter = "getStatusCode", id = 2)
    private final int zzab;
    @Field(getter = "getConfigsHolder", id = 3)
    private final DataHolder zzac;
    @Field(getter = "getExperimentPayloadsHolder", id = 5)
    private final DataHolder zzad;
    @Field(getter = "getThrottleEndTimeMillis", id = 4)
    private final long zzr;

    @Constructor
    public zzad(@Param(id = 2) int i, @Param(id = 3) DataHolder dataHolder, @Param(id = 4) long j, @Param(id = 5) DataHolder dataHolder2) {
        this.zzab = i;
        this.zzac = dataHolder;
        this.zzr = j;
        this.zzad = dataHolder2;
    }

    public final int getStatusCode() {
        return this.zzab;
    }

    public final long getThrottleEndTimeMillis() {
        return this.zzr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzab);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzac, i, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzr);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzad, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final DataHolder zzi() {
        return this.zzac;
    }

    public final DataHolder zzj() {
        return this.zzad;
    }

    public final void zzk() {
        if (this.zzac != null && !this.zzac.isClosed()) {
            this.zzac.close();
        }
    }

    public final void zzl() {
        if (this.zzad != null && !this.zzad.isClosed()) {
            this.zzad.close();
        }
    }
}
