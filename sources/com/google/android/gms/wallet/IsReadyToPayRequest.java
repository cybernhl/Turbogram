package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "IsReadyToPayRequestCreator")
@Reserved({1})
public final class IsReadyToPayRequest extends AbstractSafeParcelable {
    public static final Creator<IsReadyToPayRequest> CREATOR = new zzr();
    @Field(id = 2)
    ArrayList<Integer> zzaj;
    @Field(id = 4)
    private String zzbv;
    @Field(id = 5)
    private String zzbw;
    @Field(id = 6)
    ArrayList<Integer> zzbx;
    @Field(id = 7)
    boolean zzby;
    @Field(id = 8)
    private String zzbz;

    public final class Builder {
        final /* synthetic */ IsReadyToPayRequest zzca;

        private Builder(IsReadyToPayRequest isReadyToPayRequest) {
            this.zzca = isReadyToPayRequest;
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (this.zzca.zzaj == null) {
                this.zzca.zzaj = new ArrayList();
            }
            this.zzca.zzaj.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            boolean z = (collection == null || collection.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "allowedCardNetworks can't be null or empty. If you want the defaults, leave it unset.");
            if (this.zzca.zzaj == null) {
                this.zzca.zzaj = new ArrayList();
            }
            this.zzca.zzaj.addAll(collection);
            return this;
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (this.zzca.zzbx == null) {
                this.zzca.zzbx = new ArrayList();
            }
            this.zzca.zzbx.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection<Integer> collection) {
            boolean z = (collection == null || collection.isEmpty()) ? false : true;
            Preconditions.checkArgument(z, "allowedPaymentMethods can't be null or empty. If you want the default, leave it unset.");
            if (this.zzca.zzbx == null) {
                this.zzca.zzbx = new ArrayList();
            }
            this.zzca.zzbx.addAll(collection);
            return this;
        }

        public final Builder setExistingPaymentMethodRequired(boolean z) {
            this.zzca.zzby = z;
            return this;
        }

        public final IsReadyToPayRequest build() {
            return this.zzca;
        }
    }

    IsReadyToPayRequest() {
    }

    @Constructor
    IsReadyToPayRequest(@Param(id = 2) ArrayList<Integer> arrayList, @Param(id = 4) String str, @Param(id = 5) String str2, @Param(id = 6) ArrayList<Integer> arrayList2, @Param(id = 7) boolean z, @Param(id = 8) String str3) {
        this.zzaj = arrayList;
        this.zzbv = str;
        this.zzbw = str2;
        this.zzbx = arrayList2;
        this.zzby = z;
        this.zzbz = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzaj, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbv, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbw, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbx, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzby);
        SafeParcelWriter.writeString(parcel, 8, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzaj;
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzbx;
    }

    public final boolean isExistingPaymentMethodRequired() {
        return this.zzby;
    }

    public static IsReadyToPayRequest fromJson(String str) {
        Builder newBuilder = newBuilder();
        newBuilder.zzca.zzbz = (String) Preconditions.checkNotNull(str, "isReadyToPayRequestJson cannot be null!");
        return newBuilder.build();
    }

    public final String toJson() {
        return this.zzbz;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
