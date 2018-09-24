package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "GetSaveInstrumentDetailsResponseCreator")
public final class zzl extends AbstractSafeParcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    @Field(id = 1)
    private String[] zzez;
    @Field(id = 2)
    private int[] zzfa;
    @Field(id = 3)
    private RemoteViews zzfb;
    @Field(id = 4)
    private byte[] zzfc;

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zzez, false);
        SafeParcelWriter.writeIntArray(parcel, 2, this.zzfa, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzfb, i, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzfc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Constructor
    public zzl(@Param(id = 1) String[] strArr, @Param(id = 2) int[] iArr, @Param(id = 3) RemoteViews remoteViews, @Param(id = 4) byte[] bArr) {
        this.zzez = strArr;
        this.zzfa = iArr;
        this.zzfb = remoteViews;
        this.zzfc = bArr;
    }

    private zzl() {
    }
}
