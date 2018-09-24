package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "ShippingAddressRequirementsCreator")
public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public static final Creator<ShippingAddressRequirements> CREATOR = new zzan();
    @Field(id = 1)
    ArrayList<String> zzen;

    public final class Builder {
        private final /* synthetic */ ShippingAddressRequirements zzeo;

        private Builder(ShippingAddressRequirements shippingAddressRequirements) {
            this.zzeo = shippingAddressRequirements;
        }

        public final Builder addAllowedCountryCode(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "allowedCountryCode can't be null or empty! If you don't have restrictions, just leave it unset.");
            if (this.zzeo.zzen == null) {
                this.zzeo.zzen = new ArrayList();
            }
            this.zzeo.zzen.add(str);
            return this;
        }

        public final Builder addAllowedCountryCodes(@NonNull Collection<String> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("allowedCountryCodes can't be null or empty! If you don't have restrictions, just leave it unset.");
            }
            if (this.zzeo.zzen == null) {
                this.zzeo.zzen = new ArrayList();
            }
            this.zzeo.zzen.addAll(collection);
            return this;
        }

        public final ShippingAddressRequirements build() {
            return this.zzeo;
        }
    }

    @Constructor
    ShippingAddressRequirements(@Param(id = 1) ArrayList<String> arrayList) {
        this.zzen = arrayList;
    }

    private ShippingAddressRequirements() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, this.zzen, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final ArrayList<String> getAllowedCountryCodes() {
        return this.zzen;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
